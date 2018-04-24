/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntodeventa;

import clases.conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class Vender extends javax.swing.JFrame {
    conectar con = new conectar();
    String sql="";
    float suma= 0, rest=0;

    /**
     * Creates new form Vende
     */
    public Vender() {
        initComponents();
        txtPrecio.setEnabled(false);
    }
    
    private void Limpiar(){
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }
    
    private void confirmacionCompra(String exp){
        float resta= Float.parseFloat(txtCantidad.getText()), cantidad=0;
        String cantidad1;
        cantidad = rest - resta;
        cantidad1 = Float.toString(cantidad);
        
        int reply = JOptionPane.showConfirmDialog(null, "¿Desea agregar otra compra?", "Atención!", JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
            cantidad = rest - resta;//se manda al update para quitar la cantidad de productos 
            cantidad1 = Float.toString(cantidad);
            
            try{
                PreparedStatement us = con.getConnection().prepareStatement("UPDATE productos SET cantidad = ? WHERE nombre='" + exp+ "'");
                us.setString(1,cantidad1);
                int rs = us.executeUpdate();
                if(rs>0){
                    Limpiar();
                    btnBuscar.setEnabled(true);
                    
                } 
                } catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
            } 
            
            
        }else {
            
            try{
                PreparedStatement us = con.getConnection().prepareStatement("UPDATE productos SET cantidad = ? WHERE nombre='" + exp+ "'");
                us.setString(1,cantidad1);
                int rs = us.executeUpdate();
                if(rs>0){
                    JOptionPane.showMessageDialog(null,"Compra exitosa.","Gracias por comprar!", JOptionPane.INFORMATION_MESSAGE);
                    dispose ();
                    Sistema sistema = new Sistema ();
                    sistema.setVisible (true);
                    sistema.setLocationRelativeTo (null);
                    sistema.setResizable (false);
                    
                } 
                } catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
            }            
            
        }
    }
    
     private void buscar(String valor){
        //Se definen el nombre de las columnas de la tabla y se agregan un modelo
       /* DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id_producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Costo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Codigo producto");
        
        //Se le asigna el modelo anterior al al jTable1
        jTable2.setModel(modelo);*/
       
        
        //Si la caja de busqueda esta vacio, se mostraran todos los registos, si no
        //se ira haciendo la busqueda mediante los digitos que el usuario va agregando
        if (valor.equals("")) {
            sql="SELECT * FROM productos";
        }else{
            sql="SELECT * FROM productos  WHERE nombre like '"+valor+"'";
            
        }
        //Se define un arreglo para guardar los datos de la consulta
        
        try {
            PreparedStatement us = con.getConnection().prepareStatement(sql);
            ResultSet rs = us.executeQuery();//Almacena los datos de la consulta que se va a realizar
            //Se asignan los valores de la busqueda en una posicion del arreglo
            while( rs.next()) {
                //txtNombre.setText(rs.getString(2));
                txtPrecio.setText(rs.getString(3));
                rest = Float.parseFloat(rs.getString(4));
                //txtDescripcion.setText(rs.getString(5));
                //txtCodigo.setText(rs.getString(6));
                //modelo.addRow(datos);
            }
            //jTable2.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Existe(String exp) {
        try{
            PreparedStatement us = con.getConnection().prepareStatement("SELECT nombre FROM productos WHERE nombre = '" + exp+ "'");
            ResultSet rs = us.executeQuery();
            
            
            if(rs.next()){
                buscar(txtNombre.getText());
                
            } else
                JOptionPane.showMessageDialog(null, "NO existe el producto. ","ERROR",JOptionPane.ERROR_MESSAGE);
                rs.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vender");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENDER");

        jLabel2.setText("Nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel6.setText("Precio: $");

        jLabel7.setText("Cantidad:");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Precio a pagar: $");

        lblPrecio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPrecio.setText("0.0");

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecio)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(btnBuscar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnRegresar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnComprar)
                                .addGap(63, 63, 63)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComprar)
                    .addComponent(btnRegresar))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        if(txtNombre.getText().equals("")|| txtCantidad.getText().equals("")|| txtCantidad.getText().equals("0")){
            JOptionPane.showMessageDialog(null,"El campos estan vacios.","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }else {
            Existe(txtNombre.getText());
                   
        }
        suma=(Float.parseFloat(txtPrecio.getText())+ suma)*Float.parseFloat(txtCantidad.getText());
        lblPrecio.setText(Float.toString(suma));
        btnBuscar.setEnabled(false);
        
 
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
        if (txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"No hay cantidad o precio.","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        } else{ 
            confirmacionCompra(txtNombre.getText());
        }
    }//GEN-LAST:event_btnComprarActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        dispose();
        Sistema sistema = new Sistema();
        sistema.setVisible(true);
        sistema.setLocationRelativeTo(null);
        sistema.setResizable(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
                new Vender().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
