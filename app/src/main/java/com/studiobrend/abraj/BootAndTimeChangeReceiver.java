package com.studiobrend.abraj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootAndTimeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Proverite koji je događaj izazvao prijem
        switch (intent.getAction()) {
            case Intent.ACTION_BOOT_COMPLETED:
                // Logika za rukovanje ponovnim pokretanjem uređaja
                // Na primer, ponovo podesite alarme za vremena molitvi
                break;
            case Intent.ACTION_TIME_CHANGED:
            case Intent.ACTION_TIMEZONE_CHANGED:
                // Logika za rukovanje promenom vremena ili vremenske zone
                // Na primer, ponovo izračunajte vremena molitvi
                break;
        }
    }
}

