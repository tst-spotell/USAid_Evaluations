/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class holds the data from a single source from the USAid server.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidDataObject implements Parcelable {
    
    /** The title for display. */
    public String title;
    
    /** The string value of the published date. */
    public String publishedString;
    
    /** The java long value of the published date. */
    public long publishedValue;
    
    /** The url to the pdf file for download. */
    public String pdfUrl;
    
    /** URL to a thumb nail image of the document cover. */
    public String thumbnailUrl;
    
    /** This is the description of the document. */
    public String abstractString;
    
    /** The string name of the sector associated with this document. */
    public String sectorString;
    
    /** The sector code associated with this document. */
    public int sectorValue;
    
    /** The region code associated with this document. */
    public int regionValue;
    
    /** Is this a multi region document.  Default is false. */
    public boolean hasRegionArray = false;
    
    /** The array of regions when more than one single region is attached to this document. */
    public int[] regionArray;
    
    /** The string name of the country associated with this document. */
    public String countryString;
    
    /** The country code associated with this document. */
    public int countryCode;
    
    /** Is this a multi country document.  Default is false. */
    public boolean hasCountryArray = false;
    
    /** The array of countries when more than one single country is attached to this document. */
    public int[] countryArray;
    
    /** The type of document this is. */
    public String documentType;

    @Override
    public int describeContents() {
        return 0;
    }
    
    // empty constructor
    public USAidDataObject() {};

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(title);
        dest.writeString(publishedString);
        dest.writeLong(publishedValue);
        dest.writeString(pdfUrl);
        dest.writeString(thumbnailUrl);
        dest.writeString(abstractString);
        dest.writeString(sectorString);
        dest.writeInt(sectorValue);
        dest.writeInt(regionValue);
        dest.writeByte((byte) (hasRegionArray ? 1 : 0));     //if hasRegionArray == true, byte == 1
        
        if (hasRegionArray) {
            
            int arrayLength = regionArray.length;
            
            // write number ints
            dest.writeInt(arrayLength);
            
            for (int i = 0; i < arrayLength; i++) {
                dest.writeInt(regionArray[i]);
            }
            
        }
        
        dest.writeString(countryString);
        dest.writeInt(countryCode);
        dest.writeByte((byte) (hasCountryArray ? 1 : 0));     //if hasCountryArray == true, byte == 1
        
        if (hasCountryArray) {
            
            int arrayLength = countryArray.length;
            
            // write number ints
            dest.writeInt(arrayLength);
            
            for (int i = 0; i < arrayLength; i++) {
                dest.writeInt(countryArray[i]);
            }
            
        }
        
        dest.writeString(documentType);
        
    } // end writeToParcel
    
    public static final Parcelable.Creator<USAidDataObject> CREATOR = new Parcelable.Creator<USAidDataObject>() {
        public USAidDataObject createFromParcel(Parcel in) {
            return new USAidDataObject(in);
        }
        
        public USAidDataObject[] newArray(int size) {
            return new USAidDataObject[size];
        }
    };
    
    /* Reconstruct the object from the Parcelable data. */
    private USAidDataObject(Parcel in) {
        
        title = in.readString();
        publishedString = in.readString();
        publishedValue = in.readLong();
        pdfUrl = in.readString();
        thumbnailUrl = in.readString();
        abstractString = in.readString();
        sectorString = in.readString();
        sectorValue = in.readInt();
        regionValue = in.readInt();
        hasRegionArray = in.readByte() == 1;
        
        if (hasRegionArray) {
            
            // read the number of ints
            int arrayLength = in.readInt();
            
            // creat the int array
            regionArray = new int[arrayLength];
            
            for (int i = 0; i < arrayLength; i++) {
                regionArray[i] = in.readInt();
            }
            
        }
        
        countryString = in.readString();
        countryCode = in.readInt();
        hasCountryArray = in.readByte() == 1;
        
        if (hasCountryArray) {
            
            // read the number of ints
            int arrayLength = in.readInt();
            
            // creat the int array
            countryArray = new int[arrayLength];
            
            for (int i = 0; i < arrayLength; i++) {
                countryArray[i] = in.readInt();
            }
            
        }
        
        documentType = in.readString();
        
    }

} // end USAidDataObject
