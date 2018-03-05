package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import sun.awt.image.ImageWatched.Link;
import entidad.ApplicationException;
import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.EquiposTorneos;
import entidad.Persona;
import entidad.Torneo;
import modelo.Conexion;

public class EquiposTorneoDAO {

	private String INSERT = "insert into equipostorneos ( idCategorias,idIntituciones, nombreEquipo, idTorneos) values (?,?,?,?)";
	private String DELETE = "delete from equipostorneos where idCategorias=? and idIntituciones=? and nombreEquipo=? and idTorneos=?";
	private String LISTARIDS = "select * from equipostorneos  where idCategorias=? and idIntituciones=? and nombreEquipo=? and idTorneos=?";
	private String LISTARTOEQUIPOS = "select * from equipostorneos";
	private String LISTARPOREQUIPO = "select * from equipostorneos where idCategorias=? and idIntituciones=? and nombreEquipo like ?";
	private String LISTARPORTORNEO = "select * from equipostorneos where idTorneos=? ";

	private Connection con;

	public EquiposTorneoDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoEquipoTorneo(EquiposTorneos equipoTorneo) throws SQLException, ApplicationException {
		try {

			PreparedStatement ps = con.prepareStatement(INSERT);

			ps.setInt(1, equipoTorneo.getEquipos().getCategorias().getIdCategorias());
			ps.setInt(2, equipoTorneo.getEquipos().getInstitucion().getIdInstituciones());
			ps.setString(3, equipoTorneo.getEquipos().getNombreEquipo());
			ps.setInt(4, equipoTorneo.getTorneo().getIdTorneos());

			ps.executeUpdate();
			ps.close();
			 

		} catch (SQLException e1) {
			
			e1.printStackTrace();
			throw e1;
		}

	}
	
	
	

	public void eliminarEquipoTorneo(EquiposTorneos equipoTorneo) throws SQLException {
		try {

			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, equipoTorneo.getEquipos().getCategorias()
					.getIdCategorias());
			ps.setInt(2, equipoTorneo.getEquipos().getInstitucion()
					.getIdInstituciones());
			ps.setString(3, equipoTorneo.getEquipos().getNombreEquipo());
			ps.setInt(4, equipoTorneo.getTorneo().getIdTorneos());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<EquiposTorneos> listarTodasLosEquipoTorneo() throws SQLException {
		LinkedList<EquiposTorneos> listaEquiposTorneo = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTOEQUIPOS);
			if(rs.next()){
				 listaEquiposTorneo = new LinkedList<EquiposTorneos>();
				do{
					listaEquiposTorneo.add(popularEquipoTorneo(rs));
					
				}while (rs.next()) ;
			}
			
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaEquiposTorneo;

	}

	public LinkedList<EquiposTorneos> buscarporEquipo(Equipo equipo) throws SQLException {
		LinkedList<EquiposTorneos> listaEquiposTorneo = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPOREQUIPO);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());
		
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				 listaEquiposTorneo = new LinkedList<EquiposTorneos>();
				do{
					listaEquiposTorneo.add(popularEquipoTorneo(rs));
					
				}while (rs.next());
			}
			 
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaEquiposTorneo;
	}

	public LinkedList<EquiposTorneos> buscarporTorneo(Torneo torneo) throws SQLException {
		LinkedList<EquiposTorneos> listaEquiposTorneo = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORTORNEO);
			ps.setInt(1, torneo.getIdTorneos());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaEquiposTorneo = new LinkedList<EquiposTorneos>();
				do{
					listaEquiposTorneo.add(popularEquipoTorneo(rs));
				}	while (rs.next()) ;
			}
		
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaEquiposTorneo;
	}

	public EquiposTorneos popularEquipoTorneo(ResultSet rs) throws SQLException {

		EquiposTorneos equipoTorneo = new EquiposTorneos();
		EquiposDAO catEquipo = new EquiposDAO();
		TorneosDAO catTorneo = new TorneosDAO();

		equipoTorneo.setEquipos(catEquipo.buscarporIdsEquipo(rs.getInt(1),rs.getInt(2), rs.getString(3)));
		equipoTorneo.setTorneo(catTorneo.buscarPorId(rs.getInt(4)));
		
		return equipoTorneo;

	}

}
