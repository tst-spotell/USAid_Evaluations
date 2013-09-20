/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.tscience.usaid.evaluations.R;
import com.tscience.usaid.evaluations.USAidMainFragment;

/**
 * This class get the data from the server to display the evaluations in the list.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidListDataTask extends UsaidHttpsAsyncTask<String, Void, JSONObject> {
    
    /** Log id of this class name. */
    private static final String LOG_TAG = "USAidListDataTask";
    
    // weak reference to check and make sure fragment is still there
    private final WeakReference<USAidMainFragment> usaidMainFragment;
    
    // the context we are working with
    private Context context;
    
    // dialog used to show user that actions are running
    ProgressDialog progressDialog;
    
    /**
     * Public constructor with weak reference to the fragment that launched it.
     * 
     * @param value The launching fragment.
     */
    public USAidListDataTask(USAidMainFragment value) {
        
        usaidMainFragment = new WeakReference<USAidMainFragment>(value);
        
        context = value.getActivity();
        
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        
        // show the progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.usaid_connection_server);       
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setButton(ProgressDialog.BUTTON_NEUTRAL, context.getText(R.string.usaid_cancel_button), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                // cancel task
                cancelingTask();
                
            }
            
        });
        
        progressDialog.show();
        
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        
        String urlString = null;
        
        try {
            
            // do we have a url to connect to
            if (params != null) {
                
                urlString = params[0];
                
                
                
            } else {
                // no values passed in we are done
                return null;
            }
            
            // create the credentials with username and password
            CredentialsProvider credProvider = new BasicCredentialsProvider();
            credProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials("", ""));
            
            // get the client
            HttpClient httpClient = getNewHttpClient();
            ((AbstractHttpClient) httpClient).setCredentialsProvider(credProvider);
            
            HttpGet httpGet = new HttpGet(urlString);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            
            // get the response
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();
            
            // read the json string
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            
            String line = null;
            StringBuilder jsonStringBuilder = new StringBuilder();
            
            try {
                while ((line = rd.readLine()) != null) {
                    jsonStringBuilder.append(line);
                    if (this.isCancelled()) {
                        break;
                    }
                }
            }
            finally {
                // close the reader
                rd.close();
            }
            
            String newString = jsonStringBuilder.toString();
            
            Log.d(LOG_TAG, "-------------------------------------------------- " + newString);
            
            // create the json object
            JSONObject jsonObject = new JSONObject(newString);
            
            return jsonObject;
        
        }
        catch (Exception e) {
            
            Log.e(LOG_TAG, "------------------------------------- " + e.toString());
            
        }
        
        return null;
        
    } // end doInBackground
    
    
    @Override
    protected void onPostExecute(JSONObject result) {
        
        // null most likely caused by connection error
        if (result == null) {
            
            Log.d(LOG_TAG, "---------------------------------- no results from server");
            
        } else {
            
            // was the task canceled
            if (this.isCancelled()) {
                result = null;
                return;
            }
            
        }
        
        // process the json object
        
        // turn the progress dialog off
        try {
            progressDialog.dismiss();
        } catch (Exception ignore) {}
        
        // little cleanup
        context = null;
    }

    // method for canceling this task
    private void cancelingTask() {
        this.cancel(true);
    }

} // end USAidListDataTask
