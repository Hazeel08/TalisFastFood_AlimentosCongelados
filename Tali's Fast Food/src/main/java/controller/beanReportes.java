/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import model.*;

public class beanReportes {

    private OrdenDB ordenDB;
    private DetalleOrdenDB detalleOrdenDB;
    private FacturaDB facturaDB;

    public beanReportes() {

        ordenDB = new OrdenDB();
        detalleOrdenDB = new DetalleOrdenDB();
        facturaDB = new FacturaDB();

    }

    public List<Reporte> getObtenerReportes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        List<Orden> listaOrdenes = new ArrayList<>();
        List<DetalleOrden> listaDetallesOrdenes = new ArrayList<>();
        List<Factura> listaFacturas = new ArrayList<>();
        List<Reporte> lista = new ArrayList<>();

        try {
            listaOrdenes = ordenDB.obtenerOrdenes();
            listaDetallesOrdenes = detalleOrdenDB.obtenerDetalleOrden();
            listaFacturas = facturaDB.obtenerFacturas();
            Reporte reporte = new Reporte();

            for (Orden orden : listaOrdenes) {
                for (DetalleOrden detalleOrden : listaDetallesOrdenes) {
                    for (Factura factura : listaFacturas) {
                        reporte.setOrden(orden);
                        reporte.setProducto(detalleOrden.getProducto());
                        reporte.setFactura(factura);
                        lista.add(reporte);
                    }
                }
            }

        } catch (Exception e) {
        }
        return lista;
    }

}
