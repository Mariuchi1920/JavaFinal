package entidad;

import java.sql.SQLException;
import java.util.LinkedList;

import datos.EquiposDAO;
import datos.JugadoresPartidosDAO;
import datos.PartidoDAO;
import datos.TorneosDAO;
import sun.awt.image.ImageWatched.Link;

public class MisPartidos {
	
	private Persona persona;
	private LinkedList<Partidos> partidos;
	
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public LinkedList<Partidos> getPartidos() {
		return partidos;
	}
	public void setPartidos(LinkedList<Partidos> partidos) {
		this.partidos = partidos;
	}
	
	
	public static  LinkedList<Partidos>  buscarMisPartidos(Persona personaLogin) throws SQLException{
		 LinkedList<Partidos> misPartido=null;
		if(personaLogin.getTipoPersona().getIdTipoPersona()== TipoPersona.ARBITRO){
			misPartido = buscarPorArbitro(personaLogin);
		   
		}else if(personaLogin.getTipoPersona().getIdTipoPersona()== TipoPersona.JUGADOR){
			misPartido = buscarPorJugador(personaLogin);
		}else if(personaLogin.getTipoPersona().getIdTipoPersona()== TipoPersona.ENTRENADOR){
			misPartido = buscarPorEntrenador(personaLogin);
		}
		return misPartido;
	}
	private static LinkedList<Partidos> buscarPorArbitro(Persona personaLogin) throws SQLException {
		
		LinkedList<Partidos> misPartido=null;
	    PartidoDAO catPartidos = new PartidoDAO();
	    misPartido = catPartidos.buscarporIdArbrito(personaLogin.getIdPersona());
		
		return misPartido;
		
	}
	
	private static LinkedList<Partidos> buscarPorJugador(Persona personaLogin) throws SQLException {
		// TODO Auto-generated method stub
		LinkedList<Partidos> misPartido=null;
		JugadoresPartidosDAO catJugadoresPartido = new JugadoresPartidosDAO();
		LinkedList<JugadoresPartido> listaJugadoresPartido = catJugadoresPartido.buscarporJugador(personaLogin.getIdPersona());
		if(listaJugadoresPartido!=null && listaJugadoresPartido.size()>0){	
			for (JugadoresPartido jugadoresPartido : listaJugadoresPartido) {
				misPartido.add(jugadoresPartido.getPartido());

			}
		}
	    
		
		return misPartido;
		
	}
	private static LinkedList<Partidos> buscarPorEntrenador(Persona personaLogin) throws SQLException {
		// TODO Auto-generated method stub
		LinkedList<Partidos> misPartido=null;
		EquiposDAO catEquipo = new EquiposDAO();
		LinkedList<Equipo> listaEquipo = catEquipo.buscarporIdEntrenador(personaLogin.getIdPersona());
		if(listaEquipo!=null && listaEquipo.size()>0){
			PartidoDAO catPartido = new PartidoDAO();
			LinkedList<Partidos> listaPartidosLocal = new LinkedList<Partidos>();
			LinkedList<Partidos> listaPartidosVisitante = new LinkedList<Partidos>();
			for (Equipo equipo : listaEquipo) {
				
				listaPartidosLocal =catPartido.buscarEquipoLocal(equipo);
				if(listaPartidosLocal!=null && listaPartidosLocal.size()>0){
					misPartido.addAll(listaPartidosLocal);
				}
				
				listaPartidosVisitante =catPartido.buscarEquipoVisitante(equipo);
				if(listaPartidosVisitante!=null && listaPartidosVisitante.size()>0){
					misPartido.addAll(listaPartidosVisitante);
				}
				
			}
		}
		
		return misPartido;
		
	}
	

}
