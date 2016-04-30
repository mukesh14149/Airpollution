package com.example.mukesh.airpollution;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by mukesh on 3/3/16.
 */
public class Server_communication extends AsyncTask<NameValuePair, Void, Void> {

    public static String LOG_TAG="web";
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }



    @Override
    protected Void doInBackground(NameValuePair... dataToSend) {
        System.out.println(Arrays.asList(dataToSend).size());
        HttpURLConnection urlConnection = null;
        String string[]=null;
        final String target_uri =
                "http://ridesharingfriend.com/EVS/insertpost.php";
        try {
            URL url = new URL(target_uri.toString());
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            PrintWriter out=new PrintWriter(urlConnection.getOutputStream());
            out.write(Arrays.asList(dataToSend).get(0).toString());
            out.write("&");
           out.write(Arrays.asList(dataToSend).get(1).toString());
            out.write("&");
            out.write(Arrays.asList(dataToSend).get(2).toString());
            out.write("&");
            out.write(Arrays.asList(dataToSend).get(3).toString());
            out.write("&");
            out.write(Arrays.asList(dataToSend).get(4).toString());
            System.out.println("kutte");
            Log.e(LOG_TAG,dataToSend.toString());
            System.out.println(Arrays.asList(dataToSend).get(0).toString());
            System.out.println(Arrays.asList(dataToSend).get(1).toString());
            System.out.println(Arrays.asList(dataToSend).get(2).toString());
            System.out.println(Arrays.asList(dataToSend).get(3).toString());
            System.out.println(Arrays.asList(dataToSend).get(4).toString());
            System.out.println(Arrays.asList(dataToSend).size());
            System.out.println(dataToSend);

            out.close();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            System.out.println(buffer.toString());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
        }

        return null;
    }

}
