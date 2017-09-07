/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.potter.harry.quakereport;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/*
* {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link AndroidFlavor} objects.
* */
public class AndroidFlavorAdapter_two extends ArrayAdapter<AndroidFlavor_two> {
    public Drawable d;
    public Bitmap drwa;
    public TimeZone sd;
    ;;


    private  final String LOG_TAG = AndroidFlavorAdapter_two.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public AndroidFlavorAdapter_two(Activity context, ArrayList<AndroidFlavor_two> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    public String subaString="";
    public int y=0;
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        int max=0;
        sd = TimeZone.getTimeZone("Kolkata/India");

        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.forecast_item, parent, false);
        }


        final AndroidFlavor_two currentAndroidFlavor = getItem(position);


        if(convertView!=null){
            Animation a = AnimationUtils.loadAnimation(convertView.getContext(),R.anim.shake);

            convertView.startAnimation(a);


        }
        String timezone_hour = "";

        try {
            FileInputStream fileIn = getContext().openFileInput("mytext700011file.txt");
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




        final  ImageView weather = (ImageView) listItemView.findViewById(R.id.Description);
        TextView speed = (TextView) listItemView.findViewById(R.id.speed);
        TextView deg = (TextView) listItemView.findViewById(R.id.deg);
        TextView temp_kf= (TextView) listItemView.findViewById(R.id.temp_kf);
        TextView temp= (TextView) listItemView.findViewById(R.id.Temperature);
        final TextView time= (TextView) listItemView.findViewById(R.id.time);
        final LinearLayout background= (LinearLayout) listItemView.findViewById(R.id.thunder);
        background.setBackgroundResource(R.drawable.wdf);

        Date date = new Date(currentAndroidFlavor.time*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat sdfd = new SimpleDateFormat("hh:mm aa");
        sdfd.setTimeZone(sd);

        SimpleDateFormat sdfad = new SimpleDateFormat("HH");
        sdfad.setTimeZone(sd);
        String ah="";
        if(sdfad.format(date)!=null){
             ah = sdfad.format(date);

            y = Integer.parseInt(ah);

        }




        TextView saspeed = (TextView) listItemView.findViewById(R.id.speed);
        speed.setVisibility(View.GONE);

        TextView daseg = (TextView) listItemView.findViewById(R.id.deg);
        deg.setVisibility(View.GONE);

        TextView temp_ksf= (TextView) listItemView.findViewById(R.id.temp_kf);
        temp_kf.setVisibility(View.GONE);
        TextView temp_2= (TextView) listItemView.findViewById(R.id.temp_akf);
        temp_2.setVisibility(View.GONE);

        TextView temp_3= (TextView) listItemView.findViewById(R.id.wind_direction);
        temp_3.setVisibility(View.GONE);
        TextView temp_4= (TextView) listItemView.findViewById(R.id.wind_speed);
        temp_4.setVisibility(View.GONE);

        TextView tempa_4= (TextView) listItemView.findViewById(R.id.more);
        tempa_4.setVisibility(View.VISIBLE);



        time.setText(sdfd.format(date));
        speed.setText(""+((int)(currentAndroidFlavor.speed-0))+"km/hr");
        
        double ap = currentAndroidFlavor.deg;
        
        if(ap>348.75&&ap<=360||ap>0&&ap<=11.25){

            deg.setText("N ");



        }


        if(ap>11.25&&ap<=33.75){

            deg.setText("NNE ");

        }



        if(ap>33.75&&ap<=56.25){


            deg.setText("NE ");

        }

        if(ap>56.25&&ap<=78.75){


            deg.setText("ENE ");

        }

        if(ap>78.75&&ap<=101.25){

            deg.setText("E ");

        }

        if(ap>101.25&&ap<=123.75){

            deg.setText("ESE ");

        }

        if(ap>123.75&&ap<=146.25){

            deg.setText("SE ");

        }
        if(ap>146.25&&ap<=168.75){

            deg.setText("SSE ");

        }

        if(ap>168.75&&ap<=191.25){

            deg.setText("S ");

        }

        if(ap>191.25&&ap<=213.75){

            deg.setText("SSW ");

        }
        if(ap>213.75&&ap<=236.25){

            deg.setText("SW ");

        }

        if(ap>256.25&&ap<=258.75){

            deg.setText("WSW ");

        }

        if(ap>258.75&&ap<=281.25){

            deg.setText("W ");

        }

        if(ap>281.25&&ap<=303.25){

            deg.setText("WNW ");

        }

        if(ap>303.25&&ap<=326.25){

            deg.setText("NW ");

        }
        if(ap>326.25&&ap<=348.75){

            deg.setText("NNW ");

        }












        temp_kf.setText(""+currentAndroidFlavor.temp_kf);
        int i = ((int)(currentAndroidFlavor.temp-273));
        String  s = String.valueOf(i);

        SpannableStringBuilder cs = new SpannableStringBuilder(""+((int)(currentAndroidFlavor.temp-273))+ Html.fromHtml("&#176;")+"C");
        if(s.length()==2){

            cs.setSpan(new RelativeSizeSpan(0.75f), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        }

        if(s.length()==1){

            cs.setSpan(new RelativeSizeSpan(0.75f), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
        if(s.length()==3){

            cs.setSpan(new RelativeSizeSpan(0.75f), 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
        temp.setText(cs);
        temp.startAnimation(AnimationUtils.loadAnimation(listItemView.getContext(),R.anim.blink));




                      background.setBackgroundResource(hello(currentAndroidFlavor.main,currentAndroidFlavor.descrip));





        return listItemView;
    }

    private  void remove(int position,    View c) {
        c.setVisibility(View.VISIBLE);
    }

    private  void removed(int position,    View c) {
        c.setVisibility(View.GONE);
    }


    private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy                                    HH:mm:ss ");
        return formatter.format(timeInMilliseconds);
    }

    private int hello( String main ,String sling){
        int id =0;

        int g = y;
        if(main.equals("Rain")){

            id = R.drawable.a5;


            if(sling.equals("light rain")){
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



            }

        }
        if(main.equals("Thunderstorm")){
            id = R.drawable.a1;


        }
        if(main.equals("Atmosphere")){

            id = R.drawable.a20;

        }
        if(main.equals("Clear")){
            if(g>=0&&g<=7){
                id = R.drawable.a31;

            }
            if(g>7&&g<17){
                id = R.drawable.a32;

            }
            if(g>=17&&g<20){
                id = R.drawable.a32;

            }
            if(g>=20&&g<=24){
                id = R.drawable.a31;

            }




        }

        if(main.equals("Mist")){
            if(g>24&&g<=7){
                id = R.drawable.a31;

            }
            if(g>7&&g<17){
                id = R.drawable.a32;

            }
            if(g>=17&&g<20){
                id = R.drawable.a32;

            }
            if(g>=20&&g<=24){
                id = R.drawable.a31;

            }




        } if(main.equals("Fog")){
            if(g>=0&&g<=7){
                id = R.drawable.a31;

            }
            if(g>7&&g<17){
                id = R.drawable.a32;

            }
            if(g>=17&&g<20){
                id = R.drawable.a32;

            }
            if(g>=20&&g<=24){
                id = R.drawable.a31;

            }




        }
        if(main.equals("Clouds")){
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

                }
                if(g>7&&g<17){

                    id = R.drawable.a28;

                }
                if(g>=17&&g<20){
                    id = R.drawable.a28;

                }
                if(g>=20&&g<=24){
                    id = R.drawable.a27;

                }
            }

        }if(main.equals("Clear")){
            if(g>=0&&g<=7){

                id = R.drawable.a31;

            }
            if(g>7&&g<17){

                id = R.drawable.a32;

            }
            if(g>=17&&g<20){
                id = R.drawable.a32;

            }
            if(g>=20&&g<=24){
                id = R.drawable.a31;

            }


        }




        return id;
    }

}
