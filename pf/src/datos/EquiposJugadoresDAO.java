package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.Persona;
import modelo.Conexion;

public class EquiposJugadoresDAO {
	
	private String INSERT= "insert into equiposjugadores (idPersona, idCategorias,idIntituciones, nombreEquipo)values (?,?,?,?)";
	private String DELETE="delete from equiposjugadores where idPersona=? and idCategorias=? and idIntituciones=? and nombreEquipo=?;";
	private String EDITAR="update equiposjugadores set idPersona= ? where idCategorias=? and idIntituciones=? and nombreEquipo=?";
	private String LISTARJUGADORESEQUIPO="select idPersona from equiposjugadores where idCategorias=? and idIntituciones=? and nombreEquipo=? ";
	private String LISTAREQJU="select * from equiposjugadores where idCategorias=? and idIntituciones=? and nombreEquipo=? ";
	private String LISTAREQUIPOJUGADOR="select idCategorias, idIntituciones, nombreEquipo from equiposjugadores where idPersona=?";
	private Connection con;
	
	public EquiposJugadoresDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	
	
	public void nuevoJugadoresEquipo(EquiposJugadores e) {
		try {
			for (int i = 0; i <e.getJugadores().size(); i++) {
				PreparedStatement ps= con.prepareStatement(INSERT);
				
				ps.setInt(1,e.getJugadores().get(i).getIdPersona());
				ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
				ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
				ps.setString(4,e.getEquipo().getNombreEquipo());

				ps.executeUpdate();
				ps.close();
			}
			
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	public void nuevoJugadorEquipo(EquiposJugadores e, Persona jugador) {
		try {
			
				PreparedStatement ps= con.prepareStatement(INSERT);
				
				ps.setInt(1,jugador.getIdPersona());
				ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
				ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
				ps.setString(4,e.getEquipo().getNombreEquipo());

				ps.executeUpdate();
				ps.close();
			
			
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	
	public void editarJugadoresEquipos(EquiposJugadores e) {
		try {
			for (int i = 0; i <e.getJugadores().size(); i++) {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setInt(1,e.getJugadores().get(i).getIdPersona());
			ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
			ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
			ps.setString(4,e.getEquipo().getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			}
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void editarJugadorEquipo(EquiposJugadores e , Persona jugador) {
		try {
			
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setInt(1,jugador.getIdPersona());
			ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
			ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
			ps.setString(4,e.getEquipo().getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void eliminarJugadoresEquipo(EquiposJugadores e) {
		try {
			
			for (int i = 0; i <e.getJugadores().size(); i++) {
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,e.getJugadores().get(i).getIdPersona());
			ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
			ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
			ps.setString(4,e.getEquipo().getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			}
			
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void eliminarJugadorEquipo(EquiposJugadores e, Persona jugador) {
		try {
			
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,jugador.getIdPersona());
			ps.setInt(2,e.getEquipo().getCategorias().getIdCategorias());
			ps.setInt(3, e.getEquipo().getInstitucion().getIdInstituciones());
			ps.setString(4,e.getEquipo().getNombreEquipo());
			ps.executeUpdate();
			ps.close();
			
			
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public LinkedList<Persona> listarTodasLosJugadores(Equipo e){
		LinkedList<Persona>listaPersona= new LinkedList<Persona>();
		try{
			
			PreparedStatement ps= con.prepareStatement(LISTARJUGADORESEQUIPO);
			ps.setInt(1,e.getCategorias().getIdCategorias());
			ps.setInt(2, e.getInstitucion().getIdInstituciones());
			ps.setString(3,e.getNombreEquipo());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
			
				listaPersona.add(popularPersona(rs));
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaPersona;
		
	}
	
	
	public LinkedList<Equipo> listarTodasLosEquipos(Persona e){
		LinkedList<Equipo>listaEquipos= new LinkedList<Equipo>();
		try{
			
			PreparedStatement ps= con.prepareStatement(LISTAREQUIPOJUGADOR);
			ps.setInt(1,e.getIdPersona());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
			
				listaEquipos.add(popularEquipo(rs));
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaEquipos;
		
	}
	
	
	/*public LinkedList<EquiposJugadores> listarTodasLosEquiposJugadores(Equipo e){
		LinkedList<EquiposJugadores>listaEquipos= new LinkedList<EquiposJugadores>();
		try{
			
			PreparedStatement ps= con.prepareStatement(LISTAREQJU);
			ps.setInt(1,e.getCategorias().getIdCategorias());
			ps.setInt(2, e.getInstitucion().getIdInstituciones());
			ps.setString(3,e.getNombreEquipo());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
			
				listaEquipos.add(popularEquipo(rs));
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
		return listaEquipos;
		
	}*/
	
   
	  public Persona popularPersona(ResultSet rs){
		
		  Persona persona= null;
    	  try {
    		  PersonasDAO catPersona = new PersonasDAO();
        	   persona =catPersona.buscarPersonaId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			return persona;
    	  
    	  
      }
	  
	  public Equipo popularEquipo(ResultSet rs){
			
		  Equipo equipo= null;
    	  try {
    		  EquiposDAO catEquipo = new EquiposDAO();
    		  
    		  equipo =catEquipo.buscarporIdsEquipo(rs.getInt(1),rs.getInt(2),rs.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			return equipo;
    	  
    	  
      }
	  
	  
	 /* public EquiposJugadores populEquiposJugadores(ResultSet rs){
		  
		  Equipo equipo= null;
		  Persona persona= null;
		  EquiposJugadores eqJ=null;
    	  try {
    		  EquiposDAO catEquipo = new EquiposDAO();
    		  
    		  equipo =catEquipo.buscarporIdsEquipo(rs.getInt(2),rs.getInt(3),rs.getString(4));
    		  PersonasDAO catPersona = new PersonasDAO();
       	      persona =catPersona.buscarPersonaId(rs.getInt(1));
       	   EquiposJugadores
       	      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	 
		  
	  }*/
	
	

}
