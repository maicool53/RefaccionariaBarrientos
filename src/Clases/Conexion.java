package Clases;

import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Miguel
 */

public class Conexion {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=REFACCIONARIABARRIENTOS", "sa" , "123");
           System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
     
}}
