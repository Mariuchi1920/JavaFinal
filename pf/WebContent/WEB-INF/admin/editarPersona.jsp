<%@page import="entidad.Persona"%>
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
<title>Persona</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<script type="text/javascript">
	
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
<body>
	<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>


	<%
Persona encontrado = null;
String idPersona ="";
String nombre ="";
String apellido ="";
String numeroDocumento ="";
String tipoDocumento ="";
String telefono ="";
String usuario  ="";
String contraseña  ="";
String fechaNacimiento  ="";

CategoriasDAO catdao = new CategoriasDAO();

if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Persona)request.getSession().getAttribute("editador");	  
	   nombre=String.valueOf(encontrado.getNombre());
	   apellido=encontrado.getApellido();
	   tipoDocumento=encontrado.getTipoDocumento();
	   numeroDocumento=encontrado.getNumeroDocumento();
	   telefono=encontrado.getTelefono();
	   usuario=encontrado.getUsuario();
	   contraseña=encontrado.getContraseña();
	   fechaNacimiento=String.valueOf(encontrado.getFechaNacimiento());
}
%>

	<div id="contenido">


		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table border="4" align="center">
				<tr>
					<td colspan="2">Resgistarse</td>
				</tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
				<
				<tr>
					<td>Nombre</td>
					<td><input type="text" name="nombre" id="nombre"
						value="<%= nombre %>" /></td>
					
				</tr>
				



				<tr>
					<td>Apellido:</td>
					<td><input type="text" name="apellido" id="apellido"
						value="<%= apellido %>" /></td>
				</tr>
				<tr>
					<td>Tipo Documento:</td>
					<td><input type="text" name="tipoDocumento" id="tipoDocumento"
						value="<%= tipoDocumento %>" /></td>
				</tr>
				
				<tr>
					<td>Numero Documento:</td>
					<td><input type="text" name="numeroDocumento" id="numeroDocumento"
						value="<%= numeroDocumento %>" /></td>
				</tr>
				<tr>
					<td>Telefono:</td>
					<td><input type="text" name="telefono" id="telefono"
						value="<%= telefono %>" /></td>
				</tr>
				
				<tr>
					<td>Fecha Nacimiento:</td>
					<td><input type="text" name="fechaNacimiento" id="fechaNacimiento"
						value="<%= fechaNacimiento %>" /></td>
				</tr>
				
				<tr>
					<td>Usuario:</td>
					<td><input type="text" name="usuario" id="usuario"
						value="<%= usuario %>" /></td>
				</tr>
				
				<tr>
					<td>Contraseña:</td>
					<td><input type="text" name="contraseña" id="contraseña"
						value="<%= contraseña %>" /></td>
				</tr>
				
				
			<%-- 	<tr>
					<td>Estado:</td>
					<td>
						<% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
				                         TipoEstado tpEstado= new TipoEstado();
			
                               			 LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
                               			 %> <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
						<select name="listaTipoEStado" id="tipoEstado">
							<% for(TipoEstado te :listaEstado){  
                               			if(encontrado!=null){
                               			     if(estado.getIdTipoEstado()== te.getIdTipoEstado()){%>
							<option selected="selected"
								value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>

							<%}}else {%>
							<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>

							<% }} %>

					</select>
					</td>

				</tr> --%>



			</table>
			<%if(encontrado!=null){ %>
			<button align="center"
				onclick="javascript: editar('/admin/modificarCategoria/editar')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{%>

			<button align="center"
				onclick="javascript: registrar('/admin/modificarCategoria/agregar')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
		</form>

	</div>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>