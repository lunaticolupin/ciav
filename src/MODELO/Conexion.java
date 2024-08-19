/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.util.Properties;
import pjpuebla.utils.ConfigDB;
import java.sql.SQLException;
/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class Conexion {   
    public Conexion(){

    }
    public Connection conectar(){
        Connection con = null;
        try {
            ConfigDB config = new ConfigDB();
            
            if (!config.OK){
                throw new SQLException("Falta paramétros. Revise el archivo config.properties");
            }
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(config.URL, config.USER, config.PASSWD);
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error "+  e + "\n\n El sistema se cerrará");
            System.exit(0);
            //JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor :( ","Error al conectar",JOptionPane.ERROR_MESSAGE);
            //System.out.print(e);
        }
        return con;
    }
    
}