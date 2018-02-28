package servlets;

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
@WebServlet("/login")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
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
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,
				response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
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

			if (!usuario.equals("")) {
				PersonasDAO co = new PersonasDAO();
				// como el metodo me devuelve unn boolean puede estar dentro del
				// if sin comparar
				Persona personaLogin = co.auntenticarPersona(usuario,
						contrasena);

				if (personaLogin != null) {
					sesion.setAttribute("usuario", personaLogin);

					if (personaLogin.getTipoPersona().getIdTipoPersona() == TipoPersona.ADMINISTADOR) {

						response.sendRedirect(request.getContextPath()
								+ "/admin");

					} else {
						response.sendRedirect(request.getContextPath()
								+ "/user");
						// //response.sendRedirect(request.getContextPath() +
						// "/admin");

					}
				} else {
					request.setAttribute("error", "something");
					response.sendRedirect(request.getContextPath() + "/login");
				}

			} else {

				request.setAttribute("error", "something");
				//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				response.sendRedirect(request.getContextPath() + "/login");

			}

		} catch (IOException | NumberFormatException | SQLException ex) {
			request.setAttribute("error", "something");
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (Exception e){
			request.setAttribute("error", "something");
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
