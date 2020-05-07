package com.example.moodmusic;

import java.util.TreeMap;
public class PlayList implements Comparable {

    private TreeMap<String, AudioFile> playList;
    private String playListName;
    private int favorability;



    public PlayList(String playListName)
    {
        this.playListName = playListName;
        playList = new TreeMap<>();
        favorability = 0;



    }

    public void played()
    {
        favorability++;
    }


    public void addSong(AudioFile song)
    {
        playList.put(song.getName(), song);
    }



    public TreeMap<String, AudioFile> getPlayList() {
        return playList;
    }

    public void setPlayList(TreeMap<String , AudioFile> playList) {
        this.playList = playList;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getFavorability() {
        return favorability;
    }

    public void setFavorability(int favorability) {
        this.favorability = favorability;
    }

    @Override
    public String toString() {

        String output = "Playlist : " + playListName + " Times Played : " + favorability + "\n";
        for(String song : playList.keySet())
        {
            AudioFile audioFile = playList.get(song);
            output += audioFile + "\n";
        }
        return output;
    }

    @Override
    public int compareTo(Object o) {
       PlayList obj = (PlayList)o;
       if(obj.getFavorability() < this.favorability)
       {
           return -1;
       }else if(obj.getFavorability() > this.favorability)
       {
           return 1;
       }
       return 0;
    }
}
