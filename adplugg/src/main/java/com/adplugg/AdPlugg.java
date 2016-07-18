package com.adplugg;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by justin on 6/5/16.
 */
public class AdPlugg {
    private static String mAccessCode;
    private static WeakReference<Context> mContextRef;

    public static void initialize(Context context, String accessCode) {
        mContextRef = new WeakReference<Context>(context);
        mAccessCode = accessCode;
    }

    public static String getAccessCode() {
        return mAccessCode;
    }

    private static Context getContext() {
        return mContextRef.get();
    }
}
