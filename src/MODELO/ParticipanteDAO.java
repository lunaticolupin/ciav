package MODELO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ParticipanteDAO
{
  Conexion con;
  
  public ParticipanteDAO()
  {
    this.con = new Conexion();
  }
  
  public Object insertarParticipante(String nombre, String tipo)
  {
    String respuesta = null;
    String sql = "INSERT INTO PARTICIPANTE (nombre,tipo) VALUES(?,?)";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setString(1, nombre.toUpperCase().trim());
      pst.setString(2, tipo.toUpperCase().trim());
      
      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas > 0) {
        respuesta = "Nuevo Participante agregado";
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print("Error al ingresar participante" + e);
    }
    return respuesta;
  }
  
  public int extraerIdParticipante(String nombre, String Tipo)
  {
    int id = 0;
    String sql = "SELECT idParticipante FROM  participante where NOMBRE = '" + nombre + "' AND TIPO='"+Tipo+"'";
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
  
  public int extraerIdParticipanteExacto(String nombre, String tipo)
  {
    int id = 0;
    String sql = "SELECT idParticipante FROM  participante where NOMBRE = '" + nombre + "' AND TIPO ='" + tipo + "' ";
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
  
  public int extraerIdParticipanteEliminar(String nombre, String tipo)
  {
    int id = 0;
    String sql = "SELECT idParticipante FROM  participante where NOMBRE = '" + nombre + "' AND TIPO = '" + tipo + "'";
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
  
  public ArrayList<Participante> lsParte(int audiencia)
  {
    ArrayList listaParticipante = new ArrayList();
    
    String sql = "SELECT participante.NOMBRE from participante INNER join aud_par on aud_par.idParticipante = participante.idParticipante where aud_par.idAudiencia = '" + audiencia + "' and participante.TIPO = 'FISCAL'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante part = new Participante();
        part.setNombre(rs.getString(1));
        listaParticipante.add(part);
      }
      accesoBD.close();
    }
    catch (SQLException e)
    {
      System.out.print(e);
    }
    return listaParticipante;
  }
  
  public ArrayList<Participante> lsTodos(String tipo)
  {
    ArrayList listaParticipantes = new ArrayList();
    
    String sql = "SELECT NOMBRE,TIPO FROM participante WHERE TIPO = '" + tipo + "'ORDER BY TIPO,NOMBRE";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante part = new Participante();
        part.setNombre(rs.getString(1));
        part.setTipo(rs.getString(2));
        listaParticipantes.add(part);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaParticipantes;
  }
  
  public ArrayList<Participante> lsTodosActualizar(String tipo)
  {
    ArrayList listaParticipantes = new ArrayList();
    
    String sql = "SELECT NOMBRE,TIPO FROM participante WHERE TIPO = '" + tipo + "' ORDER BY TIPO,NOMBRE";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante part = new Participante();
        part.setNombre(rs.getString(1));
        part.setTipo(rs.getString(2));
        listaParticipantes.add(part);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaParticipantes;
  }
  
  public ArrayList<Participante> lsTodosClasificados(String tipo)
  {
    ArrayList listaParticipantes = new ArrayList();
    
    String sql = "SELECT NOMBRE,TIPO FROM participante WHERE TIPO = '" + tipo + "' ORDER BY NOMBRE";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante part = new Participante();
        part.setNombre(rs.getString(1));
        part.setTipo(rs.getString(2));
        listaParticipantes.add(part);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaParticipantes;
  }
  
  public ArrayList<Participante> lsTodosClasificadosListener(String tipo)
  {
    ArrayList listaParticipantes = new ArrayList();
    
    String sql = "SELECT NOMBRE FROM participante WHERE TIPO = '" + tipo + "' ORDER BY NOMBRE";
    Connection accesoBD = this.con.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante part = new Participante();
        part.setNombre(rs.getString(1));
        part.setTipo(rs.getString(2));
        listaParticipantes.add(part);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listaParticipantes;
  }
  
  public void actualizarParticipante(int id, String nombre, String tipo)
  {
    Connection accesoBD = this.con.conectar();
    String sql = "UPDATE participante SET NOMBRE = '" + nombre.toUpperCase() + "',TIPO= '" + tipo + "' WHERE idParticipante = '" + id + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int filasActualizadas = pst.executeUpdate();
      if (filasActualizadas > 0) {
        JOptionPane.showMessageDialog(null, "Información de participante actualizada con éxito");
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }

  
  
  
  public String verificarExistente(String nombre, String tipo)
  {
    String participante = null;
    Connection accesoBD = this.con.conectar();
    String sql = "SELECT NOMBRE FROM PARTICIPANTE WHERE NOMBRE ='" + nombre + "' AND TIPO = '" + tipo + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        participante = rs.getString(1).trim();
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return participante;
  }
  
  public String verificarExistenteEnAudiencia(String audiencia,String nombre, String tipo)
  {
    String participante = null;
    Connection accesoBD = this.con.conectar();
    String sql = "SELECT NOMBRE FROM aud_par INNER JOIN audiencia ON audiencia.idAudiencia = aud_par.idAudiencia INNER JOIN participante ON participante.idParticipante = aud_par.idParticipante WHERE aud_par.idAudiencia ='"+audiencia+"' AND participante.TIPO='"+tipo+"' AND participante.NOMBRE='"+nombre+"'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        participante = rs.getString(1).trim();
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return participante;
  }
  
  
  
  
  
  
  public String verificarTipo(String nombre, String tipo)
  {
    String participante = "";
    Connection accesoBD = this.con.conectar();
    String sql = "SELECT TIPO FROM PARTICIPANTE WHERE NOMBRE ='" + nombre + "' AND TIPO = '" + tipo + "'";
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        participante = rs.getString(1).trim();
      }
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return participante;
  }
  
  public ArrayList<Participante> lsImputadosOriginal(int audiencia)
  {
    ArrayList imputados = new ArrayList();
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'IMPUTADO'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante p = new Participante();
        p.setNombre(rs.getString(1));
        imputados.add(p);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return imputados;
  }
  
  public ArrayList<Participante> lsFiscalesOriginal(int audiencia)
  {
    ArrayList imputados = new ArrayList();
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'FISCAL'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante p = new Participante();
        p.setNombre(rs.getString(1));
        imputados.add(p);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return imputados;
  }
  
  public ArrayList<Participante> lsDefensasOriginal(int audiencia)
  {
    ArrayList imputados = new ArrayList();
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'DEFENSA'";
    try
    {
      Connection accesoBD = this.con.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Participante p = new Participante();
        p.setNombre(rs.getString(1));
        imputados.add(p);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return imputados;
  }
  
  public String fiscalOriginal(int audiencia)
  {
    String fiscal = null;
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'FISCAL'";
    try
    {
      Connection accesoBD = this.con.conectar();
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
  
  public String defensaOriginal(int audiencia)
  {
    String defensa = null;
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'DEFENSA'";
    try
    {
      Connection accesoBD = this.con.conectar();
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
  
  public String imputadoOriginal(int audiencia)
  {
    String imputado = null;
    

    String sql = "SELECT participante.NOMBRE FROM aud_par INNER JOIN participante ON aud_par.idParticipante = participante.idParticipante INNER JOIN audiencia ON aud_par.idAudiencia = audiencia.idAudiencia WHERE audiencia.idAudiencia = '" + audiencia + "' AND participante.TIPO = 'IMPUTADO'";
    try
    {
      Connection accesoBD = this.con.conectar();
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
}
