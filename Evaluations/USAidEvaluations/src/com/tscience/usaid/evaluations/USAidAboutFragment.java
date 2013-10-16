/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.app.SherlockDialogFragment;


/**
 * This dialog displays the about for the application.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidAboutFragment extends SherlockDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View descriptionView = inflater.inflate(R.layout.fragment_usaid_about, null);
        
        builder.setView(descriptionView)
        
        // Add action buttons
        .setNegativeButton(R.string.usaid_ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                USAidAboutFragment.this.getDialog().cancel();
            }
        });
        
        Dialog d = builder.create();
        d.setCanceledOnTouchOutside(false);
        
        return d;
        
    }

    
    
} // end USAidAboutFragment
