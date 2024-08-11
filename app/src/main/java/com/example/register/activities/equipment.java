package com.example.register.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.register.R;

public class equipment extends AppCompatActivity {

    ImageView iv , iv1 , iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        iv = (ImageView) findViewById(R.id.imageView8);
        iv1 = (ImageView) findViewById(R.id.imageView10);
        iv2 = (ImageView) findViewById(R.id.imageView12);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(equipment.this , Tractor.class));
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(equipment.this , Harvestor.class));
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(equipment.this , Rotavator.class));
            }
        });

    }

}
