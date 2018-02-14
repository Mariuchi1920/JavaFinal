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
	<title>Editar Categoria Seleccionada</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function pregunta() {
		if(confirm("Estas seguro de registrar una nueva categoria?")){
			Document.categoriaNueva.submit();
			
		};
		}
	function pregunta() {
		
		Document.categoriaNueva.submit();
		}
	
	</script>
</head>
<body>
	<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>


<%
Categoria encontrado = null;
String idCategoria ="";
String descripcion="";
TipoEstado estado = new TipoEstado();
CategoriasDAO catdao = new CategoriasDAO();

if(request.getAttribute("editador")!=null){
	   encontrado = (Categoria)request.getAttribute("editador");
	  
	   idCategoria=String.valueOf(encontrado.getIdCategorias());
	   descripcion=encontrado.getDescripcion();
	   estado=encontrado.getEstado();
}
%>

		<div id="contenido">
			
			
			<form action="" method="post" name="categoriaNueva">
			<table border="4" align="center">
				<tr><td colspan="2">Editar categoria seleccionada</td></tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
				
				<tr><td>Id Categoria:</td> <td><input type="text" readonly="readonly" name="idCategoria" id="idCategoria" value="<%= idCategoria %>"/></td> </tr>
				
				
				<tr><td>Descripcion:</td><td><input type="text" name="descripcion"id="descripcion" value="<%= descripcion %>"/></td></tr>
				<tr><td>Estado:</td><td> <% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
				                         TipoEstado tpEstado= new TipoEstado();
			
                               			 LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
                               			 %>
                               			 <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
                               			 <select name="listaTipoEStado" id="tipoEstado">
                               			<% for(TipoEstado te :listaEstado){  %>
                               			<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                               			
                               			<%} if(encontrado!=null){  %>
                               				<option value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>
                               				
                               			<%}%>

                               			 </select>
                               		</td>		 
                               					
              </tr>
              
				<tr><td><input type= "submit" value="Registrar Categoria" onclick="preguntar(/regCategoria/agregar)"></td></tr>
				<td><input type= "submit" value="Editar" onclick="editar(/regCategoria/editar)"></td></tr>
				
			</table>
			</form>

		</div>
	
	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>