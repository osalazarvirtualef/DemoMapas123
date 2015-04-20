package com.demomapas.pjgviewpager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.demomapas.Constants;
import com.demomapas.ObjetoMandamiento;
import com.demomapas.R;
import com.demomapas.RegisterActivity;
import com.demomapas.R.animator;
import com.demomapas.R.id;
import com.demomapas.R.layout;
import com.demomapas.deviceinfoendpoint.Deviceinfoendpoint;
import com.demomapas.deviceinfoendpoint.model.DeviceInfo;
import com.demomapas.endpoints.EndPointsInicializacion;
import com.google.android.gms.maps.MapsInitializer;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.commandmentService.model.CollectionResponseCommandmentDto;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.R.array;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.JetPlayer.OnJetEventListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
public class MainActivityPager extends FragmentActivity {
	
	public static Stack<Fragment> fragmentos;
	public static Stack<String> elementos;
	public static  CollectionResponseCommandmentDto Mandamientos = null;
	public static ArrayList<CommandmentDto> Mandamientos2 = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> aprehension = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> reaprehension = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> presentacion = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> comparecencia = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> colaboracion = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> traslados = new ArrayList<CommandmentDto>();
	
	ProgressDialog progressDialog;
	static SharedPreferences.Editor editor;
	static SharedPreferences Preferences;
	enum TipoMandamiento { APREHENSION, REAPREHENSION, PRESENTACION, COMPARECENCIA, COLABORACION , TRASLADOS};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MapsInitializer.initialize(getApplicationContext());
		
	/////9	if(savedInstanceState!=null){
	//Log.i("bundle", savedInstanceState.getString("quieras"));
//			ObjetoMandamiento o = savedInstanceState.getParcelable("mandamiento");
//			Log.i("bundle no es null", "bundle no es  nnulo");
//			if(o != null )
//				Log.i("o no es null", "o no es  nnulo");
//				//Mandamientos2 = o.Mandamientos;
//				
//				if(o.Mandamientos != null){
//					Log.i(o.Mandamientos.size()+"", "jyhtgrew");
//				
//					
//				}
//				else
//					Log.i("mandamientos 2 es nulo", "mandamientos 2 es nulo");
//				for (CommandmentDto elementos : Mandamientos2) {
//					Log.i("t"+elementos.getAddress(), "direcciom");
//				}
			
		
//		setContentView(R.layout.activity_main_activity_pager);
//			setTitle("");
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//			ActionBar actionBar = getActionBar();
//			actionBar.setDisplayHomeAsUpEnabled(true);
//
//			Preferences = getApplicationContext().getSharedPreferences(
//					"settings", 0);
//			  progressDialog = new ProgressDialog(MainActivityPager.this);  
//		    //Set the progress dialog to display a horizontal bar .  
//		    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
//		    //Set the dialog title to 'Loading...'.  
//		    progressDialog.setTitle("Obteniendo Mandamientos...");  
//		    //Set the dialog message to 'Loading application View, please wait...'.  
//		    progressDialog.setMessage("Descargando...");  
//		    //This dialog can't be canceled by pressing the back key.  
//		    progressDialog.setCancelable(false);  
//		    //This dialog isn't indeterminate.  
//		    progressDialog.setIndeterminate(false);  
//		    //The maximum number of progress items is 100.  
//		    progressDialog.setMax(100);  
//		    //Set the current progress to zero.  
//		    progressDialog.setProgress(0);  
//		    //Display the progress dialog.  
//		    progressDialog.show(); 
//		    
//		    new ObtenerInformacion(getApplicationContext()).execute();
//	
//		}else{
		setTitle("");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		
		
		setContentView(R.layout.activity_main_activity_pager);
		Preferences = getApplicationContext().getSharedPreferences(
				"settings", 0);
		
		
		
//		Bundle bundle = new Bundle();
	//	
//		ObjetoMandamiento objeto = new ObjetoMandamiento();
//		objeto.Mandamientos = (ArrayList<CommandmentDto>) Mandamientos.getItems();
//		objeto.aprehension =  aprehension;
//		objeto.reaprehension = reaprehension;
//		objeto.presentacion = presentacion;
//		objeto.comparecencia =  comparecencia;
//		objeto.colaboracion = colaboracion;
//		objeto.traslados =  traslados;
//		bundle.putParcelable("prueba", objeto);
//		BasePager paginas = new BasePager();
//		paginas.setArguments(bundle);
//		fragmentos = new Stack<Fragment>();
//		elementos = new Stack<String>();
//		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//		tx.setCustomAnimations(R.animator.enter,
//				R.animator.exit, R.animator.pop_enter,
//				R.animator.pop_exit);
//		tx.replace(R.id.frm_lyt_mainMenu, fragmentos.push(paginas));
//		elementos.push("paginas");
	//	
//		tx.commit();


		  progressDialog = new ProgressDialog(MainActivityPager.this);  
	    //Set the progress dialog to display a horizontal bar .  
	    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
	    //Set the dialog title to 'Loading...'.  
	    progressDialog.setTitle("Obteniendo Mandamientos...");  
	    //Set the dialog message to 'Loading application View, please wait...'.  
	    progressDialog.setMessage("Descargando...");  
	    //This dialog can't be canceled by pressing the back key.  
	    progressDialog.setCancelable(false);  
	    //This dialog isn't indeterminate.  
	    progressDialog.setIndeterminate(false);  
	    //The maximum number of progress items is 100.  
	    progressDialog.setMax(100);  
	    //Set the current progress to zero.  
	    progressDialog.setProgress(0);  
	    //Display the progress dialog.  
	    progressDialog.show(); 
	    
	    new ObtenerInformacion(getApplicationContext()).execute();
	
	//	}
	System.out.println();
	super.onCreate(savedInstanceState);

		
	}
		
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		//outState.putString("quieras", "quieras");
//		Log.i("ciclo de vida", "onsavedinstance");
//		ObjetoMandamiento objeto = new ObjetoMandamiento();
//		objeto.Mandamientos = (ArrayList<CommandmentDto>) Mandamientos.getItems();
//		outState.putParcelable("mandamiento", objeto);
		
		
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("estoy en el onrestore", "estoy en el onrestore");
		
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return MenuSelecciona(item);
	}
	private boolean MenuSelecciona(MenuItem item) {
		switch (item.getItemId()) {

		case 0:
//			Toast.makeText(this, "Has pulsado el Item 1 del Action Bar",
//					Toast.LENGTH_SHORT).show();
			CustomDialogFragment customDialog = new CustomDialogFragment();
			customDialog.show(getSupportFragmentManager(), "prueba");
			return true;

		case 1:
//			Toast.makeText(this, "Has pulsado el Item 2 del Action Bar",
//					Toast.LENGTH_SHORT).show();

			return true;

			// --A���������adimos el caso para cuando se pulse el boton home

		case android.R.id.home:
//			Toast.makeText(this, "Has pulsado el Home del Action Bar",
//					Toast.LENGTH_SHORT).show();

			return true;

		}
		return false;
	}
	
	
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
}
//@Override
//protected void onSaveInstanceState(Bundle outState) {
//	// TODO Auto-generated method stub
//	super.onSaveInstanceState(outState);
//	ObjetoMandamiento objeto = new ObjetoMandamiento();
//	objeto.Mandamientos = (ArrayList<CommandmentDto>) Mandamientos.getItems();
//	objeto.aprehension =  aprehension;
//	objeto.reaprehension = reaprehension;
//	objeto.presentacion = presentacion;
//	objeto.comparecencia =  comparecencia;
//	objeto.colaboracion = colaboracion;
//	objeto.traslados =  traslados;
//	outState.putParcelable("prueba", objeto);
//
//}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	CrearMenu(menu);
	return super.onCreateOptionsMenu(menu);

    
}

private void CrearMenu(Menu menu) {

	
	
//	MenuItem item = menu.add("Search");
//	SearchView sv = new SearchView(getActionBar().getThemedContext());
//	item.setActionView(sv);
//	item.setIcon(R.drawable.ic_search);
//	item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
//	        | MenuItem.SHOW_AS_ACTION_IF_ROOM);
	MenuItem item1 = menu.add(0, 0, 0, "Item 1");
	{
		// Copio las imagenes que van en cada item
		//SearchView sv = new SearchView(getActionBar().getThemedContext());
		item1.setIcon(R.drawable.telefono);
		//item1.setActionView(sv);
		item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		//item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
		 //       | MenuItem.SHOW_AS_ACTION_IF_ROOM);
	
	//	  android:actionViewClass="android.widget.SearchView"/>
		
	}

//	MenuItem item2 = menu.add(0, 1, 1, "Item 2");
//	{
//		item2.setIcon(R.drawable.camara);
//		item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//	}

}
@Override
protected void onResume() {
	// TODO Auto-generated method stub

		
	super.onResume();
}
//@Override
//public void onBackPressed() {
//	// TODO Auto-generated method stub
//	if(elementos.size()>1)
//	{
//		fragmentos.pop();
//		elementos.pop();
//		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//		
//		
//		tx.setCustomAnimations(R.animator.enter,
//				R.animator.exit, R.animator.pop_enter,
//				R.animator.pop_exit);
//		if(elementos.peek().equals("paginas")){
//			BasePager base = new BasePager();
//		tx.replace(R.id.frm_lyt_mainMenu,base) ;
//		}
//		
//		
//		tx.commit();
//	}
//	else
//	super.onBackPressed();
//}


public class ObtenerInformacion extends AsyncTask<Void, Void, Void> {

	Context context;


	private ObtenerInformacion(Context context) {
        this.context = context.getApplicationContext();
    }
	@Override
	protected Void doInBackground(Void... params) {
		EndPointsInicializacion endpoints = new EndPointsInicializacion();
		CommandmentService MandamientoEndpoint = null;
		if(Mandamientos==null)
		{
		MandamientoEndpoint = endpoints.InicializacionMandamiento();
		try {
			Log.i("entre el try", "entre al try");

			Mandamientos = MandamientoEndpoint.getCommandmentByAgentId(Preferences.getLong(Constants.idAgente,0L)).execute();
			
			for (CommandmentDto elementos : Mandamientos.getItems()) {
				if(elementos.getCommandmentType().equalsIgnoreCase("APREHENSION")){
					aprehension.add(elementos);
					Log.i("aprehension asignada", "aprehension asignada");
					
				}else if(elementos.getCommandmentType().equalsIgnoreCase("REAPREHENSION")){
					
					reaprehension.add(elementos);
					Log.i("reaprehension asignada", "reaprehension asignada");
				}else if(elementos.getCommandmentType().equalsIgnoreCase("PRESENTACION")){
					presentacion.add(elementos);
					Log.i("presentacion asignada", "presentacion asignada");
				}else if(elementos.getCommandmentType().equalsIgnoreCase("COMPARECENCIA")){
					comparecencia.add(elementos);
					Log.i("comparecencia asignada", "comparecencia asignada");
				}else if(elementos.getCommandmentType().equalsIgnoreCase("COLABORACION")){
					colaboracion.add(elementos);
					Log.i("colaboracion asignada", "colaboracion asignada");
				}else if(elementos.getCommandmentType().equalsIgnoreCase("TRASLADOS")){
					traslados.add(elementos);
					Log.i("traslados asignada", "traslados asignada");
				}
				System.out.println();
				
			}
		
			Log.i("", "");
			
			 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("entre al catch", "entre al cath");
		
			
		}
		}
			

		return null;
	}
	@Override
	protected void onPostExecute(Void result) {

		
		super.onPostExecute(result);
		Bundle bundle = new Bundle();
		
		ObjetoMandamiento objeto = new ObjetoMandamiento();
		objeto.Mandamientos = (ArrayList<CommandmentDto>) Mandamientos.getItems();
//		objeto.aprehension =  aprehension;
//		objeto.reaprehension = reaprehension;
//		objeto.presentacion = presentacion;
//		objeto.comparecencia =  comparecencia;
//		objeto.colaboracion = colaboracion;
//		objeto.traslados =  traslados;
		bundle.putParcelable("prueba", objeto);
		BasePager paginas = new BasePager();
		paginas.setArguments(bundle);
		fragmentos = new Stack<Fragment>();
		elementos = new Stack<String>();
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.setCustomAnimations(R.animator.enter,
				R.animator.exit, R.animator.pop_enter,
				R.animator.pop_exit);
		tx.replace(R.id.frm_lyt_mainMenu, fragmentos.push(paginas));
	
		elementos.push("paginas");
		Log.i("on post execute", "on post execute");
		
		tx.commit();
		progressDialog.dismiss();
	}

}
}
