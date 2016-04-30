package com.example.mukesh.airpollution;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bLogin:

                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                User user = new User(username, password);
                authenticate(user);
                //startActivity(new Intent(this, Register.class));
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                System.out.println("main aa gya");
                if(returnedUser==null){
                    showErrorMessage();
                    System.out.println("main aa");

                }else{
                    System.out.println("main");
                    logUserIn(returnedUser);

                }
            }
        });
    }
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Wrong user");
        dialogBuilder.setPositiveButton("ok", null);
        dialogBuilder.show();
    }
    private  void logUserIn(User returnedUser){
        System.out.println("maya");
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        System.out.println("aaaa");
        startActivity(new Intent(this, Help_FromFriend_Needed.class));

    }
}
