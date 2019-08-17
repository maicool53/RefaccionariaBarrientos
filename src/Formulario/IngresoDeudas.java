
/*
 * IngresoProductos.java
 *
 * @author Miguel
 */
package Formulario;
import Clases.Sonidos;
import Clases.Conexion;
import Clases.GenerarNumero;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Miguel
 */
public class IngresoDeudas extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoProductos
     */
    public IngresoDeudas() {

        initComponents();
        this.setLocation(150, 15);
        
        
        bloquear();
        cargar("");
        numeros();
        btnnuevo.setEnabled(true);
    }
    
     private void bloquear(){
    botonguardar.setEnabled(false);
    btncancelar.setEnabled(false);
    btnactualizar.setEnabled(false);
    btndeudor.setEnabled(false);
    txtadeudo.setEnabled(false);
    txtfec.setEnabled(false);
    txtId.setEnabled(false);
     }

    private void limpiar(){
    //txtcod.setText("");
    txtnomape.setText("");
    txtfec.setText("");
    txtadeudo.setText("");
    txtId.setText("");
    }

    private void desbloquear(){
    btnnuevo.setEnabled(false);
    btncancelar.setEnabled(true);
    btndeudor.setEnabled(true);
    txtadeudo.setEnabled(true);
    txtfec.setEnabled(true);
    }

    public static String fechaactual(){
        java.util.Date fecha= new java.util.Date();
        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);}
    
    private void numeros(){
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="select max(Id) from deudas";

        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                c=rs.getString(1);
            }
            if(c==null){
                txtId.setText("00000001");
            }else{
                j=Integer.parseInt(c);
                GenerarNumero gen= new GenerarNumero();
                gen.generar(j);
                txtId.setText(gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
           SS.error();
        JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    private void cargar(String valor) {
        try{
            String [] titulos={"Id","Nombre","Deuda","Fecha"};
            String [] registros= new String[4];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from deudas WHERE CONCAT (Id,'',Nombre,'',deuda,'',fecha) LIKE '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(3);
                registros[3]=rs.getString(4);
                model.addRow(registros);
            }
            tbprodeudas.setModel(model);
            }
        catch(SQLException e){
                SS.error();
        JOptionPane.showMessageDialog(null, e);
                 }
        }

private void BuscarProductoEditar(String cod) {
        
        try{
           
            String Id="",desc="",marca="",prec="";
            String cons="select * from deudas WHERE Id='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                Id=rs.getString(1);
                desc=rs.getString(2);
                marca=rs.getString(3);
                prec=rs.getString(4);
                }
            
            txtId.setText(Id);
            txtnomape.setText(desc);
            txtadeudo.setText(marca);
            txtfec.setText(prec);
            }
        catch (SQLException e) {
            SS.error();
        JOptionPane.showMessageDialog(null, e);
        }

    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Actualizar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        botonguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbprodeudas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btndeudor = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtadeudo = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Refresh_16px.png"))); // NOI18N
        Actualizar.setText("Modificar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Actualizar);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Trash_16px.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE DEUDORES");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add User Male_32px.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        botonguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save_32px.png"))); // NOI18N
        botonguardar.setText("Guardar");
        botonguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonguardarActionPerformed(evt);
            }
        });

        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Refresh_32px.png"))); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Delete_32px.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbprodeudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbprodeudas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbprodeudas);

        jLabel4.setText("Buscar:");

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Search_32px.png"))); // NOI18N
        jButton1.setText("Mostrar Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Señor(a):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(0, 51, 204));
        txtnomape.setEnabled(false);
        txtnomape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomapeActionPerformed(evt);
            }
        });

        btndeudor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Find User Male_32px.png"))); // NOI18N
        btndeudor.setText("...");
        btndeudor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeudorActionPerformed(evt);
            }
        });

        jLabel12.setText("Fecha:");

        txtfec.setBackground(new java.awt.Color(240, 240, 240));

        jLabel14.setText("Adeudo:");

        txtadeudo.setBackground(new java.awt.Color(240, 240, 240));
        txtadeudo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtId.setBackground(new java.awt.Color(240, 240, 240));

        jLabel13.setText("Deuda N°:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("$");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeudor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtadeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(187, 187, 187))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtadeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnomape)
                    .addComponent(btndeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit File_32px.png"))); // NOI18N
        btnsalir.setText("Modificar");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Trash_32px.png"))); // NOI18N
        btnsalir1.setText("Eliminar");
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print_32px.png"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsalir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsalir1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnimprimir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnimprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        try {
        int filaMod = tbprodeudas.getSelectedRow();
        if (filaMod == -1) {
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
        } else {

            btnactualizar.setEnabled(true);
            botonguardar.setEnabled(false);
            String cod = (String) tbprodeudas.getValueAt(filaMod, 0);
            desbloquear();
            BuscarProductoEditar(cod);
        }
    } catch (HeadlessException e) {
        SS.error();
        JOptionPane.showMessageDialog(null, e);
    }
    
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    SS.Clic();
    desbloquear();
    limpiar();
    txtfec.setDisabledTextColor(Color.blue);
    txtfec.setText(fechaactual());
    numeros();
    botonguardar.setEnabled(true);
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    SS.Clic();
    limpiar();
    bloquear();
    btnnuevo.setEnabled(true);
}//GEN-LAST:event_btncancelarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    SS.Clic();
    cargar("");
}//GEN-LAST:event_jButton1ActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    cargar(txtbuscar.getText());
}//GEN-LAST:event_txtbuscarKeyReleased

private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
// TODO add your handling code here:
SS.Clic();
    int filasel = tbprodeudas.getSelectedRow();
    SS.notificacion();
    int opt=JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   if (opt==0){
    try {
        if (filasel == -1) {
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Seleccione algun dato");
        } else {
            String cod = (String) tbprodeudas.getValueAt(filasel, 0);
            String eliminarSQL = "DELETE FROM deudas WHERE Id = '" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                SS.notificacion();
                JOptionPane.showMessageDialog(null, "Borrado");
                cargar("");
            } catch (Exception e) {
             SS.error();
        JOptionPane.showMessageDialog(null, e);
            }
        }
    } catch (HeadlessException e) {
        SS.error();
        JOptionPane.showMessageDialog(null, e);
    }}
}//GEN-LAST:event_EliminarActionPerformed

private void botonguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonguardarActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        String numfac,nombre,adeudo,fec,sql="";
        numfac=txtId.getText();
        nombre=txtnomape.getText();
        adeudo=txtadeudo.getText();
        fec=txtfec.getText();
        sql="INSERT INTO deudas (Id,Nombre,deuda,fecha) VALUES (?,?,?,?)";
        
       try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, numfac);
            pst.setString(2,nombre);
            pst.setString(3, adeudo);
            pst.setString(4, fec);
            
            int n=pst.executeUpdate();
            if(n>0){
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
            bloquear();
            limpiar();
        }
        cargar("");
    } catch (SQLException ex) {SS.error();
        JOptionPane.showMessageDialog(null, ex);
        Logger.getLogger(IngresoDeudas.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_botonguardarActionPerformed

private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
// TODO add your handling code here:
SS.Clic();
    try {
        int filaMod = tbprodeudas.getSelectedRow();
        if (filaMod == -1) {
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
        } else {

            btnactualizar.setEnabled(true);
            botonguardar.setEnabled(false);
            String cod = (String) tbprodeudas.getValueAt(filaMod, 0);
            desbloquear();
            BuscarProductoEditar(cod);
        }
    } catch (HeadlessException e) {
        SS.error();
        JOptionPane.showMessageDialog(null, e);
    }
}//GEN-LAST:event_ActualizarActionPerformed

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        btnnuevo.setEnabled(false);
        String sql="UPDATE deudas SET deuda= '"+txtadeudo.getText()+"' WHERE Id = '"+txtId.getText()+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Actualizado");
            cargar("");
            bloquear();
            limpiar();
            botonguardar.setEnabled(false);
            btnnuevo.setEnabled(true);
    } catch (Exception e) {
        SS.error();
        JOptionPane.showMessageDialog(null, e);
    }
}//GEN-LAST:event_btnactualizarActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtnomapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomapeActionPerformed

    private void btndeudorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeudorActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        EnviarClientesDeuda cli = new EnviarClientesDeuda();
        Principal.jdpescritorio.add(cli);
        cli.toFront();
        SS.EfectoPopUp();
        cli.setVisible(true);
    }//GEN-LAST:event_btndeudorActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
        SS.Clic();
            int filasel = tbprodeudas.getSelectedRow();
            SS.notificacion();
   int opt=JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   if (opt==0){
    try {
        if (filasel == -1) {
            SS.notificacion();
            JOptionPane.showMessageDialog(null, "Seleccione algun dato");
        } else {
            String cod = (String) tbprodeudas.getValueAt(filasel, 0);
            String eliminarSQL = "DELETE FROM deudas WHERE Id = '" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                SS.notificacion();
                JOptionPane.showMessageDialog(null, "Borrado");
                cargar("");
            } catch (Exception e) {
                SS.error();
        JOptionPane.showMessageDialog(null, e);
            }
        }
    } catch (HeadlessException e) {
       SS.error();
        JOptionPane.showMessageDialog(null, e); 
    }}
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        /*int fila=tbfacturas.getSelectedRow();
        //String factura=txtfac.getText();
        if(fila>=0){*/
            try {
                InputStream dir;
                dir = getClass().getResourceAsStream("/Reportes/ReportDeudores.jrxml");
                JasperReport reportes=JasperCompileManager.compileReport(dir);
                JasperPrint print=JasperFillManager.fillReport(reportes, null,cc.conexion());
                JasperViewer view = new JasperViewer (print, false);
                view.setVisible(true);
            } catch (JRException e) {
                SS.error();
        JOptionPane.showMessageDialog(null, e);
                //System.out.printf(e.getMessage());
            }/*}else{
                JOptionPane.showMessageDialog(null, "Seleccione algun elemento de la lista");
            }*/
    }//GEN-LAST:event_btnimprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Actualizar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JButton botonguardar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btndeudor;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbprodeudas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtadeudo;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtfec;
    public static javax.swing.JTextField txtnomape;
    // End of variables declaration//GEN-END:variables
   Sonidos SS= new Sonidos();
    Conexion cc = new Conexion();
   Connection cn = cc.conexion();

}
