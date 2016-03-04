package com.example.mukesh.airpollution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Polygon extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polygon);
        TextView text1 = (TextView) findViewById(R.id.Name1);
        Button bBack = (Button) findViewById(R.id.bBack1);
        bBack.setOnClickListener(this);

        try {
            BufferedReader in = new BufferedReader
                    (new FileReader(".\\src\\Text Files\\text.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                text1.setText(str);
                System.out.println(str);
            }
            System.out.println(str);
        }
        catch (IOException e) {
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bBack1:

                Intent intent =new Intent(this,MapsActivity.class);
                startActivity(intent);
                break;

        }
    }

}
