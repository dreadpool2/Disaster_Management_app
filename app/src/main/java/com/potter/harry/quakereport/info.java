package com.potter.harry.quakereport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class info extends AppCompatActivity {
    public  final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";
    public  final String USGS_REQAUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        EditText df = (EditText)findViewById(R.id.country);
        df.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        NumberPicker numb = (NumberPicker)findViewById(R.id.numb);
        numb.setMaxValue(10);
        numb.setMinValue(1);
        numb.setWrapSelectorWheel(true);
        NumberPicker year = (NumberPicker) findViewById(R.id.year);
        NumberPicker day = (NumberPicker) findViewById(R.id.day);
        NumberPicker month = (NumberPicker) findViewById(R.id.month);
        year.setMaxValue(2017);
        year.setMinValue(1950);
        day.setMaxValue(31);
        day.setMinValue(1);
        month.setMaxValue(12);
        month.setMinValue(1);

        NumberPicker yr = (NumberPicker) findViewById(R.id.yr);
        NumberPicker dd = (NumberPicker) findViewById(R.id.dd);
        NumberPicker mo = (NumberPicker) findViewById(R.id.mo);
        yr.setMaxValue(2017);
        yr.setMinValue(1950);
        dd.setMaxValue(31);
        dd.setMinValue(1);
        mo.setMaxValue(12);
        mo.setMinValue(1);


        numb.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
        public void onValueChange(NumberPicker numb,int oldval,int newvl) {

                int aloo = numb.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("num1", aloo);
                editor.apply();
            }});


        year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker year,int oldval,int newvl) {

                int aloo = year.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("year", aloo);
                editor.apply();
            }});

        day.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker day,int oldval,int newvl) {

                int aloo = day.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("day", aloo);
                editor.apply();
            }});
        month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker month,int oldval,int newvl) {

                int aloo = month.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("month", aloo);
                editor.apply();
            }});

        yr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker yr,int oldval,int newvl) {

                int aloo = yr.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("yr", aloo);
                editor.apply();
            }});

        dd.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker dd,int oldval,int newvl) {

                int aloo = dd.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("dd", aloo);
                editor.apply();
            }});
        mo.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker mo,int oldval,int newvl) {

                int aloo = mo.getValue();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("mo", aloo);
                editor.apply();
            }});


    }
    public void tick(View view){

        EditText edt = (EditText)findViewById(R.id.country);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("country", edt.getText().toString());
        editor.apply();
        String txta="";

        try {
            FileOutputStream fileout = openFileOutput("mytext1023file.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(edt.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent i = new Intent(this,quakes.class);
        startActivity(i);
        overridePendingTransition(0,R.anim.fade_out);
        finish();


    }
}
