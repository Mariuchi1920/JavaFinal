package entidad;



public class Categoria {




	int idCategorias;
	String a�oCategoria;
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

	public String getA�oCategoria() {
		return a�oCategoria;
	}

	public void setA�oCategoria(String a�oCategoria) {
		this.a�oCategoria = a�oCategoria;
	}

	public static boolean validarCategoria(Categoria cat) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta = true;
		String a�oCategoria = cat.a�oCategoria;
		String descripcion = cat.descripcion;
		if(cat.equals("")){
			
			respuesta= false;
			throw new ApplicationException("La descripcion esta vacia");
		}
		
		if(Util.isNumeric(a�oCategoria)){
			if(!Util.compararA�o(a�oCategoria)){
				respuesta= false;
				throw new ApplicationException("El a�o es mayor al a�o de hoy");
			}
		}else{
			respuesta= false;
			throw new ApplicationException("El a�o de categoria esta vacia");
		}
		
		
		
		return respuesta;
	}
	

	}
