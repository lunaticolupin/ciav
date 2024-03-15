        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CarlosNitsuga
 */
public class ConsultaUsuarios extends Conexion{
    public boolean registro (usuarios usr)
    {
        PreparedStatement ps= null;
        Connection con= conectar();
        String sql="INSERT INTO usuarios (Nombre,contrasena,tipo_id) VALUES (?,?,?)";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1,usr.getNombre());
            ps.setString(2,usr.getContrasena());
            ps.setInt(3, usr.getId_tipo());
            ps.execute();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public int extraerId (String usuario)
    {
        int id=0;
        PreparedStatement ps= null;
        ResultSet rs=null;
        Connection con= conectar();
        String sql="SELECT id_usuario FROM usuarios WHERE Nombre = ?";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1,usuario);
            rs =ps.executeQuery();
             if(rs.next())
             {
              id=rs.getInt(1);   
             }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return id;
    }
         public int existeUsuario (String usuario)
    {
        PreparedStatement ps= null;
        ResultSet rs=null;
        Connection con= conectar();
        String sql="SELECT count(id_usuario) FROM usuarios WHERE Nombre = ?";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1,usuario);
            rs =ps.executeQuery();
             if(rs.next())
             {
                return rs.getInt(1); 
             }
             con.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }
  
             
     
     
     
     public boolean Login(usuarios usr)
    {
        PreparedStatement ps= null;
        ResultSet rs=null;
        Connection con= conectar();
        String sql="SELECT id_usuario,Nombre,contrasena,tipo_id FROM usuarios WHERE Nombre = ?";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1,usr.getNombre());
            rs =ps.executeQuery();
             if(rs.next())
             {
                 if(usr.getContrasena().equals(rs.getString(3)))
                 {
                     usr.setId(rs.getInt(1));
                     usr.setNombre(rs.getString(2));
                     usr.setId_tipo(rs.getInt(4));
                     con.close();
                     return true;
                 }else{
                     con.close();
                     return false;
                 }
                
             }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
         public int extraerTipoUsuario (String usuario)
    {
        int id=0;
        PreparedStatement ps= null;
        ResultSet rs=null;
        Connection con= conectar();
        String sql="SELECT tipo_id FROM usuarios WHERE Nombre = ?";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1,usuario);
            rs =ps.executeQuery();
             if(rs.next())
             {
              id=rs.getInt(1);   
             }
            con.close();;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return id;
    } 
    public String BajaUsuario(String  usuario)
    { 
        String respuesta=null; 
        PreparedStatement ps= null;
        ResultSet rs=null;
        Connection con= conectar();
        String sql="UPDATE usuarios SET contrasena = '' WHERE usuarios.Nombre = ?";
        try{
            ps =con.prepareStatement(sql);
            ps.setString(1,usuario);
            int filasafectadas=ps.executeUpdate();
             if (filasafectadas > 0) {
        respuesta = "Usuario ha sido dado de baja ";
        
      }else{
                respuesta = "El usuario no se ha dado de baja, no existe ese usuario";
             }
      con.close();
            
        }catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return respuesta;
    }
    
}
