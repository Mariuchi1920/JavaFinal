package entidad;



public class Categoria {




	int idCategorias;
	String añoCategoria;
	String descripcion;
	TipoEstado estado;
	


	
	public int getIdCategorias() {
		return idCategorias;
	}

	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}

	public String getAñoCategoria() {
		return añoCategoria;
	}

	public void setAñoCategoria(String añoCategoria) {
		this.añoCategoria = añoCategoria;
	}

	public static boolean validarCategoria(Categoria cat) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta = true;
		String añoCategoria = cat.añoCategoria;
		String descripcion = cat.descripcion;
		if(cat.equals("")){
			
			respuesta= false;
			throw new ApplicationException("La descripcion esta vacia");
		}
		
		if(Util.isNumeric(añoCategoria)){
			if(!Util.compararAño(añoCategoria)){
				respuesta= false;
				throw new ApplicationException("El año es mayor al año de hoy");
			}
		}else{
			respuesta= false;
			throw new ApplicationException("El año de categoria esta vacia");
		}
		
		
		
		return respuesta;
	}
	

	}
