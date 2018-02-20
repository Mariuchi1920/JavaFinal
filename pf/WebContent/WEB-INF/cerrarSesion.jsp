<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
	<meta charset="UTF-8">
	<title>Liga Efa!</title>
	
</head>
<body>
	<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="${pageContext.request.contextPath}/imagen/logo_header.png"></div>
					<div id="usuario">La sesion a sido cerrada 
			<a href="${pageContext.request.contextPath}/login">Volver al inicio</a>						
					
					
					</div>
		</div>


	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
</body>
</html>