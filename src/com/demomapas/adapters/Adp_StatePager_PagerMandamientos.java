package com.demomapas.adapters;



import java.util.ArrayList;

import com.demomapas.Constants;
import com.demomapas.MyMapFragment;
import com.demomapas.ObjetoMandamiento;
import com.demomapas.pjgviewpager.Frag_Listados;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Adp_StatePager_PagerMandamientos extends FragmentStatePagerAdapter {

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
			retorna = new MyMapFragment();
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
