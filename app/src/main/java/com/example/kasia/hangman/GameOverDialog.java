package com.example.kasia.hangman;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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
