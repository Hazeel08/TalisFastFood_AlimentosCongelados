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
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author steve
 */
public class RolDB {

    Connection con;
    AccesoDatos accesoDatos;

    public RolDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(con);
    }

    public LinkedList<Rol> listadoRoles() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM ROL";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        LinkedList<Rol> listaRoles = new LinkedList<>();

        while (rs.next()) {
            int codigo = rs.getInt("COD_ROL");
            String tipo_rol = rs.getString("TIPO_ROL");
            Rol oRol = new Rol(codigo, tipo_rol);

            listaRoles.add(oRol);
        }
        return listaRoles;
    }
}
