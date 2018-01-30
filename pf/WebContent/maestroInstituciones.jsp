<%@page import="java.util.LinkedList"%>
<%@page import="modelo.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Administrador de instituciones</title>
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
				
				<table align="center" width="800" border="void" cellpadding="5">
			 			<tr>
						<td colspan="8" align="center">Administrador de Instituciones</td>
						</tr>
						<tr>
							<td>Id Institucion</td>
							<td>Nombre</td>
							<td>Nombre Localia</td>
							<td>Direccion Localia</td>
							<td>Nombre Delegado</td>
							<td>Apellido Delegado</td>
							<td>Telefono Delegado</td>
							<td>Accion</td>
		
						</tr>
						<% 
						Institucion ins= new Institucion();
						LinkedList <Institucion> listaInstituciones= ins.getInstitucion();
						for(int i=0; i<listaInstituciones.size();i++){
						%>
						<tr><th><%=listaInstituciones.get(i).getIdInstitucion() %></th>
							<th><%=listaInstituciones.get(i).getNombre() %></th>
							<th><%=listaInstituciones.get(i).getNombreLocalia() %></th>
							<th><%=listaInstituciones.get(i).getDireccionLocalia() %></th>
							<th><%=listaInstituciones.get(i).getNombreDelegado() %></th>
							<th><%=listaInstituciones.get(i).getApellidoDelegado() %></th>
							<th><%=listaInstituciones.get(i).getTelefonoDelegado() %></th>
							<th><img alt="" src="imagen/iconoEditar.png" width="30" height="30">||<a href="eliminarInstitucion.jsp?idinstitucion=<%=listaInstituciones.get(i).getIdInstitucion()%>">
	 						<img alt="" src="imagen/iconoEliminar.png" width="30" height="30"></a></th>
	
							</tr>
	
						<%		
							}
						%>
  			</table>
 				 <form action="nuevaInstitucion.jsp" method="post">
					<input type="submit" value="Nueva Institucion" align="center">
				</form>

		</div>
		</div>
		<div id="Pie">
			<div id="LogoPie"><img src="imagen/logo_footer.jpg"></div>
			<p>La EFA es una Agrupaci�n de Escuelas de F�tbol creada por las instituciones fundadoras y cuyo objetivo final es:

"QUE TODOS LOS NI�OS Y JOVENES SE DIVIERTAN JUGANDO AL F�TBOL SANAMENTE Y CON ALEGR�A,
SIN PRESIONES DE NINGUNA NATURALEZA, SIENDO ELLOS LOS PROTAGONISTAS PRINCIPALES".

Los adultos que integran la EFA. (Delegados, T�cnicos, padres y p�blico en general),
solamente ser�n colaboradores para lograr el fin mencionado en el p�rrafo anterior.
</p>
			<div id="LogoFondoPag"><img src="imagen/firma_efa.jpg"></div>
			<h2>esto es el pie de pagina</h2>

		</div>
	</div>
</body>
</html>