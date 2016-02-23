/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EApplication;

@EApplication
public class GodtRecipesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupPicasso();
    }

    private void setupPicasso() {
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        picassoBuilder.downloader(new OkHttpDownloader(new OkHttpClient()));

        Picasso picasso = picassoBuilder.build();
        Picasso.setSingletonInstance(picasso);
    }

}
