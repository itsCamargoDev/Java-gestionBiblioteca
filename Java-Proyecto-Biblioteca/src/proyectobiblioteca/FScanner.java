package proyectobiblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

// ESTA CLASE SE ENCARGA DE LAS ENTRADAS POR TECLADO
public class FScanner {
	static int scannerInt()	//Método para gestionar el input de ints + solucion a posible error de formato de entrada
	{
		Scanner in = new Scanner(System.in);
		int num = -12938457;			// ES UN NÚMERO DE CONTROL, EL PROGRAMA LO ENTENDERÁ
			try {						// COMO ERROR DE FORMATO
				num = in.nextInt();
			} catch(InputMismatchException ex) {
				System.out.println("\nFormato incorrecto, vuelva a intentarlo.");
			}
		return (num);
	}

	static String scannerString()	//Método para gestionar el input de cadenas de caracteres 
	{
		Scanner in = new Scanner(System.in);
		String cadena = new String();
		cadena = in.nextLine();
		return (cadena);
	}
}
