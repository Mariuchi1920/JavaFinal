package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.TorneosDAO;

import entidad.Torneo;

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
				Torneo tor = torneodao.buscarPorId(Integer.parseInt(request
						.getParameter("editar")));
				request.getSession().setAttribute("editador", tor);
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarTorneo");
			} else if (request.getParameter("eliminar") != null) {
				Torneo tor = new Torneo();
				tor.setIdTorneos(Integer.parseInt(request
						.getParameter("eliminar")));
				torneodao.eliminarTorneo(tor);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarTorneo");

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarTorneo");
			}
		} catch (IOException | NumberFormatException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarTorneo");
		}

	}

}
