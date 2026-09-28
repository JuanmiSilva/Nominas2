package com.mycompany.desarrolloservidor1.Laboral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Objeto de acceso a datos (DAO) encargado de gestionar
 * las nóminas de los empleados en la base de datos.
 *
 * @author Juan Miguel Silva Martín
 * @version 1.0
 */
public class NominasDAO {

    /**
     * Inserta una nueva nómina en la base de datos.
     *
     * @param dni DNI del empleado al que pertenece la nómina
     * @param nomina importe del sueldo calculado
     */
    public static void insertarNomina(String dni, double nomina) {

        String sql =
                "INSERT INTO nominas (dni, nomina) VALUES (?,?)";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)) {

            sentencia.setString(1, dni);
            sentencia.setDouble(2, nomina);

            sentencia.executeUpdate();

            System.out.println(
                    "Nomina insertada correctamente"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la nómina de un empleado en la base de datos.
     *
     * @param dni DNI del empleado cuya nómina se desea actualizar
     * @param nomina nuevo importe de la nómina
     */
    public static void actualizarNomina(String dni, double nomina) {

        String sql =
                "UPDATE nominas SET nomina=? WHERE dni=?";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)) {

            sentencia.setString(1, dni);
            sentencia.setDouble(2, nomina);

            sentencia.executeUpdate();

            System.out.println(
                    "Nomina actualizada correctamente"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la nómina almacenada de un empleado.
     *
     * @param dni DNI del empleado cuya nómina se desea consultar
     * @return importe de la nómina o 0 si no existe
     */
    public static int devolverNomina(String dni) {

        String sql =
                "SELECT nomina FROM nominas WHERE dni=?";

        int nomina = 0;

        try (Connection conexion = Conexion.conectar();
                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)) {

            sentencia.setString(1, dni);

            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                nomina = rs.getInt("nomina");
            }

        } catch (SQLException ex) {

            System.getLogger(NominasDAO.class.getName())
                    .log(
                            System.Logger.Level.ERROR,
                            (String) null,
                            ex
                    );
        }

        return nomina;
    }
}