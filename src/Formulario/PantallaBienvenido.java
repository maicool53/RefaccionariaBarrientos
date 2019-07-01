/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaCargando.java
 *
 * 
 */
package Formulario;

import Clases.TimerVentana;
import com.sun.awt.AWTUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author IsraelCM
 */
public final class PantallaBienvenido extends javax.swing.JFrame {

    /** Creates new form PantallaCargando */
    double i=50,

    /**
     * Creates new form TimerVentana
     */

    /**
     * Creates new form PantallaCargando
     */
    j=1;
    TimerVentana hilo;
    public PantallaBienvenido() {
        initComponents();
        iniciar();
 
    }
    public void iniciar(){
        setLocationRelativeTo(null);
        Progreso.setVisible(false);
        hilo=new TimerVentana(getProgreso());
        hilo.start();
        hilo=null;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Progreso = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ProgresoStateChanged(evt);
            }
        });
        getContentPane().add(Progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 290, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/giphy (1).gif"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void ProgresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ProgresoStateChanged
    if(Progreso.getValue()==i){
        if(j!=101){
            AWTUtilities.setWindowOpacity(this, Float.valueOf((100-j)/100+"f"));
            i++;
            j+=2;
        }
    }
    if(Progreso.getValue()==100){
        this.dispose();
    }
}//GEN-LAST:event_ProgresoStateChanged

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
            java.util.logging.Logger.getLogger(PantallaBienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaBienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaBienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaBienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PantallaBienvenido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Progreso;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Progreso
     */
    public javax.swing.JProgressBar getProgreso() {
        return Progreso;
    }

    /**
     * @param Progreso the Progreso to set
     */
    public void setProgreso(javax.swing.JProgressBar Progreso) {
        this.Progreso = Progreso;
    }
}