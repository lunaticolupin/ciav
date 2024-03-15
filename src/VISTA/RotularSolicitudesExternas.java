/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import MODELO.AudienciaDAO;
import MODELO.SolicitudExternaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import MODELO.Conexion;

/**
 *
 * @author recepcion
 */
public class RotularSolicitudesExternas extends javax.swing.JFrame {
SolicitudExternaDAO modeloSolicitudE = new SolicitudExternaDAO();
AudienciaDAO modeloAudiencia = new AudienciaDAO();
Conexion con=new Conexion();
//Registrar vista = new Registrar();
    /**
     * Creates new form RotularSolicitudesExternas
     */
    public RotularSolicitudesExternas() {
        initComponents();
           lblDefensa.setVisible(false);
        lblFiscal.setVisible(false);
       lblImputado.setVisible(false);
       txt_consolidacion.setVisible(false);
    }
    
   public void mostrarAudiencias(JTable tabla, int solicitud)
   {
               DefaultTableModel modelo = new DefaultTableModel();

            try{
                    String sql ="";
                            sql = "SELECT audiencia.FECHA, audiencia.HORA,audiencia.SALA,audiencia.CAUSA,disco.NUMERO,disco.ANIO FROM sol_dis "
                + "INNER JOIN solicitud_externa ON sol_dis.idSolicitud = solicitud_externa.ID "
                + "INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE solicitud_externa.ID = '"+solicitud+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
            modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("NUMERO");
        modelo.addColumn("AÑO");
        tabla.setModel(modelo);
                    while(rs.next())
                    {
                        Object datos[]=new Object[cantidadcolumnas];
                        for(int i =0; i<cantidadcolumnas;i++ )
                        {
                            datos[i]=rs.getObject(i+1);
                        }
                        modelo.addRow(datos);
                    }
                    rs.close();
                    int numRegistro=modelo.getRowCount();
                    if(numRegistro>0)
                    {
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                       JOptionPane.showMessageDialog(null, "No se encontro ninguna audiencia con ese folio"); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
       /* tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    txtFecha.setText(tabla.getValueAt(fila, 0).toString());
                    txtHora.setText(tabla.getValueAt(fila, 1).toString());
                    txtSala.setText(tabla.getValueAt(fila, 2).toString());
                    txtCausa.setText(tabla.getValueAt(fila, 3).toString());
                   
                    String numero = tabla.getValueAt(fila,4).toString();
                    String anio = tabla.getValueAt(fila, 5).toString();
                    String disco = numero+"-"+anio;
                    txtDisco.setText(disco);
                    String fecha =txtFecha.getText();
                    String hora = txtHora.getText();
                    String sala = txtSala.getText();
                    int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    
                    //obtiene el numero de participantes dependiendo que tipo departicipante es
                    int numImputados = modeloSolicitudE.lsImputados(audiencia).size();
                    int numFiscales = modeloSolicitudE.lsFiscales(audiencia).size();
                    int numDefensas = modeloSolicitudE.lsDefensas(audiencia).size();
                    
                    String imputado = modeloSolicitudE.imputado(audiencia);
                    String defensa = modeloSolicitudE.defensa(audiencia);
                    String fiscal = modeloSolicitudE.fiscal(audiencia);
                    System.out.print(imputado);
                    System.out.print(defensa);
                    System.out.print(fiscal);
                    
                    if (numDefensas > 1){
                        lblDefensa.setText(defensa +" "+"Y OTROS");
                    }else{
                        lblDefensa.setText(defensa);
                    }
                    
                    if (numFiscales > 1){
                        lblFiscal.setText(fiscal +" "+"Y OTROS");
                    }else{
                        lblFiscal.setText(fiscal);
                    }
                    
                    if (numImputados > 1){
                        lblImputado.setText(imputado +" "+"Y OTROS");
                    }else{
                        lblImputado.setText(imputado);
                    }
  
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); */
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCausa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDisco = new javax.swing.JTextField();
        lblImputado = new javax.swing.JLabel();
        lblDefensa = new javax.swing.JLabel();
        lblFiscal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSala = new javax.swing.JTextField();
        txt_consolidacion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnRotular = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Taudiencias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rotular solicitudes externas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 16))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("FECHA AUDIENCIA:");

        txtFecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("HORA AUDIENCIA:");

        txtHora.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("CAUSA");

        txtCausa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("DISCO");

        txtDisco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblImputado.setText("imputado");

        lblDefensa.setText("defensa");

        lblFiscal.setText("fiscal");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setText("SALA");

        txtSala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaActionPerformed(evt);
            }
        });

        txt_consolidacion.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblImputado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(lblFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_consolidacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCausa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImputado)
                    .addComponent(lblFiscal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDefensa)
                    .addComponent(txt_consolidacion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText("Introduzca el folio de la solicitud");

        txtFolio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar audiencias");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnRotular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/rotular.png"))); // NOI18N
        btnRotular.setToolTipText("Rotular disco actual");
        btnRotular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotularActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRotular, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(btnRotular, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        Taudiencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Taudiencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaudienciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Taudiencias);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaActionPerformed

    private void btnRotularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotularActionPerformed

    }//GEN-LAST:event_btnRotularActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int folio = Integer.parseInt(txtFolio.getText());
            mostrarAudiencias(Taudiencias,folio); 
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
 txtCausa.setText("");
        txtDisco.setText("");
            txtFecha.setText("");
            txtFolio.setText("");
            txtHora.setText("");
            txtSala.setText("");
            lblDefensa.setText("");
            lblFiscal.setText("");
            lblImputado.setText("");
            DefaultTableModel modelo = (DefaultTableModel)Taudiencias.getModel();
            modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void TaudienciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaudienciasMouseClicked
              if (Taudiencias.getSelectedRow() != -1){
                    int fila = Taudiencias.getSelectedRow();
                    txtFecha.setText(Taudiencias.getValueAt(fila, 0).toString());
                    txtHora.setText(Taudiencias.getValueAt(fila, 1).toString());
                    txtSala.setText(Taudiencias.getValueAt(fila, 2).toString());
                    txtCausa.setText(Taudiencias.getValueAt(fila, 3).toString());
                   
                    String numero = Taudiencias.getValueAt(fila,4).toString();
                    String anio = Taudiencias.getValueAt(fila, 5).toString();
                    String disco = numero+"-"+anio;
                    txtDisco.setText(disco);
                    String fecha =txtFecha.getText();
                    String hora = txtHora.getText();
                    String sala = txtSala.getText();
                    int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    
                    int consolidacion= modeloAudiencia.extraConsolidacion(audiencia);
                    txt_consolidacion.setText(String.valueOf(consolidacion));
                    //obtiene el numero de participantes dependiendo que tipo departicipante es
                    int numImputados = modeloSolicitudE.lsImputados(audiencia).size();
                    int numFiscales = modeloSolicitudE.lsFiscales(audiencia).size();
                    int numDefensas = modeloSolicitudE.lsDefensas(audiencia).size();
                    
                    String imputado = modeloSolicitudE.imputado(audiencia);
                    String defensa = modeloSolicitudE.defensa(audiencia);
                    String fiscal = modeloSolicitudE.fiscal(audiencia);
                    System.out.print(imputado);
                    System.out.print(defensa);
                    System.out.print(fiscal);
                    
                    if (numDefensas > 1){
                        lblDefensa.setText(defensa +" "+"Y OTROS");
                    }else{
                        lblDefensa.setText(defensa);
                    }
                    
                    if (numFiscales > 1){
                        lblFiscal.setText(fiscal +" "+"Y OTROS");
                    }else{
                        lblFiscal.setText(fiscal);
                    }
                    
                    if (numImputados > 1){
                        lblImputado.setText(imputado +" "+"Y OTROS");
                    }else{
                        lblImputado.setText(imputado);
                    }
  
                }
    }//GEN-LAST:event_TaudienciasMouseClicked

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
            java.util.logging.Logger.getLogger(RotularSolicitudesExternas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RotularSolicitudesExternas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RotularSolicitudesExternas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RotularSolicitudesExternas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RotularSolicitudesExternas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable Taudiencias;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRotular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblDefensa;
    public javax.swing.JLabel lblFiscal;
    public javax.swing.JLabel lblImputado;
    public javax.swing.JTextField txtCausa;
    public javax.swing.JTextField txtDisco;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtFolio;
    public javax.swing.JTextField txtHora;
    public javax.swing.JTextField txtSala;
    public javax.swing.JLabel txt_consolidacion;
    // End of variables declaration//GEN-END:variables
}
