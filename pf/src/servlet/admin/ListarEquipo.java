package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposDAO;
import entidad.Categoria;
import entidad.Equipo;

/**
 * Servlet implementation class ListarEquipo
 */
@WebServlet("/admin/listarEquipo/*")
public class ListarEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarEquipo() {
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
		request.getRequestDispatcher("/WEB-INF/admin/maestroEquipo.jsp")
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

			Equipo equipo = new Equipo();
			EquiposDAO catEquipo = new EquiposDAO();

			if (request.getParameter("editar") != null) {
				String[] editador = request.getParameter("editar").split("/");
				equipo = catEquipo.buscarporIdsEquipo(
						Integer.parseInt(editador[0]),
						Integer.parseInt(editador[1]), editador[2]);

				request.getSession().setAttribute("editador", equipo);
				// ////
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarEquipo");
			} else if (request.getParameter("eliminar") != null) {
				String[] editador = request.getParameter("editar").split("/");
				equipo = catEquipo.buscarporIdsEquipo(
						Integer.parseInt(editador[0]),
						Integer.parseInt(editador[1]), editador[2]);
				catEquipo.eliminarEquipo(equipo);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarEquipo");

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarEquipo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarEquipo");
		}

	}

}
