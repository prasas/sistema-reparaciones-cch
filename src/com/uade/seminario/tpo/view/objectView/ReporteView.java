package com.uade.seminario.tpo.view.objectView;

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





public class ReporteView {
	private List<ItemReporteView> itemsReporte;
	private Date fechaDesde;
	private Date fechaHasta;
	public ReporteView(Date desde, Date hasta) {
		this.itemsReporte=new ArrayList<ItemReporteView>();
		this.fechaDesde=desde;
		this.fechaHasta=hasta;
	}
	public ReporteView(){
		
		this.itemsReporte=new ArrayList<ItemReporteView>();
		
	}
	public List<ItemReporteView> getItemsReporte() {
		return itemsReporte;
	}
	public void setItemsReporte(List<ItemReporteView> itemsReporte2) {
		this.itemsReporte = itemsReporte2;
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