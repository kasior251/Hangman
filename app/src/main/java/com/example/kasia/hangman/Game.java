package com.example.kasia.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import static com.example.kasia.hangman.R.id.my_toolbar;

public class Game extends AppCompatActivity implements GameOverDialog.DialogClickListener {

    private int ordLengde;
    private char[][] ordTabell;
    private int antFeil;
    private int antRiktig;
    private String ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar myToolbar = (Toolbar)findViewById(my_toolbar);
        setSupportActionBar(myToolbar);
        ordTabell = genererOrd();
        ordLengde = ordTabell.length;
        antFeil = antRiktig = 0;
        skrivTilSkjerm(getOrd());
    }

    //sjekker om spilleren har flere forsøk
    protected boolean flereForsok()
    {
        return (antFeil < 7);
    }

    //sjekker om ordet er alt gjettet
    protected boolean ordGjettet() {
        return (antRiktig == ordLengde);
    }

    //oppdaterer bildet av galge
    protected void settBilde(int feil) {
        ImageView image = (ImageView)findViewById(R.id.galge);
        switch (feil) {
            case 0:
                image.setImageResource(R.drawable.galge0);
                break;
            case 1:
                image.setImageResource(R.drawable.galge1);
                break;
            case 2:
                image.setImageResource(R.drawable.galge2);
                break;
            case 3:
                image.setImageResource(R.drawable.galge3);
                break;
            case 4:
                image.setImageResource(R.drawable.galge4);
                break;
            case 5:
                image.setImageResource(R.drawable.galge5);
                break;
            case 6:
                image.setImageResource(R.drawable.galge6);
                break;
            case 7:
                image.setImageResource(R.drawable.galge7);
                break;
            default:
                image.setImageResource(R.drawable.galge0);
        }
    }

    //velger et randomt ord fra arrays.xml
    private char[][] genererOrd() {
        ord = (getResources().getStringArray(R.array.ord)[(new Random()).nextInt(getResources().getStringArray(R.array.ord).length)]);
        TextView visOrd = (TextView)findViewById(R.id.ord);
        visOrd.setText(ord);
        char[][] ordTabell = new char[ord.length()][2];
        for (int i = 0; i < ord.length(); i++)
        {
            ordTabell[i][0] = ord.charAt(i);
            ordTabell[i][1] = 'n';
        }

        return ordTabell;
    }

    //returnerer ordet som skal skrives til skjermen
    private String getOrd() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ordLengde; i++)
        {
            if (ordTabell[i][1] == 'y')
                sb.append(ordTabell[i][0]);
            else
                sb.append('_');
            sb.append(' ');
        }
        return sb.toString();
    }

    //skriver ordet til skjermen og oppdaterer bildet
    private void skrivTilSkjerm(String s) {
        ((TextView)findViewById(R.id.ord)).setText(s);
        settBilde(antFeil);
    }

    //sjekker hvilken bokstav er valgt og kaller på sjekkbokstav metoden
    public void valgtBokstav(View view) {

        int bokstavId = view.getId();

        switch (bokstavId) {

            case R.id.q: sjekkBokstav("q");
                break;
            case R.id.w: sjekkBokstav("w");
                break;
            case R.id.e: sjekkBokstav("e");
                break;
            case R.id.r: sjekkBokstav("r");
                break;
            case R.id.t: sjekkBokstav("t");
                break;
            case R.id.y: sjekkBokstav("y");
                break;
            case R.id.u: sjekkBokstav("u");
                break;
            case R.id.i: sjekkBokstav("i");
                break;
            case R.id.o: sjekkBokstav("o");
                break;
            case R.id.p: sjekkBokstav("p");
                break;
            case R.id.å: sjekkBokstav("å");
                break;
            case R.id.a: sjekkBokstav("a");
                break;
            case R.id.s: sjekkBokstav("s");
                break;
            case R.id.d: sjekkBokstav("d");
                break;
            case R.id.f: sjekkBokstav("f");
                break;
            case R.id.g: sjekkBokstav("g");
                break;
            case R.id.h: sjekkBokstav("h");
                break;
            case R.id.j: sjekkBokstav("j");
                break;
            case R.id.k: sjekkBokstav("k");
                break;
            case R.id.l: sjekkBokstav("l");
                break;
            case R.id.ø: sjekkBokstav("ø");
                break;
            case R.id.z: sjekkBokstav("z");
                break;
            case R.id.x: sjekkBokstav("x");
                break;
            case R.id.c: sjekkBokstav("c");
                break;
            case R.id.v: sjekkBokstav("v");
                break;
            case R.id.b: sjekkBokstav("b");
                break;
            case R.id.n: sjekkBokstav("n");
                break;
            case R.id.m: sjekkBokstav("m");
                break;
            case R.id.æ: sjekkBokstav("æ");
                break;

        }
    }

    //sjekker om bokstaven finnes i ordet
    //oppdaterer variabler og ordet samt bildet som vises på skjermen
    private void sjekkBokstav(String c) {
        if (!flereForsok() || ordGjettet()  ) {
            return;
        }

        int knappId = getResources().getIdentifier(c, "id", "com.example.kasia.hangman");
        ((Button)findViewById(knappId)).setEnabled(false);
        char bokstav = Character.toUpperCase(c.charAt(0));
        boolean funnet = false;

        for (int i = 0; i < ordLengde; i++)
        {
            if (ordTabell[i][1] == 'y')
                continue;
            if (ordTabell[i][0] == bokstav)
            {
                ordTabell [i][1] = 'y';
                antRiktig++;
                funnet = true;
            }
        }
        if (!funnet)
            antFeil++;

        skrivTilSkjerm(getOrd());

        if (ordGjettet()) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.cheering);
            mp.start();
            ((TextView)findViewById(R.id.melding)).setText(R.string.winnerMsg);
            showDialog(getString(R.string.winnerMsg) + " " + getString(R.string.theWord) + " " + ord);
            WelcomeScreen.seier();
        }
        if (!flereForsok()) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.womp);
            mp.start();
            skrivTilSkjerm(ord);
            ((TextView)findViewById(R.id.melding)).setText(R.string.loserMsg);
            showDialog(getString(R.string.loserMsg) + " " + getString(R.string.theWord) + " " + ord);
            WelcomeScreen.tap();
        }
    }

    //Metoden som blir kalt på hvis spilleren øsnker å spille en gang til
    @Override
    public void newGame() {
        finish();
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    //Metoden som kalles på når spilleren ikke vil starte et nytt spill
    @Override
    public void exit() {
        finish();
    }

    //Dialog-fragment som vises når spilleren gjettet ordet eller tapte
    public void showDialog(String string) {
        DialogFragment dialogFragment = GameOverDialog.newInstance(string);
        dialogFragment.show(getSupportFragmentManager(), "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stop:
                finish();
                break;
            default:
// If we got here, the user's action was not recognized.
// Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}


