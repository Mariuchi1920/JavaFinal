package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.apache.catalina.connector.Request;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import com.sun.corba.se.spi.orbutil.fsm.State;

public class Categoria {
	int idcateogria;
	String descripcion;
	int estado;
	
	/*public Categoria(int idcateogria, String descripcion, int estado) {
		this.idcateogria = idcateogria;
		this.descripcion = descripcion;
		this.estado = estado;
	}*/

	
	public int getIdcateogria() {
		return idcateogria;
	}

	public void setIdcateogria(int idcateogria) {
		this.idcateogria = idcateogria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
	public boolean registrarNuevaCategoria(int idcat, String descripcion, int estado) throws SQLException{
		Conexion con= new Conexion();
		PreparedStatement pst= null;
		try {
			String consulta= "insert into categoria ( idcategoria, descripcion, estado)values (?,?,?)";
			pst=con.getConexion().prepareStatement(consulta);
			pst.setInt(1,idcat);
			pst.setString(2, descripcion);
			pst.setInt(3,estado);
			
			//usa el executeUpdate para ingresar datos a la base de datos 
			if(pst.executeUpdate()==1){
				return true;
				
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			con.getConexion().close();
			pst.close();
		}
		return false;
		
	}

	
	public static LinkedList<Categoria> getCategorias() throws SQLException{
		LinkedList<Categoria>listaCategorias= new LinkedList<Categoria>();
		Conexion con= new Conexion();
		Statement st = null;
		ResultSet rs=null;
		try{
			 st= con.getConexion().createStatement();
			 rs= st.executeQuery("select * from categoria");
			while(rs.next()){
				Categoria cat= new Categoria();
				cat.setIdcateogria(rs.getInt(1));
				cat.setDescripcion(rs.getString(2));
				cat.setEstado(rs.getInt(3));
				listaCategorias.add(cat);
						
			}
			
		}catch(Exception e){
			// ver que va aca adentro averiguar.... 
		}finally {
			st.close();
			rs.close();
			con.getConexion().close();
			
			
		}
		return listaCategorias;
		
	}
	

	
	
	public void eliminarCategoria(int idCat){
		Conexion con= new Conexion();
		Statement st=null;
		System.out.println("la variable es "+idCat);
		
		String lista= "delete from categoria where idcategoria= "+idCat+";";
		
		try {
			
			st=  con.getConexion().createStatement();
			int i=st.executeUpdate(lista);
			
			st.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	}