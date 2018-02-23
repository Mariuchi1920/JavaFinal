package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.EquiposTorneos;
import entidad.Torneo;
import modelo.Conexion;

public class EquiposTorneoDAO {
	
	
	
	private String INSERT= "insert into equipostorneos ( idCategorias,idIntituciones, nombreEquipo, idTorneos) values (?,?,?,?)";
	private String DELETE="delete from equipostorneos where idCategorias=? and idIntituciones=? and nombreEquipo=? and idTorneos=?";
	private String LISTARIDS="select * from equipostorneos  where idCategorias=? and idIntituciones=? and nombreEquipo=? and idTorneos=?";
	private String LISTARTOEQUIPOS="select * from equipostorneos";
	private String LISTARPOREQUIPO="select * from equipostorneos where idCategorias=? and idIntituciones=? and nombreEquipo like ?";
	private String LISTARPORTORNEO="select * from equipostorneos where idTorneos=? ";
	
	private Connection con;
	
	public EquiposTorneoDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	
	
	
	public void nuevoEquipoTorneo(EquiposTorneos equipoTorneo) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			
			ps.setInt(1,equipoTorneo.getEquipos().getCategorias().getIdCategorias());
			ps.setInt(2, equipoTorneo.getEquipos().getInstitucion().getIdInstituciones());
			ps.setString(3,equipoTorneo.getEquipos().getNombreEquipo());
			ps.setInt(4, equipoTorneo.getTorneo().getIdTorneos());
			
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	public void eliminarEquipoTorneo(EquiposTorneos equipoTorneo) {
		try {
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,equipoTorneo.getEquipos().getCategorias().getIdCategorias());
			ps.setInt(2, equipoTorneo.getEquipos().getInstitucion().getIdInstituciones());
			ps.setString(3,equipoTorneo.getEquipos().getNombreEquipo());
			ps.setInt(4, equipoTorneo.getTorneo().getIdTorneos());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public LinkedList<EquiposTorneos> listarTodasLosEquipoTorneo(){
		LinkedList<EquiposTorneos>listaEquiposTorneo= new LinkedList<EquiposTorneos>();
		try{
			
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(LISTARIDS);
			while(rs.next()) {
				
				listaEquiposTorneo.add(popularEquipoTorneo(rs));
				

				
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaEquiposTorneo;
		
	}
	
	
	
	
	public LinkedList<EquiposTorneos> buscarporEquipo(Equipo eq) {
		LinkedList<EquiposTorneos>listaEquiposTorneo= new LinkedList<EquiposTorneos>();
		try {
			
			PreparedStatement ps= con.prepareStatement(LISTARPOREQUIPO);
			ps.setInt(1,eq.getCategorias().getIdCategorias());
			ps.setInt(2, eq.getInstitucion().getIdInstituciones());
			ps.setString(3,eq.getNombreEquipo());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaEquiposTorneo.add(popularEquipoTorneo(rs));
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaEquiposTorneo;
	}
	
	

	
	
	public LinkedList<EquiposTorneos> buscarporTorneo(Torneo torneo) {
		LinkedList<EquiposTorneos>listaEquiposTorneo= new LinkedList<EquiposTorneos>();
		try {
			
			PreparedStatement ps= con.prepareStatement(LISTARPORTORNEO);
			ps.setInt(1,torneo.getIdTorneos());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaEquiposTorneo.add(popularEquipoTorneo(rs));
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaEquiposTorneo;
	}
	
	public EquiposTorneos popularEquipoTorneo(ResultSet rs){
		
		EquiposTorneos equipoTorneo = new EquiposTorneos();
		EquiposDAO catEquipo = new EquiposDAO();
		TorneosDAO catTorneo = new TorneosDAO();
		
		try {
			equipoTorneo.setEquipos(catEquipo.buscarporIdsEquipo(rs.getInt(1), rs.getInt(2), rs.getString(3)));
			equipoTorneo.setTorneo(catTorneo.buscarPorId(rs.getInt(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipoTorneo;

		
	}
	

}
