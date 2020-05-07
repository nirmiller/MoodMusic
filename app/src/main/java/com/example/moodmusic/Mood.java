package com.example.moodmusic;

import java.security.Key;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class Mood {
    private String moodType;
    private int timesPlayed;

    public static final String SAD = "SAD";
    public static final String ANGRY = "ANGRY";
    public static final String HAPPY = "HAPPY";
    public static final String DEFAULT = "DEFAULT";
    public static final String HOYOUNG = "HOYOUNG";

    private static Map<String, Integer> SRC;
    private static Map<String, String[]> KEY;
    private static String[] sad = {"Depressed", "Sad", "Not Happy", "Crying", "Solemn", "Slow"};
    private static String[] happy = {"Happy", "Excited", "Up-beat", "Funky"};
    private static String[] angry = {"Angry", "Pissed", "Not Happy", "Furious"};
    private static String[] hoyoung = {"Korean"};

    private static void load()
    {
        SRC = new TreeMap<>();
        KEY = new TreeMap<>();

        SRC.put("DEFAULT", R.drawable.yellowcircle);
        SRC.put("SAD", R.drawable.sadface);
        SRC.put("HAPPY", R.drawable.smileface);
        SRC.put("ANGRY", R.drawable.angryface);
        SRC.put("HOYOUNG", R.drawable.houyong);

        KEY.put("SAD", sad);
        KEY.put("ANGRY", angry);
        KEY.put("HAPPY", happy);
        KEY.put("HOYOUNG", hoyoung);

    }

    public static Integer getSRC(String mood)
    {
        return SRC.get(mood);
    }

    public static String[] getKeyWords(String mood)
    {
        return KEY.get(mood);
    }

    public Mood(String mood, int timesPlayed)
    {
        load();
        moodType = mood;
        this.timesPlayed = timesPlayed;
    }


    public String getMoodType() {
        return moodType;
    }

    public void setMoodType(String moodType) {
        this.moodType = moodType;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    @Override
    public String toString() {
        return "Mood : " + moodType + " Number of Times Played :" + timesPlayed;
    }
}
