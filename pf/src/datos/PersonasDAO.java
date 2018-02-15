package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidad.Persona;
import entidad.TipoEstado;
import entidad.TipoPersona;
import modelo.Conexion;

public class PersonasDAO {
	
	
	private String INSERT= "insert into persona (nombre, apelido, telefono, fechaNacimiento, tipoDocumento, numeroDocumento"
			+ ", mail, idTipoPersona,usuario,contraseña ) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String DELETE="delete from persona where idPersona=?;";
	private String EDITAR="update tipoestado set nombre =?, apelido=? , telefono=? ,fechaNacimiento=? ,tipoDocumento=? ,numeroDocumento=? ,"
			+ "mail=? ,idTipoPersona=? ,usuario=? ,contraseña=? "
			+ " where idPersona=?";
	private String LISTATODAPERSONAS= "select * from persona";
	private String LISTARPORIDPERSONA="select * from persona where idPersona=?;";
	private String RECUPERARUSUARIO = "select * from persona where usuario=? and contraseña= ?";
	private Connection con;
	
	
	
	
	
	public PersonasDAO () {
	Conexion c=new Conexion();
	con=c.getConexion();
	}
	
	
	
	public void nuevaPersona(Persona persona) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			ps.setString(1, persona.getNombre());
			ps.setString(2,persona.getApellido());
			ps.setString(3,persona.getTelefono());
			ps.setDate(4,persona.getFechaNacimiento());
			ps.setString(5,persona.getTipoDocumento());
			ps.setString(6,persona.getNumeroDocumento());
			ps.setString(7,persona.getMail());
			ps.setInt(8,persona.getTipoPersona().getIdTipoPersona());
			ps.setString(9,persona.getUsuario());
			ps.setString(10,persona.getContraseña());
			
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void editarPersona(Persona persona) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setString(1, persona.getNombre());
			ps.setString(2,persona.getApellido());
			ps.setString(3,persona.getTelefono());
		    ps.setDate(4,persona.getFechaNacimiento());
			ps.setString(5,persona.getTipoDocumento());
			ps.setString(6,persona.getNumeroDocumento());
			ps.setString(7,persona.getMail());
			ps.setInt(8,persona.getTipoPersona().getIdTipoPersona());
			ps.setString(9,persona.getUsuario());
			ps.setString(10,persona.getContraseña());
			ps.setInt(11, persona.getIdPersona());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarPersona(Persona persona) {
		try {
			
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1, persona.getIdPersona());
			ps.executeUpdate();
			//ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
      public Persona auntenticarPersona(String usuario, String contraseña){
		
    	  Persona categorias =new Persona();
  		try {
  			PreparedStatement ps= con.prepareStatement(RECUPERARUSUARIO);
  			ps.setString(1, usuario);
  			ps.setString(2,contraseña);
  			ResultSet rs= ps.executeQuery();
  			while (rs.next()) {
  				categorias.setIdPersona(rs.getInt(1));
  				categorias.setNombre(rs.getString(2));
  				categorias.setApellido(rs.getString(3));
  				categorias.setTelefono(rs.getString(4));
  				categorias.setFechaNacimiento(rs.getDate(5));
  				categorias.setTipoDocumento(rs.getString(6));
  				categorias.setNumeroDocumento(rs.getString(7));
  				categorias.setMail(rs.getString(8));
  				TipoPersonaDAO buscar = new TipoPersonaDAO();
  				categorias.setTipoPersona(buscar.getTipoEstados(rs.getInt(9)));
  				categorias.setUsuario(rs.getString(10));
  				categorias.setContraseña(rs.getString(11));
  				
  			}
  			rs.close();
  			ps.close();
  		} catch (SQLException ex) {
  			// TODO: handle exception
  			ex.printStackTrace();
  		}
  		return categorias;
		
	}
	

}
