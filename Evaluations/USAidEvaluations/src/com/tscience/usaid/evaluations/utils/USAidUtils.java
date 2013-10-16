/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tscience.usaid.evaluations.R;
import com.tscience.usaid.evaluations.USAidConstants;

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

    /**
     * Returns a map of the sector values with key mappings.
     * 
     * @param context   The context requesting the hashmap.
     * 
     * @return  Hashmap of sector types mapped to a sector code.
     */
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
    
    /**
     * Country codes are 1 to ... so -1 gives the correct array value.
     * 
     * @param countryCode   The country code we want the region value for.
     * 
     * @return  The region value for this countryCode.
     */
    public static int getTheRegionValue(int countryCode) {
        
        if (countryCode <= 0) {
            return 0;
        }
        
        return USAidConstants.REGION_ARRAY_ID[countryCode - 1];
        
    }
    
    /**
     * Convenience method to check country array for existing country selected.
     * 
     * @param arrayValue    The array of selected country USAidDataObject's.
     * @param countryCode   The country code to test.
     * @return              True if already in the list, False if not in the list.
     */
    public static boolean isCountryAlreadySelected(ArrayList<USAidDataObject> arrayValue, int countryCode) {
        
        int arraySize = arrayValue.size();
        
        for (int i = 0; i < arraySize; i++) {
            
            if (arrayValue.get(i).countryCode == countryCode) {
                return true;
            }
            
        }
        
        return false;
        
    } // end isCountryAlreadySelected
    
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
//        int[] country_id_array = context.getResources().getIntArray(R.array.usaid_filter_country_id);
        
        int loopSize = country_array.length;
        
        for (int i = 0; i < loopSize; i++) {
        	countryMap.put(country_array[i], USAidConstants.COUNTRY_ARRAY_ID[i]);
        }
        
        return countryMap;
        
    } // end makeCountryHashMap
    
    /**
     * Gets the image id for a given sector.
     * 
     * @param value The sector image id we are looking for.
     * 
     * @return  The image id to display for this sector.
     */
    public static int getImageId(int value) {
        
        switch (value) {
            
            case USAidConstants.USAID_SECTOR_AGRICULTURE: {
                return R.drawable.sectorlist_agriculture_icon;
            }

            case USAidConstants.USAID_SECTOR_DEMOCRACY: {
                return R.drawable.sectorlist_democracy_icon;
            }

            case USAidConstants.USAID_SECTOR_ECONOMIC: {
                return R.drawable.sectorlist_fincance_icon;
            }

            case USAidConstants.USAID_SECTOR_EDUCATION: {
                return R.drawable.sectorlist_education_icon;

            }

            case USAidConstants.USAID_SECTOR_ENVIRONMENT: {
                return R.drawable.sectorlist_environment_icon;
            }

            case USAidConstants.USAID_SECTOR_GENDER: {
                return R.drawable.sectorlist_gender_icon;
            }

            case USAidConstants.USAID_SECTOR_HEALTH: {
                return R.drawable.sectorlist_health_icon;
            }

            case USAidConstants.USAID_SECTOR_SCIENCE: {
                return R.drawable.sectorlist_technology_icon;
            }

            case USAidConstants.USAID_SECTOR_WATER: {
                return R.drawable.sectorlist_water_icon;
            }

            case USAidConstants.USAID_SECTOR_CRISIS: {
                return R.drawable.sectorlist_crisis_icon;
            }
            
            default: {
                return R.drawable.sectorlist_default_icon;
            }
                
        } // end switch
        
    }
    
} // end USAidUtils
