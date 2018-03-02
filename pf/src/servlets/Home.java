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
		
		try {
			String usuario = request.getParameter("usu");
			String contrasena = request.getParameter("con");

			if (usuario !=null && contrasena!=null && !usuario.equals("") && !contrasena.equals("")) {
				PersonasDAO co = new PersonasDAO();
				// como el metodo me devuelve unn boolean puede estar dentro del
				// if sin comparar
				Persona personaLogin = co.auntenticarPersona(usuario,contrasena);

				if (personaLogin != null) {
					request.getSession().setAttribute("usuario", personaLogin);

					if (personaLogin.getTipoPersona().getIdTipoPersona() == TipoPersona.ADMINISTADOR) {

						response.sendRedirect(request.getContextPath() + "/admin");

					} else {
						response.sendRedirect(request.getContextPath()+ "/user");

					}
				} else {
					request.getSession().setAttribute("error", "No existe el Usuario y/o Contraseña");
					response.sendRedirect(request.getContextPath() + "/login");
				}

			} else {

				
				request.getSession().setAttribute("error", "Usuario y/o Contrsaeña vacio");
				response.sendRedirect(request.getContextPath() + "/login");

			}

		} catch (IOException | NumberFormatException | SQLException ex) {
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (Exception e){
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
