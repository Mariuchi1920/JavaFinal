package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Categoria;
import modelo.Conexion;
import modelo.Institucion;

/**
 * Servlet implementation class regCategoria
 */
@WebServlet("/regCategoria")
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
		int estado= Integer.parseInt(request.getParameter("estado"));
		Categoria cat= new Categoria();
		boolean rta=cat.registrarNuevaCategoria(idcat,descripcion,estado);
  		if(rta){
  			
			request.getRequestDispatcher("NuevaCategoria.jsp").forward(request, response);
		}
		
		
		
		
		/*String institucion= request.getParameter("institucion");
		
		//necesito el idInstitucion para registrar la categoria que tiene la institucion 
		Institucion i= new Institucion();
		ResultSet rs=i.buscaIdInstitucion(institucion);
		if(rs != null){
             			try {
							while(rs.next()){
												int  n = Integer.parseInt(rs.getString("idInstitucion"));
													if(n == 0){
																response.sendRedirect("index.jsp");
														}else {
															Categoria cat= new Categoria();
      		boolean rta=cat.registrarNuevaCategoria(idcat,descripcion,estado,n);
      		if(rta){
   			
   			request.getRequestDispatcher("NuevaCategoria.jsp").forward(request, response);
    		}
    		
}
          }
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	}
*/
}}
