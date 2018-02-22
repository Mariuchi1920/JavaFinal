package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import modelo.Conexion;
import entidad.Categoria;
import entidad.Equipo;
import entidad.Jornadas;

public class JornadaDAO {
	
	
	private String INSERT= "insert into jornadas (idTorneos,fechaDescripcion, idTipoEstado)values (?,?,?)";
	private String DELETE="delete from jornadas where idJornadas=?;";
	private String EDITAR="update jornadas set fechaDescripcion=?, idTipoEstado=? where idJornadas=?";
	private String LISTARTOJORNADA="select * from jornadas";
	private String LISTARPORTORNEO="select * from jornadas where idTorneos=?";
	private String LISTARPORESTADO="select * from jornadas where idTipoEstado=?";
	private String LISTARIDJORNADA="select * from jornadas where idJornadas=?";
	
	
    private Connection con;
	
	public JornadaDAO() {
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	
	
	public void nuevaJornada(Jornadas jornada) {
		try {
			PreparedStatement ps= con.prepareStatement(INSERT);
			ps.setInt(1,jornada.getTorneos().getIdTorneos());
			ps.setDate(2,jornada.getFechaDescripcion());
			ps.setInt(3, jornada.getEstado().getIdTipoEstado());
			ps.executeUpdate();
			ps.close();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void editarJornada(Jornadas jornada) {
		try {
			PreparedStatement ps= con.prepareStatement(EDITAR);
			ps.setDate(1,jornada.getFechaDescripcion());
			ps.setInt(2,jornada.getEstado().getIdTipoEstado());
			ps.setInt(3, jornada.getIdJornadas());
			ps.executeUpdate();
			ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarJornada(Jornadas jornada) {
		try {
			/* Aca hay eliminar todas las relaciones   EquiposDAO catEquipo= new EquiposDAO();
			LinkedList<Equipo> equipos = catEquipo.buscarporIdCategoria(cat.getIdCategorias());
			if(equipos != null && equipos.size()>0) 
				for (int i = 0; i < equipos.size(); i++) {
				catEquipo.eliminarEquipo(equipos.get(i));
			}*/
			PreparedStatement ps= con.prepareStatement(DELETE);
			ps.setInt(1,jornada.getIdJornadas());
			ps.executeUpdate();
			//ps.close();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public LinkedList<Jornadas> listarTodasLasJornadas(){
		LinkedList<Jornadas>listaJornadas= new LinkedList<Jornadas>();
		try{
			
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(LISTARTOJORNADA);
			while(rs.next()) {

				listaJornadas.add(popularJornadas(rs));
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			} 
		
		return listaJornadas;
		
	}
	
	public Jornadas buscarIdJornadas(int idJornada){
		Jornadas listaJornadas= new Jornadas();
		try{
			
			PreparedStatement ps= con.prepareStatement(LISTARIDJORNADA);
			ps.setInt(1,idJornada);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {

				listaJornadas= popularJornadas(rs);
			}
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			} 
		
		return listaJornadas;
		
	}
	
	
	
	public LinkedList<Jornadas> buscarporTorneos(int idTorneo) {
		LinkedList<Jornadas>listaJornadas= new LinkedList<Jornadas>();
		try {
			
			PreparedStatement ps= con.prepareStatement(LISTARPORTORNEO);
			ps.setInt(1,idTorneo);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaJornadas.add(popularJornadas(rs));
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaJornadas;
	}
	
	
	public LinkedList<Jornadas> buscarporEstado(int idEstado) {
		LinkedList<Jornadas>listaJornadas= new LinkedList<Jornadas>();
		try {
			
			PreparedStatement ps= con.prepareStatement(LISTARPORESTADO);
			ps.setInt(1,idEstado);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				
				listaJornadas.add(popularJornadas(rs));
				
				
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return listaJornadas;
	}
	
	
	
	public Jornadas popularJornadas(ResultSet rs){
		
		Jornadas jornada = new Jornadas();
		try {
			jornada.setIdJornadas(rs.getInt(1));
			TorneosDAO catTorneo = new TorneosDAO();
			jornada.setTorneos(catTorneo.buscarPorId(rs.getInt(2)));
			jornada.setFechaDescripcion(rs.getDate(3));
			TipoEstadoDAO estado = new TipoEstadoDAO();
			jornada.setEstado(estado.getTipoEstados(rs.getInt(4)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jornada;
		
		
		
		
	}
	
	

}
