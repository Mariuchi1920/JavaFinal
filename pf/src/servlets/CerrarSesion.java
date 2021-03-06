package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CerrarSeccion
 */
@WebServlet("/cerrarSesion")
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CerrarSesion() {
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

		if (request.getSession(false).getAttribute("editador") != null) {
			request.getSession(false).removeAttribute("editador");
		}
		if (request.getSession(false).getAttribute("jugadorPartido") != null) {
			request.getSession(false).removeAttribute("jugadorPartido");
		}
		if (request.getSession(false).getAttribute("agregarJugador") != null) {
			request.getSession(false).removeAttribute("agregarJugador");
		}
		if (request.getSession(false).getAttribute("editarEntrenador") != null) {
			request.getSession(false).removeAttribute("editarEntrenador");
		}
	
		request.getSession(false).removeAttribute("usuario");
		request.getSession(false).invalidate();
		request.getRequestDispatcher("/WEB-INF/cerrarSesion.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
