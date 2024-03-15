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
public class Audiencia { // 11 atributos de audiencia
    String fecha;
    String hora;
    String sala;
    String ncausa;
    String nombre;
    String estado;
    String comentario;
    String numero;
    String anio;
    String consolidacion;
    String estadoR;
    String usuario;
     String tipo;
    public Audiencia(){
        fecha = "";
        hora ="";
        sala = "";
        ncausa = "";
        nombre = "";
        estado = "";
        comentario = "";
        numero = "";
        anio="";
        consolidacion = "";
        estadoR="";
        usuario="";
        tipo="";
    }
        public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getNcausa() {
        return ncausa;
    }

    public void setNcausa(String ncausa) {
        this.ncausa = ncausa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getConsolidacion() {
        return consolidacion;
    }

    public void setConsolidacion(String consolidacion) {
        this.consolidacion = consolidacion;
    }

    public String getEstadoR() {
        return estadoR;
    }

    public void setEstadoR(String estadoR) {
        this.estadoR = estadoR;
    }

    
    
    
}
