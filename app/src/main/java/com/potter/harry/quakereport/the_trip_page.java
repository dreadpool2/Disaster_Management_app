package com.potter.harry.quakereport;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

public class the_trip_page extends AppCompatActivity {
    public String sad = "";
    public List<Address> addresses = null;
    public String country = "";
    public String state = "";
    public Document doc;
    public String count = "";
    public String schoolbag[];
    public int g = 0;
    public String gh="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_trip_page);
          BackgroungTask ag = new BackgroungTask();
          ag.execute("https://www.wolframalpha.com/input/?i=weather+conditions+chennai+2013+june+23");
        try {
            FileInputStream fileIn = openFileInput("mytextfile_count.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            int charRead;
            count = "";
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                count += readstring;
            }
            InputRead.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
        Log.d("s", count);
        int y = Integer.parseInt(count);
        schoolbag = new String[y];


        for (int i = 0; i < Integer.parseInt(count); i++) {
            String k = "";
            try {
                FileInputStream fileIn = openFileInput("mytextfile" + i + ".txt");
                InputStreamReader InputRead = new InputStreamReader(fileIn);

                char[] inputBuffer = new char[100];
                int charRead;
                k = "";
                while ((charRead = InputRead.read(inputBuffer)) > 0) {
                    // char to string conversion
                    final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    k += readstring;
                }
                InputRead.close();


            } catch (Exception e) {

                e.printStackTrace();
            }

            schoolbag[i] = k;

        }


        TextView t_line = (TextView) findViewById(R.id.timeline);

        String timeline = "";
        for (int i = 0; i < schoolbag.length; i++) {

            String aa = "" + schoolbag[i];

            SpannableStringBuilder sb = new SpannableStringBuilder("" + aa);

// create a bold StyleSpan to be used on the SpannableStringBuilder
            StyleSpan b = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold

// set only the name part of the SpannableStringBuilder to be bold --> 16, 16 + name.length()
            sb.setSpan(b, 0, aa.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
            timeline = timeline + "-> " + aa;

        }
        t_line.setText(timeline);


        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            int charRead;
            sad = "";
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                sad += readstring;
            }
            InputRead.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
        Trip_planner a = new Trip_planner();
        Log.d("sss", sad);
        a.execute("http://api.openweathermap.org/data/2.5/weather?q=" + sad + "&appid=9c973da8885c5e636d2c93bcb6355bdd");


        //use jsoup and the html url of weather and climate.com



    }

    public String LOG_TAG = "HEY";

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrlas(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private String makeHttpRequestas(URL url) throws IOException {
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
    private String readFromStreamas(InputStream inputStream) throws IOException {
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
    public AndroidFlavor_trips extractFeatureFromJsonas(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.


        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        double y = 0;
        double x = 0;

        AndroidFlavor_trips earthquake = new AndroidFlavor_trips(x, y);

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONObject hj = baseJsonResponse.getJSONObject("coord");
            double lon = 0;
            double lat = 0;
            lat = hj.getDouble("lat");
            lon = hj.getDouble("lon");

            Log.d("lat", String.valueOf(lat));

            Log.d("lon", String.valueOf(lon));


            earthquake = new AndroidFlavor_trips(lat, lon);


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquake;
    }

    public AndroidFlavor_trips fetchEarthquakeDataas(String requestUrl) {
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
        AndroidFlavor_trips earthquakes = extractFeatureFromJsonas(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }


    public class Trip_planner extends AsyncTask<String, Void, AndroidFlavor_trips> {
        @Override
//#b75757 colour is the best for forecasts

        protected AndroidFlavor_trips doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            AndroidFlavor_trips result = fetchEarthquakeDataas(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(AndroidFlavor_trips data) {

            Geocoder geocoder;
            String address = "";
            String knownName = "";
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(data.lattu, data.lonnu, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() > 0) {
                country = addresses.get(0).getCountryName();
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                state = addresses.get(0).getAdminArea();
                String postalCode = addresses.get(0).getPostalCode();
                knownName = addresses.get(0).getLocality();
            }


            TextView one = (TextView) findViewById(R.id.trip_place);
            one.setText("" + knownName + "," + state + "," + country);
            Log.d("sss", "" + knownName + "," + country + "," + state);

            BackgroungTask ag = new BackgroungTask();
            ag.execute("https://www.wolframalpha.com/input/?i=weather+conditions+chennai+2013+june+23");
            Log.d("Ha","https://www.wolframalpha.com/input/?i=weather+conditions+chennai+2013+june+23");

        }
        // Clear the adapter of previous earthquake data

        private AndroidFlavor_trips earthquake;


    }

    public void nexta(View view) {

        g++;

        String timeline = "";
        Trip_planner a = new Trip_planner();
        a.execute("http://api.openweathermap.org/data/2.5/weather?q=" + schoolbag[g] + "&appid=9c973da8885c5e636d2c93bcb6355bdd");
        TextView t_line = (TextView) findViewById(R.id.timeline);


        for (int i = 0; i < schoolbag.length; i++) {

            if (g == i) {
                String aa = "" + schoolbag[i];

                SpannableStringBuilder sb = new SpannableStringBuilder("" + aa);

// create a bold StyleSpan to be used on the SpannableStringBuilder
                StyleSpan b = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold

// set only the name part of the SpannableStringBuilder to be bold --> 16, 16 + name.length()
                sb.setSpan(b, 0, aa.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
                timeline = timeline + Html.fromHtml("&#10132;") + aa;
            } else {
                timeline = timeline + Html.fromHtml("&#10132;")+ schoolbag[i];
            }
        }
        t_line.setText(timeline);
    }

    public class BackgroungTask extends AsyncTask<String, Void, String> {

        public String doInBackground(String... params) {
         doc = null;
            try {
                doc = Jsoup.connect(params[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String title;
            title = doc.title();
            Elements div;



            return gh;
        }

        public void onPostExecute(String result) {
            //
            TextView f = (TextView) findViewById(R.id.weather_stats);
            f.setText(result);
            Element link = doc.select("div.blue-circles rw-large sign-in-resolved results-view").first();
            Elements diva = doc.select("a[href*=/MSP/]");
            //div#results-container > div#results > section#answers > section.pod ng-scope async-non-replacement > section.sub clearfix ng-scope > div.output ng-scope isProduct > img.ng-isolate-scope
            Elements get = diva.select("img.ng-isolate-scope");
            String h = diva.text();
            Log.d("BABA","AAA");
            if(link!=null){
                Log.d("Url", link.text());
                Log.d("Url", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }
        }
    }
}