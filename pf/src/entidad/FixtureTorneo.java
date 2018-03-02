package entidad;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;

import org.omg.CORBA.portable.ApplicationException;

import datos.JornadaDAO;
import datos.PartidoDAO;
import datos.PersonasDAO;
import datos.TipoEstadoDAO;

public class FixtureTorneo {

	private Jornadas jornada;
	private LinkedList<Partidos> partidosJornadas;

	public FixtureTorneo() {
		this.partidosJornadas = new LinkedList<Partidos>();
	}

	public Jornadas getJornada() {
		return jornada;
	}

	public void setJornada(Jornadas jornada) {
		this.jornada = jornada;
	}

	public LinkedList<Partidos> getPartidos() {
		return partidosJornadas;
	}

	public void setPartidos(LinkedList<Partidos> partidosJornadas) {
		this.partidosJornadas = partidosJornadas;
	}

	public void agregarPartidos(Partidos e) {
		this.partidosJornadas.add(e);
	}
	
	
	public static void generarFixture (LinkedList<EquiposTorneos> equipoTorne) throws SQLException, ApplicationException{
		
		LinkedList<FixtureTorneo> jornadasPartidos = generarJornadas(equipoTorne);
		Torneo torneo = equipoTorne.get(0).getTorneo();
		PartidoDAO catPartido = new PartidoDAO();
		JornadaDAO catJornada = new JornadaDAO();
		TipoEstadoDAO catEstado = new TipoEstadoDAO();
		JugadoresPartido catJugadoresPartido = new JugadoresPartido();
		///TipoEstado habilitada = catEstado.getTipoEstados(TipoEstado.HABILITADA);
		TipoEstado pendiente = catEstado.getTipoEstados(TipoEstado.PENDIENTE);
		Jornadas jornadaInsertar= null;
		Partidos partidoInsetar = null;
		for(int i=0; i<jornadasPartidos.size(); i++ ){
			jornadaInsertar = new Jornadas();
			jornadaInsertar.setTorneos(torneo);
			jornadaInsertar.setEstado(pendiente);
			jornadaInsertar.setFechaDescripcion(jornadasPartidos.get(i).getJornada().getFechaDescripcion());
			jornadaInsertar.setIdJornadas( catJornada.nuevaJornadaDevuelveId(jornadaInsertar));
			
			int horasEntrePartidos = Util.cantidadHorasPorPartidos(jornadasPartidos.get(i).getPartidos().size());
			for (int j = 0; j < jornadasPartidos.get(i).getPartidos().size(); j++) {
				partidoInsetar = new Partidos();
				partidoInsetar.setEquipoLocal( jornadasPartidos.get(i).getPartidos().get(j).getEquipoLocal());
				partidoInsetar.setEquipoVisitante( jornadasPartidos.get(i).getPartidos().get(j).getEquipoVisitante());
				partidoInsetar.setArbrito( jornadasPartidos.get(i).getPartidos().get(j).getArbrito());
				partidoInsetar.setEstado(pendiente);
				partidoInsetar.setJornada(jornadaInsertar);
				Time hora = Util.addTime((horasEntrePartidos * j));
				partidoInsetar.setHora(hora);
				catPartido.nuevoPartidoSINGOL(partidoInsetar);
				
				
				
				
				
				
			}
			
		}
		
		
		
		
	}
	
	
	
	public static LinkedList<Partidos>  generarPartidosPosibles (LinkedList<EquiposTorneos> equipoTorne){
		
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();

		Partidos nuevo = new Partidos();
		for (int j = 0; j < equipoTorne.size(); j++) {

			for (int k = j + 1; k < equipoTorne.size(); k++) {

				nuevo = new Partidos();
				nuevo.setEquipoLocal(equipoTorne.get(k).getEquipos());
				nuevo.setEquipoVisitante(equipoTorne.get(j).getEquipos());
				partidos.add(nuevo);

			}
		}
		return partidos;
		
	}
	
	
	
	
	

	public static LinkedList<FixtureTorneo> generarJornadas(LinkedList<EquiposTorneos> equipoTorne) throws SQLException, ApplicationException {

		LinkedList<FixtureTorneo> jornadasPartidos = null;
		LinkedList<Partidos> partidos = generarPartidosPosibles(equipoTorne);

		
		int cantidadJornadas = equipoTorne.size() - 1;
		
		if (Util.isPar(equipoTorne.size())) {
			cantidadJornadas = equipoTorne.size() - 1;
		} else {
			cantidadJornadas = equipoTorne.size();
		}
		
		
		PersonasDAO catPersona = new PersonasDAO();
		//busco los si hay arbitros para asignarle al partido
		LinkedList<Persona> arbitos = catPersona.buscarPersonaTipoPersona(TipoPersona.ARBITRO);
		if(arbitos!=null && arbitos.size()>0){

		int arbito =0;
		int cantidadDias = Util.calularCantidadDias(equipoTorne.get(0).getTorneo().getFechaInicio(), equipoTorne.get(0).getTorneo().getFechaFin())+1;
        if(cantidadJornadas <= cantidadDias){
        	 jornadasPartidos = new LinkedList<FixtureTorneo>();
        	 // cada cuantos dias se juega una fecha es el calculo de acá 
        	 int diasPartidos = Util.cantidadDiasPorJornada(cantidadDias, cantidadJornadas);
        	

			for (int i = 0; i < cantidadJornadas; i++) {
				FixtureTorneo jornadaPartido = new FixtureTorneo();
				Date dayJornada = Util.addDays(equipoTorne.get(0).getTorneo().getFechaInicio(), (diasPartidos * i));
				Jornadas jornada = new Jornadas();
				jornada.setFechaDescripcion(dayJornada);
				jornadaPartido.setJornada(jornada);
				arbito=definirArbrito(partidos.get(i), arbito,arbitos );//aca le mando el partido que le voy a agregar un árbitro
				jornadaPartido.agregarPartidos(partidos.get(i));
	
				for (int j = 0; j < partidos.size(); j++) {
	
					if (partidoEnJorda(partidos.get(j), jornadaPartido)) {
	                   
						arbito=definirArbrito(partidos.get(j), arbito,arbitos );
						jornadaPartido.agregarPartidos(partidos.get(j));

					}
					if(jornadaPartido.getPartidos().size()>6){
						
						 throw new ApplicationException("El torneo supera la cantidad de equipos maximos", null);
						
					}
					jornadasPartidos.add(jornadaPartido);
				}
			}}else{
				 throw new ApplicationException("La cantidad de dias debe ser mayor a " + cantidadJornadas, null);
			 }
		}else{
			throw new ApplicationException("Primero debe ingresar Personas tipo Arbitro", null);
		}

		return jornadasPartidos;

	}
	
	
	public static int definirArbrito(Partidos partido , int abrito, LinkedList<Persona> arbritos){
		
		 if(abrito <  arbritos.size()){
         	partido.setArbrito(arbritos.get(abrito));
         	abrito ++;
         }else{
         	abrito=0;
        	partido.setArbrito(arbritos.get(abrito));
         	abrito ++;
         }
		 return abrito;
		
	}

	public static boolean partidoEnJorda(Partidos partidos,FixtureTorneo jornadaPartido) {

		boolean respuesta = true;

		for (int k = 0; k < jornadaPartido.getPartidos().size(); k++) {

			if (partidos.getEquipoVisitante().equals(
					jornadaPartido.getPartidos().get(k).getEquipoLocal())) {
				respuesta = false;
				break;
			} else if (partidos.getEquipoVisitante().equals(
					jornadaPartido.getPartidos().get(k).getEquipoVisitante())) {
				respuesta = false;
				break;
			} else if (partidos.getEquipoLocal().equals(
					jornadaPartido.getPartidos().get(k).getEquipoVisitante())) {
				respuesta = false;
				break;
			}
			if (partidos.getEquipoLocal().equals(
					jornadaPartido.getPartidos().get(k).getEquipoLocal())) {

				respuesta = false;
				break;
			}

		}

		return respuesta;

	}

}
