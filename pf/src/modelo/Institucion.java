package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Institucion {
	
	int idInstitucion;
	String Nombre;
	String nombreLocalia;
	String direccionLocalia;
	String nombreDelegado;
	String apellidoDelegado;
	/*String tipoDocumentoDelegado;
	String nroDocumentoDelegado;*/
	String telefonoDelegado;
	/*String MailDelegado;*/
	
	
	
	

	
	public int getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombreLocalia() {
		return nombreLocalia;
	}

	public void setNombreLocalia(String nombreLocalia) {
		this.nombreLocalia = nombreLocalia;
	}

	public String getDireccionLocalia() {
		return direccionLocalia;
	}

	public void setDireccionLocalia(String direccionLocalia) {
		this.direccionLocalia = direccionLocalia;
	}

	public String getNombreDelegado() {
		return nombreDelegado;
	}

	public void setNombreDelegado(String nombreDelegado) {
		this.nombreDelegado = nombreDelegado;
	}

	public String getApellidoDelegado() {
		return apellidoDelegado;
	}






	public void setApellidoDelegado(String apellidoDelegado) {
		this.apellidoDelegado = apellidoDelegado;
	}

	public String getTelefonoDelegado() {
		return telefonoDelegado;
	}


	public void setTelefonoDelegado(String telefonoDelegado) {
		this.telefonoDelegado = telefonoDelegado;
	}

	public Institucion() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	public boolean registrarInstitucion(String ni, String nl, String dl, String nd, String ad, String td) {
		// TODO Auto-generated method stub
		Conexion con= new Conexion();
		Statement stm= null;
		
		try {
			System.out.println("aca no pasa");
			String consulta= "INSERT INTO institucion (Nombre, nombreLocalia, direccionLocalia, nombreDelegado, apellidoDelegado, telefonoDelegado) VALUES('"+ni+"','"+nl+"','"+dl+"','"+nd+"','"+ad+"', '"+td+"')";
			stm=con.getConexion().createStatement();
			
			stm.executeUpdate(consulta);
			System.out.println(consulta);
			return true;
			
			} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(con.getConexion()!= null)
				try {
					con.getConexion().close();
				stm.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
		
	
	}
	

	public static LinkedList<Institucion> getInstitucion() throws SQLException{
		LinkedList<Institucion>listaInstituciones= new LinkedList<Institucion>();
		Conexion con= new Conexion();
		Statement st = null;
		ResultSet rs=null;
		try{
			 st= con.getConexion().createStatement();
			 rs= st.executeQuery("select * from institucion");
			while(rs.next()){
				Institucion i= new Institucion();
				i.setIdInstitucion(rs.getInt(1));
				i.setNombre(rs.getString(2));
				i.setNombreLocalia(rs.getString(3));
				i.setDireccionLocalia(rs.getString(4));
				i.setNombreDelegado(rs.getString(5));
				i.setApellidoDelegado(rs.getString(6));
				i.setTelefonoDelegado(rs.getString(7));
				listaInstituciones.add(i);
						
			}
			
		}catch(Exception e){
			// ver que va aca adentro averiguar.... 
		}finally {
			st.close();
			rs.close();
			con.getConexion().close();
			
			
		}
		return listaInstituciones;
		
	}
	
	public void eliminarInstitucion(int id){
		Conexion con= new Conexion();
		Statement st=null;
		System.out.println("la variable es "+id);
		
		String lista= "delete from institucion where idinstitucion= "+id+";";
		
		try {
			
			st=  con.getConexion().createStatement();
			int i=st.executeUpdate(lista);
			
			st.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}





	
}
