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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 
	
	
<meta charset="UTF-8">
	<title>Editar Torneo</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">
	<script type="text/javascript">
	function validarDatos(){
		var nombreTorneo=document.getElementById('nombreTorneo');
		var fechaI=document.getElementById('fechaI');
		var fechaF=document.getElementById('fechaF');
		
		  var expresionRegular1=/^([0-9]+){9}$/;//<--- con esto vamos a validar el numero
		  var expresionRegular2=/\s/;//<--- con esto vamos a validar que no tenga espacios en blanco
		  var expRegNom=/^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü\s]+$/;
			var expRegMail=/^[\w-\.]+@([\w-]\.)+[\w-]{2,4}$/;
		  
		  if(!nombreTorneo.value) {
			  alert('El campo nombre de institucion no debe estar vacio.');
			  nombreI.focus();
		    return false;
		  }else if (!fechaI.value) {
			  alert('El campo nombre Localia no debe estar vacio.');
			  nomLocal.focus();
			    return false;
		  }else if (!fechaF.value) {
					  alert('El campo direccion localia no debe estar vacio.');
		  				dirLocal.focus();
					    return false; 
		}else{
		    alert("Se ha registrado Un nuevo torneo!");
			return true;
		}
	}
	
	
	function editar(met) {
		if(confirm("Estas seguro de editar el Torneo?")){
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



	
<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		
		
		<div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3 style="color: red;"><%=request.getSession().getAttribute("error").toString()%></h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				
			</div>
	</div>
		
			<div class="container tablaPersona">
			
			<h1>Edicion de Torneos</h1>
			<form id="needs-validation" name="myForm" action="" method="post"
				onsubmit="return validarDatos()">
				<table class="table table-bordered" align="center">
					<thead>
			<tr>
				<td>Nombre Torneo:</td>
				<td><input type="text" name="nombreTorneo" id="nombreTorneo" value="<%= nombre%> " class="form-control" placeholder="nombre del torneo..."  /></td>
			</tr>
			
			<% 

			
	       
			if(encontrado!=null && encontrado.getEstado().getIdTipoEstado()== TipoEstado.INICIADO){ %>	
			
			
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
						value="<%= fechaI %>" class="form-control" placeholder="Fecha de inicio del TOrneo..." /></td>
				</tr>	
			<tr>
					<td>Fecha de Fin:</td>
					<td><input type="date" name="fechaF" id="fechaF"
						value="<%= fechaF %>" class="form-control" placeholder="fecha FIn del Torneo." required="" /></td>
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
						<select name="listaTipoEStado" id="tipoEstado" class="form-control" placeholder="Selecciona Opcion" required="">
							<% for(TipoEstado te :listaEstado){  
                               			if(encontrado!=null){
                               			     if(estado.getIdTipoEstado()== te.getIdTipoEstado()){%>
							                    <option selected="selected" value="<%= estado.getIdTipoEstado() %>"><%=estado.getDescripcion() %></option>

							<%}else if(te.getIdTipoEstado()!=TipoEstado.JUGADO && te.getIdTipoEstado()!=TipoEstado.PENDIENTE && te.getIdTipoEstado()!=TipoEstado.SUSPENDIDO){%>
							<option value="<%= te.getIdTipoEstado() %>"><%=te.getDescripcion() %></option>
                              <%  			     }}else if(te.getIdTipoEstado()!=TipoEstado.JUGADO && te.getIdTipoEstado()!=TipoEstado.PENDIENTE && te.getIdTipoEstado()!=TipoEstado.SUSPENDIDO){%>
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
					
					 <select name="listarEquipos" id="listarEquipos" class="form-control" placeholder=campeon..." required="">
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

					
					
					<% }}} %>
					</select>
					
					</td>
					</tr>
					
				
			<%
			   if(encontrado!=null && fechaI!=null && !Util.compararFechaConHoy(fechaI)){
			    
			   
			%>
				
					<tr>
					<td colspan="2">
					
				<button class="botonGenerarFixture" 
				align="center"
				onclick="javascript: agregarEquipos('/admin/modificarTorneo/')"
				id="agregarEquipos" value="agregarEquipos" name="agregarEquipos">Agregar Equipos</button>
				</td>
					</tr>
				
				<% } %>



			</table>
			<%if(encontrado!=null){ %>
			<button  class="botonEditar" 
			    align="center"
				onclick="javascript: editar('/admin/modificarTorneo/')"
				id="editar" value="editar" name="editar">Editar</button>

			<% }else{ %>

			<button  class="botonEditar"
			     align="center"
				onclick="javascript: registrar('/admin/modificarTorneo/')"
				id="registar" value="registar" name="registar">Agregar</button>
			<% } %>
		</form>

	</div>
		
		<div class= "container Pie">
			<div id="Pie">
	 <div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	 
	</div>
		</div>
	
		</div>
		</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	
	<script type="text/javascript">
	(function(){
		'use strict';
		
		window.addEventListenner('load',function(){
			var form= document.getElementById('needs-validation');
			form.addEventListenner('submit',function(event){
				if(from.checkValidty()==false){
					event.preventDefault();
					event.stopPropagation();
					
				}
			form.classList.add('was-validated');
			},false);
		},false);
	})();
		
		
	</script>
</body>
</html>
