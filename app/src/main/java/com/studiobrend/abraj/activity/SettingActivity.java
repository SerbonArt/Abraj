package com.studiobrend.abraj.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.studiobrend.abraj.R;

public class SettingActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity); // Postavi layout za aktivnost
        Toolbar toolbar = findViewById(R.id.toolbar); // Pronađi Toolbar u trenutnom layout-u
        setSupportActionBar(toolbar); // Postavi Toolbar kao ActionBar
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        loadSettings();
        // Postavi naslov i dugme za povratak ako je potrebno
    }

    private void loadSettings() {
        // U ovom primeru, pretpostavljam da je 'liveWallpaper' jedina opcija
        // Možete dodati logiku za učitavanje postavki ako je potrebno
        sharedPreferences.edit().putString("displayOption", "liveWallpaper").apply();
        navigateToMain();
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
