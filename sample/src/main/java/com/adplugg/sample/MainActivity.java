package com.adplugg.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adplugg.AdRequest;
import com.adplugg.AdPlugg;
import com.adplugg.view.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdView vAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdPlugg.initialize(getApplicationContext(), getString(R.string.adplugg_access_code));

        vAdView = (AdView) findViewById(R.id.vAdView);

        AdRequest myAdRequest = new AdRequest.Builder().setZone(getFooterZone()).build();

        vAdView.loadAd(myAdRequest);
    }

    private List<String> getZones() {
        return Arrays.asList(getFooterZone());
    }

    private String getFooterZone() {
        return "footer";
    }
}
