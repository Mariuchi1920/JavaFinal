<%@page import="datos.TipoEstadoDAO"%>
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

		<jsp:include page="cabecera.jsp" />

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
					<td>Fecha Inicio:</td><td><input type="date" name="fechaI" id="fechaI"/></td>
						
					<tr>
					<td>Fecha fin:</td><td><input type="date" name="fechaF" id="fechaF"/></td>
						
					<tr>
					<!--	quiero que muestre los tipos de estado que figuran en la base de datos 
					con una lista.  -->
					
					
					<td>Estado:</td><td> <% TipoEstadoDAO catalogo = new TipoEstadoDAO();
					                        TipoEstado tpEstado= new TipoEstado();

							               LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
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
		<jsp:include page="pie.jsp" />

	</div>
	
</body>
</html>