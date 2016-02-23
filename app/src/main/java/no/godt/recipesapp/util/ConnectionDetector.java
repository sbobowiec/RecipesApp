/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;

import no.godt.recipesapp.GodtRecipesApp;

@EBean
public class ConnectionDetector {

    @App
    GodtRecipesApp mApp;

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mApp.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
