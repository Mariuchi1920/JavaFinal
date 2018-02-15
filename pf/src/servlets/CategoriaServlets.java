package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import entidad.Categoria;
import entidad.TipoEstado;

/**
 * Servlet implementation class CategoriaServlets
 */
@WebServlet("/CategoriaServlets/*")
public class CategoriaServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	///response.getWriter().append("Served at: ").append(request.getContextPath());
	 ///   doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoriasDAO catdao=new CategoriasDAO();
		try {
			String accion= request.getPathInfo();
		    
			if(accion.equalsIgnoreCase("/editar")) {
				Categoria categoria = catdao.buscarporIdCategoria(Integer.parseInt(request.getParameter("editar")));
				///request.setAttribute("editador", categoria);
				request.getSession().setAttribute("editador", categoria);
				response.sendRedirect(request.getContextPath() + "/editarCategoria.jsp");
				/*ServletContext context = getServletContext();
				RequestDispatcher rd= context.getRequestDispatcher("/editarCategoria.jsp");
				rd.forward(request, response);	*/
			}else if(accion.equalsIgnoreCase("/eliminar")) {
				Categoria categoria= new Categoria();
				categoria.setIdCategorias(Integer.parseInt(request.getParameter("eliminar")));
				catdao.eliminarCategoria(categoria);
				response.sendRedirect(request.getContextPath() + "/maestroCategoria.jsp");
							
		}} catch (IOException| NumberFormatException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath() + "/maestroCategoria.jsp");
		} 

		
	
	}

}
