package com.example.moodmusic;


import java.util.ArrayList;

public class Data {

    ArrayList<User> users;

    public Data(ArrayList<User> users)
    {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
