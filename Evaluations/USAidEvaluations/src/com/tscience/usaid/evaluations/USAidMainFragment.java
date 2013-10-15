/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.tscience.usaid.evaluations.io.USAidListDataTask;
import com.tscience.usaid.evaluations.utils.USAidDataObject;
import com.tscience.usaid.evaluations.utils.USAidUtils;

/**
 * This fragment displays the main screen.
 * 
 * @author spotell at t-sciences.com
 */
public class USAidMainFragment extends SherlockListFragment {
    
    /** Log id of this class name. */
    private static final String LOG_TAG = "USAidMainFragment";
    
    private USAidListAdapter myListAdapter;
    
    private ArrayList<USAidDataObject> currentData;
    
    private Menu myMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View listView = inflater.inflate(R.layout.fragment_usaid_main, container, false);
            
        // start getting data
        USAidListDataTask usaidListDataTask = new USAidListDataTask(this);
        usaidListDataTask.execute(getString(R.string.usaid_json_query));
        
        return listView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        
    	// get the data object
    	USAidDataObject usaidDataObject = currentData.get(position);
    	
    	// make the new bundle
    	Bundle bundle = new Bundle();
    	bundle.putParcelable(USAidConstants.USAID_BUNDLE_DATA_OBJECT, usaidDataObject);
    	
    	// create the view
    	USAidDescriptionDialog usaidDescriptionDialog = USAidDescriptionDialog.newInstance(bundle);
    	usaidDescriptionDialog.show(getActivity().getSupportFragmentManager(), "description");
    	
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        
        inflater.inflate(R.menu.usaid_main, menu);
        
        // save instance of menu
        myMenu = menu;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int currentItemId = item.getItemId();
        
        if (currentItemId == R.id.action_filter_reset) {
            
            setTheListData(currentData, false);
            
            // uncheck everything
            getActivity().invalidateOptionsMenu();
            
            return true;
            
        } else if (currentItemId == R.id.action_download_pdf_viewer) {
            
            USAidUtils.getAdobeReader(getActivity());
            return true;
            
        }
        
        // handle if checkbox
        if (item.isCheckable()) {
        
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            
            displaySectors();
            return true;
        
        }
        
        return false;
    }

    /**
     * Display the list data.
     * 
     * @param value The array of USAidDataObject's to display.
     * @param update    Update the current data (only when pull from server).
     */
    public void setTheListData(ArrayList<USAidDataObject> value, boolean update) {
        
        if (update) {
            currentData = value;
        }
        
        try {
            // add padding around list
            this.getListView().setPadding(24, 24, 24, 24);
            
            // add space between items
            this.getListView().setDividerHeight(24);
            
            // attach to the list
            myListAdapter = new USAidListAdapter(getActivity(), R.layout.usaid_list_layout, value);
            
            this.setListAdapter(myListAdapter);
            
            myListAdapter.notifyDataSetChanged();
            
        } catch (Exception ignore) {
            Log.e(LOG_TAG, "---------------------------------------- " + ignore.toString());
        }
        
    }
    
    /**
     * Creates a new array of USAidDataObject's for a sector.
     * 
     * @param value The sorted list with sector selections.
     */
    private void displaySectors() {
        
        // get the submenu
        SubMenu subMenu = myMenu.findItem(R.id.action_sector).getSubMenu();
        
        int menuSize = subMenu.size();
        
        Log.d(LOG_TAG, "----------------------------------------menuSize sectors: " + menuSize);
        
        MenuItem menuItem = null;
        
        Vector<Integer> checkedVector = new Vector<Integer>();
        
        // what menu items are checked
        for (int x = 0; x < menuSize; x++) {
            
            menuItem = subMenu.getItem(x);
            
            if (menuItem.isChecked()) {
                
                int checkedNum = Integer.valueOf(getMenuItemSectorConstant(menuItem.getItemId()));
                
                if (checkedNum > 0) {
                    checkedVector.add(checkedNum);
                }
                
            }
            
        } // end looking for checked menu items
        
        // size of checked menu items
        int numberChecked = checkedVector.size();
        
        Log.d(LOG_TAG, "---------------------------------------- numberChecked sectors: " + numberChecked);
        
        ArrayList<USAidDataObject> newData = null;
        
        if (numberChecked == 0) {
            
            // display all
            newData = currentData;
            
        } else {
            
            // only display filtered items
            newData = new ArrayList<USAidDataObject>();
            
            int maxValues = currentData.size();
            
            for (int i = 0; i < maxValues; i++) {
                
                // check each one of these against checked vector
                for (int j = 0; j < numberChecked; j++) {
                
                    if (currentData.get(i).sectorValue == checkedVector.get(j).intValue()) {
                        newData.add(currentData.get(i));
                    }
                    
                }
                
            } // end maxValues
        
        }
        
        // now check the regions before set datalist
        displayCountryAndRegions(newData);
        
    } // end displaySectors
    
    /**
     * This method does the same thing as displaySectors but for the regions.
     * 
     * @param value The sorted list with regions selections.
     */
    private void displayCountryAndRegions(ArrayList<USAidDataObject> value) {
        
        // get the region submenu
        SubMenu subMenu = myMenu.findItem(R.id.action_region).getSubMenu();
        
        int menuSize = subMenu.size();
        
        Log.d(LOG_TAG, "----------------------------------------menuSize region: " + menuSize);
        
        MenuItem menuItem = null;
        
        Vector<Integer> checkedRegionVector = new Vector<Integer>();
        
        // what menu items are checked
        for (int x = 0; x < menuSize; x++) {
            
            menuItem = subMenu.getItem(x);
            
            if (menuItem.isChecked()) {
                
                int checkedNum = Integer.valueOf(getMenuItemRegionConstant(menuItem.getItemId()));
                
                if (checkedNum > 0) {
                    checkedRegionVector.add(checkedNum);
                }
                
            }
            
        } // end looking for checked menu items
        
        // get the country's checked
        subMenu = myMenu.findItem(R.id.action_country).getSubMenu();
        
        menuSize = subMenu.size();
        
        Log.d(LOG_TAG, "----------------------------------------menuSize country: " + menuSize);
        
        menuItem = null;
        
        Vector<Integer> checkedCountryVector = new Vector<Integer>();
        
        // what menu items are checked
        for (int x = 0; x < menuSize; x++) {
            
            menuItem = subMenu.getItem(x);
            
            if (menuItem.isChecked()) {
                
                int checkedNum = Integer.valueOf(getMenuItemCountryConstant(menuItem.getItemId()));
                
                if (checkedNum > 0) {
                    checkedCountryVector.add(checkedNum);
                }
                
            }
            
        } // end looking for checked menu items
        
        // size of checked menu items
        int numberRegionChecked = checkedRegionVector.size();
        
        Log.d(LOG_TAG, "---------------------------------------- numberRegionChecked: " + numberRegionChecked);
        
        int numberCountryChecked = checkedCountryVector.size();
        
        Log.d(LOG_TAG, "---------------------------------------- numberCountryChecked: " + numberCountryChecked);
        
        ArrayList<USAidDataObject> newData = null;
        
        if ((numberRegionChecked == 0) && (numberCountryChecked == 0)) {
            
            // display all
            newData = value;
            
        } else {
            
            // only display filtered items
            newData = new ArrayList<USAidDataObject>();
            
            int maxValues = value.size();
            
            boolean addedValue = false;
            
            for (int i = 0; i < maxValues; i++) {
                
                // reset added item
                addedValue = false;
                
                // check each one of these against region checked vector
                for (int j = 0; j < numberRegionChecked; j++) {
                
                    // is this defined in the region
                    if (value.get(i).regionValue == checkedRegionVector.get(j).intValue()) {
                        newData.add(value.get(i));
                        addedValue = true;
                        break;
                    }
                    
                } // end checking regions
                
                // check against the country vector if not added by region
                if (!addedValue) {
                    
                    for (int k = 0; k < numberCountryChecked; k++) {
                        
                        // is this defined for the country
                        if (value.get(i).countryCode == checkedCountryVector.get(k).intValue()) {
                            newData.add(value.get(i));
                            break;
                        }
                        
                    }
                    
                } // end checking countries
                
            } // end maxValues
        
        }
        
        Log.d(LOG_TAG, "---------------------------------------- final number reports: " + newData.size());
        
        setTheListData(newData, false);
        
    } // end displayRegions
    
    /**
     * Convenience method to get what was constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The constant value used for sorting.
     */
    private int getMenuItemSectorConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_sector_agriculture: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked AGRICULTURE");
                return USAidConstants.USAID_SECTOR_AGRICULTURE;
                
            }
            
            case R.id.action_filter_sector_democracy: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked DEMOCRACY");
                return USAidConstants.USAID_SECTOR_DEMOCRACY;
                
            }
            
            case R.id.action_filter_sector_finance: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ECONOMIC");
                return USAidConstants.USAID_SECTOR_ECONOMIC;
                
            }
            
            case R.id.action_filter_sector_education: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked EDUCATION");
                return USAidConstants.USAID_SECTOR_EDUCATION;
                
            }
            
            case R.id.action_filter_sector_environment: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ENVIRONMENT");
                return USAidConstants.USAID_SECTOR_ENVIRONMENT;
                
            }

            case R.id.action_filter_sector_gender: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked GENDER");
                return USAidConstants.USAID_SECTOR_GENDER;
                
            }
            
            case R.id.action_filter_sector_health: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked HEALTH");
                return USAidConstants.USAID_SECTOR_HEALTH;
                
            }
            
            case R.id.action_filter_sector_technology: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked SCIENCE");
                return USAidConstants.USAID_SECTOR_SCIENCE;
                
            }
            
            case R.id.action_filter_sector_water: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked WATER");
                return USAidConstants.USAID_SECTOR_WATER;
                
            }
            
            case R.id.action_filter_sector_crisis: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked CRISIS");
                return USAidConstants.USAID_SECTOR_CRISIS;
                
            }
            
        } // end switch

        return 0;
        
    } // end getMenuItemSectorConstant
    
    /**
     * Convenience method to get what was region constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The region constant value used for sorting.
     */
    private int getMenuItemRegionConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_region_afganistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked AFGHANISTAN");
                return USAidConstants.USAID_REGION_AFGHANISTAN;
                
            }
            
            case R.id.action_filter_region_pakistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked PAKISTAN");
                return USAidConstants.USAID_REGION_PAKISTAN;
                
            }
            
            case R.id.action_filter_region_asia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ASIA");
                return USAidConstants.USAID_REGION_ASIA;
                
            }
            
            case R.id.action_filter_region_europe: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked EUROPE");
                return USAidConstants.USAID_REGION_EUROPE;
                
            }
            
            case R.id.action_filter_region_latin_america: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked LATIN_AMERICA");
                return USAidConstants.USAID_REGION_LATIN_AMERICA;
                
            }

            case R.id.action_filter_region_middle_east: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked MIDDLE_EAST");
                return USAidConstants.USAID_REGION_MIDDLE_EAST;
                
            }
            
            case R.id.action_filter_region_africa: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked AFRICA");
                return USAidConstants.USAID_REGION_AFRICA;
                
            }

            
        } // end switch

        return 0;
        
    } // end getMenuItemRegionConstant
    
    /**
     * Convenience method to get what was country constant for sorting.
     * 
     * @param value The id of the menuItem selected.
     * 
     * @return  The country constant value used for sorting.
     */
    private int getMenuItemCountryConstant(int value) {
        
        switch(value) {
            
            case R.id.action_filter_country_afghanistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked afghanistan");
                return USAidConstants.USAID_COUNTRY_AFGHANISTAN;
                
            }
            
            case R.id.action_filter_country_albania: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked albania");
                return USAidConstants.USAID_COUNTRY_ALBANIA;
                
            }
            
            case R.id.action_filter_country_angola: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked angola");
                return USAidConstants.USAID_COUNTRY_ANGOLA;
                
            }
            
            case R.id.action_filter_country_armenia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked armenia");
                return USAidConstants.USAID_COUNTRY_ARMENIA;
                
            }
            
            case R.id.action_filter_country_bangladesh: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked bangladesh");
                return USAidConstants.USAID_COUNTRY_BANGLADESH;
                
            }

            case R.id.action_filter_country_bosnia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked bosnia");
                return USAidConstants.USAID_COUNTRY_BOSNIA;
                
            }
            
            case R.id.action_filter_country_brazil: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked brazil");
                return USAidConstants.USAID_COUNTRY_BRAZIL;
                
            }
            
            case R.id.action_filter_country_burma: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked burma");
                return USAidConstants.USAID_COUNTRY_BURMA;
                
            }
            
            case R.id.action_filter_country_burundi: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked burundi");
                return USAidConstants.USAID_COUNTRY_BURUNDI;
                
            }
            
            case R.id.action_filter_country_cambodia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked cambodia");
                return USAidConstants.USAID_COUNTRY_CAMBODIA;
                
            }
            
            case R.id.action_filter_country_chad: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked chad");
                return USAidConstants.USAID_COUNTRY_CHAD;
                
            }
            
            case R.id.action_filter_country_colombia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked colombia");
                return USAidConstants.USAID_COUNTRY_COLOMBIA;
                
            }

            case R.id.action_filter_country_dominican_republic: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked dominican_republic");
                return USAidConstants.USAID_COUNTRY_DOMINICAN_REPUBLIC;
                
            }
            
            case R.id.action_filter_country_congo: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked congo");
                return USAidConstants.USAID_COUNTRY_CONGO_DR;
                
            }
            
            case R.id.action_filter_country_ecuador: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ecuador");
                return USAidConstants.USAID_COUNTRY_ECUADOR;
                
            }
            
            case R.id.action_filter_country_egypt: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked egypt");
                return USAidConstants.USAID_COUNTRY_EGYPT;
                
            }
            
            case R.id.action_filter_country_el_salvador: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked el_salvador");
                return USAidConstants.USAID_COUNTRY_EL_SALVADOR;
                
            }
            
            case R.id.action_filter_country_ethiopia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ethiopia");
                return USAidConstants.USAID_COUNTRY_ETHIOPIA;
                
            }
            
            case R.id.action_filter_country_georgia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked georgia");
                return USAidConstants.USAID_COUNTRY_GEORGIA;
                
            }

            case R.id.action_filter_country_ghana: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ghana");
                return USAidConstants.USAID_COUNTRY_GHANA;
                
            }
            
            case R.id.action_filter_country_guatemala: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked guatemala");
                return USAidConstants.USAID_COUNTRY_GUATEMALA;
                
            }
            
            case R.id.action_filter_country_haiti: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked haiti");
                return USAidConstants.USAID_COUNTRY_HAITI;
                
            }
            
            case R.id.action_filter_country_india: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked india");
                return USAidConstants.USAID_COUNTRY_INDIA;
                
            }
            
            case R.id.action_filter_country_indonesia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked indonesia");
                return USAidConstants.USAID_COUNTRY_INDONESIA;
                
            }
            
            case R.id.action_filter_country_iraq: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked iraq");
                return USAidConstants.USAID_COUNTRY_IRAQ;
                
            }
            
            case R.id.action_filter_country_kazakhstan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked kazakhstan");
                return USAidConstants.USAID_COUNTRY_KAZAKHSTAN;
                
            }

            case R.id.action_filter_country_kenya: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked kenya");
                return USAidConstants.USAID_COUNTRY_KENYA;
                
            }
            
            case R.id.action_filter_country_kosovo: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked kosovo");
                return USAidConstants.USAID_COUNTRY_KOSOVO;
                
            }
            
            case R.id.action_filter_country_kyrgyzstan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked kyrgyzstan");
                return USAidConstants.USAID_COUNTRY_KYRGYZSTAN;
                
            }
            
            case R.id.action_filter_country_lebanon: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked lebanon");
                return USAidConstants.USAID_COUNTRY_LEBANON;
                
            }
            
            case R.id.action_filter_country_liberia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked liberia");
                return USAidConstants.USAID_COUNTRY_LIBERIA;
                
            }

            case R.id.action_filter_country_libya: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked libya");
                return USAidConstants.USAID_COUNTRY_LIBYA;
                
            }
            
            case R.id.action_filter_country_mali: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked mali");
                return USAidConstants.USAID_COUNTRY_MALI;
                
            }
            
            case R.id.action_filter_country_mauritania: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked mauritania");
                return USAidConstants.USAID_COUNTRY_MAURITANIA;
                
            }
            
            case R.id.action_filter_country_mexico: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked mexico");
                return USAidConstants.USAID_COUNTRY_MEXICO;
                
            }
            
            case R.id.action_filter_country_montenegro: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked montenegro");
                return USAidConstants.USAID_COUNTRY_MONTENEGRO;
                
            }

            case R.id.action_filter_country_morocco: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked morocco");
                return USAidConstants.USAID_COUNTRY_MOROCCO;
                
            }
            
            case R.id.action_filter_country_mozambique: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked mozambique");
                return USAidConstants.USAID_COUNTRY_MOZAMBIQUE;
                
            }
            
            case R.id.action_filter_country_nepal: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked nepal");
                return USAidConstants.USAID_COUNTRY_NEPAL;
                
            }
            
            case R.id.action_filter_country_nicaragua: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked nicaragua");
                return USAidConstants.USAID_COUNTRY_NICARAGUA;
                
            }
            
            case R.id.action_filter_country_niger: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked niger");
                return USAidConstants.USAID_COUNTRY_NIGER;
                
            }

            case R.id.action_filter_country_nigeria: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked nigeria");
                return USAidConstants.USAID_COUNTRY_NIGERIA;
                
            }
            
            case R.id.action_filter_country_pakistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked pakistan");
                return USAidConstants.USAID_COUNTRY_PAKISTAN;
                
            }
            
            case R.id.action_filter_country_paraguay: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked paraguay");
                return USAidConstants.USAID_COUNTRY_PARAGUAY;
                
            }
            
            case R.id.action_filter_country_peru: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked peru");
                return USAidConstants.USAID_COUNTRY_PERU;
                
            }
            
            case R.id.action_filter_country_philippines: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked philippines");
                return USAidConstants.USAID_COUNTRY_PHILIPPINES;
                
            }

            case R.id.action_filter_country_rwanda: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked rwanda");
                return USAidConstants.USAID_COUNTRY_RWANDA;
                
            }
            
            case R.id.action_filter_country_senegal: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked senegal");
                return USAidConstants.USAID_COUNTRY_SENEGAL;
                
            }
            
            case R.id.action_filter_country_serbia: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked serbia");
                return USAidConstants.USAID_COUNTRY_SERBIA;
                
            }

            case R.id.action_filter_country_south_africa: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked south_africa");
                return USAidConstants.USAID_COUNTRY_SOUTH_AFRICA;
                
            }
            
            case R.id.action_filter_country_south_sudan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked south_sudan");
                return USAidConstants.USAID_COUNTRY_SOUTH_SUDAN;
                
            }
            
            case R.id.action_filter_country_sri_lanka: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked sri_lanka");
                return USAidConstants.USAID_COUNTRY_SRI_LANKA;
                
            }

            case R.id.action_filter_country_sudan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked sudan");
                return USAidConstants.USAID_COUNTRY_SUDAN;
                
            }
            
            case R.id.action_filter_country_tajikistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked tajikistan");
                return USAidConstants.USAID_COUNTRY_TAJIKISTAN;
                
            }
            
            case R.id.action_filter_country_tanzania: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked tanzania");
                return USAidConstants.USAID_COUNTRY_TANZANIA;
                
            }

            case R.id.action_filter_country_timor_leste: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked timor_leste");
                return USAidConstants.USAID_COUNTRY_TIMOR_LESTE;
                
            }
            
            case R.id.action_filter_country_turkmenistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked turkmenistan");
                return USAidConstants.USAID_COUNTRY_TURKMENISTAN;
                
            }
            
            case R.id.action_filter_country_uganda: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked uganda");
                return USAidConstants.USAID_COUNTRY_UGANDA;
                
            }

            case R.id.action_filter_country_ukraine: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked ukraine");
                return USAidConstants.USAID_COUNTRY_UKRAINE;
                
            }
            
            case R.id.action_filter_country_uzbekistan: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked uzbekistan");
                return USAidConstants.USAID_COUNTRY_UZBEKISTAN;
                
            }
            
            case R.id.action_filter_country_vietnam: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked vietnam");
                return USAidConstants.USAID_COUNTRY_VIETNAM;
                
            }

            case R.id.action_filter_country_gaza: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked gaza");
                return USAidConstants.USAID_COUNTRY_WB_GAZA;
                
            }
            
            case R.id.action_filter_country_yemen: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked yemen");
                return USAidConstants.USAID_COUNTRY_YEMEN;
                
            }
            
            case R.id.action_filter_country_zimbabwe: {
                Log.d(LOG_TAG, "---------------------------------------- menu is checked zimbabwe");
                return USAidConstants.USAID_COUNTRY_ZIMBABWE;
                
            }
            
        } // end switch
        
        return 0;
    }
    
    /**
     * This is the array adapter class used for our custom view.
     * 
     * @author spotell at t-sciences.com
     */
    private class USAidListAdapter extends ArrayAdapter<USAidDataObject> {
        
        private ArrayList<USAidDataObject> items;
        
        private LayoutInflater inflater;

        public USAidListAdapter(Context context, int textViewResourceId, ArrayList<USAidDataObject> objects) {
            super(context, textViewResourceId, objects);
            
            items = objects;
            
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            
            View currentView = convertView;
            
            // if the view has not been created create it
            if (currentView == null) {
                
                currentView = inflater.inflate(R.layout.usaid_list_layout, null);
                
                // create the tag we are using
                currentView.setTag(new USAidViewHolder());
                
            }
            
            USAidViewHolder usaidViewHolder = (USAidViewHolder) currentView.getTag();
            
            // load the holder if empty
            if (usaidViewHolder.publishDateView == null) {
                
                usaidViewHolder.publishDateView = (TextView) currentView.findViewById(R.id.usaid_publish_date);
                usaidViewHolder.imageView = (ImageView) currentView.findViewById(R.id.usaid_data_image_type);
                usaidViewHolder.titleView = (TextView) currentView.findViewById(R.id.usaid_title);
                usaidViewHolder.descriptionView = (TextView) currentView.findViewById(R.id.usaid_description);
                
            }
            
            // the jagwireDataObject object we are working with
            usaidViewHolder.usaidDataObject = items.get(position);
            
            // show the date published
            usaidViewHolder.publishDateView.setText(usaidViewHolder.usaidDataObject.publishedString);
            
            // show the type image
            int useThisImage = usaidViewHolder.usaidDataObject.sectorValue;
            
            Log.d(LOG_TAG, "----------------------------------------useThisImage: " + useThisImage);
            
            switch (useThisImage) {
                
                case USAidConstants.USAID_SECTOR_AGRICULTURE: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_agriculture_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_DEMOCRACY: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_democracy_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_ECONOMIC: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_fincance_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_EDUCATION: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_education_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_ENVIRONMENT: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_environment_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_GENDER: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_gender_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_HEALTH: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_health_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_SCIENCE: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_technology_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_WATER: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_water_icon));
                    break;
                }

                case USAidConstants.USAID_SECTOR_CRISIS: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_crisis_icon));
                    break;
                }
                
                default: {
                    usaidViewHolder.imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.sectorlist_default_icon));
                }
                    
            } // end switch
            
            // show the title
            usaidViewHolder.titleView.setText(usaidViewHolder.usaidDataObject.title);
            
            // show the description
            usaidViewHolder.descriptionView.setText(usaidViewHolder.usaidDataObject.abstractString);
            
            return currentView;
            
        }
        
        /**
         * Gets the current displayed array list for sorting.
         * 
         * @return Current displayed arraylist.
         */
        public ArrayList<USAidDataObject> getCurrentDisplayedArrayList() {
            
            return items;
            
        }
        
    } // end USAidListAdapter
    
    /**
     * Static class for view holder pattern.
     * 
     * @author spotell at t-sciences.com
     *
     */
    static class USAidViewHolder {
        
        TextView publishDateView;
        ImageView imageView;
        TextView titleView;
        TextView descriptionView;
        
        USAidDataObject usaidDataObject;
        
    } // end SearchResultsViewHolder

} // end USAidMainFragment
