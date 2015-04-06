package com.demomapas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("InflateParams")
public class FragmentPrincipal extends Fragment{

	private ViewPager viewPager;
	private Adp_ViewPager adapter;
	private FragmentManager fm;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		fm = getFragmentManager();
		adapter = new Adp_ViewPager(fm);
		
		viewPager = (ViewPager) getView().findViewById(R.id.view_pager);
		viewPager.setAdapter(adapter);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return (View) inflater.inflate(R.layout.lyt_fragmentprincipal, null);
	}
}
