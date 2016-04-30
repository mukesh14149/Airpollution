package com.example.mukesh.airpollution;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mukesh on 17/1/16.
 */
public class ServerRequests {
        ProgressDialog progressDialog;
        public static final int CONNECTION_TIMEOUT= 1000*15;
        public static final String SERVER_ADDRESS= "http://helo.host56.com/";

        public ServerRequests(Context context){
            progressDialog =new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Processing");
            progressDialog.setMessage("pls wait..");
        }
        public void storeUserDataInBackground(User user, GetUserCallback userCallback){
            progressDialog.show();
            new StoreUserDataAsyncTask(user, userCallback).execute();
        }
        public void fetchUserDataInBackground(User user, GetUserCallback callBack){
            progressDialog.show();
            new fetchUserDataAsyncTask(user, callBack).execute();
        }

        public class  StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User user;
        GetUserCallback userCallback;

            public StoreUserDataAsyncTask(User user, GetUserCallback userCallback){
                this.user=user;
                this.userCallback=userCallback;
            }
            @Override
            protected Void doInBackground(Void... params) {
                //ArrayList<NameValuePair> dataToSend=new ArrayList<NameValuePair>();
                System.out.println("batman");
                System.out.println(user.location+""+user.password+""+user.username);


                HttpURLConnection urlConnection = null;
                String string[]=null;
                final String target_uri =
                        "http://ridesharingfriend.com/EVS/registeruser.php";
                try {
                    URL url = new URL(target_uri.toString());
                    // Create the request to OpenWeatherMap, and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.connect();
                    PrintWriter out=new PrintWriter(urlConnection.getOutputStream());
                    out.write("person_location="+user.location);
                    out.write("&");
                    out.write("person_name="+user.username);
                    out.write("&");
                    out.write("person_password="+user.password);

                    System.out.println("kutte");
                  //  Log.e(LOG_TAG,dataToSend.toString());


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

            @Override
            protected void onPostExecute(Void aVoid){
               progressDialog.dismiss();
                userCallback.done(null);

                super.onPostExecute(aVoid);
            }
    }

    public class  fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            //ArrayList<NameValuePair> dataToSend=new ArrayList<>();
            System.out.println("batman");
            System.out.println(user.password+""+user.username);

            //dataToSend.add(new BasicNameValuePair("person_name", user.username));
            //dataToSend.add(new BasicNameValuePair("person_password", user.password));
            User returnedUser=null;
            HttpURLConnection urlConnection = null;
            String string[]=null;
            final String target_uri =
                    "http://ridesharingfriend.com/EVS/getLoginDetails.php";
            try {

                URL url = new URL(target_uri.toString());
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();
                PrintWriter out=new PrintWriter(urlConnection.getOutputStream());
                out.write("person_name="+user.username);
                out.write("&");
                out.write("person_password="+user.password);

                System.out.println("kutte");
                //  Log.e(LOG_TAG,dataToSend.toString());


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

                JSONObject jObject =new JSONObject(buffer.toString());
                if(jObject.length()==0){
                    returnedUser=null;
                    System.out.println("chl be");

                }else{
                    String id=jObject.getString("id");
                    String name=jObject.getString("name");
                    String location=jObject.getString("location");
                    System.out.println("dekho dekho"+id+name+location);
                    returnedUser=new User(id, name, location);
                }


            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection!=null){
                    urlConnection.disconnect();
                }
            }
            System.out.println("return");
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser){
            progressDialog.dismiss();
            userCallback.done(returnedUser);

            super.onPostExecute(returnedUser);
        }
    }
}
