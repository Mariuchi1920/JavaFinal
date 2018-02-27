package entidad;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class Util {

	
	public static final int K = 2; 
	
	public static Date convertirStringDate(String fechaIngresada) {
		Date fecha = null;

		Calendar calendario = Calendar.getInstance();

		String[] aux = fechaIngresada.split("-");

		calendario.setTimeZone(TimeZone
				.getTimeZone("America/Argentina/Buenos_Aires"));
		calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[2]));
		calendario.set(Calendar.MONTH, Integer.parseInt(aux[1]) - 1);
		calendario.set(Calendar.YEAR, Integer.parseInt(aux[0]));

		fecha = new Date(calendario.getTimeInMillis());

		return fecha;

	}

	public static Date recuperarHoraActualStringDate() {
		Date fecha = null;

		Calendar calendario = Calendar.getInstance();

		calendario.setTimeZone(TimeZone
				.getTimeZone("America/Argentina/Buenos_Aires"));

		fecha = new Date(calendario.getTimeInMillis());

		return fecha;

	}

	public static boolean compararFechaConHoy(Date date) {

		boolean respuesta = false;
		Calendar calendario = Calendar.getInstance();
		String[] aux = String.valueOf(date).split("-");

		calendario.setTimeZone(TimeZone
				.getTimeZone("America/Argentina/Buenos_Aires"));
		calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[2]));
		calendario.set(Calendar.MONTH, Integer.parseInt(aux[1]) - 1);
		calendario.set(Calendar.YEAR, Integer.parseInt(aux[0]));

		Calendar calendarioHoy = Calendar.getInstance();
		calendarioHoy.setTimeZone(TimeZone
				.getTimeZone("America/Argentina/Buenos_Aires"));

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
	
	
	
	public static int calcularFactorial(int calcular){
	 int numero = 1;	
	 for (int i = 1; i<=calcular;i++){
		 
		 numero = numero * i;
	 }
		
	 return numero;
		
		
	}
	


}
