package com.demomapas.adapters;



import java.util.ArrayList;
import java.util.List;

import com.demomapas.Constants;
import com.demomapas.MyMapFragment;
import com.demomapas.MyMapFragment2;
import com.demomapas.ObjetoMandamiento;
import com.demomapas.R;
import com.demomapas.pjgviewpager.Frag_Listados;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.R.array;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class Adp_StatePager_PagerMandamientos extends FragmentStatePagerAdapter{

	final int PAGE_COUNT = 7;
	int Tipo;
	int Padre;
	Context Contexto;
//ObjetoMandamiento objeto;
	/** Constructor of the class */
	public Adp_StatePager_PagerMandamientos(FragmentManager fm, Context contexto) {

		super(fm);
		this.Contexto = contexto;
	//	this.objeto = objeto;
//		this.Tipo = tipo;
//		this.Padre = padre;
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int position) {
		Bundle bundle = new Bundle();
		Fragment retorna = null;
		switch (position) {
		case 0:
			ArrayList<Marker> marcadores = new ArrayList<Marker>();
			GoogleMapOptions gmo = new GoogleMapOptions();

		SupportMapFragment googleMap = SupportMapFragment.newInstance(gmo);
			GoogleMap gMap = googleMap.getMap();
			
//			for (CommandmentDto item : MainActivityPager.Mandamientos.getItems()) {
//				GoogleMap gMap = googleMap.getMap();
//				googleMap.getMap().addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(), item.getLongitude())).visible(true));
//				
//			}
			
			
			gmo.camera(new CameraPosition(new LatLng(19.410184, -99.046524), 12, 10, 0));
//			GoogleMap googlemap = SupportMapFragment.newInstance(gmo)
			//retorna =  SupportMapFragment.newInstance(gmo);
			 ArrayList<MarkerOptions> mMarkers = new ArrayList<MarkerOptions>();
			Log.i("mandamientos no viene vacio creare el mapa", "mandamientos no viene vaciog");
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
			MyMapFragment2 mapa = new MyMapFragment2().create(null, mMarkers);
			//retorna = new MyMapFragment(MainActivityPager.Mandamientos);
//			SupportMapFragment me = new SupportMapFragment();
//			me.getMap().addMarker(new MarkerOptions().position(new LatLng(19.400159, -99.021714)));
			retorna = mapa;
			break;
			
		case 1:
			//Frag_Listados lista = new Frag_Listados();
	Frag_Listados lista = new Frag_Listados();
			bundle.putInt("Tipo", Constants.OrdenesAprehension);
			lista.setArguments(bundle);
			retorna = lista;
			
			break;
		case 2:
			
			Frag_Listados lista1 = new Frag_Listados();
			bundle.putInt("Tipo", Constants.OrdenesReaprehension);
			lista1.setArguments(bundle);
			retorna = lista1;
			break;
		case 3:
			Frag_Listados lista3 = new Frag_Listados();
			bundle.putInt("Tipo", Constants.OrdenesPresentacion);
			lista3.setArguments(bundle);
			retorna = lista3;
			break;
		case 4:
			Frag_Listados lista4 = new Frag_Listados();
			bundle.putInt("Tipo", Constants.OrdenesComparecencia);

			lista4.setArguments(bundle);
			retorna = lista4;
			break;
		case 5:
			Frag_Listados lista5 = new Frag_Listados();
			bundle.putInt("Tipo", Constants.OficiosColaboracion);

			lista5.setArguments(bundle);
			retorna = lista5;
			break;
		case 6:
			Frag_Listados lista6 = new Frag_Listados();
			bundle.putInt("Tipo", Constants.Traslados);
			lista6.setArguments(bundle);
			retorna = lista6;
			break;
		default:
			break;
		}

		return retorna;

	}

	/** Returns the number of pages */
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		CharSequence Titulo = null;
		switch (position) {
	
		
		case 0:
			Titulo = "Ubicaciones";
			break;
		case 1:
		Titulo="Aprehensión";

			break;
		case 2:
			Titulo = "Reaprehensión";
			break;
		case 3:
			Titulo = "Presentación";
			break;
		case 4:
			Titulo = "Comparecencía";
			break;
		case 5:
			Titulo = "Colaboración";
			break;
		case 6:
			Titulo = "Traslados";
			break;
		}
		return Titulo;
	}

}
