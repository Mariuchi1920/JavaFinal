package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.jasper.tagplugins.jstl.core.Out;

import modelo.Torneo;

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
		int cat= Integer.parseInt(request.getParameter("idcategoria"));
		String fi= request.getParameter("fechaIni");
		String e=request.getParameter("estado");
		boolean rta;
		Torneo torneo= new Torneo();
		
		rta=torneo.registrarNuevoTorneo(nt,fi,e,cat);
		if(rta){
			System.out.println("se ha registrado el torneo");
			request.getRequestDispatcher("nuevoTorneo.jsp").forward(request, response);
		}else{
			System.out.println("error no se registro");
		}
		
		
		
		
		
	}

}
