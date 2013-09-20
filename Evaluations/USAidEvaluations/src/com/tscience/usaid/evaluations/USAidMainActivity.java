/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.tscience.usaid.evaluations.io.USAidListDataTask;

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.usaid_main, menu);
//        return true;
//    }

} // end USAidMainActivity
