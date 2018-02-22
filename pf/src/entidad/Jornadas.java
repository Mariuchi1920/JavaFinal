package entidad;

import java.sql.Date;



public class Jornadas {
	
	private int idJornadas;
	private Torneo torneos;
	private Date fechaDescripcion;
	private TipoEstado estado;
	
	
	public int getIdJornadas() {
		return idJornadas;
	}
	public void setIdJornadas(int idJornadas) {
		this.idJornadas = idJornadas;
	}
	public Torneo getTorneos() {
		return torneos;
	}
	public void setTorneos(Torneo torneos) {
		this.torneos = torneos;
	}
	public Date getFechaDescripcion() {
		return fechaDescripcion;
	}
	public void setFechaDescripcion(Date fechaDescripcion) {
		this.fechaDescripcion = fechaDescripcion;
	}
	public TipoEstado getEstado() {
		return estado;
	}
	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}
	
	
	
}
