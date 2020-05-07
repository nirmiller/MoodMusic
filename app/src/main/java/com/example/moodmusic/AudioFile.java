package com.example.moodmusic;

import java.net.URL;

public class AudioFile {
    private Mood mood;
    private int favorability;
    private URL url;
    private String name;
    private int rawID;


    public AudioFile(Mood mood, String name, int rawID) {
        this.mood = mood;
        this.name = name;
        this.rawID = rawID;

        url = null;
        favorability = 0;
    }

    public int getRawID() {
        return rawID;
    }

    public void setRawID(int rawID) {
        this.rawID = rawID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public int getFavorability() {
        return favorability;
    }

    public void setFavorability(int favorability) {
        this.favorability = favorability;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
