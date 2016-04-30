package com.example.mukesh.airpollution;

/**
 * Created by tarun on 13/2/16.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Complaint_Authority extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_authority);
        Button button=(Button)findViewById(R.id.Submit);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(this,email_authority.class);
        startActivity(intent);

    }

}
