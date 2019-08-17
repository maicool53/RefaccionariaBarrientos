/*
 * Ticket.java
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
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Ticket extends javax.swing.JInternalFrame {

    /** Creates new form Boleta */
    public Ticket() {
        initComponents();
        this.setLocation(15,15 );
        txtfecha.setDisabledTextColor(Color.blue);
        txtfecha.setText(fechaact());
        txtnumbol.setDisabledTextColor(Color.red);
        CodCliente.setDisabledTextColor(Color.blue);
        txtdire.setDisabledTextColor(Color.blue);
        txtclasecli.setDisabledTextColor(Color.blue);
        txtnomape.setDisabledTextColor(Color.blue);
        numeros();
        auto();
    }
    
    
          void descontarstock(String codi,String can){
              int des = Integer.parseInt(can);
              String cap="";
              int desfinal;
              String consul="SELECT * FROM producto WHERE  cod_pro='"+codi+"'";
              try {
                  Statement st= cn.createStatement();
                  ResultSet rs= st.executeQuery(consul);
                  while(rs.next()){
                      cap= rs.getString(6);}
                  } catch (SQLException e) {
                      JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);}
              
              desfinal=Integer.parseInt(cap)-des;
              String modi="UPDATE producto SET Stock='"+desfinal+"' WHERE cod_pro = '"+codi+"'";
              try {
                  PreparedStatement pst = cn.prepareStatement(modi);
                  pst.executeUpdate();
                  } catch (SQLException e){
                      JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);}
          }
          
          
    private void numeros(){
        String c="";
        String SQL="select max(num_bol) from boleta";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                 c=rs.getString(1);}
            if(c==null){
                txtnumbol.setText("00000001");}
            else{
            int j=Integer.parseInt(c);
            
            GenerarNumero gen= new GenerarNumero();
            gen.generar(j);
             txtnumbol.setText(gen.serie());}
            } catch (SQLException ex) {
           Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);}
    }
    void calcular(){
        String pre;
        String can;
        double igv=0;
        double total=0;
        double subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;
        for(int i=0;i<tbdetbol.getRowCount();i++){
            pre=tbdetbol.getValueAt(i, 2).toString();
            can=tbdetbol.getValueAt(i, 3).toString();
            precio=Double.parseDouble(pre);
            cantidad=Integer.parseInt(can);
            imp=precio*cantidad;
            subtotal=subtotal+imp;
            
            tbdetbol.setValueAt(Math.rint(imp*100)/100, i, 4);}
        txttotal.setText(""+Math.rint(subtotal*100)/100);
    }
    
       private void descuento(){
      DecimalFormatSymbols sb = new DecimalFormatSymbols();
      sb.setDecimalSeparator('.');
      NumberFormat formatter = new DecimalFormat("#0.00",sb);
      String tipocliente=txtclasecli.getText();
      double descuento = 0;   
            
      if (tipocliente.equals("A")) {descuento=0.70;}
        
      if (tipocliente.equals("B")) {descuento=0.75;}
       
      if (tipocliente.equals("C")) {descuento=0.80;}
       
      if (tipocliente.equals("D")) {descuento=0.85;}
       
      if (tipocliente.equals("E")) {descuento=0.90;}
          
      if (tipocliente.equals("G")) {descuento=0.95;}
      
            String pre;
            String can;
            double total=0.00;
            double subtotal=0;
            double precio;
            int cantidadprod;
            double precioventa=0.0;

        
        for(int i=0;i<tbdetbol.getRowCount();i++){
            
            pre=tbdetbol.getValueAt(i, 2).toString();
            can=tbdetbol.getValueAt(i, 3).toString();
            
            precio=Double.parseDouble(pre);
            cantidadprod=Integer.parseInt(can);
            precioventa=(precio*cantidadprod);
            subtotal=(precioventa*descuento);
          double descuentito = (1-descuento)*100;
          tbdetbol.setValueAt(formatter.format(precioventa), i, 4);
          tbdetbol.setValueAt(formatter.format(descuentito) +"%", i, 5);
          tbdetbol.setValueAt(formatter.format(subtotal), i, 6);
            }
        SumarTotal();
       }
        //txttotal.setText(String.valueOf((subtotal)));
        //txtiva.setText(String.valueOf(formatter.format(totaliva)));
        
private void SumarTotal(){
            DecimalFormatSymbols sb = new DecimalFormatSymbols();
            sb.setDecimalSeparator('.');
            NumberFormat formatter = new DecimalFormat("#0.00",sb);
            double total = 0;
            for( int i=0 ; i<tbdetbol.getRowCount() ; i++){
                double numero =0;
                try{
                    numero = Double.parseDouble( tbdetbol.getValueAt(i, 6).toString() );
                }catch (NumberFormatException nfe){
                    numero = 0;
                    tbdetbol.setValueAt(0, i, 6);
                    }
                total += numero;
                }
            txttotal.setText( String.valueOf(formatter.format(total)) );}
    
    
    
    void boleta(){
        String InsertarSQL="INSERT INTO boleta(num_bol,cod_cli,pre_tot,fecha) VALUES (?,?,?,?)";
        String numbol=txtnumbol.getText();
        String codcli=CodCliente.getText();
        String total=txttotal.getText();
        String fecha=txtfecha.getText();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1,numbol);
            pst.setString(2,codcli);
            pst.setString(3,total);
            pst.setString(4,fecha);
           
            int n= pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");}
            } catch (SQLException ex){
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    
    
    void detalleboleta(){
        for(int i=0;i<tbdetbol.getRowCount();i++){
            String InsertarSQL="INSERT INTO detalleboleta(num_bol,cod_pro,des_pro,cant_pro,pre_unit,pre_venta) VALUES (?,?,?,?,?,?)";
            String numbol=txtnumbol.getText();
            String codpro=tbdetbol.getValueAt(i, 0).toString();
            String despro=tbdetbol.getValueAt(i, 1).toString();
            String cantpro=tbdetbol.getValueAt(i, 3).toString();
            String preunit=tbdetbol.getValueAt(i, 2).toString();
            String importe=tbdetbol.getValueAt(i, 4).toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);
                pst.setString(1,numbol);
                pst.setString(2,codpro);
                pst.setString(3,despro);
                pst.setString(4,cantpro);
                pst.setString(5,preunit);
                pst.setString(6,importe);          
                pst.executeUpdate();
                } catch(SQLException ex){
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);}
            }
    }
    private void auto(){
                if(txtclasecli.getText().equals("")){
            btnproductos.setEnabled(false);           
        }
        tbdetbol.getModel().addTableModelListener((TableModelEvent e) -> {
            if( e.getColumn()==-1){
                descuento();}});
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public static String fechaact(){
        Date fecha= new Date();
        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);}
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnumbol = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtnomape = new javax.swing.JTextField();
        txtclasecli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CodCliente = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdire = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnclientes = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnproductos = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdetbol = new javax.swing.JTable();
        txttotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnsalir = new javax.swing.JButton();
        btnven = new javax.swing.JButton();
        btneli = new javax.swing.JButton();

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

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ticket De Venta");

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("AQUI VA EL LOGO XD");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 34, 190, 22);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(209, 42, 0, 14);

        jLabel4.setText("Las Golondrinas");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(177, 86, 90, 16);

        jLabel5.setText("Tel: 9821102482");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(274, 86, 97, 16);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("RFC 10046495581");

        jLabel6.setBackground(new java.awt.Color(51, 51, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ticket de Venta");

        jLabel8.setText("Nº");

        txtnumbol.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumbol, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setEnabled(false);

        txtclasecli.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtclasecli.setEnabled(false);
        txtclasecli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclasecliActionPerformed(evt);
            }
        });

        jLabel10.setText("Tipo:");

        jLabel11.setText("Cod.Cliente");

        CodCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CodCliente.setEnabled(false);

        txtfecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtfecha.setEnabled(false);
        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        jLabel12.setText("Fecha:");

        txtdire.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdire.setEnabled(false);
        txtdire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireActionPerformed(evt);
            }
        });

        jLabel13.setText("Direccion:");

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add User Male_32px.png"))); // NOI18N
        btnclientes.setText("...");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        jLabel14.setText("Productos:");

        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add Shopping Cart_32px.png"))); // NOI18N
        btnproductos.setText("...");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        jLabel15.setText("Señor(a):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtclasecli, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnclientes))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnproductos)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtclasecli, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdetbol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "PRECIO UNITARIO", "CANTIDAD", "PRECIO VENTA", "DESCUENTO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbdetbol);

        jLabel20.setText("Total:");

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Exit_32px.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnven.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Shopping Cart_32px.png"))); // NOI18N
        btnven.setText("REALIZAR VENTA");
        btnven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvenActionPerformed(evt);
            }
        });

        btneli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clear Shopping Cart_32px.png"))); // NOI18N
        btneli.setText("ELIMINAR");
        btneli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnven, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnven)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtfechaActionPerformed

private void txtdireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtdireActionPerformed

private void txtclasecliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclasecliActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtclasecliActionPerformed

private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
// TODO add your handling code here:
    SS.Clic();
    EnviarClientesTicket cli = new EnviarClientesTicket();
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
          EnviarProductosTickets pro= new EnviarProductosTickets();
          
          Principal.jdpescritorio.add(pro);          
          pro.toFront();    
          SS.EfectoPopUp();
          pro.setVisible(true);  
          } catch (Exception e) {
              
          }
}//GEN-LAST:event_btnproductosActionPerformed

private void btnvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvenActionPerformed
// TODO add your handling code here:
SS.Clic();
    if((CodCliente.getText().equals(""))||(txttotal.getText().equals(""))){
        JOptionPane.showMessageDialog(this, "Ingrese cliente, producto o realice operacion");
    }else{
        String capcod="",capcan="";
        for(int i=0;i<Ticket.tbdetbol.getRowCount();i++){
            capcod=Ticket.tbdetbol.getValueAt(i, 0).toString();
            capcan=Ticket.tbdetbol.getValueAt(i, 3).toString();
            descontarstock(capcod, capcan);
            }
    
    boleta();
    detalleboleta();
    CodCliente.setText("");
    txtnomape.setText("");
    txtclasecli.setText("");
    txtdire.setText("");
    txttotal.setText("");
    btnproductos.setEnabled(false);
    
    DefaultTableModel modelo = (DefaultTableModel) tbdetbol.getModel();
    int a =tbdetbol.getRowCount()-1;
    int i;
    for(i=a;i>=0;i--){
        modelo.removeRow(i);}
        numeros();
    }
    
}//GEN-LAST:event_btnvenActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
SS.Clic();
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btneliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliActionPerformed
// TODO add your handling code here:
SS.Clic();
    DefaultTableModel model = (DefaultTableModel) tbdetbol.getModel();
    int fila = tbdetbol.getSelectedRow();
    if(fila>=0){
        model.removeRow(fila);}
    else{
        JOptionPane.showMessageDialog(null, "Tabla vacia o no seleccione ninguna fila");
    }
    
}//GEN-LAST:event_btneliActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField CodCliente;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneli;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnven;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable tbdetbol;
    public static javax.swing.JTextField txtclasecli;
    public static javax.swing.JTextField txtdire;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtnomape;
    private javax.swing.JTextField txtnumbol;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
    Sonidos SS= new Sonidos();
    Conexion cc= new Conexion();
    Connection cn = cc.conexion();
}
