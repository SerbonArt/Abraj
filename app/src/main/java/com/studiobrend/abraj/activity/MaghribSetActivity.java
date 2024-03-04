package com.studiobrend.abraj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.studiobrend.abraj.R;


public class MaghribSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maghrib_set_activity);

        TextView setTextView = findViewById(R.id.maghrib_set_text_view);
        setTextView.setText("Promenjeni set");


        // Pronađite Toolbar definisan u layout-u
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Postavite toolbar da deluje kao ActionBar
        setSupportActionBar(toolbar);
        // Omogućite dugme za povratak na ActionBar-u
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Maghrib Settings"); // Postavlja se naslov za Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Postavite OnClickListener na toolbar da reaguje na klik na dugme za povratak
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Završite trenutnu aktivnost i vratite se na prethodnu
                onBackPressed();
            }
        });
    }
}
