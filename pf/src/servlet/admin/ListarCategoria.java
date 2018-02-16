package servlet.admin;



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
@WebServlet({"/admin/listarCategoria","/admin/listarCategoriaeditar/", "/admin/listarCategoriaeliminar/"})
public class ListarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/////response.sendRedirect("/admin/maestroCategoria.jsp");
	   request.getRequestDispatcher("/WEB-INF/admin/maestroCategoria.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoriasDAO catdao=new CategoriasDAO();
		try {
		    
			if(request.getParameter("editar")!=null) {
				Categoria categoria = catdao.buscarporIdCategoria(Integer.parseInt(request.getParameter("editar")));
				request.getSession().setAttribute("editador", categoria);
		
				response.sendRedirect(request.getContextPath() + "/admin/modificarCategoria");
			}else if(request.getParameter("eliminar")!=null) {
				Categoria categoria= new Categoria();
				categoria.setIdCategorias(Integer.parseInt(request.getParameter("eliminar")));
				catdao.eliminarCategoria(categoria);
				request.getRequestDispatcher("/WEB-INF/admin/maestroCategoria.jsp").forward(request, response);
							
		}} catch (IOException| NumberFormatException ex) {
			// TODO: handle exception
			request.getRequestDispatcher("/WEB-INF/admin/maestroCategoria.jsp").forward(request, response);
		} 

		
	
	}

}
