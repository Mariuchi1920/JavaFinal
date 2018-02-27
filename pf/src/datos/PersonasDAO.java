package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.JugadoresPartido;
import entidad.Partidos;
import entidad.Persona;
import entidad.TipoEstado;
import entidad.TipoPersona;
import modelo.Conexion;

public class PersonasDAO {

	private String INSERT = "insert into persona (nombre, apelido, telefono, fechaNacimiento, tipoDocumento, numeroDocumento"
			+ ", mail, idTipoPersona,usuario,contraseña ) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String DELETE = "delete from persona where idPersona=?;";
	private String EDITAR = "update tipoestado set nombre =?, apelido=? , telefono=? ,fechaNacimiento=? ,tipoDocumento=? ,numeroDocumento=? ,"
			+ "mail=? ,idTipoPersona=? ,usuario=? ,contraseña=? "
			+ " where idPersona=?";
	private String LISTATODAPERSONAS = "select * from persona";
	private String LISTARPORIDPERSONA = "select * from persona where idPersona=?";
	private String LISTARPORTIPOPERSONA = "select * from persona where idTipoPersona=?";
	private String LISTARPORDNI = "select * from persona where numeroDocumento like ?;";
	private String RECUPERARUSUARIO = "select * from persona where usuario like ? and contraseña like ?";

	private Connection con;

	public PersonasDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();
	}

	public void nuevaPersona(Persona persona) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setString(3, persona.getTelefono());
			ps.setDate(4, persona.getFechaNacimiento());
			ps.setString(5, persona.getTipoDocumento());
			ps.setString(6, persona.getNumeroDocumento());
			ps.setString(7, persona.getMail());
			ps.setInt(8, persona.getTipoPersona().getIdTipoPersona());
			ps.setString(9, persona.getUsuario());
			ps.setString(10, persona.getContraseña());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void editarPersona(Persona persona) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setString(3, persona.getTelefono());
			ps.setDate(4, persona.getFechaNacimiento());
			ps.setString(5, persona.getTipoDocumento());
			ps.setString(6, persona.getNumeroDocumento());
			ps.setString(7, persona.getMail());
			ps.setInt(8, persona.getTipoPersona().getIdTipoPersona());
			ps.setString(9, persona.getUsuario());
			ps.setString(10, persona.getContraseña());
			ps.setInt(11, persona.getIdPersona());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void eliminarPersona(Persona persona) throws SQLException {
		try {

			if (validarEliminarPersona(persona)) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, persona.getIdPersona());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean validarEliminarPersona(Persona persona) throws SQLException {
		boolean repuesta = true;
		EquiposJugadoresDAO catEquipoJugadores = new EquiposJugadoresDAO();
		LinkedList<Equipo> equipoJugadores = catEquipoJugadores.listarTodasLosEquipos(persona);
		if (equipoJugadores != null && equipoJugadores.size() > 0)
			repuesta = false;
		if (repuesta) {

			EquiposDAO catEquipo = new EquiposDAO();
			LinkedList<Equipo> listarEquipo = catEquipo.buscarporIdEntrenador(persona
					.getIdPersona());
			if (listarEquipo != null && listarEquipo.size() > 0)
				repuesta = false;

		}

		if (repuesta) {

			JugadoresPartidosDAO catJugadoresPartido = new JugadoresPartidosDAO();
			LinkedList<JugadoresPartido> listarJugadoresPartido = catJugadoresPartido
					.buscarporJugador(persona.getIdPersona());
			if (listarJugadoresPartido != null && listarJugadoresPartido.size() > 0)
				repuesta = false;

		}

		if (repuesta) {

			PartidoDAO catPartido = new PartidoDAO();
			LinkedList<Partidos> listarPartidos = catPartido.buscarporIdArbrito(persona.getIdPersona());
			if (listarPartidos != null && listarPartidos.size() > 0)
				repuesta = false;

		}

		return repuesta;
	}

	public LinkedList<Persona> listarPersonas() throws SQLException {

		LinkedList<Persona> personas = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTATODAPERSONAS);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				personas = new LinkedList<Persona>();
				do{
					personas.add(personasRecuperada(rs));
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return personas;

	}

	public Persona buscarPersonaId(int id) throws SQLException {

		Persona persona = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORIDPERSONA);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				persona = personasRecuperada(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return persona;

	}

	public LinkedList<Persona> buscarPersonaTipoPersona(int id) throws SQLException {

		LinkedList<Persona> personas = new LinkedList<Persona>();
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORTIPOPERSONA);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				personas = new LinkedList<Persona>();
				do{
					Persona persona = personasRecuperada(rs);
					personas.add(persona);
					
				}while (rs.next());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return personas;

	}

	public Persona buscarPersonaDNI(String dni) throws SQLException {

		Persona persona = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORDNI);
			ps.setString(1, dni);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				persona = personasRecuperada(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return persona;

	}

	public Persona auntenticarPersona(String usuario, String contraseña) throws SQLException {

		Persona persona = null;
		try {
			PreparedStatement ps = con.prepareStatement(RECUPERARUSUARIO);
			ps.setString(1, usuario);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				persona = personasRecuperada(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return persona;

	}

	public Persona personasRecuperada(ResultSet rs) throws SQLException {
		Persona persona = new Persona();

		persona.setIdPersona(rs.getInt(1));
		persona.setNombre(rs.getString(2));
		persona.setApellido(rs.getString(3));
		persona.setTelefono(rs.getString(4));
		persona.setFechaNacimiento(rs.getDate(5));
		persona.setTipoDocumento(rs.getString(6));
		persona.setNumeroDocumento(rs.getString(7));
		persona.setMail(rs.getString(8));
		TipoPersonaDAO buscar = new TipoPersonaDAO();
		persona.setTipoPersona(buscar.getTipoPersona(rs.getInt(9)));
		persona.setUsuario(rs.getString(10));
		persona.setContraseña(rs.getString(11));
		

		return persona;

	}

}
