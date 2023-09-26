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

public class DistritoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Distrito> listaC; // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina

    public DistritoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        listaC = new LinkedList<Distrito>();
    }

    public DistritoDB() {
        super();
    }

    public LinkedList<Distrito> SeleccionarDistritoporCanton(float codigoProv, float codigoCanton)
            throws SNMPExceptions, SQLException {

        String strSQL = "";
        LinkedList<Distrito> listDist = new LinkedList<Distrito>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,COD_DISTRITO,DSC_DISTRITO,LOG_ACTIVO "
                    + "FROM INST_DISTRITO "
                    + "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA=" + codigoProv
                    + " AND COD_CANTON=" + codigoCanton + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                int codDistrito = rsPA.getInt("COD_DISTRITO");
                String descDistrito = rsPA.getString("DSC_DISTRITO");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Distrito distrito = new Distrito(codProvincia,
                        codCanton, codDistrito, descDistrito, logActivo);

                listDist.add(distrito);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        listaC = listDist;
        return listDist;

    }

    public Distrito getDistritoPorId(float cod_provincia, float cod_canton, float cod_distrito) throws SNMPExceptions {
        try {
            return SeleccionarDistritoporCanton(cod_provincia, cod_canton).stream().filter(d -> d.cod_provincia == cod_provincia && d.cod_canton == cod_canton && d.cod_distrito == cod_distrito).findFirst().orElse(null);
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

}
