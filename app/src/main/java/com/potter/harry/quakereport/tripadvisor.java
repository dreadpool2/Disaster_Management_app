package com.potter.harry.quakereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class tripadvisor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripadvisor);



        getSupportActionBar().setTitle("Your Trips");




        //so the trip advisor is to be built


        // your trips

        //list view containing all trips , not that hard

        // a fab for new trip

        //a trip layout with head consisting of a name and then below the trip
        // a button in the view (remove)icon





    }
    public void newtrip(View view){

        Intent i = new Intent(this,new_trip.class);
        startActivity(i);


    }
}
