/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import model.AutoRegistroInMemory;
import model.Usuario;
import model.UsuarioDB;
import org.primefaces.PrimeFaces;
import util.CommonStrings;

public class beanDatosCuenta {

    private Usuario oUsuario;

    private UsuarioDB oUsuarioDB;

    public beanDatosCuenta() {
        oUsuario = new Usuario();
        oUsuarioDB = new UsuarioDB();
    }

    public Usuario getUsuario() {
        return oUsuario;
    }

    public void setUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public void validarIdentificacion(FacesContext context, UIComponent comp, Object value) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String idAInsertar = (String) value;
        if (oUsuarioDB.getUsuarioPorId(idAInsertar) != null) {
            ((UIInput) comp).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(CommonStrings.ERROR, "Identifiación duplicada"), CommonStrings.IDENTIFICACION_DUPLICADO);
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void validarCorreo(FacesContext context, UIComponent comp, Object value) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String idAInsertar = (String) value;
        if (oUsuarioDB.getUsuarioPorEmail(idAInsertar) != null) {
            ((UIInput) comp).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(CommonStrings.ERROR, "Email duplicado"), CommonStrings.EMAIL_DUPLICADO);
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public boolean validaciones() {

        FacesMessage mensajeEmail = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato del correo es inválido");
        FacesMessage mensajeClave = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato de la contraseña es inválido");
        FacesMessage mensajeClavesIguales = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas deben ser iguales");
        FacesMessage mensajeCedula = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Formato de cédula inválido");

        String regexPass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^*&+=()/])"
                + "(?=\\S+$).{9,12}$";

        // Valida que solo se digiten números en la cédula
        if (!oUsuario.getCedula().matches("[0-9]{9,12}")) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeCedula);
            return false;
        }

        // Valida formato del Email
        if (!oUsuario.getEmail().trim().matches("[A-Za-z0-9+_.-]+@(.+)$")) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeEmail);
            return false;
        }

        // Valida formato de contraseña
        if (!oUsuario.getClave().trim().matches(regexPass)) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeClave);
            return false;
        }

        // Valida que ambas contraseñas digitadas sean iguales
        if (!oUsuario.getClave().equals(oUsuario.getConfirmarClave())) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeClavesIguales);
            return false;
        }

        return true;
    }

    public String guardarUsuario() {
        if (!validaciones()) {
            return "";
        } else {
            AutoRegistroInMemory.agregarUsuario(oUsuario);
            return "datosDireccion.xhtml";
        }
    }

    public void limpiarCampos() {
        this.setUsuario(new Usuario());
    }
}
