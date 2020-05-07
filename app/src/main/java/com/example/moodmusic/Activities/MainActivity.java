package com.example.moodmusic.Activities;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.util.ArrayMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.moodmusic.Helper;
import com.example.moodmusic.Mood;
import com.example.moodmusic.R;
import com.example.moodmusic.User;


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<User> users;
    User currentUser;

    EditText username;
    EditText password;

    ImageView title;

    Button signin;
    Button newuser;
    TreeMap<String, Object> dataSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dataSave = new TreeMap<>();

        //  Instead of creating a new ArrayList, this will also pull up the data base of Users created
        users = new ArrayList<>();


        username = findViewById(R.id.User);
        password = findViewById(R.id.Password);
        signin = findViewById(R.id.SignIn);
        newuser = findViewById(R.id.NewUser);
        title = findViewById(R.id.title);

        title.setImageResource(R.drawable.moodmusiclabel);


        signin.setOnClickListener(this);
        newuser.setOnClickListener(this);

        try {
            currentUser = (User) Helper.getData().get("CURRENTUSER");

        } catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "Data Not Loaded Error", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //  Creates a new user by instantiating a new user by taking the username, and password, and
            //  placing them into the users arraylist

            case R.id.NewUser:

                newUserButton();

                break;


            //  Finds a user, in the user data base, by taking the password and username, if found the current user is set
            //  to the signed in user.
            case R.id.SignIn:

                signInButton();


                break;

        }


    }

    private void signInButton() {
        if (users.size() == 0) {
            Toast toast = Toast.makeText(this, "Make a New User", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        currentUser = findUser(username.getText().toString(),
                password.getText().toString());
        if (currentUser != null) {

            changeView(MainMenuActivity.class);
        }


    }


    private void newUserButton() {
        User newUser = new User(username.getText().toString(),
                username.getText().toString(),
                password.getText().toString());
        users.add(newUser);


    }

    private void changeView(Class m) {


        dataSave.put("CURRENTUSER", currentUser);

        Intent intent = new Intent(this, m);

        Helper.inData(dataSave);

        startActivity(intent);


    }

    private User findUser(String user, String password) {

        for (User person : users) {
            if (person.getUser().equals(user)) {
                if (person.getPassword().equals(password)) {
                    return person;
                }
            }
        }
        //Prompt User Not Found!
        Toast toast = Toast.makeText(this, "User Not Found! Make a New User", Toast.LENGTH_SHORT);
        toast.show();
        return null;
    }

}
