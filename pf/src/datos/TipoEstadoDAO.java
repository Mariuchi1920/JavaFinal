package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.Categoria;
import entidad.TipoEstado;
import modelo.Conexion;

public class TipoEstadoDAO {
	private String INSERT = "insert into tipoestado ( idTipoEstado, descripcion)values (?,?)";
	private String DELETE = "delete from tipoestado where idTipoEstado=?;";
	private String EDITAR = "update tipoestado set descripcion=? where idTipoEstado=?";
	private String LISTARTODATIPOESTADO = "select * from tipoestado";
	private String LISTARTODATIPOESTADOID = "select * from tipoestado where idTipoEstado=?";
	private Connection con;

	public TipoEstadoDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevaTipoEstado(TipoEstado tipoEstado) {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, tipoEstado.getIdTipoEstado());
			ps.setString(2, tipoEstado.getDescripcion());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarTipoEstado(TipoEstado tipoEstado) {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			// ps.setInt(1, cat.getIdcateogria());
			ps.setString(1, tipoEstado.getDescripcion());
			ps.setInt(2, tipoEstado.getIdTipoEstado());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarTipoEstado(TipoEstado tipoEstado) {
		try {

			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, tipoEstado.getIdTipoEstado());
			ps.executeUpdate();
			// ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<TipoEstado> getTipoEstados() throws SQLException {
		LinkedList<TipoEstado> lista = new LinkedList<TipoEstado>();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTODATIPOESTADO);
			while (rs.next()) {
				TipoEstado cat = new TipoEstado();
				cat.setIdTipoEstado(rs.getInt(1));
				cat.setDescripcion(rs.getString(2));

				lista.add(cat);
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		return lista;

	}

	public TipoEstado getTipoEstados(int idTipoEstado) throws SQLException {

		TipoEstado categorias = new TipoEstado();
		try {
			PreparedStatement ps = con.prepareStatement(LISTARTODATIPOESTADOID);
			ps.setInt(1, idTipoEstado);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				categorias.setIdTipoEstado(rs.getInt(1));
				categorias.setDescripcion(rs.getString(2));

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
