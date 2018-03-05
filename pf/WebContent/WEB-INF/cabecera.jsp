<%@page import="entidad.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> --%>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/font-awesome/css/fontawesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}CSS/estilos.css">
</head>
<body>
	<%
		Persona persona = (Persona) session.getAttribute("usuario");
		if (persona != null) {
			if (persona.isAdmin()) {
	%>

	<!--  nav bar-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light container">


	<img src="${pageContext.request.contextPath}/imagen/logo_header.png">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Liga
		Efa</a>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">


		<ul class="nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="#">Personas</a>
				<ul>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/listarPersonas">Listar
							Persona</a></li>
					


				</ul></li>
			<li class="nav-item"><a class="nav-link" href="#">Torneo</a>
				<ul>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/listarTorneo">Listar
							Torneos</a></li>
				


				</ul></li>
			<li class="nav-item "><a class="nav-link" href="#">Categoria</a>
				<ul>
					<li>
						<!-- Aca voy al Servlet --> <a class="nav-link"
						href="${pageContext.request.contextPath}/admin/listarCategoria">Listar
							Categorias</a>
					</li>
					

				</ul></li>
			<li class="nav-item"><a class="nav-link" href="#">Institucion</a>
				<ul>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/listarInstituciones">Listar
							Instituciones</a></li>
				

				</ul>
			<li class="nav-item"><a class="nav-link" href="#">Partido</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Equipos</a>
				<ul>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/listarEquipo">Listar
							Equipos</a></li>
					
				</ul></li>

		</ul>




	</div>
	<div class="usuario">
		Usuario:
		<%=persona.getNombre()%>
		<hr>
		<a href="${pageContext.request.contextPath}/cerrarSesion">Cerrar
			Sesion</a>
		


	</div>
	</nav>
	<%}else{%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light container">


	<img src="${pageContext.request.contextPath}/imagen/logo_header.png">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Liga
		Efa</a>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">


		<ul class="nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/MostrarHistoria">Historia</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Tus torneos</a>
				<ul>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/listarTorneo">Listar
							Torneos</a></li>
					</li>
					<li><a class="nav-link"
						href="${pageContext.request.contextPath}/mostrarFixture">Ver Fixture</a>
					</li>
					</ul>
				
			<li class="nav-item "><a class="nav-link" href="#">Mis Partidos</a>
				<ul>
					<li>
						<!-- Aca voy al Servlet --> <a class="nav-link"
						href="${pageContext.request.contextPath}/verMisPartidos">Lista Mis Partidos</a>
					</li>
					
				</ul>
				</li>
				<li class="nav-item "><a class="nav-link" href="${pageContext.request.contextPath}/listarTablasPosiciones">Tabla de posiciones</a></li>
				<li class="nav-item "><a class="nav-link" href="#">Ranking</a></li>
				<li class="nav-item "><a class="nav-link" href="${pageContext.request.contextPath}/reguistarPersonas">Mi perfil</a></li>
				
				
				<div class="usuario" align="right">
				<table >
				<tr>
				<th>Usuario:</th>
				<th><%=persona.getNombre()%></th>
				</tr>
				<tr>
				<th colspan="2">
				<a href="${pageContext.request.contextPath}/cerrarSesion">Cerrar Sesion</a>
				</th>
				</tr>
					
					</table>
				</div>
				</div>
	</nav>
	<%}}else { %>
	<nav class="navbar navbar-expand-lg navbar-light bg-light container">


	<img src="${pageContext.request.contextPath}/imagen/logo_header.png">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Liga
		Efa</a><hr><h1 align="right">Bienvenido Usuario</h1></nav>
		

	<%}%>
</nav>
	<!--  nav bar-->














	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>