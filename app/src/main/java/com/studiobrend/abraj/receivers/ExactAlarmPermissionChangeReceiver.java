package com.studiobrend.abraj.receivers;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.studiobrend.abraj.PrayerTimesManager;

import timber.log.Timber;

public class ExactAlarmPermissionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Timber.i("=============== %s", action);

        if (null != action) {
            if (action.equals(AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED))
            {
                PrayerTimesManager.updatePrayerTimes(context, true);
            }
        }
    }
}
