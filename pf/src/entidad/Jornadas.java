package entidad;

import java.sql.Date;
import java.util.LinkedList;

import sun.awt.image.ImageWatched.Link;



public class Jornadas {
	
	private int idJornadas;
	private Torneo torneos;
	private Date fechaDescripcion;
	private TipoEstado estado;
	
	private LinkedList<Partidos> partidos = new LinkedList<Partidos>();
	
	
	public int getIdJornadas() {
		return idJornadas;
	}
	public void setIdJornadas(int idJornadas) {
		this.idJornadas = idJornadas;
	}
	public Torneo getTorneos() {
		return torneos;
	}
	public void setTorneos(Torneo torneos) {
		this.torneos = torneos;
	}
	public Date getFechaDescripcion() {
		return fechaDescripcion;
	}
	public void setFechaDescripcion(Date fechaDescripcion) {
		this.fechaDescripcion = fechaDescripcion;
	}
	public TipoEstado getEstado() {
		return estado;
	}
	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}
	
	
	
	
	public LinkedList<Partidos> getPartidos() {
		return partidos;
	}
	public void setPartidos(LinkedList<Partidos> partidos) {
		this.partidos = partidos;
	}
	
	public void agregarPartidos(Partidos e) {
		this.partidos.add(e);
	}
	
	
	public static LinkedList<Jornadas> generarJornadas(
			LinkedList<EquiposTorneos> equipoTorne) {

		LinkedList<Jornadas> jornadas = new LinkedList<Jornadas>();
		LinkedList<Partidos> partidos = new LinkedList<Partidos>();

		Partidos nuevo = new Partidos();
		for (int j = 0; j < equipoTorne.size(); j++) {

			for (int k = j + 1; k < equipoTorne.size(); k++) {

				nuevo = new Partidos();
				nuevo.setEquipoLocal(equipoTorne.get(k).getEquipos());
				nuevo.setEquipoVisitante(equipoTorne.get(j).getEquipos());
				partidos.add(nuevo);

			}

		}
		
		//inset jornada
	    
	    
	    for(int i=0; i< equipoTorne.size()-1; i++){
	    	Jornadas jorna = new Jornadas();
	    	jorna.agregarPartidos(partidos.get(i));
	    	
	    	for(int j=0; j<partidos.size();j++){
	    		
	    		if(partidoEnJorda(partidos.get(j), jorna)){
	    			
	    			
	    			jorna.agregarPartidos(partidos.get(j));
	    		}
	    		
	    	}
	    	 jornadas.add(jorna);
	    }
	    
	   
	    
		
		

		return jornadas;

	}
	
	
	
	public static boolean partidoEnJorda(Partidos partidos, Jornadas jorna ){
		
		boolean respuesta = true;
		
		for(int k=0; k< jorna.getPartidos().size();k++){
    		
    		if(partidos.getEquipoVisitante().equals(jorna.getPartidos().get(k).getEquipoLocal())){
    			respuesta= false;
    			break;
    		}else if(partidos.getEquipoVisitante().equals(jorna.getPartidos().get(k).getEquipoVisitante())){
    			respuesta= false;
    			break;
    		}else if(partidos.getEquipoLocal().equals(jorna.getPartidos().get(k).getEquipoVisitante())){
    			respuesta= false;
    			break;
    		}if(partidos.getEquipoLocal().equals(jorna.getPartidos().get(k).getEquipoLocal())){
		    				
		        respuesta= false;
		        break;
		    }
	    			
	  }
		
		return respuesta;
		
	}
	
}
