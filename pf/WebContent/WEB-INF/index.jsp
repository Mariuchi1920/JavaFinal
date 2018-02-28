<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >
	<title>Inicio de Sesion</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilos.css">
	
</head>
 <script type="text/javascript" language="JavaScript">
 function carga(){
	 document.getElementById("usu").focus();
	 
	 
 }

 function validarDatos() {
	 var u= document.getElementById("usu");
	var c= document.getElementById("con");
	if((u.value==""||u.value==''||u.value== null)&&(c.value==""||c.value==''||c.value== null)){
		alert("Ingresa tu usuario y contrasena");
		u.focus();
		return false;
		
	}else if(u.value==""||u.value==''||u.value== null){
		alert("Ingresa tu usuario");
		u.focus();
		return false;
	}else if(c.value==""||c.value==''||c.value== null){
		alert("ingresa tu contrasena");
		c.focus();
		return false
	}else{
		return true;
	}
}
 
 </script>

<body onload="carga()">

<%if(request.getAttribute("error")!=null){ %>
<div>
   
holaAAAA
</div>

<%} %>
<div class="my-content">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h1><strong>LIGA EFA</strong> Bienvenidos </h1>
				<div class="mydescription">
				<p>Registro de usuarios</p>
				</div>
			</div>
		</div>
		<div class="row">
		<div class="col-sm-6 offset-sm-3 myform-cont" >
			<div class= "myform-top">
					<div class= "myform-top-left">
						<h3> Ingresa a nuestro Sitio</h3>
						<p>Escribe tu usuario y contraseña.</p>
					</div>
					<div class= "myform-top-right">
					<i class="fas fa-key"></i>
					</div>
			</div>
			
			<div class="myform-bottom">
				<form role="form" action="login" method= "POST"    onsubmit="return validarDatos(); "class="">
					<div class="form-group">
					<input type="text" name="usu" id="usu" placeholder="Usuario..." class="form-control">
					</div>
					<div class="form-group">
					<input type="password"name="con" id="con" placeholder="Contraseña.." class="form-control">
					</div>
					<button type="submit" class="mybtn" >Ingresar</button> 
				</form>
			</div>
	   </div>
	   </div>
	   
	   
		<div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<h3>...visítanos tambien  por:</h3>
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3>EEEERRORR2222</h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				<div class="mysocial-login-buttons">
				<a class="mybtn-social" href="https://www.facebook.com/LIGA-EFA-159057650852892/">
				<i class="fab fa-facebook-square"></i>Facebook
				</a>
				</div>
			</div>
	</div>
		</div>
	</div>
	

			

	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

