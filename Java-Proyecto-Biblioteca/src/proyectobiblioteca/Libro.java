package proyectobiblioteca;

public class Libro extends Publicacion implements Prestable {

	// ATRIBUTOS AÑADIDOS A LOS DE Publicacion
	private boolean prestado;
	
	// CONSTRUCTORES
	// Los libros no están prestados cuando se crean
	Libro(){
		super();
		prestado = false;
	}

	Libro (String codigo, String titulo, int anioPubl){
		super(codigo, titulo, anioPubl);
		prestado = false;
	}

	Libro (Libro libro){
		super(libro.codigo, libro.titulo, libro.anioPubl);
		prestado = libro.prestado;
	}

	//Metodos Varios
	@Override
	public String toString(){
		return super.toString() + " Prestado: " + prestado;
	}

	//Interface Prestable
	@Override
	public boolean prestar(){
		if(prestado){
			return (false);
		}
		else {
			prestado = true;
		return (true);
		}
	}

	@Override
	public boolean devolver(){
		if(prestado){
			prestado = false;
			return (true);
		}
		else {
			return (false);
		}
	}

	@Override
	public boolean prestado(){
		return (prestado) ;
	}
}
