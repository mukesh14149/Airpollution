package com.example.mukesh.airpollution;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

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

        int backdropWidth = CustomUtil.getScreenWidth(getActivity());
        int backdropHeight = getResources().getDimensionPixelSize(R.dimen.details_backdrop_height);

        ImageView view_Backdrop = (ImageView) rootView.findViewById(R.id.post_back);

        Picasso.with(getActivity()).load(posts.getPost_photo()).resize(backdropWidth, backdropHeight).centerCrop().into(view_Backdrop);

        ((TextView) rootView.findViewById(R.id.heading))
                .setText(posts.getPost_heading());

        ((TextView) rootView.findViewById(R.id.content))
                .setText(posts.getPost_photo());

        return rootView;

    }
}
