package com.studiobrend.abraj.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;

import androidx.appcompat.app.AlertDialog;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import com.studiobrend.abraj.AnalogClockWallpaperService;
import com.studiobrend.abraj.ApiResponse;
import com.studiobrend.abraj.AsmaAlHusnaApi;
import com.studiobrend.abraj.R;
import com.studiobrend.abraj.model.Name;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        if (!areSettingsSet()) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.aladhan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AsmaAlHusnaApi api = retrofit.create(AsmaAlHusnaApi.class);

        api.getNames().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<Name> names = apiResponse.getData();
                } else {
                    // Obrada slučaja kada je odgovor neuspešan
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Obrada greške
            }
        });

        updateDisplayBasedOnSettings();
        setupImageClick(R.id.image_devedesetdevet, R.id.text_devedesetdevet, DevedesetDevetActivity.class);
        setupImageClick(R.id.image_quran, R.id.text_quran, QuranActivity.class);
        setupImageClick(R.id.image_kabba_kibla, R.id.text_kabba_kibla, KabbaKiblaActivity.class);
        setupImageClick(R.id.image_duas, R.id.text_duas, DuasActivity.class);
        setupImageClick(R.id.image_tasbih, R.id.text_tasbih, TasbihActivity.class);
        setupImageClick(R.id.image_islamic_calendar, R.id.text_islamic_calendar, IslamicCalendarActivity.class);
        setupImageClick(R.id.image_mosques, R.id.text_mosques, MosquesActivity.class);
        setupImageClick(R.id.image_donations, R.id.text_donations, DonationsActivity.class);
        setupImageClick(R.id.image_clock, R.id.text_clock, PrayerTimesActivity.class);
        // Dodajte ostale pozive setupImageClick za svaku sliku
    }

    private boolean areSettingsSet() {
        return sharedPreferences.contains("displayOption");
    }

    private void updateDisplayBasedOnSettings() {
        String displayOption = sharedPreferences.getString("displayOption", "liveWallpaper");
        if ("liveWallpaper".equals(displayOption)) {
            promptForLiveWallpaperSetup();
        }
    }

    private void promptForLiveWallpaperSetup() {
        new AlertDialog.Builder(this)
                .setTitle("Set Live Wallpaper")
                .setMessage("Do you want to set the analog clock as your live wallpaper?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                            new ComponentName(this, AnalogClockWallpaperService.class));
                    startActivity(intent);
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void setupImageClick(int imageViewId, int textViewId, Class<?> activityClass) {
        ImageView imageView = findViewById(imageViewId);
        TextView textView = findViewById(textViewId);
        View.OnClickListener listener = v -> {
            imageView.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction(() -> {
                imageView.animate().scaleX(1f).scaleY(1f).setDuration(200);
                Intent intent = new Intent(MainActivity.this, activityClass);
                startActivity(intent);
            });
            textView.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction(() -> textView.animate().scaleX(1f).scaleY(1f).setDuration(200));
        };
        imageView.setOnClickListener(listener);
        textView.setOnClickListener(listener); // Omogućava klik na tekst takođe
    }

    // Metode za otvaranje aktivnosti
    public void openFajrSetActivity(View view) {
        Intent intent = new Intent(MainActivity.this, FajrSetActivity.class);
        startActivity(intent);
    }

    public void openDhuhrSetActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DhuhrSetActivity.class);
        startActivity(intent);
    }

    public void openAsrSetActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AsrSetActivity.class);
        startActivity(intent);
    }

    public void openMaghribSetActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MaghribSetActivity.class);
        startActivity(intent);
    }

    public void openIshaSetActivity(View view) {
        Intent intent = new Intent(MainActivity.this, IshaSetActivity.class);
        startActivity(intent);
    }

    // Dodajte ostale potrebne metode ovde...
}
