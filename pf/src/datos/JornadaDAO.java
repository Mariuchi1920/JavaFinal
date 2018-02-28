package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import modelo.Conexion;
import entidad.Categoria;
import entidad.Equipo;
import entidad.Jornadas;
import entidad.Partidos;

public class JornadaDAO {

	private String INSERT = "insert into jornadas (idTorneos,fechaDescripcion, idTipoEstado)values (?,?,?)";
	private String DELETE = "delete from jornadas where idJornadas=?;";
	private String EDITAR = "update jornadas set fechaDescripcion=?, idTipoEstado=? where idJornadas=?";
	private String LISTARTOJORNADA = "select * from jornadas";
	private String LISTARPORTORNEO = "select * from jornadas where idTorneos=?";
	private String LISTARPORESTADO = "select * from jornadas where idTipoEstado=?";
	private String LISTARIDJORNADA = "select * from jornadas where idJornadas=?";

	private Connection con;

	public JornadaDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevaJornada(Jornadas jornada) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, jornada.getTorneos().getIdTorneos());
			ps.setDate(2, jornada.getFechaDescripcion());
			ps.setInt(3, jornada.getEstado().getIdTipoEstado());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
	public int nuevaJornadaDevuelveId(Jornadas jornada) throws SQLException {
		int affectedRows = 0;
		try {
			
			PreparedStatement ps = con.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, jornada.getTorneos().getIdTorneos());
			ps.setDate(2, jornada.getFechaDescripcion());
			ps.setInt(3, jornada.getEstado().getIdTipoEstado());
			
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				affectedRows = rs.getInt(1) ;
			}
			
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return affectedRows;
	}

	public void editarJornada(Jornadas jornada) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setDate(1, jornada.getFechaDescripcion());
			ps.setInt(2, jornada.getEstado().getIdTipoEstado());
			ps.setInt(3, jornada.getIdJornadas());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void eliminarJornada(Jornadas jornada) throws SQLException {
		try {

			PartidoDAO catPartido = new PartidoDAO();
			LinkedList<Partidos> partidos = catPartido
					.buscarporIdJornada(jornada.getIdJornadas());
			if (partidos == null && partidos.size() == 0) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, jornada.getIdJornadas());
				ps.executeUpdate();
				ps.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Jornadas> listarTodasLasJornadas() throws SQLException {
		LinkedList<Jornadas> listaJornadas = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTOJORNADA);
			if(rs.next()){
				listaJornadas = new LinkedList<Jornadas>();
				do{
					listaJornadas.add(popularJornadas(rs));
				}while (rs.next()) ;
			}
			
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaJornadas;

	}

	public Jornadas buscarIdJornadas(int idJornada) throws SQLException {
		Jornadas jornada = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARIDJORNADA);
			ps.setInt(1, idJornada);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				jornada = popularJornadas(rs);
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return jornada;

	}

	public LinkedList<Jornadas> buscarporTorneos(int idTorneo) throws SQLException {
		LinkedList<Jornadas> listaJornadas = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORTORNEO);
			ps.setInt(1, idTorneo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaJornadas = new LinkedList<Jornadas>();
				do{
					listaJornadas.add(popularJornadas(rs));
				}while (rs.next()) ;
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaJornadas;
	}

	public LinkedList<Jornadas> buscarporEstado(int idEstado) throws SQLException {
		LinkedList<Jornadas> listaJornadas = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORESTADO);
			ps.setInt(1, idEstado);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				 listaJornadas = new LinkedList<Jornadas>();
				do{
					listaJornadas.add(popularJornadas(rs));
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaJornadas;
	}

	public Jornadas popularJornadas(ResultSet rs) throws SQLException {

		Jornadas jornada = new Jornadas();
		
		jornada.setIdJornadas(rs.getInt(1));
		TorneosDAO catTorneo = new TorneosDAO();
		jornada.setTorneos(catTorneo.buscarPorId(rs.getInt(2)));
		jornada.setFechaDescripcion(rs.getDate(3));
		TipoEstadoDAO estado = new TipoEstadoDAO();
		jornada.setEstado(estado.getTipoEstados(rs.getInt(4)));
		
		return jornada;

	}

}
