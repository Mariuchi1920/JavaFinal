package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import entidad.ApplicationException;
import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Jornadas;
import entidad.JugadoresPartido;
import entidad.Partidos;
import entidad.Persona;
import entidad.TipoEstado;
import entidad.Torneo;
import modelo.Conexion;

public class TorneosDAO {
	private String INSERT = "INSERT INTO torneos (nombre, fechaInicio, fechaFin, idTipoEstado,idCategoriaCampeon, idIntitucionCampeon, nombreEquipoCampeon) VALUES (?,?,?,?,?,?,?);";
	private String DELETE = "delete from torneos where idTorneos=?;";
	private String EDITAR = "update torneos set nombre= ?,fechaInicio=?, fechaFin=? , idTipoEstado=? ,idCategoriaCampeon=?, idIntitucionCampeon=?,nombreEquipoCampeon=? where idTorneos=?";
	private String EDITARSINEQUIPO = "update torneos set nombre= ?,fechaInicio=?, fechaFin=? , idTipoEstado=?  where idTorneos=?";
	private String LISTATORNEO = "select * from torneos";
	private String LISTARPORCODIGOTORNEO = "select * from torneos where idTorneos=?;";
	private String BUSCARTORNEOGANADOR = "select * from torneos where idCategoriaCampeon=? and idIntitucionCampeon=? and nombreEquipoCampeon=?;";
	private String BUSCARTODOSJUGADORESPARTIDO = "select  jugadorespartidos.idJugadores, jugadorespartidos.cantidadGoles  "
			+ "from torneos "
			+ "inner join jornadas on torneos.idTorneos = jornadas.idTorneos "
			+ "inner join partidos on jornadas.idJornadas =partidos.idJornadas  "
			+ "inner join jugadorespartidos on jugadorespartidos.idPartidos = partidos.idPartidos "
			//+ "inner join persona on persona.idPersona = jugadorespartidos.idJugadores"
			+ "where torneos.idTorneos=? ";
	
	private String BUSCARTODOSLOSPARTIDOSJUGADOS = "select partidos.idCategoriasLocal, partidos.idIntitucionesLocal, partidos.nombreEquipoLocal,"
			+ "partidos.idCategoriasVisitante,partidos.idIntitucionesVisitante, partidos.nombreEquipoVisitante ,partidos.golesLocales, partidos.golesVisitante "
			+ "from torneos inner join jornadas on torneos.idTorneos = jornadas.idTorneos"
			+ " inner join partidos on jornadas.idJornadas =partidos.idJornadas  where partidos.idTipoEstado= 3 and torneos.idTorneos=? ";
	
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
		if(torneo.getEquipoGanador()!=null){
			modificarTorneoConEqupoGanador(torneo);
		}else{
			modificarTorneoSinEquipoGanador(torneo);
		}

	}
	
	public void modificarTorneoConEqupoGanador(Torneo torneo) throws SQLException {
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
	

	public void modificarTorneoSinEquipoGanador(Torneo torneo) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITARSINEQUIPO);
			ps.setString(1, torneo.getNombre());
			ps.setDate(2, torneo.getFechaInicio());
			ps.setDate(3, torneo.getFechaFin());
			ps.setInt(4, torneo.getEstado().getIdTipoEstado());	
			
			
			ps.setInt(5, torneo.getIdTorneos());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void eliminarTorneo(Torneo torneo) throws SQLException, ApplicationException {
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

	public boolean validarElimiarTorneo(Torneo torneo) throws SQLException, ApplicationException {

		boolean respuesta = true;
		if(torneo.getEstado().getIdTipoEstado()== TipoEstado.INICIADO || 
				torneo.getEstado().getIdTipoEstado()== TipoEstado.HABILITADA){
			EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
			LinkedList<EquiposTorneos> listaEquipoTorneo = catEquipoTorneo.buscarporTorneo(torneo);
			if (listaEquipoTorneo != null && listaEquipoTorneo.size() > 0) {
				respuesta = false;
				throw new ApplicationException(
						"El Torneo tiene equipos asociados");
	
			}
			JornadaDAO catJornada = new JornadaDAO();
			LinkedList<Jornadas> listarJornadas = catJornada.buscarporTorneos(torneo.getIdTorneos());
	
			if (listarJornadas != null && listarJornadas.size() > 0) {
				respuesta = false;
				throw new ApplicationException("El Torneo tiene Jornadas (Fixture)");
			}
		}else{
			borrarRelaciones(torneo);
		}
		return respuesta;
	}
	


	private void borrarRelaciones(Torneo torneo) throws SQLException, ApplicationException {
		
		// TODO Auto-generated method stub
		
		EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
		LinkedList<EquiposTorneos> listaEquipoTorneo = catEquipoTorneo.buscarporTorneo(torneo);
		if (listaEquipoTorneo != null && listaEquipoTorneo.size() > 0) {
			for (EquiposTorneos equiposTorneos : listaEquipoTorneo) {
				catEquipoTorneo.eliminarEquipoTorneo(equiposTorneos);
			}

		}
		JornadaDAO catJornada = new JornadaDAO();
		LinkedList<Jornadas> listarJornadas = catJornada.buscarporTorneos(torneo.getIdTorneos());

		if (listarJornadas != null && listarJornadas.size() > 0) {
			
			for (Jornadas jornadas : listarJornadas) {
				  borrarJornada(jornadas);
				  catJornada.eliminarJornada(jornadas);
			}
		}
		
	}
	
	
	
	

	private void borrarJornada(Jornadas jornadas) throws SQLException, ApplicationException {
		// TODO Auto-generated method stub
		PartidoDAO catPartidos = new PartidoDAO();
		LinkedList<Partidos> listaPartidos = catPartidos.buscarporIdJornada(jornadas.getIdJornadas());
		if(listaPartidos!=null && listaPartidos.size()>0){
			for (Partidos partidos : listaPartidos) {
				borrarPartido (partidos);
				catPartidos.eliminarPartido(partidos);
			}
		}
		
		
		
	}

	private void borrarPartido(Partidos partidos) throws SQLException {
		// TODO Auto-generated method stub
		JugadoresPartidosDAO catJugadorPartigo = new JugadoresPartidosDAO();
		LinkedList<JugadoresPartido> listaJugadores = catJugadorPartigo.buscarIDPartido(partidos.getIdPartidos());
		if(listaJugadores!=null && listaJugadores.size()>0){
			for (JugadoresPartido jugadoresPartido : listaJugadores) {
				catJugadorPartigo.eliminarJugadorPartido(jugadoresPartido);
			}
		}
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
	
	
	public LinkedList<Partidos> buscarPartidosJugados(Torneo torneo) throws SQLException {
		LinkedList<Partidos> partidosJugados=null;
		try {
			PreparedStatement ps = con.prepareStatement(BUSCARTODOSLOSPARTIDOSJUGADOS);
			ps.setInt(1,torneo.getIdTorneos());
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				partidosJugados= new LinkedList<Partidos>();
				do{
					Partidos partido = popularPartidos(rs);
					partidosJugados.add(partido);
					
				}while(rs.next());
				
				

			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return partidosJugados;

	}
	
	
	
	
	public LinkedList<JugadoresPartido> buscarJugadores(Torneo torneo) throws SQLException {
		LinkedList<JugadoresPartido> jugadores=null;
		try {
			PreparedStatement ps = con.prepareStatement(BUSCARTODOSJUGADORESPARTIDO);
			ps.setInt(1,torneo.getIdTorneos());
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				jugadores= new LinkedList<JugadoresPartido>();
				do{
					JugadoresPartido persona = popularJugador(rs);
					jugadores.add(persona);
					
				}while(rs.next());
				
				

			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return jugadores;

	}
	
	public JugadoresPartido popularJugador (ResultSet rs)throws SQLException {
		JugadoresPartido persona = new JugadoresPartido();
		PersonasDAO catPersona = new PersonasDAO();
		Persona jugador = catPersona.buscarPersonaId(rs.getInt(1));
		persona.setJugadores(jugador);
		persona.setCatidadGoles(rs.getInt(2));

		return persona;
		
		
		
	}
	
	
	public Partidos popularPartidos(ResultSet rs) throws SQLException {
		Partidos partido = new Partidos();
	
		
		
		
		EquiposDAO catEquipo = new EquiposDAO();
		partido.setEquipoLocal(catEquipo.buscarporIdsEquipo(rs.getInt(1),rs.getInt(2), rs.getString(3)));
		partido.setEquipoVisitante(catEquipo.buscarporIdsEquipo(rs.getInt(4), rs.getInt(5), rs.getString(6)));
		partido.setGolesVisitante(rs.getInt(8));
		partido.setGolesLocal(rs.getInt(7));
	
		return partido;

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
