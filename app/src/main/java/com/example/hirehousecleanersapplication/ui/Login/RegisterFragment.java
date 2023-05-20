package com.example.hirehousecleanersapplication.ui.Login;

import androidx.fragment.app.FragmentTransaction;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.Sharedpreferences;

public class RegisterFragment extends Fragment {
    TextView textRegister;
    EditText editUserName,editUserEmail,editUserPassword,editUserType;
    Button btnRegister;
    SQLiteDatabase db;


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    private RegisterViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.register_fragment, container, false);
        textRegister=view.findViewById(R.id.textRegister);
        editUserName=view.findViewById(R.id.editUserName);
        editUserEmail=view.findViewById(R.id.editUserEmail);
        editUserPassword=view.findViewById(R.id.editUserPassword);
        editUserType=view.findViewById(R.id.editUserType);
        btnRegister=view.findViewById(R.id.btnRegister);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidate()) {
                    setDB();
                    User user = new User();
                    user.setUserName(editUserName.getText().toString());
                    user.setEmail(editUserEmail.getText().toString());
                    user.setPassword(editUserPassword.getText().toString());
                    user.setUserType(editUserType.getText().toString());
                    
                    user.Save(db);
                    Toast.makeText(getActivity().getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                    Sharedpreferences preference = new Sharedpreferences();
                    preference.SaveString(view.getContext(), user.getUserName(), Sharedpreferences.KEY_USER_NAME);
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    Toast.makeText(getActivity().getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                    LoginFragment fragment = new LoginFragment();
                    trans.add(R.id.nav_host_fragment_content_main, fragment);
                    trans.addToBackStack(null);
                    trans.commit();
                }

            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }
    private void setDB(){
        try{
            db=getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if not exists User(Id integer primary key Autoincrement,Name text,Email text unique,Password text unique,UserType text)");
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(),"Error in db "+e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity().getApplicationContext(),"Please check the db "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public boolean checkValidate(){
        if(editUserName.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the user Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editUserEmail.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editUserPassword.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editUserType.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Please enter the user type", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}