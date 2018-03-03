package entidad;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import datos.EquiposTorneoDAO;
import datos.JornadaDAO;
import datos.TorneosDAO;

public class TablaPosiciones {
	
	private Equipo equipo;
	private int puntuacion;
	private int cantidadPartidosGanados;
	private int cantidadPartidosPerdidos;
	private int cantidadPartidosEmpatados;

	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getCantidadPartidosGanados() {
		return cantidadPartidosGanados;
	}
	public void setCantidadPartidosGanados(int cantidadPartidosGanados) {
		this.cantidadPartidosGanados = cantidadPartidosGanados;
	}
	public int getCantidadPartidosPerdidos() {
		return cantidadPartidosPerdidos;
	}
	public void setCantidadPartidosPerdidos(int cantidadPartidosPerdidos) {
		this.cantidadPartidosPerdidos = cantidadPartidosPerdidos;
	}
	public int getCantidadPartidosEmpatados() {
		return cantidadPartidosEmpatados;
	}
	public void setCantidadPartidosEmpatados(int cantidadPartidosEmpatados) {
		this.cantidadPartidosEmpatados = cantidadPartidosEmpatados;
	}
	
	
	
	public static LinkedList<TablaPosiciones> generarTablaPosiciones(Torneo torneo) throws SQLException{
		
		LinkedList<TablaPosiciones> tablaPosiciones=null;
		
		EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
		
		LinkedList<EquiposTorneos> listaEquipoTorneo= catEquipoTorneo.buscarporTorneo(torneo);
		
		if(listaEquipoTorneo!=null && listaEquipoTorneo.size()>0){
			
			TorneosDAO catTorneo = new TorneosDAO();
			LinkedList<Partidos> partidosJugados= catTorneo.buscarPartidosJugados(torneo);
			tablaPosiciones= new LinkedList<TablaPosiciones>();
			
			  for (EquiposTorneos equipotorneo : listaEquipoTorneo) {
				  TablaPosiciones tablaequipo = new TablaPosiciones();
				  tablaequipo.setEquipo(equipotorneo.getEquipos());
				  tablaequipo.setCantidadPartidosEmpatados(0);
				  tablaequipo.setCantidadPartidosGanados(0);
				  tablaequipo.setCantidadPartidosPerdidos(0);
				  tablaequipo.setPuntuacion(0);
				  if(partidosJugados!= null && partidosJugados.size()>0){
				  for (Partidos partidos : partidosJugados) {
					  
						if(Equipo.ifIgualDosEquipos(partidos.getEquipoLocal(), equipotorneo.getEquipos())){
							if(partidos.getGolesLocal()> partidos.getGolesVisitante()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 3);
								tablaequipo.setCantidadPartidosGanados(tablaequipo.getCantidadPartidosGanados() + 1);
								
							}else if (partidos.getGolesLocal() == partidos.getGolesVisitante()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 1);
								tablaequipo.setCantidadPartidosEmpatados(tablaequipo.getCantidadPartidosEmpatados() +1);
							}else if (partidos.getGolesLocal() < partidos.getGolesVisitante()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 0);
								tablaequipo.setCantidadPartidosPerdidos(tablaequipo.getCantidadPartidosPerdidos() +1);
							}
							
						}else if(Equipo.ifIgualDosEquipos(partidos.getEquipoVisitante(), equipotorneo.getEquipos())){
							if(partidos.getGolesVisitante() >  partidos.getGolesLocal()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 3);
								tablaequipo.setCantidadPartidosGanados(tablaequipo.getCantidadPartidosGanados() + 1);
								
							}else if (partidos.getGolesLocal() == partidos.getGolesVisitante()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 1);
								tablaequipo.setCantidadPartidosEmpatados(tablaequipo.getCantidadPartidosEmpatados() +1);
							}else if (partidos.getGolesLocal() > partidos.getGolesVisitante()){
								tablaequipo.setPuntuacion(tablaequipo.getPuntuacion() + 0);
								tablaequipo.setCantidadPartidosPerdidos(tablaequipo.getCantidadPartidosPerdidos() +1);
							}
						}
						
						
					}
			  }
			   tablaPosiciones.add(tablaequipo);
				
				
			}
			
			
			
		}
		
		 Collections.sort(tablaPosiciones, new Comparator<TablaPosiciones>() {
			 
		        @Override
		        public int compare(TablaPosiciones p1, TablaPosiciones p2) {
		            return p2.getPuntuacion() - p1.getPuntuacion();
		        }
		    });
		
		
		return tablaPosiciones;
		
		
		
		
		
	}
	
	
}
