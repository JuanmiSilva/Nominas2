package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Representa a un empleado de la empresa.
 *
 * <p>Hereda de {@link Persona} los datos identificativos básicos
 * y añade la categoría profesional y los años trabajados, utilizados
 * para calcular su nómina.</p>
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 * @see Persona
 * @see Nomina
 */
public class Empleado extends Persona {

    /** Categoría profesional del empleado, comprendida entre 1 y 10. */
    private int categoria;

    /** Número de años trabajados en la empresa. */
    public int anyos;

    /**
     * Crea un empleado con todos sus datos.
     *
     * @param dni documento nacional de identidad
     * @param nombre nombre completo del empleado
     * @param sexo sexo del empleado ('M' o 'F')
     * @param categoria categoría profesional, entre 1 y 10
     * @param anyos años trabajados, mayor o igual que 0
     * @throws DatosNoCorrectosException si la categoría no está entre
     * 1 y 10 o si los años trabajados son negativos
     */
    public Empleado(String dni, String nombre, char sexo,
            int categoria, int anyos)
            throws DatosNoCorrectosException {

        super(dni, nombre, sexo);
        setCategoria(categoria);
        setAnyos(anyos);
    }

    /**
     * Crea un empleado recién incorporado a la empresa.
     * Se establece automáticamente la categoría 1 y 0 años trabajados.
     *
     * @param dni documento nacional de identidad
     * @param nombre nombre completo del empleado
     * @param sexo sexo del empleado ('M' o 'F')
     */
    public Empleado(String dni, String nombre, char sexo) {
        super(dni, nombre, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Establece la categoría profesional del empleado.
     *
     * @param categoria nueva categoría profesional
     * @throws DatosNoCorrectosException si la categoría no está
     * comprendida entre 1 y 10
     */
    public void setCategoria(int categoria)
            throws DatosNoCorrectosException {

        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException();
        }

        this.categoria = categoria;
    }

    /**
     * Establece los años trabajados por el empleado.
     *
     * @param anyos número de años trabajados
     * @throws DatosNoCorrectosException si los años son negativos
     */
    public void setAnyos(int anyos)
            throws DatosNoCorrectosException {

        if (anyos < 0) {
            throw new DatosNoCorrectosException();
        }

        this.anyos = anyos;
    }

    /**
     * Obtiene la categoría profesional del empleado.
     *
     * @return categoría profesional del empleado
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Incrementa en un año la antigüedad del empleado.
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     * Muestra por la salida estándar los datos del empleado.
     * Sobrescribe el método {@link Persona#Imprime()}.
     */
    @Override
    public void Imprime() {
        System.out.println(
                "Empleado{"
                + "dni='" + dni + '\''
                + ", nombre='" + nombre + '\''
                + ", sexo='" + sexo + '\''
                + ", categoria='" + categoria + '\''
                + ", anyos='" + anyos + '\''
                + '}'
        );
    }
}