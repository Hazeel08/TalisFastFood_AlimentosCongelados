/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

/**
 *
 * @author Estudiante
 */
public class CantonDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Canton> listaC; // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina

    public CantonDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        listaC = new LinkedList<Canton>();
    }

    public CantonDB() {
        super();
    }

    public LinkedList<Canton> SeleccionarCantonPorProvincia(float codigoProv) throws SNMPExceptions, SQLException {

        String strSQL = "";
        LinkedList<Canton> listCant = new LinkedList<Canton>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,DSC_CANTON,LOG_ACTIVO\n "
                    + "FROM INST_CANTON\n "
                    + "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA=" + codigoProv + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                String descCanton = rsPA.getString("DSC_CANTON");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Canton canton = new Canton(codProvincia,
                        codCanton, descCanton, logActivo);

                listCant.add(canton);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        listaC = listCant;
        return listCant;
    }

    public Canton getCantonPorId(float cod_provincia, float cod_canton) throws SNMPExceptions {
        try {
            return SeleccionarCantonPorProvincia(cod_provincia).stream().filter(c -> c.cod_canton == cod_canton && c.cod_provincia == c.cod_provincia).findFirst().orElse(null);
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
