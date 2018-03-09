<%@page import="entidad.Persona"%>
<%@page import="datos.PersonasDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>Listar Personas</title>


</head>

<script type="text/javascript">
	
function editar(met) {
	document.myform.eliminar.value="";     
	document.myForm.action = met;
}
function eliminar(met) {
	document.myform.editar.value="";  
	document.myForm.action = met;
	
	
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
	
		<!-- slider -->
		<div class="container tablaPersona">
			
				 <h1>Administrador de Personas</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
    <tr>
    	<td align="center">Nombre</th>
					<td align="center">Apellido</td>
					<td align="center">Telefono</td>
					<td align="center">Fecha Nacimiento</td>
					<td align="center">Numero Documento</td>
					<td align="center">Mail</td>
					<td align="center">Tipo Persona</td>
					<td align="center" colspan="2">Acción</td>
          </tr>
  </thead>
  
				<%
					PersonasDAO catPersona = new PersonasDAO();
					LinkedList<Persona> personas = catPersona.listarPersonas();
					for (Persona c : personas) {
				%>
  <tbody>
    <tr>
      <td scope="row"><%=c.getNombre()%></td>
      				<td><%=c.getApellido()%></td>
					<td><%=c.getTelefono()%></td>
					<td><%=c.getFechaNacimiento().toString()%></td>
					<td><%=c.getNumeroDocumento()%></td>
					<td><%=c.getMail()%></td>
					<td><%=c.getTipoPersona().getDescripcion()%></td>
					<td>
					<button	class="botonEditar"
							onclick="javascript: editar('/admin/listarPersonas/')"
							value="<%= c.getIdPersona()%>" id="editar" name="editar">Editar</button> 
					</td>
					<td>	
					<button class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarPersonas/')"
							value="<%= c.getIdPersona()%>" id="eliminar" name="eliminar">Eliminar</button>
					</td>

				<%
					}
				%>

	</tr>
    <tr>
    <th colspan="9"><a type="button"  href="${pageContext.request.contextPath}/reguistarPersonas">Nueva Persona</a></th>
    </tr>
    
  </tbody>
</table>

				
		</form>
			</div>
		</div>
		<!-- slider -->


		<div class= "container Pie">
			<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />


			</div>
		</div>
	
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>