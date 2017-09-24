package com.example.kasia.hangman;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Kasia on 19.09.2017.
 */

public class GameOverDialog extends DialogFragment {

    private DialogClickListener callback;

    public interface DialogClickListener {
        public void newGame();
        public void exit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Må implementere interfacet");
        }
    }

    public static GameOverDialog newInstance(String title) {
        GameOverDialog dialog = new GameOverDialog();
        Bundle args = new Bundle();
        args.putString("Tittel", title);

        dialog.setArguments(args);
        return dialog;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        Window window = getDialog().getWindow();

        window.setGravity(Gravity.BOTTOM|Gravity.LEFT);

        WindowManager.LayoutParams params = window.getAttributes();

        //sette x og y values slik at dialogFragmenten ikke skygger for bildet i game viewet
        params.x = 300;
        params.y = 50;
        window.setAttributes(params);
        return inflater.inflate(R.layout.activity_game, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String tittel = getArguments().getString("Tittel");
        return new AlertDialog.Builder(getActivity())
                .setTitle(tittel).setMessage(R.string.message).setPositiveButton(R.string.newGame,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                callback.newGame();
                            }
                        })
                .setNegativeButton(R.string.exit,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                callback.exit();
                            }
                        })
                .setCancelable(false)
                .create();
    }
}
