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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class HorarioDB {

    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public HorarioDB() {
        accesoDatos.setDbConn(con);
    }

    public void insertarHorario(Horario pHorario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "INSERT INTO HORARIO_ENTREGA(FECHA, COD_USUARIO)"
                + "\nVALUES('" + pHorario.convertirLocalDateTimeADate() + "', "
                + "'" + pHorario.getoUsuario().getCedula() + "');";

        accesoDatos.ejecutaSQL(select);
    }

    public void editarHorario(Horario pHorario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, ClassNotFoundException {

        String select = "UPDATE HORARIO_ENTREGA"
                + "\nSET FECHA = " + pHorario.convertirLocalDateTimeADate()
                + "\nWHERE COD_USUARIO = " + pHorario.getoUsuario();

        accesoDatos.ejecutaSQL(select);
    }

    public void eliminarHorario(Horario pHorario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "DELET FROM HORARIO_ENTREGA"
                + "\nWHERE COD_USUARIO = " + pHorario.getoUsuario();

        accesoDatos.ejecutaSQL(select);
    }

    public Horario obtenerHorarioPorId(int cod_horario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return getHorarios().stream().filter(h -> h.getCod_horario() == cod_horario).findFirst().orElse(null);
    }

    public List<Horario> getHorarios(Horario pHorario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM HORARIO_ENTREGA "
                + "\nWHERE COD_USUARIO = " + "'" + pHorario.getoUsuario().getCedula() + "';";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Horario> listaHorarios = new ArrayList<>();

        while (rs.next()) {
            Horario oHorario = new Horario();
            oHorario.setCod_horario(rs.getInt("COD_HORARIO"));
            oHorario.setFechaYHorario(rs.getTimestamp("FECHA"));
            listaHorarios.add(oHorario);
        }

        return listaHorarios;
    }

    public List<Horario> getHorarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "SELECT * FROM HORARIO_ENTREGA;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Horario> listaHorarios = new ArrayList<>();

        while (rs.next()) {
            Horario oHorario = new Horario();
            oHorario.setCod_horario(rs.getInt("COD_HORARIO"));
            oHorario.setFechaYHorario(rs.getTimestamp("FECHA"));
            listaHorarios.add(oHorario);
        }

        return listaHorarios;
    }
}
