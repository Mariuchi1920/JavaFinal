package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Equipo;
import modelo.Institucion;

/**
 * Servlet implementation class regEquipo
 */
@WebServlet("/regEquipo")
public class regEquipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regEquipo() {
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
		
		String in= request.getParameter("institucion");
		String equipo= request.getParameter("nombreE");
		int idInsti;
		Equipo e= new Equipo();
		Institucion i= new Institucion();
		//tengo que buscar el id de la institucion para guardar el equipo con su institucion 
		 idInsti = i.buscaIdIstitucion(in); 
		 if(idInsti==0){
			 System.out.println("No hay instituciones creadas con ese nombre");
			 System.out.println(idInsti);
		 }else {
			e.registrarNuevoEquipo(equipo, idInsti);
			System.out.println("Se ha registrado el equipo");
		}
		
		
		
		//debo buscar la id de institucion.
		
	}

}
