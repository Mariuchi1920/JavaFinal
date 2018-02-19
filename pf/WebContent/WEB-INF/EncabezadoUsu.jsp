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
		

</body>
</html>