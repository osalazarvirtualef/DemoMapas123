package com.demomapas;

import android.os.Parcel;
import android.os.Parcelable;

public class MapaPojo  {
	double latitude;
	double longitud;
	String name;
	String firstName;
	String lastname;
	String tipo;
	boolean status;
	
	public MapaPojo() {
		// TODO Auto-generated constructor stub

	}
	public MapaPojo(Parcel in) {
		// TODO Auto-generated constructor stub
		latitude = in.readDouble();
		longitud = in.readDouble();
		name = in.readString();
		firstName   = in.readString();
		lastname = in.readString();
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean sstatus) {
		this.status = sstatus;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	
	

}
