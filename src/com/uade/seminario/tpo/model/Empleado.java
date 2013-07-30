package model;


import java.sql.Date;
import java.util.Vector;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Empleado.java
//  @ Date : 29/07/2013
//  @ Author : 
//
//




public class Empleado {
	private String nombre;
	private String apellido;
	private String tipo;
	private int nroDoc;
	private String tipoDoc;
	private Date fechaNac;
	private int legajo;
	private int idSector;
	private Vector<OrdenReparacion> aReparar;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(int nroDoc) {
		this.nroDoc = nroDoc;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	public Vector<OrdenReparacion> getaReparar() {
		return aReparar;
	}
	public void setaReparar(Vector<OrdenReparacion> aReparar) {
		this.aReparar = aReparar;
	}
	public boolean hayOrdenReparacion() {
		
		return !this.aReparar.isEmpty();
	}
	public void addAReparar(OrdenReparacion orden) {
		this.aReparar.add(orden);
		
	}
}
