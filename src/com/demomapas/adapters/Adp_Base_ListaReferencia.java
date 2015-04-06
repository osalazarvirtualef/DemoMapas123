package com.demomapas.adapters;

import java.util.List;
import java.util.zip.Inflater;




import com.demomapas.R;
import com.demomapas.pjgviewpager.Referencia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Adp_Base_ListaReferencia extends BaseAdapter {
	List<Referencia> Lista;
	Context Contexto;
	LayoutInflater inflater = null;

	public Adp_Base_ListaReferencia(List<Referencia> lista, Context contexto) {
		super();
		this.Lista = lista;
		this.Contexto = contexto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Lista.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Lista.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;

		if (arg1 == null)
			inflater = (LayoutInflater) Contexto
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		;
		vi = inflater.inflate(R.layout.lyt_fragment_childrow, null);

		// ImageView estatus=(ImageView)vi.findViewById(R.id.img_item_row);
		TextView Juzgado = (TextView) vi.findViewById(R.id.txv_Juzgado); // name
		TextView Expediente = (TextView) vi.findViewById(R.id.txv_Expediente); // email
		TextView Requerido = (TextView) vi.findViewById(R.id.txv_Requerido);
		
																				// ID
		// ImageButton button =
		// (ImageButton)vi.findViewById(R.id.btn_item_menu); // notification
		// message
		if(Lista.get(arg0).isEstatus()){
			vi.setBackgroundResource(R.drawable.bg_estatus_revisado);
			//vi.setBackgroundResource(R.drawable.prison);
		}else{
			vi.setBackgroundResource(R.drawable.bg_estatus_sinrevision);
			//vi.setBackgroundResource(R.drawable.prison2);
			
		}
//		switch (Integer.valueOf(String.valueOf(Lista.get(arg0).getIdEstatus()))) {
//		case 1:
//			// estatus.setBackgroundResource(R.drawable.ic_estatus_revisado);
//			vi.setBackgroundResource(R.drawable.bg_estatus_revisado);
//			break;
//
//		case 2:
//			// estatus.setBackgroundResource(R.drawable.ic_estatus_enrevision);
//			vi.setBackgroundResource(R.drawable.bg_estatus_enrevision);
//			break;
//
//		case 3:
//			// estatus.setBackgroundResource(R.drawable.ic_estatus_sinrevision);
//			vi.setBackgroundResource(R.drawable.bg_estatus_sinrevision);
//			break;
//
//		default:
//			break;
//		}
		Animation animation = AnimationUtils.loadAnimation(Contexto,
				R.animator.push_left_in);

		vi.startAnimation(animation);
		Juzgado.setText(Lista.get(arg0).getJuzgado());
		Expediente.setText(String.valueOf(Lista.get(arg0).getExpediente()));
		Requerido.setText(Lista.get(arg0).getRequerido());
		// button.setImageResource(android.R.drawable.ic_menu_view);
		return vi;
	}

}
