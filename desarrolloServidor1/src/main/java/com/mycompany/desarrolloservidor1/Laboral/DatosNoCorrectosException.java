package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Excepción que se produce cuando los datos introducidos para un empleado
 * no cumplen las condiciones establecidas por la aplicación.
 *
 * <p>La categoría debe estar comprendida entre 1 y 10 y los años
 * trabajados no pueden ser negativos.</p>
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class DatosNoCorrectosException extends Exception {

    /**
     * Crea una nueva excepción indicando que los datos proporcionados
     * no son correctos.
     */
    public DatosNoCorrectosException() {
        System.out.println("Datos no correctos");
    }
}