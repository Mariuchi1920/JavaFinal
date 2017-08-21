package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;



public class Torneo {
	int idtorneo;
	String nombre;
	String fecha;
	int estado;
	String campeon;
	
	
	
	//constructor
	
	public Torneo(int idtorneo, String nombre, String fecha, int estado, String campeon) {
		
		this.idtorneo = idtorneo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.estado = estado;
		this.campeon = campeon;
	}
	public Torneo() {
		// TODO Auto-generated constructor stub
	}
	// get and set
	public int getIdtorneo() {
		return idtorneo;
	}




	public void setIdtorneo(int idtorneo) {
		this.idtorneo = idtorneo;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getFecha() {
		return fecha;
	}




	public void setFecha(String fecha) {
		this.fecha = fecha;
	}




	public int getEstado() {
		return estado;
	}




	public void setEstado(int estado) {
		this.estado = estado;
	}




	public String getCampeon() {
		return campeon;
	}




	public void setCampeon(String campeon) {
		this.campeon = campeon;
	}


	
	
	
	public boolean registrarNuevoTorneo(String nt, String fi, int es, String campeon) {
		// TODO Auto-generated method stub
		
	
		Conexion con= new Conexion();
		Statement st= null;
		
		try {
			
			String consulta= "INSERT INTO torneo (nombre, fecha, estado, campeon) VALUES('"+nt+"','"+fi+"','"+es+"','"+campeon+"')";
			
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
	
	


public static LinkedList<Torneo> getTorneos() throws SQLException{
	LinkedList<Torneo>listaTorneos= new LinkedList<Torneo>();
	Conexion con= new Conexion();
	Statement st = null;
	ResultSet rs=null;
	try{
		 st= con.getConexion().createStatement();
		 rs= st.executeQuery("select * from torneo");
		while(rs.next()){
			Torneo torneo= new Torneo();
			torneo.setIdtorneo(rs.getInt(1));
			torneo.setNombre(rs.getString(2));
			torneo.setFecha(rs.getString(3));
			torneo.setEstado(rs.getInt(4));
			torneo.setCampeon(rs.getString(5));
			listaTorneos.add(torneo);
			
			
		}
		
	}catch(Exception e){
		
	}finally {
		st.close();
		rs.close();
		con.getConexion().close();
		
		
	}
	return listaTorneos;
	
}

public boolean eliminarTorneo(int id) throws SQLException{
	boolean rta=false;
	Conexion con= new Conexion();
	Statement st = null;
	try{
		 st= con.getConexion().createStatement();
		 st.execute("delete from torneo where idtorneo="+id+"");
		 return true;
	}catch(Exception e){
		
	}finally {
		st.close();
		con.getConexion().close();
	}
	return rta;
	
	
	
}
}



	


