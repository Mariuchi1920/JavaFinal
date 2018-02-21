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
import entidad.Institucion;
import entidad.Persona;
import entidad.TipoEstado;
import modelo.Conexion;

public class EquiposDAO {
	private String INSERT= "insert into equipos ( idCategorias,idIntituciones, nombreEquipo, idEntrenador)values (?,?,?,?)";
	private String DELETE="delete from equipos where idCategorias=? and idIntituciones=? and nombreEquipo=?;";
	private String EDITAR="update equipos set idEntrenador= ? where idCategorias=? and idIntituciones=? and nombreEquipo=?";
	private String LISTARTOEQUIPOS="select * from equipos";
	private String LISTARPORIDS="select * from equipos where idCategorias=? and idIntituciones=? and nombreEquipo like ?";
	private String LISTARPORCATEGORIA="select * from equipos where idCategorias=? ";
	private String LISTARPORINSITUCION="select * from equipos where idIntituciones=? ";
	private Connection con;
	
	public EquiposDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	
	public void nuevoEquipo(Equipo e) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			
			ps.setInt(1,e.getCategorias().getIdCategorias());
			ps.setInt(2, e.getInstitucion().getIdInstituciones());
			ps.setString(3,e.getNombreEquipo());
			
			ps.setInt(4, e.getEntrenador().getIdPersona());
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	public void editarEquipo(Equipo e) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setInt(1,e.getEntrenador().getIdPersona());
			ps.setInt(2,e.getCategorias().getIdCategorias());
			ps.setInt(3, e.getInstitucion().getIdInstituciones());
			ps.setString(4,e.getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void eliminarEquipo(Equipo eq) {
		try {
			
			EquiposJugadoresDAO jugadores = new EquiposJugadoresDAO();
			EquiposJugadores eqju= new EquiposJugadores();
			eqju.setEquipo(eq);
			eqju.setJugadores(jugadores.listarTodasLosJugadores(eq));
			if(eqju!=null)
			jugadores.eliminarJugadoresEquipo(eqju);
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,eq.getCategorias().getIdCategorias());
			ps.setInt(2, eq.getInstitucion().getIdInstituciones());
			ps.setString(3,eq.getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public LinkedList<Equipo> listarTodasLosEquipos(){
		LinkedList<Equipo>listaCategorias= new LinkedList<Equipo>();
		try{
			
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(LISTARTOEQUIPOS);
			while(rs.next()) {
				Equipo cat= new Equipo();

				popularEquipo(cat, rs);

				listaCategorias.add(cat);
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaCategorias;
		
	}
	
	
	
	private void popularEquipo(Equipo equipo, ResultSet rs) {
		
		// TODO Auto-generated method stub
		try {
			CategoriasDAO categoria= new CategoriasDAO();
			InstitucionesDAO institucion= new InstitucionesDAO();
			equipo.setCategorias(categoria.buscarporIdCategoria(rs.getInt(1)));
			equipo.setInstitucion(institucion.buscarPorId(rs.getInt(2)));
			equipo.setNombreEquipo(rs.getString(3));
			PersonasDAO persona=  new PersonasDAO();
			equipo.setEntrenador(persona.buscarPersonaId(rs.getInt(4)));
			
		}  catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Equipo buscarporIdsEquipo(Equipo eq) {
		Equipo equipo =new Equipo();
		try {
			
			PreparedStatement ps= con.prepareStatement(LISTARPORIDS);
			ps.setInt(1,eq.getCategorias().getIdCategorias());
			ps.setInt(2, eq.getInstitucion().getIdInstituciones());
			ps.setString(3,eq.getNombreEquipo());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				popularEquipo(equipo,rs);
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return equipo;
	}
	
	
	public LinkedList<Equipo> buscarporIdInstitucion(int institucion) {
		LinkedList<Equipo>listaCategorias= new LinkedList<Equipo>();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORINSITUCION);
			ps.setInt(1,institucion);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Equipo equipo= new Equipo();
				popularEquipo(equipo,rs);
				listaCategorias.add(equipo);
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaCategorias;
	}
	
	
	public LinkedList<Equipo> buscarporIdCategoria(int categoria) {
		LinkedList<Equipo>listaCategorias= new LinkedList<Equipo>();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORCATEGORIA);
			ps.setInt(1,categoria);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Equipo equipo= new Equipo();
				popularEquipo(equipo,rs);
				listaCategorias.add(equipo);
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaCategorias;
	}
	
	
	
	public Equipo buscarporIdsEquipo(int idInstitucion, int idCategoria, String nombre) {
		Equipo equipo =new Equipo();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORIDS);
			ps.setInt(1,idCategoria);
			ps.setInt(2, idInstitucion);
			ps.setString(3,nombre);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				popularEquipo(equipo,rs);
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return equipo;
	}
	
}


