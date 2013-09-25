/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

import java.util.ArrayList;

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
    
    private static int currentFilter = 0;

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
        
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int currentItemId = item.getItemId();
        
        if (currentItemId == R.id.action_filter_reset) {
            
            setTheListData(currentData, false);
            
        }
        else if (currentItemId == R.id.action_filter_sector_agriculture) {
            
            displayOnly(USAidConstants.USAID_SECTOR_AGRICULTURE);
            
        }
        else if (currentItemId == R.id.action_filter_sector_democracy) {
            
            displayOnly(USAidConstants.USAID_SECTOR_DEMOCRACY);
            
        }
        else if (currentItemId == R.id.action_filter_sector_finance) {
            
            displayOnly(USAidConstants.USAID_SECTOR_ECONOMIC);
            
        }
        else if (currentItemId == R.id.action_filter_sector_education) {
            
            displayOnly(USAidConstants.USAID_SECTOR_EDUCATION);
            
        }
        else if (currentItemId == R.id.action_filter_sector_environment) {
            
            displayOnly(USAidConstants.USAID_SECTOR_ENVIRONMENT);
            
        }
        else if (currentItemId == R.id.action_filter_sector_gender) {
            
            displayOnly(USAidConstants.USAID_SECTOR_GENDER);
            
        }
        else if (currentItemId == R.id.action_filter_sector_health) {
            
            displayOnly(USAidConstants.USAID_SECTOR_HEALTH);
            
        }
        else if (currentItemId == R.id.action_filter_sector_technology) {
            
            displayOnly(USAidConstants.USAID_SECTOR_SCIENCE);
            
        }
        else if (currentItemId == R.id.action_filter_sector_water) {
            
            displayOnly(USAidConstants.USAID_SECTOR_WATER);
            
        }
        else if (currentItemId == R.id.action_filter_sector_crisis) {
            
            displayOnly(USAidConstants.USAID_SECTOR_CRISIS);
            
        }
        
        return false;
    }

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
    
    private void displayOnly(int value) {
        
        currentFilter = value;
        
        ArrayList<USAidDataObject> newData = new ArrayList<USAidDataObject>();
        
        int maxValues = currentData.size();
        
        for (int i = 0; i < maxValues; i++) {
            if (currentData.get(i).sectorValue == value) {
                newData.add(currentData.get(i));
            }
        }
        
        setTheListData(newData, false);
        
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
