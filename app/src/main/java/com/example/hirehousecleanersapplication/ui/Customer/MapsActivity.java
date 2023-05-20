package com.example.hirehousecleanersapplication.ui.Customer;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hirehousecleanersapplication.R;
import com.example.hirehousecleanersapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

   // private GoogleMap mMap;

   // private ActivityMapsBinding binding;
   // private LatLng customerLocation;
   //FloatingActionButton fabOk;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        fabOk=findViewById(R.id.fabOk);
//        fabOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.putExtra("Location",customerLocation);
//                setResult(Activity.RESULT_OK,intent);
//                finish();
//            }
//        });
//
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap=googleMap;
//        LatLng Kandy=new LatLng(7.334,80.6301);
//        mMap.addMarker(new MarkerOptions().position(Kandy).title("You are in Kandy"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(Kandy));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kandy,18.0f));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//        customerLocation=null;
//        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(@NonNull LatLng latLng) {
//                customerLocation = latLng;
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Customer Location").
//                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
//
//            }
//        });
//
//    }
//
//}
//nilukshi
private GoogleMap mMap;

    private ActivityMapsBinding binding;
    private LatLng customerLocation;
    FloatingActionButton fabOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fabOk = findViewById(R.id.fabOk);
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.putExtra("Location",customerLocation);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng kandy = new LatLng(7.334, 80.6301);
        mMap.addMarker(new MarkerOptions().position(kandy).title("You are in in kandy"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kandy));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kandy,18.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true );
        customerLocation=null;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                customerLocation = latLng;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("customer Location").
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18.0f));
            }
        });
    }
}