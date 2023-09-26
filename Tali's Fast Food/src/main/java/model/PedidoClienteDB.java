/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.EEstado;
import enums.ETipoPago;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author steve
 */
public class PedidoClienteDB {

    private ProductoDB productoDB;
    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public PedidoClienteDB() {
        accesoDatos.setDbConn(con);
        productoDB = new ProductoDB();
    }

    public List<PedidoCliente> obtenerPedidosCliente(Orden pOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT DETALLE_ORDEN.COD_PRODUCTO,DETALLE_ORDEN.CANTIDAD, FACTURA.TOTAL, ORDEN.FECHA_PEDIDO, DETALLE_ORDEN.LOG_ACTIVO\n"
                + "FROM DETALLE_ORDEN, FACTURA, ORDEN \n"
                + "WHERE DETALLE_ORDEN.COD_ORDEN = " + pOrden.getCod_orden() + " AND FACTURA.COD_ORDEN = " + pOrden.getCod_orden() + ";";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<PedidoCliente> listaPedido = new ArrayList<>();

        PedidoCliente pedidoCliente = new PedidoCliente();
        while (rs.next()) {
            pedidoCliente.setProducto(productoDB.obtenerProductoPorId(rs.getInt("COD_PRODUCTO")));
            pedidoCliente.setCantidad(rs.getInt("CANTIDAD"));
            pedidoCliente.setTotal(rs.getFloat("TOTAL"));
            pedidoCliente.setFecha_pedido(rs.getDate("FECHA_PEDIDO"));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.equals(rs.getString("LOG_ACTIVO"))) {
                    pedidoCliente.setLog_activo(eEstado);
                }
            }

            listaPedido.add(pedidoCliente);
        }
        return listaPedido;
    }
}
