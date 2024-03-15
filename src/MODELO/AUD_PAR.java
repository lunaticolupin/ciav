package MODELO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AUD_PAR
{
  Conexion con;
  
  public AUD_PAR()
  {
    this.con = new Conexion();
  }
  
  public Object insertarIntermedia(int audiencia, int participante)
  {
    String respuesta = null;
    String sql = "INSERT INTO AUD_PAR (idAudiencia, idParticipante) VALUES(?,?)";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setInt(1, audiencia);
      pst.setInt(2, participante);
      
      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas > 0) {
        respuesta = "Participante agregado a la audiencia";
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return respuesta;
  }
   
  
   public int eliminarIntermedia(int audiencia, int participante)
  {
        int filasAfectadas = 0;
       try
    {
  
    String sql = "DELETE FROM aud_par WHERE idAudiencia='"+audiencia+"' AND idParticipante='"+participante+"'";
   
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
       filasAfectadas = pst.executeUpdate();
          
      accesoBD.close();
       
    }
       
    catch (Exception e)
    {
      System.out.print(e);
    }
    return filasAfectadas;  
  } 
  
  public ArrayList<Consulta> listConsulta(String valor)
  {
    ArrayList listaConsulta = new ArrayList();
    
    String sql = "";
    sql = "SELECT DISTINCT participante.NOMBRE, participante.TIPO FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE audiencia.CAUSA ='" + valor + "' ";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Consulta consulta = new Consulta();
        consulta.setNombre(rs.getString(1));
        consulta.setTipo(rs.getString(2));
        
        listaConsulta.add(consulta);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaConsulta;
  }
  
  public ArrayList<Consulta> listConsulta2(int valor)
  {
    ArrayList listaConsulta = new ArrayList();
    
    String sql = "";
    sql = "SELECT participante.NOMBRE, participante.TIPO,aud_par.copias FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + valor + "' ORDER BY participante.TIPO, participante.NOMBRE";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Consulta consulta = new Consulta();
        consulta.setNombre(rs.getString(1));
        consulta.setTipo(rs.getString(2));
        consulta.setCopias(rs.getInt(3));
        listaConsulta.add(consulta);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaConsulta;
  }
  
  public ArrayList<Consulta> listHistorialP(int valor)
  {
    ArrayList listaConsulta = new ArrayList();
    
    String sql = "";
    sql = "SELECT participante.NOMBRE, participante.TIPO FROM aud_par  INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='" + valor + "'  ORDER BY participante.TIPO, participante.NOMBRE";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Consulta consulta = new Consulta();
        consulta.setNombre(rs.getString(1));
        consulta.setTipo(rs.getString(2));
        listaConsulta.add(consulta);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaConsulta;
  }
  
  public int extraerId(int idAudiencia, int idParticipante)
  {
    int id = 0;
    String sql = "SELECT id FROM AUD_PAR WHERE idAudiencia = '" + idAudiencia + "' AND idParticipante = '" + idParticipante + "'";
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
  
  public void actualizarTabla(int id, int copias)
  {
    String sql = "UPDATE aud_par SET copias ='" + copias + "', restantes = '" + copias + "' WHERE id ='" + id + "' ";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public void eliminarParticipanteDeAudiencia(int id)
  {
    String sql = "DELETE FROM aud_par WHERE id = '" + id + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public int verificarParticipanteAudiencia(int idAudiencia, int idParticipante)
  {
    int participante = 0;
    String sql = "SELECT idParticipante FROM aud_par WHERE idAudiencia = '" + idAudiencia + "'AND idParticipante= '" + idParticipante + "'";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        participante = rs.getInt(1);
      }
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return participante;
  }
  
  public void actualizarPersonaAutorizada(int id, String comentario)
  {
    String sql = "UPDATE aud_par SET autorizados = '" + comentario + "'  WHERE id = '" + id + "'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.executeUpdate();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
  
  public String extraerAtorizados(int id)
  {
    String autorizados = null;
    String sql = "SELECT autorizados FROM AUD_PAR WHERE id = '" + id + "' ";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        autorizados = rs.getString(1);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return autorizados;
  }
}
