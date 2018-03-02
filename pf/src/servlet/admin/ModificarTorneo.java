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

			TorneosDAO catTorneo = new TorneosDAO();
			TipoEstadoDAO catEstado = new TipoEstadoDAO();
			String nombre = request.getParameter("nombreTorneo");
			Date fechaInicio = (Util.convertirStringDate(request
					.getParameter("fechaI")));
			Date fechaFin = (Util.convertirStringDate(request
					.getParameter("fechaF")));
			int estado = Integer.parseInt(request
					.getParameter("listaTipoEStado"));
			String equipoCampio = request.getParameter("listarEquipos");

			if (request.getParameter("editar") != null) {
				int idcat = ((Torneo) request.getSession().getAttribute(
						"editador")).getIdTorneos();
				Torneo torneo = new Torneo();
				torneo.setNombre(nombre);
				if (fechaInicio != null && fechaFin != null) {
					torneo.setFechaFin(fechaFin);
					torneo.setFechaInicio(fechaInicio);
				}
				torneo.setIdTorneos(idcat);
				torneo.setEstado(catEstado.getTipoEstados(estado));
				if (equipoCampio != null) {
					EquiposDAO catEquipo = new EquiposDAO();
					String[] aux = equipoCampio.split("/");
					torneo.setEquipoGanador(catEquipo.buscarporIdsEquipo(
							Integer.parseInt(aux[1]), Integer.parseInt(aux[0]),
							aux[2]));
				}

				catTorneo.modificarTorneo(torneo);
				;

				response.sendRedirect(request.getContextPath()
						+ "/admin/listarTorneo");

			} else if (request.getParameter("registar") != null) {

				//
				Torneo torneo = new Torneo();
				torneo.setNombre(nombre);
				if (fechaInicio != null && fechaFin != null) {
					torneo.setFechaFin(fechaFin);
					torneo.setFechaInicio(fechaInicio);
				}

				torneo.setEstado(catEstado.getTipoEstados(estado));
				catTorneo.nuevoTorneo(torneo);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarTorneo");

			} else if (request.getParameter("agregarEquipos") != null) {

				//

				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarEquiposTorneos");

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarTorneo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarTorneo");

		}
	}

}
