package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		int idcat= Integer.parseInt(request.getParameter("idCategoria"));
		String descripcion= request.getParameter("descripcion");
		int estado= Integer.parseInt(request.getParameter("listaTipoEStado"));		
		CategoriasDAO catdao=new CategoriasDAO();
		Categoria cat= new Categoria();
		boolean rta=false;
	
		try {
			String accion= request.getPathInfo();
		    
			if (accion.equalsIgnoreCase("/editar")) {
				cat.setIdcateogria(idcat);
				cat.setDescripcion(descripcion);
				TipoEstado tipoEstado = TipoEstado.getTipoEstados(estado);
				cat.setEstado(tipoEstado);
				catdao.editarCategoria(cat);
				
			}else if (accion.equalsIgnoreCase("/agregar")) {
				
				rta = cat.registrarNuevaCategoria(idcat,descripcion,estado);
				if(rta){
		  		   request.getRequestDispatcher("maestroCategoria.jsp").forward(request, response);
				} 
			}
		}catch (ServletException| IOException| NumberFormatException ex) {
				// TODO: handle exception
				System.err.println("Error"+ex.getMessage());
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}