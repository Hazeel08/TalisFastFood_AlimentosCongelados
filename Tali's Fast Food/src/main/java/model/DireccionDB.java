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

public class DireccionDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    ProvinciaDB oProvinciaDB;
    CantonDB oCantonDB;
    DistritoDB oDistritoDB;
    BarrioDB oBarrioDB;

    public DireccionDB() {
        accesoDatos.setDbConn(conn);
        oProvinciaDB = new ProvinciaDB();
        oCantonDB = new CantonDB();
        oDistritoDB = new DistritoDB();
        oBarrioDB = new BarrioDB();
    }

    public void insertarDireccion(Direccion pDireccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "INSERT INTO INST_DIRECCION(COD_PROVINCIA, COD_CANTON, COD_DISTRITO, COD_BARRIO, DSC_DIRECCION, DIR_PRINCIPAL, COD_USUARIO)"
                + "VALUES(" + pDireccion.getProvincia().cod_provincia + ", " + pDireccion.getCanton().cod_canton + ", "
                + pDireccion.getDistrito().cod_distrito + ", " + pDireccion.getBarrio().cod_barrio + ", "
                + "'" + pDireccion.getOtrasSennias() + "', " + pDireccion.getDireccionPrincipalStr() + ", "
                + "'" + pDireccion.getUsuario().getCedula() + "');";

        accesoDatos.ejecutaSQL(select);
    }

    public void editarDireccion(Direccion pDireccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE INST_DIRECCION"
                + "\nSET COD_PROVINCIA = " + pDireccion.getProvincia().cod_provincia
                + "COD_CANTON = " + +pDireccion.getCanton().cod_canton
                + "COD_DISTRITO = " + pDireccion.getDistrito().cod_distrito
                + "COD_BARRIO = " + pDireccion.getBarrio().cod_barrio
                + "DSC_DIRECCION = " + pDireccion.getOtrasSennias()
                + "DIR_PRINCIPAL = " + pDireccion.isEsDireccionPrincipal()
                + "WHERE COD_USUARIO = " + pDireccion.getUsuario().getCedula() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public void eliminarDireccion(Direccion pDireccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "DELETE FROM INST_DIRECCION"
                + "WHERE COD_DIRECCION = " + pDireccion.getCodigo() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public List<Direccion> getDirecciones(Direccion pDireccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM INST_DIRECCION"
                + "\nWHERE COD_USUARIO = " + "'" + pDireccion.getUsuario().getCedula() + "';";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Direccion> listaDirecciones = new ArrayList<>();

        while (rs.next()) {
            Direccion oDireccion = new Direccion();
            oDireccion.setCodigo(rs.getInt("COD_DIRECCION"));
            oDireccion.setProvincia(oProvinciaDB.getProvinciaPorId(rs.getFloat("COD_PROVINCIA")));
            oDireccion.setCanton(oCantonDB.getCantonPorId(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON")));
            oDireccion.setDistrito(oDistritoDB.getDistritoPorId(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON"), rs.getFloat("COD_DISTRITO")));
            oDireccion.setBarrio(oBarrioDB.getBarrioById(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON"), rs.getFloat("COD_DISTRITO"), rs.getFloat("COD_BARRIO")));
            oDireccion.setOtrasSennias(rs.getString("DSC_DIRECCION"));
            oDireccion.setEsDireccionPrincipal(rs.getBoolean("DIR_PRINCIPAL"));

            listaDirecciones.add(oDireccion);

        }

        return listaDirecciones;
    }

    public Direccion obtenerDireccionPorId(int cod_direccion) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM INST_DIRECCION"
                + "\nWHERE COD_DIRECCION = " + cod_direccion + ";";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        Direccion oDireccion = new Direccion();
        while (rs.next()) {
            oDireccion.setCodigo(rs.getInt("COD_DIRECCION"));
            oDireccion.setProvincia(oProvinciaDB.getProvinciaPorId(rs.getFloat("COD_PROVINCIA")));
            oDireccion.setCanton(oCantonDB.getCantonPorId(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON")));
            oDireccion.setDistrito(oDistritoDB.getDistritoPorId(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON"), rs.getFloat("COD_DISTRITO")));
            oDireccion.setBarrio(oBarrioDB.getBarrioById(rs.getFloat("COD_PROVINCIA"), rs.getFloat("COD_CANTON"), rs.getFloat("COD_DISTRITO"), rs.getFloat("COD_BARRIO")));
            oDireccion.setOtrasSennias(rs.getString("DSC_DIRECCION"));
            oDireccion.setEsDireccionPrincipal(rs.getBoolean("DIR_PRINCIPAL"));
            return oDireccion;
        }
        return null;
    }

}
