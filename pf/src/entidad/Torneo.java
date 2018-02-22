package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import java.sql.PreparedStatement;

import java.sql.Date;
import modelo.Conexion;

public class Torneo {
	private int idTorneos;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEstado estado;
	private Equipo equipoGanador;
	
	
	
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

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}
	
	public Equipo getEquipoGanador() {
		return equipoGanador;
	}

	public void setEquipoGanador(Equipo equipoGanador) {
		this.equipoGanador = equipoGanador;
	}


	
	
//
//public boolean registrarNuevoTorneo(String nt, Date fi, Date ff, int estado, String campeonCat,	String campeonInst, String campeon) {
//		Conexion con= new Conexion();
//		PreparedStatement st= null;
//		
//		try {
//			
//			String consulta= "INSERT INTO torneos (nombre, fechaInicio, fechaFin, idTipoEstado,idCategoriaCampeon, idIntitucionCampeon, nombreEquipoCampeon) VALUES (?,?,?,?,?,?,?);";
//			
//			st=con.getConexion().prepareStatement(consulta);
//			
//			st.setString(1, nt);
//			st.setDate(2, fi);
//			st.setDate(3, ff);
//			st.setInt(4, estado);
//			st.setInt(5, 2005);// idCategoriaCampeon);
//			st.setInt(6, 1); //idInstitucionCampeon);
//			if(campeon.isEmpty()) {
//				campeon=null;
//			}
//			st.setString(7, campeon );
//			
//			st.executeUpdate();
//			return true;
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//			// TODO: handle exception
//		}finally {
//			if(con.getConexion()!= null)
//				try {
//					con.getConexion().close();
//					if (st!=null) st.close();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return false;
//				}
//		}
//		
//	}

//public static LinkedList<Torneo> getTorneos() throws SQLException{
//	LinkedList<Torneo>listaTorneos= new LinkedList<Torneo>();
//	Conexion con= new Conexion();
//	Statement st = null;
//	ResultSet rs=null;
//	try{
//		 st= con.getConexion().createStatement();
//		 rs= st.executeQuery("select * from torneos");
//		while(rs.next()){
//			Torneo torneo= new Torneo();
//			torneo.setIdTorneos(rs.getInt(1));
//			torneo.setNombre(rs.getString(2));
//			torneo.setFechaInicio(rs.getDate(3));
//			torneo.setFechaFin(rs.getDate(4));
//			torneo.setIdTipoEstado(rs.getInt(5));
//			torneo.setIdCategoriaCampeon(rs.getInt(6));
//			torneo.setIdInstitucionCampeon(rs.getInt(7));
//			torneo.setNombreEquipoCampeon(rs.getString(8));
//			listaTorneos.add(torneo);
//			}
//			
//			
//			
//		}catch(Exception e){
//		
//	}finally {
//		st.close();
//		rs.close();
//		con.getConexion().close();
//		
//		
//	}
//	return listaTorneos;
//	
//}

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



	


