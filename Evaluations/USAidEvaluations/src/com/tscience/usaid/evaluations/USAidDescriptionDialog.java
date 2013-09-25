/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import android.app.Dialog;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.tscience.usaid.evaluations.utils.USAidDataObject;


/**
 * @author spotell at t-sciences.com
 *
 */
public class USAidDescriptionDialog extends SherlockDialogFragment {
    
    private USAidDataObject currentData;

    
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
        
        
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // get the current data from the bundle
        currentData = (USAidDataObject) savedInstanceState.get(USAidConstants.USAID_BUNDLE_DATA_OBJECT);
        
    }
    
    

} // end USAidDescriptionDialog
