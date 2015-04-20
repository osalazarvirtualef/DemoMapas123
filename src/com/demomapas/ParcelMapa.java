package com.demomapas;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelMapa implements Parcelable{
	public List<MapaPojo> mapasElementos;
	
	
	public ParcelMapa(Parcel in){
		ArrayList<MapaPojo> products = new ArrayList<MapaPojo>();
		in.readList(products,null);
		
	}
	public ParcelMapa() {
		// TODO Auto-generated constructor stub
		mapasElementos = new ArrayList<MapaPojo>();
	}
	  // Method to recreate a Question from a Parcel
    public static Creator<ParcelMapa> CREATOR = new Creator<ParcelMapa>() {

        @Override
        public ParcelMapa createFromParcel(Parcel source) {
            return new ParcelMapa(source);
        }

        @Override
        public ParcelMapa[] newArray(int size) {
            return new ParcelMapa[size];
        }

    };

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeList(mapasElementos);
		
	}



}
