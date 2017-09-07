package com.potter.harry.quakereport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class today extends Fragment {
    int READ_BLOCK_SIZE=100;
    public View alone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        alone = getActivity().getLayoutInflater().inflate(R.layout.activity_today,container, false);




new CountDownTimer(5000,1000){
           public void onTick(long millisInFuture){}
            public void onFinish(){
            SharedPreferences weather_one = getActivity().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = weather_one.edit();
            int a = 0;
            int y = weather_one.getInt("tab_number", a);
            if (y == 0) {

                TextView an = (TextView) alone.findViewById(R.id.texta);
                an.setText("heye");


            }
            if (y == 1) {

                TextView an = (TextView) alone.findViewById(R.id.texta);
                an.setText("heye!!!!!!!!!!!!!!!");


            }}
        }.start();
        return alone;

    }




}