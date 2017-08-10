<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Registro de Institucion</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript" src="js/confimRegInst.js">

</script>
	
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

<FORM method=post action="regInstitucion">

<TABLE BORDER=4 align="center">
<tr ><td colspan="2">Registrar nueva institucion</td></tr>

<TR><TD>Nombre:</TD> 
<TD><INPUT type=text name="nombre" id="nombre"></TD>
</TR>

<TR>
<TD>Nombre localia:</TD>
<TD><INPUT type=text name="nombrelocalia" id="nombrelocalia">
</TD>
</TR>

<TR>
<TD>Direccion localia:</TD>
<TD><INPUT type=text name="direccionLocalia" id="direccionLocalia" >
</TD>
</TR>

<TR>
<TD>Nombre Delegado:</TD>
<TD><INPUT type=text name="NombreDelegado" id="nombreDelegado">
</TD>
</TR>

<TR>
<TD>Apellido Delegado:</TD>
<TD><INPUT type=text name="ApellidoDelegado" id="apelidoDelegado">
</TD>
</TR>

<!-- <TR>
<TD>Tipo Documento:</TD>
<TD>
<SELECT name="tipo" >
<OPTION VALUE="Dni">Dni</OPTION> 
<OPTION VALUE="Libreta civica" >Libreta Civica</OPTION>
<OPTION VALUE="pasaporte">Pasaporte</OPTION>

</SELECT>
</TD>
</TR>


<TR>
<TD>Nro Documento:</TD>
<TD><INPUT type=text name="nroDoc" id="numeroDocumento">
</TD>
</TR>
 -->
<TR>
<TD>Telefono delegado:</TD>
<TD><INPUT type=text name="TelefonoDelegado" id="telefonoDelegado" >
</TD>
</TR>
<!-- <TR>
<TD>Mail delegado:</TD>
<TD><INPUT type=text name="MailDelegado" id="mailDelegado">
</TD>
</TR>
 -->

<TR>
<TD COLSPAN=2>
<INPUT type="submit" value="Registrar" onclick="consulta()">
</TD>
</TR>
</TABLE>
</FORM>
<table align="center" width="700" border="void" cellpadding="5">
	 <tr>
		<td colspan="8" align="center">Instituciones Habilitadas</td>
	</tr>
	<tr>
		<td colspan="2" align="center">idInstitucion:</td>
		<td colspan="2" align="center">Nombre:</td>
		<td colspan="2" align="center">Nombre Localia:</td>
		<td colspan="2" align="center">Direccion Localia:</td>
		<td colspan="2" align="center">Nombre Delegado:</td>
		<td colspan="2" align="center">Apellido Delegado:</td>
		<td colspan="2" align="center">Telefono Delegado:</td>
		<td colspan="2" align="center">Accion:</td>
</tr>
	<%
	Institucion insti= new Institucion();
	List<Institucion> instihab;
	instihab=insti.mostrarInstitucionesHabilitadas(); 
	Iterator<String> it= instihab.iterator();
	 while (it.hasNext()) { %>
	 <tr>
	 	<th colspan="2" align="center"><%=%> </th>
	 	<th colspan="2" align="center"><%= %> </th>
	 	<th colspan="2" align="center"><%=%> </th>
	 	<th colspan="2" align="center"><%= %> </th>
	 	<th colspan="2" align="center"><%= %> </th>
	 	<th colspan="2" align="center"><%= %> </th>
	 	<th colspan="2" align="center"><%= %> </th>
	 	<th> 
	 	<a href="" <%= %>>
	 	<img src="imagen/iconoEditar.png" width="30" height="30">||</a>
	 	<a href=""<%= %>>
	 	<img src="imagen/iconoEliminar.png" width="30" height="30"></a>
	 	</th>
	</tr> 
	
	 <%}%>
	 	
      

  </table>
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