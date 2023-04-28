/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvJson;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 /*
a) Sin usar API Stream

 - Contar el número de profesores de Tecnología.
 - Saber si algún profesor/a de Informática es también coordinador
 - Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra J.
 - Verificar que ningún profesor se llama "Jonh".

b) Repetir el apartado a) usando API Stream
 */
public class mainCsvJson {

    public static void main(String[] args) {
        List<String> listaString = ServiciosFicheros.obtenerLista("./datosPersonal.csv", StandardCharsets.ISO_8859_1);
        List<Datos> listaDatos = crearListaDatos(listaString);
        List<Datos> listaDatosFiltrada = eliminarTrabajadoresAntiguedad(listaDatos, 10, 15);
        System.out.println(listaDatosFiltrada.size());
        ServiciosFicheros.crearJson("trabajadores.json", listaDatosFiltrada);

        // A  - 
        //Contar el número de profesores de Tecnología.
        int cantidadProfesores= contarProfesores("Tecnología", listaDatos);
        //Saber si algún profesor/a de Informática es también coordinador
        int cantidadProfesoresInformaticosCoordinadores= contarProfesoresEsCordinador("Informática", listaDatos);
        //  - Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra J.
        listaDatos.sort((x,y)->x.getNombreEmpleado().compareToIgnoreCase(y.getNombreEmpleado()));
        List <String> listaDatosOrdenadaYFiltrada=new ArrayList<>();
        for (Datos empleado : listaDatos) {
            if(empleado.getDNI_Pasaporte().contains("J")){
            listaDatosOrdenadaYFiltrada.add(empleado.getNombreEmpleado().split(" ")[0]);
            }
        }
        
        listaDatosOrdenadaYFiltrada.forEach(System.out::println);
        // Verificar que ningún profesor se llama "Jonh".
        System.out.println(existe("jonh", listaDatos));
        
        // B 
        //Contar el número de profesores de Tecnología.
        System.out.println(listaDatos.stream().filter(x->x.getPuesto().contains("Tecnología")).count());
        //Saber si algún profesor/a de Informática es también coordinador
        System.out.println(listaDatos.stream().filter(x->x.getPuesto().contains("Informática")).anyMatch((t) -> t.isCoordinador()));
        // Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra J.
        List<String> apellidos=listaDatos.stream()
                .filter(x->x.getDNI_Pasaporte().contains("J"))
                .map(x-> x.getNombreEmpleado().split(" ")[0])
                .sorted()
                .collect(Collectors.toList());
        apellidos.forEach(System.out::println);
        // // Verificar que ningún profesor se llama "Jonh".
        System.out.println(listaDatos.stream().anyMatch(x->x.getNombreEmpleado().split(" ")[2].contains("Jonh")));
        
        
    }

    public static List<Datos> crearListaDatos(List<String> listaString) {
        List<Datos> listaDatos = new ArrayList<>();
        for (int i = 1; i < listaString.size(); i++) {
            String[] datosSeparados = listaString.get(i).split("\".\"");
            Datos tmp = new Datos();

            tmp.setNombreEmpleado(datosSeparados[0].replace("\"", ""));
            tmp.setDNI_Pasaporte(datosSeparados[1]);
            tmp.setPuesto(datosSeparados[2]);
            if (!datosSeparados[3].isBlank()) {
                tmp.setFechaInicio(LocalDate.parse(datosSeparados[3], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            if (!datosSeparados[4].isBlank()) {
                tmp.setFechaFin(LocalDate.parse(datosSeparados[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            tmp.setTelefono(datosSeparados[5]);
            if (!datosSeparados[5].isBlank()) {
                tmp.setCoordinador(datosSeparados[6].equalsIgnoreCase("No")
                        ?false
                        :true);
            }
            listaDatos.add(tmp);
        }
        return listaDatos;
    }

    public static List<Datos> eliminarTrabajadoresAntiguedad(List<Datos> listaDatos, int inicio, int fin) {
        return listaDatos.stream()
                .filter((t)
                        -> Period.between(t.getFechaInicio(),
                        ((t.getFechaFin() != null)
                        ? t.getFechaFin()
                        : LocalDate.now())).getYears() >= inicio
                && Period.between(t.getFechaInicio(),
                        ((t.getFechaFin() != null)
                        ? t.getFechaFin()
                        : LocalDate.now())).getYears() <= fin
                )
                .toList();
    }

    public static int contarProfesores(String tipo, List<Datos> listaDatos) {
        int contador = 0;
        for (Datos dato : listaDatos) {
            if(dato.getPuesto().contains(tipo)){
            contador ++;
            };
        }
        
        return contador;
    }
    
        public static int contarProfesoresEsCordinador(String tipo, List<Datos> listaDatos) {
        int contador = 0;
        for (Datos dato : listaDatos) {
            if(dato.getPuesto().contains(tipo) && dato.isEvaluador()){
            contador ++;
            };
        }
        
        return contador;
    }
        
        public static boolean existe(String nombre, List<Datos> listaDatos){
            for (Datos empleado : listaDatos) {
                if(empleado.getNombreEmpleado().toLowerCase().contains(nombre.toLowerCase())){
                return true;
                }
            }
            return false;
        }
    
}
