package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.EquiposDAO;
import datos.EquiposTorneoDAO;
import entidad.Equipo;
import entidad.EquiposTorneos;
import entidad.Torneo;

/**
 * Servlet implementation class AgregarEquiposTorneos
 */
@WebServlet("/admin/agregarEquiposTorneos")
public class AgregarEquiposTorneos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarEquiposTorneos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/admin/agregarEquipoTorneo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String equipoBuscar= request.getParameter("listaEquipos");
		String[] aux= equipoBuscar.split("/");
		EquiposDAO equi = new EquiposDAO();
		Equipo equipo = equi.buscarporIdsEquipo(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]), aux[2]);
		EquiposTorneoDAO equipoE = new EquiposTorneoDAO();
		EquiposTorneos eqTor = new EquiposTorneos();
		eqTor.setTorneo(((Torneo)request.getSession().getAttribute("editador")));
		if(request.getParameter("editar")!=null){
			eqTor.setEquipos(equipo);
			equipoE.nuevoEquipoTorneo(eqTor);
			response.sendRedirect(request.getContextPath() + "/admin/agregarEquiposTorneos");
		}else if(request.getParameter("eliminar")!=null){
			EquiposDAO catEquipo = new EquiposDAO();
			String [] aux1 = request.getParameter("eliminar").split("/");
			Equipo equipo1 = equi.buscarporIdsEquipo(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]), aux[2]);
			eqTor.setEquipos(equipo1);
			equipoE.eliminarEquipoTorneo(eqTor);
			response.sendRedirect(request.getContextPath() + "/admin/agregarEquiposTorneos");
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/agregarEquiposTorneos");
		}
		
		
		
		
	}

}
