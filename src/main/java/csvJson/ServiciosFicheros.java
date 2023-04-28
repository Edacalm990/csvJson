/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvJson;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author eli
 */
public class ServiciosFicheros {
    // método para leer archivo llamando al método obtenerLista

    public static void leerArchivo(String archivo, Charset codificacion) {
        List<String> lineas = obtenerLista(archivo, codificacion);
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

    // método que crea un List<String> con las lineas del archivo
    public static List<String> obtenerLista(String archivo, Charset codificacion) {
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

    public static void crearJson(String nombreArchivo, List<Datos> listaDatos) {
        try {
            ObjectMapper mapeador = new ObjectMapper();

            // Permite a mapeador usar fechas según java time
            mapeador.registerModule(new JavaTimeModule());

            // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
            mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(nombreArchivo), listaDatos);
        } catch (Exception e) {
            System.out.println("No se ha podido crear el archivo JSON");
        }

    }

}
