package com.example.mukesh.airpollution;

/**
 * Created by tarun on 15/4/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class post implements Parcelable {

    private String post_id;
    private String post_person_id;
    private String post_owner_name;
    private String post_heading;
    private String post_area;
    private String post_loaction;
    private String post_time;
    private String post_details;
    private String post_photo;

    public post(String post_id, String post_person_id, String post_owner_name, String post_heading, String post_area, String post_loaction, String post_time, String post_details, String post_photo) {
        this.post_id = post_id;
        this.post_person_id = post_person_id;
        this.post_owner_name = post_owner_name;
        this.post_heading = post_heading;
        this.post_area = post_area;
        this.post_loaction = post_loaction;
        this.post_time = post_time;
        this.post_details = post_details;
        this.post_photo = post_photo;
    }

    public post(){
        super();
    }

    protected post(Parcel in) {
        post_id = in.readString();
        post_person_id = in.readString();
        post_owner_name = in.readString();
        post_heading = in.readString();
        post_area = in.readString();
        post_loaction = in.readString();
        post_time = in.readString();
        post_details = in.readString();
        post_photo = in.readString();
    }

        public static final Creator<post> CREATOR = new Creator<post>() {
            @Override
            public post createFromParcel(Parcel in) {
                return new post(in);
            }

            @Override
            public post[] newArray(int size) {
                return new post[size];
            }
        };




    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_person_id() {
        return post_person_id;
    }

    public void setPost_person_id(String post_person_id) {
        this.post_person_id = post_person_id;
    }

    public String getPost_owner_name() {
        return post_owner_name;
    }

    public void setPost_owner_name(String post_owner_name) {
        this.post_owner_name = post_owner_name;
    }

    public String getPost_heading() {
        return post_heading;
    }

    public void setPost_heading(String post_heading) {
        this.post_heading = post_heading;
    }

    public String getPost_area() {
        return post_area;
    }

    public void setPost_area(String post_area) {
        this.post_area = post_area;
    }

    public String getPost_loaction() {
        return post_loaction;
    }

    public void setPost_loaction(String post_loaction) {
        this.post_loaction = post_loaction;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getPost_details() {
        return post_details;
    }

    public void setPost_details(String post_details) {
        this.post_details = post_details;
    }

    public String getPost_photo() {
        return post_photo;
    }

    public void setPost_photo(String post_photo) {
        this.post_photo = post_photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(post_id);
        dest.writeString(post_person_id);
        dest.writeString(post_owner_name);
        dest.writeString(post_heading);
        dest.writeString(post_area);
        dest.writeString(post_loaction);
        dest.writeString(post_time);
        dest.writeString(post_details);
        dest.writeString(post_photo);


    }
}