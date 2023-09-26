/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.AccesoDatos;
import dao.SNMPExceptions;
import enums.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class UsuarioDB {

    private Connection con;
    private AccesoDatos accesoDatos = new AccesoDatos();

    public UsuarioDB() {
        accesoDatos.setDbConn(con);
    }

    public void insertarUsuario(Usuario pUsuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "INSERT INTO USUARIO(COD_USUARIO, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, TELEFONO, EMAIL, CLAVE, COD_ESTADO, COD_ROL)"
                + "\nVALUES('" + pUsuario.getCedula() + "', '" + pUsuario.getNombre() + "', "
                + "'" + pUsuario.getPrimerApellido() + "', " + "'" + pUsuario.getSegundoApellido() + "', "
                + "'" + pUsuario.getTelefono() + "', " + "'" + pUsuario.getEmail() + "', "
                + "'" + pUsuario.getClave() + "', " + pUsuario.getEstado().Pendiente.getCod_estado() + ", " + pUsuario.getTipoRol().Cliente.getId_rol() + ");";

        accesoDatos.ejecutaSQL(select);
    }

    public void editarUsuario(Usuario pUsuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "UPDATE USUARIO"
                + "\nSET NOMBRE = " + pUsuario.getNombre()
                + "\nPRIMER_APELLIDO = " + pUsuario.getPrimerApellido()
                + "\nSEGUNDO_APELLIDO = " + pUsuario.getSegundoApellido()
                + "\nTELEFONO = " + pUsuario.getTelefono()
                + "\nEMAIL = " + pUsuario.getEmail()
                + "\nCLAVE = " + pUsuario.getClave()
                + "\nCOD_ESTADO = " + pUsuario.getEstado()
                + "\nCOD_ROL = " + pUsuario.getTipoRol()
                + "\nWHERE COD_USUARIO = " + pUsuario.getCedula() + ";";

        accesoDatos.ejecutaSQL(select);
    }

    public void restablecerContrasenna(String cedula, String contrasenna) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "UPDATE USUARIO"
                + "\nSET CLAVE = '" + contrasenna
                + "' \nWHERE COD_USUARIO = '" + cedula + "';";
        accesoDatos.ejecutaSQL(select);
    }

    public void eliminarUsuario(Usuario pUsuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "UPDATE USUARIO"
                + "\nSET COD_ESTADO = " + pUsuario.getEstado().Inactivo.getCod_estado()
                + "\nWHERE COD_USUARIO = " + pUsuario.getCedula() + ";";
        accesoDatos.ejecutaSQL(select);
    }

    public void activarUsuario(Usuario pUsuario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        String select = "UPDATE USUARIO"
                + "\nSET COD_ESTADO = " + pUsuario.getEstado().Activo.getCod_estado()
                + "\nWHERE COD_USUARIO = " + pUsuario.getCedula() + ";";
        accesoDatos.ejecutaSQL(select);
    }

    public List<Usuario> getUsuarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM USUARIO WHERE COD_ESTADO = 2 AND COD_ROL != 1;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Usuario> listaUsuarios = new ArrayList<>();

        while (rs.next()) {
            Usuario oUsuario = new Usuario();
            oUsuario.setCedula(rs.getString("COD_USUARIO"));
            oUsuario.setNombre(rs.getString("NOMBRE"));
            oUsuario.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
            oUsuario.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
            oUsuario.setTelefono(rs.getString("TELEFONO"));
            oUsuario.setEmail(rs.getString("EMAIL"));
            oUsuario.setClave(rs.getString("CLAVE"));
            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("COD_ESTADO")) {
                    oUsuario.setEstado(eEstado);
                }
            }
            for (ETipoRol eTipoRol : ETipoRol.values()) {
                if (eTipoRol.getId_rol() == rs.getInt("COD_ROL")) {
                    oUsuario.setTipoRol(eTipoRol);
                }
            }
            listaUsuarios.add(oUsuario);
        }

        return listaUsuarios;
    }

    public List<Usuario> getUsuariosInactivos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "SELECT * FROM USUARIO WHERE COD_ESTADO = 1 OR COD_ESTADO = 3 AND COD_ROL != 1;";

        ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(select);
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(null);
        while (rs.next()) {
            Usuario oUsuario = new Usuario();
            oUsuario.setCedula(rs.getString("COD_USUARIO"));
            oUsuario.setNombre(rs.getString("NOMBRE"));
            oUsuario.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
            oUsuario.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
            oUsuario.setTelefono(rs.getString("TELEFONO"));
            oUsuario.setEmail(rs.getString("EMAIL"));
            oUsuario.setClave(rs.getString("CLAVE"));
            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == rs.getInt("COD_ESTADO")) {
                    oUsuario.setEstado(eEstado);
                }
            }
            for (ETipoRol eTipoRol : ETipoRol.values()) {
                if (eTipoRol.getId_rol() == rs.getInt("COD_ROL")) {
                    oUsuario.setTipoRol(eTipoRol);
                }
            }
            listaUsuarios.add(oUsuario);
        }

        return listaUsuarios;
    }

    public Usuario getUsuarioPorId(String cedula) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return getUsuarios().stream().filter(u -> u.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    public Usuario getUsuarioPorEmail(String email) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        return getUsuarios().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
