/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations.io;

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
    
    public String publishedString;
    
    public long publishedValue;
    
    public String pdfUrl;
    
    public String thumbnailUrl;
    
    public String abstractString;
    
    public String sectorString;
    
    public int sectorValue;
    
    public String countryString;
    
    public int countryCode;
    
    public String documentType;

    @Override
    public int describeContents() {
        return 0;
    }

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
        dest.writeString(countryString);
        dest.writeInt(countryCode);
        dest.writeString(documentType);
        
    }
    
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
        countryString = in.readString();
        countryCode = in.readInt();
        documentType = in.readString();
        
    }

} // end USAidDataObject
