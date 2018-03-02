<%@page import="entidad.EquiposJugadores"%>
<%@page import="entidad.Equipo"%>
<%@page import="datos.EquiposJugadoresDAO"%>
<%@page import="entidad.Persona"%>
<%@page import="datos.PersonasDAO"%>
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
<title>Agregar Personas en un Equipo</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
<script type="text/javascript">

	function agregarEntrenador(met) {
		if(confirm("Estas seguro que desea agregarlo al Equipo")){
			document.myform.elimiarJugador.value="";
			document.myform.agregarJugador.value="";
		    document.myForm.action=met;
		};
    }
	
	function agregarJugador(met) {
		if(confirm("Estas seguro que desea agregarlo el Jugador al equipo")){
			document.myform.elimiarJugador.value="";
			document.myform.agregarEntrenador.value="";
		    document.myForm.action=met;
		};
    }
	function elimiarJugador(met) {
		if(confirm("Estas seguro que desea Eliminar este Jugador?")){
			document.myform.agregarEntrenador.value="";
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
	EquiposJugadores equipoJugador = null;
	EquiposJugadoresDAO catEQJU = new EquiposJugadoresDAO();

	if(request.getSession().getAttribute("editarEntrenador")!=null){
		   encontrado = (Equipo) request.getSession().getAttribute("editarEntrenador");	  
		   categoria=encontrado.getCategorias();
		   institucion=encontrado.getInstitucion();
		   nombre=encontrado.getNombreEquipo();
		   entrenador=encontrado.getEntrenador();
		   jugadores = catEQJU.listarTodasLosJugadores(encontrado);
	}else if(request.getSession().getAttribute("agregarJugador")!=null){
		   equipoJugador = (EquiposJugadores) request.getSession().getAttribute("agregarJugador");	  
		   categoria=equipoJugador.getEquipo().getCategorias();
		   institucion=equipoJugador.getEquipo().getInstitucion();
		   nombre=equipoJugador.getEquipo().getNombreEquipo();
		   entrenador=equipoJugador.getEquipo().getEntrenador();
		   jugadores = catEQJU.listarTodasLosJugadores(equipoJugador.getEquipo());
	}
%>

	<div id="contenido">


		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table border="4" align="center">
				<tr>
					<%if(encontrado!=null){ %>

					<td colspan="2">Entrenadores</td>


					<%}else{ %>
					<td colspan="2">Jugadores</td>

					<%} %>
				</tr>

				



					<%if(equipoJugador!=null){
						   if(jugadores!=null && jugadores.size()>0){
							   
							   for(int i=0; i<jugadores.size(); i++){
						%>
				
				<tr>
					<td><%=jugadores.get(i).getApellido() %>, <%=jugadores.get(i).getNombre() %>
					</td>
					<td>
						<button align="center"
							onclick="javascript: elimiarJugador('/admin/modificarEquipo')"
							id="elimiarJugador" value="<%=jugadores.get(i).getIdPersona() %>"
							name="elimiarJugador">Eliminar</button>
					</td>
				</tr>
				<%
							   }}}
						%>
          <tr>
				
					<%if(encontrado!=null){ %>
					<td> 
					<select name="listaEntrenadores"
					id="listaEntrenadores">
						<%
							  PersonasDAO catPersonas = new PersonasDAO();
							  LinkedList<Persona> entrenadores = new LinkedList<Persona>();
							  entrenadores = catPersonas.buscarPersonaTipoPersona(2);
							  if(entrenadores!=null && entrenadores.size()>0){ 
							  for(Persona te :entrenadores){  
                               			if(encontrado!=null){
                               			     if(entrenador.equals(te)){%>
						<option selected="selected" value="<%= te.getIdPersona() %>"><%=te.getApellido() %>,
							<%=te.getNombre() %></option>

						<%}else {%>
						<option value="<%= te.getIdPersona() %>"><%=te.getApellido() %>,
							<%=te.getNombre() %></option>

						<% }}}} %>

				</select>
				</td>

				<td>
					<button
						onclick="javascript: agregarEntrenador('${pageContext.request.contextPath}/admin/agregarPersonasEquipo')"
						id="agregarEntrenador" value="agregarEntrenador" name="agregarEntrenador">Agregar</button>
				</td>

				<%}else{ %>
				<%
							  PersonasDAO catPersonas = new PersonasDAO();
							  LinkedList<Persona> jugadorTotal = new LinkedList<Persona>();
							  jugadorTotal = catPersonas.buscarPersonaTipoPersona(4);
							  if(jugadores!=null && jugadores.size()>0){
								  for(Persona persona :jugadores){
									  jugadorTotal= Persona.eliminarPersonas(jugadorTotal, persona);
								  }
							  }
							         
							  if(jugadorTotal!=null && jugadorTotal.size()>0){ %>
				              <td>
				              <select name="listaJugadores" id="listaJugadores">
						<%   for(Persona te :jugadorTotal){ %>

						      <option value="<%= te.getIdPersona() %>"> <%=te.getApellido() %>,
							<%=te.getNombre() %></option>



				
				<% }%>
				
				</select></td>

				<td>
					<button
						onclick="javascript: agregarJugador('${pageContext.request.contextPath}/admin/agregarPersonasEquipo')"
						id="agregarJugador" value="agregarJugador" name="agregarJugador">Agregar</button>
				</td>
						
				<%			  }} %>
				</tr>




			</table>


		</form>

	</div>

	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
</body>
</html>