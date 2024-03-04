package com.studiobrend.abraj;

public class NameItem {
    private int imageResId;
    private int audioResId;
    private String description;

    public NameItem(int imageResId, int audioResId, String description) {
        this.imageResId = imageResId;
        this.audioResId = audioResId;
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getAudioResId() {
        return audioResId;
    }

    public String getDescription() {
        return description;
    }
}
