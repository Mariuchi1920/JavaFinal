package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Conexion;

public class TipoEstado {
	
	public static final int HABILITADA =1;
	public static final int DESHABILITADA =2;
	public static final int JUGADO =3;
	public static final int PENDIENTE =4;
	public static final int SUSPENDIDO =5;
	public static final int INICIADO =6;
	public static final int FINALIZADO = 7;
	
	
	
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
