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
import entidad.ApplicationException;
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
						
						 FixtureTorneo.generarFixture(equipoTorneo);
						 request.getSession().setAttribute("editador", tor);
						 response.sendRedirect(request.getContextPath()+ "/admin/mostrarFixture");
						
					}else{
						request.getSession().setAttribute("error", "La cantidad de equipos en el Torneo debe ser mayor a 2");
						response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
					}
				}else{
					request.getSession().setAttribute("error", "Primero debe agregar equipos al torneo");
					response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
				}
				
			}else if(request.getParameter("verfixture") != null){
				Torneo tor = torneodao.buscarPorId(Integer.parseInt(request.getParameter("verfixture")));
				request.getSession().setAttribute("editador", tor);
				response.sendRedirect(request.getContextPath()+ "/admin/mostrarFixture");
				
				
			}else if(request.getParameter("verfixture") == null && request.getParameter("fixture") == null && request.getParameter("eliminar") == null && request.getParameter("editar") == null) {
				response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
		}catch (Exception e){
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
		}

	}

}
