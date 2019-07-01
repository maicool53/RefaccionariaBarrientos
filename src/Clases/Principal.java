/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Formulario.PantallaCargando;
import java.sql.SQLException;
/**
 *
 * @author Miguel
 */
public class Principal {
    public static void main(String[] args) throws SQLException  {
        PantallaCargando p= new PantallaCargando();
        
        p.setVisible(true);
        p.pack();
        p.setLocationRelativeTo(null);
    }
}
