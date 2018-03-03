<%@page import="entidad.RankingPosiciones"%>
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

LinkedList<RankingPosiciones> listarRaking = null;
Torneo torneo= null;
if(request.getSession().getAttribute("editador")!=null){
	torneo = (Torneo)request.getSession().getAttribute("editador");	
	listarRaking = RankingPosiciones.generarRankingPosiciones(torneo);
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


					<td align="center">Jugador</td>
					<td align="center">Cantidad Goles</td>
				
					
				</tr>
  
  </thead>
  <%
					if(listarRaking!= null && listarRaking.size()>0){
					for (RankingPosiciones t : listarRaking) {
						
				%>
  <tbody>
  <tr>

					<th><%=t.getJugadores().getApellido()%>,<%=t.getJugadores().getNombre()%></th>
					<td><%=t.getCantidadGoles()%></td>
					
					
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