package com.demomapas;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment2 extends SupportMapFragment implements OnMarkerClickListener
{

    private List<MarkerOptions> mMarkers;
    private ArrayList<Boolean> markerstatus;
    boolean [] markerstado;
    float zooom = 4;

    public static MyMapFragment2 create(GoogleMapOptions options, ArrayList<MarkerOptions> markers, ArrayList<Boolean> markerstatus) {
        MyMapFragment2 fragment = new MyMapFragment2();
        boolean [] status = new boolean[markerstatus.size()];
        for(int r=0 ; r< markerstatus.size() ; r++){
        	status[r]=markerstatus.get(r);
        }
        Bundle args = new Bundle();
        args.putParcelable("MapOptions", options); //obtained by decompiling google-play-services.jar
        args.putParcelableArrayList("markers", markers);
        args.putBooleanArray("markerstatus", status);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hacky and ugly but it works
        Log.i("oncreatedel mapa", "oncreatedel mapa");
      markerstado = getArguments().getBooleanArray("markerstatus");
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
     	CameraUpdate center=
     	        CameraUpdateFactory.newLatLng(new LatLng(19.423753, -99.149311));
     	    CameraUpdate zoom=CameraUpdateFactory.zoomTo(zooom);
     	    mMap.moveCamera(center);
     	    mMap.animateCamera(zoom);
     	        
     	    	mMap.setMyLocationEnabled(true);
     			mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
         
         
        Log.i("el mmap es", mMap.toString());
        //add the markers
        if (mMap != null) {
        	for (int m = 0 ; m<markerstado.length ;m++) {
				
        		if(markerstado[m]==false){
        			mMarkers.get(m).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        			
        		}else
        			mMarkers.get(m).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        		  mMap.addMarker(mMarkers.get(m));
			}
//            for (final MarkerOptions marker : mMarkers) {
//            	marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.prison));
//            	   mMap.addMarker(marker);
//           
//            }
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
    
	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
		Log.i("el valor del marker es", marker.getTitle());
		if(zooom < 12)
		zooom += 4;
		  new Handler().postDelayed(new Runnable() {

	            @Override
	            public void run() {
	                getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), zooom));
	            }
	        }, 300);
		
//		CameraUpdate center= CameraUpdateFactory.newLatLng(marker.getPosition());
//	       CameraUpdate zoom=CameraUpdateFactory.zoomTo(18);
//	       mMap.moveCamera(center);
//	       mMap.animateCamera(zoom);
		return false;
	}
}
