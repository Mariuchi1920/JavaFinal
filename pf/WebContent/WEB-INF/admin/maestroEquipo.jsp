<%@page import="datos.EquiposDAO"%>
<%@page import="entidad.Equipo"%>
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
<title>Administrador de Equipo</title>
</head>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value=""     
	document.myForm.action = met;
}
function eliminar(met) {
	if (confirm("Estas seguro de eliminar Equipo?")) {
		
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
					<td colspan="10" align="center">Administrador de Equipo</td>
				</tr>
				<tr>
					
					<td>Nombre Institución</td>
					<td>Nombre Categoria</td>
					<td>Nombre Equipo</td>
					<td>Entrenador</td>
					<td colspan="2">Accion</td>

				</tr>
				<% EquiposDAO equipoDAO= new EquiposDAO();
						   LinkedList <Equipo> listadoEquipo = equipoDAO.listarTodasLosEquipos();
						
						 for(Equipo equipo: listadoEquipo){ %>
				<tr>
					
					<td><%=equipo.getInstitucion().getNombre() %></td>
					<td><%=equipo.getCategorias().getAñoCategoria() %></td>
					<td><%=equipo.getNombreEquipo() %></td>
					<td><%=equipo.getEntrenador().getApellido() %>, <%=equipo.getEntrenador().getNombre() %></td>
					<td>
					<button class="botonEditar"
							onclick="javascript: editar('/admin/listarEquipo')"
							value="<%=equipo.getInstitucion().getIdInstituciones()%>/<%=equipo.getCategorias().getIdCategorias()%>/<%=equipo.getNombreEquipo()%>" id="editar" name="editar">Editar</button> 
						<button class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarEquipo')"
							value="<%=equipo.getInstitucion().getIdInstituciones()%>/<%=equipo.getCategorias().getIdCategorias()%>/<%=equipo.getNombreEquipo()%>" id="eliminar" name="eliminar">Eliminar</button>
                     </td>
				</tr>

				<% } %>
			</table>

		</form>

		<a type="button"  href="${pageContext.request.contextPath}/admin/modificarInstitucion">Nuevo Equipo</a>

	</div>
	</div>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>
