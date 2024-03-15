/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinterno;

import CONTROLADOR.CrudAudiencia;
import MODELO.AudienciaDAO;
import MODELO.CatalogoDAO;
import MODELO.Conexion;
import VISTA.NuevaAudiencia;
import VISTA.Registrar;
import java.util.Calendar;
//import org.jvnet.substance.SubstanceLookAndFeel;
import javax.swing.*;

/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class ControlInterno {
  public static Registrar reg; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (Exception e) {
		e.printStackTrace();
	}
      
                
        reg= new Registrar();
        AudienciaDAO adao =  new AudienciaDAO();
        CrudAudiencia ca = new CrudAudiencia(reg, adao);
        //ca.llenarCombo();
        
      //reg.setDefaultLookAndFeelDecorated(true);
     // SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistAquaSkin");
      
      reg.setVisible(true);
        //Calendar cal= Calendar.getInstance(); 
        //int yearDisco= cal.get(Calendar.YEAR); 
        //System.out.print("yearDisco.............."+ yearDisco);
       
      //NuevaAudiencia na = new NuevaAudiencia();
       //CatalogoDAO cd = new CatalogoDAO();
       
       //CrudCatalogo cc = new CrudCatalogo(na, cd);
       //na.setVisible(true);
 
    }
    
}
