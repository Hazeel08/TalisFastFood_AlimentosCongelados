/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.*;
import org.primefaces.PrimeFaces;

/**
 *
 * @author steve
 */
public class beanHorario {

    Horario horario;

    public beanHorario() {
        horario = new Horario();
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public boolean hayNulos() {
        if (horario.getInfoEntregas() == null) {
            return true;
        }
        return false;
    }

    public void agregarHorario() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Información guardada exitosamente");
        if (!hayNulos()) {
            AutoRegistroInMemory.agregarHorario(horario);
            horario = new Horario();
            PrimeFaces.current().dialog().showMessageDynamic(message);
            PrimeFaces.current().executeScript("PF('dialogoAgregar').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hay campos nulos", null));
            PrimeFaces.current().executeScript("PF('dialogoAgregar').show()");
        }
    }

    public void eliminarHorario(Horario oHorario) {
        AutoRegistroInMemory.eliminarHorario(oHorario);
    }

    public List<Horario> getListadoHorario() {
        return AutoRegistroInMemory.horarios;
    }
}
