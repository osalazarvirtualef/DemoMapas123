package com.demomapas;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MandamientosJudicialesList extends Activity implements OnClickListener {
	
	LinearLayout lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mandamientos_judiciales_list);
		setTitle("Mandamientos Judiciales");
		
		
		
		lista =  (LinearLayout) findViewById(R.id.LinearMandamientos);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    llp.setMargins(0, 30, 0, 0);
	    
		TextView OrdenesAprehension = new TextView(getApplicationContext());
		OrdenesAprehension.setText("Ordenes de Aprehensión");
		OrdenesAprehension.setTextSize(25);
		OrdenesAprehension.setGravity(Gravity.CENTER);
		OrdenesAprehension.setOnClickListener(this);
		OrdenesAprehension.setId(Constants.OrdenesAprehension);
		OrdenesAprehension.setLayoutParams(llp);
		
		
		TextView OrdenesReaprehension = new TextView(getApplicationContext());
		OrdenesReaprehension.setText("Ordenes de Reaprehensión");
		OrdenesReaprehension.setTextSize(25);
		OrdenesReaprehension.setGravity(Gravity.CENTER);
		OrdenesReaprehension.setOnClickListener(this);
		OrdenesReaprehension.setId(Constants.OrdenesReaprehension);
		OrdenesReaprehension.setLayoutParams(llp);
		
		TextView OrdenesPresentacion = new TextView(getApplicationContext());
		OrdenesPresentacion.setText("Ordenes de Presentación");
		OrdenesPresentacion.setTextSize(25);
		OrdenesPresentacion.setGravity(Gravity.CENTER);
		OrdenesPresentacion.setOnClickListener(this);
		OrdenesPresentacion.setId(Constants.OrdenesPresentacion);
		OrdenesPresentacion.setLayoutParams(llp);
		
		TextView OrdenesComparecencia = new TextView(getApplicationContext());
		OrdenesComparecencia.setText("Ordenes de Comparecencia");
		OrdenesComparecencia.setTextSize(25);
		OrdenesComparecencia.setGravity(Gravity.CENTER);
		OrdenesComparecencia.setOnClickListener(this);
		OrdenesComparecencia.setId(Constants.OrdenesComparecencia);
		OrdenesComparecencia.setLayoutParams(llp);
		
		
		TextView OficiosColaboracion = new TextView(getApplicationContext());
		OficiosColaboracion.setText("Oficios de Colaboración");
		OficiosColaboracion.setTextSize(25);
		OficiosColaboracion.setGravity(Gravity.CENTER);
		OficiosColaboracion.setOnClickListener(this);
		OficiosColaboracion.setId(Constants.OficiosColaboracion);
		OficiosColaboracion.setLayoutParams(llp);
		
		
		TextView Traslados = new TextView(getApplicationContext());
		Traslados.setText("Traslados");
		Traslados.setTextSize(25);
		Traslados.setGravity(Gravity.CENTER);
		Traslados.setOnClickListener(this);
		Traslados.setId(Constants.Traslados);
		Traslados.setLayoutParams(llp);
		
		lista.addView(OrdenesAprehension);
		lista.addView(OrdenesReaprehension);
		lista.addView(OrdenesPresentacion);
		lista.addView(OrdenesComparecencia);
		lista.addView(OficiosColaboracion);
		lista.addView(Traslados);
		
		
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mandamientos_judiciales_list, menu);
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
		case Constants.OrdenesAprehension:
			Toast.makeText(getApplicationContext(), "aprehension", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MandamientosJudicialesList.this, Principal.class));
	
			
			break;
		case Constants.OrdenesReaprehension:
			Toast.makeText(getApplicationContext(), "reaprehension", Toast.LENGTH_SHORT).show();
			
			break;
		
		case Constants.OrdenesPresentacion:
			Toast.makeText(getApplicationContext(), "presentacion", Toast.LENGTH_SHORT).show();
			
			break;
		
		case Constants.OficiosColaboracion:
			Toast.makeText(getApplicationContext(), "colaboracion", Toast.LENGTH_SHORT).show();
			
			break;
		
		case Constants.OrdenesComparecencia:
			Toast.makeText(getApplicationContext(), "comparecencia", Toast.LENGTH_SHORT).show();
			break;
		
		case Constants.Traslados:
			Toast.makeText(getApplicationContext(), "traslados", Toast.LENGTH_SHORT).show();
			System.out.println();
			break;
		

		default:
			break;
		}
		
		
	}
}
