package MODELO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CatalogoDAO
{
  Conexion conexion;
  
  public CatalogoDAO()
  {
    this.conexion = new Conexion();
  }
  
  public Object insertar(String nombre)
  {
    String respuesta = null;
    String sql = "INSERT  INTO CATALOGO_AUDIENCIA(NOMBRE) VALUES (?)";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      pst.setString(1, nombre);
      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas > 0) {
        respuesta = "Registro agregado con  éxito";
      }
      pst.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return respuesta;
  }
  
  public int extraerId(String nombre)
  {
    int id = 0;
    String sql = "SELECT ID FROM CATALOGO_AUDIENCIA WHERE NOMBRE = '" + nombre + "'";
    Connection accesoBD = this.conexion.conectar();
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
  
  public ArrayList<String> llenarCombo()
  {
    ArrayList<String> lista = new ArrayList();
    String sql = "SELECT DISTINCT NOMBRE FROM CATALOGO_AUDIENCIA ORDER BY NOMBRE";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        lista.add(rs.getString("NOMBRE"));
      }
      pst.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return lista;
  }
    public ArrayList<String> llenarComboUsuario()
  {
    ArrayList<String> lista = new ArrayList();
    String sql = "SELECT DISTINCT Nombre FROM usuarios  WHERE tipo_id='2' ORDER BY Nombre";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        lista.add(rs.getString("NOMBRE"));
      }
      pst.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return lista;
  }
  public ArrayList<Catalogo_audiencia> listarCatalogo()
  {
    ArrayList listarTipos = new ArrayList();
    
    String sql = "SELECT NOMBRE FROM catalogo_audiencia ORDER BY NOMBRE";
    try
    {
      Connection accesoBD = this.conexion.conectar();
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        Catalogo_audiencia catalogo = new Catalogo_audiencia();
        catalogo.setNombre(rs.getString(1));
        listarTipos.add(catalogo);
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
    return listarTipos;
  }
  
  public void actualizar(String tipo, int id)
  {
    String sql = "UPDATE CATALOGO_AUDIENCIA SET NOMBRE = '" + tipo + "' WHERE ID = '" + id + "'";
    Connection accesoBD = this.conexion.conectar();
    try
    {
      PreparedStatement pst = accesoBD.prepareStatement(sql);
      int respuesta = pst.executeUpdate();
      if (respuesta > 0) {
        JOptionPane.showMessageDialog(null, "Registro actualizado");
      }
      accesoBD.close();
    }
    catch (Exception e)
    {
      System.out.print(e);
    }
  }
}
