package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class Consulta extends Conexion {
	
	
public boolean autenticacion(String usuario, String contrasena){
		
		PreparedStatement pst= null;
		ResultSet rst= null;
		//creamos un try catch para conectarnos a la base de datos. 
		
		try{
			String consulta= "select * from usuario where usuario=? and contrasena= ?";
			pst=getConexion().prepareStatement(consulta);
			
			
			//indica a parametro 1 el usuario, parametro 2 la contrasena
			pst.setString(1,usuario);
			pst.setString (2,contrasena);
			//se guarda el resultado de la consulta en el resulset
			rst=pst.executeQuery();
			
			if(rst.absolute(1)) {//quiere decir que si tiene un registro devuelve true
				return true;
			}
			
		}catch(Exception e){
			System.out.println("Error");
			
		}finally {
			if (getConexion()!= null) {
				try {
					getConexion().close();
					if (pst!=null) pst.close();
					if (rst!= null)rst.close();	
						
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
		}
		//devuelve false porque no encontro un usuario
		return false;
		
	}
	

public boolean registrarCategoria(int categoria, String descripcion, int estado){

	PreparedStatement pst= null;
	try {
		String consulta= "insert into categoria ( idCategoria, descripcion, estado)values (?,?,?)";
		pst=getConexion().prepareStatement(consulta);
		pst.setInt(1,categoria);
		pst.setString(2, descripcion);
		pst.setInt(3,estado);
		//usa el executeUpdate para ingresar datos a la base de datos 
		if(pst.executeUpdate()==1){
			return true;
			
		}
			
		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		if(getConexion()!= null)
			try {
				getConexion().close();
				if (pst!=null) pst.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	return false;
	
}

	





/*public static void main(String[] args) {
	Consulta c= new Consulta();
	System.out.println(c.autenticacion("juan", "2345"));
}
*/
/**
 * @param args
 */
/*public static void main(String[] args) {
	Consulta c= new Consulta();
	c.registrarCategoria(2006, "solo categoria 2006", 1);
	}*/
/*
public static void main(String[] args) {
	Consulta c= new Consulta();
	c.registrarNuevoTorneo("menores2005", "2/7/2017", "2/11/2017", "activo");
	}*/
}
