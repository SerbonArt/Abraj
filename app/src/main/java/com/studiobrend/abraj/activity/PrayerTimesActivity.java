package com.studiobrend.abraj.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import com.studiobrend.abraj.PrayerTimesApp;
import com.studiobrend.abraj.PrayerTimesManager;
import com.studiobrend.abraj.R;
import com.studiobrend.abraj.helpers.PrayerTimes;
import com.studiobrend.abraj.helpers.UserSettings;
import com.studiobrend.abraj.services.AthanService;
import org.arabeyes.prayertime.*;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import timber.log.Timber;

public class PrayerTimesActivity extends AppCompatActivity {

    private static final int BLINK_DURATION = 500;
    private static final int BLINK_COUNT = AthanService.ATHAN_DURATION / BLINK_DURATION;
    public static final String UPDATE_VIEWS = "com.studiobrend.abraj.UPDATE";


    private Boolean mUVReceiverRegistered = false;
    private BroadcastReceiver mUpdateViewsReceiver = null;

    private int mImportant = -1;

    private TextView mCityTextView;
    private TextView mDateTextView;
    private TextView mToNextTextView;
    private TextView[][] mPrayersTextViews;

    private static final int COUNT_INTERVAL_SECOND = 1000;
    private static final int COUNT_INTERVAL_MINUTE = 60 * 1000;
    private Handler mCountHandler;
    private Runnable mUpdateCount;

    ActivityResultLauncher<Intent> mSearchCityActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prayer_times_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Molitve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PreferenceManager.setDefaultValues(this, R.xml.general_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.location_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.notifications_preferences, false);

        mSearchCityActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        PrayerTimesManager.handleSettingsChange(this, -1, -1, -1);
                    }
                });

        // TODO: Implement additional logic from PrayerToTimesActivity as needed.
    }

    // Implementacija metoda za otvaranje Set Activity za svaku od molitvi
    public void openFajrSetActivity(View view) {
        Intent intent = new Intent(this, FajrSetActivity.class);
        startActivity(intent);
    }

    public void openDhuhrSetActivity(View view) {
        Intent intent = new Intent(this, DhuhrSetActivity.class);
        startActivity(intent);
    }

    public void openAsrSetActivity(View view) {
        Intent intent = new Intent(this, AsrSetActivity.class);
        startActivity(intent);
    }

    public void openMaghribSetActivity(View view) {
        Intent intent = new Intent(this, MaghribSetActivity.class);
        startActivity(intent);
    }

    public void openIshaSetActivity(View view) {
        Intent intent = new Intent(this, IshaSetActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // TODO: Dodajte specifične metode i logiku iz PrayerToTimesActivity koji se tiču ažuriranja prikaza vremena molitvi, obrade događaja i slično.

    // Registrovanje BroadcastReceiver-a za ažuriranje UI komponenti na osnovu sistema obaveštenja
    private void registerUpdateViewsReceiver() {
        if (!mUVReceiverRegistered) {
            mUpdateViewsReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (UPDATE_VIEWS.equals(intent.getAction())) {
                        // Ovde dodajte logiku za ažuriranje prikaza vremena molitvi
                        // Na primer, osvežite TextView za prikaz vremena za svaku molitvu
                    }
                }
            };
            IntentFilter filter = new IntentFilter(UPDATE_VIEWS);
            registerReceiver(mUpdateViewsReceiver, filter);
            mUVReceiverRegistered = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerUpdateViewsReceiver();
        // Ovde možete dodati dodatne metode koje su potrebne za ažuriranje UI-a ili logiku aplikacije
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mUVReceiverRegistered) {
            unregisterReceiver(mUpdateViewsReceiver);
            mUVReceiverRegistered = false;
        }
    }

    // Metoda za ažuriranje TextView-a sa vremenima molitvi
    private void updatePrayerTimesDisplay() {
        // Pretpostavimo da imate objekat koji sadrži vremena molitvi
        // Primer: PrayerTimes prayerTimes = getPrayerTimesForToday();
        // Zatim ažurirajte TextView za svaku molitvu
        // Na primer: mFajrTimeTextView.setText(prayerTimes.getFajr());
        // Ponovite za svaku molitvu...
    }

    // Metoda za inicijalizaciju i postavljanje vremena za sledeću molitvu
    private void setupNextPrayerCountdown() {
        // Ovde dodajte logiku za postavljanje odbrojavanja do sledeće molitve
    }

    // Dodajte dodatne metode potrebne za vašu aplikaciju...

}
