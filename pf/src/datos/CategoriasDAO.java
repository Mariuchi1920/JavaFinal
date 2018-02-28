package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.Categoria;
import entidad.Equipo;
import entidad.TipoEstado;
import modelo.Conexion;

public class CategoriasDAO {
	private String INSERT = "insert into categorias (anioCategorias,descripcion, estado)values (?,?,?)";
	private String DELETE = "delete from categorias where idCategorias=?;";
	private String EDITAR = "update categorias set anioCategorias= ?,descripcion=?, estado=? where idCategorias=?";
	private String LISTARTODACATEGORIA = "select * from categorias";
	private String LISTARPORCODIGOCAT = "select * from categorias where idCategorias=?";
	private Connection con;

	public CategoriasDAO() {
		Conexion c = new Conexion();
		con = c.getConexion();

	}

	public void nuevaCategoria(Categoria cat) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setString(1, cat.getAñoCategoria());
			ps.setString(2, cat.getDescripcion());
			ps.setInt(3, cat.getEstado().getIdTipoEstado());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void editarCategoria(Categoria cat) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(EDITAR);
			ps.setString(1, cat.getAñoCategoria());
			ps.setString(2, cat.getDescripcion());
			ps.setInt(3, cat.getEstado().getIdTipoEstado());
			ps.setInt(4, cat.getIdCategorias());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
			throw e;
		}
	}

	public void eliminarCategoria(Categoria cat) throws SQLException {
		try {
			EquiposDAO catEquipo = new EquiposDAO();
			LinkedList<Equipo> equipos = catEquipo.buscarporIdCategoria(cat
					.getIdCategorias());
			if (equipos == null && equipos.size() == 0) {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, cat.getIdCategorias());
				ps.executeUpdate();
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Categoria> listarTodasLasCategorias() throws SQLException {
		LinkedList<Categoria> listaCategorias = null;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(LISTARTODACATEGORIA);
			if (rs.next()) {
				listaCategorias = new LinkedList<Categoria>();
                do {
                	Categoria cat = new Categoria();
    				popularCategoria(cat, rs);
    				listaCategorias.add(cat);
                } while (rs.next());
            }
			
		} catch (SQLException ex) {
			// TODO: handle exception
		
			ex.printStackTrace();
			throw ex;
		}

		return listaCategorias;

	}

	private void popularCategoria(Categoria cat, ResultSet rs) throws SQLException {

		
		cat.setIdCategorias(rs.getInt(1));
		cat.setAñoCategoria(rs.getString(2));
		cat.setDescripcion(rs.getString(3));
		TipoEstadoDAO estadoDAO = new TipoEstadoDAO();
		TipoEstado estadoCategoria = estadoDAO.getTipoEstados(rs.getInt(4));
		cat.setEstado(estadoCategoria);

		
	}

	public Categoria buscarporIdCategoria(int idCat) throws SQLException {
		Categoria categorias = null;
		try {
			PreparedStatement ps = con.prepareStatement(LISTARPORCODIGOCAT);
			ps.setInt(1, idCat);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				categorias = new Categoria();
				popularCategoria(categorias, rs);

			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			throw ex;
		}
		return categorias;
	}
}
