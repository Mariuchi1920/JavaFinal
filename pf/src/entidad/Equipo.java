package entidad;

import java.sql.SQLException;
import java.sql.Statement;

import sun.nio.cs.ext.ISCII91;
import modelo.Conexion;

public class Equipo {
	//private int idEquipo;
	private String nombreEquipo;
	private Institucion idInstitucion;
	private Persona idEntrenador;
	private Categoria idCategorias;
	 


 
	
	
	public boolean registrarNuevoEquipo(String nombre, int institucion, int categoria) {
	 	
	 	Conexion con= new Conexion();
	 	Statement st=null;
	 	try {
	 		String consulta="INSERT INTO equipo (nombre, idInstitucion,idCategorias )VALUES('"+nombre+"','"+
	 				            institucion+"','"+categoria+"')";
	 		st=con.getConexion().createStatement();
	 		st.executeUpdate(consulta);
	 		
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(con.getConexion()!= null)
				try {
					con.getConexion().close();
					if (st!=null) st.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
		
	}






	public String getNombreEquipo() {
		return nombreEquipo;
	}






	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}






	public Institucion getIdInstitucion() {
		return idInstitucion;
	}






	public void setIdInstitucion(Institucion idInstitucion) {
		this.idInstitucion = idInstitucion;
	}






	public Persona getIdEntrenador() {
		return idEntrenador;
	}






	public void setIdEntrenador(Persona idEntrenador) {
		this.idEntrenador = idEntrenador;
	}






	public Categoria getIdCategorias() {
		return idCategorias;
	}






	public void setIdCategorias(Categoria idCategorias) {
		this.idCategorias = idCategorias;
	}
}
