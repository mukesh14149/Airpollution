package com.example.mukesh.airpollution;

/**
 * Created by tarun on 12/2/16.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Monitor_DelhiPollution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.monitor_delhi_pollution);
        Intent intent =new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

}