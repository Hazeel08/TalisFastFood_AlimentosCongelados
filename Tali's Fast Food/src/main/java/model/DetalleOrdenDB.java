/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.EEstado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class DetalleOrdenDB {

    Connection conn;
    AccesoDatos accesoDatos;

    OrdenDB ordenDB;
    ProductoDB productoDB;

    public DetalleOrdenDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

        ordenDB = new OrdenDB();
        productoDB = new ProductoDB();
    }

    public void insertarDetalleOrden(DetalleOrden pDetalleOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO DETALLE_ORDEN(COD_ORDEN, COD_PRODUCTO, CANTIDAD, MONTO_UNIDAD, LOG_ACTIVO)"
                + "VALUES(" + pDetalleOrden.getOrden().getCod_orden()
                + ", " + pDetalleOrden.getProducto().getCod_producto() + ", "
                + pDetalleOrden.getCantidad() + ", " + pDetalleOrden.getMonto_unidad()
                + ", " + pDetalleOrden.getLog_activo().getCod_estado() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public List<DetalleOrden> obtenerDetalleOrdenPendientes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM DETALLE_ORDEN WHERE LOG_ACTIVO = " + EEstado.Pendiente.getCod_estado();

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<DetalleOrden> listaDetalleOrden = new ArrayList<>();

        while (rs.next()) {
            DetalleOrden detalleOrden = new DetalleOrden();
            detalleOrden.setCod_orden(rs.getInt("COD_DETALLE"));
            detalleOrden.setOrden(ordenDB.obtenerOrdenPorId(rs.getInt("COD_ORDEN")));
            detalleOrden.setProducto(productoDB.obtenerProductoPorId(rs.getInt("COD_PRODUCTO")));
            detalleOrden.setCantidad(rs.getInt("CANTIDAD"));
            detalleOrden.setMonto_unidad(rs.getInt("MONTO_UNIDAD"));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt(rs.getInt("LOG_ACTIVO"))) {
                    detalleOrden.setLog_activo(eEstado);
                }
            }
            listaDetalleOrden.add(detalleOrden);
        }
        return listaDetalleOrden;
    }

    public List<DetalleOrden> obtenerDetalleOrdenActivos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM DETALLE_ORDEN WHERE LOG_ACTIVO = " + EEstado.Activo.getCod_estado();

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<DetalleOrden> listaDetalleOrden = new ArrayList<>();

        while (rs.next()) {
            DetalleOrden detalleOrden = new DetalleOrden();
            detalleOrden.setCod_orden(rs.getInt("COD_DETALLE"));
            detalleOrden.setOrden(ordenDB.obtenerOrdenPorId(rs.getInt("COD_ORDEN")));
            detalleOrden.setProducto(productoDB.obtenerProductoPorId(rs.getInt("COD_PRODUCTO")));
            detalleOrden.setCantidad(rs.getInt("CANTIDAD"));
            detalleOrden.setMonto_unidad(rs.getInt("MONTO_UNIDAD"));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt(rs.getInt("LOG_ACTIVO"))) {
                    detalleOrden.setLog_activo(eEstado);
                }
            }
            listaDetalleOrden.add(detalleOrden);
        }
        return listaDetalleOrden;
    }

    public List<DetalleOrden> obtenerDetalleOrden() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM DETALLE_ORDEN;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<DetalleOrden> listaDetalleOrden = new ArrayList<>();

        while (rs.next()) {
            DetalleOrden detalleOrden = new DetalleOrden();
            detalleOrden.setCod_orden(rs.getInt("COD_DETALLE"));
            detalleOrden.setOrden(ordenDB.obtenerOrdenPorId(rs.getInt("COD_ORDEN")));
            detalleOrden.setProducto(productoDB.obtenerProductoPorId(rs.getInt("COD_PRODUCTO")));
            detalleOrden.setCantidad(rs.getInt("CANTIDAD"));
            detalleOrden.setMonto_unidad(rs.getInt("MONTO_UNIDAD"));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt(rs.getInt("LOG_ACTIVO"))) {
                    detalleOrden.setLog_activo(eEstado);
                }
            }
            listaDetalleOrden.add(detalleOrden);
        }
        return listaDetalleOrden;
    }

    public DetalleOrden obtenerDetallePorCodigoDetalle(Orden pOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return obtenerDetalleOrdenPendientes().stream().filter(o -> o.getOrden().getCod_orden() == pOrden.getCod_orden()).findFirst().orElse(null);
    }
}
