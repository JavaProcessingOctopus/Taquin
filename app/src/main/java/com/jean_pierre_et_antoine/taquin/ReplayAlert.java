package com.jean_pierre_et_antoine.taquin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Jean-Pierre on 30/11/2015.
 */
public class ReplayAlert extends DialogFragment {
    private final TaquinImageAdapter taquinImgs;

    public ReplayAlert(TaquinImageAdapter taquinImgs) {
        super();
        this.taquinImgs = taquinImgs;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Rejouer)
                .setPositiveButton(R.string.Oui, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        taquinImgs.removeSq();
                        taquinImgs.shuffle();
                    }
                })
                .setNegativeButton(R.string.Non, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}