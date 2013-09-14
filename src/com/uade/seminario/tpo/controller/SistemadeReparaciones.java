package com.uade.seminario.tpo.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.List;

import com.uade.seminario.tpo.service.ClienteDataService;
import com.uade.seminario.tpo.service.EmpleadoDataService;
import com.uade.seminario.tpo.view.objectView.ClienteView;
import com.uade.seminario.tpo.view.objectView.EquipoView;
import com.uade.seminario.tpo.view.objectView.ModeloView;
import com.uade.seminario.tpo.view.objectView.OrdenReparacionView;
import com.uade.seminario.tpo.view.objectView.PiezaView;
import com.uade.seminario.tpo.view.objectView.TareaReparacionView;
import com.uade.seminario.tpo.exceptions.ExceptionExisteCliente;
import com.uade.seminario.tpo.exceptions.ExceptionExisteModelo;
import com.uade.seminario.tpo.exceptions.ExceptionModeloInactivo;
import com.uade.seminario.tpo.exceptions.ExceptionModeloPerteneceEquipo;
import com.uade.seminario.tpo.exceptions.ExceptionNoExisteModelo;
import com.uade.seminario.tpo.model.Cliente;
import com.uade.seminario.tpo.model.ClienteId;
import com.uade.seminario.tpo.model.Empleado;
import com.uade.seminario.tpo.model.Equipo;
import com.uade.seminario.tpo.model.Garantia;
import com.uade.seminario.tpo.model.ItemReporte;
import com.uade.seminario.tpo.model.Modelo;
import com.uade.seminario.tpo.model.OrdenReparacion;
import com.uade.seminario.tpo.model.Pieza;
import com.uade.seminario.tpo.model.Remito;
import com.uade.seminario.tpo.model.Reporte;
import com.uade.seminario.tpo.model.TareaReparacion;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Sistema de Reparaciones.java
//  @ Date : 29/07/2013
//  @ Author : 
//
//




public class SistemadeReparaciones {
	private static SistemadeReparaciones instancia;
	private List<Remito> remitos;
	private List<Garantia> garantias;
	private List<Modelo> modelos;
	private List<Pieza> piezas;
	private List<OrdenReparacion> ordReparacion;
	private List<Equipo> equipos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Empleado> empleados;
	private List<Reporte> reportes;
	
	private SistemadeReparaciones(){
		this.remitos=new ArrayList<Remito>();
		this.garantias=new ArrayList<Garantia>();
		this.modelos=new ArrayList<Modelo>();
		this.piezas=new ArrayList<Pieza>();
		this.ordReparacion=new ArrayList<OrdenReparacion>();
		this.empleados=new ArrayList<Empleado>();
		this.equipos=new ArrayList<Equipo>();
		this.clientes= new ArrayList<Cliente>();
		this.reportes=new ArrayList<Reporte>();
		
		/* CLASES PARA TEST */
		Pieza pieza= new Pieza(1, "tornillo", "es un tornillo");
		pieza.setEstado("activo");
		piezas.add(pieza);
		
		Pieza pieza2= new Pieza(2, "tuerca", "es una tuerca");
		pieza2.setEstado("activo");
		piezas.add(pieza2);
		
		Modelo modelo= new Modelo("XP","windows", 1);
		modelo.setEstado("activo");
		modelo.addPieza(pieza);
		modelos.add(modelo);
		Cliente cliente= new Cliente("1", "DNI", "mau", "Gri", "bla", "bla", "14/12/1990", "12");
		clientes.add(cliente);
		Date dia=new Date( Date.parse("12/14/1990"));
		Garantia garantia=new Garantia(1,dia);
		garantias.add(garantia);
		Equipo equipo=new Equipo(1, modelo, cliente, garantia);
		equipo.setEstado("activo");
		equipos.add(equipo);
		Equipo equipo2=new Equipo(2, modelo, cliente, garantia);
		equipo2.setEstado("activo");
		equipos.add(equipo2);
		OrdenReparacion orden=new OrdenReparacion(1);
		TareaReparacion tarea=new TareaReparacion(1, "Le falta un tornillo");
		TareaReparacion tarea1=new TareaReparacion(2, "Le faltan dos tornillos");
		tarea.agregarPieza(pieza);
		tarea.setEstado("activo");
		tarea1.agregarPieza(pieza);
		tarea1.setEstado("activo");
		orden.setDescripcionFallas("falla blabla");		
		orden.setEquipo(equipo);
		orden.setEstado("A reparar");
		orden.setEstaEnGarantiaFisica(true);
		orden.setFecha(dia);
		orden.setPrioridad(10);
		orden.setRepararDeTodosModos(true);
		orden.agregarItemReparacion(tarea);
		orden.agregarItemReparacion(tarea1);
		ordReparacion.add(orden);
		Empleado empleado=new Empleado();
		empleado.setLegajo(1);
		empleado.setaReparar(new ArrayList<OrdenReparacion>());
		empleados.add(empleado);
		
		OrdenReparacion orden1=new OrdenReparacion(2);
		TareaReparacion tarea2=new TareaReparacion(1, "Le falta una tuerca");
		TareaReparacion tarea3=new TareaReparacion(2, "Le faltan dos tuercas");
		tarea2.agregarPieza(pieza);
		tarea2.setEstado("activo");
		tarea3.agregarPieza(pieza2);
		tarea3.setEstado("activo");
		orden1.setDescripcionFallas("falla blabla");		
		orden1.setEquipo(equipo);
		orden1.setEstado("Entregado");
		orden1.setEstaEnGarantiaFisica(true);
		orden1.setFecha(dia);
		orden1.setPrioridad(8);
		orden1.setRepararDeTodosModos(true);
		orden1.agregarItemReparacion(tarea2);
		orden1.agregarItemReparacion(tarea3);
		ordReparacion.add(orden1);
		
		OrdenReparacion orden2=new OrdenReparacion(2);
		orden2.setDescripcionFallas("falla blabla");		
		orden2.setEquipo(equipo);
		orden2.setEstado("Reparado");
		orden2.setEstaEnGarantiaFisica(true);
		orden2.setFecha(dia);
		orden2.setPrioridad(9);
		orden2.setRepararDeTodosModos(true);
		orden2.agregarItemReparacion(tarea2);
		orden2.agregarItemReparacion(tarea3);
		ordReparacion.add(orden1);
		
		/* CLASES PARA TEST */
	}
	
	public static SistemadeReparaciones getInstancia(){
		if(instancia==null)
			instancia=new SistemadeReparaciones();
		return instancia;
	}
	
	public void altaModelo(String nombre,int codigo,String descripcion){   
		Modelo modelo=buscarModelo(codigo);
		if(modelo==null){
			modelo=new Modelo(nombre,descripcion,codigo);
			modelos.add(modelo);
		}
		else
			throw new ExceptionExisteModelo(codigo);
	}
	
	public void modificarModelo(int codigo, String nombre, String descripcion){							
		Modelo modelo=this.buscarModelo(codigo);
		if(modelo!=null && modelo.modeloActivo()){
			modelo.setDescripcion(descripcion);
			modelo.setNombre(nombre);
		}
		else
			throw new ExceptionNoExisteModelo(codigo);
		
	}
	
	public void bajaModelo(int codigo) throws ExceptionModeloInactivo{
		Modelo modelo=this.buscarModelo(codigo);
		if(modelo!=null && modelo.modeloActivo()){
			if(!existeElModeloEnUnEquipo(codigo)){
				modelo.darBajaModelo();
			}
			else{
				throw new ExceptionModeloPerteneceEquipo(codigo);
			}
		}
		else{
			if(modelo==null)
				throw new ExceptionNoExisteModelo(codigo);
			else
				throw new ExceptionModeloInactivo(codigo);
			
		}
		
	}
	
	public void eliminarModelo(int codigo) {
		Modelo modelo=this.buscarModelo(codigo);
		if(modelo!=null){
			modelo.eliminar();
			modelos.remove(modelo);
		}
		
	}
	
	

	public boolean existeElModeloEnUnEquipo(int codigo) {
		for (Equipo equipo : equipos) {
			if(equipo.getModelo().getNroModelo()==codigo)
				return true;
		}
		return false;
	}

	public Modelo buscarModelo(int codigo) {
		for (Modelo modelo : modelos) {
			if(modelo.getNroModelo()==codigo){
				return modelo;
			}
		}
		return null;
	}
	
	
	public int altaOrdenReparacion(int nroSerieEquipo){
		Equipo equipo=buscarEquipo(nroSerieEquipo);
		OrdenReparacion or=BuscarEquipoxOrdenRep(nroSerieEquipo);
		OrdenReparacion or1=BuscarEquipoxOrdenRepAConfirmar(nroSerieEquipo);
		if(equipo!=null && or==null && or1==null){
			int nroOrden=this.obtenerNroOrdenReparacion();
			OrdenReparacion orden=new OrdenReparacion(nroOrden);
			orden.setEquipo(equipo);
			orden.setEstado("A Confirmar");
			ordReparacion.add(orden);
			return nroOrden;
		}
		else
			return 0;
		
	}

	private OrdenReparacion BuscarEquipoxOrdenRepAConfirmar(int nroSerieEquipo) {
		for (OrdenReparacion or : ordReparacion) {
			if(or.esTuEquipo(nroSerieEquipo) && or.estadoAConfirmar()){
				return or;
			}
		}
		return null;
	}

	public void modificarOrdenReparacion(int nroReparacion, String fallas, int prioridad){
		OrdenReparacion orden=buscarOrdenReparacion(nroReparacion);
		if(orden!=null && !orden.getEstado().equals("Reparado")){
			orden.setDescripcionFallas(fallas);
			orden.setPrioridad(prioridad);
		}
			
		
	}
	
	public OrdenReparacion buscarOrdenReparacion(int nroReparacion) {
		for (OrdenReparacion orden : ordReparacion) {
			if(orden.getNroOrden()==nroReparacion)
				return orden;
		}
		return null;
	}

	

	private OrdenReparacion BuscarEquipoxOrdenRep(int nroSerieEquipo) {
		for (OrdenReparacion or : ordReparacion) {
			if(or.esTuEquipo(nroSerieEquipo) && or.estadoAReparar()){
				return or;
			}
		}
		return null;
	}

	public Equipo buscarEquipo(int nroSerieEquipo) {
		for (Equipo equipo : equipos) {
			if(equipo.getNroSerie()==nroSerieEquipo)
				return equipo;
		}
		return null;
	}
	
	public OrdenReparacionView misReparaciones(String legajo){
		Empleado empleado=buscarEmpleado(legajo);
		if(empleado!=null){
			if(!empleado.hayOrdenReparacion()){
				OrdenReparacion orden= this.buscarOrdenReparacionPrioridad();
				empleado.addAReparar(orden);
				return orden.getView();
			}
			else
				return empleado.getaReparar().get(0).getView();
		}
		else
			return null;		
	}

	public OrdenReparacion buscarOrdenReparacionPrioridad() { //SE supone que el List esta ordenado de mayor a menor segun el numero de prioridad!
		/*Collections.sort(ordReparacion, new Comparator<OrdenReparacion>() {
		    public int compare(OrdenReparacion o1, OrdenReparacion o2) {
		        return String.valueOf(o1.getPrioridad()).compareTo(String.valueOf(o1.getPrioridad()));
		    }
		});*/
		for (OrdenReparacion orden : ordReparacion) {
			if(orden.estadoAReparar())
				return orden;
		}		
		return null;
	}

	private Empleado buscarEmpleado(String legajo) {
		EmpleadoDataService empleadoDataService = EmpleadoDataService.getInstance();
		for (Empleado empleado : empleados) {
			if(empleado.getLegajo()==Integer.parseInt(legajo)) 
				return empleado;
		}
		
		Empleado empleado=empleadoDataService.findByLegajo(Integer.parseInt(legajo));
		if (empleado != null){
			empleados.add(empleado);//bring the client to memory for future reference
		}
		return empleado;
	}
	
	
	public List<TareaReparacionView> listarTareasReparacion(int nroReparacion){
		OrdenReparacion orden=buscarOrdenReparacion(nroReparacion);
		if(orden!=null){
			return orden.listarTareasView();
		}
		return null;
		
	}
	
	public Reporte emitirReportePiezas(Date desde,Date hasta){
		Reporte reporte=new Reporte(desde,hasta);
		List<ItemReporte> itemsReporte=new ArrayList<ItemReporte>();
		List<OrdenReparacion> ordenes=buscarOrdenesReporte(desde,hasta);
		itemsReporte=generarListaPiezas(ordenes);
		reporte.setItemsReporte(itemsReporte);
		reportes.add(reporte);
		return reporte;
	}

	public List<ItemReporte> generarListaPiezas(List<OrdenReparacion> ordenes) {
		List<String> piezas=new ArrayList<String>();
		List<String> nombrePiezas=new ArrayList<String>();
		List<Integer> cantidad=new ArrayList<Integer>();
		List<ItemReporte> itemsReporte=new ArrayList<ItemReporte>();
		for (OrdenReparacion ordenReparacion : ordenes) {
			nombrePiezas=ordenReparacion.listaPiezas();
			
			for (String nombre : nombrePiezas) {
				if(piezas.contains(nombre)){
					int posicion=piezas.indexOf(nombre);
					cantidad.set(posicion, cantidad.get(posicion).intValue()+1);
				}
				else{
					piezas.add(nombre);
					cantidad.add(1);
				}
			}
		}
		int i=0;
		while(cantidad.size()>i){
			ItemReporte item= new ItemReporte(piezas.get(i),cantidad.get(i));
			itemsReporte.add(item);
			i++;
		}
		return itemsReporte;
	}

	public List<OrdenReparacion> buscarOrdenesReporte(Date desde, Date hasta) {
		List<OrdenReparacion> ordenes=new ArrayList<OrdenReparacion>();
		for (OrdenReparacion orden : ordReparacion) {
			if(orden.getFecha().after(desde)&&orden.getFecha().before(hasta) && (orden.getEstado().equals("Reparado")||orden.getEstado().equals("Entregado"))){
				ordenes.add(orden);
			}
		}
		return ordenes;
	}

	


	public void altaCliente(String nroDoc, String tipoDoc, String nombre, String apellido,
			String direccion, String mail, String fechaNac, String tel) {
		Cliente cliente;
		ClienteDataService clienteDataService = ClienteDataService.getInstance();
		cliente =  buscarCliente(nroDoc, tipoDoc);
		if(cliente==null){//if client is not in memory, search into the DB
			cliente=clienteDataService.findByDNI(nroDoc, tipoDoc);			
		}
		if(cliente==null){// client not exist in the system
			cliente=new Cliente(nroDoc,tipoDoc,nombre,apellido,direccion,mail,fechaNac,tel);
			clientes.add(cliente);
			clienteDataService.save(cliente);
		}else {
			throw new ExceptionExisteCliente(nroDoc);
		}
		
	}

	private Cliente buscarCliente(String nroDoc, String tipoDoc) {
		ClienteId id= new ClienteId(nroDoc, tipoDoc);
		ClienteDataService clienteDataService = ClienteDataService.getInstance();
		for (Cliente cliente : clientes) {
			if(cliente.getId().equals(id)) 
				return cliente;
		}
		
		Cliente cliente=clienteDataService.findByDNI(nroDoc, tipoDoc);
		if (cliente != null){
			clientes.add(cliente);//bring the client to memory for future reference
		}
		return cliente;
	}

	public ClienteView obtenerClienteView(String nroDoc,String tipoDoc) {
		Cliente cliente=buscarCliente(nroDoc,tipoDoc);
		if(cliente!=null)
			return cliente.getView();
		else
			return null;
	}

	public void modificarCliente(String nroDoc, String tipoDoc,String nombre1, String dir, String tel,
			String mail) {
		Cliente cliente=buscarCliente(nroDoc,tipoDoc);
		cliente.setNombre(nombre1);
		cliente.setDireccion(dir);
		cliente.setTelefono(tel);
		cliente.setEmail(mail);		
	}

	public void altaPieza(String nombre, int codPieza, int codModelo, String descripcion) {
		Modelo modelo=buscarModelo(codModelo);
		Pieza pieza=buscarPieza(codPieza);
		if(modelo!=null && pieza==null){
			pieza=new Pieza(codPieza,nombre,descripcion);
			modelo.addPieza(pieza);
			piezas.add(pieza);
		}
		
	}

	private Pieza buscarPieza(int codPieza) {
		for (Pieza pieza : piezas) {
			if(pieza.getNroPieza()==codPieza)
				return pieza;
		}
		return null;
	}

	public PiezaView buscarPiezaView(int codigoPieza) {
		Pieza pieza=buscarPieza(codigoPieza);
		if(pieza!=null)
			return pieza.getView();
		else
			return null;
	}

	public void modificarPieza(String nombre, int codPieza,
			String descripcion) {
		Pieza pieza=buscarPieza(codPieza);
		if(pieza!=null){
			pieza.setNombrePieza(nombre);
			pieza.setDescripcion(descripcion);
		}
		
	}

	public void BajaPieza(int codigoPieza) {
		Pieza pieza=buscarPieza(codigoPieza);
		if(pieza!=null && !hayModelosConPieza(codigoPieza) && pieza.estaActiva()){
			pieza.darBajaPieza();
		}
		
		
	}

	private boolean hayModelosConPieza(int codigoPieza) {
		Pieza pieza=buscarPieza(codigoPieza);
		for (Modelo modelo : modelos) {
			if(modelo.getPiezas().contains(pieza))
				return true;
		}
		return false;
	}

	public ModeloView buscarModeloView(int codModelo) {
		Modelo modelo=buscarModelo(codModelo);
		if(modelo!=null)
			return modelo.getView();
		else
			return null;
	}

	public List<PiezaView> buscarPiezaXModeloView(int codModelo) {
		 List<PiezaView> piezasview=new ArrayList<PiezaView>();
		 Modelo modelo=buscarModelo(codModelo);
		 if(modelo!=null){
			 List<Pieza> piezas=modelo.getPiezas();
			 for (Pieza p : piezas) {
				piezasview.add(p.getView());				
			}
		 }
		 return piezasview;
	}

	public void confirmarModelo(int codigo) {
		Modelo modelo= buscarModelo(codigo);
		if(modelo!=null)
			modelo.activar();
		else
			throw new ExceptionNoExisteModelo(codigo);
		
	}
	public void altaEquipo(int nroEquipo,int nroModelo,String tipoDoc,String nroDoc,Date fecha,String nroGarantia,boolean repararDeTodosModos){
		Equipo equipo=buscarEquipo(nroEquipo);
		Modelo modelo=buscarModelo(nroModelo);
		Cliente cliente=buscarCliente(nroDoc, tipoDoc);
		Garantia garantia1=null;
		if(!nroGarantia.equals(""))
			garantia1=buscarGarantia(Integer.parseInt(nroGarantia));
		if(equipo==null && modelo!=null && cliente!=null){
			equipo=new Equipo(nroEquipo,modelo,cliente,garantia1);
		}
		
	}

	private Garantia buscarGarantia(int nroGarantia) {
		for (Garantia garantia : garantias) {
			if(garantia.getNroGarantia()==nroGarantia)
				return garantia;			
		}
		return null;
	}

	public void altaGarantia(int nroGarantia, Date fecha1) {
		Garantia garantia=buscarGarantia(nroGarantia);
		if(garantia==null){
			garantia=new Garantia(nroGarantia,fecha1);
			garantias.add(garantia);
		}
	}

	public void quitarPiezaModelo(int codigoModelo, int nroPieza) {
		Modelo modelo=buscarModelo(codigoModelo);
		Pieza pieza=buscarPieza(nroPieza);
		if(modelo!=null && pieza!=null){
			modelo.quitarPieza(pieza);
		}
		
	}

	public void agregarPiezaModelo(PiezaView piezaV, int codigoModelo) {
		Modelo modelo=buscarModelo(codigoModelo);
		Pieza pieza=buscarPieza(piezaV.getNroPieza());
		if(modelo!=null && pieza!=null){
			modelo.addPieza(pieza);
		}
		
	}

	public EquipoView buscarEquipoView(int nroSerie) {
		Equipo equipo=buscarEquipo(nroSerie);
		if(equipo!=null && equipo.estaActivo()){
			EquipoView equipoV=equipo.getView();
			return equipoV;
		}
		else
			return null;
	}

	public OrdenReparacionView buscarOrdenConEquipoARepararView(int nroSerie) {
		Equipo equipo=buscarEquipo(nroSerie);
		if(equipo!=null){
			for (OrdenReparacion orden : ordReparacion) {
				if(orden.estadoAReparar() && orden.esTuEquipo(nroSerie)){
					return orden.getView();
				}
			}
		}
		return null;
	}

	public int obtenerNroOrdenReparacion() {
		
		return (ordReparacion.size()+1);
	}

	public List<TareaReparacionView> buscarTareasXOrdenReparacionView(int nroOrdenReparacion) {
		List<TareaReparacionView> tareasview=new ArrayList<TareaReparacionView>();
		 OrdenReparacion orden=buscarOrdenReparacion(nroOrdenReparacion);
		 if(orden!=null){
			 List<TareaReparacion> tareas=orden.getItemsReparacion();
			 for (TareaReparacion t : tareas) {
				 if(t.estaActiva())
					 tareasview.add(t.getView());				
			}
		 }
		 return tareasview;
	}

	public OrdenReparacionView buscarOrdenReparacionView(int nroOrden) {
		OrdenReparacion orden=this.buscarOrdenReparacion(nroOrden);
		if(orden!=null){
			return orden.getView();			
		}
		return null;
	}

	public int crearTareaReparacion(String descripcion, int numeroOrden) {
		OrdenReparacion orden=buscarOrdenReparacion(numeroOrden);
		if(orden!=null && !orden.getEstado().equals("Reparado")){
			int numeroTarea=obtenerNumeroTarea(orden);
			TareaReparacion tarea=new TareaReparacion(numeroTarea, descripcion);
			orden.agregarItemReparacion(tarea);
			return numeroTarea;
		}
		return 0;
		
	}

	private int obtenerNumeroTarea(OrdenReparacion orden) {
		return orden.getItemsReparacion().size()+1;
	}

	public List<PiezaView> buscarPiezasXTareaView(int nroOrden, int nroTarea) {
		List<PiezaView> piezas=new ArrayList<PiezaView>();
		OrdenReparacion orden=buscarOrdenReparacion(nroOrden);
		if(orden!=null){
			TareaReparacion tarea=orden.obtenerTarea(nroTarea);
			if(tarea!=null){
				for (Pieza pieza : tarea.getPiezas()) {
					if(pieza.estaActiva())
					piezas.add(pieza.getView());
				}
			}
		}
		return piezas;
	}

	public void agregarPiezaTarea(int nroOrden, int nroTarea, int nroPieza) {
		OrdenReparacion orden=buscarOrdenReparacion(nroOrden);
		Pieza pieza=buscarPieza(nroPieza);
		if(orden!=null){
			TareaReparacion tarea= orden.obtenerTarea(nroTarea);
			if(tarea!=null){
				tarea.agregarPieza(pieza);
			}
		}
		
		
	}

	public void quitarPiezaTarea(int nroOrden, int nroTarea, int nroPieza) {
		OrdenReparacion orden=buscarOrdenReparacion(nroOrden);
		Pieza pieza=buscarPieza(nroPieza);
		if(orden!=null){
			TareaReparacion tarea= orden.obtenerTarea(nroTarea);
			if(tarea!=null){
				pieza.setEstado("inactivo");
			}
		}
		
	}

	public void quitarTareaOrdenReparacion(int nroOrden, int nroTarea) {
		OrdenReparacion orden=buscarOrdenReparacion(nroOrden);
		if(orden!=null){
			TareaReparacion tarea=orden.obtenerTarea(nroTarea);
			if(tarea!=null && tarea.estaActiva()){
				tarea.setEstado("inactivo");
			}
		}
		
	}

	public void confirmarOrdenReparacion(int nroOrden, String fallas, boolean estaEnGarantiaFisica,boolean repararDeTodosModos, int prioridad1) {
		OrdenReparacion orden=buscarOrdenReparacion(nroOrden);
		if(orden!=null){
			orden.setDescripcionFallas(fallas);
			orden.setEstaEnGarantiaFisica(estaEnGarantiaFisica);
			orden.setRepararDeTodosModos(repararDeTodosModos);
			orden.setPrioridad(prioridad1);
			
			if(repararDeTodosModos||(orden.getEquipo().getGarantia().estasEnGarantia() && estaEnGarantiaFisica) ){
				orden.setEstado("A reparar");
			}
			else{
				orden.setEstado("Presupuestar");
			}
			
		}
		
	}

	public void confirmarTarea(int nroTarea, int numeroOrden) {
		OrdenReparacion orden=buscarOrdenReparacion(numeroOrden);
		if(orden!=null){
			TareaReparacion tarea=orden.obtenerTarea(nroTarea);
			if(tarea!=null)
				tarea.setEstado("activo");
		}
		
	}

	public String retrocederEtapaOrden(int nroOrden) {
		
		OrdenReparacion orden = buscarOrdenReparacion(nroOrden);
		if(orden.getEstado().equals("A reparar")){
			orden.setEstado("Presupuestar");
			return orden.getEstado();
		}
		else{
			if(orden.getEstado().equals("Reparado")){
				orden.setEstado("A reparar");
				return orden.getEstado();
			}
			else{
				if(orden.getEstado().equals("Entregado")){
					orden.setEstado("Reparado");
					return orden.getEstado();
				}
			}
		}
		return "";
		
	}

	public String avanzarEtapaOrden(int nroOrden) {
		OrdenReparacion orden = buscarOrdenReparacion(nroOrden);
		if(orden.getEstado().equals("A reparar")){
			orden.setEstado("Reparado");
			return orden.getEstado();
		}
		else{
			if(orden.getEstado().equals("Reparado")){
				orden.setEstado("Entregado");
				return orden.getEstado();
			}
			else{
				if(orden.getEstado().equals("Presupuestar")){
					orden.setEstado("A reparar");
					return orden.getEstado();
				}
			}
		}
		return "";
	}


	

	
}