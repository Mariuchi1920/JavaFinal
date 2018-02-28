package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposTorneoDAO;
import datos.TorneosDAO;
import entidad.EquiposTorneos;
import entidad.FixtureTorneo;
import entidad.Jornadas;
import entidad.Torneo;
import entidad.Util;

/**
 * Servlet implementation class ListarTorneo
 */
@WebServlet("/admin/listarTorneo/*")
public class ListarTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarTorneo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/maestroTorneos.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TorneosDAO torneodao = new TorneosDAO();
		try {

			if (request.getParameter("editar") != null) {
				Torneo tor = torneodao.buscarPorId(Integer.parseInt(request.getParameter("editar")));
				request.getSession().setAttribute("editador", tor);
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarTorneo");
			} else if (request.getParameter("eliminar") != null) {
				Torneo tor = new Torneo();
				tor.setIdTorneos(Integer.parseInt(request.getParameter("eliminar")));
				torneodao.eliminarTorneo(tor);
				response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");

			} if (request.getParameter("fixture") != null) {
			    
				Torneo tor = torneodao.buscarPorId(Integer.parseInt(request.getParameter("fixture")));
				EquiposTorneoDAO catEquipoTorneo = new EquiposTorneoDAO();
				LinkedList<EquiposTorneos> equipoTorneo= catEquipoTorneo.buscarporTorneo(tor);
				if(equipoTorneo!=null && equipoTorneo.size()>0){
					int cantidad = equipoTorneo.size();
					if(cantidad >2){
						///int jornadas = Util.calulcarCantidadJordan(cantidad);
						 FixtureTorneo.generarFixture(equipoTorneo);
						/*LinkedList<FixtureTorneo> jornadaPartidos =  FixtureTorneo.generarJornadas(equipoTorneo);
						if(jornadaPartidos!=null){
							for(int i=0; i<jornadaPartidos.size(); i++){
								for(int j =0 ; j<jornadaPartidos.get(i).getPartidos().size();j++ ){
									System.out.println(" Jornada " +i);
								    System.out.println(" VISITANTE: " + jornadaPartidos.get(i).getPartidos().get(j).getEquipoVisitante().getNombreEquipo()  );
								    System.out.println(" LOCAL " +jornadaPartidos.get(i).getPartidos().get(j).getEquipoLocal().getNombreEquipo() +"\n" );
								}
							}
						}*/
						
					}
				}
				response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
				

			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarTorneo");
		}

	}

}
