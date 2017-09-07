package com.potter.harry.quakereport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class new_trip extends AppCompatActivity {
    public int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

    }


    public void next(View view){


        LinearLayout layout = (LinearLayout) findViewById(R.id.oho);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"
            LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER);

            row.setOrientation(LinearLayout.VERTICAL);
            row.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));


        TextView ja  = new TextView(this);
        ja.setText(Html.fromHtml("&#8595;"));
        ja.setTextSize(30);
        ja.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
        row.addView(ja);


            EditText j  = new EditText(this);
                j.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
                j.setId(k);

                row.addView(j);;



            layout.addView(row);

        if(k==0){
            EditText a = (EditText)findViewById(R.id.a_1);

            try {

                FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(a.getText().toString());
                outputWriter.close();
                Toast.makeText(getBaseContext(),"-1th file saved !", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(k!=0) {

            try {
                EditText a = (EditText)findViewById(k-1);
                FileOutputStream fileout = openFileOutput("mytextfile" + (k - 1) + ".txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(a.getText().toString());
                outputWriter.close();
                Toast.makeText(getBaseContext(), (k - 1) + "th file saved !", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        k++;
           }
    public void done_trip(View view){
        if(k==0){
            EditText a = (EditText)findViewById(R.id.a_1);

            try {

                FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(a.getText().toString());
                outputWriter.close();
                Toast.makeText(getBaseContext(),"-1th file saved !", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(k!=0) {

            try {
                EditText a = (EditText) findViewById(k - 1);
                FileOutputStream fileout = openFileOutput("mytextfile" + (k) + ".txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(a.getText().toString());
                outputWriter.close();
                Toast.makeText(getBaseContext(), k + "th file saved !", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            FileOutputStream fileout = openFileOutput("mytextfile_count.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(""+(k+1));
            outputWriter.close();
            Toast.makeText(getBaseContext(), "yath file saved !", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i = new  Intent(this,the_trip_page.class);
        startActivity(i);

        }


    public class BackgroungTask extends AsyncTask<String, Void, String> {

        public String doInBackground(String... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect(params[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String title;
            title = doc.title();
            Elements div;
            Element masthead = doc.select("select.SearchCity1_ddl_continent").first();
            Elements links = masthead.select("option");
            String ad = links.text();
            return ad;
        }

        public void onPostExecute(String result) {
            //
            TextView f = (TextView) findViewById(R.id.weather_stats);
            f.setText(result);
        }
    }

}
