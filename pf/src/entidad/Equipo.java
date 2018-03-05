package entidad;

import java.sql.SQLException;
import java.util.LinkedList;

import jdk.nashorn.internal.runtime.RewriteException;
import datos.EquiposJugadoresDAO;
import datos.EquiposTorneoDAO;
import datos.TipoEstadoDAO;



public class Equipo {
	

	private Categoria categorias;
	private Institucion institucion;
	private String nombreEquipo;
	private Persona entrenador;
	
	
	public Categoria getCategorias() {
		return categorias;
	}
	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	public Persona getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(Persona entrenador) {
		this.entrenador = entrenador;
	}
	
	
	
	public static boolean ifIgualDosEquipos (Equipo equipo1,Equipo equipo2 ){
		boolean  repuesta = false;
		if(equipo1.getCategorias().getIdCategorias() ==  equipo2.getCategorias().getIdCategorias()){
			if(equipo1.getInstitucion().getIdInstituciones() ==  equipo2.getInstitucion().getIdInstituciones()){
				if(equipo1.getNombreEquipo().equals(equipo2.getNombreEquipo())){
					repuesta = true;
					
				}
			}
			
		}
		
		
		return repuesta;
		
	}
	
	
	
	public static LinkedList<Equipo> removeEquipoLista (LinkedList<Equipo> listaequipos,Equipo equipo2 ){
		LinkedList<Equipo> lista = listaequipos;
		
		for (int i = 0; i < lista.size(); i++) {
			
			if(Equipo.ifIgualDosEquipos(lista.get(i), equipo2)){
				lista.remove(i);
			}
			
		}
		
		
		return lista;
		
	}
	public static boolean validarEquipoParaTorneo(Equipo equipo) throws SQLException, ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta = false;
		EquiposJugadoresDAO catJugadoresPorEquipo = new EquiposJugadoresDAO();
		LinkedList<Persona> jugadores = catJugadoresPorEquipo.listarTodasLosJugadores(equipo);
		if(jugadores.size()>=7 && jugadores.size()<=25){
			respuesta = true;
		}else{
			respuesta = false;
			throw new ApplicationException("La cantidad de Jugadores del equipo es menor a 7 y/o mayor a 25");
		}
		if(equipo.getCategorias().getEstado().getIdTipoEstado()!=TipoEstado.DESHABILITADA){
			respuesta = true;
		}else{
			respuesta = false;
			throw new ApplicationException("La categoria del equipo se encuentra deshabilitada");
		}
		
		
		return respuesta;
	}
	
	
	
	public static boolean validarEntrenador(EquiposTorneos equipo) throws SQLException, ApplicationException{
		boolean respuesta = true;
		
		EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
		LinkedList<EquiposTorneos> listaEquipos = catEquipoTorneo.buscarporTorneo(equipo.getTorneo());
		if(listaEquipos!=null && listaEquipos.size()>0){
			for (EquiposTorneos equiposTorneos : listaEquipos) {
				if(equiposTorneos.getEquipos().getEntrenador().getIdPersona() ==equipo.getEquipos().getEntrenador().getIdPersona() ){
					respuesta= false;
					throw new ApplicationException("El Entrenador se encuentra en este torneo con algun equipo");
				
						
				}
			}
			
		}
		
		   
		return respuesta;
	}
	
	public static boolean  validarPersonasEquipo(EquiposTorneos equipoTorneo) throws SQLException, ApplicationException{
		boolean respuesta = true;
		EquiposTorneoDAO catEquipoTorne = new EquiposTorneoDAO();
		LinkedList<EquiposTorneos> equipos = catEquipoTorne.buscarporTorneo(equipoTorneo.getTorneo());
		if(equipos!=null && equipos.size()>0){
			EquiposJugadoresDAO catEquiposJugadoes = new EquiposJugadoresDAO();
			LinkedList<Persona> listaJugaoresEquipos = new LinkedList<Persona>();
			for (EquiposTorneos equiposenTorneos : equipos) {
				listaJugaoresEquipos.addAll(catEquiposJugadoes.listarTodasLosJugadores(equiposenTorneos.getEquipos()));
				
				
			}
			
			LinkedList<Persona> jugadoresEquipoNuevo = catEquiposJugadoes.listarTodasLosJugadores(equipoTorneo.getEquipos());
			if(listaJugaoresEquipos!=null && listaJugaoresEquipos.size()>0){
				for (Persona persona : jugadoresEquipoNuevo) {
					if(Persona.buscarPersona(listaJugaoresEquipos, persona)){
						respuesta= false;
						throw new ApplicationException("El Jugador se encuentra en este torneo con algun equipo");
					}
				}
				
			}
		}
		
		
		
		
		return respuesta;
	}
	
	
	
	
	}
