package com.mycompany.desarrolloservidor1.Laboral;

/**
 * Representa a una persona con sus datos identificativos básicos.
 * Es la superclase de la que hereda {@link Empleado}.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class Persona {

    /** Nombre completo de la persona. */
    public String nombre;

    /** Documento nacional de identidad. */
    public String dni;

    /** Sexo de la persona: 'M' masculino, 'F' femenino. */
    public char sexo;

    /**
     * Crea una persona con todos sus datos.
     *
     * @param dni    documento nacional de identidad
     * @param nombre nombre completo de la persona
     * @param sexo   sexo de la persona ('M' o 'F')
     */
    public Persona(String dni, String nombre, char sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Crea una persona sin DNI, que deberá asignarse después
     * mediante {@link #setDni(String)}.
     *
     * @param nombre nombre completo de la persona
     * @param sexo   sexo de la persona ('M' o 'F')
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Asigna el DNI de la persona.
     *
     * @param dni documento nacional de identidad
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Muestra por la salida estándar el DNI y el nombre de la persona.
     */
    public void Imprime() {
        System.out.println("Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}');
    }
}