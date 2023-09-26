/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.*;
import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.EEstado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

public class EstadoDB {

    Connection con;
    AccesoDatos accesoDatos;

    public EstadoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(con);
    }

    public LinkedList<Estado> listadoEstadoUsuarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM ESTADO_USUARIO";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        LinkedList<Estado> listaEstadosUsuarios = new LinkedList<>();

        while (rs.next()) {
            int cod_estado = rs.getInt("COD_ESTADO");
            int log_activo = rs.getInt("LOG_aCTIVO");
            Estado estado = new Estado();
            estado.setCod_estado(cod_estado);
            estado.setEstado(log_activo);

            listaEstadosUsuarios.add(estado);
        }
        return listaEstadosUsuarios;
    }
}
