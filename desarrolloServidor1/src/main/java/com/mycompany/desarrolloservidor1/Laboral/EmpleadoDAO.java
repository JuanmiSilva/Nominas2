package com.mycompany.desarrolloservidor1.Laboral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoDAO {

    public static void insertarEmpleado(Empleado emple) {

        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) "
                + "VALUES (?,?, ?, ?, ?)";
        
        try(Connection conexion = Conexion.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, emple.dni);
            sentencia.setString(2, emple.nombre);
            sentencia.setString(3, String.valueOf(emple.sexo));
            sentencia.setInt(4, emple.getCategoria());
            sentencia.setInt(5, emple.anyos);
            
            sentencia.executeUpdate();
            
            System.out.println("Empleado insertado correctamente");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}