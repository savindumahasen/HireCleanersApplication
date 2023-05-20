package com.example.hirehousecleanersapplication.ui.cleaners;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hirehousecleanersapplication.MainActivity;
import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.ui.Customer.CustomerFragment;

import java.util.List;

public class CleanersAdapter  extends RecyclerView.Adapter<CleanersAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<Cleaner> cleanerList;

    public CleanersAdapter(List<Cleaner> cleaners, SQLiteDatabase _db){
        cleanerList=cleaners;
        db=_db;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View cleanerItems=inflater.inflate(R.layout.cleaner_item,parent,false);
        ViewHolder holder=new ViewHolder(cleanerItems);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cleaner cleaner=cleanerList.get(position);
        holder.textCleanerName.setText(cleaner.getName());
        holder.textCleanerPhone.setText(cleaner.getPhone());
        holder.textCleanerEmail.setText(cleaner.getEmail());
        holder.btnCleanerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.btnCleanerDelete.getContext());
                builder.setMessage("Are you sure, you want to delete?");
                builder.setTitle("Confirm Delete");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cleaner.delete(db);
                        cleanerList.remove(position);
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


            }});holder.btnCleanerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext()).cleaner=cleaner;
                CleanersFragment fragment=new CleanersFragment();
                FragmentTransaction ft=((MainActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return cleanerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCleanerName,textCleanerEmail,textCleanerPhone;
        Button  btnCleanerEdit, btnCleanerDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCleanerName=itemView.findViewById(R.id.textCleanerName);
            textCleanerEmail=itemView.findViewById(R.id.textCleanerEmail);
            textCleanerPhone=itemView.findViewById(R.id.textCleanerPhone);
            btnCleanerEdit=itemView.findViewById(R.id.btnCleanerEdit);
            btnCleanerDelete=itemView.findViewById(R.id.btnCleanerDelete);
        }
    }
}
