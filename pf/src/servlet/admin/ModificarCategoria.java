/**
 * 
 */
package servlet.admin;


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


@WebServlet({"/admin/modificarCategoria","/admin/modificarCategoria/*"})
public class ModificarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/admin/editarCategoria.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			String descripcion= request.getParameter("descripcion");
			String añocategoria= request.getParameter("añoCategoria");
			int estado= Integer.parseInt(request.getParameter("listaTipoEStado"));		
			CategoriasDAO catdao=new CategoriasDAO();
			Categoria cat= new Categoria();
			cat.setAñoCategoria(añocategoria);
			cat.setDescripcion(descripcion);
			TipoEstado tipoEstado;
			TipoEstadoDAO estadoDAO=  new TipoEstadoDAO();
			tipoEstado = estadoDAO.getTipoEstados(estado);
			cat.setEstado(tipoEstado);
		    
			if (request.getParameter("editar")!=null) {
				int idcat= ((Categoria)request.getSession().getAttribute("editador")).getIdCategorias();
				cat.setIdCategorias(idcat);
				catdao.editarCategoria(cat);
				response.sendRedirect(request.getContextPath() + "/admin/listarCategoria");
				
				
			}else if (request.getParameter("registar")!=null) {
				
				 catdao.nuevaCategoria(cat);
				 response.sendRedirect(request.getContextPath() + "/admin/listarCategoria");
				
				 
			}
		}catch ( IOException| NumberFormatException ex) {
				// TODO: handle exception
			ServletContext context = getServletContext();
			request.getRequestDispatcher("/WEB-INF/admin/editarCategoria.jsp").forward(request, response);
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
