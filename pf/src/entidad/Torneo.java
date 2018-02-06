package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import sun.util.calendar.LocalGregorianCalendar.Date;
import modelo.Conexion;



public class Torneo {
	private int idTorneos;
	private String nombre;
	private java.sql.Date fechaInicio;
	private TipoEstado estado;
	private Equipo equipoCampion;
	
		
	





public int getIdTorneos() {
	return idTorneos;
}




public void setIdTorneos(int idTorneos) {
	this.idTorneos = idTorneos;
}




public String getNombre() {
	return nombre;
}




public void setNombre(String nombre) {
	this.nombre = nombre;
}




public String getFechaInicio() {
	return fechaInicio.toString();
}




public void setFechaInicio(java.sql.Date date) {
	this.fechaInicio = date;
}




public TipoEstado getEstado() {
	return estado;
}




public void setEstado(TipoEstado estado) {
	this.estado = estado;
}




public Equipo getEquipoCampion() {
	return equipoCampion;
}




public void setEquipoCampion(Equipo equipoCampion) {
	this.equipoCampion = equipoCampion;
}




public boolean registrarNuevoTorneo(String nt, String fi, String ff, int estado, String campeonCat,
		String campeonInst, String campeon) {Conexion con= new Conexion();
		Statement st= null;
		
		try {
			
			String consulta= "INSERT INTO torneo (nombre, fechaInicio, fechaFin,idTipoEstado,idCategoriaCampeo, idInstitucionCampeon, nombreEquipoCampeon) VALUES('"+nt+"','"+fi+"','"+ff+"','"+estado+"','"+campeonCat+"', '"+campeonInst+"','"+campeon+"')";
			
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
		 rs= st.executeQuery("select * from torneos");
		while(rs.next()){
			Torneo torneo= new Torneo();
			torneo.setIdTorneos(rs.getInt(1));
			torneo.setNombre(rs.getString(2));
			torneo.setFechaInicio(rs.getDate(3));
			torneo.set
			
			
			//Aca enviar el codigo para recuperar el Estado completo. con el id
			//////////////////// aca hayy que setearle un estado primero torneo.setEstado(rs.getInt(4));
			TipoEstado estado = new TipoEstado();
			estado.setIdTipoEstado(rs.getInt(4));
			torneo.setEstado(estado);
			
			if(rs.getString(5)!= null){
				//// aca hay que hhacer el select en realidad para obtener todo el eqquio completo
				
			   Equipo equipoCampeon = new Equipo();
			   Categoria categoria = new Categoria();
			   categoria.setIdcateogria(rs.getInt(5));
			   Institucion institucion = new Institucion();
			   equipoCampeon.setIdCategorias(categoria);
			   institucion.setIdInstituciones(rs.getInt(6));
			   equipoCampeon.setIdInstitucion(institucion);
			   equipoCampeon.setNombreEquipo(rs.getString(7));
			   torneo.setEquipoCampion(equipoCampeon);
			   
			}
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



	


