package com.adplugg;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * AdView
 *
 * View component that loads AdRequests and displays them
 *
 * Some attributes can be set in the layout XML
 * - ap_accessCode - The access code to use for this AdView. Defaults to AdPLugg.getAccessCode().
 * - ap_zone - The zone for this AdView
 * - ap_autoLoad - If all parameters are available to load the AdRequest, then load the AdRequest immediately when shown
 *
 * Programmatically you can:
 * - setAdRequest() to be loaded later
 * - load AdRequests and Builders via the load() methods
 *
 * @author justin.fiedler
 * @date 6/5/16
 */
public class AdView extends WebView {
    public static final String LOG_TAG = AdView.class.getSimpleName();

    private AdRequest mAdRequest;

    public AdView(Context context) {
        super(context);
        init(null);
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public AdView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Deprecated
    public AdView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setAttrs(attrs);

//        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("MyApplication", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId() );
                return true;
            }
        });

        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void setAttrs(AttributeSet attrs) {
        AdRequest.Builder adBuilder = new AdRequest.Builder();
        boolean autoLoad = false;

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.AdView,
                    0, 0);

            try {
                String accessCode = a.getString(R.styleable.AdView_ap_accessCode);
                if (accessCode != null && !accessCode.equals("")) {
                    adBuilder.setAccessCode(accessCode);
                }

                String zone = a.getString(R.styleable.AdView_ap_zone);
                if (zone != null && !zone.isEmpty()) {
                    adBuilder.setZone(zone);
                }

                autoLoad = a.getBoolean(R.styleable.AdView_ap_autoLoad, true);
            } finally {
                a.recycle();
            }
        }

        if (adBuilder.canBuild()) {
            try {
                AdRequest adRequest = adBuilder.build();
                setAdRequest(adRequest);

                // Autoload the AdRequest from the Attributes
                if (autoLoad) {
                    load();
                }
            } catch (AdRequestException ex) {
                // unable to build AdRequest log error
                Log.e(LOG_TAG, "Unable to build AdRequest.", ex);
            }
        }
    }

    public void setAdRequest(AdRequest adRequest) {
        mAdRequest = adRequest;
    }

    public void clearAdRequest() {
        mAdRequest = null;
    }


    /**
     * Loads the current AdRequest
     */
    public void load() {
        load(mAdRequest);
    }

    /**
     * Loads the given AdRequest in the view
     *
     * @param adRequest
     */
    public void load(AdRequest adRequest) {
        if (adRequest != null) {
            loadUrl(adRequest.getUrl());
        }
    }

    /**
     * Load the AdRequest produced by the given AdRequestBuilder
     *
     * @param adRequestBuilder
     */
    public void load(AdRequest.Builder adRequestBuilder) {
        try {
            String adUrl = adRequestBuilder.build().getUrl();

            loadUrl(adUrl);
        } catch (AdRequestException ex) {
            // unable to build AdRequest log error
            Log.e(LOG_TAG, "Unable to build AdRequest.", ex);
        }
    }

    @Override
    public void loadUrl(String url) {
        Log.d(LOG_TAG, "[loadUrl] " + url);
        super.loadUrl(url);
    }
}
