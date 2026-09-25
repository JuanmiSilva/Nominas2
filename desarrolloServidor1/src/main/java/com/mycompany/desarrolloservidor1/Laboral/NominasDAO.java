package com.mycompany.desarrolloservidor1.Laboral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NominasDAO {

    public static void insertarNomina(String dni, double nomina) {

        String sql = "INSERT INTO nominas (dni, nomina) VALUES (?,?)";
        
        try(Connection conexion = Conexion.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, dni);
            sentencia.setDouble(2, nomina);
            
            sentencia.executeUpdate();
            
            System.out.println("Nomina insertada correctamente");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void actualizarNomina(String dni, double nomina){
        String sql= "UPDATE INTO nominas (dni, nomina) VALUES (?,?)";
        
        try(Connection conexion = Conexion.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, dni);
            sentencia.setDouble(2, nomina);
            
            sentencia.executeUpdate();
            
            System.out.println("Nomina actualizada correctamente");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}