package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PartidoDAO;
import entidad.Jornadas;
import entidad.Partidos;

/**
 * Servlet implementation class Fixture
 */
@WebServlet("/mostrarFixture/*")
public class MostrarFixture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarFixture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/mostrarfixture.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stuby
		
		
		try {
			
			if(request.getParameter("verpartido")!=null){
				
				int idParitod = Integer.parseInt(request.getParameter("verpartido"));
				PartidoDAO catPartido = new PartidoDAO();
				Partidos partido = catPartido.buscarporIdsPartido(idParitod);
				request.getSession().setAttribute("editador",partido );
				response.sendRedirect(request.getContextPath()
						+ "/verPartido");
				
				
				
				
			}else{
				////request.getSession().setAttribute("error", "Ocurrio un error inesperado");
				response.sendRedirect(request.getContextPath()
						+ "/mostrarFixture");
			}
			
			
			
			
		}  catch (IOException | NumberFormatException  ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/mostrarFixture");
		}catch (Exception e) {
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/mostrarFixture");
		}
	}

}
