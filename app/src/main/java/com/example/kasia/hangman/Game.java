package com.example.kasia.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/*
TODO
implementere sjekk om det er oppnådd maks antall feil - da bør det vises ordet og spillet stopper
implementere sjekk om alle bokstaver er gjettet

 */
public class Game extends AppCompatActivity {

    private int ordLengde;
    private char[][] ordTabell;
    int antFeil;
    int antRiktig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ordTabell = genererOrd();
        ordLengde = ordTabell.length;
        antFeil = antRiktig = 0;
        skrivTilSkjerm(getOrd());
    }

    //oppdaterer bildet av galge
    protected void settBilde(int feil) {
        ImageView image = (ImageView)findViewById(R.id.galge);
        switch (feil) {
            case 0:
                image.setImageResource(R.drawable.d001);
                break;
            case 1:
                image.setImageResource(R.drawable.d002);
                break;
            case 2:
                image.setImageResource(R.drawable.d003);
                break;
            case 3:
                image.setImageResource(R.drawable.d004);
                break;
            case 4:
                image.setImageResource(R.drawable.d005);
                break;
            case 5:
                image.setImageResource(R.drawable.d006);
                break;
            case 6:
                image.setImageResource(R.drawable.d007);
                break;
            case 7:
                image.setImageResource(R.drawable.d008);
                break;
            default:
                image.setImageResource(R.drawable.d001);
        }
    }

    //velger et ramdomt ord fra arrays.xml
    private char[][] genererOrd() {
        String randomOrd = (getResources().getStringArray(R.array.ord)[(new Random()).nextInt(9)]);
        TextView visOrd = (TextView)findViewById(R.id.ord);
        visOrd.setText(randomOrd);
        char[][] ord = new char[randomOrd.length()][2];
        for (int i = 0; i < randomOrd.length(); i++)
        {
            ord[i][0] = randomOrd.charAt(i);
            ord[i][1] = 'n';
        }

        return ord;
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
    }


}


