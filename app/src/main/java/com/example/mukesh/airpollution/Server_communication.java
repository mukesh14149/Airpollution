package com.example.mukesh.airpollution;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by mukesh on 3/3/16.
 */
public class Server_communication extends AsyncTask<List<NameValuePair>, Void, Void> {


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
    protected Void doInBackground(List<NameValuePair>... dataToSend) {

        HttpURLConnection urlConnection = null;
        String string[]=null;
        final String target_uri =
                "http://ridesharingfriend.com/EVS/insertuser.php";
        try {
            URL url = new URL(target_uri.toString());
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            PrintWriter out=new PrintWriter(urlConnection.getOutputStream());
            out.write("username=Mukkuing");

            out.close();


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
