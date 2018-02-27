package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Jornadas;
import entidad.TipoEstado;
import entidad.Torneo;
import modelo.Conexion;

public class TorneosDAO {
	private String INSERT = "INSERT INTO torneos (nombre, fechaInicio, fechaFin, idTipoEstado,idCategoriaCampeon, idIntitucionCampeon, nombreEquipoCampeon) VALUES (?,?,?,?,?,?,?);";
	private String DELETE = "delete from torneos where idTorneos=?;";
	private String EDITAR = "update torneos set nombre= ?,fechaInicio=?, fechaFin=? , idTipoEstado=? ,idCategoriaCampeon=?, tidIntitucionCampeon=?,nombreEquipoCampeon=? where idTorneos=?";
	private String LISTATORNEO = "select * from torneos";
	private String LISTARPORCODIGOTORNEO = "select * from torneos where idTorneos=?;";
	private String BUSCARTORNEOGANADOR = "select * from torneos where idCategoriaCampeon=? and idIntitucionCampeon=? and nombreEquipoCampeon=?;";
	private Connection con;

	public TorneosDAO() {
		// TODO Auto-generated constructor stub
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoTorneo(Torneo torneo) throws SQLException {
		try {
			System.out.println("Entre aca en  nuevo torneo");
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setString(1, torneo.getNombre());
			ps.setDate(2, torneo.getFechaInicio());
			ps.setDate(3, torneo.getFechaFin());
			ps.setInt(4, torneo.getEstado().getIdTipoEstado());
			ps.setInt(5, torneo.getEquipoGanador().getCategorias()
					.getIdCategorias());
			ps.setInt(6, torneo.getEquipoGanador().getInstitucion()
					.getIdInstituciones());
			ps.setString(7, torneo.getEquipoGanador().getNombreEquipo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void modificarTorneo(Torneo torneo) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setString(1, torneo.getNombre());
			ps.setDate(2, torneo.getFechaInicio());
			ps.setDate(3, torneo.getFechaFin());
			ps.setInt(4, torneo.getEstado().getIdTipoEstado());
			ps.setInt(5, torneo.getEquipoGanador().getCategorias().getIdCategorias());
			ps.setInt(6, torneo.getEquipoGanador().getInstitucion().getIdInstituciones());
			ps.setString(7, torneo.getEquipoGanador().getNombreEquipo());
			ps.setInt(8, torneo.getIdTorneos());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void eliminarTorneo(Torneo torneo) throws SQLException {
		try {

			if (validarElimiarTorneo(torneo)) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, torneo.getIdTorneos());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public boolean validarElimiarTorneo(Torneo torneo) throws SQLException {

		boolean respuesta = true;
		EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
		LinkedList<EquiposTorneos> listaEquipoTorneo = catEquipoTorneo.buscarporTorneo(torneo);
		if (listaEquipoTorneo != null && listaEquipoTorneo.size() > 0) {
			respuesta = false;
		}
		JornadaDAO catJornada = new JornadaDAO();
		LinkedList<Jornadas> listarJornadas = catJornada.buscarporTorneos(torneo.getIdTorneos());

		if (listarJornadas != null && listarJornadas.size() > 0) {
			respuesta = false;
		}
		return respuesta;
	}

	public LinkedList<Torneo> listarTodosLosTorneos() throws SQLException {
		LinkedList<Torneo> listarTorneos = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTATORNEO);
			if(rs.next()){
				 listarTorneos = new LinkedList<Torneo>();
				do{
					Torneo torneo = new Torneo();
					popularTorneo(torneo, rs);
					listarTorneos.add(torneo);
				}while (rs.next());
			}
			
			rs.close();
			st.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listarTorneos;

	}

	public Torneo buscarPorId(int idTorneos) throws SQLException {
		Torneo torneo = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORCODIGOTORNEO);
			ps.setInt(1, idTorneos);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				torneo = new Torneo();
				popularTorneo(torneo, rs);

			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return torneo;

	}

	public Torneo buscarPorEquipoGanador(Equipo ganador) throws SQLException {
		Torneo torneo = null;
		try {
			PreparedStatement ps = con.prepareStatement(BUSCARTORNEOGANADOR);
			ps.setInt(1, ganador.getCategorias().getIdCategorias());
			ps.setInt(2, ganador.getInstitucion().getIdInstituciones());
			ps.setString(3, ganador.getNombreEquipo());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				 torneo = new Torneo();
				popularTorneo(torneo, rs);

			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return torneo;

	}

	private void popularTorneo(Torneo torneo, ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
	
		torneo.setIdTorneos(rs.getInt(1));
		torneo.setNombre(rs.getString(2));
		torneo.setFechaInicio(rs.getDate(3));
		torneo.setFechaFin(rs.getDate(4));
		TipoEstadoDAO tedao = new TipoEstadoDAO();
		TipoEstado te = tedao.getTipoEstados(rs.getInt(5));
		torneo.setEstado(te);
		EquiposDAO edao = new EquiposDAO();
		Equipo equipoCampeon = edao.buscarporIdsEquipo(rs.getInt(6),rs.getInt(7), rs.getString(8));
		torneo.setEquipoGanador(equipoCampeon);


	}

}
