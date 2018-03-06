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
import entidad.ApplicationException;
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
		if(request.getSession().getAttribute("usuario")!=null && !((Persona) request.getSession().getAttribute("usuario")).isAdmin()){
			request.getSession(false).removeAttribute("editador");
		}
		request.getRequestDispatcher("/WEB-INF/editarPersona.jsp").forward(request, response);
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
			persona.setNombre(request.getParameter("nombre"));
			persona.setApellido(request.getParameter("apellido"));
			persona.setNumeroDocumento(request.getParameter("numeroDocumento"));
			persona.setTipoDocumento("DNI");
			persona.setMail(request.getParameter("mail"));
			persona.setTelefono(request.getParameter("telefono"));
			persona.setFechaNacimiento(Util.convertirStringDate(request .getParameter("fechaNacimiento")));
			
			if(Persona.validarPersona(persona)){
			    persona.setUsuario(request.getParameter("usuario"));
				persona.setContraseña(request.getParameter("contraseña"));
				int estado = Integer.parseInt(request.getParameter("listaTipoPersona"));
				TipoPersonaDAO catTipoPersona = new TipoPersonaDAO();
				persona.setTipoPersona(catTipoPersona.getTipoPersona(estado));
				Persona personaEditada= null;
				if(request.getSession().getAttribute("usuario")!=null && !((Persona) request.getSession().getAttribute("usuario")).isAdmin()){
					personaEditada = (Persona) request.getSession().getAttribute("usuario");
				}else{
					 personaEditada = ((Persona) request.getSession().getAttribute("editador"));
				}
				
				
				if(Persona.validarPersonaUsuarioContraseña(persona,personaEditada)){
					
					
					if(validarIngresarTipoPersona(request, response,persona)){
					
						if (request.getParameter("editar") != null) {
							editarPersona(request, response, persona , personaEditada);
							
						}else if (request.getParameter("registar") != null) {
							
							reguistarPersona (request, response, persona);
						}else{
					         /// No clickeo Opcion
					        definirRedireccion (request,response, false,"registar");
				        }
					}else {
						///No puede ingresar ese tipo persona
						 request.getSession().setAttribute("error","Usted no tiene permisos para registrar ese tipo de persona");
						 definirRedireccion (request,response, false,"registar");
					}
						
			  }else{
				  ////ERROR USUARIO CONTRASEÑA
				  definirRedireccion (request,response, false,"registar");
			  }
			}else{
				///ERROR VALIDAR PERSONA
				definirRedireccion (request,response, false,"registar");
			}
		     
		} catch (SQLException | IOException | NumberFormatException ex) {
			request.getSession().setAttribute("error","Error inesperado");
			definirRedireccion (request,response,false,"registar");

		}catch (ApplicationException e){
			request.getSession().setAttribute("error", e.getMessage());
			definirRedireccion (request,response,false,"registar");
		}catch (Exception e) {
			request.getSession().setAttribute("error","Error inesperado");
			definirRedireccion (request,response,false,"registar");
		}
		System.out.println(request.getSession().getAttribute("error"));
	}
	
	
	
	public void reguistarPersona (HttpServletRequest request,HttpServletResponse response, Persona persona) throws SQLException, IOException{
		PersonasDAO catPersona = new PersonasDAO();

		if (catPersona.buscarPersonaDNI(persona.getNumeroDocumento()) == null) {
		 ///DNI NO EXISTE
			catPersona.nuevaPersona(persona);
			definirRedireccion (request,response, true,"registar");
      	}else{
		///// DNI EXISTE
		request.getSession().setAttribute("error","El dni ingresado ya existe");
		definirRedireccion (request,response, false, "registar");
	   }

	}
	
	
	
	public boolean validarIngresarTipoPersona (HttpServletRequest request,HttpServletResponse response, Persona persona){
		boolean respuesta = true;
		if(request.getSession().getAttribute("usuario")==null){
			if(persona.getTipoPersona().getIdTipoPersona()== TipoPersona.ADMINISTADOR){
				respuesta=false;
			}
			
		}else if(!((Persona) request.getSession().getAttribute("usuario")).isAdmin()){
			if(persona.getTipoPersona().getIdTipoPersona()== TipoPersona.ADMINISTADOR){
				respuesta=false;
			}
		}  
		 
		
		
		return respuesta;
	}
	
	public void editarPersona(HttpServletRequest request,HttpServletResponse response, Persona persona, Persona personaEditada) throws SQLException,IOException {
		PersonasDAO catPersona = new PersonasDAO();
		
		if (!persona.getNumeroDocumento().equals(personaEditada.getNumeroDocumento())) {
			if (catPersona.buscarPersonaDNI(persona.getNumeroDocumento()) == null) {
				// ///DNI NO EXISTE
				persona.setIdPersona(personaEditada.getIdPersona());
				catPersona.editarPersona(persona);
				definirRedireccion(request, response, true, "editar");
			} else {
				// / DNI NO EXISTE
				request.getSession().setAttribute("error",
						"El dni ingresado ya existe");
				definirRedireccion(request, response, false, "editar");
			}

		} else {
			// / NO modifico campo DNI
			persona.setIdPersona(personaEditada.getIdPersona());
			catPersona.editarPersona(persona);
			definirRedireccion(request, response, true, "editar");
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
				if(ok){
			
				response.sendRedirect(request.getContextPath()
						+ "/user");
				
				}else{
					response.sendRedirect(request.getContextPath()
							+ "/reguistarPersonas");
				}
			}
			
		    
			 
		}else {
			if(ok){
			response.sendRedirect(request.getContextPath() + "/login");
			}else {
				response.sendRedirect(request.getContextPath() + "/reguistarPersonas");
			}
		}
		
	}

}
