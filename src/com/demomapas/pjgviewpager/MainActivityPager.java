package com.demomapas.pjgviewpager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import com.demomapas.Constants;
import com.demomapas.R;
import com.demomapas.RegisterActivity;
import com.demomapas.R.animator;
import com.demomapas.R.id;
import com.demomapas.R.layout;
import com.demomapas.deviceinfoendpoint.Deviceinfoendpoint;
import com.demomapas.deviceinfoendpoint.model.DeviceInfo;
import com.demomapas.endpoints.EndPointsInicializacion;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.commandmentService.model.CollectionResponseCommandmentDto;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivityPager extends FragmentActivity {
	
	public static Stack<Fragment> fragmentos;
	public static Stack<String> elementos;
	public static  CollectionResponseCommandmentDto Mandamientos;
	public static ArrayList<CommandmentDto> aprehension;
	public static ArrayList<CommandmentDto> reaprehension;
	public static ArrayList<CommandmentDto> presentacion;
	public static ArrayList<CommandmentDto> comparecencia;
	public static ArrayList<CommandmentDto> colaboracion;
	public static ArrayList<CommandmentDto> traslados;
	
	ProgressDialog progressDialog;
	static SharedPreferences.Editor editor;
	static SharedPreferences Preferences;
	enum TipoMandamiento { APREHENSION, REAPREHENSION, PRESENTACION, COMPARECENCIA, COLABORACION , TRASLADOS};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Policía de Investigación");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		aprehension = new ArrayList<CommandmentDto>();
		reaprehension = new ArrayList<CommandmentDto>();
		presentacion = new ArrayList<CommandmentDto>();
		comparecencia = new ArrayList<CommandmentDto>();
		colaboracion = new ArrayList<CommandmentDto>();
		traslados = new ArrayList<CommandmentDto>();
		
		
		setContentView(R.layout.activity_main_activity_pager);
		Preferences = getApplicationContext().getSharedPreferences(
				"settings", 0);
		
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
		
		
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	if(elementos.size()>1)
	{
		fragmentos.pop();
		elementos.pop();
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		
		
		tx.setCustomAnimations(R.animator.enter,
				R.animator.exit, R.animator.pop_enter,
				R.animator.pop_exit);
		if(elementos.peek().equals("paginas")){
			BasePager base = new BasePager();
		tx.replace(R.id.frm_lyt_mainMenu,base) ;
		}
		
		
		tx.commit();
	}
	else
	super.onBackPressed();
}


public class ObtenerInformacion extends AsyncTask<Void, Void, Void> {

	Context context;


	private ObtenerInformacion(Context context) {
        this.context = context.getApplicationContext();
    }
	@Override
	protected Void doInBackground(Void... params) {
		EndPointsInicializacion endpoints = new EndPointsInicializacion();
		CommandmentService MandamientoEndpoint;
		MandamientoEndpoint = endpoints.InicializacionMandamiento();
		try {

			Mandamientos = MandamientoEndpoint.getCommandmentByAgentId(Preferences.getLong(Constants.idAgente,0L)).execute();
			
			for (CommandmentDto elementos : Mandamientos.getItems()) {
				if(elementos.getCommandmentType().equalsIgnoreCase("APREHENSION")){
					aprehension.add(elementos);
					
				}else if(elementos.getCommandmentType().equalsIgnoreCase("REAPREHENSION")){
					reaprehension.add(elementos);
				}else if(elementos.getCommandmentType().equalsIgnoreCase("PRESENTACION")){
					presentacion.add(elementos);
				}else if(elementos.getCommandmentType().equalsIgnoreCase("COMPARECENCIA")){
					comparecencia.add(elementos);
				}else if(elementos.getCommandmentType().equalsIgnoreCase("COLABORACION")){
					colaboracion.add(elementos);
				}else if(elementos.getCommandmentType().equalsIgnoreCase("TRASLADOS")){
					traslados.add(elementos);
				}
				System.out.println();
				
			}
			Log.i("", "");
			 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}

		return null;
	}
	@Override
	protected void onPostExecute(Void result) {

		
		super.onPostExecute(result);
		BasePager paginas = new BasePager();
		fragmentos = new Stack<Fragment>();
		elementos = new Stack<String>();
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.setCustomAnimations(R.animator.enter,
				R.animator.exit, R.animator.pop_enter,
				R.animator.pop_exit);
		tx.replace(R.id.frm_lyt_mainMenu, fragmentos.push(paginas));
		elementos.push("paginas");
		
		tx.commit();
		progressDialog.dismiss();
	}

}
}
