package entidad;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import datos.JornadaDAO;
import datos.PartidoDAO;
import datos.TipoEstadoDAO;





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
	
	
	public static void definirEstadoJoranda (int idJornada) throws SQLException{
		PartidoDAO catPartido = new PartidoDAO();
		LinkedList<Partidos> listapartidos = catPartido.buscarporIdJornada(idJornada);
		int cantidadSuspendido= 0;
		int cantidadJugados= 0;
		int cantidadPendiente= 0;
		for (Partidos partidos : listapartidos) {
			if(partidos.getEstado().getIdTipoEstado()== TipoEstado.SUSPENDIDO){
				cantidadSuspendido ++;
			}else if(partidos.getEstado().getIdTipoEstado()== TipoEstado.JUGADO){
				cantidadJugados ++;
			}if(partidos.getEstado().getIdTipoEstado()== TipoEstado.PENDIENTE){
				cantidadPendiente ++;
			}
			
		}
		
		JornadaDAO catJornada = new JornadaDAO();
		Jornadas jornada = catJornada.buscarIdJornadas(idJornada);
		TipoEstadoDAO tipoEstado = new TipoEstadoDAO();
		
		if(cantidadSuspendido == listapartidos.size() ){
			jornada.setEstado(tipoEstado.getTipoEstados(TipoEstado.SUSPENDIDO));
			catJornada.editarJornada(jornada);
			
		}else if(cantidadJugados == listapartidos.size() ){
			jornada.setEstado(tipoEstado.getTipoEstados(TipoEstado.JUGADO));
			catJornada.editarJornada(jornada);
			
		}else if(cantidadPendiente == listapartidos.size() ){
			jornada.setEstado(tipoEstado.getTipoEstados(TipoEstado.PENDIENTE));
			catJornada.editarJornada(jornada);
			
		}
		
	}
	
	
	
}
