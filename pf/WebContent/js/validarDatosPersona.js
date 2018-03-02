/**
 * 
 */
$(document).ready(function () {
    var emailreg = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/;
    $(".boton").click(function (){
        $(".error").remove();
        if( $(".nombre").val() == "" ){
            $(".nombre").focus().after("<span class='error'>Ingrese su nombre</span>");
            return false;
        }else if( $(".apellido").val() == "" ){
	            $(".apellido").focus().after("<span class='error'>Ingrese su nombre</span>");
	            return false;
	        }else if ( $(".dni").val() == "" ){
		            $(".dni").focus().after("<span class='error'>Ingrese su nombre</span>");
		            return false;
			}else if( $(".telefono").val() == "" ){
				            $(".telefono").focus().after("<span class='error'>Ingrese su nombre</span>");
				            return false;
				
			}else if( $(".fechaN").val() == "" ){
				            $(".fechaN").focus().after("<span class='error'>Ingrese su nombre</span>");
				            return false;
			} else if( $(".mail").val() == "" || !emailreg.test($(".email").val()) ){
            $(".mail").focus().after("<span class='error'>Ingrese un email correcto</span>");
            return false;
        }else if( $(".usuario").val() == "" ){
	            $(".usuario").focus().after("<span class='error'>Ingrese su nombre</span>");
	            return false;
	}else if( $(".contraseña").val() == "" ){
		            $(".contraseña").focus().after("<span class='error'>Ingrese su nombre</span>");
		            return false;
	}

    });
    $(".nombre, .apellido, .dni, .telefono, .fechaN, .usuario").keyup(function(){
        if( $(this).val() != "" ){
            $(".error").fadeOut();
            return false;
        }
    });
    $(".mail").keyup(function(){
        if( $(this).val() != "" && emailreg.test($(this).val())){
            $(".error").fadeOut();
            return false;
        }
    });
});



