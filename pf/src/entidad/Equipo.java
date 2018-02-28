package entidad;

import java.util.LinkedList;



public class Equipo {
	

	private Categoria categorias;
	private Institucion institucion;
	private String nombreEquipo;
	private Persona entrenador;
	
	
	public Categoria getCategorias() {
		return categorias;
	}
	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	public Persona getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(Persona entrenador) {
		this.entrenador = entrenador;
	}
	
	
	
	public static boolean ifIgualDosEquipos (Equipo equipo1,Equipo equipo2 ){
		boolean  repuesta = false;
		if(equipo1.getCategorias().getIdCategorias() ==  equipo2.getCategorias().getIdCategorias()){
			if(equipo1.getInstitucion().getIdInstituciones() ==  equipo2.getInstitucion().getIdInstituciones()){
				if(equipo1.getNombreEquipo().equals(equipo2.getNombreEquipo())){
					repuesta = true;
					
				}
			}
			
		}
		
		
		return repuesta;
		
	}
	
	
	
	public static LinkedList<Equipo> removeEquipoLista (LinkedList<Equipo> listaequipos,Equipo equipo2 ){
		LinkedList<Equipo> lista = listaequipos;
		
		for (int i = 0; i < lista.size(); i++) {
			
			if(Equipo.ifIgualDosEquipos(lista.get(i), equipo2)){
				lista.remove(i);
			}
			
		}
		
		
		return lista;
		
	}
	
	
	
	
	
	}
