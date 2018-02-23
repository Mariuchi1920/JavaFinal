<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta charset="UTF-8">
<title>Administrador de instituciones</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value=""     
	document.myForm.action = met;
}
function eliminar(met) {
	if (confirm("Estas seguro de eliminar Categoría?")) {
		
		document.myform.editar.value=""  
		document.myForm.action = met;
	
	};
	}
	
	</script>

<body>
	<div id="contenedor">

		<jsp:include page="cabecera.jsp" />

	</div>


	<div id="contenido">
		<form id="myForm" name="myForm" action="" method="post">

			<table align="center" width="800" border="void" cellpadding="5">
				<tr>
					<td colspan="10" align="center">Administrador de Instituciones</td>
				</tr>
				<tr>
					
					<td>Nombre</td>
					<td>Nombre Localia</td>
					<td>Direccion Localia</td>
					<td>Nombre Delegado</td>
					<td>Apellido Delegado</td>
					<td>Telefono Delegado</td>
					<td>Mail Delegado</td>
					<td colspan="2">Accion</td>

				</tr>
				<% InstitucionesDAO institucionDAO= new InstitucionesDAO();
						   LinkedList <Institucion> listadoI = institucionDAO.listarTodasLasInstituciones();
						
						 for(Institucion in: listadoI){ %>
				<tr>
					
					<td><%=in.getNombre() %></td>
					<td><%=in.getNombreLocalia() %></td>
					<td><%=in.getDireccionLocalia() %></td>
					<td><%=in.getNombreDelegado() %></td>
					<td><%=in.getApellidoDelegado() %></td>
					<td><%=in.getTelefonoDelegado() %></td>
					<td><%=in.getMailDelegado() %></td>
					<td>
					<button class="botonEditar"
							onclick="javascript: editar('/admin/listarInstituciones/')"
							value="<%=in.getIdInstituciones()%>" id="editar" name="editar">Editar</button> 
					</td>
					<td>
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarInstituciones/')"
							value="<%=in.getIdInstituciones()%>" id="eliminar" name="eliminar">Eliminar</button>
                     </td>
				</tr>

				<% } %>
				
				</table>
		</form>
		<table align="center" width="800" border="void" cellpadding="5">
		<th colspan="10">
			<a type="button"  href="${pageContext.request.contextPath}/admin/modificarInstitucion">Nueva Institución</a>
			</th>
		</table>

		

	</div>
	</div>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>
