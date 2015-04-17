package com.demomapas;

import java.util.ArrayList;
import java.util.List;

import com.virtualef.pgj.service.commandmentService.model.CollectionResponseCommandmentDto;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.os.Parcel;
import android.os.Parcelable;

public class ObjetoMandamiento implements Parcelable {
	
	public List<CommandmentDto> Mandamientos;
//	public ArrayList<CommandmentDto> aprehension;
//	public ArrayList<CommandmentDto> reaprehension;
//	public ArrayList<CommandmentDto> presentacion;
//	public ArrayList<CommandmentDto> comparecencia;
//	public ArrayList<CommandmentDto> colaboracion;
//	public ArrayList<CommandmentDto> traslados;

	
	public ObjetoMandamiento(Parcel in) {
	
		
		System.out.println();

//		aprehension =  new ArrayList<CommandmentDto>();
//		reaprehension =  new ArrayList<CommandmentDto>();
//		presentacion = new ArrayList<CommandmentDto>();
//		comparecencia = new ArrayList<CommandmentDto>();
//		colaboracion = new ArrayList<CommandmentDto>();
//		traslados = new ArrayList<CommandmentDto>();
//		notas = new float[3];
//		amigos = new ArrayList<ObjetoMandamiento>();
		
		readFromParcel(in);
	}
	  // Method to recreate a Question from a Parcel
    public static Creator<ObjetoMandamiento> CREATOR = new Creator<ObjetoMandamiento>() {

        @Override
        public ObjetoMandamiento createFromParcel(Parcel source) {
            return new ObjetoMandamiento(source);
        }

        @Override
        public ObjetoMandamiento[] newArray(int size) {
            return new ObjetoMandamiento[size];
        }

    };
	
	public ObjetoMandamiento() {
		// TODO Auto-generated constructor stub
		Mandamientos = new ArrayList<CommandmentDto>();
//		aprehension = new ArrayList<CommandmentDto>();
//		reaprehension =  new ArrayList<CommandmentDto>();
//		presentacion = new ArrayList<CommandmentDto>();
//		comparecencia = new ArrayList<CommandmentDto>();
//		colaboracion = new ArrayList<CommandmentDto>();
//		traslados = new ArrayList<CommandmentDto>();
		
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeList(Mandamientos);
//		dest.writeList(aprehension);
//		dest.writeList(reaprehension);
//		dest.writeList(presentacion);
//		dest.writeList(comparecencia);
//		dest.writeList(colaboracion);
//		dest.writeList(traslados);

	}

	
	private void readFromParcel(Parcel in) {
		
//	    fechaNacimiento = in.readInt();
//	    nombreCompleto = in.readString();
//	 
//	    boolean[] temp = new boolean[1];
//	    in.readBooleanArray(temp);
//	    esHijoUnico = temp[0];
//	 
//	    in.readFloatArray(notas);
		//in.readList(Mandamientos, null);
		ArrayList<CommandmentDto> products = new ArrayList<CommandmentDto>();
		in.readList(products,null);
	   // in.readList(Mandamientos, null);
	}
}
