package proyectobiblioteca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


// ESTA CLASE SE ENCARGA DE GESTIONAR LA LECTURA Y ARCHIVACIÓN DEL BINARIO

public class Binario {
	public static void archivarBin (ArrayList arraylist) { //guarda ArrayList en archivo binario
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("binPubli.bin"));
			for (Iterator i = arraylist.iterator(); i.hasNext();) {
				Publicacion p = (Publicacion)i.next();
				oos.writeObject(p);
			}
			oos.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
		} catch (IOException ex){
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
		}
	}

	public static void leerBin (ArrayList arraylist) throws ClassNotFoundException, IOException{ //recupera los objetos del binario (Publicaciones archivadas)
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("binPubli.bin"));
			Publicacion p = (Publicacion) ois.readObject();
			while (p != null){
				if (p instanceof Libro)
					arraylist.add(new Libro((Libro)p));
				else // si es revista
                                        arraylist.add(new Revista((Revista)p));
				p = (Publicacion)ois.readObject();
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Archivo no encontrado");
		} catch (ClassCastException ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			//EOF
		}
	}
}