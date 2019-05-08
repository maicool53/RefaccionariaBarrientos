/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Formulario.Login;
/**
 *
 * @author Miguel
 */
public class Principal {
    public static void main(String[] args) {
        Login p= new Login();
        
        p.setVisible(true);
        p.pack();
        p.setLocationRelativeTo(null);
    }
}
