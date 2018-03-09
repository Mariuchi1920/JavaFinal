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
import entidad.ApplicationException;
import entidad.Categoria;
import entidad.Institucion;
import entidad.TipoEstado;
import modelo.Conexion;

@WebServlet("/admin/modificarCategoria/*")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/admin/editarCategoria.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			String descripcion = request.getParameter("descripcion");
			String añocategoria = request.getParameter("añoCategoria");
			int estado = Integer.parseInt(request.getParameter("listaTipoEStado"));
			CategoriasDAO catdao = new CategoriasDAO();
			Categoria cat = new Categoria();
			cat.setAñoCategoria(añocategoria);
			cat.setDescripcion(descripcion);
			TipoEstado tipoEstado;
			TipoEstadoDAO estadoDAO = new TipoEstadoDAO();
			tipoEstado = estadoDAO.getTipoEstados(estado);
			cat.setEstado(tipoEstado);
			if(Categoria.validarCategoria(cat)){

			if (request.getParameter("editar") != null) {
				int idcat = ((Categoria) request.getSession().getAttribute(
						"editador")).getIdCategorias();
				cat.setIdCategorias(idcat);
				catdao.editarCategoria(cat);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarCategoria");

			} else if (request.getParameter("registar") != null) {

				catdao.nuevaCategoria(cat);
				response.sendRedirect(request.getContextPath()
						+ "/admin/listarCategoria");

			} else if (request.getParameter("registar") == null && request.getParameter("editar") == null){
				response.sendRedirect(request.getContextPath()
						+ "/admin/modificarCategoria");
			}
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/modificarCategoria");
			}
		} catch (SQLException | IOException | NumberFormatException ex) {
			// TODO: handle exception
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath() + "/admin/modificarCategoria");

		}catch (ApplicationException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarCategoria");
		}catch (Exception e) {
			request.getSession().setAttribute("error", "Ocurrio un error inesperado");
			response.sendRedirect(request.getContextPath()
					+ "/admin/modificarCategoria");
		}
		
	}

}
