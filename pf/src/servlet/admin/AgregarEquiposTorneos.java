package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposDAO;
import datos.EquiposTorneoDAO;
import entidad.ApplicationException;
import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Torneo;

/**
 * Servlet implementation class AgregarEquiposTorneos
 */
@WebServlet("/admin/agregarEquiposTorneos/*")
public class AgregarEquiposTorneos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarEquiposTorneos() {
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
		request.getRequestDispatcher("/WEB-INF/admin/agregarEquipoTorneo.jsp")
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

			
			EquiposDAO equi = new EquiposDAO();
			EquiposTorneoDAO equipoE = new EquiposTorneoDAO();
			EquiposTorneos eqTor = new EquiposTorneos();
			eqTor.setTorneo(((Torneo) request.getSession().getAttribute("editador")));
			if (request.getParameter("editar") != null) {
				String equipoBuscar = request.getParameter("listaEquipos");
				String[] aux = equipoBuscar.split("/");
				Equipo equipo = equi.buscarporIdsEquipo(Integer.parseInt(aux[1]),
				Integer.parseInt(aux[0]), aux[2]);
				eqTor.setEquipos(equipo);
				if(Equipo.validarEquipoParaTorneo(equipo)){
					if(Equipo.validarEntrenador(eqTor)){
						if(Equipo.validarPersonasEquipo(eqTor)){
							equipoE.nuevoEquipoTorneo(eqTor);
						}
					}
				}
				response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");
			} else if (request.getParameter("eliminar") != null) {
				EquiposDAO catEquipo = new EquiposDAO();
				String[] aux1 = request.getParameter("eliminar").split("/");
				Equipo equipo1 = equi.buscarporIdsEquipo(Integer.parseInt(aux1[1].trim()), Integer.parseInt(aux1[0].trim()),aux1[2].trim());
				eqTor.setEquipos(equipo1);
				equipoE.eliminarEquipoTorneo(eqTor);
				response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");
			} else if (request.getParameter("volver") != null ){
				
				response.sendRedirect(request.getContextPath()+ "/admin/modificarTorneo");
				
			}else if (request.getParameter("eliminar") == null && request.getParameter("editar") == null && request.getParameter("volver") != null ){
				response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
		
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/admin/agregarPersonasEquipo");
		}catch (ApplicationException ex) {
		
			request.getSession().setAttribute("error",ex.getMessage());
			response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");
			
			
		}catch (Exception ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()+ "/admin/agregarEquiposTorneos");
		}
		
	}

}
