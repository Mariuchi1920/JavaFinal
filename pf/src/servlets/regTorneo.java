package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.jasper.tagplugins.jstl.core.Out;

import entidad.Torneo;

/**
 * Servlet implementation class regTorneo
 */
@WebServlet("/regTorneo")
public class regTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regTorneo() {
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
		String nt=request.getParameter("nombreTorneo");
		Date fi= Date.valueOf(request.getParameter("fechaFi"));
		Date ff= Date.valueOf(request.getParameter("fechaFin"));
		int estado= Integer.parseInt(request.getParameter("listaTipoEStado"));
		String campeonCat=request.getParameter("campeonCat");
		String campeonInst=request.getParameter("campeonInst");
		String campeon=request.getParameter("campeon");
		
		
		//ver validacion antes de mandar datos
		
		Torneo tor= new Torneo();
		boolean rta= tor.registrarNuevoTorneo(nt, fi, ff, estado, campeonCat, campeonInst, campeon);
				
  		if(rta){
  			
			request.getRequestDispatcher("maestroTorneo.jsp").forward(request, response);
		}
		
		
		
		
		
		
	}

}
