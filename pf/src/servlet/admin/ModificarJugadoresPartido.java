package servlet.admin;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposJugadoresDAO;
import datos.JugadoresPartidosDAO;
import datos.PartidoDAO;
import datos.TipoEstadoDAO;
import entidad.Equipo;
import entidad.JugadoresPartido;
import entidad.Partidos;
import entidad.Persona;
import entidad.TipoEstado;

/**
 * Servlet implementation class ModificarJugadoresPartido
 */
@WebServlet("/admin/modificarJugadoresPartido")
public class ModificarJugadoresPartido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarJugadoresPartido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/editarJugadorPartidoEquipo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			if(request.getParameter("editar")!=null){
				String[] cantidadgoles = request.getParameterValues("cantidadgoles");
				String[] cantidadAmarillas = request.getParameterValues("cantidadAmarillas");
				String[] cantidadrojas = request.getParameterValues("cantidadrojas");
				JugadoresPartidosDAO catJugadorPartido = new JugadoresPartidosDAO();
				EquiposJugadoresDAO catEquipoJugadores = new EquiposJugadoresDAO();
				Partidos partido = (Partidos) request.getSession().getAttribute("editador");
				int idPartidos = partido.getIdPartidos();
				
				Equipo equipo = (Equipo) request.getSession().getAttribute("jugadorPartido");
				LinkedList<JugadoresPartido> jugadores = new LinkedList<JugadoresPartido>();
				LinkedList<JugadoresPartido> jugadoresPartido = catJugadorPartido.buscarIDPartido(idPartidos);
				LinkedList<Persona> jugadoresEquipo = catEquipoJugadores.listarTodasLosJugadores(equipo);
	
				for (JugadoresPartido jugadorPartido : jugadoresPartido) {
					if (Persona.buscarPersona(jugadoresEquipo,jugadorPartido.getJugadores())) {
						jugadores.add(jugadorPartido);
					}
				}
	
				Equipo equipoLocal = partido.getEquipoLocal();
				Equipo equipoVisitante = partido.getEquipoVisitante();
	
				
	
				int contarCantidadGoles = 0;
				if (jugadores != null && jugadores.size() > 0) {
	
					for (int i = 0; i < jugadores.size(); i++) {
	
						jugadores.get(i).setCatidadGoles(Integer.parseInt(cantidadgoles[i]));
						jugadores.get(i).setCantidadTarjetasAmarillas(Integer.parseInt(cantidadAmarillas[i]));
						jugadores.get(i).setCantidadTarjetasRojas(Integer.parseInt(cantidadrojas[i]));
						catJugadorPartido.editarJugadorPartido(jugadores.get(i));
						contarCantidadGoles = contarCantidadGoles + Integer.parseInt(cantidadgoles[i]);
	
					}
					PartidoDAO catPartido = new PartidoDAO();
					TipoEstadoDAO tipoEstado = new TipoEstadoDAO();
					partido.setEstado(tipoEstado.getTipoEstados(TipoEstado.JUGADO));
					if (Equipo.ifIgualDosEquipos(equipoLocal, equipo)) {
						partido.setGolesLocal(contarCantidadGoles);
						catPartido.editarPartido(partido);
	
					} else if (Equipo.ifIgualDosEquipos(equipoVisitante, equipo)) {
						partido.setGolesVisitante(contarCantidadGoles);
						
						catPartido.editarPartido(partido);
					}
					
					response.sendRedirect(request.getContextPath() + "/admin/verModificarPartido");
	
				} else {
					request.getSession().setAttribute("error",
							"Ocurrio un error inesperado");
					response.sendRedirect(request.getContextPath()
							+ "/admin/verModificarPartido");
				}
			}else if(request.getParameter("cancelar")!=null){
				response.sendRedirect(request.getContextPath() + "/admin/verModificarPartido");
			}else if(request.getParameter("cancelar")==null && request.getParameter("editar")==null){
				response.sendRedirect(request.getContextPath() + "/admin/modificarJugadoresPartido");
			}

		} catch (NumberFormatException ex) {
			// TODO: handle exception

			request.getSession().setAttribute("error",
					"Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarJugadoresPartido");
		} catch (Exception e) {
			request.getSession().setAttribute("error",
					"Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarJugadoresPartido");
		}
		System.out.println(request.getSession().getAttribute("error"));
	}
		 

}
