package com.studiobrend.abraj;

import org.arabeyes.prayertime.Method;
import org.arabeyes.prayertime.Prayer;
import org.arabeyes.prayertime.PrayerTime;
import org.arabeyes.prayertime.PTLocation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class PrayerTimesCalculator {

    private Method method;
    private PTLocation location;
    private Prayer prayer;

    public PrayerTimesCalculator() {
        // Inicijalizacija objekata
        method = new Method();
        prayer = new Prayer();
    }

    public void setMethod(int methodIndex) {
        // Konfiguracija metode izračunavanja na osnovu indeksa
        method.setMethod(methodIndex);
    }

    public void setLocation(double latitude, double longitude, double altitude, String timezone) {
        // Konfiguracija lokacije
        double gmtDiff = TimeZone.getTimeZone(timezone).getRawOffset() / (1000.0 * 3600);
        location = new PTLocation(latitude, longitude, gmtDiff, 0, altitude, 1010, 10);
    }

    public PrayerTime[] calculatePrayerTimes() {
        // Trenutni datum
        Date date = new Date();

        // Izračunavanje vremena molitvi
        return prayer.getPrayerTimes(location, method, date);
    }

    // Dodajte dodatne metode za prilagođavanje konfiguracije ako je potrebno
}
