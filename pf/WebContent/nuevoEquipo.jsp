<%@page import="java.sql.ResultSet"%>
<%@page import="modelo.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Registrar nuevo equipor</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript" src="js/confirmarRegistroTorneo.js"></script>
	
</head>

<body onload="carga()">
	<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="imagen/logo_header.png"></div>
					<div id="usuario">					
					Bienvenido <%= session.getAttribute("usuario") %>
					<a href="cerrarSesion.jsp">cerrar Sesion</a>
					</div>
		</div>
		<div id="menu">
		
			<ul class="nav">
				<li><a href="#">Registros</a>
					<ul>
						<li><a href="nuevoTorneo.jsp">Torneo</a></li>
						<li><a href="NuevaCategoria.jsp">Categoria</a></li>
						<li><a href="nuevaInstitucion.jsp">Institucion</a></li>	
						<li><a href="nuevoJugador.jsp">Jugador</a></li>
						<li><a href="nuevoArbitro.jsp">Arbitro</a></li>
						<li><a href="nuevoEquipo.jsp">Equipos</a></li>
							

					</ul>
					</li>
				<li><a href="#">Categoria</a>
					<ul>
						<li><a href="maestroCategoria.jsp">Maestro Categorias</a></li>
						<li><a href="NuevaCategoria.jsp">Nueva Categoria</a></li>
						<li><a href="#">Listar</a></li>	

					</ul>
					</li>
				<li><a href="#">Jugadores</a></li>
				<li><a href="#">Institucion</a></li>
				<li><a href="#">Partido</a></li>
				
			</ul>
			
		</div>
		<div id="contenido">
		<form action="regEquipo" method= "POST">				
		<table border="4" align="center">
		<tr><td colspan="2">Nuevo equipo</td></tr>
		<tr><td>Nombre:</td><td> <input type="text" name="nombreE"id="nombreE"/></td></tr>
		<tr><td>Institucion:</td><td>
									<% Institucion i= new Institucion(); %>
									<%ResultSet resIns= i.mostrarInstituciones(); %>
									<select name="institucion">
									<option value="">Seleccione Institucion</option>
									<%while(resIns.next()){ 
										String Nombre=resIns.getString(1);
										%>
									<option value="<%=Nombre%>"><%=Nombre%> </option>
						<%} %>
									</select></td></tr>
		<tr><td></td><td> <input type="submit" value="Registrar"/></td></tr>
		</table>
		</form>

		<div id="Pie">
			<div id="LogoPie"><img src="imagen/logo_footer.jpg"></div>
			<p>La EFA es una Agrupación de Escuelas de Fútbol creada por las instituciones fundadoras y cuyo objetivo final es:

"QUE TODOS LOS NIÑOS Y JOVENES SE DIVIERTAN JUGANDO AL FÚTBOL SANAMENTE Y CON ALEGRÍA,
SIN PRESIONES DE NINGUNA NATURALEZA, SIENDO ELLOS LOS PROTAGONISTAS PRINCIPALES".

Los adultos que integran la EFA. (Delegados, Técnicos, padres y público en general),
solamente serán colaboradores para lograr el fin mencionado en el párrafo anterior.
</p>
			<div id="LogoFondoPag"><img src="imagen/firma_efa.jpg"></div>
			<h2>esto es el pie de pagina</h2>

		</div>
	</div>
</body>
</html>