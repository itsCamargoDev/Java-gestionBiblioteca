package proyectobiblioteca;

import java.util.ArrayList;
import java.util.Iterator;

// ESTA CLASE SE ENCARGA DE LAS FUNCIONES DE GESTIÓN DEL ARRAYLIST
public class Biblioteca {

					////////////////////////////////////////////////////////////////////////////////////////
					//									OPCIONES DEL PROGRAMA							  //
					///////////////////////////////////////////////////////////////////////////////////////

	// OPCIÓN 1 - AÑADIR PUBLICACIÓN
	public static void addPublicacion (ArrayList Publicaciones) {
		String titulo, codigoFin= new String();
		int anioPubl, libroORevista, numRevista, numCodigo;

		do { //Insertar 1 o 0 si es Revista o Publicacion respectivamente
			System.out.print("La publicación a introducir es un Libro (0) o una Revista (1)?: ");
			libroORevista = FScanner.scannerInt();
		} while(libroORevista != 0 && libroORevista != 1);

		do { //Insertar título de la nueva Publicacion
			System.out.print("Inserte el título de la publicación: ");
			titulo = FScanner.scannerString();
			if (titulo == "")
				System.out.println("\nEntrada no válida.");
		} while (titulo == "");

		do { //Insertar código de la nueva publicación
			System.out.print("Inserte el código de la Publicación (4 números máximo): ");
			numCodigo = FScanner.scannerInt();
			
			if (numCodigo == -12938457){
				codigoFin = "(null)";
			} else if (numCodigo < 0){
				codigoFin = "(null)";
				System.out.println("\nEntrada no válida. Número negativo.");
			}else if (numCodigo > 9999){
				codigoFin = "(null)";
				System.out.println("\nEntrada no válida. Número demasiado grande.");
			} else {
				codigoFin = concatenarCodigo(numCodigo, libroORevista);
				if (existeCodigo(codigoFin, Publicaciones)){ //Si encuentra una publicación con el mismo código te obliga ponerle otro excepto si es el mismo
					codigoFin = "(null)";
					System.out.println("\nYa existe una publicación con este código. Ponga un código distinto.");
				}
			}
		} while (codigoFin == "(null)");

		do {
			System.out.print("Inserte el año de publicación: ");
			anioPubl = FScanner.scannerInt();
			if (anioPubl < 0 && anioPubl != -12938457)
				System.out.println("Solo se aceptan años positivos, a partir del 0");
		} while (anioPubl < 0); // Se admiten años a partir del 0

		if (libroORevista == 0){
			Publicaciones.add(new Libro(codigoFin, titulo, anioPubl));
			System.out.println("\n--- Libro añadido a la Biblioteca ---");
		} else if (libroORevista == 1){
			System.out.print("Inserte número de la revista: ");
			numRevista = FScanner.scannerInt();
			Publicaciones.add(new Revista(codigoFin, titulo, anioPubl, numRevista));
			System.out.println("\n--- Revista añadida a la biblioteca ---");
		}
	}

	// OPCIÓN 2 - BORRAR PUBLICACIÓN
	public static void borrarPublicacion (ArrayList Publicaciones) {
		String codigo = new String();
		int select;

		System.out.print("Inserte el código de la publicación a borrar (L****/R****): ");
		codigo = FScanner.scannerString();
		if (existeCodigo(codigo, Publicaciones)) {
			System.out.print("Está seguro de querer borrar la Publicación con título "	//mensaje que pregunta si se esta seguro
			+ imprimirTituloPorCodigo(Publicaciones,codigo) + " (No (0) / Si (1))?: ");	//de borrar la publicacion con el titulo mostrado

			select = FScanner.scannerInt();
			if (select == 1){ // si dice que si
				borrarPubli(codigo, Publicaciones);
				System.out.println("\n--- Publicación borrada ---");
			} else // cualquier otra opción
				System.out.println("\nNo se ha efectuado el borrado");
		} else
			System.out.println("\nCódigo no encontrado o mal introducido");
	}

	// OPCIÓN 3 - MOFICIAR PUBLICACIÓN
	public static void modificarPublicacion (ArrayList Publicaciones) {
		String titulo, codigoOriginal, codigoFin = new String();
		int anioPubl, numCodigo;
		int numRevista = 0; //valor por defecto
		codigoFin = "(null)"; //esta asignación a este String me ayuda a que no se ejecute uno de los ifs
		System.out.print("Inserte el código de la publicación a modificar (R****/L****): ");
		codigoOriginal = FScanner.scannerString();
		if (existeCodigo(codigoOriginal, Publicaciones)){

			do { //bucle do while si se introduce un código que ya existe
				System.out.print("Inserte un nuevo código NUMÉRICO o introduzca el mismo si lo desea mantener (4 dígitos max): ");
				numCodigo = FScanner.scannerInt();

				if (numCodigo == -12938457){
					//No hace nada
				} else if (numCodigo < 0){
					codigoFin = "(null)";
					System.out.println("\nEntrada no válida. Número negativo.");
				} else if (numCodigo > 9999){
					codigoFin = "(null)";
					System.out.println("\nEntrada no válida. Número demasiado grande.");
				} else if (codigoOriginal.charAt(0)=='L' || codigoOriginal.charAt(0)=='R'){ //si la primera letra del codigo es una L o una R
					if (codigoOriginal.charAt(0)=='L') 											//si codigo corresponde con libro
						codigoFin = concatenarCodigo(numCodigo, 0);
					else 																			// si codigo corresponde con revista
						codigoFin = concatenarCodigo(numCodigo, 1);
					if (existeCodigo(codigoFin, Publicaciones) && !codigoFin.equals(codigoOriginal)){ //Si encuentra una publicación con el mismo código te obliga ponerle otro, excepto si es el de esa misma publicacion
						codigoFin = "(null)";
						System.out.println("\nYa existe una publicación con este código. Ponga un código distinto.");
					}
				}
			} while (codigoFin == "(null)"); // permito que el programa pueda pasar si se mete el mismo codigo de publicacion que tiene la publicacion

			do {
				System.out.print("Inserte un nuevo título: ");
				titulo = FScanner.scannerString();
			} while(titulo == "");

			do {
				System.out.print("Inserte un nuevo año de publicación: ");
				anioPubl = FScanner.scannerInt();
			
				if (anioPubl < 0 && anioPubl != -12938457)
					System.out.println("\nNo se permiten años negativos");
			} while (anioPubl < 0); //Se admiten únicamente valores a partir del 0

			if (codigoFin.charAt(0) == 'R') //si la publicación es una revista
				do {
					System.out.print("Inserte un nuevo número de revista: ");
					numRevista = FScanner.scannerInt();
					if (numRevista < 0 && numRevista != -12938457)
						System.out.println("\nNo se permiten números de revista negativos");
				} while(numRevista < 0);

			buscaYModifica(codigoOriginal, codigoFin, titulo, anioPubl, Publicaciones, numRevista);
			System.out.println("\n--- Modificación satisfactoria ---");
		} else
			System.out.println("\nCódigo no encontrado o mal introducido.");
	}

	// OPCIÓN 4 - MOSTRAR PUBLICACIONES
	public static void mostrarPublicaciones (ArrayList Publicaciones) {
		int select;
		int coincidencias;
		String fragmento = new String();
		System.out.print("Mostrar todas las publicaciones (0) o buscar por texto (1)?: ");
		select = FScanner.scannerInt();
		System.out.println();
		if (select == 0) {
			System.out.println(); // simplemente un salto de línea
			imprimirPublicaciones(Publicaciones);
			System.out.println("\n	--- Existen un total de " + Publicaciones.size() + " Publicaciones,\n	--- de las cuales "
			+ cuentaRevistas(Publicaciones) + " son Revistas y " + cuentaLibros(Publicaciones) + " son Libros");
		}
		else if (select == 1) {
			System.out.print("Inserte fragmento de texto a buscar: ");
			fragmento = FScanner.scannerString();
			System.out.println(); // simplemente un salto de línea
			coincidencias = imprimirPorTitulo(Publicaciones, fragmento); // toma el valor que devuelve el metodo
			if (coincidencias == 0){
				System.out.println("No se ha encontrado ningun título con ese fragmento");
			} else {
				System.out.println("\n	--- Se han encontrado un total de " + coincidencias + " coincidencias");
			}
		} else if (select != -12938457)
			System.out.println("\nSelección errónea, solo se admite \"1\" o \"0\"");
	}

					////////////////////////////////////////////////////////////////////////////////////////
					// 				FUNCIONES IMPLEMENTADAS EN LAS OPCIONES DEL PROGRAMA				  //
					///////////////////////////////////////////////////////////////////////////////////////

	/* ---------- FUNCIONES DE IMPRESIÓN POR PANTALLA ---------- */
	
	// Función que imprime todas las Publicaciones del ArrayList
	private static void imprimirPublicaciones (ArrayList arrayList){
		for (Iterator i = arrayList.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro)
				System.out.print("--- LIBRO ---	");
			else
				System.out.print("--- REVISTA ---	");
			System.out.println(elemento.toString());
		}
	}

	// Función que imprime una Publicación a partir de un fragmento del título, devuelve la cantidad de coincidencias encontradas
	public static int imprimirPorTitulo(ArrayList arrayList, String fragmento){
		int encontrado = 0;
		for (Iterator i = arrayList.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento.getTitulo().contains(fragmento)) {
				encontrado++;
				if (elemento instanceof Libro)
					System.out.print("--- LIBRO ---	");
				else
					System.out.print("--- REVISTA ---	");
				System.out.println(elemento.toString());
			}
		}
		return (encontrado);
	}

	// Función que imprime el titulo de una Publicación a partir de su código
	public static String imprimirTituloPorCodigo(ArrayList arraylist, String codigo){
		for(Iterator i = arraylist.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento.getCodigo().equals(codigo))
				return elemento.getTitulo();
		}
		return "";
	}

	// ---------- FUNCIONES QUE CUENTAN PUBLICACIONES O SUS TIPOS ----------

	// Función que cuenta todas las Revistas que existen en el ArrayList
	private static int cuentaRevistas(ArrayList arrayList) {
		// Devuelve el número de revistas archivadas
			int contador = 0;
			for (Iterator i = arrayList.iterator(); i.hasNext();)
				if ((Publicacion) i.next() instanceof Revista)
					contador++;
			return contador;
	}
	
	// Función que cuenta todos los Libros que existen en el ArrayList
	private static int cuentaLibros(ArrayList arrayList) {
		// Devuelve el número de revistas archivadas
			int contador = 0;
			for (Iterator i = arrayList.iterator(); i.hasNext();)
				if ((Publicacion) i.next() instanceof Libro)
					contador++;
			return contador;
		}

	// ---------- FUNCIONES DE BORRADO Y MODIFICACIÓN DE ELEMENTOS DEL ARRAYLIST ---------- //

	// Función para borrar publicación dado un código
	public static void borrarPubli(String codigo, ArrayList arraylist){
		for(Iterator i = arraylist.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento.getCodigo().equalsIgnoreCase(codigo))
				i.remove();
		}
	}

	// Función que busca un elemento a partir de su código en el ArrayList y lo modifica
	public static void buscaYModifica(String codigoOriginal, String codigoNew, String titulo, int anioPubl, ArrayList arraylist, int numRevista){
		for(Iterator i = arraylist.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento.getCodigo().equalsIgnoreCase(codigoOriginal)){ //compara el codigo de la piblicacion con el codigo que buscamos
				if (codigoNew != "")
					elemento.setCodigo(codigoNew);
				if (titulo != "")
					elemento.setTitulo(titulo);
				if (anioPubl != 0);
					elemento.setAnio(anioPubl);
				if (elemento instanceof Revista)
					((Revista) elemento).setNumero(numRevista);
				break ; // finalizar bucle for, ahorro de iteraciones
			}
		}
	}

	// ---------- OTRAS FUNCIONES -----------


	// Función que crea el código dependiendo de si es Libro o si es Revista
	public static String concatenarCodigo(int codigo, int libroORevista){
		String codigoFin = new String();

		if (libroORevista == 0)
			codigoFin = "L";
		else
			codigoFin = "R";

		if (codigo == 0) { 			//Estas condiciones ayudan a insertar
			codigoFin += "0000";	//ceros si el número es demasiado pequeño.
		} else if (codigo < 10){
			codigoFin += "000";
			codigoFin += Integer.toString(codigo);
		} else if (codigo < 100){
			codigoFin += "00";
			codigoFin += Integer.toString(codigo);
		} else if (codigo < 1000){
			codigoFin += "0";
			codigoFin += Integer.toString(codigo);
		} else
			codigoFin += Integer.toString(codigo);
		return codigoFin;
	}

	// Función para controlar el flujo del programa (no me gusta que cada vez que termine cada una de las opciones salga automáticamente el mennu principal)
	public static void presionarEnter(){
		System.out.println("\nPresione ENTER para continuar...");
		try {
			System.in.read();
		} catch(Exception e){}
	}

	// Función que comprueba si un código existe en algún elemento del ArrayList
	public static boolean existeCodigo(String codigo, ArrayList arraylist) {
		for(Iterator i = arraylist.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento.getCodigo().equalsIgnoreCase(codigo)) //compara el codigo de la piblicacion con el codigo que buscamos
				return (true);
		}
		return (false);
	}

					////////////////////////////////////////////////////////////////////////////////////////
					// 								OPCIONES Y FUNCIONES EXTRA							 //
					///////////////////////////////////////////////////////////////////////////////////////

	// OPCIÓN 5 - PRESTAMO LIBRO
	public static void prestamo(ArrayList Publicaciones){
		String codigo;
		System.out.print("Inserte código del libro que deseas prestar: ");
		codigo = FScanner.scannerString();
		if (existeCodigo(codigo,Publicaciones)){
			prestar(Publicaciones, codigo);
		} else
			System.out.println("\nCódigo introducido erróneo. Inténtelo de nuevo");
	}

	// OPCIÓN 6 - DEVOLVER LIBRO
	public static void devolucion(ArrayList Publicaciones){
		String codigo;
		System.out.print("Inserte código del libro que deseas devolver: ");
		codigo = FScanner.scannerString();
		if (existeCodigo(codigo,Publicaciones)){
			devolver(Publicaciones, codigo);
		} else
			System.out.println("\nCódigo introducido erróneo. Inténtelo de nuevo");
	}

	// OPCIÓN 7 - MOSTRAR REVISTAS
	public static void imprimirRevistas (ArrayList Publicaciones){
		for (Iterator i = Publicaciones.iterator(); i.hasNext();) {
		Publicacion elemento = (Publicacion) i.next();
		if (elemento instanceof Revista) {
			System.out.print("--- REVISTA ---	");
			System.out.println(elemento.toString());
			}
		}
		System.out.println("\n--- Existe(n) un total de " + cuentaRevistas(Publicaciones) + " Revista(s) ---");
	}

	// OPCIÓN 8 - MOSTRAR LIBROS
	public static void imprimirLibros (ArrayList Publicaciones){
		for (Iterator i = Publicaciones.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro) {
				System.out.print("--- LIBRO ---	");
				System.out.println(elemento.toString());
			}
		}
		System.out.println("\n--- Existe(n) un total de " + cuentaLibros(Publicaciones) + " Libro(s) ---");
	}

	// OPCIÓN 9 - MOSTRAR LIBROS PRESTADOS
	public static void mostrarPrestados(ArrayList Publicaciones){
		Libro aux = new Libro();
		int contador = 0;
		for (Iterator i = Publicaciones.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro){
				aux = (Libro)elemento;
				if (aux.prestado()){
					System.out.print("\n--- LIBRO ---	");
					System.out.println(elemento.toString());
					contador++;
				}
			}
		}
		System.out.println("\n--- Hay un total de " + contador + " Libros prestados.");
	}

	// OPCIÓN 10 - MOSTRAR LIBROS NO PRESTADOS
	public static void mostrarNoPrestados(ArrayList Publicaciones){
		Libro aux = new Libro();
		int contador = 0;
		for (Iterator i = Publicaciones.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro){
				aux = (Libro)elemento;
				if (!aux.prestado()){
					System.out.print("\n--- LIBRO ---	");
					System.out.println(elemento.toString());
					contador++;
				}
			}
		}
		System.out.println("\n--- Hay un total de " + contador + " Libros sin prestar.");
	}
	 
	// OPCIÓN 11 - BORRADO DE ELEMENTOS
	public static void borrado(ArrayList Publicaciones){
		int eleccion, siNo;

		siNo = 0;
		System.out.print("Seleccione una de las opciones (0 para BORRAR TODAS las REVISTAS, 1 para BORRAR TODOS los LIBROS, 2 para borrar ABSOLUTAMENTE TODO): ");
		eleccion = FScanner.scannerInt();
		if (eleccion == 0 || eleccion == 1 || eleccion == 2){
			System.out.print("\n ¿Está seguro de que quieres continuar continuar con el borrado");
			switch (eleccion) { // tontería para que imprima el nombre de la selección 
				case 0: System.out.print(" DE TODAS LAS REVISTAS?"); break;
				case 1: System.out.print(" DE TODOS LOS LIBROS?"); break;
				case 2: System.out.print(" DE TODAS LAS PUBLICACIONES?"); break;
			}
			System.out.print("(0 para cancelar operación, 1 para continuar): ");
			siNo = FScanner.scannerInt();
		} else
			System.out.println("\nOperación cancelada, selección errónea.");
		if (siNo == 1){
			switch(eleccion){
				case 0: borradoRevistas(Publicaciones);
				System.out.println("\n--- Borrado de Revistas satisfactorio ---"); break;
				case 1: borradoLibros(Publicaciones);
				System.out.println("\n--- Borrado de Libros satisfactorio ---"); break;
				case 2: borradoBombaAtomica(Publicaciones);
				System.out.println("\n¡¡¡¡ Ha caído una bomba atómica en la Biblioteca. Ya no existen Publicaciones !!!!"); break;
			}
		}
	}

	// OPCIÓN 12 - INSERTAR LISTA DE PRUEBAS (PARA TESTEO DEL PROGRAMA) Y LAS MUESTRA
	public static void insertarListaPrueba(ArrayList Publicaciones){
		int eleccion;
		System.out.println("Esta función del programa le permitirá probar el programa, se borrarán TODAS las Publicaciones.");
		System.out.println("Estas Publicaciones no se podrán volver a recuperar.");
		System.out.print("¿Está de acuerdo? (0 = No, 1 = Si): ");
		eleccion = FScanner.scannerInt();
		if (eleccion == 1){
			borradoBombaAtomica(Publicaciones);
			addListaTest(Publicaciones);
			System.out.println();
			imprimirPublicaciones(Publicaciones);
			System.out.println("\n--- Lista de prueba insertada satisfactoriamente ---");
		} else
			System.out.println("\nOperación cancelada.");
	}

	// Función para borrar TODAS las Revistas 
	public static void borradoRevistas(ArrayList arraylist){
		for(Iterator i = arraylist.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Revista)
				i.remove();
		}
	}

	// Función para borrar TODOS los Libros
	public static void borradoLibros(ArrayList arraylist){
		for(Iterator i = arraylist.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro)
				i.remove();
		}
	}

	// Función para borrar todas las Publicaciones
	public static void borradoBombaAtomica(ArrayList arraylist){
		for(Iterator i = arraylist.iterator(); i.hasNext();) {
			Publicacion elemento = (Publicacion) i.next();
				i.remove();
		}
	}

	// ---------- FUNCIONES PRESTAMO/DEVOLUCIÓN

	// Función que presta un libro de un ArrayList e imprime si se ha prestado correctamente o si ya está prestado
	private static void prestar(ArrayList arraylist, String codigo){
		Libro aux = new Libro();
		for (Iterator i = arraylist.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro && elemento.getCodigo().equals(codigo)){
				aux = (Libro)elemento;
				if (aux.prestar()){
					System.out.println("\n--- Prestamo realizado satisfactoriamente ---");
					break;
				} else {
					System.out.println("\nEl libro ya está prestado. Disculpe las molestias.");
					break;
				}
			}
		}
	}

	private static void devolver(ArrayList arraylist, String codigo){
		Libro aux = new Libro();
		for (Iterator i = arraylist.iterator(); i.hasNext();){
			Publicacion elemento = (Publicacion) i.next();
			if (elemento instanceof Libro && elemento.getCodigo().equals(codigo)){
				aux = (Libro)elemento;
				if (aux.devolver()){
					System.out.println("\n--- Devolución realizada satisfactoriamente ---");
					break; // (pretendo salir del bucle for, ahorro de iteraciones)
				} else {
					System.out.println("\nEl libro no está prestado. Disculpe las molestias.");
					break; // (pretendo salir del bucle for, ahorro de iteraciones)
				}
			}
		}
	}

	// ---------- OTRAS FUNCIONES ----------

	// Función realizada para ponerla en aquellos programas que no estuviesen disponibles en el momento
	public static void mensajeMantenimiento() { 
		System.out.println("Sección del programa en desarrollo/mantenimiento. Disculpe las molestias.");
	}

	// Función que añade Publicaciones para la prueba/test del programa
	public static void addListaTest(ArrayList arraylist){
		arraylist.add(new Libro("L0000", "Harry Potter y el misterio del Office Perdido", 2000));
		arraylist.add(new Libro("L0001", "El libro mágico de SQL", 1729));
		arraylist.add(new Libro("L0002", "¡Aquí, aquí está la excepción!", 2022));
		arraylist.add(new Revista("R0000", "Forbes: los programadores mejor pagados del mundo", 2022, 131));
		arraylist.add(new Revista("R0001", "MuyProgramador: actualización del último SDK de Java", 2021, 1496));
		arraylist.add(new Revista("R0002", "TecnoGeeks: Los mejores portátiles para programar en 2022", 2022, 5));
	}
}