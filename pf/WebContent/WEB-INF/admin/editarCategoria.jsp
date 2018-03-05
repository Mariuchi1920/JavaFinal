<%@page import="datos.TipoEstadoDAO"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="com.mysql.jdbc.EscapeTokenizer"%>
<%@page import="entidad.Categoria"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Torneo"%>
<%@page import="entidad.TipoEstado"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
<meta charset="UTF-8">
<title>Editar Categoria</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/estilos.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">


<script type="text/javascript">
function carga(){
	 document.getElementById("añoCategoria").focus();
	 
	 
}
	
	function editar(met) {
		if(confirm("Estas seguro de editar una nueva categoria?")){
			document.myform.registar.value=""  
		    document.myForm.action=met;
		};
    }
	
	function registar(met) {
		if(confirm("Estas seguro de registrar una nueva categoria?")){
			document.myform.editar.value=""  
		    document.myForm.action=met;
		};
    }

	
	</script>
</head>
<body onload="carga()">
	<div id="contenedor">

		<jsp:include page="/WEB-INF/cabecera.jsp" />

	</div>


	<%
Categoria encontrado = null;
String idCategoria ="";
String añoCategoria="";
String descripcion="";
TipoEstado estado = new TipoEstado();
CategoriasDAO catdao = new CategoriasDAO();

if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Categoria)request.getSession().getAttribute("editador");	  
	   idCategoria=String.valueOf(encontrado.getIdCategorias());
	   añoCategoria=encontrado.getAñoCategoria();
	   descripcion=encontrado.getDescripcion();
	   estado=encontrado.getEstado();
}
%>

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

		<h1>Administrador de Categorías</h1>
		<form id="myForm" name="myForm" action="" method="post">

			<table class="table table-bordered" align="center">
				
					<tr>
						<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
					<tr>
						<td>Año Categoria</td>
						<td><input type="text" name="añoCategoria" id="añoCategoria"
							value="<%= añoCategoria %>" class="form-control" placeholder="nombre..." /></td>

					</tr>




					<tr>
						<td>Descripcion:</td>
						<td><input type="text" name="descripcion" id="descripcion"
							value="<%= descripcion %>"class="form-control" placeholder="nombre..."  /></td>
					</tr>
					<tr>
						<td>Estado:</td>
						<td>
							<% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
				                         TipoEstado tpEstado= new TipoEstado();
			
                               			 LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
                               			 %> <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
							<select name="listaTipoEStado" id="tipoEstado" class="form-control" placeholder="Selecciones tipo de estado"  >
								<!-- <option value="-1" selected="selected">- selecciona tipo de estado-</option> -->
								<% for(TipoEstado te :listaEstado){  
                               			if(encontrado!=null){
                               			     if(estado.getIdTipoEstado()== te.getIdTipoEstado()){%>
								<option selected="selected"
									value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>

								<%}else if(te.getIdTipoEstado()== TipoEstado.HABILITADA ||te.getIdTipoEstado()== TipoEstado.DESHABILITADA  ){%>
										<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
								<%}}else if(te.getIdTipoEstado()== TipoEstado.HABILITADA ||te.getIdTipoEstado()== TipoEstado.DESHABILITADA  ){%>
								<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>

								<% }} %>

						</select>
						</td>

					</tr>
					
					
			<!-- ver si puedo meter estos botones adentro de la tabla  -->
			<tr>
			<th colspan="2">
			<%if(encontrado!=null){ %>
			<button class="botonEditar" align="center"
				onclick="javascript: editar('/admin/modificarCategoria/editar')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{%>

			<button class="botonEditar" align="center"
				onclick="javascript: registrar('/admin/modificarCategoria/agregar')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
			
			</th>
			</tr>
			</table>
			
		</form>

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