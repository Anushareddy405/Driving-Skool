package com.example.anusha.project_driving.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anusha on 04-04-2017.
 */

public class SharedPrefer {
    protected static final String PREFERENCE_NAME = "first_application_in_android";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEdit;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public SharedPrefer(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        sharedEdit = sharedPreferences.edit();
    }

    public void setUsername(String value){
        sharedEdit.putString(USERNAME,value);
        sharedEdit.commit();
    }

    public String getUsername(){

        return sharedPreferences.getString(USERNAME,"");
    }

    public void setPassword(String value){
        sharedEdit.putString(PASSWORD,value);
        sharedEdit.commit();
    }

    public String getPassword(){

        return sharedPreferences.getString(PASSWORD,"");
    }

}
