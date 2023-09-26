/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class PedidoCliente {

    private Producto producto;
    private int cantidad;
    private float total;
    private Date fecha_pedido;
    private EEstado log_activo;

    public PedidoCliente() {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public EEstado getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(EEstado log_activo) {
        this.log_activo = log_activo;
    }

    public String getFormatoFechaYHora() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(this.fecha_pedido);
    }

}
