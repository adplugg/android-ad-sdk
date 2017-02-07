package com.adplugg.sample;

import android.app.Application;
import android.util.Log;

import com.adplugg.AdPlugg;

/**
 * ExampleApplication for AdPlugg Ad SDK
 *
 * - Initializes AdPlugg by setting access code
 *
 * @author justin.fiedler
 * @date 2/6/17
 */

public class ExampleApplication extends Application {
    private static final String LOG_TAG = ExampleApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(LOG_TAG, "[onCreate] Initialize AdPlugg access code at Application.onCreate()");

        // You can initialize AdPlugg once at Application creation
        AdPlugg.initialize(getApplicationContext(), getString(R.string.adplugg_access_code));
    }
}
