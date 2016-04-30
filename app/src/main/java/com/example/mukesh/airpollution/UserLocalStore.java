package com.example.mukesh.airpollution;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by mukesh on 17/1/16.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDataBase;

    public UserLocalStore(Context context)
    {
        userLocalDataBase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putString("post_person_id",user.id);
        spEditor.putString("person_name", user.username);
        spEditor.putString("person_location", user.location);

        spEditor.commit();

    }

    public User getLoggedInUser()
    {
        String post_person_id = userLocalDataBase.getString("post_person_id", "");

        String person_name = userLocalDataBase.getString("person_name", "");
        String person_location = userLocalDataBase.getString("person_location","");

        User storedUser = new User(post_person_id, person_name, person_location);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn()
    {
        if(userLocalDataBase.getBoolean("loggedIn", false)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
