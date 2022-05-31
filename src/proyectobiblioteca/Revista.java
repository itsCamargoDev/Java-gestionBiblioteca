package proyectobiblioteca;

public class Revista extends Publicacion {

	private int numero;

	//Constructores
	Revista(String codigo, String titulo, int anioPubl, int numero){
		super(codigo, titulo, anioPubl);
		this.numero = numero;
	}

	Revista(Revista revista) {
		super(revista.codigo, revista.titulo, revista.anioPubl);
		numero = revista.numero;
	}

	public void setNumero(int numero){
		this.numero = numero;
	}

	public int getNumero(){
		return numero;
	}
	
	@Override
	public String toString(){
		return (super.toString() + " Número: " + numero);
	}
}
