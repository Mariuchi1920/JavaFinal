<%@page import="entidad.TipoEstado"%>
<%@page import="entidad.TipoPersona"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.MisPartidos"%>
<%@page import="entidad.Partidos"%>
<%@page import="entidad.FixtureTorneo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="datos.PartidoDAO"%>
<%@page import="datos.JornadaDAO"%>
<%@page import="entidad.Jornadas"%>
<%@page import="datos.TorneosDAO"%>
<%@page import="entidad.Torneo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
<title>Fixture</title>
</head>
<script type="text/javascript">
	
function verpartido(met) {
// 	document.myform.eliminar.value=""     
	document.myForm.action = met;
}

	
	</script>

<body>


<%
	
   Persona encontrado =null;
	LinkedList<Partidos> verMisPartidos =  null;

	if(request.getSession().getAttribute("usuario")!=null){
	    encontrado = (Persona)request.getSession().getAttribute("usuario");	  
	    verMisPartidos = MisPartidos.buscarMisPartidos(encontrado);
	    
	   }
 
	   

%>
	


<div class="container">
		<div id="contenedor">
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	</div>
	
	
	<div class="container">
		<form id="myForm" name="myForm" action="" method="post">
		
		<h3>Proximos Partidos de : <%=encontrado.getApellido() %>,<%=encontrado.getNombre() %></h3>
		<%
		if(verMisPartidos!=null){
			  
			   for(int i=0; i<verMisPartidos.size(); i++){
				   if(verMisPartidos.get(i).getEstado().getIdTipoEstado()!=TipoEstado.JUGADO){
		      %>
		      
		      
		    <table class="table" align="center" width="700" border="void" cellpadding="5">
			  <thead class="thead-default">
			
			    <tr>
					<td colspan="6" align="center">Torneo : <%=verMisPartidos.get(i).getJornada().getTorneos().getNombre() %></td>
				</tr>
                <tr>
					<td colspan="6" align="center">Jornada : <%=verMisPartidos.get(i).getJornada().getIdJornadas() %> - Fecha: <%=verMisPartidos.get(i).getJornada().getFechaDescripcion() %> (<%=verMisPartidos.get(i).getJornada().getEstado().getDescripcion() %>  )</td>
				</tr>
				<tr>
					<td colspan="6" align="center">Partido</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">Local</td> <td align="center">VS</td> <td colspan="2" align="center">Visitante</td>
				</tr>
					
			     <tr>
					<td colspan="2" align="center"><%=verMisPartidos.get(i).getEquipoLocal().getInstitucion().getNombre() %>-<%=verMisPartidos.get(i).getEquipoLocal().getCategorias().getAñoCategoria() %><%=verMisPartidos.get(i).getEquipoLocal().getNombreEquipo() %>  </td> 
					
					 </td> <td align="center">VS</td> 
					
					 <td colspan="2" align="center"><%=verMisPartidos.get(i).getEquipoVisitante().getInstitucion().getNombre() %>-<%=verMisPartidos.get(i).getEquipoVisitante().getCategorias().getAñoCategoria() %><%=verMisPartidos.get(i).getEquipoVisitante().getNombreEquipo() %> </td> 
					 <td align="center">
				</tr>
				<tr>
					<td colspan="3" align="center">Hora</td>
					<td colspan="2" align="center"><%=verMisPartidos.get(i).getHora() %></td>  
				</tr>

				</thead>
			</table>
			
		      
		
		<% }}}else{ %>
		
		
		<h3>No se han encontrados partidos pendientes</h3>
			
			<%} %>
		
	
		</form>
	
	</div>
	
	
	</div>
		
		 <div id="Pie">
			<jsp:include page="/WEB-INF/pie.jsp" />
		 </div>
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>



</body>
</html>