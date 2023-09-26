/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.*;
import model.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;

@Named(value = "beanProvincias")
@SessionScoped
public class beanProvincias implements Serializable {

    Direccion oDireccion;
    DireccionDB direccionDB;

    //ATRIBUTOS PROVINCIA
    float cod_provincia;
    String dsc_corta_provincia;
    String dsc_provincia = "Alajuela";
    float log_activo;
    LinkedList<SelectItem> listaPro = new LinkedList<>();
    LinkedList<Provincia> listaTablaProvincia = new LinkedList<Provincia>();

    //provincia
    Provincia pro;
    ProvinciaDB proDB;

    //ATRIBUTOS CANTON
    float cod_provinciaCan;
    float cod_canton;
    String dsc_canton;
    float log_activoCan;
    LinkedList<SelectItem> listaCan = new LinkedList<>();

    Canton can;
    CantonDB canDB;

    //Atributos Distrito
    float cod_provinciaDis;
    float cod_cantonDist;
    float cod_distrito;
    String dsc_distrito;
    float log_activodist;
    LinkedList<SelectItem> listaDis = new LinkedList<>();

    Distrito dis;
    DistritoDB disDB;

    //Atributos Barrio
    float cod_provinciaBar;
    float cod_cantonBar;
    float cod_distritoBar;
    float cod_barrio;
    String dsc_barrio;
    float log_activobar;
    String otrasSennas;

    boolean esDireccionPrincipal;

    LinkedList<SelectItem> listaBar = new LinkedList<>();

    Distrito bar;
    BarrioDB barDB;

    public beanProvincias() {

        proDB = new ProvinciaDB();
        canDB = new CantonDB();
        disDB = new DistritoDB();
        barDB = new BarrioDB();

        oDireccion = new Direccion();
        direccionDB = new DireccionDB();
    }

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Provincias">
    public float getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(float cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public String getDsc_corta_provincia() {
        return dsc_corta_provincia;
    }

    public void setDsc_corta_provincia(String dsc_corta_provincia) {
        this.dsc_corta_provincia = dsc_corta_provincia;
    }

    public String getDsc_provincia() {
        return dsc_provincia;
    }

    public void setDsc_provincia(String dsc_provincia) {
        this.dsc_provincia = dsc_provincia;
    }

    public float getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(float log_activo) {
        this.log_activo = log_activo;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets TablaProvincias">
    public LinkedList<Provincia> getListaTablaProvincia() throws SNMPExceptions, SQLException {

        LinkedList<Provincia> lista = new LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();

        lista = pDB.moTodo();

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;

    }

    public void setListaTablaProvincia(LinkedList<
            Provincia> listaTablaProvincia) {
        this.listaTablaProvincia = listaTablaProvincia;
    }

    public void setListPro(LinkedList<SelectItem> listProv) {
        this.listaPro = listProv;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Lista Provincias">
    public LinkedList<SelectItem> getListaPro()
            throws SNMPExceptions, SQLException {
        String dscProvincia = "";
        float codigoProvincia = 0;

        LinkedList<Provincia> lista = new LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();

        lista = pDB.moTodo();

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Provincia pro = (Provincia) iter.next();
            dscProvincia = pro.getDsc_provincia();
            codigoProvincia = pro.getCod_provincia();
            resultList.add(new SelectItem(codigoProvincia,
                    dscProvincia));
        }
        return resultList;

    }

    public void setListaPro(LinkedList<SelectItem> listaPro) {
        this.listaPro = listaPro;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Cantones">
    public float getCod_provinciaCan() {
        return cod_provinciaCan;
    }

    public void setCod_provinciaCan(float cod_provinciaCan) {
        this.cod_provinciaCan = cod_provinciaCan;
    }

    public float getCod_canton() {
        return cod_canton;
    }

    public void setCod_canton(float cod_canton) {
        this.cod_canton = cod_canton;
    }

    public String getDsc_canton() {
        return dsc_canton;
    }

    public void setDsc_canton(String dsc_canton) {
        this.dsc_canton = dsc_canton;
    }

    public float getLog_activoCan() {
        return log_activoCan;
    }

    public void setLog_activoCan(float log_activoCan) {
        this.log_activoCan = log_activoCan;
    }
// </editor-fold>

    public String getOtrasSennas() {
        return otrasSennas;
    }

    public void setOtrasSennas(String otrasSennas) {
        this.otrasSennas = otrasSennas;
    }

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Lista Cantones">
    public LinkedList<SelectItem> getListaCan() throws SNMPExceptions, SQLException {

        String descripcion = "";
        float codigoCant = 0;

        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB cantonDB = new CantonDB();

        lista = cantonDB.SeleccionarCantonPorProvincia(cod_provincia);

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Canton canton = (Canton) iter.next();
            descripcion = canton.getDsc_canton();
            codigoCant = canton.getCod_canton();
            resultList.add(new SelectItem(codigoCant, descripcion));

        }
        return resultList;
    }

    public void setListaCan(LinkedList<SelectItem> listaCan) {
        this.listaCan = listaCan;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Distritos">
    public float getCod_provinciaDis() {
        return cod_provinciaDis;
    }

    public void setCod_provinciaDis(float cod_provinciaDis) {
        this.cod_provinciaDis = cod_provinciaDis;
    }

    public float getCod_cantonDist() {
        return cod_cantonDist;
    }

    public void setCod_cantonDist(float cod_cantonDist) {
        this.cod_cantonDist = cod_cantonDist;
    }

    public float getCod_distrito() {
        return cod_distrito;
    }

    public void setCod_distrito(float cod_distrito) {
        this.cod_distrito = cod_distrito;
    }

    public String getDsc_distrito() {
        return dsc_distrito;
    }

    public void setDsc_distrito(String dsc_distrito) {
        this.dsc_distrito = dsc_distrito;
    }

    public float getLog_activodist() {
        return log_activodist;
    }

    public void setLog_activodist(float log_activodist) {
        this.log_activodist = log_activodist;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Lista Distritos">
    public LinkedList<SelectItem> getListaDis() throws SNMPExceptions, SQLException {
        String descripcion = "";
        float codigoDis = 0;

        LinkedList<Distrito> lista = new LinkedList<Distrito>();
        DistritoDB distritoDB = new DistritoDB();

        lista = distritoDB.SeleccionarDistritoporCanton(cod_provincia, cod_canton);

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Distrito distrito = (Distrito) iter.next();
            descripcion = distrito.getDsc_Distrito();
            codigoDis = distrito.getCod_distrito();
            resultList.add(new SelectItem(codigoDis, descripcion));

        }
        return resultList;

    }

    public void setListaDis(LinkedList<SelectItem> listaDis) {
        this.listaDis = listaDis;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Barrio">
    public float getCod_provinciaBar() {
        return cod_provinciaBar;
    }

    public void setCod_provinciaBar(float cod_provinciaBar) {
        this.cod_provinciaBar = cod_provinciaBar;
    }

    public float getCod_cantonBar() {
        return cod_cantonBar;
    }

    public void setCod_cantonBar(float cod_cantonBar) {
        this.cod_cantonBar = cod_cantonBar;
    }

    public float getCod_distritoBar() {
        return cod_distritoBar;
    }

    public void setCod_distritoBar(float cod_distritoBar) {
        this.cod_distritoBar = cod_distritoBar;
    }

    public float getCod_barrio() {
        return cod_barrio;
    }

    public void setCod_barrio(float cod_barrio) {
        this.cod_barrio = cod_barrio;
    }

    public String getDsc_barrio() {
        return dsc_barrio;
    }

    public void setDsc_barrio(String dsc_barrio) {
        this.dsc_barrio = dsc_barrio;
    }

    public float getLog_activobar() {
        return log_activobar;
    }

    public void setLog_activobar(float log_activobar) {
        this.log_activobar = log_activobar;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets y Sets Lista Barrios">
    public LinkedList<SelectItem> getListaBar() throws SNMPExceptions, SQLException {
        String descripcion = "";
        float codigoBar = 0;

        LinkedList<Barrio> lista = new LinkedList<Barrio>();
        BarrioDB barrioDB = new BarrioDB();

        lista = barrioDB.SeleccionarBarrioporDistrito(cod_provincia, cod_canton, cod_distrito);

        LinkedList resultList = new LinkedList();
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Barrio barrio = (Barrio) iter.next();
            descripcion = barrio.getDsc_barrio();
            codigoBar = barrio.getCod_barrio();
            resultList.add(new SelectItem(codigoBar, descripcion));
        }

        return resultList;
    }

    public void setListaBar(LinkedList<SelectItem> listaBar) {
        this.listaBar = listaBar;
    }

    public boolean isEsDireccionPrincipal() {
        return esDireccionPrincipal;
    }

    public void setEsDireccionPrincipal(boolean esDireccionPrincipal) {
        this.esDireccionPrincipal = esDireccionPrincipal;
    }
// </editor-fold>

    public boolean hayNulos() {
        if (cod_provincia == 0 || cod_canton == 0 || cod_distrito == 0 || cod_barrio == 0) {
            return true;
        }
        return false;
    }

    public String validacionListaVacia() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Debe agregar al menos 1 dirección");
        if (AutoRegistroInMemory.direcciones.isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return "";
        }
        return "datosHorario.xhtml";
    }

    public void agregarDireccion() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Dirección guardada exitosamente");

        try {
            if (!hayNulos()) {
                // Se agregan las direcciones a la tabla en memoria
                oDireccion.setProvincia(proDB.getProvinciaPorId(cod_provincia));
                oDireccion.setCanton(canDB.getCantonPorId(cod_provincia, cod_canton));
                oDireccion.setDistrito(disDB.getDistritoPorId(cod_provincia, cod_canton, cod_distrito));
                oDireccion.setBarrio(barDB.getBarrioById(cod_provincia, cod_canton, cod_distrito, cod_barrio));
                oDireccion.setOtrasSennias(otrasSennas);
                oDireccion.setEsDireccionPrincipal(esDireccionPrincipal);
                AutoRegistroInMemory.agregarDireccion(oDireccion);
                limpiarCampos();
                PrimeFaces.current().dialog().showMessageDynamic(message);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hay campos nulos", null));
                PrimeFaces.current().executeScript("PF('dialogoAgregar').show();");
            }

        } catch (SNMPExceptions ex) {
            Logger.getLogger(beanProvincias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarDireccion() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Dirección eliminada exitosamente");
        // Se elimina todo el objeto direccion seleccionada en la tabla
        AutoRegistroInMemory.eliminarDireccion(oDireccion);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void limpiarCampos() {
        this.setCod_provincia(0f);
        this.setCod_canton(0f);
        this.setCod_distrito(0f);
        this.setCod_barrio(0f);
        this.setOtrasSennas("");
        this.setEsDireccionPrincipal(false);
    }
}
