package com.example.mukesh.airpollution;

/**
 * Created by Tarun  on 12/2/16.
 */

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }


    public void openMainActivity(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void openActivity1(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Monitor_DelhiPollution.class));

    }

    public void openActivity2(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Monitor_IIITDPollution.class));

    }

    public void openActivity3(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Help_FromFriend.class));

    }

    public void openActivity4(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Complaint_Authority.class));

    }


}