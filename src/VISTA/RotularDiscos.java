/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import MODELO.AudienciaDAO;
import MODELO.Conexion;
import MODELO.DiscoDAO;
import MODELO.ParticipanteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author recepcion
 */
public class RotularDiscos extends javax.swing.JFrame {

    /**
     * Creates new form RotularDiscos
     */
    AudienciaDAO modeloAudiencia = new AudienciaDAO();
    ParticipanteDAO modeloParticipante = new ParticipanteDAO();
    DiscoDAO modeloDisco = new DiscoDAO();
     Conexion  con;
    public RotularDiscos() {
        initComponents();
        con = new Conexion();
        consolidacion_txt.setVisible(false);
        btnRotular.setVisible(false);
        btnrotularVarios.setToolTipText("ROTULAR DISCO");
        
    }
    public void llenarTablaAudienciasRotular(JTable tabla,String fechaInicial, String fechaFinal){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("TIPO AUDIENCIA");
        modelo.addColumn("CONSOLIDACION");
        tabla.setModel(modelo);*/
        DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
        
        try{
       String sql="SELECT audiencia.FECHA, audiencia.HORA, audiencia.SALA, audiencia.CAUSA,"
            + " catalogo_audiencia.NOMBRE, audiencia.CONSOLIDACION,audiencia.ESTADOROTULACION "
            + "FROM audiencia INNER JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID"
            + " WHERE audiencia.FECHA BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "' "
            + "AND (audiencia.ESTADO='CELEBRADA') ORDER BY FECHA,HORA,SALA";
        Connection accesoBD = con.conectar();
         PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
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
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }

  
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
       /* tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String fecha = tabla.getValueAt(fila, 0).toString();
                    String hora = tabla.getValueAt(fila, 1).toString();
                    String sala = tabla.getValueAt(fila, 2).toString();
                    String causa = tabla.getValueAt(fila, 3).toString();
                    lblFecha.setText(fecha);
                    lblHora.setText(hora);
                    lblSala.setText(sala);
                    lblCausa.setText(causa);
                    int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    System.out.println("IdAudiencia: "+idAudiencia);
                    int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                    int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                    String disco = numDisco +"-"+anioDisco;
                    lblDisco.setText(disco);
                    //Obtiene el numero de participantes sdegun el tipo
                    int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                    int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                    int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();
                    
                    String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                    String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                    String imputado = modeloParticipante.imputadoOriginal(idAudiencia);
                    
                    
                    if (numDefensas > 1){
                        lblDefensa.setText(defensa +" "+"Y OTROS");
                    }
                    else {
                        lblDefensa.setText(defensa);
                    }
                    if (numFiscales > 1){
                        lblFiscal.setText(fiscal+" "+"Y OTROS");
                    }
                    else {
                        lblFiscal.setText(fiscal);
                    }
                    if (numImputados > 1){
                        lblImputado.setText(imputado +" "+"Y OTROS");
                    } 
                    else{
                        lblImputado.setText(imputado);
                    }
                    
                }
                
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
        
    }
    
    
    public void llenarTablaAudienciasRotularUsuario(JTable tabla,String fechaInicial, String fechaFinal){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("TIPO AUDIENCIA");
        modelo.addColumn("CONSOLIDACION");
        tabla.setModel(modelo);*/
        String usuario = (String) usuarioscombo.getSelectedItem();
        DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
        
        try{
       String sql="SELECT audiencia.FECHA, audiencia.HORA, audiencia.SALA, audiencia.CAUSA,"
            + " catalogo_audiencia.NOMBRE, audiencia.CONSOLIDACION,audiencia.ESTADOROTULACION "
            + "FROM audiencia INNER JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID INNER JOIN usuarios ON usuarios.id_usuario=audiencia.usuario_id"
            + " WHERE usuarios.Nombre='"+usuario+"' AND audiencia.FECHA BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "' "
            + "AND (audiencia.ESTADO='CELEBRADA') ORDER BY FECHA,HORA,SALA";
        Connection accesoBD = con.conectar();
         PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
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
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }

  
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
       /* tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String fecha = tabla.getValueAt(fila, 0).toString();
                    String hora = tabla.getValueAt(fila, 1).toString();
                    String sala = tabla.getValueAt(fila, 2).toString();
                    String causa = tabla.getValueAt(fila, 3).toString();
                    lblFecha.setText(fecha);
                    lblHora.setText(hora);
                    lblSala.setText(sala);
                    lblCausa.setText(causa);
                    int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    System.out.println("IdAudiencia: "+idAudiencia);
                    int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                    int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                    String disco = numDisco +"-"+anioDisco;
                    lblDisco.setText(disco);
                    //Obtiene el numero de participantes sdegun el tipo
                    int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                    int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                    int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();
                    
                    String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                    String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                    String imputado = modeloParticipante.imputadoOriginal(idAudiencia);
                    
                    
                    if (numDefensas > 1){
                        lblDefensa.setText(defensa +" "+"Y OTROS");
                    }
                    else {
                        lblDefensa.setText(defensa);
                    }
                    if (numFiscales > 1){
                        lblFiscal.setText(fiscal+" "+"Y OTROS");
                    }
                    else {
                        lblFiscal.setText(fiscal);
                    }
                    if (numImputados > 1){
                        lblImputado.setText(imputado +" "+"Y OTROS");
                    } 
                    else{
                        lblImputado.setText(imputado);
                    }
                    
                }
                
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAudiencias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        JDdesde = new com.toedter.calendar.JDateChooser();
        JDhasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        usuarioscombo = new javax.swing.JComboBox<>();
        btnrotularVarios = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        lblCausa = new javax.swing.JLabel();
        lblImputado = new javax.swing.JLabel();
        lblDefensa = new javax.swing.JLabel();
        lblFiscal = new javax.swing.JLabel();
        consolidacion_txt = new javax.swing.JLabel();
        lblDisco = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRotular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rotular discos originales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        tablaAudiencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "HORA", "SALA", "CAUSA", "TIPO AUDIENCIA", "CONSOLIDACION", "ESTADO", "SELECCIONAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaAudiencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAudienciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAudiencias);
        if (tablaAudiencias.getColumnModel().getColumnCount() > 0) {
            tablaAudiencias.getColumnModel().getColumn(0).setMaxWidth(80);
            tablaAudiencias.getColumnModel().getColumn(1).setMaxWidth(60);
            tablaAudiencias.getColumnModel().getColumn(2).setMaxWidth(40);
            tablaAudiencias.getColumnModel().getColumn(3).setMaxWidth(120);
            tablaAudiencias.getColumnModel().getColumn(4).setMaxWidth(260);
            tablaAudiencias.getColumnModel().getColumn(5).setMaxWidth(110);
            tablaAudiencias.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText("Fecha inicial:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setText("Fecha final:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Seleccione el rango de fechas que desea buscar");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N

        JDdesde.setDateFormatString("yyyy-MM-dd");

        JDhasta.setDateFormatString("yyyy-MM-dd");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setText("Usuario:");

        btnrotularVarios.setBackground(new java.awt.Color(0, 255, 51));
        btnrotularVarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/rotular.png"))); // NOI18N
        btnrotularVarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrotularVariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JDdesde, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JDhasta, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuarioscombo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnrotularVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel2))
                        .addComponent(JDdesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JDhasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(usuarioscombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnrotularVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblFecha.setText("jLabel4");

        lblHora.setText("jLabel4");

        lblSala.setText("jLabel4");

        lblCausa.setText("jLabel4");

        lblImputado.setText("jLabel4");

        lblDefensa.setText("jLabel4");

        lblFiscal.setText("jLabel4");

        consolidacion_txt.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addGap(18, 18, 18)
                        .addComponent(lblHora)
                        .addGap(18, 18, 18)
                        .addComponent(lblSala)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCausa))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblImputado)
                        .addGap(18, 18, 18)
                        .addComponent(lblDefensa)
                        .addGap(18, 18, 18)
                        .addComponent(lblFiscal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(consolidacion_txt)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(lblHora)
                    .addComponent(lblSala)
                    .addComponent(lblCausa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImputado)
                    .addComponent(lblDefensa)
                    .addComponent(lblFiscal)
                    .addComponent(consolidacion_txt)))
        );

        lblDisco.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setText("Número de disco:");

        btnRotular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/rotular.png"))); // NOI18N
        btnRotular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRotular, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRotular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblDisco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnRotularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotularActionPerformed
String[] opciones={"CANON","EPSON"};
        int seleccion=JOptionPane.showOptionDialog(null, "Seleccione la impresora en la cual va a rotular", "seleccion de impresora", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
       if(seleccion==0)
       {
        try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = lblFecha.getText();
                Date fecha1 = formatter.parse(fecha);
                String sala = lblSala.getText();
                String hora = lblHora.getText();
                String causa = lblCausa.getText();
                String disco = lblDisco.getText();
                String imputado = lblImputado.getText();
                String defensa = lblDefensa.getText();
                String fiscal = lblFiscal.getText();
                String consolidacion= consolidacion_txt.getText();
                int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                //String juez = modeloAudiencia.extraerJuez(idAudiencia);
                modeloAudiencia.actualizarEstadoRotulacion(idAudiencia);
                modeloDisco.generarRotulo(fecha1, hora, causa, disco, imputado, defensa, fiscal,consolidacion);
                
                String inicio = ((JTextField)JDdesde.getDateEditor().getUiComponent()).getText();
                String fFinal = ((JTextField)JDhasta.getDateEditor().getUiComponent()).getText();
                DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                modelo.setRowCount(0);
                llenarTablaAudienciasRotular(tablaAudiencias,inicio , fFinal);
                //vistaRotular.lblDisco.setText("");
            } catch (Exception ex) {
                System.out.print(ex);
                
            }  
       }else{
                   try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = lblFecha.getText();
                Date fecha1 = formatter.parse(fecha);
                String sala = lblSala.getText();
                String hora = lblHora.getText();
                String causa = lblCausa.getText();
                String disco = lblDisco.getText();
                String imputado = lblImputado.getText();
                String defensa = lblDefensa.getText();
                String fiscal = lblFiscal.getText();
                String consolidacion= consolidacion_txt.getText();
                int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                //String juez = modeloAudiencia.extraerJuez(idAudiencia);
                modeloAudiencia.actualizarEstadoRotulacion(idAudiencia);
                modeloDisco.generarRotulo2(fecha1, hora, causa, disco, imputado, defensa, fiscal,consolidacion);
                
                String inicio = ((JTextField)JDdesde.getDateEditor().getUiComponent()).getText();
                String fFinal = ((JTextField)JDhasta.getDateEditor().getUiComponent()).getText();
                DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                modelo.setRowCount(0);
                llenarTablaAudienciasRotular(tablaAudiencias,inicio , fFinal);
                //vistaRotular.lblDisco.setText("");
            } catch (Exception ex) {
                System.out.print(ex);
                
            }  
       }
    }//GEN-LAST:event_btnRotularActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
 
String inicial =  ((JTextField)JDdesde.getDateEditor().getUiComponent()).getText();
String fechaFinal =  ((JTextField)JDhasta.getDateEditor().getUiComponent()).getText();

             int numAudiencias = modeloAudiencia.listAudienciasRotular(inicial, fechaFinal).size();
             if (inicial.isEmpty() || fechaFinal.isEmpty()){
                 JOptionPane.showMessageDialog(null, "Indique la fecha de inicio y fecha final","Fecha vacia", JOptionPane.ERROR_MESSAGE);
             }
             else if(usuarioscombo.getSelectedIndex()==-1 || usuarioscombo.getSelectedItem().toString().isEmpty())
                   {
                       if (numAudiencias > 0)
                       {
                       DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                       modelo.setRowCount(0);

                       llenarTablaAudienciasRotular(tablaAudiencias, inicial,fechaFinal);
                       //SE AGREGA DE LLENAR LOS CHECKLIST EN FALSO 19/FEB/2021
                       // SE REALIZA PARA QUE PUEDAN ROTULAR VARIOS DISCOS AL MISMO TIEMPO
                       int tamanio = tablaAudiencias.getRowCount();
                      for(int i =0; i<tamanio; i++)
                      {
                            tablaAudiencias.setValueAt(false, i, 7);
                       }
                       }
                       else 
                       {
                        JOptionPane.showMessageDialog(null, "No existen audiencias consolidadas","No existen registros", JOptionPane.ERROR_MESSAGE);
                       }
                   }
             else {
                       if (numAudiencias > 0)
                       {
                       DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                       modelo.setRowCount(0);
                       llenarTablaAudienciasRotularUsuario(tablaAudiencias, inicial,fechaFinal);
                        //SE AGREGA DE LLENAR LOS CHECKLIST EN FALSO 19/FEB/2021
                       // SE REALIZA PARA QUE PUEDAN ROTULAR VARIOS DISCOS AL MISMO TIEMPO
                      int tamanio = tablaAudiencias.getRowCount();
                      for(int i =0; i<tamanio; i++)
                      {
                            tablaAudiencias.setValueAt(false, i, 7);
                       }
                       }
                       else 
                       {
                        JOptionPane.showMessageDialog(null, "No existen audiencias consolidadas","No existen registros", JOptionPane.ERROR_MESSAGE);
                       }
                  }         
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaAudienciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAudienciasMouseClicked
 
        //ACTUALIZACION DE ROTULAR VARIOS DISCOS 25 DE FEBRERO DEL 2021
        //SE QUITARAN LINEAS QUE YA NO SON NECESARIAS YA QUE SON REDUNDANTES
        
        if (tablaAudiencias.getSelectedRow() != -1){
                    int fila = tablaAudiencias.getSelectedRow();
                    String fecha = tablaAudiencias.getValueAt(fila, 0).toString();
                    String hora = tablaAudiencias.getValueAt(fila, 1).toString();
                    String sala = tablaAudiencias.getValueAt(fila, 2).toString();
                    String causa = tablaAudiencias.getValueAt(fila, 3).toString();
                    //String consolidacion=tablaAudiencias.getValueAt(fila, 5).toString();
                    //consolidacion_txt.setText(consolidacion);
                    //lblFecha.setText(fecha);
                    //lblHora.setText(hora);
                    //lblSala.setText(sala);
                    //lblCausa.setText(causa);
                    int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    //System.out.println("IdAudiencia: "+idAudiencia);
                    int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                    int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                    String disco = numDisco +"-"+anioDisco;
                    lblDisco.setText(disco);
                    //Obtiene el numero de participantes sdegun el tipo
                    
                    // SE COMENTARAN LAS LINEAS DONDE SE EXTRAIGAN A LOS PARTICIPANTES
                   
                   /* int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                    int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                    int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();
                    
                    String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                    String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                    String imputado = modeloParticipante.imputadoOriginal(idAudiencia);
                    
                    
                    if (numDefensas > 1){
                        lblDefensa.setText(defensa +" "+"Y OTROS");
                    }
                    else {
                        lblDefensa.setText(defensa);
                    }
                    if (numFiscales > 1){
                        lblFiscal.setText(fiscal+" "+"Y OTROS");
                    }
                    else {
                        lblFiscal.setText(fiscal);
                    }
                    if (numImputados > 1){
                        lblImputado.setText(imputado +" "+"Y OTROS");
                    } 
                    else{
                        lblImputado.setText(imputado);
                    }*/
                    
                }
    }//GEN-LAST:event_tablaAudienciasMouseClicked

    private void btnrotularVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrotularVariosActionPerformed

        int tamanio = tablaAudiencias.getRowCount();
       // System.out.println("tamanio:"+tamanio);
        int contador = 0;
           for(int y=0; y<tamanio;y++){
                Boolean estadoSeleccion = Boolean.valueOf(tablaAudiencias.getValueAt(y, 7).toString());
                if(estadoSeleccion.equals(false)){
                    contador++;
                }
            }
           //System.out.println("cuantos falsos:"+contador);
           if(tamanio==contador){
                JOptionPane.showMessageDialog(null, "Seleccione las audiencias que desea rotular","Error al rotular",JOptionPane.ERROR_MESSAGE);
            }
           else
           {
            
                    //VALIDACION DE IMPRESORA
                    String[] opciones={"CANON","EPSON","CANCELAR"};
                    int seleccion=JOptionPane.showOptionDialog(null, "Seleccione la impresora en la cual va a rotular los "+(tamanio-contador)+" discos", "seleccion de impresora", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    //INICIO CANON
                    if(seleccion==0)
                     {


                       int contador_verdaderos=0;
                       for(int i = 0; i<tamanio; i++)
                       {
                        int idAudiencia = 0;
                        Boolean verdadero = new Boolean(true);
                        Boolean estadoColumna = Boolean.valueOf(tablaAudiencias.getValueAt(i, 7).toString());
                        if(estadoColumna.equals(verdadero))
                            {
                                contador_verdaderos++;
                                Object fecha  = tablaAudiencias.getValueAt(i, 0);
                                Object hora  = tablaAudiencias.getValueAt(i, 1); 
                                Object sala  = tablaAudiencias.getValueAt(i, 2);
                                Object causa  = tablaAudiencias.getValueAt(i, 3);
                                Object consolidacion  = tablaAudiencias.getValueAt(i, 5);

                                //EXTRAER ID DE AUDIENCIA
                                idAudiencia = modeloAudiencia.extraerId(String.valueOf(fecha), String.valueOf(hora), String.valueOf(sala));

                                //EXTRAER NUMERO DE DISCO
                                int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                                int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                                String disco = numDisco +"-"+anioDisco;        
                                //System.out.println("id de la audiencia"+idAudiencia);

                             //EXTRAER PARTICIPANTES   
                            int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                            int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                            int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();

                            String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                            String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                            String imputado = modeloParticipante.imputadoOriginal(idAudiencia);


                            if (numDefensas > 1){
                                lblDefensa.setText(defensa +" "+"Y OTROS");
                            }
                            else {
                                lblDefensa.setText(defensa);
                            }
                            if (numFiscales > 1){
                                lblFiscal.setText(fiscal+" "+"Y OTROS");
                            }
                            else {
                                lblFiscal.setText(fiscal);
                            }
                            if (numImputados > 1){
                                lblImputado.setText(imputado +" "+"Y OTROS");
                            } 
                            else{
                                lblImputado.setText(imputado);
                            }   
                            //TERMINA EXTRAER PARTICIPANTES
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date fecha1;
                            String fechaStrin=String.valueOf(fecha);
                            try {
                                fecha1 = formatter.parse(fechaStrin);
                                modeloAudiencia.actualizarEstadoRotulacion(idAudiencia);
                                modeloDisco.generarRotulo(fecha1,String.valueOf(hora),String.valueOf(causa), disco, imputado, defensa, fiscal,String.valueOf(consolidacion));
                            } catch (ParseException ex) {
                                Logger.getLogger(RotularDiscos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            }
                        }
                           //System.out.println("contador de verdaderos:"+contador_verdaderos);
                     }
                    
                    //FIN CANON
                    
                    //SI ES EPSON
                    else if(seleccion==1)
                     {
                                                int contador_verdaderos=0;
                       for(int i = 0; i<tamanio; i++)
                       {
                        int idAudiencia = 0;
                        Boolean verdadero = new Boolean(true);
                        Boolean estadoColumna = Boolean.valueOf(tablaAudiencias.getValueAt(i, 7).toString());
                        if(estadoColumna.equals(verdadero))
                            {
                                contador_verdaderos++;
                                Object fecha  = tablaAudiencias.getValueAt(i, 0);
                                Object hora  = tablaAudiencias.getValueAt(i, 1); 
                                Object sala  = tablaAudiencias.getValueAt(i, 2);
                                Object causa  = tablaAudiencias.getValueAt(i, 3);
                                Object consolidacion  = tablaAudiencias.getValueAt(i, 5);

                                //EXTRAER ID DE AUDIENCIA
                                idAudiencia = modeloAudiencia.extraerId(String.valueOf(fecha), String.valueOf(hora), String.valueOf(sala));

                                //EXTRAER NUMERO DE DISCO
                                int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                                int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                                String disco = numDisco +"-"+anioDisco;        
                                //System.out.println("id de la audiencia"+idAudiencia);

                             //EXTRAER PARTICIPANTES   
                            int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                            int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                            int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();

                            String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                            String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                            String imputado = modeloParticipante.imputadoOriginal(idAudiencia);


                            if (numDefensas > 1){
                                lblDefensa.setText(defensa +" "+"Y OTROS");
                            }
                            else {
                                lblDefensa.setText(defensa);
                            }
                            if (numFiscales > 1){
                                lblFiscal.setText(fiscal+" "+"Y OTROS");
                            }
                            else {
                                lblFiscal.setText(fiscal);
                            }
                            if (numImputados > 1){
                                lblImputado.setText(imputado +" "+"Y OTROS");
                            } 
                            else{
                                lblImputado.setText(imputado);
                            }   
                            //TERMINA EXTRAER PARTICIPANTES
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date fecha1;
                            String fechaStrin=String.valueOf(fecha);
                            try {
                                fecha1 = formatter.parse(fechaStrin);
                                modeloAudiencia.actualizarEstadoRotulacion(idAudiencia);
                                modeloDisco.generarRotulo2(fecha1,String.valueOf(hora),String.valueOf(causa), disco, imputado, defensa, fiscal,String.valueOf(consolidacion));
                            } catch (ParseException ex) {
                                Logger.getLogger(RotularDiscos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            }
                        }
                   
                   
                     }
                    //FIN EPSON
                    else
                    {
                        JOptionPane.showMessageDialog(null, "NO SE HA SELECCIONADO LA IMPRESORA","CANCELACION DE ROTULACION", JOptionPane.ERROR_MESSAGE);
                    }
                
                // SE VUELVEN A CARGAR LOS DATOS EN LA TABLA DE MOSTRAR DISCOS A ROTULAR 
                
                //SE VALIDA SI SE SELECCIONO USUARIO
                if(usuarioscombo.getSelectedIndex()==-1 || usuarioscombo.getSelectedItem().toString().isEmpty())
                   {
                      String inicio = ((JTextField)JDdesde.getDateEditor().getUiComponent()).getText();
                      String fFinal = ((JTextField)JDhasta.getDateEditor().getUiComponent()).getText();
                      DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                      modelo.setRowCount(0);
                      llenarTablaAudienciasRotular(tablaAudiencias,inicio , fFinal);
                      int tamanioUltimo = tablaAudiencias.getRowCount();
                      for(int i =0; i<tamanioUltimo; i++)
                      {
                            tablaAudiencias.setValueAt(false, i, 7);
                       }
                   }
                else
                   {
                       String inicio = ((JTextField)JDdesde.getDateEditor().getUiComponent()).getText();
                      String fFinal = ((JTextField)JDhasta.getDateEditor().getUiComponent()).getText();
                       DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
                       modelo.setRowCount(0);
                       llenarTablaAudienciasRotularUsuario(tablaAudiencias,inicio,fFinal);
                        //SE AGREGA DE LLENAR LOS CHECKLIST EN FALSO 19/FEB/2021
                       // SE REALIZA PARA QUE PUEDAN ROTULAR VARIOS DISCOS AL MISMO TIEMPO
                      int tamanioUltimoUsuario = tablaAudiencias.getRowCount();
                      for(int i =0; i<tamanioUltimoUsuario; i++)
                      {
                            tablaAudiencias.setValueAt(false, i, 7);
                      }
                       
                   }
   
                    
           }

               
           
           
           
    }//GEN-LAST:event_btnrotularVariosActionPerformed

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
            java.util.logging.Logger.getLogger(RotularDiscos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RotularDiscos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RotularDiscos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RotularDiscos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RotularDiscos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JDateChooser JDdesde;
    public com.toedter.calendar.JDateChooser JDhasta;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRotular;
    public javax.swing.JButton btnrotularVarios;
    private javax.swing.JLabel consolidacion_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblCausa;
    public javax.swing.JLabel lblDefensa;
    public javax.swing.JLabel lblDisco;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblFiscal;
    public javax.swing.JLabel lblHora;
    public javax.swing.JLabel lblImputado;
    public javax.swing.JLabel lblSala;
    public javax.swing.JTable tablaAudiencias;
    public javax.swing.JComboBox<String> usuarioscombo;
    // End of variables declaration//GEN-END:variables
}
