package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import entidad.ApplicationException;
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
	
	private String INSERTSINGOL = "insert into partidos (idJornadas,idArbrito, idCategoriasLocal, idIntitucionesLocal,"
			+ "nombreEquipoLocal,idCategoriasVisitante,idIntitucionesVisitante,nombreEquipoVisitante, idTipoEstado, hora)"
			+ "values (?,?,?,?,?,?,?,?,?,?)";
	private String DELETE = "delete from partidos where idPartidos=? ";
	private String EDITAR = "update partidos set golesVisitante= ?,golesLocales=? , idTipoEstado=?, observaciones=?,hora=?  where idPartidos=?";
	private String LISTARTOPARTIDOS = "select * from partidos";
	private String LISTARPORIDS = "select * from partidos where  idPartidos=?";
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

	public void nuevoPartido(Partidos partido) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);

			ps.setInt(1, partido.getJornada().getIdJornadas());
			ps.setInt(2, partido.getArbrito().getIdPersona());
			ps.setInt(3, partido.getEquipoLocal().getCategorias().getIdCategorias());
			ps.setInt(4, partido.getEquipoLocal().getInstitucion().getIdInstituciones());
			ps.setString(5, partido.getEquipoLocal().getNombreEquipo());
			ps.setInt(6, partido.getEquipoVisitante().getCategorias().getIdCategorias());
			ps.setInt(7, partido.getEquipoVisitante().getInstitucion().getIdInstituciones());
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
			throw e1;
		}

	}
	
	
	public int nuevoPartidoReturnId(Partidos partido) throws SQLException {
		int affectedRows=0;
		try {
			PreparedStatement ps = con.prepareStatement(INSERTSINGOL, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, partido.getJornada().getIdJornadas());
			ps.setInt(2, partido.getArbrito().getIdPersona());
			ps.setInt(3, partido.getEquipoLocal().getCategorias().getIdCategorias());
			ps.setInt(4, partido.getEquipoLocal().getInstitucion().getIdInstituciones());
			ps.setString(5, partido.getEquipoLocal().getNombreEquipo());
			ps.setInt(6, partido.getEquipoVisitante().getCategorias().getIdCategorias());
			ps.setInt(7, partido.getEquipoVisitante().getInstitucion().getIdInstituciones());
			ps.setString(8, partido.getEquipoVisitante().getNombreEquipo());
			ps.setInt(9, partido.getEstado().getIdTipoEstado());
			ps.setTime(10, partido.getHora());

			ps.executeUpdate();
			
            ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				affectedRows = rs.getInt(1) ;
			}

			rs.close();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
		return affectedRows;

	}

	public void editarPartido(Partidos partido) throws SQLException {
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
			throw e1;
		}
	}

	public void eliminarPartido(Partidos partido) throws SQLException, ApplicationException {
		try {

			JugadoresPartidosDAO catJugadoresPartido = new JugadoresPartidosDAO();
			LinkedList<JugadoresPartido> listaJugadoresPartido = catJugadoresPartido.buscarIDPartido(partido.getIdPartidos());
			if (listaJugadoresPartido == null && listaJugadoresPartido.size() == 0) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, partido.getIdPartidos());
				ps.executeUpdate();
				ps.close();
			}else {
				throw new ApplicationException(
						"El partido tiene Jugadores asociados");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Partidos buscarporIdsPartido(int idPartido) throws SQLException {
		Partidos partidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORIDS);
			ps.setInt(1, idPartido);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				partidos = popularPartidos(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarporIdJornada(int idJornada) throws SQLException {
		LinkedList<Partidos> partidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORJORNADA);
			ps.setInt(1, idJornada);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				partidos = new LinkedList<Partidos>();
				do{
				 partidos.add(popularPartidos(rs));
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarporIdArbrito(int idArbito) throws SQLException {
		LinkedList<Partidos> listaPartidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORARBITO);
			ps.setInt(1, idArbito);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){ 
				 listaPartidos = new LinkedList<Partidos>();
				do{
					listaPartidos.add(popularPartidos(rs));
				}while (rs.next());
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaPartidos;
	}

	public LinkedList<Partidos> buscarEquipoLocal(Equipo equipo) throws SQLException {
		LinkedList<Partidos> partidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPOREQUIPOLOCAL);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				partidos = new LinkedList<Partidos>();
				do{
					partidos.add(popularPartidos(rs));
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return partidos;
	}

	public LinkedList<Partidos> buscarEquipoVisitante(Equipo equipo) throws SQLException {
		LinkedList<Partidos> partidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPOREQUIPOVISITANTE);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				partidos = new LinkedList<Partidos>();
				do{
					partidos.add(popularPartidos(rs));
				}while (rs.next());
			}
		
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return partidos;
	}

	public LinkedList<Partidos> listarPartido() throws SQLException {
		LinkedList<Partidos> partidos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARTOPARTIDOS);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				partidos = new LinkedList<Partidos>();
				do{
					partidos.add(popularPartidos(rs));
				}while (rs.next());
			}
		
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return partidos;
	}

	public Partidos popularPartidos(ResultSet rs) throws SQLException {
		Partidos partido = new Partidos();
	
		partido.setIdPartidos(rs.getInt(1));
		JornadaDAO catJornada = new JornadaDAO();
		partido.setJornada(catJornada.buscarIdJornadas(rs.getInt(2)));
		PersonasDAO arb = new PersonasDAO();
		partido.setArbrito(arb.buscarPersonaId(rs.getInt(3)));
		EquiposDAO catEquipo = new EquiposDAO();
		partido.setEquipoLocal(catEquipo.buscarporIdsEquipo(rs.getInt(4),rs.getInt(5), rs.getString(6)));
		partido.setEquipoVisitante(catEquipo.buscarporIdsEquipo(rs.getInt(7), rs.getInt(8), rs.getString(9)));
		partido.setGolesVisitante(rs.getInt(10));
		partido.setGolesLocal(rs.getInt(11));
		TipoEstadoDAO catEstado = new TipoEstadoDAO();
		partido.setEstado(catEstado.getTipoEstados(rs.getInt(12)));
		partido.setObservaciones(rs.getString(13));
		partido.setHora(rs.getTime(14));

		return partido;

	}

}
