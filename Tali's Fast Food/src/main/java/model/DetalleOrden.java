/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.SNMPExceptions;
import enums.EEstado;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class DetalleOrden {

    private int cod_orden;
    private Orden orden;
    private Producto producto;
    private int cantidad;
    private float monto_unidad;
    private EEstado log_activo;

    public DetalleOrden() {
        orden = new Orden();
        producto = new Producto();
    }

    public int getCod_orden() {
        return cod_orden;
    }

    public void setCod_orden(int cod_orden) {
        this.cod_orden = cod_orden;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getMonto_unidad() {
        return monto_unidad;
    }

    public void setMonto_unidad(float monto_unidad) {
        this.monto_unidad = monto_unidad;
    }

    public EEstado getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(EEstado log_activo) {
        this.log_activo = log_activo;
    }
}
