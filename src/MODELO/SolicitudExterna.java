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
public class SolicitudExterna {
    int id;
    String fecha;
    String hora;
    int copias;
    String estado;
    String fecha_constancia;
    String fecha_entrega;
    String tipo;
    String solicitante;
    String amparo;
    String dependencia;
    String motivo;
    String acuerdo;
    

    public SolicitudExterna() {
        id = 0;
        fecha = "";
        hora = "";
        copias = 0;
        estado = "";
        fecha_constancia="";
        fecha_entrega =  "";
        tipo ="";
        solicitante = "";
        amparo = "";
        dependencia = "";
        motivo = "";
        acuerdo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_constancia() {
        return fecha_constancia;
    }

    public void setFecha_constancia(String fecha_constancia) {
        this.fecha_constancia = fecha_constancia;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getAmparo() {
        return amparo;
    }

    public void setAmparo(String amparo) {
        this.amparo = amparo;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }
    
    
    
    
    
    
    
}
