package com.demomapas.pjgviewpager;

import com.demomapas.R;
import com.demomapas.adapters.Adp_StatePager_PagerMandamientos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BasePager extends Fragment {
	

	ViewPager pager;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	ViewGroup root = (ViewGroup) inflater.inflate(
			R.layout.lyt_fragment_referencia, null);
	return root;
}

@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		View v = getView();
		pager = (ViewPager) getView().findViewById(R.id.vpag_referencia);

		/** Getting fragment manager */
		final FragmentManager fm = getFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		Adp_StatePager_PagerMandamientos pagerAdapter = new Adp_StatePager_PagerMandamientos(fm, getActivity()
				.getBaseContext());
		

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		
	}
}
