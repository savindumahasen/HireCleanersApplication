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

import com.example.hirehousecleanersapplication.MainActivity;
import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.Sharedpreferences;
import com.example.hirehousecleanersapplication.ui.backload.BlankFragment;

public class LoginFragment extends Fragment {
    TextView textWelcome,textLogEmail,textUserType;
    EditText editLogEmail,editLogPassword,editLogType;
    Button btnLogin;
    SQLiteDatabase db;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        textWelcome = view.findViewById(R.id.textWelcome);
        textLogEmail = view.findViewById(R.id.textLogEmail);
        textUserType = view.findViewById(R.id.textUserType);
        editLogEmail = view.findViewById(R.id.editLogEmail);
        editLogPassword = view.findViewById(R.id.editLogPassword);
        editLogType = view.findViewById(R.id.editLoginType);
        btnLogin=view.findViewById(R.id.btnLogin);

        Sharedpreferences preference = new Sharedpreferences();
        String name = preference.GetString(view.getContext(), Sharedpreferences.KEY_USER_NAME);
        if (name != null) ;
        {
            textWelcome.setText("Welcome " + name);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidate()) {
                    setDB();
                    User user = new User();
                    user.setUserName(editLogEmail.getText().toString());
                    user.setUserType(editLogType.getText().toString());
                    user.setEmail(editLogEmail.getText().toString());
                    user.setPassword(editLogPassword.getText().toString());
                    if (user.checkLogin(db)) {
                        String Type = user.getType(db);
                        Toast.makeText(getActivity().getApplicationContext(), Type, Toast.LENGTH_SHORT).show();


                        preference.SaveString(view.getContext(), Type, Sharedpreferences.KEY_UserType);

                        preference.SaveBoolean(view.getContext(), true, Sharedpreferences.KEY_STATUS);
                        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                        BlankFragment fragment = new BlankFragment();
                        trans.replace(R.id.nav_host_fragment_content_main, fragment);
                        trans.addToBackStack(null);
                        trans.commit();
                        ((MainActivity) view.getContext()).setenabled(true);
                        Toast.makeText(getActivity().getApplicationContext(), "Login is successfully", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Login is not  successfully", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }
    private void setDB(){
        try{
            db=getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create table if not exists User(Id integer primary key Autoincrement,Email text unique,Password text unique,UserType text)");
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(),"Error in db "+e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity().getApplicationContext(),"Please check the db "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public boolean checkValidate(){
        if(editLogEmail.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter the email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editLogPassword.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please enter the password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editLogType.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "please enter the log type", Toast.LENGTH_SHORT).show();
           return false;
        }
        return true;
    }


}