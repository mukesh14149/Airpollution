package com.example.mukesh.airpollution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;

    EditText etLocation, etAge, etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etLocation = (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bRegister:
                String location = etLocation.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                int age = 11;

                User user = new User(location, age, username, password);

                registerUser(user);
                break;
        }
    }
    private  void registerUser(User user){
        System.out.println("hello");
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }
}