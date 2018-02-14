package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Conexion;

public class TipoEstado {
	
	
	private int idTipoEstado;
	

	private String descripcion;
	
	
	public int getIdTipoEstado() {
		return idTipoEstado;
	}
	public void setIdTipoEstado(int idTipoEstado) {
		this.idTipoEstado = idTipoEstado;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
	

}
