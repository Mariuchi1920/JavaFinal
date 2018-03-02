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
	Torneo encontrado= null;
	int idTorneo=0;
	String nombre="";
	
	TorneosDAO torDao= new TorneosDAO();
	JornadaDAO catJornada = new JornadaDAO();
	PartidoDAO catPartido = new PartidoDAO();
	
	LinkedList<FixtureTorneo> fixture =  null;

	if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Torneo)request.getSession().getAttribute("editador");	  
	   idTorneo=encontrado.getIdTorneos();
	   nombre=encontrado.getNombre();
	   LinkedList <Jornadas> jornadas = catJornada.buscarporTorneos(idTorneo);
	   if(jornadas!=null){
		   fixture = new LinkedList<FixtureTorneo>();
		   for(int i=0; i<jornadas.size(); i++){
			   FixtureTorneo fix= new FixtureTorneo();
			   fix.setJornada(jornadas.get(i));
			   LinkedList<Partidos> partidos = catPartido.buscarporIdJornada(jornadas.get(i).getIdJornadas());
			   fix.setPartidos(partidos);
			   fixture.add(fix);

		   }
	   }
 
	   
}
%>
	


<div class="container">
		<div id="contenedor">
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	</div>
	
	
	<div class="container">
		<form id="myForm" name="myForm" action="" method="post">
		
		<h3>Torneo : <%=nombre %> </h3>
		<%
		if(fixture!=null){
			  
			   for(int i=0; i<fixture.size(); i++){
		      %>
		      
		      
		    <table class="table" align="center" width="700" border="void" cellpadding="5">
			  <thead class="thead-default">
			
			
                <tr>
					<td colspan="6" align="center">Jornada : <%=fixture.get(i).getJornada().getIdJornadas() %> - Fecha: <%=fixture.get(i).getJornada().getFechaDescripcion() %> (<%=fixture.get(i).getJornada().getEstado().getDescripcion() %>  )</td>
				</tr>
				<tr>
					<td colspan="6" align="center">Partidos</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">Local</td> <td align="center">VS</td> <td colspan="2" align="center">Visitante</td> <td align="center">Accion</td> 
				</tr>
				
				<%
				for (int j=0; j<fixture.get(i).getPartidos().size(); j++){
					Partidos partido = fixture.get(i).getPartidos().get(j);
					%>
					
			     <tr>
					<td colspan="1" align="center"><%=partido.getEquipoLocal().getInstitucion().getNombre() %>-<%=partido.getEquipoLocal().getCategorias().getAñoCategoria() %><%=partido.getEquipoLocal().getNombreEquipo() %>  </td> 
					 <td colspan="1" align="center"><%=partido.getGolesLocal() %>
					 </td> <td align="center">VS</td> 
					 <td colspan="1" align="center"><%=partido.getGolesVisitante() %></td>
					 <td colspan="1" align="center"><%=partido.getEquipoVisitante().getInstitucion().getNombre() %>-<%=partido.getEquipoVisitante().getCategorias().getAñoCategoria() %><%=partido.getEquipoVisitante().getNombreEquipo() %> </td> 
					 <td align="center">
					      <button class="botonEditar"
							onclick="javascript: verpartido('/admin/mostrarFixture/')"
							value="<%=partido.getIdPartidos()%>" id="verpartido" name="verpartido">Detalle Partido</button>
					 
					 </td> 
				</tr>
					
			<%	} %>
							
			     
			
			
				</thead>
			</table>
			
		      
		
		<% }} %>
	
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