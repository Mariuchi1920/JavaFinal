package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.PersonasDAO;
import entidad.Persona;
import entidad.TipoPersona;

/**
 * Servlet implementation class login
 */
@WebServlet("/admin")
public class PanelAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanelAdmin() {
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
		request.getRequestDispatcher("/WEB-INF/admin/menuPrincipal.jsp")
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
			HttpSession sesion = request.getSession();// obtiene la sesion de
														// ese usuario en ese
														// mometo.sesion es una
														// variable global, la
														// podemos usar en
														// cualquier parte del
														// proyecto.
			String usuario = request.getParameter("usu");
			String contrasena = request.getParameter("con");

			PersonasDAO co = new PersonasDAO();
			// como el metodo me devuelve unn boolean puede estar dentro del if
			// sin comparar
			Persona personaLogin = co.auntenticarPersona(usuario, contrasena);

			if (personaLogin != null) {
				sesion.setAttribute("usuario", personaLogin);

				if (personaLogin.getTipoPersona().getIdTipoPersona() == TipoPersona.ADMINISTADOR) {
					response.sendRedirect(request.getContextPath()
							+ "/menuPrincipal.jsp");
				} else {
					response.sendRedirect(request.getContextPath()
							+ "/menuUsuario.jsp");

				}
			} else {

				response.sendRedirect(request.getContextPath() + "/login");

			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarInstituciones");
		}catch (Exception e) {
			request.setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarInstituciones");
		}
		System.out.println(request.getSession().getAttribute("error"));
	}

}
