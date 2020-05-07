package com.example.moodmusic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moodmusic.Helper;
import com.example.moodmusic.Mood;
import com.example.moodmusic.R;
import com.example.moodmusic.User;

import java.util.TreeMap;


public class MainMenuActivity extends Activity implements View.OnClickListener {

    ImageView lastEmotion;

    TextView userTitle;

    Button back;
    Button playlist;
    Button settings;
    Button musicplayer;

    TreeMap<String, Object> dataSave;
    User currentUser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        back = findViewById(R.id.BackButton);
        userTitle = findViewById(R.id.UserTitle);
        playlist = findViewById(R.id.Playlist);
        settings = findViewById(R.id.Settings);
        musicplayer = findViewById(R.id.MusicPlayer);
        lastEmotion = findViewById(R.id.lastEmotion);


        back.setOnClickListener(this);
        playlist.setOnClickListener(this);
        settings.setOnClickListener(this);
        musicplayer.setOnClickListener(this);
        lastEmotion.setOnClickListener(this);

        dataSave = new TreeMap<>();

        try {
            currentUser = (User) Helper.getData().get("CURRENTUSER");
            userTitle.setText(currentUser.getName());
            lastEmotion.setImageResource(Mood.getSRC(currentUser.getRecentMood().getMoodType()));
        } catch (NullPointerException e) {
            changeView(MoodChooserActivity.class);
            lastEmotion.setImageResource(Mood.getSRC(Mood.DEFAULT));
            Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
            toast.show();
        }


        lastEmotion.setImageResource(Mood.getSRC(currentUser.getRecentMood().getMoodType()));

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.BackButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
            case R.id.Playlist:

                playlistButton();

                break;
            case R.id.Settings:

                settingsButton();

                break;
            case R.id.MusicPlayer:

                musicplayerButton();

                break;
            case R.id.lastEmotion:

                moodChooser();

                break;
        }


    }

    private void moodChooser() {
        changeView(MoodChooserActivity.class);

    }

    private void playlistButton() {
        changeView(PlaylistActivity.class);
    }

    private void settingsButton() {
        //  Not Implementation
    }

    private void musicplayerButton() {
        changeView(MusicPlayerActivity.class);


    }


    private void changeView(Class m) {

        dataSave.put("CURRENTUSER", currentUser);

        Intent intent = new Intent(this, m);

        Helper.inData(dataSave);

        startActivity(intent);

    }
}
