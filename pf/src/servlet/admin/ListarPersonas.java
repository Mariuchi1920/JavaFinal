package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.InstitucionesDAO;
import datos.PersonasDAO;
import entidad.Institucion;
import entidad.Persona;

/**
 * Servlet implementation class ListarPersonas
 */
@WebServlet("/admin/listarPersonas")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/admin/maestroPersona.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Persona persona = new Persona();
			PersonasDAO perDao = new PersonasDAO();
			

			if (request.getParameter("editar") != null) {

				int id=Integer.parseInt(request.getParameter("editar"));
				persona= perDao.buscarPersonaId(id);
				request.getSession().setAttribute("editador", persona);
				response.sendRedirect(request.getContextPath() + "/reguistarPersonas");

			} else if (request.getParameter("eliminar") != null) {

				int id=Integer.parseInt(request.getParameter("eliminar"));
				persona= perDao.buscarPersonaId(id);
				perDao.eliminarPersona(persona);
				response.sendRedirect(request.getContextPath() + "/admin/listarPersonas");

			}
	
	} catch (IOException | NumberFormatException ex) {
		// TODO: handle exception
		response.sendRedirect(request.getContextPath() + "/admin/listarInstituciones");
	}

	}

}
