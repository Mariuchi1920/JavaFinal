package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.JugadoresPartido;
import modelo.Conexion;

public class JugadoresPartidosDAO {
	
	
	private String INSERT= "insert into jugadorespartidos ( idPartidos,idJugadores, cantidadTarjetasAmarillas, cantidadTarjetasRojas,cantidadGoles)values (?,?,?,?,?)";
	private String DELETE="delete from jugadorespartidos where idPartidos=? and idJugadores=? ";
	private String EDITAR="update jugadorespartidos set cantidadTarjetasAmarillas= ?,cantidadTarjetasRojas=?, cantidadGoles=? where idPartidos=? and idJugadores=? ";
	private String LISTARTOEQUIPOS="select * from jugadorespartidos";
	private String LISTARPORJUGADOR="select * from jugadorespartidos where idJugadores=? ";
	private String LISTARPORPARTIDO="select * from jugadorespartidos where idPartidos=? ";
	
	
    private Connection con;
	
	public JugadoresPartidosDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	} 
	
	public void nuevoJugadorPartido(JugadoresPartido jugPar) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			ps.setInt(1, jugPar.getPartido().getIdPartidos());
			ps.setInt(2,jugPar.getJugadores().getIdPersona());
			ps.setInt(3,jugPar.getCantidadTarjetasAmarillas());
			ps.setInt(4,jugPar.getCantidadTarjetasRojas());
			ps.setInt(4,jugPar.getCatidadGoles());
			
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	public void editarJugadorPartido(JugadoresPartido jugPar) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setInt(1,jugPar.getCantidadTarjetasAmarillas());
			ps.setInt(2,jugPar.getCantidadTarjetasRojas());
			ps.setInt(3,jugPar.getCatidadGoles());
			ps.setInt(4, jugPar.getPartido().getIdPartidos());
			ps.setInt(5,jugPar.getJugadores().getIdPersona());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void eliminarJugadorPartido(JugadoresPartido jugPar) {
		try {
			
			
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1, jugPar.getPartido().getIdPartidos());
			ps.setInt(2,jugPar.getJugadores().getIdPersona());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public LinkedList<JugadoresPartido> buscarporJugador(int idJugador) {
		LinkedList<JugadoresPartido>listaCategorias= new LinkedList<JugadoresPartido>();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORJUGADOR);
			ps.setInt(1,idJugador);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaCategorias.add(popularJugadorEquipo(rs));
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaCategorias;
	}
	
	public LinkedList<JugadoresPartido> buscarIDPartido(int idPartido) {
		LinkedList<JugadoresPartido>listaCategorias= new LinkedList<JugadoresPartido>();
		try {
			PreparedStatement ps= con.prepareStatement(LISTARPORPARTIDO);
			ps.setInt(1,idPartido);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaCategorias.add(popularJugadorEquipo(rs));
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaCategorias;
	}
	
	
	public JugadoresPartido popularJugadorEquipo(ResultSet rs){
		JugadoresPartido juPa = new JugadoresPartido();
		PartidoDAO catPartido = new PartidoDAO();
		PersonasDAO catJugador = new PersonasDAO();
		
		try {
			juPa.setPartido(catPartido.buscarporIdsPartido(rs.getInt(1)));
			juPa.setJugadores(catJugador.buscarPersonaId(rs.getInt(2)));
			juPa.setCantidadTarjetasAmarillas(rs.getInt(3));
			juPa.setCantidadTarjetasRojas(rs.getInt(4));
			juPa.setCatidadGoles(rs.getInt(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return juPa;
		
		
		
	}
	

}
