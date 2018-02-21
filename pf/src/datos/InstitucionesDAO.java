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

import entidad.Categoria;
import entidad.Equipo;
import entidad.Institucion;
import modelo.Conexion;

public class InstitucionesDAO {
	private String INSERT= "insert into instituciones (nombre, nombreLocalia, direccionLocalia, nombreDelegado, apellidoDelegado, telefonoDelegado, mailDelegado) VALUES (?,?,?,?,?,?,?)";
	private String DELETE="delete from instituciones where idIntituciones=?;";
	private String EDITAR="update instituciones set nombre= ?,nombreLocalia=?, direccionLocalia=? , nombreDelegado=? ,apellidoDelegado=?, telefonoDelegado=?,mailDelegado=? where idIntituciones=?";
	private String LISTATODAINSTITUCIONES= "select * from instituciones";
	private String LISTARPORCODIGOINST="select * from instituciones where idIntituciones=?;";
	private Connection con;
	
	
	public InstitucionesDAO () {
	Conexion c=new Conexion();
	con=c.getConexion();
	}
	
	
	public void nuevaInstitucion(Institucion inst) {
		try {
			System.out.println("Entre aca en  nueva categoria");
			PreparedStatement ps= con.prepareStatement(INSERT);
			ps.setString(1, inst.getNombre());
			ps.setString(2,inst.getNombreLocalia());
			ps.setString(3,inst.getDireccionLocalia());
			ps.setString(4,inst.getNombreDelegado());
			ps.setString(5,inst.getApellidoDelegado());
			ps.setString(6,inst.getTelefonoDelegado());
			ps.setString(7,inst.getMailDelegado());
			ps.executeUpdate();
			ps.close();
						} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarIstitucion(Institucion i) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setString(1,i.getNombre());
			ps.setString(2,i.getNombreLocalia());
			ps.setString(3,i.getDireccionLocalia());
			ps.setString(4,i.getNombreDelegado());
			ps.setString(5,i.getApellidoDelegado());
			ps.setString(6,i.getTelefonoDelegado());
			ps.setString(7,i.getMailDelegado());
			ps.setInt(8,i.getIdInstituciones());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
	}

	public void eliminarInstitucion(Institucion institucion) {
		try {
			EquiposDAO catEquipo= new EquiposDAO();
			LinkedList<Equipo> equipos = catEquipo.buscarporIdInstitucion(institucion.getIdInstituciones());
			if(equipos != null && equipos.size()>0) 
				for (int i = 0; i < equipos.size(); i++) {
				catEquipo.eliminarEquipo(equipos.get(i));
			}
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,institucion.getIdInstituciones());
			ps.executeUpdate();
			ps.close();
			
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	}
	public LinkedList<Institucion> listarTodasLasInstituciones(){
		LinkedList<Institucion>listaInstituciones= new LinkedList<Institucion>();
		try{
			
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(LISTATODAINSTITUCIONES);
			while(rs.next()) {
				Institucion i= new Institucion();
				popularInstitucion(i, rs);
				listaInstituciones.add(i);
			}
			rs.close();
			st.close();
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaInstituciones;
		
	}
	public Institucion buscarPorId(int idInstitucion) {
		Institucion i=new Institucion();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORCODIGOINST);
			ps.setInt(1, idInstitucion);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				popularInstitucion(i, rs);
				
			}
			rs.close();
			ps.close();
			
		}catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	
		return i;
		
	}


	private void popularInstitucion(Institucion i, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			i.setIdInstituciones(rs.getInt(1));
			i.setNombre(rs.getString(2));
			i.setNombreLocalia(rs.getString(3));
			i.setDireccionLocalia(rs.getString(4));
			i.setNombreDelegado(rs.getString(5));
			i.setApellidoDelegado(rs.getString(6));
			i.setTelefonoDelegado(rs.getString(7));
			i.setMailDelegado(rs.getString(8));
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	


}
