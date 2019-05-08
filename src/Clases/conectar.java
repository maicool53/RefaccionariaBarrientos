package Clases;

import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Miguel
 */

public class conectar {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=REFACCIONARIABARRIENTOS", "sa" , "123");
           System.out.println(" Se realizo una conexion exitosa");
           //JOptionPane.showMessageDialog(null, "conectado");
           //Cargamos el Driver Access
           //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           //Conectar en red base 
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=//servidor/bd_cw/cw.mdb";
           //Conectar Localmente
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/cwnetbeans/cw.mdb";
          //conect = DriverManager.getConnection(strConect,"",""); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
     
}}
