package entidad;



public class Categoria {




	int idCategorias;
	String aņoCategoria;
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

	public String getAņoCategoria() {
		return aņoCategoria;
	}

	public void setAņoCategoria(String aņoCategoria) {
		this.aņoCategoria = aņoCategoria;
	}

	public static boolean validarCategoria(Categoria cat) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta = true;
		String aņoCategoria = cat.aņoCategoria;
		String descripcion = cat.descripcion;
		if(cat.equals("")){
			
			respuesta= false;
			throw new ApplicationException("La descripcion esta vacia");
		}
		
		if(Util.isNumeric(aņoCategoria)){
			if(!Util.compararAņo(aņoCategoria)){
				respuesta= false;
				throw new ApplicationException("El aņo es mayor al aņo de hoy");
			}
		}else{
			respuesta= false;
			throw new ApplicationException("El aņo de categoria esta vacia");
		}
		
		
		
		return respuesta;
	}
	

	}
