package com.potter.harry.quakereport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class thirdday extends Fragment {
    int READ_BLOCK_SIZE=100;
    public View alone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        alone = getActivity().getLayoutInflater().inflate(R.layout.activity_thirdday,container, false);



        return alone;
    }




}