/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Alertas.OpcionesAcceso;
import Alertas.TranferirSU;
import Clases.Conexion;
import Clases.Sonidos;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author l
 */
public class AdministrarCuentas extends javax.swing.JFrame {

    static Conexion cc = new Conexion();
    static Connection cn = cc.conexion();
    String nombre;

    /**
     * Creates new form administrar_cuentas
     */
    public AdministrarCuentas() {
        initComponents();
        mostrar_pendientes();
        ya_asignados();
        oculto.setVisible(false);
        oculto1.setVisible(false);
        //rip = new reporte_individual();
    }

    public static void mostrar_pendientes() {
        DefaultTableModel modelox = new DefaultTableModel();
        modelox.addColumn("Usuario");
        modelox.addColumn("Nombre");

        
        
        try {
            String ConsultaSql="select User_nam,Primer_nombre from Usuarios where Access = 0";
            String datos[] = new String[2];
            
            Statement ps = cn.createStatement();
            ResultSet rs= ps.executeQuery(ConsultaSql);
            
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelox.addRow(datos);
            }
            TablaPendientes.setModel(modelox);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ya_asignados() {
        DefaultTableModel modelox = new DefaultTableModel();
        modelox.addColumn("Usuario");
        modelox.addColumn("Nombre");
        modelox.addColumn("Tipo de acceso");
        tabla_asignados.setModel(modelox);
        String datos[] = new String[3];
        try {
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("select User_nam,Primer_nombre,Access from Usuarios  where access >=1");
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                if (rs.getString(3).equals("0")) {
                    datos[2] = "Denegado";
                } else {
                    if (rs.getString(3).equals("1")) {
                        datos[2] = "Super Usuario";
                    } else {
                        if (rs.getString(3).equals("2")) {
                            datos[2] = "Usuario restringido";
                        }
                    }
                }
                modelox.addRow(datos);
            }
            tabla_asignados.setModel(modelox);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void verdetalles(){
        SS.Clic();
        try {
            int fila = TablaPendientes.getSelectedRow();
            String usuario = TablaPendientes.getValueAt(fila, 0).toString();
            if (fila== -1) {
                SS.notificacion();
                JOptionPane.showMessageDialog(this, "Selecciona una fila", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Detalles i = new Detalles();
                i.setVisible(true);
                i.setLocationRelativeTo(null);
                i.setAlwaysOnTop(true);
                //i.setBounds(500, 250, 500, 300); 
                
                Detalles.Nombre.setEditable(false);
                Detalles.SegundoNombre.setEditable(false);
                Detalles.ApePaterno.setEditable(false);
                Detalles.ApeMaterno.setEditable(false);
                Detalles.Edad.setEditable(false);
                Detalles.Sexo.setEditable(false);
                Detalles.Email.setEditable(false);
                Detalles.Telefono.setEditable(false);
                               
                try {
                    ResultSet rs = null;
                    PreparedStatement pps = cn.prepareStatement("select Primer_nombre,Segundo_nombre,Ape_Paterno,Ape_Materno,Edad,Sexo,Email,Tel from Usuarios where User_nam = ?");
                    pps.setString(1, usuario);
                    rs = pps.executeQuery();

                    if (rs.next()) {
                        Detalles.Nombre.setText(rs.getString(1));
                        Detalles.SegundoNombre.setText(rs.getString(2));
                        Detalles.ApePaterno.setText(rs.getString(3));
                        Detalles.ApeMaterno.setText(rs.getString(4));
                        Detalles.Edad.setText(rs.getString(5));
                        
                        if (rs.getString(6).equals("M")) {
                            Detalles.Sexo.setSelectedItem("MASCULINO");
                        }else {
                            Detalles.Sexo.setSelectedItem("FEMENINO");
                        }
                        
                        Detalles.Email.setText(rs.getString(7));
                        Detalles.Telefono.setText(rs.getString(8));

                    }
                } catch (SQLException ex) {
                    
                    java.util.logging.Logger.getLogger(SingUp.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Selecciona una fila");
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Selecciona Una Fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPendientes = new javax.swing.JTable();
        masInfo1 = new javax.swing.JButton();
        establecerAcceso = new javax.swing.JButton();
        oculto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_asignados = new javax.swing.JTable();
        masInfo2 = new javax.swing.JButton();
        modificarAcceso = new javax.swing.JButton();
        oculto1 = new javax.swing.JTextField();
        comodin = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("AdminCuentas"); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(18, 30, 49));

        TablaPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaPendientes);

        masInfo1.setBackground(new java.awt.Color(153, 153, 153));
        masInfo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add List_32px.png"))); // NOI18N
        masInfo1.setText("Más información");
        masInfo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masInfo1ActionPerformed(evt);
            }
        });

        establecerAcceso.setBackground(new java.awt.Color(153, 153, 153));
        establecerAcceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add User Group Man Man_32px.png"))); // NOI18N
        establecerAcceso.setText("Establecer Acceso");
        establecerAcceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                establecerAccesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(establecerAcceso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(masInfo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(oculto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(masInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(establecerAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pendientes", jPanel2);

        tabla_asignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_asignados.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tabla_asignados);
        tabla_asignados.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        masInfo2.setBackground(new java.awt.Color(153, 153, 153));
        masInfo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add List_32px.png"))); // NOI18N
        masInfo2.setText("Más información");
        masInfo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masInfo2ActionPerformed(evt);
            }
        });

        modificarAcceso.setBackground(new java.awt.Color(153, 153, 153));
        modificarAcceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add User Male_32px.png"))); // NOI18N
        modificarAcceso.setText("Modificar acceso");
        modificarAcceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarAccesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masInfo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(modificarAcceso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(oculto1)
                    .addComponent(comodin))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(masInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificarAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comodin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oculto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Asignados", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void masInfo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masInfo1ActionPerformed
    verdetalles();
    }//GEN-LAST:event_masInfo1ActionPerformed

    private void establecerAccesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_establecerAccesoActionPerformed
       SS.Clic();
        try {
            int fila = TablaPendientes.getSelectedRow();
            String usuario = TablaPendientes.getValueAt(fila, 0).toString();
            oculto.setText(usuario);
            if (fila == -1) {
                SS.notificacion();
                JOptionPane.showMessageDialog(this, "Selecciona una fila", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                OpcionesAcceso N = new OpcionesAcceso();
                N.setLocationRelativeTo(null);
                N.setVisible(true);
                N.setAlwaysOnTop(true);
            }
        } catch (SecurityException ex) {
            System.out.println(ex.getMessage());
        }       
    }//GEN-LAST:event_establecerAccesoActionPerformed

    private void masInfo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masInfo2ActionPerformed

        SS.Clic();
        try {
            int fila = tabla_asignados.getSelectedRow();
            String usuario = tabla_asignados.getValueAt(fila, 0).toString();
            if (fila== -1) {
                SS.notificacion();
                JOptionPane.showMessageDialog(this, "Selecciona una fila", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Detalles i = new Detalles();
                i.setVisible(true);
                i.setLocationRelativeTo(null);
                i.setAlwaysOnTop(true);
                //i.setBounds(500, 250, 500, 300); 
                
                Detalles.Nombre.setEditable(false);
                Detalles.SegundoNombre.setEditable(false);
                Detalles.ApePaterno.setEditable(false);
                Detalles.ApeMaterno.setEditable(false);
                Detalles.Edad.setEditable(false);
                Detalles.Sexo.setEditable(false);
                Detalles.Email.setEditable(false);
                Detalles.Telefono.setEditable(false);
                               
                try {
                    ResultSet rs = null;
                    PreparedStatement pps = cn.prepareStatement("select Primer_nombre,Segundo_nombre,Ape_Paterno,Ape_Materno,Edad,Sexo,Email,Tel from Usuarios where User_nam = ?");
                    pps.setString(1, usuario);
                    rs = pps.executeQuery();

                    if (rs.next()) {
                        Detalles.Nombre.setText(rs.getString(1));
                        Detalles.SegundoNombre.setText(rs.getString(2));
                        Detalles.ApePaterno.setText(rs.getString(3));
                        Detalles.ApeMaterno.setText(rs.getString(4));
                        Detalles.Edad.setText(rs.getString(5));
                        
                        if (rs.getString(6).equals("M")) {
                            Detalles.Sexo.setSelectedItem("MASCULINO");
                        }else {
                            Detalles.Sexo.setSelectedItem("FEMENINO");
                        }
                        
                        Detalles.Email.setText(rs.getString(7));
                        Detalles.Telefono.setText(rs.getString(8));

                    }
                } catch (SQLException ex) {
                    
                    java.util.logging.Logger.getLogger(SingUp.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Selecciona una fila");
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Selecciona Una Fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_masInfo2ActionPerformed

    private void modificarAccesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarAccesoActionPerformed
        SS.Clic();
        try {
            int fila = tabla_asignados.getSelectedRow();
            String usuario = tabla_asignados.getValueAt(fila, 0).toString();
            oculto.setText(usuario);
            oculto1.setText(usuario);
            if (fila == -1) {
                SS.notificacion();
                JOptionPane.showMessageDialog(this, "Selecciona una fila", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    ResultSet rs = null;
                    PreparedStatement sss = cn.prepareStatement("select Access from Usuarios where User_nam = ?");
                    sss.setString(1, oculto.getText());
                    rs = sss.executeQuery();
                    if (rs.next()) {
                        String resp = rs.getString(1);
                        int v2 = Integer.parseInt(resp);
                        if (v2 == 1) {
                            TranferirSU sut = new TranferirSU();
                            sut.setLocationRelativeTo(null);
                            sut.setVisible(true);
                        } else {
                            OpcionesAcceso noa = new OpcionesAcceso();
                            noa.setLocationRelativeTo(null);
                            noa.setVisible(true);
                        }
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        
    }//GEN-LAST:event_modificarAccesoActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarCuentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TablaPendientes;
    private javax.swing.JTextField comodin;
    public static javax.swing.JButton establecerAcceso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton masInfo1;
    private javax.swing.JButton masInfo2;
    private javax.swing.JButton modificarAcceso;
    public static javax.swing.JTextField oculto;
    public static javax.swing.JTextField oculto1;
    public static javax.swing.JTable tabla_asignados;
    // End of variables declaration//GEN-END:variables
Sonidos SS= new Sonidos();
}
