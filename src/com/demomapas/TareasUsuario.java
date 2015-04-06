package com.demomapas;

import com.google.android.gms.maps.model.LatLng;

public class TareasUsuario {
	LatLng ubicacion;
	boolean status;
	Long idTarea;
	
	public LatLng getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(LatLng ubicacion) {
		this.ubicacion = ubicacion;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}
	
	

}
