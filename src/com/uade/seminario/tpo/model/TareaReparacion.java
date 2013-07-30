package model;
import java.util.Vector;

import view.TareaReparacionView;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Tarea Reparacion.java
//  @ Date : 29/07/2013
//  @ Author : 
//
//




public class TareaReparacion {
	private int nroItemReparacion;
	private String detalle;
	private Vector<Pieza> piezas;
	private String estado;
	
	public TareaReparacion(int nroItem,String descripcion) {
		this.nroItemReparacion=nroItem;
		this.piezas=new Vector<Pieza>();
		this.detalle=descripcion;
	}
	public int getNroItemReparacion() {
		return nroItemReparacion;
	}
	public void setNroItemReparacion(int nroItemReparacion) {
		this.nroItemReparacion = nroItemReparacion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Vector<Pieza> getPiezas() {
		return piezas;
	}
	public void setPiezas(Vector<Pieza> piezas) {
		this.piezas = piezas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean estaActiva() {
		return estado.equals("Activa");
	}
	public TareaReparacionView getView() {
		TareaReparacionView tareaView=new TareaReparacionView();
		return null;
	}
	public void listaPiezas() {
		// TODO Auto-generated method stub
		
	}
}