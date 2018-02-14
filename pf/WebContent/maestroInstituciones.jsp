<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="datos.InstitucionesDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Administrador de instituciones</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>


		<div id="contenido">
				
				<table align="center" width="800" border="void" cellpadding="5">
			 			<tr>
						<td colspan="10" align="center">Administrador de Instituciones</td>
						</tr>
						<tr>
							<td>Id Institucion</td>
							<td>Nombre</td>
							<td>Nombre Localia</td>
							<td>Direccion Localia</td>
							<td>Nombre Delegado</td>
							<td>Apellido Delegado</td>
							<td>Telefono Delegado</td>
							<td>Mail Delegado</td>
							<td colspan="2">Accion</td>
		
						</tr>
						<% InstitucionesDAO institucionDAO= new InstitucionesDAO();
						   LinkedList <Institucion> listadoI = institucionDAO.listarTodasLasInstituciones();
						
						 for(Institucion in: listadoI){ %>
						<tr>
							<td><%=in.getIdInstituciones() %></td>
							<td><%=in.getNombre() %></td>
							<td><%=in.getNombreLocalia() %></td>
							<td><%=in.getDireccionLocalia() %></td>
							<td><%=in.getNombreDelegado() %></td>
							<td><%=in.getApellidoDelegado() %></td>
							<td><%=in.getTelefonoDelegado() %></td>
							<td><%=in.getMailDelegado() %></td>
							
							<td><button
						onclick="javascript: submitForm('InstitucionServlets/editar')"
						value="<%= in.getIdInstituciones()%>"  name="editar">Editar</button></td>	
						<td><button  
						onclick="javascript: submitForm('InstitucionServlets/eliminar')"
						value="<%= in.getIdInstituciones()%>" name="eliminar">Eliminar</button>
						</td>
						</tr>
						
						<% } %>
								</table>
 				 <form action="nuevaInstitucion.jsp" method="post">
					<input type="submit" value="Nueva Institucion" align="center">
				</form>

		</div>
		</div>
		
	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>
