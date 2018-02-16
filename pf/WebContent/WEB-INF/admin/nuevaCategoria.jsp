<%@page import="datos.TipoEstadoDAO"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
	<meta charset="UTF-8">
	<title>Nueva Categoria</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function pregunta() {
		if(confirm("Estas seguro de registrar una nueva categoria?")){
			Document.categoriaNueva.submit();
			
		};
		}
	
	</script>
</head>
<body>

<div id="contenedor">

		<jsp:include page="/WEB-INF/admin/cabezera.jsp" />

	</div>

		<div id="contenido">
			
			
			<form action="${pageContext.request.contextPath}/admin/listarCategoria?accion=registrar" method="post" name="categoriaNueva">
			<table border="4" align="center">
				<tr><td colspan="2">Nueva Categoria</td></tr>
				<tr><td>Id Categoria:</td> <td><input type="text" name="idCategoria" id="idCategoria"/></td> </tr>
				<tr><td>Descripcion:</td><td><input type="text" name="descripcion"id="descripcion"/></td></tr>
				<tr><td>Estado:</td><td> <% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
                						TipoEstado tpEstado= new TipoEstado();

      			 							LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
				
                               			 %>
                               			 <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
                               			 <select name="listaTipoEStado" id="tipoEstado">
                               			<% for(TipoEstado te :listaEstado){  %>
                               			<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                               			
                               			<%} %>

                               			 </select>
                               		</td>		 
                               					
              </tr>
				<tr><td><input type= "submit" value="Registrar Categoria" onclick="pregunta()"></td></tr>
				
			</table>
			</form>

		</div>
		<div id="contenedor">

		<jsp:include page="/WEB-INF/admin/pie.jsp" />

	</div>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>