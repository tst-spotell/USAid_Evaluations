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
        
    } // end getImageId
    
    /**
     * Convenience method to get what was region constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The region constant value used for sorting.
     */
    public static int getMenuItemRegionConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_region_afganistan: {
                return USAidConstants.USAID_REGION_AFGHANISTAN;
            }
            
            case R.id.action_filter_region_pakistan: {
                return USAidConstants.USAID_REGION_PAKISTAN;
            }
            
            case R.id.action_filter_region_asia: {
                return USAidConstants.USAID_REGION_ASIA;
            }
            
            case R.id.action_filter_region_europe: {
                return USAidConstants.USAID_REGION_EUROPE;
            }
            
            case R.id.action_filter_region_latin_america: {
                return USAidConstants.USAID_REGION_LATIN_AMERICA;
            }

            case R.id.action_filter_region_middle_east: {
                return USAidConstants.USAID_REGION_MIDDLE_EAST;
            }
            
            case R.id.action_filter_region_africa: {
                return USAidConstants.USAID_REGION_AFRICA;
            }
            
        } // end switch

        return 0;
        
    } // end getMenuItemRegionConstant
    
    /**
     * Convenience method to get what was constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The constant value used for sorting.
     */
    public static int getMenuItemSectorConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_sector_agriculture: {
                return USAidConstants.USAID_SECTOR_AGRICULTURE;
            }
            
            case R.id.action_filter_sector_democracy: {
                return USAidConstants.USAID_SECTOR_DEMOCRACY;
            }
            
            case R.id.action_filter_sector_finance: {
                return USAidConstants.USAID_SECTOR_ECONOMIC;
            }
            
            case R.id.action_filter_sector_education: {
                return USAidConstants.USAID_SECTOR_EDUCATION;
            }
            
            case R.id.action_filter_sector_environment: {
                return USAidConstants.USAID_SECTOR_ENVIRONMENT;
            }

            case R.id.action_filter_sector_gender: {
                return USAidConstants.USAID_SECTOR_GENDER;
            }
            
            case R.id.action_filter_sector_health: {
                return USAidConstants.USAID_SECTOR_HEALTH;
            }
            
            case R.id.action_filter_sector_technology: {
                return USAidConstants.USAID_SECTOR_SCIENCE;
            }
            
            case R.id.action_filter_sector_water: {
                return USAidConstants.USAID_SECTOR_WATER;
            }
            
            case R.id.action_filter_sector_crisis: {
                return USAidConstants.USAID_SECTOR_CRISIS;
            }
            
        } // end switch

        return 0;
        
    } // end getMenuItemSectorConstant
    
    /**
     * Convenience method to get what was country constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The country constant value used for sorting.
     */
    public static int getMenuItemCountryConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_country_afghanistan: {
                return USAidConstants.USAID_COUNTRY_AFGHANISTAN;
            }
            
            case R.id.action_filter_country_albania: {
                return USAidConstants.USAID_COUNTRY_ALBANIA;
            }
            
            case R.id.action_filter_country_angola: {
                return USAidConstants.USAID_COUNTRY_ANGOLA;
            }
            
            case R.id.action_filter_country_armenia: {
                return USAidConstants.USAID_COUNTRY_ARMENIA;
            }
            
            case R.id.action_filter_country_bangladesh: {
                return USAidConstants.USAID_COUNTRY_BANGLADESH;
            }

            case R.id.action_filter_country_bosnia: {
                return USAidConstants.USAID_COUNTRY_BOSNIA;
            }
            
            case R.id.action_filter_country_brazil: {
                return USAidConstants.USAID_COUNTRY_BRAZIL;
            }
            
            case R.id.action_filter_country_burma: {
                return USAidConstants.USAID_COUNTRY_BURMA;
            }
            
            case R.id.action_filter_country_burundi: {
                return USAidConstants.USAID_COUNTRY_BURUNDI;
            }
            
            case R.id.action_filter_country_cambodia: {
                return USAidConstants.USAID_COUNTRY_CAMBODIA;
            }
            
            case R.id.action_filter_country_chad: {
                return USAidConstants.USAID_COUNTRY_CHAD;
            }
            
            case R.id.action_filter_country_colombia: {
                return USAidConstants.USAID_COUNTRY_COLOMBIA;
            }

            case R.id.action_filter_country_dominican_republic: {
                return USAidConstants.USAID_COUNTRY_DOMINICAN_REPUBLIC;
            }
            
            case R.id.action_filter_country_congo: {
                return USAidConstants.USAID_COUNTRY_CONGO_DR;
            }
            
            case R.id.action_filter_country_ecuador: {
                return USAidConstants.USAID_COUNTRY_ECUADOR;
            }
            
            case R.id.action_filter_country_egypt: {
                return USAidConstants.USAID_COUNTRY_EGYPT;
            }
            
            case R.id.action_filter_country_el_salvador: {
                return USAidConstants.USAID_COUNTRY_EL_SALVADOR;
            }
            
            case R.id.action_filter_country_ethiopia: {
                return USAidConstants.USAID_COUNTRY_ETHIOPIA;
            }
            
            case R.id.action_filter_country_georgia: {
                return USAidConstants.USAID_COUNTRY_GEORGIA;
            }

            case R.id.action_filter_country_ghana: {
                return USAidConstants.USAID_COUNTRY_GHANA;
            }
            
            case R.id.action_filter_country_guatemala: {
                return USAidConstants.USAID_COUNTRY_GUATEMALA;
            }
            
            case R.id.action_filter_country_haiti: {
                return USAidConstants.USAID_COUNTRY_HAITI;
            }
            
            case R.id.action_filter_country_india: {
                return USAidConstants.USAID_COUNTRY_INDIA;
            }
            
            case R.id.action_filter_country_indonesia: {
                return USAidConstants.USAID_COUNTRY_INDONESIA;
            }
            
            case R.id.action_filter_country_iraq: {
                return USAidConstants.USAID_COUNTRY_IRAQ;
            }
            
            case R.id.action_filter_country_kazakhstan: {
                return USAidConstants.USAID_COUNTRY_KAZAKHSTAN;
            }

            case R.id.action_filter_country_kenya: {
                return USAidConstants.USAID_COUNTRY_KENYA;
            }
            
            case R.id.action_filter_country_kosovo: {
                return USAidConstants.USAID_COUNTRY_KOSOVO;
            }
            
            case R.id.action_filter_country_kyrgyzstan: {
                return USAidConstants.USAID_COUNTRY_KYRGYZSTAN;
            }
            
            case R.id.action_filter_country_lebanon: {
                return USAidConstants.USAID_COUNTRY_LEBANON;
            }
            
            case R.id.action_filter_country_liberia: {
                return USAidConstants.USAID_COUNTRY_LIBERIA;
            }

            case R.id.action_filter_country_libya: {
                return USAidConstants.USAID_COUNTRY_LIBYA;
            }
            
            case R.id.action_filter_country_mali: {
                return USAidConstants.USAID_COUNTRY_MALI;
            }
            
            case R.id.action_filter_country_mauritania: {
                return USAidConstants.USAID_COUNTRY_MAURITANIA;
            }
            
            case R.id.action_filter_country_mexico: {
                return USAidConstants.USAID_COUNTRY_MEXICO;
            }
            
            case R.id.action_filter_country_montenegro: {
                return USAidConstants.USAID_COUNTRY_MONTENEGRO;
            }

            case R.id.action_filter_country_morocco: {
                return USAidConstants.USAID_COUNTRY_MOROCCO;
            }
            
            case R.id.action_filter_country_mozambique: {
                return USAidConstants.USAID_COUNTRY_MOZAMBIQUE;
            }
            
            case R.id.action_filter_country_nepal: {
                return USAidConstants.USAID_COUNTRY_NEPAL;
            }
            
            case R.id.action_filter_country_nicaragua: {
                return USAidConstants.USAID_COUNTRY_NICARAGUA;
            }
            
            case R.id.action_filter_country_niger: {
                return USAidConstants.USAID_COUNTRY_NIGER;
            }

            case R.id.action_filter_country_nigeria: {
                return USAidConstants.USAID_COUNTRY_NIGERIA;
            }
            
            case R.id.action_filter_country_pakistan: {
                return USAidConstants.USAID_COUNTRY_PAKISTAN;
            }
            
            case R.id.action_filter_country_paraguay: {
                return USAidConstants.USAID_COUNTRY_PARAGUAY;
            }
            
            case R.id.action_filter_country_peru: {
                return USAidConstants.USAID_COUNTRY_PERU;
            }
            
            case R.id.action_filter_country_philippines: {
                return USAidConstants.USAID_COUNTRY_PHILIPPINES;
            }

            case R.id.action_filter_country_rwanda: {
                return USAidConstants.USAID_COUNTRY_RWANDA;
            }
            
            case R.id.action_filter_country_senegal: {
                return USAidConstants.USAID_COUNTRY_SENEGAL;
            }
            
            case R.id.action_filter_country_serbia: {
                return USAidConstants.USAID_COUNTRY_SERBIA;
            }

            case R.id.action_filter_country_south_africa: {
                return USAidConstants.USAID_COUNTRY_SOUTH_AFRICA;
            }
            
            case R.id.action_filter_country_south_sudan: {
                return USAidConstants.USAID_COUNTRY_SOUTH_SUDAN;
            }
            
            case R.id.action_filter_country_sri_lanka: {
                return USAidConstants.USAID_COUNTRY_SRI_LANKA;
            }

            case R.id.action_filter_country_sudan: {
                return USAidConstants.USAID_COUNTRY_SUDAN;
            }
            
            case R.id.action_filter_country_tajikistan: {
                return USAidConstants.USAID_COUNTRY_TAJIKISTAN;
            }
            
            case R.id.action_filter_country_tanzania: {
                return USAidConstants.USAID_COUNTRY_TANZANIA;
            }

            case R.id.action_filter_country_timor_leste: {
                return USAidConstants.USAID_COUNTRY_TIMOR_LESTE;
            }
            
            case R.id.action_filter_country_turkmenistan: {
                return USAidConstants.USAID_COUNTRY_TURKMENISTAN;
            }
            
            case R.id.action_filter_country_uganda: {
                return USAidConstants.USAID_COUNTRY_UGANDA;
            }

            case R.id.action_filter_country_ukraine: {
                return USAidConstants.USAID_COUNTRY_UKRAINE;
            }
            
            case R.id.action_filter_country_uzbekistan: {
                return USAidConstants.USAID_COUNTRY_UZBEKISTAN;
            }
            
            case R.id.action_filter_country_vietnam: {
                return USAidConstants.USAID_COUNTRY_VIETNAM;
            }

            case R.id.action_filter_country_gaza: {
                return USAidConstants.USAID_COUNTRY_WB_GAZA;
            }
            
            case R.id.action_filter_country_yemen: {
                return USAidConstants.USAID_COUNTRY_YEMEN;
            }
            
            case R.id.action_filter_country_zimbabwe: {
                return USAidConstants.USAID_COUNTRY_ZIMBABWE;
            }
            
        } // end switch
        
        return 0;
        
    } // end getMenuItemCountryConstant
    
} // end USAidUtils
