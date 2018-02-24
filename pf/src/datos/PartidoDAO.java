package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.JugadoresPartido;
import entidad.Partidos;
import entidad.Persona;
import modelo.Conexion;

public class PartidoDAO {

	private String INSERT = "insert into partidos (idJornadas,idArbrito, idCategoriasLocal, idIntitucionesLocal,"
			+ "nombreEquipoLocal,idCategoriasVisitante,idIntitucionesVisitante,nombreEquipoVisitante,golesVisitante ,"
			+ "golesLocales, idTipoEstado,observaciones, hora)"
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String DELETE = "delete from partidos where idPartidos=? ";
	private String EDITAR = "update partidos set golesVisitante= ?,golesLocales=? , idTipoEstado=?, observaciones=?,hora=?  where idPartidos=?";
	private String LISTARTOPARTIDOS = "select * from partidos";
	private String LISTARPORIDS = "select * from partidos where where idPartidos=?";
	private String LISTARPORJORNADA = "select * from partidos where idJornadas=? ";
	private String LISTARPORARBITO = "select * from partidos where idArbrito=? ";
	private String LISTARPOREQUIPOLOCAL = "select * from partidos where idCategoriasLocal=? and idIntitucionesLocal=? and nombreEquipoLocal=? ";
	private String LISTARPOREQUIPOVISITANTE = "select * from partidos where idCategoriasVisitante=? and idIntitucionesVisitante=? and nombreEquipoVisitante=? ";
	// /private String
	// LISTARPORINSITUCION="select * from partidos where idIntituciones=? ";

	private Connection con;

	public PartidoDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoPartido(Partidos partido) {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);

			ps.setInt(1, partido.getJornada().getIdJornadas());
			ps.setInt(2, partido.getArbrito().getIdPersona());
			ps.setInt(3, partido.getEquipoLocal().getCategorias()
					.getIdCategorias());
			ps.setInt(4, partido.getEquipoLocal().getInstitucion()
					.getIdInstituciones());
			ps.setString(5, partido.getEquipoLocal().getNombreEquipo());
			ps.setInt(6, partido.getEquipoVisitante().getCategorias()
					.getIdCategorias());
			ps.setInt(7, partido.getEquipoVisitante().getInstitucion()
					.getIdInstituciones());
			ps.setString(8, partido.getEquipoVisitante().getNombreEquipo());
			ps.setInt(9, partido.getGolesVisitante());
			ps.setInt(10, partido.getGolesLocal());
			ps.setInt(11, partido.getEstado().getIdTipoEstado());
			ps.setString(12, partido.getObservaciones());
			ps.setTime(12, partido.getHora());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void editarPartido(Partidos partido) {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setInt(1, partido.getGolesVisitante());
			ps.setInt(2, partido.getGolesLocal());
			ps.setInt(3, partido.getEstado().getIdTipoEstado());
			ps.setString(4, partido.getObservaciones());
			ps.setTime(5, partido.getHora());
			ps.setInt(6, partido.getIdPartidos());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void eliminarPartido(Partidos partido) {
		try {

			JugadoresPartidosDAO jugPartido = new JugadoresPartidosDAO();
			LinkedList<JugadoresPartido> listaJugPar = jugPartido
					.buscarIDPartido(partido.getIdPartidos());
			if (listaJugPar == null && listaJugPar.size() == 0) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, partido.getIdPartidos());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Partidos buscarporIdsPartido(int idPartido) {
		Partidos partidos = new Partidos();
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORIDS);
			ps.setInt(1, idPartido);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos = popularPartidos(rs);

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarporIdJornada(int idJornada) {
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORJORNADA);
			ps.setInt(1, idJornada);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos.add(popularPartidos(rs));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarporIdArbrito(int idArbito) {
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORARBITO);
			ps.setInt(1, idArbito);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos.add(popularPartidos(rs));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarEquipoLocal(Equipo equipo) {
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPOREQUIPOLOCAL);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos.add(popularPartidos(rs));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarEquipoVisitante(Equipo equipo) {
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();
		try {

			PreparedStatement ps = con
					.prepareStatement(LISTARPOREQUIPOVISITANTE);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos.add(popularPartidos(rs));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public LinkedList<Partidos> ListarPartido() {
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORIDS);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				partidos.add(popularPartidos(rs));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return partidos;
	}

	public Partidos popularPartidos(ResultSet rs) {
		Partidos partido = new Partidos();
		try {
			partido.setIdPartidos(rs.getInt(1));
			JornadaDAO catJornada = new JornadaDAO();
			partido.setJornada(catJornada.buscarIdJornadas(rs.getInt(2)));
			PersonasDAO arb = new PersonasDAO();
			partido.setArbrito(arb.buscarPersonaId(rs.getInt(3)));
			EquiposDAO catEquipo = new EquiposDAO();
			partido.setEquipoLocal(catEquipo.buscarporIdsEquipo(rs.getInt(4),
					rs.getInt(5), rs.getString(6)));
			partido.setEquipoVisitante(catEquipo.buscarporIdsEquipo(
					rs.getInt(7), rs.getInt(8), rs.getString(9)));
			partido.setGolesVisitante(rs.getInt(10));
			partido.setGolesLocal(rs.getInt(11));
			TipoEstadoDAO catEstado = new TipoEstadoDAO();
			partido.setEstado(catEstado.getTipoEstados(rs.getInt(12)));
			partido.setObservaciones(rs.getString(13));
			partido.setHora(rs.getTime(14));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return partido;

	}

}
