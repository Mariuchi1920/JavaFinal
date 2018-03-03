<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">


	 
	 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 
	
	
	 
</head>
<script type="text/javascript" language="JavaScript">
function carga(){
	 document.getElementById("nombreI").focus();
	 
	 
}
		
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
<body onload="carga()">

<div class="container-fluid">
	
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

	
		<div class="container tablaPersona">
		<h1>Nueva Institución</h1>
			<form id="needs-validation" name="myForm" action="" method="post" onsubmit="return validarDatos()" >
				<table class="table table-bordered" align="center">
				
				<tr><td>Institucion:</td> <td><input type="text" value="<%= nombre %>" name="institucion" id="nombreI" class="form-control" placeholder="Nombre de Institucion..." required=""/></td> </tr>
				<tr><td>Nombre Localia:</td><td><input type="text" value="<%= nombreLocalia %>" name="nombreLocalia"id="nomLocal"class="form-control" placeholder="Localía donde estan las canchas..." required=""/></td></tr>
				<tr><td>Direccion:</td><td><input type="text" value="<%= direccionLocalia %>" name="direccionLocalia"id="direccionlocalia"class="form-control" placeholder="Direccion donde estan las canchas..." required=""/></td></tr>
				<tr><td>Nombre delegado:</td><td><input type="text" value="<%= nombreDelegado %>" name="nombreDelegado"id="nombreDelegado"class="form-control" placeholder="Nombre delegado..." required=""/></td></tr>
				<tr><td>Apellido delegado:</td><td><input type="text" value="<%= apellidoDelegado %>" name="apellidoDelegado"id="apellidoDelegado"class="form-control" placeholder="Apellido delegado.." required=""/></td></tr>
				<tr><td>Telefono Delegado:</td><td><input type="text" value="<%= telefono %>" name="telefonoDelegado"id="telefonoDelegado"class="form-control" placeholder="Teléfono delegado..." required=""/></td></tr>
				<tr><td>Mail delegado:</td><td><input type="text" value="<%= mail %>" name="mail"id="mail"class="form-control" placeholder="Mail delegado..." required=""/></td></tr>
				
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