package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.InstitucionesDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/nuevaInstitucion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InstitucionesDAO instidao= new InstitucionesDAO();
		try {
			Institucion i= new Institucion();
			i.setNombre(request.getParameter("institucion"));
			i.setNombreLocalia(request.getParameter("nombreLocalia"));
			i.setDireccionLocalia(request.getParameter("direccionLocalia"));
			i.setNombreDelegado(request.getParameter("nombreDelegado"));
			i.setApellidoDelegado(request.getParameter("apellidoDelegado"));
			i.setTelefonoDelegado(request.getParameter("telefonoDelegado"));
			i.setMailDelegado(request.getParameter("mail"));
			
			
			if (request.getParameter("editar")!=null) {
				 int id= ((Institucion)request.getSession().getAttribute("editador")).getIdInstituciones();
				 i.setIdInstituciones(id);
				
				 instidao.modificarIstitucion(i);
				 response.sendRedirect(request.getContextPath() + "/admin/listarInstituciones");
				
				
			}else if (request.getParameter("registar")!=null) {
				
				 instidao.nuevaInstitucion(i);
				 response.sendRedirect(request.getContextPath() + "/admin/listarInstituciones");
				
				 
			}
			
			
		} catch ( IOException| NumberFormatException ex) {
			// TODO: handle exception
			System.err.println("Error"+ex.getMessage());
		}
				
			}
			
	
	}






