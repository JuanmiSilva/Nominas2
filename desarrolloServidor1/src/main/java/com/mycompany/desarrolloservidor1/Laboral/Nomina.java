package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Clase encargada de calcular el sueldo de los empleados.
 *
 * <p>El sueldo se obtiene a partir del sueldo base correspondiente
 * a la categoría profesional y de un complemento de 5000 euros
 * por cada año trabajado.</p>
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 * @see Empleado
 */
public class Nomina {

    /**
     * Sueldos base correspondientes a las categorías profesionales
     * del 1 al 10.
     */
    private static final int SUELDO_BASE[] = {
        50000, 70000, 90000, 110000, 130000,
        150000, 170000, 190000, 210000, 230000
    };

    /**
     * Calcula el sueldo de un empleado.
     *
     * <p>El sueldo se obtiene sumando al sueldo base de la categoría
     * 5000 euros por cada año trabajado.</p>
     *
     * @param emple empleado cuyo sueldo se desea calcular
     * @return sueldo calculado
     */
    public static int sueldo(Empleado emple) {

        return SUELDO_BASE[emple.getCategoria() - 1]
                + 5000 * emple.anyos;
    }
}