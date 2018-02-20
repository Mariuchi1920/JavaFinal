<%@page import="entidad.Persona"%>
<%@page import="datos.PersonasDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="en">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta charset="UTF-8">
<title>Maestro categoria</title>


</head>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value=""     
	document.myForm.action = met;
}
function eliminar(met) {
	if (confirm("Estas seguro de eliminar esta Persona?")) {
		
		document.myform.editar.value=""  
		document.myForm.action = met;
	
	};
	}
	
	</script>
<body>
	<div id="contenedor">

		<jsp:include page="/WEB-INF/admin/cabecera.jsp" />

	</div>


	<div id="contenido">

		<form id="myForm" name="myForm" action="" method="post">


			<table align="center" width="700" border="void" cellpadding="5">

				<tr>
					<td colspan="8" align="center">Administrador de Persona</td>
				</tr>
				<tr>
					<td align="center">Nombre</td>
					<td align="center">Apellido</td>
					<td align="center">Telefono</td>
					<td align="center">Fecha Nacimiento</td>
					<td align="center">Numero Documento</td>
					<td align="center">Mail</td>
					<td align="center">Tipo Persona</td>
					<td align="center">Acción</td>
					
				</tr>

				<%
					PersonasDAO catPersona = new PersonasDAO();
					LinkedList<Persona> personas = catPersona.listarPersonas();
					for (Persona c : personas) {
				%>
				<tr>
					<th><%=c.getNombre()%></th>
					<th><%=c.getApellido()%></th>
					<th><%=c.getTelefono()%></th>
					<th><%=c.getFechaNacimiento().toString()%></th>
					<th><%=c.getNumeroDocumento()%></th>
					<th><%=c.getMail()%></th>
					<th><%=c.getTipoPersona().getDescripcion()%></th>
					<th>
					<button
							onclick="javascript: editar('/admin/listarPersonas/')"
							value="<%= c.getIdPersona()%>" id="editar" name="editar">Editar</button> 
						<button
							onclick="javascript: eliminar('/admin/listarPersonas/')"
							value="<%= c.getIdPersona()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>



				</tr>
				<%
					}
				%>

			</table>

		</form>
		
		<a type="button"  href="${pageContext.request.contextPath}/reguistarPersonas">Nueva Persona</a>
		
	</div>

	</div>



	<div id="Pie">
		<jsp:include page="/WEB-INF/admin/pie.jsp" />

	</div>
</body>
</html>
