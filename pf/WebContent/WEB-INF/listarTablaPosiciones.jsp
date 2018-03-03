<%@page import="entidad.TablaPosiciones"%>
<%@page import="entidad.TipoEstado"%>
<%@page import="com.mysql.jdbc.EscapeTokenizer"%>
<%@page import="datos.JornadaDAO"%>
<%@page import="entidad.Jornadas"%>
<%@page import="entidad.Torneo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="datos.TorneosDAO"%>
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

<title>Listar Torneos</title>

<%

LinkedList<TablaPosiciones> listarTablaPosiciones = null;
Torneo torneo= null;
if(request.getSession().getAttribute("editador")!=null){
	torneo = (Torneo)request.getSession().getAttribute("editador");	
	listarTablaPosiciones = TablaPosiciones.generarTablaPosiciones(torneo);
}

%>


</head>

<script type="text/javascript">

	
	function verfixture(met) {
	     	 
		    document.myForm.action=met;
	
    }
	
	
	
	</script>

<body>

<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		<div class="container tablaPersona">
			
				 <h1>Tabla Posiciones</h1>
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
 				<tr>


					<td align="center">Equipo</td>
					<td align="center">Puntuacion</td>
					<td align="center">PG</td>
					<td align="center">PE</td>
					<td align="center">PP</td>
					
				</tr>
  
  </thead>
  <%
					if(listarTablaPosiciones!= null && listarTablaPosiciones.size()>0){
					for (TablaPosiciones t : listarTablaPosiciones) {
						
				%>
  <tbody>
  <tr>

					<th><%=t.getEquipo().getInstitucion().getNombre()%>-<%=t.getEquipo().getCategorias().getAñoCategoria()%>- <%=t.getEquipo().getNombreEquipo()%></th>
					<td><%=t.getPuntuacion()%></td>
					<td><%=t.getCantidadPartidosGanados()%></td>
					<td><%=t.getCantidadPartidosEmpatados()%></td>
					<td><%=t.getCantidadPartidosPerdidos()%></td>
					
				<%
					}}
				%>
				
				


			</table>
		</form>

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