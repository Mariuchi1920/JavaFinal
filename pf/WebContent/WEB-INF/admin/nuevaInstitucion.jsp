<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Editar Institucion</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	<script type="text/javascript">
	function editar(met) {
		if(confirm("Estas seguro de editar esta Institución?")){
			document.myform.registar.value=""  
		    document.myForm.action=met;
		};
    }
	
	function registar(met) {
		if(confirm("Estas seguro de registrar una nueva Institución?")){
			document.myform.editar.value=""  
		    document.myForm.action=met;
		};
    }
	
	</script>
</head>
<body>

<div id="contenedor">

	<jsp:include page="/WEB-INF/cabecera.jsp" />
	</div>

	<%
Institucion encontrado = null;
String idInstitucion="";
String nombre ="";
String nombreLocalia ="";
String direccionLocalia="";
String nombreDelegado ="";
String apellidoDelegado ="";
String telefono="";
String mail ="";
Institucion estado = new Institucion();
InstitucionesDAO instdao = new InstitucionesDAO();

if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Institucion)request.getSession().getAttribute("editador");	  
	   idInstitucion=String.valueOf(encontrado.getIdInstituciones());
	    nombre =encontrado.getNombre();
	    nombreLocalia =encontrado.getNombreLocalia();
	    direccionLocalia=encontrado.getDireccionLocalia();
	    nombreDelegado =encontrado.getNombreDelegado();
	    apellidoDelegado =encontrado.getApellidoDelegado();
	    telefono=encontrado.getTelefonoDelegado();
	    mail = encontrado.getMailDelegado();
}
%>

	
		<div id="contenido">
			
			<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
		
			<table border="4" align="center">
				<tr><td colspan="2">Nueva Intitucion</td></tr>
				<tr><td>Institucion:</td> <td><input type="text" value="<%= nombre %>" name="institucion" id="nombre"/></td> </tr>
				<tr><td>Nombre Localia:</td><td><input type="text" value="<%= nombreLocalia %>" name="nombreLocalia"id="descripcion"/></td></tr>
				<tr><td>Direccion:</td><td><input type="text" value="<%= direccionLocalia %>" name="direccionLocalia"id="direccionlocalia"/></td></tr>
				<tr><td>Nombre delegado:</td><td><input type="text" value="<%= nombreDelegado %>" name="nombreDelegado"id="nombreDelegado"/></td></tr>
				<tr><td>Apellido delegado:</td><td><input type="text" value="<%= apellidoDelegado %>" name="apellidoDelegado"id="apellidoDelegado"/></td></tr>
				<tr><td>Telefono Delegado:</td><td><input type="text" value="<%= telefono %>" name="telefonoDelegado"id="telefonoDelegado"/></td></tr>
				<tr><td>Mail delegado:</td><td><input type="text" value="<%= mail %>" name="mail"id="mail"/></td></tr>
				
			</table>
			
			<%if(encontrado!=null){ %>
			<button align="center"
				onclick="javascript: editar('/admin/modificarInstitucion/editar')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{%>

			<button align="center"
				onclick="javascript: registrar('/admin/modificarInstitucion/agregar')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
			</form>
		

		</div>
	

	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
	</div>
</body>
</html>
