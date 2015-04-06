package com.demomapas;

import com.google.android.gms.maps.SupportMapFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Adp_ViewPager extends FragmentStatePagerAdapter{

	public Adp_ViewPager(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0) {
		case 0:
			return SupportMapFragment.newInstance();
		case 1:
			return new Pagina1();
		
		default:
			break;
		}
		
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		CharSequence ch = null;
		switch (position) {
		case 0:
			ch = "Vista Mapa";
			break;
		case 1:
			ch = "Vista Listado";
			break;


		default:
			break;
			
		}
		return ch;
	}
}
