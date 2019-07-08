/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Alertas.ContraseñaIncorrecta;
import Clases.Conexion;
import Clases.Sonidos;
import Formulario.PantallaBienvenido;
import Formulario.Principal;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author l
 */
public class Loggin extends javax.swing.JFrame {
Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
getMaximumWindowBounds();
    /**
     * Creates new form loggin
     */
    public Loggin() {
        initComponents();
        Usuario.requestFocus();


    }
    Sonidos S = new Sonidos();
    Conexion cc = new Conexion();
   Connection cn = cc.conexion();
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Usuario = new javax.swing.JTextField();
        EtiquetaUsuario = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Contraseña = new javax.swing.JPasswordField();
        EtiquetaContraseña = new javax.swing.JLabel();
        Entrar = new javax.swing.JButton();
        Registrarse = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Usuario.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsuarioMouseClicked(evt);
            }
        });
        Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UsuarioKeyTyped(evt);
            }
        });

        EtiquetaUsuario.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        EtiquetaUsuario.setForeground(new java.awt.Color(206, 158, 0));
        EtiquetaUsuario.setText("USUARIO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Usuario)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(EtiquetaUsuario)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EtiquetaUsuario)
                .addGap(18, 18, 18)
                .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Contraseña.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Contraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContraseñaMouseClicked(evt);
            }
        });
        Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContraseñaKeyTyped(evt);
            }
        });

        EtiquetaContraseña.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        EtiquetaContraseña.setForeground(new java.awt.Color(206, 158, 0));
        EtiquetaContraseña.setText("CONTRASEÑA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Contraseña)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(EtiquetaContraseña)
                .addGap(89, 89, 89))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(EtiquetaContraseña)
                .addGap(18, 18, 18)
                .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Entrar.setBackground(new java.awt.Color(153, 153, 153));
        Entrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Entrar.setText("Ingresar");
        Entrar.setFocusPainted(false);
        Entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EntrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EntrarMouseExited(evt);
            }
        });
        Entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrarActionPerformed(evt);
            }
        });

        Registrarse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Registrarse.setText("Registrarse");
        Registrarse.setFocusPainted(false);
        Registrarse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Registrarse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistrarseMouseClicked(evt);
            }
        });
        Registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarseActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usadas/user_male2-128.png"))); // NOI18N

        Salir.setBackground(new java.awt.Color(153, 153, 153));
        Salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Salir.setText("Salir");
        Salir.setFocusPainted(false);
        Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalirMouseExited(evt);
            }
        });
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel6)))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrarActionPerformed
        S.Click();
        if ((Usuario.getText().equals("")) || (Contraseña.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir tu usuario y tu contraseña :)", "¡Campos vacios!", JOptionPane.WARNING_MESSAGE);

            if (Usuario.getText().equals("")) {
                jPanel2.setBackground(new Color(135, 0, 0));}
            if (Contraseña.getText().equals("")){
                jPanel3.setBackground(new Color(135, 0, 0));}

        } else {
            try {
                ResultSet rs = null;
                PreparedStatement pps = cn.prepareStatement("SELECT * FROM Usuarios where User_nam = ?");
                pps.setString(1, Usuario.getText());
                rs = pps.executeQuery();

                if (rs.next()) {
                    if ((Contraseña.getText().equals(rs.getString(3)))) {
                        int x;
                        x = rs.getInt(4);

                        if (x == 0) {
                            JOptionPane.showMessageDialog(null, "Aun no tienes permiso para acceder, ponte en contacto con el administrador :) ", "¡Acceso Denegado!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (x == 1) {
                                Principal i = new Principal();
                                i.setVisible(true);
                                i.pack();
                                i.setSize(maxBounds.width, maxBounds.height);
                                i.setLocationRelativeTo(null);
                               
                                PantallaBienvenido b = new PantallaBienvenido();
                                b.setLocationRelativeTo(null);
                                b.setVisible(true);
                                S.IniciarSesion();
                            }
                                  
                    else {if (x == 2) {
                                
                                Principal i = new Principal();
                                i.setVisible(true);
                                i.pack();
                                i.setSize(maxBounds.width, maxBounds.height);
                                i.setLocationRelativeTo(null);
                                PantallaBienvenido b = new PantallaBienvenido();
                                b.setLocationRelativeTo(null);
                                b.setVisible(true);
                                b.setUndecorated(true);
                                S.IniciarSesion();}}
                            this.dispose();
                        }

                    } else {
                        ContraseñaIncorrecta v = new ContraseñaIncorrecta();
                        v.setLocationRelativeTo(null);
                        v.setVisible(true);
                        v.setAlwaysOnTop(true);
                        jPanel3.setBackground(new Color(255, 51, 51));
                        Contraseña.setText("");
                    }
                } else {
                    S.error();
                    String L = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "Oh no esa cuenta no existe, ¿seguro que estas registrado? "+L+"Asegurate de estar registrado o contacta a tu administrador", "¡Cuenta Inexistente!", JOptionPane.ERROR_MESSAGE);
                    Registrarse.setBorderPainted(true);
                }

            } catch (SQLException ex) {

                Logger.getLogger(Loggin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_EntrarActionPerformed

    private void RegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarseActionPerformed
        S.Click();
        SingUp W = new SingUp();
        W.setLocationRelativeTo(null);
        W.setVisible(true);
    }//GEN-LAST:event_RegistrarseActionPerformed

    private void ContraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContraseñaMouseClicked

    }//GEN-LAST:event_ContraseñaMouseClicked

    private void UsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioMouseClicked

    }//GEN-LAST:event_UsuarioMouseClicked

    private void EntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarMouseEntered

    }//GEN-LAST:event_EntrarMouseEntered

    private void EntrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarMouseExited

    }//GEN-LAST:event_EntrarMouseExited

    private void ContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContraseñaKeyTyped
        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            Entrar.doClick();
            S.Click();
        }
    }//GEN-LAST:event_ContraseñaKeyTyped

    private void SalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirMouseEntered

    private void SalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirMouseExited

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
        S.Click();
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsuarioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            Contraseña.requestFocus();
        }
    }//GEN-LAST:event_UsuarioKeyTyped

    private void EntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarMouseClicked
        // TODO add your handling code here:
        S.Click();     
    }//GEN-LAST:event_EntrarMouseClicked

    private void SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseClicked
        // TODO add your handling code here:
        S.Click();
    }//GEN-LAST:event_SalirMouseClicked

    private void RegistrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarseMouseClicked
        // TODO add your handling code here:
        S.Click();
    }//GEN-LAST:event_RegistrarseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loggin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPasswordField Contraseña;
    public static javax.swing.JButton Entrar;
    private javax.swing.JLabel EtiquetaContraseña;
    private javax.swing.JLabel EtiquetaUsuario;
    private javax.swing.JButton Registrarse;
    public static javax.swing.JButton Salir;
    public static javax.swing.JTextField Usuario;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
