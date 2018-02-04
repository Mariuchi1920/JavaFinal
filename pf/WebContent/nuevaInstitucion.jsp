<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Nueva Institucion</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function pregunta() {
		if(confirm("Estas seguro de registrar una nueva Institucion?")){
			Document.institucionNueva.submit();
			
		};
		}
	
	</script>
</head>
<body>
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
				<li><a href="#">Institucion</a>
				<ul>
						<li><a href="maestroInstituciones.jsp">Maestro Instituciones</a></li>
						<li><a href="nuevaInstitucion.jsp">Nueva Institucion</a></li>
						<li><a href="#">Listar</a></li>	

					</ul>
				<li><a href="#">Partido</a></li>
				
			</ul>
			
		</div>
		<div id="contenido">
			
			
			<form action="regInstitucion" method="post" name="institucionNueva">
			<table border="4" align="center">
				<tr><td colspan="2">Nueva Intitucion</td></tr>
				<tr><td>Nombre Institucion:</td> <td><input type="text" name="nombre" id="nombre"/></td> </tr>
				<tr><td>Nombre Localia:</td><td><input type="text" name="nombrelocalia"id="descripcion"/></td></tr>
				<tr><td>Direccion:</td><td><input type="text" name="direccionLocalia"id="direccionlocalia"/></td></tr>
				<tr><td>Nombre delegado:</td><td><input type="text" name="nombredelegado"id="nombreDelegado"/></td></tr>
				<tr><td>Apellido delegado:</td><td><input type="text" name="apellidoDelegado"id="apellidoDelegado"/></td></tr>
				<tr><td>Telefono Delegado:</td><td><input type="text" name="telefonoDelegado"id="telefonoDelegado"/></td></tr>
				<tr><td><input type= "submit" value="Registrar Institucion" onclick="pregunta()"></td></tr>
				
			</table>
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