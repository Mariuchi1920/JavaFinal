package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.JugadoresPartido;
import modelo.Conexion;

public class JugadoresPartidosDAO {

	private String INSERT = "insert into jugadorespartidos ( idPartidos,idJugadores, cantidadTarjetasAmarillas, cantidadTarjetasRojas,cantidadGoles) values (?,?,?,?,?)";
	private String DELETE = "delete from jugadorespartidos where idPartidos=? and idJugadores=? ";
	private String EDITAR = "update jugadorespartidos set cantidadTarjetasAmarillas= ?,cantidadTarjetasRojas=?, cantidadGoles=? where idPartidos=? and idJugadores=? ";
	private String LISTARTOEQUIPOS = "select * from jugadorespartidos";
	private String LISTARPORJUGADOR = "select * from jugadorespartidos where idJugadores=? ";
	private String LISTARPORPARTIDO = "select * from jugadorespartidos where idPartidos=? ";

	private Connection con;

	public JugadoresPartidosDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoJugadorPartido(JugadoresPartido jugadoresPartido) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, jugadoresPartido.getPartido().getIdPartidos());
			ps.setInt(2, jugadoresPartido.getJugadores().getIdPersona());
			ps.setInt(3, jugadoresPartido.getCantidadTarjetasAmarillas());
			ps.setInt(4, jugadoresPartido.getCantidadTarjetasRojas());
			ps.setInt(5, jugadoresPartido.getCatidadGoles());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}

	}

	public void editarJugadorPartido(JugadoresPartido jugadoresPartido) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setInt(1, jugadoresPartido.getCantidadTarjetasAmarillas());
			ps.setInt(2, jugadoresPartido.getCantidadTarjetasRojas());
			ps.setInt(3, jugadoresPartido.getCatidadGoles());
			ps.setInt(4, jugadoresPartido.getPartido().getIdPartidos());
			ps.setInt(5, jugadoresPartido.getJugadores().getIdPersona());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public void eliminarJugadorPartido(JugadoresPartido jugadoresPartido) throws SQLException {
		try {

			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, jugadoresPartido.getPartido().getIdPartidos());
			ps.setInt(2, jugadoresPartido.getJugadores().getIdPersona());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<JugadoresPartido> buscarporJugador(int idJugador) throws SQLException {
		LinkedList<JugadoresPartido> listaCategorias = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORJUGADOR);
			ps.setInt(1, idJugador);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaCategorias = new LinkedList<JugadoresPartido>();
				do{
					listaCategorias.add(popularJugadorEquipo(rs));
				}while (rs.next());
			}
		
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaCategorias;
	}

	public LinkedList<JugadoresPartido> buscarIDPartido(int idPartido) throws SQLException {
		LinkedList<JugadoresPartido> listaCategorias = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORPARTIDO);
			ps.setInt(1, idPartido);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaCategorias = new LinkedList<JugadoresPartido>();
				do{
					listaCategorias.add(popularJugadorEquipo(rs));
				}while (rs.next()) ;
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaCategorias;
	}

	public JugadoresPartido popularJugadorEquipo(ResultSet rs) throws SQLException {
		JugadoresPartido jugadoresPartido = new JugadoresPartido();
		PartidoDAO catPartido = new PartidoDAO();
		PersonasDAO catJugador = new PersonasDAO();

		jugadoresPartido.setPartido(catPartido.buscarporIdsPartido(rs.getInt(1)));
		jugadoresPartido.setJugadores(catJugador.buscarPersonaId(rs.getInt(2)));
		jugadoresPartido.setCantidadTarjetasAmarillas(rs.getInt(3));
		jugadoresPartido.setCantidadTarjetasRojas(rs.getInt(4));
		jugadoresPartido.setCatidadGoles(rs.getInt(5));
	

		return jugadoresPartido;

	}

}
