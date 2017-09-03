package com.example.mehrbod.gifcomplexobject;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.mehrbod.gifcomplexobject.FrameGrabber.media.FrameGrabber;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by mehrb on 8/29/2017.
 */

public class GifModel {
    // initializer
    private String mPath;

    // attributes
    private double mFrameRate;
    private long mDuration;
    private long mTotalFrames;

    // current frame for return
    private Bitmap mCurrentFrame;
    private int mCurrentFrameNumber = 0;
    private int mEachFrameLengthInUs = 0;
    private long mEachFrameLengthInMs = 0;

    // Metadata Retriever object
    private FFmpegMediaMetadataRetriever mMetadataRetriever;

    // temporary variables
    private long mStartTime = 0;

    // FrameGrabber - the lib which we use to get the frames
    private FrameGrabber mFrameGrabber;

    // constructors
    public GifModel(String path) {
        this.mPath = path;
        prepare();
    }

    // prepare methods for initializing instance variables
    private void prepare() {
        mFrameGrabber = new FrameGrabber();
        mFrameGrabber.setDataSource(mPath);
        mFrameGrabber.setTargetSize(300, 300);
        mFrameGrabber.init();


        mMetadataRetriever = new FFmpegMediaMetadataRetriever();
        mMetadataRetriever.setDataSource(mPath);

        // calculate and log mFrameRate - frame rate of the video

        String sTemp = mMetadataRetriever.extractMetadata(
                FFmpegMediaMetadataRetriever.METADATA_KEY_FRAMERATE
        );
        mFrameRate = Double.parseDouble(sTemp);
        Log.d("GifModel", "prepare-FrameRate = " + mFrameRate);

        // calculate and log mDuration - duration of the video
        sTemp = mMetadataRetriever.extractMetadata(
                FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION
        );
        mDuration = Long.parseLong(sTemp);
        Log.d("GifModel", "prepare-Duration = " + mDuration);

        // calculate and log total number of frames for use of retrieving the frames
        mTotalFrames = (long) (((double) mDuration / 1000.0) * mFrameRate);
        Log.d("GifModel", "prepare-TotalFrames = " + mTotalFrames);

        // calculate and log each frame length
        mEachFrameLengthInUs = (int) (((double)mDuration / mTotalFrames) * 1000.0);
        mEachFrameLengthInMs = mEachFrameLengthInUs / 1000;
        Log.d("GifModel", "prepare-EachFrameLength = " + mEachFrameLengthInUs);

        mMetadataRetriever.release();
        mMetadataRetriever = null;

        // preparing the first frame
        mCurrentFrame = mFrameGrabber.getFrameAtTime(0);
    }

    // returning the next frame we have to display
    public Bitmap getNextFrame() {
        mStartTime = System.currentTimeMillis();
        return mCurrentFrame;
    }


    // prepare the mCurrentFrame for the next frame
    public void scheduleNextFrame() {
        if (mCurrentFrameNumber == mTotalFrames) {
            mCurrentFrameNumber = 0;
        }

//        ensureMetadataRetriever();
//        mCurrentFrame = mMetadataRetriever.getFrameAtTime(
//                mCurrentFrameNumber * mEachFrameLengthInUs, MediaMetadataRetriever.OPTION_CLOSEST
//        );

        try {
            mCurrentFrame = mFrameGrabber.getFrameAtTime(mCurrentFrameNumber * mEachFrameLengthInUs);
            Log.d("FuckingFrame", "" + mCurrentFrameNumber);
        } catch (Exception e) {
            Log.d("FuckingFrame", "" + mCurrentFrameNumber);
            mCurrentFrameNumber = 0;
            mCurrentFrame = mFrameGrabber.getFrameAtTime(mCurrentFrameNumber * mEachFrameLengthInUs);
        }
        mCurrentFrameNumber++;
        Log.d("grab", "finished");
    }
    
    /**
     *  if it is time to change the frame it returns true
     *  else it returns false
     */
    public boolean isTimeToChange() {
        if ((System.currentTimeMillis() - mStartTime) > mEachFrameLengthInMs) {
            return true;
        }
        return false;
    }

    // returns the stop time between each frame
    public long stopDuration() {
        return mEachFrameLengthInMs;
    }

    // for ensuring to release the retriever before the second loop of displaying the gif
    public void releaseRetriever() {
        if (mMetadataRetriever != null) {
            mMetadataRetriever.release();
            mMetadataRetriever = null;
        }
    }

    // for ensuring the metadata retriever working well
    private void ensureMetadataRetriever() {
        if (mMetadataRetriever == null) {
            mMetadataRetriever = new FFmpegMediaMetadataRetriever();
            mMetadataRetriever.setDataSource(mPath);
        }
    }

    // use this before finishing the work
    public void release() {
        releaseRetriever();
        mFrameGrabber.release();
    }
}
