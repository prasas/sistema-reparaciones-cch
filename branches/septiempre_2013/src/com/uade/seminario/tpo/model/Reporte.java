package model;

import java.sql.Date;
import java.util.Vector;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Reporte.java
//  @ Date : 29/07/2013
//  @ Author : 
//
//




public class Reporte {
	private Vector<itemReporte> itemsReporte;
	private Date fechaDesde;
	private Date fechaHasta;
	public Vector<itemReporte> getItemsReporte() {
		return itemsReporte;
	}
	public void setItemsReporte(Vector<itemReporte> itemsReporte) {
		this.itemsReporte = itemsReporte;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
