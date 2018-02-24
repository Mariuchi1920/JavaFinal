<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="en">
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/CSS/style.css" 	href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta charset="UTF-8">
<title>Listar Categorias</title>


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

		<jsp:include page="/WEB-INF/cabecera.jsp" />

	</div>


	<div id="contenido">

		<form id="myForm" name="myForm" action="" method="post">


			<table align="center" width="700" border="void" cellpadding="5">

				<tr>
					<td colspan="4" align="center">Administrador de categorias</td>
				</tr>
				<tr>
					<td align="center">Año Categoria</td>
					<td align="center">Descripcion</td>
					<td align="center">Estado</td>
					<td align="center">Accion</td>
				</tr>

				<%
					CategoriasDAO catDao = new CategoriasDAO();
					LinkedList<Categoria> categorias = catDao
							.listarTodasLasCategorias();
					for (Categoria c : categorias) {
				%>
				<tr>
					<th><%=c.getAñoCategoria()%></th>
					<th><%=c.getDescripcion()%></th>
					<th><%=c.getEstado().getDescripcion()%></th>
					<th>
					<button class="botonEditar"
							onclick="javascript: editar('admin/listarCategoria/editar')"
							value="<%= c.getIdCategorias()%>" id="editar" name="editar">Editar</button> 
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('admin/listarCategoria/eliminar')"
							value="<%= c.getIdCategorias()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>



				</tr>
				<%
					}
				%>
				<th colspan="4">
		<a type="button"  href="${pageContext.request.contextPath}/admin/modificarCategoria">Nueva Categoria</a>
			</table>
				</th>
		</form>
		
		
		
	</div>

	</div>



	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
</body>
</html>
