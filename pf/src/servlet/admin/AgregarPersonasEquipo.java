package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
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
			if (request.getParameter("agregarEntrenador") != null) {
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

			} else if (request.getParameter("agregarJugador") != null) {

				int idPersona = Integer.parseInt(request
						.getParameter("listaJugadores"));
				PersonasDAO catPersona = new PersonasDAO();
				EquiposJugadoresDAO catEJ = new EquiposJugadoresDAO();
				EquiposJugadores equipo = ((EquiposJugadores) request
						.getSession().getAttribute("agregarJugador"));

				catEJ.nuevoJugadorEquipo(equipo,
						catPersona.buscarPersonaId(idPersona));
				
				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarPersonasEquipo");
			} else if (request.getParameter("elimiarJugador") != null) {
				int idcat = Integer.parseInt(request
						.getParameter("elimiarJugador"));
				PersonasDAO catpersona = new PersonasDAO();
				EquiposJugadores equipo = new EquiposJugadores();
				equipo.setEquipo(((Equipo) request.getSession().getAttribute(
						"editador")));
				EquiposJugadoresDAO catEJ = new EquiposJugadoresDAO();
				catEJ.eliminarJugadorEquipo(equipo,
						catpersona.buscarPersonaId(idcat));
				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarPersonasEquipo");

			}else if (request.getParameter("elimiarJugador") == null &&
					request.getParameter("agregarJugador")  == null && 
					request.getParameter("agregarEntrenador") == null){
				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarPersonasEquipo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath()
					+ "/admin/agregarPersonasEquipo");
		}
	}

}
