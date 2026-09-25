package com.mycompany.desarrolloservidor1.Laboral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() throws SQLException {

        String url = "jdbc:mariadb://localhost:3307/empleados";
        String usuario = "root";
        String password = "0607";

        return DriverManager.getConnection(url, usuario, password);
    }
}