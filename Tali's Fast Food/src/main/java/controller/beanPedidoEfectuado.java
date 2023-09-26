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
import util.CommonStrings;
import util.SessionHelper;

public class beanPedidoEfectuado {

    private Orden orden;
    private DetalleOrden detalleOrden;
    private Usuario usuarioLogueado;

    private OrdenDB ordenDB;
    private DetalleOrdenDB detalleOrdenDB;
    private FacturaDB facturaDB;
    private PedidoClienteDB pedidoClienteDB;
    private ProductoDB productoDB;

    public beanPedidoEfectuado() {
        orden = new Orden();
        detalleOrden = new DetalleOrden();

        ordenDB = new OrdenDB();
        detalleOrdenDB = new DetalleOrdenDB();
        pedidoClienteDB = new PedidoClienteDB();
        facturaDB = new FacturaDB();
        productoDB = new ProductoDB();
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Usuario obtenerUsuarioLogueado() {
        this.usuarioLogueado = (Usuario) SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO);
        this.setUsuarioLogueado(usuarioLogueado);
        return usuarioLogueado;
    }

    public Orden obtenerOrden() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            orden = ordenDB.obtenerOrdenPorCodUsuario(obtenerUsuarioLogueado());
        } catch (Exception e) {
        }
        return orden;
    }

    public List<PedidoCliente> getObtenerPedidos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        List<PedidoCliente> lista = new ArrayList<>();
        PedidoCliente pedidoCliente = new PedidoCliente();

        Factura factura = facturaDB.obtenerFacturaPorOrden(obtenerOrden());
        DetalleOrden detalleOrden = detalleOrdenDB.obtenerDetallePorCodigoDetalle(obtenerOrden());

        try {
            pedidoCliente.setProducto(productoDB.obtenerProductoPorId(detalleOrden.getProducto().getCod_producto()));
            pedidoCliente.setCantidad(detalleOrden.getCantidad());
            pedidoCliente.setTotal(factura.getTotal());
            pedidoCliente.setFecha_pedido(detalleOrden.getOrden().getFecha_bd());
            pedidoCliente.setLog_activo(detalleOrden.getOrden().getLog_activo());
            lista.add(pedidoCliente);
        } catch (Exception e) {
        }

        return lista;
    }

}
