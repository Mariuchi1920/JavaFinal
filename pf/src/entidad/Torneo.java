package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import java.sql.PreparedStatement;

import java.sql.Date;
import modelo.Conexion;

public class Torneo {
	private int idTorneos;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEstado estado;
	private Equipo equipoGanador;
	
	
	
public int getIdTorneos() {
		return idTorneos;
	}

	public void setIdTorneos(int idTorneos) {
		this.idTorneos = idTorneos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}
	
	public Equipo getEquipoGanador() {
		return equipoGanador;
	}

	public void setEquipoGanador(Equipo equipoGanador) {
		this.equipoGanador = equipoGanador;
	}


	
	



}



	


