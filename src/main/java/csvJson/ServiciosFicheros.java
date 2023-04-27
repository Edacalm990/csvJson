/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvJson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eli
 */
public class ServiciosFicheros {
        // método para leer archivo llamando al método obtenerLista
    public static void leerArchivo(String archivo, Charset  codificacion) {
        List<String> lineas = obtenerLista(archivo, codificacion);
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }
    
    
    
        // método que crea un List<String> con las lineas del archivo
    public static List<String> obtenerLista(String archivo, Charset  codificacion ) {
        List<String> lineas = new ArrayList<>();
        try {
            lineas = Files.readAllLines(Paths.get(archivo),
                    StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero");
        }
        return lineas;
    }
    
    // crea un fichero con un String de datos y el nombre
     public static void crearFichero(String datos, String nombreFichero) {

        try {
            Files.write(Paths.get(nombreFichero), datos.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
    }
     
       
     

}
