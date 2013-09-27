/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tscience.usaid.evaluations.R;

/**
 * This class holds the various utility methods used by the application.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidUtils {
    
    /**
     * This method opens the market with adobe reader for downloading.
     * 
     * @param context   The context requesting the intent.
     */
    public static void getAdobeReader(Context context) {
        
        Intent marketIntent = new Intent(Intent.ACTION_VIEW);
        marketIntent.setData(Uri.parse("market://details?id=com.adobe.reader"));
        context.startActivity(marketIntent);
        
    }

    public static Map<String, Integer> getTheSectorValue(Context context) {
        
        // make the sector hashmap
        HashMap<String, Integer> sectorMap = new HashMap<String, Integer>();
        
        String[] sector_array = context.getResources().getStringArray(R.array.usaid_filter_sectors_types);
        int[] sector_id_array = context.getResources().getIntArray(R.array.usaid_filter_sectors_types_id);
        
        int loopSize = sector_array.length;
        
        for (int i = 0; i < loopSize; i++) {
            sectorMap.put(sector_array[i], sector_id_array[i]);
        }
        
        return sectorMap;
        
    } // end getTheSectorValue
    
    public static int getTheRegionValue(int countryCode) {
        
        return 0;
        
    }
    
    /**
     * This method creates the hashmap with all country codes so we
     * can find them quickly with strings from the server json.
     * 
     * @return	Hashmap of the country codes.
     */
    public static Map<String, Integer> makeCountryHashMap(Context context) {
        
        // make the country hashmap
        HashMap<String, Integer> countryMap = new HashMap<String, Integer>();
        
        String[] country_array = context.getResources().getStringArray(R.array.usaid_filter_country);
        int[] country_id_array = context.getResources().getIntArray(R.array.usaid_filter_country_id);
        
        int loopSize = country_array.length;
        
        for (int i = 0; i < loopSize; i++) {
        	countryMap.put(country_array[i], country_id_array[i]);
        }
        
        return countryMap;
        
    }
    
} // end USAidUtils
