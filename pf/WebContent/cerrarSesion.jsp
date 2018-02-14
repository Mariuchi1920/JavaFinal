<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Liga Efa!</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
	<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="imagen/logo_header.png"></div>
					<div id="usuario">La sesion a sido cerrada. 
					<% request.getSession().invalidate();%>
			<a href="index.jsp">Volver al inicio</a>						
					
					
					</div>
		</div>


	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>