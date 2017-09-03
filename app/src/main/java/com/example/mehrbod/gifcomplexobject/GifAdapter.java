package com.example.mehrbod.gifcomplexobject;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mehrbod on 8/30/17.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.MyViewHolder> {
    private HashMap<ImageView, GifRunnable> mModelMap;
    private ArrayList<GifModel> mModelList;

    // activity to send to runnable
    private Activity mActivity;

    // thread
    private ExecutorService executorService;
    private GifRunnable runnable;

    public GifAdapter(Activity activity, ArrayList<GifModel> modelList) {
        mModelMap = new HashMap<>();
        this.mModelList = modelList;
        this.executorService = Executors.newCachedThreadPool();
        this.mActivity = activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.myImageView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GifRunnable gifRunnable = new GifRunnable(mActivity, holder.mImageView, mModelList.get(position));
        mModelMap.put(holder.mImageView, gifRunnable);
        executorService.execute(gifRunnable);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
        mModelMap.get(holder.mImageView).release();
        mModelMap.remove(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }
}
