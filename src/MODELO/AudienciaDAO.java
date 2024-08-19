        package MODELO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.util.JRSaver;

public class AudienciaDAO
{
  Conexion conexion;
  
  public AudienciaDAO()
  {
    this.conexion = new Conexion();
  }
  public Object insertar(String fecha, String hora, String sala, String causa, int id, String estado, String consolidacion, String estadoR,int idusuario,String tipo)
  {
    String respuesta = null;
    String sql = "INSERT INTO AUDIENCIA (FECHA,HORA,SALA,CAUSA,NOMBREA,ESTADO,CONSOLIDACION,ESTADOROTULACION,USUARIO_ID,tipo) VALUES(?,?,?,?,?,?,?,?,?,?)";
    try
    {
      Connection accesoBD = this.conexion.conectar(); //conexion con la base de datos
      PreparedStatement pst = accesoBD.prepareStatement(sql); //se crea un objeto que contiene la sentencia sql  
      pst.setString(1, fecha);// agrega datos 
      pst.setString(2, hora);
      pst.setString(3, sala);
      pst.setString(4, causa);
      pst.setInt(5, id);
      pst.setString(6, estado);
      pst.setString(7, consolidacion);
      pst.setString(8, estadoR);
      pst.setInt(9,idusuario);
      pst.setString(10,tipo);
      int filasAfectadas = pst.executeUpdate(); //actualiza la fila
      if (filasAfectadas > 0) {
        respuesta = "Audiencia agregada";
      }
      accesoBD.close();// cierra conexion con base de datos
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return respuesta;
  }
  
  public ArrayList<Audiencia> lsAudiencia(String valor) //Hace una consulta de audencia
  {
    ArrayList listaAudiencia = new ArrayList();//crea un nuevo arreglo dinamico de la clase Audicencia
    
    String sql = "";
    if (valor.equals("")) {
      sql = "SELECT * FROM AUDIENCIA"; //selecciona toda el campo audiencia si la cadena esta vacia
    } else {//si no esta vacia la cadena inserta los valores de la audiencia
      sql = "SELECT a.FECHA, a.HORA, a.SALA, c.NOMBRE, a.COMENTARIO, u.Nombre FROM audiencia as a INNER JOIN catalogo_audiencia as c ON a.NOMBREA = c.ID LEFT OUTER JOIN usuarios as u ON a.usuario_id=u.id_usuario WHERE a.CAUSA ='"+valor+"' AND a.ESTADO != 'CANCELADA' ORDER BY a.FECHA,a.HORA, a.SALA";
    }
    try
    {
      Connection accesoBD = this.conexion.conectar();//conexion a DB
      PreparedStatement pst = accesoBD.prepareStatement(sql);////se crea un objeto que contiene la sentencia sql 
      ResultSet rs = pst.executeQuery();//se cargan la consulta en rs
      while (rs.next())
      {
        Audiencia audiencia = new Audiencia();
        audiencia.setFecha(rs.getString(1));
        audiencia.setHora(rs.getString(2));
        audiencia.setSala(rs.getString(3));
        audiencia.setNombre(rs.getString(4));
        audiencia.setComentario(rs.getString(5));
        audiencia.setUsuario(rs.getString(6));
        listaAudiencia.add(audiencia);//agrega los datos obtenidos por rs
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaAudiencia;
  }
  
  public ArrayList<Audiencia> listAudiencia()
  {
    ArrayList listaAudiencia = new ArrayList();
    



    String sql = "SELECT audiencia.FECHA, audiencia.HORA, audiencia.SALA, audiencia.CAUSA, catalogo_audiencia.NOMBRE,audiencia.ESTADO FROM AUDIENCIA INNER JOIN CATALOGO_AUDIENCIA ON catalogo_audiencia.ID = audiencia.NOMBREA WHERE audiencia.ESTADO = 'NO CELEBRADA' ORDER BY audiencia.FECHA, audiencia.HORA,audiencia.SALA";
    
    //System.out.print("-4--" + sql);
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Audiencia audiencia = new Audiencia();
        audiencia.setFecha(rs.getString(1));
        audiencia.setHora(rs.getString(2));
        audiencia.setSala(rs.getString(3));
        audiencia.setNcausa(rs.getString(4));
        audiencia.setNombre(rs.getString(5));
        audiencia.setEstado(rs.getString(6));
        listaAudiencia.add(audiencia);
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaAudiencia;
  }
  
  public ArrayList<Audiencia> listAudienciaEditar(String causa)
  {
    ArrayList listaAudiencia = new ArrayList();
    

    String sql = "SELECT audiencia.FECHA, audiencia.HORA, audiencia.SALA, audiencia.CAUSA, catalogo_audiencia.NOMBRE, audiencia.CONSOLIDACION, audiencia.tipo "
            + "FROM audiencia INNER JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
            + "WHERE audiencia.CAUSA = '" + causa + "' AND ( ESTADO = 'CELEBRADA' OR ESTADO = 'NO CELEBRADA')";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Audiencia audiencia = new Audiencia();
        audiencia.setFecha(rs.getString(1));
        audiencia.setHora(rs.getString(2));
        audiencia.setSala(rs.getString(3));
        audiencia.setNcausa(rs.getString(4));
        audiencia.setNombre(rs.getString(5));
        audiencia.setConsolidacion(rs.getString(6));
        audiencia.setTipo(rs.getString(7));
        listaAudiencia.add(audiencia);
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaAudiencia;
  }
  
 /* public void felicidad()
  {
    Connection accesoBD = this.conexion.conectar();
    try
    {
      String sql = "DROP DATABASE controlinterno";
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }*/
  
  public ArrayList<Audiencia> listAudienciasRotular(String fechaInicial, String fechaFinal)
  {
    ArrayList listaAudiencia = new ArrayList();
    

    String sql = "SELECT audiencia.FECHA, audiencia.HORA, audiencia.SALA, audiencia.CAUSA,"
            + " catalogo_audiencia.NOMBRE, audiencia.CONSOLIDACION,audiencia.ESTADOROTULACION "
            + "FROM audiencia INNER JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID"
            + " WHERE audiencia.FECHA BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "' "
            + "AND (audiencia.ESTADO='CELEBRADA') ORDER BY FECHA,HORA,SALA";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Audiencia audiencia = new Audiencia();
        audiencia.setFecha(rs.getString(1));
        audiencia.setHora(rs.getString(2));
        audiencia.setSala(rs.getString(3));
        audiencia.setNcausa(rs.getString(4));
        audiencia.setNombre(rs.getString(5));
        audiencia.setConsolidacion(rs.getString(6));
        audiencia.setEstadoR(rs.getString(7));
        listaAudiencia.add(audiencia);
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaAudiencia;
  }
  
  public ArrayList<Historial> listHistorial(String Finicio, String Ffinal)
  {
    ArrayList listaAudiencia = new ArrayList();
  //  System.out.print(Finicio + " ----------------------------------- " + Ffinal);
    
    String sql =
           "SELECT DISTINCT(disco.numero),CONCAT(disco.NUMERO,'-',disco.ANIO) , audiencia.CAUSA, audiencia.FECHA,audiencia.HORA,"
         + " catalogo_audiencia.NOMBRE,audiencia.SALA,audiencia.CONSOLIDACION " 
         + " FROM audiencia LEFT JOIN catalogo_audiencia ON audiencia.NOMBREA = catalogo_audiencia.ID "
         + " INNER JOIN disco ON disco.AUDIENCIA = audiencia.idAudiencia "
         + " WHERE audiencia.FECHA BETWEEN '" + Finicio + "' AND '" + Ffinal + "' ";
    
    //System.out.print(sql);
   
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Historial h = new Historial();
        h.setHistory(rs.getString(1));
        h.setDisco(rs.getString(2));
        h.setCausa(rs.getString(3));
        h.setFecha(rs.getString(4));
        h.setHora(rs.getString(5));
        h.setTipo(rs.getString(6));
        h.setSala(rs.getString(7));
        h.setConsolidacion(rs.getInt(8));
        listaAudiencia.add(h);
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaAudiencia;

  }
  
  public int extraerId(String fecha, String hora, String sala)
  {
    int id = 0;
    String sql = "SELECT idAudiencia FROM AUDIENCIA WHERE FECHA = '" + fecha + "' AND HORA = '" + hora + "' AND sala = '" + sala + "'";
    try
    {
      Connection accesoBD = this.conexion.conectar();
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
    int consolidacion = 0;
    String sql = "SELECT CONSOLIDACION from audiencia where idAudiencia= '"+id_audiencia+"'";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        consolidacion = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return consolidacion;
  }
  
  public int extraerIdCanceladas(String fecha, String hora, String sala)
  {
    int id = 0;
    String sql = "SELECT idAudiencia FROM AUDIENCIA WHERE FECHA = '" + fecha + "' AND HORA = '" + hora + "' AND sala = '" + sala + "' AND ESTADO != 'CANCELADA'";
    try
    {
      Connection accesoBD = this.conexion.conectar();
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
  
  public int extraerIdExacta(String fecha, String hora, String sala, String causa)
  {
    int id = 0;
    String sql = "SELECT idAudiencia FROM AUDIENCIA WHERE FECHA = '" + fecha + "' AND HORA = '" + hora + "' AND sala = '" + sala + "' AND CAUSA ='" + causa + "'";
    try
    {
      Connection accesoBD = this.conexion.conectar();
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
  
  public String extraerJuez(int audiencia)
  {
    String juez = "";
    String sql = "SELECT participante.NOMBRE, participante.TIPO from aud_par INNER JOIN audiencia on aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN participante  ON aud_par.idParticipante = participante.idParticipante WHERE participante.TIPO = 'JUEZ' AND audiencia.idAudiencia = '" + audiencia + "' ";
    


    Connection accesoBD = this.conexion.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        juez = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return juez;
  }
  
  public String extraerImputado(int audiencia)
  {
    String imputado = "";
    String sql = "SELECT participante.NOMBRE from aud_par INNER JOIN audiencia on aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN participante  ON aud_par.idParticipante = participante.idParticipante WHERE participante.TIPO = 'IMPUTADO' AND audiencia.idAudiencia = '" + audiencia + "' ";
    


    Connection accesoBD = this.conexion.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        imputado = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return imputado;
  }
  
  public String extraerDefensa(int audiencia)
  {
    String defensa = "";
    String sql = "SELECT participante.NOMBRE, participante.TIPO from aud_par INNER JOIN audiencia on aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN participante  ON aud_par.idParticipante = participante.idParticipante WHERE participante.TIPO = 'DEFENSA' AND audiencia.idAudiencia = '" + audiencia + "' ";
    


    Connection accesoBD = this.conexion.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        defensa = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return defensa;
  }
  
  public String extraerFiscal(int audiencia)
  {
    String fiscal = "";
    String sql = "SELECT participante.NOMBRE from aud_par INNER JOIN audiencia on aud_par.idAudiencia = audiencia.idAudiencia INNER JOIN participante  ON aud_par.idParticipante = participante.idParticipante WHERE participante.TIPO = 'FISCAL' AND audiencia.idAudiencia = '" + audiencia + "' ";
    


    Connection accesoBD = this.conexion.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        fiscal = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return fiscal;
  }
  
  //validar que ya esta consolidada
    public int ValidarConsolidacion(int audiencia)
  {
      int cantidadfilas=0;
    String sql = "SELECT * FROM AUDIENCIA WHERE idAudiencia='"+audiencia+"' AND ESTADO='CELEBRADA' ";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
while(rs.next())
{
    cantidadfilas++;
}
      System.out.println("idAudiencia : "+audiencia);
      if (cantidadfilas > 0) {
          
          System.out.println("Filas : "+cantidadfilas);
      return 1 ;
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print("ERROR EN VALIDAR CONSOLIDACION" + e);
    }
    return 0;
  }
  
  
  
  public void actualizarEstado(int id)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE audiencia SET ESTADO = 'CELEBRADA' WHERE idAudiencia= '" + id + "'";
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
  
  public void actualizarAudiencia(String fecha, String hora, String sala, String causa, int tipo, int id, String consolidacion, String tipoau)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE audiencia SET FECHA= '" + fecha + "', HORA = '" + hora + "', SALA = '" + sala + "', CAUSA = '" + causa + "',NOMBREA ='" + tipo + "', CONSOLIDACION = '" + consolidacion + "', tipo = '"+tipoau+"' WHERE idAudiencia= '" + id + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int respuesta = pst.executeUpdate();
      if (respuesta > 0) {
        JOptionPane.showMessageDialog(null, "Audiencia actualizada con éxito");
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void cancelarAudiencia(int id)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE audiencia SET ESTADO = 'CANCELADA' WHERE idAudiencia= '" + id + "'";
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
  
  public void reporteGeneral(Date fInicio, Date fFinal, String total)
  {
    Connection accesoBD = this.conexion.conectar();
    try
    {
      Map parametros = new HashMap();
      parametros.put("inicio", fInicio);
      parametros.put("fin", fFinal);
      parametros.put("total", total);
      String direccion = System.getProperty("user.dir") + "\\reportes\\GENERAL.jasper";
      JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(direccion);

      JasperPrint mostrar = JasperFillManager.fillReport(reporte, parametros, accesoBD);

      JasperViewer.viewReport(mostrar, false);
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  public void actualizarAudienciaDuracion(int id, String duracion)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE audiencia SET duracion= '" + duracion + "' WHERE idAudiencia= '" + id + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int respuesta = pst.executeUpdate();
      if (respuesta > 0) {
        JOptionPane.showMessageDialog(null, "Se Registro la duracion de la Audiencia");
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  public void actualizarComentario(int id, String comentario)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE audiencia SET COMENTARIO = '" + comentario + "' WHERE idAudiencia= '" + id + "'";
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
  
  public String verificaNumConsolidacion(String numero)
  {
    String consolidacion = null;
    Connection accesoBD = this.conexion.conectar();
    String sql = "SELECT CONSOLIDACION FROM audiencia WHERE CONSOLIDACION = '" + numero + "' AND ESTADO != 'CANCELADA'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        consolidacion = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print("ERROR EN CONSOLIDACION" + e);
    }
    return consolidacion;
  }
  
  public int extraConsolidacion(int audiencia)
  {
    int consolidacion = 0;
    Connection accesoBD = this.conexion.conectar();
    String sql = "SELECT CONSOLIDACION FROM audiencia WHERE idAudiencia = '" + audiencia + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        consolidacion = rs.getInt(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return consolidacion;
  }
  
  public void actualizarEstadoRotulacion(int audiencia)
  {
    Connection accesoBD = this.conexion.conectar();
    String sql = "UPDATE AUDIENCIA SET ESTADOROTULACION='ROTULADO' WHERE idAudiencia = '" + audiencia + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int i = pst.executeUpdate();
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
}
