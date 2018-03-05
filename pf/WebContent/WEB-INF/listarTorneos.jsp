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


</head>

<script type="text/javascript">

	
	function verfixture(met) {
		document.myform.ranking.value="";
		document.myform.tablaPosiciones.value="";
		    document.myForm.action=met;
	
    }
	
	function tablaPosiciones(met) {
		document.myform.verfixture.value="";
		document.myform.ranking.value="";
	    document.myForm.action=met;

}
	function ranking(met) {
		document.myform.verfixture.value="";
		document.myform.tablaPosiciones.value="";
	    document.myForm.action=met;

}
	
	
	
	</script>

<body>

<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		<div class="container tablaPersona">
			
				 <h1>Lista de Torneos</h1>
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
 				<tr>


					<td align="center">Nombre</td>
					<td align="center">Fecha Inicio</td>
					<td align="center">Fecha Fin</td>
					<td align="center">Estado</td>
					<td align="center">Equipo Campeon</td>
					<td align="center" colspan="3">Accion</td>
				</tr>
  
  </thead>
  <%
					TorneosDAO torneoDao = new TorneosDAO();
					LinkedList<Torneo> torneos = torneoDao.listarTodosLosTorneos();
					for (Torneo t : torneos) {
						if(t.getEstado().getIdTipoEstado()!= TipoEstado.DESHABILITADA){
				%>
  <tbody>
  <tr>

					<td><%=t.getNombre()%></td>
					<td><%=t.getFechaInicio()%></td>
					<td><%=t.getFechaFin()%></td>
					<td><%=t.getEstado().getDescripcion()%></td>
					<%
						if (t.getEquipoGanador() != null) {
					%>
					<td><%=t.getEquipoGanador().getInstitucion().getNombre()%>-
						<%=t.getEquipoGanador().getCategorias().getAñoCategoria()%> - <%=t.getEquipoGanador().getNombreEquipo() %> </td>
					<%
						} else {
					%>
					<td>-</td>
					<%
						}
					%>
				
					
					<%
						JornadaDAO catJornada = new JornadaDAO();
					    LinkedList<Jornadas> listaJornada = catJornada.buscarporTorneos(t.getIdTorneos());
					    if(listaJornada!=null && listaJornada.size()>0){
					%>
					
					   <td><button class="verFixture"
							onclick="javascript: verfixture('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="verfixture" name="verfixture">Ver Fixture</button></td>
						<td><button class="botonGenerarFixture"
							onclick="javascript: tablaPosiciones('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="tablaPosiciones" name="tablaPosiciones">Tabla Posiciones</button></td>
						<td><button class="botonEditar"
							onclick="javascript: ranking('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="ranking" name="ranking">Ranking Goleadores</button></td>

					
					
				
					
					<%
					}else{
				%>
				    <th>
					</th>
				<%
					}
				%>
					
				</tr>
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