package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import datos.EquiposDAO;
import datos.TipoEstadoDAO;
import datos.TorneosDAO;
import entidad.Categoria;
import entidad.Equipo;
import entidad.TipoEstado;
import entidad.Torneo;
import entidad.Util;

import java.sql.Date;

/**
 * Servlet implementation class modificarTorneo
 */
@WebServlet("/admin/modificarTorneo/*" )
public class ModificarTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarTorneo() {
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
		request.getSession(false).removeAttribute("agregarJugador");
		request.getRequestDispatcher("/WEB-INF/admin/editarTorneo.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Torneo torneo = new Torneo();
			TorneosDAO catTorneo = new TorneosDAO();
			TipoEstadoDAO catEstado = new TipoEstadoDAO();
			torneo.setNombre(request.getParameter("nombreTorneo"));
			torneo.setFechaInicio(Util.convertirStringDate(request.getParameter("fechaI")));
			torneo.setFechaFin(Util.convertirStringDate(request.getParameter("fechaF")));
			torneo.setEstado(catEstado.getTipoEstados(Integer.parseInt(request.getParameter("listaTipoEStado"))));

			String equipoCampio = request.getParameter("listarEquipos");
			
			if(equipoCampio!=null){
				EquiposDAO catEquipo = new EquiposDAO();
				String[] aux = equipoCampio.split("/");
				torneo.setEquipoGanador(catEquipo.buscarporIdsEquipo(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]),aux[2]));
			}else{
				Equipo equipo =null;
				torneo.setEquipoGanador(equipo);
			}
			
			

			if (request.getParameter("editar") != null) {
				int idTorneo = ((Torneo) request.getSession().getAttribute("editador")).getIdTorneos();
				if(Torneo.validarTorneo(torneo)){
					torneo.setIdTorneos(idTorneo);
					catTorneo.modificarTorneo(torneo);
					response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
				}else{
					response.sendRedirect(request.getContextPath() + "/admin/modificarTorneo");
				}
				

				

			} else if (request.getParameter("registar") != null) {

				if(Torneo.validarTorneo(torneo)){
					catTorneo.nuevoTorneo(torneo);
					response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
				}else{
					response.sendRedirect(request.getContextPath() + "/admin/modificarTorneo");
				}
				

			} else if (request.getParameter("agregarEquipos") != null) {

				response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");

			} else if (request.getParameter("registar") == null && request.getParameter("editar") != null && request.getParameter("agregarEquipos") == null) {
				response.sendRedirect(request.getContextPath() + "/admin/modificarTorneo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath() + "/admin/modificarTorneo");

		}catch (Exception e) {
			request.setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarTorneo");
		}
	}

}
