<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Torneo"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
	<meta charset="UTF-8">
	<title>Menu Principal</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
	<<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>

	
		<div id="contenido">
			<h1 align="center">Administracion de torneos</h1>
<table border="1" width="600" align="center">

<tr >
<th> id Torneo</th>
<th> Nombre</th> 
<th> Fecha Inicio</th>
<th> Fecha Fin</th>
<th> Estado</th>
<th> categoria campeon</th>
<th> Institución campeon</th>
<th> equipo campeon</th>

<th> Accion</th>
</tr> 
<!-- ahora trabajo en java para mostrar los datos d ela base de datos 
en las demas filas  -->

<%  Torneo torneo= new Torneo();
	LinkedList <Torneo> listaTorneo= torneo.getTorneos();
	%>

<% for(Torneo t :listaTorneo){  %>
	<tr><th><%= t.getIdTorneos() %></th>
	<th><%=t.getNombre() %></th>
	<th><%=t.getFechaInicio()%></th>
	<th><%=t.getFechaFin() %></th>
	<th><%=t.getIdTipoEstado()%></th>
	<th><%=t.getIdCategoriaCampeon()%></th>
	<th><%=t.getIdInstitucionCampeon()%></th>
	<th><%=t.getNombreEquipoCampeon()%></th>
	<th>
	<img alt="" src="imagen/iconoEditar.png" width="30" height="30"><%-- ||<a href="eliminarTorneo.jsp?idtorneo=<%= //listaTorneo.get(t).getIdtorneo() %>"> --%>
	 <img alt="" src="imagen/iconoEliminar.png" width="30" height="30"><!-- </a> --></th>
	</tr>
		<%}%>
</table>
<form action="nuevoTorneo.jsp" method="post">
<input type="submit" value="Nuevo Torneo">
</form>
		</div>
		

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>