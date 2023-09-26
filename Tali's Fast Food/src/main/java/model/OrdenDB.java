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

/**
 *
 * @author steve
 */
public class OrdenDB {

    UsuarioDB usuarioDB;
    DireccionDB direccionDB;
    HorarioDB horarioDB;
    Connection conn;
    AccesoDatos accesoDatos;

    public OrdenDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        usuarioDB = new UsuarioDB();
        direccionDB = new DireccionDB();
        horarioDB = new HorarioDB();
    }

    public void insertarOrden(Orden pOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO ORDEN (COD_USUARIO, COD_DIRECCION, COD_HORARIO, LOG_ACTIVO, FECHA_PEDIDO)"
                + "\nVALUES('" + pOrden.getUsuario().getCedula() + "', " + pOrden.getDireccion().getCodigo() + ", "
                + pOrden.getHorario().getCod_horario() + ", " + pOrden.getLog_activo().getCod_estado() + ", "
                + "'" + pOrden.conversionASQLFecha() + "');";

        accesoDatos.ejecutaSQL(select);
    }

    public void editarOrden(Orden pOrden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE ORDEN"
                + "\nSET LOG_ACTIVO = " + pOrden.getLog_activo().getCod_estado()
                + "\nWHERE COD_ORDEN = " + pOrden.getCod_orden() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public Orden obtenerOrdenPorCodUsuario(Usuario pUsuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM ORDEN WHERE LOG_ACTIVO = " + EEstado.Pendiente.getCod_estado()
                + "\nAND COD_USUARIO = " + "'" + pUsuario.getCedula() + "';";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);

        Orden orden = new Orden();
        while (rs.next()) {
            orden.setCod_orden(rs.getInt("COD_ORDEN"));
            orden.setUsuario(usuarioDB.getUsuarioPorId(rs.getString("COD_USUARIO")));
            orden.setDireccion(direccionDB.obtenerDireccionPorId(rs.getInt("COD_DIRECCION")));
            orden.setHorario(horarioDB.obtenerHorarioPorId(rs.getInt("COD_HORARIO")));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("LOG_ACTIVO")) {
                    orden.setLog_activo(eEstado);
                }
            }

            orden.setFecha_bd(rs.getDate("FECHA_PEDIDO"));

            return orden;
        }
        return null;
    }

    public Orden obtenerOrdenPorId(int cod_orden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM ORDEN WHERE LOG_ACTIVO = " + EEstado.Pendiente.getCod_estado()
                + "\nAND COD_ORDEN = " + cod_orden + ";";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);

        Orden orden = new Orden();
        while (rs.next()) {
            orden.setCod_orden(rs.getInt("COD_ORDEN"));
            orden.setUsuario(usuarioDB.getUsuarioPorId(rs.getString("COD_USUARIO")));
            orden.setDireccion(direccionDB.obtenerDireccionPorId(rs.getInt("COD_DIRECCION")));
            orden.setHorario(horarioDB.obtenerHorarioPorId(rs.getInt("COD_HORARIO")));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("LOG_ACTIVO")) {
                    orden.setLog_activo(eEstado);
                }
            }

            orden.setFecha_bd(rs.getDate("FECHA_PEDIDO"));

            return orden;
        }
        return null;
    }

    public List<Orden> obtenerOrdenesPendientes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM ORDEN WHERE LOG_ACTIVO = " + EEstado.Pendiente.getCod_estado() + ";";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Orden> listaOrdenes = new ArrayList<>();

        Orden orden = new Orden();
        while (rs.next()) {
            orden.setCod_orden(rs.getInt("COD_ORDEN"));
            orden.setUsuario(usuarioDB.getUsuarioPorId(rs.getString("COD_USUARIO")));
            orden.setDireccion(direccionDB.obtenerDireccionPorId(rs.getInt("COD_DIRECCION")));
            orden.setHorario(horarioDB.obtenerHorarioPorId(rs.getInt("COD_HORARIO")));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("LOG_ACTIVO")) {
                    orden.setLog_activo(eEstado);
                }
            }

            orden.setFecha_bd(rs.getDate("FECHA_PEDIDO"));

            listaOrdenes.add(orden);
        }
        return listaOrdenes;
    }

    public List<Orden> obtenerOrdenesActivas() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM ORDEN WHERE LOG_ACTIVO = " + EEstado.Activo.getCod_estado() + ";";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Orden> listaOrdenes = new ArrayList<>();

        Orden orden = new Orden();
        while (rs.next()) {
            orden.setCod_orden(rs.getInt("COD_ORDEN"));
            orden.setUsuario(usuarioDB.getUsuarioPorId(rs.getString("COD_USUARIO")));
            orden.setDireccion(direccionDB.obtenerDireccionPorId(rs.getInt("COD_DIRECCION")));
            orden.setHorario(horarioDB.obtenerHorarioPorId(rs.getInt("COD_HORARIO")));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("LOG_ACTIVO")) {
                    orden.setLog_activo(eEstado);
                }
            }

            orden.setFecha_bd(rs.getDate("FECHA_PEDIDO"));

            listaOrdenes.add(orden);
        }
        return listaOrdenes;
    }

    public List<Orden> obtenerOrdenes() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM ORDEN;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Orden> listaOrdenes = new ArrayList<>();

        Orden orden = new Orden();
        while (rs.next()) {
            orden.setCod_orden(rs.getInt("COD_ORDEN"));
            orden.setUsuario(usuarioDB.getUsuarioPorId(rs.getString("COD_USUARIO")));
            orden.setDireccion(direccionDB.obtenerDireccionPorId(rs.getInt("COD_DIRECCION")));
            orden.setHorario(horarioDB.obtenerHorarioPorId(rs.getInt("COD_HORARIO")));

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("LOG_ACTIVO")) {
                    orden.setLog_activo(eEstado);
                }
            }

            orden.setFecha_bd(rs.getDate("FECHA_PEDIDO"));

            listaOrdenes.add(orden);
        }
        return listaOrdenes;
    }

    public Orden obtenerOrdenPorID(int codigo_orden) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return obtenerOrdenesActivas().stream().filter(o -> o.getCod_orden() == codigo_orden).findFirst().orElse(null);
    }
}
