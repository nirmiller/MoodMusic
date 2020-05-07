package com.example.moodmusic.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moodmusic.AudioFile;
import com.example.moodmusic.Helper;
import com.example.moodmusic.Mood;
import com.example.moodmusic.R;
import com.example.moodmusic.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MusicPlayerActivity extends Activity implements View.OnClickListener {

    private boolean pause;

    TextView title;

    MediaPlayer mediaPlayer;

    TreeMap<String, Object> dataSave;
    User currentUser;

    AudioFile songPlaying;

    Animation rotateAnimation;
    ImageView disc;


    ImageView currentEmotion;

    Button back;

    int currentTime;
    private boolean spinning;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);


        spinning = false;
        pause = true;
        currentTime = 0;

        disc = findViewById(R.id.discplayer);
        disc.setImageResource(R.drawable.musicdisc);
        disc.setOnClickListener(this);


        title = findViewById(R.id.Title);

        currentEmotion = findViewById(R.id.currentEmotion);
        back = findViewById(R.id.backAct);

        back.setOnClickListener(this);
        currentEmotion.setOnClickListener(this);

        dataSave = new TreeMap<>();
        try {
            Helper.load();
            currentUser = (User) Helper.getData().get("CURRENTUSER");
            currentEmotion.setImageResource(Mood.getSRC(currentUser.getRecentMood().getMoodType()));

        } catch (NullPointerException e) {
            currentEmotion.setImageResource(Mood.getSRC(Mood.DEFAULT));
            changeView(MoodChooserActivity.class);
            Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
            toast.show();
        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.currentEmotion:
                changeView(MoodChooserActivity.class);

                break;
            case R.id.backAct:
                changeView(MainMenuActivity.class);
                break;
            case R.id.discplayer:

                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, pullSong(currentUser.getRecentMood()));
                    mediaPlayer.start();
                    currentTime = 0;
                    pause = false;
                } else if (!pause) {
                    mediaPlayer.pause();
                    currentTime = mediaPlayer.getCurrentPosition();
                    pause = true;
                } else if (pause) {
                    mediaPlayer.seekTo(currentTime);
                    mediaPlayer.start();
                    pause = false;
                }


                break;


        }

    }

    private int pullSong(Mood mood) {

        int rawID = R.raw.sundaysbest;

        if (mood.getMoodType().equals(Mood.DEFAULT)) {
            rawID = R.raw.homedepothappy;
        } else {
            try {
                TreeMap<String, AudioFile> music = (TreeMap<String, AudioFile>) Helper.getData().get("MUSIC");
                rawID = (music.get(mood.getMoodType())).getRawID();
                songPlaying = music.get(mood.getMoodType());
                setTitle();

            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
                toast.show();
            }
        }


        return rawID;


    }


    private void changeView(Class m) {

        dataSave.put("CURRENTUSER", currentUser);

        Intent intent = new Intent(this, m);

        Helper.inData(dataSave);

        startActivity(intent);

        try {
            mediaPlayer.stop();
        } catch (NullPointerException e) {
            System.out.println("NOTHING");
        }

    }

    private void spinDisc(MediaPlayer media, float degree) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int lastDir = 0;
                int newDir = lastDir + 1;
                float pivotX = (float) (disc.getWidth() / 2.0);
                float pivotY = (float) (disc.getHeight() / 2.0);




                while(newDir < 360)
                {
                    Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
                    rotate.setDuration(25);
                    rotate.setFillAfter(true);
                    newDir = lastDir + 1;
                    lastDir = newDir;
                    disc.startAnimation(rotate);
                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spinning = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            spinning = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

            }
        });

        t.start();

    }

    private void setTitle() {

        if (songPlaying == null) {
            title.setText("No Song Loaded");
        } else {
            title.setText(songPlaying.getName());
        }

    }

}
