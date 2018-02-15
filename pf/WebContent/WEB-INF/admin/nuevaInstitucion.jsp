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

<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>



	
		<div id="contenido">
			
			
			<form action="InstitucionServlets?accion=registrar" method="post" name="institucionNueva">
			<table border="4" align="center">
				<tr><td colspan="2">Nueva Intitucion</td></tr>
				<tr><td>Institucion:</td> <td><input type="text" name="institucion" id="nombre"/></td> </tr>
				<tr><td>Nombre Localia:</td><td><input type="text" name="nombreLocalia"id="descripcion"/></td></tr>
				<tr><td>Direccion:</td><td><input type="text" name="direccionLocalia"id="direccionlocalia"/></td></tr>
				<tr><td>Nombre delegado:</td><td><input type="text" name="nombreDelegado"id="nombreDelegado"/></td></tr>
				<tr><td>Apellido delegado:</td><td><input type="text" name="apellidoDelegado"id="apellidoDelegado"/></td></tr>
				<tr><td>Telefono Delegado:</td><td><input type="text" name="telefonoDelegado"id="telefonoDelegado"/></td></tr>
				<tr><td>Mail delegado:</td><td><input type="text" name="mail"id="mail"/></td></tr>
				<tr><td><input type= "submit" value="Registrar Institucion" onclick="pregunta()"></td></tr>
				
			</table>
			</form>

		</div>
	

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
	</div>
</body>
</html>
