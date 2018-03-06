package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.InstitucionesDAO;
import entidad.ApplicationException;
import entidad.Categoria;
import entidad.Institucion;

/**
 * Servlet implementation class InstitucionServlets
 */
@WebServlet("/admin/modificarInstitucion/*")
public class ModificarInstitucion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarInstitucion() {
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
		request.getRequestDispatcher("/WEB-INF/admin/nuevaInstitucion.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InstitucionesDAO instidao = new InstitucionesDAO();
		try {
			Institucion i = new Institucion();
			i.setNombre(request.getParameter("institucion"));
			i.setNombreLocalia(request.getParameter("nombreLocalia"));
			i.setDireccionLocalia(request.getParameter("direccionLocalia"));
			i.setNombreDelegado(request.getParameter("nombreDelegado"));
			i.setApellidoDelegado(request.getParameter("apellidoDelegado"));
			i.setTelefonoDelegado(request.getParameter("telefonoDelegado"));
			i.setMailDelegado(request.getParameter("mail"));
			
			if(Institucion.validarInstitucion(i)){

				if (request.getParameter("editar") != null) {
					int id = ((Institucion) request.getSession().getAttribute("editador")).getIdInstituciones();
					i.setIdInstituciones(id);
	
					instidao.modificarIstitucion(i);
					response.sendRedirect(request.getContextPath()+ "/admin/listarInstituciones");
	
				} else if (request.getParameter("registar") != null) {
	
					instidao.nuevaInstitucion(i);
					response.sendRedirect(request.getContextPath()
							+ "/admin/listarInstituciones");
	
				} else if (request.getParameter("registar") == null && request.getParameter("editar") != null)  {
					response.sendRedirect(request.getContextPath()
							+ "/admin/listarInstituciones");
				}
			}else{
				
			}
			

		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarInstitucion");
		}catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarInstitucion");
		}catch (Exception e) {
			request.getSession().setAttribute("error", "error inseperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarInstitucion");
		}
		System.out.println(request.getSession().getAttribute("error"));

	}

}
