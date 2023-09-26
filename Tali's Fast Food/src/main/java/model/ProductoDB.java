/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductoDB {

    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public ProductoDB(Connection con) {
        accesoDatos.setDbConn(con);
    }

    public ProductoDB() {
        super();
    }

    public void insertarProducto(Producto pProducto) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO PRODUCTO(FOTOGRAFIA, NOMBRE, DSC_PRODUCTO, CANTIDAD_MINIMA, CANTIDAD_EXISTENCIA, LOG_ACTIVO, PRECIO)"
                + "VALUES('" + pProducto.getUrl_fotografia() + "', " + "'" + pProducto.getNombre() + "',"
                + "'" + pProducto.getDsc_producto() + "'," + pProducto.getCantidad_existencia() + ","
                + pProducto.getCantidad_existencia() + "," + pProducto.getLog_activo() + "," + pProducto.getPrecio() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public void editarProducto(Producto pProducto) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE PRODUCTO\n"
                + "SET FOTOGRAFIA = " + "'" + pProducto.getUrl_fotografia() + "',\n"
                + "NOMBRE = " + "'" + pProducto.getNombre() + "',\n"
                + "DSC_PRODUCTO = " + "'" + pProducto.getDsc_producto() + "',\n"
                + "CANTIDAD_MINIMA = " + pProducto.getCantidad_existencia() + ",\n"
                + "CANTIDAD_EXISTENCIA = " + pProducto.getCantidad_existencia() + ",\n"
                + "LOG_ACTIVO = " + pProducto.getLog_activo() + ",\n"
                + "PRECIO = " + pProducto.getPrecio() + "\n"
                + "WHERE COD_PRODUCTO = " + pProducto.getCod_producto() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public void eliminarProducto(Producto pProducto) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "DELETE FROM PRODUCTO\n"
                + "WHERE COD_PRODUCTO = " + pProducto.getCod_producto() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public List<Producto> getProductos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM PRODUCTO WHERE LOG_ACTIVO = 1 AND CANTIDAD_EXISTENCIA > 0";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Producto> listaProducto = new ArrayList<>();

        while (rs.next()) {
            Producto oProducto = new Producto();
            oProducto.setCod_producto(rs.getInt("COD_PRODUCTO"));
            oProducto.setUrl_fotografia(rs.getString("FOTOGRAFIA"));
            oProducto.setNombre(rs.getString("NOMBRE"));
            oProducto.setDsc_producto(rs.getString("DSC_PRODUCTO"));
            oProducto.setCantidad_minima(rs.getInt("CANTIDAD_MINIMA"));
            oProducto.setCantidad_existencia(rs.getInt("CANTIDAD_EXISTENCIA"));
            oProducto.setLog_activo(rs.getInt("LOG_ACTIVO"));
            oProducto.setPrecio(rs.getFloat("PRECIO"));
            listaProducto.add(oProducto);
        }
        return listaProducto;
    }

    public void activarProducto(Producto pProducto) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE PRODUCTO\n"
                + "SET LOG_ACTIVO = " + 1
                + "WHERE COD_PRODUCTO = " + pProducto.getCod_producto() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public List<Producto> getProductosInactivos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM PRODUCTO WHERE LOG_ACTIVO = 0 AND CANTIDAD_EXISTENCIA = 0";
        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Producto> listaProducto = new ArrayList<>();

        while (rs.next()) {
            Producto oProducto = new Producto();
            oProducto.setCod_producto(rs.getInt("COD_PRODUCTO"));
            oProducto.setUrl_fotografia(rs.getString("FOTOGRAFIA"));
            oProducto.setNombre(rs.getString("NOMBRE"));
            oProducto.setDsc_producto(rs.getString("DSC_PRODUCTO"));
            oProducto.setCantidad_minima(rs.getInt("CANTIDAD_MINIMA"));
            oProducto.setCantidad_existencia(rs.getInt("CANTIDAD_EXISTENCIA"));
            oProducto.setLog_activo(rs.getInt("LOG_ACTIVO"));
            oProducto.setPrecio(rs.getFloat("PRECIO"));
            listaProducto.add(oProducto);
        }
        return listaProducto;
    }

    public Producto obtenerProductoPorId(int cod_producto) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return this.getProductos().stream().filter(p -> p.getCod_producto() == cod_producto).findFirst().orElse(null);
    }
}
