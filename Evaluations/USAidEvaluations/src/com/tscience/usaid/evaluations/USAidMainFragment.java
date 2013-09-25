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
        
        // TODO open large view dialog
        
        super.onListItemClick(l, v, position, id);
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
            
        }
        
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        
        switch(currentItemId) {
            
            case R.id.action_filter_sector_agriculture:
            case R.id.action_filter_sector_democracy:
            case R.id.action_filter_sector_finance:
            case R.id.action_filter_sector_education:
            case R.id.action_filter_sector_environment:
            case R.id.action_filter_sector_gender:
            case R.id.action_filter_sector_health:
            case R.id.action_filter_sector_technology:
            case R.id.action_filter_sector_water:
            case R.id.action_filter_sector_crisis: {
                
                displaySectors();
                return true;
                
            }
            
        } // end switch
        
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
     * @param value The sector value to display (from USAidConstants--sector image values).
     */
    private void displaySectors() {
        
        // get the submenu
        SubMenu subMenu = myMenu.getItem(1).getSubMenu();
        
        int menuSize = subMenu.size();
        
        Log.d(LOG_TAG, "----------------------------------------menuSize: " + menuSize);
        
        MenuItem menuItem = null;
        
        Vector<Integer> checkedVector = new Vector<Integer>();
        
        // what menu items are checked
        for (int x = 0; x < menuSize; x++) {
            
            menuItem = subMenu.getItem(x);
            
            if (menuItem.isChecked()) {
                
                Log.d(LOG_TAG, "---------------------------------------- menu is checked");
                
                int checkedNum = Integer.valueOf(getMenuItemSectorConstant(menuItem.getItemId()));
                
                if (checkedNum > 0) {
                    checkedVector.add(checkedNum);
                }
                
            }
            
        } // end looking for checked menu items
        
        // size of checked menu items
        int numberChecked = checkedVector.size();
        
        Log.d(LOG_TAG, "---------------------------------------- numberChecked: " + numberChecked);
        
        ArrayList<USAidDataObject> newData = new ArrayList<USAidDataObject>();
        
        int maxValues = currentData.size();
        
        for (int i = 0; i < maxValues; i++) {
            
            // check each one of these against checked vector
            for (int j = 0; j < numberChecked; j++) {
            
                if (currentData.get(i).sectorValue == checkedVector.get(j).intValue()) {
                    newData.add(currentData.get(i));
                }
                
            }
            
        } // end maxValues
        
        setTheListData(newData, false);
        
    }
    
    /**
     * Convience menthod to get what was constant for sorting.
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
        
    } // end getMenuItemConstant
    
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
