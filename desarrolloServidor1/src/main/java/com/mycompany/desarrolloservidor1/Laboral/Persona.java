package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Representa una persona mediante sus datos identificativos básicos.
 *
 * <p>Esta clase actúa como superclase de {@link Empleado}.</p>
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 * @see Empleado
 */
public class Persona {

    /** Nombre completo de la persona. */
    public String nombre;

    /** Documento nacional de identidad de la persona. */
    public String dni;

    /** Sexo de la persona: 'M' o 'F'. */
    public char sexo;

    /**
     * Crea una persona con todos sus datos identificativos.
     *
     * @param dni documento nacional de identidad
     * @param nombre nombre completo de la persona
     * @param sexo sexo de la persona ('M' o 'F')
     */
    public Persona(String dni, String nombre, char sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Crea una persona indicando su nombre y sexo.
     * El DNI podrá establecerse posteriormente mediante
     * {@link #setDni(String)}.
     *
     * @param nombre nombre completo de la persona
     * @param sexo sexo de la persona ('M' o 'F')
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni nuevo documento nacional de identidad
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Muestra por la salida estándar los datos básicos de la persona.
     */
    public void Imprime() {
        System.out.println(
                "Persona{"
                + "dni='" + dni + '\''
                + ", nombre='" + nombre + '\''
                + '}'
        );
    }
}