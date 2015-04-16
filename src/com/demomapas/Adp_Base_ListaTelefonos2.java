package com.demomapas;

import java.util.ArrayList;

import com.demomapas.NumerosTelefonicos;
import com.demomapas.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Adp_Base_ListaTelefonos2 extends BaseAdapter {
	//ArrayList<String>telefonos = new ArrayList<String>();
	ArrayList<NumerosTelefonicos> telefonos = new ArrayList<NumerosTelefonicos>(); 
	Context contexto;
	LayoutInflater inflater = null;

	public Adp_Base_ListaTelefonos2(ArrayList<NumerosTelefonicos> telefonos,
			Context baseContext) {
		this.telefonos = telefonos;
		this.contexto = baseContext;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return telefonos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return telefonos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (convertView == null)
			inflater = (LayoutInflater) contexto
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		vi = inflater.inflate(R.layout.lyt_fragment_phones, null);
//		TextView Juzgado = (TextView) vi.findViewById(R.id.txv_Juzgado); // name
//		TextView Expediente = (TextView) vi.findViewById(R.id.txv_Expediente); // email
//		TextView Requerido = (TextView) vi.findViewById(R.id.txv_Requerido);
		TextView telefonoPlace = (TextView) vi.findViewById(R.id.textoPhone);
		telefonoPlace.setText(telefonos.get(position).getText());
		TextView boton = (TextView)vi.findViewById(R.id.ButtonCall);
		boton.setText(telefonos.get(position).getNumero());
		return vi;
	}
	

}
