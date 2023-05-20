package com.example.hirehousecleanersapplication.ui.Login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {
    private String UserName;
    private String Password;
    private String Email;
    private String UserType;

    public User(){

    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getUserType() {
        return UserType;
    }
    public void Save(SQLiteDatabase db){
        try{
            ContentValues values=new ContentValues();
            values.put("Name",UserName);
            values.put("Password",Password);
            values.put("Email",Email);
            values.put("UserType",UserType);
            db.insert("User",null,values);
        }catch(Exception ex){
            throw ex;
        }

    }
    public boolean checkLogin(SQLiteDatabase db){
        try{
            String query = "select UserType,Password from User where UserType=? and Password=?";
            Cursor cursor = db.rawQuery(query, new String[]{UserType, Password});
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(0).equals(UserType) && (cursor.getString(1)).equals(Password)) {
                         return true;

                    }
                } while (cursor.moveToNext());
            }
            return false;
        }catch(Exception ex){
            throw ex;
        }
    }
    public String getType(SQLiteDatabase db){
        try {

            String query = "select UserType,Password from User where Password='"+Password+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(1).equals(Password) ) {
                        String Type= cursor.getString(0);
                        return Type;
                    }
                }

                while (cursor.moveToNext()) ;
            }
            return  null;
        }
        catch(Exception ex){
            throw  ex;
        }
    }

}
