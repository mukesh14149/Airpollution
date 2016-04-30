package com.example.mukesh.airpollution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Submit_application extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

                    Intent intent=new Intent(this,MainMenu.class);
                    startActivity(intent);

        }

}
