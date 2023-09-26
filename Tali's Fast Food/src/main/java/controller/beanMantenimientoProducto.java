/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;
import util.CommonStrings;
import util.MessageHelper;

public class beanMantenimientoProducto {

    private Producto producto;
    private ProductoDB productoDB;
    private List<Producto> listaProducto;
    private List<Producto> listaProductoInactivos;
    private List<Producto> filtradoListaProductos;

    public beanMantenimientoProducto() {
        producto = new Producto();
        productoDB = new ProductoDB();
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoDB getProductoDB() {
        return productoDB;
    }

    public void setProductoDB(ProductoDB productoDB) {
        this.productoDB = productoDB;
    }

    public List<Producto> getFiltradoListaProductos() {
        return filtradoListaProductos;
    }

    public void setFiltradoListaProductos(List<Producto> filtradoListaProductos) {
        this.filtradoListaProductos = filtradoListaProductos;
    }

    public void limpiarProducto() {
        producto = new Producto();
    }

    public boolean hayNulos() {
        if (producto.getNombre().isEmpty() || producto.getUrl_fotografia().isEmpty() || producto.getPrecio() == 0) {
            return true;
        }
        return false;
    }

    public void agregarProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Producto guardado exitosamente");
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hay campos nulos");
        try {
            if (!hayNulos()) {
                productoDB.insertarProducto(producto);
                limpiarProducto();
                PrimeFaces.current().dialog().showMessageDynamic(message);
            } else {
                PrimeFaces.current().executeScript("PF('dialogoAgregar').show()");
                PrimeFaces.current().dialog().showMessageDynamic(error);
            }
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public void modificarProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            productoDB.editarProducto(producto);
            PrimeFaces.current().ajax().update(":formListado:tablaProductos");
            limpiarProducto();
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public void eliminarProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            productoDB.eliminarProducto(producto);
            limpiarProducto();
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public List<Producto> getListaProductos() {
        try {
            listaProducto = productoDB.getProductos();
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
        return listaProducto;
    }

    public void activarProducto() {
        try {
            productoDB.activarProducto(producto);
            PrimeFaces.current().ajax().update(":formListado:tablaProductos");
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public List<Producto> getListaProductosInactivos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            listaProductoInactivos = productoDB.getProductosInactivos();
        } catch (SNMPExceptions | SQLException | NamingException | ClassNotFoundException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
        return listaProductoInactivos;
    }
}
