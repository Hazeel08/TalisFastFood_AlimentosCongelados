/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.ETipoRol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class InicioSesionDB {

    Connection con;
    AccesoDatos accesoDatos;

    public InicioSesionDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(con);
    }

    public Usuario esInicioSesionCorrecto(String email, String clave, int rol) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM USUARIO WHERE COD_ROL = " + rol
                + "\nAND EMAIL = " + "'" + email + "'"
                + "\nAND CLAVE = " + "'" + clave + "'"
                + "\nAND COD_ESTADO = 2;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        Usuario usuario = new Usuario();
        while (rs.next()) {
            usuario.setCedula(rs.getString("COD_USUARIO"));
            usuario.setNombre(rs.getString("NOMBRE"));
            usuario.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
            usuario.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
            usuario.setTelefono(rs.getString("TELEFONO"));
            usuario.setEmail(rs.getString("EMAIL"));
            usuario.setClave(rs.getString("CLAVE"));
            for (ETipoRol eTipoRol : ETipoRol.values()) {
                if (eTipoRol.getId_rol() == rs.getInt("COD_ROL")) {
                    usuario.setTipoRol(eTipoRol);
                }
            }
            return usuario;
        }
        return null;
    }
}
