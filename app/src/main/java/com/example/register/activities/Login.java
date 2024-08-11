package com.example.register.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.register.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private EditText email , pass;
    private String uemail , upass;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    private Button login;
    private TextView dont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        login = (Button) findViewById(R.id.loginbutton);
        email = (EditText) findViewById(R.id.emailtext);
        pass = (EditText) findViewById(R.id.passwordtext);
        dont = (TextView) findViewById(R.id.donttext);
        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this , register.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail = email.getText().toString().trim();
                upass = pass.getText().toString().trim();
                if(uemail.isEmpty() || upass.isEmpty()){
                    Toast.makeText(Login.this , "Field is empty!" , Toast.LENGTH_SHORT).show();
                }
                else{
                    signInAccount(uemail , upass);
                }
            }
        });
    }



    private void signInAccount(String uemail , String upass) {
        auth.signInWithEmailAndPassword(uemail , upass).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(Login.this , "Login Successful!" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this , home.class));
            }
            else{
                Toast.makeText(Login.this , "Login failed!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}