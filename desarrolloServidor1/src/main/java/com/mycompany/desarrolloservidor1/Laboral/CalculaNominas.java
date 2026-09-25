package com.mycompany.desarrolloservidor1.Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Programa principal que crea varios empleados, muestra sus datos
 * y sus nóminas, y vuelve a mostrarlos tras modificar su antigüedad
 * y su categoría.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class CalculaNominas {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de la línea de comandos (no se utilizan)
     * @throws DatosNoCorrectosException si alguno de los datos asignados a los
     * empleados no es válido
     */
    static String ruta = "empleados.txt";

    public static void main(String[] args) throws DatosNoCorrectosException, IOException {

////        ArrayList<Empleado> emples = new ArrayList<>();
//        ArrayList<Empleado> emples = lectura(ruta);
//
        Empleado e1 = new Empleado("32000032G", "James Gosling", 'M', 10, 3);
////        Empleado e2 = new Empleado("32000031R", "Ada Lovelace", 'F');
////        emples.add(e1);
////        emples.add(e2);
////        
////        escritura(emples);
////        
////        escribe(e1);
////        escribe(e2);
////        for (int i = 0; i < 2; i++) {
////            e2.incrAnyo();
////        }
////        e1.setCategoria(9);
////
////        escribe(e1);
////        escribe(e2);
//        System.out.println("DEL ARCHIVO");
//        for (Empleado emple : emples) {
//            escribe(emple);
//        }

        altaEmpleado(e1);
    }

    /**
     * Muestra por la salida estándar los datos y el sueldo de dos empleados.
     *
     * @param e1 empleado a mostrar
     */
    public static void escribe(Empleado e1) {
        System.out.println("Empleado{"
                + "dni='" + e1.dni + '\''
                + ", nombre='" + e1.nombre + '\''
                + ", sexo='" + e1.sexo + '\''
                + ", categoria='" + e1.getCategoria() + '\''
                + ", anyos='" + e1.anyos + '\''
                + ", sueldo='" + Nomina.sueldo(e1) + '\''
                + '}');
    }

    public static ArrayList<Empleado> lectura(String ruta) throws DatosNoCorrectosException {
        ArrayList<Empleado> emples = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                emples.add(new Empleado(partes[0], partes[1], partes[2].charAt(0), Integer.parseInt(partes[3]), Integer.parseInt(partes[4])));
            }
            System.out.println("Se han leido los empleados");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return emples;
    }

    public static void escritura(ArrayList<Empleado> emples) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Empleado e : emples) {
                bw.write(e.dni + ";" + e.nombre + ";" + e.sexo + ";" + e.getCategoria() + ";" + e.anyos);
                bw.newLine();
            }
            System.out.println("Se han escrito los empleados");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    
    public static void altaEmpleado(Empleado emple){
        EmpleadoDAO.insertarEmpleado(emple);
        NominasDAO.insertarNomina(emple.dni, Nomina.sueldo(emple));
    }
    
    public static void altaEmpleado(String ruta) throws DatosNoCorrectosException{
        ArrayList<Empleado> nuevos = lectura(ruta);
        for (Empleado nuevo:nuevos){
            altaEmpleado(nuevo);
        }
    }
}