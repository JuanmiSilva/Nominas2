package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Excepción que se lanza cuando los datos de un empleado no son
 * válidos: categoría fuera del rango 1-10 o años trabajados negativos.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class DatosNoCorrectosException extends Exception {

    /**
     * Crea la excepción e informa por la salida estándar de que
     * los datos no son correctos.
     */
    public DatosNoCorrectosException() {
        System.out.println("Datos no correctos");
    }
}