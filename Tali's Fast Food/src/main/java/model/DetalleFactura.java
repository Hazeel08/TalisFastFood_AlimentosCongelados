/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ETipoPago;

/**
 *
 * @author steve
 */
public class DetalleFactura {

    private int cod_detalle_factura;
    private float impuesto_cu;
    private float monto_descuento;
    private ETipoPago eTipoPago;
    private float total_factura;
    private float total_descuento;
    private float total_impuesto;

    public DetalleFactura() {
    }

    public int getCod_detalle_factura() {
        return cod_detalle_factura;
    }

    public void setCod_detalle_factura(int cod_detalle_factura) {
        this.cod_detalle_factura = cod_detalle_factura;
    }

    public float getImpuesto_cu() {
        return impuesto_cu;
    }

    public void setImpuesto_cu(float impuesto_cu) {
        this.impuesto_cu = impuesto_cu;
    }

    public float getMonto_descuento() {
        return monto_descuento;
    }

    public void setMonto_descuento(float monto_descuento) {
        this.monto_descuento = monto_descuento;
    }

    public ETipoPago geteTipoPago() {
        return eTipoPago;
    }

    public void seteTipoPago(ETipoPago eTipoPago) {
        this.eTipoPago = eTipoPago;
    }

    public float getTotal_factura() {
        return total_factura;
    }

    public void setTotal_factura(float total_factura) {
        this.total_factura = total_factura;
    }

    public float getTotal_descuento() {
        return total_descuento;
    }

    public void setTotal_descuento(float total_descuento) {
        this.total_descuento = total_descuento;
    }

    public float getTotal_impuesto() {
        return total_impuesto;
    }

    public void setTotal_impuesto(float total_impuesto) {
        this.total_impuesto = total_impuesto;
    }

}
