/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinterno;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 *
 * @author EPA-PJ-009
 */
public class ConfigDB {
    private final Properties prop = new Properties();
    public String URL;
    public String USER;
    public String PASSWD;
    public boolean OK=false;
    private String host;
    private String db;
    private String port;
    private String passwd;
    private final String url = "jdbc:mysql://%s:%s/%s?zeroDateTimeBehavior=convertToNull&useSSL=false";
    
    public ConfigDB (){
        getConfig();
    }
    
    private void getConfig(){
        try (InputStream input = new FileInputStream("config.properties")){
           
            if (input==null){
                createConfig();
                return;
            }
            
            prop.load(input);
            
            OK=validar();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            createConfig();
        }
    }
    
    private String getPassword(String passwordEncrypt){       
        try{
            String secretPassword = SecurityConfig.getProperty("key.password");
            String secretSalt = SecurityConfig.getProperty("key.salt");
            SecretKey key = ConversionClassUtil.getKeyFromPassword(secretPassword, secretSalt);

            return ConversionClassUtil.decrypt(passwordEncrypt, key);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error "+  e);
            return null;
        }
    }
    
    private void createConfig(){
        try{
            Scanner sc = new Scanner(System.in);
            OutputStream output = new FileOutputStream("config.properties");

            System.out.print("Host: ");
            host = sc.nextLine();
            
            System.out.print("Puerto: ");
            port = sc.nextLine();
            
            System.out.print("Base de datos: ");
            db = sc.nextLine();
            
            System.out.print("Usuario: ");
            USER = sc.nextLine();
            
            System.out.print("Password: ");
            passwd = sc.nextLine();

            if (host.isEmpty()||port.isEmpty()||db.isEmpty()||USER.isEmpty()||passwd.isEmpty()){
                System.out.println("No se puede continuar");
                System.exit(0);
            }
            
            PASSWD = setPassword(passwd);

            prop.setProperty("db.host", host);
            prop.setProperty("db.port", port);
            prop.setProperty("db.database", db);
            prop.setProperty("db.user", USER);
            prop.setProperty("db.password", PASSWD);
            
            prop.store(output, null);
            
            OK = validar();
            
            if (!OK){
                System.out.println("Faltan datos");
            }
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            // System.exit(0);
        }
    }
    
     private String setPassword(String password){       
        try{
            String secretPassword = SecurityConfig.getProperty("key.password");
            String secretSalt = SecurityConfig.getProperty("key.salt");
            SecretKey key = ConversionClassUtil.getKeyFromPassword(secretPassword, secretSalt);

            return ConversionClassUtil.encrypt(password, key);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error "+  e);
            return null;
        }
    }
     
     private boolean validar(){
         
        host = prop.getProperty("db.host");
        port = prop.getProperty("db.port");
        db = prop.getProperty("db.database");
        passwd = prop.getProperty("db.password");
        
        if (host.isEmpty()||port.isEmpty()||db.isEmpty()||passwd.isEmpty()){
            return false;
        }

        URL = String.format(url, host, port, db);
        USER = prop.getProperty("db.user");
        PASSWD = getPassword(passwd);

        return !(URL.isEmpty()||USER.isEmpty()||PASSWD.isEmpty());
     }
    
}
