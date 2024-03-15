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
public class Historial {
    
    String history;
    String disco;
    String causa;
    String fecha;
    String hora;
    String sala;
    String tipo;
    String estado;
    int consolidacion;

    public Historial() {
        history ="";
        disco ="";
        causa = "";
        fecha = "";
        hora = "";
        sala = "";
        tipo = "";
        estado = "";
        consolidacion=0;
    }
    
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getConsolidacion() {
        return consolidacion;
    }

    public void setConsolidacion(int consolidacion) {
        this.consolidacion = consolidacion;
    }
    
    
    
    
    
}
