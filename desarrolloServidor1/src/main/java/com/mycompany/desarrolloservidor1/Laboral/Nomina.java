package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Calcula el sueldo de los empleados a partir de su categoría
 * profesional y de los años trabajados.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 * @see Empleado
 */
public class Nomina {

    /** Sueldos base por categoría; la posición 0 corresponde a la categoría 1. */
    private static final int SUELDO_BASE[] =
            {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

    /**
     * Calcula el sueldo de un empleado como el sueldo base de su
     * categoría más 5000 por cada año trabajado.
     *
     * @param emple empleado del que se quiere calcular el sueldo
     * @return sueldo resultante del empleado
     */
    public static int sueldo(Empleado emple) {
        return SUELDO_BASE[emple.getCategoria() - 1] + 5000 * emple.anyos;
    }
}