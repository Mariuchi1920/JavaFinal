<%@page import="entidad.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%
Persona persona = (Persona) session.getAttribute("usuario"); 
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">

<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="${pageContext.request.contextPath}/imagen/logo_header.png"></div>
					<div id="usuario">
					
					Bienvenido <%=persona.getNombre()%>
						<a href="${pageContext.request.contextPath}/cerrarSesion">Cerrar Sesion</a>
					</div>
		</div>
		<div id="menu">
		
			<ul class="nav">
			<li><a href="#">Personas</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/admin/listarPersonas">Listar Persona</a></li>
						<li><a href="${pageContext.request.contextPath}/reguistarPersonas">Agregar Persona</a></li>
						

					</ul>
					</li>
				<li><a href="#">Torneo</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/admin/listarTorneo">Listar Torneos</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/modificarTorneo">Nuevo Torneo</a></li>
						

					</ul>
					</li>
				<li><a href="#">Categoria</a>
				<ul>
						<li>
						
						<!-- Aca voy al Servlet -->
						<a href="${pageContext.request.contextPath}/admin/listarCategoria">Listar Categorias</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/admin/modificarCategoria">Nueva Categoria</a></li>
							

					</ul>
				</li>
				
				<li><a href="#">Institucion</a>
				<ul>
						<li><a href="${pageContext.request.contextPath}/admin/listarInstituciones">Listar Instituciones</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/modificarInstituciones">Nueva Institucion</a></li>
					

					</ul>
				<li><a href="#">Partido</a></li>
				<li><a href="#">Equipos</a>
				<ul>
				       <li><a href="${pageContext.request.contextPath}/admin/listarEquipo">Listar Equipos</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/modificarEquipo">Nueva Equipos</a></li>
				</ul>
				</li>
				
				
				
			</ul>
			
		</div>

</body>
</html>