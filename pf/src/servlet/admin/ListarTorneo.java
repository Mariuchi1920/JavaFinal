package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CategoriasDAO;
import datos.TorneosDAO;
import entidad.Categoria;
import entidad.Torneo;

/**
 * Servlet implementation class ListarTorneo
 */
@WebServlet({"/admin/listarTorneo","/admin/listarTorneoeditar/", "/admin/listarTorneoeliminar/"})
public class ListarTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTorneo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/maestroTorneo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TorneosDAO torneodao=new TorneosDAO();
		try {
		    
			if(request.getParameter("editar")!=null) {
				Torneo torneo = torneodao.buscarPorId(Integer.parseInt(request.getParameter("editar")));
												
				request.getSession().setAttribute("editador", torneo);
				//////
				response.sendRedirect(request.getContextPath() + "/admin/modificarTorneo");
			}else if(request.getParameter("eliminar")!=null) {
				Torneo torneo=new Torneo();
				torneo.setIdTorneos(Integer.parseInt(request.getParameter("eliminar")));
				torneodao.eliminarTorneo(torneo);
				response.sendRedirect(request.getContextPath() + "/admin/listarTorneoa");
				
							
		}} catch (IOException| NumberFormatException ex) {
			// TODO: handle exception
			response.sendRedirect(request.getContextPath() + "/admin/listarTorneo");
		} 

		
	
	}
	}


