package com.studiobrend.abraj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;

import com.studiobrend.abraj.R;

public class SplashScreenActivity extends Activity {

    private static final long SPLASH_DURATION_MS = 5000; // Trajanje splash ekrana: 5 sekundi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ProgressBar progressBar = findViewById(R.id.splash_progress);

        // Animacija progres bara od 0 do 100% tokom 5 sekundi
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(SPLASH_DURATION_MS);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        // Handler za prelazak na MainActivity nakon 5 sekundi
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DURATION_MS);
    }
}
