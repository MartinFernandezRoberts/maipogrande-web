/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Negocio.*;
import DTO.*;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Grupo2
 */
public class PantallaConsultaCliente extends javax.swing.JFrame {

    /**
     * Creates new form PantallaConsultaCliente
     */
    public PantallaConsultaCliente() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaCliente = new javax.swing.JTextArea();
        btoMostrar = new javax.swing.JButton();
        btoSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textAreaCliente.setColumns(20);
        textAreaCliente.setRows(5);
        jScrollPane1.setViewportView(textAreaCliente);

        btoMostrar.setText("Mostrar");
        btoMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoMostrarActionPerformed(evt);
            }
        });

        btoSalir.setText("Salir");
        btoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoSalirActionPerformed(evt);
            }
        });

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Rut", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btoMostrar)
                .addGap(70, 70, 70)
                .addComponent(btoSalir)
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btoMostrar)
                            .addComponent(btoSalir))))
                .addContainerGap(281, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.gc();
        
    }//GEN-LAST:event_btoSalirActionPerformed

    private void btoMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoMostrarActionPerformed
        // TODO add your handling code here:
        
        Negocio auxNegocio = new Negocio();
        
        Iterator iter = auxNegocio.retornaCliente().iterator();
        
        this.textAreaCliente.setText("");

        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo = (DefaultTableModel) this.jTableCliente.getModel();
        modelo.setRowCount(0);
        int fila = 0;
        
        while (iter.hasNext())
        {
           Object[] num = {};
           modelo.addRow(num);
           Cliente auxCliente = new Cliente();
           auxCliente = (Cliente) iter.next();
           this.textAreaCliente.append(auxCliente.getRut() + " "
                                       + auxCliente.getNombre() + "\n");
           this.jTableCliente.setValueAt(auxCliente.getRut(), fila, 0);
           this.jTableCliente.setValueAt(auxCliente.getNombre(), fila, 1);
           fila++;
        
        }
        
        
        
        
    }//GEN-LAST:event_btoMostrarActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btoMostrar;
    private javax.swing.JButton btoSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextArea textAreaCliente;
    // End of variables declaration//GEN-END:variables
}
