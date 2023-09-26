/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.*;
import java.time.LocalDate;
import java.util.Date;

public class Despacho {

    private int idDespacho;
    private Date fechaEntrega;
    private Date horaEntrega;
    private String observacion;
    private float costo;
    private EEstado estado;
    private ETipoEntrega tipoEntrega;
    private Orden orden;

    public Despacho() {
        orden = new Orden();
    }

    public int getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(int idDespacho) {
        this.idDespacho = idDespacho;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public EEstado getEstado() {
        return estado;
    }

    public void setEstado(EEstado estado) {
        this.estado = estado;
    }

    public ETipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(ETipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public java.sql.Date getFechaEnvio() {
        LocalDate localDate = LocalDate.now();

        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        return sqlDate;
    }

    public java.sql.Date getHoraEnvio() {
        LocalDate localDate = LocalDate.now();

        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        return sqlDate;
    }
}
