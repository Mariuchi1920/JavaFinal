<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Categoria"%>
<%@page import="datos.CategoriasDAO"%>
<%@page import="modelo.Consulta"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Maestro categoria</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<script type="text/javascript">
	function nuevaCategoria(url) {
		window.open(url, "", "algun parametro que desees");
	}
	function submitForm(met) {
		document.myForm.action=met;
		//document.getElementById("myFrom").submit();
    }

</script>
</head>
<body>
	<div id="contenedor">

		<jsp:include page="cabezera.jsp" />

	</div>


	<div id="contenido">

		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">


			<table align="center" width="700" border="void" cellpadding="5">

				<tr>
					<td colspan="4" align="center">Administrador de categorias</td>
				</tr>
				<tr>
					<td align="center">Id Categoria</td>
					<td align="center">Descripcion</td>
					<td align="center">Estado</td>
					<td align="center">Accion</td>
				</tr>

				<%
					CategoriasDAO catDao = new CategoriasDAO();
					LinkedList<Categoria> categorias = catDao
							.listarTodasLasCategorias();
					for (Categoria c : categorias) {
				%>
				<tr>
					<th><%=c.getIdCategorias()%></th>
					<th><%=c.getDescripcion()%></th>
					<th><%=c.getEstado().getDescripcion()%></th>
					<th><button
						onclick="javascript: submitForm('CategoriaServlets/editar')"
						value="<%= c.getIdCategorias()%>"  name="editar">Editar</button>	
						<button 
						onclick="javascript: submitForm('CategoriaServlets/eliminar')"
						value="<%= c.getIdCategorias()%>" name="eliminar">Eliminar</button>
						</th>


					
				</tr>
				<%
					}
				%>

			</table>

		</form>
		<form action="nuevaCategoria.jsp" method="post">
			<input type="submit" value="Nueva Categoria">
		</form>
	</div>

	</div>



	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>
</body>
</html>
