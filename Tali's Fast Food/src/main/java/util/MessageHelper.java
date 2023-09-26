/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

public class MessageHelper {

    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void mostrarMensajeInformacion(String titulo, String contenido) {
        addMessage(FacesMessage.SEVERITY_INFO, titulo, contenido);
    }

    public static void mostrarMensajeError(String titulo, String contenido) {
        addMessage(FacesMessage.SEVERITY_ERROR, titulo, contenido);
    }

    public static void addErrorMessage(String pErrorMessage) {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", pErrorMessage);
        PrimeFaces.current().dialog().showMessageDynamic(mensaje);
    }

    public static void addSuccessMessage(String pSuccessMessage) {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Información!", pSuccessMessage);
        PrimeFaces.current().dialog().showMessageDynamic(mensaje);
    }

    public static void addWaringMessage(String pSuccessMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, pSuccessMessage, null));
    }
}
