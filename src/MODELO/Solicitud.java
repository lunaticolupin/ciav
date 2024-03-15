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
public class Solicitud {
    int id;
    String fecha;
    String hora;
    int copias;
    String estado;
    String fecha_constancia;
    String hora_constancia;
    String fecha_entrega;
    String hora_entrega;
    String participante;
    String tipoP;
    String tipoA;
    int disco;

    public Solicitud() {
        id = 0;
        fecha = "";
        hora = "";
        copias = 0;
        estado = "";
        fecha_constancia="";
        hora_constancia = "";
        fecha_entrega =  "";
        hora_entrega = "";
        participante = "";
        tipoP = "";
        disco  = 0;
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

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getTipoP() {
        return tipoP;
    }

    public void setTipoP(String tipoP) {
        this.tipoP = tipoP;
    }
    

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora_constancia() {
        return hora_constancia;
    }

    public void setHora_constancia(String hora_constancia) {
        this.hora_constancia = hora_constancia;
    }

    public String getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(String hora_entrega) {
        this.hora_entrega = hora_entrega;
    }
    
       public String gettipoA() {
        return tipoA;
    }

    public void settipoA(String tipoA) {
        this.tipoA = tipoA;
    }
    
    
    
}
