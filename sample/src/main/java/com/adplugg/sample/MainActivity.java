package com.adplugg.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.adplugg.AdRequest;
import com.adplugg.AdRequestException;
import com.adplugg.AdView;

import java.util.Arrays;
import java.util.List;

/**
 * MainActivity
 *
 * - Sets content view to activity_main
 * - Loads an AdRequest into an AdView
 *
 * @author justin.fiedler
 * @date 2/6/17
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AdView vAdViewHeader;
    private AdView vAdViewCenter;
    private AdView vAdViewFooter;

    public static class Zones {
        public static String HEADER = "header";
        public static String FOOTER = "footer";
        public static String CENTER = "center";

        private List<String> getList() {
            return Arrays.asList(HEADER, FOOTER, CENTER);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "[onCreate] Load AdRequests in AdViews");

        // Get a reference to the AdViews
        vAdViewHeader = (AdView) findViewById(R.id.vAdViewHeader);
        vAdViewCenter = (AdView) findViewById(R.id.vAdViewCenter);
        vAdViewFooter = (AdView) findViewById(R.id.vAdViewFooter);

        // The AdViewHeader is constructed entirely in the layout - activity_main.xml
        // by setting the 'ap_zone' attribute and default 'autoLoad' set to true

        // You can load a AdRequest.Builder directly
        vAdViewCenter.load(new AdRequest.Builder().setZone(Zones.CENTER));

        // Or you can build the AdRequest
        // Create an AdRequest for the desired zone
        try {
            AdRequest myAdRequest = new AdRequest.Builder().setZone(Zones.FOOTER).build();

            // Load the request in the AdView
            vAdViewFooter.load(myAdRequest);
        } catch (AdRequestException ex) {
            Log.e(LOG_TAG, "Unable to load Footer zone", ex);
        }
    }



}
