package com.example.mukesh.airpollution;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationData extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();
    public static ArrayList<String> data = new ArrayList<String>();
    public static String j = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_data);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            j = (String) b.get("message");
            data = (ArrayList<String>) b.get("message2");
        }
/*
        Thread downloadThread = new Thread() {
            public void run() {

                try {
                    ArrayList<String> area = new ArrayList<String>();
                    area.add("D.C.E.");
                    area.add("Punjabi5Bagh");
                    area.add("Shadipur");
                    area.add("Dwarka");
                    area.add("Mandir5Marg");
                    area.add("ITO");
                    area.add("Anand5Vihar");
                    area.add("R5K5Puram");
                    area.add("Ihbas");
                    area.add("Civil5Lines");
                    area.add("IGI5Airport");
                    for (String temp : area) {
                        Log.e(LOG_TAG, "Logging Area");
                        try {
                            Web_crawling w = new Web_crawling(getApplicationContext());
                            data.add(w.web_crawl(temp));
                            //Log.e(LOG_TAG, data.get(i).toString());

                            Log.e(LOG_TAG, "Logging TRY");
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(LOG_TAG, "Catch Error");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        downloadThread.start();
        */


        if (j == "1")
            func1();
        if (j == "2")
            func2();
        if (j == "3")
            func3();
        if (j == "4")
            func4();
        if (j == "5")
            func5();
        if (j == "6")
            func6();
        if (j == "7")
            func7();
        if (j == "8")
            func8();
        if (j == "9")
            func9();

    }



    public void func1()
    {

    }
    void func2()
    {

    }
    void func3()
    {

    }
    void func4()
    {

    }
    void func5()
    {

    }
    void func6()
    {

    }
    void func7()
    {

    }
    void func8()
    {

    }
    void func9()
    {

    }
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> mForecastAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Create some dummy data for the ListView.  Here's a sample weekly forecast
//            String[] data = {
//                    "Mon 6/23 - Sunny - 31/17",
//                    "Tue 6/24 - Foggy - 21/8",
//                         "Thurs 6/26 - Rainy - 18/11",
//              "Wed 6/25 - Cloudy - 22/17",
//                     "Fri 6/27 - Foggy - 21/10",
//                    "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
//                    "Sun 6/29 - Sunny - 20/7"
//            };


            //String array[] = new String[data.size()];
            //for(int j =0;j<data.size();j++){


            String array[] = new String[2];
            array[0] = data.get(Integer.parseInt(j));
                System.out.println(array[0]);

            String [] x = array[0].split(",");
            ArrayList<String> t= new ArrayList<String>();
            int j=0;
            String r="";
            int i=0;
            int c=0;
            for(i=0;i<x.length;i++)
            {
                c++;
                r=r+x[i];
                if((c==0)||(c%3==0))
                {
                    t.add(r);
                    //t[j]=r;
                    j++;
                    r="";
                }
            }

//            System.out.println(array[0]);
            List<String> weekForecast = new ArrayList<String>(t);


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            mForecastAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)

                            R.layout.list_item_forecast, // The name of the layout ID.
                            R.id.list_item_forecast_textview, // The ID of the textview to populate.
                            weekForecast);

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(mForecastAdapter);

            return rootView;
        }
    }
}


