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

<html  lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">

<head>
	<meta charset="UTF-8">
	<title>Editar Torneo</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	
	function editar(met) {
		if(confirm("Estas seguro de editar un nuevo Torneo?")){
			document.myform.registar.value="" ;
		    document.myform.agregarEquipos.value="";  
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
	Torneo encontrado= null;
	int idTorneo=0;
	String nombre="";
	Date fechaI=Util.recuperarHoraActualStringDate();
	Date fechaF=Util.recuperarHoraActualStringDate();
	TipoEstado estado = new TipoEstado();
	Equipo equipoCampeon=null;
	
	TorneosDAO torDao= new TorneosDAO();

	if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Torneo)request.getSession().getAttribute("editador");	  
	   idTorneo=encontrado.getIdTorneos();
	   nombre=encontrado.getNombre();
	   fechaI=encontrado.getFechaInicio();
	   fechaF=encontrado.getFechaFin();
	   estado=encontrado.getEstado();
	   equipoCampeon=encontrado.getEquipoGanador();
	   
	   
}
%>
	
	
		<div id="contenido">
		<form class="form-signin" id="myForm" name="myForm" action="" method="post">
			<table border="4" align="center">
			<tr>
				<td colspan="2">Editar Torneo Seleccionado</td>
			</tr>
				<!-- ESTO NO SE MUY BIEN COMO ES SI VA A UN SERVLETS O NO -->
			<tr>
				<td>Nombre Torneo:</td>
				<td><input type="text" name="nombreTorneo" id="nombreTorneo" value="<%= nombre%>" /></td>
			</tr>
			
			<% 

			JornadaDAO catJornada = new JornadaDAO ();
			LinkedList<Jornadas> jordanadas = catJornada.buscarporTorneos(idTorneo);
			
			
			if(jordanadas!=null && jordanadas.size()>0){ %>	
			
			
			<tr>
					<td>Fecha de Inicio: <%= fechaI %></td>
					
				</tr>	
			<tr>
					<td>Fecha de Fin: <%= fechaF %></td>
					
			</tr>
			
			<% }else{ %>
			<tr>
					<td>Fecha de Inicio:</td>
					<td><input type="date" name="fechaI" id="fechaI"
						value="<%= fechaI %>" /></td>
				</tr>	
			<tr>
					<td>Fecha de Fin:</td>
					<td><input type="date" name="fechaF" id="fechaF"
						value="<%= fechaF %>" /></td>
			</tr>	
			<tr>
			
			<% } %>
					<td>Estado:</td>
					<td>
						<% 
										TipoEstadoDAO catalogo = new TipoEstadoDAO();
				                         TipoEstado tpEstado= new TipoEstado();
			
                               			 LinkedList <TipoEstado> listaEstado= catalogo.getTipoEstados();
                               			 %> <!--NO OLVIDAR LO QUE VA AL SERLVET ES EL NAME  -->
						<select name="listaTipoEStado" id="tipoEstado">
							<% for(TipoEstado te :listaEstado){  
                               			if(encontrado!=null){
                               			     if(estado.getIdTipoEstado()== te.getIdTipoEstado()){%>
							<option selected="selected"
								value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>

							<%}else{%>
							<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                              <%  			     }}else {%>
							<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>

							<% }} %>

					</select>
					</td>

				</tr>
				
				
				
				
				<%  
				  if(fechaF!=null && Util.compararFechaConHoy(fechaF)){
   				    EquiposTorneoDAO catEqTor= new EquiposTorneoDAO();
				    LinkedList<EquiposTorneos> listarEquiposTorneo = catEqTor.buscarporTorneo(encontrado);
				    if(listarEquiposTorneo!=null && listarEquiposTorneo.size()>0){
				     
				%>
				<tr> 
					<td>Equipo campión:</td>
					<td>
					
					 <select name="listarEquipos" id="listarEquipos">
							<%
							for(EquiposTorneos te :listarEquiposTorneo){  
                               	if(encontrado.getEquipoGanador()!=null){
                                  if(encontrado.getEquipoGanador().equals(te.getEquipos())){%>
							       <option selected="selected" value="<%=te.getEquipos().getInstitucion().getIdInstituciones() %> / <%=te.getEquipos().getCategorias().getIdCategorias()%> / <%=te.getEquipos().getNombreEquipo() %>"> <%=te.getEquipos().getInstitucion().getNombre() %> - <%=te.getEquipos().getCategorias().getAñoCategoria() %> - <%=te.getEquipos().getNombreEquipo()%> </option>

							       <%}else {%>
							<option  value="<%=te.getEquipos().getInstitucion().getIdInstituciones() %> / <%=te.getEquipos().getCategorias().getIdCategorias() %> / <%=te.getEquipos().getNombreEquipo() %>"> <%=te.getEquipos().getInstitucion().getNombre() %> - <%=te.getEquipos().getCategorias().getAñoCategoria() %> - <%=te.getEquipos().getNombreEquipo() %> </option>

							<% }}else { %>
							<option  value="<%=te.getEquipos().getInstitucion().getIdInstituciones() %> / <%=te.getEquipos().getCategorias().getIdCategorias()%> / <%=te.getEquipos().getNombreEquipo() %>"> <%=te.getEquipos().getInstitucion().getNombre() %> - <%=te.getEquipos().getCategorias().getAñoCategoria() %> - <%=te.getEquipos().getNombreEquipo() %> </option>
						<%	} %>

					</select>
					
					</td>
					</tr>
					
					<% }}} %>
					
				
			<%
			   if(fechaI!=null && Util.compararFechaConHoy(fechaI)){
			    
			   
			%>
				
					<tr>
					<td colspan="2">
					
				<button align="center"
				onclick="javascript: agregarEquipos('/admin/modificarTorneo/')"
				id="agregarEquipos" value="agregarEquipos" name="agregarEquipos">Agregar Equipos</button>
				</td>
					</tr>
				
				<% } %>



			</table>
			<%if(encontrado!=null){ %>
			<button align="center"
				onclick="javascript: editar('/admin/modificarTorneo/editar')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{ %>

			<button align="center"
				onclick="javascript: registrar('/admin/modificarTorneo/agregar')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
		</form>

	</div>

	<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
	
</body>
</html>