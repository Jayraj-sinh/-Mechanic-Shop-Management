package dao; 

 

import java.sql.Connection; 

import java.sql.DriverManager; 

import java.sql.SQLException; 

 

public class DatabaseConnection { 

    private static final String URL = "jdbc:mariadb://localhost:3306/mechanic_shop"; 

    private static final String USER = "root"; 

    private static final String PASSWORD = "password";

 

    public static Connection getConnection() throws SQLException { 

        return DriverManager.getConnection(URL, USER, PASSWORD); 

    } 

} 