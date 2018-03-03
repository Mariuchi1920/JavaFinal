<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>Listar Instituciones</title>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value=""     
	document.myForm.action = met;
}
function eliminar(met) {
	if (confirm("Estas seguro de eliminar Institución?")) {
		
		document.myform.editar.value=""  
		document.myForm.action = met;
	
	};
	}
	
	</script>

</head>
<body>
<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		
		<!--cuerpo  -->
		<div class="container tablaPersona">
		<h1>Administrador Instituciones</h1>
			<form id="needs-validation" name="myForm" action="" method="post" onsubmit="return validarDatos()">
				<table class="table table-bordered" align="center">
					<thead>
					
					<tr>
					
					<td>Nombre</td>
					<td>Nombre Localia</td>
					<td>Direccion Localia</td>
					<td>Nombre Delegado</td>
					<td>Apellido Delegado</td>
					<td>Telefono Delegado</td>
					<td>Mail Delegado</td>
					<td colspan="2">Accion</td>

				</tr>
		</thead>
		<tbody>
		<% InstitucionesDAO institucionDAO= new InstitucionesDAO();
						   LinkedList <Institucion> listadoI = institucionDAO.listarTodasLasInstituciones();
						   
						if(listadoI!=null){
						 for(Institucion in: listadoI){ %>
				<tr>
					
					<td><%=in.getNombre() %></td>
					<td><%=in.getNombreLocalia() %></td>
					<td><%=in.getDireccionLocalia() %></td>
					<td><%=in.getNombreDelegado() %></td>
					<td><%=in.getApellidoDelegado() %></td>
					<td><%=in.getTelefonoDelegado() %></td>
					<td><%=in.getMailDelegado() %></td>
					<td>
					<button class="botonEditar"
							onclick="javascript: editar('/admin/listarInstituciones/')"
							value="<%=in.getIdInstituciones()%>" id="editar" name="editar">Editar</button> 
					</td>
					<td>
						<button
							class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarInstituciones/')"
							value="<%=in.getIdInstituciones()%>" id="eliminar" name="eliminar">Eliminar</button>
                     </td>
				</tr>

				<% }}else {%>
				<tr>
				<th colspan="8" style="color: red">
				No hay instituciones disponibles !</th>
				</tr>
				<% } %>
				
				<tr>
				<th colspan="10">
			<a type="button"  href="${pageContext.request.contextPath}/admin/modificarInstitucion">Nueva Institución</a>
			</th>
				</tr>	
		</tbody>
		
		</table>
		</form>
		<%-- <table align="center" width="800" border="void" cellpadding="5">
		<th colspan="10">
			<a type="button"  href="${pageContext.request.contextPath}/admin/modificarInstitucion">Nueva Institución</a>
			</th>
		</table>
		</div> --%>
		
		
		
	
</div>
</div>
<!--cuerpo  -->
		<div class= "container Pie">
			<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />


			</div>
		</div>
	
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>