package entidad;

import java.util.ArrayList;
import java.util.LinkedList;

public class EquiposJugadores {
	
	private LinkedList<Persona> jugadores;
	private Equipo equipo;
	
	public LinkedList<Persona> getJugadores() {
		return jugadores;
	}
	public void setJugadores(LinkedList<Persona> jugadores) {
		this.jugadores = jugadores;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	

}
