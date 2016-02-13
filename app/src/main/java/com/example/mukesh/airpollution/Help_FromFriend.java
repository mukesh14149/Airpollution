package com.example.mukesh.airpollution;

/**
 * Created by Tarun on 13/2/16.
 */

import android.content.Intent;
import android.os.Bundle;
/*import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;*/
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Help_FromFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_from_friend);

    }

    public void HelpNeeded(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Help_FromFriend_Needed.class));

    }

    public void HelpOffered(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Help_FromFriend_Offered.class));

    }
}
