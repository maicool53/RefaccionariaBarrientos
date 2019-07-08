
/*
 * IngresoProductos.java
 *
 * @author Miguel
 */
package Formulario;

import Clases.GenerarCodigos;
import Clases.Conexion;
import static Formulario.Principal.jdpescritorio;
import java.awt.Dimension;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Miguel
 */
public class IngresoProductos extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoProductos
     */
    public IngresoProductos() {

        initComponents();
        this.setLocation(150, 15);
        
        bloquear();
        cargar("");
        llenarcombofamilias();
        llenarcomboprovedor();
    }
    
     void bloquear(){
    txtcod.setEnabled(false);
    txtprod.setEnabled(false);
    txtprecom.setEnabled(false);
    txtstock.setEnabled(false);
    botonguardar.setEnabled(false);
    btnnuevo.setEnabled(true);
    btncancelar.setEnabled(false);
    btnactualizar.setEnabled(false);
    txtdmarca.setEnabled(false);
    txtprevent.setEnabled(false);
    ComboEstante.setEnabled(false);
    ComboRepisa.setEnabled(false);
    ComboFamilia.setEnabled(false);
    btngenerarcodbar.setEnabled(false);
    ComboProvedor.setEnabled(false);
    txtprov.setEnabled(false);
    }

    void limpiar(){
    txtcod.setText("");
    txtprod.setText("");
    txtdmarca.setText("");
    txtprecom.setText("");
    txtprevent.setText("");
    txtstock.setText("");
    txtprov.setText("");
    ProvedorNombre.setText("");
    ComboFamilia.setSelectedItem("Seleccione una opcion");
    ComboEstante.setSelectedItem("Seleccione una opcion");
    ComboRepisa.setSelectedItem("Seleccione una opcion");
    ComboProvedor.setSelectedItem("Seleccione una opcion");}

    void desbloquear(){
    txtcod.setEnabled(true);
    txtprod.setEnabled(true);
    txtprecom.setEnabled(true);
    txtstock.setEnabled(true);
    botonguardar.setEnabled(true);
    btnnuevo.setEnabled(false);
    btncancelar.setEnabled(true);
    txtdmarca.setEnabled(true);
    ComboEstante.setEnabled(true);
    ComboRepisa.setEnabled(true);
    ComboFamilia.setEnabled(true);
    btngenerarcodbar.setEnabled(true);
    ComboProvedor.setEnabled(true);
    txtprov.setEnabled(true);
    }

    void cargar(String valor) {
        try{
            String [] titulos={"Codigo","Descripcion","Marca","Precio Compra","Precio Venta","Stock","Estante","Repisa","Familia","Provedor","Codigo Barras Provedor"};
            String [] registros= new String[11];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from producto WHERE CONCAT (descripcion,'',precio) LIKE '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(3);
                registros[3]=rs.getString(4);
                registros[4]=rs.getString(5);
                registros[5]=rs.getString(6);
                registros[6]=rs.getString(7);
                registros[7]=rs.getString(8);
                registros[8]=rs.getString(9);
                registros[9]=rs.getString(10);
                registros[10]=rs.getString(11);
                
                model.addRow(registros);
            }

            tbproductos.setModel(model);
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(5).setPreferredWidth(70);
            tbproductos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbproductos.getColumnModel().getColumn(7).setPreferredWidth(80);
            tbproductos.getColumnModel().getColumn(8).setPreferredWidth(80);
            tbproductos.getColumnModel().getColumn(9).setPreferredWidth(100);
            }
        catch(Exception e){
                System.out.println(e.getMessage());
                 }
        
        
        
     
        }

        void BuscarProductoEditar(String cod) {
        
        try{
           
            String codi="",desc="",marca="",prec="",preven="",stock="",estante="",repisa="",familia="",provedor="",codbar_prov="";
            String cons="select * from producto WHERE cod_pro='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next())
            {
                codi=rs.getString(1);
                desc=rs.getString(2);
                marca=rs.getString(3);
                prec=rs.getString(4);
                preven=rs.getString(5);
                stock=rs.getString(6);
                estante=rs.getString(7);
                repisa=rs.getString(8);
                familia=rs.getString(9);
                provedor=rs.getString(10);
                codbar_prov=rs.getString(11);
            }
            txtcod.setText(codi);
            txtprod.setText(desc);
            txtdmarca.setText(marca);
            txtprecom.setText(prec);
            txtprevent.setText(preven);
            txtstock.setText(stock);
            ComboEstante.setSelectedItem(estante);
            ComboRepisa.setSelectedItem(repisa);
            ComboFamilia.setSelectedItem(familia);
            ComboProvedor.setSelectedItem(provedor);
            txtprov.setText(codbar_prov);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

        void codigos(){

        int j;
        int cont=1;
        String num="";
        String c="";
        String SQL="select max(cod_pro) from producto";
        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                txtcod.setText("CP0001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;
                j = Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                gen.generar(j);
                txtcod.setText("CP" + gen.serie());

           }

        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void verprov(){
    System.out.println("si se ejecuta");
    try {
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery("Select nom_prov from provedor where cod_prov= '"+ComboProvedor.getSelectedItem().toString()+"'");
        while(rs.next()){
            ProvedorNombre.setText(rs.getString("nom_prov"));}}
    catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null,ex);
        }
    //;
    }
    

    private void llenarcombofamilias(){
            
        try {
        ComboFamilia.removeAllItems();
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery("Select * from Familias");
        ComboFamilia.addItem("Seleccione una opcion");    
        
        while(rs.next()){
            ComboFamilia.addItem(rs.getString("Familia"));}}
           
           catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null,ex);
        }

    }
        
    private void llenarcomboprovedor(){
            
        try {
        ComboProvedor.removeAllItems();
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery("Select cod_prov from provedor order by cod_prov asc");
        ComboProvedor.addItem("Seleccione una opcion");    
        
        while(rs.next()){
            ComboProvedor.addItem(rs.getString("cod_prov"));
           
            }
        }
           catch (SQLException ex) {
            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null,ex);
        }}
               
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnactualizar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtprod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtprecom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtprevent = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdmarca = new javax.swing.JTextField();
        ComboEstante = new javax.swing.JComboBox<>();
        ComboRepisa = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ComboFamilia = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ComboProvedor = new javax.swing.JComboBox<>();
        txtprov = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ProvedorNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        botonguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btngenerarcodbar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

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

        mnactualizar.setText("Modificar");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnactualizar);

        mneliminar.setText("Eliminar");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE PRODUCTOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Producto"));

        jLabel1.setText("Codigo de barra:");

        txtcod.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodKeyTyped(evt);
            }
        });

        jLabel2.setText("Producto:");

        txtprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprodActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio compra:");

        txtprecom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecomActionPerformed(evt);
            }
        });
        txtprecom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecomKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecomKeyTyped(evt);
            }
        });

        jLabel5.setText("Stock:");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        jLabel6.setText("Precio Venta:");

        txtprevent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreventActionPerformed(evt);
            }
        });

        jLabel7.setText("Marca:");

        txtdmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdmarcaActionPerformed(evt);
            }
        });

        ComboEstante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        ComboRepisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel8.setText("Estante:");

        jLabel9.setText("Repisa:");

        ComboFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboFamiliaActionPerformed(evt);
            }
        });

        jLabel10.setText("Familia:");

        jLabel11.setText("Provedor:");

        ComboProvedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboProvedorItemStateChanged(evt);
            }
        });

        txtprov.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprovActionPerformed(evt);
            }
        });

        jLabel12.setText("Codigo Provedor:");

        ProvedorNombre.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3)))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprecom, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdmarca)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(23, 23, 23)
                                .addComponent(txtprevent, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(64, 64, 64)
                                .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(3, 3, 3)
                                .addComponent(txtprov))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(47, 47, 47)
                                .addComponent(txtprod)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboProvedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ProvedorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(ComboFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(ComboEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addComponent(ComboRepisa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1))
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12))
                            .addComponent(txtprov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel2))
                            .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtdmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(txtprecom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(ComboFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ComboEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(ComboRepisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(5, 5, 5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ComboProvedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ProvedorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtprevent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        botonguardar.setText("Grabar");
        botonguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btngenerarcodbar.setText("Crear Codigo De Barras");
        btngenerarcodbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarcodbarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonguardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btngenerarcodbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btngenerarcodbar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproductos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbproductos);

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

        jButton1.setText("Mostrar Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, 0, 943, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton1))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
// TODO add your handling code here:
    txtcod.transferFocus();
}//GEN-LAST:event_txtcodActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    desbloquear();
    limpiar();
    txtcod.requestFocus();
    codigos();
    verprov();
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    limpiar();
    bloquear();
}//GEN-LAST:event_btncancelarActionPerformed

private void txtprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprodActionPerformed
// TODO add your handling code here:
    txtprod.transferFocus();
}//GEN-LAST:event_txtprodActionPerformed

private void txtprecomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecomActionPerformed
// TODO add your handling code here:

    txtprecom.transferFocus();
}//GEN-LAST:event_txtprecomActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    cargar("");
}//GEN-LAST:event_jButton1ActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    cargar(txtbuscar.getText());
}//GEN-LAST:event_txtbuscarKeyReleased

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:
    int filasel = tbproductos.getSelectedRow();
    int opt=JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   if (opt==0){
    try {
        if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione algun dato");
        } else {
            String cod = (String) tbproductos.getValueAt(filasel, 0);
            String eliminarSQL = "DELETE FROM producto WHERE cod_pro = '" + cod + "'";
            try {
                PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Borrado");
                cargar("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    } catch (Exception e) {
    }}
}//GEN-LAST:event_mneliminarActionPerformed

private void botonguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonguardarActionPerformed
// TODO add your handling code here:
    String cod,des,marca,pre,preven,stock,stante,repisa,familia,cod_prov,codbar_prov;
            String sql="";
            cod=txtcod.getText();
            des=txtprod.getText();
            marca=txtdmarca.getText();
            pre=txtprecom.getText();
            preven=txtprevent.getText();
            stock=txtstock.getText();
            stante=ComboEstante.getSelectedItem().toString();
            repisa=ComboRepisa.getSelectedItem().toString();
            familia=ComboFamilia.getSelectedItem().toString();
            cod_prov=ComboProvedor.getSelectedItem().toString();
            codbar_prov=txtprov.getText();
            
           sql="INSERT INTO producto (cod_pro,descripcion,marca,precio,precio_venta,Stock,estante,repisa,familia,cod_prov,codbar_prov)"
                   + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
       try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, cod);
            pst.setString(2, des);
            pst.setString(3, marca);
            pst.setString(4, pre);
            pst.setString(5, preven);
            pst.setString(6, stock);
            pst.setString(7, stante);
            pst.setString(8, repisa);
            pst.setString(9, familia);
            pst.setString(10, cod_prov);
            pst.setString(11, codbar_prov);
            
            int n=pst.executeUpdate();
            if(n>0){

            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
            bloquear();
        }
        cargar("");
    } catch (SQLException ex) {
        Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_botonguardarActionPerformed

private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
// TODO add your handling code here:

    try {
        int filaMod = tbproductos.getSelectedRow();
        if (filaMod == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
        } else {

            btnactualizar.setEnabled(true);
            botonguardar.setEnabled(false);
            String cod = (String) tbproductos.getValueAt(filaMod, 0);
            desbloquear();
            BuscarProductoEditar(cod);
        }
    } catch (Exception e) {
    }
     verprov();
}//GEN-LAST:event_mnactualizarActionPerformed

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
// TODO add your handling code here:
     String sql="UPDATE producto SET precio = '"+txtprecom.getText()
             +"',descripcion ='"+txtprod.getText()
             +"',marca ='"+ txtdmarca.getText()
             +"',precio_venta ='"+txtprevent.getText()
             +"',Stock = '"+txtstock.getText()
             +"',estante='"+ComboEstante.getSelectedItem()
             +"',repisa='"+ComboRepisa.getSelectedItem()
             +"',familia='"+ComboFamilia.getSelectedItem()
             +"',cod_prov='"+ComboProvedor.getSelectedItem()
             +"',codbar_prov='"+txtprov.getText()
             +"' WHERE cod_pro = '"+txtcod.getText()+"'";
    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Actualizado");
        cargar("");
        bloquear();
        limpiar();
        verprov();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}//GEN-LAST:event_btnactualizarActionPerformed

private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtstockActionPerformed

    private void txtpreventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpreventActionPerformed

    private void txtdmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdmarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdmarcaActionPerformed

    private void txtprecomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecomKeyReleased
        double preciocompra = Double.parseDouble(txtprecom.getText());
        double precioventa =  (preciocompra*2); 
        String cadena = String.valueOf(precioventa);
            if(txtprecom.getText().equals("")){
            txtprevent.setText("");}
            else {
                txtprevent.setText(cadena); 
            }
        
    }//GEN-LAST:event_txtprecomKeyReleased

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtprecomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecomKeyTyped
    char car2 = evt.getKeyChar();
    if((car2<'0' || car2>'9')) evt.consume();       
    // TODO add your handling code here:
    }//GEN-LAST:event_txtprecomKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
 char car = evt.getKeyChar();
    if((car<'0' || car>'9')) evt.consume();         // TODO add your handling code here:
    }//GEN-LAST:event_txtstockKeyTyped

    private void btngenerarcodbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarcodbarActionPerformed
        // TODO add your handling code here:
        CrearBarras frm = new CrearBarras();        
        Principal.jdpescritorio.add(frm);
        frm.toFront();
        Dimension desktopSize = jdpescritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        frm.txtcodigodebarras.setText(txtcod.getText());
        
    }//GEN-LAST:event_btngenerarcodbarActionPerformed

    private void txtcodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodKeyTyped

    private void txtprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprovActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprovActionPerformed

    private void ComboFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboFamiliaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboFamiliaActionPerformed

    private void ComboProvedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboProvedorItemStateChanged
        // TODO add your handling code here: 
        verprov();
    }//GEN-LAST:event_ComboProvedorItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboEstante;
    private javax.swing.JComboBox<String> ComboFamilia;
    private javax.swing.JComboBox<String> ComboProvedor;
    private javax.swing.JComboBox<String> ComboRepisa;
    private javax.swing.JTextField ProvedorNombre;
    private javax.swing.JButton botonguardar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btngenerarcodbar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem mnactualizar;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdmarca;
    private javax.swing.JTextField txtprecom;
    private javax.swing.JTextField txtprevent;
    private javax.swing.JTextField txtprod;
    private javax.swing.JTextField txtprov;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
   Conexion cc = new Conexion();
   Connection cn = cc.conexion();

}
