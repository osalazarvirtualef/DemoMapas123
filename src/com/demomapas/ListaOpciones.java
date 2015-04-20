package com.demomapas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Stack;

import com.demomapas.R.color;
import com.demomapas.endpoints.EndPointsInicializacion;
import com.demomapas.pjgviewpager.BasePager;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.virtualef.pgj.service.agentService.AgentService;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.commandmentService.model.CollectionResponseCommandmentDto;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListaOpciones extends Activity implements android.view.View.OnClickListener {

	RelativeLayout principal;
	LinearLayout lista;
	ProgressDialog progressDialog;
	static SharedPreferences.Editor editor;
	static SharedPreferences Preferences;
	
	public static  CollectionResponseCommandmentDto Mandamientos;
	public static ArrayList<CommandmentDto> aprehension = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> reaprehension = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> presentacion = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> comparecencia = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> colaboracion= new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> traslados = new ArrayList<CommandmentDto>();
	public static ArrayList<CommandmentDto> listprueba = new ArrayList<CommandmentDto>();

	
	//localizacion 

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_opciones);
		setTitle("");
	    Preferences = getApplicationContext().getSharedPreferences(
				"settings", 0);
		//inicializamos los layouts
		principal =  (RelativeLayout) findViewById(R.id.RelativeListaOpciones);
		lista =  (LinearLayout) findViewById(R.id.ListaElementos);
		principal.setBackgroundColor(Color.BLACK);
		lista.setBackgroundColor(Color.BLACK);
	
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
	    //llp.setMargins(0, 30, 0, 0);
		
		
		Button MandamientosJudiaciales = (Button) findViewById(R.id.Mandamientos);
		//Button MandamientosJudiaciales = new Button(getApplicationContext());
//		MandamientosJudiaciales.setText("Mandamientos Judiciales");
		MandamientosJudiaciales.setTextSize(20);
		MandamientosJudiaciales.setGravity(Gravity.LEFT);
		MandamientosJudiaciales.setGravity(Gravity.CENTER_VERTICAL);
		MandamientosJudiaciales.setOnClickListener(this);
		MandamientosJudiaciales.setTextColor(Color.BLACK);
//		MandamientosJudiaciales.setBackgroundColor(Color.WHITE);
//		MandamientosJudiaciales.setId(Constants.MandamientosJudiciales);
//		MandamientosJudiaciales.setPadding(0, 5, 0, 5);
//	
		
		Button Documentos = (Button) findViewById(R.id.Documentos);
//		Documentos.setLayoutParams(llp);
//		Documentos.setText("Documentos");
		Documentos.setTextSize(20);
		Documentos.setGravity(Gravity.CENTER);
		Documentos.setGravity(Gravity.CENTER_VERTICAL);
		Documentos.setOnClickListener(this);
		Documentos.setTextColor(Color.BLACK);
//		Documentos.setId(Constants.Documentos);
//		Documentos.setBackgroundColor(Color.WHITE);
		
		
		Button Emergencias = (Button) findViewById(R.id.Emergencias);
//		Emergencias.setText("Emergencias");
//		Emergencias.setLayoutParams(llp);
		Emergencias.setTextSize(20);
		Emergencias.setGravity(Gravity.CENTER);
		Emergencias.setGravity(Gravity.CENTER_VERTICAL);
		Emergencias.setOnClickListener(this);
		Emergencias.setTextColor(Color.BLACK);
//		Emergencias.setId(Constants.Emergencias);
//		Emergencias.setBackgroundColor(Color.WHITE);
		
		
		Button TransmitirBitacora = (Button) findViewById(R.id.Transmitir);
//		TransmitirBitacora.setText(" Transmitir Bitácoras");
//		TransmitirBitacora.setLayoutParams(llp);
		TransmitirBitacora.setTextSize(20);
		TransmitirBitacora.setGravity(Gravity.CENTER);
		TransmitirBitacora.setGravity(Gravity.CENTER_VERTICAL);
		TransmitirBitacora.setOnClickListener(this);
		TransmitirBitacora.setTextColor(Color.BLACK);
//		TransmitirBitacora.setId(Constants.TransmitirBitacora);
//		TransmitirBitacora.setBackgroundColor(Color.WHITE);
		
		Button comodin = new Button(getApplicationContext());
		comodin.setText(" ");
		comodin.setTextSize(20);
		comodin.setBackgroundColor(Color.WHITE);
		Button comodin2 = new Button(getApplicationContext());
		comodin2.setText(" ");
		comodin2.setTextSize(20);
		comodin2.setBackgroundColor(Color.WHITE);
		Button comodin3 = new Button(getApplicationContext());
		comodin3.setText(" ");
		comodin3.setTextSize(20);
		comodin3.setBackgroundColor(Color.WHITE);
		Button comodin4 = new Button(getApplicationContext());
		comodin4.setText(" ");
		comodin4.setTextSize(20);
		comodin4.setBackgroundColor(Color.WHITE);
		Button comodin5 = new Button(getApplicationContext());
		comodin5.setText(" ");
		comodin5.setTextSize(20);
		comodin5.setBackgroundColor(Color.WHITE);
		Button comodin6 = new Button(getApplicationContext());
		comodin6.setText(" ");
		comodin6.setTextSize(20);
		comodin6.setBackgroundColor(Color.WHITE);
		Button comodin7 = new Button(getApplicationContext());
		comodin7.setText(" ");
		comodin7.setTextSize(25);
		comodin7.setBackgroundColor(Color.WHITE);
		Button comodin8 = new Button(getApplicationContext());
		comodin8.setText(" ");
		comodin8.setTextSize(25);
		comodin8.setBackgroundColor(Color.WHITE);
		Button comodin9 = new Button(getApplicationContext());
		comodin9.setText(" ");
		comodin9.setTextSize(25);
		comodin9.setBackgroundColor(Color.WHITE);
		Button comodin10 = new Button(getApplicationContext());
		comodin10.setText(" ");
		comodin10.setTextSize(25);
		comodin10.setBackgroundColor(Color.WHITE);
//		
//		lista.addView(MandamientosJudiaciales);
//		lista.addView(Documentos);
//		lista.addView(Emergencias);
//		lista.addView(TransmitirBitacora);
//		lista.addView(comodin);
//		lista.addView(comodin2);
//		lista.addView(comodin3);
//		lista.addView(comodin4);
//		lista.addView(comodin5);
//		lista.addView(comodin6);
//		lista.addView(comodin7);
//		lista.addView(comodin8);
//		lista.addView(comodin9);
//		lista.addView(comodin10);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_opciones, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Mandamientos:
			Log.i("Mandamientos Judiciales", "Mandamientos Judiaciales");
			//new ObtenerInformacion(getApplicationContext()).execute();
			startActivity(new Intent(ListaOpciones.this,MainActivityPager.class));
			break;
		case R.id.Documentos:
			Log.i("Documentos", "Documentos");
			Toast.makeText(getApplicationContext(), "Documentos", Toast.LENGTH_LONG).show();
			break;
		case R.id.Emergencias:
			Log.i("Emergencia", "Emergencia");
			startActivity(new Intent(ListaOpciones.this, NumerosEmergencia.class));
			break;
		case R.id.Transmitir:
			Log.i("Transmitir Bitacora", "Transmitir Bitacora");
			Toast.makeText(getApplicationContext(), "Transmitir Bitacora", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		
		
	}

	public class ObtenerInformacion extends AsyncTask<Void, Void, Void> {

		Context context;


		private ObtenerInformacion(Context context) {
	        this.context = context.getApplicationContext();
	    }
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			  progressDialog = new ProgressDialog(ListaOpciones.this);  
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
		}
		@Override
		protected Void doInBackground(Void... params) {
			EndPointsInicializacion endpoints = new EndPointsInicializacion();
			CommandmentService MandamientoEndpoint;
			MandamientoEndpoint = endpoints.InicializacionMandamiento();
			try {

				Mandamientos = MandamientoEndpoint.getCommandmentByAgentId(Preferences.getLong(Constants.idAgente,0L)).execute();
				
				
				listprueba.addAll(Mandamientos.getItems());
//				for (CommandmentDto elementos : Mandamientos.getItems()) {
//					if(elementos.getCommandmentType().equalsIgnoreCase("APREHENSION")){
//						aprehension.add(elementos);
//						
//					}else if(elementos.getCommandmentType().equalsIgnoreCase("REAPREHENSION")){
//						reaprehension.add(elementos);
//					}else if(elementos.getCommandmentType().equalsIgnoreCase("PRESENTACION")){
//						presentacion.add(elementos);
//					}else if(elementos.getCommandmentType().equalsIgnoreCase("COMPARECENCIA")){
//						comparecencia.add(elementos);
//					}else if(elementos.getCommandmentType().equalsIgnoreCase("COLABORACION")){
//						colaboracion.add(elementos);
//					}else if(elementos.getCommandmentType().equalsIgnoreCase("TRASLADOS")){
//						traslados.add(elementos);
//					}
//					System.out.println();
//					
//				}
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
			Bundle bundle = new Bundle();
			
			ObjetoMandamiento objeto = new ObjetoMandamiento();
			objeto.Mandamientos = listprueba;
		
			bundle.putParcelable("prueba", objeto);
			BasePager paginas = new BasePager();
			paginas.setArguments(bundle);
			Intent intent = new Intent(context, MainActivityPager.class);
			intent.putExtra("mandamientos", objeto);
		//	intent.putExtras(bundle);
			startActivity(intent);
//			fragmentos = new Stack<Fragment>();
//			elementos = new Stack<String>();
//			FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//			tx.setCustomAnimations(R.animator.enter,
//					R.animator.exit, R.animator.pop_enter,
//					R.animator.pop_exit);
//			tx.replace(R.id.frm_lyt_mainMenu, fragmentos.push(paginas));
//			elementos.push("paginas");
//			
//			tx.commit();
			progressDialog.dismiss();
		}

	}

	
}
