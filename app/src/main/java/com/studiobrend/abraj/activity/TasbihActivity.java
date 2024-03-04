package com.studiobrend.abraj.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.studiobrend.abraj.R;

public class TasbihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasbih_activity); // Postavlja se layout za aktivnost
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Tasbih"); // Postavlja se naslov za Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Omogućava se dugme za povratak
        }
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
