package com.adplugg;

import android.net.Uri;

/**
 * Created by justin on 6/5/16.
 */
public class AdRequest {
    private static final String AD_SDK_VERSION = "1.1";
    private static final String AD_URL_TEMPLATE = "http://www.adplugg.com/serve/%s/html/%s/index.html";

    public enum Zone {
        FOOTER
    }

    private String mAccessCode;
    private String mZone;

    public AdRequest(String accessCode, String zone) {
        mAccessCode = accessCode;
        mZone = zone;
    }

    public String getUrl() {
        return appendZoneParameterToUrl(getBasedUrl(), mZone);
    }

    private String getBasedUrl() {
        return String.format(
                AD_URL_TEMPLATE,
                mAccessCode,
                AD_SDK_VERSION
        );
    }

    private String appendZoneParameterToUrl(String baseUrl, String zone) {
        return Uri
                .parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("zn", zone.toLowerCase())
                .toString();
    }
    public static class Builder {
        private String mAccessCode;
        private String mZone;

        public Builder setZone(String zone) {
            mZone = zone;
            return this;
        }

        public Builder setAccessCode(String accessCode) {
            mAccessCode = accessCode;
            return this;
        }

        public AdRequest build() {
            if (mAccessCode == null) {
                mAccessCode = AdPlugg.getAccessCode();
            }
            return new AdRequest(mAccessCode, mZone);
        }
    }
}
