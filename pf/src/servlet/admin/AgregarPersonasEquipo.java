package servlet.admin;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposDAO;
import datos.EquiposJugadoresDAO;
import datos.PersonasDAO;
import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.Persona;

/**
 * Servlet implementation class AgregarPersonasEquipo
 */
@WebServlet("/admin/agregarPersonasEquipo/*")
public class AgregarPersonasEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarPersonasEquipo() {
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
		request.getRequestDispatcher("/WEB-INF/admin/agregarPersonasEquipo.jsp")
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
			if (request.getSession().getAttribute("editarEntrenador") != null) {
				int idPersona = Integer.parseInt(request
						.getParameter("listaEntrenadores"));
				PersonasDAO catPersona = new PersonasDAO();
				Equipo equipo = (Equipo) request.getSession().getAttribute(
						"editarEntrenador");
				equipo.setEntrenador(catPersona.buscarPersonaId(idPersona));
				EquiposDAO catEquipo = new EquiposDAO();
				catEquipo.editarEquipo(equipo);
				request.getSession(false).removeAttribute("editarEntrenador");
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarEquipo");

			} else if (request.getSession().getAttribute("agregarJugador") != null) {

				int idPersona = Integer.parseInt(request
						.getParameter("listaJugadores"));
				PersonasDAO catPersona = new PersonasDAO();
				EquiposJugadoresDAO catEJ = new EquiposJugadoresDAO();
				EquiposJugadores equipo = ((EquiposJugadores) request
						.getSession().getAttribute("agregarJugador"));

				catEJ.nuevoJugadorEquipo(equipo,
						catPersona.buscarPersonaId(idPersona));
				request.getSession(false).removeAttribute("agregarJugador");
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarEquipo");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarPersonasEquipo");
			}
		} catch (IOException | NumberFormatException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/agregarPersonasEquipo");
		}
	}

}
