/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.*;
import java.time.LocalDate;
import java.sql.Date;

public class Factura {

    private int cod_factura;
    private float subtotal;
    private float pos_descuento;
    private final float IVA = 0.15f;
    private float totalIVA;
    private float total;
    private ETipoPago eTipoPago;
    private LocalDate fecha;
    private Date fechasql;
    private Orden orden;
    private DetalleFactura detalleFactura;
    private String descTipoPago;

    public Factura() {
        orden = new Orden();
        detalleFactura = new DetalleFactura();
    }

    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getPos_descuento() {
        return pos_descuento;
    }

    public void setPos_descuento(float pos_descuento) {
        this.pos_descuento = pos_descuento;
    }

    public float getIVA() {
        return IVA;
    }

    public float getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(float totalIVA) {
        this.totalIVA = totalIVA;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ETipoPago geteTipoPago() {
        return eTipoPago;
    }

    public void seteTipoPago(ETipoPago eTipoPago) {
        this.eTipoPago = eTipoPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Date getFechasql() {
        return fechasql;
    }

    public void setFechasql(Date fechasql) {
        this.fechasql = fechasql;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public String getDescTipoPago() {
        return descTipoPago;
    }

    public void setDescTipoPago(String descTipoPago) {
        this.descTipoPago = descTipoPago;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public void toStringTipoPago() {
        if (this.geteTipoPago().getCod_tipo_pago() == 1) {
            this.setDescTipoPago("Tarjeta");
        }
        if (this.geteTipoPago().getCod_tipo_pago() == 2) {
            this.setDescTipoPago("Efectivo");
        }
        if (this.geteTipoPago().getCod_tipo_pago() == 3) {
            this.setDescTipoPago("Cuenta por Cobrar");
        }
    }

    public Date getFechaSQL() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        return date;
    }
}
