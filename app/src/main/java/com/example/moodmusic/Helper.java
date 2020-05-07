package com.example.moodmusic;

import java.util.TreeMap;

public class Helper {

    static TreeMap<String, Object> data;


    public static void load()
    {
        TreeMap<String, AudioFile> music = new TreeMap<>();

        music.put("SAD", new AudioFile(new Mood(Mood.SAD, 0), "XXXTentacion - Everyone Dies in Their Nightmares", R.raw.xxxtentacioneverybodydiesintheirnightmares));
        music.put("HAPPY", new AudioFile(new Mood(Mood.HAPPY, 0), "Surfaces - Sundays Best", R.raw.sundaysbest));
        music.put("ANGRY", new AudioFile(new Mood(Mood.ANGRY, 0), "Eminem - Lose Yourself", R.raw.loseyourself));
        music.put("HOYOUNG", new AudioFile(new Mood(Mood.HOYOUNG, 0), "Hoyoung - Anthem", R.raw.sovietunion));

        data.put("MUSIC", music);
    }

    public static void inData(TreeMap<String, Object> inData)
    {
        data = inData;
    }

    public static TreeMap<String, Object> getData()
    {
        return data;
    }

    public static void clearData()
    {
        data = null;
    }

}
