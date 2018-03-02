<%@page import="entidad.Util"%>
<%@page import="java.sql.Date"%>
<%@page import="entidad.TipoPersona"%>
<%@page import="datos.TipoPersonaDAO"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}CSS/estilos.css">
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js'></script>
<script src='validarDatosPersona.js'></script>

<script type="text/javascript">
	function editar(met) {
		if (confirm("Estas seguro de editar una nueva categoria?")) {
			document.myform.registar.value = ""
			document.myForm.action = met;
		}
		;
	}

	function registar(met) {
		if (confirm("Estas seguro de registrar una nueva categoria?")) {
			document.myform.editar.value = ""
			document.myForm.action = met;
		}
		;
	}
	
	
</script>
</head>
<body>

<div class="container">
	
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	
	
	<!-- slider -->
		<div class="container tablaPersona">
		
			<%
		Persona encontrado = null;
		String idPersona = "";
		String nombre = "";
		String apellido = "";
		String numeroDocumento = "";
		String tipoDocumento = "";
		String telefono = "";
		String usuario = "";
		String contraseña = "";
		///Date fechaNacimiento = Util.recuperarHoraActualStringDate();
		String fechaNacimiento = "";
		String mail = "";

		CategoriasDAO catdao = new CategoriasDAO();

		if (request.getSession().getAttribute("editador") != null) {
			encontrado = (Persona) request.getSession().getAttribute(
					"editador");
			nombre = String.valueOf(encontrado.getNombre());
			apellido = encontrado.getApellido();
			tipoDocumento = encontrado.getTipoDocumento();
			numeroDocumento = encontrado.getNumeroDocumento();
			telefono = encontrado.getTelefono();
			usuario = encontrado.getUsuario();
			contraseña = encontrado.getContraseña();
			///fechaNacimiento = encontrado.getFechaNacimiento();
			fechaNacimiento = String.valueOf(encontrado.getFechaNacimiento());
			mail = encontrado.getMail();
		} else if (request.getSession().getAttribute("usuario") != null) {
			if (!((Persona) request.getSession().getAttribute("usuario"))
					.isAdmin()) {
				encontrado = (Persona) request.getSession().getAttribute(
						"usuario");
				nombre = String.valueOf(encontrado.getNombre());
				apellido = encontrado.getApellido();
				tipoDocumento = encontrado.getTipoDocumento();
				numeroDocumento = encontrado.getNumeroDocumento();
				telefono = encontrado.getTelefono();
				usuario = encontrado.getUsuario();
				contraseña = encontrado.getContraseña();
			////	fechaNacimiento =encontrado.getFechaNacimiento();
			fechaNacimiento = String.valueOf(encontrado.getFechaNacimiento());
				mail = encontrado.getMail();
			}
		}
	%>
			<h1>Ingrese sus datos para el registro</h1>
			<form id="needs-validation" name="myForm" action="" method="post"
				onsubmit="return validacion()">
				<table class="table table-bordered" align="center">
					<thead>
						<tr>
							<%
								if (encontrado != null) {
							%>
							<td colspan="2">Editar</td>
							<%
								} else {
							%>

							<%
								}
							%>
						</tr>

						<tr>
							<td>Nombre</td>
							<td><input align="center" type="text" name="nombre"	id="nombre" value="<%=nombre%>" class="form-control" placeholder="nombre..." required="" /></td>
							<div class="invalid-feedback">
								debe ingresar un nombre
							</div>
						</tr>

						<tr>
							<td>Apellido:</td>
							<td><input align="center" type="text" name="apellido" id="apellido" value="<%=apellido%>" class="form-control" placeholder="apellido..." required="" /></td>
						
						</tr>


						<tr>
							<td>Numero Documento:</td>
							<td><input align="center" type="text" name="numeroDocumento"
								id="numeroDocumento" value="<%=numeroDocumento%>" class="form-control" placeholder="dni..." required=""/></td>
						</tr>
						<tr>
							<td>Telefono:</td>
							<td><input align="center" type="text" name="telefono"
								id="telefono" value="<%=telefono%>" class="form-control" placeholder="telefono..." required=""  /></td>
						</tr>

						<tr>
							<td>Fecha Nacimiento:</td>
							<td><input align="center" type="date" name="fechaNacimiento"
								id="fechaNacimiento" value="<%=fechaNacimiento%>" class="form-control" placeholder="Fecha nacimiento..." required="" /></td>
						</tr>

						<tr>
							<td>Mail</td>
							<td><input align="center" type="text" name="mail" id="mail"
								value="<%=mail%>" class="form-control" placeholder="mail..." required="" /></td>
						</tr>


						<tr>
							<td>Usuario:</td>
							<td><input align="center" type="text" name="usuario"
								id="usuario" value="<%=usuario%>" class="form-control" placeholder="usuario..." required="" /></td>
						</tr>

						<tr>
							<td>Contraseña:</td>
							<td><input align="center" type="text" name="contraseña"
								id="contraseña" value="<%=contraseña%>" class="form-control" placeholder=contraseña..." required="" /></td>
						</tr>


						<tr>
							<td>Tipo Persona:</td>
							<td>
								<%
									TipoPersonaDAO catPersona = new TipoPersonaDAO();
									TipoPersona persona = new TipoPersona();
									LinkedList<TipoPersona> listaPersona = catPersona.getTipoPersonas();
								%> <select align="center" name="listaTipoPersona"
								id="listaTipoPersona">

									<%
										if (request.getSession().getAttribute("usuario") != null
												&& ((Persona) request.getSession().getAttribute("usuario")).isAdmin()) {
											for (TipoPersona te : listaPersona) {
												if (encontrado != null) {
													if (encontrado.getTipoPersona().getIdTipoPersona() == te.getIdTipoPersona()) {
									%>
									<option selected="selected"
										value="<%=encontrado.getTipoPersona().getIdTipoPersona()%>"><%=encontrado.getTipoPersona().getDescripcion()%></option>

									<%
										} else {
									%>

									<option value="<%=te.getIdTipoPersona()%>"><%=te.getDescripcion()%></option>
									<%
										}
									%>

									<%
										} else {
									%>
									<option value="<%=te.getIdTipoPersona()%>"><%=te.getDescripcion()%></option>


									<%
										}
											}
										} else {

											for (TipoPersona te : listaPersona) {
												if (te.getIdTipoPersona() != 1) {
													if (encontrado != null) {
														if (encontrado.getTipoPersona().getIdTipoPersona() == te.getIdTipoPersona()) {
									%>
									<option selected="selected"
										value="<%=encontrado.getTipoPersona().getIdTipoPersona()%>"><%=encontrado.getTipoPersona().getDescripcion()%></option>

									<%
										} else {
									%>

									<option value="<%=te.getIdTipoPersona()%>"><%=te.getDescripcion()%></option>
									<%
										}
									%>

									<%
										} else {
									%>
									<option value="<%=te.getIdTipoPersona()%>"><%=te.getDescripcion()%></option>



									<%
										}
												}
											}
										}
									%>

							</select>
							</td>

						</tr>
						<td colspan="2" align="center">
							<%
								if (encontrado != null) {
							%>
							<button align="center"
								onclick="javascript: editar('/admin/modificarCategoria/editar')"
								id="editar" value="editar" name="editar" class="editar">Editar</button>
							<%
								} else {
							%>

							<button align="center"
								onclick="javascript: registrar('/admin/modificarCategoria/agregar')"
								id="registar" value="registar" name="registar" class="agregar">Agregar</button>
							<%
 	}
 %>
						</td>


					</thead>
				</table>
			</form>
		</div>
		<!-- slider -->


		<div class= "container Pie">
			<div id="Pie">
	 <div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	 
	</div>
		</div>
	
		</div>
		</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	
	<script type="text/javascript">
	(function(){
		'use strict';
		
		window.addEventListenner('load',function(){
			var form= document.getElementById('needs-validation');
			form.addEventListenner('submit',function(event){
				if(from.checkValidty()==false){
					event.preventDefault();
					event.stopPropagation();
					
				}
			form.classList.add('was-validated');
			},false);
		},false);
	})();
		);
		}
	</script>
</body>
</html>
