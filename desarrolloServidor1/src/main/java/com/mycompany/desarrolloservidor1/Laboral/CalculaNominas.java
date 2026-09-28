package com.mycompany.desarrolloservidor1.Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Programa principal de gestión de empleados y nóminas.
 * Permite consultar empleados, consultar sus salarios, modificar sus datos,
 * realizar altas y crear copias de seguridad de la información almacenada
 * en la base de datos.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class CalculaNominas {

    /** Ruta del fichero de texto utilizado para realizar la copia de seguridad. */
    static String ruta = "empleados.txt";

    /** Scanner utilizado para leer datos introducidos por el usuario. */
    static Scanner sc = new Scanner(System.in);

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de la línea de comandos; no se utilizan
     * @throws DatosNoCorrectosException si los datos de un empleado no son válidos
     * @throws IOException si se produce un error durante una operación de entrada
     * o salida
     */
    public static void main(String[] args) throws DatosNoCorrectosException, IOException {

        ArrayList<Empleado> emples = new ArrayList<>();

        int resp = 0;

        mostrarEmpleados();

        altaEmpleado(new Empleado("00000001L", "Lidia", 'F'));

        do {
            System.out.println();
            System.out.println("--- MENU DE ACCIONES ---");
            System.out.println("1.- Mostrar los empleados");
            System.out.println("2.- Mostrar el salario de un empleado");
            System.out.println("3.- Modificar empleado");
            System.out.println("4.- Hacer copia de seguridad");
            System.out.println("5.- Salir");
            System.out.println();
            System.out.print("Introduce el numero: ");

            resp = sc.nextInt();
            sc.nextLine();

            switch (resp) {
                case 1 ->
                    mostrarEmpleados();

                case 2 -> {
                    System.out.println("Digame el dni del empleado:");
                    String dni = sc.nextLine();
                    sc.nextLine();

                    System.out.println("DNI: " + dni + "Nomina: "
                            + NominasDAO.devolverNomina(dni));
                }

                case 3 ->
                    actualizarEmpleado();

                case 4 ->
                    copiaSeguridad(ruta);

                case 5 -> {
                    System.out.println("Saliendo de la aplicacion.");
                    System.out.println("Se hará una copia de seguridad");
                    copiaSeguridad(ruta);
                    break;
                }

                default -> {
                    System.out.println("Introduce un numero válido");
                }
            }

        } while (resp != 5);
    }

    /**
     * Muestra por la salida estándar los datos de un empleado y su sueldo.
     *
     * @param e1 empleado cuyos datos se desean mostrar
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

    /**
     * Lee los empleados almacenados en un fichero de texto.
     * Cada línea del fichero debe contener los datos de un empleado
     * separados mediante punto y coma.
     *
     * @param ruta ruta del fichero de texto que contiene los empleados
     * @return lista de empleados obtenidos del fichero
     * @throws DatosNoCorrectosException si los datos de algún empleado
     * no son válidos
     */
    public static ArrayList<Empleado> lectura(String ruta)
            throws DatosNoCorrectosException {

        ArrayList<Empleado> emples = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                emples.add(new Empleado(
                        partes[0],
                        partes[1],
                        partes[2].charAt(0),
                        Integer.parseInt(partes[3]),
                        Integer.parseInt(partes[4])
                ));
            }

            System.out.println("Se han leido los empleados");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return emples;
    }

    /**
     * Escribe en el fichero de texto los datos de una lista de empleados.
     * Los datos se almacenan separados mediante punto y coma.
     *
     * @param emples lista de empleados que se desea almacenar
     */
    public static void escritura(ArrayList<Empleado> emples) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {

            for (Empleado e : emples) {

                bw.write(e.dni + ";"
                        + e.nombre + ";"
                        + e.sexo + ";"
                        + e.getCategoria() + ";"
                        + e.anyos);

                bw.newLine();
            }

            System.out.println("Se han escrito los empleados");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: "
                    + e.getMessage());
        }
    }

    /**
     * Da de alta un empleado en la base de datos.
     * Tras insertar el empleado, calcula automáticamente su sueldo
     * y almacena la nómina correspondiente.
     *
     * @param emple empleado que se desea dar de alta
     */
    public static void altaEmpleado(Empleado emple) {

        EmpleadoDAO.insertarEmpleado(emple);

        NominasDAO.insertarNomina(
                emple.dni,
                Nomina.sueldo(emple)
        );
    }

    /**
     * Da de alta varios empleados a partir de un fichero de texto.
     * Cada empleado leído del fichero se inserta en la base de datos
     * mediante el método de alta individual.
     *
     * @param ruta ruta del fichero que contiene los nuevos empleados
     * @throws DatosNoCorrectosException si los datos de algún empleado
     * no son válidos
     */
    public static void altaEmpleado(String ruta)
            throws DatosNoCorrectosException {

        ArrayList<Empleado> nuevos = lectura(ruta);

        for (Empleado nuevo : nuevos) {
            altaEmpleado(nuevo);
        }
    }

    /**
     * Obtiene y muestra todos los empleados almacenados en la base de datos.
     * Se muestran el DNI, nombre, sexo, categoría y años trabajados.
     *
     * @throws DatosNoCorrectosException si los datos de algún empleado
     * recuperado de la base de datos no son válidos
     */
    public static void mostrarEmpleados()
            throws DatosNoCorrectosException {

        ArrayList<Empleado> emples =
                EmpleadoDAO.devolverEmpleados();

        System.out.printf(
                "%-10s %-20s %-5s %-10s %-10s%n",
                "DNI",
                "Nombre",
                "Sexo",
                "Categoría",
                "Años"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (Empleado emple : emples) {

            System.out.printf(
                    "%-10s %-20s %-5s %-10d %-10d%n",
                    emple.dni,
                    emple.nombre,
                    emple.sexo,
                    emple.getCategoria(),
                    emple.anyos
            );
        }
    }

    /**
     * Permite modificar los datos de un empleado almacenado en la base
     * de datos. El usuario puede modificar el nombre, sexo, categoría
     * y años trabajados.
     *
     * <p>Una vez modificados los datos del empleado, se vuelve a calcular
     * su sueldo y se actualiza la nómina almacenada en la base de datos.</p>
     *
     * @throws DatosNoCorrectosException si los nuevos datos del empleado
     * no son válidos
     */
    public static void actualizarEmpleado()
            throws DatosNoCorrectosException {

        String cont;

        String sql = "UPDATE Empleados SET 1=1";

        System.out.println("DNI del empleado: ");

        String dni = sc.nextLine();
        sc.nextLine();

        System.out.println("Quieres modificar el nombre?(s/n)");

        cont = sc.nextLine();
        sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nuevo nombre: ");

            String nombre = sc.nextLine();
            sc.nextLine();

            sql += ", nombre = " + nombre;
        }

        System.out.println("Quieres modificar el sexo?(s/n)");

        cont = sc.nextLine();
        sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            char sexo;

            do {
                System.out.println("Nuevo sexo: ");

                sexo = sc.nextLine().charAt(0);
                sc.nextLine();

                if (sexo != 'M' && sexo != 'F') {
                    System.out.println(
                            "El sexo debe ser 'M' o 'f'"
                    );
                }

            } while (sexo != 'M' && sexo != 'F');

            sql += ", sexo = " + sexo;
        }

        System.out.println(
                "Quieres modificar la categoria?(s/n)"
        );

        cont = sc.nextLine();
        sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nueva categoría: ");

            int categoria = sc.nextInt();
            sc.nextLine();

            sql += ", categoria = " + categoria;
        }

        System.out.println(
                "Quieres modificar los años?(s/n)"
        );

        cont = sc.nextLine();
        sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nuevo año: ");

            int anyos = sc.nextInt();
            sc.nextLine();

            sql += ", anyos = " + anyos;
        }

        sql += ";";

        EmpleadoDAO.actualizarEmpleado(sql);

        NominasDAO.actualizarNomina(
                dni,
                Nomina.sueldo(
                        EmpleadoDAO.devolverEmpleado(dni)
                )
        );
    }

    /**
     * Realiza una copia de seguridad de los empleados almacenados
     * en la base de datos utilizando el fichero de texto configurado
     * en la aplicación.
     *
     * @param ruta ruta del fichero donde se almacenará la copia
     * @throws DatosNoCorrectosException si los datos de algún empleado
     * no son válidos
     */
    public static void copiaSeguridad(String ruta)
            throws DatosNoCorrectosException {

        ArrayList<Empleado> emples =
                EmpleadoDAO.devolverEmpleados();

        escritura(emples);

        System.out.println(
                "Se ha hecho una copia de seguridad en " + ruta
        );
    }
}