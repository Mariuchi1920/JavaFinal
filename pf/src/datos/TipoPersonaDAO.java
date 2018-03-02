package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.TipoEstado;
import entidad.TipoPersona;
import modelo.Conexion;

public class TipoPersonaDAO {

	private String INSERT = "insert into tipopersona ( idTipoPersona, descripcion)values (?,?)";
	private String DELETE = "delete from tipopersona where idTipoPersona=?;";
	private String EDITAR = "update tipopersona set descripcion=? where idTipoPersona=?";
	private String LISTARTODATIPOPERSONA = "select * from tipopersona";
	private String RECUPERARIDPERSONA = "select * from tipopersona where idTipoPersona=?";

	private Connection con;

	public TipoPersonaDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevaTipoPersona(TipoPersona tipoPersona) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, tipoPersona.getIdTipoPersona());
			ps.setString(2, tipoPersona.getDescripcion());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void editarTipoPersona(TipoPersona tipoPersona) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			// ps.setInt(1, cat.getIdcateogria());
			ps.setString(1, tipoPersona.getDescripcion());
			ps.setInt(2, tipoPersona.getIdTipoPersona());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void eliminarTipoPersona(TipoPersona tipoPersona) throws SQLException {
		try {

			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, tipoPersona.getIdTipoPersona());
			ps.executeUpdate();
			// ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<TipoPersona> getTipoPersonas() throws SQLException {
		LinkedList<TipoPersona> listaTipoPersona = null;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTODATIPOPERSONA);
			if(rs.next()){
				listaTipoPersona = new LinkedList<TipoPersona>();
				do{

					TipoPersona cat = new TipoPersona();
					cat.setIdTipoPersona(rs.getInt(1));
					cat.setDescripcion(rs.getString(2));
					listaTipoPersona.add(cat);
				}while (rs.next());
			}
			while (rs.next()) {
				
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}

		return listaTipoPersona;

	}

	public TipoPersona getTipoPersona(int idTipoPersona) throws SQLException {

		TipoPersona tipoPersona = null;
		System.out.println("TIPO "+idTipoPersona);
		try {
			PreparedStatement ps = con.prepareStatement(RECUPERARIDPERSONA);
			ps.setInt(1, idTipoPersona);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tipoPersona = new TipoPersona();
				tipoPersona.setIdTipoPersona(rs.getInt(1));
				tipoPersona.setDescripcion(rs.getString(2));

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return tipoPersona;

	}

}
