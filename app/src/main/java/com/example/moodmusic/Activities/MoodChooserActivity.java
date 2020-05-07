package com.example.moodmusic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moodmusic.Helper;
import com.example.moodmusic.Mood;
import com.example.moodmusic.R;
import com.example.moodmusic.User;

import java.util.TreeMap;

public class MoodChooserActivity extends Activity implements View.OnClickListener {

    ImageView sad;
    ImageView happy;
    ImageView angry;
    ImageView hoyoung;

    TreeMap<String, Object> dataSave;
    User currentUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_chooser);

        dataSave = new TreeMap<>();

        try {
            currentUser = (User) Helper.getData().get("CURRENTUSER");
        } catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
            toast.show();
        }

        sad = findViewById(R.id.sad);
        happy = findViewById(R.id.smile);
        angry = findViewById(R.id.angry);
        hoyoung = findViewById(R.id.hoyoung);

        sad.setImageResource(Mood.getSRC(Mood.SAD));
        happy.setImageResource(Mood.getSRC(Mood.HAPPY));
        angry.setImageResource(Mood.getSRC(Mood.ANGRY));
        hoyoung.setImageResource(Mood.getSRC(Mood.HOYOUNG));


        sad.setOnClickListener(this);
        happy.setOnClickListener(this);
        angry.setOnClickListener(this);
        hoyoung.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Mood setMood;

        switch (v.getId()) {
            case R.id.angry:
                currentUser.setRecentMood(Mood.ANGRY);
                currentUser.indexMood();
                changeView(MainMenuActivity.class);

                break;
            case R.id.smile:

                currentUser.setRecentMood(Mood.HAPPY);
                currentUser.indexMood();
                changeView(MainMenuActivity.class);
                break;
            case R.id.sad:
                currentUser.setRecentMood(Mood.SAD);
                currentUser.indexMood();
                changeView(MainMenuActivity.class);
                break;
            case R.id.hoyoung:
                currentUser.setRecentMood(Mood.HOYOUNG);
                currentUser.indexMood();
                changeView(MainMenuActivity.class);

                break;
        }

    }

    private void changeView(Class m) {

        dataSave.put("CURRENTUSER", currentUser);

        Intent intent = new Intent(this, m);

        Helper.inData(dataSave);

        startActivity(intent);

    }
}
