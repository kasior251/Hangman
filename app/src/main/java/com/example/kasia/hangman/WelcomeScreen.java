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

    //hvor mange ganger tapte spilleren i denne økten
    private static int tapTeller;
    //hvor mange ganger tapte spilleren i denne økten
    private static int seierTeller;
    //språk
    private String currLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad;

        //hente info fra bundle
        if (savedInstanceState != null) {
            languageToLoad = savedInstanceState.getString("lang");
            tapTeller = savedInstanceState.getInt("tap");
            seierTeller = savedInstanceState.getInt("seier");
        }

        //sette default verdier hvis ingen bundle ble sendt inn
        else {
            languageToLoad = "no";
            tapTeller = seierTeller = 0;
        }
        currLanguage = languageToLoad;


        //Setter default språk til norsk
        loadLanguage(currLanguage);

        this.setContentView(R.layout.activity_welcome_screen);
    }

    //holde rede på språk, antall seiere og antall tap
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("lang", currLanguage);
        outState.putInt("tap", tapTeller);
        outState.putInt("seier", seierTeller);
        System.out.println("Langueage som sendes er " + currLanguage);

    }

    //øke tap teller med 1
    public static void tap() {
        tapTeller++;
    }

    //øke seier teller med 1
    public static void seier() {
        seierTeller++;
    }

    //vise regler for spillet
    public void rulesOnClick(View v) {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    //starte spillet
    public void startOnClick(View v) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    //endre språk
    public void langOnClick(View v) {
        Locale currLocale = getResources().getConfiguration().locale;
        if (currLocale.toString().equals("en")) {
            loadLanguage("no");
            currLanguage = "no";
        }
        else {
            loadLanguage("en");
            currLanguage = "en";
        }

        this.setContentView(R.layout.activity_welcome_screen);
    }

    //hjelpemetode for å endre språk
    private void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    //vise statistikk for denne økten
    public void statsOnClick(View v) {
        Intent intent = new Intent(this, Stats.class);
        intent.putExtra("seier", seierTeller);
        intent.putExtra("tap", tapTeller);
        startActivity(intent);
    }

    public void quitOnClick(View v) {
        finish();
    }

}