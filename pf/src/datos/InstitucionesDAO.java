package datos;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;

import entidad.ApplicationException;
import entidad.Categoria;
import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Institucion;
import modelo.Conexion;

public class InstitucionesDAO {
	private String INSERT = "insert into instituciones (nombre, nombreLocalia, direccionLocalia, nombreDelegado, apellidoDelegado, telefonoDelegado, mailDelegado) VALUES (?,?,?,?,?,?,?)";
	private String DELETE = "delete from instituciones where idIntituciones=?;";
	private String EDITAR = "update instituciones set nombre= ?,nombreLocalia=?, direccionLocalia=? , nombreDelegado=? ,apellidoDelegado=?, telefonoDelegado=?,mailDelegado=? where idIntituciones=?";
	private String LISTATODAINSTITUCIONES = "select * from instituciones";
	private String LISTARPORCODIGOINST = "select * from instituciones where idIntituciones=?;";
	private Connection con;

	public InstitucionesDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();
	}

	public void nuevaInstitucion(Institucion institucion) throws SQLException {
		try {
		
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setString(1, institucion.getNombre());
			ps.setString(2, institucion.getNombreLocalia());
			ps.setString(3, institucion.getDireccionLocalia());
			ps.setString(4, institucion.getNombreDelegado());
			ps.setString(5, institucion.getApellidoDelegado());
			ps.setString(6, institucion.getTelefonoDelegado());
			ps.setString(7, institucion.getMailDelegado());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void modificarIstitucion(Institucion institucion) throws SQLException, ApplicationException {
		try {
			if(validarTorneo(institucion)){
				PreparedStatement ps = con.prepareStatement(EDITAR);
				ps.setString(1, institucion.getNombre());
				ps.setString(2, institucion.getNombreLocalia());
				ps.setString(3, institucion.getDireccionLocalia());
				ps.setString(4, institucion.getNombreDelegado());
				ps.setString(5, institucion.getApellidoDelegado());
				ps.setString(6, institucion.getTelefonoDelegado());
				ps.setString(7, institucion.getMailDelegado());
				ps.setInt(8, institucion.getIdInstituciones());
				ps.executeUpdate();
				ps.close();
			}else {
				throw new ApplicationException("La intitucion se encuentra en un torneo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
	
	public boolean validarTorneo(Institucion institucion) throws SQLException{
		boolean repuesta=true;
		EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
		LinkedList<EquiposTorneos> listaEquipos = catEquipoTorneo.listarTodasLosEquipoTorneo();
		if(listaEquipos!=null && listaEquipos.size()>0){
			for (EquiposTorneos equiposTorneos : listaEquipos) {
				if(equiposTorneos.getEquipos().getInstitucion().getIdInstituciones() ==institucion.getIdInstituciones() ){
					repuesta= false;
					break;
						
				}
			}
			
		}
		
		
		return repuesta;
	}
	

	public void eliminarInstitucion(Institucion institucion) throws SQLException, ApplicationException {
		try {
			EquiposDAO catEquipo = new EquiposDAO();
			LinkedList<Equipo> equipos = catEquipo
					.buscarporIdInstitucion(institucion.getIdInstituciones());
			if (equipos == null ) {

				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, institucion.getIdInstituciones());
				ps.executeUpdate();
				ps.close();
			}else{
				throw new ApplicationException(
						"La institucion tiene equipos asociados");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public LinkedList<Institucion> listarTodasLasInstituciones() throws SQLException {
		LinkedList<Institucion> listaInstituciones = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTATODAINSTITUCIONES);
			if(rs.next()){
				listaInstituciones = new LinkedList<Institucion>();
				do{
					Institucion i = new Institucion();
					popularInstitucion(i, rs);
					listaInstituciones.add(i);
				}while (rs.next());
			}
			
			rs.close();
			st.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaInstituciones;

	}

	public Institucion buscarPorId(int idInstitucion) throws SQLException {
		Institucion i = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORCODIGOINST);
			ps.setInt(1, idInstitucion);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				 i = new Institucion();
				popularInstitucion(i, rs);

			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return i;

	}

	private void popularInstitucion(Institucion institucion, ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
	
		institucion.setIdInstituciones(rs.getInt(1));
		institucion.setNombre(rs.getString(2));
		institucion.setNombreLocalia(rs.getString(3));
		institucion.setDireccionLocalia(rs.getString(4));
		institucion.setNombreDelegado(rs.getString(5));
		institucion.setApellidoDelegado(rs.getString(6));
		institucion.setTelefonoDelegado(rs.getString(7));
		institucion.setMailDelegado(rs.getString(8));

		
	}

}
