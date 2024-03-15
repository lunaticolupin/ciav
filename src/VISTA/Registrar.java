/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;
import VISTA.Login;
import CONTROLADOR.CrudAudiencia;
import MODELO.AudienciaDAO;
import MODELO.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import  MODELO.SolicitudExterna;
import MODELO.SolicitudExternaDAO;
import java.util.Arrays;
import MODELO.AudienciaDAO;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.sql.Statement;
import MODELO.SolicitudDAO;
import MODELO.DiscoDAO;
import MODELO.ParticipanteDAO;
import static controlinterno.ControlInterno.reg;
import javax.swing.JDialog;
//import org.jvnet.substance.SubstanceLookAndFeel;
import MODELO.AUD_PAR;
import MODELO.CatalogoDAO;
import MODELO.ConsultaUsuarios;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class Registrar extends javax.swing.JFrame {
     JScrollPane scrollPane;
     Conexion  con;
     AgregarParticipanteAudiencia formAgregar = new AgregarParticipanteAudiencia();
     SolicitudExternaDAO modeloSolicitudE = new SolicitudExternaDAO();
     AudienciaDAO modeloAudiencia= new AudienciaDAO();
     SolicitudDAO modeloSolicitud =  new SolicitudDAO();
     ParticipanteDAO modeloParticipante = new ParticipanteDAO();
     DiscoDAO modeloDisco = new DiscoDAO();
     AUD_PAR modelointermedia=new AUD_PAR();
       TextAutoCompleter textAutoAcompleter;
       TextAutoCompleter textAutoAcompleter2;
        TextAutoCompleter textAutoAcompleter3;
       ImageIcon icono=new ImageIcon("inactivo.png");
public static Login sesion;
public static Registro sign;
public static Administrador admin;
public static BajaUsuarios baja;
CatalogoDAO modeloCatalogo = new CatalogoDAO();
    ConsultaUsuarios usuarios = new ConsultaUsuarios();
    AUD_PAR modeloIntermedia = new AUD_PAR();
    SpinnerNumberModel modeloHora = new SpinnerNumberModel();
    SpinnerNumberModel modeloMinutos = new SpinnerNumberModel();
    RotularSolicitudesExternas rotuloExternas = new RotularSolicitudesExternas();
    String hora, minutos, completa;
    Calendar fecha = Calendar.getInstance();
    int anio = fecha.get(Calendar.YEAR);
public Registrar() {
        
        initComponents();
        generar_rotulo.setToolTipText("GENERAR ROTULO");
        Estado.setVisible(false);
        btnConsultarP.setVisible(false);
        tSolicitud.add(jRadioButton1);
        tSolicitud.add(RadioBtnPendiente);
        tSolicitud.add(RadioBtnRotulado);
        tSolicitud.add(cancelada);
        externas.add(Ramparo);
        externas.add(Racuerdo);
        externas2.add(Racuerdo2);
        externas2.add(Ramparo2);
        buttonGroup1.add(jTodosF);
        buttonGroup1.add(jPendienteF);
        buttonGroup1.add(jRotuladoF);
        buttonGroup1.add(jCanceladaF);
        textAutoAcompleter = new TextAutoCompleter(txtPartes);
        textAutoAcompleter2 = new TextAutoCompleter(txtPartes2);
        textAutoAcompleter3 = new TextAutoCompleter(txtPartes3);
        scrollPane=new JScrollPane();
        scrollPane.setBounds(10,10,1340,700);
    //scrollPane.setBounds(10,10,1200,1070);
//scrollPane.setBounds(10,10,1200,1010);
       scrollPane.setViewportView(jTabbedPane1);
       add(scrollPane);
        con = new Conexion();
        comboTipo.setSelectedIndex(-1);
        comboTipo2.setSelectedIndex(-1);
        Racuerdo.setSelected(true);
        Racuerdo2.setSelected(true);
        fieldComentario.setEditable(false);
        jRadioButton1.setSelected(true);
      // tSolicitud.add(Ramparo);
       txtusuario.setVisible(false);
       modeloHora.setMinimum(0);
        modeloHora.setMaximum(24);
        modeloMinutos.setMinimum(0);
        modeloMinutos.setMaximum(59);
       JS5.setModel(modeloHora);
       JS6.setModel(modeloMinutos);
       lblParticipante2.setVisible(false);
       lblPrueba2.setVisible(false);
       conso_txt.setVisible(false);
       btnCancelar.setToolTipText("Cancelar"); 
       btnImprimirFolios.setToolTipText("Reimpresion de folios");
       jTodosF.setSelected(true);
       
 llenarCombo();
 autocompletado_busqueda_participante();
    }

public void autocompletado_busqueda_participante()
        {
            Statement st=null;
            ResultSet rs=null;
        try{ 
             Connection accesoBD = con.conectar();
            st=(Statement)accesoBD.createStatement();
            //rs=st.executeQuery("SELECT NOMBRE FROM aud_par INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='"+audiencia+"' AND participante.TIPO='"+tipos+"'");
             rs=st.executeQuery("SELECT CONCAT(NOMBRE,' ',TIPO,' ',idParticipante) as participante FROM PARTICIPANTE ");
            while(rs.next()){
                textAutoAcompleter3.addItem(rs.getString("participante"));
                
            }
            rs.close();
        }   catch(SQLException de){
            JOptionPane.showMessageDialog(this,de.getMessage());
            System.err.println(de.toString());
        }
        }
public void Count_externa(String tipo)
 {
     try{
        String Finicio = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
        String Ffinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
         System.out.println("FECHA: "+Finicio);
       String sql="SELECT fecha,COUNT(*) FROM solicitud_externa WHERE ESTADO!='Cancelada' AND FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND TIPO='"+tipo+"' GROUP BY FECHA" ;
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
                        System.out.println("Fecha "+i+" : "+datos[i]);
                    }
                 
           }
                rs.close();
     }catch(SQLException ex){
                System.err.println(ex.toString()); 
     }
 }
public void Count_copias()
 {
     try{
        String Finicio = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String Ffinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       String sql="SELECT COUNT(*) FROM solicitud WHERE ESTADO!='Cancelada' AND FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' GROUP BY FECHA" ;
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
                        System.out.println("Fecha "+i+" : "+datos[i]);
                    }
                 
           }
                rs.close();
     }catch(SQLException ex){
                System.err.println(ex.toString()); 
     }
 }
public void Count_audiencias()
 {
     try{
        String Finicio = ((JTextField)JDinicial.getDateEditor().getUiComponent()).getText();
        String Ffinal = ((JTextField)JDfinal.getDateEditor().getUiComponent()).getText();
       String sql="SELECT COUNT(*) FROM audiencia WHERE audiencia.ESTADO='CELEBRADA' AND FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' GROUP BY FECHA" ;
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
                        System.out.println("Fecha "+i+" : "+datos[i]);
                    }
                 
           }
                rs.close();
     }catch(SQLException ex){
                System.err.println(ex.toString()); 
     }
 }


public void reproducir_video(String nombre)
{
    //20190517_479_479_551
    try {
    //Runtime.getRuntime().exec("cmd /c start \"%programfiles%\\Windows Media Player\\wmplayer.exe\" \"c:\\mcr.mp3\"");
    //Runtime.getRuntime().exec("cmd /c start \"%programfiles%\\Windows Media Player\\wmplayer.exe\" \"\\\\10.27.46.12\\puebla-centro"
     //       + "\\CONSOLIDACION\\"+consolidacion+"\\"+nombre+"\"");
     Runtime.getRuntime().exec("cmd /c start \"%programfiles%\\Windows Media Player\\wmplayer.exe\" \"\\\\10.27.51.240\\Consolidacion\\"+nombre+".MP4");
    }
catch(Exception e)
{ 
    System.out.println(e.getMessage()); 
} 
}
public void llenarTablaFolioExternaAmparo(int folio)
{     DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
    try{
 
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                    //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE S.ID='"+folio+"'";
                    sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA,audiencia.causa,S.ARCHIVO,COUNT(sol_dis.idSolicitud) as num FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE S.ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                   modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                    JOptionPane.showMessageDialog(null,"Busqueda Finalizada");
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
    
    
}
public void llenarTablaFolioExternaAcuerdo(int folio)
{     DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);

    try{

                    DefaultTableModel modelo= new DefaultTableModel();
                    
                    //String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE S.ID='"+folio+"'";
                    String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO,audiencia.causa,S.ARCHIVO,COUNT(sol_dis.idSolicitud) as num FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE S.ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {55,65,65,10,62,65,110,120,80,200,50,100,100,10,20};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                    JOptionPane.showMessageDialog(null,"Busqueda Finalizada");
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
    
    
}


   public String ExtraerTipoSolicitud (int solicitud){
        String tipo ="";
        String sql;
        sql = "SELECT S.TIPO FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE S.ID='"+solicitud+"'";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tipo = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return tipo;
    }



public void llenarTablaAmparoCausa(String estado,String causa)
{
     DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
              if(estado=="todos")
       {
                 try{
                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                    //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' ORDER by S.ID";
                     sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA,audiencia.causa,S.ARCHIVO,COUNT(sol_dis.idSolicitud) as num "
                             + "FROM sol_dis INNER JOIN solicitud_externa AS S "
                             + "ON sol_dis.idSolicitud = S.ID INNER JOIN disco "
                             + "ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia "
                             + "ON disco.AUDIENCIA = audiencia.idAudiencia "
                             + "WHERE audiencia.CAUSA='"+causa+"' "
                             + "AND S.TIPO='AMPARO' "
                             + "GROUP BY S.ID";
                   Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes con el numero de causa: "+causa); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
       }
              else{
                                 try{
                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                    //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' AND S.ESTADO='"+estado+"' ORDER by S.ID";
                    sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA,audiencia.causa,S.ARCHIVO,COUNT(sol_dis.idSolicitud) as num FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' AND S.ESTADO='"+estado+"' GROUP BY S.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en estado: "+estado+" con la causa: "+causa); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }  
                  
                  
                  
              }
    
    
}






public void llenarTablaAcuerdoCausa(String estado, String causa){
        DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
       if(estado=="todos")
       {                    lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                      try{

                    DefaultTableModel modelo= new DefaultTableModel();
                    
                    //String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='ACUERDO' ORDER by S.ID";
                    String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO,audiencia.causa,S.ARCHIVO, COUNT(sol_dis.idSolicitud) FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='ACUERDO' GROUP by S.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();

                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {55,65,65,10,62,65,110,120,80,200,50,100,100,10,20};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes con la causa: "+causa); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
       }
         else{      lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                                    try{

                    DefaultTableModel modelo= new DefaultTableModel();
                    
                   // String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.ESTADO='"+estado+"' AND S.TIPO='ACUERDO' ORDER by S.ID";
       String sql="SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.MOTIVO,S.FECHA_ACUERDO,audiencia.causa,S.ARCHIVO,COUNT(sol_dis.idSolicitud) FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.ESTADO='"+estado+"' AND S.TIPO='ACUERDO' GROUP by S.ID";
       
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();

                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {55,65,65,10,62,65,110,120,80,200,50,100,100,10,20};;
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en estado: "+estado+" con la causa: "+causa); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }               
                              
                              
                              
                              }   
    
    
    
}


public void llenarTablaFolio()
{
           DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       modelo.setRowCount(0);
       int id=(Integer.parseInt(txtBuscarFolio.getText()));
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.ID='"+id+"' ORDER BY S.ID";
       String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.ID='"+id+"' ORDER BY S.ID"; 
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
int numRegistro= modelo.getRowCount();
if(numRegistro>0)
{
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    JOptionPane.showMessageDialog(null, "No se encontro ese numero de folio");
}
        //System.out.println(fecha);
        //System.out.println(fechaFinal);
                     // DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
               modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });
}

//CONSULTA PARA MOSTRAR POR FECHA DE CONSTANCIA
public void llenarTablaExternaAcuerdoConstancia()
  {
       DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
                      try{

                    String fecha = ((JTextField)JDbuscarSolicitudE1.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal1.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    
                    //String sql="SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,MOTIVO,FECHA_ACUERDO "
                    //+ "FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND ESTADO!='Cancelada'"    ;
                    String sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.MOTIVO,se.FECHA_ACUERDO,a.causa,SE.ARCHIVO, COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se "
                            + "INNER JOIN SOL_DIS AS sl "
                            + "ON sl.idSolicitud=se.id "
                            + "INNER JOIN disco AS d "
                            + "ON d.id_disco=sl.idDisco "
                            + "INNER JOIN audiencia AS a "
                            + "ON a.idAudiencia=d.audiencia  "
                            + "WHERE se.TIPO = 'ACUERDO' "
                            + "AND str_to_date(substr(FECHA_CONSTANCIA,1,10),'%d-%m-%Y')  "
                            + "BETWEEN '"+fecha+"' and '"+fechaFinal+"' "
                            + "GROUP BY se.ID";
       
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {55,65,65,10,62,65,110,120,80,200,50,100,100,10,20};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas"); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
    
}




//CONSULTA PARA MOSTRAR POR FECHA DE CONSTANCIA
public void llenarTablaExternaAmparoConstancia()
{      DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
                 try{
                    String fecha = ((JTextField)JDbuscarSolicitudE1.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal1.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                    sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.NUMAMPARO,se.DEPENDENCIA,a.causa,se.ARCHIVO,COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se INNER JOIN SOL_DIS AS sl ON sl.idSolicitud=se.id INNER JOIN disco AS d ON d.id_disco=sl.idDisco INNER JOIN audiencia AS a ON a.idAudiencia=d.audiencia  WHERE se.TIPO = 'AMPARO' AND str_to_date(substr(FECHA_CONSTANCIA,1,10),'%d-%m-%Y')  BETWEEN '"+fecha+"' and '"+fechaFinal+"' GROUP BY se.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                     modelo.addColumn("CAUSA");
                     modelo.addColumn("ARCHIVO");
                     modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas"); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
              
}


public void llenarTablaExternaAmparo(String estado)
{      DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
       System.out.println("dentro de EXTERNA AMPARO");
              if(estado=="todos")
       {
                 try{
                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                    //sql= "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,NUMAMPARO,DEPENDENCIA "
                    //+ "FROM SOLICITUD_EXTERNA WHERE TIPO = 'AMPARO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND ESTADO!='Cancelada'";
                    sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.NUMAMPARO,se.DEPENDENCIA,a.causa,se.ARCHIVO,COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se "
                            + "INNER JOIN SOL_DIS AS sl "
                            + "ON sl.idSolicitud=se.id "
                            + "INNER JOIN disco AS d "
                            + "ON d.id_disco=sl.idDisco "
                            + "INNER JOIN audiencia AS a "
                            + "ON a.idAudiencia=d.audiencia  "
                            + "WHERE se.TIPO = 'AMPARO' "
                            + "AND se.FECHA BETWEEN '"+fecha+"' "
                            + "AND '"+fechaFinal+"' "
                            + "AND se.ESTADO!='Cancelada' "
                            + "GROUP BY se.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                   
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                     modelo.addColumn("CAUSA");
                     modelo.addColumn("ARCHIVO");
                     modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }                                      
                    
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
                    
                    
                    if(numRegistro>0){
                    
                    }else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas"); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
       }
              else{
                                 try{
                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    String sql ="";
                   // sql= "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,NUMAMPARO,DEPENDENCIA "
                   // + "FROM SOLICITUD_EXTERNA WHERE TIPO = 'AMPARO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND ESTADO='"+estado+"'";
                    sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.NUMAMPARO,se.DEPENDENCIA,a.causa,se.ARCHIVO, COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se INNER JOIN SOL_DIS AS sl ON sl.idSolicitud=se.id INNER JOIN disco AS d ON d.id_disco=sl.idDisco INNER JOIN audiencia AS a ON a.idAudiencia=d.audiencia  WHERE se.TIPO = 'AMPARO' AND se.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND SE.ESTADO='"+estado+"' GROUP BY se.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    String sqlSum= "SELECT SUM(COPIAS) FROM solicitud_externa WHERE TIPO = 'AMPARO'";
                    PreparedStatement pstSum = accesoBD.prepareStatement(sqlSum);
                    ResultSet rsSum = pstSum.executeQuery();
                    System.out.println("SUMA: "+rsSum);
                    lblAcuerdo.setVisible(false);
                    jLabel53.setVisible(false);
                    lblAmparo.setVisible(true);
                    jLabel52.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("AMPARO");
                    modelo.addColumn("DEPENDENCIA");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                int [] anchos = {60,95,55,30,50,100,100,10,150,40,100,150,20,10};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        int sumaT = 0;
                        int suma = 0;
                        int colCopia = 0;
                        int colGatto= 0;
                        int mult = 0;
                        for (int i = 0; i<modelo.getRowCount(); i++) {
                            String colCopias = modelo.getValueAt(i, 3).toString();
                            String colGatito = modelo.getValueAt(i, 13).toString();
                            colCopia = parseInt(colCopias);
                            colGatto = parseInt(colGatito);
                            mult = colCopia * colGatto;
                            
                            suma = mult;
                            sumaT += suma;
                        }
                        System.out.println("sumaMul:"+sumaT);
                        total_discos.setText(String.valueOf(sumaT));
                        total_externas.setText(String.valueOf(numRegistro));    
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en estado: "+estado); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }  
                  
                  
                  
              }
    
}
public void llenarTablaExternaAcuerdo(String estado)
  {
       DefaultTableModel modelo2= (DefaultTableModel)tablaComentarioExterna.getModel();
       modelo2.setRowCount(0);
       if(estado=="todos")
       {
                      try{

                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    
                    //String sql="SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,MOTIVO,FECHA_ACUERDO "
                    //+ "FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND ESTADO!='Cancelada'"    ;
                    String sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.MOTIVO,se.FECHA_ACUERDO,a.causa,SE.ARCHIVO, COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se INNER JOIN SOL_DIS AS sl ON sl.idSolicitud=se.id INNER JOIN disco AS d ON d.id_disco=sl.idDisco INNER JOIN audiencia AS a ON a.idAudiencia=d.audiencia  WHERE se.TIPO = 'ACUERDO' AND se.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND se.ESTADO!='Cancelada' GROUP BY se.ID";
       
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {11,65,65,10,62,65,110,120,80,50,200,100,100,10,20};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        int sumaT = 0;
                        int suma = 0;
                        int colCopia = 0;
                        int colGatto= 0;
                        int mult = 0;
                        for (int i = 0; i<modelo.getRowCount(); i++) {
                            String colCopias = modelo.getValueAt(i, 3).toString();
                            String colGatito = modelo.getValueAt(i, 13).toString();
                            colCopia = parseInt(colCopias);
                            colGatto = parseInt(colGatito);
                            mult = colCopia * colGatto;
                            
                            suma = mult;
                            sumaT += suma;
                        }
                        System.out.println("sumaMul:"+sumaT);
                        total_discos.setText(String.valueOf(sumaT));
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas"); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }
       }
         else{
                                    try{

                    String fecha = ((JTextField)JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
                    String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
                    DefaultTableModel modelo= new DefaultTableModel();
                    
                   // String sql="SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,MOTIVO,FECHA_ACUERDO "
                   // + "FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND ESTADO='"+estado+"' ";
                    String sql= "SELECT se.ID, se.FECHA,se.HORA, se.COPIAS,se.ESTADO,se.FECHA_CONSTANCIA,se.FECHA_ENTREGA,se.TIPO,se.SOLICITANTE,se.MOTIVO,se.FECHA_ACUERDO,a.causa,se.ARCHIVO, COUNT(sl.idSolicitud) "
                    + "FROM SOLICITUD_EXTERNA AS se INNER JOIN SOL_DIS AS sl ON sl.idSolicitud=se.id INNER JOIN disco AS d ON d.id_disco=sl.idDisco INNER JOIN audiencia AS a ON a.idAudiencia=d.audiencia  WHERE se.TIPO = 'ACUERDO' AND se.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND SE.ESTADO='"+estado+"' GROUP BY se.ID";
                    
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    lblAmparo.setVisible(false);
                    jLabel52.setVisible(false);
                    lblAcuerdo.setVisible(true);
                    jLabel53.setVisible(true);
                    modelo.addColumn("ID");
                    modelo.addColumn("FECHA");
                    modelo.addColumn("HORA");
                    modelo.addColumn("COPIAS");
                    modelo.addColumn("ESTADO");
                    modelo.addColumn("FECHA CONSTANCIA");
                    modelo.addColumn("FECHA ENTREGA");
                    modelo.addColumn("TIPO");
                    modelo.addColumn("SOLICITANTE");
                    modelo.addColumn("MOTIVO");
                    modelo.addColumn("FECHA ACUERDO");
                    modelo.addColumn("CAUSA");
                    modelo.addColumn("ARCHIVO");
                    modelo.addColumn("#");
                    tablaSolicitudExterna.setModel(modelo);
                    int [] anchos = {11,65,65,10,62,65,110,120,80,50,200,100,100,10,20};
                    for(int x=0; x<cantidadcolumnas;x++)
                    {
                        tablaSolicitudExterna.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                    }
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
                        int sumaT = 0;
                        int suma = 0;
                        int colCopia = 0;
                        int colGatto= 0;
                        int mult = 0;
                        for (int i = 0; i<modelo.getRowCount(); i++) {
                            String colCopias = modelo.getValueAt(i, 3).toString();
                            String colGatito = modelo.getValueAt(i, 13).toString();
                            colCopia = parseInt(colCopias);
                            colGatto = parseInt(colGatito);
                            mult = colCopia * colGatto;
                            
                            suma = mult;
                            sumaT += suma;
                        }
                        System.out.println("sumaMul:"+sumaT);
                        total_discos.setText(String.valueOf(sumaT));
                        total_externas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                    }
                    else{
                        total_externas.setText(String.valueOf(numRegistro));
                       JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en estado: "+estado); 
                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }               
                              
                              
                              
                              }
    
}



public void ActualizarTablaCausa()
{
                btnEntrega.setVisible(true);
            btnConstancia.setVisible(true);
            btnCancelar.setVisible(true);
            try{
                String causa=CausaSolicitud.getText();
                //DefaultTableModel modelo= new DefaultTableModel();
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
               // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
               String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID"; 
               Connection accesoBD = con.conectar();
                PreparedStatement pst = accesoBD.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadcolumnas = rsMd.getColumnCount();
                /* modelo.addColumn("Folio");
                modelo.addColumn("Fecha solicitud");
                modelo.addColumn("Hora solicitud");
                modelo.addColumn("Copias");
                modelo.addColumn("Estado");
                modelo.addColumn("Fecha_constancia");
                modelo.addColumn("Hora constancia");
                modelo.addColumn("Fecha_entrega");
                modelo.addColumn("Hora entrega");
                modelo.addColumn("Participante");
                modelo.addColumn("Tipo");
                modelo.addColumn("Audiencia");*/
                //tablaEntrega.setModel(modelo);
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
                int total = tablaEntrega.getRowCount();
                if(total ==0){
                    JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa+"");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                }
            } catch (SQLException ex){
                System.err.println(ex.toString());
            }
            tablaEntrega.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(tablaEntrega.getSelectedRow() != -1){
                        int fila = tablaEntrega.getSelectedRow();
                        fieldComentario.setText("");
                        txtEnombreS.setText(tablaEntrega.getValueAt(fila, 10).toString());
                        txtEfechaSoli.setText(tablaEntrega.getValueAt(fila, 1).toString());
                        txtTipoAudiencia.setText(tablaEntrega.getValueAt(fila, 12).toString());
                        int solicitud =Integer.parseInt( tablaEntrega.getValueAt(fila, 0).toString());
                        txtEcopiasS.setText(tablaEntrega.getValueAt(fila, 3).toString());
                        txtEtipoS.setText(tablaEntrega.getValueAt(fila, 11).toString());
                        int disco = modeloSolicitud.extraerNumdisco(solicitud);
                        String fecha = modeloDisco.extraerFechaAudiencia(disco);
                        String hora = modeloDisco.extraerHoraAudiencia(disco);
                        String causa = modeloDisco.extraerCausaAudiencia(disco);
                        int audiencia = modeloDisco.extraerIdAudiencia(disco);

                        int numFiscales = modeloParticipante.lsFiscalesOriginal(audiencia).size();
                        int numDefensas = modeloParticipante.lsDefensasOriginal(audiencia).size();
                        int numImputados = modeloParticipante.lsImputadosOriginal(audiencia).size();

                        String imputado = modeloParticipante.imputadoOriginal(audiencia);
                        String defensa = modeloParticipante.defensaOriginal(audiencia);
                        String fiscal = modeloParticipante.fiscalOriginal(audiencia);
                        //System.out.print("Fiscales " +numFiscales);
                        //System.out.print("Defensas " +numDefensas);
                        //System.out.print("Imputados " +numImputados);
                        if (numDefensas > 1){
                            lblDefensaS.setText(defensa +" "+"Y OTROS");
                        } else{
                            lblDefensaS.setText(defensa);
                        }
                        if (numFiscales > 1){
                            lblFiscal.setText(fiscal+" "+"Y OTROS");
                        } else {
                            lblFiscal.setText(fiscal);
                        }
                        if (numImputados > 1){
                            lblImputadoS.setText(imputado+" "+"Y OTROS");
                        } else {
                            lblImputadoS.setText(imputado);
                        }

                        String juez  = modeloAudiencia.extraerJuez(audiencia);
                        /*String imputado = modeloAudiencia.extraerImputado(audiencia);
                        String defensa = modeloAudiencia.extraerDefensa(audiencia);
                        String fiscal = modeloAudiencia.extraerFiscal(audiencia);*/
                        //JOptionPane.showMessageDialog(null, solicitud);
                        txtEfechaS.setText(fecha);
                        txtEhoraS.setText(hora);
                        txtEcausaS.setText(causa);
                        txtEjuezS.setText(juez);
                        lblIdSoli.setText(tablaEntrega.getValueAt(fila, 0).toString());
                        int idDisco = modeloSolicitud.extraerNumdisco(Integer.parseInt(lblIdSoli.getText()));
                        int numero =  modeloDisco.numeroDisco(idDisco);
                        int anio = modeloDisco.anioDisco(idDisco);
                        String completo = numero+"-"+ anio;
                        txtDisco.setText(String.valueOf(completo));
                        if(tablaEntrega.getValueAt(fila, 5)==null)
                        {
                            fieldComentario.setText("Sin Comentario");
                        }
                        else{
                        fieldComentario.setText(tablaEntrega.getValueAt(fila, 5).toString());
                        }
                            
                    }

                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();              
            modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                             
                             
                             
                         }
                         
                     }
                 }
             });
}
    public void llenarTabla(JTable tablaD){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Sala");
        modelo.addColumn("Nombre Audiencia");
        tablaD.setModel(modelo);*/
        DefaultTableModel modelo = (DefaultTableModel)tablaD.getModel();        
       // System.out.println("numregistros:"+numRegistros);
        try{
            String sql="SELECT a.FECHA, a.HORA, a.SALA, c.NOMBRE, a.COMENTARIO, u.Nombre FROM audiencia as a INNER JOIN catalogo_audiencia as c ON a.NOMBREA = c.ID LEFT OUTER JOIN usuarios as u ON a.usuario_id=u.id_usuario WHERE a.CAUSA ='"+txtBuscar.getText()+"' AND a.ESTADO != 'CANCELADA' ORDER BY a.FECHA,a.HORA, a.SALA";
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
        }catch (SQLException ex){
                    System.err.println(ex.toString());
                }
        int numRegistros=modelo.getRowCount();
        if(numRegistros > 0){


           tablaD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tablaD.getSelectedRow() != -1){
                        int fila = tablaD.getSelectedRow();
                        txtFechaS.setText(tablaD.getValueAt(fila, 0).toString());
                        txtHoraS.setText(tablaD.getValueAt(fila, 1).toString());
                        txtSalaS.setText(tablaD.getValueAt(fila, 2).toString());
                        
                    }
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
                   String fecha = txtFechaS.getText();
                    String hora = txtHoraS.getText();
                    String sala = txtSalaS.getText();
                    String causa = txtBuscar.getText();
                    System.out.println("hora: "+hora+"fecha: "+fecha+"sala: "+sala+"causa: "+causa);
                    formAgregar.txtHora.setText(hora);
                    formAgregar.JDFechaParticipante.setText(fecha);
                    formAgregar.txtSala.setText(sala);
                    formAgregar.txtCausa.setText(causa);
                    int numAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    int numDisco =  modeloDisco.extraerIdDisco(numAudiencia,anio);
                    int anioDisco  = modeloDisco.extraerAnioDisco(numAudiencia);
                    int numConsolidacion = modeloAudiencia.extraConsolidacion(numAudiencia);
                    txtDiscoS.setText(String.valueOf(numDisco+"/"+anioDisco));
                    txtConsolidacionS.setText(String.valueOf(numConsolidacion));
                   //participantesAgregadosSolicitud(vista.tablaParticipantes);

                }
            }); 

            modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 4){
                             String fecha = txtFechaS.getText();
                             String hora = txtHoraS.getText();
                             String sala = txtSalaS.getText();
                             int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                             String comentario = tablaD.getValueAt(fila, columna).toString();
                             modeloAudiencia.actualizarComentario(audiencia, comentario);
                             
                             
                             
                         }
                         
                     }
                 }
             });
        } else {
            JOptionPane.showMessageDialog(null, "No existe la causa escrita");
        }
        
       
        
    }

    public void participantesAgregadosSolicitud(JTable tabla){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Copias solicitadas");
        tabla.setModel(modelo);*/
        DefaultTableModel modelo =  (DefaultTableModel)tabla.getModel();

      modelo.setRowCount(0);
        String fecha = txtFechaS.getText();
        String hora = txtHoraS.getText();
        String sala = txtSalaS.getText();
        int audiencia= modeloAudiencia.extraerId(fecha, hora, sala);
                try{
            String sql="SELECT participante.NOMBRE, participante.TIPO,aud_par.copias FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + audiencia + "' ORDER BY participante.TIPO, participante.NOMBRE";
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
            
            
        }catch (SQLException ex){
                    System.err.println(ex.toString());
                }
        //System.out.println(audiencia);
        
        
        
int numRegistros=modelo.getRowCount();
        if(numRegistros > 0){
                    


        }
        
        else {
            JOptionPane.showMessageDialog(null, "No existen participantes agregados a esta causa");
        }
   
         }

    public void limpiarCamposSolicitud(){
        txtEcausaS.setText("");
        txtEcopiasS.setText("");
        txtEfechaS.setText("");
        txtEfechaSoli.setText("");
        txtEhoraS.setText("");
        txtEhoraS.setText("");
        txtEjuezS.setText("");
        txtEnombreS.setText("");
        lblIdSoli.setText("");
        txtDisco.setText("");
        fieldComentario.setText("");
        //((JTextField)vista.JDfechaBuscar.getDateEditor().getUiComponent()).setText("");
        
    }
    public int numCosolidacion(int audiencia){
        int numero = 0;
        String sql = "SELECT CONSOLIDACION FROM audiencia WHERE idAudiencia = '"+audiencia+"'";
        Connection accesoBD = con.conectar();
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                numero = rs.getInt(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return numero;
    }

      public void generar(String disco, int consolidacion,String autorizados,
              String fecha_au, String fecha_soli, String nombre, String copias,
      String causa,String hora,int folio,String tipo,String sala,String comentario){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros = new HashMap();
            parametros.put("fecha_soli", fecha_soli);
            parametros.put("folio", folio);
            parametros.put("fecha_audiencia", fecha_au);
            parametros.put("disco", disco);
            parametros.put("causa", causa);
            parametros.put("hora", hora);
            parametros.put("sala", sala);
            parametros.put("nombre", nombre);
            parametros.put("tipo", tipo);
            parametros.put("copias", copias);
            parametros.put("consolidacion", consolidacion);
            parametros.put("autorizados", autorizados);
            
            parametros.put("comentario", comentario);
            
            String direccion = System.getProperty("user.dir")+ "\\reportes\\Solicitud.jasper";
            
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            //JasperReport reporte = JasperCompileManager.compileReport(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
          // JasperPrintManager.printReport(mostrar, true);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            //JasperViewer.viewReport(mostrar, false);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
 //Busqueda por cancelada
 public void llenarTablaSolicitud4(JTable tabla){
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha solicitud");
        modelo.addColumn("Hora solicitud");
        modelo.addColumn("Copias");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha_constancia");
        modelo.addColumn("Hora constancia");
        modelo.addColumn("Fecha_entrega");
        modelo.addColumn("Hora entrega");
        modelo.addColumn("Participante");
        modelo.addColumn("Tipo");
        tabla.setModel(modelo);*/
       DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='Cancelada' ORDER BY S.ID";
       String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='Cancelada' ORDER BY S.ID"; 
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
int numRegistro=modelo.getRowCount();
if(numRegistro>0)
{
    total_participantes.setText(String.valueOf(numRegistro));
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    total_participantes.setText(String.valueOf(numRegistro));
    JOptionPane.showMessageDialog(null, "No se encontraron solicitudes Canceladas en ese rango de fechas");
}
                       modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });  
        
        
    }     
      
//Busqueda por ROTULADO
 public void llenarTablaSolicitud3(JTable tabla){
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha solicitud");
        modelo.addColumn("Hora solicitud");
        modelo.addColumn("Copias");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha_constancia");
        modelo.addColumn("Hora constancia");
        modelo.addColumn("Fecha_entrega");
        modelo.addColumn("Hora entrega");
        modelo.addColumn("Participante");
        modelo.addColumn("Tipo");
        tabla.setModel(modelo);*/
       DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='ROTULADO' ORDER BY S.ID";
       String sql="SELECT S.ID,S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='ROTULADO' ORDER BY S.ID"; 
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
int numRegistro=modelo.getRowCount();
if(numRegistro>0)
{
    total_participantes.setText(String.valueOf(numRegistro));
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    total_participantes.setText(String.valueOf(numRegistro));
    JOptionPane.showMessageDialog(null, "No se encontraron solicitudes Rotulados en ese rango de fechas");
}
        
                       modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });
        
    }


//para busqueda por PENDIENTE
 public void llenarTablaSolicitud2(JTable tabla){
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha solicitud");
        modelo.addColumn("Hora solicitud");
        modelo.addColumn("Copias");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha_constancia");
        modelo.addColumn("Hora constancia");
        modelo.addColumn("Fecha_entrega");
        modelo.addColumn("Hora entrega");
        modelo.addColumn("Participante");
        modelo.addColumn("Tipo");
        tabla.setModel(modelo);*/
       DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='PENDIENTE' ORDER BY S.ID";
       String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO='PENDIENTE' ORDER BY S.ID";
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

int numRegistro=modelo.getRowCount();
if(numRegistro>0)
{
    total_participantes.setText(String.valueOf(numRegistro));
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    total_participantes.setText(String.valueOf(numRegistro));
    JOptionPane.showMessageDialog(null, "No se encontraron solicitudes Pendientes en ese rango de fechas");
}
                       modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });
  
    }



 public void llenarTablaSolicitud(JTable tabla){
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha solicitud");
        modelo.addColumn("Hora solicitud");
        modelo.addColumn("Copias");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha_constancia");
        modelo.addColumn("Hora constancia");
        modelo.addColumn("Fecha_entrega");
        modelo.addColumn("Hora entrega");
        modelo.addColumn("Participante");
        modelo.addColumn("Tipo");
        tabla.setModel(modelo);*/
       DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO !='Cancelada' ORDER BY S.ID";
       String sql="SELECT S.ID,S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE ,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO !='Cancelada' ORDER BY S.ID"; 
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
int numRegistro=modelo.getRowCount();
if(numRegistro>0)
{
    total_participantes.setText(String.valueOf(numRegistro));
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    total_participantes.setText(String.valueOf(numRegistro));
    JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas");
}
        //System.out.println(fecha);
        //System.out.println(fechaFinal);
                     // DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
               modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });
    }
 
 //FUNCION PARA LLENAR TABLA PARA MOSTRAR POR FECHA LA CONSTANCIA
 public void llenarTablaSolicitudConstancia(JTable tabla){
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
       /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha solicitud");
        modelo.addColumn("Hora solicitud");
        modelo.addColumn("Copias");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha_constancia");
        modelo.addColumn("Hora constancia");
        modelo.addColumn("Fecha_entrega");
        modelo.addColumn("Hora entrega");
        modelo.addColumn("Participante");
        modelo.addColumn("Tipo");
        tabla.setModel(modelo);*/
       DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
       try{
       //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO !='Cancelada' ORDER BY S.ID";
       //String sql="SELECT S.ID,S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE ,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO !='Cancelada' ORDER BY S.ID"; 
       String sql="SELECT S.ID,S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE ,A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia AS c on c.ID=A.NOMBREA WHERE str_to_date(substr(S.FECHA_CONSTANCIA,1,10),'%d-%m-%Y') BETWEEN '"+fecha+"' AND '"+fechaFinal+"' AND S.ESTADO !='Cancelada' ORDER BY S.ID";
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
int numRegistro=modelo.getRowCount();
if(numRegistro>0)
{
    total_participantes.setText(String.valueOf(numRegistro));
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
}else{
    total_participantes.setText(String.valueOf(numRegistro));
    JOptionPane.showMessageDialog(null, "No se encontraron solicitudes en ese rango de fechas");
}
        //System.out.println(fecha);
        //System.out.println(fechaFinal);
                     // DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
               modelo.addTableModelListener(new TableModelListener() {
                 @Override
                 public void tableChanged(TableModelEvent e) {
                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 5){
                             int folio = Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());
                             String comentario = tablaEntrega.getValueAt(fila, 5).toString();
                             modeloSolicitud.actualizarComentarioSolicitud(folio, comentario);
                         }
                     }
                   //   JOptionPane.showMessageDialog(null, "Comentario actualizado");
                }
                
             });
    }
 
 
    public void llenarCombo (){
        
        ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarCombo();
        for(int i = 0; i< lista.size(); i++){
            comboAudiencia2.addItem(lista.get(i));
        }
    }
    public void limpiarCampos(){
        txtCausa2.setText("");
        ((JTextField) JDC2.getDateEditor().getUiComponent()).setText("");
    }
    public void limpiarTabla(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
       public void participantesAgregados(JTable tabla){
        String causa = jLabel101.getText();
        DefaultTableModel modelo = (DefaultTableModel) Tparticipantes2.getModel();
        Object [] columna = new Object[3];
        
        int numRegistros = modeloIntermedia.listConsulta(causa).size();
        if(numRegistros > 0){
            for (int i=0; i<numRegistros; i++){
            
            columna[0] = modeloIntermedia.listConsulta(causa).get(i).getNombre();
            columna[1] = modeloIntermedia.listConsulta(causa).get(i).getTipo();
            modelo.addRow(columna);
        }
        tabla.setModel(modelo);
        }
        else {
            JOptionPane.showMessageDialog(null, "No existen participantes agregados a esta causa");
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

        tSolicitud = new javax.swing.ButtonGroup();
        externas = new javax.swing.ButtonGroup();
        externas2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtPartes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAgregarParte = new javax.swing.JButton();
        comboTipo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboAudiencia = new javax.swing.JComboBox<>();
        JS1 = new javax.swing.JSpinner();
        btnRegistrar = new javax.swing.JButton();
        btnNuevaA = new javax.swing.JButton();
        JS2 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        JBCsalas = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        txtConsolidacion = new javax.swing.JTextField();
        txtCausa = new javax.swing.JTextField();
        JDC = new com.toedter.calendar.JDateChooser();
        InicioSesion = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel109 = new javax.swing.JLabel();
        tipo_audiencia = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblFa = new javax.swing.JLabel();
        lblhr = new javax.swing.JLabel();
        lblsala = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tparticipantes = new javax.swing.JTable();
        btnAgregarSeleccionados = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        lblParticipante = new javax.swing.JLabel();
        lblPrueba = new javax.swing.JLabel();
        btnEliminarP = new javax.swing.JButton();
        txtusuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAudiencias = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaParticipantes = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarAu = new javax.swing.JButton();
        btnLimpiarTodo = new javax.swing.JButton();
        btnIrAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtFechaS = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtHoraS = new javax.swing.JTextField();
        txtSalaS = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtDiscoS = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNombreS = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTipoS = new javax.swing.JTextField();
        txtCopias = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtConsolidacionS = new javax.swing.JTextField();
        lblIdIntermedia = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtcomentario = new javax.swing.JTextField();
        btnConsolidar = new javax.swing.JButton();
        btnActualizarP = new javax.swing.JButton();
        btncAnual = new javax.swing.JButton();
        btnIrRotular = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnConsultarP = new javax.swing.JButton();
        btnConsultaAutorizados = new javax.swing.JButton();
        btnPedirComentario = new javax.swing.JButton();
        video_player = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEntrega = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnBuscaSolicitud = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        JDfechaBuscar = new com.toedter.calendar.JDateChooser();
        JDfechaBuscarFinal = new com.toedter.calendar.JDateChooser();
        RadioBtnPendiente = new javax.swing.JRadioButton();
        RadioBtnRotulado = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        cancelada = new javax.swing.JRadioButton();
        btnBuscaSolicitud1 = new javax.swing.JButton();
        btnConstancia = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtEfechaS = new javax.swing.JTextField();
        txtEhoraS = new javax.swing.JTextField();
        txtEcausaS = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtEfechaSoli = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtEnombreS = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtEjuezS = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        lblIdSoli = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtEcopiasS = new javax.swing.JTextField();
        btnEntrega = new javax.swing.JButton();
        txtEtipoS = new javax.swing.JTextField();
        btnReporte = new javax.swing.JButton();
        txtDisco = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        lblImputadoS = new javax.swing.JLabel();
        lblDefensaS = new javax.swing.JLabel();
        lblFiscal = new javax.swing.JLabel();
        btnLimpiarS = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btnBuscaSolicitudCausa = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        CausaSolicitud = new javax.swing.JTextField();
        EstadosCombo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTipoAudiencia = new javax.swing.JTextField();
        btnImprimirFolios = new javax.swing.JButton();
        txtComentarioSoli = new javax.swing.JLabel();
        fieldComentario = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        BusquedaFolio = new javax.swing.JButton();
        txtBuscarFolio = new javax.swing.JTextField();
        conso_txt = new javax.swing.JLabel();
        total_participantes = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaSolicitudExterna = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        Racuerdo = new javax.swing.JRadioButton();
        Ramparo = new javax.swing.JRadioButton();
        btnSolicitudE = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        JDbuscarSolicitudE = new com.toedter.calendar.JDateChooser();
        JDbuscarSolicitudEFinal = new com.toedter.calendar.JDateChooser();
        EstadoComboExternas = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        JDbuscarSolicitudE1 = new com.toedter.calendar.JDateChooser();
        jLabel117 = new javax.swing.JLabel();
        JDbuscarSolicitudEFinal1 = new com.toedter.calendar.JDateChooser();
        jLabel118 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        btnConstanciaEx = new javax.swing.JButton();
        btnEntregaEx = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        lblFechas = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblHoras = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblCausa = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lblJueces = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblDiscoEx = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        num_audiencias = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblSolicitante = new javax.swing.JLabel();
        lblAcuerdo = new javax.swing.JLabel();
        lblCopiasEx = new javax.swing.JLabel();
        lblAmparo = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lblIdSolicitudEx = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnImpEntregasExt = new javax.swing.JButton();
        btnLimpiarSE = new javax.swing.JButton();
        ReimprimirExternas = new javax.swing.JButton();
        CancelarExternas = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaComentarioExterna = new javax.swing.JTable();
        Estado = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        BtnBuscarCausaExterna = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        txtCausaSoliExter = new javax.swing.JTextField();
        Racuerdo2 = new javax.swing.JRadioButton();
        Ramparo2 = new javax.swing.JRadioButton();
        EstadoComboExternas2 = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        txtFolioExterna = new javax.swing.JTextField();
        btnFolioExterna = new javax.swing.JButton();
        total_externas = new javax.swing.JLabel();
        total_discos = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaAudienciasGeneral = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablapartGeneral = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btngeneralBuscar = new javax.swing.JButton();
        btnReporteGeneral = new javax.swing.JButton();
        lblIdgeneral = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        lblTotalAu = new javax.swing.JLabel();
        btnLimpiarGeneral = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnExcelSimple = new javax.swing.JButton();
        JDinicial = new com.toedter.calendar.JDateChooser();
        JDfinal = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TablaComentario = new javax.swing.JTable();
        jLabel107 = new javax.swing.JLabel();
        salacombo = new javax.swing.JComboBox();
        jLabel108 = new javax.swing.JLabel();
        usuariocombo = new javax.swing.JComboBox();
        jLabel111 = new javax.swing.JLabel();
        tipo_aud = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        lblsala2 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        txtPartes2 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        btnAgregarParte2 = new javax.swing.JButton();
        comboTipo2 = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        comboAudiencia2 = new javax.swing.JComboBox<>();
        JS5 = new javax.swing.JSpinner();
        btnRegistrar2 = new javax.swing.JButton();
        JS6 = new javax.swing.JSpinner();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        JBCsalas2 = new javax.swing.JComboBox<>();
        jLabel99 = new javax.swing.JLabel();
        txtConsolidacion2 = new javax.swing.JTextField();
        txtCausa2 = new javax.swing.JTextField();
        JDC2 = new com.toedter.calendar.JDateChooser();
        InicioSesion1 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        tipo_audiencia2 = new javax.swing.JComboBox<>();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        lblhr2 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Tparticipantes2 = new javax.swing.JTable();
        jLabel102 = new javax.swing.JLabel();
        lblFa2 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        btnAgregarSeleccionados2 = new javax.swing.JButton();
        btnTerminar2 = new javax.swing.JButton();
        lblParticipante2 = new javax.swing.JLabel();
        lblPrueba2 = new javax.swing.JLabel();
        btnEliminarP2 = new javax.swing.JButton();
        txtusuario2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        num_causa_txt = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        minutos_txt = new javax.swing.JSpinner();
        jLabel75 = new javax.swing.JLabel();
        hora_txt = new javax.swing.JSpinner();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        fecha_audiencia_txt = new com.toedter.calendar.JDateChooser();
        jLabel78 = new javax.swing.JLabel();
        num_disco_txt = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        num_consolidacion_txt = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        imputado_txt = new javax.swing.JTextField();
        defensa_txt = new javax.swing.JTextField();
        fiscal_txt = new javax.swing.JTextField();
        generar_rotulo = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel105 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();
        jLabel113 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel114 = new javax.swing.JLabel();
        txtPartes3 = new javax.swing.JTextField();
        btnBuscarAu1 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        TablaPartAudiencia = new javax.swing.JTable();
        jLabel115 = new javax.swing.JLabel();
        totalaud = new javax.swing.JLabel();
        btnLimpiar1 = new javax.swing.JButton();
        idForanea = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        btnBuscaSolicitudCausaFora = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        CausaSolicitudForanea = new javax.swing.JTextField();
        comboEstadoForanea = new javax.swing.JComboBox<>();
        jPanel25 = new javax.swing.JPanel();
        btnBuscaSolicitudF = new javax.swing.JButton();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        JDfechaBuscarF = new com.toedter.calendar.JDateChooser();
        JDfechaBuscarFinalF = new com.toedter.calendar.JDateChooser();
        jPendienteF = new javax.swing.JRadioButton();
        jRotuladoF = new javax.swing.JRadioButton();
        jTodosF = new javax.swing.JRadioButton();
        jCanceladaF = new javax.swing.JRadioButton();
        btnBuscaSolicitud3 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        BusquedaFolioF = new javax.swing.JButton();
        txtBuscarFolioF = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaSolicitudForaneas = new javax.swing.JTable();
        totalSolicitudForaneas = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        txtHoraAudienciaF = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        txtFechaAudienciaF = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        txtCausaF = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        txtSolicitanteF = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        txtImputadoF = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        txtTipoSolicitanteF = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        txtCopiasF = new javax.swing.JTextField();
        btnConstanciaFora = new javax.swing.JButton();
        btnEntregaFora = new javax.swing.JButton();
        btnImprimirFoliosFora = new javax.swing.JButton();
        jLabel134 = new javax.swing.JLabel();
        txtIdFora = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        fechaSolicitudF = new javax.swing.JLabel();
        horaSolicitudF = new javax.swing.JLabel();
        CancelarForaneas = new javax.swing.JButton();
        btnLimpiarForaneo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1220, 1080));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(42767, 42767));
        jPanel3.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        txtPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartesActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Agregar  participantes");

        btnAgregarParte.setBackground(new java.awt.Color(0, 204, 51));
        btnAgregarParte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarParte.setText("Agregar");
        btnAgregarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarParteActionPerformed(evt);
            }
        });

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JUEZ", "FISCAL", "RLEGAL", "IMPUTADO", "VICTIMA", "DEFENSA", "ASESOR JURIDICO", "ESPECIALISTA", "TRADUCTOR" }));
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartes, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnAgregarParte, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarParte)
                .addGap(43, 43, 43))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Número causa:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Fecha audiencía:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Tipo audiencía:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Hora audiencía:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Número sala:");

        comboAudiencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        JS1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnRegistrar.setBackground(new java.awt.Color(102, 255, 102));
        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnNuevaA.setText("Nuevo tipo \naudiencia");
        btnNuevaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaAActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Horas");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Minutos");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setText("Agregar audiencia");

        JBCsalas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "HP" }));

        jLabel59.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel59.setText("Consolidación:");

        JDC.setDateFormatString("yyyy-MM-dd");

        InicioSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/inactivo.png"))); // NOI18N
        InicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioSesionActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(255, 51, 0));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel109.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel109.setText("TIPO:");

        tipo_audiencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presencial", "Videoconferencia" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel18)
                        .addGap(134, 134, 134))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel109))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(JDC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevaA)
                                .addGap(17, 17, 17))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipo_audiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JS1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JS2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(JBCsalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel59)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtConsolidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(comboAudiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtCausa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(JDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNuevaA)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboAudiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JS1)
                    .addComponent(jLabel7)
                    .addComponent(JS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(JBCsalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(txtConsolidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(tipo_audiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(InicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addContainerGap())
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setText("Participantes ya registrados de la causa:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        lblFa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblhr.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblsala.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel14.setText("Audiencía donde serán agregados los participantes:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel15.setText("Fecha");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setText("Hora");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setText("Sala");

        Tparticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tparticipantes);
        if (Tparticipantes.getColumnModel().getColumnCount() > 0) {
            Tparticipantes.getColumnModel().getColumn(0).setMaxWidth(250);
            Tparticipantes.getColumnModel().getColumn(1).setMaxWidth(150);
            Tparticipantes.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        btnAgregarSeleccionados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAgregarSeleccionados.setForeground(java.awt.Color.red);
        btnAgregarSeleccionados.setText("Agregar seleccionados");

        btnTerminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnTerminar.setForeground(java.awt.Color.red);
        btnTerminar.setText("Terminar audiencia");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        lblParticipante.setText("jLabel19");

        lblPrueba.setText("jLabel14");

        btnEliminarP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEliminarP.setForeground(java.awt.Color.red);
        btnEliminarP.setText("Eliminar seleccionado");
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtusuario))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrueba)
                            .addComponent(lblParticipante)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFa, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(lblhr, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblsala, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(btnAgregarSeleccionados)))))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblParticipante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrueba)
                        .addGap(270, 270, 270))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtusuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(lblhr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblsala, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregarSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(215, 215, 215))
        );

        jTabbedPane1.addTab("AUDIENCIAS", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaAudiencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Sala", "Tipo audiencia", "Comentario", "Usuario", "Tipo"
            }
        ));
        tablaAudiencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAudienciasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAudiencias);
        if (tablaAudiencias.getColumnModel().getColumnCount() > 0) {
            tablaAudiencias.getColumnModel().getColumn(0).setMinWidth(100);
            tablaAudiencias.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaAudiencias.getColumnModel().getColumn(0).setMaxWidth(100);
            tablaAudiencias.getColumnModel().getColumn(1).setMinWidth(80);
            tablaAudiencias.getColumnModel().getColumn(1).setPreferredWidth(80);
            tablaAudiencias.getColumnModel().getColumn(1).setMaxWidth(80);
            tablaAudiencias.getColumnModel().getColumn(2).setMinWidth(50);
            tablaAudiencias.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaAudiencias.getColumnModel().getColumn(2).setMaxWidth(50);
            tablaAudiencias.getColumnModel().getColumn(5).setMinWidth(200);
            tablaAudiencias.getColumnModel().getColumn(5).setPreferredWidth(200);
            tablaAudiencias.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        tablaParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Copias"
            }
        ));
        tablaParticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaParticipantesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaParticipantes);
        if (tablaParticipantes.getColumnModel().getColumnCount() > 0) {
            tablaParticipantes.getColumnModel().getColumn(0).setMaxWidth(300);
            tablaParticipantes.getColumnModel().getColumn(1).setMaxWidth(170);
            tablaParticipantes.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setText("Introduzca el número de causa");

        btnBuscarAu.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscarAu.setText("Buscar");
        btnBuscarAu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarAu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarAu)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnLimpiarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N

        btnIrAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/participante.png"))); // NOI18N
        btnIrAgregar.setToolTipText("Agregar participante a audiencia");
        btnIrAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/eliminarP.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar participante de audiencia");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtFechaS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel27.setText("Fecha:");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel28.setText("Hora:");

        txtHoraS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtSalaS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel29.setText("Sala:");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel30.setText("Disco generado:");

        txtDiscoS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiscoS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscoSActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel31.setText("Nombre:");

        txtNombreS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel32.setText("Tipo:");

        txtTipoS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtCopias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel25.setText("Copias:");

        jLabel60.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel60.setText("Consolidación:");

        txtConsolidacionS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblIdIntermedia.setText("jLabel60");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel24.setText("Participantes que pueden solicitar");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel23.setText("Audiencias realizadas de la causa seleccionada");

        jLabel72.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel72.setText("COMENTARIO:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreS, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoraS, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSalaS, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtTipoS, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel25)
                        .addGap(26, 26, 26)
                        .addComponent(txtCopias, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscoS, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConsolidacionS, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(246, 246, 246))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIdIntermedia)
                .addGap(330, 330, 330))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(txtcomentario, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiscoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)
                        .addComponent(txtConsolidacionS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel28)
                        .addComponent(jLabel29)
                        .addComponent(txtFechaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoraS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSalaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txtCopias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(txtNombreS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)
                        .addComponent(txtTipoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdIntermedia)
                    .addComponent(jLabel72))
                .addGap(4, 4, 4)
                .addComponent(txtcomentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18))
        );

        btnConsolidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/consolidar.png"))); // NOI18N
        btnConsolidar.setToolTipText("Consolidar discos");

        btnActualizarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/actualizar.png"))); // NOI18N
        btnActualizarP.setToolTipText("Abrir para actualizar participante y/o audiencia\n");
        btnActualizarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPActionPerformed(evt);
            }
        });

        btncAnual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/calendario.png"))); // NOI18N
        btncAnual.setToolTipText("Corte anual");

        btnIrRotular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/rotular.png"))); // NOI18N
        btnIrRotular.setToolTipText("Rotular originales");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Hacer respaldo de BD");

        btnConsultarP.setText("Consultar");
        btnConsultarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarPActionPerformed(evt);
            }
        });

        btnConsultaAutorizados.setText("Consulta autorizados");

        btnPedirComentario.setText("Introducir autorizados");

        video_player.setBackground(new java.awt.Color(0, 204, 51));
        video_player.setText("Ver Video");
        video_player.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                video_playerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(video_player)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConsultarP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(125, 125, 125)
                                    .addComponent(btnPedirComentario)
                                    .addGap(43, 43, 43)
                                    .addComponent(btnConsultaAutorizados)
                                    .addGap(326, 326, 326)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLimpiarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIrAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsolidar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIrRotular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIrRotular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncAnual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConsolidar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIrAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConsultarP)
                            .addComponent(btnPedirComentario)
                            .addComponent(video_player)))
                    .addComponent(btnConsultaAutorizados, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(308, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AGREGAR DISCOS AUTORIZADOS", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaEntrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Fecha solicitud", "Hora solicitud", "Copias", "Estado", "Comentario", "Feha constancia", "Hora constancia", "Fecha entrega", "Hora entrega", "Participante", "Tipo", "Audiencia", "Causa", "Archivo"
            }
        ));
        tablaEntrega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntregaMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEntregaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEntrega);
        if (tablaEntrega.getColumnModel().getColumnCount() > 0) {
            tablaEntrega.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaEntrega.getColumnModel().getColumn(3).setPreferredWidth(40);
            tablaEntrega.getColumnModel().getColumn(4).setPreferredWidth(50);
            tablaEntrega.getColumnModel().getColumn(5).setPreferredWidth(100);
            tablaEntrega.getColumnModel().getColumn(6).setPreferredWidth(90);
            tablaEntrega.getColumnModel().getColumn(11).setPreferredWidth(90);
        }

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBuscaSolicitud.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitud.setText("Buscar");
        btnBuscaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitudActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel21.setText("Elija las fechas que desea buscar");

        jLabel62.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel62.setText("Inicio:");

        jLabel63.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel63.setText("Final:");

        JDfechaBuscar.setDateFormatString("yyyy-MM-dd");

        JDfechaBuscarFinal.setDateFormatString("yyyy-MM-dd");

        RadioBtnPendiente.setText("PENDIENTE");

        RadioBtnRotulado.setText("ROTULADO");

        jRadioButton1.setText("TODOS");

        cancelada.setText("CANCELADA");

        btnBuscaSolicitud1.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitud1.setText("Buscar (Constancia)");
        btnBuscaSolicitud1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitud1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(JDfechaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnBuscaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDfechaBuscarFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscaSolicitud1)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel21))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(RadioBtnPendiente)
                        .addGap(18, 18, 18)
                        .addComponent(RadioBtnRotulado)
                        .addGap(18, 18, 18)
                        .addComponent(cancelada)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(RadioBtnPendiente)
                    .addComponent(RadioBtnRotulado)
                    .addComponent(cancelada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JDfechaBuscarFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addComponent(JDfechaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaSolicitud1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        btnConstancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/constancia.png"))); // NOI18N
        btnConstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConstanciaActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel22.setText("Información de la audiencia");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel26.setText("Fecha audiencia :");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel35.setText("Hora audiencia:");

        jLabel36.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel36.setText("Causa:");

        txtEfechaS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtEhoraS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtEcausaS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel37.setText("Información de la solicitud");

        txtEfechaSoli.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel38.setText("Fecha:");

        txtEnombreS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel39.setText("Nombre:");

        txtEjuezS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel40.setText("Juez:");

        lblIdSoli.setText("jLabel41");

        jLabel41.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel41.setText("Copias:");

        txtEcopiasS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/entrega.png"))); // NOI18N
        btnEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaActionPerformed(evt);
            }
        });

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/reporte.png"))); // NOI18N

        txtDisco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel43.setText("Disco:");

        lblImputadoS.setText("imputado");

        lblDefensaS.setText("defensa");

        lblFiscal.setText("fiscal");

        btnLimpiarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiarS.setToolTipText("Limppiar pantalla");
        btnLimpiarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBuscaSolicitudCausa.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitudCausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btnBuscaSolicitudCausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitudCausaActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel69.setText("Buscar por Causa:");

        EstadosCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Entregados", "Pendiente", "Rotulado", "Cancelada" }));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CausaSolicitud))
                .addGap(18, 18, 18)
                .addComponent(EstadosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscaSolicitudCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscaSolicitudCausa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EstadosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CausaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setText("Tipo Audiencia");

        btnImprimirFolios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/imprimir.png"))); // NOI18N
        btnImprimirFolios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirFoliosActionPerformed(evt);
            }
        });

        txtComentarioSoli.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtComentarioSoli.setText("COMENTARIO");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel68.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel68.setText("BUSCAR FOLIO:");

        BusquedaFolio.setBackground(new java.awt.Color(0, 204, 51));
        BusquedaFolio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        BusquedaFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusquedaFolioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BusquedaFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BusquedaFolio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        conso_txt.setText("jLabel73");

        total_participantes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        total_participantes.setText("0");

        jLabel106.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel106.setText("TOTAL DE SOLICITUDES:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(23, 23, 23))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(lblFiscal)
                            .addGap(18, 18, 18)
                            .addComponent(lblImputadoS)
                            .addGap(18, 18, 18)
                            .addComponent(lblDefensaS)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEfechaSoli, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblIdSoli)
                                .addGap(18, 18, 18)
                                .addComponent(conso_txt))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtEfechaS, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEhoraS, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEcausaS, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEjuezS, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnombreS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEcopiasS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTipoAudiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(txtComentarioSoli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarS, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(312, 312, 312))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnImprimirFolios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel106)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(total_participantes))
                            .addComponent(txtEtipoS, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(jLabel22))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(jLabel37)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(jLabel33)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtEjuezS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(txtEfechaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEhoraS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEcausaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(txtDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(txtEfechaSoli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnombreS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txtEcopiasS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTipoAudiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiscal)
                    .addComponent(lblImputadoS)
                    .addComponent(lblDefensaS)
                    .addComponent(lblIdSoli)
                    .addComponent(conso_txt))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106)
                            .addComponent(total_participantes)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEtipoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComentarioSoli)
                            .addComponent(fieldComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImprimirFolios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConstancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SOLICITUDES PARTICIPANTES", jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        tablaSolicitudExterna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaSolicitudExterna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSolicitudExternaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaSolicitudExternaMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tablaSolicitudExterna);

        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setPreferredSize(new java.awt.Dimension(655, 200));

        Racuerdo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Racuerdo.setText("Acuerdo");

        Ramparo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ramparo.setText("Amparo");
        Ramparo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RamparoActionPerformed(evt);
            }
        });

        btnSolicitudE.setBackground(new java.awt.Color(0, 204, 51));
        btnSolicitudE.setText("Buscar");
        btnSolicitudE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudEActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel42.setText("Elija la fecha a buscar");

        jLabel64.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel64.setText("Inicio:");

        jLabel65.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel65.setText("Final:");

        JDbuscarSolicitudE.setForeground(new java.awt.Color(255, 255, 255));
        JDbuscarSolicitudE.setDateFormatString("yyyy-MM-dd");

        JDbuscarSolicitudEFinal.setForeground(new java.awt.Color(255, 255, 255));
        JDbuscarSolicitudEFinal.setDateFormatString("yyyy-MM-dd");

        EstadoComboExternas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Entregados", "Pendientes", "Rotulados", "Cancelados" }));

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel116.setText("Inicio:");

        JDbuscarSolicitudE1.setForeground(new java.awt.Color(255, 255, 255));
        JDbuscarSolicitudE1.setDateFormatString("yyyy-MM-dd");

        jLabel117.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel117.setText("Final:");

        JDbuscarSolicitudEFinal1.setForeground(new java.awt.Color(255, 255, 255));
        JDbuscarSolicitudEFinal1.setDateFormatString("yyyy-MM-dd");

        jLabel118.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel118.setText("Elija la fecha (fecha de constancia)");

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(Racuerdo))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDbuscarSolicitudE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDbuscarSolicitudEFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnSolicitudE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Ramparo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EstadoComboExternas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel118)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel116)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JDbuscarSolicitudE1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel117)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JDbuscarSolicitudEFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(127, 127, 127))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EstadoComboExternas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Racuerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ramparo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel116)
                                        .addComponent(JDbuscarSolicitudE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(JDbuscarSolicitudEFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel117))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel64)
                                        .addComponent(JDbuscarSolicitudE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(JDbuscarSolicitudEFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel65)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(btnSolicitudE)))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btnConstanciaEx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/constancia.png"))); // NOI18N
        btnConstanciaEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConstanciaExActionPerformed(evt);
            }
        });

        btnEntregaEx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/entrega.png"))); // NOI18N
        btnEntregaEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaExActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel44.setText("Información de la audiencia");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel46.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel46.setText("Fecha(s) audiencia:");

        lblFechas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFechas.setText(".");

        jLabel45.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel45.setText("Hora audiencia:");

        lblHoras.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblHoras.setText(".");

        jLabel47.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel47.setText("Causa:");

        lblCausa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCausa.setText(".");

        jLabel48.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel48.setText("Juez:");

        lblJueces.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblJueces.setText(".");

        jLabel54.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel54.setText("Disco(s):");

        lblDiscoEx.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDiscoEx.setText(".");

        jLabel112.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel112.setText("#");

        num_audiencias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        num_audiencias.setText(".");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCausa, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiscoEx, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_audiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblJueces, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(num_audiencias)
                        .addComponent(jLabel112))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFechas)
                        .addComponent(jLabel45)
                        .addComponent(lblHoras)
                        .addComponent(jLabel46)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lblCausa)
                    .addComponent(jLabel48)
                    .addComponent(lblJueces)
                    .addComponent(jLabel54)
                    .addComponent(lblDiscoEx))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSolicitante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSolicitante.setText(".");

        lblAcuerdo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAcuerdo.setText(".");

        lblCopiasEx.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCopiasEx.setText(".");

        lblAmparo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAmparo.setText(".");

        jLabel50.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel50.setText("Solicitante:");

        jLabel51.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel51.setText("Copias:");

        jLabel52.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel52.setText("Amparo:");

        jLabel53.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel53.setText("Acuerdo:");

        jLabel66.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel66.setText("ID:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdSolicitudEx, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCopiasEx)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addGap(67, 67, 67)
                .addComponent(lblAmparo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAcuerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdSolicitudEx, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(lblSolicitante)
                        .addComponent(jLabel51)
                        .addComponent(lblCopiasEx)
                        .addComponent(jLabel52)
                        .addComponent(lblAmparo)
                        .addComponent(jLabel53)
                        .addComponent(lblAcuerdo)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel49.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel49.setText("TOTAL DE SOLICITUDES:");

        btnImpEntregasExt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/reporte.png"))); // NOI18N
        btnImpEntregasExt.setToolTipText("Imprimir reporte de entregas");

        btnLimpiarSE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiarSE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSEActionPerformed(evt);
            }
        });

        ReimprimirExternas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/imprimir.png"))); // NOI18N
        ReimprimirExternas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReimprimirExternasActionPerformed(evt);
            }
        });

        CancelarExternas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/cancelar.png"))); // NOI18N
        CancelarExternas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarExternasActionPerformed(evt);
            }
        });

        tablaComentarioExterna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaComentarioExterna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaComentarioExternaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tablaComentarioExterna);

        Estado.setText("jLabel70");

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BtnBuscarCausaExterna.setBackground(new java.awt.Color(0, 204, 51));
        BtnBuscarCausaExterna.setForeground(new java.awt.Color(0, 204, 0));
        BtnBuscarCausaExterna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        BtnBuscarCausaExterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCausaExternaActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel70.setText("BUSCAR POR CAUSA:");

        Racuerdo2.setText("Acuerdo");

        Ramparo2.setText("Amparo");

        EstadoComboExternas2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Entregados", "Pendientes", "Rotulados", "Cancelados" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCausaSoliExter, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(Racuerdo2)
                                .addGap(28, 28, 28)
                                .addComponent(Ramparo2)))
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscarCausaExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EstadoComboExternas2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(EstadoComboExternas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Racuerdo2)
                            .addComponent(Ramparo2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCausaSoliExter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnBuscarCausaExterna, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel71.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel71.setText("FOLIO:");

        btnFolioExterna.setBackground(new java.awt.Color(0, 204, 51));
        btnFolioExterna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btnFolioExterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFolioExternaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel71)
                    .addComponent(txtFolioExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFolioExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFolioExterna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18)
                        .addComponent(txtFolioExterna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        total_externas.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        total_externas.setText("0");

        total_discos.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        total_discos.setText("0");

        jLabel136.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel136.setText("TOTAL DE DISCOS:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(92, 92, 92))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(Estado)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(405, 405, 405)
                                                        .addComponent(jLabel44))
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(244, 244, 244)
                                                        .addComponent(jLabel34))))
                                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReimprimirExternas, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEntregaEx, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnConstanciaEx, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_externas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel136)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_discos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelarExternas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImpEntregasExt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiarSE, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Estado))
                    .addComponent(jLabel34))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)))
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_discos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_externas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ReimprimirExternas)
                            .addComponent(btnConstanciaEx)
                            .addComponent(CancelarExternas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImpEntregasExt)
                            .addComponent(btnLimpiarSE))
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEntregaEx))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SOLICITUDES EXTERNAS", jPanel6);

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setPreferredSize(new java.awt.Dimension(1475, 800));

        tablaAudienciasGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NUM DISCO", "CAUSA", "FECHA", "HORA", "TIPO AUDIENCIA", "SALA", "#CONSOLIDACION"
            }
        ));
        tablaAudienciasGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAudienciasGeneralMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAudienciasGeneralMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tablaAudienciasGeneral);
        if (tablaAudienciasGeneral.getColumnModel().getColumnCount() > 0) {
            tablaAudienciasGeneral.getColumnModel().getColumn(0).setMaxWidth(90);
            tablaAudienciasGeneral.getColumnModel().getColumn(1).setMaxWidth(160);
            tablaAudienciasGeneral.getColumnModel().getColumn(2).setMaxWidth(120);
            tablaAudienciasGeneral.getColumnModel().getColumn(3).setMaxWidth(55);
            tablaAudienciasGeneral.getColumnModel().getColumn(4).setMaxWidth(200);
            tablaAudienciasGeneral.getColumnModel().getColumn(5).setMaxWidth(50);
            tablaAudienciasGeneral.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        jScrollPane7.setViewportView(tablapartGeneral);

        jLabel55.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel55.setText("AUDIENCIAS CELEBRADAS");

        jLabel56.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel56.setText("PARTICIPANTES EN AUDIENCIA");

        btngeneralBuscar.setBackground(new java.awt.Color(0, 204, 51));
        btngeneralBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btngeneralBuscar.setToolTipText("Buscar audiencias");
        btngeneralBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneralBuscarActionPerformed(evt);
            }
        });

        btnReporteGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/reporte.png"))); // NOI18N
        btnReporteGeneral.setToolTipText("Impimir reporte");

        lblIdgeneral.setText("jLabel57");

        jLabel57.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel57.setText("Fecha inicial");

        jLabel58.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel58.setText("Fecha final");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel61.setText("Total de audiencias listadas:");

        lblTotalAu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnLimpiarGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiarGeneral.setToolTipText("Limpiar pantalla");
        btnLimpiarGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarGeneralActionPerformed(evt);
            }
        });

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/excel.png"))); // NOI18N
        btnExcel.setToolTipText("Consulta compuesta");

        btnExcelSimple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/excel2.png"))); // NOI18N
        btnExcelSimple.setToolTipText("Consulta simple");

        JDinicial.setDateFormatString("yyyy-MM-dd");

        JDfinal.setDateFormatString("yyyy-MM-dd");

        jLabel67.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel67.setText("COMENTARIO");

        TablaComentario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(TablaComentario);

        jLabel107.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel107.setText("TIPO:");

        salacombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "HP", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16" }));

        jLabel108.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel108.setText("Usuario:");

        jLabel111.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel111.setText("SALA:");

        tipo_aud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "Presencial", "Videoconferencia" }));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel108)
                        .addGap(5, 5, 5)
                        .addComponent(usuariocombo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipo_aud, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salacombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(lblIdgeneral)
                                .addGap(131, 131, 131)
                                .addComponent(jLabel55))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JDinicial, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JDfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalAu, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btngeneralBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReporteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcelSimple))))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel55)
                                .addComponent(lblIdgeneral))
                            .addGap(39, 39, 39)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel58)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel57)
                                    .addComponent(lblTotalAu, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDinicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(JDfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btngeneralBuscar))
                    .addComponent(btnReporteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel)
                    .addComponent(btnExcelSimple))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salacombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel111))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel108)
                            .addComponent(usuariocombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107)
                            .addComponent(tipo_aud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CONSULTA GENERAL DE DISCOS", jPanel14);

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel86.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel86.setText("Hora");

        lblsala2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel87.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel87.setText("Sala");

        jLabel88.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel88.setText("Audiencía donde serán agregados los participantes:");

        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        txtPartes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartes2ActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel89.setText("Nombre");

        jLabel90.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel90.setText("Agregar  participantes");

        btnAgregarParte2.setBackground(new java.awt.Color(0, 204, 51));
        btnAgregarParte2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarParte2.setText("Agregar");
        btnAgregarParte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarParte2ActionPerformed(evt);
            }
        });

        comboTipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JUEZ", "FISCAL", "RLEGAL", "IMPUTADO", "VICTIMA", "DEFENSA", "ASESOR JURIDICO", "ESPECIALISTA", "TRADUCTOR" }));
        comboTipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addComponent(comboTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartes2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnAgregarParte2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(txtPartes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarParte2)
                .addGap(43, 43, 43))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        jLabel91.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel91.setText("Número causa:");

        jLabel92.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel92.setText("Fecha audiencía:");

        jLabel93.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel93.setText("Tipo audiencía:");

        jLabel94.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel94.setText("Hora audiencía:");

        jLabel95.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel95.setText("Número sala:");

        comboAudiencia2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        JS5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnRegistrar2.setBackground(new java.awt.Color(102, 255, 102));
        btnRegistrar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar2.setText("Registrar");
        btnRegistrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar2ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel96.setText("Horas");

        jLabel97.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel97.setText("Minutos");

        jLabel98.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel98.setText("Agregar audiencia");

        JBCsalas2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "HP" }));

        jLabel99.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel99.setText("Consolidación:");

        JDC2.setDateFormatString("yyyy-MM-dd");

        InicioSesion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/inactivo.png"))); // NOI18N
        InicioSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioSesion1ActionPerformed(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel110.setText("TIPO:");

        tipo_audiencia2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presencial", "Videoconferencia" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel91)
                        .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel93)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95)
                    .addComponent(jLabel110))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JS5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JS6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCausa2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDC2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(152, 152, 152)
                                .addComponent(InicioSesion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel98)
                                    .addComponent(comboAudiencia2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBCsalas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(tipo_audiencia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel99)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtConsolidacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnRegistrar2)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel91)
                            .addComponent(txtCausa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel92)
                            .addComponent(JDC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(InicioSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(comboAudiencia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JS5)
                    .addComponent(jLabel94)
                    .addComponent(JS6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96)
                    .addComponent(jLabel97))
                .addGap(2, 2, 2)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(JBCsalas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel99)
                        .addComponent(txtConsolidacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegistrar2))
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel110)
                        .addComponent(tipo_audiencia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel100.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel100.setText("Fecha");

        jLabel101.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        lblhr2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        Tparticipantes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(Tparticipantes2);
        if (Tparticipantes2.getColumnModel().getColumnCount() > 0) {
            Tparticipantes2.getColumnModel().getColumn(0).setMaxWidth(250);
            Tparticipantes2.getColumnModel().getColumn(1).setMaxWidth(150);
            Tparticipantes2.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        lblFa2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel103.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel103.setText("Participantes ya registrados de la causa:");

        btnAgregarSeleccionados2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAgregarSeleccionados2.setForeground(java.awt.Color.red);
        btnAgregarSeleccionados2.setText("Agregar seleccionados");
        btnAgregarSeleccionados2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSeleccionados2ActionPerformed(evt);
            }
        });

        btnTerminar2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnTerminar2.setForeground(java.awt.Color.red);
        btnTerminar2.setText("Terminar audiencia");
        btnTerminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminar2ActionPerformed(evt);
            }
        });

        lblParticipante2.setText("jLabel19");

        lblPrueba2.setText("jLabel14");

        btnEliminarP2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEliminarP2.setForeground(java.awt.Color.red);
        btnEliminarP2.setText("Eliminar seleccionado");
        btnEliminarP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarP2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtusuario2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel103)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrueba2)
                            .addComponent(lblParticipante2)))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTerminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminarP2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel100)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblFa2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel86)
                            .addGap(18, 18, 18)
                            .addComponent(lblhr2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel87)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnAgregarSeleccionados2))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(lblsala2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtusuario2)
                                .addGap(583, 583, 583))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(lblParticipante2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrueba2)
                                .addGap(376, 376, 376)))
                        .addGap(389, 389, 389))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel88)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(lblhr2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblsala2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel86)
                                .addComponent(jLabel87)))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnAgregarSeleccionados2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTerminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarP2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(314, 314, 314))))
        );

        jTabbedPane1.addTab("AUDIENCIAS 2", jPanel16);

        jLabel73.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel73.setText("NUMERO DE");

        jLabel74.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel74.setText("FECHA DE ");

        jLabel75.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel75.setText("Minutos");

        hora_txt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel76.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel76.setText("Horas");

        jLabel77.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel77.setText("HORA AUDIENCIA:");

        fecha_audiencia_txt.setDateFormatString("yyyy-MM-dd");

        jLabel78.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel78.setText("NUMERO DE");

        jLabel79.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel79.setText("NUMERO DE");

        jLabel80.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel80.setText("IMPUTADO:");

        jLabel81.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel81.setText("DEFENSA:");

        jLabel82.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel82.setText("FISCAL:");

        generar_rotulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/rotular.png"))); // NOI18N
        generar_rotulo.setToolTipText("Rotular originales");
        generar_rotulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generar_rotuloActionPerformed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel83.setText("CONSOLIDACION:");

        jLabel84.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel84.setText("DISCO:");

        jLabel85.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel85.setText("AUDIENCIA:");

        jLabel104.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel104.setText("CAUSA:");

        jLabel105.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel105.setText("Ingresa los datos para poder generar el rotulo:");

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel113.setText("BUSQUEDA DE PARTICPANTE ");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel114.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel114.setText("Nombre");

        txtPartes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPartes3ActionPerformed(evt);
            }
        });

        btnBuscarAu1.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscarAu1.setText("Buscar");
        btnBuscarAu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAu1ActionPerformed(evt);
            }
        });

        TablaPartAudiencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAUSA", "FECHA", "HORA", "SALA"
            }
        ));
        jScrollPane11.setViewportView(TablaPartAudiencia);

        jLabel115.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel115.setText("Total de audiencias listadas:");

        totalaud.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnLimpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel105)
                        .addGap(311, 311, 311)
                        .addComponent(jLabel113)
                        .addContainerGap())
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addGap(65, 65, 65)
                                .addComponent(defensa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78)
                                    .addComponent(jLabel84))
                                .addGap(45, 45, 45)
                                .addComponent(num_disco_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel79)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(num_consolidacion_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hora_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minutos_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel85)
                                    .addComponent(jLabel74))
                                .addGap(55, 55, 55)
                                .addComponent(fecha_audiencia_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addGap(53, 53, 53)
                                .addComponent(imputado_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(btnLimpiar)
                                .addGap(36, 36, 36)
                                .addComponent(generar_rotulo))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel104))
                                .addGap(55, 55, 55)
                                .addComponent(num_causa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addGap(81, 81, 81)
                                .addComponent(fiscal_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                            .addComponent(jSeparator9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator10))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGap(98, 98, 98)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel114)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtPartes3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarAu1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel22Layout.createSequentialGroup()
                                                .addComponent(jLabel115)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(totalaud, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(79, 79, 79)
                                        .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 383, Short.MAX_VALUE))))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(jLabel113))
                .addGap(5, 5, 5)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel114)
                                    .addComponent(txtPartes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarAu1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel115)
                                    .addComponent(totalaud, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane11))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel104)
                            .addComponent(num_causa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel74)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(fecha_audiencia_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hora_txt)
                            .addComponent(jLabel77)
                            .addComponent(minutos_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76)
                            .addComponent(jLabel75))
                        .addGap(9, 9, 9)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(num_disco_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel79)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(num_consolidacion_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(imputado_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(defensa_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(fiscal_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(generar_rotulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1))
                .addGap(410, 410, 410))
        );

        jTabbedPane1.addTab(" ROTULO/PARTICIPANTE", jPanel22);

        idForanea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), javax.swing.UIManager.getDefaults().getColor("ComboBox.selectionBackground"))); // NOI18N
        idForanea.setForeground(new java.awt.Color(255, 255, 204));
        idForanea.setAutoscrolls(true);

        jLabel119.setBackground(new java.awt.Color(204, 204, 204));
        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/logo-htsjpuebla650.png"))); // NOI18N

        jPanel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBuscaSolicitudCausaFora.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitudCausaFora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        btnBuscaSolicitudCausaFora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitudCausaForaActionPerformed(evt);
            }
        });

        jLabel120.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel120.setText("Buscar por Causa:");

        comboEstadoForanea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "ENTREGADOS", "PENDIENTE", "ROTULADO", "CANCELADA" }));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CausaSolicitudForanea))
                .addGap(18, 18, 18)
                .addComponent(comboEstadoForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscaSolicitudCausaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscaSolicitudCausaFora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEstadoForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CausaSolicitudForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBuscaSolicitudF.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitudF.setText("Buscar");
        btnBuscaSolicitudF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitudFActionPerformed(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel121.setText("Elija las fechas que desea buscar");

        jLabel122.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel122.setText("Inicio:");

        jLabel123.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel123.setText("Final:");

        JDfechaBuscarF.setDateFormatString("yyyy-MM-dd");

        JDfechaBuscarFinalF.setDateFormatString("yyyy-MM-dd");

        buttonGroup1.add(jPendienteF);
        jPendienteF.setText("PENDIENTE");

        buttonGroup1.add(jRotuladoF);
        jRotuladoF.setText("ROTULADO");

        buttonGroup1.add(jTodosF);
        jTodosF.setText("TODOS");

        buttonGroup1.add(jCanceladaF);
        jCanceladaF.setText("CANCELADA");

        btnBuscaSolicitud3.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscaSolicitud3.setText("Buscar (Constancia)");
        btnBuscaSolicitud3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaSolicitud3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(JDfechaBuscarF, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel123))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnBuscaSolicitudF, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDfechaBuscarFinalF, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscaSolicitud3)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel121))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTodosF)
                        .addGap(18, 18, 18)
                        .addComponent(jPendienteF)
                        .addGap(18, 18, 18)
                        .addComponent(jRotuladoF)
                        .addGap(18, 18, 18)
                        .addComponent(jCanceladaF)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTodosF)
                    .addComponent(jPendienteF)
                    .addComponent(jRotuladoF)
                    .addComponent(jCanceladaF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel122)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel123, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JDfechaBuscarFinalF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addComponent(JDfechaBuscarF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscaSolicitudF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaSolicitud3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel124.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel124.setText("BUSCAR FOLIO:");

        BusquedaFolioF.setBackground(new java.awt.Color(0, 204, 51));
        BusquedaFolioF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/buscar2.png"))); // NOI18N
        BusquedaFolioF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusquedaFolioFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarFolioF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BusquedaFolioF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BusquedaFolioF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel124)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarFolioF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel125.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel125.setText("TOTAL DE SOLICITUDES:");

        tablaSolicitudForaneas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaSolicitudForaneas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSolicitudForaneasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaSolicitudForaneasMousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(tablaSolicitudForaneas);

        totalSolicitudForaneas.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        totalSolicitudForaneas.setText("0");

        jLabel126.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel126.setText("Fecha Audiencia:");

        txtHoraAudienciaF.setEditable(false);

        jLabel127.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel127.setText("Hora Audiencia:");

        txtFechaAudienciaF.setEditable(false);

        jLabel128.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel128.setText("Causa Audiencia:");

        txtCausaF.setEditable(false);

        jLabel129.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jLabel130.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel130.setText("Solicitante:");

        txtSolicitanteF.setEditable(false);

        jLabel131.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel131.setText("Imputado:");

        txtImputadoF.setEditable(false);

        jLabel132.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel132.setText("Tipo de Solicitante:");

        txtTipoSolicitanteF.setEditable(false);

        jLabel133.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel133.setText("Copias:");

        txtCopiasF.setEditable(false);

        btnConstanciaFora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/constanciaF.png"))); // NOI18N
        btnConstanciaFora.setToolTipText("Rotular/Constancia");
        btnConstanciaFora.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnConstanciaFora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConstanciaForaActionPerformed(evt);
            }
        });

        btnEntregaFora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/entregaF.png"))); // NOI18N
        btnEntregaFora.setToolTipText("Entregado");
        btnEntregaFora.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnEntregaFora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaForaActionPerformed(evt);
            }
        });

        btnImprimirFoliosFora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/imprimirF.png"))); // NOI18N
        btnImprimirFoliosFora.setToolTipText("Imprimir");
        btnImprimirFoliosFora.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnImprimirFoliosFora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirFoliosForaActionPerformed(evt);
            }
        });

        txtIdFora.setEditable(false);
        txtIdFora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdFora.setBorder(null);
        txtIdFora.setSelectedTextColor(new java.awt.Color(240, 240, 240));

        jLabel135.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel135.setText("Folio:");

        fechaSolicitudF.setText("jLabel136");

        horaSolicitudF.setText("jLabel136");

        CancelarForaneas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/cancelar.png"))); // NOI18N
        CancelarForaneas.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        CancelarForaneas.setMaximumSize(new java.awt.Dimension(57, 33));
        CancelarForaneas.setMinimumSize(new java.awt.Dimension(57, 33));
        CancelarForaneas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarForaneasActionPerformed(evt);
            }
        });

        btnLimpiarForaneo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VISTA/imagenes/limpiar.png"))); // NOI18N
        btnLimpiarForaneo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnLimpiarForaneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarForaneoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout idForaneaLayout = new javax.swing.GroupLayout(idForanea);
        idForanea.setLayout(idForaneaLayout);
        idForaneaLayout.setHorizontalGroup(
            idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idForaneaLayout.createSequentialGroup()
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idForaneaLayout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel119))
                    .addGroup(idForaneaLayout.createSequentialGroup()
                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, idForaneaLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel129, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, idForaneaLayout.createSequentialGroup()
                                        .addComponent(jLabel130)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSolicitanteF))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, idForaneaLayout.createSequentialGroup()
                                        .addComponent(btnImprimirFoliosFora, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnEntregaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnConstanciaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))
                                    .addGroup(idForaneaLayout.createSequentialGroup()
                                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel126)
                                            .addGroup(idForaneaLayout.createSequentialGroup()
                                                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtIdFora, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaAudienciaF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel127)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoraAudienciaF, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel128)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCausaF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel133)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCopiasF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel134))))
                    .addGroup(idForaneaLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addGap(366, 366, 366)
                                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(idForaneaLayout.createSequentialGroup()
                                        .addComponent(jLabel131)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtImputadoF, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel132))
                                    .addGroup(idForaneaLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel125)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalSolicitudForaneas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTipoSolicitanteF, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(idForaneaLayout.createSequentialGroup()
                                        .addComponent(CancelarForaneas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(btnLimpiarForaneo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(idForaneaLayout.createSequentialGroup()
                                .addComponent(fechaSolicitudF)
                                .addGap(57, 57, 57)
                                .addComponent(horaSolicitudF)))))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        idForaneaLayout.setVerticalGroup(
            idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idForaneaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdFora, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(txtHoraAudienciaF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel127)
                    .addComponent(txtFechaAudienciaF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128)
                    .addComponent(txtCausaF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133)
                    .addComponent(txtCopiasF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jLabel129)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(txtSolicitanteF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel131)
                    .addComponent(txtImputadoF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel132)
                    .addComponent(txtTipoSolicitanteF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel134)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, idForaneaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalSolicitudForaneas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(idForaneaLayout.createSequentialGroup()
                        .addComponent(btnImprimirFoliosFora, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(idForaneaLayout.createSequentialGroup()
                        .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiarForaneo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEntregaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConstanciaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CancelarForaneas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(idForaneaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaSolicitudF)
                    .addComponent(horaSolicitudF))
                .addContainerGap())
        );

        jTabbedPane1.addTab("SOLICITUDES FORANEAS", idForanea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarForaneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarForaneoActionPerformed
        // TODO LIMPIAR LOS DATOS DE LA VISTA FORANEA
        System.out.println("limpiar");
        txtIdFora.setText("");
        txtCausaF.setText("");
        txtSolicitanteF.setText("");
        txtImputadoF.setText("");
        txtTipoSolicitanteF.setText("");
        txtFechaAudienciaF.setText("");
        txtHoraAudienciaF.setText("");
        txtCopiasF.setText("");
    }//GEN-LAST:event_btnLimpiarForaneoActionPerformed

    private void CancelarForaneasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarForaneasActionPerformed
        // TODO PARA CANCELAR FORANEAS
        int numSolicitud = Integer.parseInt(txtIdFora.getText());
        String estado = "CANCELADA";
        int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar como Cancelada?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);

        if (dialogoResultado == 0) {
            modeloSolicitud.cancelacionForanea(numSolicitud,estado);
        } else {
            JOptionPane.showMessageDialog(null, "Ha cancelado la accion de marcar como cancelada ");
        }
    }//GEN-LAST:event_CancelarForaneasActionPerformed

    private void btnImprimirFoliosForaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirFoliosForaActionPerformed
        // TODO add your handling code here:
        String idForanea = txtIdFora.getText();
        String causaForanea = txtCausaF.getText();
        String solicitanteForanea = txtSolicitanteF.getText();
        String imputadoForanea = txtImputadoF.getText();
        String tipoSolicitanteForanea = txtTipoSolicitanteF.getText();
        String fechaAudienciaForanea = txtFechaAudienciaF.getText();
        String horaAudienicaForanea = txtHoraAudienciaF.getText();
        String copiasForanea = txtCopiasF.getText();
        String fechaSolicitudForanea = fechaSolicitudF.getText();
        String horaSolicitudForanea = horaSolicitudF.getText();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
        String fechaActual = fechaHora.format(date);
        SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = Hora.format(date);
        if(causaForanea.isEmpty() && imputadoForanea.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se selecciono la audiencia");
        } else {
            modeloSolicitud.generarSolicitudForanea(idForanea,causaForanea,solicitanteForanea,imputadoForanea,tipoSolicitanteForanea,fechaAudienciaForanea,horaAudienicaForanea,copiasForanea,fechaActual,horaActual,fechaSolicitudForanea,horaSolicitudForanea);
        }
        System.out.println(
            "id"+idForanea+
            "causa:"+causaForanea+
            "soli"+solicitanteForanea+
            "impu"+imputadoForanea+
            "tipoSoli"+tipoSolicitanteForanea+
            "fechaAud"+fechaAudienciaForanea+
            "horaAud"+horaAudienicaForanea+
            "copias"+copiasForanea+
            "hora Actual"+horaActual+
            "fech Actual"+fechaActual+
            "fecha Solicitud"+fechaSolicitudForanea+
            "hora solicitud"+horaSolicitudForanea);
    }//GEN-LAST:event_btnImprimirFoliosForaActionPerformed

    private void btnEntregaForaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaForaActionPerformed
        // TODO funcion para marcar como entregada la solicitud
        String idForanea = txtIdFora.getText();
        modeloSolicitud.actualizarEnteregadoSolicutudForanea(idForanea);
    }//GEN-LAST:event_btnEntregaForaActionPerformed

    private void btnConstanciaForaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConstanciaForaActionPerformed
        // TODO funcion para rotular y generar la constancia de FORANEOS
        java.util.Date date = new java.util.Date();
        String idForanea = txtIdFora.getText();
        String causaForanea = txtCausaF.getText();
        String solicitanteForanea = txtSolicitanteF.getText();
        String imputadoForanea = txtImputadoF.getText();
        String tipoSolicitanteForanea = txtTipoSolicitanteF.getText();

        String fechaAudienciaForanea = txtFechaAudienciaF.getText();

        String horaAudienicaForanea = txtHoraAudienciaF.getText();
        String copiasForanea = txtCopiasF.getText();

        SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
        String fechaActual = fechaHora.format(date);
        SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = Hora.format(date);
        String estado = "ROTULADO";
        System.out.println(
            "id:"+idForanea+
            "causa:"+causaForanea+
            "soli"+solicitanteForanea+
            "impu"+imputadoForanea+
            "tipoSoli"+tipoSolicitanteForanea+
            "fechaAud"+fechaAudienciaForanea+
            "horaAud"+horaAudienicaForanea+
            "copias"+copiasForanea+
            "hora Actual"+horaActual+
            "fech Actual"+fechaActual);

        if ( causaForanea.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "No se selecciono la audiencia");
        } else {
            int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea generar la constancia?", "Confirmación constancia", JOptionPane.OK_CANCEL_OPTION);
            if (dialogoResultado == 0) {
                String[] opciones = {"CANON","EPSON"};
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la impresora en la cual va a rotular", "seleccion de impresora", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (seleccion == 0) {
                    modeloSolicitud.actualizarSolicutudForanea(idForanea,horaActual,estado);//SOLO DIOS SABE QUE PASA ACA, SIN ESTE LLAMADO NO FUNCIONA EL SIGUIENTE
                    modeloSolicitud.constanciaForaneo(idForanea,causaForanea,solicitanteForanea,imputadoForanea,tipoSolicitanteForanea,fechaAudienciaForanea,horaAudienicaForanea,copiasForanea,fechaActual,horaActual);
                    modeloSolicitud.routarDiscoForaneo(idForanea,causaForanea,solicitanteForanea,imputadoForanea,tipoSolicitanteForanea,fechaAudienciaForanea,horaAudienicaForanea,copiasForanea,fechaActual,horaActual);
                    limpiarDatosFolioF();
                } else {
                    modeloSolicitud.actualizarSolicutudForanea(idForanea,horaActual,estado);//SOLO DIOS SABE QUE PASA ACA, SIN ESTE LLAMADO NO FUNCIONA EL SIGUIENTE
                    modeloSolicitud.constanciaForaneo(idForanea,causaForanea,solicitanteForanea,imputadoForanea,tipoSolicitanteForanea,fechaAudienciaForanea,horaAudienicaForanea,copiasForanea,fechaActual,horaActual);
                    modeloSolicitud.routarEpsonDiscoForaneo(idForanea,causaForanea,solicitanteForanea,imputadoForanea,tipoSolicitanteForanea,fechaAudienciaForanea,horaAudienicaForanea,copiasForanea,fechaActual,horaActual);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ha cancelado la elaboración ");
            }
        }
    }//GEN-LAST:event_btnConstanciaForaActionPerformed

    private void tablaSolicitudForaneasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSolicitudForaneasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSolicitudForaneasMousePressed

    private void tablaSolicitudForaneasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSolicitudForaneasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSolicitudForaneasMouseClicked

    private void BusquedaFolioFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusquedaFolioFActionPerformed
        // TODO boton para buscar el folio de la constancia
        String id = txtBuscarFolioF.getText();
        if (!id.isEmpty()) {
            buscarFolioForaneo(id);
        } else {
            JOptionPane.showMessageDialog(null, "Indique el folio","Folio vacio",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BusquedaFolioFActionPerformed

    private void btnBuscaSolicitud3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitud3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "EN PRODUCCION","ERROR",JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnBuscaSolicitud3ActionPerformed

    private void btnBuscaSolicitudFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitudFActionPerformed
        // TODO funcion para poder buscar por fechas
        String fechaInicial = ((JTextField)JDfechaBuscarF.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDfechaBuscarFinalF.getDateEditor().getUiComponent()).getText();
        String estado = "";
        if (jTodosF.isSelected()) {
            if (!(JDfechaBuscarF.getDate() == null && JDfechaBuscarFinalF.getDate() == null)) { //
                estado = jTodosF.getText();
                limpiarDatosFolioF();
                solicitudesXFechasForaneas(fechaInicial,fechaFinal,estado);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } else if (jPendienteF.isSelected()) {
            if (!(JDfechaBuscarF.getDate() == null && JDfechaBuscarFinalF.getDate() == null)) { //
                estado = jPendienteF.getText();
                limpiarDatosFolioF();
                solicitudesXFechasForaneas(fechaInicial,fechaFinal,estado);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } else if (jRotuladoF.isSelected()) {
            if (!(JDfechaBuscarF.getDate() == null && JDfechaBuscarFinalF.getDate() == null)) { //
                estado = jRotuladoF.getText();
                limpiarDatosFolioF();
                solicitudesXFechasForaneas(fechaInicial,fechaFinal,estado);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } else if (jCanceladaF.isSelected()) {
            if (!(JDfechaBuscarF.getDate() == null && JDfechaBuscarFinalF.getDate() == null)) { //
                estado = jCanceladaF.getText();
                limpiarDatosFolioF();
                solicitudesXFechasForaneas(fechaInicial,fechaFinal,estado);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscaSolicitudFActionPerformed

    private void btnBuscaSolicitudCausaForaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitudCausaForaActionPerformed
        // TODO add your handling code here:
        String causa = CausaSolicitudForanea.getText();
        String estado = comboEstadoForanea.getSelectedItem().toString();
        if (!causa.isEmpty()) {
            limpiarDatosFolioF();
            buscarCausaForaneos(causa, estado);
        } else {
            JOptionPane.showMessageDialog(null, "Indique la causa","Causa vacia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscaSolicitudCausaForaActionPerformed

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
        txtPartes3.setText("");
        totalaud.setText("");
        DefaultTableModel modelo= (DefaultTableModel)TablaPartAudiencia.getModel();
        modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnBuscarAu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAu1ActionPerformed
        // CODIGO PARA EXTRAR EL ID DEL PARTICIPANTE QUE SE VA A BUSCAR
        String cadena=txtPartes3.getText();
        ArrayList<Character> lista = new ArrayList<>();
        for(int i = 0; i< cadena.length(); i ++)
        {
            if(Character.isDigit(cadena.charAt(i)))
            lista.add(cadena.charAt(i));

        }
        for(int j = 0; j< lista.size();j ++)
        {
            // System.out.println(lista.get(j));
        }

        String idpart = "";

        for (Character s : lista)
        {
            idpart += s;
        }
        //System.out.println("id: "+idpart);
        // FIN DE OBTENER EL ID

        /// MOSTRAR LAS AUDIENCIAS RELACIONADAS CON EL PARTICIPANTE
        try{

            DefaultTableModel modelo= new DefaultTableModel();
            String sql="SELECT au.causa,au.fecha,au.hora,au.sala FROM audiencia au, aud_par aup WHERE aup.idAudiencia=au.idAudiencia and aup.idParticipante='"+idpart+"'";
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadcolumnas = rsMd.getColumnCount();
            modelo.addColumn("CAUSA");
            modelo.addColumn("FECHA");
            modelo.addColumn("HORA");
            modelo.addColumn("SALA");
            TablaPartAudiencia.setModel(modelo);
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
            int total = TablaPartAudiencia.getRowCount();
            if(total ==0){
                JOptionPane.showMessageDialog(null, "No existen audiencias relacionadas con ese participante");
            }
            else{
                totalaud.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

            }
        } catch (SQLException ex){
            System.err.println(ex.toString());
        }
        // FIN DE MOSTRAR LAS AUDIENCIAS
    }//GEN-LAST:event_btnBuscarAu1ActionPerformed

    private void txtPartes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartes3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartes3ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        num_causa_txt.setText("");
        hora_txt.setValue(0);
        minutos_txt.setValue(0);
        fecha_audiencia_txt.setCalendar(null);
        num_disco_txt.setText("");
        num_consolidacion_txt.setText("");
        imputado_txt.setText("");
        defensa_txt.setText("");
        fiscal_txt.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void generar_rotuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generar_rotuloActionPerformed

        if(num_causa_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de causa","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(fecha_audiencia_txt.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de la audiencia","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(num_disco_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de disco","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(num_consolidacion_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de la consolidacion","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(imputado_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el campo de imputado","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(defensa_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el campo de defensa","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(fiscal_txt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el campo de fiscal","ERROR",JOptionPane.ERROR_MESSAGE);
        }else{
            
        }
    }//GEN-LAST:event_generar_rotuloActionPerformed

    private void btnEliminarP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarP2ActionPerformed
        int filasafectadas=0;
        DefaultTableModel modelo = (DefaultTableModel)Tparticipantes2.getModel();
        int tamano = Tparticipantes2.getRowCount();
        for (int i = 0; i <tamano; i++) {
            Boolean estado = new Boolean(true);
            Boolean estadoColumna = Boolean.valueOf(Tparticipantes2.getValueAt(i, 2).toString());
            if(estadoColumna.equals(estado)){
                String nombre =Tparticipantes2.getValueAt(i, 0).toString();
                String tipo=Tparticipantes2.getValueAt(i, 1).toString();
                int idparticipante=modeloParticipante.extraerIdParticipanteExacto(nombre,tipo);
                filasafectadas=filasafectadas+modelointermedia.eliminarIntermedia(Integer.parseInt(lblPrueba2.getText()),idparticipante);
                Tparticipantes2.setValueAt(null,i,0);
                Tparticipantes2.setValueAt(null,i,1);
                Tparticipantes2.setValueAt(null,i,2);
            }

        }

        if(filasafectadas>1)
        {
            JOptionPane.showMessageDialog(null, "Participantes eliminados");
        }
        else if(filasafectadas==1)
        {
            JOptionPane.showMessageDialog(null, "Participante eliminado");
        }
        else{
            JOptionPane.showMessageDialog(null, "No se borro ningun participante");
        }
    }//GEN-LAST:event_btnEliminarP2ActionPerformed

    private void btnTerminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminar2ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel)Tparticipantes2.getModel();
        modelo.setRowCount(0);
        //            DefaultTableModel modelo2 = (DefaultTableModel)vista.tablaListener.getModel();
        //          modelo2.setRowCount(0);
        txtPartes2.setText("");
        lblPrueba2.setText("");
        lblParticipante2.setText("");
        txtCausa2.setText("");
        ((JTextField)JDC2.getDateEditor().getUiComponent()).setText("");
        modeloHora.setValue(0);
        modeloMinutos.setValue(0);
        lblFa2.setText("");
        lblhr2.setText("");
        lblsala2.setText("");
        jLabel101.setText("");
        txtCausa2.grabFocus();
        txtConsolidacion2.setText("");
        comboAudiencia2.setSelectedIndex(-1);
        JBCsalas2.setSelectedIndex(-1);
    }//GEN-LAST:event_btnTerminar2ActionPerformed

    private void btnAgregarSeleccionados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSeleccionados2ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel)Tparticipantes2.getModel();
        int tamano = Tparticipantes2.getRowCount();

        for (int i = 0; i < tamano; i++) {
            Object nombre = Tparticipantes2.getValueAt(i, 0);
            Object tipo = Tparticipantes2.getValueAt(i, 1);
            Boolean estado = new Boolean(true);
            Boolean estadoColumna = Boolean.valueOf(Tparticipantes2.getValueAt(i, 2).toString());

            if(estadoColumna.equals(estado)){

                System.out.print(estadoColumna);
                //JOptionPane.showMessageDialog(null,nombre + " "+ tipo);
                lblParticipante2.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombre.toString(),tipo.toString())));
                int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText()));
                if(participante > 0){
                    // JOptionPane.showMessageDialog(null, "Participante repetido "+nombre + " "+ tipo);
                } else {
                    //String rptaRegistro;
                    //rptaRegistro = (String)
                    modeloIntermedia.insertarIntermedia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText()));

                    /* if(rptaRegistro != null){
                        JOptionPane.showMessageDialog(null, rptaRegistro);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al insertar");
                    }*/
                }

            }

            /*else {
                System.out.print(vista.Tparticipantes.getValueAt(i, 3).toString() + ":0 "+ nombre);
            }*/
        }
        modelo.setRowCount(0);
        JOptionPane.showMessageDialog(null, "Participantes Agregados");
    }//GEN-LAST:event_btnAgregarSeleccionados2ActionPerformed

    private void InicioSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioSesion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InicioSesion1ActionPerformed

    private void btnRegistrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar2ActionPerformed

        if(txtCausa2.getText().isEmpty() || txtConsolidacion2.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Por favor ingrese los datos faltantes", "Campos vacios", JOptionPane.ERROR_MESSAGE);

        }

        else{
            if(txtusuario.getText().isEmpty())
            {

                JOptionPane.showMessageDialog(null, "Por favor inicie sesion en AUDIENCIAS 1", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else{

                hora = JS5.getValue().toString();
                minutos = JS6.getValue().toString();

                for(int i=0; i<10; i++){
                    if(JS5.getValue().equals(i)){
                        hora = "0" + JS5.getValue().toString();
                    }
                }

                for(int i=0; i<10; i++){
                    if(JS6.getValue().equals(i)){
                        minutos = "0" + JS6.getValue().toString();
                    }
                }

                completa = hora+ ":"+ minutos;
                String fecha = ((JTextField) JDC2.getDateEditor().getUiComponent()).getText();
                String hora = completa;
                String sala = JBCsalas2.getSelectedItem().toString();
                String ncausa = txtCausa2.getText().toUpperCase();
                String nombre = comboAudiencia2.getSelectedItem().toString();
                int audienciaRegistada = modeloAudiencia.extraerIdCanceladas(fecha, hora, sala);
                String consolidacion = txtConsolidacion2.getText();
                String numconsolidacion = modeloAudiencia.verificaNumConsolidacion(consolidacion);
                String estadoRotulacion="NO ROTULADO";
                //System.out.print("Número de consolidación "+numconsolidacion);

                String estado = "NO CELEBRADA";
                if (audienciaRegistada> 0 ){
                    JOptionPane.showMessageDialog(null, "Esta audiencia ya existe","Error al ingresar audiencia",JOptionPane.ERROR_MESSAGE);
                }
                else if (numconsolidacion != null){
                    JOptionPane.showMessageDialog(null, "El número de consolidación ya existe","Error al ingresar audiencia", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    //System.out.print(nombre);
                    int id = modeloCatalogo.extraerId(nombre);
                    int idusuario = usuarios.extraerId(txtusuario.getText());
                    String tipo=(String) tipo_audiencia2.getSelectedItem();
                    //System.out.print(id);
                    //se agrega la audiencia a la BD
                    String rpta = (String) modeloAudiencia.insertar(fecha, hora, sala, ncausa, id,estado,consolidacion,estadoRotulacion,idusuario,tipo);

                    // se proporciona informacion a usuario de  que audiencia se va a agregar los participantes
                    jLabel101.setText(txtCausa2.getText());
                    lblFa2.setText(fecha);
                    lblhr2.setText(completa);
                    lblsala2.setText(sala);
                    lblPrueba2.setText(String.valueOf(modeloAudiencia.extraerId(fecha, hora, sala)));

                    if(rpta != null){

                        JOptionPane.showMessageDialog(null, rpta);
                        limpiarCampos();
                        limpiarTabla(Tparticipantes2);
                        participantesAgregados(Tparticipantes2);// carga la tabla  de los participantes ya agregados a la causa
                        int tamano = Tparticipantes2.getRowCount();// obtiene el tamaño de la cantidad de registros
                        // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                        for(int i=0; i<tamano; i++){
                            Tparticipantes2.setValueAt(false, i, 2);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Datos erroneos, por favor verifique la información");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnRegistrar2ActionPerformed

    private void comboTipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipo2ActionPerformed
        textAutoAcompleter2.removeAll();
        String tipos=(String) comboTipo2.getSelectedItem();
        Statement st=null;
        ResultSet rs=null;
        try{
            Connection accesoBD = con.conectar();
            st=(Statement)accesoBD.createStatement();
            rs=st.executeQuery("SELECT DISTINCT NOMBRE FROM PARTICIPANTE WHERE TIPO='"+tipos+"'");
            //rs=st.executeQuery("SELECT NOMBRE FROM participante");

            while(rs.next()){

                textAutoAcompleter2.addItem(rs.getString("NOMBRE"));
            }

        }   catch(Exception de){
            JOptionPane.showMessageDialog(this,de.getMessage());
        }
    }//GEN-LAST:event_comboTipo2ActionPerformed

    private void btnAgregarParte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarParte2ActionPerformed
        if((txtPartes2.getText().isEmpty())){
            JOptionPane.showMessageDialog(null, "Por favor ingrese los datos faltantes", "Campos vacios", JOptionPane.ERROR_MESSAGE);
        }
        else{
            //vista.txtPartes.setText(null);
            //vista.jtipo.setText(null);
            String nombreP = txtPartes2.getText().toUpperCase();
            String tipoP =comboTipo2.getSelectedItem().toString();
            //String tipoP = vista.jtipo.getText();
            String nombre = modeloParticipante.verificarExistente(nombreP, tipoP);

            // String tipo = modeloParticipante.verificarTipo();

            if(nombre !=null){
                lblParticipante2.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP)));
                int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText()));
                System.out.println(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP));
                if (participante > 0){
                    JOptionPane.showMessageDialog(null, "Participante repetido en la audiencia ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String rptaRegistro2 = (String) modeloIntermedia.insertarIntermedia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText().toUpperCase()));
                    JOptionPane.showMessageDialog(null, rptaRegistro2);
                    limpiarTabla(Tparticipantes2);
                    participantesAgregados(Tparticipantes2);

                    int tamano = Tparticipantes2.getRowCount();// obtiene el tamaño de la cantidad de registros
                    // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                    for(int i=0; i<tamano; i++){
                        Tparticipantes2.setValueAt(false, i, 2);
                    }
                    txtPartes2.setText("");
                    textAutoAcompleter2.removeAll();
                    comboTipo2.setSelectedIndex(0);
                }

            }
            else{
                String rptaRegistro = (String) modeloParticipante.insertarParticipante(nombreP, tipoP);
                lblParticipante2.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP)));
                int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText()));
                System.out.print(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP));
                if(participante > 0){
                    JOptionPane.showMessageDialog(null, "Participante repetido en la audiencia ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                }else {
                    String rptaRegistro2 = (String) modeloIntermedia.insertarIntermedia(Integer.parseInt(lblPrueba2.getText()), Integer.parseInt(lblParticipante2.getText()));
                    JOptionPane.showMessageDialog(null, rptaRegistro2);
                    limpiarTabla(Tparticipantes2);
                    participantesAgregados(Tparticipantes2);

                    int tamano = Tparticipantes2.getRowCount();// obtiene el tamaño de la cantidad de registros
                    // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                    for(int i=0; i<tamano; i++){
                        Tparticipantes2.setValueAt(false, i, 2);
                    }
                    txtPartes2.setText("");
                    textAutoAcompleter2.removeAll();
                    comboTipo2.setSelectedIndex(0);
                }

            }

        }
    }//GEN-LAST:event_btnAgregarParte2ActionPerformed

    private void txtPartes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartes2ActionPerformed

    }//GEN-LAST:event_txtPartes2ActionPerformed

    private void btnLimpiarGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarGeneralActionPerformed

    private void btngeneralBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneralBuscarActionPerformed
        //Count_audiencias();
        String sala = (String) salacombo.getSelectedItem();
        String usuario = (String) usuariocombo.getSelectedItem();
        String tipo = (String) tipo_aud.getSelectedItem();
        System.out.println(""+tipo);
        String Finicio = ((JTextField)JDinicial.getDateEditor().getUiComponent()).getText();
        String Ffinal = ((JTextField)JDfinal.getDateEditor().getUiComponent()).getText();
        lblTotalAu.setText("");
        if (Finicio.isEmpty() || Ffinal.isEmpty()){
            JOptionPane.showMessageDialog(null, "Elija la fecha de inicio y fecha final","Fechas vacias",JOptionPane.ERROR_MESSAGE);

        }

        else{

            if(usuariocombo.getSelectedIndex()==-1 || usuariocombo.getSelectedItem().toString().isEmpty())
            {

                if(sala.equals("TODAS") && tipo.equals("TODAS"))
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        //String sql="SELECT DISTINCT(disco.numero),CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        //modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("Usuario");
                        modelo.addColumn("TIPO");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                }

                else if(sala.equals("TODAS") && (tipo =="Presencial" || tipo == "Videoconferencia"))
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.tipo='"+tipo+"' AND audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        // modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("TIPO");
                        modelo.addColumn("Usuario");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }

                }
                else if(!sala.equals("TODAS") && tipo=="TODAS")
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND audiencia.SALA = '"+sala+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        // modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("TIPO");
                        modelo.addColumn("Usuario");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }

                }
                else
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.tipo='"+tipo+"'  AND audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND audiencia.SALA = '"+sala+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        // modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("TIPO");
                        modelo.addColumn("Usuario");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }

                }
            }

            else
            {
                if(sala.equals("TODAS") && tipo=="TODAS" )
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND usuarios.Nombre='"+usuario+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        //modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("Usuario");
                        modelo.addColumn("TIPO");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                }
                else if(sala.equals("TODAS") && (tipo =="Presencial" || tipo == "Videoconferencia"))
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.tipo='"+tipo+"' AND audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND usuarios.Nombre='"+usuario+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        //modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("Usuario");
                        modelo.addColumn("TIPO");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                }
                else if(!sala.equals("TODAS") && (tipo =="Presencial" || tipo == "Videoconferencia"))
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.sala='"+sala+"' AND audiencia.tipo='"+tipo+"' AND audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND usuarios.Nombre='"+usuario+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        //modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("Usuario");
                        modelo.addColumn("TIPO");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                }
                else
                {
                    try
                    {
                        DefaultTableModel modelo = new DefaultTableModel();
                        String sql="SELECT CONCAT(disco.NUMERO,'-',disco.ANIO),"
                        + " audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,audiencia.duracion,catalogo_audiencia.NOMBRE,audiencia.SALA,"
                        + " audiencia.CONSOLIDACION, usuarios.Nombre, audiencia.tipo FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
                        + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia LEFT JOIN usuarios ON "
                        + " usuarios.id_usuario=audiencia.usuario_id WHERE audiencia.sala='"+sala+"'  AND audiencia.FECHA BETWEEN '"+Finicio+"' AND '"+Ffinal+"' AND usuarios.Nombre='"+usuario+"'"
                        + "ORDER BY disco.numero" ;
                        Connection accesoBD = con.conectar();
                        PreparedStatement pst = accesoBD.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadcolumnas = rsMd.getColumnCount();
                        //modelo.addColumn("#");
                        modelo.addColumn("N.DISCO");
                        modelo.addColumn("CAUSA");
                        modelo.addColumn("FECHA");
                        modelo.addColumn("HORA");
                        modelo.addColumn("DURACION");
                        modelo.addColumn("TIPO AUDIENCIA");
                        modelo.addColumn("SALA");
                        modelo.addColumn("#CONSOLIDACION");
                        modelo.addColumn("Usuario");
                        modelo.addColumn("TIPO");
                        tablaAudienciasGeneral.setModel(modelo);
                        int [] anchos = {15,35,10,5,5,90,3,7,20,50};
                        for(int x=0; x<cantidadcolumnas;x++)
                        {
                            tablaAudienciasGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                        }
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
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                        int total = tablaAudienciasGeneral.getRowCount();
                        if(total !=0)
                        {
                            lblTotalAu.setText(String.valueOf(total));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                }

            }
        }
    }//GEN-LAST:event_btngeneralBuscarActionPerformed

    private void tablaAudienciasGeneralMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAudienciasGeneralMousePressed
        if (evt.getClickCount() > 1) {
            Point point = evt.getPoint();
            int row = tablaAudienciasGeneral.rowAtPoint(point);
            int column = tablaAudienciasGeneral.columnAtPoint(point);
            TableModel model = tablaAudienciasGeneral.getModel();
            String causa = tablaAudienciasGeneral.getValueAt(row, 1).toString();
            String consolidacion= tablaAudienciasGeneral.getValueAt(row, 7).toString();
            String [] botones = { " Ver Video", "Agregar Duracion", "Cancelar" };
            if(tablaAudienciasGeneral.getValueAt(row, 4)==null)
            {
                int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);

                if(variable==1)
                {
                    String id=lblIdgeneral.getText();
                    String duracion =JOptionPane.showInputDialog(null,"Introduzca la duración\n"+"la Causa: "+causa+" y Consolidacion: "+consolidacion,null);

                    if (duracion==null)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                    }else{
                        //System.out.println("id: "+id+"duracion:"+duracion);
                        modeloAudiencia.actualizarAudienciaDuracion(Integer.parseInt(id), duracion);
                        tablaAudienciasGeneral.setValueAt(duracion, row, 4);
                    }
                }
                else if(variable==2)
                {
                    JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                }
                else
                {
                    // JFileChooser jf= new JFileChooser("\\\\10.27.46.12\\puebla-centro\\CONSOLIDACION\\"+consolidacion);
                        //FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter(".asf","asf");
                        //jf.setFileFilter(filtroImagen);
                        // int r= jf.showOpenDialog(this);
                        //if(r==JFileChooser.APPROVE_OPTION)
                        //  {
                            //  File archivo = jf.getSelectedFile();
                            // String nombre=jf.getName(archivo);
                            // System.out.println("nombre archivo:"+jf.getName(archivo));
                            reproducir_video("A_"+consolidacion);
                            //}

                    }
                }
                else
                {
                    int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);
                    if(variable==1)
                    {
                        String dura= tablaAudienciasGeneral.getValueAt(row, 4).toString();
                        String id=lblIdgeneral.getText();
                        String duracion =JOptionPane.showInputDialog(null,"Introduzca la duración\n"+"la Causa: "+causa+" y Consolidacion: "+consolidacion, dura);
                        if (duracion==null)
                        {
                            JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                        }
                        else
                        {
                            //System.out.println("id: "+id+"duracion:"+duracion);
                            modeloAudiencia.actualizarAudienciaDuracion(Integer.parseInt(id), duracion);
                            tablaAudienciasGeneral.setValueAt(duracion, row, 4);
                        }
                    }
                    else if(variable==2)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                    }
                    else
                    {
                        //   JFileChooser jf= new JFileChooser("\\\\10.27.46.12\\puebla-centro\\CONSOLIDACION\\"+consolidacion);
                            // FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter(".asf","asf");
                            //   jf.setFileFilter(filtroImagen);
                            //  int r= jf.showOpenDialog(this);
                            //  if(r==JFileChooser.APPROVE_OPTION)
                            //  {
                                //          File archivo = jf.getSelectedFile();
                                //        String nombre=jf.getName(archivo);
                                //      System.out.println("nombre archivo:"+jf.getName(archivo));
                                reproducir_video("A_"+consolidacion);
                                //        }

                        }
                    }

                }
    }//GEN-LAST:event_tablaAudienciasGeneralMousePressed

    private void tablaAudienciasGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAudienciasGeneralMouseClicked
        //lblIdgeneral.setVisible(true);
        int fila = tablaAudienciasGeneral.getSelectedRow();
        String causa = tablaAudienciasGeneral.getValueAt(fila, 1).toString();
        String fecha = tablaAudienciasGeneral.getValueAt(fila, 2).toString();
        String hora = tablaAudienciasGeneral.getValueAt(fila, 3).toString();
        String sala = tablaAudienciasGeneral.getValueAt(fila, 6).toString();
        int id = modeloAudiencia.extraerIdExacta(fecha, hora, sala,causa);
        lblIdgeneral.setText(String.valueOf(id));
        //lblIdgeneral.setText(causa);
        //historialParticipante(tablapartGeneral);        // TODO add your handling code here:
        try{
            DefaultTableModel modelo = new DefaultTableModel();
            String sql = "";
            sql = "SELECT participante.NOMBRE, participante.TIPO,aud_par.copias FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + id + "'  ORDER BY participante.TIPO, participante.NOMBRE";
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadcolumnas = rsMd.getColumnCount();
            modelo.addColumn("NOMBRE");
            modelo.addColumn("TIPO");
            modelo.addColumn("COPIAS");
            tablapartGeneral.setModel(modelo);
            int [] anchos = {100,15,1};
            for(int x=0; x<cantidadcolumnas;x++)
            {
                tablapartGeneral.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
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
        }catch (SQLException ex){
            System.err.println(ex.toString());
        }
        try{
            DefaultTableModel modelo = new DefaultTableModel();
            String sql = "";
            sql = "SELECT DISTINCT audiencia.COMENTARIO FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + id + "'";
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadcolumnas = rsMd.getColumnCount();
            modelo.addColumn("COMENTARIO");
            TablaComentario.setModel(modelo);
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
        }catch (SQLException ex){
            System.err.println(ex.toString());
        }

        DefaultTableModel modelo =  (DefaultTableModel)tablapartGeneral.getModel();
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() ==  TableModelEvent.UPDATE){
                    String fecha = (tablaAudienciasGeneral.getValueAt(fila,2).toString());
                    String hora = (tablaAudienciasGeneral.getValueAt(fila,3).toString());
                    String sala = (tablaAudienciasGeneral.getValueAt(fila,6).toString());
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    String nombre =(tablapartGeneral.getValueAt(fila,0).toString());
                    if(columna == 2){
                        String tipo = (tablapartGeneral.getValueAt(fila,1).toString());
                        int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                        //System.out.println("ID_AUDIENCIA: "+idAudiencia);
                        int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                        //System.out.println("ID_PARTICIPANTE: "+idParticipante);
                        int idIntermedia = modeloIntermedia.extraerId(idAudiencia, idParticipante);
                        //System.out.println("ID_INTERMEDIA: "+idIntermedia);

                        //System.out.println("TIPO: "+tipo);
                        //JOptionPane.showMessageDialog(null,"audiencia "+ idAudiencia + "participante "+idParticipante);
                        int copias = Integer.parseInt(tablapartGeneral.getValueAt(fila,columna).toString());
                        modeloIntermedia.actualizarTabla(idIntermedia, copias);

                    }
                }
                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }//GEN-LAST:event_tablaAudienciasGeneralMouseClicked

    private void btnFolioExternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolioExternaActionPerformed
        txtCausaSoliExter.setText("");
        if(txtFolioExterna.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese una folio","Sin folio",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int folio=Integer.parseInt(txtFolioExterna.getText());
            String tipo=ExtraerTipoSolicitud(folio);
            if(tipo.equals("ACUERDO"))
            {
                llenarTablaFolioExternaAcuerdo(folio);

            }
            else if(tipo.equals("AMPARO")) {
                llenarTablaFolioExternaAmparo(folio);
            }
            else if(tipo.equals("")){
                JOptionPane.showMessageDialog(null,"No existe ese folio");
            }

        }
    }//GEN-LAST:event_btnFolioExternaActionPerformed

    private void BtnBuscarCausaExternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCausaExternaActionPerformed
        txtFolioExterna.setText("");
        if(txtCausaSoliExter.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese una causa","Sin causa",JOptionPane.ERROR_MESSAGE);
        }     else{
            String causa=txtCausaSoliExter.getText();

            if(Racuerdo2.isSelected()){
                String estado="";
                int seleccion=EstadoComboExternas2.getSelectedIndex();
                if(seleccion==0)
                {
                    estado="todos";
                    llenarTablaAcuerdoCausa(estado,causa);
                }
                else if(seleccion==1)
                {
                    estado="Entregado";
                    llenarTablaAcuerdoCausa(estado,causa);
                }
                else if(seleccion==2)
                {
                    estado="PENDIENTE";
                    llenarTablaAcuerdoCausa(estado,causa);
                }
                else if(seleccion==3)
                {
                    estado="ROTULADO";
                    llenarTablaAcuerdoCausa(estado,causa);
                }
                else{
                    estado="Cancelada";
                    llenarTablaAcuerdoCausa(estado,causa);
                }

            }
            else{
                String estado="";
                int seleccion=EstadoComboExternas2.getSelectedIndex();
                if(seleccion==0)
                {
                    estado="todos";
                    llenarTablaAmparoCausa(estado,causa);
                }
                else if(seleccion==1)
                {
                    estado="Entregado";
                    llenarTablaAmparoCausa(estado,causa);
                }
                else if(seleccion==2)
                {
                    estado="PENDIENTE";
                    llenarTablaAmparoCausa(estado,causa);
                }
                else if(seleccion==3)
                {
                    estado="ROTULADO";
                    llenarTablaAmparoCausa(estado,causa);
                }
                else{
                    estado="Cancelada";
                    llenarTablaAmparoCausa(estado,causa);
                }

            }
        }
    }//GEN-LAST:event_BtnBuscarCausaExternaActionPerformed

    private void tablaComentarioExternaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaComentarioExternaMouseClicked
        DefaultTableModel modelo = (DefaultTableModel)tablaComentarioExterna.getModel();
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getType() == TableModelEvent.UPDATE){
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if(columna == 0){

                        // String fecha = txtFechaS.getText();
                        // String hora = txtHoraS.getText();
                        // String sala = txtSalaS.getText();
                        //int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                        //int id =(Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, columna).toString()));
                        int id=(Integer.parseInt(lblIdSolicitudEx.getText()));
                        String comentario = tablaComentarioExterna.getValueAt(fila, columna).toString();
                        modeloSolicitudE.actualizarComentarioExterna(id, comentario);

                    }

                }
            }
        });
    }//GEN-LAST:event_tablaComentarioExternaMouseClicked

    private void CancelarExternasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarExternasActionPerformed
        int folio = Integer.parseInt(lblIdSolicitudEx.getText());
        String estado = "Cancelada";
        int seleccion=EstadoComboExternas.getSelectedIndex();
        int seleccion2=EstadoComboExternas2.getSelectedIndex();
        String causa=txtCausaSoliExter.getText();
        int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar como Cancelada?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
        if(dialogoResultado == 0)
        {

            modeloSolicitudE.actualizarCancelacionExterna(folio,estado);
            if(txtCausaSoliExter.getText().isEmpty())
            {

                if(txtFolioExterna.getText().isEmpty())
                {

                    if(Racuerdo.isSelected())
                    {
                        if(seleccion==0)
                        {
                            estado="todos";
                            llenarTablaExternaAcuerdo(estado);
                        }
                        else if(seleccion==1)
                        {
                            estado="Entregado";
                            llenarTablaExternaAcuerdo(estado);
                        }
                        else if(seleccion==2)
                        {
                            estado="PENDIENTE";
                            llenarTablaExternaAcuerdo(estado);
                        }
                        else if(seleccion==3)
                        {
                            estado="ROTULADO";
                            llenarTablaExternaAcuerdo(estado);
                        }
                    }
                    else{
                        if(seleccion==0)
                        {
                            estado="todos";
                            llenarTablaExternaAmparo(estado);
                        }
                        else if(seleccion==1)
                        {
                            estado="Entregado";
                            llenarTablaExternaAmparo(estado);
                        }
                        else if(seleccion==2)
                        {
                            estado="PENDIENTE";
                            llenarTablaExternaAmparo(estado);
                        }
                        else if(seleccion==3)
                        {
                            estado="ROTULADO";
                            llenarTablaExternaAmparo(estado);
                        }
                    }

                }
                else{
                    String tipo=ExtraerTipoSolicitud(folio);
                    if(tipo.equals("ACUERDO"))
                    {
                        llenarTablaFolioExternaAcuerdo(folio);
                    }else{
                        llenarTablaFolioExternaAmparo(folio);
                    }
                }
            }
            else if(txtFolioExterna.getText().isEmpty()){

                if(Racuerdo2.isSelected())
                {
                    if(seleccion2==0)
                    {
                        estado="todos";
                        llenarTablaAcuerdoCausa(estado,causa);
                    }
                    else if(seleccion2==1)
                    {
                        estado="Entregado";
                        llenarTablaAcuerdoCausa(estado,causa);
                    }
                    else if(seleccion2==2)
                    {
                        estado="PENDIENTE";
                        llenarTablaAcuerdoCausa(estado,causa);
                    }
                    else if(seleccion2==3)
                    {
                        estado="ROTULADO";
                        llenarTablaAcuerdoCausa(estado,causa);
                    }
                }
                else{
                    if(seleccion2==0)
                    {
                        estado="todos";
                        llenarTablaAmparoCausa(estado,causa);
                    }
                    else if(seleccion2==1)
                    {
                        estado="Entregado";
                        llenarTablaAmparoCausa(estado,causa);
                    }
                    else if(seleccion2==2)
                    {
                        estado="PENDIENTE";
                        llenarTablaAmparoCausa(estado,causa);
                    }
                    else if(seleccion2==3)
                    {
                        estado="ROTULADO";
                        llenarTablaAmparoCausa(estado,causa);
                    }
                }
            }

        }

        else{
            JOptionPane.showMessageDialog(null,"Operacion Cancelada");
        }
    }//GEN-LAST:event_CancelarExternasActionPerformed

    private void ReimprimirExternasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReimprimirExternasActionPerformed
        int fila = tablaSolicitudExterna.getSelectedRow();
        int folio=0;
        String tipo="";
        if(fila!=-1)
        {
            tipo=(tablaSolicitudExterna.getValueAt(fila, 7).toString());
            System.out.println(""+tipo);
            if(tipo.equals("AMPARO"))
            {
                System.out.println("entre aqui en amaparo");

                //CODIGO AMAPARO INICIO
                folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                Connection accesoBD = con.conectar();
                JasperReport reporte=null;
                try {
                    Map parametros = new HashMap();
                    parametros.put("folio", folio);
                    String direccion = System.getProperty("user.dir")+ "\\reportes\\AmparoImpr.jasper";
                    reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
                    //JasperReport reporte = JasperCompileManager.compileReport(direccion);
                    JasperPrint mostrar = JasperFillManager.fillReport(reporte,parametros, accesoBD);
                    //JasperPrintManager.printReport(mostrar, false);
                    JasperViewer ver = new  JasperViewer(mostrar,false);
                    // ver.viewReport(mostrar, false);
                    ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    ver.setVisible(true);
                } catch (Exception e) {
                    System.out.print(e);
                }
                ///AMPARO FIN
            }else{

                System.out.println("aqui acuerdo");
                //ACUERDO INICIO
                folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                Connection accesoBD = con.conectar();
                JasperReport reporte=null;
                try {
                    Map parametros = new HashMap();
                    parametros.put("folio", folio);
                    String direccion = System.getProperty("user.dir")+ "\\reportes\\AcuerdoImpr.jasper";
                    reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
                    //JasperReport reporte = JasperCompileManager.compileReport(direccion);
                    JasperPrint mostrar = JasperFillManager.fillReport(reporte,parametros, accesoBD);
                    //JasperPrintManager.printReport(mostrar, false);
                    //JasperViewer ver = new  JasperViewer(mostrar);
                    //ver.viewReport(mostrar, false);
                    JasperViewer ver = new  JasperViewer(mostrar,false);
                    ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    ver.setVisible(true);
                } catch (Exception e) {
                    System.out.print(e);
                }
                //ACUERDO FIN
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la tabla","Sin seleccion",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ReimprimirExternasActionPerformed

    private void btnLimpiarSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSEActionPerformed
        DefaultTableModel modelo= (DefaultTableModel)tablaComentarioExterna.getModel();
        modelo.setRowCount(0);
        txtFolioExterna.setText("");
        txtCausaSoliExter.setText("");
        total_externas.setText("");
        total_discos.setText("");
        num_audiencias.setText("");
    }//GEN-LAST:event_btnLimpiarSEActionPerformed

    private void btnEntregaExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaExActionPerformed

        if(!"Entregado".equals(Estado.getText()))
        {

            if(!"PENDIENTE".equals(Estado.getText()))
            {
                java.util.Date date = new java.util.Date();
                SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
                String fechaCompleta = fechaHora.format(date);
                String estado = "Entregado";
                int numSolicitud = Integer.parseInt(lblIdSolicitudEx.getText());
                int seleccion=EstadoComboExternas.getSelectedIndex();
                int seleccion2=EstadoComboExternas2.getSelectedIndex();
                String causa=txtCausaSoliExter.getText();
                int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar la entrega?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
                if(dialogoResultado == 0){
                    if(txtCausaSoliExter.getText().isEmpty())
                    {
                        if(txtFolioExterna.getText().isEmpty())
                        {
                            if(Racuerdo.isSelected())
                            {
                                if(seleccion==0)
                                {
                                    modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                    estado="todos";
                                    llenarTablaExternaAcuerdo(estado);

                                }
                                else if(seleccion==3)
                                {
                                    modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                    estado="ROTULADO";
                                    llenarTablaExternaAcuerdo(estado);

                                }

                            }
                            else if(Ramparo.isSelected())
                            {
                                if(seleccion==0)
                                {
                                    modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                    estado="todos";
                                    llenarTablaExternaAmparo(estado);
                                }
                                else if(seleccion==3)
                                {
                                    modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                    estado="ROTULADO";
                                    llenarTablaExternaAmparo(estado);
                                }

                            }
                        }
                        else{
                            String tipo=ExtraerTipoSolicitud(numSolicitud);
                            if(tipo.equals("ACUERDO"))
                            {
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                llenarTablaFolioExternaAcuerdo(numSolicitud);
                            }else{
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                llenarTablaFolioExternaAmparo(numSolicitud);
                            }
                        }
                    }
                    else{
                        if(Racuerdo2.isSelected())
                        {
                            if(seleccion2==0)
                            {
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                estado="todos";
                                llenarTablaAcuerdoCausa(estado,causa);
                            }
                            else if(seleccion2==3)
                            {
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                estado="ROTULADO";
                                llenarTablaAcuerdoCausa(estado,causa);
                            }

                        }
                        else if(Ramparo2.isSelected())
                        {
                            if(seleccion2==0)
                            {
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                estado="todos";
                                llenarTablaAmparoCausa(estado,causa);
                            }
                            else if(seleccion2==3)
                            {
                                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                                estado="ROTULADO";
                                llenarTablaAmparoCausa(estado,causa);
                            }

                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ha cancelado la entrega");
                }
            }else{
                JOptionPane.showMessageDialog(null, "La solicitud necesita estar en estado rotulado para poder entregar","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }

        else {

            JOptionPane.showMessageDialog(null, "Esa solicitud ya ha sido Entregada","ERROR",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnEntregaExActionPerformed

    private void btnConstanciaExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConstanciaExActionPerformed

    }//GEN-LAST:event_btnConstanciaExActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fecha = ((JTextField)JDbuscarSolicitudE1.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)JDbuscarSolicitudEFinal1.getDateEditor().getUiComponent()).getText();
        if(JDbuscarSolicitudE1.getDate()==null || JDbuscarSolicitudEFinal1.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(Racuerdo.isSelected())
            {
                llenarTablaExternaAcuerdoConstancia();
            }
            else if (Ramparo.isSelected())
            {
                llenarTablaExternaAmparoConstancia();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSolicitudEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudEActionPerformed

        txtCausaSoliExter.setText("");
        txtFolioExterna.setText("");
        lblSolicitante.setText("");
        lblCopiasEx.setText("");
        lblAmparo.setText("");
        lblAcuerdo.setText("");
        lblIdSolicitudEx.setText("");

        if(JDbuscarSolicitudE.getDate()==null || JDbuscarSolicitudEFinal.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "ingrese las fechas por favor","ERROR",JOptionPane.ERROR_MESSAGE);
        }

        else{

            if(Racuerdo.isSelected())
            {
                String estado="";
                int seleccion=EstadoComboExternas.getSelectedIndex();
                if(seleccion==0)
                {
                    String tipo="ACUERDO";
                    //Count_externa(tipo);
                    estado="todos";
                    llenarTablaExternaAcuerdo(estado);
                }
                else if(seleccion==1)
                {
                    estado="Entregado";
                    System.out.println("Estado: "+estado);
                    llenarTablaExternaAcuerdo(estado);
                }
                else if(seleccion==2)
                {
                    estado="PENDIENTE";
                    llenarTablaExternaAcuerdo(estado);
                }
                else if(seleccion==3)
                {
                    estado="ROTULADO";
                    llenarTablaExternaAcuerdo(estado);
                }
                else{
                    estado="Cancelada";
                    llenarTablaExternaAcuerdo(estado);
                }
            }
            else if (Ramparo.isSelected()){
                String estado="";
                int seleccion=EstadoComboExternas.getSelectedIndex();
                if(seleccion==0)
                {
                    String tipo="AMPARO";
                    //Count_externa(tipo);
                    estado="todos";
                    llenarTablaExternaAmparo(estado);
                }
                else if(seleccion==1)
                {
                    estado="Entregado";
                    llenarTablaExternaAmparo(estado);
                }
                else if(seleccion==2)
                {
                    estado="PENDIENTE";
                    llenarTablaExternaAmparo(estado);
                }
                else if(seleccion==3)
                {
                    estado="ROTULADO";
                    llenarTablaExternaAmparo(estado);
                }
                else{
                    estado="Cancelada";
                    llenarTablaExternaAmparo(estado);
                }

            }
        }
    }//GEN-LAST:event_btnSolicitudEActionPerformed

    private void RamparoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RamparoActionPerformed

    }//GEN-LAST:event_RamparoActionPerformed

    private void tablaSolicitudExternaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSolicitudExternaMousePressed
        if (evt.getClickCount() > 1) {
            Point point = evt.getPoint();
            int row = tablaSolicitudExterna.rowAtPoint(point);
            int column = tablaSolicitudExterna.columnAtPoint(point);
            String ruta="";
            TableModel model = tablaSolicitudExterna.getModel();
            String participante = tablaSolicitudExterna.getValueAt(row, 8).toString();
            String folio= tablaSolicitudExterna.getValueAt(row, 0).toString();
            String bandera=modeloSolicitudE.extraerRuta(folio);
            //VALIDACION SI YA EXISTE UN ARCHIVO EN ESE FOLIO
            if(bandera==null)
            {
                String [] botones = { "Agregar PDF", "Cancelar" };
                int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);

                if(variable==0)
                {
                    //Choose file
                    JFileChooser choose = new JFileChooser();
                    choose.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
                    choose.showOpenDialog(null);
                    Path sourcePath = choose.getSelectedFile().toPath();
                    //get file extension
                    String[] parts = sourcePath.toString().split("\\.");
                    System.out.println(sourcePath);
                    System.out.println(Arrays.toString(parts));
                    System.out.println(parts.length);
                    String extension = parts[parts.length - 1];
                    //generate destination
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");

                    String filename;
                    Path destinationPath;
                    int index = 0;

                    do {
                        filename ="" +participante+ "__" + folio + "__"+ sdf.format(today) + "." + extension;
                        //filename ="" +participante+ "__" + folio + "." + extension;
                        //destinationPath = Paths.get("D:\\documents\\" + filename);
                            destinationPath=Paths.get("\\\\10.27.47.11\\ciav_cjp\\controlinterno\\files\\SolicitudE\\"+filename);
                                ruta=destinationPath.toString();
                                //destinationPath = Paths.get("C:\\Users\\ceo\\AppData\\Local\\Temp\\" + filename);
                                    System.out.println(destinationPath);
                                    index++;
                                }
                                while (destinationPath.toFile().exists());
                                //move file
                                try {
                                    Files.copy(
                                        sourcePath,
                                        destinationPath//,
                                        // since the destinationPath is unique, do not replace
                                        //                  StandardCopyOption.REPLACE_EXISTING,
                                        // works for moving file on the same drive
                                        //its basically a renaming of path
                                        //                  StandardCopyOption.ATOMIC_MOVE
                                    );
                                    //          JOptionPane.showMessageDialog(null, "file " + sourcePath.getFileName() + " moved");
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "falló al copiar para archivo: " + sourcePath.getFileName(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                                JOptionPane.showMessageDialog(null, "Se guardo con exito el archivo "+ sourcePath.getFileName());
                                //Guardar la ruta en la base de datos
                                ruta="\\\\10.27.47.11\\\\ciav_cjp\\\\controlinterno\\\\files\\\\SolicitudE\\\\"+filename;
                                int folio_int=parseInt(folio);
                                modeloSolicitudE.GuardarRutaSolicitud(folio_int, ruta);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(rootPane, "Esta solicitud con folio "+folio+" ya tiene un archivo pdf");
                            String [] botones = { "Ver PDF", "Agregar PDF", "Cancelar" };
                            int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);

                            if(variable==0)
                            {
                                //String nombreArchivo=""+participante+"__"+folio+".pdf";
                                String nombreArchivo=modeloSolicitudE.extraerRuta(folio);
                                System.out.println(""+nombreArchivo);
                                try {
                                    Desktop.getDesktop().open(new File(nombreArchivo));
                                } catch (IOException e1) {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "fallo al abrir el archivo: " ,
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                    System.exit(1);

                                }
                            }
                            else if(variable==1)
                            {
                                //Choose file
                                JFileChooser choose = new JFileChooser();
                                choose.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
                                choose.showOpenDialog(null);
                                Path sourcePath = choose.getSelectedFile().toPath();
                                //get file extension
                                String[] parts = sourcePath.toString().split("\\.");
                                System.out.println(sourcePath);
                                System.out.println(Arrays.toString(parts));
                                System.out.println(parts.length);
                                String extension = parts[parts.length - 1];
                                //generate destination
                                Date today = Calendar.getInstance().getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");

                                String filename;
                                Path destinationPath;
                                int index = 0;

                                do {
                                    filename ="" +participante+ "__" + folio + "__"+ sdf.format(today) + "." + extension;
                                    //filename ="" +participante+ "__" + folio + "." + extension;
                                    //destinationPath = Paths.get("D:\\documents\\" + filename);
                                        destinationPath=Paths.get("\\\\10.27.47.11\\ciav_cjp\\controlinterno\\files\\SolicitudE\\"+filename);
                                            //destinationPath = Paths.get("C:\\Users\\ceo\\AppData\\Local\\Temp\\" + filename);
                                                System.out.println(destinationPath);
                                                index++;
                                            }
                                            while (destinationPath.toFile().exists());
                                            //move file
                                            try {
                                                Files.copy(
                                                    sourcePath,
                                                    destinationPath//,
                                                    // since the destinationPath is unique, do not replace
                                                    //                  StandardCopyOption.REPLACE_EXISTING,
                                                    // works for moving file on the same drive
                                                    //its basically a renaming of path
                                                    //                  StandardCopyOption.ATOMIC_MOVE
                                                );
                                                //          JOptionPane.showMessageDialog(null, "file " + sourcePath.getFileName() + " moved");
                                            } catch (IOException e) {
                                                // TODO Auto-generated catch block
                                                JOptionPane.showMessageDialog(
                                                    null,
                                                    "falló al copiar para archivo: " + sourcePath.getFileName(),
                                                    "Error",
                                                    JOptionPane.ERROR_MESSAGE
                                                );
                                                e.printStackTrace();
                                                System.exit(1);
                                            }
                                            JOptionPane.showMessageDialog(null, "Se guardo con exito el archivo "+ sourcePath.getFileName());
                                            //Guardar la ruta en la base de datos
                                            ruta="\\\\10.27.47.11\\\\ciav_cjp\\\\controlinterno\\\\files\\\\SolicitudE\\\\"+filename;
                                            int folio_int=parseInt(folio);
                                            modeloSolicitudE.GuardarRutaSolicitud(folio_int, ruta);
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                                        }
                                    }//FIN DE ELSE CUANDO YA EXISTE UN ARCHIVO PDF
                                }
    }//GEN-LAST:event_tablaSolicitudExternaMousePressed

    private void tablaSolicitudExternaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSolicitudExternaMouseClicked

        if(txtCausaSoliExter.getText().isEmpty())
        {
            if(Ramparo.isSelected())
            {
                int fila = tablaSolicitudExterna.getSelectedRow();
                Estado.setText(tablaSolicitudExterna.getValueAt(fila, 4).toString());
                lblIdSolicitudEx.setText(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                lblCopiasEx.setText(tablaSolicitudExterna.getValueAt(fila, 3).toString());
                lblAmparo.setText(tablaSolicitudExterna.getValueAt(fila, 9).toString());
                lblSolicitante.setText(tablaSolicitudExterna.getValueAt(fila, 8).toString());
                String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblCausa.setText(causa);
                String juez = modeloSolicitudE.extJuez(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblJueces.setText(juez);
                int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                //se obtienen las fechas de las audiencias solicitadas
                Object  [] fechas = new Object[numRegistros];
                for(int i = 0; i< numRegistros; i++)
                {
                    fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getFecha();

                }
                num_audiencias.setText(String.valueOf(numRegistros));
                // se muestran las fechas de audiencias en los labels
                lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+",");
                //se obtienen las horas de las audiencias solicitadas
                int numRegistrosHora = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                Object  [] horas = new Object[numRegistrosHora];
                for(int i = 0; i< numRegistrosHora; i++)
                {
                    horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getHora();

                }
                // se muestran las fechas de audiencias en los labels
                lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                int numRegistrosDisco = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                String [] numero = new String[numRegistrosDisco];
                String [] anio = new String [numRegistrosDisco];
                Object [] completoA =  new Object[numRegistrosDisco];
                String v_numero;
                String v_anio;
                String completo="";
                System.out.println("TAMAÑO " + numRegistrosDisco);
                for(int i=0; i<numRegistrosDisco; i++){
                    numero [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getNumero();
                    anio [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getAnio();
                    v_numero = numero[i];
                    v_anio =  anio[i];
                    completo = v_numero +"-"+ v_anio;
                    completoA[i] = completo;
                    // System.out.println("Array completo " + completoA[i]);

                }
                lblDiscoEx.setText(Arrays.toString(completoA).replace("[","").replace("]", "")+",");
                int folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                try{
                    DefaultTableModel modelo = new DefaultTableModel();
                    String sql = "";
                    sql = "SELECT comentario FROM SOLICITUD_EXTERNA WHERE TIPO = 'AMPARO' AND ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    modelo.addColumn("COMENTARIO");
                    tablaComentarioExterna.setModel(modelo);
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
                }catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }
            else if(Racuerdo.isSelected())
            {
                int fila = tablaSolicitudExterna.getSelectedRow();
                Estado.setText(tablaSolicitudExterna.getValueAt(fila, 4).toString());
                lblIdSolicitudEx.setVisible(true);
                lblIdSolicitudEx.setText(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                lblCopiasEx.setText(tablaSolicitudExterna.getValueAt(fila, 3).toString());
                lblAcuerdo.setText(tablaSolicitudExterna.getValueAt(fila, 10).toString());
                lblSolicitante.setText(tablaSolicitudExterna.getValueAt(fila, 8).toString());
                String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblCausa.setText(causa);
                String juez = modeloSolicitudE.extJuez(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblJueces.setText(juez);

                int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                //se obtienen las fechas de las audiencias solicitadas
                Object  [] fechas = new Object[numRegistros];
                for(int i = 0; i< numRegistros; i++)
                {
                    fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getFecha();

                }
                num_audiencias.setText(String.valueOf(numRegistros));
                // se muestran las fechas de audiencias en los labels
                lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+",");

                //se obtienen las horas de las audiencias solicitadas
                int numRegistrosHora = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                Object  [] horas = new Object[numRegistrosHora];
                for(int i = 0; i< numRegistrosHora; i++)
                {
                    horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getHora();

                }
                // se muestran las horas de audiencias en los labels
                lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                int numRegistrosDisco = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                String [] numero = new String[numRegistrosDisco];
                String [] anio = new String [numRegistrosDisco];
                Object [] completoA =  new Object[numRegistrosDisco];
                String v_numero;
                String v_anio;
                String completo="";
                System.out.println("TAMAÑO " + numRegistrosDisco);
                for(int i=0; i<numRegistrosDisco; i++){
                    numero [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getNumero();
                    anio [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getAnio();
                    v_numero = numero[i];
                    v_anio =  anio[i];
                    completo = v_numero +"-"+ v_anio;
                    completoA[i] = completo;
                    //System.out.println("Array completo " + completoA[i]);

                }
                lblDiscoEx.setText(Arrays.toString(completoA).replace("[","").replace("]", "")+",");
                int folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                try{
                    DefaultTableModel modelo = new DefaultTableModel();
                    String sql = "";
                    sql = "SELECT comentario FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    modelo.addColumn("COMENTARIO");
                    tablaComentarioExterna.setModel(modelo);
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
                }catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }
        }
        else{
            if(Ramparo2.isSelected())
            {
                int fila = tablaSolicitudExterna.getSelectedRow();
                Estado.setText(tablaSolicitudExterna.getValueAt(fila, 4).toString());
                lblIdSolicitudEx.setText(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                lblCopiasEx.setText(tablaSolicitudExterna.getValueAt(fila, 3).toString());
                lblAmparo.setText(tablaSolicitudExterna.getValueAt(fila, 9).toString());
                lblSolicitante.setText(tablaSolicitudExterna.getValueAt(fila, 8).toString());
                String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblCausa.setText(causa);
                String juez = modeloSolicitudE.extJuez(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblJueces.setText(juez);
                int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                //se obtienen las fechas de las audiencias solicitadas
                Object  [] fechas = new Object[numRegistros];
                for(int i = 0; i< numRegistros; i++)
                {
                    fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getFecha();

                }
                num_audiencias.setText(String.valueOf(numRegistros));
                // se muestran las fechas de audiencias en los labels
                lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+",");
                //se obtienen las horas de las audiencias solicitadas
                int numRegistrosHora = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                Object  [] horas = new Object[numRegistrosHora];
                for(int i = 0; i< numRegistrosHora; i++)
                {
                    horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getHora();

                }
                // se muestran las fechas de audiencias en los labels
                lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                int numRegistrosDisco = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                String [] numero = new String[numRegistrosDisco];
                String [] anio = new String [numRegistrosDisco];
                Object [] completoA =  new Object[numRegistrosDisco];
                String v_numero;
                String v_anio;
                String completo="";
                System.out.println("TAMAÑO " + numRegistrosDisco);
                for(int i=0; i<numRegistrosDisco; i++){
                    numero [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getNumero();
                    anio [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getAnio();
                    v_numero = numero[i];
                    v_anio =  anio[i];
                    completo = v_numero +"-"+ v_anio;
                    completoA[i] = completo;
                    // System.out.println("Array completo " + completoA[i]);

                }
                lblDiscoEx.setText(Arrays.toString(completoA).replace("[","").replace("]", "")+",");
                int folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                try{
                    DefaultTableModel modelo = new DefaultTableModel();
                    String sql = "";
                    sql = "SELECT comentario FROM SOLICITUD_EXTERNA WHERE TIPO = 'AMPARO' AND ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    modelo.addColumn("COMENTARIO");
                    tablaComentarioExterna.setModel(modelo);
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
                }catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }
            else if(Racuerdo2.isSelected())
            {
                int fila = tablaSolicitudExterna.getSelectedRow();
                Estado.setText(tablaSolicitudExterna.getValueAt(fila, 4).toString());
                lblIdSolicitudEx.setVisible(true);
                lblIdSolicitudEx.setText(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                lblCopiasEx.setText(tablaSolicitudExterna.getValueAt(fila, 3).toString());
                lblAcuerdo.setText(tablaSolicitudExterna.getValueAt(fila, 10).toString());
                lblSolicitante.setText(tablaSolicitudExterna.getValueAt(fila, 8).toString());
                String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblCausa.setText(causa);
                String juez = modeloSolicitudE.extJuez(Integer.parseInt(lblIdSolicitudEx.getText()));
                lblJueces.setText(juez);

                int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                //se obtienen las fechas de las audiencias solicitadas
                Object  [] fechas = new Object[numRegistros];
                for(int i = 0; i< numRegistros; i++)
                {
                    fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getFecha();

                }
                num_audiencias.setText(String.valueOf(numRegistros));
                // se muestran las fechas de audiencias en los labels
                lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+",");

                //se obtienen las horas de las audiencias solicitadas
                int numRegistrosHora = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                Object  [] horas = new Object[numRegistrosHora];
                for(int i = 0; i< numRegistrosHora; i++)
                {
                    horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getHora();

                }
                // se muestran las horas de audiencias en los labels
                lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                int numRegistrosDisco = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).size();
                String [] numero = new String[numRegistrosDisco];
                String [] anio = new String [numRegistrosDisco];
                Object [] completoA =  new Object[numRegistrosDisco];
                String v_numero;
                String v_anio;
                String completo="";
                System.out.println("TAMAÑO " + numRegistrosDisco);
                for(int i=0; i<numRegistrosDisco; i++){
                    numero [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getNumero();
                    anio [i] = modeloSolicitudE.lsDisco(Integer.parseInt(lblIdSolicitudEx.getText())).get(i).getAnio();
                    v_numero = numero[i];
                    v_anio =  anio[i];
                    completo = v_numero +"-"+ v_anio;
                    completoA[i] = completo;
                    //System.out.println("Array completo " + completoA[i]);

                }
                lblDiscoEx.setText(Arrays.toString(completoA).replace("[","").replace("]", "")+",");
                int folio=Integer.parseInt(tablaSolicitudExterna.getValueAt(fila, 0).toString());
                try{
                    DefaultTableModel modelo = new DefaultTableModel();
                    String sql = "";
                    sql = "SELECT comentario FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND ID='"+folio+"'";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    modelo.addColumn("COMENTARIO");
                    tablaComentarioExterna.setModel(modelo);
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
                }catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }
        }
    }//GEN-LAST:event_tablaSolicitudExternaMouseClicked

    private void BusquedaFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusquedaFolioActionPerformed
        if(txtBuscarFolio.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Ingrese el folio","Sin Folio",JOptionPane.ERROR_MESSAGE);
        }else{

            ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).setText("");
            ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).setText("");
            CausaSolicitud.setText("");

            llenarTablaFolio();
        }
    }//GEN-LAST:event_BusquedaFolioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        /*java.util.Date date = new java.util.Date();

        SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
        String fechaCompleta = fechaHora.format(date);
        SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
        String horaCompleta = Hora.format(date);*/
        int numSolicitud = Integer.parseInt(lblIdSoli.getText());
        String estado = "Cancelada";

        int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar como Cancelada?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
        if(dialogoResultado == 0) {
            modeloSolicitud.actualizarCancelacion(numSolicitud,estado);
            //limpiarCamposSolicitud();
            DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
            modelo.setRowCount(0);
            if(txtBuscarFolio.getText().isEmpty())  {

                if(CausaSolicitud.getText().isEmpty()) {
                    if(RadioBtnPendiente.isSelected()) {
                        llenarTablaSolicitud2(tablaEntrega);
                        limpiarCamposSolicitud();
                    } else if(RadioBtnRotulado.isSelected()) {
                        llenarTablaSolicitud3(tablaEntrega);
                        limpiarCamposSolicitud();
                    }
                    else if(jRadioButton1.isSelected()) {
                        llenarTablaSolicitud(tablaEntrega);
                        limpiarCamposSolicitud();
                    }
                } else  {
                    if(EstadosCombo.getSelectedIndex()==0) {
                        try{
                            String causa=CausaSolicitud.getText();
                            //DefaultTableModel modelo= new DefaultTableModel();
                            //DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                            modelo.setRowCount(0);
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                            // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            Connection accesoBD = con.conectar();
                            PreparedStatement pst = accesoBD.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            ResultSetMetaData rsMd = rs.getMetaData();
                            int cantidadcolumnas = rsMd.getColumnCount();
                            /* modelo.addColumn("Folio");
                            modelo.addColumn("Fecha solicitud");
                            modelo.addColumn("Hora solicitud");
                            modelo.addColumn("Copias");
                            modelo.addColumn("Estado");
                            modelo.addColumn("Fecha_constancia");
                            modelo.addColumn("Hora constancia");
                            modelo.addColumn("Fecha_entrega");
                            modelo.addColumn("Hora entrega");
                            modelo.addColumn("Participante");
                            modelo.addColumn("Tipo");
                            modelo.addColumn("Audiencia");*/
                            //tablaEntrega.setModel(modelo);
                            while(rs.next()) {
                                Object datos[]=new Object[cantidadcolumnas];
                                for(int i =0; i<cantidadcolumnas;i++ ) {
                                    datos[i]=rs.getObject(i+1);
                                }
                                modelo.addRow(datos);
                            }
                            rs.close();
                            int total = tablaEntrega.getRowCount();
                            if(total ==0){
                                JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa+"");
                            } else {
                                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                            }
                        } catch (SQLException ex){
                            System.err.println(ex.toString());
                        }
                    } else {
                        String estado2="";
                        if(EstadosCombo.getSelectedIndex()==1) {
                            estado2="PENDIENTE";
                        } else if(EstadosCombo.getSelectedIndex()==2) {
                            estado2="ROTULADO";
                        } else if(EstadosCombo.getSelectedIndex()==3) {
                            estado2="CANCELADA";
                        }
                        try{
                            String causa=CausaSolicitud.getText();
                            //DefaultTableModel modelo= new DefaultTableModel();
                            // DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                            modelo.setRowCount(0);
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                            // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' AND S.ESTADO='"+estado2+"'ORDER BY S.ID";
                            String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' AND S.ESTADO='"+estado2+"' ORDER BY S.ID";
                            Connection accesoBD = con.conectar();
                            PreparedStatement pst = accesoBD.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            ResultSetMetaData rsMd = rs.getMetaData();
                            int cantidadcolumnas = rsMd.getColumnCount();
                            /* modelo.addColumn("Folio");
                            modelo.addColumn("Fecha solicitud");
                            modelo.addColumn("Hora solicitud");
                            modelo.addColumn("Copias");
                            modelo.addColumn("Estado");
                            modelo.addColumn("Fecha_constancia");
                            modelo.addColumn("Hora constancia");
                            modelo.addColumn("Fecha_entrega");
                            modelo.addColumn("Hora entrega");
                            modelo.addColumn("Participante");
                            modelo.addColumn("Tipo");
                            modelo.addColumn("Audiencia");*/
                            //tablaEntrega.setModel(modelo);
                            while(rs.next()) {
                                Object datos[]=new Object[cantidadcolumnas];
                                for(int i =0; i<cantidadcolumnas;i++ ) {
                                    datos[i]=rs.getObject(i+1);
                                }
                                modelo.addRow(datos);
                            }
                            rs.close();
                            int total = tablaEntrega.getRowCount();
                            if(total ==0){
                                JOptionPane.showMessageDialog(null, "Ya no existen solicitudes en estado "+estado2+ "de la causa: "+causa+"");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                            }
                        } catch (SQLException ex){
                            System.err.println(ex.toString());
                        }
                    }
                }
            }else{
                llenarTablaFolio();
            }
        } else{
            JOptionPane.showMessageDialog(null, "Ha cancelado la accion de marcar como cancelada ");
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirFoliosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirFoliosActionPerformed
        int fila = tablaEntrega.getSelectedRow();
        if(fila!=-1)
        {
            String fechaAudiencia =txtEfechaS.getText();
            String nombre=txtEnombreS.getText();
            String hora=txtEhoraS.getText();
            String disco=txtDisco.getText();
            String causa=txtEcausaS.getText();
            String copias=txtEcopiasS.getText();
            String fechaSolicitud=txtEfechaSoli.getText();
            String comentario = fieldComentario.getText();
            String tipo=tablaEntrega.getValueAt(fila, 11).toString();
            int folio=Integer.parseInt(tablaEntrega.getValueAt(fila, 0).toString());

            int numdisco=modeloSolicitud.extraerNumdisco(folio);
            String sala=modeloDisco.extraerSalaAudiencia(numdisco);
            int idAudiencia=modeloDisco.extraerIdAudiencia(numdisco);
            int consolidacion=numCosolidacion(idAudiencia);
            int idParticipante=modeloSolicitud.extraerIdparticipante(folio);
            int idIntermedia=modeloIntermedia.extraerId(idAudiencia, idParticipante);
            String autorizados=modeloIntermedia.extraerAtorizados(idIntermedia);
            generar(disco,consolidacion,autorizados,fechaAudiencia,fechaSolicitud,
                nombre,copias,causa,hora,folio,tipo,sala,comentario);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la tabla","Sin seleccion",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImprimirFoliosActionPerformed

    private void btnBuscaSolicitudCausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitudCausaActionPerformed
        txtBuscarFolio.setText("");
        ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).setText("");
        ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).setText("");
        if(CausaSolicitud.getText().isEmpty())

        {
            JOptionPane.showMessageDialog(null, "Ingrese una causa","Sin causa",JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            if(EstadosCombo.getSelectedIndex()==0)
            {
                try{
                    String causa=CausaSolicitud.getText();
                    //DefaultTableModel modelo= new DefaultTableModel();
                    DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                    modelo.setRowCount(0);
                    //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                    // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                    //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                    String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    /* modelo.addColumn("Folio");
                    modelo.addColumn("Fecha solicitud");
                    modelo.addColumn("Hora solicitud");
                    modelo.addColumn("Copias");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Fecha_constancia");
                    modelo.addColumn("Hora constancia");
                    modelo.addColumn("Fecha_entrega");
                    modelo.addColumn("Hora entrega");
                    modelo.addColumn("Participante");
                    modelo.addColumn("Tipo");
                    modelo.addColumn("Audiencia");*/
                    //tablaEntrega.setModel(modelo);
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
                    int total = tablaEntrega.getRowCount();
                    if(total ==0){
                        total_participantes.setText(String.valueOf(total));
                        JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa+"");
                    }
                    else{
                        total_participantes.setText(String.valueOf(total));
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }
            else{
                String estado="";
                if(EstadosCombo.getSelectedIndex()==2)
                {
                    estado="PENDIENTE";
                }
                else if(EstadosCombo.getSelectedIndex()==1)
                {
                    estado="ENTREGADO";
                }
                else if(EstadosCombo.getSelectedIndex()==3)
                {
                    estado="ROTULADO";
                }
                else if(EstadosCombo.getSelectedIndex()==4)
                {
                    estado="CANCELADA";
                }
                try{
                    String causa=CausaSolicitud.getText();
                    //DefaultTableModel modelo= new DefaultTableModel();
                    DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                    modelo.setRowCount(0);
                    //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                    // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                    // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' AND S.ESTADO='"+estado+"'ORDER BY S.ID";
                    String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa,S.ARCHIVO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' AND S.ESTADO='"+estado+"'ORDER BY S.ID";
                    Connection accesoBD = con.conectar();
                    PreparedStatement pst = accesoBD.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadcolumnas = rsMd.getColumnCount();
                    /* modelo.addColumn("Folio");
                    modelo.addColumn("Fecha solicitud");
                    modelo.addColumn("Hora solicitud");
                    modelo.addColumn("Copias");
                    modelo.addColumn("Estado");
                    modelo.addColumn("Fecha_constancia");
                    modelo.addColumn("Hora constancia");
                    modelo.addColumn("Fecha_entrega");
                    modelo.addColumn("Hora entrega");
                    modelo.addColumn("Participante");
                    modelo.addColumn("Tipo");
                    modelo.addColumn("Audiencia");*/
                    //tablaEntrega.setModel(modelo);
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
                    int total = tablaEntrega.getRowCount();
                    if(total ==0){
                        total_participantes.setText(String.valueOf(total));
                        JOptionPane.showMessageDialog(null, "No existen solicitudes en estado "+estado+ "de la causa: "+causa+"");
                    }
                    else{
                        total_participantes.setText(String.valueOf(total));
                        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                    }
                } catch (SQLException ex){
                    System.err.println(ex.toString());
                }

            }

        }
    }//GEN-LAST:event_btnBuscaSolicitudCausaActionPerformed

    private void btnLimpiarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSActionPerformed
        CausaSolicitud.setText("");
        fieldComentario.setText("");
        txtBuscarFolio.setText("");
        total_participantes.setText("");
    }//GEN-LAST:event_btnLimpiarSActionPerformed

    private void btnEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaActionPerformed
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
        String fechaCompleta = fechaHora.format(date);
        SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
        String horaCompleta = Hora.format(date);
        int numSolicitud = Integer.parseInt(lblIdSoli.getText());
        String estado = "Entregado";

        int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar la entrega?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
        if(dialogoResultado == 0){
            if(txtBuscarFolio.getText().isEmpty())
            {           if(CausaSolicitud.getText().isEmpty())
                {

                    if(RadioBtnPendiente.isSelected())
                    {
                        modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                        DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                        modelo.setRowCount(0);
                        limpiarCamposSolicitud();
                        llenarTablaSolicitud2(tablaEntrega);
                    }
                    else if(RadioBtnRotulado.isSelected())
                    {
                        modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                        DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                        modelo.setRowCount(0);
                        limpiarCamposSolicitud();
                        llenarTablaSolicitud3(tablaEntrega);
                    }
                    else if(jRadioButton1.isSelected())
                    {
                        modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                        DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                        modelo.setRowCount(0);
                        limpiarCamposSolicitud();
                        llenarTablaSolicitud(tablaEntrega);
                    }

                }else{
                    modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                    DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                    modelo.setRowCount(0);
                    limpiarCamposSolicitud();
                    // ActualizarTablaCausa();
                    if(EstadosCombo.getSelectedIndex()==0)
                    {
                        try{
                            String causa1=CausaSolicitud.getText();
                            //DefaultTableModel modelo= new DefaultTableModel();
                            // DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                            modelo.setRowCount(0);
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                            // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
                            String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
                            Connection accesoBD = con.conectar();
                            PreparedStatement pst = accesoBD.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            ResultSetMetaData rsMd = rs.getMetaData();
                            int cantidadcolumnas = rsMd.getColumnCount();
                            /* modelo.addColumn("Folio");
                            modelo.addColumn("Fecha solicitud");
                            modelo.addColumn("Hora solicitud");
                            modelo.addColumn("Copias");
                            modelo.addColumn("Estado");
                            modelo.addColumn("Fecha_constancia");
                            modelo.addColumn("Hora constancia");
                            modelo.addColumn("Fecha_entrega");
                            modelo.addColumn("Hora entrega");
                            modelo.addColumn("Participante");
                            modelo.addColumn("Tipo");
                            modelo.addColumn("Audiencia");*/
                            //tablaEntrega.setModel(modelo);
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
                            int total = tablaEntrega.getRowCount();
                            if(total ==0){
                                JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa1+"");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                            }
                        } catch (SQLException ex){
                            System.err.println(ex.toString());
                        }

                    }
                    else{
                        String estado2="";
                        if(EstadosCombo.getSelectedIndex()==1)
                        {
                            estado2="PENDIENTE";
                        }
                        else if(EstadosCombo.getSelectedIndex()==2)
                        {
                            estado2="ROTULADO";
                        }
                        else if(EstadosCombo.getSelectedIndex()==3)
                        {
                            estado2="CANCELADA";
                        }
                        try{
                            String causa2=CausaSolicitud.getText();
                            //DefaultTableModel modelo= new DefaultTableModel();
                            //DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                            modelo.setRowCount(0);
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia WHERE CAUSA='"+causa+"'";
                            // String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa+"' ORDER BY S.ID";
                            //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"'ORDER BY S.ID";
                            String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"' ORDER BY S.ID";
                            Connection accesoBD = con.conectar();
                            PreparedStatement pst = accesoBD.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery();
                            ResultSetMetaData rsMd = rs.getMetaData();
                            int cantidadcolumnas = rsMd.getColumnCount();
                            /* modelo.addColumn("Folio");
                            modelo.addColumn("Fecha solicitud");
                            modelo.addColumn("Hora solicitud");
                            modelo.addColumn("Copias");
                            modelo.addColumn("Estado");
                            modelo.addColumn("Fecha_constancia");
                            modelo.addColumn("Hora constancia");
                            modelo.addColumn("Fecha_entrega");
                            modelo.addColumn("Hora entrega");
                            modelo.addColumn("Participante");
                            modelo.addColumn("Tipo");
                            modelo.addColumn("Audiencia");*/
                            //tablaEntrega.setModel(modelo);
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
                            int total = tablaEntrega.getRowCount();
                            if(total ==0){
                                JOptionPane.showMessageDialog(null, "Ya no existen solicitudes en estado "+estado2+ "de la causa: "+causa2+"");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                            }
                        } catch (SQLException ex){
                            System.err.println(ex.toString());
                        }

                    }

                }

            }else{
                modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                limpiarCamposSolicitud();
                llenarTablaFolio();
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Ha cancelado la entrega");
        }
    }//GEN-LAST:event_btnEntregaActionPerformed

    private void btnConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConstanciaActionPerformed
        int numSolicitud = Integer.parseInt(lblIdSoli.getText());
        int numParte = modeloSolicitud.extraerIdparticipante(Integer.parseInt(lblIdSoli.getText()));
        int numDisco = modeloSolicitud.extraerNumdisco(Integer.parseInt(lblIdSoli.getText()));
        int numAudiencia = modeloDisco.extraerIdAudiencia(numDisco);
        String juez = txtEjuezS.getText();
        String fAudiencia = txtEfechaS.getText();
        String hora = txtEhoraS.getText();
        String causa = txtEcausaS.getText();
        String parte = txtEnombreS.getText();
        String tipo = txtEtipoS.getText();
        String disco = txtDisco.getText();
        String imputado = lblImputadoS.getText();
        String defensa = lblDefensaS.getText();
        String fiscal = lblFiscal.getText();
        String folio = lblIdSoli.getText();
        String consolidacion = conso_txt.getText();
        int copias = Integer.parseInt(txtEcopiasS.getText());
        String estado = "ROTULADO";

        java.util.Date date = new java.util.Date();
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
        String fechaCompleta = fechaHora.format(date);
        SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
        String horaCompleta = Hora.format(date);

        int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea generar la constancia?", "Confirmación constancia", JOptionPane.OK_CANCEL_OPTION);
        if(dialogoResultado ==0){
            String[] opciones={"CANON","EPSON"};
            int seleccion=JOptionPane.showOptionDialog(null, "Seleccione la impresora en la cual va a rotular", "seleccion de impresora", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(seleccion==0)
            {
                modeloSolicitud.actualizarSolicitud(Integer.parseInt(lblIdSoli.getText()),fechaCompleta,horaCompleta,estado);
                modeloSolicitud.solicitud(numSolicitud, numParte, numDisco);
                modeloSolicitud.generar(juez,fAudiencia,causa,parte,fechaCompleta,horaCompleta,copias,folio);
                modeloSolicitud.generarRotulo(fAudiencia, hora , causa, disco, imputado, defensa, fiscal,consolidacion);
                modeloSolicitud.borrarContenidoTabla();
                //limpiarCamposSolicitud();
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                limpiarCamposSolicitud();

                if(txtBuscarFolio.getText().isEmpty())
                {
                    if(CausaSolicitud.getText().isEmpty())
                    {
                        if(RadioBtnPendiente.isSelected())
                        {
                            llenarTablaSolicitud2(tablaEntrega);
                        }
                        else if(RadioBtnRotulado.isSelected())
                        {
                            llenarTablaSolicitud3(tablaEntrega);
                        }
                        else if(jRadioButton1.isSelected())
                        {
                            llenarTablaSolicitud(tablaEntrega);
                        }
                    }
                    else{
                        if(EstadosCombo.getSelectedIndex()==0)
                        {
                            try{
                                String causa1=CausaSolicitud.getText();
                                modelo.setRowCount(0);
                                //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
                                String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
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
                                int total = tablaEntrega.getRowCount();
                                if(total ==0){
                                    JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa1+"");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                                }
                            } catch (SQLException ex){
                                System.err.println(ex.toString());
                            }

                        }
                        else{
                            String estado2="";
                            if(EstadosCombo.getSelectedIndex()==1)
                            {
                                estado2="PENDIENTE";
                            }
                            else if(EstadosCombo.getSelectedIndex()==2)
                            {
                                estado2="ROTULADO";
                            }
                            else if(EstadosCombo.getSelectedIndex()==3)
                            {
                                estado2="CANCELADA";
                            }
                            try{
                                String causa2=CausaSolicitud.getText();
                                modelo.setRowCount(0);
                                //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"'ORDER BY S.ID";
                                String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"' ORDER BY S.ID";
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
                                int total = tablaEntrega.getRowCount();
                                if(total ==0){
                                    JOptionPane.showMessageDialog(null, "Ya no existen solicitudes en estado "+estado2+ "de la causa: "+causa2+"");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                                }
                            } catch (SQLException ex){
                                System.err.println(ex.toString());
                            }

                        }

                    }

                }

                else{
                    llenarTablaFolio();
                }

            }else{
                modeloSolicitud.actualizarSolicitud(Integer.parseInt(lblIdSoli.getText()),fechaCompleta,horaCompleta,estado);
                modeloSolicitud.solicitud(numSolicitud, numParte, numDisco);
                modeloSolicitud.generar(juez,fAudiencia,causa,parte,fechaCompleta,horaCompleta,copias,folio);
                modeloSolicitud.generarRotulo2(fAudiencia, hora , causa, disco, imputado, defensa, fiscal,consolidacion);
                modeloSolicitud.borrarContenidoTabla();
                //limpiarCamposSolicitud();
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                limpiarCamposSolicitud();

                if(txtBuscarFolio.getText().isEmpty())
                {
                    if(CausaSolicitud.getText().isEmpty())
                    {
                        if(RadioBtnPendiente.isSelected())
                        {
                            llenarTablaSolicitud2(tablaEntrega);
                        }
                        else if(RadioBtnRotulado.isSelected())
                        {
                            llenarTablaSolicitud3(tablaEntrega);
                        }
                        else if(jRadioButton1.isSelected())
                        {
                            llenarTablaSolicitud(tablaEntrega);
                        }
                    }
                    else{
                        if(EstadosCombo.getSelectedIndex()==0)
                        {
                            try{
                                String causa1=CausaSolicitud.getText();
                                modelo.setRowCount(0);
                                //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
                                String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa1+"' ORDER BY S.ID";
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
                                int total = tablaEntrega.getRowCount();
                                if(total ==0){
                                    JOptionPane.showMessageDialog(null, "No existen solicitudes de participantes en la causa: "+causa1+"");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                                }
                            } catch (SQLException ex){
                                System.err.println(ex.toString());
                            }

                        }
                        else{
                            String estado2="";
                            if(EstadosCombo.getSelectedIndex()==1)
                            {
                                estado2="PENDIENTE";
                            }
                            else if(EstadosCombo.getSelectedIndex()==2)
                            {
                                estado2="ROTULADO";
                            }
                            else if(EstadosCombo.getSelectedIndex()==3)
                            {
                                estado2="CANCELADA";
                            }
                            try{
                                String causa2=CausaSolicitud.getText();
                                modelo.setRowCount(0);
                                //String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"'ORDER BY S.ID";
                                String sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.comentario,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE, A.causa FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE CAUSA='"+causa2+"' AND S.ESTADO='"+estado2+"' ORDER BY S.ID";
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
                                int total = tablaEntrega.getRowCount();
                                if(total ==0){
                                    JOptionPane.showMessageDialog(null, "Ya no existen solicitudes en estado "+estado2+ "de la causa: "+causa2+"");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");

                                }
                            } catch (SQLException ex){
                                System.err.println(ex.toString());
                            }

                        }

                    }

                }

                else{
                    llenarTablaFolio();
                }

            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Ha cancelado la elaboración ");
        }
    }//GEN-LAST:event_btnConstanciaActionPerformed

    private void btnBuscaSolicitud1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitud1ActionPerformed
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal =  ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
        if (fecha.isEmpty() || fechaFinal.isEmpty()){
            JOptionPane.showMessageDialog(null, "Indique la fecha de la solicitud","Fecha vacia",JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
            modelo.setRowCount(0);
            llenarTablaSolicitudConstancia(tablaEntrega);
        }
    }//GEN-LAST:event_btnBuscaSolicitud1ActionPerformed

    private void btnBuscaSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaSolicitudActionPerformed
        //Count_copias();
        txtBuscarFolio.setText("");
        CausaSolicitud.setText("");
        String fecha = ((JTextField)JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal =  ((JTextField)JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
        if (fecha.isEmpty() || fechaFinal.isEmpty()){
            JOptionPane.showMessageDialog(null, "Indique la fecha de la solicitud","Fecha vacia",JOptionPane.ERROR_MESSAGE);
        } else {

            if(jRadioButton1.isSelected())
            {
                //btnCancelar.setVisible(false);
                //btnConstancia.setVisible(false);
                //btnEntrega.setVisible(false);
                //System.out.println(fecha);
                //System.out.println(fechaFinal);
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                llenarTablaSolicitud(tablaEntrega);

            }
            else if(RadioBtnPendiente.isSelected())
            {
                // btnCancelar.setVisible(true);
                // btnConstancia.setVisible(true);
                // btnEntrega.setVisible(false);
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                llenarTablaSolicitud2(tablaEntrega);
            }
            else if(RadioBtnRotulado.isSelected())
            {
                // btnEntrega.setVisible(true);
                // btnCancelar.setVisible(false);
                //  btnConstancia.setVisible(false);
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                llenarTablaSolicitud3(tablaEntrega);

            }
            else{
                //btnEntrega.setVisible(false);
                //btnCancelar.setVisible(false);
                //btnConstancia.setVisible(false);
                DefaultTableModel modelo = (DefaultTableModel)tablaEntrega.getModel();
                modelo.setRowCount(0);
                llenarTablaSolicitud4(tablaEntrega);

            }
        }
    }//GEN-LAST:event_btnBuscaSolicitudActionPerformed

    private void tablaEntregaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregaMouseClicked
        if(tablaEntrega.getSelectedRow() != -1){
            int fila = tablaEntrega.getSelectedRow();
            fieldComentario.setText("");
            txtEnombreS.setText(tablaEntrega.getValueAt(fila, 10).toString());
            txtEfechaSoli.setText(tablaEntrega.getValueAt(fila, 1).toString());
            txtTipoAudiencia.setText(tablaEntrega.getValueAt(fila,12).toString());
            int solicitud =Integer.parseInt( tablaEntrega.getValueAt(fila, 0).toString());
            txtEcopiasS.setText(tablaEntrega.getValueAt(fila, 3).toString());
            txtEtipoS.setText(tablaEntrega.getValueAt(fila, 11).toString());
            int disco = modeloSolicitud.extraerNumdisco(solicitud);
            String fecha = modeloDisco.extraerFechaAudiencia(disco);
            String hora = modeloDisco.extraerHoraAudiencia(disco);
            String causa = modeloDisco.extraerCausaAudiencia(disco);
            int audiencia = modeloDisco.extraerIdAudiencia(disco);

            int consolidacion = modeloDisco.extraerConsolidacion(audiencia);

            int numFiscales = modeloParticipante.lsFiscalesOriginal(audiencia).size();
            int numDefensas = modeloParticipante.lsDefensasOriginal(audiencia).size();
            int numImputados = modeloParticipante.lsImputadosOriginal(audiencia).size();

            String imputado = modeloParticipante.imputadoOriginal(audiencia);
            String defensa = modeloParticipante.defensaOriginal(audiencia);
            String fiscal = modeloParticipante.fiscalOriginal(audiencia);
            //System.out.print("Fiscales " +numFiscales);
            //System.out.print("Defensas " +numDefensas);
            //System.out.print("Imputados " +numImputados);
            if (numDefensas > 1){
                lblDefensaS.setText(defensa +" "+"Y OTROS");
            } else{
                lblDefensaS.setText(defensa);
            }
            if (numFiscales > 1){
                lblFiscal.setText(fiscal+" "+"Y OTROS");
            } else {
                lblFiscal.setText(fiscal);
            }
            if (numImputados > 1){
                lblImputadoS.setText(imputado+" "+"Y OTROS");
            } else {
                lblImputadoS.setText(imputado);
            }

            String juez  = modeloAudiencia.extraerJuez(audiencia);
            /*String imputado = modeloAudiencia.extraerImputado(audiencia);
            String defensa = modeloAudiencia.extraerDefensa(audiencia);
            String fiscal = modeloAudiencia.extraerFiscal(audiencia);*/
            //JOptionPane.showMessageDialog(null, solicitud);
            txtEfechaS.setText(fecha);
            txtEhoraS.setText(hora);
            txtEcausaS.setText(causa);
            txtEjuezS.setText(juez);

            conso_txt.setText(String.valueOf(consolidacion));
            lblIdSoli.setText(tablaEntrega.getValueAt(fila, 0).toString());
            int idDisco = modeloSolicitud.extraerNumdisco(Integer.parseInt(lblIdSoli.getText()));
            int numero =  modeloDisco.numeroDisco(idDisco);
            int anio = modeloDisco.anioDisco(idDisco);
            String completo = numero+"-"+ anio;
            txtDisco.setText(String.valueOf(completo));
            if(tablaEntrega.getValueAt(fila, 5)==null)
            {
                fieldComentario.setText("Sin Comentario");
            }
            else{
                fieldComentario.setText(tablaEntrega.getValueAt(fila, 5).toString());
            }

        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //}
        // });
    }//GEN-LAST:event_tablaEntregaMouseClicked

    private void tablaEntregaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregaMousePressed
        if (evt.getClickCount() > 1) {
            Point point = evt.getPoint();
            int row = tablaEntrega.rowAtPoint(point);
            int column = tablaEntrega.columnAtPoint(point);
            String ruta="";
            TableModel model = tablaEntrega.getModel();
            String participante = tablaEntrega.getValueAt(row, 10).toString();
            String folio= tablaEntrega.getValueAt(row, 0).toString();
            String bandera=modeloSolicitud.extraerRuta(folio);
            //System.out.println("bandera:"+bandera);
            if(bandera==null)
            {

                String [] botones = { "Agregar PDF", "Cancelar" };
                int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar al folio "+folio+"?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);
                if(variable==0)
                {
                    //Choose file
                    JFileChooser choose = new JFileChooser();
                    choose.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
                    choose.showOpenDialog(null);
                    Path sourcePath = choose.getSelectedFile().toPath();
                    //get file extension
                    String[] parts = sourcePath.toString().split("\\.");
                    System.out.println(sourcePath);
                    System.out.println(Arrays.toString(parts));
                    System.out.println(parts.length);
                    String extension = parts[parts.length - 1];
                    //generate destination
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");

                    String filename;
                    Path destinationPath;
                    int index = 0;

                    do {
                        //filename = sdf.format(today) + "__" + index + "." + extension;
                        filename ="" +participante+ "__" + folio + "__"+ sdf.format(today) + "." + extension;
                        //destinationPath = Paths.get("D:\\documents\\" + filename);
                            destinationPath=Paths.get("\\\\10.27.47.11\\ciav_cjp\\controlinterno\\files\\SolicitudP\\"+filename);
                                ruta=destinationPath.toString();
                                System.out.println("ruta que se guarda"+ruta);
                                //destinationPath = Paths.get("C:\\Users\\ceo\\AppData\\Local\\Temp\\" + filename);
                                    System.out.println(destinationPath);
                                    index++;
                                }
                                while (destinationPath.toFile().exists());
                                //move file
                                try {
                                    Files.copy(
                                        sourcePath,
                                        destinationPath//,
                                        // since the destinationPath is unique, do not replace
                                        //                  StandardCopyOption.REPLACE_EXISTING,
                                        // works for moving file on the same drive
                                        //its basically a renaming of path
                                        //                  StandardCopyOption.ATOMIC_MOVE
                                    );
                                    //          JOptionPane.showMessageDialog(null, "file " + sourcePath.getFileName() + " moved");
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "falló al copiar para archivo: " + sourcePath.getFileName(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                                JOptionPane.showMessageDialog(null, "Se guardo con exito el archivo "+ sourcePath.getFileName());
                                //Guardar la ruta en la base de datos
                                ruta="\\\\10.27.47.11\\\\ciav_cjp\\\\controlinterno\\\\files\\\\SolicitudP\\\\"+filename;
                                int folio_int=parseInt(folio);
                                modeloSolicitud.GuardarRutaSolicitud(folio_int, ruta);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(rootPane, "Esta solicitud con folio "+folio+" ya tiene un archivo pdf");

                            String [] botones = { "Ver PDF", "Actualizar PDF", "Cancelar" };
                            int variable = JOptionPane.showOptionDialog (null, " ¿Qué desea realizar al folio "+folio+"?", "Duracion de Audiencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null/*icono*/, botones, botones[0]);

                            if(variable==0)
                            {
                                //String nombreArchivo=""+participante+"__"+folio+".pdf";
                                String nombreArchivo=modeloSolicitud.extraerRuta(folio);
                                //System.out.println(""+nombreArchivo);
                                try {
                                    Desktop.getDesktop().open(new File(nombreArchivo));
                                } catch (IOException e1) {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "fallo al abrir el archivo: " ,
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                    System.exit(1);

                                }
                            }
                            else if(variable==1)
                            {
                                //Choose file
                                JFileChooser choose = new JFileChooser();
                                choose.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
                                choose.showOpenDialog(null);
                                Path sourcePath = choose.getSelectedFile().toPath();
                                //get file extension
                                String[] parts = sourcePath.toString().split("\\.");
                                System.out.println(sourcePath);
                                System.out.println(Arrays.toString(parts));
                                System.out.println(parts.length);
                                String extension = parts[parts.length - 1];
                                //generate destination
                                Date today = Calendar.getInstance().getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");

                                String filename;
                                Path destinationPath;
                                int index = 0;

                                do {
                                    //filename ="" +participante+ "__" + folio + "." + extension;
                                    filename ="" +participante+ "__" + folio + "__"+ sdf.format(today) + "." + extension;
                                    //destinationPath = Paths.get("D:\\documents\\" + filename);
                                        destinationPath=Paths.get("\\\\10.27.47.11\\ciav_cjp\\controlinterno\\files\\SolicitudP\\"+filename);
                                            ruta=destinationPath.toString();
                                            System.out.println("ruta que se guarda"+ruta);
                                            //destinationPath = Paths.get("C:\\Users\\ceo\\AppData\\Local\\Temp\\" + filename);
                                                System.out.println(destinationPath);
                                                index++;
                                            }
                                            while (destinationPath.toFile().exists());
                                            //move file
                                            try {
                                                Files.copy(
                                                    sourcePath,
                                                    destinationPath//,
                                                    // since the destinationPath is unique, do not replace
                                                    //                 StandardCopyOption.REPLACE_EXISTING,
                                                    // works for moving file on the same drive
                                                    //its basically a renaming of path
                                                    //                  StandardCopyOption.ATOMIC_MOVE
                                                );
                                                //          JOptionPane.showMessageDialog(null, "file " + sourcePath.getFileName() + " moved");
                                            } catch (IOException e) {
                                                // TODO Auto-generated catch block
                                                JOptionPane.showMessageDialog(
                                                    null,
                                                    "falló al copiar para archivo: " + sourcePath.getFileName(),
                                                    "Error",
                                                    JOptionPane.ERROR_MESSAGE
                                                );
                                                e.printStackTrace();
                                                System.exit(1);
                                            }
                                            JOptionPane.showMessageDialog(null, "Se guardo con exito el archivo "+ sourcePath.getFileName());
                                            //Guardar la ruta en la base de datos
                                            ruta="\\\\10.27.47.11\\\\ciav_cjp\\\\controlinterno\\\\files\\\\SolicitudP\\\\"+filename;
                                            int folio_int=parseInt(folio);
                                            modeloSolicitud.GuardarRutaSolicitud(folio_int, ruta);
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Acción Cancelada");
                                        }
                                    }//FIN DE ELSE CUANDO YA EXISTE UN ARCHIVO PDF

                                }

    }//GEN-LAST:event_tablaEntregaMousePressed

    private void video_playerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_video_playerActionPerformed
        if(txtConsolidacionS.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Seleccione una audiencia","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String consolidacion= txtConsolidacionS.getText();
            // JFileChooser jf= new JFileChooser("\\\\10.27.46.12\\puebla-centro\\CONSOLIDACION\\"+consolidacion);
                //JFileChooser jf= new JFileChooser("\\\\10.27.51.240\\Consolidacion\\");
                    // FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter(".asf","asf");
                    //jf.setFileFilter(filtroImagen);
                    //int r= jf.showOpenDialog(this);
                    //  if(r==JFileChooser.APPROVE_OPTION)
                    // {
                        //      File archivo = jf.getSelectedFile();
                        //    String nombre=jf.getName(archivo);
                        //System.out.println("nombre archivo:"+jf.getName(archivo));
                        reproducir_video("A_"+consolidacion);
                        //}
                }
    }//GEN-LAST:event_video_playerActionPerformed

    private void btnConsultarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarPActionPerformed
        DefaultTableModel modelo = (DefaultTableModel)tablaParticipantes.getModel();
        modelo.setRowCount(0);
        participantesAgregadosSolicitud(tablaParticipantes);
    }//GEN-LAST:event_btnConsultarPActionPerformed

    private void btnActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarPActionPerformed

    private void txtDiscoSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscoSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscoSActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String nombre = txtNombreS.getText();
        String fecha = txtFechaS.getText();
        String hora = txtHoraS.getText();
        String sala = txtSalaS.getText();
        String tipo = txtTipoS.getText();
        int participante =  modeloParticipante.extraerIdParticipanteEliminar(nombre,tipo);
        int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
        int intermedia = modeloIntermedia.extraerId(audiencia, participante);
        System.out.print(intermedia);

        //int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea quitar al participante de la audiencia?","Confirmacion de operacion",JOptionPane.OK_CANCEL_OPTION);
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea quitar al participante de la audiencia?","Confirmar",JOptionPane.YES_NO_OPTION);
        if(confirmacion == JOptionPane.YES_OPTION){
            modeloIntermedia.eliminarParticipanteDeAudiencia(intermedia);
            DefaultTableModel modelo = (DefaultTableModel)tablaParticipantes.getModel();
            modelo.setRowCount(0);
            participantesAgregadosSolicitud(tablaParticipantes);
            txtNombreS.setText("");
            txtTipoS.setText("");
            txtCopias.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "Se ha cancelado la operación");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnIrAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrAgregarActionPerformed

    }//GEN-LAST:event_btnIrAgregarActionPerformed

    private void btnBuscarAuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAuActionPerformed
        DefaultTableModel modelo = (DefaultTableModel)tablaParticipantes.getModel();
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if(e.getType() ==  TableModelEvent.UPDATE){
                    String fecha = txtFechaS.getText();
                    String hora = txtHoraS.getText();
                    String sala = txtSalaS.getText();
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    String nombre =txtNombreS.getText();
                    if(columna == 2){
                        String tipo = (tablaParticipantes.getValueAt(fila,1).toString());
                        int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                        //System.out.println("ID_AUDIENCIA: "+idAudiencia);
                        int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                        //System.out.println("ID_PARTICIPANTE: "+idParticipante);
                        int idIntermedia = modeloIntermedia.extraerId(idAudiencia, idParticipante);
                        //System.out.println("ID_INTERMEDIA: "+idIntermedia);

                        //System.out.println("TIPO: "+tipo);
                        //JOptionPane.showMessageDialog(null,"audiencia "+ idAudiencia + "participante "+idParticipante);
                        int copias = Integer.parseInt(tablaParticipantes.getValueAt(fila,columna).toString());
                        modeloIntermedia.actualizarTabla(idIntermedia, copias);

                    }
                }
                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        /*   if(txtBuscar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese  el número de causa");
        }
        else{
            DefaultTableModel modelo = (DefaultTableModel)tablaAudiencias.getModel();
            modelo.setRowCount(0);
            llenarTabla(tablaAudiencias);
        }*/
    }//GEN-LAST:event_btnBuscarAuActionPerformed

    private void tablaParticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaParticipantesMouseClicked
        //   DefaultTableModel modelo = (DefaultTableModel)tablaParticipantes.getModel();

        if (tablaParticipantes.getSelectedRow() != -1){
            int fila = tablaParticipantes.getSelectedRow();
            String fecha = txtFechaS.getText();
            String hora = txtHoraS.getText();
            String sala = txtSalaS.getText();
            int audiencia= modeloAudiencia.extraerId(fecha, hora, sala);
            //System.out.println("id audiencia "+audiencia);
            txtNombreS.setText(tablaParticipantes.getValueAt(fila, 0).toString());
            txtTipoS.setText(tablaParticipantes.getValueAt(fila, 1).toString());
            // txtCopias.setText(tabla.getValueAt(fila, 2).toString());
            if(tablaParticipantes.getValueAt(fila, 2)==null)
            {
                txtCopias.setText("0");
            }
            else{
                txtCopias.setText(tablaParticipantes.getValueAt(fila, 2).toString());
            }

            String nombre =txtNombreS.getText();
            String tipo = (tablaParticipantes.getValueAt(fila,1).toString());
            int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
            int idIntermedia = modeloIntermedia.extraerId(audiencia, idParticipante);
            //System.out.println("participante " + idParticipante);
            //System.out.println("intermedia"+ idIntermedia);
            lblIdIntermedia.setText(String.valueOf(idIntermedia));
        }

    }//GEN-LAST:event_tablaParticipantesMouseClicked

    private void tablaAudienciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAudienciasMouseClicked

        //if (tablaAudiencias.getSelectedRow() != -1){
            int fila = tablaAudiencias.getSelectedRow();
            txtFechaS.setText(tablaAudiencias.getValueAt(fila, 0).toString());
            txtHoraS.setText(tablaAudiencias.getValueAt(fila, 1).toString());
            txtSalaS.setText(tablaAudiencias.getValueAt(fila, 2).toString());
            if(tablaAudiencias.getValueAt(fila, 4)==null)
            {
                txtcomentario.setText("Sin Comentario");
            }
            else{
                txtcomentario.setText(tablaAudiencias.getValueAt(fila, 4).toString());
            }
            String fecha = txtFechaS.getText();
            String hora = txtHoraS.getText();
            String sala =txtSalaS.getText();
            String causa = txtBuscar.getText();
            int numAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
            int numDisco =  modeloDisco.extraerIdDisco2(numAudiencia);
            int anioDisco  = modeloDisco.extraerAnioDisco(numAudiencia);
            int numConsolidacion = modeloAudiencia.extraConsolidacion(numAudiencia);
            txtDiscoS.setText(String.valueOf(numDisco+"/"+anioDisco));
            txtConsolidacionS.setText(String.valueOf(numConsolidacion));

            participantesAgregadosSolicitud(tablaParticipantes);
            //  }

    }//GEN-LAST:event_tablaAudienciasMouseClicked

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        int filasafectadas=0;
        DefaultTableModel modelo = (DefaultTableModel)Tparticipantes.getModel();
        int tamano = Tparticipantes.getRowCount();
        for (int i = 0; i <tamano; i++) {
            Boolean estado = new Boolean(true);
            Boolean estadoColumna = Boolean.valueOf(Tparticipantes.getValueAt(i, 2).toString());
            if(estadoColumna.equals(estado)){
                String nombre =Tparticipantes.getValueAt(i, 0).toString();
                String tipo=Tparticipantes.getValueAt(i, 1).toString();
                int idparticipante=modeloParticipante.extraerIdParticipanteExacto(nombre,tipo);
                filasafectadas=filasafectadas+modelointermedia.eliminarIntermedia(Integer.parseInt(lblPrueba.getText()),idparticipante);
                Tparticipantes.setValueAt(null,i,0);
                Tparticipantes.setValueAt(null,i,1);
                Tparticipantes.setValueAt(null,i,2);
            }

        }

        if(filasafectadas>1)
        {
            JOptionPane.showMessageDialog(null, "Participantes eliminados");
        }
        else if(filasafectadas==1)
        {
            JOptionPane.showMessageDialog(null, "Participante eliminado");
        }
        else{
            JOptionPane.showMessageDialog(null, "No se borro ningun participante");
        }
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        if(txtusuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor primero inicie sesion", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        else{
            txtusuario.setText("");
            reg.setTitle("CENTRO DE JUSTICIA PENAL DE PUEBLA");
            JOptionPane.showMessageDialog(null, "Has cerrado sesion");
            InicioSesion.setIcon(icono);
            InicioSesion1.setIcon(icono);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void InicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioSesionActionPerformed
        if(txtusuario.getText().isEmpty())
        {
            sesion=new Login();
            sesion.setVisible(true);

        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor cierre sesion", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_InicioSesionActionPerformed

    private void btnNuevaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaAActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        textAutoAcompleter.removeAll();
        String tipos=(String) comboTipo.getSelectedItem();
        Statement st=null;
        ResultSet rs=null;
        try{
            Connection accesoBD = con.conectar();
            st=(Statement)accesoBD.createStatement();
            rs=st.executeQuery("SELECT DISTINCT NOMBRE FROM PARTICIPANTE WHERE TIPO='"+tipos+"'");
            //rs=st.executeQuery("SELECT NOMBRE FROM participante");

            while(rs.next()){

                textAutoAcompleter.addItem(rs.getString("NOMBRE"));
            }

        }   catch(Exception de){
            JOptionPane.showMessageDialog(this,de.getMessage());
        }
    }//GEN-LAST:event_comboTipoActionPerformed

    private void btnAgregarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarParteActionPerformed

    }//GEN-LAST:event_btnAgregarParteActionPerformed

    private void txtPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPartesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartesActionPerformed
        
    public void buscarCausaFora(String causa){
        //  TODO funcion que nos devuele todas las causas de acuerdo a su estado en la BD 
        try {
            DefaultTableModel modelo= new DefaultTableModel();
            String sql = "";
            //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' ORDER by S.ID";
            sql = "SELECT * FROM foraneo WHERE numero_causa = '"+causa+"'";
            //SELECT * FROM `foraneo` WHERE `numero_causa` = '0013/2022/PRUEBA' AND `estado` = 'PENDIENTE'
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID-BD");
            modelo.addColumn("CAUSA");
            modelo.addColumn("SOLICITANTE");
            modelo.addColumn("IMPUTADO");
            modelo.addColumn("TIPO-SOLI");
            modelo.addColumn("FECHA");
            modelo.addColumn("FECHA-SOLI");
            modelo.addColumn("HORA");
            modelo.addColumn("HORA-SOLI");
            modelo.addColumn("COPIAS");
            modelo.addColumn("REGION");
            modelo.addColumn("ESTADO");
            modelo.addColumn("ENCARGADO");
            tablaSolicitudForaneas.setModel(modelo);
            int [] anchos = {10,150,70,70,35,35,35,12,10,5,70,70,70};
            for (int x=0; x<cantidadColumnas;x++) {
                tablaSolicitudForaneas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
            while (rs.next()) {
                Object datos[]=new Object[cantidadColumnas];
                for (int i =0; i<cantidadColumnas;i++ ) {
                    datos[i]=rs.getObject(i+1);
                }
                modelo.addRow(datos);
            }
            rs.close();
            int numRegistro=modelo.getRowCount();
            if (numRegistro>0) {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
            } else {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes con el numero de causa"); 
            }
            tablaSolicitudForaneas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    
                    if(tablaSolicitudForaneas.getSelectedRow() != -1){
                        int fila = tablaSolicitudForaneas.getSelectedRow();
                        txtIdFora.setText(tablaSolicitudForaneas.getValueAt(fila, 0).toString());
                        txtCausaF.setText(tablaSolicitudForaneas.getValueAt(fila, 1).toString());
                        txtSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 2).toString());
                        txtImputadoF.setText(tablaSolicitudForaneas.getValueAt(fila, 3).toString());
                        txtTipoSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 4).toString());
                        txtFechaAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 5).toString());
                        txtHoraAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 7).toString());
                        txtCopiasF.setText(tablaSolicitudForaneas.getValueAt(fila, 9).toString());
                        fechaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 6).toString());
                        horaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 8).toString());
                    }
                }
            });
        } catch (SQLException ex) {
           System.err.println(ex.toString());
        }
    }
    
    public void buscarCausaEstadoForaneo(String causa, String estado ) {
        //TODO funcion que busca la causa por su estado
        try {
            DefaultTableModel modelo= new DefaultTableModel();
            String sql = "";
            //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' ORDER by S.ID";
            sql = "SELECT * FROM `foraneo` WHERE numero_causa = '"+causa+"' AND estado = '"+estado+"'";
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID-BD");
            modelo.addColumn("CAUSA");
            modelo.addColumn("SOLICITANTE");
            modelo.addColumn("IMPUTADO");
            modelo.addColumn("TIPO-SOLI");
            modelo.addColumn("FECHA");
            modelo.addColumn("FECHA-SOLI");
            modelo.addColumn("HORA");
            modelo.addColumn("HORA-SOLI");
            modelo.addColumn("COPIAS");
            modelo.addColumn("REGION");
            modelo.addColumn("ESTADO");
            modelo.addColumn("ENCARGADO");
            tablaSolicitudForaneas.setModel(modelo);
            int [] anchos = {10,150,70,70,35,35,35,12,10,5,70,70,70};
            for (int x=0; x<cantidadColumnas;x++) {
                tablaSolicitudForaneas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
            while (rs.next()) {
                Object datos[]=new Object[cantidadColumnas];
                for (int i =0; i<cantidadColumnas;i++ ) {
                    datos[i]=rs.getObject(i+1);
                }
                modelo.addRow(datos);
            }
            rs.close();
            int numRegistro=modelo.getRowCount();
            if (numRegistro>0) {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
            } else {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes con el numero de causa"); 
            }
            tablaSolicitudForaneas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    
                    if(tablaSolicitudForaneas.getSelectedRow() != -1){
                        int fila = tablaSolicitudForaneas.getSelectedRow();
                        txtIdFora.setText(tablaSolicitudForaneas.getValueAt(fila, 0).toString());
                        txtCausaF.setText(tablaSolicitudForaneas.getValueAt(fila, 1).toString());
                        txtSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 2).toString());
                        txtImputadoF.setText(tablaSolicitudForaneas.getValueAt(fila, 3).toString());
                        txtTipoSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 4).toString());
                        txtFechaAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 5).toString());
                        txtHoraAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 7).toString());
                        txtCopiasF.setText(tablaSolicitudForaneas.getValueAt(fila, 9).toString());
                        fechaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 6).toString());
                        horaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 8).toString());
                    }
                }
            });
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    public void buscarCausaForaneos(String causa, String estado) {
       //TODO funcion para poder buscar una causa existente en la tabla de la BD foraneos
        switch (estado) {
            case "TODOS":
                buscarCausaFora(causa);//FUNCION QUE NOS PERMITE BUSCAR Y TRAES LA CAUSA 
                break;
            case "ENTREGADOS":
                buscarCausaEstadoForaneo(causa, estado); //FUNCION QUE NOS PERMITE MOSTRAR Y TRAER LA CAUSA DE ACUERDO A SU ESTADO
                break;
            case "PENDIENTE":
                buscarCausaEstadoForaneo(causa, estado); //FUNCION QUE NOS PERMITE MOSTRAR Y TRAER LA CAUSA DE ACUERDO A SU ESTADO
                break;
            case "ROTULADO":
                buscarCausaEstadoForaneo(causa, estado); //FUNCION QUE NOS PERMITE MOSTRAR Y TRAER LA CAUSA DE ACUERDO A SU ESTADO
                break;
            case "CANCELADA":
                buscarCausaEstadoForaneo(causa, estado); //FUNCION QUE NOS PERMITE MOSTRAR Y TRAER LA CAUSA DE ACUERDO A SU ESTADO
                break;
        }
    }
    public void solicitudesXFechasForaneas(String fechaInicial,String fechaFinal,String estado ) {
        //  TODO funcion para buscar y mostarar por fechas deacuerdo a su estado
        try {
            //SELECT * FROM `foraneo` WHERE fecha_audiencia BETWEEN '2015-01-11' AND '2022-01-11' ORDER BY id; TODAS
            //SELECT * FROM `foraneo` WHERE fecha_audiencia BETWEEN '2015-01-11' AND '2022-01-11' AND estado='CANCELADA' ORDER BY id; POR ESTADO
            if ( estado == "TODOS") {
                DefaultTableModel modelo= new DefaultTableModel();
                String sql = "";
                System.out.println("ESTADO IF: "+estado+" "+fechaInicial+" "+fechaFinal);    
                sql="SELECT * FROM `foraneo` WHERE fecha_solicitud BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' AND estado != 'CANCELADA'  ORDER BY id";
                Connection accesoBD = con.conectar();
                PreparedStatement pst = accesoBD.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                System.out.println(cantidadColumnas);
                modelo.addColumn("ID-BD");
                modelo.addColumn("CAUSA");
                modelo.addColumn("SOLICITANTE");
                modelo.addColumn("IMPUTADO");
                modelo.addColumn("TIPO-SOLI");
                modelo.addColumn("FECHA");
                modelo.addColumn("FECHA-SOLI");
                modelo.addColumn("HORA");
                modelo.addColumn("HORA-SOLI");
                modelo.addColumn("COPIAS");
                modelo.addColumn("REGION");
                modelo.addColumn("ESTADO");
                modelo.addColumn("ENCARGADO");
                tablaSolicitudForaneas.setModel(modelo);
                int [] anchos = {10,150,70,70,35,35,35,12,10,5,70,70,70};
                for (int x=0; x<cantidadColumnas;x++) {
                    tablaSolicitudForaneas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                }
                while (rs.next()) {
                    Object datos[]=new Object[cantidadColumnas];
                    for (int i =0; i<cantidadColumnas;i++ ) {
                        datos[i]=rs.getObject(i+1);
                    }
                    modelo.addRow(datos);
                }
                rs.close();
                int numRegistro=modelo.getRowCount();
                if (numRegistro>0) {
                    totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                } else {
                    totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "No se encontraron registros en esas fechas"); 
                }
                tablaSolicitudForaneas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {

                        if(tablaSolicitudForaneas.getSelectedRow() != -1){
                            int fila = tablaSolicitudForaneas.getSelectedRow();
                            txtIdFora.setText(tablaSolicitudForaneas.getValueAt(fila, 0).toString());
                            txtCausaF.setText(tablaSolicitudForaneas.getValueAt(fila, 1).toString());
                            txtSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 2).toString());
                            txtImputadoF.setText(tablaSolicitudForaneas.getValueAt(fila, 3).toString());
                            txtTipoSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 4).toString());
                            txtFechaAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 5).toString());
                            txtHoraAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 7).toString());
                            txtCopiasF.setText(tablaSolicitudForaneas.getValueAt(fila, 9).toString());
                            fechaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 6).toString());
                            horaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 8).toString());
                        }
                    }
                });
            } else {
                DefaultTableModel modelo= new DefaultTableModel();
                String sql = "";
                System.out.println("ESTADO else: "+estado+" "+fechaInicial+" "+fechaFinal);  
                sql="SELECT * FROM `foraneo` WHERE fecha_audiencia BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' AND estado = '"+estado+"' ORDER BY id";
                Connection accesoBD = con.conectar();
                PreparedStatement pst = accesoBD.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                System.out.println("can else: "+cantidadColumnas);
                modelo.addColumn("ID-BD");
                modelo.addColumn("CAUSA");
                modelo.addColumn("SOLICITANTE");
                modelo.addColumn("IMPUTADO");
                modelo.addColumn("TIPO-SOLI");
                modelo.addColumn("FECHA");
                modelo.addColumn("FECHA-SOLI");
                modelo.addColumn("HORA");
                modelo.addColumn("HORA-SOLI");
                modelo.addColumn("COPIAS");
                modelo.addColumn("REGION");
                modelo.addColumn("ESTADO");
                modelo.addColumn("ENCARGADO");
                tablaSolicitudForaneas.setModel(modelo);
                int [] anchos = {10,150,70,70,35,35,35,12,10,5,70,70,70};
                for (int x=0; x<cantidadColumnas;x++) {
                    tablaSolicitudForaneas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
                }    
                while (rs.next()) {
                    Object datos[]=new Object[cantidadColumnas];
                    for (int i =0; i<cantidadColumnas;i++ ) {
                        datos[i]=rs.getObject(i+1);
                    }
                    modelo.addRow(datos);
                }
                rs.close();
                int numRegistro=modelo.getRowCount();
                if (numRegistro>0) {
                    totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                } else {
                    totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                    JOptionPane.showMessageDialog(null, "No se encontraron registros en esas fechas"); 
                }
                tablaSolicitudForaneas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(tablaSolicitudForaneas.getSelectedRow() != -1){
                            int fila = tablaSolicitudForaneas.getSelectedRow();
                            txtIdFora.setText(tablaSolicitudForaneas.getValueAt(fila, 0).toString());
                            txtCausaF.setText(tablaSolicitudForaneas.getValueAt(fila, 1).toString());
                            txtSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 2).toString());
                            txtImputadoF.setText(tablaSolicitudForaneas.getValueAt(fila, 3).toString());
                            txtTipoSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 4).toString());
                            txtFechaAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 5).toString());
                            txtHoraAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 7).toString());
                            txtCopiasF.setText(tablaSolicitudForaneas.getValueAt(fila, 9).toString());
                            fechaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 6).toString());
                            horaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 8).toString());
                        }
                    }
                });
            } 
            //String sql="SELECT * FROM `foraneo` WHERE fecha_audiencia BETWEEN  '"+fechaInical+"' AND '"+fechaFinal+"' AND S.ESTADO='"+estado+"' ORDER BY id";

        } catch(SQLException ex) {
            System.err.println(ex.toString());
        }
    }
        
    public void buscarFolioForaneo(String id) {
        //  TODO funcion para hacer la consulta y buscar de acuerdo al id(FOLIO DE LA CONSTANCIA)
        try {
            limpiarDatosFolioF();
            DefaultTableModel modelo= new DefaultTableModel();
            String sql = "";
            //sql= "SELECT DISTINCT S.ID,S.FECHA,S.HORA,S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.FECHA_ENTREGA,S.TIPO,S.SOLICITANTE,S.NUMAMPARO,S.DEPENDENCIA FROM sol_dis INNER JOIN solicitud_externa AS S ON sol_dis.idSolicitud = S.ID INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia WHERE audiencia.CAUSA='"+causa+"' AND S.TIPO='AMPARO' ORDER by S.ID";
            sql = "SELECT * FROM `foraneo` WHERE id = "+id;
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("ID-BD");
            modelo.addColumn("CAUSA");
            modelo.addColumn("SOLICITANTE");
            modelo.addColumn("IMPUTADO");
            modelo.addColumn("TIPO-SOLI");
            modelo.addColumn("FECHA-AU");
            modelo.addColumn("FECHA-SOLI");
            modelo.addColumn("HORA-AU");
            modelo.addColumn("HORA-SOLI");
            modelo.addColumn("COPIAS");
            modelo.addColumn("REGION");
            modelo.addColumn("ESTADO");
            modelo.addColumn("ENCARGADO");
            tablaSolicitudForaneas.setModel(modelo);
            int [] anchos = {10,150,70,70,35,35,35,12,10,5,70,70,70};
            for (int x=0; x<cantidadColumnas;x++) {
                tablaSolicitudForaneas.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }
            while (rs.next()) {
                Object datos[]=new Object[cantidadColumnas];
                for (int i =0; i<cantidadColumnas;i++ ) {
                    datos[i]=rs.getObject(i+1);
                }
                modelo.addRow(datos);
            }
            rs.close();
            int numRegistro=modelo.getRowCount();
            if (numRegistro>0) {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                
                
            } else {
                totalSolicitudForaneas.setText(String.valueOf(numRegistro));
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes con el numero de causa"); 
            }
            tablaSolicitudForaneas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    
                    if(tablaSolicitudForaneas.getSelectedRow() != -1){
                        int fila = tablaSolicitudForaneas.getSelectedRow();
                        txtIdFora.setText(tablaSolicitudForaneas.getValueAt(fila, 0).toString());
                        txtCausaF.setText(tablaSolicitudForaneas.getValueAt(fila, 1).toString());
                        txtSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 2).toString());
                        txtImputadoF.setText(tablaSolicitudForaneas.getValueAt(fila, 3).toString());
                        txtTipoSolicitanteF.setText(tablaSolicitudForaneas.getValueAt(fila, 4).toString());
                        txtFechaAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 5).toString());
                        txtHoraAudienciaF.setText(tablaSolicitudForaneas.getValueAt(fila, 7).toString());
                        txtCopiasF.setText(tablaSolicitudForaneas.getValueAt(fila, 9).toString());
                        fechaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 6).toString());
                        horaSolicitudF.setText(tablaSolicitudForaneas.getValueAt(fila, 8).toString());
                    }
                }
            });
                            /**/
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    public void limpiarDatosFolioF() {
        txtIdFora.setText("");
        txtCausaF.setText("");
        txtSolicitanteF.setText("");
        txtImputadoF.setText("");
        txtTipoSolicitanteF.setText("");
        txtFechaAudienciaF.setText("");
        txtHoraAudienciaF.setText("");
        txtCopiasF.setText("");
    }
    
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
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
           
                new Registrar().setVisible(true);
                  
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnBuscarCausaExterna;
    private javax.swing.JButton BusquedaFolio;
    private javax.swing.JButton BusquedaFolioF;
    private javax.swing.JButton CancelarExternas;
    private javax.swing.JButton CancelarForaneas;
    private javax.swing.JTextField CausaSolicitud;
    private javax.swing.JTextField CausaSolicitudForanea;
    private javax.swing.JLabel Estado;
    public javax.swing.JComboBox<String> EstadoComboExternas;
    public javax.swing.JComboBox<String> EstadoComboExternas2;
    private javax.swing.JComboBox<String> EstadosCombo;
    public javax.swing.JButton InicioSesion;
    public javax.swing.JButton InicioSesion1;
    public javax.swing.JComboBox<String> JBCsalas;
    public javax.swing.JComboBox<String> JBCsalas2;
    public com.toedter.calendar.JDateChooser JDC;
    public com.toedter.calendar.JDateChooser JDC2;
    public com.toedter.calendar.JDateChooser JDbuscarSolicitudE;
    public com.toedter.calendar.JDateChooser JDbuscarSolicitudE1;
    public com.toedter.calendar.JDateChooser JDbuscarSolicitudEFinal;
    public com.toedter.calendar.JDateChooser JDbuscarSolicitudEFinal1;
    public com.toedter.calendar.JDateChooser JDfechaBuscar;
    public com.toedter.calendar.JDateChooser JDfechaBuscarF;
    public com.toedter.calendar.JDateChooser JDfechaBuscarFinal;
    public com.toedter.calendar.JDateChooser JDfechaBuscarFinalF;
    public com.toedter.calendar.JDateChooser JDfinal;
    public com.toedter.calendar.JDateChooser JDinicial;
    public javax.swing.JSpinner JS1;
    public javax.swing.JSpinner JS2;
    public javax.swing.JSpinner JS5;
    public javax.swing.JSpinner JS6;
    public javax.swing.JRadioButton Racuerdo;
    public javax.swing.JRadioButton Racuerdo2;
    private javax.swing.JRadioButton RadioBtnPendiente;
    private javax.swing.JRadioButton RadioBtnRotulado;
    public javax.swing.JRadioButton Ramparo;
    public javax.swing.JRadioButton Ramparo2;
    private javax.swing.JButton ReimprimirExternas;
    private javax.swing.JTable TablaComentario;
    private javax.swing.JTable TablaPartAudiencia;
    public javax.swing.JTable Tparticipantes;
    public javax.swing.JTable Tparticipantes2;
    public javax.swing.JButton btnActualizarP;
    public javax.swing.JButton btnAgregarParte;
    public javax.swing.JButton btnAgregarParte2;
    public javax.swing.JButton btnAgregarSeleccionados;
    public javax.swing.JButton btnAgregarSeleccionados2;
    public javax.swing.JButton btnBuscaSolicitud;
    public javax.swing.JButton btnBuscaSolicitud1;
    public javax.swing.JButton btnBuscaSolicitud3;
    public javax.swing.JButton btnBuscaSolicitudCausa;
    public javax.swing.JButton btnBuscaSolicitudCausaFora;
    public javax.swing.JButton btnBuscaSolicitudF;
    public javax.swing.JButton btnBuscarAu;
    public javax.swing.JButton btnBuscarAu1;
    private javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnConsolidar;
    public javax.swing.JButton btnConstancia;
    public javax.swing.JButton btnConstanciaEx;
    public javax.swing.JButton btnConstanciaFora;
    public javax.swing.JButton btnConsultaAutorizados;
    public javax.swing.JButton btnConsultarP;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnEliminarP;
    public javax.swing.JButton btnEliminarP2;
    public javax.swing.JButton btnEntrega;
    public javax.swing.JButton btnEntregaEx;
    public javax.swing.JButton btnEntregaFora;
    public javax.swing.JButton btnExcel;
    public javax.swing.JButton btnExcelSimple;
    private javax.swing.JButton btnFolioExterna;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnImpEntregasExt;
    private javax.swing.JButton btnImprimirFolios;
    private javax.swing.JButton btnImprimirFoliosFora;
    public javax.swing.JButton btnIrAgregar;
    public javax.swing.JButton btnIrRotular;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnLimpiar1;
    public javax.swing.JButton btnLimpiarForaneo;
    public javax.swing.JButton btnLimpiarGeneral;
    public javax.swing.JButton btnLimpiarS;
    public javax.swing.JButton btnLimpiarSE;
    public javax.swing.JButton btnLimpiarTodo;
    public javax.swing.JButton btnNuevaA;
    public javax.swing.JButton btnPedirComentario;
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JButton btnRegistrar2;
    public javax.swing.JButton btnReporte;
    public javax.swing.JButton btnReporteGeneral;
    public javax.swing.JButton btnSolicitudE;
    public javax.swing.JButton btnTerminar;
    public javax.swing.JButton btnTerminar2;
    public javax.swing.JButton btncAnual;
    public javax.swing.JButton btngeneralBuscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cancelada;
    public javax.swing.JComboBox<String> comboAudiencia;
    public javax.swing.JComboBox<String> comboAudiencia2;
    private javax.swing.JComboBox<String> comboEstadoForanea;
    public javax.swing.JComboBox<String> comboTipo;
    public javax.swing.JComboBox<String> comboTipo2;
    private javax.swing.JLabel conso_txt;
    private javax.swing.JTextField defensa_txt;
    private javax.swing.ButtonGroup externas;
    private javax.swing.ButtonGroup externas2;
    private javax.swing.JLabel fechaSolicitudF;
    public com.toedter.calendar.JDateChooser fecha_audiencia_txt;
    private javax.swing.JTextField fieldComentario;
    private javax.swing.JTextField fiscal_txt;
    public javax.swing.JButton generar_rotulo;
    private javax.swing.JLabel horaSolicitudF;
    public javax.swing.JSpinner hora_txt;
    private javax.swing.JPanel idForanea;
    private javax.swing.JTextField imputado_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton jCanceladaF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    public javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    public javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    public javax.swing.JLabel jLabel52;
    public javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    public static javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jPendienteF;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRotuladoF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton jTodosF;
    public javax.swing.JLabel lblAcuerdo;
    public javax.swing.JLabel lblAmparo;
    public javax.swing.JLabel lblCausa;
    public javax.swing.JLabel lblCopiasEx;
    public javax.swing.JLabel lblDefensaS;
    public javax.swing.JLabel lblDiscoEx;
    public javax.swing.JLabel lblFa;
    public javax.swing.JLabel lblFa2;
    public javax.swing.JLabel lblFechas;
    public javax.swing.JLabel lblFiscal;
    public javax.swing.JLabel lblHoras;
    public javax.swing.JLabel lblIdIntermedia;
    public javax.swing.JLabel lblIdSoli;
    public javax.swing.JLabel lblIdSolicitudEx;
    public javax.swing.JLabel lblIdgeneral;
    public javax.swing.JLabel lblImputadoS;
    public javax.swing.JLabel lblJueces;
    public javax.swing.JLabel lblParticipante;
    public javax.swing.JLabel lblParticipante2;
    public javax.swing.JLabel lblPrueba;
    public javax.swing.JLabel lblPrueba2;
    public javax.swing.JLabel lblSolicitante;
    public javax.swing.JLabel lblTotalAu;
    public javax.swing.JLabel lblhr;
    public javax.swing.JLabel lblhr2;
    public javax.swing.JLabel lblsala;
    public javax.swing.JLabel lblsala2;
    public javax.swing.JSpinner minutos_txt;
    public javax.swing.JLabel num_audiencias;
    private javax.swing.JTextField num_causa_txt;
    private javax.swing.JTextField num_consolidacion_txt;
    private javax.swing.JTextField num_disco_txt;
    private javax.swing.JComboBox salacombo;
    public javax.swing.ButtonGroup tSolicitud;
    public javax.swing.JTable tablaAudiencias;
    public javax.swing.JTable tablaAudienciasGeneral;
    private javax.swing.JTable tablaComentarioExterna;
    public javax.swing.JTable tablaEntrega;
    public javax.swing.JTable tablaParticipantes;
    public javax.swing.JTable tablaSolicitudExterna;
    public javax.swing.JTable tablaSolicitudForaneas;
    public javax.swing.JTable tablapartGeneral;
    private javax.swing.JComboBox tipo_aud;
    public javax.swing.JComboBox<String> tipo_audiencia;
    public javax.swing.JComboBox<String> tipo_audiencia2;
    private javax.swing.JLabel totalSolicitudForaneas;
    private javax.swing.JLabel total_discos;
    private javax.swing.JLabel total_externas;
    private javax.swing.JLabel total_participantes;
    public javax.swing.JLabel totalaud;
    public javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarFolio;
    private javax.swing.JTextField txtBuscarFolioF;
    public javax.swing.JTextField txtCausa;
    public javax.swing.JTextField txtCausa2;
    private javax.swing.JTextField txtCausaF;
    public javax.swing.JTextField txtCausaSoliExter;
    private javax.swing.JLabel txtComentarioSoli;
    public javax.swing.JTextField txtConsolidacion;
    public javax.swing.JTextField txtConsolidacion2;
    public javax.swing.JTextField txtConsolidacionS;
    public javax.swing.JTextField txtCopias;
    private javax.swing.JTextField txtCopiasF;
    public javax.swing.JTextField txtDisco;
    public javax.swing.JTextField txtDiscoS;
    public javax.swing.JTextField txtEcausaS;
    public javax.swing.JTextField txtEcopiasS;
    public javax.swing.JTextField txtEfechaS;
    public javax.swing.JTextField txtEfechaSoli;
    public javax.swing.JTextField txtEhoraS;
    public javax.swing.JTextField txtEjuezS;
    public javax.swing.JTextField txtEnombreS;
    public javax.swing.JTextField txtEtipoS;
    private javax.swing.JTextField txtFechaAudienciaF;
    public javax.swing.JTextField txtFechaS;
    public javax.swing.JTextField txtFolioExterna;
    private javax.swing.JTextField txtHoraAudienciaF;
    public javax.swing.JTextField txtHoraS;
    private javax.swing.JTextField txtIdFora;
    private javax.swing.JTextField txtImputadoF;
    public javax.swing.JTextField txtNombreS;
    public javax.swing.JTextField txtPartes;
    public javax.swing.JTextField txtPartes2;
    public javax.swing.JTextField txtPartes3;
    public javax.swing.JTextField txtSalaS;
    private javax.swing.JTextField txtSolicitanteF;
    public javax.swing.JTextField txtTipoAudiencia;
    public javax.swing.JTextField txtTipoS;
    private javax.swing.JTextField txtTipoSolicitanteF;
    private javax.swing.JTextField txtcomentario;
    public static javax.swing.JLabel txtusuario;
    public static javax.swing.JLabel txtusuario2;
    public javax.swing.JComboBox usuariocombo;
    private javax.swing.JButton video_player;
    // End of variables declaration//GEN-END:variables
}
