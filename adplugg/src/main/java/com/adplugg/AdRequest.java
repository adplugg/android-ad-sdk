package com.adplugg;

import android.net.Uri;
import android.util.Log;

/**
 *  AdRequest
 *
 *  Object that describes an AdRequest.
 *
 *  Valid AdRequests contain:
 *  - Access code
 *  - Zone
 *
 *  If no Access Code is directly set on the Builder, it will default to the value set to AddPlugg.getAccessCode()
 *
 *  @author justin.fiedler
 */
public class AdRequest {
    private static final String LOG_TAG = AdRequest.class.getSimpleName();

    private static final String AD_SDK_VERSION = "1.1";
    private static final String AD_URL_TEMPLATE = "http://www.adplugg.com/serve/%s/html/%s/index.html";

    private String mAccessCode;
    private String mZone;

    public AdRequest(String accessCode, String zone) {
        mAccessCode = accessCode;
        mZone = zone;
    }

    /**
     * Returns url where this AdRequest can be loaded from
     * @return
     */
    public String getUrl() {
        return appendZoneParameterToUrl(getBasedUrl(), mZone);
    }

    /**
     * Returns the base API url for the SDK
     *
     * e.g. "http://www.adplugg.com/serve/ACCESS_CODE/html/1.1/index.html";
     *
     * @return
     */
    private String getBasedUrl() {
        return String.format(
                AD_URL_TEMPLATE,
                mAccessCode,
                AD_SDK_VERSION
        );
    }

    /**
     * Given a url appends the Zone API parameter
     *
     * e.g.
     *
     * "http://www.adplugg.com/serve/ACCESS_CODE/html/1.1/index.html"
     *
     * becomes
     *
     * "http://www.adplugg.com/serve/ACCESS_CODE/html/1.1/index.html&zn=footer"
     *
     * @param baseUrl
     * @param zone
     * @return
     */
    private String appendZoneParameterToUrl(String baseUrl, String zone) {
        return Uri
                .parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("zn", zone.toLowerCase())
                .toString();
    }

    /**
     * AdRequest.Builder
     *
     * Helps construct AdRequests
     */
    public static class Builder {
        private String mAccessCode;
        private String mZone;

        /**
         * Set the Zone of the AdRequest
         * @param zone
         * @return
         */
        public Builder setZone(String zone) {
            mZone = zone;
            return this;
        }

        /**
         * Set the Access Code of the AdRequest
         * @param accessCode
         * @return
         */
        public Builder setAccessCode(String accessCode) {
            mAccessCode = accessCode;
            return this;
        }

        /**
         * Returns true if Builder has all necessary information to build an AdRequest
         * @return
         */
        public boolean canBuild() {
            String accessCode = mAccessCode;
            if (isEmpty(accessCode)) {
                accessCode = AdPlugg.getAccessCode();
            }

            return !isEmpty(accessCode) && !isEmpty(mZone);
        }

        /**
         * Build an AdRequest from the current Builder configuration
         * @return
         * @throws AdRequestException
         */
        public AdRequest build() throws AdRequestException {
            if (canBuild()) {
                return new AdRequest(getAccessCode(), getZone());
            } else {
                throw new AdRequestException("Unable to build AdRequest. Missing AccessCode or Zone.");
            }
        }

        /**
         * Returns the current Zone
         * @return
         */
        private String getZone() {
            return mZone;
        }

        /**
         * Retruns the current Access Code
         * @return
         */
        private String getAccessCode() {
            String accessCode = mAccessCode;
            if (isEmpty(accessCode)) {
                accessCode = AdPlugg.getAccessCode();
            }

            return accessCode;
        }

        /**
         * Returns true is @string is null or empty
         *
         * @param string
         * @return
         */
        private boolean isEmpty(String string) {
            return string == null || string.isEmpty();
        }
    }
}
