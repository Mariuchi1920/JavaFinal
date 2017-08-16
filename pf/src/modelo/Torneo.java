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


	
	
	
	public boolean registrarNuevoTorneo(String nt, String fi, String es, int cat) {
		// TODO Auto-generated method stub
		
	
		Conexion con= new Conexion();
		Statement st= null;
		System.out.print(nt);
		System.out.print(fi);
		System.out.print(es);
		System.out.print(cat);
		try {
			
			String consulta= "INSERT INTO torneo (nombreTorneo, fechaComienzo, estado, idcategoria) VALUES('"+nt+"','"+fi+"','"+es+"','"+cat+"')";
			
			st=con.getConexion().createStatement();
			
			st.executeUpdate(consulta);
			System.out.println("por que 1");
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
	public ResultSet listarTorneo(){
		Conexion con= new Conexion();
		Statement st=null;
		ResultSet rs=null;
		try {
			String lista= "select * from torneo";
			//PreparedStatement ps=con.getConexion().prepareStatement(lista);
			st=con.getConexion().createStatement();
			rs = st.executeQuery(lista);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
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
}
	


