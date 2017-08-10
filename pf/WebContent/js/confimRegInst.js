/**
 * 
 */
function consulta() {
//Ingresamos un mensaje a mostrar
var mensaje = confirm("Esta seguro que desea registrar una Nueva Institucion?");
//Detectamos si el usuario acepto el mensaje
if (mensaje) {
alert("Se ha registrado una nueva Istitucion");
}
//Detectamos si el usuario denegó el mensaje
else {
	
alert("¡Haz denegado el mensaje!");
}
}

function carga(){
	 document.getElementById("nombre").focus();
	 
	 
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