/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.*;
import dao.AccesoDatos;
import dao.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Alberto
 */
public class BarrioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Barrio> listaB; // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina

    public BarrioDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        listaB = new LinkedList<Barrio>();
    }

    public BarrioDB() {
        super();
    }

    public LinkedList<Barrio> SeleccionarBarrioporDistrito(float codigoProv, float codigoCanton, float codigoDistrito)
            throws SNMPExceptions, SQLException {

        String strSQL = "";
        LinkedList<Barrio> listBarrio = new LinkedList<Barrio>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA, COD_CANTON, COD_DISTRITO, COD_BARRIO, DSC_BARRIO, LOG_ACTIVO\n"
                    + "FROM INST_BARRIO\n"
                    + "WHERE LOG_ACTIVO = 1 AND COD_PROVINCIA = " + codigoProv + "\n"
                    + "AND COD_CANTON = " + codigoCanton + " AND COD_DISTRITO = " + codigoDistrito + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                int codDistrito = rsPA.getInt("COD_DISTRITO");
                int codBarrio = rsPA.getInt("COD_BARRIO");
                String descBarrio = rsPA.getString("DSC_BARRIO");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Barrio barrio = new Barrio(codProvincia,
                        codCanton, codDistrito, codBarrio, descBarrio, logActivo);

                listBarrio.add(barrio);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        listaB = listBarrio;
        return listBarrio;
    }

    public Barrio getBarrioById(float cod_provincia, float cod_canton, float cod_distrito, float cod_barrio) throws SNMPExceptions {
        try {
            return SeleccionarBarrioporDistrito(cod_provincia, cod_canton, cod_distrito).stream().filter(b -> b.cod_provincia == cod_provincia && b.cod_canton == cod_canton && 
                   b.cod_distrito == cod_distrito &&  b.cod_barrio == cod_barrio).findFirst().orElse(null);
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
