package com.demomapas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.media.JetPlayer.OnJetEventListener;
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
import com.virtualef.pgj.service.commandmentService.model.CollectionResponseCommandmentDto;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

public class MyMapFragment extends SupportMapFragment implements OnMarkerClickListener {
	 GoogleMap mMap;
	 float zooom = 4;
     public static ArrayList<MapaPojo> mapaSettings  = new ArrayList<MapaPojo>();
     
	 ArrayList<CommandmentDto> mandamientos;

    private List<MarkerOptions> mMarkers;



    public MyMapFragment() {
		// TODO Auto-generated constructor stub
	}
	public MyMapFragment(CollectionResponseCommandmentDto mandamientos) {
		// TODO Auto-generated constructor stub
		this.mandamientos = (ArrayList<CommandmentDto>) mandamientos.getItems();
	}

	public static MyMapFragment create(GoogleMapOptions options, ArrayList<MarkerOptions> markers, CollectionResponseCommandmentDto mandamientos2) {
        MyMapFragment fragment = new MyMapFragment();

        Bundle args = new Bundle();
  
        Log.i("este es el log que quiero ver 1", "este es el log que quiero ver 1");
        //ObjetoMandamiento object = new ObjetoMandamiento();
        
        //object.Mandamientos = mandamientos2.getItems();
       // args.putParcelable("mandamientos", object);
//        args.putParcelable("MapOptions", options); //obtained by decompiling google-play-services.jar
//        args.putParcelableArrayList("markers", markers);
        
    //    args.putParcelable("objeto",objeto);
        
        
//        for (CommandmentDto elementos : MandamientosGennnerales) {
//        	if(elementos.getStatus()){
//        		LatLng a = new LatLng(elementos.getLatitude(), elementos.getLongitude());
//        		String name = elementos.getRequire().getPerson().getFirstName();
//        		String firstname = elementos.getRequire().getPerson().getFirstName();
//        		String lastname = elementos.getRequire().getPerson().getFirstName();
//        		mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison)));
//        	}else{
//        		LatLng z = new LatLng(elementos.getLatitude(), elementos.getLongitude());
//    		String name = elementos.getRequire().getPerson().getFirstName();
//    		String firstname = elementos.getRequire().getPerson().getFirstName();
//    		String lastname = elementos.getRequire().getPerson().getFirstName();
//        		
//        	mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison2)));
//        	}
        	MapaPojo mapa = new MapaPojo();
        for (CommandmentDto elementos : mandamientos2.getItems()) {
        
        	mapa.setLatitude(elementos.getLatitude());
        	mapa.setLongitud(elementos.getLongitude());
        	mapa.setName( elementos.getRequire().getPerson().getFirstName());
        	mapa.setFirstName(elementos.getRequire().getPerson().getFirstName());
        	mapa.setLastname( elementos.getRequire().getPerson().getFirstName());
        	mapa.setTipo(elementos.getCommandmentType());
        	if(elementos.getStatus())
        		mapa.setStatus(true);
        	else
        		mapa.setStatus(false);
        	mapaSettings.add(mapa);
		}
        ParcelMapa parcelMapas = new ParcelMapa();
        parcelMapas.mapasElementos = mapaSettings;
        args.putParcelable("mandamientos", parcelMapas);
        
        
        
        
        
        
        
        
        
        
        Log.i("create del mapa", "create del mapa");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("este es el log que quiero ver 3", "este es el log que quiero ver 3");
        // Hacky and ugly but it works
    //    ArrayList<Parcelable> list = getArguments().getParcelableArrayList("markers");
//        ParcelMapa object = getArguments().getParcelable("mandamientos");
////        if(savedInstanceState!=null)
//      //  ArrayList<CommandmentDto> MandamientosGennnerales = (ArrayList<CommandmentDto>) object.Mandamientos;
////        //objeto = getArguments().getParcelable("prueba");
////    //    	objeto = savedInstanceState.getParcelable("prueba");
////        try {
////        //	Log.i("mandamientos", MainActivityPager.Mandamientos.getItems().size()+"");	
////     //   	Log.i("mandamientos", objeto.Mandamientos.size()+"");
////		} catch (Exception e) {
////			// TODO: handle exception
////			
////		}
////        
// //       mMarkers = new ArrayList<MarkerOptions>(objeto.Mandamientos.size());
//        
//        ArrayList<MapaPojo> elementosMarker = (ArrayList<MapaPojo>) object.mapasElementos;
//        mMarkers = new ArrayList<MarkerOptions>(elementosMarker.size());
//        for (MapaPojo mapaPojo : elementosMarker) {
//        	if(mapaPojo.isStatus())
//        		
//        		mMarkers.add(new MarkerOptions().position(new LatLng(mapaPojo.getLatitude(), mapaPojo.getLongitud())).title(mapaPojo.getName()+" "+mapaPojo.getFirstName()+" "+mapaPojo.getLastname()+"   '"+mapaPojo.getTipo()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison)));
//        	else
//        		mMarkers.add(new MarkerOptions().position(new LatLng(mapaPojo.getLatitude(), mapaPojo.getLongitud())).title(mapaPojo.getName()+" "+mapaPojo.getFirstName()+" "+mapaPojo.getLastname()+"   '"+mapaPojo.getTipo()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison2)));
//		}aquiiii
        
//        if(MandamientosGennnerales == null)
//        	Log.i("mandamientos generales viene vacio", "mandamientos generales viene vacio");
//        Log.i("mandamientos generales size es:", MandamientosGennnerales.size()+"");
//      
//    //    for (Parcelable parcelable : list) {
//        ImageView icono  = new ImageView(getActivity());
//
//        
//        
//     //   for (CommandmentDto elementos : objeto.Mandamientos) {
//        for (CommandmentDto elementos : MandamientosGennnerales) {
//        	if(elementos.getStatus()){
//        		LatLng a = new LatLng(elementos.getLatitude(), elementos.getLongitude());
//        		String name = elementos.getRequire().getPerson().getFirstName();
//        		String firstname = elementos.getRequire().getPerson().getFirstName();
//        		String lastname = elementos.getRequire().getPerson().getFirstName();
//        		mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison)));
//        	}else{
//        		LatLng z = new LatLng(elementos.getLatitude(), elementos.getLongitude());
//    		String name = elementos.getRequire().getPerson().getFirstName();
//    		String firstname = elementos.getRequire().getPerson().getFirstName();
//    		String lastname = elementos.getRequire().getPerson().getFirstName();
//        		
//        	mMarkers.add(new MarkerOptions().position(new LatLng(elementos.getLatitude(), elementos.getLongitude())).title(elementos.getRequire().getPerson().getName()+" "+elementos.getRequire().getPerson().getFirstName()+" "+elementos.getRequire().getPerson().getLastName()+"   '"+elementos.getCommandmentType()+"'").icon(BitmapDescriptorFactory.fromResource(R.drawable.prison2)));
//        	}
//        	
//		}
            
      //  }
    }
    
  
// @Override
//public void onSaveInstanceState(Bundle outState) {
//	// TODO Auto-generated method stub
////	 
////	 
////	
//	
//	 super.onSaveInstanceState(outState);
//	 ParcelMapa guardar = new ParcelMapa();
//	 guardar.mapasElementos = mapaSettings;
////	outState.putParcelable("mandamientos", guardar);
////	ObjetoMandamiento objeto = new ObjetoMandamiento();
////
////	outState.putParcelable("prueba", MyMapFragment.objeto);
////	
//}
    
    @Override
    public void onResume() {
        super.onResume();
        Log.i("este es el log que quiero ver 2", "este es el log que quiero ver 2");
         mMap = super.getMap();
		CameraUpdate center=
        CameraUpdateFactory.newLatLng(new LatLng(19.423753, -99.149311));
    CameraUpdate zoom=CameraUpdateFactory.zoomTo(zooom);
    mMap.moveCamera(center);
    mMap.animateCamera(zoom);
        
    	mMap.setMyLocationEnabled(true);
		mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        
        
        //add the markers
//        if (mMap != null) {
//            for (MarkerOptions marker : mMarkers) {
//                mMap.addMarker(marker);
//            }
//        }
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
