package com.demomapas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.demomapas.endpoints.EndPointsInicializacion;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.virtualef.pgj.service.agentService.AgentService;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;

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
	
	
	//localizacion 

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_opciones);
		setTitle("Policía de Investigación");
	    Preferences = getApplicationContext().getSharedPreferences(
				"settings", 0);
		//inicializamos los layouts
		principal =  (RelativeLayout) findViewById(R.id.RelativeListaOpciones);
		lista =  (LinearLayout) findViewById(R.id.ListaElementos);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    llp.setMargins(0, 30, 0, 0);
		
		
		
		TextView MandamientosJudiaciales = new TextView(getApplicationContext());
		MandamientosJudiaciales.setText("Mandamientos Judiciales");
		MandamientosJudiaciales.setTextSize(25);
		MandamientosJudiaciales.setGravity(Gravity.CENTER);
		MandamientosJudiaciales.setOnClickListener(this);
		MandamientosJudiaciales.setTextColor(Color.BLACK);
		MandamientosJudiaciales.setId(Constants.MandamientosJudiciales);
		
		TextView Documentos = new TextView(getApplicationContext());
		Documentos.setLayoutParams(llp);
		Documentos.setText("Documentos");
		Documentos.setTextSize(25);
		Documentos.setGravity(Gravity.CENTER);
		Documentos.setOnClickListener(this);
		Documentos.setTextColor(Color.BLACK);
		Documentos.setId(Constants.Documentos);
		
		
		
		TextView Emergencias = new TextView(getApplicationContext());
		Emergencias.setText("Emergencias");
		Emergencias.setLayoutParams(llp);
		Emergencias.setTextSize(25);
		Emergencias.setGravity(Gravity.CENTER);
		Emergencias.setOnClickListener(this);
		Emergencias.setTextColor(Color.BLACK);
		Emergencias.setId(Constants.Emergencias);
		
		
		TextView TransmitirBitacora = new TextView(getApplicationContext());
		TransmitirBitacora.setText(" Transmitir Bitácoras");
		TransmitirBitacora.setLayoutParams(llp);
		TransmitirBitacora.setTextSize(25);
		TransmitirBitacora.setGravity(Gravity.CENTER);
		TransmitirBitacora.setOnClickListener(this);
		TransmitirBitacora.setTextColor(Color.BLACK);
		TransmitirBitacora.setId(Constants.TransmitirBitacora);
		
		lista.addView(MandamientosJudiaciales);
		lista.addView(Documentos);
		lista.addView(Emergencias);
		lista.addView(TransmitirBitacora);
		
		
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
		case Constants.MandamientosJudiciales:
			Log.i("Mandamientos Judiciales", "Mandamientos Judiaciales");
			startActivity(new Intent(ListaOpciones.this,MainActivityPager.class));
			break;
		case Constants.Documentos:
			Log.i("Documentos", "Documentos");
			Toast.makeText(getApplicationContext(), "Documentos", Toast.LENGTH_LONG).show();
			break;
		case Constants.Emergencias:
			Log.i("Emergencia", "Emergencia");
			Toast.makeText(getApplicationContext(), "Emergencia", Toast.LENGTH_LONG).show();
			break;
		case Constants.TransmitirBitacora:
			Log.i("Transmitir Bitacora", "Transmitir Bitacora");
			Toast.makeText(getApplicationContext(), "Transmitir Bitacora", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		
		
	}



	
}
