package com.studiobrend.abraj.model;

public class Name {
    private String name;
    private String transliteration;
    private int number;
    private Translation en; // Ovo je instanca unutrašnje klase Translation

    // Getteri za svako polje
    public String getName() {
        return name;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public int getNumber() {
        return number;
    }

    public Translation getTranslation() {
        return en;
    }

    // Unutrašnja klasa Translation
    public static class Translation {
        private String meaning;

        // Getter za polje meaning
        public String getMeaning() {
            return meaning;
        }
    }
}
