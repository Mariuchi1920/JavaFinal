<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.ResultSet"%>
<%@page import="modelo.Categoria"%>
<%@page import="modelo.Consulta"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Menu Principal</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function pregunta() {
		if(confirm("Estas seguro que desea realizar los cabios?")){
			Document.categoriaNueca.submit();
			
		};
		}
	
	</script>
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
			
		
			
			<form action="">
				<table border="2 px" align="center">
			
				<tr>
				<td colspan="2">Editar Categoria</td>
				<tr>
				
				<td>idCategoria:</td>
				<td><input type="text" name="idCategoria" id="idCategoria" value=""/></td>
				</tr>
				<tr>
				<td>descripcion:</td>
				<td><input type="text" name="descripcion"id="descripcion"value=""/></td>
				</tr>
				<tr>
				<td>estado:</td>
				<td><input type="text" name="estado"id="estado"value=""/></td>
				
				<tr>
				<tr>
				
				<td colspan="2"><input type= "submit" value="Guardar Cambios" onclick="pregunta()"></td>
				<tr>
				
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