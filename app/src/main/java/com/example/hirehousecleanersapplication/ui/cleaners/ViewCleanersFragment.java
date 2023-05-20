package com.example.hirehousecleanersapplication.ui.cleaners;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.ui.Customer.Customer;
import com.example.hirehousecleanersapplication.ui.Customer.CustomerAdapter;
import com.example.hirehousecleanersapplication.ui.Login.User;

import java.util.ArrayList;
import java.util.List;

public class ViewCleanersFragment extends Fragment {
    SQLiteDatabase db;


    private ViewCleanersViewModel mViewModel;

    public static ViewCleanersFragment newInstance() {
        return new ViewCleanersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_cleaners_fragment, container, false);setDB();
        RecyclerView recyclerView=v.findViewById(R.id.rcvCleaners);
        Cleaner cleaner=new Cleaner();
        List<Cleaner>cleanerList=cleaner.Getcleaners(db);
        CleanersAdapter adapter=new CleanersAdapter(cleanerList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);



        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewCleanersViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setDB() {
        try {
            db = getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE, null);
            db.execSQL("Create table if not exists Cleaner(Id integer primary key Autoincrement,Name text,Phone text,Email text)");

        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "Error  in DB", Toast.LENGTH_SHORT).show();

        }
    }
}
