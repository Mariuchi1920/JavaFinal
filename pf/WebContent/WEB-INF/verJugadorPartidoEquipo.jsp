<%@page import="datos.EquiposJugadoresDAO"%>
<%@page import="datos.JugadoresPartidosDAO"%>
<%@page import="entidad.JugadoresPartido"%>
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
		if(confirm("Si edita, el partido cambia estado Jugado?")){
			
		    document.myForm.action=met;
		};
    }
	
	function agregarEquipos(met) {
		
			document.myform.registar.value="";
			document.myform.editar.value=""  ;
		    document.myForm.action=met;
		
    }
	
	function registar(met) {
		if(confirm("Estas seguro de registrar un nuevo Torneo?")){
			document.myform.editar.value=""  ;
				 document.myform.agregarEquipos.value=""; 
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
	 Equipo equipo= null;
	 Equipo equipoLocal= null;
	 Equipo equipoVisitante =null;
	 int golesVisitante= 0;
	 int golesLocal = 0;
	 boolean isEquipoLocal = false;
	 boolean isEquipoVisitante = false;
	 JugadoresPartidosDAO catJugadorPartido = new JugadoresPartidosDAO();
	 EquiposJugadoresDAO catEquipoJugadores = new EquiposJugadoresDAO();
	 LinkedList<JugadoresPartido> jugadores = new  LinkedList<JugadoresPartido>();

	if(request.getSession().getAttribute("editador")!=null && request.getSession().getAttribute("jugadorPartido")!=null){
		partido = (Partidos)request.getSession().getAttribute("editador");	  
		idPartidos=partido.getIdPartidos();
		equipo = (Equipo) request.getSession().getAttribute("jugadorPartido");
		 
	     equipoLocal = partido.getEquipoLocal();
		 equipoVisitante = partido.getEquipoVisitante();
		 golesVisitante = partido.getGolesVisitante();
		 golesLocal = partido.getGolesLocal();
		 
		 LinkedList<JugadoresPartido> jugadoresPartido = catJugadorPartido.buscarIDPartido(idPartidos);
		 
		 LinkedList<Persona> jugadoresEquipo = catEquipoJugadores.listarTodasLosJugadores(equipo) ;
		 
		 for(JugadoresPartido jugadorPartido :jugadoresPartido){
			 if(Persona.buscarPersona(jugadoresEquipo, jugadorPartido.getJugadores())){
				 jugadores.add(jugadorPartido);
			 }
		 }
		 
		 if(Equipo.ifIgualDosEquipos(equipoLocal, equipo)){
			 isEquipoLocal=true;
			 
		 }else  if(Equipo.ifIgualDosEquipos(equipoVisitante, equipo)){
			 isEquipoVisitante=true;
		 }
		
	   
}
%>


	<div class="container tablaPersona">
		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table class="table table-bordered" align="center">
				<tr>
					<td colspan="4">Jugadores Equipo :  <%= equipo.getInstitucion().getNombre() %> - <%= equipo.getCategorias().getAņoCategoria()%> - <%= equipo.getNombreEquipo() %></td>
				</tr>
	            
	           <tr>
					<td>Jugador</td> <td>Cantidad goles</td> <td>Cantidad tajerta Amarillas</td>  <td>Cantidad tajerta Rojas</td>
 
				</tr> 
				
				<%if (jugadores !=null && jugadores.size()>0 )  {
					for(JugadoresPartido jugador :jugadores ){   %>
					
			      <tr>
					<td><%=jugador.getJugadores().getApellido() %>,<%=jugador.getJugadores().getNombre() %> (<%=jugador.getJugadores().getNumeroDocumento() %>)  </td>
					 <td><%=jugador.getCatidadGoles() %></td> 
						
					 <td><%=jugador.getCantidadTarjetasAmarillas() %></td>  
				   <td><%=jugador.getCantidadTarjetasRojas() %></td>
 
				</tr>
					
					
					
				<%}}%>
	            
				
             <tr>
				<td colspan="4">
					<a type="button" align="center"
				        href="${pageContext.request.contextPath}/verPartido">Volve al partido </a>
					</td>
				</tr>





			</table>

			

		</form>

	</div>

	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>

</body>
</html>