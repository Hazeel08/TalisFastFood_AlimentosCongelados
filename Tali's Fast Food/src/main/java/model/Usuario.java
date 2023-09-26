/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.*;

public class Usuario {

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String cedula;
    private String telefono;
    private String email;
    private String clave;
    private String confirmarClave;
    private ETipoRol tipoRol; // Cliente
    private EEstado estado; // Pendiente

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    public ETipoRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(ETipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    public EEstado getEstado() {
        return estado;
    }

    public void setEstado(EEstado estado) {
        this.estado = estado;
    }

    public String toStringTipoRol() {

        for (ETipoRol eTipoRol : ETipoRol.values()) {
            if (eTipoRol.Administrador.getId_rol() == 1) {
                return "Administrador";
            }
            if (eTipoRol.Cliente.getId_rol() == 2) {
                return "Cliente";
            }
            if (eTipoRol.Despachador.getId_rol() == 3) {
                return "Despachador";
            }
        }
        return "";
    }

}
