package com.potter.harry.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class mapearthquake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapearthquake);

        WebView web = (WebView)findViewById(R.id.web);
        web.getSettings();
        WebSettings webSettings = web.getSettings();
        webSettings.setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://earthquake.usgs.gov/earthquakes/eventpage/ci37919112#map");



    }
}
