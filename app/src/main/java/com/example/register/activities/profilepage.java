package com.example.register.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.register.R;
import com.example.register.model.UserModel;

public class profilepage extends AppCompatActivity {

    TextView name , email ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profilepage);
        name = (TextView) findViewById(R.id.nametext);
        email = (TextView) findViewById(R.id.emailtext);
        String[] user = UserModel.getUser();
        name.setText(user[0]);
        email.setText(user[1]);
        Button logout;
        logout = (Button) findViewById(R.id.Signout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profilepage.this,register.class));
            }
        });
    };
}