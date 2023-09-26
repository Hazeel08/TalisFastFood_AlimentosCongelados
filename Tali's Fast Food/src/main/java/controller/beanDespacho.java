/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import enums.EEstado;
import enums.ETipoEntrega;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;
import util.*;

/**
 *
 * @author steve
 */
public class beanDespacho {

    private int tipo_envio;
    private Despacho oDespachos;
    private OrdenFactura ordenSeleccionada;
    private Orden orden;
    private DespachoDB oDespachoDB;
    private OrdenDB ordenDB;
    private DetalleOrdenDB detalleOrdenDB;
    private FacturaDB facturaDB;
    private DespachoDB despachoDB;

    public beanDespacho() {

        ordenSeleccionada = new OrdenFactura();
        orden = new Orden();

        oDespachos = new Despacho();
        oDespachoDB = new DespachoDB();
        ordenDB = new OrdenDB();
        detalleOrdenDB = new DetalleOrdenDB();
        facturaDB = new FacturaDB();
        despachoDB = new DespachoDB();
    }

    public int getTipo_envio() {
        return tipo_envio;
    }

    public void setTipo_envio(int tipo_envio) {
        this.tipo_envio = tipo_envio;
    }

    public OrdenFactura getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(OrdenFactura ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public Despacho getDespachos() {
        return oDespachos;
    }

    public void setDespachos(Despacho oDespachos) {
        this.oDespachos = oDespachos;
    }

    public List<OrdenFactura> getListadoOrdenesPendientes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        List<Orden> listaOrdenes = new ArrayList<>();
        List<Factura> listaFacturas = new ArrayList<>();
        List<DetalleOrden> listaDetalleOrdenes = new ArrayList<>();
        List<OrdenFactura> listaOrdenFacturas = new ArrayList<>();
        OrdenFactura ordenFactura = new OrdenFactura();
        try {
            listaOrdenes = ordenDB.obtenerOrdenesPendientes();
            listaFacturas = facturaDB.obtenerFacturas();
            listaDetalleOrdenes = detalleOrdenDB.obtenerDetalleOrdenPendientes();
            for (Orden orden : listaOrdenes) {
                for (Factura factura : listaFacturas) {
                    for (DetalleOrden detalleOrden : listaDetalleOrdenes) {
                        ordenFactura.setOrden(orden);
                        ordenFactura.setFactura(factura);
                        ordenFactura.setDetalleOrden(detalleOrden);
                        ordenFactura.setProducto(detalleOrden.getProducto());
                        listaOrdenFacturas.add(ordenFactura);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listaOrdenFacturas;
    }

    public List<Despacho> getObtenerDespachos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        List<Despacho> listaDespachos = new ArrayList<>();
        listaDespachos = despachoDB.listaDesapachos();
        return listaDespachos;
    }

    public void guardarDespacho() {
        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Despacho agregado exitosamente.");

            for (ETipoEntrega eTipoEntrega : ETipoEntrega.values()) {
                if (eTipoEntrega.getId_entrega() == this.tipo_envio) {
                    this.oDespachos.setTipoEntrega(eTipoEntrega);
                }
            }

            this.oDespachos.setEstado(EEstado.Activo);
            this.oDespachos.setOrden(ordenSeleccionada.getOrden());
            orden = ordenSeleccionada.getOrden();
            orden.setLog_activo(EEstado.Activo);
            ordenDB.editarOrden(orden);
            oDespachoDB.insertarDespacho(oDespachos);

            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (Exception ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public void modificarDespacho() {
        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Despacho modificado exitosamente.");

            oDespachoDB.modificarDespacho(oDespachos);

            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (Exception ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public void estadoDespacho() {
        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Despacho finalizado exitosamente.");

            oDespachoDB.estadoDespachos(oDespachos);

            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (Exception ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }
}
