package com.example.register.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.register.R;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Find the ImageView by ID
        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Load the animation from the anim folder
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Apply the animation to the ImageView
        logoImageView.startAnimation(fadeInAnimation);

        // Delay the transition to the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent intent = new Intent(Splash_Screen.this, Login.class);
                startActivity(intent);
                // Finish the splash activity so the user can't go back to it
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}