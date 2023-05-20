package com.example.hirehousecleanersapplication.ui.Login;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hirehousecleanersapplication.R;

public class FrontFragment extends Fragment {
    Button btnSignIn, btnSignUp;

    private SigninViewModel mViewModel;


    public static FrontFragment newInstance() {
        return new FrontFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.front_fragment, container, false);
        btnSignIn=view.findViewById(R.id.btnSignIn);
        btnSignUp=view.findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
                LoginFragment fragment=new LoginFragment();
                trans.add(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }


        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans=getActivity().getSupportFragmentManager().beginTransaction();
                RegisterFragment fragment=new RegisterFragment();
                trans.add(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SigninViewModel.class);
        // TODO: Use the ViewModel
    }

}