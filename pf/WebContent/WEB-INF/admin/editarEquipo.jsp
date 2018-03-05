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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>editar equipos</title>


</head>
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

<body>
	
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

<div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3 style="color: red;"><%=request.getSession().getAttribute("error").toString()%></h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				
			</div>
	</div>

<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
	
		<!-- slider -->
		<div class="container tablaPersona">
			
				 <h1>Administrador de Equipos</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
				
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
				
				
						
			

				
				<tr>
				
				<td colspan="2">
						<button align="center" onclick="javascript: agregarJugador('/admin/modificarEquipo')" name="agregarJugador" id="agregarJugador" value="agregarJugador" />
						Agregar Jugadores</button>
						</td>

                </tr>


			</table>
			<% if(encontrado==null){ %>
			<button align="center" onclick="javascript: agregarEquipo('/admin/modificarEquipo')" name="agregarEquipo" id="agregarEquipo" value="agregarEquipo" />
			  Agregar Equipo</button>
			   <%
							   }
						%>
			  
		</form>

	</div></div>


		<div class= "container Pie">
			<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />


			</div>
		</div>
	
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>