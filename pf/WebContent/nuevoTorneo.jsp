<%@page import="modelo.Categoria"%>
<%@page import="modelo.Conexion"%>
<%@page import="modelo.Torneo"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Registrar Nuevo torneo</title>
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
					<td>Categoria:</td><td>
						<%Categoria cat= new Categoria(); %>
						<%ResultSet rs=cat.listarCategoria();  %>
						<select name="idcategoria">
						<option value="">Seleccione categoria</option>
						<%while(rs.next()){ 
						String idcategoria=rs.getString(1);
						%>
						<option value="<%=idcategoria%>"><%=idcategoria%> </option>
						<%} %>
						</select></td>
					</tr>
					<tr>
					<td>Fecha Inicio:</td><td><input type="text" name="fechaIni" id="fechaIni"/></td>
					</tr>			
					<tr>
					<td>Estado:</td><td><input type="text" name="estado" id="estado"/></td>
					</tr>			
					<tr>
					<td colspan="2"><input type="submit" value="Registrar" onclick="validarDatos()" ></td>
					</tr>			
				</table>
				<br>
			</form>
	</div>

			<div id="contenidoDer" class="division">
				<table border="4">
				<tr><td colspan="7">Gestion de torneos</td></tr>
				<tr><td>Seleccionar</td><td>Id Torneo</td><td>Nombre Torneo</td><td>Categoria</td><td>Fecha de inicio</td><td>Estado 1->activo</td><td>Accion</td>
				</tr>
				<%
				Torneo tor=new Torneo();
				ResultSet rst=null;
				rst=tor.listarTorneo();
				 while (rst.next()) {%>
				<tr>
				<td>Seleccionar</td><td><%=rst.getString(1) %></td><td><%=rst.getString(2) %></td><td><%=rst.getString(5) %></td><td><%=rst.getString(3) %></td><td><%=rst.getString(4) %></td>
				<td>
	 				<img src="imagen/iconoEditar.png" width="30" height="30">||</a>
	 				
	 				<img src="imagen/iconoEliminar.png" width="30" height="30"></a></td>
				</tr>
				<%}%>
			

</table>

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