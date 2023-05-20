package com.example.hirehousecleanersapplication.ui.Customer;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.R;

public class FeedbackFragment extends Fragment {
    TextView textFeedBack;
    ImageView imgReview;
    RatingBar  ratingBar;



    private FeedbackViewModel mViewModel;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_feedback, container, false);
        textFeedBack=view.findViewById(R.id.textFeedBack);
        ratingBar=view.findViewById(R.id.ratingBar);
        imgReview=view.findViewById(R.id.imgReview);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                if(rating==0){
                    textFeedBack.setText("Dissatisfied");
                }else if(rating==1){
                    textFeedBack.setText("Dissatisfied");
                }else if (rating==2||rating==3){
                    textFeedBack.setText("OK");
                }else if(rating==4){
                    textFeedBack.setText("Satisfied");
                }else if(rating==5){
                    textFeedBack.setText("Very Satisfied");
                }else{

                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
        // TODO: Use the ViewModel
    }


}