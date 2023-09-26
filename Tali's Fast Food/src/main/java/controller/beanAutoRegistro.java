/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;
import util.CommonStrings;
import util.ManejadorCorreo;
import util.MessageHelper;

/**
 *
 * @author steve
 */
public class beanAutoRegistro {

    Usuario oUsuario;

    private UsuarioDB oUsuarioDB;
    private DireccionDB oDireccionDB;
    private HorarioDB oHorarioDB;

    public beanAutoRegistro() {
        oUsuario = AutoRegistroInMemory.getoUsuario();
        oUsuarioDB = new UsuarioDB();
        oDireccionDB = new DireccionDB();
        oHorarioDB = new HorarioDB();
    }

    public String guardarRegistro() throws SNMPExceptions {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Registro guardado exitosamente!", "Su solicitud se ha enviado con éxito, en breve recibirá un correo para la activación de su cuenta");
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Debe seleccionar al menos un horario");
        try {
            if (!AutoRegistroInMemory.horarios.isEmpty()) {
                insertarUsuario();
                insertarDirecciones();
                insertarHorarios();

                if (ManejadorCorreo.correoEnviado(oUsuario)) {
                    PrimeFaces.current().dialog().showMessageDynamic(mensaje);
                    AutoRegistroInMemory.direcciones.clear();
                    AutoRegistroInMemory.horarios.clear();
                    AutoRegistroInMemory.agregarUsuario(new Usuario());
                    return "inicioSesion.xhtml";
                }
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(error);
            }

        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return "";
    }

    public void insertarUsuario() throws SNMPExceptions {
        try {
            oUsuarioDB.insertarUsuario(oUsuario);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void insertarDirecciones() {
        List<Direccion> listaDirecciones = new ArrayList<>();
        listaDirecciones = AutoRegistroInMemory.direcciones;
        try {
            for (Direccion direccion : listaDirecciones) {
                direccion.setUsuario(oUsuario);
                oDireccionDB.insertarDireccion(direccion);
            }
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

    public void insertarHorarios() {
        List<Horario> listaHorarios = new ArrayList<>();
        listaHorarios = AutoRegistroInMemory.horarios;

        try {
            for (Horario horario : listaHorarios) {
                horario.setUsuario(oUsuario);
                oHorarioDB.insertarHorario(horario);
            }
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException ex) {
            MessageHelper.mostrarMensajeError("Error", String.format(CommonStrings.ERROR, ex.getMessage()));
        }
    }

}
