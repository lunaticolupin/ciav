/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;
//import com.mysql.jdbc.Connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.table.DefaultTableModel;
import VISTA.Registrar;
import java.sql.ResultSetMetaData;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class SolicitudExternaDAO {
    Conexion  con;
    
    
//Registrar vista = new Registrar();
    public SolicitudExternaDAO() {
        con = new Conexion();
       
       
    }
      public void actualizarComentarioExterna(int id, String comentario)
  {
    Connection accesoBD =con.conectar();
    String sql = "UPDATE solicitud_externa SET COMENTARIO = '" + comentario + "' WHERE ID= '" + id + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
         public void actualizarCancelacionExterna(int solicitud,String estado)
  {
    String sql = "UPDATE solicitud_externa SET ESTADO = '" + estado + "' WHERE ID = '" + solicitud + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int filasActualizadas = pst.executeUpdate();
      if (filasActualizadas > 0) {
        JOptionPane.showMessageDialog(null, "Cancelacion éxitosa");
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
  }
 
//Guardar ruta en solicitud
  
  public void GuardarRutaSolicitud(int folio, String ruta)
  {
    Connection accesoBD = this.con.conectar();
    String sql = "UPDATE Solicitud_externa SET ruta =  '" +"\\\\"+ ruta + "', archivo='SI' WHERE ID = '" + folio + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int filasActualizadas = pst.executeUpdate();
      if (filasActualizadas > 0) {
        JOptionPane.showMessageDialog(null, "Se guardo la ruta en la base de datos exitosamente numero de folio:"+folio);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
      
      
      
  }         
         
         
         
         
         
  //VALIDACION SI EXISTE LA RUTA DE UN ARCHIVO PDF
  
    public String extraerRuta(String folio)
  {
    String ruta ="";
    String sql = "SELECT ruta FROM SOLICITUD_EXTERNA WHERE id='"+folio+"'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        ruta = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return ruta;
  }       
         
         
      
    public ArrayList<SolicitudExterna> lsSolicitudE(){
        ArrayList listarSolicitudes  = new ArrayList();
        SolicitudExterna solicitudExterna;
        
        String sql ="";
        sql= "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,NUMAMPARO,DEPENDENCIA,MOTIVO,FECHA_ACUERDO "
                + "FROM SOLICITUD_EXTERNA";
        try {
            Connection accesoBD = con.conectar();
            
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                solicitudExterna =  new SolicitudExterna();
                solicitudExterna.setId(rs.getInt(1));
                solicitudExterna.setFecha(rs.getString(2));
                solicitudExterna.setHora(rs.getString(3));
                solicitudExterna.setCopias(rs.getInt(4));
                solicitudExterna.setEstado(rs.getString(5));
                solicitudExterna.setFecha_constancia(rs.getString(6));
                solicitudExterna.setFecha_entrega(rs.getString(7));
                solicitudExterna.setTipo(rs.getString(8));
                solicitudExterna.setSolicitante(rs.getString(9));
                solicitudExterna.setAmparo(rs.getString(10));
                solicitudExterna.setDependencia(rs.getString(11));
                solicitudExterna.setMotivo(rs.getString(12));
                solicitudExterna.setAcuerdo(rs.getString(13));
                listarSolicitudes.add(solicitudExterna);
                
            }
            accesoBD.close();
            
        } catch (SQLException e) {
            System.out.print("Error en externa "+ e);
        }
         
        return listarSolicitudes;
    }
    
    public ArrayList<SolicitudExterna> lsSolicitudEAmparo(String fecha,String fechaFinal){
        ArrayList listarSolicitudes  = new ArrayList();
        SolicitudExterna solicitudExterna;
        
        String sql ="";
                    sql= "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,NUMAMPARO,DEPENDENCIA "
                            + "FROM SOLICITUD_EXTERNA WHERE TIPO = 'AMPARO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' ";
            
        try {
            Connection accesoBD = con.conectar();
            
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                solicitudExterna =  new SolicitudExterna();
                solicitudExterna.setId(rs.getInt(1));
                solicitudExterna.setFecha(rs.getString(2));
                solicitudExterna.setHora(rs.getString(3));
                solicitudExterna.setCopias(rs.getInt(4));
                solicitudExterna.setEstado(rs.getString(5));
                solicitudExterna.setFecha_constancia(rs.getString(6));
                solicitudExterna.setFecha_entrega(rs.getString(7));
                solicitudExterna.setTipo(rs.getString(8));
                solicitudExterna.setSolicitante(rs.getString(9));
                solicitudExterna.setAmparo(rs.getString(10));
                solicitudExterna.setDependencia(rs.getString(11));
                listarSolicitudes.add(solicitudExterna);
                
            }
            accesoBD.close();
            pst.close();
            
        } catch (SQLException e) {
            System.out.print("Error en externa "+ e);
        }
         
        return listarSolicitudes;
    }
    
    public ArrayList<SolicitudExterna> lsSolicitudEAcuerdo(String fecha,String fechaFinal){
        
        ArrayList listarSolicitudes  = new ArrayList();
        SolicitudExterna solicitudExterna;
         DefaultTableModel modelotabla = new DefaultTableModel();
        String sql ="";
        sql= "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,FECHA_ENTREGA,TIPO,SOLICITANTE,MOTIVO,FECHA_ACUERDO "
                + "FROM SOLICITUD_EXTERNA WHERE TIPO = 'ACUERDO' AND FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"'"    ;
        try {
            Connection accesoBD = con.conectar();
            
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                solicitudExterna =  new SolicitudExterna();
                solicitudExterna.setId(rs.getInt(1));
                solicitudExterna.setFecha(rs.getString(2));
                solicitudExterna.setHora(rs.getString(3));
                solicitudExterna.setCopias(rs.getInt(4));
                solicitudExterna.setEstado(rs.getString(5));
                solicitudExterna.setFecha_constancia(rs.getString(6));
                solicitudExterna.setFecha_entrega(rs.getString(7));
                solicitudExterna.setTipo(rs.getString(8));
                solicitudExterna.setSolicitante(rs.getString(9));
                solicitudExterna.setMotivo(rs.getString(10));
                solicitudExterna.setAcuerdo(rs.getString(11));
                listarSolicitudes.add(solicitudExterna);
                
            }
            accesoBD.close();
            
        } catch (SQLException e) {
            System.out.print("Error en externa "+ e);
        }
         
        return listarSolicitudes;
    }
    
    
    public void actualizarEntregaExterna(int solicitud,String fecha,String estado){
        String sql = "UPDATE SOLICITUD_EXTERNA SET FECHA_ENTREGA = '"+fecha+"',ESTADO = '"+estado+"' WHERE ID = '"+solicitud+"'";
        Connection accesoBD = con.conectar();
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            int filasActualizadas = pst.executeUpdate();
            
            if(filasActualizadas >0){
                JOptionPane.showMessageDialog(null, "Entrega éxitosa");
            }
            accesoBD.close();
            
        } catch (SQLException e) {
            System.out.print(e);
        }
       
    }
    public void actualizarConstanciaExterna(int solicitud,String fecha,String estado){
        String sql = "UPDATE SOLICITUD_EXTERNA SET FECHA_CONSTANCIA = '"+fecha+"',ESTADO = '"+estado+"' WHERE ID = '"+solicitud+"'";
        Connection accesoBD = con.conectar();
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            int filasActualizadas = pst.executeUpdate();
            
            if(filasActualizadas >0){
                JOptionPane.showMessageDialog(null, "Constancia generada exitosamente");
            }
            accesoBD.close();
            
        } catch (SQLException e) {
            System.out.print(e);
        }
       
    }
    public ArrayList<Audiencia> lsFechas(int solicitud){
        ArrayList listarFechas = new ArrayList();
        Audiencia a;
        String sql = "SELECT audiencia.FECHA from disco "
                + "INNER JOIN sol_dis on sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE sol_dis.idSolicitud = '"+solicitud+"'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                a = new Audiencia();
                a.setFecha(rs.getString(1));
                listarFechas.add(a);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    return listarFechas;
}
    
    public ArrayList<Audiencia> lsHoras(int solicitud){
        ArrayList listarHoras = new ArrayList();
        Audiencia a;
        String sql = "SELECT audiencia.HORA from disco "
                + "INNER JOIN sol_dis on sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE sol_dis.idSolicitud = '"+solicitud+"'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                a = new Audiencia();
                a.setHora(rs.getString(1));
                listarHoras.add(a);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    return listarHoras;
}
    
    public ArrayList<Disco> lsDisco(int solicitud){
        ArrayList listarDisco = new ArrayList();
        Disco d;
        String sql;
        sql ="SELECT disco.NUMERO,disco.ANIO from disco "
                + "INNER JOIN sol_dis on sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE sol_dis.idSolicitud = '"+solicitud+"'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                d = new Disco();
                d.setNumero(rs.getString(1));
                d.setAnio(rs.getString(2));
                listarDisco.add(d);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    return listarDisco;
}
    public ArrayList<Audiencia> lsAudiencia (int solicitud){
        ArrayList audiencias  =  new ArrayList();
        Audiencia a;
        String sql = "";
        sql = "SELECT audiencia.FECHA, audiencia.HORA,audiencia.SALA,audiencia.CAUSA,disco.NUMERO,disco.ANIO FROM sol_dis "
                + "INNER JOIN solicitud_externa ON sol_dis.idSolicitud = solicitud_externa.ID "
                + "INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE solicitud_externa.ID = '"+solicitud+"'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                a = new Audiencia();
                a.setFecha(rs.getString(1));
                a.setHora(rs.getString(2));
                a.setSala(rs.getString(3));
                a.setNcausa(rs.getString(4));
                a.setNumero(rs.getString(5));
                a.setAnio(rs.getString(6));
                audiencias.add(a);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return audiencias;
    }
    public ArrayList<Participante> lsImputados(int audiencia){
        ArrayList imputados = new ArrayList();
        Participante p;
        String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'IMPUTADO'";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p = new Participante();
                p.setNombre(rs.getString(1));
                imputados.add(p);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return imputados;
    }
    public String imputado(int audiencia){
        String imputado = null;
        Participante p;
        String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'IMPUTADO'";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                imputado = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return imputado;
    }
    public ArrayList<Participante> lsFiscales(int audiencia){
        ArrayList fiscales = new ArrayList();
        Participante p;
         String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'FISCAL'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p = new Participante();
                p.setNombre(rs.getString(1));
                fiscales.add(p);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return fiscales;
    }
    public String fiscal(int audiencia){
        String fiscal = null;
        Participante p;
        String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'FISCAL'";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(    );
            while(rs.next()){
                fiscal = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return fiscal;
    }
   public ArrayList<Participante> lsDefensas(int audiencia){
        ArrayList defensas = new ArrayList();
        Participante p;
         String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'DEFENSA'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p = new Participante();
                p.setNombre(rs.getString(1));
                defensas.add(p);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return defensas;
    }
   public String defensa(int audiencia){
        String defensa = null;
        Participante p;
        String sql = "SELECT participante.NOMBRE FROM aud_par "
                + "INNER JOIN participante ON aud_par.idParticipante =  participante.idParticipante "
                + "INNER JOIN audiencia on aud_par.idAudiencia =  audiencia.idAudiencia "
                + "WHERE audiencia.idAudiencia = '"+audiencia+"' AND participante.TIPO = 'DEFENSA'";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                defensa = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return defensa;
    }
   
   /* public ArrayList<Participante> lsJueces(int solicitud){
        ArrayList listarJueces = new ArrayList();
        Participante p;
        String sql;
        sql = "SELECT DISTINCT participante.NOMBRE from disco "
                + "INNER JOIN sol_dis on sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "INNER JOIN aud_par ON aud_par.idAudiencia = audiencia.idAudiencia "
                + "INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante "
                + "WHERE sol_dis.idSolicitud = '"+solicitud+"' AND participante.TIPO = 'JUEZ'";
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p = new Participante();
                p.setNombre(rs.getString(1));
                listarJueces.add(p);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return listarJueces;
    }*/
    
    public String extJuez (int solicitud){
        String juez = null;
        String sql;
        sql = "SELECT DISTINCT participante.NOMBRE from disco "
                + "INNER JOIN sol_dis on sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "INNER JOIN aud_par ON aud_par.idAudiencia = audiencia.idAudiencia "
                + "INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante "
                + "WHERE sol_dis.idSolicitud = '"+solicitud+"' AND participante.TIPO = 'JUEZ';";
        
        try {
            Connection accesoBD = con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                juez = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return juez;
    }
    public void generarAcuerdo(String juez,String fAudiencia,String causa,String solicitante,String fActual,String horaActual,int copias,String fAcuerdo,String folio){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros =  new HashMap();
            parametros.put("encabezado", "CONSTANCIA, En Juzgado de Oralidad Penal y Ejecución Región Judicial Centro,Puebla \n a");
            parametros.put("cuerpo","Operador de audio y video,adscrito a la dirección de informatica y con funciones en los juzgados de Oralidad Penal y Ejecución Región"
                                   + " Judicial Centro hace constar que siendo las '"+horaActual+"' horas del dia de hoy (dia/mes/año)"
                                   + " '"+fActual+"' y en cumplimiento a lo Ordenado por el acuerdo con fecha del '"+fAcuerdo+"' (año/mes/dia) "
                                   + "se entregan '"+copias+"' copia(s) autorizada(s) en relación a la causa número '"+causa+"' celebradas con"
                                   + " fechas del (año/mes/dia) '"+fAudiencia+"' por el Juez LIC. '"+juez+"' al LIC. '"+solicitante+"' los cuales"
                                   + " reciben a entera satisfacción, y firma al calce lo que se asienta para constancia legal N.I.C '"+causa+"'");
                                           
                                     
            parametros.put("footer", "La información requerida, puede contener datos privilegiados, confidenciales o sensibles, la "
                        + "Administración de este Juzgado de Oralidad Penal y Ejecución, no autoriza para que tales datos e "
                        + "información se utilicen con fines de comercio, trata de información por su destinatario o terceros, en "
                        + "términos del artículo 50 del Código Nacional de Procedimientos Penales, 2 y 20 de la Ley Federal de "
                        + "Transparencia y Acceso a la Información Pública Gubernamental y 38, 40 y 42 de la Ley de "
                        + " Transparencia y Acceso a la Información Pública del Estado de Puebla. Quedando bajo "
                        + "responsabilidad de los intervinientes en el proceso y solicitantes de la información, la guarda de la "
                        + "información contenida en los registros solicitados.---------------------------------------------------------------------------"
                        + "--------------------------");
            parametros.put("folio", folio);
            
            
            String direccion = System.getProperty("user.dir")+ "\\reportes\\ACUERDO1.jasper";
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            //JasperPrintManager.printReport(mostrar, true);
            //JasperViewer ver = new  JasperViewer(mostrar);
            //ver.viewReport(mostrar, false);
                  JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void generarAmparo(String juez,String fAudiencia,String causa,String fActual,String horaActual,int copias,String amparo,String folio){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros =  new HashMap();
            parametros.put("encabezado", "CONSTANCIA, En Juzgado de Oralidad Penal y Ejecución Región Judicial Centro,Puebla \n a");
            parametros.put("cuerpo","Operador de audio y video,adscrito a la dirección de informatica y con funciones en los juzgados de Oralidad Penal y Ejecución Región"
                                   + " Judicial Centro hace constar que siendo las '"+horaActual+"' horas del dia de hoy (dia/mes/año)"
                                   + " '"+fActual+"' y en cumplimiento a lo Ordenado por MEMO Num. '"+amparo+"'"
                                   + " se entregan '"+copias+"' copia(s) autorizada(s) en relación a la causa número '"+causa+"' celebradas con"
                                   + " fechas del (año/mes/dia) '"+fAudiencia+"' por el Juez LIC. '"+juez+"'  los cuales"
                                   + " reciben a entera satisfacción, y firma al calce lo que se asienta para constancia legal N.I.C '"+causa+"'");
                                           
                                     
            parametros.put("footer", "La información requerida, puede contener datos privilegiados, confidenciales o sensibles, la "
                        + "Administración de este Juzgado de Oralidad Penal y Ejecución, no autoriza para que tales datos e "
                        + "información se utilicen con fines de comercio, trata de información por su destinatario o terceros, en "
                        + "términos del artículo 50 del Código Nacional de Procedimientos Penales, 2 y 20 de la Ley Federal de "
                        + "Transparencia y Acceso a la Información Pública Gubernamental y 38, 40 y 42 de la Ley de "
                        + " Transparencia y Acceso a la Información Pública del Estado de Puebla. Quedando bajo "
                        + "responsabilidad de los intervinientes en el proceso y solicitantes de la información, la guarda de la "
                        + "información contenida en los registros solicitados.---------------------------------------------------------------------------"
                        + "--------------------------");
            parametros.put("folio", folio);
            
            
            String direccion = System.getProperty("user.dir")+ "\\reportes\\AMPARO.jasper";
            //JasperReport reporte = JasperCompileManager.compileReport(direccion);
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            //JasperPrintManager.printReport(mostrar, true);
            //JasperViewer ver = new  JasperViewer(mostrar);
            //ver.viewReport(mostrar, false);
            JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void generarRotulo(Date fecha,String hora,String causa,String disco,String imputado,String defensa,String fiscal,String juez,int folio, String consolidacion){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros = new HashMap();
            parametros.put("fecha",fecha );
            parametros.put("hora",hora );
            parametros.put("causa",causa );
            parametros.put("disco",disco );
            parametros.put("consolidacion",consolidacion);
            parametros.put("imputado",imputado );
            parametros.put("defensa",defensa );
            parametros.put("fiscal",fiscal);
            parametros.put("juez", juez);
            parametros.put("folio", folio);
            
           String direccion = System.getProperty("user.dir")+ "\\reportes\\ROTULOEXTERNO.jasper";
            //JasperReport reporte = JasperCompileManager.compileReport(direccion);
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            //JasperPrintManager.printReport(mostrar, true);
           // JasperViewer ver = new  JasperViewer(mostrar);
            //ver.viewReport(mostrar, false);
                  JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
        public void generarRotulo2(Date fecha,String hora,String causa,String disco,String imputado,String defensa,String fiscal,String juez,int folio, String consolidacion){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map <String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha",fecha );
            parametros.put("hora",hora );
            parametros.put("causa",causa );
            parametros.put("disco",disco );
            parametros.put("consolidacion",consolidacion );
            parametros.put("imputado",imputado );
            parametros.put("defensa",defensa );
            parametros.put("fiscal",fiscal);
            parametros.put("juez", juez);
            parametros.put("folio", folio);
            
           String direccion = System.getProperty("user.dir")+ "\\reportes\\ROTULOEXTERNO_EPSON.jasper";
            //JasperReport reporte = JasperCompileManager.compileReport(direccion);
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            //JasperPrintManager.printReport(mostrar, true);
           // JasperViewer ver = new  JasperViewer(mostrar);
            //ver.viewReport(mostrar, false);
                  JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void imprimirSolicitud(int idSolicitud){
          Connection accesoBD = con.conectar();
          String sql = "INSERT INTO AMPARO(IDSOLICITUD) VALUES(?)";
          try {
              PreparedStatement pst = accesoBD.prepareStatement(sql);
              pst.setInt(1,idSolicitud);
              pst.executeLargeUpdate();
              accesoBD.close();
              
          } catch (Exception e) {
              System.out.print(e);
          }
      }
    public void borrarContenidoTabla(){
        Connection accesoBD = con.conectar();
        String sql ="DELETE FROM AMPARO";
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            pst.executeUpdate();
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public String extraerCausa(int solicitud){
        String causa =  null;
        Connection accesoBD = con.conectar();
        String sql;
        sql = "SELECT DISTINCT audiencia.CAUSA FROM sol_dis "
                + "INNER JOIN solicitud_externa ON sol_dis.idSolicitud = solicitud_externa.ID "
                + "INNER JOIN disco ON sol_dis.idDisco = disco.ID_DISCO "
                + "INNER JOIN audiencia ON disco.AUDIENCIA = audiencia.idAudiencia "
                + "WHERE solicitud_externa.ID = '"+solicitud+"'";
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                causa = rs.getString(1);
            }
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return causa;
    }
    public void generarEntregas(){
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            
            String direccion = System.getProperty("user.dir")+ "\\reportes\\REPORTE_ENTREGASEXT.jasper";
            //JasperReport reporte = JasperCompileManager.compileReport(direccion);
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, null, accesoBD);
            //JasperPrintManager.printReport(mostrar, true);
            //JasperViewer ver = new  JasperViewer(mostrar);
            //ver.viewReport(mostrar, false);
                  JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
}
