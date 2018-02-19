<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.Persona"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%
Persona persona = (Persona) session.getAttribute("usuario"); 
%>
<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="${pageContext.request.contextPath}/imagen/logo_header.png"></div>
					<div id="usuario">
					
						Bienvenido <%=persona.getNombre()%>
				
					<a href="/WEB-INF/cerrarSesion.jsp">cerrar Sesion</a>
					</div>
		</div>
		<div id="menu">
		
			<ul class="nav">
				<li><a href="#">LIGA EFA</a>
					<ul>
						<li><a href="WEB-INF/historiaEfa.jsp">Historia</a></li>
						<li><a href="#">Reglamento</a></li>	
						

					</ul>
					</li>
				<li><a href="#">FIXTURE</a>
				
				</li>
				<li><a href="#">TABLAS</a></li>
				<li><a href="#">INFORMACIÓN GRAL</a>
				<ul>
						<li><a href="maestroInstituciones.jsp">Clubes habilitados</a></li>
						
						<li><a href="#">Datos Entrenadores</a></li>	

					</ul>
							</ul>
			
		</div>
		<div id="imagenHistoria">
		<img alt="" src="WebContent/imagen/historiaLigaEfa.gif"/>
		</div>

</body>
</html>