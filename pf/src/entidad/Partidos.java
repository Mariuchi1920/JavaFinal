package entidad;

import java.sql.Time;

public class Partidos {
	
	
	private int idPartidos;
	private Jornadas jornada;
	private Persona arbrito;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int golesVisitante;
	private int golesLocal;
	private TipoEstado estado;
	private String observaciones;
	private Time hora;
	
	
	public int getIdPartidos() {
		return idPartidos;
	}
	public void setIdPartidos(int idPartidos) {
		this.idPartidos = idPartidos;
	}
	public Jornadas getJornada() {
		return jornada;
	}
	public void setJornada(Jornadas jornada) {
		this.jornada = jornada;
	}
	public Persona getArbrito() {
		return arbrito;
	}
	public void setArbrito(Persona arbrito) {
		this.arbrito = arbrito;
	}
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}
	public int getGolesVisitante() {
		return golesVisitante;
	}
	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	public int getGolesLocal() {
		return golesLocal;
	}
	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}
	public TipoEstado getEstado() {
		return estado;
	}
	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	
	
	
}
