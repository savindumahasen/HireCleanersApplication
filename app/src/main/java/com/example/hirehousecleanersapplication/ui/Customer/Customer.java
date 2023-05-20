package com.example.hirehousecleanersapplication.ui.Customer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class Customer {
    private int ID;
    private String Name;
    private String Phone;
    private String Email;
    private int Rooms;
    private int Bathrooms;
    private String Date;
    private String Time;
    private String Location;
    private String FloorType;
    private int Area;
    private String  Price;
    private byte[] Image;
    private String Status;
    private  String FeedBack;


    public Customer() {

    }


    public void setID(int ID) {

        this.ID = ID;
    }

    public int getID() {

        return ID;
    }

    public void setName(String name) {

        this.Name = name;
    }

    public String getName() {

        return Name;
    }

    public void setPhone(String phone) {

        this.Phone = phone;
    }

    public String getPhone() {

        return Phone;
    }

    public void setEmail(String email) {

        this.Email = email;
    }

    public String getEmail() {

        return Email;
    }

    public void setRooms(int rooms) {

        this.Rooms = rooms;
    }

    public int getRooms() {

        return Rooms;
    }

    public void setBathrooms(int bathrooms) {

        this.Bathrooms = bathrooms;
    }

    public int getBathrooms() {

        return Bathrooms;
    }

    public void setDate(String date) {

        this.Date = date;
    }
    public String getDate() {
        return Date;
    }

    public void setTime(String time) {

        Time = time;
    }

    public String getTime() {
        return Time;
    }

    public void setLocation(String location) {

        this.Location = location;
    }

    public String getLocation() {

        return Location;
    }

    public void setFloorType(String floorType) {

        this.FloorType = floorType;
    }

    public String getFloorType() {

        return FloorType;
    }

    public void setArea(int area) {
        this.Area = area;
    }

    public int getArea() {

        return Area;
    }

    public void setPrice(String price) {

        this.Price = price;
    }

    public String  getPrice() {

        return Price;
    }

    public void setImage(byte[] image) {

        this.Image = image;
    }

    public byte[] getImage() {

        return Image;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setFeedBack(String feedBack) {
        FeedBack = feedBack;
    }

    public String getFeedBack() {
        return FeedBack;
    }

    public void save(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Phone", Phone);
            values.put("Email", Email);
            values.put("Rooms", Rooms);
            values.put("Bathrooms", Bathrooms);
            values.put("Date", Date);
            values.put("Time", Time);
            values.put("Location", Location);
            values.put("FloorType", FloorType);
            values.put("Area", Area);
            values.put("Price",Price);
            values.put("Image", Image);
            values.put("Status",Status);
            db.insert("CustomerServices1", null, values);


        } catch (Exception ex) {
            throw ex;
        }
    }


    public List<Customer> GetOpenCustomer(SQLiteDatabase db) {
        try {
            List<Customer> customerList = new ArrayList<>();
            String query = "select ID,Name,Phone,Email,Rooms,Bathrooms,Date,Time,Location,FloorType,Area,Image,Price from CustomerServices1 where Status='Open'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Customer customer = new Customer();
                    customer.setID(cursor.getInt(0));
                    customer.setName(cursor.getString(1));
                    customer.setPhone(cursor.getString(2));
                    customer.setEmail(cursor.getString(3));
                    customer.setRooms(cursor.getInt(4));
                    customer.setBathrooms(cursor.getInt(5));
                    customer.setDate(cursor.getString(6));
                    customer.setTime(cursor.getString(7));
                    customer.setLocation(cursor.getString(8));
                    customer.setFloorType(cursor.getString(9));
                    customer.setArea(cursor.getInt(10));
                    customer.setImage(cursor.getBlob(11));
                    customer.setPrice(cursor.getString(12));


                    customerList.add(customer);
                } while (cursor.moveToNext());
            }

            return customerList;

        } catch (Exception ex) {
            throw ex;
        }



    }


    public void setStatusDB(SQLiteDatabase db,int Id) {
        try{
            ContentValues values = new ContentValues();
            values.put("Status","Assigned");
            db.update("CustomerServices1",values,"ID="+Id,null);

        }catch(Exception ex){
            throw  ex;
        }

    }
    public void delete(SQLiteDatabase db) {
        try{
            db.delete("CustomerServices1","ID="+ID ,null);
        }catch(Exception ex){
            throw  ex;
        }

    }
    public void update(SQLiteDatabase db){
        try{

            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Phone", Phone);
            values.put("Email", Email);
            values.put("Rooms", Rooms);
            values.put("Bathrooms", Bathrooms);
            values.put("Date", Date);
            values.put("Time", Time);
            values.put("Location", Location);
            values.put("FloorType", FloorType);
            values.put("Area", Area);
            values.put("Price",Price);
            values.put("Image", Image);
            db.update("CustomerServices1",values,"Id="+ID,null);


        }catch(Exception e){
            throw e;
        }
    }
}


