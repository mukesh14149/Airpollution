package com.example.mukesh.airpollution;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class postDetailActivityFragment extends Fragment {

    public postDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_post_detail, container, false);
        Bundle b = getActivity().getIntent().getExtras();
        post posts = b.getParcelable("POST");

        ((TextView) rootView.findViewById(R.id.heading))
                .setText(posts.getPost_heading());

        ((TextView) rootView.findViewById(R.id.content))
                .setText(posts.getPost_details());

        return rootView;

    }
}
