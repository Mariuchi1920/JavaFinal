package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Institucion;
import sun.font.Script;

/**
 * Servlet implementation class regInstitucion
 */
@WebServlet("/regInstitucion")
public class EditInstitucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInstitucion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String ni=request.getParameter("nombre");
		String nl=request.getParameter("nombrelocalia");
		String dl=request.getParameter("direccionLocalia");
		String nd=request.getParameter("nombreDelegado");
		String ad=request.getParameter("apellidoDelegado");
		String td=request.getParameter("telefonoDelegado");
		String mail=request.getParameter("mail");
		boolean rta;
		
		
		Institucion inst= new Institucion();
		rta=inst.registrarInstitucion(ni,nl,dl,nd,ad,td,mail);
		if(rta){
			System.out.println("se registro con exito");
			request.getRequestDispatcher("maestroInstituciones.jsp").forward(request, response);
		}else{

			
			
			System.out.println("error no se registro la institucion.");
		}
	}

}
