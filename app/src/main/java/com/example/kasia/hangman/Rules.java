package com.example.kasia.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class Rules extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rules);
            ((TextView)findViewById(R.id.rulesHeader)).setText(R.string.rulesHeader);
            ((TextView)findViewById(R.id.rulesContent)).setText(R.string.rulesContent);
        }
    }