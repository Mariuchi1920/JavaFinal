<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Inicio de Sesion</title>
	<link rel="stylesheet" type="text/css" href="CSS/style.css">




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
</head>
<body onload="carga()">
<div id="contenedor">
		<div id="cabecera">
					<div id="Logo"><img src="imagen/logo_header.png"></div>
					<!-- <div id="usuario">muestra el usuario en java</div> -->
		</div>

<div id="contenido">


<table border="2px" align="center" >
	<form action="login" method= "POST" onsubmit="return validarDatos();">
	<caption>Acceso a Liga Efa</caption>
		<tr align="center"> 
		<td><label for="usuario"> Usuario:</label>		
			<input type="text" name="usu" id="usu"/></td>		
		</tr>
		<tralign="center"> 
		<td><label for="contrasena">Contrasena:</label>
			<input type="password" name="con" id="con"/></td>
			
		<tr align="center">
		<td><input type="submit" value="Ingresar"> </td>			
		</tr>
		</form>
</table>

			

		</div>
		<div id="Pie">
			<div id="LogoPie"><img src="imagen/logo_footer.jpg"></div>
			<p>La EFA es una Agrupación de Escuelas de Fútbol creada por las instituciones fundadoras y cuyo objetivo final es:

"QUE TODOS LOS NIÑOS Y JOVENES SE DIVIERTAN JUGANDO AL FÚTBOL SANAMENTE Y CON ALEGRÍA,
SIN PRESIONES DE NINGUNA NATURALEZA, SIENDO ELLOS LOS PROTAGONISTAS PRINCIPALES".

Los adultos que integran la EFA. (Delegados, Técnicos, padres y público en general),
solamente serán colaboradores para lograr el fin mencionado en el párrafo anterior.
</p>
			<div id="LogoFondoPag"><img src="imagen/firma_efa.jpg"></div>
			<h2>esto es el pie de pagina</h2>

		</div>
	</div>
</body>
</html>