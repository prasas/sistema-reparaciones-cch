package com.uade.seminario.tpo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.uade.seminario.tpo.service.ClienteService;
import com.uade.seminario.tpo.service.EmpleadoService;
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
import com.uade.seminario.tpo.model.Modelo;
import com.uade.seminario.tpo.model.OrdenReparacion;
import com.uade.seminario.tpo.model.Pieza;
import com.uade.seminario.tpo.model.Remito;
import com.uade.seminario.tpo.model.Reporte;
import com.uade.seminario.tpo.model.TareaReparacion;

public class SistemadeReparaciones {
	private static SistemadeReparaciones instancia;
	private Vector<Remito> remitos;
	private Vector<Garantia> garantias;
	private Vector<Modelo> modelos;
	private Vector<Pieza> piezas;
	private Vector<OrdenReparacion> ordReparacion;
	private Vector<Equipo> equipos;
	private ArrayList<Cliente> clientes;
	private ArrayList<Empleado> empleados;
	private Vector<Reporte> reportes;
	
	private SistemadeReparaciones(){
		this.remitos=new Vector<Remito>();
		this.garantias=new Vector<Garantia>();
		this.modelos=new Vector<Modelo>();
		this.piezas=new Vector<Pieza>();
		this.ordReparacion=new Vector<OrdenReparacion>();
		this.empleados=new ArrayList<Empleado>();
		this.equipos=new Vector<Equipo>();
		this.clientes= new ArrayList<Cliente>();
		this.reportes=new Vector<Reporte>();
		
		/* CLASES PARA TEST */
		Pieza pieza= new Pieza(1, "tornillo", "es un tornillo");
		pieza.setEstado("activo");
		piezas.add(pieza);
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
		empleado.setaReparar(new Vector<OrdenReparacion>());
		empleados.add(empleado);
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
		if(equipo!=null && or==null){
			int nroOrden=this.obtenerNroOrdenReparacion();
			OrdenReparacion orden=new OrdenReparacion(nroOrden);
			orden.setEquipo(equipo);
			orden.setEstado("A confirmar");
			ordReparacion.add(orden);
			return nroOrden;
		}
		else
			return 0;
		
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
	
	public OrdenReparacionView misReparaciones(int legajo){
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

	public OrdenReparacion buscarOrdenReparacionPrioridad() { //SE supone que el vector esta ordenado de mayor a menor segun el numero de prioridad!
		for (OrdenReparacion orden : ordReparacion) {
			if(orden.estadoAReparar())
				return orden;
		}		
		return null;
	}

	private Empleado buscarEmpleado(int legajo) {
		EmpleadoService empleadoService = EmpleadoService.getInstance();
		for (Empleado empleado : empleados) {
			if(empleado.getLegajo() == legajo) 
				return empleado;
		}
		
		Empleado empleado=empleadoService.findByLegajo(legajo);
		if (empleado != null){
			empleados.add(empleado);//bring the client to memory for future reference
		}
		return empleado;
	}
	
	
	public Vector<TareaReparacionView> listarTareasReparacion(int nroReparacion){
		OrdenReparacion orden=buscarOrdenReparacion(nroReparacion);
		if(orden!=null){
			return orden.listarTareasView();
		}
		return null;
		
	}
	
	public void emitirReportePiezas(Date desde,Date hasta){
		Vector<OrdenReparacion> ordenes=buscarOrdenesReporte(desde,hasta);
		generarListaPiezas(ordenes);
	}

	public void generarListaPiezas(Vector<OrdenReparacion> ordenes) {
		for (OrdenReparacion ordenReparacion : ordenes) {
			ordenReparacion.listaPiezas();
		}
	}

	public Vector<OrdenReparacion> buscarOrdenesReporte(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	


	public void altaCliente(String nroDoc, String tipoDoc, String nombre, String apellido,
			String direccion, String mail, String fechaNac, String tel) {
		Cliente cliente;
		ClienteService clienteService = ClienteService.getInstance();
		cliente =  buscarCliente(nroDoc, tipoDoc);
		if(cliente==null){//if client is not in memory, search into the DB
			cliente=clienteService.findByDNI(nroDoc, tipoDoc);			
		}
		if(cliente==null){// client not exist in the system
			cliente=new Cliente(nroDoc,tipoDoc,nombre,apellido,direccion,mail,fechaNac,tel);
			clientes.add(cliente);
			clienteService.save(cliente);
		}else {
			throw new ExceptionExisteCliente(nroDoc);
		}
		
	}

	private Cliente buscarCliente(String nroDoc, String tipoDoc) {
		ClienteId id= new ClienteId(nroDoc, tipoDoc);
		ClienteService clienteService = ClienteService.getInstance();
		for (Cliente cliente : clientes) {
			if(cliente.getId().equals(id)) 
				return cliente;
		}
		
		Cliente cliente=clienteService.findByDNI(nroDoc, tipoDoc);
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

	public Vector<PiezaView> buscarPiezaXModeloView(int codModelo) {
		 Vector<PiezaView> piezasview=new Vector<PiezaView>();
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

	public Vector<TareaReparacionView> buscarTareasXOrdenReparacionView(int nroOrdenReparacion) {
		Vector<TareaReparacionView> tareasview=new Vector<TareaReparacionView>();
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

	public Vector<PiezaView> buscarPiezasXTareaView(int nroOrden, int nroTarea) {
		Vector<PiezaView> piezas=new Vector<PiezaView>();
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


	

	
}
