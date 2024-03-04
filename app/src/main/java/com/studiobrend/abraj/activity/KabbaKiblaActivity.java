package com.studiobrend.abraj.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.location.Geocoder;
import android.location.Address;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.studiobrend.abraj.R;


public class KabbaKiblaActivity extends AppCompatActivity implements SensorEventListener {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final double KABA_LATITUDE = 21.422487;
    private static final double KABA_LONGITUDE = 39.826206;

    private ImageView kabbaKiblaDark, kabbaKiblaLight;
    private TextView locationName, distance;

    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private LocationManager locationManager;
    private final float[] rotationMatrix = new float[9];
    private final float[] orientationAngles = new float[3];

    private double currentLatitude;
    private double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kabba_kibla_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kabbaKiblaDark = findViewById(R.id.kabba_kibla_dark);
        kabbaKiblaLight = findViewById(R.id.kabba_kibla_light);
        locationName = findViewById(R.id.location_name);
        distance = findViewById(R.id.distance);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        requestLocation();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Qibla"); // Postavlja se naslov za Toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Omogućava se dugme za povratak
        }
    }

    private void requestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                updateLocationName(currentLatitude, currentLongitude);
                updateDistanceToKaaba(currentLatitude, currentLongitude);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == rotationSensor) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
            SensorManager.getOrientation(rotationMatrix, orientationAngles);

            float azimuthInRadians = orientationAngles[0];
            float azimuthInDegrees = (float) Math.toDegrees(azimuthInRadians);
            float qiblaDirection = calculateQiblaDirection(currentLatitude, currentLongitude);

            rotateCompass(azimuthInDegrees, qiblaDirection);
            updateDistanceToKaaba(currentLatitude, currentLongitude);
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


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Implementacija logike za rukovanje promenom tačnosti senzora...
    }

    private float calculateQiblaDirection(double currentLatitude, double currentLongitude) {
        double deltaLongitude = KABA_LONGITUDE - currentLongitude;
        double y = Math.sin(Math.toRadians(deltaLongitude)) * Math.cos(Math.toRadians(KABA_LATITUDE));
        double x = Math.cos(Math.toRadians(currentLatitude)) * Math.sin(Math.toRadians(KABA_LATITUDE))
                - Math.sin(Math.toRadians(currentLatitude)) * Math.cos(Math.toRadians(KABA_LATITUDE)) * Math.cos(Math.toRadians(deltaLongitude));
        double qiblaBearing = Math.toDegrees(Math.atan2(y, x));
        qiblaBearing = (qiblaBearing + 360) % 360; // Normalizacija na 0-360 stepeni
        return (float) qiblaBearing;
    }

    private void rotateCompass(float azimuthInDegrees, float qiblaDirection) {
        float rotationAngle = azimuthInDegrees - qiblaDirection;

        kabbaKiblaDark.setRotation(rotationAngle);

        if (Math.abs(rotationAngle) <= 3) {
            kabbaKiblaLight.setVisibility(ImageView.VISIBLE);
            kabbaKiblaDark.setVisibility(ImageView.GONE);
        } else {
            kabbaKiblaLight.setVisibility(ImageView.GONE);
            kabbaKiblaDark.setVisibility(ImageView.VISIBLE);
        }
    }
    private void updateDistanceToKaaba(double currentLatitude, double currentLongitude) {
        Location currentLocation = new Location("");
        currentLocation.setLatitude(currentLatitude);
        currentLocation.setLongitude(currentLongitude);

        Location kaabaLocation = new Location("");
        kaabaLocation.setLatitude(KABA_LATITUDE);
        kaabaLocation.setLongitude(KABA_LONGITUDE);

        float distanceToKaaba = currentLocation.distanceTo(kaabaLocation) / 1000; // Udaljenost u kilometrima
        distance.setText(getString(R.string.distance_to_kaaba, distanceToKaaba));
    }


    private void updateLocationName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String locationName = address.getLocality(); // Ili neki drugi detalj koji želite da prikažete
                this.locationName.setText(locationName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


