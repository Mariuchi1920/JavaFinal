package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.Date;
import modelo.Conexion;

public class Torneo {
	private int idTorneos;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int idTipoEstado;
	private int idCategoriaCampeon;
	private int idInstitucionCampeon;
	private String nombreEquipoCampeon;
	
	
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdTipoEstado() {
		return idTipoEstado;
	}

	public void setIdTipoEstado(int idTipoEstado) {
		this.idTipoEstado = idTipoEstado;
	}

	public int getIdCategoriaCampeon() {
		return idCategoriaCampeon;
	}

	public void setIdCategoriaCampeon(int idCategoriaCampeon) {
		this.idCategoriaCampeon = idCategoriaCampeon;
	}

	public int getIdInstitucionCampeon() {
		return idInstitucionCampeon;
	}

	public void setIdInstitucionCampeon(int idInstitucionCampeon) {
		this.idInstitucionCampeon = idInstitucionCampeon;
	}

	public String getNombreEquipoCampeon() {
		return nombreEquipoCampeon;
	}

	public void setNombreEquipoCampeon(String nombreEquipoCampeon) {
		this.nombreEquipoCampeon = nombreEquipoCampeon;
	}

	
	
public boolean registrarNuevoTorneo(String nt, Date fi, Date ff, int estado, String campeonCat,	String campeonInst, String campeon) {Conexion con= new Conexion();
		Statement st= null;
		
		try {
			
			String consulta= "INSERT INTO torneo (nombre, fechaInicio, fechaFin,idTipoEstado,idCategoriaCampeon, idInstitucionCampeon, nombreEquipoCampeon) VALUES('"+nt+"','"+fi+"','"+ff+"','"+estado+"','"+campeonCat+"', '"+campeonInst+"','"+campeon+"')";
			
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
			torneo.setFechaFin(rs.getDate(4));
			torneo.setIdTipoEstado(rs.getInt(5));
			torneo.setIdCategoriaCampeon(rs.getInt(6));
			torneo.setIdInstitucionCampeon(rs.getInt(7));
			torneo.setNombreEquipoCampeon(rs.getString(8));
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



	


