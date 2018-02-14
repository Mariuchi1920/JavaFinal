package servlets;

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
@WebServlet("/InstitucionServlets")
public class InstitucionServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstitucionServlets() {
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
		InstitucionesDAO instidao= new InstitucionesDAO();
		try {
			String accion=request.getParameter("accion");
			if (accion.equalsIgnoreCase("registrar")) {
				Institucion i= new Institucion();
				i.setNombre(request.getParameter("institucion"));
				i.setNombreLocalia(request.getParameter("nombreLocalia"));
				i.setDireccionLocalia(request.getParameter("direccionLocalia"));
				i.setNombreDelegado(request.getParameter("nombreDelegado"));
				i.setApellidoDelegado(request.getParameter("apellidoDelegado"));
				i.setTelefonoDelegado(request.getParameter("telefonoDelegado"));
				i.setMailDelegado(request.getParameter("mail"));
				instidao.nuevaInstitucion(i);
				RequestDispatcher rd= request.getRequestDispatcher("maestroInstituciones.jsp");
				rd.forward(request, response);
				
			} else if(accion.equalsIgnoreCase("editar")) {
				Institucion i=new Institucion();
				i.setIdInstituciones(Integer.parseInt(request.getParameter("idInstituciones")));
				i.setNombre(request.getParameter("nombre"));
				i.setNombre(request.getParameter("nombrelocalia"));
				i.setNombre(request.getParameter("direccionLocalia"));
				i.setNombre(request.getParameter("nombreDelegado"));
				i.setNombre(request.getParameter("apellidoDelegado"));
				i.setNombre(request.getParameter("telefonoDelegado"));
				i.setNombre(request.getParameter("mail"));
				instidao.modificarIstitucion(i);
				
				RequestDispatcher rd= request.getRequestDispatcher("maestroCategoria.jsp");
				rd.forward(request, response);
				
			}else if(accion.equalsIgnoreCase("eliminar")) {
				
				
				RequestDispatcher rd= request.getRequestDispatcher("maestroCategoria.jsp");
				rd.forward(request, response);
							
		}} catch (ServletException| IOException| NumberFormatException ex) {
			// TODO: handle exception
			System.err.println("Error"+ex.getMessage());
		}
				
			}
			
	
	}


