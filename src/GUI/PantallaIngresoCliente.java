/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Negocio.*;
import DTO.*;
import javax.swing.JOptionPane;

 
/**
 *
 * @author Grupo2
 */
public class PantallaIngresoCliente extends javax.swing.JFrame {

    /**
     * Creates new form PantallaIngresoCliente
     */
    public PantallaIngresoCliente() {
        initComponents();
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
        txtRut = new javax.swing.JTextField();
        btoGrabar = new javax.swing.JButton();
        btoSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Rut");

        jLabel2.setText("Nombre");

        btoGrabar.setText("Grabar");
        btoGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoGrabarActionPerformed(evt);
            }
        });

        btoSalir.setText("Salir");
        btoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btoGrabar)
                        .addGap(102, 102, 102)
                        .addComponent(btoSalir)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btoGrabar)
                    .addComponent(btoSalir))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.gc();
    }//GEN-LAST:event_btoSalirActionPerformed

    private void btoGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoGrabarActionPerformed
        // TODO add your handling code here:
        
        try
        {
        
           Cliente auxCliente = new Cliente();
           auxCliente.setRut(this.txtRut.getText());
           auxCliente.setNombre(this.txtNombre.getText());
        
           Negocio auxNegocio = new Negocio();
           auxNegocio.ingresaCliente(auxCliente);
           JOptionPane.showMessageDialog(null, "Datos Grabados ");
        }
        catch(Exception ex)
        {
          JOptionPane.showMessageDialog(null, "Datos no grabados " + ex.getMessage());
        }
    }//GEN-LAST:event_btoGrabarActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btoGrabar;
    private javax.swing.JButton btoSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
