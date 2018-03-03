package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PersonasDAO;
import datos.TipoEstadoDAO;
import datos.TipoPersonaDAO;
import entidad.Institucion;
import entidad.Persona;
import entidad.TipoPersona;
import entidad.Util;

/**
 * Servlet implementation class ReguistarPersonas
 */
@WebServlet("/reguistarPersonas")
public class ReguistarPersonas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReguistarPersonas() {
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

		request.getRequestDispatcher("/WEB-INF/editarPersona.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			Persona persona = new Persona();
			PersonasDAO catPersona = new PersonasDAO();

			persona.setNombre(request.getParameter("nombre"));
			persona.setApellido(request.getParameter("apellido"));
			persona.setNumeroDocumento(request.getParameter("numeroDocumento"));
			persona.setTipoDocumento("DNI");
			persona.setMail(request.getParameter("mail"));
			persona.setTelefono(request.getParameter("telefono"));
			persona.setUsuario(request.getParameter("usuario"));
			persona.setContraseña(request.getParameter("contraseña"));

			persona.setFechaNacimiento(Util.convertirStringDate(request
					.getParameter("fechaNacimiento")));
			int estado = Integer.parseInt(request
					.getParameter("listaTipoPersona"));
			TipoPersonaDAO catTipoPersona = new TipoPersonaDAO();

			persona.setTipoPersona(catTipoPersona.getTipoPersona(estado));

			if (catPersona.auntenticarPersona(persona.getUsuario(),persona.getContraseña()) == null) {

				if (request.getParameter("editar") != null) {
					Persona personaEditada = ((Persona) request.getSession().getAttribute("editador"));
					persona.setIdPersona(personaEditada.getIdPersona());
					if(!persona.getNumeroDocumento().equals(personaEditada.getNumeroDocumento())){
					if (catPersona.buscarPersonaDNI(persona.getNumeroDocumento()) == null) {
						catPersona.editarPersona(persona);

						definirRedireccion (request,response, true, "editar");
					}else{
						definirRedireccion (request,response, false, "editar");
					}
					}else {
						catPersona.editarPersona(persona);

						definirRedireccion (request,response, true, "editar");
					}

				} else if (request.getParameter("registar") != null) {

					if (catPersona.buscarPersonaDNI(persona.getNumeroDocumento()) == null) {
						catPersona.nuevaPersona(persona);
						definirRedireccion (request,response, true,"registar");
					} else {

						// ////////DNI existe
						definirRedireccion (request,response, false, "registar");
					}

				}
			} else {
				definirRedireccion (request,response, false,"registar");
				
				

			}
		} catch (SQLException | IOException | NumberFormatException ex) {
			definirRedireccion (request,response,false,"registar");

		}
	}
	
	
	
	public void definirRedireccion (HttpServletRequest request,HttpServletResponse response , boolean ok, String boton) throws IOException{
		
		if(request.getSession().getAttribute("usuario")!=null){
			
			
			if (((Persona) request.getSession().getAttribute("usuario")).isAdmin()) {
				if(ok){
				response.sendRedirect(request.getContextPath() + "/admin/listarPersonas");
				}else{
					response.sendRedirect(request.getContextPath() + "/reguistarPersonas");
				}
			} else {
				if(boton.equals("editar")){
			 
				response.sendRedirect(request.getContextPath()
						+ "/user");
				}else{
					response.sendRedirect(request.getContextPath()
							+ "/login");
				}
			}
			
		    
			 
		}else {
			
			response.sendRedirect(request.getContextPath() + "/login");
		}
		
	}

}
