package com.example.mukesh.airpollution;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tarun on 15/4/16.
 */
public class postListViewCustomAdaptor extends BaseAdapter {

    private ArrayList<post> _data;
    Context _c;

    postListViewCustomAdaptor (ArrayList<post> data, Context c){
        _data = data;
        _c = c;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_post, null);
            Log.v("custom Adaptor","v is null   ");
        }


        //ImageView image = (ImageView) v.findViewById(R.id.post_photo);
        TextView titleView = (TextView)v.findViewById(R.id.post_title);
        TextView ownerNameView = (TextView)v.findViewById(R.id.post_owner_name);
        TextView content = (TextView)v.findViewById(R.id.post_content);

        post post_details = _data.get(position);
        //image.setImageResource(post_details .getPost_photo());
        Log.v("custom Adaptor", "setting postHeading: " + post_details.getPost_heading());

        Log.v("custom Adaptor", "title from View : " +  titleView.getText());

        titleView.setText(post_details.getPost_heading());
        ownerNameView.setText(post_details .getPost_owner_name());
        content.setText(post_details.getPost_details());
        return v;
    }
}