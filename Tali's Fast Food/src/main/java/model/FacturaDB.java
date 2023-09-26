/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.ETipoPago;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author steve
 */
public class FacturaDB {

    private OrdenDB ordenDB;
    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public FacturaDB() {
        accesoDatos.setDbConn(con);
        ordenDB = new OrdenDB();
    }

    public void insertFactura(Factura pFactura) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO FACTURA(SUBTOTAL, POS_DESCUENTO, IVA, TOTAL, TIPO_PAGO, FECHA, COD_ORDEN, COD_DETALLE_FACTURA)"
                + "\nVALUES(" + pFactura.getSubtotal() + ", "
                + pFactura.getPos_descuento() + ", "
                + pFactura.getIVA() + ", "
                + pFactura.getTotal() + ", "
                + "'" + pFactura.geteTipoPago() + "', "
                + "'" + pFactura.getFechaSQL() + "', "
                + pFactura.getOrden().getCod_orden() + ", "
                + pFactura.getDetalleFactura().getCod_detalle_factura() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public List<Factura> obtenerFacturas() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM FACTURA;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Factura> listaFactura = new ArrayList<>();

        Factura factura = new Factura();
        while (rs.next()) {
            factura.setCod_factura(rs.getInt("COD_FACTURA"));
            factura.setSubtotal(rs.getFloat("SUBTOTAL"));
            factura.setPos_descuento(rs.getFloat("POS_DESCUENTO"));
            factura.setTotalIVA(rs.getFloat("IVA"));
            factura.setTotal(rs.getFloat("TOTAL"));

            for (ETipoPago eTipoPago : ETipoPago.values()) {
                if (eTipoPago.equals(rs.getString("Tipo_Pago"))) {
                    factura.seteTipoPago(eTipoPago);
                }
            }

            factura.setFechasql(rs.getDate("FECHA"));
            factura.setOrden(ordenDB.obtenerOrdenPorId(rs.getInt("COD_ORDEN")));

            listaFactura.add(factura);
        }
        return listaFactura;
    }

    public Factura obtenerFacturaPorOrden(Orden pOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return obtenerFacturas().stream().filter(o -> o.getOrden().getCod_orden() == pOrden.getCod_orden()).findFirst().orElse(null);
    }
}
