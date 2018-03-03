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

	public static boolean validarTorneo(Torneo torneo) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta= true;
		Date fechaInicio = torneo.getFechaInicio();
		Date fechaFin = torneo.getFechaFin();
		TipoEstado estadoTorneo= torneo.getEstado();
		Equipo equipoCampeo = torneo.getEquipoGanador();
		
		if(fechaInicio!=null && fechaFin!=null){
			
			if(fechaInicio.getTime()>= fechaFin.getTime()){
				respuesta = false;
				throw new ApplicationException("Fecha inicio del torneo es mayor o igual a la fecha fin");
			}else if(Util.compararFechaConHoy(fechaInicio)){
				
				if(equipoCampeo==null){
					respuesta = false;
					throw new ApplicationException("Fecha inicio menor a la fecha hoy");
					
				}else if(estadoTorneo.getIdTipoEstado() != TipoEstado.INICIADO){
					respuesta = false;
					throw new ApplicationException("El debe colocarse en estado INICIADO");
				}
				
				
			}else if (Util.compararFechaConHoy(fechaFin)){
				if(equipoCampeo==null){
					respuesta = false;
					throw new ApplicationException("Fecha fin menor a la fecha hoy, debe elegir un equipo campeon");
					
				}else if(estadoTorneo.getIdTipoEstado() != TipoEstado.FINALIZADO){
					respuesta = false;
					throw new ApplicationException("El debe colocarse en estado Finalizado");
				}

			}
			
		}else{
			respuesta = false;
			throw new ApplicationException("El torneo no tiene fecha inicio y/o fin ingresadas");
		}
		
		
		
		
		
		return respuesta;
	}


	
	



}



	


