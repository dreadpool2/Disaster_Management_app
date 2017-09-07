package com.potter.harry.quakereport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class quakes extends AppCompatActivity {
    public int jajju=0;
    public int e_quake=0;
    public int e_quakemag=0;
    public int ghi=0;
    public int asa=0;
    public String srad="";
    public Snackbar snackbar;
    public int y=0;
    public int z=0;
    public   String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";
    public  final String USGS_REQUEST_URL2 =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";

    public   String sad=USGS_REQUEST_URL;
    public String subaString="";
    public TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);
        ListView a =(ListView)findViewById(R.id.list);
        a.setDivider(null);
         ProgressBar pbar= (ProgressBar)findViewById(R.id.pbar);
        pbar.setVisibility(View.VISIBLE);
        FrameLayout ya = (FrameLayout)findViewById(R.id.heya);

         snackbar = Snackbar
                .make(ya, "Earthquake occurences in "+joy()+"... with a minimum magnitude of "+asa+" in Richter Scale", Snackbar.LENGTH_INDEFINITE);
         Log.d("3",sad);



        a.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    z=0;
                    if(y==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.options);
                        Animation a = AnimationUtils.loadAnimation(quakes.this, R.anim.abc_slide_out_bottom);
                        options.startAnimation(a);
                        snackbar.dismiss();
                    }if(y>0) {
                    LinearLayout options = (LinearLayout) findViewById(R.id.options);

                    options.setVisibility(View.GONE);
                    snackbar.dismiss();
                    }Log.i("SCROLLING DOWN","TRUE");
                    y++;
                }
                if(mLastFirstVisibleItem>firstVisibleItem)

                {  y=0;

                    if(z==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.options);
                        options.setVisibility(View.VISIBLE);
                        Animation a = AnimationUtils.loadAnimation(quakes.this, R.anim.abc_slide_in_top);
                        options.startAnimation(a);
                        snackbar.show();
                    }


                    Log.i("SCROLLING UP","TRUE");
                    z++;
                }

                mLastFirstVisibleItem=firstVisibleItem;

            }
        });


        getSupportActionBar().setTitle("Results");
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new AndroidFlavorAdapter(this, new ArrayList<AndroidFlavor>());
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        txt=(TextView)findViewById(R.id.alert);
        txt.setVisibility(View.GONE);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        int asf = 0;
        int asfa = 0;
        int asfaa = 0;
        int asfaaa = 0;
        int aasf = 0;
        int aasfa = 0;
        int aasfaa = 0;
        int aasfaaa = 0;

        asa = pref.getInt("num1", asf);
        int ty=0;
        int u=0;

        u = pref.getInt("a", ty);

        if(u>0){

            task.execute("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000");
            ghi=1;

        }
        if(u==0){
        if(asa==0) {
            ghi=0;
            task.execute(sad);
        }
        if(asa>0) {
            ghi=0;
            int asad = pref.getInt("month", asfa);
            int asadd = pref.getInt("day", asfaa);
            int asaddd = pref.getInt("year", asfaaa);
            String month = "" + asad;
            String day = "" + asadd;
            String year = "" + asaddd;
            int aasad = pref.getInt("mo", aasfa);
            int aasadd = pref.getInt("dd", aasfaa);
            int aasaddd = pref.getInt("yr", aasfaaa);
            String mo = "" + aasad;
            String dd = "" + aasadd;
            String yr = "" + aasaddd;

            String d = year + "-" + month + "-" + day;
            String da = yr + "-" + mo + "-" + dd;
            //     https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";
//"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";


            if (asa == 1) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=1");


            }

            if (asa == 2) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=2");

            }
            if (asa == 3) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=3");

            }
            if (asa == 4) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=4");

            }
            if (asa == 5) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=5");

            }
            if (asa == 6) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=6");

            }
            if (asa == 7) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=7");

            }
            if (asa == 8) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=8");
            }
            if (asa == 9) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=9");

            }
            if (asa == 10) {
                sad = USGS_REQUEST_URL.replace("&minmag=1&limit=2000", "&starttime=" + d + "&endtime=" + da + "&minmagnitude=10");

            }
            task.execute(sad);
        }}
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                AndroidFlavor currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getImageResoaurceId());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        editor.putInt("a", 0);
        editor.apply();
    }

    public  class EarthquakeAsyncTask extends AsyncTask<String, Void, List<AndroidFlavor>> {
        public ProgressBar pbar= (ProgressBar)findViewById(R.id.pbar);
        public TextView txt = (TextView)findViewById(R.id.alert);

        @Override


        protected List<AndroidFlavor> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<AndroidFlavor> result = fetchEarthquakeData(urls[0]);
            return result;

        }

        @Override
        protected   void onPostExecute(List<AndroidFlavor> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            FrameLayout y = (FrameLayout)findViewById(R.id.heya);

            snackbar.setText("Earthquake occurences in "+joy()+"... with a minimum magnitude of "+asa+" in Richter Scale");
            snackbar.show();
            if(sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000")) {

                Log.d("1",sad);

               snackbar = Snackbar
                            .make(y, "Earthquake occurences in Countries... with a minimum magnitude of 1 in Richter Scale", Snackbar.LENGTH_INDEFINITE);
                snackbar.setActionTextColor(Color.parseColor("#FFF7F30D"));
                snackbar.show();

            }

            if(sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=magnitude&minmag=1&limit=2000")) {
                Log.d("2",sad);

             snackbar = Snackbar
                        .make(y, "Earthquake occurences in Countries... with a minimum magnitude of 1 in Richter Scale", Snackbar.LENGTH_INDEFINITE);
                snackbar.show();

            }

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if(!mAdapter.isEmpty()){
                pbar.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
                return;
            }

            else{
                mAdapter.addAll(data);

                pbar.setVisibility(View.GONE);
            }
        }}
    private  AndroidFlavorAdapter mAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() ==R.id.sign_out_menu){
            txt.setVisibility(View.GONE);
            e_quake=1;
            mAdapter.clear();
            EarthquakeAsyncTask task = new EarthquakeAsyncTask();
            task.execute("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000");
            ProgressBar pbar= (ProgressBar)findViewById(R.id.pbar);
            pbar.setVisibility(View.VISIBLE);
            sad="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";
            return true;


        }

        if(item.getItemId()==R.id.info){
            if (p != null){
                showPopup(quakes.this, p);
        }

            return true;

        }

        if(item.getItemId()==R.id.menu_magnitude){
            String sada = "";

            EarthquakeAsyncTask a =new EarthquakeAsyncTask();
            if(!sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000")) {
                e_quakemag=0;
                sad =sad.replaceAll("orderby=time","orderby=magnitude");
            }
            if(sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000")) {
                e_quakemag=1;
                sad="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=magnitude&minmag=1&limit=2000";
            }
            ProgressBar pbar= (ProgressBar)findViewById(R.id.pbar);

            pbar.setVisibility(View.VISIBLE);

            a.execute(sad);
            return true;

        }

        if(item.getItemId()==R.id.menu_date){

            String sada = "";

            EarthquakeAsyncTask a =new EarthquakeAsyncTask();
            if(!sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=magnitude&minmag=1&limit=2000")) {
                e_quakemag=0;
                sad =sad.replaceAll("orderby=magnitude","orderby=time");

            }
            if(sad.equals("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=magnitude&minmag=1&limit=2000")) {
                 e_quakemag=1;
                sad="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=2000";
            }

            ProgressBar pbar= (ProgressBar)findViewById(R.id.pbar);

            pbar.setVisibility(View.VISIBLE);

            a.execute(sad);
            return true;

        }

        return super.onOptionsItemSelected(item);

    }
public void search(View view){
    jajju++;
    e_quake=0;
    Animation first = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
    Animation second = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);


    Intent i = new Intent(this,info.class);
    startActivity(i);
    overridePendingTransition(0,R.anim.fade_out);
    finish();




}



    public void exit(View view){


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);



    }


    public void back(View view){

        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(i);
        overridePendingTransition(0,R.anim.fade_out);
        finish();



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

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = 450;
        int popupHeight = 420;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 330;
        int OFFSET_Y = 600;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }



































        /** Sample JSON response for a USGS query */
        /**
         * Create a private constructor because no one should ever create a {@link } object.
         * This class is only meant to hold static variables and methods, which can be accessed
         * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
         */


        public  final String LOG_TAG = "Hey!!";

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
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
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
        public  List<AndroidFlavor> extractFeatureFromJson(String earthquakeJSON) {
            // If the JSON string is empty or null, then return early.


            if (TextUtils.isEmpty(earthquakeJSON)) {
                return null;
            }

            // Create an empty ArrayList that we can start adding earthquakes to
            List<AndroidFlavor> earthquakes = new ArrayList<>();

            // Try to parse the JSON response string. If there's a problem with the way the JSON
            // is formatted, a JSONException exception object will be thrown.
            // Catch the exception so the app doesn't crash, and print the error message to the logs.
            try {

                // Create a JSONObject from the JSON response string
                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

                // Extract the JSONArray associated with the key called "features",
                // which represents a list of features (or earthquakes).
                JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

                // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
                for (int i = 0; i < earthquakeArray.length(); i++) {

                    // Get a single earthquake at position i within the list of earthquakes
                    JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                    // For a given earthquake, extract the JSONObject associated with the
                    // key called "properties", which represents a list of all properties
                    // for that earthquake.
                    JSONObject properties = currentEarthquake.getJSONObject("properties");

                    // Extract the value for the key called "mag"
                    double magnitude = properties.getDouble("mag");

                    // Extract the value for the key called "place"
                    String location = properties.getString("place");

                    // Extract the value for the key called "time"
                    long time = properties.getLong("time");


                    // Extract the value for the key called "url"
                    String url = properties.getString("url");

                    // Create a new {@link Earthquake} object with the magnitude, location, time,
                    // and url from the JSON response.
                    AndroidFlavor earthquake = new AndroidFlavor(magnitude, location, time, url);

                    int iend = location.indexOf(","); //this finds the first occurrence of "."
                    int iaend = location.indexOf(""); //this finds the first occurrence of "."

                    if (iaend != -1)
                    { subaString= location.substring(iend+1 , location.length());

                    }
                     srad = joy() ;

                    if(ghi==1) {
                        earthquakes.add(earthquake);
                    }if(ghi==0) {
                        if (srad.equals("All")) {

                            earthquakes.add(earthquake);


                        }


                        if (e_quake == 0 && e_quakemag == 0) {


                            if ((" " + srad).equals(subaString)) {
                                earthquakes.add(earthquake);
                            }


                        }

                        if (e_quake == 1 || e_quakemag == 1) {
                            earthquakes.add(earthquake);


                        }
                    }

                    // Add the new {@link Earthquake} to the list of earthquakes.
                }
                ghi=0;
             e_quake=0;
            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }

            // Return the list of earthquakes
            return earthquakes;
        }
        public  List<AndroidFlavor> fetchEarthquakeData(String requestUrl) {
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
            List<AndroidFlavor> earthquakes = extractFeatureFromJson(jsonResponse);

            // Return the list of {@link Earthquake}s
            return earthquakes;
        }

        private  String joy(){
            String sra="All";
            try {
                FileInputStream fileIn = openFileInput("mytext1023file.txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                sra = "";
                int charRead;

                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    sra += readstring;

                }

                InputRead.close();



            } catch (Exception e) {
                e.printStackTrace();
            }

            return sra;
        }













    }



