package entidad;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	
	public static final int K = 2; 
	
	public static Date convertirStringDate(String fechaIngresada) {
		Date fecha = null;

		Calendar calendario = Calendar.getInstance();

		String[] aux = fechaIngresada.split("-");

		calendario.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[2]));
		calendario.set(Calendar.MONTH, Integer.parseInt(aux[1]) - 1);
		calendario.set(Calendar.YEAR, Integer.parseInt(aux[0]));

		fecha = new Date(calendario.getTimeInMillis());

		return fecha;

	}

	public static Date recuperarHoraActualStringDate() {
		Date fecha = null;
		Calendar calendario = Calendar.getInstance();
		calendario.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		fecha = new Date(calendario.getTimeInMillis());
		return fecha;

	}

	public static boolean compararFechaConHoy(Date date) {

		boolean respuesta = false;
		Calendar calendario = Calendar.getInstance();
		String[] aux = String.valueOf(date).split("-");

		calendario.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[2]));
		calendario.set(Calendar.MONTH, Integer.parseInt(aux[1]) - 1);
		calendario.set(Calendar.YEAR, Integer.parseInt(aux[0]));
		Calendar calendarioHoy = Calendar.getInstance();
		calendarioHoy.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

		if (calendarioHoy.getTimeInMillis() > calendario.getTimeInMillis())
			respuesta = true;

		return respuesta;

	}
	
	
	
	public static int calulcarCantidadJordan(int n) {
		 int jornadas=1;
		 
		 int factoriaN= Util.calcularFactorial(n);
		 int factorialNK= Util.calcularFactorial(n-K);
		 int factorialK = Util.calcularFactorial(K);
		 
		 int divisor =  factorialNK * factorialK;
		 
		 jornadas = factoriaN/divisor;
		 
		 return jornadas;
		
	
	}
	
	
	public static boolean isPar(int numero){
		
		if(numero%2==0){
           return true;
       }else{
    	   return false;
       }      
		
	}
	
	
	
	
	public static int calcularFactorial(int calcular){
	 int numero = 1;	
	 for (int i = 1; i<=calcular;i++){
		 
		 numero = numero * i;
	 }
		
	 return numero;
		
		
	}
	
	
	
	public static int calularCantidadDias(Date fechaInicio , Date fechaFin){
		
		
		return  (int)( (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	
	public static int cantidadDiasPorJornada (int cantidadDias , int cantidadJornadas){
		
		
		return (int ) cantidadDias/cantidadJornadas;
		
		
	}
	
	
	public static Date addDays(Date date, int days)
    {
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTimeInMillis());
    }
	
	
    public static int cantidadHorasPorPartidos ( int cantidadPatidos){
		
		
		return (int ) 12/cantidadPatidos;
		
		
	}
	
	public static Time addTime( int horas)
    {
		
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR,8);
        cal.add(Calendar.HOUR, horas); //minus number would decrement the days
        return new Time(cal.getTimeInMillis());
    }
	
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PATTERN_NOMBRE_APELLIDO = "([a-z]|[A-Z]|\\s)+";
	private static final String PATTERN_NUMEROS = "/[^0-9]/";
 
   
    public static boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    public static boolean validateNombreApellido(String nombre) {
    	 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_NOMBRE_APELLIDO);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
 
    }
    
    public static boolean validateTelefono(String telefono) {
   	 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_NUMEROS);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
 
    }
    
    
    public static boolean isNumeric(String cadena){
    	try {
    		Long.parseLong(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }

	public static boolean compararAño(String añoCategoria) {
		// TODO Auto-generated method stub
		boolean respuesta= true;
		
		int año = Integer.parseInt(añoCategoria);
		Calendar calendarioHoy = Calendar.getInstance();
		if(año >= calendarioHoy.get(Calendar.YEAR)){
			respuesta= false;
		}
		
		
		return respuesta;
	}
    
    
    



}
