/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
        setContentView(R.layout.activity_usaid_main);
        
        USAidMainFragment usaidMainFragment = new USAidMainFragment();
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.usaid_home_fragment_container, usaidMainFragment);
    
        // Commit the transaction
        transaction.commit();
        
    }

    public void noPdfViewer() {
        
        // No application to view, ask to download one
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Application Found");
        builder.setMessage("Download one from Android Market?");
        builder.setPositiveButton("Yes, Please",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                        marketIntent
                                .setData(Uri
                                        .parse("market://details?id=com.adobe.reader"));
                        startActivity(marketIntent);
                    }
                });
        builder.setNegativeButton("No, Thanks", null);
        builder.create().show();
        
    }
    
} // end USAidMainActivity
