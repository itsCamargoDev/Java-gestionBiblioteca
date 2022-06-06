package proyectobiblioteca;

import java.util.ArrayList;
import java.io.IOException;

public class Principal {
	public static void main (String []args) throws IOException, Exception {

		ArrayList Publicaciones = new ArrayList<>();
		int eleccion;

		Binario.leerBin(Publicaciones);
		do {	// PRINCIPIO DEL POSIBLE BUCLE SI NO SE ESCOGE LA OPCIÓN DE SALIDA DEL PROGRAMA
			System.out.println("\n\n			--- Bienvenido al Programa Biblioteca ---\n");
			System.out.println("	(Inserte el número del índice para realizar la correspondiente acción)\n");

			System.out.println("	--- 0. SALIR del Programa Biblioteca");
			System.out.println("	--- 1. Dar de ALTA una Publicación");
			System.out.println("	--- 2. Dar de BAJA una Publicación (vía código de Publicación)");
			System.out.println("	--- 3. MODIFICAR Publicación (vía código de Publicación)");
			System.out.println("	--- 4. CONSULTAR Publicación (mostrar todos o buscar por fragmento de título)");

			System.out.println("\n					--- EXTRAS ---\n");

			System.out.println("	--- 5. PRÉSTAMO de un Libro");
			System.out.println("	--- 6. DEVOLVER un Libro");
			System.out.println("	--- 7. Mostrar todas las Publicaciones que sean Revistas");
			System.out.println("	--- 8. Mostrar todas las Publicaciones que sean Libros");
			System.out.println("	--- 9. Mostrar todos los Libros que están PRESTADOS");
			System.out.println("	-- 10. Mostrar todos los Libros que NO están PRESTADOS");
			System.out.println("	-- 11. RESETEO del Programa Biblioteca (BORRADO MASIVO, únicamente LIBROS o únicamente REVISTAS)");
			System.out.println("	-- 12. ¡¡¡ PARA PROBAR EL PROGRAMA !!! INSERTAR PUBLIACIONES DE PRUEBA");

			System.out.print("\nIntroduzca una opción del menú: ");
			eleccion = FScanner.scannerInt(); 
			System.out.println(); // simplemente un salto de línea

			switch (eleccion) { //Coge el caso con el índice que hemos introducido por consola

				case 0: System.out.println("Fin del programa"); break;
				case 1: Biblioteca.addPublicacion(Publicaciones); break ;
				case 2: Biblioteca.borrarPublicacion(Publicaciones); break;
				case 3: Biblioteca.modificarPublicacion(Publicaciones); break;
				case 4: Biblioteca.mostrarPublicaciones(Publicaciones); break;
				
				//CASOS EXTRA V1.0

				case 5:  Biblioteca.prestamo(Publicaciones); break;
				case 6:  Biblioteca.devolucion(Publicaciones); break;
				case 7:  Biblioteca.imprimirRevistas(Publicaciones); break;
				case 8:  Biblioteca.imprimirLibros(Publicaciones); break;
				case 9:  Biblioteca.mostrarPrestados(Publicaciones); break;
				case 10: Biblioteca.mostrarNoPrestados(Publicaciones); break;
				case 11: Biblioteca.borrado(Publicaciones); break;
				case 12: Biblioteca.insertarListaPrueba(Publicaciones); break;

				//CASOS EXTRA V1.1

				// case 13: Biblioteca.ordenarAlfa(Publicaciones); break;
				// case 14: Biblioteca.ordenarInvAlfa(Publicaciones); break;

				default: System.out.println("Elección errónea, vuelva a intentarlo.");
			}
			Biblioteca.presionarEnter();
		} while (eleccion != 0);
		Binario.archivarBin (Publicaciones);
	}
}
