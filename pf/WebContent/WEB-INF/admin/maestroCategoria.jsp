<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>Listar Categorias </title>


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
<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		<div class="container tablaPersona">
			
				 <h1>Administrador de Categorías</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
		<tr>
					<td align="center">Año Categoria</td>
					<td align="center">Descripcion</td>
					<td align="center">Estado</td>
					<td align="center" colspan="2">Accion</td>
				</tr>
		</thead>
		 <tbody>
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
					</th>
					<th>
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('admin/listarCategoria/eliminar')"
							value="<%= c.getIdCategorias()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>



				</tr>
				<%
					}
				%>
				<th colspan="4"><a type="button"  href="${pageContext.request.contextPath}/admin/modificarCategoria">Nueva Categoria</a>
		 </tbody>
		</table></form>
		</div>
	</div>


		<div class= "container Pie">
			<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />


			</div>
		</div>
	
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>