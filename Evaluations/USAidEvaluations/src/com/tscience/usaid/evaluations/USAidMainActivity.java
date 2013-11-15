/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.tscience.usaid.evaluations.utils.USAidUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

/**
 * The main activity for the application holds the fragment container.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidMainActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the view
        setContentView(R.layout.usaid_main_activity);
        
        USAidMainFragment usaidMainFragment = new USAidMainFragment();
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.usaid_home_fragment_container, usaidMainFragment);
    
        // Commit the transaction
        transaction.commit();
        
    }

    /**
     * This is the alert dialog if the user has nothing to view pdf files with.
     */
    public void noPdfViewer() {
        
        // No application to view, ask to download one
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.usaid_nopdf_title));
        builder.setMessage(getString(R.string.usaid_nopdf_message));
        builder.setPositiveButton(getString(R.string.usaid_nopdf_yes_please),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        USAidUtils.getAdobeReader(USAidMainActivity.this);
                    }
                });
        builder.setNegativeButton(getString(R.string.usaid_nopdf_no_thanks), null);
        builder.create().show();
        
    } // end noPdfViewer
    
} // end USAidMainActivity
