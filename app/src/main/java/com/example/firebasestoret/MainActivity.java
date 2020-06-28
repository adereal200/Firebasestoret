package com.example.firebasestoret;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ViewPager viewPager;
private  SliderView sliderView;
    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<SliderItem> sliderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sliderView = findViewById(R.id.imageSlider);


        progressDialog = new ProgressDialog(this);

        sliderItems = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference("Image");

        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    SliderItem upload = postSnapshot.getValue(SliderItem.class);
                    sliderItems.add(upload);
                }

                SliderAdapterExample adapter = new SliderAdapterExample(getApplicationContext(),sliderItems);

                sliderView.setSliderAdapter(adapter);
              sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
               sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
               sliderView.setIndicatorSelectedColor(Color.WHITE);
               sliderView.setIndicatorUnselectedColor(Color.GRAY);
               sliderView.setScrollTimeInSec(5); //set scroll delay in seconds :
               sliderView.startAutoCycle();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();            }
        });

    }}
