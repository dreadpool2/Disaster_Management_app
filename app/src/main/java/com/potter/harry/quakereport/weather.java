package com.potter.harry.quakereport;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class weather extends AppCompatActivity {
    TabLayout tabLayout;
    public int yu=0;
    int ad=0;
    public int lmn=0;
    public String ico;
    public int i=0;
    public int yop=0;
    public int hyu=0;
    public int yop_0=0;
        public TimeZone sd = TimeZone.getTimeZone("Beijing/China");
    public int yop_1=0;

    public int yop_2=0;

    public int yop_3=0;
    public int id5=0;

    public     String state="";

    public int id1=0;
    public int yak =0;
    public int id2=0;
    public static String country="";

    public int id3=0;

    public int id4=0;
    public int id_6=0;
    public static String agf="";
    public int id_63=0;

    public static long sunrise=0;
    public static long sunset=0;

    public static String city="";
    public String txtas="";
    public  ListView forecasts;
    public String city_1="";
    public String country_3="";
    public List<Address> addresses = null;

    public String URL_1= "http://api.openweathermap.org/data/2.5/weather?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd";
    public String URL_2= "http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd";
    public String sad="";
    public String sad2="";

    public double lat=0;

    public double lon=0;
    public static String descripation="";

    public Handler mHandler;
    TabLayout.Tab FIRST,SECOND,THIRD,FOURTH,FIFTH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Log.d("1st",URL_1);


        Log.d("2ND",URL_2);
        ImageView f = (ImageView)findViewById(R.id.settiangs);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(weather.this,p);
            }
        });













        Runnable m_Runnablae = new Runnable() {
            public void run()

            {

                EarthquakeAsyncTass vg = new EarthquakeAsyncTass();
                vg.execute("http://api.geonames.org/timezoneJSON?lat="+lat+"&"+"lng="+lon+"&username=sanyogghosh@yahoo.co.in");

            }
        };

        m_Runnablae.run();





                //SEARCH BOX VISIBILITY....
        LinearLayout box = (LinearLayout)findViewById(R.id.box);
        box.setVisibility(View.GONE);

        mAdapter = new AndroidFlavorAdapter_two(this, new ArrayList<AndroidFlavor_two>());
        forecasts = (ListView) findViewById(R.id.forecasts);
        forecasts.setAdapter(mAdapter);
        forecasts.setDivider(null);

        final FloatingActionButton bg = (FloatingActionButton) findViewById(R.id.done);
        final TextView bv = (TextView)findViewById(R.id.cplace);

        bg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAdapter.clear();

                if(yak%2==0){
                    LinearLayout ad = (LinearLayout)findViewById(R.id.box);
                    ad.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down));

                    ad.setVisibility(View.VISIBLE);

                    bv.setText("Done");}
                if(yak%2!=0){

                    LinearLayout ad = (LinearLayout)findViewById(R.id.box);
                    ad.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up));

                    ad.setVisibility(View.GONE);

                    ProgressBar s = (ProgressBar)findViewById(R.id.progress);
                    s.setVisibility(View.VISIBLE);
                    bv.setText("Change Place");

                    EditText as = (EditText)findViewById(R.id.city_1);


                    EditText b = (EditText) findViewById(R.id.country_3);


                    if(as.getText().toString().equals("")){



                        if(b.getText().toString().equals("")){


                            sad=URL_1.replace("q=Chennai","q=Kolkata");
                            sad2=URL_2.replace("q=Chennai","q=Kolkata");



                        }

                    }
                    else{

                        sad=URL_1.replace("q=Chennai","q="+as.getText().toString()+","+b.getText().toString());
                        sad2=URL_2.replace("q=Chennai","q="+as.getText().toString()+","+b.getText().toString());}

                    try {
                        FileOutputStream fileout = openFileOutput("mytext7000file.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.write(sad);
                        outputWriter.close();

                        //display file saved message

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileOutputStream fileout = openFileOutput("mytext8000file.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.write(sad2);
                        outputWriter.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    EarthquakeAsyncTass vg = new EarthquakeAsyncTass();
                    vg.execute("http://api.geonames.org/timezoneJSON?lat="+lat+"&"+"lng="+lon+"&username=sanyogghosh@yahoo.co.in");
                    mAdapter.clear();



                    Intent ag  = new Intent(getApplication(),weather.class);
                    startActivity(ag);
                    finish();



                }
                yak++;

            }


        });


        bv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAdapter.clear();

                if(yak%2==0){
                LinearLayout ad = (LinearLayout)findViewById(R.id.box);
                    ad.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down));

                    ad.setVisibility(View.VISIBLE);

                    bv.setText("Done");}
                if(yak%2!=0){

                    LinearLayout ad = (LinearLayout)findViewById(R.id.box);
                    ad.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up));

                    ad.setVisibility(View.GONE);

                    ProgressBar s = (ProgressBar)findViewById(R.id.progress);
                    s.setVisibility(View.VISIBLE);
                    bv.setText("Change Place");

                    EditText as = (EditText)findViewById(R.id.city_1);


                     EditText b = (EditText) findViewById(R.id.country_3);


                    if(as.getText().toString().equals("")){



                        if(b.getText().toString().equals("")){


                            sad=URL_1.replace("q=Chennai","q=Kolkata");
                            sad2=URL_2.replace("q=Chennai","q=Kolkata");



                        }

                    }
else{

                    sad=URL_1.replace("q=Chennai","q="+as.getText().toString()+","+b.getText().toString());
                    sad2=URL_2.replace("q=Chennai","q="+as.getText().toString()+","+b.getText().toString());}

                    try {
                        FileOutputStream fileout = openFileOutput("mytext7000file.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.write(sad);
                        outputWriter.close();

                        //display file saved message

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileOutputStream fileout = openFileOutput("mytext8000file.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.write(sad2);
                        outputWriter.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    EarthquakeAsyncTass vg = new EarthquakeAsyncTass();
                    vg.execute("http://api.geonames.org/timezoneJSON?lat="+lat+"&"+"lng="+lon+"&username=sanyogghosh@yahoo.co.in");
                    mAdapter.clear();



                    Intent ag  = new Intent(getApplication(),weather.class);
                    startActivity(ag);
                    finish();



                }
                yak++;

            }
        });


        forecasts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(i%2!=0){
                ImageView descripa = (ImageView) view.findViewById(R.id.Description);

                TextView speed = (TextView) view.findViewById(R.id.speed);
                    speed.setVisibility(View.GONE);

                    TextView deg = (TextView) view.findViewById(R.id.deg);
                    deg.setVisibility(View.GONE);

                    TextView temp_kf= (TextView) view.findViewById(R.id.temp_kf);
                    temp_kf.setVisibility(View.GONE);
                 TextView temp_2= (TextView) view.findViewById(R.id.temp_akf);
                temp_2.setVisibility(View.GONE);

                   TextView temp_3= (TextView) view.findViewById(R.id.wind_direction);
                    temp_3.setVisibility(View.GONE);


                    TextView tea= (TextView) view.findViewById(R.id.more);
                    tea.setVisibility(View.VISIBLE);

                    TextView temp_4= (TextView) view.findViewById(R.id.wind_speed);
                    temp_4.setVisibility(View.GONE);

                }
            if(i%2==0) {
                    ImageView descripa = (ImageView) view.findViewById(R.id.Description);
                    TextView speed = (TextView) view.findViewById(R.id.speed);
                    speed.setVisibility(View.VISIBLE);

                    TextView deg = (TextView) view.findViewById(R.id.deg);
                    deg.setVisibility(View.VISIBLE);

                    TextView temp_kf= (TextView) view.findViewById(R.id.temp_kf);
                    temp_kf.setVisibility(View.VISIBLE);
                    TextView temp_2= (TextView) view.findViewById(R.id.temp_akf);
                    temp_2.setVisibility(View.VISIBLE);

                TextView tea= (TextView) view.findViewById(R.id.more);
                tea.setVisibility(View.GONE);

                    TextView temp_3= (TextView) view.findViewById(R.id.wind_direction);
                    temp_3.setVisibility(View.VISIBLE);
                    TextView temp_4= (TextView) view.findViewById(R.id.wind_speed);
                    temp_4.setVisibility(View.VISIBLE);


                }
                i++;
            }
        });










        EarthquakeAsyncTass vga = new EarthquakeAsyncTass();
        vga.execute("http://api.geonames.org/timezoneJSON?lat="+lat+"&"+"lng="+lon+"&username=sanyogghosh@yahoo.co.in");
        mAdapter.clear();







        try {
            FileInputStream fileIn = openFileInput("mytext7000file.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            int charRead;
             sad="";
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                sad+= readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = openFileInput("mytext8000file.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            int charRead;
            sad2="";
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                sad2+= readstring;
                EarthquakeAsyncTaska ava = new EarthquakeAsyncTaska();

                ava.execute(sad2);


            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTimeZone(sd);

        TextView d_a = (TextView)findViewById(R.id.date);

        TextView d_aa = (TextView)findViewById(R.id.day);

        TextView d_aaa = (TextView)findViewById(R.id.month);

        d_a.setText(DateFormat.format("dd",c.getTime()));
        d_aa.setText(DateFormat.format("EEEE",c.getTime()));
        d_aaa.setText(DateFormat.format("MMMM",c.getTime()));










        mHandler = new Handler();

        mHandler.postDelayed(m_Runnable,0);


        forecasts.setNestedScrollingEnabled(true);
        //we need a tab layout
        Log.d("1s2t",URL_1);


        Log.d("2N2D",URL_2);


      tabLayout  = (TabLayout) findViewById(R.id.tab_layout);
        FIRST =tabLayout.newTab().setText("A");
        tabLayout.addTab(FIRST);
        SECOND =tabLayout.newTab().setText("B");
        tabLayout.addTab(SECOND);
        THIRD =tabLayout.newTab().setText("C");
        tabLayout.addTab(THIRD);
        FOURTH =tabLayout.newTab().setText("D");
        tabLayout.addTab(FOURTH);
        FIFTH =tabLayout.newTab().setText("E");
        tabLayout.addTab(FIFTH);
        tabLayout.setEnabled(false);



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ad=tab.getPosition();
                Calendar c = Calendar.getInstance();
                c.setTimeZone(sd);


                //DateFormat.format("dd",c.getTime())
                TextView d_a = (TextView)findViewById(R.id.date);

                TextView d_aa = (TextView)findViewById(R.id.day);

                TextView d_aaa = (TextView)findViewById(R.id.month);
                int u=0;
                if(ad==0){
                    mAdapter.clear();
                    ProgressBar ag = (ProgressBar)findViewById(R.id.progress);
                    ag.setVisibility(View.VISIBLE);

                    if(u>0){

                   }
                    d_a.setText(DateFormat.format("dd",c.getTime()));
                    d_aa.setText(DateFormat.format("EEEE",c.getTime()));
                    d_aaa.setText(DateFormat.format("MMMM",c.getTime()));
                    lmn=0;

                    EarthquakeAsyncTaska a = new EarthquakeAsyncTaska();
                    a.execute("http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");

                  u++;

                    View view_1  = SECOND.getCustomView();

                    LinearLayout a_1 = null;
                    if (view_1 != null) {
                        a_1 = (LinearLayout) view_1.findViewById(R.id.tabn);
                        a_1.setAlpha((float)0.2);
                    }


                    View view_5  = FIRST.getCustomView();
                    LinearLayout a_5 = null;
                    if (view_5 != null) {
                        a_5 = (LinearLayout) view_5.findViewById(R.id.tabn);
                        a_5.setAlpha(1);
                    }



                    View view_2  = THIRD.getCustomView();

                    LinearLayout a_2 = null;
                    if (view_2 != null) {
                        a_2 = (LinearLayout) view_2.findViewById(R.id.tabn);
                        a_2.setAlpha((float)0.2);
                    }


                    View view_3  = FOURTH.getCustomView();

                    LinearLayout a_3 = null;
                    if (view_3 != null) {
                        a_3 = (LinearLayout) view_3.findViewById(R.id.tabn);
                        a_3.setAlpha((float)0.2);
                    }


                    View view_4  = FIFTH.getCustomView();

                    LinearLayout a_4 = null;
                    if (view_4 != null) {
                        a_4 = (LinearLayout) view_4.findViewById(R.id.tabn);
                        a_4.setAlpha((float)0.2);
                    }

                }
                if(ad==1){
                    mAdapter.clear();

                    ProgressBar ag = (ProgressBar)findViewById(R.id.progress);
                    ag.setVisibility(View.VISIBLE);

                    c.add(Calendar.DATE,1);
                    d_a.setText(DateFormat.format("dd",c.getTime()));
                    d_aa.setText(DateFormat.format("EEEE",c.getTime()));
                    d_aaa.setText(DateFormat.format("MMMM",c.getTime()));
                    lmn=1;


                    EarthquakeAsyncTaska a = new EarthquakeAsyncTaska();
                    a.execute("http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");

                    View view_5  = SECOND.getCustomView();


                        LinearLayout a_5 = null;
                        if (view_5 != null) {
                            a_5 = (LinearLayout) view_5.findViewById(R.id.tabn);
                            a_5.setAlpha(1);

                        }

                    View view_1  = FIRST.getCustomView();
                    LinearLayout a_1 = null;
                    if (view_1 != null) {
                        a_1 = (LinearLayout) view_1.findViewById(R.id.tabn);
                        a_1.setAlpha((float)0.2);

                    }




                    View view_2  = THIRD.getCustomView();
                    LinearLayout a_2 = null;
                    if (view_2 != null) {
                        a_2 = (LinearLayout) view_2.findViewById(R.id.tabn);
                        a_2.setAlpha((float)0.2);

                    }

                    View view_3  = FOURTH.getCustomView();
                    LinearLayout a_3 = null;
                    if (view_3 != null) {
                        a_3 = (LinearLayout) view_3.findViewById(R.id.tabn);
                        a_3.setAlpha((float)0.2);

                    }

                    View view_4  = FIFTH.getCustomView();
                    LinearLayout a_4 = null;
                    if (view_4 != null) {
                        a_4 = (LinearLayout) view_4.findViewById(R.id.tabn);
                        a_4.setAlpha((float)0.2);

                    }
                }
                if(ad==2){
                    mAdapter.clear();

                    ProgressBar ag = (ProgressBar)findViewById(R.id.progress);
                    ag.setVisibility(View.VISIBLE);

                    c.add(Calendar.DATE,2);
                    d_a.setText(DateFormat.format("dd",c.getTime()));
                    d_aa.setText(DateFormat.format("EEEE",c.getTime()));
                    d_aaa.setText(DateFormat.format("MMMM",c.getTime()));
                    lmn=2;

                    EarthquakeAsyncTaska a = new EarthquakeAsyncTaska();
                    a.execute("http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");
                    View view_5  = SECOND.getCustomView();
                    LinearLayout a_5 = null;
                    if (view_5 != null) {
                        a_5 = (LinearLayout) view_5.findViewById(R.id.tabn);
                        a_5.setAlpha((float)0.2);
                    }


                    View view_1  = FIRST.getCustomView();
                    LinearLayout a_1 = null;
                    if (view_1 != null) {
                        a_1 = (LinearLayout) view_1.findViewById(R.id.tabn);
                        a_1.setAlpha((float)0.2);

                    }





                    View view_2  = THIRD.getCustomView();
                    LinearLayout a_2 = null;
                    if (view_2 != null) {
                        a_2 = (LinearLayout) view_2.findViewById(R.id.tabn);
                        a_2.setAlpha((float)1);
                    }


                    View view_3  = FOURTH.getCustomView();
                    LinearLayout a_3 = null;
                    if (view_3 != null) {
                        a_3 = (LinearLayout) view_3.findViewById(R.id.tabn);
                        a_3.setAlpha((float)0.2);
                    }


                    View view_4  = FIFTH.getCustomView();
                    LinearLayout a_4 = null;
                    if (view_4 != null) {
                        a_4 = (LinearLayout) view_4.findViewById(R.id.tabn);
                        a_4.setAlpha((float)0.2);

                    }


                }
                if(ad==3){
                    mAdapter.clear();

                    ProgressBar ag = (ProgressBar)findViewById(R.id.progress);
                    ag.setVisibility(View.VISIBLE);

                    c.add(Calendar.DATE,3);
                    d_a.setText(DateFormat.format("dd",c.getTime()));
                    d_aa.setText(DateFormat.format("EEEE",c.getTime()));
                    d_aaa.setText(DateFormat.format("MMMM",c.getTime()));
                    lmn=3;

                    EarthquakeAsyncTaska a = new EarthquakeAsyncTaska();
                    a.execute("http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");

                    View view_5  = FOURTH.getCustomView();
                    LinearLayout a_5 = null;
                    if (view_5 != null) {
                        a_5 = (LinearLayout) view_5.findViewById(R.id.tabn);
                        a_5.setAlpha(1);
                    }



                    View view_1  = SECOND.getCustomView();
                    LinearLayout a_1 = null;
                    if (view_1 != null) {
                        a_1 = (LinearLayout) view_1.findViewById(R.id.tabn);
                        a_1.setAlpha((float)0.2);
                    }





                    View view_2  = THIRD.getCustomView();
                    LinearLayout a_2 = null;
                    if (view_2 != null) {
                        a_2 = (LinearLayout) view_2.findViewById(R.id.tabn);
                        a_2.setAlpha((float)0.2);

                    }

                    View view_3  = FIRST.getCustomView();
                    LinearLayout a_3 = null;
                    if (view_3 != null) {
                        a_3 = (LinearLayout) view_3.findViewById(R.id.tabn);
                        a_3.setAlpha((float)0.2);

                    }

                    View view_4  = FIFTH.getCustomView();
                    LinearLayout a_4 = null;
                    if (view_4 != null) {
                        a_4 = (LinearLayout) view_4.findViewById(R.id.tabn);
                        a_4.setAlpha((float)0.2);

                    }
                }


                if(ad==4){
                    mAdapter.clear();

                    ProgressBar ag = (ProgressBar)findViewById(R.id.progress);
                    ag.setVisibility(View.VISIBLE);

                    c.add(Calendar.DATE,4);
                    d_a.setText(DateFormat.format("dd",c.getTime()));
                    d_aa.setText(DateFormat.format("EEEE",c.getTime()));
                    d_aaa.setText(DateFormat.format("MMMM",c.getTime()));
                    lmn=4;

                    EarthquakeAsyncTaska a = new EarthquakeAsyncTaska();
                    a.execute("http://api.openweathermap.org/data/2.5/forecast?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");

                    View view_5  = FOURTH.getCustomView();
                    LinearLayout a_5 = null;
                    if (view_5 != null) {
                        a_5 = (LinearLayout) view_5.findViewById(R.id.tabn);
                        a_5.setAlpha((float)0.2);
                    }



                    View view_1  = SECOND.getCustomView();
                    LinearLayout a_1 = null;
                    if (view_1 != null) {
                        a_1 = (LinearLayout) view_1.findViewById(R.id.tabn);
                        a_1.setAlpha((float)0.2);
                    }





                    View view_2  = THIRD.getCustomView();
                    LinearLayout a_2 = null;
                    if (view_2 != null) {
                        a_2 = (LinearLayout) view_2.findViewById(R.id.tabn);
                        a_2.setAlpha((float)0.2);

                    }

                    View view_3  = FIRST.getCustomView();
                    LinearLayout a_3 = null;
                    if (view_3 != null) {
                        a_3 = (LinearLayout) view_3.findViewById(R.id.tabn);
                        a_3.setAlpha((float)0.2);

                    }

                    View view_4  = FIFTH.getCustomView();
                    LinearLayout a_4 = null;
                    if (view_4 != null) {
                        a_4 = (LinearLayout) view_4.findViewById(R.id.tabn);
                        a_4.setAlpha(1);

                    }
                }
                SharedPreferences weather_one = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                SharedPreferences.Editor editor = weather_one.edit();
                editor.putInt("tab_number",tab.getPosition());
                editor.apply();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
    private Bitmap drwa;
    private Bitmap drwaa;
    private Bitmap drwaaa;
    private Bitmap drwaaaa;
    private Bitmap drwaaaaa;

    private Drawable d;
    private Drawable da;
    private Drawable daa;
    private Drawable daaa;
    private Drawable daaaa;

















    /** Sample JSON response for a USGS query */
    /**
     * Create a private constructor because no one should ever create a {@link } object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */


    public  final String LOG_TAGA= "Hey!!";

    /**
     * Return a list of {@link AndroidFlavor} objects that has been built up from
     * parsing a JSON response.
     */

    /**
     * Returns new URL object from the given string URL.
     */
    private  URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);

        }
        return url;
    }
    private  String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000 /* milliseconds */);
            urlConnection.setConnectTimeout(25000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            
          
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {

           
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private  String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Return a list of {@link AndroidFlavor} objects that has been built up from
     * parsing the given JSON response.
     */
    public List<AndroidFlavor_two> extractFeatureFromJson(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.

        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<AndroidFlavor_two> earthquakes = new ArrayList<>();
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("list");

            JSONObject prop_2a =    baseJsonResponse.getJSONObject("city");
            JSONObject prop_2a1 =    prop_2a.getJSONObject("coord");
            Double    lattu =          prop_2a1.getDouble("lat");
            Double    lonnu =          prop_2a1.getDouble("lon");


            int i=0;
            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
               for(i=0;i<earthquakeArray.length();i++) {
                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.


                JSONObject properties = currentEarthquake.getJSONObject("main");
                   double humidity =   properties.getDouble("humidity");
                   double temp =       properties.getDouble("temp");
                   double pressure =   properties.getDouble("pressure");
                   double mintemp =    properties.getDouble("temp_min");
                   double maxtemp =    properties.getDouble("temp_max");
                   double sea_level =  properties.getDouble("sea_level");
                   double grnd_level = properties.getDouble("grnd_level");
                   double temp_kf =    properties.getDouble("temp_kf");


                   JSONArray prop = currentEarthquake.getJSONArray("weather");
                   JSONObject weathera = prop.getJSONObject(0);
                    String description = weathera.getString("description");
                    String icon =        weathera.getString("icon");
                    String main =        weathera.getString("main");

                   JSONObject  prop_1 =   currentEarthquake.getJSONObject("wind");
                    Double     deg =         prop_1.getDouble("deg");
                    Double     speed =       prop_1.getDouble("speed");

                   JSONObject prop_2 =    currentEarthquake.getJSONObject("clouds");
                    String    all =          prop_2.getString("all");



                   String  dttxt =  currentEarthquake.getString("dt_txt");

                   long  dt = currentEarthquake.getLong("dt");
                   Date date = new Date(dt*1000L);
                   SimpleDateFormat sdf = new SimpleDateFormat("dd");
                   sdf.setTimeZone(sd);

                   String day  = sdf.format(date);
                   Calendar c = Calendar.getInstance();
                   c.setTimeZone(sd);


                   AndroidFlavor_two fore = new AndroidFlavor_two(temp,deg,temp_kf,description,speed,dt,icon,main,lattu,lonnu);



                   if(day.equals(DateFormat.format("dd",c.getTime()))){
                       if(yop==0){
                           id1 = hello(main,description);
                       }

                       if(lmn==0){

                           earthquakes.add(fore);

                       }

                       yop++;
                   }
                   c.add(Calendar.DATE,1);

                   if(day.equals(DateFormat.format("dd",c.getTime()))){
                       if(yop_0==0){
                           id2 = hello(main,description);
                       } if(lmn==1){

                           earthquakes.add(fore);

                       }
                       yop_0++;
                   }
                   c.add(Calendar.DATE,1);
                   if(day.equals(DateFormat.format("dd",c.getTime()))){
                       if(yop_1==0){
                           id3 = hello(main,description);
                       }  if(lmn==2){

                           earthquakes.add(fore);

                       }
                       yop_1++;
                   }
                   c.add(Calendar.DATE,1);
                   if(day.equals(DateFormat.format("dd",c.getTime()))){
                      if(yop_2==0){
                          id4 = hello(main,description);

                       }
                       if(lmn==3){

                           earthquakes.add(fore);

                       }
                       yop_2++;
                   }
                   c.add(Calendar.DATE,1);
                   if(day.equals(DateFormat.format("dd",c.getTime()))){
                       if(yop_3==0){
                         id5 = hello(main,description);
                       } if(lmn==4){

                           earthquakes.add(fore);

                       }
                       yop_3++;
                   }






            }


        }
        
        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public  List<AndroidFlavor_two> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<AndroidFlavor_two> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }





      public int in=0;


    public  class EarthquakeAsyncTaska extends AsyncTask<String, Void, List<AndroidFlavor_two>> {
       @Override


        protected List<AndroidFlavor_two> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<AndroidFlavor_two> result = fetchEarthquakeData(urls[0]);

            return result;

        }

        @Override
        protected   void onPostExecute(List<AndroidFlavor_two> data) {

            tabLayout.setEnabled(true);

            mAdapter.clear();
            if(data==null){

                TextView ui = (TextView)findViewById(R.id.results);
                ui.setVisibility(View.VISIBLE);
                ProgressBar s = (ProgressBar) findViewById(R.id.progress);
                s.setVisibility(View.GONE);
            }

          if(data!=null) {

              TextView ui = (TextView)findViewById(R.id.results);
              ui.setVisibility(View.GONE);
              mAdapter.clear();
              if (in == 0) {
                  Calendar c = Calendar.getInstance();
                  c.setTimeZone(sd);

//list views??
                  View view3 = getLayoutInflater().inflate(R.layout.customtab, null);
                  TextView a = (TextView) view3.findViewById(R.id.texter);
                  a.setText(DateFormat.format("dd", c.getTime()));
                  TextView ao = (TextView) view3.findViewById(R.id.texter_month);
                  ao.setText(DateFormat.format("MMM", c.getTime()));
                  ImageView b = (ImageView) view3.findViewById(R.id.hyu);
                  b.setImageResource(id1);
                  FIRST.setCustomView(view3);

                  c.add(Calendar.DATE, 1);

                  View view4 = getLayoutInflater().inflate(R.layout.customtab, null);
                  TextView as = (TextView) view4.findViewById(R.id.texter);
                  as.setText(DateFormat.format("dd", c.getTime()));
                  TextView aoo = (TextView) view4.findViewById(R.id.texter_month);
                  aoo.setText(DateFormat.format("MMM", c.getTime()));
                  ImageView sb = (ImageView) view4.findViewById(R.id.hyu);
                  sb.setImageResource(id2);
                  SECOND.setCustomView(view4);


                  c.add(Calendar.DATE, 1);


                  View view5 = getLayoutInflater().inflate(R.layout.customtab, null);
                  TextView asa = (TextView) view5.findViewById(R.id.texter);
                  asa.setText(DateFormat.format("dd", c.getTime()));
                  TextView aooo = (TextView) view5.findViewById(R.id.texter_month);
                  aooo.setText(DateFormat.format("MMM", c.getTime()));

                  ImageView bsa = (ImageView) view5.findViewById(R.id.hyu);
                  bsa.setImageResource(id3);
                  THIRD.setCustomView(view5);

                  c.add(Calendar.DATE, 1);

                  View view6 = getLayoutInflater().inflate(R.layout.customtab, null);
                  TextView aas = (TextView) view6.findViewById(R.id.texter);
                  aas.setText(DateFormat.format("dd", c.getTime()));
                  TextView aoooo = (TextView) view6.findViewById(R.id.texter_month);
                  aoooo.setText(DateFormat.format("MMM", c.getTime()));

                  ImageView bas = (ImageView) view6.findViewById(R.id.hyu);
                  bas.setImageResource(id4);
                  FOURTH.setCustomView(view6);

                  c.add(Calendar.DATE, 1);
                  View view7 = getLayoutInflater().inflate(R.layout.customtab, null);
                  TextView asd = (TextView) view7.findViewById(R.id.texter);
                  asd.setText(DateFormat.format("dd", c.getTime()));
                  TextView aooooo = (TextView) view7.findViewById(R.id.texter_month);
                  aooooo.setText(DateFormat.format("MMM", c.getTime()));

                  ImageView bsd = (ImageView) view7.findViewById(R.id.hyu);
                  bsd.setImageResource(id5);
                  FIFTH.setCustomView(view7);
                  in++;
              }
              // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
              // data set. This will trigger the ListView to update.
              if (data != null && !data.isEmpty()) {
                  mAdapter.addAll(data);
                  mAdapter.notifyDataSetChanged();

                  ProgressBar s = (ProgressBar) findViewById(R.id.progress);
                  s.setVisibility(View.GONE);
              }
          }
        }
            // Clear the adapter of previous earthquake data





}
//async task for current weather


    private  AndroidFlavorAdapter_two mAdapter;














    public  final String LOG_TAG = "Hey!!";

    /**
     * Return a list of {@link AndroidFlavor} objects that has been built up from
     * parsing a JSON response.
     */

    /**
     * Returns new URL object from the given string URL.
     */
    private  URL createUrla(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private  String makeHttpRequesta(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStreama = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStreama = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStreama);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreama != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStreama.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private  String readFromStreama(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Return a list of {@link AndroidFlavor} objects that has been built up from
     * parsing the given JSON response.
     */
    public AndroidFlavor_three extractFeatureFromJsona(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.


        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        double x=0;
        String xx="";
        double y=0;
        double z=0;
        double zz=0;
        double zzz=0;

        AndroidFlavor_three earthquake=new AndroidFlavor_three(x,y,z,zz,zzz,xx);

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONObject earthquakeObject = baseJsonResponse.getJSONObject("main");
            JSONObject aga = baseJsonResponse.getJSONObject("wind");
            double windspeed = aga.getDouble("speed");
            double winddirection=0.01;
            if(aga.length()==2){


                winddirection = aga.getDouble("deg");

            }

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object

            // Get a single earthquake at position i within the list of earthquak
            // For a given earthquake, extract the JSONObject associated with the
            // key called "properties", which represents a list of all properties
            // for that earthquake.

            // Extract the value for the key called "mag"
            double temp = earthquakeObject.getDouble("temp");
            double pressure = earthquakeObject.getDouble("pressure");
           double humidity = earthquakeObject.getDouble("humidity");
            JSONArray earthquakeObjecta = baseJsonResponse.getJSONArray("weather");
            JSONObject ag = earthquakeObjecta.getJSONObject(0);
            String main = ag.getString("main");
            String description = ag.getString("description");
            JSONObject hj = baseJsonResponse.getJSONObject("coord");
             lon = hj.getDouble("lon");
             lat = hj.getDouble("lat");



            city = baseJsonResponse.getString("name");



            JSONObject agah = baseJsonResponse.getJSONObject("sys");
            agf = agah.getString("country");
            sunrise=agah.getLong("sunrise");
            sunset=agah.getLong("sunset");

            descripation = description;



            id_6 = hello(main,description);
            id_63 =hello2(main,description);






            // Extract the value for the key called "place"

            earthquake = new AndroidFlavor_three(temp,windspeed,winddirection,humidity,pressure,main);


        }

        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquake;
    }

    public  AndroidFlavor_three fetchEarthquakeDataa(String requestUrl) {
        // Create URL object
        URL urla = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(urla);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        AndroidFlavor_three earthquakes = extractFeatureFromJsona(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }


    public  class EarthquakeAsyncTas extends AsyncTask<String, Void, AndroidFlavor_three> {
        @Override
//#b75757 colour is the best for forecasts

        protected AndroidFlavor_three doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            AndroidFlavor_three result = fetchEarthquakeDataa(urls[0]);

            return result;

        }

        @Override
        protected   void onPostExecute(AndroidFlavor_three data) {

            if(data==null){
                ProgressBar pba = (ProgressBar)findViewById(R.id.progress);
                pba.setVisibility(View.VISIBLE);
                TextView ui = (TextView)findViewById(R.id.results);
                ui.setVisibility(View.VISIBLE);


            }
if(data!=null){

    ImageView ui = (ImageView) findViewById(R.id.mainpicture);
            ui.setImageResource(id_63);
            ProgressBar pba = (ProgressBar)findViewById(R.id.progress);
    pba.setVisibility(View.GONE);
            TextView a = (TextView) findViewById(R.id.descriptiona);

            String aa = descripation;

            a.setText(descripation);

            //all ats
            TextView at1 = (TextView) findViewById(R.id.humid);
            at1.setText(" " + data.humidity);
            TextView deg = (TextView) findViewById(R.id.wdir);
            TextView at3 = (TextView) findViewById(R.id.wspeed);
            at3.setText(" " + data.windspeed + "km/hr");
            if (data.winddirection > 348.75 && data.winddirection <= 360 || data.winddirection > 0 && data.winddirection <= 11.25) {

                deg.setText(" N ");


            }


            if (data.winddirection > 11.25 && data.winddirection <= 33.75) {

                deg.setText(" NNE ");

            }


            if (data.winddirection > 33.75 && data.winddirection <= 56.25) {


                deg.setText(" NE ");

            }

            if (data.winddirection > 56.25 && data.winddirection <= 78.75) {


                deg.setText(" ENE ");

            }

            if (data.winddirection > 78.75 && data.winddirection <= 101.25) {

                deg.setText(" E ");

            }

            if (data.winddirection > 101.25 && data.winddirection <= 123.75) {

                deg.setText(" ESE ");

            }

            if (data.winddirection > 123.75 && data.winddirection <= 146.25) {

                deg.setText(" SE ");

            }
            if (data.winddirection > 146.25 && data.winddirection <= 168.75) {

                deg.setText(" SSE ");

            }

            if (data.winddirection > 168.75 && data.winddirection <= 191.25) {

                deg.setText(" S ");

            }

            if (data.winddirection > 191.25 && data.winddirection <= 213.75) {

                deg.setText(" SSW ");

            }
            if (data.winddirection > 213.75 && data.winddirection <= 236.25) {

                deg.setText(" SW ");

            }

            if (data.winddirection > 256.25 && data.winddirection <= 258.75) {

                deg.setText(" WSW ");

            }

            if (data.winddirection > 258.75 && data.winddirection <= 281.25) {

                deg.setText(" W ");

            }

            if (data.winddirection > 281.25 && data.winddirection <= 303.25) {

                deg.setText(" WNW ");

            }

            if (data.winddirection > 303.25 && data.winddirection <= 326.25) {

                deg.setText(" NW ");

            }
            if (data.winddirection > 326.25 && data.winddirection <= 348.75) {

                deg.setText(" NNW ");

            }

            TextView at = (TextView) findViewById(R.id.currenttemp);
            int yu = (int) (data.currentdegree() - 273);
            String s = String.valueOf(yu);
            SpannableStringBuilder cs = new SpannableStringBuilder("" + yu + Html.fromHtml("&#176;") + "C");
            if (s.length() == 2) {

                cs.setSpan(new RelativeSizeSpan(0.75f), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            if (s.length() == 1) {

                cs.setSpan(new RelativeSizeSpan(0.75f), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            }
            if (s.length() == 3) {

                cs.setSpan(new RelativeSizeSpan(0.75f), 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            }
            at.setText(cs);
            at.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink));
            ImageView aj = (ImageView) findViewById(R.id.heroa);
            aj.setImageResource(id_6);
            TextView aop = (TextView) findViewById(R.id.lat);
            aop.setText("Lat:" + lat);

            TextView aoap = (TextView) findViewById(R.id.lon);

            String timezone_hour = "";

            try {
                FileInputStream fileIn = openFileInput("mytext700011file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                timezone_hour = "";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    timezone_hour += readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


            double part = 0;
            if (!timezone_hour.equals("")) {
                part = Double.parseDouble(timezone_hour);

                if (part < 0) {

                    part = Math.abs(part);
                    Log.d("fs", String.valueOf(part));

                    int ar = (int) part;

                    double part2 = part - ar;


                    int minutes = (int) (part2 * 60);
                    if (minutes == 0) {

                        sd = TimeZone.getTimeZone("GMT-" + ar + ":00");


                    } else {
                        sd = TimeZone.getTimeZone("GMT-" + ar + ":" + minutes + "" + minutes);

                        Log.d("aaa", "GMT-" + ar + ":00");
                    }

                    part = -48;
                }
                if (part > 0) {

                    part = Math.abs(part);
                    Log.d("fs", String.valueOf(part));

                    int ar = (int) part;

                    double part2 = part - ar;


                    int minutes = (int) (part2 * 60);
                    if (minutes == 0) {
                        sd = TimeZone.getTimeZone("GMT+" + ar + ":00");


                    } else {


                        sd = TimeZone.getTimeZone("GMT+" + ar + ":" + minutes);
                        Log.d("aaa", "GMT+" + ar + ":00");

                    }
                }

            }

            aoap.setText("Lon:" + lon);

            Date date_rise = new Date(sunrise * 1000L); // *1000 is to convert seconds to milliseconds
            Date date_set = new Date(sunset * 1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat setrise = new SimpleDateFormat("hh:mm aa");
            setrise.setTimeZone(sd);
            TextView op = (TextView) findViewById(R.id.sunrisetime);
            TextView op_1 = (TextView) findViewById(R.id.sunsettime);

            op.setText(setrise.format(date_rise));
            op_1.setText(setrise.format(date_set));
            //location using geocoder

            Geocoder geocoder;
            String knownName="";
            String address="";
            String locality="";
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat, lon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(addresses!=null) {
                if (addresses.size() > 0) {
                    country = addresses.get(0).getCountryName();

                    TextView y_2 = (TextView) findViewById(R.id.country_appbar);
                    y_2.setText(country);


                    TextView y_4 = (TextView) findViewById(R.id.country_1);
                    y_4.setText(country);

                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    state = addresses.get(0).getAdminArea();
                    String postalCode = addresses.get(0).getPostalCode();
                    knownName = addresses.get(0).getFeatureName();
                    locality = addresses.get(0).getLocality();
                }
            }
            TextView y_1 = (TextView) findViewById(R.id.city);
            y_1.setText(city + "," +locality+","+ state);

            TextView y_3 = (TextView) findViewById(R.id.city_appbar);
            if(locality==null){
                y_3.setText(city);
            }
    else {
                y_3.setText(city);
            }

        } }
        // Clear the adapter of previous earthquake data

        private  AndroidFlavor_three earthquake;




    }

// Geonames api


    /**
     * Returns new URL object from the given string URL.
     */
    private  URL createUrlas(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private  String makeHttpRequestas(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStreama = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000 /* milliseconds */);
            urlConnection.setConnectTimeout(25000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStreama = urlConnection.getInputStream();
                jsonResponse = readFromStreamas(inputStreama);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreama != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStreama.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private  String readFromStreamas(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Return a list of {@link AndroidFlavor} objects that has been built up from
     * parsing the given JSON response.
     */
        public AndroidFlavor_four extractFeatureFromJsonas(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.


        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        double x=0;


        AndroidFlavor_four earthquake=new AndroidFlavor_four(x);

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            double gmt  = baseJsonResponse.getDouble("dstOffset");
            // Extract the value for the key called "place"

            earthquake = new AndroidFlavor_four(gmt);


        }

        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquake;
    }

    public  AndroidFlavor_four fetchEarthquakeDataas(String requestUrl) {
        // Create URL object
        URL urla = createUrlas(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequestas(urla);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        AndroidFlavor_four earthquakes = extractFeatureFromJsonas(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }


    public  class EarthquakeAsyncTass extends AsyncTask<String, Void, AndroidFlavor_four> {
        @Override
//#b75757 colour is the best for forecasts

        protected AndroidFlavor_four doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            AndroidFlavor_four result = fetchEarthquakeDataas(urls[0]);

            return result;

        }

        @Override
        protected   void onPostExecute(AndroidFlavor_four data) {
            if(data==null){

                TextView ui = (TextView)findViewById(R.id.results);
                ui.setVisibility(View.VISIBLE);

            }
       if(data!=null) {


           try {
               FileOutputStream fileout = openFileOutput("mytext700011file.txt", MODE_PRIVATE);
               OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
               outputWriter.write("" + data.gmtoff);
               outputWriter.close();


           } catch (Exception e) {
               e.printStackTrace();
           }


       }
        }
        // Clear the adapter of previous earthquake data

        private  AndroidFlavor_four earthquake;




    }

//ends here









    private final Runnable m_Runnable = new Runnable()
    {
        public void run()



        {

            EarthquakeAsyncTass vg = new EarthquakeAsyncTass();
            vg.execute("http://api.geonames.org/timezoneJSON?lat="+lat+"&"+"lng="+lon+"&username=sanyogghosh@yahoo.co.in");

            Log.d("12st",URL_1);
            try {
                FileInputStream fileIn = openFileInput("mytext7000file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                sad="";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    sad+= readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                FileInputStream fileIn = openFileInput("mytext8000file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                sad2="";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    sad2+= readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.d("2sND",URL_2);
                    if(sad2.equals("")){
                        if(yu==1){
                            mAdapter.clear();
                            Log.d("yu",""+yu);
                        }
                        if(yu>0){

                            EarthquakeAsyncTas ava = new EarthquakeAsyncTas();

                            ava.execute("http://api.openweathermap.org/data/2.5/weather?q=Chennai&appid=9c973da8885c5e636d2c93bcb6355bdd");

                        }


                    }

            else {




            EarthquakeAsyncTas avas = new EarthquakeAsyncTas();
            Log.d("yu",""+yu);
            avas.execute(sad);

                        if (vg.getStatus()== AsyncTask.Status.RUNNING){

                            ProgressBar a  = (ProgressBar)findViewById(R.id.progress);

                            if(yu<=2&&yu>0){


                                TextView g  = (TextView)findViewById(R.id.set);
                                g.setVisibility(View.VISIBLE);


                            }
                            if(yu<2){
                                a.setVisibility(View.VISIBLE);
                                forecasts.setVisibility(View.GONE);


                            }
                            if(yu==2) {
                                a.setVisibility(View.GONE);
                                TextView g  = (TextView)findViewById(R.id.set);
                                g.setVisibility(View.GONE);
                                forecasts.setVisibility(View.VISIBLE);
                                if(vg.getStatus() == AsyncTask.Status.FINISHED){

                                    ProgressBar afd  = (ProgressBar)findViewById(R.id.progress);
                                    afd.setVisibility(View.GONE);
                                    mAdapter.clear();
                                    EarthquakeAsyncTaska avasa = new EarthquakeAsyncTaska();
                                    avasa.execute("http://api.openweathermap.org/data/2.5/forecast?q="+sad2+"&appid=9c973da8885c5e636d2c93bcb6355bdd");


                                }
                            }

                        }

            mAdapter.notifyDataSetChanged();










                    }


            weather.this.mHandler.postDelayed(m_Runnable, 10000);

            TextView fg = (TextView)findViewById(R.id.timea);

            String timezone_hour = "";

            try {
                FileInputStream fileIn = openFileInput("mytext700011file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                timezone_hour="";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    timezone_hour+= readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            double part=0;
            if(!timezone_hour.equals("")){

                part = Double.parseDouble(timezone_hour);
                if(part<0){

                    part=Math.abs(part);
                    Log.d("fs",String.valueOf(part));

                    int ar = (int)part;

                    double part2 = part-ar;


                    int minutes = (int) (part2*60);

                    if(minutes==0){
                        sd = TimeZone.getTimeZone("GMT-"+ar+":00");



                    }
                    else{
                        sd = TimeZone.getTimeZone("GMT-"+ar+":"+minutes);}

                    part=-48;

                }
                if(part>0){

                    part=Math.abs(part);
                    Log.d("fs",String.valueOf(part));

                    int ar = (int)part;

                    double part2 = part-ar;


                    int minutes = (int) (part2*60);

                    if(minutes==0){
                        sd = TimeZone.getTimeZone("GMT+"+ar+":00");



                    }
                    else{
                        sd = TimeZone.getTimeZone("GMT+"+ar+":"+minutes);}
                }

            }
            Date date = new Date(System.currentTimeMillis()); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            SimpleDateFormat sdfd = new SimpleDateFormat("hh:mm aa");
            sdfd.setTimeZone(sd);

            Log.d("HAA",sdfd.format(date));


            fg.setText((String)sdfd.format(date));
      yu++;
        }

    };
//BELOW IS USELESS
    public static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth, float newHeight) {
        if(bitmapToScale == null)
            return null;
//get the original width and height
        int width = bitmapToScale.getWidth();
        int height = bitmapToScale.getHeight();
// create a matrix for the manipulation
        Matrix matrix = new Matrix();

// resize the bit map
        matrix.postScale(newWidth / width, newHeight / height);

// recreate the new Bitmap and set it back
        return Bitmap.createBitmap(bitmapToScale, 0, 0, bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix, true);  }


    private String getDayString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy                                    HH:mm:ss ");


        return formatter.format(timeInMilliseconds);
    }


    private int hello2( String main ,String sling){
        int id_63=0;
        Calendar c = Calendar.getInstance();
        c.setTimeZone(sd);

        Date date = new Date(System.currentTimeMillis()); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat sdfd = new SimpleDateFormat("HH");
        sdfd.setTimeZone(sd);

        Log.d("HAA",sdfd.format(date));



        String x = sdfd.format(date);
        int g = Integer.parseInt(x);
        Log.d("g",g+"");
        if(main.equals("Rain")){
            if(g>=0&&g<=7){
                id_63=(R.drawable.rainy);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.sunnyrain);

            }
            if(g>=17&&g<19){
                id_63=(R.drawable.sunnyrain);

            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.rainy);

            }

            if(sling.equals("light rain")){
                if(g>=0&&g<=7){
                    id_63=(R.drawable.rainy);

                }
                if(g>7&&g<17){
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=17&&g<19){
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=19&&g<=24){
                    id_63=(R.drawable.rainy);

                }



            }

        }

        if(main.equals("Drizzle")){



            if(sling.equals("light intensity drizzle")){


                id_63=(R.drawable.abh);


            }

        }
        if(main.equals("Thunderstorm")){
            id_63=(R.drawable.unnamed);



        }
        if(main.equals("Snow")){



            id_63=(R.drawable.snowas);

        }

        if(main.equals("Snow")){



            id_63=(R.drawable.snowas);

        }
        if(main.equals("Atmosphere")){

            if(g>=0&&g<=7){
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.hazewe);

            }
            if(g>=17&&g<19){
                id_63=(R.drawable.hazewe);
            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.mistd);

            }

        }
        if(main.equals("Haze")){

            if(g>=0&&g<=7){
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.hazewe);

            }
            if(g>=17&&g<19){
                id_63=(R.drawable.hazewe);
            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.mistd);

            }

        }
        if(main.equals("Extreme")){

            id_63=(R.drawable.badweather);

        }
        if(main.equals("Mist")){

            if(g>=0&&g<=7){
                id_63=(R.drawable.mistd);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.mistm);


            }
            if(g>=17&&g<19){
                id_63=(R.drawable.mistm);

            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.mistd);

            }


        }

        if(main.equals("Fog")){

            if(g>=0&&g<=7){
                id_63=(R.drawable.mistd);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.mistm);


            }
            if(g>=17&&g<19){
                id_63=(R.drawable.mistm);

            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.mistd);

            }


        }
        if(main.equals("Clear")){
            if(g>=0&&g<=7){
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.ed);


            }
            if(g>=17&&g<19){
                id_63=(R.drawable.rsz_evening_sunset_hd);

            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.rsz_night_time_in_new_york_wallpaper1080);

            }




        }
        if(main.equals("Clouds")){
            if(g<=7){

                id_63=(R.drawable.backgroundnight);


            }
            if(g>7&&g<17){

                id_63=(R.drawable.partlycloudy);


            }
            if(g>=17&&g<19){

                id_63=(R.drawable.partlycloudy);

            }
            if(g>=19&&g<=24){

                id_63=(R.drawable.backgroundnight);

            }
            if(sling.equals("scattered clouds")){

                if(g<=7){

                    id_63=(R.drawable.backgroundnight);


                }
                if(g>7&&g<17){

                    id_63=(R.drawable.partlycloudy);


                }
                if(g>=17&&g<19){

                    id_63=(R.drawable.partlycloudy);

                }
                if(g>=19&&g<=24){

                    id_63=(R.drawable.backgroundnight);

                }



            }
            if(sling.equals("broken clouds")){

                if(g<=7){

                    id_63=(R.drawable.backgroundnight);


                }
                if(g>7&&g<17){

                    id_63=(R.drawable.partlycloudy);


                }
                if(g>=17&&g<19){

                    id_63=(R.drawable.partlycloudy);

                }
                if(g>=19&&g<=24){

                    id_63=(R.drawable.backgroundnight);

                }


            }

            if(sling.equals("overcast clouds")){

                if(g>=0&&g<=7){


                    id_63=(R.drawable.qq);

                }
                if(g>7&&g<17){


                    id_63=(R.drawable.qj);

                }
                if(g>=17&&g<19){
                    id_63=(R.drawable.qj);


                }
                if(g>=19&&g<=24){
                    id_63=(R.drawable.qq);


                }
            }

        }






        return id_63;
    }

    private int hello( String main ,String sling){
        int id=0;

        Date date = new Date(System.currentTimeMillis()); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat sdfd = new SimpleDateFormat("HH");
        sdfd.setTimeZone(sd);

        Log.d("HAA",sdfd.format(date));



        String x = sdfd.format(date);
        int g = Integer.parseInt(x);
        Log.d("gaa",String.valueOf(g));
        if(main.equals("Rain")){
            id = R.drawable.a5;
            if(g>0&&g<=7){
                id_63=(R.drawable.rainy);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.sunnyrain);

            }
            if(g>=17&&g<19){
                id_63=(R.drawable.sunnyrain);

            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.rainy);

            }

            if(sling.equals("light rain")){
                if(g>=0&&g<=7){
                    id = R.drawable.a45;
                    id_63=(R.drawable.rainy);

                }
                if(g>7&&g<17){
                    id = R.drawable.a39;
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=17&&g<19){
                    id = R.drawable.a39;
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=19&&g<=24){
                    id = R.drawable.a45;
                    id_63=(R.drawable.rainy);

                }



            }

        }

        if(main.equals("Drizzle")){

            id = R.drawable.a5;


            if(sling.equals("light intensity drizzle")){
                if(g>=0&&g<=7){
                    id = R.drawable.a45;

                }
                if(g>7&&g<17){
                    id = R.drawable.a39;

                }
                if(g>=17&&g<20){
                    id = R.drawable.a39;

                }
                if(g>=20&&g<=24){
                    id = R.drawable.a45;

                }

                id_63=(R.drawable.abh);


            }

        }
        if(main.equals("Thunderstorm")){
            id = R.drawable.a1;
            id_63=(R.drawable.unnamed);



        }

        if(main.equals("Mist")){
            id = R.drawable.a43;
            id_63=(R.drawable.unnamed);



        }

        if(main.equals("Fog")){
            id = R.drawable.a43;
            id_63=(R.drawable.unnamed);



        }
        if(main.equals("Snow")){
            id = R.drawable.a14;

            if(sling.equals("heavy snow")){

                id = R.drawable.a18;


            }

            id_63=(R.drawable.snowas);

        }
        if(main.equals("Atmosphere")){

            id = R.drawable.a20;
            if(g>=0&&g<=7){
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id_63=(R.drawable.hazewe);

            }
            if(g>=17&&g<19){
                id_63=(R.drawable.hazewe);
            }
            if(g>=19&&g<=24){
                id_63=(R.drawable.mistd);

            }

        }
        if(main.equals("Haze")){
            if(g>=0&&g<=7){
                id = R.drawable.a22;
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id = R.drawable.a22;
                id_63=(R.drawable.hazewe);

            }
            if(g>=17&&g<19){
                id = R.drawable.a22;
                id_63=(R.drawable.hazewe);
            }
            if(g>=19&&g<=24){
                id = R.drawable.a22;
                id_63=(R.drawable.mistd);

            }



        }
        if(main.equals("Extreme")){

            id = R.drawable.a1;
            id_63=(R.drawable.badweather);

        }
        if(main.equals("Clear")){
            if(g>=0&&g<=7){
                id = R.drawable.a31;
                id_63=(R.drawable.hoenixight);

            }
            if(g>7&&g<17){
                id = R.drawable.a32;
                id_63=(R.drawable.ed);


            }
            if(g>=17&&g<19){
                id = R.drawable.a32;
                id_63=(R.drawable.rsz_evening_sunset_hd);

            }
            if(g>=19&&g<=24){
                id = R.drawable.a31;
                id_63=(R.drawable.rsz_night_time_in_new_york_wallpaper1080);

            }




        }
        if(main.equals("Clouds")){
            id = R.drawable.a33;

            if(g>=0&&g<=7){

                id = R.drawable.a33;
                id_63=(R.drawable.backgroundnight);


            }
            if(g>7&&g<17){

                id = R.drawable.a34;
                id_63=(R.drawable.partlycloudy);


            }
            if(g>=17&&g<19){
                id = R.drawable.a34;

                id_63=(R.drawable.partlycloudy);

            }
            if(g>=19&&g<=24){
                id = R.drawable.a33;

                id_63=(R.drawable.backgroundnight);

            }
            if(sling.equals("scattered clouds")){


                if(g>=0&&g<=7){

                    id = R.drawable.a33;
                    id_63=(R.drawable.qq);

                }
                if(g>7&&g<17){

                    id = R.drawable.a34;
                    id_63=(R.drawable.qj);

                }
                if(g>=17&&g<20){
                    id = R.drawable.a34;
                    id_63=(R.drawable.qj);

                }
                if(g>=20&&g<=24){
                    id = R.drawable.a33;
                    id_63=(R.drawable.qq);

                }

            }
            if(sling.equals("broken clouds")){

                if(g>=0&&g<=7){

                    id = R.drawable.a26;

                    id_63=(R.drawable.qq);

                }
                if(g>7&&g<17){

                    id = R.drawable.a26;
                    id_63=(R.drawable.qj);

                }
                if(g>=17&&g<19){
                    id = R.drawable.a26;
                    id_63=(R.drawable.qj);


                }
                if(g>=19&&g<=24){
                    id = R.drawable.a26;
                    id_63=(R.drawable.qq);


                }

            }

            if(sling.equals("overcast clouds")){

                if(g>=0&&g<=7){

                    id = R.drawable.a27;

                    id_63=(R.drawable.qq);

                }
                if(g>7&&g<17){

                    id = R.drawable.a28;
                    id_63=(R.drawable.qj);

                }
                if(g>=17&&g<19){
                    id = R.drawable.a28;
                    id_63=(R.drawable.qj);


                }
                if(g>=19&&g<=24){
                    id = R.drawable.a27;
                    id_63=(R.drawable.qq);


                }
            }

        }






        return id;
    }
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };





    public static class LocalService extends Service {

        private int i = 0;

        private NotificationManager mNM;

        // Unique Identification Number for the Notification.
        // We use it on Notification start, and to cancel it.
        public LocalService() {
            super();
        }
        /**
         * Class for clients to access.  Because we know this service always
         * runs in the same process as its clients, we don't need to deal with
         * IPC.
         */
        public class LocalBinder extends Binder {
            LocalService getService() {
                return LocalService.this;
            }
        }

        @Override
        public void onCreate() {
            mNM = (NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE);

            // Display a notification about us starting.  We put an icon in the status bar.
            showNotification();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("LocalService", "Received start id " + startId + ": " + intent);


            return START_STICKY;
        }

        @Override
        public void onDestroy() {
            // Cancel the persistent notification.
            mNM.cancel(i);

            // Tell the user we stopped.
            Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }

        // This is the object that receives interactions from clients.  See
        // RemoteService for a more complete example.
        private final IBinder mBinder = new LocalBinder();

        /**
         * Show a notification while this service is running.
         */
        private void showNotification() {
            // In this sample, we'll use the same text for the ticker and the expanded notification
            String text = descripation;

            // The PendingIntent to launch our activity if the user selects this notification
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, LocalService.class), 0);
            SimpleDateFormat setrise = new SimpleDateFormat("hh:mm aa");

            // Set the info for the views that show in the notification panel.
            Notification notification = new Notification.Builder(this)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
                    .setSmallIcon(R.drawable.icon)
                    .setColor(Color.parseColor("#000000"))
                    .setTicker(text)// the status text
                    .setWhen(System.currentTimeMillis())// the time stamp
                    .setContentTitle("Disaster Management")  // the label of the entry
                    .setContentText("Notifications have been activated !")// the contents of the entry
                    .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                    .build();


            // Send the notification.

            mNM.notify(i, notification);
            i++;

        }
    }



    public static class TimeAlarm extends BroadcastReceiver {
            TimeZone sd;
           int  id = R.drawable.a31;

        private int hello( String main ,String sling){



            int id=0;

            Calendar c = Calendar.getInstance();
            c.setTimeZone(sd);


            String x = (String)DateFormat.format("HH",c.getTime());
            int g = Integer.parseInt(x);
            if(main.equals("Rain")){
                id = R.drawable.a5;
                if(g>=0&&g<=7){
                    id_63=(R.drawable.rainy);

                }
                if(g>7&&g<17){
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=17&&g<19){
                    id_63=(R.drawable.sunnyrain);

                }
                if(g>=19&&g<=24){
                    id_63=(R.drawable.rainy);

                }

                if(sling.equals("light rain")){
                    if(g>=0&&g<=7){
                        id = R.drawable.a45;
                        id_63=(R.drawable.rainy);

                    }
                    if(g>7&&g<17){
                        id = R.drawable.a39;
                        id_63=(R.drawable.sunnyrain);

                    }
                    if(g>=17&&g<19){
                        id = R.drawable.a39;
                        id_63=(R.drawable.sunnyrain);

                    }
                    if(g>=19&&g<=24){
                        id = R.drawable.a45;
                        id_63=(R.drawable.rainy);

                    }



                }

            }

            if(main.equals("Drizzle")){

                id = R.drawable.a5;


                if(sling.equals("light intensity drizzle")){
                    if(g>=0&&g<=7){
                        id = R.drawable.a45;

                    }
                    if(g>7&&g<17){
                        id = R.drawable.a39;

                    }
                    if(g>=17&&g<20){
                        id = R.drawable.a39;

                    }
                    if(g>=20&&g<=24){
                        id = R.drawable.a45;

                    }

                    id_63=(R.drawable.abh);


                }

            }
            if(main.equals("Thunderstorm")){
                id = R.drawable.a1;
                id_63=(R.drawable.unnamed);



            }
            if(main.equals("Mist")){
                id = R.drawable.a43;
                id_63=(R.drawable.unnamed);



            }

            if(main.equals("Fog")){
                id = R.drawable.a43;
                id_63=(R.drawable.unnamed);



            }
            if(main.equals("Snow")){
                id = R.drawable.a14;

                if(sling.equals("heavy snow")){

                    id = R.drawable.a18;


                }

                id_63=(R.drawable.snowas);

            }
            if(main.equals("Atmosphere")){

                id = R.drawable.a20;
                id_63=(R.drawable.hazewe);

            }
            if(main.equals("Haze")){

                id = R.drawable.a22;
                id_63=(R.drawable.hazewe);

            }
            if(main.equals("Extreme")){

                id = R.drawable.a43;
                id_63=(R.drawable.badweather);

            }
            if(main.equals("Clear")){
                if(g>=0&&g<=7){
                    id = R.drawable.a31;
                    id_63=(R.drawable.hoenixight);

                }
                if(g>7&&g<17){
                    id = R.drawable.a32;
                    id_63=(R.drawable.ed);


                }
                if(g>=17&&g<19){
                    id = R.drawable.a32;
                    id_63=(R.drawable.rsz_evening_sunset_hd);

                }
                if(g>=19&&g<=24){
                    id = R.drawable.a31;
                    id_63=(R.drawable.rsz_night_time_in_new_york_wallpaper1080);

                }
            }
            if(main.equals("Clouds")){
                if(g>=0&&g<=7){

                    id = R.drawable.a33;
                    id_63=(R.drawable.backgroundnight);


                }
                if(g>7&&g<17){

                    id = R.drawable.a34;
                    id_63=(R.drawable.partlycloudy);


                }
                if(g>=17&&g<19){
                    id = R.drawable.a34;

                    id_63=(R.drawable.partlycloudy);

                }
                if(g>=19&&g<=24){
                    id = R.drawable.a33;

                    id_63=(R.drawable.backgroundnight);

                }
                if(sling.equals("scattered clouds")){

                    if(g>=0&&g<=7){

                        id = R.drawable.a33;

                    }
                    if(g>7&&g<17){

                        id = R.drawable.a34;

                    }
                    if(g>=17&&g<20){
                        id = R.drawable.a34;

                    }
                    if(g>=20&&g<=24){
                        id = R.drawable.a33;

                    }

                }
                if(sling.equals("broken clouds")){

                    id = R.drawable.a26;


                }

                if(sling.equals("overcast clouds")){

                    if(g>=0&&g<=7){

                        id = R.drawable.a27;

                        id_63=(R.drawable.qq);

                    }
                    if(g>7&&g<17){

                        id = R.drawable.a28;
                        id_63=(R.drawable.qj);

                    }
                    if(g>=17&&g<19){
                        id = R.drawable.a28;
                        id_63=(R.drawable.qj);


                    }
                    if(g>=19&&g<=24){
                        id = R.drawable.a27;
                        id_63=(R.drawable.qq);


                    }
                }

            }






            return id;
        }
        private double lon,lat =0;
        private int id_6,id_63=0;
        private String fd="";


        private int i=0;
        public TimeAlarm() {
            super();
        }
        private  URL createUrlaaa( String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e("ih", "Problem building the URL ", e);
            }
            return url;
        }



        private  String makeHttpRequesta(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStreama = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStreama = urlConnection.getInputStream();
                    jsonResponse = readFromStreamas(inputStreama);
                } else {
                    Log.e("jhj", "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e("jbj", "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStreama != null) {
                    // Closing the input stream could throw an IOException, which is why
                    // the makeHttpRequest(URL url) method signature specifies than an IOException
                    // could be thrown.
                    inputStreama.close();
                }
            }
            return jsonResponse;
        }
        private  String readFromStreamas(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        private AndroidFlavor_three extractFeatureFromJsona(String earthquakeJSON) {
            // If the JSON string is empty or null, then return early.


            if (TextUtils.isEmpty(earthquakeJSON)) {
                return null;
            }

            // Create an empty ArrayList that we can start adding earthquakes to
            double x=0;
            String xx="";
            double y=0;
            double z=0;
            double zz=0;
            double zzz=0;

            AndroidFlavor_three earthquake=new AndroidFlavor_three(x,y,z,zz,zzz,xx);

            // Try to parse the JSON response string. If there's a problem with the way the JSON
            // is formatted, a JSONException exception object will be thrown.
            // Catch the exception so the app doesn't crash, and print the error message to the logs.
            try {

                // Create a JSONObject from the JSON response string
                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

                // Extract the JSONArray associated with the key called "features",
                // which represents a list of features (or earthquakes).
                JSONObject earthquakeObject = baseJsonResponse.getJSONObject("main");
                JSONObject aga = baseJsonResponse.getJSONObject("wind");
                double windspeed = aga.getDouble("speed");
                double winddirection=0;

                if(aga.length()==2){


                    winddirection = aga.getDouble("deg");

                }
                // For each earthquake in the earthquakeArray, create an {@link Earthquake} object

                // Get a single earthquake at position i within the list of earthquak
                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.

                // Extract the value for the key called "mag"
                double temp = earthquakeObject.getDouble("temp");
                double pressure = earthquakeObject.getDouble("pressure");
                double humidity = earthquakeObject.getDouble("humidity");
                JSONArray earthquakeObjecta = baseJsonResponse.getJSONArray("weather");
                JSONObject ag = earthquakeObjecta.getJSONObject(0);
                String main = ag.getString("main");
                String description = ag.getString("description");
                JSONObject hj = baseJsonResponse.getJSONObject("coord");
                lon = hj.getDouble("lon");


                lat = hj.getDouble("lat");



                city = baseJsonResponse.getString("name");



                JSONObject agah = baseJsonResponse.getJSONObject("sys");
                agf = agah.getString("country");
                sunrise=agah.getLong("sunrise");
                sunset=agah.getLong("sunset");

                fd = description;
                Log.d("main",main);
                Log.d("desc",description);

                id = hello(main,description);







                // Extract the value for the key called "place"

                earthquake = new AndroidFlavor_three(temp,windspeed,winddirection,humidity,pressure,main);


            }

            catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }

            // Return the list of earthquakes
            return earthquake;
        }



        private   AndroidFlavor_three fetchEarthquakeDataa(String requestUrl) {
            // Create URL object
            URL urla = createUrlaaa(requestUrl);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            try {
                jsonResponse = makeHttpRequesta(urla);
            } catch (IOException e) {
                Log.e("kjjk", "Problem making the HTTP request.", e);
            }

            // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
            AndroidFlavor_three earthquakes = extractFeatureFromJsona(jsonResponse);

            // Return the list of {@link Earthquake}s
            return earthquakes;
        }


        @Override
        public  void onReceive(final Context context, Intent intent) {


            class EarthquakeAsyncTasa extends AsyncTask<String, Void, AndroidFlavor_three> {
                @Override
//#b75757 colour is the best for forecasts

                protected AndroidFlavor_three doInBackground(String... urls) {
                    // Don't perform the request if there are no URLs, or the first URL is null.
                    if (urls.length < 1 || urls[0] == null) {
                        return null;
                    }

                    AndroidFlavor_three result = fetchEarthquakeDataa(urls[0]);

                    return result;

                }

                @Override
                protected   void onPostExecute(AndroidFlavor_three data) {

                    sd = TimeZone.getTimeZone("Beijing/China");

                    NotificationManager mNM;
                    mNM = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
                    // Set the icon, scrolling text and timestamp
                    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, weather.class), 0);
                    SimpleDateFormat setrise = new SimpleDateFormat("hh:mm aa");

                    String timezone_hour = "";

                    try {
                        FileInputStream fileIn = context.openFileInput("mytext700011file.txt");
                        InputStreamReader InputRead = new InputStreamReader(fileIn);

                        char[] inputBuffer = new char[100];
                        int charRead;
                        timezone_hour = "";
                        while ((charRead = InputRead.read(inputBuffer)) > 0) {
                            // char to string conversion
                            final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                            timezone_hour += readstring;
                        }
                        InputRead.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    double part = 0;
                    if (!timezone_hour.equals("")) {
                        part = Double.parseDouble(timezone_hour);

                        if (part < 0) {

                            part = Math.abs(part);
                            Log.d("fs", String.valueOf(part));

                            int ar = (int) part;

                            double part2 = part - ar;


                            int minutes = (int) (part2 * 60);
                            if (minutes == 0) {

                                sd = TimeZone.getTimeZone("GMT-" + ar + ":00");


                            } else {
                                sd = TimeZone.getTimeZone("GMT-" + ar + ":" + minutes + "" + minutes);

                                Log.d("aaa", "GMT-" + ar + ":00");
                            }

                            part = -48;
                        }
                        if (part > 0) {

                            part = Math.abs(part);
                            Log.d("fs", String.valueOf(part));

                            int ar = (int) part;

                            double part2 = part - ar;


                            int minutes = (int) (part2 * 60);
                            if (minutes == 0) {
                                sd = TimeZone.getTimeZone("GMT+" + ar + ":00");


                            } else {


                                sd = TimeZone.getTimeZone("GMT+" + ar + ":" + minutes);
                                Log.d("aaa", "GMT+" + ar + ":00");

                            }
                        }

                    }

                    if(data!=null) {
                        Notification notification = new Notification.Builder(context)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), id))
                                .setSmallIcon(id)
                                .setContentInfo(city)
                                .setColor(Color.parseColor("#000000"))
                                .setTicker("ahha")// the status text
                                .setWhen(System.currentTimeMillis())// the time stamp
                                .setContentTitle("Live Temperature : " + (int) (data.currentdegree - 273) + " C")  // the label of the entry
                                .setContentText(fd)// the contents of the entry
                                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                                .build();
                        // The PendingIntent to launch our activity if the user selects this notification
                        // Set the info for the views that show in the notification panel.
                        // Send the notification.
                        // We use a layout id because it is a unique number. We use it later to cancel
                        //
                        //7
                        String ka = "2";
                        try {
                            FileInputStream fileIn = context.openFileInput("my_ka22.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);

                            char[] inputBuffer = new char[100];
                            int charRead;
                            ka = "";
                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                // char to string conversion
                                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                ka += readstring;
                            }
                            InputRead.close();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("ka", ka);
                        if (Integer.parseInt(ka) == 0) {
                            mNM.notify(i, notification);

                            final Intent intent1 = new Intent(context, TimeAlarm.class);
                            context.startService(intent1);
                        }


                        i++;


                    }


                }
                // Clear the adapter of previous earthquake data

                private  AndroidFlavor_three earthquake;




            }
            String sad2="";
            try {
                FileInputStream fileIn = context.openFileInput("mytext7000file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                sad2="";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    sad2+= readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            context.stopService(intent);
            EarthquakeAsyncTasa  h  =new EarthquakeAsyncTasa();
            h.execute(sad2);




        }
    }

    Point p;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];


        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }
    private Intent notifyIntent;

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        int popupHeight =  LinearLayout.LayoutParams.WRAP_CONTENT;

        int[] location = new int[2];


        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup2);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup2, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
        popup.setAnimationStyle(R.style.animationName);


        ImageView fg  = (ImageView)findViewById(R.id.settiangs);
        fg.getLocationInWindow(location);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 330;
        int OFFSET_Y = 600;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x-200 , p.y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);

        TextView d = (TextView)layout.findViewById(R.id.non);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileout = openFileOutput("my_ka22.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write("0");
                    outputWriter.close();

                    //display file saved message

                } catch (Exception e) {
                    e.printStackTrace();
                }
                notifyIntent = new Intent(getApplicationContext(),TimeAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),60000, pendingIntent);


                Toast.makeText(getApplicationContext(),"Notifications on !!!(One Minute Interval)",Toast.LENGTH_LONG).show();


            }
        });

        TextView e = (TextView)layout.findViewById(R.id.nof);

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Notifications off !!!",Toast.LENGTH_LONG).show();

                Runnable m_Runnable = new Runnable() {
                    public void run()

                    {
                        Log.d("12st", URL_1);
                        try {
                            FileOutputStream fileout = openFileOutput("my_ka22.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write("2");
                            outputWriter.close();

                            //display file saved message

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };

                m_Runnable.run();

            }
        });


        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }



}


