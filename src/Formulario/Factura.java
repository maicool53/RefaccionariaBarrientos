
/*
 * Factura.java
 *
 * @author Miguel
 */
package Formulario;

import Clases.GenerarNumero;
import Clases.Conexion;
import Clases.Sonidos;
import java.awt.Color;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel
 */
public class Factura extends javax.swing.JInternalFrame {

    /** Creates new form Factura */
    public Factura() {
        initComponents();
        this.setLocation(25,15 );
        txtfac.setEnabled(false);
        txtfec.setEnabled(false);
        txtfec.setDisabledTextColor(Color.blue);
        txtfec.setText(fechaactual());
        numeros();
        auto();
    }
    
    private void auto(){
        if(txtcod.getText().equals("")){
            btnproductos.setEnabled(false);           
        }
        tbdet.getModel().addTableModelListener((TableModelEvent e) -> {
            if( e.getColumn()==-1){
                descuento();}});
        
    }
    
    void descontarstock(String codi,String can){
        int des = Integer.parseInt(can);
        String cap="";
        int desfinal;
        String consul="SELECT * FROM producto WHERE cod_pro='"+codi+"'";
        
        try{
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next()){
                cap= rs.getString(6);}   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+ e);
        }
        
        desfinal=Integer.parseInt(cap)-des;
        String modi="UPDATE producto SET Stock='"+desfinal+"' WHERE cod_pro = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error: "+ e);
        }
        
       
         
    }
     private void numeros(){
        int j;
        int cont=1;
        String num="";
        String c="";
        String SQL="select max(num_fac) from factura";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                c=rs.getString(1);
            }
            if(c==null){
                txtfac.setText("00000001");
            }else{
                j=Integer.parseInt(c);
                GenerarNumero gen= new GenerarNumero();
                gen.generar(j);
                txtfac.setText(gen.serie());
            }
            }catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    
    void calcular(){
        String pre;
        String can;
        double iva=0;
        double total=0;
        double subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;

        for(int i=0;i<tbdet.getRowCount();i++){
            
            pre=tbdet.getValueAt(i, 2).toString();
            can=tbdet.getValueAt(i, 3).toString();
            precio=Double.parseDouble(pre);
            cantidad=Integer.parseInt(can);
            imp=precio*cantidad;
            subtotal=subtotal+imp;
            iva=subtotal*0.16;
            total=subtotal+iva;
            //txtcod.setText(""+Math.rint(c*100)/100)
            tbdet.setValueAt(Math.round(imp*100)/100d, i, 4);
            
        }
        txtsubtotal.setText(Double.toString(subtotal));
        txtiva.setText("$"+Math.round(iva*100)/100d);
        txttotal.setText("$"+Math.round(total*100)/100d);
        
            
    }
    
     void factura(){
       String InsertarSQL="INSERT INTO factura (num_fac,cod_cli,subtotal,igv,total,fec_fac) VALUES (?,?,?,?,?,?)";
    String numfac=txtfac.getText();
    String codcli=txtcod.getText();
    String subtotal=txtsubtotal.getText();
    String iva=txtiva.getText();
    String total=txttotal.getText();
    String fecha=txtfec.getText();
    try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numfac);
            pst.setString(2,codcli);
            pst.setString(3,subtotal);
            pst.setString(4,iva);
            pst.setString(5,total);
            pst.setString(6,fecha);
            int n= pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void detallefactura(){
            
        
        for(int i=0;i<tbdet.getRowCount();i++){
        String InsertarSQL="INSERT INTO detallefactura(num_fac,cod_pro,des_pro,cant_pro,pre_unit,pre_tot) VALUES (?,?,?,?,?,?)";
        String numfac=txtfac.getText();
        String codpro=tbdet.getValueAt(i, 0).toString();
        String despro=tbdet.getValueAt(i, 1).toString();
        String cantpro=tbdet.getValueAt(i, 3).toString();
        String preunit=tbdet.getValueAt(i, 2).toString();
        String importe=tbdet.getValueAt(i, 4).toString();
    
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numfac);
            pst.setString(2,codpro);
            pst.setString(3,despro);
            pst.setString(4,cantpro);
            pst.setString(5,preunit);
            pst.setString(6,importe);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    
    
    
    void descuento(){
      DecimalFormatSymbols sb = new DecimalFormatSymbols();
      sb.setDecimalSeparator('.');
      NumberFormat formatter = new DecimalFormat("#0.00",sb);
      String tipocliente=ClaseCliente.getText();
      double descuento = 0;   
            
      if (tipocliente.equals("A")) {descuento=0.70;}
        
      if (tipocliente.equals("B")) {descuento=0.75;}
       
      if (tipocliente.equals("C")) {descuento=0.80;}
       
      if (tipocliente.equals("D")) {descuento=0.85;}
       
      if (tipocliente.equals("E")) {descuento=0.90;}
          
      if (tipocliente.equals("G")) {descuento=0.95;}
      
            String pre;
            String can;
            double iva=0;
            double total=0.00;
            double subtotal=0;
            double precio;
            int cantidadprod;
            double importe=0.0;
            double totaliva=0.0;
            double sumaiva;
        
        for(int i=0;i<tbdet.getRowCount();i++){
            
            pre=tbdet.getValueAt(i, 2).toString();
            can=tbdet.getValueAt(i, 3).toString();
            
            precio=Double.parseDouble(pre);
            cantidadprod=Integer.parseInt(can);
            totaliva=((precio/100)*16);
            sumaiva = ((precio/100)*16)*cantidadprod;
            importe=(precio+totaliva);
            subtotal=(cantidadprod*importe)*descuento;
            total=subtotal+iva;
          double descuentito = (1-descuento)*100;
            tbdet.setValueAt(formatter.format(sumaiva), i, 4);
            tbdet.setValueAt(formatter.format(descuentito) +"%", i, 5);
            tbdet.setValueAt(formatter.format(subtotal), i, 6);
            }
        txtsubtotal.setText(String.valueOf(formatter.format(subtotal)));
        //txtiva.setText(String.valueOf(formatter.format(totaliva)));
        SumarTotal();
        SumarTotalIva();
    }
    
        private void SumarTotal(){
            DecimalFormatSymbols sb = new DecimalFormatSymbols();
            sb.setDecimalSeparator('.');
            NumberFormat formatter = new DecimalFormat("#0.00",sb);
            double total = 0;
            for( int i=0 ; i<tbdet.getRowCount() ; i++){
                double numero =0;
                try{
                    numero = Double.parseDouble( tbdet.getValueAt(i, 6).toString() );
                }catch (NumberFormatException nfe){
                    numero = 0;
                    tbdet.setValueAt(0, i, 6);
                    }
                total += numero;
                }
            this.txttotal.setText( String.valueOf(formatter.format(total)) );
            }
        private void SumarTotalIva(){
            DecimalFormatSymbols sb = new DecimalFormatSymbols();
            sb.setDecimalSeparator('.');
            NumberFormat formatter = new DecimalFormat("#0.00",sb);
            double total = 0;
            for( int i=0 ; i<tbdet.getRowCount() ; i++){
                double numero =0;
                try{
                    numero = Double.parseDouble( tbdet.getValueAt(i, 4).toString() );
                }catch (NumberFormatException nfe){
                    numero = 0;
                    tbdet.setValueAt(0, i, 4);
                    }
                total += numero;
                }
            this.txtiva.setText( String.valueOf(formatter.format(total)) );
            }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btnclientes = new javax.swing.JButton();
        btnproductos = new javax.swing.JButton();
        txtcod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ClaseCliente = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtfac = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtiva = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURA");
        setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel8.setText("Señor(a):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(0, 51, 204));
        txtnomape.setEnabled(false);

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Find User Male_32px.png"))); // NOI18N
        btnclientes.setText("...");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add Shopping Cart_32px.png"))); // NOI18N
        btnproductos.setText("BUSCAR");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setEnabled(false);

        jLabel11.setText("Cod. Cliente:");

        ClaseCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ClaseCliente.setForeground(new java.awt.Color(51, 51, 255));
        ClaseCliente.setEnabled(false);

        txtemail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtemail.setEnabled(false);

        jLabel10.setText("E-mail");

        jLabel9.setText("Tipo:");

        jLabel12.setText("Fecha:");

        jLabel14.setText("Articulo:");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("FACTURA DE VENTA");

        jLabel6.setText("RFC 10046495581");

        jLabel17.setText("Nº");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtfac))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15))))
                .addContainerGap(36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(11, 11, 11)
                .addComponent(jLabel15)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ClaseCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnproductos))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnclientes)))
                        .addGap(12, 12, 12)))
                .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ClaseCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(btnproductos))))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("SubTotal:");

        jLabel19.setText("IVA:");

        jLabel20.setText("Total:");

        tbdet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Precio Unitario", "Cantidad", "Importe", "Descuento", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbdet);
        if (tbdet.getColumnModel().getColumnCount() > 0) {
            tbdet.getColumnModel().getColumn(0).setResizable(false);
            tbdet.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdet.getColumnModel().getColumn(1).setResizable(false);
            tbdet.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbdet.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Shopping Cart_32px.png"))); // NOI18N
        btnguardar.setText("REALIZAR VENTA");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Exit_32px.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clear Shopping Cart_32px.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnguardar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1109, 488);
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual(){
        Date fecha= new Date();
        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
}

private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        EnviarClientesFactura cli = new EnviarClientesFactura();
        Principal.jdpescritorio.add(cli);
        cli.toFront();
        SS.EfectoPopUp();
        cli.setVisible(true);
        btnproductos.setEnabled(true);   
}//GEN-LAST:event_btnclientesActionPerformed

private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        try {
            EnviarProductosFactura pro= new EnviarProductosFactura();
            Principal.jdpescritorio.add(pro);
            SS.EfectoPopUp();
            pro.toFront();
            pro.setVisible(true);
            } catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error: "+ e);
    }
     
}//GEN-LAST:event_btnproductosActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        
        if((txtcod.getText().equals("")) || (txtsubtotal.getText().equals(""))){
            SS.notificacion();
            JOptionPane.showMessageDialog(this, "No ingreso cliente,productos o realice operacion");
            }else{
            
            @SuppressWarnings("UnusedAssignment")
            String capcod = "";
            String capcan = "";
            
            for(int i=0;i<Factura.tbdet.getRowCount();i++){
                capcod=Factura.tbdet.getValueAt(i, 0).toString();
                capcan=Factura.tbdet.getValueAt(i, 3).toString();
                descontarstock(capcod, capcan);}
            factura();
            detallefactura();
            
            txtcod.setText("");
            txtnomape.setText("");
            txtemail.setText("");
            ClaseCliente.setText("");
            txtiva.setText("");
            txtsubtotal.setText("");
            txttotal.setText("");
            txtfec.setText("");
            btnproductos.setEnabled(false);
            
            DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();
            int a =tbdet.getRowCount()-1;
            int i;
            for(i=a;i>=0;i--){
            modelo.removeRow(i);
        }
            numeros();
  }
      
    

}//GEN-LAST:event_btnguardarActionPerformed

private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        SS.Clic();
        DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();
        int fila = tbdet.getSelectedRow();
        if(fila>=0){
            modelo.removeRow(fila);
        }else{
        JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");
    }
}//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ClaseCliente;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tbdet;
    public static javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfac;
    private javax.swing.JTextField txtfec;
    private javax.swing.JTextField txtiva;
    public static javax.swing.JTextField txtnomape;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
    Sonidos SS = new Sonidos();
   Conexion cc=new Conexion();
   Connection cn= cc.conexion();
   
}
