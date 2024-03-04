package com.studiobrend.abraj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

import java.util.Calendar;
import java.util.TimeZone;

public class AnalogClockWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new AnalogClockEngine();
    }

    private class AnalogClockEngine extends Engine {

        private final Handler handler = new Handler();
        private boolean visible = true;
        private final Runnable drawRunner = this::draw;

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawRunner);
            } else {
                handler.removeCallbacks(drawRunner);
            }
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            handler.post(drawRunner);
        }

        private void draw() {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;

            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    drawAnalogClock(canvas, getResources());
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            handler.removeCallbacks(drawRunner);
            if (visible) {
                handler.postDelayed(drawRunner, 1000);
            }
        }

        private void drawAnalogClock(Canvas canvas, Resources resources) {
            boolean isDaytime = isDaytime();
            int backgroundResId = isDaytime ? R.drawable.background_day : R.drawable.background_night;
            int clockFaceResId = isDaytime ? R.drawable.clock_face_day : R.drawable.clock_face_night;
            int hourHandResId = isDaytime ? R.drawable.hand_hour_day : R.drawable.hand_hour_night;
            int minuteHandResId = isDaytime ? R.drawable.hand_minute_day : R.drawable.hand_minute_night;

            Bitmap backgroundBitmap = BitmapFactory.decodeResource(resources, backgroundResId);
            Bitmap clockFaceBitmap = BitmapFactory.decodeResource(resources, clockFaceResId);
            Bitmap hourHandBitmap = BitmapFactory.decodeResource(resources, hourHandResId);
            Bitmap minuteHandBitmap = BitmapFactory.decodeResource(resources, minuteHandResId);

            DisplayMetrics metrics = resources.getDisplayMetrics();
            int screenWidth = metrics.widthPixels;
            int screenHeight = metrics.heightPixels;

            // Skaliranje lica sata i kazaljki
            Bitmap scaledBackground = Bitmap.createScaledBitmap(backgroundBitmap, screenWidth, screenHeight, true);
            Bitmap scaledClockFace = Bitmap.createScaledBitmap(clockFaceBitmap, screenWidth, (1920 * screenWidth) / 1080, true);
            Bitmap scaledHourHand = Bitmap.createScaledBitmap(hourHandBitmap, (132 * screenWidth) / 1080, screenHeight, true);
            Bitmap scaledMinuteHand = Bitmap.createScaledBitmap(minuteHandBitmap, (132 * screenWidth) / 1080, screenHeight, true);

            canvas.drawBitmap(scaledBackground, 0, 0, null);

            // Centriranje lica sata
            int clockFaceCenterX = (screenWidth - scaledClockFace.getWidth()) / 2;
            int clockFaceCenterY = (screenHeight - scaledClockFace.getHeight()) / 2;
            canvas.drawBitmap(scaledClockFace, clockFaceCenterX, clockFaceCenterY, null);

            // Postavljanje i rotacija kazaljki
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int hour = calendar.get(Calendar.HOUR_OF_DAY) % 12;
            int minute = calendar.get(Calendar.MINUTE);

            float hourRotation = 360f * ((hour % 12) + minute / 60f) / 12f;
            float minuteRotation = 360f * minute / 60f;

            int centerX = screenWidth / 2;
            int centerY = screenHeight / 2;

            drawRotatedBitmap(canvas, scaledHourHand, hourRotation, centerX, centerY);
            drawRotatedBitmap(canvas, scaledMinuteHand, minuteRotation, centerX, centerY);
        }

        private void drawRotatedBitmap(Canvas canvas, Bitmap bitmap, float rotation, int centerX, int centerY) {
            int bitmapCenterX = bitmap.getWidth() / 2;
            int bitmapCenterY = bitmap.getHeight() / 2;

            canvas.save();
            canvas.rotate(rotation, centerX, centerY);
            canvas.drawBitmap(bitmap, centerX - bitmapCenterX, centerY - bitmapCenterY, null);
            canvas.restore();
        }

        private boolean isDaytime() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            return hour >= 5 && hour < 21; // Smatra se da je dan od 05:00 do 20:59
        }
    }
}
