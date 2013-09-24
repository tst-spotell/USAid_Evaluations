/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.tscience.usaid.evaluations.R;

/**
 * This class holds the various utility methods used by the application.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidUtils {

    public static int getTheSectorValue(String value) {
        
        return 0;
        
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
