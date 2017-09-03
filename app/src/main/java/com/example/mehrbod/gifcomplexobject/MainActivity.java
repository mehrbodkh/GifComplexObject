package com.example.mehrbod.gifcomplexobject;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permissions, 1);

        ArrayList<GifModel> modelList = new ArrayList<>();
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));
        modelList.add(new GifModel("/storage/emulated/0/Download/ge.mp4"));

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setAdapter(new GifAdapter(this, modelList));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    }
}
