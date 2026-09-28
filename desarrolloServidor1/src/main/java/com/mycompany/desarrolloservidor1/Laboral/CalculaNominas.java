package com.mycompany.desarrolloservidor1.Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Programa principal de gestión de empleados y nóminas. Permite consultar
 * empleados, consultar sus salarios, modificar sus datos, realizar altas y
 * crear copias de seguridad de la información almacenada en la base de datos.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class CalculaNominas {

    /**
     * Ruta del fichero de texto utilizado para realizar la copia de seguridad.
     */
    static String ruta = "empleados.txt";

    /**
     * Scanner utilizado para leer datos introducidos por el usuario.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de la línea de comandos; no se utilizan
     * @throws DatosNoCorrectosException si los datos de un empleado no son
     * válidos
     * @throws IOException si se produce un error durante una operación de
     * entrada o salida
     */
    public static void main(String[] args)
            throws DatosNoCorrectosException, IOException {

        int resp;

        mostrarEmpleados();

        altaEmpleado(new Empleado("00000001L", "Lidia", 'F'));

        do {
            System.out.println();
            System.out.println("--- MENU DE ACCIONES ---");
            System.out.println("1.- Mostrar los empleados");
            System.out.println("2.- Mostrar el salario de un empleado");
            System.out.println("3.- Modificar empleado");
            System.out.println("4.- Hacer copia de seguridad");
            System.out.println("5.- Introducir mediante .txt");
            System.out.println("6.- Crear Empleado");
            System.out.println("7.- Salir");
            System.out.println();
            System.out.print("Introduce el numero: ");

            resp = sc.nextInt();
            sc.nextLine(); // Limpiar el Enter de nextInt()

            switch (resp) {

                case 1 ->
                    mostrarEmpleados();

                case 2 -> {
                    System.out.println("Dígame el DNI del empleado:");
                    String dni = sc.nextLine();

                    System.out.println("DNI: " + dni + " - Nómina: "
                            + NominasDAO.devolverNomina(dni));
                }

                case 3 ->
                    actualizarEmpleado();

                case 4 ->
                    copiaSeguridad(ruta);

                case 5 -> {
                    System.out.println("--- INTRODUCIR EMPLEADOS MEDIANTE .TXT ---");

                    System.out.print("Introduce la ruta del fichero: ");
                    String rutaFichero = sc.nextLine();

                    altaEmpleado(rutaFichero);
                }

                case 6 -> {
                    System.out.println("--- CREAR EMPLEADO ---");

                    System.out.print("DNI: ");
                    String dni = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    char sexo;

                    do {
                        System.out.print("Sexo (M/F): ");
                        sexo = sc.nextLine().charAt(0);
                        sexo = Character.toUpperCase(sexo);

                        if (sexo != 'M' && sexo != 'F') {
                            System.out.println(
                                    "El sexo debe ser 'M' o 'F'."
                            );
                        }

                    } while (sexo != 'M' && sexo != 'F');

                    System.out.print("Categoría: ");
                    int categoria = sc.nextInt();
                    sc.nextLine(); // Limpiar el Enter

                    System.out.print("Años trabajados: ");
                    int anyos = sc.nextInt();
                    sc.nextLine(); // Limpiar el Enter

                    Empleado empleado = new Empleado(
                            dni,
                            nombre,
                            sexo,
                            categoria,
                            anyos
                    );

                    altaEmpleado(empleado);

                    System.out.println("Empleado creado correctamente.");
                }

                case 7 -> {
                    System.out.println("Saliendo de la aplicación.");
                    System.out.println("Se hará una copia de seguridad.");

                    copiaSeguridad(ruta);
                }

                default ->
                    System.out.println("Introduce un número válido.");
            }

        } while (resp != 7);
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
     *
     * @param ruta ruta del fichero de texto
     * @return lista de empleados obtenidos del fichero
     * @throws DatosNoCorrectosException si los datos no son válidos
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
            System.out.println(
                    "Error al leer el archivo: " + e.getMessage()
            );
        }

        return emples;
    }

    /**
     * Escribe en el fichero de texto los datos de una lista de empleados.
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
            System.out.println(
                    "Error al escribir el archivo: " + e.getMessage()
            );
        }
    }

    /**
     * Da de alta un empleado en la base de datos.
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
     *
     * @param ruta ruta del fichero
     * @throws DatosNoCorrectosException si los datos no son válidos
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
     *
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public static void mostrarEmpleados()
            throws DatosNoCorrectosException {

        ArrayList<Empleado> emples
                = EmpleadoDAO.devolverEmpleados();

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
     * Permite modificar los datos de un empleado.
     *
     * @throws DatosNoCorrectosException si los nuevos datos no son válidos
     */
    public static void actualizarEmpleado()
            throws DatosNoCorrectosException {

        String cont;

        String sql = "UPDATE Empleados SET 1=1";

        System.out.println("DNI del empleado:");
        String dni = sc.nextLine();

        System.out.println("¿Quieres modificar el nombre? (s/n)");
        cont = sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nuevo nombre:");
            String nombre = sc.nextLine();

            sql += ", nombre = '" + nombre + "'";
        }

        System.out.println("¿Quieres modificar el sexo? (s/n)");
        cont = sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            char sexo;

            do {
                System.out.println("Nuevo sexo:");
                sexo = sc.nextLine().charAt(0);
                sexo = Character.toUpperCase(sexo);

                if (sexo != 'M' && sexo != 'F') {
                    System.out.println(
                            "El sexo debe ser 'M' o 'F'"
                    );
                }

            } while (sexo != 'M' && sexo != 'F');

            sql += ", sexo = '" + sexo + "'";
        }

        System.out.println("¿Quieres modificar la categoría? (s/n)");
        cont = sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nueva categoría:");
            int categoria = sc.nextInt();
            sc.nextLine();

            sql += ", categoria = " + categoria;
        }

        System.out.println("¿Quieres modificar los años? (s/n)");
        cont = sc.nextLine();

        if ("s".equalsIgnoreCase(cont)) {

            System.out.println("Nuevos años:");
            int anyos = sc.nextInt();
            sc.nextLine();

            sql += ", anyos = " + anyos;
        }

        sql += " WHERE dni = '" + dni + "';";

        EmpleadoDAO.actualizarEmpleado(sql);

        NominasDAO.actualizarNomina(
                dni,
                Nomina.sueldo(
                        EmpleadoDAO.devolverEmpleado(dni)
                )
        );
    }

    /**
     * Realiza una copia de seguridad de los empleados.
     *
     * @param ruta ruta del fichero donde se almacenará la copia
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public static void copiaSeguridad(String ruta)
            throws DatosNoCorrectosException {

        ArrayList<Empleado> emples
                = EmpleadoDAO.devolverEmpleados();

        escritura(emples);

        System.out.println(
                "Se ha hecho una copia de seguridad en " + ruta
        );
    }
}