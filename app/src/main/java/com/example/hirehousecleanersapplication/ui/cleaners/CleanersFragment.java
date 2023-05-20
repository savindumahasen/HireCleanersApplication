package com.example.hirehousecleanersapplication.ui.cleaners;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.MainActivity;
import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.ui.Customer.Customer;
import com.example.hirehousecleanersapplication.ui.Customer.ViewCustomerFragment;

import java.io.ByteArrayOutputStream;

public class CleanersFragment extends Fragment {
    SQLiteDatabase db;
    TextView textTitle,textCleanerID;
    EditText editCleanersName,editCleanersPhone,editCleanersEmail;
    Button  btnCleanersAdd,btnUpdateCleaners,btnViewCleaners,btnView;

    private CleanersViewModel mViewModel;

    public static CleanersFragment newInstance() {
        return new CleanersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setDB();
        View view=inflater.inflate(R.layout.fragment_cleaners, container, false);
        textTitle=view.findViewById(R.id.textTitle);
        editCleanersName=view.findViewById(R.id.editCleanersName);
        editCleanersEmail=view.findViewById(R.id.editCleanersEmail);
        editCleanersPhone=view.findViewById(R.id.editCleanersPhone);
        btnCleanersAdd=view.findViewById(R.id.btnCleanersAdd);
        btnUpdateCleaners=view.findViewById(R.id.btnUpdateCleaners);
        btnViewCleaners=view.findViewById(R.id.btnViewCleaners);
        btnView=view.findViewById(R.id.btnView);
        textCleanerID=view.findViewById(R.id.textCleanerID);
        if (((MainActivity) getActivity()).cleaner != null) {
            Cleaner e = ((MainActivity) getActivity()).cleaner;
            editCleanersName.setText(e.getName());
            editCleanersPhone.setText(e.getPhone());
            editCleanersEmail.setText(e.getEmail());
            textCleanerID.setText(String.valueOf(e.getID()));
        }





      btnCleanersAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkvalidate()) {
                    try {
                        Cleaner cleaner = new Cleaner();
                        cleaner.setName(editCleanersName.getText().toString());
                        cleaner.setEmail(editCleanersEmail.getText().toString());
                        cleaner.setPhone(editCleanersPhone.getText().toString());
                        cleaner.saveDB(db);
                        Toast.makeText(view.getContext(), "Cleaners  data added Successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }
        });
        btnUpdateCleaners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cleaner cleaner = new Cleaner();
                    cleaner.setName(editCleanersName.getText().toString());
                    cleaner.setEmail(editCleanersEmail.getText().toString());
                    cleaner.setPhone(editCleanersPhone.getText().toString());
                    cleaner.setID(Integer.valueOf(textCleanerID.getText().toString()));
                    cleaner.update(db);
                    Toast.makeText(view.getContext(), "Cleaner data updated", Toast.LENGTH_LONG).show();


                } catch (SQLException ex) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error in Updating" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }


            }

        });
      btnViewCleaners.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ViewCleanersFragment fragment=new ViewCleanersFragment();
              getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
          }
      });
      btnView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ViewCustomerFragment fragment=new ViewCustomerFragment();
              getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,fragment).commit();
          }
      });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CleanersViewModel.class);
        // TODO: Use the ViewModel
    }
    public void setDB(){
        try{
            db=getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if not exists Cleaner(Id integer primary key Autoincrement,Name text,Phone text,Email text)");

        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "Error  in DB", Toast.LENGTH_SHORT).show();

        }

    }
    public boolean checkvalidate(){
        if(editCleanersName.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the Cleaners Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editCleanersPhone.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please enter the Cleaners Phone", Toast.LENGTH_LONG);
            return false;
        }
        if(editCleanersEmail.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the  Cleaners Email", Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }

}