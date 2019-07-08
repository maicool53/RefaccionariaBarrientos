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

import Clases.Conexion;
import Clases.Sonidos;
import Login.Loggin;
import Clases.TimerVentana;
import com.sun.awt.AWTUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
/**
 *
 * @author Miguel
 */

public final class PantallaCargando extends javax.swing.JFrame {
    Sonidos S = new Sonidos();
    Conexion cc = new Conexion();
    Connection cn = cc.conexion();
    /** Creates new form PantallaCargando */
    double i=50,
    j=1;
    TimerVentana hilo;
   public static Clip sonido;
   
    public PantallaCargando() throws SQLException {
        initComponents();
        iniciar();
        tunear();
        S.Iniciar();
    } 
    void Nimbus(){
    try {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
         repaint();
         }catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException ex){
             Logger.getLogger(Principal.class.getName()) .log(Level.SEVERE,null, ex);
             }catch (IllegalAccessException ex){
}
    }   
    void BlackEye(){
    try {
        UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
    }
    void BlackMoon(){
        try{
            UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
            repaint();
            }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }   
    void BlueIce(){
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }
    void BlueLight(){
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }   
    void BlueMoon(){
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
            repaint();
        } catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        } 
    void BlueSteel(){
        try{
            UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e){
            }
        }   
    void GreenDream(){
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }   
    void MauveMetallic(){
        try{
            UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e){
            }
        }
    void OrangeMetallic(){
        try {
            UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }
    void SilverMoon(){
        try {
            UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }  
    void Simple2D(){
        try {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }
    void SkyMetallic(){
        try {
            UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
        }
    }    
    void WhiteVision(){
        try {
            UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
            repaint();
        }catch (ParseException | UnsupportedLookAndFeelException e) {
            }
        }
    public void tunear(){
       
        try {
            String Tema=null;
            ResultSet rs = null;
            PreparedStatement SS = cn.prepareStatement("Select Tema from Temas where Id='1' "); 
            rs=SS.executeQuery();
            while(rs.next())
           Tema=rs.getString(1);
            
        if("Nimbus".equals(Tema)){
            Nimbus();
        }
        if( "Black Eye".equals(Tema)){
            BlackEye();
        }
        if( "Black Moon".equals(Tema)){
            BlackMoon();
        }
        if( "Blue Ice".equals(Tema)){
            BlueIce();
        }
        if( "Blue Moon".equals(Tema)){
            BlueMoon();
        }
        if( "Blue Steel".equals(Tema)){
            BlueSteel();
        }
        if( "Mauve Metallic".equals(Tema)){
            MauveMetallic();
        }
        if( "Orange Metallic".equals(Tema)){
            OrangeMetallic();
        }
        if( "Silver Moon".equals(Tema)){
            SilverMoon();
        }
        if( "Simple 2D".equals(Tema)){
            Simple2D();
        }
        if( "Sky Metallic".equals(Tema)){
            SkyMetallic();
        }
        if( "White Vision".equals(Tema)){
            WhiteVision();
        }
        }catch (SQLException ex){
            Logger.getLogger(ConfigurarTema.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,ex);
            System.err.print(ex);
            }
    }
       
    public void iniciar(){
        setLocationRelativeTo(null);
        Progreso.setVisible(true);
        hilo=new TimerVentana(getProgreso());
        hilo.start();
        hilo=null;}
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Progreso = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ProgresoStateChanged(evt);
            }
        });
        getContentPane().add(Progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 300, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/thumbnail_LogoRefBarr.png"))); // NOI18N
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 320));

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
        Loggin p=new Loggin ();
        p.setVisible(true);
        p.pack();
        p.setLocationRelativeTo(null);
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
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new PantallaCargando().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaCargando.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Progreso;
    private javax.swing.JLabel jLabel1;
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
