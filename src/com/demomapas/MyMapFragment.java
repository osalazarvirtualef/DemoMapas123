package com.demomapas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.demomapas.pjgviewpager.MainActivityPager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

public class MyMapFragment extends SupportMapFragment implements OnMarkerClickListener {
	 GoogleMap mMap;
	 float zooom = 4;
	 public static ObjetoMandamiento objeto;

    private List<MarkerOptions> mMarkers;


    public MyMapFragment(ObjetoMandamiento objeto2) {
		// TODO Auto-generated constructor stub
    	this.objeto = objeto2;
	}

    public MyMapFragment() {
		// TODO Auto-generated constructor stub
	}
	public static MyMapFragment create(GoogleMapOptions options, ArrayList<MarkerOptions> markers) {
        MyMapFragment fragment = new MyMapFragment();

        Bundle args = new Bundle();
//        args.putParcelable("MapOptions", options); //obtained by decompiling google-play-services.jar
//        args.putParcelableArrayList("markers", markers);
        
    //    args.putParcelable("objeto",objeto);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hacky and ugly but it works
    //    ArrayList<Parcelable> list = getArguments().getParcelableArrayList("markers");
        if(savedInstanceState!=null)
        //objeto = getArguments().getParcelable("prueba");
    //    	objeto = savedInstanceState.getParcelable("prueba");
        try {
        //	Log.i("mandamientos", MainActivityPager.Mandamientos.getItems().size()+"");	
     //   	Log.i("mandamientos", objeto.Mandamientos.size()+"");
		} catch (Exception e) {
			// TODO: handle exception
			
		}
        
 //       mMarkers = new ArrayList<MarkerOptions>(objeto.Mandamientos.size());
        mMarkers = new ArrayList<MarkerOptions>(MainActivityPager.Mandamientos.size());
    //    for (Parcelable parcelable : list) {
        ImageView icono  = new ImageView(getActivity());

        
        
     //   for (CommandmentDto elementos : objeto.Mandamientos) {
        for (CommandmentDto elementos : MainActivityPager.Mandamientos.getItems()) {
        	if(elementos.getStatus()){
        		LatLng a = new LatLng(elementos.getLatitude(), elementos.getLongitude());
        		String name = elementos.getRequire().getPerson().getFirstName();
        		String firstname = elementos.getRequire().getPerson().getFirstName();
        		String lastname = elementos.getRequire().getPerson().getFirstName();
        		mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison)));
        	}else{
        		LatLng z = new LatLng(elementos.getLatitude(), elementos.getLongitude());
    		String name = elementos.getRequire().getPerson().getFirstName();
    		String firstname = elementos.getRequire().getPerson().getFirstName();
    		String lastname = elementos.getRequire().getPerson().getFirstName();
        		
        	mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison2)));
        	}
        	
		}
            
      //  }
    }
    
  
// @Override
//public void onSaveInstanceState(Bundle outState) {
//	// TODO Auto-generated method stub
//	 
//	 
//	
//	super.onSaveInstanceState(outState);
//	ObjetoMandamiento objeto = new ObjetoMandamiento();
//
//	outState.putParcelable("prueba", MyMapFragment.objeto);
//	
//}
    
    @Override
    public void onResume() {
        super.onResume();
         mMap = super.getMap();
		CameraUpdate center=
        CameraUpdateFactory.newLatLng(new LatLng(19.423753, -99.149311));
    CameraUpdate zoom=CameraUpdateFactory.zoomTo(zooom);
    mMap.moveCamera(center);
    mMap.animateCamera(zoom);
        
    	mMap.setMyLocationEnabled(true);
		mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        
        
        //add the markers
        if (mMap != null) {
            for (MarkerOptions marker : mMarkers) {
                mMap.addMarker(marker);
            }
        }
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
