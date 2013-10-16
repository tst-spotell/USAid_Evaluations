/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.tscience.usaid.evaluations.utils.USAidDataObject;
import com.tscience.usaid.evaluations.utils.USAidUtils;


/**
 * @author spotell at t-sciences.com
 *
 */
public class USAidDescriptionDialog extends SherlockDialogFragment {
    
    /** Log id of this class name. */
    private static final String LOG_TAG = "USAidDescriptionDialog";
    
    private USAidDataObject currentData;


    public static USAidDescriptionDialog newInstance(Bundle bundle) {
    	
    	USAidDescriptionDialog f = new USAidDescriptionDialog();
    	f.setArguments(bundle);
    	
    	return f;
    	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // get the current data from the bundle
        currentData = (USAidDataObject) this.getArguments().get(USAidConstants.USAID_BUNDLE_DATA_OBJECT);
        
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	// Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View descriptionView = inflater.inflate(R.layout.usaid_description_fragment, null);
        
        // display the title
        TextView titleView = (TextView) descriptionView.findViewById(R.id.usaid_description_title);
        titleView.setText(currentData.title);
        
        // display the image
        ImageView imageView = (ImageView) descriptionView.findViewById(R.id.usaid_description_image);
        imageView.setImageDrawable(getActivity().getResources().getDrawable(USAidUtils.getImageId(currentData.sectorValue)));
        
    	// load the webview
    	WebView webView = (WebView) descriptionView.findViewById(R.id.usaid_description_value_web);
    	
    	webView.getSettings().setBuiltInZoomControls(true);
    	
    	if (currentData.abstractString.length() == 0) {
    	    webView.loadData(getActivity().getString(R.string.usaid_description_value_none), "text/html", null);
    	} else {
    	    webView.loadData(currentData.abstractString, "text/html", null);
    	}
        
        builder.setView(descriptionView)
        
        // Add action buttons
        .setPositiveButton(R.string.usaid_get_pdf_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                
            	getThePdf();
                
            }
        })
        
        .setNegativeButton(R.string.usaid_cancel_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	USAidDescriptionDialog.this.getDialog().cancel();
            }
        });
        
        Dialog d = builder.create();
        d.setCanceledOnTouchOutside(false);
        
        return d;
        
    }
    
    /**
     * This method is called to get the pdf file by creating an intent for pdf files.
     * If the user does not have a pdf viewer they will be prompted to down load one.
     */
    private void getThePdf() {
    	
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentData.pdfUrl));
        
        try {
            startActivity(intent);
        } 
        catch (ActivityNotFoundException e) {
            Log.d(LOG_TAG, "-------------------------------------------------- " + e.toString());
            
            ((USAidMainActivity)getActivity()).noPdfViewer();
            
        }
    	
    }

} // end USAidDescriptionDialog
