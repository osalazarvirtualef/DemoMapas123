package com.demomapas;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;





public class mapa extends Fragment implements OnMarkerClickListener {
	SupportMapFragment fragment;
	    private GoogleMap map;
	    float zooom = 4;
	    ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();

	public mapa() {
		// TODO Auto-generated constructor stub
	
	}

	

	public mapa(ArrayList<MarkerOptions> mMarkers) {
		// TODO Auto-generated constructor stub
	//	markers = mMarkers;
	}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		

		return inflater.inflate(R.layout.activity_main,null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
		fragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.frag_ubicacion3);
		if (map == null) {
		//map =((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.frag_ubicacion)).getMap();
		}
		map = fragment.getMap();
		CameraUpdate center=
		        CameraUpdateFactory.newLatLng(new LatLng(19.423753, -99.149311));
	//	    CameraUpdate zoom=CameraUpdateFactory.zoomTo(zooom);
		    map.moveCamera(center);
	//	    map.animateCamera(zoom);
		        
		    	map.setMyLocationEnabled(true);
				map.setOnMarkerClickListener((OnMarkerClickListener) this);
		
		for (MarkerOptions markerOptions : markers) {
			map.addMarker(markerOptions);
		}
	
//		mMap=  mMapFragment.getMap();
//		for (MarkerOptions markerOptions : mMarkersArrayList) {
//			mMap.addMarker(markerOptions);
//		}
//		
//		
		
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		
 	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
	
		
	
	    super.onSaveInstanceState(outState);
	}
	public void onDestroyView() {
	    super.onDestroyView();
	    Log.e("entre al ondestroyview", "entre al ondestroyview");
	    FragmentManager fm = getActivity().getSupportFragmentManager();
	    Fragment fragment = (fm.findFragmentById(R.id.frag_ubicacion3));
	    FragmentTransaction ft = fm.beginTransaction();
	    ft.remove(fragment);
	    ft.commit();
	
	}
@Override
public void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
 
}


	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
		Log.i("el valor del marker es", marker.getTitle());
		if(zooom < 12)
		zooom += 4;
		  new Handler().postDelayed(new Runnable() {

	            @Override
	            public void run() {
	                map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), zooom));
	            }
	        }, 300);
		return false;
	}
}
