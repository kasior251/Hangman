package com.example.kasia.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void rulesOnClick(View v) {
        ((Button) v).setText("clicked");
    }

    public void startOnClick(View v) {
        ((Button) v).setText("clicked");
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void langOnClick(View v) {
        ((Button) v).setText("clicked");
    }

    public void statsOnClick(View v) {
        ((Button) v).setText("clicked");
    }
}
