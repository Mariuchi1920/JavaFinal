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
<script type="text/javascript" language="JavaScript"> 
	
function editar(met) {
	
	document.myform.eliminar.value="";     
	document.myForm.action = met;
}
function eliminar(met) {
	
		
		document.myform.editar.value="";
		document.myForm.action = met;
	
	
	}
	
	</script>
<body>


<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		
		
        <div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3 style="color: red;"><%=request.getSession().getAttribute("error").toString()%></h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				
			</div>
	   </div>
		
		<div class="container tablaPersona">
			
				 <h1>Administrador de Categor�as</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
		<tr>
					<td align="center">A�o Categoria</td>
					<td align="center">Descripcion</td>
					<td align="center">Estado</td>
					<td align="center" colspan="2">Accion</td>
				</tr>
		</thead>
		 <tbody>
		 <%
					CategoriasDAO catDao = new CategoriasDAO();
					LinkedList<Categoria> categorias = catDao.listarTodasLasCategorias();
					if(categorias!=null){
					
					for (Categoria c : categorias) {%>
		 <tr>
					<td><%=c.getA�oCategoria()%></td>
					<td><%=c.getDescripcion()%></td>
					<td><%=c.getEstado().getDescripcion()%></td>
					<td>
					<button class="botonEditar" onclick="javascript: editar('admin/listarCategoria/editar')" value="<%= c.getIdCategorias()%>" id="editar" name="editar">Editar</button> 
					</td>
					<td>
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('admin/listarCategoria/eliminar')"
							value="<%= c.getIdCategorias()%>" id="eliminar" name="eliminar">Eliminar</button>
					</td>



				</tr>
				<%
					}}else {%>
					<tr>
					<th colspan="8" style="color: red">
					No hay categorias disponibles!</th>
					</tr>
					<% } %>
				<tr>
				<th colspan="10">
				<a type="button"  href="${pageContext.request.contextPath}/admin/modificarCategoria">Nueva Categoria</a>
				</th>
				</tr>	
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