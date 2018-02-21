package servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import datos.TipoEstadoDAO;
import datos.TorneosDAO;
import entidad.Categoria;
import entidad.TipoEstado;
import entidad.Torneo;
import entidad.Util;

import java.sql.Date;
/**
 * Servlet implementation class modificarTorneo
 */
@WebServlet({"/admin/modificarTorneo","/admin/modificarTorneo/*"})
public class modificarTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarTorneo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/editarTorneo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			String nombre= request.getParameter("nombreTorneo");
			Date fechaI=(Util.convertirStringDate(request.getParameter("fechaI")));
			Date fechaF=(Util.convertirStringDate(request.getParameter("fechaF")));
			int estado= Integer.parseInt(request.getParameter("listaTipoEStado"));		
			
			
			
			TorneosDAO tordao= new TorneosDAO();
			Torneo tor= new Torneo();
			//como hago para pasarle los datos de los equipos campeones
			
//			CategoriasDAO catdao=new CategoriasDAO();
//			Categoria cat= new Categoria();
//			cat.setAñoCategoria(añocategoria);
//			cat.setDescripcion(descripcion);
//			TipoEstado tipoEstado;
//			TipoEstadoDAO estadoDAO=  new TipoEstadoDAO();
//			tipoEstado = estadoDAO.getTipoEstados(estado);
//			cat.setEstado(tipoEstado);
		    
			if (request.getParameter("editar")!=null) {
				int idcat= ((Categoria)request.getSession().getAttribute("editador")).getIdCategorias();
//				cat.setIdCategorias(idcat);
//				catdao.editarCategoria(cat);
				response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
				
				
			}else if (request.getParameter("registar")!=null) {
				
//				 catdao.nuevaCategoria(cat);
				 response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
				
				 
			}
		}catch ( IOException| NumberFormatException ex) {
				// TODO: handle exception
			ServletContext context = getServletContext();
			request.getRequestDispatcher("/WEB-INF/admin/editarTorneo.jsp").forward(request, response);
			
			} 
	}
	

}
