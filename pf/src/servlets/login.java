package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Consulta;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		PrintWriter out = response.getWriter(); 
		
		HttpSession sesion = request.getSession();//obtiene la sesion de ese usuario en ese mometo.sesion es una variable global, la podemos usar en cualquier parte del proyecto.
		String usuario=request.getParameter("usu");
		String contrasena= request.getParameter("con");
	
		
		Consulta co= new Consulta();
		//como el metodo me devuelve unn boolean puede estar dentro del if sin comparar 
		if(co.autenticacion(usuario, contrasena)){
			sesion.setAttribute("usuario", usuario);
			response.sendRedirect("menuPrincipal.jsp");
		}else {
			//String e= "Usuario o contrasena incorrectos";
			//response.sendRedirect("index.jsp"+e+"");
			out.print("<p style=\"color:red\">El nombre de usuario o contrasena son incorrectos</p>");  
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
            rd.include(request,response);  
			System.out.println("usuario y o contrasena incorrectos");
			
			
		}
		
	}
	

}
