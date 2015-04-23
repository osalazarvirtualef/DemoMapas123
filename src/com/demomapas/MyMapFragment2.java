package com.demomapas;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment2 extends SupportMapFragment {

    private List<MarkerOptions> mMarkers;

    public static MyMapFragment2 create(GoogleMapOptions options, ArrayList<MarkerOptions> markers) {
        MyMapFragment2 fragment = new MyMapFragment2();

        Bundle args = new Bundle();
        args.putParcelable("MapOptions", options); //obtained by decompiling google-play-services.jar
        args.putParcelableArrayList("markers", markers);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hacky and ugly but it works
        Log.i("oncreatedel mapa", "oncreatedel mapa");
        ArrayList<Parcelable> list = getArguments().getParcelableArrayList("markers");
        if(list==null)Log.i("viene nulo", "viene nulo");
        mMarkers = new ArrayList<MarkerOptions>(list.size());
        Log.i("mmarkers tienen un tama��o de:", list.size()+"");
        for (Parcelable parcelable : list) {
            mMarkers.add((MarkerOptions) parcelable);
            Log.i("se agrefgo el mmarker ","dfvdsdsv");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onresume del mapa", "onresume del mapa");
         GoogleMap mMap = super.getMap();
        Log.i("el mmap es", mMap.toString());
        //add the markers
        if (mMap != null) {
            for (final MarkerOptions marker : mMarkers) {
            	   mMap.addMarker(marker);
           
            }
        }
    }
    
    
   @Override
public void onDestroyView() {
	// TODO Auto-generated method stub
	super.onDestroyView();
	
}
    @Override
    public void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	Log.i("ondestroy", "ondestroy");
    }
}
