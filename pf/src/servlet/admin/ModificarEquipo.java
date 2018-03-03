package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import datos.EquiposDAO;
import datos.EquiposJugadoresDAO;
import datos.InstitucionesDAO;
import datos.PersonasDAO;
import entidad.ApplicationException;
import entidad.Categoria;
import entidad.Equipo;
import entidad.EquiposJugadores;
import entidad.Institucion;
import entidad.Persona;

/**
 * Servlet implementation class ModificarEquipo
 */
@WebServlet("/admin/modificarEquipo/*")
public class ModificarEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarEquipo() {
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
		request.getSession(false).setAttribute("editarEntrenador", null);
		request.getSession(false).setAttribute("agregarJugador", null);
		request.getRequestDispatcher("/WEB-INF/admin/editarEquipo.jsp")
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

			if (request.getParameter("editarEntrenador") != null) {
				/*
				 * int idcat=
				 * Integer.parseInt(request.getParameter("editarEntrenador"));
				 * PersonasDAO catpersona = new PersonasDAO();
				 * catpersona.buscarPersonaId(idcat);
				 */

				request.getSession()
						.setAttribute(
								"editarEntrenador",
								((Equipo) request.getSession().getAttribute(
										"editador")));
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
				catEJ.eliminarJugadorEquipo(equipo, catpersona.buscarPersonaId(idcat));
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarEquipo");

			} else if (request.getParameter("agregarJugador") != null) {

				EquiposJugadoresDAO buscadore = new EquiposJugadoresDAO();
				EquiposJugadores equipo = new EquiposJugadores();
				equipo.setEquipo(((Equipo) request.getSession().getAttribute(
						"editador")));
				equipo.setJugadores(buscadore.listarTodasLosJugadores(equipo
						.getEquipo()));
				request.getSession().setAttribute("agregarJugador", equipo);
				response.sendRedirect(request.getContextPath()
						+ "/admin/agregarPersonasEquipo");

			} else if (request.getParameter("agregarEquipo") != null) {

				Equipo equipo = new Equipo();
				EquiposDAO catEquipo = new EquiposDAO();
				CategoriasDAO catCategoria = new CategoriasDAO();
				equipo.setCategorias(catCategoria.buscarporIdCategoria(Integer
						.parseInt(request.getParameter("listaCategorias"))));
				InstitucionesDAO catIntitucion = new InstitucionesDAO();
				equipo.setInstitucion(catIntitucion.buscarPorId(Integer
						.parseInt(request.getParameter("listaInstitucion"))));
				PersonasDAO catPersona = new PersonasDAO();
				equipo.setEntrenador(catPersona.buscarPersonaId(Integer
						.parseInt(request.getParameter("listaEntrenadores"))));
				equipo.setNombreEquipo(request.getParameter("nombreEquipo"));
				catEquipo.nuevoEquipo(equipo);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarEquipo");

			} else if (request.getParameter("agregarEquipo") == null && request.getParameter("agregarJugador") == null 
					&& request.getParameter("elimiarJugador") == null && request.getParameter("editarEntrenador") == null ) {

				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarEquipo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()+ "/admin/modificarEquipo");

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/admin/modificarEquipo");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/admin/modificarEquipo");
		}
		System.out.println(request.getSession().getAttribute("error"));

	}

}
