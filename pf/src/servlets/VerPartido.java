package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.JornadaDAO;
import datos.PartidoDAO;
import datos.TipoEstadoDAO;
import datos.TorneosDAO;
import entidad.Jornadas;
import entidad.Partidos;
import entidad.TipoEstado;

/**
 * Servlet implementation class VerModificarPartido
 */
@WebServlet("/verPartido/*")
public class VerPartido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPartido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession(false).setAttribute("jugadorPartido",null);
		request.getRequestDispatcher("/WEB-INF/verPartido.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
        	if(request.getParameter("editar")!=null){
        		Partidos partido = (Partidos)request.getSession().getAttribute("editador");
        		request.getSession().setAttribute("editador", partido.getJornada().getTorneos());
	            response.sendRedirect(request.getContextPath() + "/mostrarFixture");
        		
        	}else if(request.getParameter("local")!=null){
				Partidos partido = (Partidos)request.getSession().getAttribute("editador");
				
				 request.getSession().setAttribute("jugadorPartido", partido.getEquipoLocal());
	            response.sendRedirect(request.getContextPath() + "/verJugadoresPartido");
	            
			}else  if(request.getParameter("visitante")!=null){
				Partidos partido = (Partidos)request.getSession().getAttribute("editador");
				request.getSession().setAttribute("jugadorPartido", partido.getEquipoVisitante());
	            response.sendRedirect(request.getContextPath() + "/verJugadoresPartido");
	            
			}else if (request.getParameter("visitante")==null && request.getParameter("local")==null){
				////request.getSession().setAttribute("error", "Ocurrio un error inesperado");
				response.sendRedirect(request.getContextPath() + "/verPartido");
			}
			
			
			
			
		}  catch (IOException | NumberFormatException  ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/verPartido");
		}catch (Exception e) {
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/verPartido");
		}
		
	}

}
