/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.utils;

import java.util.HashMap;
import java.util.Map;

import com.tscience.usaid.evaluations.USAidConstants;

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
    
    public static int getTheCountryCode(String value) {
        
        return 0;
        
    }
    
    public static int getTheRegionValue(String value) {
        
        return 0;
        
    }
    
    public static Map<String, Integer> makeCountryHashMap() {
        
        // make the country hashmap
        HashMap<String, Integer> countryMap = new HashMap<String, Integer>();
        
        countryMap.put(USAidConstants.COUNTRY_AFGANISTAN, USAidConstants.COUNTRY_AFGANISTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_ALBANIA, USAidConstants.COUNTRY_ALBANIA_ID);
        countryMap.put(USAidConstants.COUNTRY_ANGOLA, USAidConstants.COUNTRY_ANGOLA_ID);
        countryMap.put(USAidConstants.COUNTRY_ARMENIA, USAidConstants.COUNTRY_ARMENIA_ID);
        countryMap.put(USAidConstants.COUNTRY_BANGLADESH, USAidConstants.COUNTRY_BANGLADESH_ID);
        countryMap.put(USAidConstants.COUNTRY_BOSNIA, USAidConstants.COUNTRY_BOSNIA_ID);
        countryMap.put(USAidConstants.COUNTRY_BRAZIL, USAidConstants.COUNTRY_BRAZIL_ID);
        countryMap.put(USAidConstants.COUNTRY_BURMA, USAidConstants.COUNTRY_BURMA_ID);
        countryMap.put(USAidConstants.COUNTRY_CAMBODIA, USAidConstants.COUNTRY_CAMBODIA_ID);
        countryMap.put(USAidConstants.COUNTRY_CHAD, USAidConstants.COUNTRY_CHAD_ID);
        countryMap.put(USAidConstants.COUNTRY_COLOMBIA, USAidConstants.COUNTRY_COLOMBIA_ID);
        countryMap.put(USAidConstants.COUNTRY_DOMINICAN_REPUBLIC, USAidConstants.COUNTRY_DOMINICAN_REPUBLIC_ID);
        countryMap.put(USAidConstants.COUNTRY_CONGO, USAidConstants.COUNTRY_CONGO_ID);
        countryMap.put(USAidConstants.COUNTRY_ECUADOR, USAidConstants.COUNTRY_ECUADOR_ID);
        countryMap.put(USAidConstants.COUNTRY_EGYPT, USAidConstants.COUNTRY_EGYPT_ID);
        countryMap.put(USAidConstants.COUNTRY_EL_SALVADOR, USAidConstants.COUNTRY_EL_SALVADOR_ID);
        countryMap.put(USAidConstants.COUNTRY_ETHIOPIA, USAidConstants.COUNTRY_ETHIOPIA_ID);
        countryMap.put(USAidConstants.COUNTRY_GEORGIA, USAidConstants.COUNTRY_GEORGIA_ID);
        countryMap.put(USAidConstants.COUNTRY_GHANA, USAidConstants.COUNTRY_GHANA_ID);
        countryMap.put(USAidConstants.COUNTRY_GUATEMALA, USAidConstants.COUNTRY_GUATEMALA_ID);
        countryMap.put(USAidConstants.COUNTRY_HAITI, USAidConstants.COUNTRY_HAITI_ID);
        countryMap.put(USAidConstants.COUNTRY_INDIA, USAidConstants.COUNTRY_INDIA_ID);
        countryMap.put(USAidConstants.COUNTRY_INDONESIA, USAidConstants.COUNTRY_INDONESIA_ID);
        countryMap.put(USAidConstants.COUNTRY_IRAQ, USAidConstants.COUNTRY_IRAQ_ID);
        countryMap.put(USAidConstants.COUNTRY_KAZAKHSTAN, USAidConstants.COUNTRY_KAZAKHSTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_KENYA, USAidConstants.COUNTRY_KENYA_ID);
        countryMap.put(USAidConstants.COUNTRY_KOSOVO, USAidConstants.COUNTRY_KOSOVO_ID);
        countryMap.put(USAidConstants.COUNTRY_KYRGYZSTAN, USAidConstants.COUNTRY_KYRGYZSTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_LEBANON, USAidConstants.COUNTRY_LEBANON_ID);
        countryMap.put(USAidConstants.COUNTRY_LIBERIA, USAidConstants.COUNTRY_LIBERIA_ID);
        countryMap.put(USAidConstants.COUNTRY_MALI, USAidConstants.COUNTRY_MALI_ID);
        countryMap.put(USAidConstants.COUNTRY_MAURITANIA, USAidConstants.COUNTRY_MAURITANIA_ID);
        countryMap.put(USAidConstants.COUNTRY_MEXICO, USAidConstants.COUNTRY_MEXICO_ID);
        countryMap.put(USAidConstants.COUNTRY_MONTENEGRO, USAidConstants.COUNTRY_MONTENEGRO_ID);
        countryMap.put(USAidConstants.COUNTRY_MOROCCO, USAidConstants.COUNTRY_MOROCCO_ID);
        countryMap.put(USAidConstants.COUNTRY_MOZAMBIQUE, USAidConstants.COUNTRY_MOZAMBIQUE_ID);
        countryMap.put(USAidConstants.COUNTRY_NEPAL, USAidConstants.COUNTRY_NEPAL_ID);
        countryMap.put(USAidConstants.COUNTRY_NICARAGUA, USAidConstants.COUNTRY_NICARAGUA_ID);
        countryMap.put(USAidConstants.COUNTRY_NIGER, USAidConstants.COUNTRY_NIGER_ID);
        countryMap.put(USAidConstants.COUNTRY_NIGERIA, USAidConstants.COUNTRY_NIGERIA_ID);
        countryMap.put(USAidConstants.COUNTRY_PAKISTAN, USAidConstants.COUNTRY_PAKISTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_PARAGUAY, USAidConstants.COUNTRY_PARAGUAY_ID);
        countryMap.put(USAidConstants.COUNTRY_PERU, USAidConstants.COUNTRY_PERU_ID);
        countryMap.put(USAidConstants.COUNTRY_PHILIPPINES, USAidConstants.COUNTRY_PHILIPPINES_ID);
        countryMap.put(USAidConstants.COUNTRY_RWANDA, USAidConstants.COUNTRY_RWANDA_ID);
        countryMap.put(USAidConstants.COUNTRY_SENEGAL, USAidConstants.COUNTRY_SENEGAL_ID);
        countryMap.put(USAidConstants.COUNTRY_SERBIA, USAidConstants.COUNTRY_SERBIA_ID);
        countryMap.put(USAidConstants.COUNTRY_SOUTH_AFRICA, USAidConstants.COUNTRY_SOUTH_AFRICA_ID);
        countryMap.put(USAidConstants.COUNTRY_SOUTH_SUDAN, USAidConstants.COUNTRY_SOUTH_SUDAN_ID);
        countryMap.put(USAidConstants.COUNTRY_SRI_LANKA, USAidConstants.COUNTRY_SRI_LANKA_ID);
        countryMap.put(USAidConstants.COUNTRY_TAJIKISTAN, USAidConstants.COUNTRY_TAJIKISTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_TANZANIA, USAidConstants.COUNTRY_TANZANIA_ID);
        countryMap.put(USAidConstants.COUNTRY_TIMOR_LESTE, USAidConstants.COUNTRY_TIMOR_LESTE_ID);
        countryMap.put(USAidConstants.COUNTRY_TURKMENISTAN, USAidConstants.COUNTRY_TURKMENISTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_UGANDA, USAidConstants.COUNTRY_UGANDA_ID);
        countryMap.put(USAidConstants.COUNTRY_UKRAINE, USAidConstants.COUNTRY_UKRAINE_ID);
        countryMap.put(USAidConstants.COUNTRY_UZBEKISTAN, USAidConstants.COUNTRY_UZBEKISTAN_ID);
        countryMap.put(USAidConstants.COUNTRY_VIETNAM, USAidConstants.COUNTRY_VIETNAM_ID);
        countryMap.put(USAidConstants.COUNTRY_WB_GAZA, USAidConstants.COUNTRY_WB_GAZA_ID);
        countryMap.put(USAidConstants.COUNTRY_YEMEN, USAidConstants.COUNTRY_YEMEN_ID);
        countryMap.put(USAidConstants.COUNTRY_ZIMBABWE, USAidConstants.COUNTRY_ZIMBABWE_ID);
        
        return countryMap;
        
    }
    
} // end USAidUtils
