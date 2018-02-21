<%@page import="datos.TorneosDAO"%>
<%@page import="datos.TipoEstadoDAO"%>
<%@page import="entidad.TipoEstado"%>
<%@page import="entidad.Categoria"%>
<%@page import="entidad.Institucion"%>
<%@page import="modelo.Conexion"%>
<%@page import="entidad.Torneo"%>
<%@page import="java.sql.ResultSet"%>
    <%@page import="java.util.LinkedList"%>
   <%@page import=" java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Editar Torneo</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	
	function editar(met) {
		if(confirm("Estas seguro de editar un nuevo Torneo?")){
			document.myform.registar.value=""  
		    document.myForm.action=met;
		};
    }
	
	function registar(met) {
		if(confirm("Estas seguro de registrar un nuevo Torneo?")){
			document.myform.editar.value=""  
		    document.myForm.action=met;
		};
    }

	
	</script>
</head>
<body>

<div id="contenedor">

		<jsp:include page="cabecera.jsp" />

	</div>

	
	<%
	Torneo encontrado= null;
	String idTorneo="";
	String nombre="";
	Date fechaI=null;
	Date fechaF=null;
	TipoEstado estado = new TipoEstado();
	Categoria idCategoriaCampeon=null;
	Institucion idInstitucionCampeon=null;
	String nombreEquipoCampeon="";
	TorneosDAO torDao= new TorneosDAO();

	if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Torneo)request.getSession().getAttribute("editador");	  
	   idTorneo=String.valueOf(encontrado.getIdTorneos());
	   nombre=encontrado.getNombre();
	   fechaI=encontrado.getFechaInicio();
	   fechaF=encontrado.getFechaFin();
	   estado=encontrado.getEstado();
	   idCategoriaCampeon=encontrado.getCategorias();
	   idInstitucionCampeon=encontrado.getInstitucion();
	   nombreEquipoCampeon=encontrado.getNombreEquipo();
	   
}
%>
	
	
		<div id="contenido">
		<form class="form-signin" id="myForm" name="myForm" action="" method="post">
			<table border="4" align="center">
			<tr>
				<td colspan="2">Editar Torneo Seleccionado</td>
			</tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
			<tr>
				<td>Nombre Torneo:</td>
				<td><input type="text" name="nombreTorneo" id="nombreTorneo" value="<%= nombre%>" /></td>
			</tr>	
			<tr>
					<td>Fecha de Inicio:</td>
					<td><input type="text" name="fechaI" id="fechaI"
						value="<%= fechaI %>" /></td>
				</tr>	
			<tr>
					<td>Fecha de Fin:</td>
					<td><input type="text" name="fechaF" id="fechaF"
						value="<%= fechaF %>" /></td>
			</tr>	
			<tr>
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

				</tr>
				<tr>
					<td>Categoria Campeona:</td>
					<td><input type="text" name="catCamp" id="catCamp"
						value="<%=idCategoriaCampeon.getDescripcion()  %>" /></td>
				</tr>
				<tr>
					<td>Institucion Campeona :</td>
					<td><input type="text" name="instiCamp" id="fechaF"
						value="<%= idInstitucionCampeon.getNombre() %>" /></td>
				</tr>
				<tr>
					<td>Nombre del Campeon:</td>
					<td><input type="text" name="nomequi" id="fechaF"
						value="<%= nombreEquipoCampeon %>" /></td>
				</tr>
				
				



			</table>
			<%if(encontrado!=null){ %>
			<button align="center"
				onclick="javascript: editar('/admin/modificarTorneo/editar')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{%>

			<button align="center"
				onclick="javascript: registrar('/admin/modificarTorneo/agregar')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
		</form>

	</div>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>