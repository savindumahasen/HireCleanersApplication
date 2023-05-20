package com.example.hirehousecleanersapplication;

import android.content.Context;

import java.lang.reflect.Type;

public class Sharedpreferences {
    public static final String APP_NAME="Hire_House_Cleaners_App";
    public static  final String KEY_USER_NAME="USER_NAME";
    public static final String  KEY_STATUS="STATUS";
    public static final String  KEY_UserType="USERTYPE";


    public Sharedpreferences(){
        super();
    }
    public void SaveString(Context context, String value, String Key){
        android.content.SharedPreferences preferences=context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor= preferences.edit();
        editor.putString(Key,value);
        editor.commit();
    }
    public void SaveBoolean(Context context,boolean value,String key){
        android.content.SharedPreferences preferences= context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public String GetString(Context context,String key){
        android.content.SharedPreferences preference=context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preference.getString(key,null);
    }
    public boolean GetBoolean(Context context,String key){
        android.content.SharedPreferences preference=context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preference.getBoolean(key,false);
    }


}
