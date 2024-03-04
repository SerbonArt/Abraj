package com.studiobrend.abraj;

public class PrayerTimes {
    private String fajr;
    private String sunrise;
    private String dhuhr;
    private String asr;
    private String maghrib;
    private String isha;

    // Konstruktor koji prima vremena molitvi
    public PrayerTimes(String fajr, String sunrise, String dhuhr, String asr, String maghrib, String isha) {
        this.fajr = fajr;
        this.sunrise = sunrise;
        this.dhuhr = dhuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
    }

    // Getere
    public String getFajr() {
        return fajr;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsha() {
        return isha;
    }

    // Setere
    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    // Možete dodati dodatne metode za manipulaciju ili prikaz vremena molitvi
}
