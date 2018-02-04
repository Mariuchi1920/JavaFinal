package entidad;

public class JugadoresPartido {
	
	private Partidos partido;
	private Persona jugadores;
	private int cantidadTarjetasAmarillas;
	private int cantidadTarjetasRojas;
	private int catidadGoles;
	
	
	public Partidos getPartido() {
		return partido;
	}
	public void setPartido(Partidos partido) {
		this.partido = partido;
	}
	public Persona getJugadores() {
		return jugadores;
	}
	public void setJugadores(Persona jugadores) {
		this.jugadores = jugadores;
	}
	public int getCantidadTarjetasAmarillas() {
		return cantidadTarjetasAmarillas;
	}
	public void setCantidadTarjetasAmarillas(int cantidadTarjetasAmarillas) {
		this.cantidadTarjetasAmarillas = cantidadTarjetasAmarillas;
	}
	public int getCantidadTarjetasRojas() {
		return cantidadTarjetasRojas;
	}
	public void setCantidadTarjetasRojas(int cantidadTarjetasRojas) {
		this.cantidadTarjetasRojas = cantidadTarjetasRojas;
	}
	public int getCatidadGoles() {
		return catidadGoles;
	}
	public void setCatidadGoles(int catidadGoles) {
		this.catidadGoles = catidadGoles;
	}

}
