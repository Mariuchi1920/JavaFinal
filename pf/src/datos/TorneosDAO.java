package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.mysql.jdbc.EscapeTokenizer;

import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Jornadas;
import entidad.TipoEstado;
import entidad.Torneo;
import modelo.Conexion;

public class TorneosDAO {
	private String INSERT= "INSERT INTO torneos (nombre, fechaInicio, fechaFin, idTipoEstado,idCategoriaCampeon, idIntitucionCampeon, nombreEquipoCampeon) VALUES (?,?,?,?,?,?,?);";
	private String DELETE="delete from torneos where idTorneos=?;";
	private String EDITAR="update torneos set nombre= ?,fechaInicio=?, fechaFin=? , idTipoEstado=? ,idCategoriaCampeon=?, tidIntitucionCampeon=?,nombreEquipoCampeon=? where idTorneos=?";
	private String LISTATORNEO= "select * from torneos";
	private String LISTARPORCODIGOTORNEO="select * from torneos where idTorneos=?;";
	private Connection con;
	
	
	 public TorneosDAO() {
		// TODO Auto-generated constructor stub
		Conexion c=new Conexion();
		con=c.getConexion();
		
	}
	 public void nuevoTorneo(Torneo torneo) {
			try {
				System.out.println("Entre aca en  nuevo torneo");
				PreparedStatement ps= con.prepareStatement(INSERT);
				ps.setString(1, torneo.getNombre());
				ps.setDate(2,torneo.getFechaInicio());
				ps.setDate(3,torneo.getFechaFin());
				ps.setInt(4, torneo.getEstado().getIdTipoEstado());
				ps.setInt(5,torneo.getEquipoGanador().getCategorias().getIdCategorias());
				ps.setInt(6,torneo.getEquipoGanador().getInstitucion().getIdInstituciones());
				ps.setString(7,torneo.getEquipoGanador().getNombreEquipo());
				ps.executeUpdate();
				ps.close(); 
							} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void modificarTorneo(Torneo torneo) {
			try {
				PreparedStatement ps= con.prepareStatement(EDITAR);
				ps.setString(1,torneo.getNombre());
				ps.setDate(2,torneo.getFechaInicio());
				ps.setDate(3,torneo.getFechaFin());
				ps.setInt(4, torneo.getEstado().getIdTipoEstado());
				ps.setInt(5,torneo.getEquipoGanador().getCategorias().getIdCategorias());
				ps.setInt(6,torneo.getEquipoGanador().getInstitucion().getIdInstituciones());
				ps.setString(7,torneo.getEquipoGanador().getNombreEquipo());
				ps.setInt(8,torneo.getIdTorneos());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					
		}

		public void eliminarTorneo(Torneo t) {
			try {
				EquiposTorneoDAO equiTorneo = new EquiposTorneoDAO();
				LinkedList<EquiposTorneos> listaEquipoTorneo = equiTorneo.buscarporTorneo(t);
				if(listaEquipoTorneo!=null && listaEquipoTorneo.size()>0){
					for (int i = 0; i < listaEquipoTorneo.size(); i++) {
						equiTorneo.eliminarEquipoTorneo(listaEquipoTorneo.get(i));
					}
					
				}
				JornadaDAO catJornada = new JornadaDAO();
				LinkedList<Jornadas> jornadas= catJornada.buscarporTorneos(t.getIdTorneos());
				
				if(jornadas!=null && jornadas.size()>0){
					for (int i = 0; i < jornadas.size(); i++) {
						catJornada.eliminarJornada(jornadas.get(i));
					}
					
				}
				
				PreparedStatement ps= con.prepareStatement(DELETE);
				ps.setInt(1,t.getIdTorneos());
				ps.executeUpdate();
				ps.close();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		}
		
		public LinkedList<Torneo> listarTodosLosTorneos(){
			LinkedList<Torneo> listarTorneos= new LinkedList<Torneo>();
			try{
				
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(LISTATORNEO);
				while(rs.next()) {
					Torneo i= new Torneo();
					popularTorneo(i, rs);
					listarTorneos.add(i);
				}
				rs.close();
				st.close();
				}catch (SQLException ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			
			return listarTorneos;
			
		}
		public Torneo buscarPorId(int idTorneos) {
			Torneo i=new Torneo();
			try {
				PreparedStatement ps= con.prepareStatement(LISTARPORCODIGOTORNEO);
				ps.setInt(1, idTorneos);
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) {
					popularTorneo(i, rs);
					
				}
				rs.close();
				ps.close();
				
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		
			return i;
			
		}


		private void popularTorneo(Torneo i, ResultSet rs) {
			// TODO Auto-generated method stub
			try {
				i.setIdTorneos(rs.getInt(1));
				i.setNombre(rs.getString(2));
				i.setFechaInicio(rs.getDate(3));
				i.setFechaFin(rs.getDate(4));
				TipoEstadoDAO tedao=new TipoEstadoDAO();
				TipoEstado te= tedao.getTipoEstados(rs.getInt(5));
				i.setEstado(te);
				EquiposDAO edao=new EquiposDAO();
				Equipo equipoCam= edao.buscarporIdsEquipo(rs.getInt(6), rs.getInt(7), rs.getString(8) );
				i.setEquipoGanador(equipoCam);
				
//				i.setNombre(rs.getString(4));
//				i.setIdTorneos(rs.getInt(5));
//				i.setNombre(rs.getString(6));
//				i.setIdTorneos(rs.getInt(7));
//				i.setNombre(rs.getString(8));
//				i.setFechaInicio(rs.getDate(3));
//				i.setFechaFin(rs.getDate(4));
//				TipoEstadoDAO estadoDAO=  new TipoEstadoDAO();
//				TipoEstado estadoTorneo = estadoDAO.getTipoEstados(rs.getInt(5));
//				i.setEstado(estadoTorneo);
////				EquiposDAO edao= new EquiposDAO();
////				Equipo equipoCampeon=edao.
////				i.setEquipoGanador(edao.buscarporIdsEquipo(rs.getInt(6), rs.getInt(7), rs.getString(8)));

				
			
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		


	}

	
	

