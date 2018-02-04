<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Torneo"%>

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
						<li><a href="nuevaCategoria.jsp">Nueva Categoria</a></li>
						<li><a href="#">Listar</a></li>	

					</ul>
				</li>
				<li><a href="#">Jugadores</a></li>
				<li><a href="#">Institucion</a></li>
				<li><a href="#">Partido</a></li>
				
			</ul>
			
		</div>
		<div id="contenido">
			<h1 align="center">Administracion de torneos</h1>
<table border="1" width="600" align="center">
<tr >
<th> id Torneo</th>
<th> Nombre</th> 
<th> Fecha</th>
<th> Estado</th>
<th> Campeon</th>
<th> Accion</th>
</tr> 
<!-- ahora trabajo en java para mostrar los datos d ela base de datos 
en las demas filas  -->

<%
Torneo torneo= new Torneo();
LinkedList <Torneo> listaTorneo= torneo.getTorneos();
for(int i=0; i<listaTorneo.size();i++){
	%>
	<tr><th><%=listaTorneo.get(i).getIdtorneo() %></th>
	<th><%=listaTorneo.get(i).getNombre() %></th>
	<th><%=listaTorneo.get(i).getFecha() %></th>
	<th><%=listaTorneo.get(i).getEstado() %></th>
	<th><%=listaTorneo.get(i).getCampeon() %></th>
	<th>
	
	 <img alt="" src="imagen/iconoEditar.png" width="30" height="30">||<a href="eliminarTorneo.jsp?idtorneo=<%=listaTorneo.get(i).getIdtorneo() %>">
	 <img alt="" src="imagen/iconoEliminar.png" width="30" height="30"></a></th>
	
	</tr>
	
	<%		
}
%>
</table>
<form action="nuevoTorneo.jsp" method="post">
<input type="submit" value="Nuevo Torneo">
</form>
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
	</div>
</body>
</html>