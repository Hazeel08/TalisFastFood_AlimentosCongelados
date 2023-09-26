/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Direccion {

    private int codigo;
    private Provincia provincia;
    private Canton canton;
    private Distrito distrito;
    private Barrio barrio;
    private String otrasSennias;
    private boolean esDireccionPrincipal;
    private Usuario usuario;

    public Direccion(int codigo, Provincia provincia, Canton canton, Distrito distrito, Barrio barrio, String otrasSennias, boolean esDireccionPrincipal, Usuario usuario) {
        this.codigo = codigo;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
        this.otrasSennias = otrasSennias;
        this.esDireccionPrincipal = esDireccionPrincipal;
        this.usuario = usuario;
    }

    public Direccion() {
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSennias() {
        return otrasSennias;
    }

    public void setOtrasSennias(String otrasSennias) {
        this.otrasSennias = otrasSennias;
    }

    public boolean isEsDireccionPrincipal() {
        return esDireccionPrincipal;
    }

    public int getDireccionPrincipalStr() {
        return esDireccionPrincipal ? 1 : 0;
    }

    public void setEsDireccionPrincipal(boolean esDireccionPrincipal) {
        this.esDireccionPrincipal = esDireccionPrincipal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String toString() {
        String vHilera = "";

        vHilera += "Provincia " + provincia.getDsc_provincia()
                + "\nCantón " + canton.getDsc_canton()
                + "\nDistrito " + distrito.getDsc_Distrito()
                + "\nBarrio " + barrio.getDsc_barrio()
                + "\nOtras señas " + this.otrasSennias;

        return vHilera;
    }

}
