package com.example.register.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.register.R;
import com.example.register.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class register extends AppCompatActivity {
    Button sub ;
    EditText name ,email, pass , conpass;
    TextView sign_in;
    String uname ,email1, word , con;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        sub = (Button) findViewById(R.id.signup);
        name = (EditText)findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        conpass = (EditText) findViewById(R.id.conpass);
        sign_in = (TextView) findViewById(R.id.alreadyacc);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this , Login.class));
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = name.getText().toString().trim();
                email1 = email.getText().toString().trim();
                word = pass.getText().toString().trim();
                con = conpass.getText().toString().trim();
                if(uname.isEmpty() || email1.isEmpty() || word.isEmpty() || con.isEmpty() )
                {
                    Toast.makeText(register.this , "Field is empty!!!" , Toast.LENGTH_SHORT).show();
                }
                else if(word.length() < 6)
                {
                    Toast.makeText(register.this , "Password must be of length six!!!!" , Toast.LENGTH_SHORT).show();
                }
                else if(word.equals(con)){
                    createAccount(email1 , word);
                }
                else{
                    Toast.makeText(register.this , "Password and Confirm Password is not matching!!!!" , Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    conpass.setText("");
                }
            }
        });
    }

    private void createAccount(String email1, String word) {
        auth.createUserWithEmailAndPassword(email1 , word).addOnCompleteListener(register.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this , "Account created!!!" , Toast.LENGTH_SHORT).show();
                            saveUserData();
                            startActivity(new Intent(register.this , home.class));
                        }
                        else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Account creation failed";
                            Toast.makeText(register.this, "Account creation failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            pass.setText("");
                            conpass.setText("");
                        }
                    }
                });
    }

    private void saveUserData() {

        UserModel um = new UserModel(uname ,email1, word);

        myref.child("users").child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).setValue(um)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Data stored successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(register.this, "Data store failed!", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            pass.setText("");
                            conpass.setText("");
                        }
                    }
                });
    }
}