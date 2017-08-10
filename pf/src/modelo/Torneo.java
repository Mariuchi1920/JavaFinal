package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.catalina.connector.Request;

public class Torneo {
	Integer idTorneo;
	String nombreTorneo;
	String fechaComienzo;
	String estado;
	Integer idcategoria;
	
	
	
	

	

	public Torneo(Integer idTorneo, String nombreTorneo, String fechaComienzo, String estado, Integer idcategoria) {
		// TODO Auto-generated constructor stub
		
		this.idTorneo = idTorneo;
		this.nombreTorneo = nombreTorneo;
		this.fechaComienzo = fechaComienzo;
		this.estado = estado;
		this.idcategoria = idcategoria;
	
	}
	
	public Torneo() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(Integer idTorneo) {
		this.idTorneo = idTorneo;
	}
	public String getNombreTorneo() {
		return nombreTorneo;
	}
	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}
	public String getFechaComienzo() {
		return fechaComienzo;
	}
	public void setFechaComienzo(String fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
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
	}

	
	


