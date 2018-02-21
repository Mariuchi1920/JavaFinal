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
	private String INSERT= "insert into categorias ( anioCategorias,descripcion, estado)values (?,?)";
	private String DELETE="delete from categorias where idCategorias=?;";
	private String EDITAR="update categorias set anioCategorias= ?,descripcion=?, estado=? where idCategorias=?";
	private String LISTARTODACATEGORIA="select * from categorias";
	private String LISTARPORCODIGOCAT="select * from categorias where idCategorias=?";
	private Connection con;
	
	public CategoriasDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	
	public void nuevaCategoria(Categoria cat) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			ps.setString(1,cat.getAñoCategoria());
			ps.setString(2,cat.getDescripcion());
			ps.setInt(3, cat.getEstado().getIdTipoEstado());
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void editarCategoria(Categoria cat) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setString(1,cat.getAñoCategoria());
			ps.setString(2,cat.getDescripcion());
			ps.setInt(3, cat.getEstado().getIdTipoEstado());
			ps.setInt(4, cat.getIdCategorias());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarCategoria(Categoria cat) {
		try {
			EquiposDAO catEquipo= new EquiposDAO();
			LinkedList<Equipo> equipos = catEquipo.buscarporIdCategoria(cat.getIdCategorias());
			if(equipos != null && equipos.size()>0) 
				for (int i = 0; i < equipos.size(); i++) {
				catEquipo.eliminarEquipo(equipos.get(i));
			}
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,cat.getIdCategorias());
			ps.executeUpdate();
			//ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public LinkedList<Categoria> listarTodasLasCategorias(){
		LinkedList<Categoria>listaCategorias= new LinkedList<Categoria>();
		try{
			
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(LISTARTODACATEGORIA);
			while(rs.next()) {
				Categoria cat= new Categoria();

				popularCategoria(cat, rs);

				listaCategorias.add(cat);
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaCategorias;
		
	}
	private void popularCategoria(Categoria cat, ResultSet rs) {
		
		// TODO Auto-generated method stub
		try {
			
			cat.setIdCategorias(rs.getInt(1));
			cat.setAñoCategoria(rs.getString(2));
			cat.setDescripcion(rs.getString(3));
			TipoEstadoDAO estadoDAO=  new TipoEstadoDAO();
			TipoEstado estadoCategoria = estadoDAO.getTipoEstados(rs.getInt(4));
			cat.setEstado(estadoCategoria);
			
		}  catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Categoria buscarporIdCategoria(int idCat) {
		Categoria categorias =new Categoria();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORCODIGOCAT);
			ps.setInt(1,idCat);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				popularCategoria(categorias,rs);
				
				
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
