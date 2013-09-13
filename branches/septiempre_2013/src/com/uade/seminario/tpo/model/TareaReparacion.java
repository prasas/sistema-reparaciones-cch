package com.uade.seminario.tpo.model;

import java.util.List;
import java.util.Vector;

import javax.persistence.*;

import com.uade.seminario.tpo.view.objectView.TareaReparacionView;

@Entity
@Table(name="tarea_reparacion")
public class TareaReparacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nro_tarea_reparacion")
	private int nroItemReparacion;
	@Column(name="nt_detalle", columnDefinition="varchar(200)")
	private String detalle;
	@ManyToMany
	@JoinTable(name="tarea_reparacion_pieza",
	joinColumns={@JoinColumn(name="nro_tarea_reparacion")},
	inverseJoinColumns={@JoinColumn(name="nro_pieza")},
	uniqueConstraints=@UniqueConstraint(columnNames={"nro_tarea_reparacion","nro_pieza"}))
	private List<Pieza> piezas;
	@Column(name="nt_estado", columnDefinition="varchar(20)")
	private String estado;
	
	public TareaReparacion(int nroItem,String descripcion) {
		this.nroItemReparacion=nroItem;
		this.piezas=new Vector<Pieza>();
		this.detalle=descripcion;
		estado="inactivo";
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
	public List<Pieza> getPiezas() {
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
		return estado.equals("activo");
	}
	public TareaReparacionView getView() {
		TareaReparacionView tareaView=new TareaReparacionView(nroItemReparacion,detalle,piezas,estado);
		return tareaView;
	}
	public void listaPiezas() {
		// TODO Auto-generated method stub
		
	}
	public void agregarPieza(Pieza pieza) {
		this.piezas.add(pieza);
		
	}
	public void quitarPieza(Pieza pieza) {
		this.piezas.remove(pieza);
		
	}
}