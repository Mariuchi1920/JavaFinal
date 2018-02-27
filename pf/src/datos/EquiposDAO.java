package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.Categoria;
import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.EquiposTorneos;
import entidad.Institucion;
import entidad.Partidos;
import entidad.Persona;
import entidad.TipoEstado;
import entidad.Torneo;
import modelo.Conexion;

public class EquiposDAO {
	private String INSERT = "insert into equipos ( idCategorias,idIntituciones, nombreEquipo, idEntrenador)values (?,?,?,?)";
	private String DELETE = "delete from equipos where idCategorias=? and idIntituciones=? and nombreEquipo=?;";
	private String EDITAR = "update equipos set idEntrenador= ? where idCategorias=? and idIntituciones=? and nombreEquipo=?";
	private String LISTARTOEQUIPOS = "select * from equipos";
	private String LISTARPORIDS = "select * from equipos where idCategorias=? and idIntituciones=? and nombreEquipo like ?";
	private String LISTARPORCATEGORIA = "select * from equipos where idCategorias=? ";
	private String LISTARPORINSITUCION = "select * from equipos where idIntituciones=? ";
	private String LISTARPORENTRENADOR = "select * from equipos where idEntrenador=? ";
	private Connection con;

	public EquiposDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevoEquipo(Equipo equipo) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);

			ps.setInt(1, equipo.getCategorias().getIdCategorias());
			ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
			ps.setString(3, equipo.getNombreEquipo());
			ps.setInt(4, equipo.getEntrenador().getIdPersona());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}

	}

	public void editarEquipo(Equipo equipo) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setInt(1, equipo.getEntrenador().getIdPersona());
			ps.setInt(2, equipo.getCategorias().getIdCategorias());
			ps.setInt(3, equipo.getInstitucion().getIdInstituciones());
			ps.setString(4, equipo.getNombreEquipo());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	public void eliminarEquipo(Equipo equipo) throws SQLException {
		try {
			if (validarEliminar(equipo)) {
	
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, equipo.getCategorias().getIdCategorias());
				ps.setInt(2, equipo.getInstitucion().getIdInstituciones());
				ps.setString(3, equipo.getNombreEquipo());
				ps.executeUpdate();
				ps.close();
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean validarEliminar(Equipo equipo) throws SQLException {

		boolean respuesta = true;

		EquiposJugadoresDAO catEquiposJugadores = new EquiposJugadoresDAO();
		EquiposJugadores equipoJugadores = new EquiposJugadores();
		equipoJugadores.setEquipo(equipo);
		equipoJugadores.setJugadores(catEquiposJugadores.listarTodasLosJugadores(equipo));
		if (equipoJugadores != null)
			respuesta = false;
		if (respuesta) {
			EquiposTorneoDAO equipoTorneo = new EquiposTorneoDAO();
			LinkedList<EquiposTorneos> listaEquipoTorneo = equipoTorneo.buscarporEquipo(equipo);
			if (listaEquipoTorneo != null && listaEquipoTorneo.size() > 0) {
				respuesta = false;

			}
		}
		if (respuesta) {
			TorneosDAO catTorneo = new TorneosDAO();
			Torneo torneo = catTorneo.buscarPorEquipoGanador(equipo);
			if (torneo != null)
				respuesta = false;
		}
		if (respuesta) {
			PartidoDAO catPartido = new PartidoDAO();
			LinkedList<Partidos> partido = catPartido.buscarEquipoLocal(equipo);
			if (partido != null && partido.size() > 0)
				respuesta = false;
			if (respuesta) {
				partido = catPartido.buscarEquipoLocal(equipo);
				if (partido != null && partido.size() > 0)
					respuesta = false;
			}
		}

		return respuesta;

	}

	public LinkedList<Equipo> listarTodasLosEquipos() throws SQLException {
		LinkedList<Equipo> listaEquipos = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTOEQUIPOS);
			if(rs.next()){
				listaEquipos = new LinkedList<Equipo>();
				 do {
					 Equipo cat = new Equipo();

						popularEquipo(cat, rs);

						listaEquipos.add(cat);
				 }while (rs.next());
				
			
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaEquipos;

	}

	private void popularEquipo(Equipo equipo, ResultSet rs) throws SQLException {

		// TODO Auto-generated method stub
	
			CategoriasDAO categoria = new CategoriasDAO();
			InstitucionesDAO institucion = new InstitucionesDAO();
			equipo.setCategorias(categoria.buscarporIdCategoria(rs.getInt(1)));
			equipo.setInstitucion(institucion.buscarPorId(rs.getInt(2)));
			equipo.setNombreEquipo(rs.getString(3));
			PersonasDAO persona = new PersonasDAO();
			equipo.setEntrenador(persona.buscarPersonaId(rs.getInt(4)));

		
	}

	public Equipo buscarporIdsEquipo(Equipo equipoBuscar) throws SQLException {
		Equipo equipo = null;
		try {

			PreparedStatement ps = con.prepareStatement(LISTARPORIDS);
			ps.setInt(1, equipoBuscar.getCategorias().getIdCategorias());
			ps.setInt(2, equipoBuscar.getInstitucion().getIdInstituciones());
			ps.setString(3, equipoBuscar.getNombreEquipo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				equipo = new Equipo();
				popularEquipo(equipo, rs);

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return equipo;
	}

	public LinkedList<Equipo> buscarporIdInstitucion(int institucion) throws SQLException {
		LinkedList<Equipo> listaEquipos = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORINSITUCION);
			ps.setInt(1, institucion);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				 listaEquipos = new LinkedList<Equipo>();
				do{
					Equipo equipo = new Equipo();
					popularEquipo(equipo, rs);
					listaEquipos.add(equipo);
				}while (rs.next());
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

	public LinkedList<Equipo> buscarporIdEntrenador(int idEntrenador) throws SQLException {
		LinkedList<Equipo> listaEquipo = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORENTRENADOR);
			ps.setInt(1, idEntrenador);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaEquipo = new LinkedList<Equipo>();
				do{
					Equipo equipo = new Equipo();
					popularEquipo(equipo, rs);
					listaEquipo.add(equipo);
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaEquipo;
	}

	public LinkedList<Equipo> buscarporIdCategoria(int categoria) throws SQLException {
		LinkedList<Equipo> listaEquipo = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORCATEGORIA);
			ps.setInt(1, categoria);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				listaEquipo = new LinkedList<Equipo>();
				do{
					Equipo equipo = new Equipo();
					popularEquipo(equipo, rs);
					listaEquipo.add(equipo);
				}while (rs.next()) ;
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return listaEquipo;
	}

	public Equipo buscarporIdsEquipo(int idCategoria, int idInstitucion, String nombre) throws SQLException {
		Equipo equipo = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORIDS);
			ps.setInt(1, idCategoria);
			ps.setInt(2, idInstitucion);
			ps.setString(3, nombre);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				equipo = new Equipo();
				popularEquipo(equipo, rs);

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return equipo;
	}

}
