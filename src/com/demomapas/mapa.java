package com.demomapas;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;





public class mapa extends Fragment{
	  private MapFragment fragment;
	    private GoogleMap map;

	public mapa() {
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.mapa,container,false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
//		mMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.frag_ubicacion);
//		mMap=  mMapFragment.getMap();
//		for (MarkerOptions markerOptions : mMarkersArrayList) {
//			mMap.addMarker(markerOptions);
//		}
		
		
		
	}
}
