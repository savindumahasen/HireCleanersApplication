package com.example.hirehousecleanersapplication.ui.cleaners;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hirehousecleanersapplication.ui.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    private String Name;
    private String Phone;
    private String Email;
    private int ID;


    public Cleaner() {

    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getName() {
        return Name;

    }

    public void setPhone(String phone) {

        Phone = phone;
    }

    public String getPhone()
    {

        return Phone;
    }

    public void setEmail(String email) {

        Email = email;
    }

    public String getEmail() {

        return Email;
    }


    public void saveDB(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Phone", Phone);
            values.put("Email", Email);
            db.insert("Cleaner", null, values);

        } catch (Exception e) {
            throw e;
        }

    }

    public void delete(SQLiteDatabase db) {
        try {
            db.delete("Cleaner", "ID=" + ID, null);
        } catch (Exception ex) {
            throw ex;
        }

    }

    public List<Cleaner> Getcleaners(SQLiteDatabase db) {
        try {
            List<Cleaner> cleanerList = new ArrayList<>();
            String query = "select Id,Name,Phone,Email from Cleaner";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Cleaner cleaner = new Cleaner();
                    cleaner.setID(cursor.getInt(0));
                    cleaner.setName(cursor.getString(1));
                    cleaner.setPhone(cursor.getString(2));
                    cleaner.setEmail(cursor.getString(3));
                    cleanerList.add(cleaner);
                } while (cursor.moveToNext());

            }

            return cleanerList;
        } catch (Exception ex) {
            throw ex;
        }


    }
    public void update(SQLiteDatabase db){
        try {
            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Phone", Phone);
            values.put("Email", Email);
            db.update("Cleaner",values,"Id="+ID,null);

        } catch (Exception e) {
            throw e;
        }

    }
}




