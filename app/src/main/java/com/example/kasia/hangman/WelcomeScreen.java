package com.example.kasia.hangman;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //Setter default språk til norsk
        String languageToLoad  = "no"; //
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_welcome_screen);
    }

    public void rulesOnClick(View v) {
        ((Button) v).setText("clicked");
    }

    public void startOnClick(View v) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void langOnClick(View v) {
        Locale currLocale = getResources().getConfiguration().locale;
        if (currLocale.toString().equals("en")) {
            setLanguage(new Locale("no"));
        }
        else {
            setLanguage(new Locale("en"));
        }

        this.setContentView(R.layout.activity_welcome_screen);
    }

    private void setLanguage(Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public void statsOnClick(View v) {
        ((Button) v).setText("clicked");
    }
}