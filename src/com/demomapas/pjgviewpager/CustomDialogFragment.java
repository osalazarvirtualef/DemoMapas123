package com.demomapas.pjgviewpager;

import java.util.ArrayList;

import com.demomapas.NumerosTelefonicos;
import com.demomapas.Pagina1;
import com.demomapas.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CustomDialogFragment  extends DialogFragment{

	
	ArrayList<String> telefonosText = new ArrayList<String>();
	ListView listaTelefonos;
	ArrayList<NumerosTelefonicos> telefonos = new ArrayList<NumerosTelefonicos>();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.customdialog, container);
		listaTelefonos = (ListView) rootView.findViewById(R.id.listTelefonos);
		getDialog().setTitle("Llamada de Emergencia");
		
		NumerosTelefonicos nt = new NumerosTelefonicos();
		nt.setText("Centro de Asistencia Juridica en linea: ");
		nt.setNumero("51281122");
		telefonos.add(nt);
		NumerosTelefonicos nt2 = new NumerosTelefonicos();
		nt2.setText("Honestel: ");
		nt2.setNumero("55335533");
		telefonos.add(nt2);
		NumerosTelefonicos nt3 = new NumerosTelefonicos();

		nt3.setText("Denuncias por celular: ");
		nt3.setNumero("5533");
		telefonos.add(nt3);
		NumerosTelefonicos nt4 = new NumerosTelefonicos();

		nt4.setText("Linea de atención a víctimas de Trata de Personas: ");
		nt4.setNumero("53468800");
		telefonos.add(nt4);
		NumerosTelefonicos nt5 = new NumerosTelefonicos();

		nt5.setText("Atención a Victimas del Delito: ");
		nt5.setNumero("018000074533");
		telefonos.add(nt5);
		NumerosTelefonicos nt6 = new NumerosTelefonicos();

		nt6.setText("Teléfono de atención  de la Secretaría de Desarrollo económico: ");
		nt6.setNumero("56879270");
		telefonos.add(nt6);
		NumerosTelefonicos nt7 = new NumerosTelefonicos();

		nt7.setText("Centro de atención a migrantes y sus familias: ");
		nt7.setNumero("018000091111");
		telefonos.add(nt7);
		

		
//		telefonosText.add(object);
//		telefonos.add("57979810");
//		telefonosText.add(object);
//		telefonos.add("57979810");
//		telefonosText.add(object);
//		telefonos.add("57979810");
//		telefonosText.add(object);
//		telefonos.add("57979810");
//		telefonosText.add(object);
//		telefonos.add("57979810");
		Adp_Base_ListaTelefonos telefonoslist = new Adp_Base_ListaTelefonos(telefonos,getActivity().getBaseContext());
		listaTelefonos.setAdapter(telefonoslist);
	
		
		
		return rootView;
	}
@Override
public void onActivityCreated(Bundle arg0) {
	// TODO Auto-generated method stub
	super.onActivityCreated(arg0);
	listaTelefonos.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Log.i("arg0", arg2 + " " + arg3 + "");
			MakeCall(telefonos.get(arg2).getNumero());
			dismiss();
	
			
		}
	});
}
private void MakeCall(String descripcion) {
	// TODO Auto-generated method stub
	
	Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
			+ descripcion));
	startActivity(callIntent);
	//startActivityForResult(callIntent, RESULT_CANCELED);
}
}
