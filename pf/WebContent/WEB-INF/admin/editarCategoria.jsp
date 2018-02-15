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
	function submitForm(met) {
		document.myForm.action=met;
		
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

if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Categoria)request.getSession().getAttribute("editador");
	  
	   idCategoria=String.valueOf(encontrado.getIdCategorias());
	   descripcion=encontrado.getDescripcion();
	   estado=encontrado.getEstado();
}
%>

		<div id="contenido">
			
			
			<form class="form-signin" id="myForm" name="myForm" action="" method="post">
			<table border="4" align="center">
				<tr><td colspan="2">Editar categoria seleccionada</td></tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
				
				<tr><td>Id Categoria:</td> <td><%= idCategoria %></td> </tr>
				
				
				<tr><td>Descripcion:</td><td><input type="text" name="descripcion"id="descripcion" value="<%= descripcion %>"/></td></tr>
				<tr><td>Estado:</td><td> <% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
				                         TipoEstado tpEstado= new TipoEstado();
			
                               			 LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
                               			 %>
                               			 <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
                               			 <select name="listaTipoEStado" id="tipoEstado">
                               			<% for(TipoEstado te :listaEstado){  
                               			if(encontrado!=null){  %>
                               				<option selected="selected" value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>
                               				
                               			<%}else {%>
                               			      <option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                               			
                               			<% }} %>

                               			 </select>
                               		</td>		 
                               					
              </tr>
              
			
				
			</table>
			
			   <button align="center" onclick="javascript: submitForm('regCategoria/editar')" value="<%=idCategoria %>name="editar">Editar</button>
			</form>

		</div>
	
	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>