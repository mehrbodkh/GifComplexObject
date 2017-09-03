package com.example.mehrbod.gifcomplexobject;

import android.widget.ImageView;

/**
 * Created by mehrbod on 8/30/17.
 */

public class GifObject {
    public GifObject(GifModel gifModel, ImageView imageView) {
        this.gifModel = gifModel;
        this.imageView = imageView;
    }

    public GifModel gifModel;
    public ImageView imageView;
}
