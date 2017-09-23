package com.example.kasia.hangman;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeScreen extends AppCompatActivity {

    private static int tapTeller;
    private static int seierTeller;
    private String currLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad;
        if (savedInstanceState != null) {
            languageToLoad = savedInstanceState.getString("lang");
            tapTeller = savedInstanceState.getInt("tap");
            seierTeller = savedInstanceState.getInt("seier");
        }
        else {
            languageToLoad = "no";
            tapTeller = seierTeller = 0;
        }
        currLanguage = languageToLoad;


        //Setter default språk til norsk
        loadLanguage(currLanguage);

        this.setContentView(R.layout.activity_welcome_screen);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("lang", currLanguage);
        outState.putInt("tap", tapTeller);
        outState.putInt("seier", seierTeller);
        System.out.println("Langueage som sendes er " + currLanguage);

    }

    private void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public static void tap() {
        tapTeller++;
    }

    public static void seier() {
        seierTeller++;
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
            currLanguage = "no";
        }
        else {
            setLanguage(new Locale("en"));
            currLanguage = "en";
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
        Intent intent = new Intent(this, Stats.class);
        intent.putExtra("seier", seierTeller);
        intent.putExtra("tap", tapTeller);

        startActivity(intent);
    }

}