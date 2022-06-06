package proyectobiblioteca;

import java.io.Serializable;

public class Publicacion implements Serializable {
	/* Almacena las características comunes para revistas y libros */
	String codigo; // Código por el que distinguimos las publicaciones
	String titulo; // Título de la publicación
	int anioPubl; // Año de publicación

	Publicacion() { //constructor vacío
	}

	Publicacion(String codigoInicial, String tituloInicial, int anioPubl) { //Constructor dando parámetros
		codigo = codigoInicial;
		titulo = tituloInicial;
		this.anioPubl = anioPubl;
	}

	public int getAaPubl() { // devuelve el año de publicación de la Publicación
		return anioPubl;
	}
	public String getCodigo() { // devuelve el código de la Publicación
		return codigo;
	}
	public String getTitulo() { // devuelve el título de la Publicación
		return titulo;
	}

	public void setAnio(int newAnio){ // Cambia el valor de anioPubl por el parámetro dado
		anioPubl = newAnio;
	}

	public void setCodigo(String newCodigo){ // Cambia el valor de codigo por el parámetro dado
		codigo = newCodigo;
	}

	public void setTitulo(String newTitulo){ // Cambia el valor de título por el parámetro dado
		titulo = newTitulo;
	}

	@Override
	public String toString(){ // Devuelve los distintos atributos del objeto
		return ("Código " + codigo + " - Título: " + titulo + " - Año Publicado: " + anioPubl + " -");
	}
}
