<%@page import="datos.TorneosDAO"%>
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

				<jsp:include page="/WEB-INF/admin/cabecera.jsp" />

	</div>

	
		<div id="contenido">
			<h1 align="center">Administracion de torneos</h1>
<table border="1" width="600" align="center">

<tr >

<th> Nombre</th> 
<th> Fecha Inicio</th>
<th> Fecha Fin</th>
<th> Estado</th>
<th> Equipo Campion</th>


<th> Accion</th>
</tr> 
<!-- ahora trabajo en java para mostrar los datos d ela base de datos 
en las demas filas  -->

<%  TorneosDAO torneo= new TorneosDAO();
	LinkedList <Torneo> listaTorneo= torneo.listarTodosLosTorneos();
			
	%>

<% for(Torneo t :listaTorneo){  %>
	
	<th><%=t.getNombre() %></th>
	<th><%=t.getFechaInicio()%></th>
	<th><%=t.getFechaFin() %></th>
	<th><%=t.getEstado().getDescripcion()%></th>
	<th><%=t.getEquipoGanador().getInstitucion().getNombre()%>- <%=t.getEquipoGanador().getCategorias().getAñoCategoria()%>-<%=t.getNombre()%></th>
					

	<th>
	<button class="botonEditar"
							onclick="javascript: editar('/admin/listarCategoriaeditar/')"
							value="<%= t.getIdTorneos()%>" id="editar" name="editar">Editar</button> 
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarCategoriaeliminar/')"
							value="<%= t.getIdTorneos()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>

		<%}%>
</table>
<form action="nuevoTorneo.jsp" method="post">
<input type="submit" value="Nuevo Torneo">
</form>
		</div>
		

	<div id="Pie">
				<jsp:include page="/WEB-INF/admin/pie.jsp" />

	</div>
</body>
</html>