package com.example.hirehousecleanersapplication.ui.Customer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hirehousecleanersapplication.MainActivity;
import com.example.hirehousecleanersapplication.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomerFragment extends androidx.fragment.app.Fragment {

    private CustomerViewModel mViewModel;
    TextView editTitle, textPrice, textID;
    EditText editName, editPhone, editEmail, editRooms, editBathrooms, editDate, editTime, editLocation, editFloorType, editFloorArea;
    ImageView imagehome;
    Button btnCalculate, btnAdd, btnUpdate, btnView;
    Calendar calendar = Calendar.getInstance();
    Bitmap pic;
    SQLiteDatabase db;

    public static CustomerFragment newInstance() {
        return new CustomerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        setDB();
        textID = view.findViewById(R.id.textID);
        editTitle = view.findViewById(R.id.editTitle);
        editName = view.findViewById(R.id.editName);
        editPhone = view.findViewById(R.id.editPhone);
        editEmail = view.findViewById(R.id.editEmail);
        editRooms = view.findViewById(R.id.editRooms);
        editBathrooms = view.findViewById(R.id.editBathrooms);
        editDate = view.findViewById(R.id.editDate);
        editTime = view.findViewById(R.id.editTime);
        editLocation = view.findViewById(R.id.editLocation);
        editFloorType = view.findViewById(R.id.editFloorType);
        editFloorArea = view.findViewById(R.id.editFloorArea);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        textPrice = view.findViewById(R.id.textPrice);

        imagehome = view.findViewById(R.id.imagehome);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnView = view.findViewById(R.id.btnUpdateCleaners);
        if (((MainActivity) getActivity()).customer != null) {
            Customer e = ((MainActivity) getActivity()).customer;
            editName.setText(e.getName());
            editPhone.setText(e.getPhone());
            editEmail.setText(e.getEmail());
            editRooms.setText(String.valueOf(e.getRooms()));
            editBathrooms.setText(String.valueOf(e.getBathrooms()));
            editDate.setText(e.getDate());
            editTime.setText(e.getTime());
            editLocation.setText(e.getLocation());
            editFloorType.setText(e.getFloorType());
            editFloorArea.setText(String.valueOf(e.getArea()));
            textPrice.setText(e.getPrice());
            byte[] imgArray = e.getImage();
                pic = BitmapFactory.decodeByteArray(imgArray, 0, imgArray.length);
                imagehome.setImageBitmap(pic);

            textID.setText(String.valueOf(e.getID()));
        }

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String Date = "dd/mm/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(Date, Locale.JAPAN);
                editDate.setText(format.format(calendar.getTime()));
            }
        };

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minutes) {
                        editTime.setText(hour + ":" + minutes);
                    }
                }, hour, minutes, true);
                dialog.setTitle("Select the time");
                dialog.show();
            }


        });
//        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == Activity.RESULT_OK)
//                {
//                    Intent intent = result.getData();
//                    LatLng latLng = intent.getParcelableExtra("Location");
//                    editLocation.setText(String.valueOf(latLng));
//
//                }
//            }
//        });
        ActivityResultLauncher<Intent>launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK)
                {
                    Intent intent = result.getData();
                    LatLng LatLng= intent.getParcelableExtra("Location");
                    editLocation.setText(String.valueOf(LatLng));
                }

            }


        });
        editLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),MapsActivity.class);
                launcher.launch(intent);
            }
        });


//        editLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),MapsActivity.class);
//                launcher.launch(intent);
//
//            }
//        });

        ActivityResultLauncher camLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                pic = (Bitmap) intent.getExtras().get("data");
                imagehome.setImageBitmap(pic);


            }
        });
        ActivityResultLauncher gallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    Uri selectedImage = intent.getData();
                    imagehome.setImageURI(selectedImage);
                    pic = ((BitmapDrawable)imagehome.getDrawable()).getBitmap();

                }

            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkvalide()){
                String editFloorTypes = editFloorType.getText().toString();
                int floorArea = Integer.valueOf(editFloorArea.getText().toString());
                if (editFloorTypes != null) {
                    if (editFloorTypes.equalsIgnoreCase("Tyles")) {
                        int price = floorArea * 100;
                        textPrice.setText("floor type is Tyle  " + "price is " + String.valueOf(price));  // if floor type is tyle 1m*1m=100
                    } else if (editFloorTypes.equalsIgnoreCase("Wood")) {
                        int price = floorArea * 200;
                        textPrice.setText("floor type is wood " + String.valueOf(price));  // if floor type is tyle 1m*1m=200
                    } else if (editFloorTypes.equalsIgnoreCase("Cement")) {
                        int price = floorArea * 300;         // if floor type is tyle 1m*1m=300
                        textPrice.setText("floor type is cement " + String.valueOf(price));

                    }

                    }
                }
            }

        });
        imagehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(imagehome.getContext());
                builder.setMessage("Please select the option,that you wan to use").setTitle("Select the image selection").setPositiveButton("use camera",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                camLauncher.launch(intent);


                            }
                        }).setNegativeButton("use Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        gallery.launch(intent);

                    }
                });
                AlertDialog dialog = builder.create();
                builder.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkvalidation()) {
                    try {
                        Customer customer = new Customer();

                        customer.setName(editName.getText().toString());
                        customer.setPhone(editPhone.getText().toString());
                        customer.setEmail(editEmail.getText().toString());
                        customer.setRooms(Integer.valueOf(editRooms.getText().toString()));
                        customer.setBathrooms(Integer.valueOf(editBathrooms.getText().toString()));
                        customer.setDate(editDate.getText().toString());
                        customer.setTime(editTime.getText().toString());
                        customer.setLocation(editLocation.getText().toString());
                        customer.setFloorType(editFloorType.getText().toString());
                        customer.setArea(Integer.valueOf(editFloorArea.getText().toString()));
                        customer.setPrice(textPrice.getText().toString());
                        customer.setStatus("Open");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        try {
                            pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                        } catch (NullPointerException e) {
                            e.getMessage();
                        }
                        byte[] byteArray = stream.toByteArray();
                        customer.setImage(byteArray);
                        customer.save(db);
                        Toast.makeText(view.getContext(), "Customer data added Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(view.getContext(), "Post is created Successfully", Toast.LENGTH_SHORT).show();


                    } catch (SQLException ex) {
                        Toast.makeText(getActivity().getApplicationContext(), "Error in Adding" + ex.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity().getApplicationContext(), "Please check the data" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Customer customer = new Customer();
                    customer.setName(editName.getText().toString());
                    customer.setPhone(editPhone.getText().toString());
                    customer.setEmail(editEmail.getText().toString());
                    customer.setDate(editDate.getText().toString());
                    customer.setTime(editTime.getText().toString());
                    customer.setRooms(Integer.valueOf(editRooms.getText().toString()));
                    customer.setBathrooms(Integer.valueOf(editBathrooms.getText().toString()));
                    customer.setID(Integer.valueOf(textID.getText().toString()));
                    customer.setLocation(editLocation.getText().toString());
                    customer.setFloorType(editFloorType.getText().toString());
                    customer.setArea(Integer.valueOf(editFloorArea.getText().toString()));
                    customer.setPrice(textPrice.getText().toString());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    try {
                        pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                        byte[] byteArray = stream.toByteArray();
                        customer.setImage(byteArray);
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    customer.update(db);
                    Toast.makeText(view.getContext(), "Customer data updated", Toast.LENGTH_LONG).show();


                } catch (SQLException ex) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error in Updating" + ex.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCustomerFragment fragment = new ViewCustomerFragment();

                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setDB() {
        try {
            db = getActivity().openOrCreateDatabase("CustomerDB", Context.MODE_PRIVATE, null);
            db.execSQL("Create table if not exists CustomerServices1(Id integer primary key Autoincrement,Name text,Phone text,Email text,Rooms integer,Bathrooms integer,Date text,Time text," +
                    "Location text,FloorType text,Area integer,Price text,Image blog,Status text)");
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "Error in db " + e.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity().getApplicationContext(), "Please check the db " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public boolean checkvalide() {
        if(editFloorType.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "please Enter the floor type", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editFloorArea.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "please Enter the floor Are", Toast.LENGTH_LONG).show();
            return false;
        }
        try{
            int floorArea=Integer.valueOf(editFloorArea.getText().toString());
        }catch(NumberFormatException e){
            e.getMessage();
            return false;
        }
        return true;
    }
    public boolean checkvalidation(){
        if (editName.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please Enter the Customer Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editPhone.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please Enter the customer phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editEmail.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please Enter the customer Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editRooms.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please Enter the Number of Customer rooms", Toast.LENGTH_SHORT).show();
        }
        try{
            int  numberOfRooms=Integer.valueOf(editRooms.getText().toString());

        }catch (NumberFormatException e){
            e.getMessage();
        }
        if(editBathrooms.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "please Enter the Customer Bathrooms", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editDate.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please select the data", Toast.LENGTH_SHORT).show();
             return false;
        }
        if(editTime.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please select the time", Toast.LENGTH_SHORT).show();
             return false;
        }
        if(editLocation.getText().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(),"Please click the location", Toast.LENGTH_LONG).show();
            return false;
        }
        if(imagehome.getContext().toString().equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Please select the image", Toast.LENGTH_SHORT).show();
        }
        return true;

    }
}