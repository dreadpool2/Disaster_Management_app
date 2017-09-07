package com.potter.harry.quakereport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
public  int a=0;
private AdView mAdView;
private Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-1887633011875798~9441072450");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Log.d("time",String.valueOf(System.currentTimeMillis()));

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        TextView txt = (TextView)findViewById(R.id.hero);
        Typeface a = Typeface.createFromAsset(getAssets(),  "fonts/futureearth.ttf");
        txt.setTypeface(a);

        final CollapsingToolbarLayout mapbar=(CollapsingToolbarLayout)findViewById(R.id.collapse);
        mapbar.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));
        mapbar.setTitle("Disaster Management");

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;

                } else if (isShow) {
                    isShow = false;

                }
            }
        });
    }


    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
    public void events(View view){
        Animation first = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        Animation second = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);

        Intent i =new Intent(this,quakes.class);
        startActivity(i);

        overridePendingTransition(0,R.anim.fade_out);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("num1", a);
        editor.apply();



    }
    public void weather(View view){

        Intent i =new Intent(this,weather.class);
        startActivity(i);

        overridePendingTransition(0,R.anim.fade_out);




    }

    public void tripadvisor(View view){

        Intent i =new Intent(this,tripadvisor.class);
        startActivity(i);

        overridePendingTransition(0,R.anim.fade_out);




    }
}