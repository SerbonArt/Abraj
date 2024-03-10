package com.studiobrend.abraj.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.studiobrend.abraj.PrayerTimesManager;
import com.studiobrend.abraj.activity.PrayerTimesActivity;
import com.studiobrend.abraj.helpers.UserSettings;
import com.studiobrend.abraj.helpers.WakeLocker;
import com.studiobrend.abraj.services.AthanService;

import timber.log.Timber;

// Can't use WakefulBroadcastReceiver as it relies on PARTIAL_WAKE_LOCK
// which doesn't stop Athan on power button press
// It's deprecated anyway
public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        int prayer = intent.getIntExtra(AthanService.EXTRA_PRAYER, 2);
        Timber.i("=============== Athan alarm is ON: %d", prayer);

        if (UserSettings.isNotificationEnabled(context)) {
            WakeLocker.acquire(context);
            Intent athanIntent = new Intent(context, AthanService.class);
            athanIntent.setAction(AthanService.ACTION_NOTIFY_ATHAN);
            athanIntent.putExtra(AthanService.EXTRA_PRAYER, prayer);
            athanIntent.putExtra(AthanService.EXTRA_MUEZZIN, UserSettings.getMuezzin(context));
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(athanIntent);
                }
            else {
                context.startService(athanIntent);
            }
        }
        else {
            Timber.e("Alarm received when set off by user!");
        }

        // Broadcast to MainActivity so it updates its screen if on
        Intent updateIntent = new Intent(PrayerTimesActivity.UPDATE_VIEWS);
        context.sendBroadcast(updateIntent);

        // Re-arm alarm.
        PrayerTimesManager.updatePrayerTimes(context, false);
    }
}
