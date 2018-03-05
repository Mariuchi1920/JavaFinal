<%@page import="java.sql.Time"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Partidos"%>
<%@page import="entidad.Util"%>
<%@page import="entidad.EquiposTorneos"%>
<%@page import="datos.EquiposTorneoDAO"%>
<%@page import="entidad.Equipo"%>
<%@page import="entidad.Jornadas"%>
<%@page import="datos.JornadaDAO"%>
<%@page import="datos.TorneosDAO"%>
<%@page import="datos.TipoEstadoDAO"%>
<%@page import="entidad.TipoEstado"%>
<%@page import="entidad.Categoria"%>
<%@page import="entidad.Institucion"%>
<%@page import="modelo.Conexion"%>
<%@page import="entidad.Torneo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.LinkedList"%>
<%@page import=" java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/estilos.css">
<head>
<meta charset="UTF-8">
<title>Editar Torneo</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<script type="text/javascript">
	
	function editar(met) {
		if(confirm("Estas seguro de editar el Partido?")){
			document.myform.visitante.value="" ;
		    document.myform.local.value="";  
		    document.myForm.action=met;
		};
    }
	
	function visitante(met) {
		
			document.myform.local.value="";
			document.myform.editar.value=""  ;
		    document.myForm.action=met;
		
    }
	
	function local(met) {
		if(confirm("Estas seguro de registrar un nuevo Torneo?")){
			document.myform.editar.value=""  ;
		    document.myform.visitante.value=""; 
		    document.myForm.action=met;
		};
    }

	
	</script>
</head>
<body>

	<div id="contenedor">
		<jsp:include page="/WEB-INF/cabecera.jsp" />



	</div>


	<%
	Partidos partido= null;
	 int idPartidos =0;
	 Jornadas jornada= null;
	 Persona arbrito = null;
	 Equipo equipoLocal= null;
	 Equipo equipoVisitante =null;
	 int golesVisitante= 0;
	 int golesLocal = 0;
	 TipoEstado estado = null;
	 String observaciones = "";
	 Time hora = null;

	if(request.getSession().getAttribute("editador")!=null){
		partido = (Partidos)request.getSession().getAttribute("editador");	  
		idPartidos=partido.getIdPartidos();
		  jornada = partido.getJornada();
		  arbrito = partido.getArbrito();
		  equipoLocal = partido.getEquipoLocal();
		  equipoVisitante = partido.getEquipoVisitante();
		  golesVisitante = partido.getGolesVisitante();
		  golesLocal = partido.getGolesLocal();
		  estado = partido.getEstado();
		  observaciones=partido.getObservaciones();
		  hora = partido.getHora();
	   
	   
}
%>


	 <div class="container tablaPersona">
		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			  <table class="table table-bordered" align="center">
				<tr>
					<td colspan="5"> Partido</td>
				</tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
				<tr>
					<td colspan="5">Jornada : <%= jornada.getIdJornadas()%> -
						Fecha : <%= jornada.getFechaDescripcion()%></td>

				</tr>
				<tr>
					<td colspan="5">Arbitro : <%= arbrito.getApellido()%>,<%= arbrito.getNombre()%>
						(<%= arbrito.getNumeroDocumento()%>)
					</td>

				</tr>

				<tr>
					<td>Equipo Local : <%=partido.getEquipoLocal().getInstitucion().getNombre() %>-<%=partido.getEquipoLocal().getCategorias().getAñoCategoria() %><%=partido.getEquipoLocal().getNombreEquipo() %>
					</td>
					<td><%=golesLocal %></td>
					<td>VS</td>
					<td><%=golesVisitante %></td>
					<td>Equipo Visitante : <%=partido.getEquipoVisitante().getInstitucion().getNombre() %>-<%=partido.getEquipoVisitante().getCategorias().getAñoCategoria() %><%=partido.getEquipoVisitante().getNombreEquipo() %>
					</td>

				</tr>
				
				<tr>
					<td colspan="2">
					<button  align="center" class="verFixture"
				onclick="javascript: local('/admin/verModificarPartido/')"
				id="local" value="local" name="local">Jugadores Local</button></td>
					<td colspan="1"></td>
					<td colspan="2">
					<button align="center" class="verFixture"
				onclick="javascript: visitante('/admin/verModificarPartido/')"
				id="visitante" value="visitante" name="visitante"> Jugadores Visitante </button></td>

				</tr>

				<tr>
					<td colspan="5">Hora : <%= hora%></td>

				</tr>

				<tr>
					<td>Observacion </td>
					
					<td colspan="4"><%=observaciones==null?"":observaciones %></td>
				</tr>

            
				<tr>
					<td>Estado:</td>
					
					<td colspan="4"><%=partido.getEstado().getDescripcion() %></td>
					
					

				</tr>







			</table>
 
			<button align="center" class="botonGenerarFixture" 
				onclick="javascript: editar('/verPartido/')"
				id="editar" value="editar" name="editar">Volver al fixture</button>

		</form>

	</div>

	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>

</body>
</html>