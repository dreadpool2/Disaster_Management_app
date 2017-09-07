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
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
* {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link AndroidFlavor} objects.
* */
public class AndroidFlavorAdapter extends ArrayAdapter<AndroidFlavor> {


    private  final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public AndroidFlavorAdapter(Activity context, ArrayList<AndroidFlavor> androidFlavors) {
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        int max=0;

        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        AndroidFlavor currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        TextView colour = (TextView) listItemView.findViewById(R.id.colour);




        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(""+currentAndroidFlavor.getVersionName());
       double y = currentAndroidFlavor.getVersionName();
        if(y>0 && y<1 ){
            nameTextView.setBackgroundColor(Color.parseColor("#000000"));
            colour.setBackgroundColor(Color.parseColor("#000000"));

        }

        if(y>=1 && y<2 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FF57E909"));

            colour.setBackgroundColor(Color.parseColor("#FF57E909"));
        }
        if(y>=2 && y<3 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FF26C6DA"));

            colour.setBackgroundColor(Color.parseColor("#FF26C6DA"));
        }
        if(y>=3 && y<4 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FFF4511E"));

            colour.setBackgroundColor(Color.parseColor("#FFF4511E"));
        }
        if(y>=4 && y<5 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FFAD1457"));

            colour.setBackgroundColor(Color.parseColor("#FFAD1457"));
        }
        if(y>=5 && y<6 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FF501F54"));

            colour.setBackgroundColor(Color.parseColor("#FF501F54"));
        }
        if(y>=6 && y<7 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FFD25C5C"));

            colour.setBackgroundColor(Color.parseColor("#FFD25C5C"));
        }
        if(y>=7 && y<8 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FFBF0404"));

            colour.setBackgroundColor(Color.parseColor("#FFBF0404"));
        }
        if(y>=8 && y<9 ){
            nameTextView.setBackgroundColor(Color.parseColor("#FFFF0000"));

            colour.setBackgroundColor(Color.parseColor("#FFFF0000"));
        }

        if(y>=9 && y<10 ){
            nameTextView.setBackgroundColor(Color.parseColor("#000000"));

            colour.setBackgroundColor(Color.parseColor("#000000"));
        }




        if(listItemView!=null){
            Animation a = AnimationUtils.loadAnimation(listItemView.getContext(),R.anim.slidein);

            listItemView.startAnimation(a);


        }



        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextViewString txt = ((currentAndroidFlavor.getVersionNumber()).substring((currentAndroidFlavor.getVersionNumber()).lastIndexOf(",")+1));
        TextView numaberTextView = (TextView) listItemView.findViewById(R.id.vers);

        int iend = currentAndroidFlavor.getVersionNumber().indexOf(","); //this finds the first occurrence of "."
        int iaend = currentAndroidFlavor.getVersionNumber().indexOf(""); //this finds the first occurrence of "."



//in string thus giving you the index of where it is in the string

// Now iend can be -1, if lets say the string had no "." at all in it i.e. no "." is not found.
//So check and account for it.

        if (iend != -1)
        {String subString= currentAndroidFlavor.getVersionNumber().substring(0 , iend);
            numberTextView.setText(subString);
        }
//DOWN BELOW THE PLACE
        if (iaend != -1)
        { subaString= currentAndroidFlavor.getVersionNumber().substring(iend+1 , currentAndroidFlavor.getVersionNumber().length());
            numaberTextView.setText(subaString);
        }


        SharedPreferences pref =getContext().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String count="";
        String edt = pref.getString("country",count);



            //this will give abc  // Find the ImageView in the list_item.xml layout with the ID list_item_icon
            TextView iconView = (TextView) listItemView.findViewById(R.id.list_item_icon);
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            iconView.setText("" + getDateString(currentAndroidFlavor.getImageResourceId()));
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
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


}
