package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import datos.TipoEstadoDAO;
import entidad.Categoria;
import entidad.Institucion;
import entidad.TipoEstado;
import modelo.Conexion;

/**
 * Servlet implementation class regCategoria
 */
@WebServlet("/regCategoria/*")
public class regCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idcat= Integer.parseInt(request.getParameter("idCategoria"));
		String descripcion= request.getParameter("descripcion");
		int estado= Integer.parseInt(request.getParameter("listaTipoEStado"));		
		CategoriasDAO catdao=new CategoriasDAO();
		Categoria cat= new Categoria();
		cat.setIdCategorias(idcat);
		cat.setDescripcion(descripcion);
		TipoEstado tipoEstado;
		try {
			TipoEstadoDAO estadoDAO=  new TipoEstadoDAO();
			 tipoEstado = estadoDAO.getTipoEstados(estado);
			cat.setEstado(tipoEstado);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
			String accion= request.getPathInfo();
		    
			if (accion.equalsIgnoreCase("/editar")) {
				
				catdao.editarCategoria(cat);
				ServletContext context = getServletContext();
				RequestDispatcher rd= context.getRequestDispatcher("/maestroCategoria.jsp");
				rd.forward(request, response);
				
			}else if (accion.equalsIgnoreCase("/agregar")) {
				
				 catdao.nuevaCategoria(cat);
				
				 ServletContext context = getServletContext();
					RequestDispatcher rd= context.getRequestDispatcher("/maestroCategoria.jsp");
					rd.forward(request, response);;
				 
			}
		}catch (ServletException| IOException| NumberFormatException ex) {
				// TODO: handle exception
			ServletContext context = getServletContext();
			RequestDispatcher rd= context.getRequestDispatcher("/editarCategoria.jsp");
			rd.forward(request, response);
			}
	}
	

}