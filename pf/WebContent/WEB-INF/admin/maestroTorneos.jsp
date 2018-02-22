<%@page import="entidad.Torneo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="datos.TorneosDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="contenedor">

		<jsp:include page="/WEB-INF/admin/cabecera.jsp" />

	</div>


	<div id="contenido">
	<form id="myForm" name="myForm" action="" method="post">
			<table align="center" width="700" border="void" cellpadding="5">
			<tr>
			<td colspan="10" align="center">Administrador de Torneo</td>
			</tr>
			<tr>
			<td align="center">Id Torneo</td>
			<td align="center">Nombre</td>
			<td align="center">Fecha Inicio</td>
			<td align="center">Fecha Fin</td>
			<td align="center">Estado</td>
			<td align="center">Categoria</td>
			<td align="center">Intitucion</td>
			<td align="center">Equipo</td>
			<td align="center" colspan="2">Accion</td>
			</tr>
			<%
			TorneosDAO torneoDao= new TorneosDAO();
			LinkedList <Torneo> torneos= torneoDao.listarTodosLosTorneos();
			for (Torneo t : torneos) {
				%>
				<tr>
			<th><%= t.getIdTorneos() %></th>
			<th><%= t.getNombre() %></th>
			<td><%= t.getFechaInicio() %></td>
			<td><%= t.getFechaFin() %></td>
			<td><%= t.getEstado().getDescripcion() %></td>
			<td ></td>
			<td ></td>
			<td ></td>
			<th><button class="botonEditar" onclick= "javascript: editar('/admin/listarTorneoeditar/')"
							value="<%= t.getIdTorneos()%>" id="editar" name="editar">Editar</button> </th>
						<th><button
							class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarTorneoeliminar/')"
							value="<%=t.getIdTorneos()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>
				</tr>
				<%
					}
				%>
			<th colspan="10"> <a type="button"  href="${pageContext.request.contextPath}/admin/modificarTorneo">Nuevo Torneo</th>
		</form>
	
		
	</table></form>

</div>

	<div id="Pie">
		<jsp:include page="/WEB-INF/admin/pie.jsp" />

	</div>
</body>
</html>