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
						<td colspan="8" align="center">Administrador de Instituciones</td>
						</tr>
						<tr>
							<td>Id Institucion</td>
							<td>Nombre</td>
							<td>Nombre Localia</td>
							<td>Direccion Localia</td>
							<td>Nombre Delegado</td>
							<td>Apellido Delegado</td>
							<td>Telefono Delegado</td>
							<td>Accion</td>
		
						</tr>
						<% 
						Institucion ins= new Institucion();
						LinkedList <Institucion> listaInstituciones= ins.getInstitucion();
						for(int i=0; i<listaInstituciones.size();i++){
						%>
						<tr><th><%=listaInstituciones.get(i).getIdInstituciones() %></th>
							<th><%=listaInstituciones.get(i).getNombre() %></th>
							<th><%=listaInstituciones.get(i).getNombreLocalia() %></th>
							<th><%=listaInstituciones.get(i).getDireccionLocalia() %></th>
							<th><%=listaInstituciones.get(i).getNombreDelegado() %></th>
							<th><%=listaInstituciones.get(i).getApellidoDelegado() %></th>
							<th><%=listaInstituciones.get(i).getTelefonoDelegado() %></th>
							<th><%=listaInstituciones.get(i).getMailDelegado() %></th>
							<th><img alt="" src="imagen/iconoEditar.png" width="30" height="30">||<a href="eliminarInstitucion.jsp?idinstitucion=<%=listaInstituciones.get(i).getIdInstituciones()%>">
	 						<img alt="" src="imagen/iconoEliminar.png" width="30" height="30"></a></th>
	
							</tr>
	
						<%		
							}
						%>
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