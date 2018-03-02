<%@page import="datos.InstitucionesDAO"%>
<%@page import="datos.PersonasDAO"%>
<%@page import="datos.EquiposJugadoresDAO"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Equipo"%>
<%@page import="datos.TipoEstadoDAO"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="com.mysql.jdbc.EscapeTokenizer"%>
<%@page import="entidad.Categoria"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Torneo"%>
<%@page import="entidad.TipoEstado"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
<meta charset="UTF-8">
<title>Editar Equipo </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<script type="text/javascript">
	
	function editarEntrenador(met) {
		
			document.myform.elimiarJugador.value="";
			document.myform.agregarJugador.value="";
			document.myform.agregarEquipo.value="";
		    document.myForm.action=met;
		
    }
	
	function elimiarJugador(met) {
		if(confirm("Estas seguro que desea Eliminar este Jugador?")){
			document.myform.editarEntrenador.value="";
			document.myform.agregarJugador.value="";
			document.myform.agregarEquipo.value="";
		    document.myForm.action=met;
		};
    }
	
	function agregarJugador(met) {
	      	document.myform.elimiarJugador.value="";
		    document.myform.editarEntrenador.value="";
		    document.myform.agregarEquipo.value="";
		    document.myForm.action=met;
		
    }
	function agregarEquipo(met){
		
		if(confirm("Estas seguro que desea agreagr este Equipo?")){
			document.myform.elimiarJugador.value="";
			document.myform.editarEntrenador.value="";
			document.myform.agregarJugador.value="";
		    document.myForm.action=met;
		};
		
	}
	

	
	</script>
</head>
<body>
	<div id="contenedor">

		<jsp:include page="/WEB-INF/cabecera.jsp" />

	</div>


	<%
Equipo encontrado = null;
Categoria categoria =null;
Institucion institucion= null;
String nombre="";
Persona entrenador= null;
LinkedList<Persona> jugadores =null;
EquiposJugadoresDAO catEQJU = new EquiposJugadoresDAO();

if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Equipo) request.getSession().getAttribute("editador");	  
	   categoria=encontrado.getCategorias();
	   institucion=encontrado.getInstitucion();
	   nombre=encontrado.getNombreEquipo();
	   entrenador=encontrado.getEntrenador();
	   jugadores = catEQJU.listarTodasLosJugadores(encontrado);
}
%>

	<div id="contenido">


		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table align="center"  border="4" >
				<tr>
					<td colspan="2">Equipo</td>
				</tr>
				
				<tr>
				<% if(encontrado!=null){ %>
					<td colspan="2">Institucion :  <%=institucion.getNombre() %></td>		
					<% }else{ %>
					
					<td >Institucion :</td>
					 <td>
					<select name="listaInstitucion" id="listaInstitucion">
							<%
							 InstitucionesDAO catinstitucion = new InstitucionesDAO();
							  LinkedList<Institucion> instituciones = new LinkedList<Institucion>();
							  instituciones = catinstitucion.listarTodasLasInstituciones();
							  if(instituciones!=null && instituciones.size()>0){ 
							  for(Institucion te :instituciones){%>
								  
								  
								  <option value="<%= te.getIdInstituciones() %>"><%=te.getNombre() %></option>
							 <% }} %>	
							 
							 </select>
					</td>
					<% } %>
					 
				</tr>
				
				<tr>
				<% if(encontrado!=null){ %>
					<td colspan="2">Categoria :  <%=categoria.getAñoCategoria() %></td>		
				
				
				<% }else{ %>
					   <td >Categoria: </td>
					  <td>
					    <select name="listaCategorias" id="listaCategorias">
							<%
							  CategoriasDAO catCategorias = new CategoriasDAO();
							  LinkedList<Categoria> categorias = new LinkedList<Categoria>();
							  categorias = catCategorias.listarTodasLasCategorias();
							  if(categorias!=null && categorias.size()>0){ 
							  for(Categoria te :categorias){%>
								  
								  
								  <option value="<%= te.getIdCategorias() %>"><%=te.getAñoCategoria() %></option>
							 <% }} %>

					</select>
					</td>
					    
					  
					<% } %>
					
					
				</tr>
				
				<tr>
				<% if(encontrado!=null){ %>
					<td colspan="2">Nombre Equipo:  <%=nombre %></td>		
					<% }else{ %>
					   <td >Nombre Equipo: </td>
					  
					   
					   <td><input type="text" name="nombreEquipo" id="nombreEquipo"
						 /></td> 
					  
					<% } %>
				</tr>
				
				<tr>
				<% if(encontrado!=null){ %>
					<td colspan="1">Entrenador :  <%=entrenador.getApellido() %>, <%=entrenador.getNombre() %></td>		
					<td colspan="1">
					<button align="center" onclick="javascript: editarEntrenador('/admin/modificarEquipo/agregarJugador')" name="editarEntrenador" id="editarEntrenador" value="editarEntrenador" />
					Editar Entrenador</button>
					
					</td>
					
				<% }else{ %>
				
				  	<td >Entrenador </td>		
					<td >
					 <select name="listaEntrenadores" id="listaEntrenadores">
							<%
							  PersonasDAO catPersonas = new PersonasDAO();
							  LinkedList<Persona> entrenadores = new LinkedList<Persona>();
							  entrenadores = catPersonas.buscarPersonaTipoPersona(2);
							  if(entrenadores!=null && entrenadores.size()>0){ 
							  for(Persona te :entrenadores){%>
								  
								  
								  <option value="<%= te.getIdPersona() %>"><%=te.getApellido() %>, <%=te.getNombre() %></option>
							 <% }} %>

					</select>
					
					</td>
				<% } %>
				</tr>
				
				<%-- <% if(encontrado!=null){ %>
				<tr>
					<td colspan="2">Jugadores </td>
				</tr>
					
						<%
						   if(jugadores!=null && jugadores.size()>0){
							   
							   for(int i=0; i<jugadores.size(); i++){
						%>
					<tr>   
						<td>
						   <%=jugadores.get(i).getApellido() %>, <%=jugadores.get(i).getNombre() %>
						</td>
						<td>
						  <button align="center"
				               onclick="javascript: elimiarJugador('/admin/modificarEquipo')"
				               id="elimiarJugador" value="<%=jugadores.get(i).getIdPersona() %>" name="elimiarJugador">Eliminar</button>
						</td>
					</tr>
					   <%
							   }}
						%>
						 --%>
						
			

				
				<tr>
				
				<td colspan="2">
						<button align="center" onclick="javascript: agregarJugador('/admin/modificarEquipo')" name="agregarJugador" id="agregarJugador" value="agregarJugador" />
						Agregar Jugadores</button>
						</td>

                </tr>
<%--                 <% --%>
// 							   }
<%-- 						%> --%>

			</table>
			<% if(encontrado==null){ %>
			<button align="center" onclick="javascript: agregarEquipo('/admin/modificarEquipo')" name="agregarEquipo" id="agregarEquipo" value="agregarEquipo" />
			  Agregar Equipo</button>
			   <%
							   }
						%>
			  
		</form>

	</div>

	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
</body>
</html>