package entidad;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class Util {
	
	
	
	public static Date convertirStringDate(String fechaIngresada){
		Date fecha = null;
		
		
		
		Calendar calendario = Calendar.getInstance();
		
		String[] aux= fechaIngresada.split("-");
		
		calendario.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(aux[2]));
		calendario.set(Calendar.MONTH, Integer.parseInt(aux[1]));
		calendario.set(Calendar.YEAR, Integer.parseInt(aux[0]));
		
		fecha= new Date(calendario.getTimeInMillis());
		
		return fecha;
		
		
		
	}
	
	public static Date recuperarHoraActualStringDate(){
		Date fecha = null;
		
		Calendar calendario = Calendar.getInstance();
		
		
		
		calendario.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		
		
		fecha= new Date(calendario.getTimeInMillis());
		
		return fecha;
		
		
		
	}

}
