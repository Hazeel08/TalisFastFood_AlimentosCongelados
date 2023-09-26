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

public class ProvinciaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina
    private LinkedList<Provincia> listaPro;

    public ProvinciaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
        listaPro = new LinkedList<Provincia>();
    }

    public ProvinciaDB() {
        super();
    }

    public LinkedList<Provincia> moTodo() throws SNMPExceptions, SQLException {

        // breakpoint en plublic**
        String select = "";
        LinkedList<Provincia> listaProv = new LinkedList<Provincia>();

        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            select = "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,DSC_PROVINCIA,LOG_ACTIVO "
                    + "FROM INST_PROVINCIA WHERE LOG_ACTIVO=1;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString("" + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo = rsPA.getFloat("LOG_ACTIVO");

                Provincia perProvincia = new Provincia(codigoProvincia, dscCortaProvincia, dscProvincia, logActivo);
                listaProv.add(perProvincia);

            }

            rsPA.close(); // cierra conexion

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
        listaPro = listaProv;
        return listaProv;
    }

    public Provincia getProvinciaPorId(float cod_provincia) throws SNMPExceptions {
        try {
            return moTodo().stream().filter(p -> p.cod_provincia == cod_provincia).findFirst().orElse(null);
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
