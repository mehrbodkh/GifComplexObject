package com.example.mehrbod.gifcomplexobject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by mehrbod on 8/30/17.
 */

public class GifRunnable implements Runnable {
    private GifModel mGifModel;
    private Activity mActivity;
    private ImageView mImageView;

    private boolean finish = false;

    public GifRunnable(Activity activity, ImageView imageView, GifModel gifModel) {
        this.mActivity = activity;
        this.mImageView = imageView;
        this.mGifModel = gifModel;
    }

    @Override
    public void run() {
        while (!finish) {
            if (mGifModel.isTimeToChange()) {
                final Bitmap frame = mGifModel.getNextFrame();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(frame);
                        Log.d("grab", "put");
                    }
                });
                Log.d("grab", "started");
                mGifModel.scheduleNextFrame();
            }

            try {
                Thread.sleep(mGifModel.stopDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mGifModel.release();
        mGifModel = null;
    }

    public void release() {
        finish = true;
    }
}
