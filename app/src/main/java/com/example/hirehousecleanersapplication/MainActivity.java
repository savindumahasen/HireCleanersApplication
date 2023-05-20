package com.example.hirehousecleanersapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.ui.Customer.Customer;
import com.example.hirehousecleanersapplication.ui.Customer.CustomerFragment;
import com.example.hirehousecleanersapplication.ui.Customer.FeedbackFragment;
import com.example.hirehousecleanersapplication.ui.Login.FrontFragment;
import com.example.hirehousecleanersapplication.ui.Login.LoginFragment;
import com.example.hirehousecleanersapplication.ui.Login.RegisterFragment;
import com.example.hirehousecleanersapplication.ui.backload.BlankFragment;
import com.example.hirehousecleanersapplication.ui.cleaners.Cleaner;
import com.example.hirehousecleanersapplication.ui.cleaners.CleanersFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hirehousecleanersapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    public Customer customer;
    public Cleaner cleaner;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    boolean Register=false;
    boolean Status=false;
    NavigationView navigationView;

    //public MainActivity(NavigationView navigationView) {
        //this.navigationView = navigationView;
    //]}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_customer,R.id.nav_cleaners)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);
        getSupportFragmentManager().popBackStack();
        FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
        Sharedpreferences preference=new Sharedpreferences();
        Status=preference.GetBoolean(getApplicationContext(),Sharedpreferences.KEY_STATUS);
        String name=preference.GetString(getApplicationContext(),Sharedpreferences.KEY_USER_NAME);
        if(name!=null){
            Register=true;
        }
        if(Register) {

            if(Status) {
                BlankFragment fragment = new BlankFragment();
                trans.add(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
                String UT = preference.GetString(getApplicationContext(), Sharedpreferences.KEY_UserType);
               try {
                    if (UT.equalsIgnoreCase("Customer")) {
                        setenabled(true);
                        //customer
                        Menu menu = navigationView.getMenu();
                       MenuItem item = menu.findItem(R.id.nav_customer);
                       item.setVisible(true);
                       item = menu.findItem(R.id.nav_cleaners);
                       item.setVisible(false);
                    } else {
                       //cleaner
                    Menu menu = navigationView.getMenu();
                    MenuItem item = menu.findItem(R.id.nav_cleaners);
                    item.setVisible(true);
                    item = menu.findItem(R.id.nav_customer);
                    item.setVisible(false);
                    }
                }catch(NullPointerException e){
                    e.getMessage();
                }

            }else {
                    LoginFragment fragment = new LoginFragment();
                    trans.add(R.id.nav_host_fragment_content_main, fragment);
                    trans.addToBackStack(null);
                    trans.commit();
                    setenabled(true);



            }
        }else{
            RegisterFragment fragment = new RegisterFragment();
            trans.add(R.id.nav_host_fragment_content_main, fragment);
            trans.addToBackStack(null);
            trans.commit();
            setenabled(true);





        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuID=item.getItemId();
                FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
                if(menuID==R.id.nav_home){

                    BlankFragment fragment=new BlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);
                    trans.commit();
                }else if(menuID==R.id.nav_customer){
                    CustomerFragment fragment=new CustomerFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);
                    trans.commit();
                }else  if(menuID==R.id.nav_cleaners){
                    CleanersFragment fragment=new CleanersFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);

                    trans.addToBackStack(null);
                    trans.commit();
                }
                else if(menuID==R.id.nav_Exit){
                       finish();
                }
                else if(menuID==R.id.nav_LogOut){
                    FrontFragment fragment=new FrontFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);
                    trans.commit();
                    preference.SaveBoolean(getApplicationContext(),false,Sharedpreferences.KEY_STATUS);
                    setenabled(false);

                }else if(menuID==R.id.nav_Rate){
                    FeedbackFragment fragment=new FeedbackFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);
                    trans.commit();

                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }
    public void setenabled(boolean enable){
        Menu menu=navigationView.getMenu();
        MenuItem item=menu.findItem(R.id.nav_cleaners);
        item.setVisible(enable);
        item=menu.findItem(R.id.nav_customer);
        item.setVisible(enable);
        item=menu.findItem(R.id.nav_home);
        item.setVisible(enable);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}