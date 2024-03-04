package com.studiobrend.abraj;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QiblaFinder {
    private Context context;
    private Retrofit retrofit;

    public QiblaFinder(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.aladhan.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void findQiblaDirection(final QiblaCallback callback) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            QiblaService service = retrofit.create(QiblaService.class);
            Call<QiblaResponse> call = service.getQiblaDirection(latitude, longitude);
            call.enqueue(new Callback<QiblaResponse>() {
                @Override
                public void onResponse(Call<QiblaResponse> call, Response<QiblaResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        QiblaResponse qiblaResponse = response.body();
                        callback.onDirectionFound(qiblaResponse.data.direction, qiblaResponse.data.distance);
                    }
                }

                @Override
                public void onFailure(Call<QiblaResponse> call, Throwable t) {
                    // Handle failure
                }
            });
        }
    }

    public interface QiblaService {
        @GET("qibla/find")
        Call<QiblaResponse> getQiblaDirection(@Query("latitude") double latitude, @Query("longitude") double longitude);
    }

    public interface QiblaCallback {
        void onDirectionFound(float qiblaDirection, float distanceToKaaba);
        void onLocationName(String location);
    }

    class QiblaResponse {
        Data data;
    }

    class Data {
        float direction;
        float distance;
    }
}
