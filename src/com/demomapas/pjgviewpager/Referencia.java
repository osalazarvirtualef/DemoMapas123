/**
 * 
 */
package com.demomapas.pjgviewpager;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

/**
 * @author Usuario
 *
 */
@Entity
public class Referencia {
	
	@Id
	private long idReferencia;
	private String juzgado;
	private String expediente;
	//private int noReferencia;
	private String ofocio;
	private String juez;
	private String delito;
	private String requerido;
	private String alias;
	private Long edad;
	private String sexo;
	private String domicilio;
	
//	private String cliente;
//	private String rfc;
//	private String fecha_fin_previo;
//	private int idRazonCierre;
//	private String comentariosCierre;
	private boolean Estatus;
//	private long facturas_IdFactura;
	public long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public String getOfocio() {
		return ofocio;
	}
	public void setOfocio(String ofocio) {
		this.ofocio = ofocio;
	}
	public String getJuez() {
		return juez;
	}
	public void setJuez(String juez) {
		this.juez = juez;
	}
	public String getDelito() {
		return delito;
	}
	public void setDelito(String delito) {
		this.delito = delito;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public Long getEdad() {
		return edad;
	}
	public void setEdad(Long edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public boolean isEstatus() {
		return Estatus;
	}
	public void setEstatus(boolean estatus) {
		Estatus = estatus;
	}

	
	
	
	}
