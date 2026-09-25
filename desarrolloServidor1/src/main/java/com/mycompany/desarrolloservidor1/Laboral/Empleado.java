package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Empleado de la empresa. Hereda de {@link Persona} y añade la
 * categoría profesional y los años trabajados, datos necesarios
 * para el cálculo de su nómina.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 * @see Nomina
 */
public class Empleado extends Persona {

    /** Categoría profesional del empleado, en el rango de 1 a 10. */
    private int categoria;

    /** Años trabajados en la empresa; nunca negativo. */
    public int anyos;

    /**
     * Crea un empleado con todos sus datos, validando categoría y años.
     *
     * @param dni       documento nacional de identidad
     * @param nombre    nombre completo del empleado
     * @param sexo      sexo del empleado ('M' o 'F')
     * @param categoria categoría profesional, entre 1 y 10
     * @param anyos     años trabajados, mayor o igual que 0
     * @throws DatosNoCorrectosException si la categoría está fuera del rango 1-10
     *                                   o si los años son negativos
     */
    public Empleado(String dni, String nombre, char sexo, int categoria, int anyos)
            throws DatosNoCorrectosException {
        super(dni, nombre, sexo);
        setCategoria(categoria);
        setAnyos(anyos);
    }

    /**
     * Crea un empleado recién incorporado, con categoría 1 y 0 años trabajados.
     *
     * @param dni    documento nacional de identidad
     * @param nombre nombre completo del empleado
     * @param sexo   sexo del empleado ('M' o 'F')
     */
    public Empleado(String dni, String nombre, char sexo) {
        super(dni, nombre, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Asigna la categoría profesional del empleado.
     *
     * @param categoria categoría profesional, entre 1 y 10
     * @throws DatosNoCorrectosException si la categoría está fuera del rango 1-10
     */
    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException();
        }
        this.categoria = categoria;
    }

    /**
     * Asigna los años trabajados por el empleado.
     *
     * @param anyos años trabajados, mayor o igual que 0
     * @throws DatosNoCorrectosException si los años son negativos
     */
    public void setAnyos(int anyos) throws DatosNoCorrectosException {
        if (anyos < 0) {
            throw new DatosNoCorrectosException();
        }
        this.anyos = anyos;
    }

    /**
     * Devuelve la categoría profesional del empleado.
     *
     * @return categoría entre 1 y 10
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Incrementa en uno los años trabajados por el empleado.
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     * Muestra por la salida estándar todos los datos del empleado.
     * Sobrescribe a {@link Persona#Imprime()}.
     */
    public void Imprime() {
        System.out.println("Empleado{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", anyos='" + anyos + '\'' +
                '}');
    }
    
}