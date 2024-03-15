    package MODELO;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
    
public class DiscoDAO {
    
    AudienciaDAO modeloAudiencia= new AudienciaDAO();
    Conexion con;
    public DiscoDAO()
    {
        this.con = new Conexion();
    }
    //Funcion para saber el ultimo disco que esta en temporal_disco
    public ArrayList<Disco> getTemporalDisco(){
        ArrayList discos_temporal = new ArrayList();
        String sql = "SELECT * FROM `temporal_disco` ORDER by NUMERO DESC LIMIT 2";
        Connection accesoBD = this.con.conectar();
        try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Disco disco = new Disco();
                disco.setNumero(rs.getString(1));
                discos_temporal.add(disco);
            }
            accesoBD.close();
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return discos_temporal;
    }
    //Funcion para saber el ultimo disco que esta en disco
    public ArrayList<Disco> getDisco(){
        ArrayList discos = new ArrayList();
        String sql = "SELECT * FROM `disco` ORDER by ID_DISCO DESC LIMIT 2";
        Connection accesoBD = this.con.conectar();
          try {
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              Disco disco = new Disco();
                disco.setNumero(rs.getString(1));
                discos.add(disco);
            }
            accesoBD.close();
          }
          catch (Exception e) {
            System.out.print(e);
          }
        return discos;
    }
  
    public Object insertarTemporalDisco(int anio, int audiencia) {
        String respuesta = null;
        String sql = "INSERT INTO TEMPORAL_DISCO (ANIO,AUDIENCIA) VALUES(?,?)";
        try {
            Connection accesoBD = this.con.conectar();
            PreparedStatement pst = accesoBD.prepareStatement(sql);
            pst.setInt(1, anio);
            pst.setInt(2, audiencia);

            int filas = pst.executeUpdate();
            if (filas > 0) {}
            accesoBD.close();
        }
        catch (Exception e) {
            System.out.print("ERROR EN TEMPORAL" + e);
        }
        return respuesta;
    }
  
  public Object insertarDisco(String audiencia)
  {
    String respuesta = null;
    String sql = "INSERT INTO DISCO (AUDIENCIA) VALUES (?)";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setString(1, audiencia);
      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas > 0) {
        JOptionPane.showMessageDialog(null, "Registro agregado conéxito (Disco)");
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print("Error al insertar en Disco " + e);
    }
    return respuesta;
  }
  
  public void copiarDatos(int audiencia)
  {
    String sql = "INSERT INTO DISCO(NUMERO, ANIO,AUDIENCIA) SELECT TEMPORAL_DISCO.NUMERO,TEMPORAL_DISCO.ANIO,TEMPORAL_DISCO.audiencia  FROM TEMPORAL_DISCO WHERE AUDIENCIA = '" + audiencia + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeLargeUpdate();
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public int extraerIdDisco(int num, int anio)
  {
    int id = 0;
    String sql = "SELECT NUMERO FROM DISCO WHERE audiencia = '" + num + "' AND ANIO = '" + anio + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
  //validar que sea de cualquier año el numero de disoc
   public int extraerIdDisco2(int num)
  {
    int id = 0;
    String sql = "SELECT NUMERO FROM DISCO WHERE audiencia = '" + num + "' ";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
  
  public int extraerAnioDisco(int num)
  {
    int id = 0;
    String sql = "SELECT ANIO FROM DISCO WHERE audiencia = '" + num + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
  
  public int extraerNumeroDisco(int num)
  {
    int numero = 0;
    String sql = "SELECT NUMERO FROM DISCO WHERE audiencia = '" + num + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        numero = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return numero;
  }
  
  public void cambiarIcrementable()
  {
    String sql = "ALTER TABLE TEMPORAL_DISCO AUTO_INCREMENT = 1";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void eliminar()
  {
    Connection accesoBD = this.con.conectar();
    String sql = "DELETE FROM TEMPORAL_DISCO";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
      pst.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public String extraerFechaAudiencia(int disco)
  {
    String fecha = "";
    String sql = "SELECT audiencia.FECHA from disco INNER JOIN audiencia on disco.AUDIENCIA = audiencia.idAudiencia where disco.ID_DISCO = '" + disco + "'";
    
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        fecha = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return fecha;
  }
    public String extraerSalaAudiencia(int disco)
  {
    String sala = "";
    String sql = "SELECT audiencia.SALA from disco INNER JOIN audiencia on disco.AUDIENCIA = audiencia.idAudiencia where disco.ID_DISCO = '" + disco + "'";
    
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        sala = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return sala;
  }
  public String extraerHoraAudiencia(int disco)
  {
    String hora = "";
    String sql = "SELECT audiencia.HORA from disco INNER JOIN audiencia on disco.AUDIENCIA = audiencia.idAudiencia where disco.ID_DISCO = '" + disco + "'";
    
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        hora = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return hora;
  }
  
  public String extraerCausaAudiencia(int disco)
  {
    String hora = "";
    String sql = "SELECT audiencia.CAUSA from disco INNER JOIN audiencia on disco.AUDIENCIA = audiencia.idAudiencia where disco.ID_DISCO = '" + disco + "'";
    
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        hora = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return hora;
  }
  
  public int extraerIdAudiencia(int disco)
  {
    int id = 0;
    String sql = "SELECT AUDIENCIA FROM DISCO WHERE ID_DISCO = '" + disco + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
   public int extraerConsolidacion(int id_audiencia)
  {
    int id = 0;
    String sql = "SELECT CONSOLIDACION FROM audiencia WHERE idAudiencia = '" + id_audiencia + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
  
  public int anioDisco(int num)
  {
    int id = 0;
    String sql = "SELECT ANIO FROM DISCO WHERE ID_DISCO = '" + num + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return id;
  }
  
  public int numeroDisco(int num)
  {
    int numero = 0;
    String sql = "SELECT NUMERO FROM DISCO WHERE ID_DISCO = '" + num + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        numero = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return numero;
  }
  
  public void generarRotulo(Date fecha, String hora, String causa, String disco, String imputado, String defensa, String fiscal,String consolidacion)
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
      String direccion = System.getProperty("user.dir") + "\\reportes\\ORIGINAL.jasper";
      reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      
      //JasperViewer ver = new JasperViewer(mostrar);
      JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      //JasperViewer.viewReport(mostrar, false);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  //Para impresora epson
    public void generarRotulo2(Date fecha, String hora, String causa, String disco, String imputado, String defensa, String fiscal,String consolidacion)
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
      String direccion = System.getProperty("user.dir") + "\\reportes\\ORIGINAL_EPSON.jasper";
      reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      
      //JasperViewer ver = new JasperViewer(mostrar);
      JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      //JasperViewer.viewReport(mostrar, false);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
      public void generarRotuloEspecifico(int ban,Date fecha, String hora, String causa, String disco, String imputado, String defensa, String fiscal,String consolidacion)
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
      if(ban==1)
      {
      String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_ESPECIFICO.jasper";
           reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      
      //JasperViewer ver = new JasperViewer(mostrar);
      JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      //JasperViewer.viewReport(mostrar, false);
      accesoBD.close();
      }else
      {
          String direccion = System.getProperty("user.dir") + "\\reportes\\ROTULO_ESPECIFICO_EPSON.jasper";
                reporte =(JasperReport) JRLoader.loadObjectFromFile(direccion);
      //JasperReport reporte = JasperCompileManager.compileReport(direccion);
      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);
      
      //JasperViewer ver = new JasperViewer(mostrar);
      JasperViewer ver = new  JasperViewer(mostrar,false);
      ver.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      ver.setVisible(true);
      //JasperViewer.viewReport(mostrar, false);
      accesoBD.close();
      }

    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void escribirExcel(File file, String fInicio, String fFinal)
  {
    int row = 1;
    Connection accesoBD = this.con.conectar();
    WritableSheet excelsheet = null;
    WritableWorkbook workbook = null;
    try
    {
      workbook = Workbook.createWorkbook(file);
      workbook.createSheet("datos", 0);
      excelsheet = workbook.getSheet(0);
      System.out.println("Creando hoja de excel");
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    String sql = "SELECT DISTINCT disco.NUMERO, CONCAT(disco.NUMERO,\"-\",disco.ANIO) AS DISCO, audiencia.CAUSA,audiencia.FECHA, audiencia.HORA, audiencia.SALA, catalogo_audiencia.NOMBRE,audiencia.CONSOLIDACION FROM aud_par INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante "
            + "INNER JOIN catalogo_audiencia on audiencia.NOMBREA = catalogo_audiencia.ID WHERE audiencia.FECHA BETWEEN '" + fInicio + "' AND '" + fFinal + "' ORDER BY DISCO";
        //System.out.print("-1---" + sql);
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Label disco = new Label(0, row, rs.getString("DISCO"));
        Label causa = new Label(1, row, rs.getString("audiencia.CAUSA"));
        Label fecha = new Label(2, row, rs.getString("audiencia.FECHA"));
        Label hora = new Label(3, row, rs.getString("audiencia.HORA"));
        Label sala = new Label(4, row, rs.getString("audiencia.SALA"));
        Label tipo = new Label(5, row, rs.getString("catalogo_audiencia.NOMBRE"));
        Label consolidacion = new Label(6, row, rs.getString("audiencia.CONSOLIDACION"));
          //int id = modeloAudiencia.extraerId(rs.getString("audiencia.FECHA"),rs.getString("audiencia.HORA"),rs.getString("audiencia.SALA"));
       //String sql2 = "SELECT participante.NOMBRE, participante.TIPO FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + id + "'  ORDER BY participante.TIPO, participante.NOMBRE";
       
//Label nombrep = new Label(7, row, rs.getString("participante.NOMBRE"));
        //Label tipoP = new Label(8, row, rs.getString("participante.TIPO"));
       // Label autorizados = new Label(9, row, rs.getString("aud_par.AUTORIZADOS"));
       // Label copias = new Label(10, row, rs.getString("aud_par.COPIAS"));
        row++;
        try
        {
          Label encabezado = new Label(0, 0, "DISCO");
          Label encabezadoCausa = new Label(1, 0, "CAUSA");
          Label encabezadoFecha = new Label(2, 0, "FECHA AUDIENCIA");
          Label encabezadoHora = new Label(3, 0, "HORA AUDIENCIA");
          Label encabezadoSala = new Label(4, 0, "SALA");
          Label encabezadoTipoA = new Label(5, 0, "TIPO AUDIENCIA");
          Label encabezadoConso = new Label(6, 0, "CONSOLIDACION");
          //Label encabezadoParticipante = new Label(7, 0, "NOMBRE PARTICIPANTE");
          //Label encabezadoTipoP = new Label(8, 0, "TIPO PARTICIPANTE");
          //Label encabezadoAutor = new Label(9, 0, "AUTORIZADOS");
          //Label encabezadoCopias = new Label(10, 0, "COPIAS");
          
          excelsheet.addCell(encabezado);
          excelsheet.addCell(encabezadoCausa);
          excelsheet.addCell(encabezadoFecha);
          excelsheet.addCell(encabezadoHora);
          excelsheet.addCell(encabezadoSala);
          excelsheet.addCell(encabezadoTipoA);
          excelsheet.addCell(encabezadoConso);
          //excelsheet.addCell(encabezadoParticipante);
          //excelsheet.addCell(encabezadoTipoP);
          //excelsheet.addCell(encabezadoAutor);
          //excelsheet.addCell(encabezadoCopias);
          excelsheet.addCell(disco);
          excelsheet.addCell(causa);
          excelsheet.addCell(fecha);
          excelsheet.addCell(hora);
          excelsheet.addCell(sala);
          excelsheet.addCell(tipo);
          excelsheet.addCell(consolidacion);
         //excelsheet.addCell(nombrep);
         // excelsheet.addCell(tipoP);
         // excelsheet.addCell(autorizados);
         // excelsheet.addCell(copias);
        }
        catch (WriteException e)
        {
          System.out.print("Error de escritura  " + e);
        }
      }
      accesoBD.close();
      try
      {
        workbook.write();
        workbook.close();
        JOptionPane.showMessageDialog(null, "Archivo en excel creado en " + file);
      }
      catch (Exception e)
      {
        System.out.print(e);
      }
    }
    catch (SQLException e)
    {
      System.out.print("try general " + e);
    }
  }
  
  public void escribirExcelSimple(File file, String fInicio, String fFinal)
  {
    int row = 1;
    Connection accesoBD = this.con.conectar();
    WritableSheet excelsheet = null;
    WritableWorkbook workbook = null;
    try
    {
      workbook = Workbook.createWorkbook(file);
      workbook.createSheet("datos", 0);
      excelsheet = workbook.getSheet(0);
      System.out.println("Creando hoja de excel");
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    String sql = "SELECT  audiencia.FECHA, audiencia.HORA, audiencia.SALA,audiencia.CAUSA,catalogo_audiencia.NOMBRE,audiencia.ESTADO,audiencia.CONSOLIDACION,participante.NOMBRE FROM aud_par INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante WHERE participante.TIPO = 'JUEZ' AND audiencia.ESTADO = 'CELEBRADA' ORDER BY audiencia.FECHA,audiencia.HORA";
    System.out.print("----" + sql);
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Label fecha = new Label(0, row, rs.getString("audiencia.FECHA"));
        Label hora = new Label(1, row, rs.getString("audiencia.HORA"));
        Label sala = new Label(2, row, rs.getString("audiencia.SALA"));
        Label causa = new Label(3, row, rs.getString("audiencia.CAUSA"));
        Label tipo = new Label(4, row, rs.getString("catalogo_audiencia.NOMBRE"));
        Label estado = new Label(5, row, rs.getString("audiencia.ESTADO"));
        Label consolidacion = new Label(6, row, rs.getString("audiencia.CONSOLIDACION"));
        Label nombrep = new Label(7, row, rs.getString("participante.NOMBRE"));
        row++;
        try
        {
          Label encabezadoFecha = new Label(0, 0, "FECHA AUDIENCIA");
          Label encabezadoHora = new Label(1, 0, "HORA AUDIENCIA");
          Label encabezadoSala = new Label(2, 0, "SALA");
          Label encabezadoCausa = new Label(3, 0, "CAUSA");
          Label encabezadoTipoA = new Label(4, 0, "TIPO AUDIENCIA");
          Label encabezadoEst = new Label(5, 0, "ESTADO");
          Label encabezadoConso = new Label(6, 0, "CONSOLIDACION");
          Label encabezadoParticipante = new Label(7, 0, "NOMBRE JUEZ");
          

          excelsheet.addCell(encabezadoFecha);
          excelsheet.addCell(encabezadoHora);
          excelsheet.addCell(encabezadoSala);
          excelsheet.addCell(encabezadoCausa);
          excelsheet.addCell(encabezadoTipoA);
          excelsheet.addCell(encabezadoEst);
          excelsheet.addCell(encabezadoConso);
          excelsheet.addCell(encabezadoParticipante);
          excelsheet.addCell(fecha);
          excelsheet.addCell(hora);
          excelsheet.addCell(sala);
          excelsheet.addCell(causa);
          excelsheet.addCell(tipo);
          excelsheet.addCell(estado);
          excelsheet.addCell(consolidacion);
          excelsheet.addCell(nombrep);
        }
        catch (WriteException e)
        {
          System.out.print("Error de escritura  " + e);
        }
      }
      accesoBD.close();
      try
      {
        workbook.write();
        workbook.close();
        JOptionPane.showMessageDialog(null, "Archivo en excel creado en " + file);
      }
      catch (Exception e)
      {
        System.out.print(e);
      }
    }
    catch (SQLException e)
    {
      System.out.print("try general " + e);
    }
  }
}
