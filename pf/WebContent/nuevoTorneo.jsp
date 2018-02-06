<%@page import="entidad.TipoEstado"%>
<%@page import="entidad.Categoria"%>
<%@page import="modelo.Conexion"%>
<%@page import="entidad.Torneo"%>
<%@page import="java.sql.ResultSet"%>
    <%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Menu Principal</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
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
				<li><a href="#">Torneo</a>
					<ul>
						<li><a href="maestroTorneo.jsp">Maestro de torneo</a></li>
						<li><a href="nuevoTorneo.jsp">Nuevo Torneo</a></li>
						<li><a href="#">Listar</a></li>	

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
		<div id="contenidoIz"class="division">
				<form action="regTorneo" method= "POST">				
					<table border="4" >

					<tr>
					<td colspan="2" align="center">Nuevo Torneo</td>
					</tr>
					<tr>
					<td>Nombre Torneo:</td><td><input type="text" name="nombreTorneo" id="nombreTorneo"/></td>
					</tr>
					<tr>
					<td>Fecha Inicio:</td><td><input type="text" name="fecha" id="fechaI"/></td>
						
					<tr>
					<td>Fecha fin:</td><td><input type="text" name="fecha" id="fechaF"/></td>
						
					<tr>
					<!--	quiero que muestre los tipos de estado que figuran en la base de datos 
					con una lista.  -->
					
					
					<td>Estado:</td><td> <% TipoEstado tpEstado= new TipoEstado();
                               			 LinkedList <TipoEstado> listaEstado= tpEstado.getTipoEstados();
                               			 %>
                               			 <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
                               			 <select name="listaTipoEStado" id="tipoEstado">
                               			<% for(TipoEstado te :listaEstado){  %>
                               			<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                               			
                               			<%} %></select>
</td>
					</tr>			
					<tr>
					<td>Categoria Campeon: </td><td><input type="text" name="campeonCat" id="campeonCat"/></td>
					</tr>
					<tr>
					<td>Institucion Campeon: </td><td><input type="text" name="campeonInst" id="campeonInst"/></td>
					</tr>
					<tr>
					<td>Equipo Campeon: </td><td><input type="text" name="campeon" id="campeon"/></td>
					</tr>					
					<tr>
					<td colspan="2"><input type="submit" value="Registrar" onclick="validarDatos()" ></td>
					</tr>			
				</table>
				<br>
			</form>
	</div>

		



</div>
</div>

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
	
</body>
</html>