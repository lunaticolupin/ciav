package MODELO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SolicitudDAO
{
  Conexion con;
  
  public SolicitudDAO()
  {
    this.con = new Conexion();
  }
  
  //Guardar ruta en solicitud
  
  public void GuardarRutaSolicitud(int folio, String ruta)
  {
    Connection accesoBD = this.con.conectar();
    String sql = "UPDATE Solicitud SET ruta =  '" +"\\\\"+ ruta + "', archivo='SI' WHERE ID = '" + folio + "'";
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
    String sql = "SELECT ruta FROM SOLICITUD WHERE id='"+folio+"'";
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
  
  
  
  
  //actualizar estado
  
    public void actualizarComentarioSolicitud(int folio, String comentario)
  {
    Connection accesoBD = this.con.conectar();
    String sql = "UPDATE Solicitud SET COMENTARIO = '" + comentario + "' WHERE ID= '" + folio + "'";
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
  public Object insertarSolicitud(String fecha, int copias, String encargado, String estado, int idParticipante, int disco)
  {
    String respuesta = null;
    String sql = "INSERT INTO SOLICITUD (FECHA, COPIAS, ENCARGADO,ESTADO, ID_PARTICIPANTE, NUMEROD) VALUES(?,?,?,?,?,?)";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setString(1, fecha);
      pst.setInt(2, copias);
      pst.setString(3, encargado);
      pst.setString(4, estado);
      pst.setInt(5, idParticipante);
      pst.setInt(6, disco);
      
      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas > 0) {
        respuesta = "Registro agregado con éxito";
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return respuesta;
  }
  
  public ArrayList<Solicitud> lsSolicitud(String fecha, String fechaFinal)
  {
    ArrayList listarSolicitud = new ArrayList();
    
    String sql = "";
    //sql = "SELECT ID, FECHA,HORA, COPIAS,ESTADO,FECHA_CONSTANCIA,HORA_CONSTANCIA,FECHA_ENTREGA,HORA_ENTREGA,NOMBRE,TIPO FROM solicitud INNER JOIN participante on ID_PARTICIPANTE = idParticipante WHERE FECHA BETWEEN '" + fecha + "' AND '" + fechaFinal + "'";
        sql="SELECT S.ID, S.FECHA,S.HORA, S.COPIAS,S.ESTADO,S.FECHA_CONSTANCIA,S.HORA_CONSTANCIA,S.FECHA_ENTREGA,S.HORA_ENTREGA,P.NOMBRE,P.TIPO, c.NOMBRE FROM solicitud AS S INNER JOIN participante AS P on S.ID_PARTICIPANTE = P.idParticipante INNER JOIN DISCO AS D ON S.NUMEROD = D.ID_DISCO INNER JOIN audiencia AS A on D.AUDIENCIA= A.idAudiencia INNER JOIN catalogo_audiencia  AS c on c.ID=A.NOMBREA WHERE S.FECHA BETWEEN '"+fecha+"' AND '"+fechaFinal+"' ORDER BY S.ID";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(rs.getInt(1));
        solicitud.setFecha(rs.getString(2));
        solicitud.setHora(rs.getString(3));
        solicitud.setCopias(rs.getInt(4));
        solicitud.setEstado(rs.getString(5));
        solicitud.setFecha_constancia(rs.getString(6));
        solicitud.setHora_constancia(rs.getString(7));
        solicitud.setFecha_entrega(rs.getString(8));
        solicitud.setHora_entrega(rs.getString(9));
        solicitud.setParticipante(rs.getString(10));
        solicitud.setTipoP(rs.getString(11));
        solicitud.settipoA(rs.getString(12));
        listarSolicitud.add(solicitud);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listarSolicitud;
  }
  
  public void actualizarSolicitud(int solicitud, String fecha, String hora, String estado)
  {
    String sql = "UPDATE solicitud SET FECHA_CONSTANCIA = '" + fecha + "', HORA_CONSTANCIA = '" + hora + "',ESTADO = '" + estado + "' WHERE ID = '" + solicitud + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int filasActualizadas = pst.executeUpdate();
      if (filasActualizadas > 0) {
        JOptionPane.showMessageDialog(null, "Constancia generada exitosamente");
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
  }
  
    
  //Actualizar 
   public void actualizarCancelacion(int solicitud,String estado)
  {
    String sql = "UPDATE solicitud SET ESTADO = '" + estado + "' WHERE ID = '" + solicitud + "'";
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
  public void actualizarEntrega(int solicitud, String fecha, String hora, String estado)
  {
    String sql = "UPDATE solicitud SET FECHA_ENTREGA = '" + fecha + "', HORA_ENTREGA = '" + hora + "',ESTADO = '" + estado + "' WHERE ID = '" + solicitud + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int filasActualizadas = pst.executeUpdate();
      if (filasActualizadas > 0) {
        JOptionPane.showMessageDialog(null, "Entrega éxitosa");
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
  }
  
  public int extraerNumdisco(int solicitud)
  {
    int disco = 0;
    String sql = "SELECT NUMEROD FROM SOLICITUD WHERE ID = '" + solicitud + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        disco = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return disco;
  }
  
  public int extraerIdparticipante(int solicitud)
  {
    int parte = 0;
    String sql = "SELECT ID_PARTICIPANTE FROM SOLICITUD WHERE ID = '" + solicitud + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        parte = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return parte;
  }
  
  public void generar(String juez, String fAudiencia, String causa, String parte, String fActual, String horaActual, int copias, String folio)
  {
    Connection accesoBD = this.con.conectar();
    JasperReport reporte=null;
    try
    {
      Map parametros = new HashMap();
      parametros.put("encabezado", "CONSTANCIA, En Juzgado de Oralidad Penal y Ejecución Región Judicial Centro,Puebla \n a");
      parametros.put("cuerpo", "Operador de audio y video, adscrito a la dirección de informatica y con funciones en los juzgados de Oralidad Penal y Ejecución Región Judicial Centro hace constar que siendo las '" + horaActual + "' horas del dia de hoy (dia/mes/año)'" + fActual + "' y en cumplimiento a los Ordenado en audiencia oral y publica con fecha del (año/mes/dia) '" + fAudiencia + "' por el Juez LIC. '" + juez + "', en relacion a la causa numero '" + causa + "', se entregan '" + copias + "' copia(s) autorizada(s) del audio y videograbacion que se solicitó en la causa penal ya mencionada a LIC.'" + parte + "' de quien se omiten sus generales por ya constar en actuaciones, los cuales reciben a entera satisfacción, y firma al calce  lo que se asienta para constancia legal N.I.C '" + causa + "'");
      







      parametros.put("footer", "La información requerida, puede contener datos privilegiados, confidenciales o sensibles, la Administración de este Juzgado de Oralidad Penal y Ejecución, no autoriza para que tales datos e información se utilicen con fines de comercio, trata de información por su destinatario o terceros, en términos del artículo 50 del Código Nacional de Procedimientos Penales, 2 y 20 de la Ley Federal de Transparencia y Acceso a la Información Pública Gubernamental y 38, 40 y 42 de la Ley de  Transparencia y Acceso a la Información Pública del Estado de Puebla. Quedando bajo responsabilidad de los intervinientes en el proceso y solicitantes de la información, la guarda de la información contenida en los registros solicitados.-----------------------------------------------------------------------------------------------------");
      









      parametros.put("fActual", fActual);
      parametros.put("JUEZ", "AA" + juez);
      parametros.put("fechaAu", fAudiencia);
      parametros.put("causa", causa);
      parametros.put("parte", parte);
      parametros.put("folio", folio);
      String direccion = System.getProperty("user.dir") + "\\reportes\\CONSTANCIA2.jasper";
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
       reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      
     // JasperViewer ver = new JasperViewer(mostrar);
      //JasperViewer.viewReport(mostrar, false);
      JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void generarEntregas()
  {
    Connection accesoBD = this.con.conectar();
    JasperReport reporte=null;
    try
    {
      String direccion = System.getProperty("user.dir") + "\\reportes\\REPORTE_ENTREGAS.jasper";
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
      reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, null, accesoBD);
      
      //JasperViewer ver = new JasperViewer(mostrar);
      //JasperViewer.viewReport(mostrar, false);
                       JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void generarRotulo(String fecha, String hora, String causa, String disco, String imputado, String defensa, String fiscal,String consolidacion)
  {
    Connection accesoBD = this.con.conectar();
    JasperReport reporte=null;
    try
    {
      Map parametros = new HashMap();
      parametros.put("fecha", fecha);
      parametros.put("hora", hora);
      parametros.put("causa", causa);
      parametros.put("disco", disco);
      parametros.put("consolidacion", consolidacion);
      parametros.put("imputado", imputado);
      parametros.put("defensa", defensa);
      parametros.put("fiscal", fiscal);
      String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO.jasper";
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
       reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      //JasperViewer ver = new JasperViewer(mostrar);
      //JasperViewer.viewReport(mostrar, false);
       JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
    public void generarRotulo2(String fecha, String hora, String causa, String disco, String imputado, String defensa, String fiscal,String consolidacion)
  {
    Connection accesoBD = this.con.conectar();
    JasperReport reporte=null;
    try
    {
      Map parametros = new HashMap();
      parametros.put("fecha", fecha);
      parametros.put("hora", hora);
      parametros.put("causa", causa);
      parametros.put("disco", disco);
      parametros.put("consolidacion",consolidacion);
      parametros.put("imputado", imputado);
      parametros.put("defensa", defensa);
      parametros.put("fiscal", fiscal);
      String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_EPSON.jasper";
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
       reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      //JasperViewer ver = new JasperViewer(mostrar);
      //JasperViewer.viewReport(mostrar, false);
       JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  public void solicitud(int solicitud, int participante, int disco)
  {
    Connection accesoBD = this.con.conectar();
    int numSolicitud = 0;
    String sql = "INSERT  INTO  SOLICITUD_EJEMPLO (SOLICITUD,IDPARTICIPANTE, IDDISCO) VALUES(?,?,?)";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setInt(1, solicitud);
      pst.setInt(2, participante);
      pst.setInt(3, disco);
      pst.executeUpdate();
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void borrarContenidoTabla()
  {
    Connection accesoBD = this.con.conectar();
    String sql = "DELETE FROM SOLICITUD_EJEMPLO";
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
  
    public void constanciaForaneo(String id,String causa,String solicitante,String imputado,String tipoSolicitante,String fechaAudi,String horaAudie,String copias,String fechaActual, String horaActual) { //
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros = new HashMap();
            String folio = "";
            if(id.length() == 1){
                folio = "000"+id;
            } else if(id.length() == 2) {
                folio = "00"+id;
            } else if(id.length() == 3) {
                folio = "0"+id;
            } else {
                folio = id;
            }
            parametros.put("folio", folio);
            /*parametros.put("causa:", causa);
            parametros.put("solicitante", solicitante);
            parametros.put("imputado", imputado);
            parametros.put("tipoSolicitante", tipoSolicitante);
            parametros.put("fechaAudiencia", fechaAudi);
            parametros.put("horaAudiencia", horaAudie);
            parametros.put("copias", copias);*/
            ///AUN NO SE COMO COLOCAR LAS VARIABLES EN EL PDF
            parametros.put("causa", "CONSTANCIA, En Juzgado de Oralidad Penal y Ejecución Región Judicial Centro,Puebla \n a");
            parametros.put("fecha_soli", "Operador de audio y video, adscrito a la dirección de informatica y con funciones en los juzgados de Oralidad Penal y Ejecución Región Judicial Centro hace constar que siendo las '" + horaActual + "' horas del dia de hoy (dia/mes/año) '"+ fechaActual +"' y en cumplimiento a los Ordenado en audiencia oral y publica con fecha del (año/mes/dia) '"+ fechaAudi +"'  en relacion a la causa numero '"+ causa +"' se entregan '"+ copias +"' copia(s) autorizada(s) del audio y videograbacion que se solicitó en la causa penal ya mencionada a LIC. '"+ solicitante +"' de quien se omiten sus generales por ya constar en actuaciones, los cuales reciben a entera satisfacción, y firma al calce  lo que se asienta para constancia legal N.I.C '"+ causa +"'");
            parametros.put("nombre", "La información requerida, puede contener datos privilegiados, confidenciales o sensibles, la Administración de este Juzgado de Oralidad Penal y Ejecución, no autoriza para que tales datos e información se utilicen con fines de comercio, trata de información por su destinatario o terceros, en términos del artículo 50 del Código Nacional de Procedimientos Penales, 2 y 20 de la Ley Federal de Transparencia y Acceso a la Información Pública Gubernamental y 38, 40 y 42 de la Ley de  Transparencia y Acceso a la Información Pública del Estado de Puebla. Quedando bajo responsabilidad de los intervinientes en el proceso y solicitantes de la información, la guarda de la información contenida en los registros solicitados.-----------------------------------------------------------------------------------------------------");
            
            String direccion = System.getProperty("user.dir") + "\\reportes\\foraneaSolicitud.jasper";
            
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print("constanciaForaneo: "+e);
        }
    }
    public void routarDiscoForaneo(String id,String causa,String solicitante,String imputado,String tipoSolicitante,String fechaAudi,String horaAudie,String copias,String fechaActual, String horaActual){
        //ROTULO PARA CANON
        System.out.println("DENTRO DE CANON");
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        String jefeCausas = "JEFE DE CAUSAS";
        String encSala = "ENC. SALA";
        if(tipoSolicitante.equals(jefeCausas)) {
            solicitante = "";
            tipoSolicitante="";
        } else if (tipoSolicitante.equals(encSala)) {
            solicitante = "";
            tipoSolicitante="";
        }
        try {
            Map parametros = new HashMap();
            String folio = "";
            if(id.length() == 1){
                folio = "000"+id;
            } else if(id.length() == 2) {
                folio = "00"+id;
            } else if(id.length() == 3) {
                folio = "0"+id;
            } else {
                folio = id;
            }
            parametros.put("folio", folio);
            parametros.put("fiscal", fechaAudi);//fechaAudiencia
            //parametros.put("fecha", fechaActual);
            parametros.put("hora",horaAudie);
            parametros.put("causa", causa);
            parametros.put("disco", folio);
            parametros.put("consolidacion",folio);
            parametros.put("imputado", imputado);
            parametros.put("defensa", solicitante);
            parametros.put("tipoS", tipoSolicitante);
            String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_ESPECIFICO_CANON.jasper";
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print("routarDiscoForaneo: "+e);
        }
    }
    public void generarRotuloSolicitudForanea(
            String id,String causa,
            String solicitante,String imputado,
            String tipoSolicitante,String fechaAudi,
            String horaAudie,String copias,String fechaActual, 
            String horaActual,String fechaSolicitud, String horaSolicitud
    ){
        System.out.println("DENTRO DE ROTULO_ESPECIFICO_CANON");
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        
        try {
            Map parametros = new HashMap();
            String folio = "";
            if(id.length() == 1){
                folio = "000"+id;
            } else if(id.length() == 2) {
                folio = "00"+id;
            } else if(id.length() == 3) {
                folio = "0"+id;
            } else {
                folio = id;
            }
            parametros.put("folio", folio);
            parametros.put("fiscal", fechaAudi);//fechaAudiencia
            //parametros.put("fecha", fechaActual);
            parametros.put("hora",horaAudie);
            parametros.put("causa", causa);
            parametros.put("disco", folio);
            parametros.put("consolidacion",folio);
            parametros.put("imputado", imputado);
            parametros.put("defensa", solicitante);
            String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_ESPECIFICO_CANON.jasper";
            
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void generarSolicitudForanea(
            String id,String causa,
            String solicitante,String imputado,
            String tipoSolicitante,String fechaAudi,
            String horaAudie,String copias,String fechaActual, 
            String horaActual,String fechaSolicitud, String horaSolicitud
    ){
        
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        try {
            Map parametros = new HashMap();
            parametros.put("fecha_soli", fechaSolicitud);
            parametros.put("folio", id);
            parametros.put("fecha_audiencia", fechaAudi);
            parametros.put("disco", id);
            parametros.put("causa", causa);
            parametros.put("hora", horaAudie);
            parametros.put("sala", imputado);
            parametros.put("nombre", solicitante);
            parametros.put("tipo", tipoSolicitante);
            parametros.put("copias", copias);
            parametros.put("consolidacion", id);
            //parametros.put("autorizados", autorizados);    
            String direccion = System.getProperty("user.dir")+ "\\reportes\\solicitudForanea.jasper";
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
   
    public void actualizarSolicutudForanea(String id, String horaActual, String estado) {
        //  TODO funcion para actulizar el estado de la solicitud
        Connection accesoBD = con.conectar();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = fechaHora.format(date);
        String sql = "UPDATE foraneo SET estado='"+estado+"' WHERE ID ='"+id+"'";
        try
        {
          PreparedStatement pst = accesoBD.prepareStatement(sql);
          int filasActualizadas = pst.executeUpdate();
          if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Constancia generada exitosamente");
          }
          accesoBD.close();
        }
        catch (SQLException e)
        {
          System.out.print(e);
        }
    }
    
    public void actualizarEnteregadoSolicutudForanea(String id) {
        //  TODO funcion para actulizar el estado de la solicitud
        Connection accesoBD = con.conectar();
        java.util.Date date = new java.util.Date();
        String estado = "ENTREGADOS";
        String sql = "UPDATE foraneo SET estado='"+estado+"' WHERE ID ='"+id+"'";
        try
        {
          PreparedStatement pst = accesoBD.prepareStatement(sql);
          int filasActualizadas = pst.executeUpdate();
          if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Constancia actulizada exitosamente");
          }
          accesoBD.close();
        }
        catch (SQLException e)
        {
          System.out.print(e);
        }
    }
    public void routarEpsonDiscoForaneo(String id,String causa,String solicitante,String imputado,String tipoSolicitante,String fechaAudi,String horaAudie,String copias,String fechaActual, String horaActual){
        //ROTULO PARA ROTULO_ESPECIFICO_EPSON_F
        System.out.println("DENTRO DE ROTULO_ESPECIFICO_EPSON_F");
        Connection accesoBD = con.conectar();
        JasperReport reporte=null;
        String jefeCausas = "JEFE DE CAUSAS";
        String encSala = "ENC. SALA";
        if(tipoSolicitante.equals(jefeCausas)) {
            solicitante = "";
            tipoSolicitante="";
        } else if (tipoSolicitante.equals(encSala)) {
            solicitante = "";
            tipoSolicitante="";
        }
        try {
            Map parametros = new HashMap();
            String folio = "";
            if(id.length() == 1){
                folio = "000"+id;
            } else if(id.length() == 2) {
                folio = "00"+id;
            } else if(id.length() == 3) {
                folio = "0"+id;
            } else {
                folio = id;
            }
            System.out.println(imputado +" "+ solicitante);
            parametros.put("folio", folio);
            parametros.put("fiscal", fechaAudi);//fechaAudiencia
            //parametros.put("fecha", fechaActual);
            parametros.put("hora",horaAudie);
            parametros.put("causa", causa);
            parametros.put("disco", folio);
            parametros.put("consolidacion",folio);
            parametros.put("imputado", imputado);
            parametros.put("defensa", solicitante);
            parametros.put("juez", tipoSolicitante);
            String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_ESPECIFICO_EPSON_F.jasper";
            reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
            JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
            JasperViewer ver = new  JasperViewer(mostrar,false);
            ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ver.setVisible(true);
            accesoBD.close();
        } catch (Exception e) {
            System.out.print("routarDiscoForaneo: "+e);
        }
    }
    public void cancelacionForanea(int solicitud,String estado) {
        String sql = "UPDATE foraneo SET ESTADO = '" + estado + "' WHERE ID = '" + solicitud + "'";
        Connection accesoBD = this.con.conectar();
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            int filasActualizadas = pst.executeUpdate();
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Cancelacion éxitosa");
            }
            accesoBD.close();
        }catch (SQLException e) {
            System.out.print(e);
        }
    }
}

