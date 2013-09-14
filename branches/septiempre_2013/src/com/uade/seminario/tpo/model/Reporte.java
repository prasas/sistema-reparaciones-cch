package com.uade.seminario.tpo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
	private List<ItemReporte> itemsReporte;
	private Date fechaDesde;
	private Date fechaHasta;
	public Reporte(Date desde, Date hasta) {
		this.itemsReporte=new ArrayList<ItemReporte>();
		this.fechaDesde=desde;
		this.fechaHasta=hasta;
	}
	public Reporte(){
		
		this.itemsReporte=new ArrayList<ItemReporte>();
		
	}
	public List<ItemReporte> getItemsReporte() {
		return itemsReporte;
	}
	public void setItemsReporte(Vector<ItemReporte> itemsReporte) {
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
