<%@page import="java.sql.ResultSet"%>
<%@page import="modelo.Categoria"%>
<%@page import="modelo.Consulta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Maestro categoria</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function nuevaCategoria(url){
		window.open(url,"","algun parametro que desees");
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

<table align="center" width="700" border="void" cellpadding="5">
	 <tr>
		<td colspan="8" align="center">Categorias Habilitadas</td>
	</tr>
	<tr>
		<td colspan="2" align="center">Id Categoria</td>
		<td colspan="2" align="center">Descripcion</td>
		<td colspan="2" align="center">Estado</td>
		<td colspan="2" align="center">Accion</td>
</tr>
	<%
	Categoria cate= new Categoria();
	ResultSet rs=null;
	rs=cate.mostrarCategoriaExistentes();
	
	 while (rs.next()) { %>
	 <tr>
	 	<th colspan="2" align="center"><%=rs.getInt(1) %> </th>
	 	
	 	<th colspan="2" align="center"><%=rs.getString(2) %> </th>
	 	<th colspan="2" align="center"><%=rs.getString(3) %> </th>
	 	<th> 
	 	<a href="editarCategoria.jsp?idcategoria=" <%=Integer.toString(rs.getInt(1)) %>>
	 	<img src="imagen/iconoEditar.png" width="30" height="30">||</a>
	 	<a href="eliminarCategoria.jsp?idcategoria="<%=Integer.toString(rs.getInt(1)) %>>
	 	<img src="imagen/iconoEliminar.png" width="30" height="30"></a>
	 	</th>
	</tr> 
	
	 <%}%>
	 	
      	<tr >
     	<th colspan="8" > <input type="submit" value="Nueva Categoria" onclick="nuevaCategoria('NuevaCategoria.jsp')"> </th>
       	</tr>

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
	</div>
</body>
</html>