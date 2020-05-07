package com.example.moodmusic;

import java.util.ArrayList;
import java.util.TreeMap;

public class User {


    String name;
    private TreeMap<String, PlayList> songBase;
    private String user;
    private String password;
    private Mood recentMood;

    private ArrayList<Mood> moods;

    public User(String name, TreeMap<String, PlayList> songBase, String user, String password, Mood recentMood)
    {

        moods = new ArrayList<>();
        moods.add(new Mood(Mood.SAD, 0));
        moods.add(new Mood(Mood.ANGRY, 0));
        moods.add(new Mood(Mood.HAPPY, 0));

        this.name = name;
        this.songBase = songBase;
        this.user = user;
        this.password = password;
        this.recentMood = new Mood(Mood.DEFAULT, 0);
    }

    public void setRecentMood(Mood recentMood) {
        this.recentMood = recentMood;
    }

    public ArrayList<Mood> getMoods() {
        return moods;
    }

    public void setMoods(ArrayList<Mood> moods) {
        this.moods = moods;
    }

    public User(User user)
    {

        this.moods = user.getMoods();
        this.name = user.getName();
        this.songBase = user.getSongBase();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.recentMood = user.getRecentMood();
    }

    public User(String name, String user, String password)
    {
        moods = new ArrayList<>();
        moods.add(new Mood(Mood.SAD, 0));
        moods.add(new Mood(Mood.ANGRY, 0));
        moods.add(new Mood(Mood.HAPPY, 0));

        this.name = name;
        this.user = user;
        this.password = password;
        songBase = new TreeMap<>();
        this.recentMood = new Mood(Mood.DEFAULT, 0);
    }

    public void indexMood()
    {
        Mood r = getRecentMood();
        for(Mood m : moods)
        {
            if(r.getMoodType().equals(m.getMoodType()))
            {
                m.setTimesPlayed(m.getTimesPlayed() + 1);
            }
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<String, PlayList> getSongBase() {
        return songBase;
    }

    public void setSongBase(TreeMap<String, PlayList> songBase) {
        this.songBase = songBase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Mood getRecentMood() {
        return recentMood;
    }

    public void setRecentMood(String recentMoodType) {


        recentMood.setMoodType(recentMoodType);

    }

    public String toString()
    {
        return "User : " + user + " Time : " + System.currentTimeMillis();
    }
}
