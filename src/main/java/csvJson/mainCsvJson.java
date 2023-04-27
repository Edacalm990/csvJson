/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvJson;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eli
 */
/*
Crea una clase que contenga el método main() y gestione la lectura del fichero adjunto. 
Ten en cuenta que la codificación que ha usado el sistema Séneca para exportar el fichero ha sido ISO-8859-1. 
Puedes leer el fichero completo y después crear una lista de objetos o bien hacerlo línea a línea.

Una vez tengas  la lista con todos los registros cargados guarda en un fichero JSON la lista de empleados que, 
contando a partir de hoy, lleven trabajando más de 10 y menos de 15 años (ten en cuenta la fecha de toma de posesión en el puesto de trabajo).

Las lecturas y escrituras de los ficheros se realizan de forma relativa a la carpeta raíz del proyecto Java. 
Cuanto más modularizado esté todo mejor.

*/
public class mainCsvJson {
    public static void main(String[] args) {
        List<String> listaString= ServiciosFicheros.obtenerLista("./datosPersonal.csv", StandardCharsets.ISO_8859_1);
        List<Datos> listaDatos=new ArrayList<>();
        for (int i = 0; i < listaString.size(); i++) {
            String [] datosSeparados= listaString.get(i).split("\".\"");
            Datos tmp=new Datos();
            
        }
        
    }
}
