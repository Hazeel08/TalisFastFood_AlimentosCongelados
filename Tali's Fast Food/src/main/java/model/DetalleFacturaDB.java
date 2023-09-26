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
public class DetalleFacturaDB {

    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public DetalleFacturaDB() {
        accesoDatos.setDbConn(con);
    }

    public void insertDetalleFactura(DetalleFactura pDetalleFactura) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO DETALLE_FACTURA(IMPUESTO_CU, MONTO_DESCUENTO, TIPO_PAGO, TOTAL_FACTURA , TOTAL_DESCUENTO, TOTAL_IMPUESTO)"
                + "\nVALUES(" + pDetalleFactura.getImpuesto_cu() + ", "
                + pDetalleFactura.getMonto_descuento()
                + ", '" + pDetalleFactura.geteTipoPago() + "', "
                + pDetalleFactura.getTotal_factura()
                + ", " + pDetalleFactura.getTotal_descuento() + ", "
                + pDetalleFactura.getTotal_impuesto() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public List<DetalleFactura> obtenerDetallesFacturas() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM DETALLE_FACTURA;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<DetalleFactura> listaDetalleFactura = new ArrayList<>();

        DetalleFactura detalleFactura = new DetalleFactura();
        while (rs.next()) {
            detalleFactura.setCod_detalle_factura(rs.getInt("COD_DETALLE_FACTURA"));
            detalleFactura.setImpuesto_cu(rs.getFloat("IMPUESTO_CU"));
            detalleFactura.setMonto_descuento(rs.getFloat("MONTO_DESCUENTO"));

            for (ETipoPago eTipoPago : ETipoPago.values()) {
                if (eTipoPago.equals(rs.getString("TIPO_PAGO"))) {
                    detalleFactura.seteTipoPago(eTipoPago);
                }
            }

            detalleFactura.setTotal_factura(rs.getFloat("TOTAL_FACTURA"));
            detalleFactura.setTotal_descuento(rs.getFloat("TOTAL_DESCUENTO"));
            detalleFactura.setTotal_impuesto(rs.getFloat("TOTAL_IMPUESTO"));

            listaDetalleFactura.add(detalleFactura);
        }
        return listaDetalleFactura;
    }

    public DetalleFactura obtenerOrdenPorId(int cod_detalle_orden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return obtenerDetallesFacturas().stream().filter(d -> d.getCod_detalle_factura() == cod_detalle_orden).findFirst().orElse(null);
    }
}
