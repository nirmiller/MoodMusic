package com.example.moodmusic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.moodmusic.Helper;
import com.example.moodmusic.Mood;
import com.example.moodmusic.R;
import com.example.moodmusic.User;

import java.util.TreeMap;

public class PlaylistActivity extends Activity {


    TreeMap<String, Object> dataSave;
    User currentUser;

    ListView playList_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_directory);

        try {
            currentUser = (User) Helper.getData().get("CURRENTUSER");


        } catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
            toast.show();
        }



    }


    private void changeView(Class m) {

        dataSave.put("CURRENTUSER", currentUser);

        Intent intent = new Intent(this, m);

        Helper.inData(dataSave);

        startActivity(intent);

    }
}
