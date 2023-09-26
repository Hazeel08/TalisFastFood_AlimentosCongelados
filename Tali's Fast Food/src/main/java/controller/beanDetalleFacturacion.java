/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import enums.ETipoPago;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import model.AutoRegistroInMemory;
import model.*;
import org.primefaces.PrimeFaces;

public class beanDetalleFacturacion {

    private int tipo_pago;
    private List<DetalleOrden> listaDetalleOrden;
    private List<Orden> listaOrden;

    private DetalleFactura detalleFactura;
    private Factura factura;

    private FacturaDB facturaDB;
    private DetalleFacturaDB detalleFacturaDB;
    private OrdenDB ordenDB;
    private DetalleOrdenDB detalleOrdenDB;
    private ProductoDB productoDB;
    private Producto producto;

    private Orden orden;

    public beanDetalleFacturacion() {
        listaDetalleOrden = AutoRegistroInMemory.detallesOrdenes;
        listaOrden = AutoRegistroInMemory.listaOrden;

        orden = AutoRegistroInMemory.getOrden();
        detalleFactura = new DetalleFactura();
        factura = new Factura();

        facturaDB = new FacturaDB();
        detalleFacturaDB = new DetalleFacturaDB();
        ordenDB = new OrdenDB();
        detalleOrdenDB = new DetalleOrdenDB();
        productoDB = new ProductoDB();
        producto = new Producto();
    }

    public int getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(int tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public List<DetalleOrden> getListaDetalleOrden() {
        return listaDetalleOrden;
    }

    public void setListaDetalleOrden(List<DetalleOrden> listaDetalleOrden) {
        this.listaDetalleOrden = listaDetalleOrden;
    }

    public List<Orden> getListaOrden() {
        return listaOrden;
    }

    public void setListaOrden(List<Orden> listaOrden) {
        this.listaOrden = listaOrden;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public float getCalculoIVA() {
        float total = 0f;

        for (DetalleOrden detalleOrden : listaDetalleOrden) {
            total += detalleOrden.getMonto_unidad() * factura.getIVA();
        }
        return total;
    }

    public String formatoIVA() {
        DecimalFormat formato = new DecimalFormat("#.00");

        return formato.format(getCalculoIVA());
    }

    public float getSubtotal() {
        float subtotal = 0f;

        for (DetalleOrden detalleOrden : listaDetalleOrden) {
            subtotal = detalleOrden.getMonto_unidad();
        }
        return subtotal;
    }

    public float getTotal() {
        float total = 0f;

        total = getSubtotal() + getCalculoIVA();

        return total;
    }

    public String finalizarCompra() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Pedido finalizado con éxito");
        try {
            guardarOrden();
            guardarDetalleOrden();
            guardarDetalleFactura();
            guardarFactura();
            AutoRegistroInMemory.detallesOrdenes.clear();
            AutoRegistroInMemory.listaOrden.clear();
            AutoRegistroInMemory.agregarOrden(new Orden());
            factura = new Factura();
            detalleFactura = new DetalleFactura();
            PrimeFaces.current().dialog().showMessageDynamic(mensaje);
            return "pedidoCliente.xhtml";
        } catch (Exception e) {
        }
        return "";
    }

    public void guardarOrden() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            ordenDB.insertarOrden(orden);
        } catch (Exception e) {
        }
    }

    public void guardarDetalleOrden() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        try {
            for (DetalleOrden detalleOrden : listaDetalleOrden) {
                detalleOrden.setOrden(ordenDB.obtenerOrdenPorCodUsuario(orden.getUsuario()));
                producto = detalleOrden.getProducto();
                this.producto.setCantidad_existencia(producto.getCantidad_existencia() - detalleOrden.getCantidad());
                this.producto.setCantidad_minima(producto.getCantidad_minima() - detalleOrden.getCantidad());
                this.productoDB.editarProducto(producto);
                detalleOrdenDB.insertarDetalleOrden(detalleOrden);
            }
        } catch (Exception e) {
        }
    }

    public void guardarFactura() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        float subTotal = getSubtotal();
        float total = getTotal();
        float IVA = getCalculoIVA();

        try {
            this.factura.setSubtotal(subTotal);
            this.factura.setPos_descuento(0);
            this.factura.setTotalIVA(IVA);
            this.factura.setTotal(total);

            for (ETipoPago eTipoPago : ETipoPago.values()) {
                if (eTipoPago.getCod_tipo_pago() == this.tipo_pago) {
                    this.factura.seteTipoPago(eTipoPago);
                }
            }

            this.factura.setOrden(ordenDB.obtenerOrdenPorCodUsuario(orden.getUsuario()));

            for (DetalleFactura detalleFactura : detalleFacturaDB.obtenerDetallesFacturas()) {
                this.factura.setDetalleFactura(detalleFacturaDB.obtenerOrdenPorId(detalleFactura.getCod_detalle_factura()));
            }

            facturaDB.insertFactura(factura);
        } catch (Exception e) {
        }
    }

    public void guardarDetalleFactura() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        float total = getTotal();
        float IVA = getCalculoIVA();

        try {
            this.detalleFactura.setImpuesto_cu(0f);
            this.detalleFactura.setMonto_descuento(0f);
            for (ETipoPago eTipoPago : ETipoPago.values()) {
                if (eTipoPago.getCod_tipo_pago() == this.tipo_pago) {
                    this.detalleFactura.seteTipoPago(eTipoPago);
                }
            }
            this.detalleFactura.setTotal_factura(total);
            this.detalleFactura.setTotal_descuento(0f);
            this.detalleFactura.setTotal_impuesto(IVA);

            detalleFacturaDB.insertDetalleFactura(detalleFactura);
        } catch (Exception e) {
        }

    }
}
