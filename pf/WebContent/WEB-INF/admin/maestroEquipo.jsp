<%@page import="datos.EquiposDAO"%>
<%@page import="entidad.Equipo"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.util.LinkedList"%>
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

<title>Listado de equipos</title>


</head>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value=""     
	document.myForm.action = met;
}
function eliminar(met) {
	if (confirm("Estas seguro de eliminar Equipo?")) {
		
		document.myform.editar.value=""  
		document.myForm.action = met;
	
	};
	}
	
	</script>

<body>



<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		
		<div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3 style="color: red;"><%=request.getSession().getAttribute("error").toString()%></h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				
			</div>
	</div>
		
	<div class="container tablaPersona">
			
				 <h1>Administrador de Equipos</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
				<thead>
				<tr>
					
					<td>Nombre Institución</td>
					<td>Nombre Categoria</td>
					<td>Nombre Equipo</td>
					<td>Entrenador</td>
					<td colspan="2">Accion</td>

				</tr>
				</thead>			
				<% EquiposDAO equipoDAO= new EquiposDAO();
					LinkedList <Equipo> listadoEquipo=equipoDAO.listarTodasLosEquipos();
					if(listadoEquipo!=null){
						for(Equipo equipo: listadoEquipo){  %>
								
					<tr>
					<td><%=equipo.getInstitucion().getNombre() %></td>
					<td><%=equipo.getCategorias().getAñoCategoria() %></td>
					<td><%=equipo.getNombreEquipo() %></td>
					<td><%=equipo.getEntrenador().getApellido() %>, <%=equipo.getEntrenador().getNombre() %></td>
					<td>
					<button class="botonEditar"
							onclick="javascript: editar('/admin/listarEquipo')"
							value="<%=equipo.getInstitucion().getIdInstituciones()%>/<%=equipo.getCategorias().getIdCategorias()%>/<%=equipo.getNombreEquipo()%>" id="editar" name="editar">Editar/agregarJugadores</button> 
						<button class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarEquipo')"
							value="<%=equipo.getInstitucion().getIdInstituciones()%>/<%=equipo.getCategorias().getIdCategorias()%>/<%=equipo.getNombreEquipo()%>" id="eliminar" name="eliminar">Eliminar</button>
                     </td>
				</tr>

				<%}} else {%>
				<tr>
				<th colspan="8" style="color: red">
				No hay equipos Inscriptos todavia!</th>
				</tr>
				<% } %>
				<tr>
				<th colspan="6">
				<a type="button"  href="${pageContext.request.contextPath}/admin/modificarEquipo">Nuevo Equipo</a>
				</th>
				</tr>
				
			</table>

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