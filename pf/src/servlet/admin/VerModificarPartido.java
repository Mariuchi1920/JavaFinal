package servlet.admin;

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
@WebServlet("/admin/verModificarPartido/*")
public class VerModificarPartido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerModificarPartido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession(false).setAttribute("jugadorPartido",null);
		request.getRequestDispatcher("/WEB-INF/admin/editarPartido.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
			
			if(request.getParameter("editar")!=null){
				
				
				PartidoDAO catPartido = new PartidoDAO();
				Partidos partido = (Partidos)request.getSession().getAttribute("editador");
				partido.setObservaciones(request.getParameter("observaciones"));
				///partido.setGolesLocal(Integer.parseInt(request.getParameter("golesLocal")));
				////partido.setGolesVisitante(Integer.parseInt(request.getParameter("golesVisitante")));
				TipoEstadoDAO catalogoEstado = new TipoEstadoDAO();
	            TipoEstado tpEstado= catalogoEstado.getTipoEstados(Integer.parseInt(request.getParameter("listaTipoEStado")));
	            partido.setEstado(tpEstado);
	            catPartido.editarPartido(partido);
	            Jornadas.definirEstadoJoranda(partido.getJornada().getIdJornadas());
	            TorneosDAO catTorneo = new TorneosDAO();
	            request.getSession(false).setAttribute("editador", null);
	            request.getSession().setAttribute("editador", partido.getJornada().getTorneos());
	            response.sendRedirect(request.getContextPath() + "/admin/mostrarFixture");
	            
			}else if(request.getParameter("local")!=null){
				Partidos partido = (Partidos)request.getSession().getAttribute("editador");
				
				 request.getSession().setAttribute("jugadorPartido", partido.getEquipoLocal());
	            response.sendRedirect(request.getContextPath() + "/admin/modificarJugadoresPartido");
	            
			}else  if(request.getParameter("visitante")!=null){
				Partidos partido = (Partidos)request.getSession().getAttribute("editador");
				request.getSession().setAttribute("jugadorPartido", partido.getEquipoVisitante());
	            response.sendRedirect(request.getContextPath() + "/admin/modificarJugadoresPartido");
	            
			}else if (request.getParameter("visitante")==null && request.getParameter("local")==null && request.getParameter("editar")==null){
				////request.getSession().setAttribute("error", "Ocurrio un error inesperado");
				response.sendRedirect(request.getContextPath()
						+ "/admin/verModificarPartido");
			}
			
			
			
			
		}  catch (IOException | NumberFormatException  ex) {
			// TODO: handle exception
			
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/verModificarPartido");
		}catch (Exception e) {
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/verModificarPartido");
		}
		
	}

}
