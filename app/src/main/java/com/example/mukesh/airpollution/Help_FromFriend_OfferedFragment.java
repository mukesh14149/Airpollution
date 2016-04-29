package com.example.mukesh.airpollution;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class Help_FromFriend_OfferedFragment extends Fragment {

    ListView msgList;
    ArrayList<post> details= new ArrayList<>();
    AdapterView.AdapterContextMenuInfo info;
    private String Preference;
    private View rootView;

    public Help_FromFriend_OfferedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_help__from_friend__offered, container, false);




        //check whether Movies key is present in sharedpref.
        if (savedInstanceState == null || !savedInstanceState.containsKey("POSTS")) {
            updatePosts(getActivity(),rootView);
            //gridview = (GridView) rootView.findViewById(R.id.gridView);
          //  msgList = (ListView) rootView.findViewById(R.id.listView_post);
        }

        //if sharedpref already contain a key Movies
        else {
            post[] tem_detail;
            tem_detail = (post[]) savedInstanceState.getParcelableArray("POSTS");

            ArrayList<post> te = new ArrayList<post>(Arrays.asList(tem_detail));
           details= te;

           // msgList = (ListView) rootView.findViewById(R.id.listView_post);

        }




        msgList = (ListView) rootView.findViewById(R.id.listView_post);


       /* FetchPostList FetchPostData = new FetchPostList(getActivity(),rootView);
                FetchPostData.execute("delhi");*/

        msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                post postDetail = details.get(position);
                Intent intent = new Intent(getActivity().getApplication(), postDetailActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("POST", postDetail);
                intent.putExtras(b);
                startActivity(intent);

                Toast.makeText(getActivity(),postDetail.getPost_details(),Toast.LENGTH_SHORT).show();

            }
        });


        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        updatePosts(getActivity(), rootView);    //whenever activity start it will update the content.
    }


    @Override
    public void onSaveInstanceState(Bundle saving_State) {


        post[] tem_detail = details.toArray(new post[details.size()]);

        saving_State.putParcelableArray("POSTS",tem_detail);
        saving_State.putString("Preference", Preference);
        super.onSaveInstanceState(saving_State);

    }




    private void updatePosts(Context context, View rootView){
        //If internet is available
        if(Is_Online()==true) {
            FetchPostList updateMovies = new FetchPostList(context,rootView);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            Preference = sharedPref.getString(getString(R.string.area), getString(R.string.pref_area));
            updateMovies.execute(Preference);
        }
        else
        {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), getString(R.string.offline_message),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
            toast.show();
        }
    }


    public boolean Is_Online(){
        ConnectivityManager connectivity = (ConnectivityManager)getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }





public class FetchPostList extends AsyncTask<String,Void,post[]> {

    private Context mContext;
    private View rootView;

    private final String LOG_TAG = FetchPostList.class.getSimpleName();
    public  FetchPostList(Context context, View rootView){
        this.mContext=context;
        this.rootView=rootView;
    }


    protected post[] doInBackground(String... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String postJsonStr = null;




        try {
            Uri buildUri = Uri.parse(getString(R.string.post_list_url)).buildUpon()
                    .appendQueryParameter(getString(R.string.area), params[0])
                    .build();
            Log.e(LOG_TAG, buildUri.toString());
            URL url = new URL(buildUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(getString(R.string.request_method));
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if(inputStream == null)
                postJsonStr= null;

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if(buffer.length() == 0)
                postJsonStr = null;

            postJsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e("HelpOfferFragment", "Error ", e);
            // If the code didn't successfully get the popular movies list, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("HepOfferFragment", "Error closing stream", e);
                }
            }
        }
        try {
            return getPostDataFromJson(postJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG,e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(post[] result) {
           details= new ArrayList<>();

        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                details.add(result[i]);
                Log.v(LOG_TAG, "Post Heading: " + result[i].getPost_heading());
                Log.v(LOG_TAG, "post postOwner: " + result[i].getPost_owner_name());
            }

            msgList.setAdapter(new postListViewCustomAdaptor(details, getActivity()));

        }

    }


    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     *
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    private post[] getPostDataFromJson(String postJsonStr)
            throws JSONException {

        Log.v(LOG_TAG, "starting getMovieData : " + postJsonStr);

        // These are the names of the JSON objects that need to be extracted.
        final String RESULTS= "results";




        JSONObject postJson = new JSONObject(postJsonStr);
        JSONArray postArray = postJson.getJSONArray(RESULTS);



        post[] postResultStr = new post[postArray.length()];

        for(int i = 0; i < postArray.length(); i++) {


            postResultStr[i] = new post();



            // Get the JSON object of movies individually
            JSONObject postDetailsObj = postArray.getJSONObject(i);


            final String post_id = postDetailsObj.getString("post_id");
            final String post_person_id = postDetailsObj.getString("post_person_id");
            final String post_owner_name = postDetailsObj.getString("post_owner_name");
            final String post_heading = postDetailsObj.getString("post_heading");
            final String post_area = postDetailsObj.getString("post_area");
            final String post_location = postDetailsObj.getString("post_location");
            final String post_time = postDetailsObj.getString("post_time");
            final String post_details = postDetailsObj.getString("post_details");
            final String post_photo = postDetailsObj.getString("post_photo");;

            post postDetail = new post(post_id, post_person_id, post_owner_name, post_heading, post_area, post_location, post_time, post_details, post_photo);

            postResultStr[i] = postDetail;


        }

        for (post s : postResultStr) {
            Log.v(LOG_TAG, "post Title: " + s.getPost_heading());
            Log.v(LOG_TAG, "post Description: " + s.getPost_details());
            Log.v(LOG_TAG, "post locations: " + s.getPost_loaction());
        }
        return postResultStr;

    }


}




}




