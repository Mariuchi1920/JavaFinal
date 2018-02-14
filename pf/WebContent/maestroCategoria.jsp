<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="modelo.Consulta"%>
<%@page import="java.util.LinkedList"%>
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

<table align="center" width="700" border="void" cellpadding="5">
	 <tr>
		<td colspan="4" align="center">Administrador de categorias</td>
	</tr>
	<tr>
		<td align="center">Id Categoria</td>
		<td  align="center">Descripcion</td>
		<td  align="center">Estado</td>
		<td align="center">Accion</td>
</tr>

		<%
		CategoriasDAO catDao= new CategoriasDAO();
		LinkedList<Categoria> categorias=catDao.listarTodasLasCategorias(); 
		for(Categoria c:categorias){%>
		<tr>  <th> <%= c.getIdCategorias() %></th>
			  <th> <%= c.getDescripcion() %></th>
			  <th> <%= c.getEstado() %></th>
			<th><a href="CategoriaServlets?accion=editar"><img alt="" src="imagen/iconoEditar.png" width="30" height="30"></a>||<a href="CategoriaServlets?accion=eliminar&idCategoria=<%=c.getIdCategorias() %>">
	 			<img alt="" src="imagen/iconoEliminar.png" width="30" height="30"></a></th>	
		</tr>
			<%		
			}
			%>

<%-- <% 
Categoria cate= new Categoria();
LinkedList <Categoria> listaCategoria= cate.getCategorias();
for(int i=0; i<listaCategoria.size();i++){
	%>
	<tr><th><%=listaCategoria.get(i).getIdcateogria() %></th>
		<th><%=listaCategoria.get(i).getDescripcion() %></th>
		<th><%=listaCategoria.get(i).getEstado() %></th>
		<th><a href="editarCategoria.jsp?idcategoria=<%=listaCategoria.get(i).getIdcateogria()%>"><img alt="" src="imagen/iconoEditar.png" width="30" height="30"></a>||<a href="eliminarCategoria.jsp?idcategoria=<%=listaCategoria.get(i).getIdcateogria()%>">
	 <img alt="" src="imagen/iconoEliminar.png" width="30" height="30"></a></th>
	
	</tr>
	
	<%		
}
%> --%>
  </table>
  <form action="nuevaCategoria.jsp" method="post">
<input type="submit" value="Nueva Categoria">
</form>
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