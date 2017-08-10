package modelo;

import java.sql.SQLException;
import java.sql.Statement;

public class Equipo {
	int idEquipo;
	String nombre;
	String idInstitucion;
	
	
/*	public Equipo(int idEquipo, String nombre, String idInstitucion) {
		
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.idInstitucion = idInstitucion;
	}
*/



	public int getIdEquipo() {
		return idEquipo;
	}


	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIdInstitucion() {
		return idInstitucion;
	}


	public void setIdInstitucion(String idInstitucion) {
		this.idInstitucion = idInstitucion;
	}
	
 
	
	
	public boolean registrarNuevoEquipo(String n, int inst) {
	 	
	 	Conexion con= new Conexion();
	 	Statement st=null;
	 	try {
	 		String consulta="INSERT INTO equipo (nombre, idInstitucion)VALUES('"+n+"','"+inst+"')";
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
}
