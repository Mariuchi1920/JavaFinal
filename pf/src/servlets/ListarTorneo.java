package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import datos.EquiposTorneoDAO;
import datos.TorneosDAO;
import entidad.ApplicationException;
import entidad.EquiposTorneos;
import entidad.FixtureTorneo;
import entidad.Jornadas;
import entidad.Torneo;
import entidad.Util;

/**
 * Servlet implementation class ListarTorneo
 */
@WebServlet("/listarTorneo/*")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/listarTorneos.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TorneosDAO torneodao = new TorneosDAO();
		try {

			 if(request.getParameter("verfixture") != null){
				Torneo tor = torneodao.buscarPorId(Integer.parseInt(request.getParameter("verfixture")));
				request.getSession().setAttribute("editador", tor);
				response.sendRedirect(request.getContextPath()+ "/mostrarFixture");
				
				
			}else{
				response.sendRedirect(request.getContextPath()+ "/admin/listarTorneo");
			}
		} catch (IOException | NumberFormatException | SQLException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/listarTorneo");
		}catch (Exception e){
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/listarTorneo");
		}

	}

}
