package com.example.mukesh.airpollution;

/**
 * Created by mukesh on 17/1/16.
 */
public class User {
    String location, username, password;
    int age;
    String id;
    public User(String location, int  age, String username, String password)
    {
        this.location = location;
        this.age = age;
        this.username = username;
        this.password = password;
    }
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.location ="";
    }
    public User(String id, String username, String location)
    {
        this.id = id;
        this.username = username;

        this.location =location;
    }
}
