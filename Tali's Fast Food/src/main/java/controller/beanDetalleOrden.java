/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import enums.EEstado;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;

public class beanDetalleOrden implements Serializable {

    public int contador;

    private Orden orden;
    private DetalleOrden detalleOrden;
    private Producto productoSeleccionado;

    private ProductoDB productoDB;

    List<Producto> filtradoListaProductos;

    public beanDetalleOrden() {
        orden = new Orden();
        detalleOrden = new DetalleOrden();
        productoSeleccionado = new Producto();

        productoDB = new ProductoDB();

        filtradoListaProductos = new ArrayList<>();
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Producto> getFiltradoListaProductos() {
        return filtradoListaProductos;
    }

    public void setFiltradoListaProductos(List<Producto> filtradoListaProductos) {
        this.filtradoListaProductos = filtradoListaProductos;
    }

    public boolean validaciones() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        FacesMessage excedeCantidad = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ha excedido la cantidad máxima en existencia del producto");
        FacesMessage listaVacia = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos un producto");

        Producto producto = productoDB.obtenerProductoPorId(productoSeleccionado.getCod_producto());

        if (detalleOrden.getCantidad() > producto.getCantidad_existencia()) {
            PrimeFaces.current().dialog().showMessageDynamic(excedeCantidad);
            detalleOrden.setCantidad(0);
            return true;
        }

        return false;

    }

    public void guardarDetalleCompra() {

        try {
            if (!validaciones()) {
                this.setContador(contador + 1);
                float total = calcularPrecio();
                this.detalleOrden.setOrden(AutoRegistroInMemory.getOrden());
                this.detalleOrden.setProducto(productoSeleccionado);
                this.detalleOrden.setMonto_unidad(total);
                this.detalleOrden.setLog_activo(EEstado.Pendiente);
                AutoRegistroInMemory.agregarDetalleOrden(detalleOrden);
                detalleOrden = new DetalleOrden();
            }
        } catch (Exception e) {
        }
    }

    public void eliminarDetalleCompra(DetalleOrden pDetalleOrden) {

        try {
            int num = this.getContador() - 1;
            this.setContador(num);
            AutoRegistroInMemory.eliminarDetalleOrden(pDetalleOrden);
            PrimeFaces.current().executeScript("PF('dialogoCarrito').show()");
        } catch (Exception e) {
        }
    }

    public float calcularPrecio() {
        float total = 0f;
        total = productoSeleccionado.getPrecio() * detalleOrden.getCantidad();
        return total;
    }

    public List<DetalleOrden> obtenerInformacionSeleccionada() {
        List<DetalleOrden> lista = new ArrayList<>();
        try {
            lista = AutoRegistroInMemory.detallesOrdenes;
        } catch (Exception e) {
        }
        return lista;
    }

    public String redirigirAFacturacion() {
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos 1 producto");
        if (!AutoRegistroInMemory.detallesOrdenes.isEmpty()) {
            return "detalleFacturacion.xhtml";
        }
        PrimeFaces.current().dialog().showMessageDynamic(error);
        return "";
    }
}
