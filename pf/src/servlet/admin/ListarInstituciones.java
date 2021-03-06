package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.InstitucionesDAO;
import entidad.ApplicationException;
import entidad.Categoria;
import entidad.Institucion;
import sun.font.Script;

/**
 * Servlet implementation class regInstitucion
 */
@WebServlet("/admin/listarInstituciones/*")
public class ListarInstituciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarInstituciones() {
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
		request.getSession(false).removeAttribute("editador");
		request.getRequestDispatcher("/WEB-INF/admin/maestroInstituciones.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			Institucion inst = new Institucion();
			InstitucionesDAO instDAO = new InstitucionesDAO();

			if (request.getParameter("editar") != null) {

				int id = Integer.parseInt(request.getParameter("editar"));
				inst = instDAO.buscarPorId(id);
				request.getSession().setAttribute("editador", inst);
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarInstitucion");

			} else if (request.getParameter("eliminar") != null) {

				int id = Integer.parseInt(request.getParameter("eliminar"));
				inst = instDAO.buscarPorId(id);
				instDAO.eliminarInstitucion(inst);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarInstituciones");

			} else if (request.getParameter("eliminar") == null && request.getParameter("editar") == null){
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarInstituciones");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarInstituciones");
		}catch (ApplicationException ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", ex.getMessage());
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarInstituciones");
		}catch (Exception ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarInstituciones");
		}

	}

}
