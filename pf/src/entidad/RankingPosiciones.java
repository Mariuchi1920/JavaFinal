package entidad;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import datos.TorneosDAO;

public class RankingPosiciones {
	
	private Persona jugadores;
	private int cantidadGoles ;
	
	public Persona getJugadores() {
		return jugadores;
	}
	public void setJugadores(Persona jugadores) {
		this.jugadores = jugadores;
	}
	public int getCantidadGoles() {
		return cantidadGoles;
	}
	public void setCantidadGoles(int cantidadGoles) {
		this.cantidadGoles = cantidadGoles;
	}
	
	public static LinkedList<RankingPosiciones> generarRankingPosiciones(Torneo torneo) throws SQLException{
		LinkedList<RankingPosiciones> listaJugadores = null;
		TorneosDAO catTorneo = new TorneosDAO();
		LinkedList<JugadoresPartido> jugadorPartido = catTorneo.buscarJugadores(torneo); 
		if(jugadorPartido!=null&& jugadorPartido.size()>0){
			listaJugadores = new LinkedList<RankingPosiciones>();
			 for (JugadoresPartido jugadoresPartido : jugadorPartido) {
				 int lugar = Persona.buscarPersonaListaRanking(listaJugadores, jugadoresPartido.getJugadores());
				 RankingPosiciones ranking = new RankingPosiciones();
				 if(lugar == -1){
				    ranking.setJugadores(jugadoresPartido.getJugadores());
				    ranking.setCantidadGoles(jugadoresPartido.getCatidadGoles());
				    listaJugadores.add(ranking);
					
				}else{
					
					listaJugadores.get(lugar).setCantidadGoles(listaJugadores.get(lugar).getCantidadGoles() + jugadoresPartido.getCatidadGoles());
				}
			 }
		}
		
		 Collections.sort(listaJugadores, new Comparator<RankingPosiciones>() {
			 
		        @Override
		        public int compare(RankingPosiciones p1, RankingPosiciones p2) {
		            return p2.getCantidadGoles() - p1.getCantidadGoles();
		        }
		    });
		
		return listaJugadores;
		
	}
	

}
