/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.EEstado;
import enums.ETipoEntrega;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author hcald
 */
public class DespachoDB {

    Connection conn;
    AccesoDatos accesoDatos;
    OrdenDB ordenDB;

    public DespachoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        ordenDB = new OrdenDB();
    }

    public void insertarDespacho(Despacho pDespacho) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO DESPACHO(FECHA_ENVIO, HORA_ENVIO, OBSERVACION, LOG_ACTIVO, COSTO, COD_ENTREGA, COD_ORDEN)"
                + "VALUES('" + pDespacho.getFechaEnvio() + "', " //Hacer la conversión en Despachos
                + "'" + pDespacho.getHoraEnvio() + "', " //Hacer la conversión en Despachos
                + "'" + pDespacho.getObservacion() + "', "
                + pDespacho.getEstado().getCod_estado() + ", "
                + pDespacho.getCosto() + ", "
                + pDespacho.getTipoEntrega().getId_entrega() + ", "
                + pDespacho.getOrden().getCod_orden() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public void modificarDespacho(Despacho pDespacho) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE DESPACHO"
                + "\nSET FECHA_ENVIO = " + pDespacho.getFechaEntrega()
                + "\nHORA_ENVIO = " + pDespacho.getHoraEntrega()
                + "\nOBSERVACION = " + pDespacho.getObservacion()
                + "\nLOG_ACTIVO = " + pDespacho.getEstado()
                + "\nCOSTO = " + pDespacho.getCosto()
                + "\nCOD_ENTREGA = " + pDespacho.getTipoEntrega().getId_entrega()
                + "\nCOD_ORDEN = " + pDespacho.getOrden().getCod_orden()
                + "\nWHERE COD_DESPACHO = " + pDespacho.getIdDespacho() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public void estadoDespachos(Despacho pDespacho) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE DESPACHO"
                + "\nSET LOG_ACTIVO = " + pDespacho.getEstado().getCod_estado()
                + "\nWHERE  COD_DESPACHO = " + pDespacho.getIdDespacho() + ";";
        accesoDatos.ejecutaSQL(select);
    }

    public List<Despacho> listaDesapachos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        List<Despacho> lista = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM DESPACHO";
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(consulta);

            while (rsPA.next()) {
                Despacho despacho = new Despacho();

                despacho.setIdDespacho(rsPA.getInt("COD_DESPACHO"));
                despacho.setFechaEntrega(rsPA.getDate("FECHA_ENVIO"));
                despacho.setHoraEntrega(rsPA.getDate("HORA_ENVIO"));
                despacho.setObservacion(rsPA.getString("OBSERVACION"));

                for (EEstado eEstado : EEstado.values()) {
                    if (eEstado.getCod_estado() == rsPA.getInt("LOG_ACTIVO")) {
                        despacho.setEstado(eEstado);
                    }
                }

                despacho.setCosto(rsPA.getFloat("COSTO"));

                for (ETipoEntrega eEntrega : ETipoEntrega.values()) {
                    if (eEntrega.getId_entrega() == rsPA.getInt("COD_ENTREGA")) {
                        despacho.setTipoEntrega(eEntrega);
                    }
                }

                despacho.setOrden(ordenDB.obtenerOrdenPorID(rsPA.getInt("COD_ORDEN")));

                lista.add(despacho);
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        return lista;
    }

}
