package com.adplugg.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.adplugg.AdRequest;
import com.adplugg.R;

/**
 * Created by justin on 6/5/16.
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
            } finally {
                a.recycle();
            }
        }

        mAdRequest = adBuilder.build();
    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//    }

    public void load() {
        loadAd(mAdRequest);
    }

    public void loadAd(AdRequest adRequest) {
        String adUrl = adRequest.getUrl();

        Log.d(LOG_TAG, adUrl);

        loadUrl(adUrl);
    }

//    public void setZone(Zone zone) {
//        mZone = zone;
//        invalidate();
//        requestLayout();
//    }
}
