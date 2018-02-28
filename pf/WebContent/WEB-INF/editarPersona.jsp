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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
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

	<%
		if (request.getSession().getAttribute("usuario") != null) {
	%>
	<div id="contenedor">
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	</div>

	<%
		}
	%>
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

	<div align="center" id="contenido">


		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table border="4" align="center">
				<tr>
					<%
						if (encontrado != null) {
					%>
					<td colspan="2">Editar</td>
					<%
						} else {
					%>
					<td colspan="2">Resgistarse</td>
					<%
						}
					%>
				</tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
				
				<tr>
					<td>Nombre</td>
					<td><input align="center" type="text" name="nombre"
						id="nombre" value="<%=nombre%>" /></td>

				</tr>

				<tr>
					<td>Apellido:</td>
					<td><input align="center" type="text" name="apellido"
						id="apellido" value="<%=apellido%>" /></td>
				</tr>


				<tr>
					<td>Numero Documento:</td>
					<td><input align="center" type="text" name="numeroDocumento"
						id="numeroDocumento" value="<%=numeroDocumento%>" /></td>
				</tr>
				<tr>
					<td>Telefono:</td>
					<td><input align="center" type="text" name="telefono"
						id="telefono" value="<%=telefono%>" /></td>
				</tr>

				<tr>
					<td>Fecha Nacimiento:</td>
					<td>
					
					 <input align="center" type="date"   name="fechaNacimiento"
						id="fechaNacimiento" value="<%=fechaNacimiento%>" /></td> 
				</tr>
				
				<tr>
					<td>Mail</td>
					<td><input align="center" type="text" name="mail"
						id="mail" value="<%=mail%>" /></td>
				</tr>
				

				<tr>
					<td>Usuario:</td>
					<td><input align="center" type="text" name="usuario"
						id="usuario" value="<%=usuario%>" /></td>
				</tr>

				<tr>
					<td>Contraseña:</td>
					<td><input align="center" type="text" name="contraseña"
						id="contraseña" value="<%=contraseña%>" /></td>
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
										&& ((Persona) request.getSession().getAttribute("usuario"))
												.isAdmin()) {
									for (TipoPersona te : listaPersona) {
										if (encontrado != null) {
											if (encontrado.getTipoPersona().getIdTipoPersona() == te
													.getIdTipoPersona()) {
							%>
							<option selected="selected"
								value="<%=encontrado.getTipoPersona()
									.getIdTipoPersona()%>"><%=encontrado.getTipoPersona()
									.getDescripcion()%></option>

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
												if (encontrado.getTipoPersona().getIdTipoPersona() == te
														.getIdTipoPersona()) {
							%>
							<option selected="selected"
								value="<%=encontrado.getTipoPersona()
										.getIdTipoPersona()%>"><%=encontrado.getTipoPersona()
										.getDescripcion()%></option>

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
						id="editar" value="editar" name="editar">Editar</button> <%
 	} else {
 %>

					<button align="center"
						onclick="javascript: registrar('/admin/modificarCategoria/agregar')"
						id="registar" value="registar" name="registar">Agregar</button> <%
 	}
 %>
				</td>
			</table>

		</form>

	</div>

	<div align="center" id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
</body>
</html>