/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Marcos Luis Goxcon Portillo
 */
public class Consulta {
    String causa;
    String nombre;
    String tipo;
    int copias;
    int booleana;

    public Consulta() {
        causa = "";
        nombre = "";
        tipo = "";
        //booleana = 0;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBooleana() {
        return booleana;
    }

    public void setBooleana(int booleana) {
        this.booleana = booleana;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }
    
    
    
    
    
}
