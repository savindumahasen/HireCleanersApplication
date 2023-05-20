package com.example.hirehousecleanersapplication.ui.Customer;

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

import java.util.List;

public class ViewCustomerFragment extends Fragment {
    SQLiteDatabase db;

    private ViewCustomerViewModel mViewModel;

    public static ViewCustomerFragment newInstance() {
        return new ViewCustomerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.view_customer_fragment, container, false);

        setDB();
        RecyclerView recyclerView=view.findViewById(R.id.rcvCustomers);
        Customer customer=new Customer();
        List<Customer>customerList=customer.GetOpenCustomer(db);
        CustomerAdapter adapter=new CustomerAdapter(db, customerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewCustomerViewModel.class);
        // TODO: Use the ViewModel
    }
    private void setDB(){
        try{
            db=getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if not exists CustomerServices1(id integer primary key Autoincrement,Name text,Phone text,Email text,Rooms integer,Bathrooms integer,Date text,Time text," +
                    "Location text,FloorType text,Area integer ,Price text,Image blog,Status text,FeedBack text)");
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(),"Error in db "+e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity().getApplicationContext(),"Please check the db "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}