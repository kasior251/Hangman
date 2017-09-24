package com.example.kasia.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kasia on 20.09.2017.
 */

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_stats);
        ((TextView)findViewById(R.id.victories)).setText(Integer.toString(getIntent().getIntExtra("seier", 0)));
        ((TextView)findViewById(R.id.losses)).setText(Integer.toString(getIntent().getIntExtra("tap", 0)));
    }

    //lukk viewet
    public void stop(View view) {
        finish();
    }

}
