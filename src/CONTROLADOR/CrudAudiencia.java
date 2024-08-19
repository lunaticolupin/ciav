/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.AUD_PAR;
import MODELO.AudienciaDAO;
import MODELO.CatalogoDAO;
import MODELO.Conexion;
import MODELO.ConsultaUsuarios;
import MODELO.DiscoDAO;
import MODELO.Historial;
import MODELO.ParticipanteDAO;
import MODELO.SolicitudDAO;
import MODELO.SolicitudExterna;
import MODELO.SolicitudExternaDAO;
import VISTA.AgregarParticipanteAudiencia;
import VISTA.BuscarParticipante;
import VISTA.ConsolidarDiscos;
import VISTA.ConsultaGeneral;
import VISTA.CorteAnual;
import VISTA.EditarParticipante;
import VISTA.ExportarBD;
import VISTA.NuevaAudiencia;
import VISTA.Registrar;
import VISTA.RotularDiscos;
import VISTA.RotularSolicitudesExternas;
import com.mysql.jdbc.exceptions.DeadlockTimeoutRollbackMarker;
//import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.Spinner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import jxl.Workbook;//libreria para el uso de excel
import jxl.write.WritableSheet;//excel
import jxl.write.WritableWorkbook;//excel
import net.sf.jasperreports.engine.JasperCompileManager;//librearia para la creacion de reportes
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class CrudAudiencia implements ActionListener{ 
    Registrar vista = new Registrar();
    EditarParticipante formParticipante = new EditarParticipante();
    AgregarParticipanteAudiencia formAgregar = new AgregarParticipanteAudiencia();
    BuscarParticipante buscarParticipante = new BuscarParticipante();
    AudienciaDAO modeloAudiencia = new AudienciaDAO();
    NuevaAudiencia na = new NuevaAudiencia();
    CatalogoDAO modeloCatalogo = new CatalogoDAO();
    ParticipanteDAO modeloParticipante = new ParticipanteDAO();
    DiscoDAO modeloDisco = new DiscoDAO();
    AUD_PAR modeloIntermedia = new AUD_PAR();
    SolicitudDAO modeloSolicitud =  new SolicitudDAO();
    CorteAnual corte  = new CorteAnual();
    RotularSolicitudesExternas rotuloExternas = new RotularSolicitudesExternas();
    ConsolidarDiscos consolidar =  new ConsolidarDiscos();
    ConsultaGeneral general = new ConsultaGeneral();
    SolicitudExternaDAO modeloSolicitudE = new SolicitudExternaDAO();
    RotularDiscos vistaRotular = new RotularDiscos();
    ExportarBD vistaExportar = new ExportarBD();
    ConsultaUsuarios usuarios = new ConsultaUsuarios();
    Conexion con=new Conexion();
    SpinnerNumberModel modeloHora = new SpinnerNumberModel();
    SpinnerNumberModel modeloMinutos = new SpinnerNumberModel();
    String hora, minutos, completa;
    String columna;
    
   /* java.util.Date date = new java.util.Date();
    SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
    String fechaCompleta = fechaHora.format(date);
    SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
    String horaCompleta = Hora.format(date);*/
    
    
     Calendar fecha = Calendar.getInstance();
    int anio = fecha.get(Calendar.YEAR);
    java.util.Date date = new java.util.Date();
    SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
    String fechaGeneral = fechaHora.format(date);
   

    public CrudAudiencia(Registrar vista, AudienciaDAO modeloAudiencia) {
        //contructor para inicializar todos los botones y ventanas del programa
        this.vista = vista;
        this.modeloAudiencia = modeloAudiencia;
        //this.vista.Barra.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnNuevaA.addActionListener(this);
        this.vista.btnAgregarParte.addActionListener(this);
        this.na.btnNueva.addActionListener(this);
        this.vista.btnAgregarSeleccionados.addActionListener(this);
        this.vista.btnBuscarAu.addActionListener(this);
        this.vista.btnConsultarP.addActionListener(this);
        this.corte.btnCorte.addActionListener(this);
        this.vista.btnBuscaSolicitud.addActionListener(this);
        this.vista.btnConstancia.addActionListener(this);
        this.vista.btnEntrega.addActionListener(this);
        this.vista.btnSolicitudE.addActionListener(this);
        this.vista.btnConstanciaEx.addActionListener(this);
        this.vista.btnEntregaEx.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.btnLimpiarTodo.addActionListener(this);
        this.vista.btngeneralBuscar.addActionListener(this);
        this.vista.btnReporteGeneral.addActionListener(this);
        this.vista.btnTerminar.addActionListener(this);
        this.vista.btnPedirComentario.addActionListener(this);
        this.vista.btnConsultaAutorizados.addActionListener(this);
        this.vista.btnLimpiarGeneral.addActionListener(this);
        this.formParticipante.btnActualizarP.addActionListener(this);
        this.formAgregar.btnBuscarAu.addActionListener(this);
        this.formAgregar.btnTerminar.addActionListener(this);
        this.vista.btnIrAgregar.addActionListener(this);
        this.vista.btncAnual.addActionListener(this);
        this.vista.btnImpEntregasExt.addActionListener(this);
        this.vista.btnIrRotular.addActionListener(this);
        this.vista.btnLimpiarS.addActionListener(this);
        this.vista.btnLimpiarSE.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnExcel.addActionListener(this);
        this.vista.btnExcelSimple.addActionListener(this);
        this.formAgregar.btnAgregarIntermedia.addActionListener(this);
        this.corte.btnAyuda.addActionListener(this);
        this.formParticipante.setLocationRelativeTo(null);
        this.buscarParticipante.setLocationRelativeTo(null);
        this.formParticipante.btnBuscar.addActionListener(this);
        this.buscarParticipante.btnBuscar.addActionListener(this);
        this.formParticipante.btnActualizarAu.addActionListener(this);
        this.formParticipante.btnActualizarCatalogo.addActionListener(this);
        this.formParticipante.btnBuscarPart.addActionListener(this);
        this.formAgregar.btnAgregarN.addActionListener(this);
        this.rotuloExternas.btnBuscar.addActionListener(this);
        this.rotuloExternas.btnRotular.addActionListener(this);
        this.rotuloExternas.lblDefensa.setVisible(false);
        this.rotuloExternas.lblFiscal.setVisible(false);
        this.rotuloExternas.lblImputado.setVisible(false);
        this.rotuloExternas.btnLimpiar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnConsolidar.addActionListener(this);
        this.consolidar.btnAsignar.addActionListener(this);
        this.consolidar.btnBuscar.addActionListener(this);
        this.consolidar.btnCancelar.addActionListener(this);
        this.vista.btnActualizarP.addActionListener(this);
        this.vistaRotular.btnBuscar.addActionListener(this);
        this.vistaRotular.btnRotular.addActionListener(this);
        this.vistaRotular.btnLimpiar.addActionListener(this);
        this.consolidar.btnConsolidarIndividual.addActionListener(this);
        this.formParticipante.setTitle("Editar participante");
        this.vista.setLocationRelativeTo(null);
        this.vista.setTitle("CENTRO DE JUSTICIA PENAL DE PUEBLA");
        this.na.setLocationRelativeTo(null);
        this.corte.setLocationRelativeTo(null);
        this.consolidar.btnLimpiar.addActionListener(this);
        this.vistaExportar.btnBuscar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vistaExportar.btnExportar.addActionListener(this);
        /*if(fechaGeneral.equals("02-10-2017")|| fechaGeneral.equals("03-10-2017")|| fechaGeneral.equals("04-10-2017")){
           vista.btnRegistrar.setVisible(false);
                
        }*/
        modeloHora.setMinimum(0);
        modeloHora.setMaximum(24);
        modeloMinutos.setMinimum(0);
        modeloMinutos.setMaximum(59);
        vista.JS1.setModel(modeloHora);
        vista.JS2.setModel(modeloMinutos);
        vista.txtCausa.grabFocus();
        //se ocultan y deshabilitan los textbox y labels para el usuario
         vista.txtFechaS.setEditable(false);
         vista.txtHoraS.setEditable(false);
         vista.txtSalaS.setEditable(false);
         vista.txtDiscoS.setEditable(false);
         vista.txtNombreS.setEditable(false);
         vista.txtTipoS.setEditable(false);
         vista.txtCopias.setEditable(false);
         vista.lblPrueba.setVisible(false);
         vista.lblParticipante.setVisible(false);
         vista.lblIdSoli.setVisible(false);
         vista.txtEtipoS.setVisible(false);
         vista.txtEcausaS.setEditable(false);
         vista.txtEcopiasS.setEditable(false);
         vista.txtEfechaS.setEditable(false);
         vista.txtTipoAudiencia.setEditable(false);
         vista.txtEfechaSoli.setEditable(false);
         vista.txtEhoraS.setEditable(false);
         vista.txtEjuezS.setEditable(false);
         vista.txtEnombreS.setEditable(false);
         vista.txtDisco.setEditable(false);
         vista.lblImputadoS.setVisible(false);
         vista.lblDefensaS.setVisible(false);
         vista.lblFiscal.setVisible(false);
         vista.txtConsolidacionS.setEditable(false);
         vista.lblIdIntermedia.setVisible(false);
         vistaRotular.lblCausa.setVisible(false);
         vistaRotular.lblDefensa.setVisible(false);
         //vistaRotular.lblDisco.setVisible(false);
         vistaRotular.lblFecha.setVisible(false);
         vistaRotular.lblFiscal.setVisible(false);
         vistaRotular.lblHora.setVisible(false);
         vistaRotular.lblImputado.setVisible(false);
         vistaRotular.lblSala.setVisible(false);
         //vista.lblIdSolicitudEx.setVisible(false);
         vista.lblIdgeneral.setVisible(false);
         formParticipante.lblIdAct.setVisible(false);
         formParticipante.lblIdCatalogo.setVisible(false);
         formAgregar.lblAudiencia.setVisible(false);
         formAgregar.lblPart.setVisible(false);
         rotuloExternas.txtCausa.setEditable(false);
         rotuloExternas.txtDisco.setEditable(false);
         rotuloExternas.txtFecha.setEditable(false);
         rotuloExternas.txtHora.setEditable(false);
         rotuloExternas.txtSala.setEditable(false);
         consolidar.lblAudiencia.setVisible(false);
         formParticipante.lblId.setVisible(false);
         general.lblId.setVisible(false);
         
         //se coloca un mensaje de ayuda en cada boton
         vista.InicioSesion.setToolTipText("Iniciar Sesion");
         vista.btnConstancia.setToolTipText("Imprimir constancia");
         vista.btnEntrega.setToolTipText("Marcar como entregado");
         vista.btnConstanciaEx.setToolTipText("Imprimir constancia");
         vista.btnEntregaEx.setToolTipText("Marcar como entregado");
         vista.btnLimpiarTodo.setToolTipText("Limpiar todos los campos");
         vista.btnReporte.setToolTipText("Imprimir reporte de solicitudes entregadas");
         vista.btnBuscaSolicitud.setToolTipText("Buscar solicitudes");
         vista.btnSolicitudE.setToolTipText("Buscar solicitudes"); 
        llenarCombo();    
        llenarcombousuarios();
        vista.usuariocombo.setSelectedIndex(-1);
        //tablaListener();
        //tablaListenerParticipantes();
            
         
    }
 
    
    

    //en este metodo se empiezan a llamar todos los eventos para hacwer funcionar cada boton
    public void actionPerformed(ActionEvent e){
         
        //EVENTO  QUE REGISTRA LA INFORMACION DE LAS AUDIENCIAS
        if(e.getSource() == vista.btnRegistrar){
        
            if(vista.txtCausa.getText().isEmpty() || vista.txtConsolidacion.getText().isEmpty() || vista.tipo_audiencia.getSelectedIndex()==-1 ){
                JOptionPane.showMessageDialog(null, "Por favor ingrese los datos faltantes", "Campos vacios", JOptionPane.ERROR_MESSAGE);
             
            }

            else{
                             if(vista.txtusuario.getText().isEmpty())
                    {
                        
                JOptionPane.showMessageDialog(null, "Por favor inicie sesion", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
                             else{
                
                hora = vista.JS1.getValue().toString();
                minutos = vista.JS2.getValue().toString();

                 for(int i=0; i<10; i++){
                     if(vista.JS1.getValue().equals(i)){
                         hora = "0" + vista.JS1.getValue().toString();
                     }
                 }

                  for(int i=0; i<10; i++){
                     if(vista.JS2.getValue().equals(i)){
                         minutos = "0" + vista.JS2.getValue().toString();
                     }
                 }
                

                completa = hora+ ":"+ minutos;
                String fecha = ((JTextField) vista.JDC.getDateEditor().getUiComponent()).getText();
                String hora = completa;
                String sala = vista.JBCsalas.getSelectedItem().toString();
                String ncausa = vista.txtCausa.getText().toUpperCase();
                String nombre = vista.comboAudiencia.getSelectedItem().toString();
                int audienciaRegistada = modeloAudiencia.extraerIdCanceladas(fecha, hora, sala);
                String consolidacion = vista.txtConsolidacion.getText();
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
                    int idusuario = usuarios.extraerId(vista.txtusuario.getText());
                    String tipo=(String) vista.tipo_audiencia.getSelectedItem();
                    //System.out.print(id);
                    //se agrega la audiencia a la BD 
                    String rpta = (String) modeloAudiencia.insertar(fecha, hora, sala, ncausa, id,estado,consolidacion,estadoRotulacion,idusuario,tipo); 

                    // se proporciona informacion a usuario de  que audiencia se va a agregar los participantes
                    vista.jLabel13.setText(vista.txtCausa.getText());
                    vista.lblFa.setText(fecha);
                    vista.lblhr.setText(completa);
                    vista.lblsala.setText(sala);
                    vista.lblPrueba.setText(String.valueOf(modeloAudiencia.extraerId(fecha, hora, sala)));

                    if(rpta != null){

                        JOptionPane.showMessageDialog(null, rpta);
                        limpiarCampos();
                        limpiarTabla(vista.Tparticipantes);
                        participantesAgregados(vista.Tparticipantes);// carga la tabla  de los participantes ya agregados a la causa
                        int tamano = vista.Tparticipantes.getRowCount();// obtiene el tamaño de la cantidad de registros
                        // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                         for(int i=0; i<tamano; i++){   
                             vista.Tparticipantes.setValueAt(false, i, 2);
                         }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Datos erroneos, por favor verifique la información");
                    }
                }
            } 
            }
        }
        
        //  EVENTO QUE ABRE LA VENTANA PARA AGREGAR UNA NUEVA TIPO DE AUDIENCIA AL CATALOGO DE AUDIENCIAS
        else if (e.getSource() == vista.btnNuevaA){
            na.setVisible(true);
        }
        //Boton para terminar la audiencia (borra todo el contenido de la tabla y etiquetas)
        else if (e.getSource() == vista.btnTerminar){
            DefaultTableModel modelo = (DefaultTableModel)vista.Tparticipantes.getModel();
            modelo.setRowCount(0);
 //            DefaultTableModel modelo2 = (DefaultTableModel)vista.tablaListener.getModel();
   //          modelo2.setRowCount(0);
            vista.txtPartes.setText("");
            vista.lblPrueba.setText("");
            vista.lblParticipante.setText("");
            vista.txtCausa.setText("");
            ((JTextField)vista.JDC.getDateEditor().getUiComponent()).setText("");
            modeloHora.setValue(0);
            modeloMinutos.setValue(0);
            vista.lblFa.setText("");
            vista.lblhr.setText("");
            vista.lblsala.setText("");
            vista.jLabel13.setText("");
            vista.txtCausa.grabFocus();
            vista.txtConsolidacion.setText("");
            vista.comboAudiencia.setSelectedIndex(-1);
            vista.JBCsalas.setSelectedIndex(-1);
            
        }
        
        
        // EVENTO QUE AGREGA EL NUEVO TIPO DE AUDIENCIAS A LA TABLA "CATALOGO_AUDIENCIA"
        else if (e.getSource() == na.btnNueva){
            
            if(na.txtNuevaAudiencia.getText().isEmpty()){
                
                 JOptionPane.showMessageDialog(null, "Por favor ingrese los datos faltantes", "Campos vacios", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String nombre =  na.txtNuevaAudiencia.getText().toUpperCase();
                String rptaRegistro = (String) modeloCatalogo.insertar(nombre);

                   if(rptaRegistro != null)
                   {
                       JOptionPane.showMessageDialog(null, rptaRegistro);
                       na.txtNuevaAudiencia.setText("");
                       na.txtNuevaAudiencia.grabFocus();
                       vista.comboAudiencia.removeAllItems();
                       llenarCombo();
                       llenarCombo2();
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null, "Datos erroneos, por favor verifique la información");
                       na.txtNuevaAudiencia.grabFocus();
                   }
            }
            
             
        }
        //EVENTO QUE AGREGA UN NUEVO PARTICIPANTE(PARTE) A LA BASE DE DATOS
        else if(e.getSource() == vista.btnAgregarParte){
            if((vista.txtPartes.getText().isEmpty() || vista.comboTipo.getSelectedIndex() == -1)){
                JOptionPane.showMessageDialog(null, "Por favor ingrese los datos faltantes", "Campos vacios", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //vista.txtPartes.setText(null);
                //vista.jtipo.setText(null);
                String nombreP = vista.txtPartes.getText().toUpperCase();
                String tipoP = vista.comboTipo.getSelectedItem().toString();
                //String tipoP = vista.jtipo.getText();
                String nombre = modeloParticipante.verificarExistente(nombreP, tipoP);
                

               // String tipo = modeloParticipante.verificarTipo();
                
               if(nombre !=null){   
                    vista.lblParticipante.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP)));
                   int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText()));
                    System.out.println(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP));
                   if (participante > 0){
                        JOptionPane.showMessageDialog(null, "Participante repetido en la audiencia ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                   }
                    else{
                      String rptaRegistro2 = (String) modeloIntermedia.insertarIntermedia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText().toUpperCase()));
                        JOptionPane.showMessageDialog(null, rptaRegistro2);
                        limpiarTabla(vista.Tparticipantes);
                        participantesAgregados(vista.Tparticipantes);
                        int tamano = vista.Tparticipantes.getRowCount();// obtiene el tamaño de la cantidad de registros
                        // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                         for(int i=0; i<tamano; i++){   
                             vista.Tparticipantes.setValueAt(false, i, 2);
                         }
                        vista.txtPartes.setText("");
                   }
                    

                }
                else{
                   String rptaRegistro = (String) modeloParticipante.insertarParticipante(nombreP, tipoP);
                    vista.lblParticipante.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP)));
                    int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText()));
                    System.out.print(modeloParticipante.extraerIdParticipanteExacto(nombreP,tipoP));
                    if(participante > 0){
                        JOptionPane.showMessageDialog(null, "Participante repetido en la audiencia ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                    }else {
                        String rptaRegistro2 = (String) modeloIntermedia.insertarIntermedia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText()));
                        JOptionPane.showMessageDialog(null, rptaRegistro2);
                        limpiarTabla(vista.Tparticipantes);
                        participantesAgregados(vista.Tparticipantes);
                        int tamano = vista.Tparticipantes.getRowCount();// obtiene el tamaño de la cantidad de registros
                        // este  ciclo for esta sirviendo para inicializar todas las columnas  boolean en "false"  una vez que se hizo la consulta
                         for(int i=0; i<tamano; i++){   
                             vista.Tparticipantes.setValueAt(false, i, 2);
                         }
                        vista.txtPartes.setText("");
                    }
                    

                }

            }
            
         
            
        }
 
        
        //Con este evento agregamos a los participantes seleccionados en la tabla tomando como referencia que los valores esten en true
        else if (e.getSource() == vista.btnAgregarSeleccionados){
            
            DefaultTableModel modelo = (DefaultTableModel)vista.Tparticipantes.getModel();
            int tamano = vista.Tparticipantes.getRowCount();    
            
            for (int i = 0; i < tamano; i++) {
                Object nombre = vista.Tparticipantes.getValueAt(i, 0);
                Object tipo = vista.Tparticipantes.getValueAt(i, 1);
                Boolean estado = new Boolean(true);
                Boolean estadoColumna = Boolean.valueOf(vista.Tparticipantes.getValueAt(i, 2).toString());
                
                if(estadoColumna.equals(estado)){

                        System.out.print(estadoColumna);
                        //JOptionPane.showMessageDialog(null,nombre + " "+ tipo);
                        vista.lblParticipante.setText(String.valueOf(modeloParticipante.extraerIdParticipanteExacto(nombre.toString(),tipo.toString())));
                        int participante = modeloIntermedia.verificarParticipanteAudiencia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText()));
                        if(participante > 0){
                            //JOptionPane.showMessageDialog(null, "Participante repetido "+nombre + " "+ tipo);
                        } else {
                            //String rptaRegistro;
                            //rptaRegistro = (String) 
                                    modeloIntermedia.insertarIntermedia(Integer.parseInt(vista.lblPrueba.getText()), Integer.parseInt(vista.lblParticipante.getText()));


                            /*if(rptaRegistro != null){
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
        }
        /*//Boton que abre la vista para buscar participantes
        else if (e.getSource()== vista.btnBuscarP){
            buscarParticipante.setVisible(true);
            //mostrarParticipantes3(buscarParticipante.Tparticipantes);
        }*/
        //con este boton se hace una busqueda de los participantes dependiendo del radio button seleccionado
        else if (e.getSource()== buscarParticipante.btnBuscar){
            buscarParticipante.Gparticipantes.add(buscarParticipante.Rajuridico);
            buscarParticipante.Gparticipantes.add(buscarParticipante.Rdefensa);
            buscarParticipante.Gparticipantes.add(buscarParticipante.Rfiscal);
            buscarParticipante.Gparticipantes.add(buscarParticipante.Rjuez);
            buscarParticipante.Gparticipantes.add(buscarParticipante.Rimputado);
            
            if(buscarParticipante.Rajuridico.isSelected()){
                mostrarParticipantes3(buscarParticipante.Tparticipantes, "ASESOR JURIDICO");
            }
            else if (buscarParticipante.Rdefensa.isSelected()){
                mostrarParticipantes3(buscarParticipante.Tparticipantes,"DEFENSA");
            }
            else if(buscarParticipante.Rfiscal.isSelected()){
                mostrarParticipantes3(buscarParticipante.Tparticipantes, "FISCAL");
            }
            else if(buscarParticipante.Rjuez.isSelected()){
                mostrarParticipantes3(buscarParticipante.Tparticipantes, "JUEZ");
            }
            else if(buscarParticipante.Rimputado.isSelected()){
                mostrarParticipantes3(buscarParticipante.Tparticipantes, "IMPUTADO");
            }
            
            
        }
        
        //Con este evento buscamos todas audiencias de la causa escrita
        else if(e.getSource() == vista.btnBuscarAu){
          
            if(vista.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese  el número de causa");
            }
            else{
                DefaultTableModel modelo = (DefaultTableModel)vista.tablaAudiencias.getModel();
                modelo.setRowCount(0);
                 llenarTabla(vista.tablaAudiencias);  
                //participantesAgregadosSolicitud(vista.tablaParticipantes);
               //participantesAgregadosSolicitud(vista.tablaParticipantes);
            }
            
        }
        //consulta los participantes de la audiencia seleccionada, con este metodo se puede modificar directamente desde la tabla cargada
        else if (e.getSource() == vista.btnConsultarP){
           /* DefaultTableModel modelo = (DefaultTableModel)vista.tablaParticipantes.getModel();
            modelo.setRowCount(0);
            participantesAgregadosSolicitud(vista.tablaParticipantes);*/
        }
        else if (e.getSource() == vista.btnLimpiarTodo){
       //     if (fechaGeneral.equals("01-11-2017")||fechaGeneral.equals("02-11-2017")
       //             ||fechaGeneral.equals("03-11-2017") ||fechaGeneral.equals("03-11-2017")){
      //      vista.btnConsolidar.setVisible(false);
      //  } else {
                vista.txtFechaS.setText("");
                vista.txtHoraS.setText("");
                vista.txtSalaS.setText("");
                vista.txtDiscoS.setText("");
                vista.txtNombreS.setText("");
                vista.txtTipoS.setText("");
                vista.txtCopias.setText("");
                vista.txtBuscar.setText("");
                vista.lblIdIntermedia.setText("");
                vista.txtConsolidacionS.setText("");
                DefaultTableModel modelo1 = (DefaultTableModel)vista.tablaAudiencias.getModel();
                DefaultTableModel modelo2 = (DefaultTableModel) vista.tablaParticipantes.getModel();
                modelo1.setRowCount(0);
                modelo2.setRowCount(0);
          //  } 
 
        } 
        
        //
        //con este boton primero elimina los registros de una tabla temporal que guarda los discos con el año actual
        // despues cambia el numero de id y lo pone en "1" para empezar de nuevo con la numeracion de discos
        else if(e.getSource() == corte.btnCorte){
            int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea hacer el reinicio de disco actual", "Confirmación de corte anual", JOptionPane.OK_CANCEL_OPTION);
            if (respuesta == 0){
                 modeloDisco.eliminar();
                 modeloDisco.cambiarIcrementable();
            } else{
                JOptionPane.showMessageDialog(null, "Se ha cancelado el corte anual");
            }
           
        }
        //Abre la ventana de corte de discos 
        else if(e.getSource() == vista.btncAnual){
            corte.setVisible(true);
        }
        //Boton de ayuda que muestra un mensaje de como utilizar la ventana
        else if(e.getSource() == corte.btnAyuda){
            JOptionPane.showMessageDialog(null, "Esta ventana sirve para poder hacer un corte anual de disco, al momento de dar click en el boton se reinicia el  "
                    + "autoincrementable del numero de disco actual. "
                    + "\n \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t"
                    + "\t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t"
                    + "Se debe hacer  al momento de que sea un año nuevo  "
                    + "\n \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t"
                    + "\t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t \t\t \t\t \t\t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t\t \t"
                    + "Ejemplo: disco actual : 100-2017 al hacer click ----> 1-2018 ",
                    "Acerca del módulo corte",JOptionPane.INFORMATION_MESSAGE);
        }
        //Busca las solicitudes echas dependiendo de la fecha indicada
        else if(e.getSource() == vista.btnBuscaSolicitud){
          /*  String fecha = ((JTextField)vista.JDfechaBuscar.getDateEditor().getUiComponent()).getText();
            String fechaFinal =  ((JTextField)vista.JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
            if (fecha.isEmpty() || fechaFinal.isEmpty()){
                JOptionPane.showMessageDialog(null, "Indique la fecha de la solicitud","Fecha vacia",JOptionPane.ERROR_MESSAGE);
            } else {
      
                    //System.out.println(fecha);
                    //System.out.println(fechaFinal);
                    DefaultTableModel modelo = (DefaultTableModel)vista.tablaEntrega.getModel();
                    modelo.setRowCount(0);
                    llenarTablaSolicitud(vista.tablaEntrega);
                    
            }*/
            
        }
        //CON ESTE EVENTO SE ACTUALIZA LA FECHA Y LA HORA DE LA CONSTANCIA DE LA SOLICITUD
        else if(e.getSource()==vista.btnConstancia){
           /* int numSolicitud = Integer.parseInt(vista.lblIdSoli.getText());
            int numParte = modeloSolicitud.extraerIdparticipante(Integer.parseInt(vista.lblIdSoli.getText()));
            int numDisco = modeloSolicitud.extraerNumdisco(Integer.parseInt(vista.lblIdSoli.getText()));
            int numAudiencia = modeloDisco.extraerIdAudiencia(numDisco);
            String juez = vista.txtEjuezS.getText();
            String fAudiencia = vista.txtEfechaS.getText();
            String hora = vista.txtEhoraS.getText();
            String causa = vista.txtEcausaS.getText();
            String parte = vista.txtEnombreS.getText();
            String tipo = vista.txtEtipoS.getText();
            String disco = vista.txtDisco.getText();
            String imputado = vista.lblImputadoS.getText();
            String defensa = vista.lblDefensaS.getText();
            String fiscal = vista.lblFiscal.getText();
            String folio = vista.lblIdSoli.getText();
          
            int copias = Integer.parseInt(vista.txtEcopiasS.getText());
            String estado = "ROTULADO";
            
            java.util.Date date = new java.util.Date();
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
            String fechaCompleta = fechaHora.format(date);
            SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
            String horaCompleta = Hora.format(date);
            
            int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea generar la constancia?", "Confirmación constancia", JOptionPane.OK_CANCEL_OPTION);
            if(dialogoResultado ==0){
                    modeloSolicitud.actualizarSolicitud(Integer.parseInt(vista.lblIdSoli.getText()),fechaCompleta,horaCompleta,estado);
                    modeloSolicitud.solicitud(numSolicitud, numParte, numDisco);
                    modeloSolicitud.generar(juez,fAudiencia,causa,parte,fechaCompleta,horaCompleta,copias,folio);
                    modeloSolicitud.generarRotulo(fAudiencia, hora , causa, disco, imputado, defensa, fiscal);
                    modeloSolicitud.borrarContenidoTabla();
                    limpiarCamposSolicitud();
                    DefaultTableModel modelo = (DefaultTableModel)vista.tablaEntrega.getModel();
                    modelo.setRowCount(0);
                    llenarTablaSolicitud(vista.tablaEntrega);
          
                             
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha cancelado la elaboración ");
            }*/
            
        }
        //Boton que actualiza la fecha y hora de la solicitud de disco
        //Ademas cambia el estado a Entregado
        else if (e.getSource() == vista.btnEntrega){
            
          /*  java.util.Date date = new java.util.Date();
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
            String fechaCompleta = fechaHora.format(date);
            SimpleDateFormat Hora = new SimpleDateFormat("HH:mm:ss");
            String horaCompleta = Hora.format(date);
            int numSolicitud = Integer.parseInt(vista.lblIdSoli.getText());
            String estado = "Entregado";
            
            int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar la entrega?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
            if(dialogoResultado == 0){
                modeloSolicitud.actualizarEntrega(numSolicitud, fechaCompleta, horaCompleta,estado);
                limpiarCamposSolicitud();
                DefaultTableModel modelo = (DefaultTableModel)vista.tablaEntrega.getModel();
                modelo.setRowCount(0);
                llenarTablaSolicitud(vista.tablaEntrega);
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha cancelado la entrega");
            }*/
        }
        //Boton de buscar en la parte de solicitud externa (amparo/acuerdo)
        //hace la busqueda dependiendo del chekcbox seleccionado
       // else if(e.getSource() == vista.btnSolicitudE){
         //   vista.tSolicitud.add(vista.Racuerdo);
           // vista.tSolicitud.add(vista.Ramparo);
            
         //if(vista.Racuerdo.isSelected()){
            // if(vista.JDbuscarSolicitudE.toString().isEmpty() || vista.JDbuscarSolicitudEFinal.toString().isEmpty())
            // {
              //   JOptionPane.showMessageDialog(null, "ingrese las fechas");
             //}
             //else{
             
//limpiarSolicitudExterna();
// String fecha = ((JTextField)vista.JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
 //String fechaFinal = ((JTextField)vista.JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
 //modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal);
//llenarTablaSolicitudExternaAcuerdo(vista.tablaSolicitudExterna);
             //}
       //}
            //if(vista.Ramparo.isSelected()){
                
              //  limpiarSolicitudExterna();
                //llenarTablaSolicitudExternaAmparo(vista.tablaSolicitudExterna);
            //}
      // }
        //Evento que actualiza la fecha de entregas externas
        else if(e.getSource() == vista.btnEntregaEx){
            /*java.util.Date date = new java.util.Date();
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
            String fechaCompleta = fechaHora.format(date);
            String estado = "Entregado";
            int numSolicitud = Integer.parseInt(vista.lblIdSolicitudEx.getText());
            
            int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea marcar la entrega?","Confirmación entrega",JOptionPane.OK_CANCEL_OPTION);
            if(dialogoResultado == 0){
                modeloSolicitudE.actualizarEntregaExterna(numSolicitud, fechaCompleta, estado);
                if(vista.Racuerdo.isSelected()){
                llenarTablaSolicitudExternaAcuerdo(vista.tablaSolicitudExterna);
                vista.lblFechas.setText("");
                vista.lblHoras.setText("");
                vista.lblCausa.setText("");
                vista.lblDiscoEx.setText("");
                vista.lblJueces.setText("");
                vista.lblSolicitante.setText("");
                vista.lblCopiasEx.setText("");
                vista.lblAcuerdo.setText("");
                
                }
                else if(vista.Ramparo.isSelected()){
                    llenarTablaSolicitudExternaAmparo(vista.tablaSolicitudExterna);
                      vista.lblFechas.setText("");
                    vista.lblHoras.setText("");
                    vista.lblCausa.setText("");
                    vista.lblDiscoEx.setText("");
                    vista.lblJueces.setText("");
                    vista.lblSolicitante.setText("");
                    vista.lblCopiasEx.setText("");
                    vista.lblAmparo.setText("");
                }
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha cancelado la entrega");
            }   */
        }
        //Evento que imprime la constancia de las solicitudes externas
        else if (e.getSource() == vista.btnConstanciaEx){
                        java.util.Date date = new java.util.Date();
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
            String fechaCompleta = fechaHora.format(date);
            
            SimpleDateFormat fechaH = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
            String completa = fechaH.format(date);
            
            SimpleDateFormat Hora = new SimpleDateFormat("HH:mm");
            String horaCompleta = Hora.format(date);
            int folio = Integer.parseInt(vista.lblIdSolicitudEx.getText());
            int solicitud = Integer.parseInt(vista.lblIdSolicitudEx.getText());
            int copias = Integer.parseInt(vista.lblCopiasEx.getText());
            String acuerdo = vista.lblAcuerdo.getText();
            //String juez = modeloSolicitudE.extJuez(solicitud);
            String solicitante = vista.lblSolicitante.getText();
            String causa = vista.lblCausa.getText();
            String amparo = vista.lblAmparo.getText();
            int numRegistros = modeloSolicitudE.lsFechas(folio).size();
            String juez = vista.lblJueces.getText();
            String estado = "ROTULADO";
            String solicitud2 = vista.lblIdSolicitudEx.getText();
            /*
            // Se extraer todas la fechas de las audiencias
            Object  [] fechas = new Object[numRegistros];
            for(int i = 0; i< numRegistros; i++)
            {
                fechas[i] = modeloSolicitudE.lsFechas(folio).get(i).getFecha();

            }
            // se muestran las fechas de audiencias en los labels
            vista.lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+","); */
            //vista.lblJueces.setText(juez);
            String extraerFechas = vista.lblFechas.getText();
            int dialogoResultado = JOptionPane.showConfirmDialog(null, "¿Realmente desea generar la constancia?", "Confirmació constancia", JOptionPane.OK_CANCEL_OPTION);
            int seleccion=vista.EstadoComboExternas.getSelectedIndex();
            int seleccion2=vista.EstadoComboExternas2.getSelectedIndex();
            String causa2=vista.txtCausaSoliExter.getText();
            if(dialogoResultado == 0){
                rotuloExternas.setVisible(true);
                rotuloExternas.setLocationRelativeTo(null);
                
                if(vista.txtCausaSoliExter.getText().isEmpty())
                {
                    if(vista.txtFolioExterna.getText().isEmpty())
                    {  
                    if (vista.Racuerdo.isSelected()){
                    modeloSolicitudE.actualizarConstanciaExterna(solicitud,completa , estado);
                    modeloSolicitudE.imprimirSolicitud(solicitud);
                    modeloSolicitudE.generarAcuerdo(juez, extraerFechas, causa, solicitante, fechaCompleta, horaCompleta, copias,acuerdo,solicitud2);
                    modeloSolicitudE.borrarContenidoTabla();
                    if(seleccion==0)
                    {   
                        estado="todos";
                    vista.llenarTablaExternaAcuerdo(estado);

                    }
                    else if(seleccion==2)
                    {estado="PENDIENTE";
                    vista.llenarTablaExternaAcuerdo(estado);
                    }
                    else if(seleccion==3)
                    {
                        estado="ROTULADO";
                     vista.llenarTablaExternaAcuerdo(estado);
 
                    }

                
                }
                else if (vista.Ramparo.isSelected()){
                     modeloSolicitudE.actualizarConstanciaExterna(solicitud,completa , estado);
                     modeloSolicitudE.imprimirSolicitud(solicitud);
                     modeloSolicitudE.generarAmparo(juez, extraerFechas, causa, fechaCompleta, horaCompleta, copias,amparo,solicitud2);
                     modeloSolicitudE.borrarContenidoTabla();
                                         if(seleccion==0)
                    {   
                        estado="todos";
                    vista.llenarTablaExternaAmparo(estado);

                    }
                    else if(seleccion==2)
                    {estado="PENDIENTE";
                    vista.llenarTablaExternaAmparo(estado);
                    }
                    else if(seleccion==3)
                    {
                        estado="ROTULADO";
                     vista.llenarTablaExternaAmparo(estado);
 
                    }
                                          

                }
                }
                    else{
                    modeloSolicitudE.actualizarConstanciaExterna(solicitud,completa , estado);
                    modeloSolicitudE.imprimirSolicitud(solicitud);
                    modeloSolicitudE.generarAcuerdo(juez, extraerFechas, causa, solicitante, fechaCompleta, horaCompleta, copias,acuerdo,solicitud2);
                    modeloSolicitudE.borrarContenidoTabla();
                         String tipo=vista.ExtraerTipoSolicitud(folio);
                            if(tipo.equals("ACUERDO"))
                            {
                                vista.llenarTablaFolioExternaAcuerdo(folio);
                            }else{
                                  vista.llenarTablaFolioExternaAmparo(folio);
                                 }
                     }
                }
                else{
                   if (vista.Racuerdo2.isSelected()){
                    modeloSolicitudE.actualizarConstanciaExterna(solicitud,completa , estado);
                    modeloSolicitudE.imprimirSolicitud(solicitud);
                    modeloSolicitudE.generarAcuerdo(juez, extraerFechas, causa, solicitante, fechaCompleta, horaCompleta, copias,acuerdo,solicitud2);
                    modeloSolicitudE.borrarContenidoTabla();
                    if(seleccion2==0)
                    {   
                        estado="todos";
                    vista.llenarTablaAcuerdoCausa(estado,causa2);

                    }
                    else if(seleccion2==2)
                    {estado="PENDIENTE";
                    vista.llenarTablaAcuerdoCausa(estado,causa2);
                    }
                    else if(seleccion2==3)
                    {
                        estado="ROTULADO";
                     vista.llenarTablaAcuerdoCausa(estado,causa2);
 
                    }

                
                }
                else if (vista.Ramparo2.isSelected()){
                     modeloSolicitudE.actualizarConstanciaExterna(solicitud,completa , estado);
                     modeloSolicitudE.imprimirSolicitud(solicitud);
                     modeloSolicitudE.generarAmparo(juez, extraerFechas, causa, fechaCompleta, horaCompleta, copias,amparo,solicitud2);
                     modeloSolicitudE.borrarContenidoTabla();
                                         if(seleccion2==0)
                    {   
                        estado="todos";
                    vista.llenarTablaAmparoCausa(estado,causa2);

                    }
                    else if(seleccion2==2)
                    {estado="PENDIENTE";
                    vista.llenarTablaAmparoCausa(estado,causa2);
                    }
                    else if(seleccion2==3)
                    {
                        estado="ROTULADO";
                     vista.llenarTablaAmparoCausa(estado,causa2);
 
                    }
                                          

                }   
                    
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Ha cancelado la elaboración ");
            }
           
        }
        else if (e.getSource() == vista.btnReporte){
            modeloSolicitud.generarEntregas();
        }
        //Abrir formulario para editar algun participante
        else if(e.getSource() == vista.btnActualizarP){
            //DefaultTableModel modeloParticipantes = (DefaultTableModel)formParticipante.tablaPart.getModel();
            //modeloParticipantes.setRowCount(0);
            formParticipante.CBTiposPart.setSelectedIndex(-1);
            DefaultTableModel modeloCatalogo = (DefaultTableModel)formParticipante.tablaCatalogo.getModel();
            modeloCatalogo.setRowCount(0);
            
            formParticipante.setVisible(true);
            //mostrarParticipantes(formParticipante.tablaPart);
            tablaCatalogo(formParticipante.tablaCatalogo);
            //editarAudiencia(formParticipante.tablaAudiencias);
            llenarComboActualizar();
            
        }
       // else if (e.getSource() == formParticipante.btnBuscarPart){
         //   mostrarParticipantes(formParticipante.tablaPart);
        //}
        //Boton para buscar las audiencia dependiendo de la causa escrita
        else if(e.getSource()== formParticipante.btnBuscar){
            if(formParticipante.txtBcausa.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese el número de causa");
            }
            else{

                editarAudiencia(formParticipante.tablaAudiencias);
            }
            
            
        }
        //Actualiza la iformacion de una audiencia
        else if (e.getSource() == formParticipante.btnActualizarAu){
            String consolidacion="";
            String fecha = formParticipante.txtFecha.getText();
            String hora = formParticipante.txtHora.getText();
            String sala = formParticipante.txtSala.getText();
            String causa = formParticipante.txtCausa.getText();
            String tipo = formParticipante.CBtipos.getSelectedItem().toString();
            String tipoau = formParticipante.CBtipo.getSelectedItem().toString();
            int idCatalogo = modeloCatalogo.extraerId(tipo);
            //int consolidacion = Integer.parseInt(formParticipante.txtConsolidacion.getText());
            //PARA PODER ACEPTAR STRING EN VEZ DE ENTERO
            consolidacion = formParticipante.txtConsolidacion.getText();
            ///String tipo = formParticipante.txtTipo.getText().trim();
            String id = formParticipante.lblId.getText();
            DefaultTableModel modelo = (DefaultTableModel)formParticipante.tablaAudiencias.getModel();
            if(fecha.isEmpty() || hora.isEmpty() || sala.isEmpty() || causa.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor seleccione una audiencia de la tabla","Error al actualizar",JOptionPane.ERROR_MESSAGE);
            }
            else{
                modeloAudiencia.actualizarAudiencia(fecha, hora, sala, causa,idCatalogo, Integer.parseInt(id),consolidacion,tipoau);
                //editarAudiencia(formParticipante.tablaAudiencias);
                formParticipante.txtBcausa.setText("");
                formParticipante.txtCausa.setText("");
                formParticipante.txtFecha.setText("");
                formParticipante.txtHora.setText("");
                formParticipante.txtSala.setText("");
                formParticipante.txtConsolidacion.setText("");
                formParticipante.CBtipos.setSelectedIndex(-1);
                formParticipante.CBtipo.setSelectedIndex(-1);
                modelo.setRowCount(0);
            }
            
            
        }
        //Boton actualizar participante
        else if(e.getSource() == formParticipante.btnActualizarP){
             //DefaultTableModel modeloParticipantes = (DefaultTableModel)formParticipante.tablaPart.getModel();
             //modeloParticipantes.setRowCount(0);
            if(formParticipante.txtNombre.getText().isEmpty() ||  formParticipante.CBTiposPart.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(null, "Por favor escriba un participante o seleccione tipo", "Campos vacios", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if( formParticipante.lblIdAct.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor presione la tecla *SHIFT* para seleccionar el nombre a modificar ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else{
                modeloParticipante.actualizarParticipante(Integer.parseInt(formParticipante.lblIdAct.getText()), formParticipante.txtNombre.getText().toUpperCase(), 
                formParticipante.CBTiposPart.getSelectedItem().toString());
                //mostrarParticipantes(formParticipante.tablaPart);
                formParticipante.txtNombre.setText("");
                formParticipante.CBTiposPart.setSelectedIndex(-1);
                formParticipante.lblIdAct.setText("");
                }
            }
        }
        //Boton que te lleva al formulario de agregar participantes a audiencia ya registrada
        else if(e.getSource() == vista.btnIrAgregar){
            formAgregar.setVisible(true);
            formAgregar.setLocationRelativeTo(null);
            formAgregar.tipocombo.setSelectedIndex(-1);
                     String fecha = vista.txtFechaS.getText();
                    String hora = vista.txtHoraS.getText();
                    String sala =vista.txtSalaS.getText();
                    String causa = vista.txtBuscar.getText();
                    formAgregar.txtHora.setText(hora);
                    formAgregar.JDFechaParticipante.setText(fecha);
                    formAgregar.txtSala.setText(sala);
                    formAgregar.txtCausa.setText(causa);
            //mostrarParticipantes2(formAgregar.tablaPart);
        }
        //Boton que busca el id de audiencia relaiconado la la informacion escrita
        else if(e.getSource() == formAgregar.btnBuscarAu){
            //String fecha = ((JTextField)formAgregar.JDfecha.getDateEditor().getUiComponent()).getText();
            String fecha=formAgregar.JDFechaParticipante.getText();
            String hora = formAgregar.txtHora.getText();
            String sala = formAgregar.txtSala.getText();
            String causa = formAgregar.txtCausa.getText();
            if(fecha.isEmpty() || hora.isEmpty() || sala.isEmpty() || causa.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, introduzca todos los campos");
            }
            else{
                int audiencia =  modeloAudiencia.extraerIdExacta(fecha, hora, sala, causa);
                System.out.print(audiencia);
                formAgregar.lblAudiencia.setText(String.valueOf(audiencia));
                if (audiencia > 0){
                    JOptionPane.showMessageDialog(null, "Audiencia encontrada");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Audiencia no encontrada","Error audiencia no encontrada", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
        //Agregar nuevo participante a la tabla participante
       /*else if(e.getSource() == formAgregar.btnAgregarNuevo){
            String nombre = modeloParticipante.verificarNombre();
            String tipo = modeloParticipante.verificarTipo();
            if(formAgregar.txtNombre.getText().isEmpty() || formAgregar.txtTipo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Por favor, ingrese el nombre y el tipo de participante a agregar");
            }
            else{
                
                if(nombre.equals(formAgregar.txtNombre.getText())){
                    JOptionPane.showMessageDialog(null, "El nombre escrito ya existe");
                }
                else{
                    String respuesta = (String) modeloParticipante.insertarParticipante(formAgregar.txtNombre.getText(), formAgregar.txtTipo.getText());
                    if(respuesta != null){
                        JOptionPane.showMessageDialog(null, "Participante agregado con éxito");
                        mostrarParticipantes2(formAgregar.tablaPart);
                        formAgregar.txtNombre.setText("");
                        formAgregar.txtTipo.setText("");
                    }
                }
                
                
            }
            
        }*/
        //Boton que agregar un participante a una audiencia ya registrada
        /*else if(e.getSource()== formAgregar.btnAgregarIntermedia){
            //int audiencia = Integer.parseInt(formAgregar.lblAudiencia.getText());
            int idParticipante = modeloParticipante.extraerIdParticipante(formAgregar.txtnombreparti.getText());
                    formAgregar.lblPart.setText(String.valueOf(idParticipante));
            String audiencia =formAgregar.lblAudiencia.getText();
            String participante =formAgregar.lblPart.getText();
            //String participante = Integer.parseInt(formAgregar.lblPart.getText());
            
            if(audiencia.isEmpty() || participante.isEmpty()){
                JOptionPane.showMessageDialog(null, "Error al agregar, verifique que haya buscado la audiencia y seleccionado al participante","Error al agregar",JOptionPane.ERROR_MESSAGE);
            }
            
                String respuesta = (String)modeloIntermedia.insertarIntermedia(Integer.parseInt(audiencia),Integer.parseInt(participante));
                if (respuesta != null){
                    JOptionPane.showMessageDialog(null,"Participante agregado con éxito a la audiencia");
                    //formAgregar.lblAudiencia.setText("");
                    formAgregar.lblPart.setText("");
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Hubo un error al agregar al participante");
                
            }
            
        }*/
        //Borra el ID de la audiencia Y todos los campos
        else if (e.getSource() == formAgregar.btnTerminar){
             formAgregar.txtCausa.setText("");
             formAgregar.txtHora.setText("");
             formAgregar.txtSala.setText("");
             formAgregar.lblAudiencia.setText("");
             //((JTextField)formAgregar.JDfecha.getDateEditor().getUiComponent()).setText("");
             formAgregar.JDFechaParticipante.setText("");
             formAgregar.lblPart.setText("");
             formAgregar.txtnombreparti.setText("");
             DefaultTableModel modelo = (DefaultTableModel)formAgregar.tablaPart.getModel();
             modelo.setRowCount(0);
            
        }
        //Buscar las audiencias apartir del foio prooprcionado
        else if (e.getSource() == rotuloExternas.btnBuscar){
           /* int folio = Integer.parseInt( rotuloExternas.txtFolio.getText());
            mostrarAudiencias(rotuloExternas.Taudiencias,folio); */
        }
        else if(e.getSource() == rotuloExternas.btnRotular) {
           try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = rotuloExternas.txtFecha.getText();
                Date fecha1 = formatter.parse(fecha);
                
                String juez = vista.lblJueces.getText();
                System.out.println("juez :"+juez);
                String hora = rotuloExternas.txtHora.getText();
                String causa = rotuloExternas.txtCausa.getText();
                String disco = rotuloExternas.txtDisco.getText();
                String imputado = rotuloExternas.lblImputado.getText();
                String defensa = rotuloExternas.lblDefensa.getText();
                String fiscal =  rotuloExternas.lblFiscal.getText();
                String folio = rotuloExternas.txtFolio.getText();
                String consolidacion= rotuloExternas.txt_consolidacion.getText();
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea generar el disco","Confirmación de rotulo",JOptionPane.OK_CANCEL_OPTION);
                if (respuesta ==0){
                    String[] opciones={"CANON","EPSON"};
                    int seleccion=JOptionPane.showOptionDialog(null, "Seleccione la impresora en la cual va a rotular", "seleccion de impresora", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if(seleccion==0) {
                        modeloSolicitudE.generarRotulo(fecha1, hora, causa, disco, imputado, defensa, fiscal,juez,Integer.parseInt(folio),consolidacion);
                        rotuloExternas.txtCausa.setText("");
                        rotuloExternas.txtDisco.setText("");
                        rotuloExternas.txtFecha.setText("");
                        rotuloExternas.txtHora.setText("");
                        rotuloExternas.txtSala.setText("");
                        rotuloExternas.lblDefensa.setText("");
                        rotuloExternas.lblFiscal.setText("");
                        rotuloExternas.lblImputado.setText("");
                        rotuloExternas.txtFolio.setText("");
                        DefaultTableModel modelo= (DefaultTableModel)rotuloExternas.Taudiencias.getModel();
                        modelo.setRowCount(0);
                    } else {
                        modeloSolicitudE.generarRotulo2(fecha1, hora, causa, disco, imputado, defensa, fiscal,juez,Integer.parseInt(folio),consolidacion);
                        rotuloExternas.txtCausa.setText("");
                        rotuloExternas.txtDisco.setText("");
                        rotuloExternas.txtFecha.setText("");
                        rotuloExternas.txtHora.setText("");
                        rotuloExternas.txtSala.setText("");
                        rotuloExternas.lblDefensa.setText("");
                        rotuloExternas.lblFiscal.setText("");
                        rotuloExternas.lblImputado.setText("");
                        rotuloExternas.txtFolio.setText("");
                        DefaultTableModel modelo= (DefaultTableModel)rotuloExternas.Taudiencias.getModel();
                        modelo.setRowCount(0);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Creación del rotulo cancelada");
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        }
        else if(e.getSource() == vista.btnEliminar){
          /*  String nombre = vista.txtNombreS.getText();
            String fecha = vista.txtFechaS.getText();
            String hora = vista.txtHoraS.getText();
            String sala = vista.txtSalaS.getText();
            String tipo = vista.txtTipoS.getText();
            int participante =  modeloParticipante.extraerIdParticipanteEliminar(nombre,tipo);
            int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
            int intermedia = modeloIntermedia.extraerId(audiencia, participante);
            System.out.print(intermedia);
            
            //int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea quitar al participante de la audiencia?","Confirmacion de operacion",JOptionPane.OK_CANCEL_OPTION);
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Realmente desea quitar al participante de la audiencia?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                 modeloIntermedia.eliminarParticipanteDeAudiencia(intermedia);
                 DefaultTableModel modelo = (DefaultTableModel)vista.tablaParticipantes.getModel();
                 modelo.setRowCount(0);
                 participantesAgregadosSolicitud(vista.tablaParticipantes);
                 vista.txtNombreS.setText("");
                 vista.txtTipoS.setText("");
                 vista.txtCopias.setText("");
            }
            else {
                JOptionPane.showMessageDialog(null, "Se ha cancelado la operación");
            }*/
           
        }
        //boton que abre la vista de consolidacion de discos
        else if (e.getSource() == vista.btnConsolidar){
            consolidar.setVisible(true);
            consolidar.setLocationRelativeTo(null);
            String disco_temporal1 = modeloDisco.getTemporalDisco().get(0).getNumero();
            System.out.println("1: "+disco_temporal1);
            
            String disco_temporal2 = modeloDisco.getTemporalDisco().get(1).getNumero();
            System.out.println("2: "+disco_temporal2);
            String disco1 = modeloDisco.getDisco().get(0).getNumero();
            String disco2 = modeloDisco.getDisco().get(1).getNumero();

            System.out.println("1: "+disco1);
            System.out.println("2: "+disco2);
            if (disco_temporal1 == disco_temporal2) {
                JOptionPane.showMessageDialog(null, "LOS NUMEROS DE DISCO SE REPITEN VERIFIQUE DATOS ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                if (disco1 == disco2)  {
                    JOptionPane.showMessageDialog(null, "LOS NUMEROS DE DISCO SE REPITEN VERIFIQUE DATOS ","Registro repetido",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        //busca todas las audiencias no celebradas
        else if (e.getSource() == consolidar.btnBuscar){
            /*DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            int tamanio = consolidar.Taudiencias.getRowCount();
            for(int i =0; i<tamanio; i++){
                consolidar.Taudiencias.setValueAt(false, i, 6);
            } */ 
            
        }
        //se asigna un numero de disco a cada uudiencia que existe en la tabbla y actualiza el estado a "CELEBRADA"
        else if (e.getSource() == consolidar.btnAsignar){
            
            int idAudiencia = 0;
            int bandera = 0;
            int numRegistros = consolidar.Taudiencias.getRowCount();
            int cont=0;
            //JOptionPane.showMessageDialog(null, numRegistros);
            
            for(int i = 0; i<numRegistros; i++){
                //JOptionPane.showMessageDialog(null, "fila "+i);
                Object fecha  = consolidar.Taudiencias.getValueAt(i, 0);
                Object hora  = consolidar.Taudiencias.getValueAt(i, 1); 
                Object sala  = consolidar.Taudiencias.getValueAt(i, 2);
                //JOptionPane.showMessageDialog(null, fecha +" "+hora+" "+sala);
                idAudiencia = modeloAudiencia.extraerId(String.valueOf(fecha), String.valueOf(hora), String.valueOf(sala));
                 bandera=modeloAudiencia.ValidarConsolidacion(idAudiencia);
                 System.out.println("Bandera : "+bandera);
                //JOptionPane.showMessageDialog(null,"id audiencia "+idAudiencia);
                if(bandera == 1)
                {
                    cont++;
                }
              //  String resp = (String)modeloDisco.insertarTemporalDisco(2017, idAudiencia);
                else{
                Calendar cal= Calendar.getInstance(); 
                int yearDisco= cal.get(Calendar.YEAR); 
                String resp = (String)modeloDisco.insertarTemporalDisco(yearDisco, idAudiencia);
                modeloDisco.copiarDatos(idAudiencia);
                modeloAudiencia.actualizarEstado(idAudiencia);
                }
              
            }
            System.out.println("contador : "+cont);
            if(cont==numRegistros)
            {
                JOptionPane.showMessageDialog(null,"Las Audiencias ya han sido consolidadas por alguien mas");
                 DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText("");
            }
            else if(cont==0) {
            DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText("");
            }
            else{
            JOptionPane.showMessageDialog(null," "+cont+" Audiencia ya han sido consolidadas por alguien mas");
                           DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText(""); 
            }
             
        }
        //Boton que consolida las audiencias que esten marcadas con el checkbox
        else if (e.getSource() == consolidar.btnConsolidarIndividual){
            int tamanio = consolidar.Taudiencias.getRowCount();
            int contador = 0;
            int cont=0;
            int contador_verdaderos=0;
            for(int y=0; y<tamanio;y++){
                Boolean estadoSeleccion = Boolean.valueOf(consolidar.Taudiencias.getValueAt(y, 6).toString());
                if(estadoSeleccion.equals(false)){
                    contador++;
                }
            }
            System.out.print(contador);
            if(tamanio==contador){
                JOptionPane.showMessageDialog(null, "Seleccione las audiencias a consolidar","Error al consolidar",JOptionPane.ERROR_MESSAGE);
            } else {
                for(int i = 0; i<tamanio; i++){
                Boolean verdadero = new Boolean(true);
                Boolean estadoColumna = Boolean.valueOf(consolidar.Taudiencias.getValueAt(i, 6).toString());
                int idAudiencia = 0;
                int bandera=0;
                if(estadoColumna.equals(verdadero)){
                    contador_verdaderos++;
                    Object fecha  = consolidar.Taudiencias.getValueAt(i, 0);
                    Object hora  = consolidar.Taudiencias.getValueAt(i, 1); 
                    Object sala  = consolidar.Taudiencias.getValueAt(i, 2);
                   //    JOptionPane.showMessageDialog(null, "Fecha "+ fecha +" Hora "+hora+" Sala "+sala,"Audiencia consolidada",JOptionPane.INFORMATION_MESSAGE);
                    idAudiencia = modeloAudiencia.extraerId(String.valueOf(fecha), String.valueOf(hora), String.valueOf(sala));
                    //JOptionPane.showMessageDialog(null,"id audiencia "+idAudiencia);
                   bandera=modeloAudiencia.ValidarConsolidacion(idAudiencia);
                                   if(bandera == 1)
                {
                    cont++;
                }
                                   else{
                    String resp = (String)modeloDisco.insertarTemporalDisco(anio, idAudiencia);
                    modeloDisco.copiarDatos(idAudiencia);
                    modeloAudiencia.actualizarEstado(idAudiencia);
                                   }
                }
            }
                //DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
                //modelo.setRowCount(0);
                //consolidar.lblTotal.setText("");
                //audienciasConsolidacion(consolidar.Taudiencias);
                            if(cont==contador_verdaderos)
            {
                JOptionPane.showMessageDialog(null,"Las Audiencias seleccionadas ya han sido consolidadas por alguien mas");
                 DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText("");
            }
            else if(cont==0) {
            DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText("");
            }
            else{
            JOptionPane.showMessageDialog(null," "+cont+" Audiencia ya han sido consolidadas por alguien mas");
                           DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            audienciasConsolidacion(consolidar.Taudiencias);
            consolidar.lblTotal.setText(""); 
            }
            }
            
            
            
        }
        //Boton para cancelar una audiencia
        else if(e.getSource() ==  consolidar.btnCancelar){
            String id = consolidar.lblAudiencia.getText();
            System.out.print("el id es "+ id);
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null, "Selecione una audiencia","No ha seleccionado una audiencia", JOptionPane.ERROR_MESSAGE);
            } else {
                modeloAudiencia.cancelarAudiencia(Integer.parseInt(id));
                audienciasConsolidacion(consolidar.Taudiencias);
                JOptionPane.showMessageDialog(null, "Audiencia cancelada");
            }
            DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            System.out.println(consolidar.Taudiencias);
            audienciasConsolidacion(consolidar.Taudiencias);
        }
        //boton para actualizar el catalogo de audiencias
        else if (e.getSource() == formParticipante.btnActualizarCatalogo){
            DefaultTableModel modeloCat = (DefaultTableModel)formParticipante.tablaCatalogo.getModel();
            modeloCat.setRowCount(0);
            String tipo = formParticipante.txtTaudiencia.getText();
            String id = formParticipante.lblIdCatalogo.getText();
            modeloCatalogo.actualizar(tipo.toUpperCase(),Integer.parseInt(id));
            tablaCatalogo(formParticipante.tablaCatalogo);
            formParticipante.txtTaudiencia.setText("");
        }
        //Busca todas las audiencias co su respectivo disco
        else if (e.getSource() == vista.btngeneralBuscar){
            /*DefaultTableModel modelo = (DefaultTableModel)vista.tablaAudienciasGeneral.getModel();
            modelo.setRowCount(0);
            String Finicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
            String Ffinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
            vista.lblTotalAu.setText("");
            if (Finicio.isEmpty() || Ffinal.isEmpty()){
                JOptionPane.showMessageDialog(null, "Elija la fecha de inicio y fecha final","Fechas vacias",JOptionPane.ERROR_MESSAGE);
                
            } else{
                historial(vista.tablaAudienciasGeneral,Finicio, Ffinal);
                int total = vista.tablaAudienciasGeneral.getRowCount();
                if(total !=0){
                    vista.lblTotalAu.setText(String.valueOf(total));
                } else {
                    JOptionPane.showMessageDialog(null, "No existen audiencias del rango seleccionado");
                }
                
            }*/
            
            
        }
        //Mnada a imprimir un reporte general de lso discos 
        else if (e.getSource() ==  vista.btnReporteGeneral){
             String fInicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
             String fFinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
             String total = vista.lblTotalAu.getText();
            try {
                if (fInicio.isEmpty() || fFinal.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Elija la fecha inicial y la fecha final","Error al imprimir reporte",JOptionPane.ERROR_MESSAGE);
                }else{
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha1 = formatter.parse(fInicio);
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha2 = formatter.parse(fFinal);
                    modeloAudiencia.reporteGeneral(fecha1,fecha2,total);
                }
                
            } catch (Exception ex) {
                System.out.print(ex);
            }
            
        }
        //Generea un reporte para las entergas de discos externas (amparo y acuerdo)
        else if (e.getSource() == vista.btnImpEntregasExt){
            modeloSolicitudE.generarEntregas();
        }
        //BOTON QUE TE AGREGA A LA BASE DE DATIOS A PARTICIPANTES
               else if (e.getSource() == formAgregar.btnAgregarN){
            String nombre = formAgregar.txtnombreparti.getText();
            //String tipo = formAgregar.CBpartes.getSelectedItem().toString();
            String tipo = formAgregar.tipocombo.getSelectedItem().toString();
            String verificarNombre = modeloParticipante.verificarExistente(nombre, tipo);
            System.out.print(verificarNombre);
            if (nombre.isEmpty() || tipo.isEmpty() || formAgregar.tipocombo.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos","Campos vacios",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(verificarNombre != null){
                    JOptionPane.showMessageDialog(null, "Ya existe un participante con el mismo nombre ","Error al gregar a participante",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    modeloParticipante.insertarParticipante(nombre,tipo);
                    JOptionPane.showMessageDialog(null, "Participante agregado con éxito");
                    formAgregar.txtnombreparti.setText("");
                    formAgregar.tipocombo.setSelectedIndex(-1);
                   //mostrarParticipantes2(formAgregar.tablaPart, tipo);
                    //formAgregar.txtTipo.setText("");
                }
                
                
            }
            
        }
            
        
        else if (e.getSource() == vista.btnIrRotular){
            vistaRotular.setVisible(true);
            vistaRotular.setLocationRelativeTo(null);
            vistaRotular.setTitle("CENTRO DE JUSTICIA PENAL DE PUEBLA");
            vistaRotular.usuarioscombo.removeAllItems();
            llenarcombousuariosRotular();
            vistaRotular.usuarioscombo.setSelectedIndex(-1);
        }
        //Busca audiencia dependiendo delrango prooprciondo
        else if (e.getSource() == vistaRotular.btnBuscar){
            
            /* String inicial =  ((JTextField)vistaRotular.JDdesde.getDateEditor().getUiComponent()).getText();
             String fechaFinal =  ((JTextField)vistaRotular.JDhasta.getDateEditor().getUiComponent()).getText();
             int numAudiencias = modeloAudiencia.listAudienciasRotular(inicial, fechaFinal).size();
             if (inicial.isEmpty() || fechaFinal.isEmpty()){
                 JOptionPane.showMessageDialog(null, "Indique la fecha de inicio y fecha final","Fecha vacia", JOptionPane.ERROR_MESSAGE);
             } else {
                 if (numAudiencias > 0){
                     DefaultTableModel modelo = (DefaultTableModel)vistaRotular.tablaAudiencias.getModel();
                     modelo.setRowCount(0);
                    llenarTablaAudienciasRotular(vistaRotular.tablaAudiencias, inicial,fechaFinal);
                 } else {
                     JOptionPane.showMessageDialog(null, "No existen audiencias consolidadas","No existen registros", JOptionPane.ERROR_MESSAGE);
                 }
             }*/
             
             
        }
    else if (e.getSource() == vistaRotular.btnRotular){
            
          /* try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = vistaRotular.lblFecha.getText();
                Date fecha1 = formatter.parse(fecha);
                String sala = vistaRotular.lblSala.getText();
                String hora = vistaRotular.lblHora.getText();
                String causa = vistaRotular.lblCausa.getText();
                String disco = vistaRotular.lblDisco.getText();
                String imputado = vistaRotular.lblImputado.getText();
                String defensa = vistaRotular.lblDefensa.getText();
                String fiscal = vistaRotular.lblFiscal.getText();
                int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                //String juez = modeloAudiencia.extraerJuez(idAudiencia);
                modeloAudiencia.actualizarEstadoRotulacion(idAudiencia);
                modeloDisco.generarRotulo(fecha1, hora, causa, disco, imputado, defensa, fiscal);
                
                String inicio = ((JTextField)vistaRotular.JDdesde.getDateEditor().getUiComponent()).getText();
                String fFinal = ((JTextField)vistaRotular.JDhasta.getDateEditor().getUiComponent()).getText();
                DefaultTableModel modelo = (DefaultTableModel)vistaRotular.tablaAudiencias.getModel();
                modelo.setRowCount(0);
                llenarTablaAudienciasRotular(vistaRotular.tablaAudiencias,inicio , fFinal);
                //vistaRotular.lblDisco.setText("");
            } catch (Exception ex) {
                System.out.print(ex);
                
            }*/
        }
        // Agrega un comentario a la tabla intermedia para poder meteer a personas autorizadas paera recoger discos
        else if (e.getSource() == vista.btnPedirComentario){
            String id = vista.lblIdIntermedia.getText();
            if (vista.txtNombreS.getText().isEmpty() || vista.txtTipoS.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un participante","Campos vacios",JOptionPane.ERROR_MESSAGE);
            } else {
                
                String autorizados;
                autorizados = JOptionPane.showInputDialog(null,"Introduzca las personas autorizadas","Personas autorizadas",JOptionPane.INFORMATION_MESSAGE);
                modeloIntermedia.actualizarPersonaAutorizada(Integer.parseInt(id), autorizados);
                
            }
           
        }
        else if (e.getSource() == vista.btnConsultaAutorizados){
            String id = vista.lblIdIntermedia.getText();
            if (vista.txtNombreS.getText().isEmpty() || vista.txtTipoS.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un participante","Campos vacios",JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = modeloIntermedia.extraerAtorizados(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, nombre);
                
            }
            
        }
        else if (e.getSource() == vista.btnLimpiarGeneral){
            ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).setText("");
            ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).setText("");
            vista.lblTotalAu.setText("");
            DefaultTableModel modelo = (DefaultTableModel)vista.tablaAudienciasGeneral.getModel();
            modelo.setRowCount(0);
            DefaultTableModel modelo2 = (DefaultTableModel)vista.tablapartGeneral.getModel();
            modelo2.setRowCount(0);
            
        }
        //Limpia la pantalla de solicitudes
        else if (e.getSource() == vista.btnLimpiarS){
            ((JTextField)vista.JDfechaBuscar.getDateEditor().getUiComponent()).setText("");
            ((JTextField)vista.JDfechaBuscarFinal.getDateEditor().getUiComponent()).setText("");
            DefaultTableModel modelo = (DefaultTableModel)vista.tablaEntrega.getModel();
            modelo.setRowCount(0);
            vista.txtEhoraS.setText("");
            vista.txtTipoAudiencia.setText("");
            vista.txtEfechaSoli.setText("");
            vista.txtEcausaS.setText("");
            vista.txtFechaS.setText("");
            vista.txtHoraS.setText("");
            vista.txtCausa.setText("");
            vista.txtDisco.setText("");
            vista.txtEjuezS.setText("");
            vista.txtEfechaS.setText("");
            vista.txtEnombreS.setText("");
            vista.txtEcopiasS.setText("");
            vista.txtEtipoS.setText("");
            vista.lblIdSoli.setText("");
            vista.lblImputadoS.setText("");
            vista.lblDefensaS.setText("");
            vista.lblFiscal.setText("");
        }
        //Limpia la pantalla de solicitudes externas
        else if (e.getSource() == vista.btnLimpiarSE){
            ((JTextField)vista.JDbuscarSolicitudE.getDateEditor().getUiComponent()).setText("");
            ((JTextField)vista.JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).setText("");
            vista.lblFechas.setText("");
            vista.lblHoras.setText("");
            vista.lblCausa.setText("");
            vista.lblDiscoEx.setText("");
            vista.lblIdSolicitudEx.setText("");
            vista.lblSolicitante.setText("");
            vista.lblCopiasEx.setText("");
            vista.lblAcuerdo.setText("");
            vista.lblAmparo.setText("");
            vista.lblJueces.setText("");
            DefaultTableModel modelo =(DefaultTableModel)vista.tablaSolicitudExterna.getModel();
            modelo.setRowCount(0);
            
        }
        //limpia la pantalla de rotular discos originales
        else if (e.getSource()==vistaRotular.btnLimpiar){
            ((JTextField)vistaRotular.JDdesde.getDateEditor().getUiComponent()).setText("");
            ((JTextField)vistaRotular.JDhasta.getDateEditor().getUiComponent()).setText("");
            vistaRotular.lblDisco.setText("");
            vistaRotular.lblCausa.setText("");
            vistaRotular.lblDefensa.setText("");
            vistaRotular.lblFecha.setText("");
            vistaRotular.lblFiscal.setText("");
            vistaRotular.lblHora.setText("");
            vistaRotular.lblImputado.setText("");
            vistaRotular.lblSala.setText("");
            DefaultTableModel modelo =(DefaultTableModel) vistaRotular.tablaAudiencias.getModel();
                modelo.setRowCount(0);
            
        }
        //limpia la pantalla de rotular discos externos
        else if (e.getSource()==rotuloExternas.btnLimpiar){
        /*    rotuloExternas.txtCausa.setText("");
            rotuloExternas.txtDisco.setText("");
            rotuloExternas.txtFecha.setText("");
            rotuloExternas.txtFolio.setText("");
            rotuloExternas.txtHora.setText("");
            rotuloExternas.txtSala.setText("");
            rotuloExternas.lblDefensa.setText("");
            rotuloExternas.lblFiscal.setText("");
            rotuloExternas.lblImputado.setText("");
            DefaultTableModel modelo = (DefaultTableModel)rotuloExternas.Taudiencias.getModel();
            modelo.setRowCount(0);*/
        }
        //Limpia la ventana de consolidar discos
        else if (e.getSource()==consolidar.btnLimpiar){
            consolidar.lblAudiencia.setText("");
            consolidar.lblTotal.setText("");
            DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
            modelo.setRowCount(0);
            
        }
        else if (e.getSource() == vista.btnGuardar){
            vistaExportar.setVisible(true);
            vistaExportar.setLocationRelativeTo(null);
        }
        else if (e.getSource() == vistaExportar.btnBuscar){
            JFileChooser ch = new JFileChooser();
            ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int se = ch.showSaveDialog(null);
            if (se == JFileChooser.APPROVE_OPTION){
                String suta = ch.getSelectedFile().getPath();
                vistaExportar.txtRuta.setText(suta);
            }
            
        }
        //crear un respaldo de la BD
        else if (e.getSource() == vistaExportar.btnExportar){
            java.util.Date date = new java.util.Date();
            SimpleDateFormat fechaHora = new SimpleDateFormat("dd-MM-YYYY");
            String fechaCompleta = fechaHora.format(date);
            String ruta = vistaExportar.txtRuta.getText();
            String nombre = "\\respaldo.sql";
            String comando = "";
            if (ruta.trim().length() !=0){
                try {
                    comando = "mysqldump.exe --opt -h 10.27.46.249 -u Administrator -pNetworksupport -B controlinterno -r "+ruta+nombre;
                    JOptionPane.showMessageDialog(null, "Respaldo creado correctamente");
                    Runtime rt = Runtime.getRuntime();
                    rt.exec(comando);
                    vistaExportar.txtRuta.setText("");
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        }
        else if (e.getSource() == vista.btnExcel){
            String fechaInicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
            String fechaFinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
            if(fechaInicio.isEmpty() || fechaFinal.isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione la fecha de inicio y la fecha final");
            } else {
                String fileName = System.getProperty("user.dir")+"\\datosSimple.xls";
                File file = new File(fileName);
                String fInicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
                String fFinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
                modeloDisco.escribirExcel(file,fInicio,fFinal);
            }
            
        }
        else if (e.getSource()==vista.btnExcelSimple){
            String fechaInicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
            String fechaFinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
            if(fechaInicio.isEmpty() || fechaFinal.isEmpty()){
                 JOptionPane.showMessageDialog(null, "Seleccione la fecha de inicio y la fecha final");
            } else {
                String fileName = System.getProperty("user.dir")+"\\datos.xls";
                File file = new File(fileName);
                String fInicio = ((JTextField)vista.JDinicial.getDateEditor().getUiComponent()).getText();
                String fFinal = ((JTextField)vista.JDfinal.getDateEditor().getUiComponent()).getText();
                modeloDisco.escribirExcelSimple(file,fInicio,fFinal);
            }
          
            
        }
        
        
        
    }
   

    public void limpiarCampos(){
        vista.txtCausa.setText("");
        ((JTextField) vista.JDC.getDateEditor().getUiComponent()).setText("");
    }
    /*public void tablaListener(){
        vista.comboTipoP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = vista.comboTipoP.getSelectedItem().toString();
                if (tipo.equals("JUEZ")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("FISCAL")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("ASESOR JURIDICO")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("RLEGAL")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("VICTIMA")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("DEFENSA")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("IMPUTADO")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("TRADUCTOR")){
                    mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                else if (tipo.equals("ESPECIALISTA")){
                  mostrarParticipantesListener(vista.tablaListener, tipo);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }*/
    
   /* public void tablaListenerParticipantes(){
        formAgregar.CBpartes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = formAgregar.CBpartes.getSelectedItem().toString();
                if (tipo.equals("JUEZ")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("FISCAL")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("ASESOR JURIDICO")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("RLEGAL")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("VICTIMA")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("DEFENSA")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("IMPUTADO")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("TRADUCTOR")){
                    mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                else if (tipo.equals("ESPECIALISTA")){
                   mostrarParticipantes2(formAgregar.tablaPart, tipo);
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }*/
    
        public void llenarcombousuariosRotular()
    {
                ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarComboUsuario();
        for(int i = 0; i< lista.size(); i++){
            vistaRotular.usuarioscombo.addItem(lista.get(i));
        }
        vistaRotular.usuarioscombo.addItem("");
    }    
    
        public void llenarcombousuarios()
    {
                ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarComboUsuario();
        for(int i = 0; i< lista.size(); i++){
            vista.usuariocombo.addItem(lista.get(i));
        }
        vista.usuariocombo.addItem("");
    }
    //llena el combobox de los tipos de audiencia
    public void llenarCombo (){
        
        ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarCombo();
        for(int i = 0; i< lista.size(); i++){
            vista.comboAudiencia.addItem(lista.get(i));
        }
    }
        public void llenarCombo2 (){
        
        ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarCombo();
        for(int i = 0; i< lista.size(); i++){
            vista.comboAudiencia2.addItem(lista.get(i));
        }
    }
    //llena el combo de la vista de actuallizar
    public void llenarComboActualizar (){
        
        ArrayList <String> lista = new ArrayList<>();
        lista = modeloCatalogo.llenarCombo();
        for(int i = 0; i< lista.size(); i++){
            formParticipante.CBtipos.addItem(lista.get(i));
        }
    }
  
    
    public void participantesAgregados(JTable tabla){
        String causa = vista.jLabel13.getText();
        DefaultTableModel modelo = (DefaultTableModel) vista.Tparticipantes.getModel();
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
    
    public void participantesAgregadosSolicitud(JTable tabla){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Copias solicitadas");
        tabla.setModel(modelo);*/
        DefaultTableModel modelo =  (DefaultTableModel)tabla.getModel();
        Object [] columna = new Object[3];
      
        String fecha = vista.txtFechaS.getText();
        String hora = vista.txtHoraS.getText();
        String sala = vista.txtSalaS.getText();
        int audiencia= modeloAudiencia.extraerId(fecha, hora, sala);
        //System.out.println(audiencia);
        
        
        
        int numRegistros = modeloIntermedia.listConsulta2(audiencia).size();
        if(numRegistros > 0){
            for (int i=0; i<numRegistros; i++){
            
            columna[0] = modeloIntermedia.listConsulta2(audiencia).get(i).getNombre();
            columna[1] = modeloIntermedia.listConsulta2(audiencia).get(i).getTipo();
            columna[2] = modeloIntermedia.listConsulta2(audiencia).get(i).getCopias();
            modelo.addRow(columna);
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String fecha = vista.txtFechaS.getText();
                    String hora = vista.txtHoraS.getText();
                    String sala = vista.txtSalaS.getText();
                    int audiencia= modeloAudiencia.extraerId(fecha, hora, sala);
                    //System.out.println("id audiencia "+audiencia);
                    vista.txtNombreS.setText(tabla.getValueAt(fila, 0).toString());
                    vista.txtTipoS.setText(tabla.getValueAt(fila, 1).toString());
                    vista.txtCopias.setText(tabla.getValueAt(fila, 2).toString());
                    String nombre =vista.txtNombreS.getText();
                    String tipo = (tabla.getValueAt(fila,1).toString());
                    int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                    int idIntermedia = modeloIntermedia.extraerId(audiencia, idParticipante);
                    //System.out.println("participante " + idParticipante);
                    //System.out.println("intermedia"+ idIntermedia);
                    vista.lblIdIntermedia.setText(String.valueOf(idIntermedia));
                }
               //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            modelo.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if(e.getType() ==  TableModelEvent.UPDATE){
                        String fecha = vista.txtFechaS.getText();
                        String hora = vista.txtHoraS.getText();
                        String sala = vista.txtSalaS.getText();
                        int columna = e.getColumn();
                        int fila = e.getFirstRow();
                         String nombre =vista.txtNombreS.getText();
                        if(columna == 2){
                            String tipo = (tabla.getValueAt(fila,1).toString());
                            int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                            //System.out.println("ID_AUDIENCIA: "+idAudiencia);
                            int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                            //System.out.println("ID_PARTICIPANTE: "+idParticipante);
                            int idIntermedia = modeloIntermedia.extraerId(idAudiencia, idParticipante);
                            //System.out.println("ID_INTERMEDIA: "+idIntermedia);
                           
                            //System.out.println("TIPO: "+tipo);
                            //JOptionPane.showMessageDialog(null,"audiencia "+ idAudiencia + "participante "+idParticipante);
                            int copias = Integer.parseInt(tabla.getValueAt(fila,columna).toString());
                            modeloIntermedia.actualizarTabla(idIntermedia, copias);
                           
                        }
                    }
               //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        
        else {
            JOptionPane.showMessageDialog(null, "No existen participantes agregados a esta causa");
        }
        
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
    
   
    public void llenarTabla(JTable tablaD){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Sala");
        modelo.addColumn("Nombre Audiencia");
        tablaD.setModel(modelo);*/
         DefaultTableModel modelo = (DefaultTableModel)tablaD.getModel(); 
         try{
            String sql="SELECT a.FECHA, a.HORA, a.SALA, c.NOMBRE, a.COMENTARIO, u.Nombre,a.tipo FROM audiencia as a INNER JOIN catalogo_audiencia as c ON a.NOMBREA = c.ID LEFT OUTER JOIN usuarios as u ON a.usuario_id=u.id_usuario WHERE a.CAUSA ='"+vista.txtBuscar.getText()+"' AND a.ESTADO != 'CANCELADA' ORDER BY a.FECHA,a.HORA, a.SALA";
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
                    //rs.close();
        }catch (SQLException ex){
                    System.err.println(ex.toString());
                }
         int numRegistros=modelo.getRowCount();
        if(numRegistros > 0){
JOptionPane.showMessageDialog(null, "Busqueda Finalizada");

            modelo.addTableModelListener(new TableModelListener() {
                 @Override

                 public void tableChanged(TableModelEvent e) {

                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     if (e.getType() == TableModelEvent.UPDATE){
                         int columna = e.getColumn();
                         int fila = e.getFirstRow();
                         if(columna == 4){
                             String fecha =vista.txtFechaS.getText();
                             String hora = vista.txtHoraS.getText();
                             String sala = vista.txtSalaS.getText();
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
    
    public void llenarTablaSolicitud(JTable tabla){
        String fecha = ((JTextField)vista.JDfechaBuscar.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)vista.JDfechaBuscarFinal.getDateEditor().getUiComponent()).getText();
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
        DefaultTableModel modelo = (DefaultTableModel)vista.tablaEntrega.getModel();
        
        Object [] columnas = new Object[12];
        
        int numRegistros = modeloSolicitud.lsSolicitud(fecha,fechaFinal).size();
        for(int i = 0; i<numRegistros; i++) {
            columnas[0] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getId();
            columnas[1] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getFecha();
            columnas[2] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getHora();
            columnas[3] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getCopias();
            columnas[4] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getEstado();
            columnas[5] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getFecha_constancia();
            columnas[6] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getHora_constancia();
            columnas[7] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getFecha_entrega();
            columnas[8] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getHora_entrega();
            columnas[9] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getParticipante();
            columnas[10] = modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).getTipoP();
            columnas[11] =modeloSolicitud.lsSolicitud(fecha,fechaFinal).get(i).gettipoA();
            modelo.addRow(columnas);
        }
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
        //System.out.println(fecha);
        //System.out.println(fechaFinal);
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    vista.txtEnombreS.setText(tabla.getValueAt(fila, 9).toString());
                    vista.txtEfechaSoli.setText(tabla.getValueAt(fila, 1).toString());
                    vista.txtTipoAudiencia.setText(tabla.getValueAt(fila,11).toString());
                    int solicitud =Integer.parseInt( tabla.getValueAt(fila, 0).toString());
                    vista.txtEcopiasS.setText(tabla.getValueAt(fila, 3).toString());
                    vista.txtEtipoS.setText(tabla.getValueAt(fila, 10).toString());
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
                        vista.lblDefensaS.setText(defensa +" "+"Y OTROS");
                    } else{
                        vista.lblDefensaS.setText(defensa);
                    }
                    if (numFiscales > 1){
                        vista.lblFiscal.setText(fiscal+" "+"Y OTROS");
                    } else {
                        vista.lblFiscal.setText(fiscal);
                    }
                    if (numImputados > 1){
                        vista.lblImputadoS.setText(imputado+" "+"Y OTROS");
                    } else {
                        vista.lblImputadoS.setText(imputado);
                    }
                   
                    String juez  = modeloAudiencia.extraerJuez(audiencia);
                    /*String imputado = modeloAudiencia.extraerImputado(audiencia);
                    String defensa = modeloAudiencia.extraerDefensa(audiencia);
                    String fiscal = modeloAudiencia.extraerFiscal(audiencia);*/
                    //JOptionPane.showMessageDialog(null, solicitud);
                    vista.txtEfechaS.setText(fecha);
                    vista.txtEhoraS.setText(hora);
                    vista.txtEcausaS.setText(causa);
                    vista.txtEjuezS.setText(juez);
                    vista.lblIdSoli.setText(tabla.getValueAt(fila, 0).toString());
                    int idDisco = modeloSolicitud.extraerNumdisco(Integer.parseInt(vista.lblIdSoli.getText()));
                    int numero =  modeloDisco.numeroDisco(idDisco);
                    int anio = modeloDisco.anioDisco(idDisco);
                    String completo = numero+"-"+ anio;
                    vista.txtDisco.setText(String.valueOf(completo));
                    
                    
    
                }
                
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    
    
   /* public void llenarTablaSolicitudExterna(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
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
        modelo.addColumn("MOTIVO");
        modelo.addColumn("FECHA ACUERDO");
        tabla.setModel(modelo);
        
        Object [] columnas = new Object[13];
        
        int numRegistros = modeloSolicitudE.lsSolicitudE().size();
        
        for(int i = 0; i<numRegistros; i++){
            columnas[0] = modeloSolicitudE.lsSolicitudE().get(i).getId();
            columnas[1] = modeloSolicitudE.lsSolicitudE().get(i).getFecha();
            columnas[2] = modeloSolicitudE.lsSolicitudE().get(i).getHora();
            columnas[3] = modeloSolicitudE.lsSolicitudE().get(i).getCopias();
            columnas[4] = modeloSolicitudE.lsSolicitudE().get(i).getEstado();
            columnas[5] = modeloSolicitudE.lsSolicitudE().get(i).getFecha_constancia();
            columnas[6] = modeloSolicitudE.lsSolicitudE().get(i).getFecha_entrega();
            columnas[7] = modeloSolicitudE.lsSolicitudE().get(i).getTipo();
            columnas[8] = modeloSolicitudE.lsSolicitudE().get(i).getSolicitante();
            columnas[9] = modeloSolicitudE.lsSolicitudE().get(i).getAmparo();
            columnas[10] = modeloSolicitudE.lsSolicitudE().get(i).getDependencia();
            columnas[11] = modeloSolicitudE.lsSolicitudE().get(i).getMotivo();
            columnas[12] = modeloSolicitudE.lsSolicitudE().get(i).getAcuerdo();
            modelo.addRow(columnas);
        }  
        
    }*/
    
      public void llenarTablaSolicitudExternaAmparo(JTable tabla){
          try {
             
             String fecha = ((JTextField)vista.JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
             String fechaFinal = ((JTextField)vista.JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
            DefaultTableModel modelo = new DefaultTableModel();
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
            tabla.setModel(modelo);

            Object [] columnas = new Object[11];

            int numRegistros = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).size();
            vista.lblAcuerdo.setVisible(false);
            vista.jLabel53.setVisible(false);
            vista.lblAmparo.setVisible(true);
            vista.jLabel52.setVisible(true);

            for(int i = 0; i<=numRegistros; i++){
                columnas[0] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getId();
                columnas[1] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getFecha();
                columnas[2] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getHora();
                columnas[3] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getCopias();
                columnas[4] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getEstado();
                columnas[5] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getFecha_constancia();
                columnas[6] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getFecha_entrega();
                columnas[7] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getTipo();
                columnas[8] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getSolicitante();
                columnas[9] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getAmparo();
                columnas[10] = modeloSolicitudE.lsSolicitudEAmparo(fecha,fechaFinal).get(i).getDependencia();
                modelo.addRow(columnas);
            }
            JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
            tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    if(tabla.getSelectedRow() != -1){

                        int fila = tabla.getSelectedRow();
                        vista.lblIdSolicitudEx.setText(tabla.getValueAt(fila, 0).toString());
                        vista.lblCopiasEx.setText(tabla.getValueAt(fila, 3).toString());
                       vista.lblAmparo.setText(tabla.getValueAt(fila, 9).toString()); //modifcar el 9 en 10
                        vista.lblSolicitante.setText(tabla.getValueAt(fila, 8).toString());
                        String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                        vista.lblCausa.setText(causa);
                        String juez = modeloSolicitudE.extJuez(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                        vista.lblJueces.setText(juez);

                        int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).size();
                        //se obtienen las fechas de las audiencias solicitadas
                        Object  [] fechas = new Object[numRegistros];
                        for(int i = 0; i< numRegistros; i++)
                        {   
                            fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getFecha();

                        }
                        // se muestran las fechas de audiencias en los labels
                        vista.lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+","); 

                        //se obtienen las horas de las audiencias solicitadas
                        int numRegistrosHora = modeloSolicitudE.lsHoras(Integer.parseInt(vista.lblIdSolicitudEx.getText())).size();
                        Object  [] horas = new Object[numRegistrosHora];
                        for(int i = 0; i< numRegistrosHora; i++)
                        {
                            horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getHora();

                        }
                        // se muestran las fechas de audiencias en los labels
                        vista.lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+","); 

                        // se obtienen los numeros de discos (numero y año)

                        int numRegistrosDisco = modeloSolicitudE.lsDisco(Integer.parseInt(vista.lblIdSolicitudEx.getText())).size();
                        String [] numero = new String[numRegistrosDisco];
                        String [] anio = new String [numRegistrosDisco];
                        Object [] completoA =  new Object[numRegistrosDisco];
                        String v_numero;
                        String v_anio;
                        String completo="";
                        System.out.println("TAMAÑO " + numRegistrosDisco);
                        for(int i=0; i<numRegistrosDisco; i++){
                            numero [i] = modeloSolicitudE.lsDisco(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getNumero();
                            anio [i] = modeloSolicitudE.lsDisco(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getAnio();
                            v_numero = numero[i];
                            v_anio =  anio[i];
                            completo = v_numero +"-"+ v_anio;
                            completoA[i] = completo;
                            System.out.println("Array completo " + completoA[i]);

                        }
                       vista.lblDiscoEx.setText(Arrays.toString(completoA).replace("[","").replace("]", "")+","); 

                    }

                }
            });
          } catch (Exception e) {
          }
        
        
        
        
    }
      
      
     // public void llenarTablaSolicitudExternaAcuerdo(JTable tabla){
       
  /*        
    vista.btnSolicitudE.addActionListener(new ActionListener() 
{
    
@Override
public void actionPerformed(java.awt.event.ActionEvent arg0) 
	{
            
               Runnable miRunnable = new Runnable()
		{
                    
			public void run()
		        {
 String fecha = ((JTextField)vista.JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)vista.JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
        DefaultTableModel modelo = new DefaultTableModel();
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
        
                                                // JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
                  vista.Barra.setStringPainted(true);
	          vista.Barra.setVisible(true);
                          tabla.setModel(modelo);     
                                                 
        Object [] columnas = new Object[11];
        
        int numRegistros = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).size();
        vista.Barra.setMaximum(numRegistros);
          vista.lblAmparo.setVisible(false);
          vista.jLabel52.setVisible(false);
          vista.lblAcuerdo.setVisible(true);
          vista.jLabel53.setVisible(true); 
                     tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               if(tabla.getSelectedRow() != -1){
                  
                    int fila = tabla.getSelectedRow();
                    vista.lblIdSolicitudEx.setText(tabla.getValueAt(fila, 0).toString());
                    vista.lblCopiasEx.setText(tabla.getValueAt(fila, 3).toString());
                    vista.lblAcuerdo.setText(tabla.getValueAt(fila, 10).toString());
                    vista.lblSolicitante.setText(tabla.getValueAt(fila, 8).toString());
                    String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                    vista.lblCausa.setText(causa);
                    String juez = modeloSolicitudE.extJuez(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                    vista.lblJueces.setText(juez);
                    
                    
                    int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).size();
                    //se obtienen las fechas de las audiencias solicitadas
                    Object  [] fechas = new Object[numRegistros];
                    for(int i = 0; i< numRegistros; i++)
                    {
                        fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getFecha();

                    }
                    // se muestran las fechas de audiencias en los labels
                    vista.lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+","); 
                   
                    //se obtienen las fechas de las audiencias solicitadas
                    Object  [] horas = new Object[numRegistros];
                    for(int i = 0; i< numRegistros; i++)
                    {
                        horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getFecha();

                    }
                    // se muestran las fechas de audiencias en los labels
                    vista.lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                    
                }
               
            }
        });
        
				try
				{
   
      for(int i = 0; i<numRegistros; i++){
                         if(i+1==numRegistros)
                         {
                           vista.Barra.setValue(numRegistros);
                         }
                         else{
                             vista.Barra.setValue(i);
                             vista.Barra.repaint();
            columnas[0] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getId();
            columnas[1] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha();
            columnas[2] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getHora();
            columnas[3] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getCopias();
            columnas[4] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getEstado();
            columnas[5] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha_constancia();
            columnas[6] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha_entrega();
            columnas[7] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getTipo();
            columnas[8] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getSolicitante();
            columnas[9] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getMotivo();
            columnas[10] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getAcuerdo();
          
            //Thread.sleep(100);
                         }
      }        
            modelo.addRow(columnas);       
                                       
                                }
                                   
				catch(Exception e)
				{
					e.printStackTrace();
				}
            
 
		}           
                
         };

               
           Thread hilo = new Thread (miRunnable);
	   hilo.start();
           limpiarSolicitudExterna();
	}   

     });
                      
        
      
          //aqui va  el codigo que empieza con object      
      
           
          
        
        
        
    */    
   // }
      
       public void llenarTablaSolicitudExternaAcuerdo(JTable tabla){
        String fecha = ((JTextField)vista.JDbuscarSolicitudE.getDateEditor().getUiComponent()).getText();
        String fechaFinal = ((JTextField)vista.JDbuscarSolicitudEFinal.getDateEditor().getUiComponent()).getText();
        DefaultTableModel modelo = new DefaultTableModel();
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
        tabla.setModel(modelo);
        
        
        Object [] columnas = new Object[11];
        
        int numRegistros = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).size();
          vista.lblAmparo.setVisible(false);
          vista.jLabel52.setVisible(false);
          vista.lblAcuerdo.setVisible(true);
          vista.jLabel53.setVisible(true);         
        
        for(int i = 0; i<numRegistros; i++){
            columnas[0] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getId();
            columnas[1] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha();
            columnas[2] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getHora();
            columnas[3] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getCopias();
            columnas[4] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getEstado();
            columnas[5] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha_constancia();
            columnas[6] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getFecha_entrega();
            columnas[7] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getTipo();
            columnas[8] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getSolicitante();
            columnas[9] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getMotivo();
            columnas[10] = modeloSolicitudE.lsSolicitudEAcuerdo(fecha,fechaFinal).get(i).getAcuerdo();
            modelo.addRow(columnas);
        }  
        JOptionPane.showMessageDialog(null, "Busqueda finalizada");
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               if(tabla.getSelectedRow() != -1){
                  
                    int fila = tabla.getSelectedRow();
                    vista.lblIdSolicitudEx.setText(tabla.getValueAt(fila, 0).toString());
                    vista.lblCopiasEx.setText(tabla.getValueAt(fila, 3).toString());
                    vista.lblAcuerdo.setText(tabla.getValueAt(fila, 10).toString());
                    vista.lblSolicitante.setText(tabla.getValueAt(fila, 8).toString());
                    String causa =  modeloSolicitudE.extraerCausa(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                    vista.lblCausa.setText(causa);
                    String juez = modeloSolicitudE.extJuez(Integer.parseInt(vista.lblIdSolicitudEx.getText()));
                    vista.lblJueces.setText(juez);
                    
                    
                    int numRegistros = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).size();
                    //se obtienen las fechas de las audiencias solicitadas
                    Object  [] fechas = new Object[numRegistros];
                    for(int i = 0; i< numRegistros; i++)
                    {
                        fechas[i] = modeloSolicitudE.lsFechas(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getFecha();

                    }
                    // se muestran las fechas de audiencias en los labels
                    vista.lblFechas.setText(Arrays.toString(fechas).replace("[","").replace("]", "")+","); 
                   
                    //se obtienen las fechas de las audiencias solicitadas
                    Object  [] horas = new Object[numRegistros];
                    for(int i = 0; i< numRegistros; i++)
                    {
                        horas[i] = modeloSolicitudE.lsHoras(Integer.parseInt(vista.lblIdSolicitudEx.getText())).get(i).getFecha();

                    }
                    // se muestran las fechas de audiencias en los labels
                    vista.lblHoras.setText(Arrays.toString(horas).replace("[","").replace("]", "")+",");
                    
                }
               
            }
        });
        
    }
    public void limpiarCamposSolicitud(){
        vista.txtEcausaS.setText("");
        vista.txtEcopiasS.setText("");
        vista.txtEfechaS.setText("");
        vista.txtEfechaSoli.setText("");
        vista.txtEhoraS.setText("");
        vista.txtEhoraS.setText("");
        vista.txtEjuezS.setText("");
        vista.txtEnombreS.setText("");
        vista.lblIdSoli.setText("");
        vista.txtDisco.setText("");
        //((JTextField)vista.JDfechaBuscar.getDateEditor().getUiComponent()).setText("");
        
    }
    public void limpiarSolicitudExterna(){
        vista.lblSolicitante.setText("");
        vista.lblCopiasEx.setText("");
        vista.lblAmparo.setText("");
        vista.lblAcuerdo.setText("");
        vista.lblIdSolicitudEx.setText("");
        DefaultTableModel modelo  = (DefaultTableModel)vista.tablaSolicitudExterna.getModel();
        modelo.setRowCount(0);
}
    public void mostrarParticipantes(JTable tabla){
       DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        tabla.setModel(modelo);
       String tipos = formParticipante.CBTiposPart.getSelectedItem().toString();
        
        Object [] partes= new Object[2];
        int totalRegistros = modeloParticipante.lsTodosActualizar(tipos).size();
        
        for(int i=0; i<totalRegistros; i++){
            partes[0] = modeloParticipante.lsTodosActualizar(tipos).get(i).getNombre();
            partes[1] = modeloParticipante.lsTodosActualizar(tipos).get(i).getTipo();
            modelo.addRow(partes);
            
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    formParticipante.txtNombre.setText(tabla.getValueAt(fila, 0).toString());
                    //formParticipante.txtTipo.setText(tabla.getValueAt(fila, 1).toString());
                    String nombre  = formParticipante.txtNombre.getText();
                   // String tipo = formParticipante.txtTipo.getText();
                   String tipo = (tabla.getValueAt(fila,1).toString());
                    int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                    formParticipante.lblIdAct.setText(String.valueOf(idParticipante));
                    //formParticipante.lblIdAct.setText(tabla.getValueAt(fila, 0).toString());
                    
                    
                }
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
            }
        });
        
        
    }
    
    public void mostrarParticipantes2(JTable tabla, String tipo){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        tabla.setModel(modelo);
        
        Object [] partes= new Object[2];
        int totalRegistros = modeloParticipante.lsTodos(tipo).size();
        
        for(int i=0; i<totalRegistros; i++){
            partes[0] = modeloParticipante.lsTodos(tipo).get(i).getNombre();
            partes[1] = modeloParticipante.lsTodos(tipo).get(i).getTipo();
            modelo.addRow(partes);
            
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String nombre = tabla.getValueAt(fila, 0).toString();
                    String tipo = tabla.getValueAt(fila, 1).toString();
                    int idParticipante = modeloParticipante.extraerIdParticipante(nombre,tipo);
                    formAgregar.lblPart.setText(String.valueOf(idParticipante));
                    
                    
                }
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
            }
        });  
        
    }
    //borra mas adelante !OJO!
    public void mostrarParticipantes3(JTable tabla,String tipo){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        tabla.setModel(modelo);
        
        Object [] partes= new Object[2];
        int totalRegistros = modeloParticipante.lsTodosClasificados(tipo).size();
        
        for(int i=0; i<totalRegistros; i++){
            partes[0] = modeloParticipante.lsTodosClasificados(tipo).get(i).getNombre();
            partes[1] = modeloParticipante.lsTodosClasificados(tipo).get(i).getTipo();
            modelo.addRow(partes);
            
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    vista.txtPartes.setText(tabla.getValueAt(fila, 0).toString());
                    /*buscarParticipante.lblNombre.setText(tabla.getValueAt(fila, 0).toString());
                    buscarParticipante.lblTipo.setText(tabla.getValueAt(fila, 1).toString());
                    String nombre = buscarParticipante.lblNombre.getText();
                    String tipo =buscarParticipante.lblTipo.getText();
                    vista.txtPartes.setText(nombre);*/
                    
                    
                }
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
            }
        });  
        
    }
    public void mostrarParticipantesListener(JTable tabla,String tipo){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("SELECCIONE AL PARTICIPANTE QUE DESEA AGREGAR");
        tabla.setModel(modelo);
        
        Object [] partes= new Object[1];
        int totalRegistros = modeloParticipante.lsTodosClasificados(tipo).size();
        
        for(int i=0; i<totalRegistros; i++){
            partes[0] = modeloParticipante.lsTodosClasificados(tipo).get(i).getNombre();
            modelo.addRow(partes);
            
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                   
                    buscarParticipante.lblNombre.setText(tabla.getValueAt(fila, 0).toString());
                    String nombre = buscarParticipante.lblNombre.getText();
                    String tipo =buscarParticipante.lblTipo.getText();
                    vista.txtPartes.setText(nombre);
                    
                    
                }
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
            }
        });  
        
    }
    
    public void mostrarAudiencias(JTable tabla, int solicitud){
       /* DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("NUMERO");
        modelo.addColumn("AÑO");
        tabla.setModel(modelo);
        Object[] audiencias  =  new Object[6];
        int numRegistros = modeloSolicitudE.lsAudiencia(solicitud).size();
        
        for(int i =0; i<numRegistros; i++){
            audiencias[0] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getFecha();
            audiencias[1] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getHora();
            audiencias[2] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getSala();
            audiencias[3] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getNcausa();
            audiencias[4] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getNumero();
            audiencias[5] = modeloSolicitudE.lsAudiencia(solicitud).get(i).getAnio();
            modelo.addRow(audiencias);
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    rotuloExternas.txtFecha.setText(tabla.getValueAt(fila, 0).toString());
                    rotuloExternas.txtHora.setText(tabla.getValueAt(fila, 1).toString());
                    rotuloExternas.txtSala.setText(tabla.getValueAt(fila, 2).toString());
                    rotuloExternas.txtCausa.setText(tabla.getValueAt(fila, 3).toString());
                   
                    String numero = tabla.getValueAt(fila,4).toString();
                    String anio = tabla.getValueAt(fila, 5).toString();
                    String disco = numero+"-"+anio;
                    rotuloExternas.txtDisco.setText(disco);
                    String fecha =rotuloExternas.txtFecha.getText();
                    String hora = rotuloExternas.txtHora.getText();
                    String sala = rotuloExternas.txtSala.getText();
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
                        rotuloExternas.lblDefensa.setText(defensa +" "+"Y OTROS");
                    }else{
                        rotuloExternas.lblDefensa.setText(defensa);
                    }
                    
                    if (numFiscales > 1){
                        rotuloExternas.lblFiscal.setText(fiscal +" "+"Y OTROS");
                    }else{
                        rotuloExternas.lblFiscal.setText(fiscal);
                    }
                    
                    if (numImputados > 1){
                        rotuloExternas.lblImputado.setText(imputado +" "+"Y OTROS");
                    }else{
                        rotuloExternas.lblImputado.setText(imputado);
                    }
  
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); */
        
    }
    public void audienciasConsolidacion(JTable tabla){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("TIPO AUDIENCIA");
        modelo.addColumn("ESTADO");
        tabla.setModel(modelo);*/
        DefaultTableModel modelo = (DefaultTableModel)consolidar.Taudiencias.getModel();
        
        Object [] audiencias = new Object[6];
        int numRegistros = modeloAudiencia.listAudiencia().size();
        if (numRegistros > 0) {
            for(int i = 0; i < numRegistros; i++){
                audiencias[0] = modeloAudiencia.listAudiencia().get(i).getFecha();
                audiencias[1] = modeloAudiencia.listAudiencia().get(i).getHora();
                audiencias[2] = modeloAudiencia.listAudiencia().get(i).getSala();
                audiencias[3] = modeloAudiencia.listAudiencia().get(i).getNcausa();
                audiencias[4] = modeloAudiencia.listAudiencia().get(i).getNombre();
                audiencias[5] = modeloAudiencia.listAudiencia().get(i).getEstado();
                modelo.addRow(audiencias);
            }
            consolidar.lblTotal.setText(String.valueOf(numRegistros));
            tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tabla.getSelectedRow() != -1){
                        int fila = tabla.getSelectedRow();
                         String fecha = tabla.getValueAt(fila, 0).toString();
                         String hora = tabla.getValueAt(fila, 1).toString();
                         String sala = tabla.getValueAt(fila, 2).toString();
                         int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                         consolidar.lblAudiencia.setText(String.valueOf(audiencia));

                    }

                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        else {
            JOptionPane.showMessageDialog(null, "No existen audiencias 'NO CELEBRADAS'");
        }
    }
    
    public void editarAudiencia(JTable tabla){
       
        String causa = formParticipante.txtBcausa.getText();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("SALA");
        modelo.addColumn("CAUSA");
        modelo.addColumn("TIPO AUDIENCIA");
        modelo.addColumn("CONSOLIDACION");
        modelo.addColumn("TIPO");
        tabla.setModel(modelo);
        
        Object [] audiencias = new Object[7];
        int numRegistros = modeloAudiencia.listAudienciaEditar(causa).size();
        
        for(int i = 0; i < numRegistros; i++){
            audiencias[0] = modeloAudiencia.listAudienciaEditar(causa).get(i).getFecha();
            audiencias[1] = modeloAudiencia.listAudienciaEditar(causa).get(i).getHora();
            audiencias[2] = modeloAudiencia.listAudienciaEditar(causa).get(i).getSala();
            audiencias[3] = modeloAudiencia.listAudienciaEditar(causa).get(i).getNcausa();
            audiencias[4] = modeloAudiencia.listAudienciaEditar(causa).get(i).getNombre();
            audiencias[5] = modeloAudiencia.listAudienciaEditar(causa).get(i).getConsolidacion();
            audiencias[6] = modeloAudiencia.listAudienciaEditar(causa).get(i).getTipo();
            modelo.addRow(audiencias);
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                     String fecha = tabla.getValueAt(fila, 0).toString();
                     String hora = tabla.getValueAt(fila, 1).toString();
                     String sala = tabla.getValueAt(fila, 2).toString();
                     String causa = tabla.getValueAt(fila, 3).toString();
                     String tipo = tabla.getValueAt(fila, 4).toString();
                     String tipoau = tabla.getValueAt(fila, 6).toString();
                     String consolidacion = tabla.getValueAt(fila, 5).toString();
                     formParticipante.txtFecha.setText(fecha);
                     formParticipante.txtHora.setText(hora);
                     formParticipante.txtSala.setText(sala);
                     formParticipante.txtCausa.setText(causa);
                     formParticipante.txtConsolidacion.setText(consolidacion);
                     formParticipante.CBtipos.setSelectedItem(tipo);
                     formParticipante.CBtipo.setSelectedItem(tipoau);
                     //formParticipante.txtTaudiencia.setText(tipo);
                     int audiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                     formParticipante.lblId.setText(String.valueOf(audiencia));
                     System.out.print(audiencia);       
                }
               
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }
    public void historial(JTable tabla,String Finicio, String Ffinal){
       /* DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("N.DISCO");
        modelo.addColumn("CAUSA");
        modelo.addColumn("FECHA");
         modelo.addColumn("HORA");
        modelo.addColumn("TIPO AUDIENCIA");
        modelo.addColumn("SALA");
        modelo.addColumn("#CONSOLIDACION");

        
        tabla.setModel(modelo);
    //   DefaultTableModel modelo = (DefaultTableModel)vista.tablaAudienciasGeneral.getModel();
        
        Object [] audiencias = new Object[8];
        int numRegistros = modeloAudiencia.listHistorial(Finicio,Ffinal).size();
        
        for(int i = 0; i < numRegistros; i++){
            audiencias[0] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getHistory();
            audiencias[1] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getDisco();
            audiencias[2] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getCausa();
            audiencias[3] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getFecha();
            audiencias[4] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getHora();
            audiencias[5] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getTipo();
            audiencias[6] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getSala();
            audiencias[7] = modeloAudiencia.listHistorial(Finicio,Ffinal).get(i).getConsolidacion();
            modelo.addRow(audiencias);
        }
        JOptionPane.showMessageDialog(null, "Busqueda finalizada");
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String fecha = tabla.getValueAt(fila, 2).toString();
                    String hora = tabla.getValueAt(fila, 3).toString();
                    String sala = tabla.getValueAt(fila, 5).toString();
                    int id = modeloAudiencia.extraerId(fecha, hora, sala);
                    vista.lblIdgeneral.setText(String.valueOf(id));
                    historialParticipante(vista.tablapartGeneral);
                       
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        */
    }
    
    public void historialParticipante(JTable tabla){
        int idAudiencia = Integer.parseInt(vista.lblIdgeneral.getText());
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("TIPO");
        tabla.setModel(modelo);
        
        Object [] participantes = new Object[2];
        int numRegistros = modeloIntermedia.listHistorialP(idAudiencia).size();
        
        for (int i=0; i< numRegistros; i++){
            participantes[0] = modeloIntermedia.listHistorialP(idAudiencia).get(i).getNombre();
            participantes[1] = modeloIntermedia.listHistorialP(idAudiencia).get(i).getTipo();
            modelo.addRow(participantes);
        }
        
    }
    public void tablaCatalogo(JTable tabla){
        /*DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("TIPO DE AUDIENCIA");
        tabla.setModel(modelo);*/
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        
        Object [] tipos= new Object[1];
        int numRegistros = modeloCatalogo.listarCatalogo().size();
        
        for(int i = 0; i <numRegistros; i++){
            tipos[0] = modeloCatalogo.listarCatalogo().get(i).getNombre();
            modelo.addRow(tipos);
        }
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String tipo = tabla.getValueAt(fila, 0).toString();
                    formParticipante.txtTaudiencia.setText(tipo);
                    int id = modeloCatalogo.extraerId(tipo);
                    formParticipante.lblIdCatalogo.setText(String.valueOf(id));
                }
            }
        });
        
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
        DefaultTableModel modelo = (DefaultTableModel)vistaRotular.tablaAudiencias.getModel();
        
        Object [] audiencia =  new Object[7];
        int numRegistros = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).size();
        for(int i=0; i<numRegistros; i++){
            audiencia[0] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getFecha();
            audiencia[1] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getHora();
            audiencia[2] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getSala();
            audiencia[3] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getNcausa();
            audiencia[4] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getNombre();
            audiencia[5] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getConsolidacion();
            audiencia[6] = modeloAudiencia.listAudienciasRotular(fechaInicial, fechaFinal).get(i).getEstadoR();
            modelo.addRow(audiencia);
        }
        JOptionPane.showMessageDialog(null, "Búsqueda finalizada");
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabla.getSelectedRow() != -1){
                    int fila = tabla.getSelectedRow();
                    String fecha = tabla.getValueAt(fila, 0).toString();
                    String hora = tabla.getValueAt(fila, 1).toString();
                    String sala = tabla.getValueAt(fila, 2).toString();
                    String causa = tabla.getValueAt(fila, 3).toString();
                    vistaRotular.lblFecha.setText(fecha);
                    vistaRotular.lblHora.setText(hora);
                    vistaRotular.lblSala.setText(sala);
                    vistaRotular.lblCausa.setText(causa);
                    int idAudiencia = modeloAudiencia.extraerId(fecha, hora, sala);
                    int numDisco = modeloDisco.extraerNumeroDisco(idAudiencia);
                    int anioDisco = modeloDisco.extraerAnioDisco(idAudiencia);
                    String disco = numDisco +"-"+anioDisco;
                    vistaRotular.lblDisco.setText(disco);
                    //Obtiene el numero de participantes sdegun el tipo
                    int numFiscales = modeloParticipante.lsFiscalesOriginal(idAudiencia).size();
                    int numDefensas = modeloParticipante.lsDefensasOriginal(idAudiencia).size();
                    int numImputados = modeloParticipante.lsImputadosOriginal(idAudiencia).size();
                    
                    String fiscal = modeloParticipante.fiscalOriginal(idAudiencia);
                    String defensa = modeloParticipante.defensaOriginal(idAudiencia);
                    String imputado = modeloParticipante.imputadoOriginal(idAudiencia);
                    
                    
                    if (numDefensas > 1){
                        vistaRotular.lblDefensa.setText(defensa +" "+"Y OTROS");
                    }
                    else {
                        vistaRotular.lblDefensa.setText(defensa);
                    }
                    if (numFiscales > 1){
                        vistaRotular.lblFiscal.setText(fiscal+" "+"Y OTROS");
                    }
                    else {
                        vistaRotular.lblFiscal.setText(fiscal);
                    }
                    if (numImputados > 1){
                        vistaRotular.lblImputado.setText(imputado +" "+"Y OTROS");
                    } 
                    else{
                        vistaRotular.lblImputado.setText(imputado);
                    }
                    
                }
                
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }
    
}
