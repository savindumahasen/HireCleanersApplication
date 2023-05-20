package com.example.hirehousecleanersapplication.ui.Customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hirehousecleanersapplication.MainActivity;
import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.ui.cleaners.Cleaner;
import com.example.hirehousecleanersapplication.ui.cleaners.CleanersFragment;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<Customer>customerList;

    public CustomerAdapter(SQLiteDatabase _db,List<Customer>customers){
        db=_db;
        customerList=customers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View customerItems=inflater.inflate(R.layout.customer_item,parent,false);
        ViewHolder holder=new ViewHolder(customerItems);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer=customerList.get(position);
        holder.txvName.setText("Name:  "+customer.getName());
        holder.txvPhone.setText("Phone: "+customer.getPhone());
        holder.txvEmail.setText("Email: "+customer.getEmail());
        holder.txvRooms.setText("Number of rooms: "+String.valueOf(customer.getRooms()));
        holder.txvBathrooms.setText("Number of Bathrooms: "+String.valueOf(customer.getBathrooms()));
        holder.txvDate.setText("Date: "+customer.getDate());
        holder.txvTime.setText("Time: "+customer.getTime());
        holder.txvLocation.setText("Location: "+customer.getLocation());
        holder.txvFloorType.setText("FloorType: "+customer.getFloorType());
        holder.txvArea.setText("Area: "+String.valueOf(customer.getArea()));
        holder.txvprice.setText("Price: "+String.valueOf(customer.getPrice()));
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(customer.getImage(), 0, customer.getImage().length);
            holder.imgView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.getMessage();
        }


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.btnDelete.getContext());
                builder.setMessage("Are you sure, you want to delete?");
                builder.setTitle("Confirm Delete");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        customer.delete(db);
                        customerList.remove(position);
                        notifyItemRemoved(position);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // if no data
                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext()).customer=customer;
                CustomerFragment fragment=new CustomerFragment();
                FragmentTransaction ft=((MainActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }



        });holder.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer cust=new Customer();
                cust.setStatusDB(db,customer.getID());
                Toast.makeText((MainActivity)view.getContext(),"Job is Accepted",Toast.LENGTH_LONG).show();




                   




                }
            });




    }

    @Override
    public int getItemCount() {

        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvName,txvPhone,txvEmail,txvRooms,txvBathrooms,txvDate,txvTime,txvLocation,txvFloorType,txvArea,txvprice;
        ImageView imgView;
        ImageButton btnEdit,btnGet,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvName= itemView.findViewById(R.id.txvName);
            txvPhone= itemView.findViewById(R.id.txvPhone);
            txvEmail= itemView.findViewById(R.id.txvEmail);
            txvRooms= itemView.findViewById(R.id.txvRooms);
            txvBathrooms= itemView.findViewById(R.id.txvBathrooms);
            txvDate= itemView.findViewById(R.id.txvDate);
            txvTime= itemView.findViewById(R.id.txvTime);
            txvLocation= itemView.findViewById(R.id.txvLocation);
            txvFloorType= itemView.findViewById(R.id.txvFloorType);
            txvArea= itemView.findViewById(R.id.txvArea);
            txvprice=itemView.findViewById(R.id.txvprice);
            imgView=itemView.findViewById(R.id.imgView);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnGet=itemView.findViewById(R.id.btnGet);
            btnDelete=itemView.findViewById(R.id.btnDelete);


        }
    }
}
