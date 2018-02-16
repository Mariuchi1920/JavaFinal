<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="${pageContext.request.contextPath}/imagen/logo_header.png"></div>
					<div id="usuario">
					
					Bienvenido <%= session.getAttribute("usuario") %>
					<a href="cerrarSesion.jsp">cerrar Sesion</a>
					</div>
		</div>
		<div id="menu">
		
			<ul class="nav">
				<li><a href="#">Torneo</a>
					<ul>
						<li><a href="maestroTorneo.jsp">Maestro de torneo</a></li>
						
						<li><a href="#">Listar</a></li>	

					</ul>
					</li>
				<li><a href="#">Categoria</a>
				<ul>
						<li><a href="maestroCategoria.jsp">Maestro Categorias</a></li>
					
						<li><a href="#">Listar</a></li>	

					</ul>
				</li>
				<li><a href="#">Jugadores</a></li>
				<li><a href="#">Institucion</a>
				<ul>
						<li><a href="maestroInstituciones.jsp">Maestro Instituciones</a></li>
						
						<li><a href="#">Listar</a></li>	

					</ul>
				<li><a href="#">Partido</a></li>
				
			</ul>
			
		</div>

</body>
</html>