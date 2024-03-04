package com.studiobrend.abraj.activity;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.studiobrend.abraj.R;


public class PrayerTimesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prayer_times_activity); // Postavlja se layout za aktivnost
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Prayers"); // Postavlja se naslov za Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Omogućava se dugme za povratak
        }
    }
    // Metoda za otvaranje Fajr Set Activity
    public void openFajrSetActivity(View view) {
        Intent intent = new Intent(this, FajrSetActivity.class);
        startActivity(intent);
    }

    // Metoda za otvaranje Dhuhr Set Activity
    public void openDhuhrSetActivity(View view) {
        Intent intent = new Intent(this, DhuhrSetActivity.class);
        startActivity(intent);
    }

    // Metoda za otvaranje Asr Set Activity
    public void openAsrSetActivity(View view) {
        Intent intent = new Intent(this, AsrSetActivity.class);
        startActivity(intent);
    }

    // Metoda za otvaranje Maghrib Set Activity
    public void openMaghribSetActivity(View view) {
        Intent intent = new Intent(this, MaghribSetActivity.class);
        startActivity(intent);
    }

    // Metoda za otvaranje Isha Set Activity
    public void openIshaSetActivity(View view) {
        Intent intent = new Intent(this, IshaSetActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Zatvara aktivnost i vraća se na prethodnu
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}