package com.example.hirehousecleanersapplication.ui.Customer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String FeedBack;

    public Review() {

    }

    public String getFeedBack() {

        return FeedBack;
    }

    public void setFeedBack(String feedBack) {

        FeedBack = feedBack;
    }

    public void save(SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();
            values.put("FeedBack", FeedBack);
            db.insert("FeedBack", null, values);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<Review> getReviews(SQLiteDatabase db) {
        try {
            List<Review> reviewList = new ArrayList<>();
            String query = "select FeedBack from FeedBack";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Review review = new Review();
                    review.setFeedBack(cursor.getString(0));
                    reviewList.add(review);
                } while (cursor.moveToNext());

            }

            return reviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }
}


