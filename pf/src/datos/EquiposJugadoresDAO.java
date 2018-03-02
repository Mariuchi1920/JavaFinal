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
import modelo.Conexion;

public class EquiposJugadoresDAO {

	private String INSERT = "insert into equiposjugadores (idPersona, idCategorias,idIntituciones, nombreEquipo)values (?,?,?,?)";
	private String DELETE = "delete from equiposjugadores where idPersona=? and idCategorias=? and idIntituciones=? and nombreEquipo=?;";
	private String EDITAR = "update equiposjugadores set idPersona= ? where idCategorias=? and idIntituciones=? and nombreEquipo=?";
	private String LISTARJUGADORESEQUIPO = "select idPersona from equiposjugadores where idCategorias=? and idIntituciones=? and nombreEquipo=? ";
	private String LISTAREQJU = "select * from equiposjugadores where idCategorias=? and idIntituciones=? and nombreEquipo=? ";
	private String LISTAREQUIPOJUGADOR = "select idCategorias, idIntituciones, nombreEquipo from equiposjugadores where idPersona=?";
	private Connection con;

	public EquiposJugadoresDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoJugadoresEquipo(EquiposJugadores equipoJugador)
			throws SQLException, ApplicationException {
		try {
			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				for (int i = 0; i < equipoJugador.getJugadores().size(); i++) {
					if (validarJugadoreNoTorneo(equipoJugador.getJugadores()
							.get(i))) {
						PreparedStatement ps = con.prepareStatement(INSERT);

						ps.setInt(1, equipoJugador.getJugadores().get(i)
								.getIdPersona());
						ps.setInt(2, equipoJugador.getEquipo().getCategorias()
								.getIdCategorias());
						ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
								.getIdInstituciones());
						ps.setString(4, equipoJugador.getEquipo()
								.getNombreEquipo());

						ps.executeUpdate();
						ps.close();
					} else {
						throw new ApplicationException(
								"El jugador se encuentra en un equipo que esta en un torneo");
					}
				}
			} else {
				throw new ApplicationException(
						"El equipo que esta en un torneo");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}

	}

	public void nuevoJugadorEquipo(EquiposJugadores equipoJugador,
			Persona jugador) throws SQLException, ApplicationException {
		try {

			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				if (validarJugadoreNoTorneo(jugador)) {
					PreparedStatement ps = con.prepareStatement(INSERT);

					ps.setInt(1, jugador.getIdPersona());
					ps.setInt(2, equipoJugador.getEquipo().getCategorias()
							.getIdCategorias());
					ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
							.getIdInstituciones());
					ps.setString(4, equipoJugador.getEquipo().getNombreEquipo());

					ps.executeUpdate();
					ps.close();
				} else {
					throw new ApplicationException(
							"El jugador se encuentra en un equipo que esta en un torneo");
				}
			} else {
				throw new ApplicationException(
						"El equipo que esta en un torneo");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}

	}

	public boolean validarJugadoreNoTorneo(Persona persona)
			throws SQLException, ApplicationException {
		boolean respuesta = true;

		EquiposJugadoresDAO catEquipoJugador = new EquiposJugadoresDAO();
		LinkedList<Equipo> listaEquipo = catEquipoJugador
				.listarTodasLosEquipos(persona);
		if (listaEquipo != null && listaEquipo.size() > 0) {
			for (Equipo equipo : listaEquipo) {

				if (!validarNoEquipoEnTorneo(equipo)) {
					respuesta = false;
					break;
				}

			}

		}

		return respuesta;

	}

	public boolean validarNoEquipoEnTorneo(Equipo equipo) throws SQLException {
		boolean respuesta = true;
		try {

			EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
			LinkedList<EquiposTorneos> encontrado;

			encontrado = catEquipoTorneo.buscarporEquipo(equipo);

			if (encontrado != null && encontrado.size() > 0) {
				respuesta = false;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return respuesta;
	}

	public void editarJugadoresEquipos(EquiposJugadores equipoJugador)
			throws SQLException, ApplicationException {
		try {
			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				for (int i = 0; i < equipoJugador.getJugadores().size(); i++) {
					PreparedStatement ps = con.prepareStatement(EDITAR);
					ps.setInt(1, equipoJugador.getJugadores().get(i)
							.getIdPersona());
					ps.setInt(2, equipoJugador.getEquipo().getCategorias()
							.getIdCategorias());
					ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
							.getIdInstituciones());
					ps.setString(4, equipoJugador.getEquipo().getNombreEquipo());
					ps.executeUpdate();
					ps.close();
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public void editarJugadorEquipo(EquiposJugadores equipoJugador,
			Persona jugador) throws SQLException, ApplicationException {
		try {
			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				PreparedStatement ps = con.prepareStatement(EDITAR);
				ps.setInt(1, jugador.getIdPersona());
				ps.setInt(2, equipoJugador.getEquipo().getCategorias()
						.getIdCategorias());
				ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
						.getIdInstituciones());
				ps.setString(4, equipoJugador.getEquipo().getNombreEquipo());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public void eliminarJugadoresEquipo(EquiposJugadores equipoJugador)
			throws SQLException, ApplicationException {
		try {
			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				for (int i = 0; i < equipoJugador.getJugadores().size(); i++) {
					PreparedStatement ps = con.prepareStatement(DELETE);
					ps.setInt(1, equipoJugador.getJugadores().get(i)
							.getIdPersona());
					ps.setInt(2, equipoJugador.getEquipo().getCategorias()
							.getIdCategorias());
					ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
							.getIdInstituciones());
					ps.setString(4, equipoJugador.getEquipo().getNombreEquipo());
					ps.executeUpdate();
					ps.close();
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public void eliminarJugadorEquipo(EquiposJugadores equipoJugador,
			Persona jugador) throws SQLException, ApplicationException {
		try {
			if (validarNoEquipoEnTorneo(equipoJugador.getEquipo())) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, jugador.getIdPersona());
				ps.setInt(2, equipoJugador.getEquipo().getCategorias()
						.getIdCategorias());
				ps.setInt(3, equipoJugador.getEquipo().getInstitucion()
						.getIdInstituciones());
				ps.setString(4, equipoJugador.getEquipo().getNombreEquipo());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public LinkedList<Persona> listarTodasLosJugadores(Equipo equipo) throws SQLException{
		LinkedList<Persona> listaPersona = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARJUGADORESEQUIPO);
			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				listaPersona = new LinkedList<Persona>();
				do {
					listaPersona.add(popularPersona(rs));
				} while (rs.next());
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaPersona;

	}

	public LinkedList<Equipo> listarTodasLosEquipos(Persona jugador)
			throws SQLException {
		LinkedList<Equipo> listaEquipos = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTAREQUIPOJUGADOR);
			ps.setInt(1, jugador.getIdPersona());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				listaEquipos = new LinkedList<Equipo>();
				do {
					listaEquipos.add(popularEquipo(rs));
				} while (rs.next());
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaEquipos;

	}

	public Persona popularPersona(ResultSet rs) throws SQLException  {

		Persona persona = null;

		PersonasDAO catPersona = new PersonasDAO();
		System.out.println(rs.getInt(1));
		persona = catPersona.buscarPersonaId(rs.getInt(1));

		return persona;

	}

	public Equipo popularEquipo(ResultSet rs) throws SQLException {

		Equipo equipo = null;

		EquiposDAO catEquipo = new EquiposDAO();

		equipo = catEquipo.buscarporIdsEquipo(rs.getInt(1), rs.getInt(2),
				rs.getString(3));

		return equipo;

	}

}
