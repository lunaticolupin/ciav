/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinterno;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author EPA-PJ-009
 */
public class SecurityConfig {
    private static final Properties prop = new Properties();
    
    public static String getProperty(String property){
        String value="";
        
        try {
            InputStream input = ConversionClassUtil.class.getClassLoader().getResourceAsStream("resources/security.properties");
            if (input == null) {
                System.out.println("Sorry, unable to find security.properties");
                return null;
            }

            prop.load(input);

            value = prop.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return value;
        
    }
    
}
