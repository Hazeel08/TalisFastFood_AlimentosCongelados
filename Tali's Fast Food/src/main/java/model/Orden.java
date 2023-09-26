/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.EEstado;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Orden {

    private int cod_orden;
    private Usuario usuario;
    private Direccion direccion;
    private Horario horario;
    private EEstado log_activo;
    private LocalDate fecha_pedido;
    private Date fecha_bd;

    public Orden() {
        usuario = new Usuario();
        direccion = new Direccion();
        horario = new Horario();
    }

    public int getCod_orden() {
        return cod_orden;
    }

    public void setCod_orden(int cod_orden) {
        this.cod_orden = cod_orden;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public EEstado getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(EEstado log_activo) {
        this.log_activo = log_activo;
    }

    public LocalDate getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDate fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Date getFecha_bd() {
        return fecha_bd;
    }

    public void setFecha_bd(Date fecha_bd) {
        this.fecha_bd = fecha_bd;
    }

    public java.sql.Date conversionASQLFecha() {
        Date date = Date.valueOf(fecha_pedido.now());
        return date;
    }

    public String getFormatoFechaYHora() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(fecha_bd);
    }
}
