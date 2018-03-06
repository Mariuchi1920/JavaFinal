package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.InstitucionesDAO;
import datos.PersonasDAO;
import entidad.ApplicationException;
import entidad.Institucion;
import entidad.Persona;
import entidad.TipoPersona;

/**
 * Servlet implementation class ListarPersonas
 */
@WebServlet("/admin/listarPersonas/*")
public class ListarPersonas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarPersonas() {
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
		request.getRequestDispatcher("/WEB-INF/admin/maestroPersona.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Persona persona = new Persona();
			PersonasDAO perDao = new PersonasDAO();

			if (request.getParameter("editar") != null) {

				int id = Integer.parseInt(request.getParameter("editar"));
				persona = perDao.buscarPersonaId(id);
				request.getSession().setAttribute("editador", persona);
				response.sendRedirect(request.getContextPath()
						+ "/reguistarPersonas");

			} else if (request.getParameter("eliminar") != null) {
				
				int id = Integer.parseInt(request.getParameter("eliminar"));
				persona = perDao.buscarPersonaId(id);
			    if(persona.getTipoPersona().getIdTipoPersona()== TipoPersona.ADMINISTADOR){
			    	LinkedList<Persona> administrador =perDao.buscarPersonaTipoPersona(TipoPersona.ADMINISTADOR);
			    	if(administrador!=null && administrador.size()>2){
			    		perDao.eliminarPersona(persona);
						response.sendRedirect(request.getContextPath()
								+ "/admin/listarPersonas");
			    	}else {
						request.getSession().setAttribute("error", "Debe existir al menos un administrador");
						response.sendRedirect(request.getContextPath()
								+ "/admin/listarPersonas");
					}
			    	
			    	
			    }else {
			    	perDao.eliminarPersona(persona);
					response.sendRedirect(request.getContextPath()
							+ "/admin/listarPersonas");
			    }
				

				
				
				

			} else if (request.getParameter("eliminar") == null && request.getParameter("editar") == null  ){
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarPersonas");
			}

		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarPersonas");
		}catch (ApplicationException ex) {
			// TODO: handle exception
		
			request.getSession().setAttribute("error", ex.getMessage());
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarPersonas");
		}catch (Exception ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/listarPersonas");
		}
		System.out.println(request.getSession().getAttribute("error"));
	}

}
