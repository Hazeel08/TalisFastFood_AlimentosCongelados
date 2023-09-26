/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ETipoRol;

/**
 *
 * @author steve
 */
public class Rol {

    private int cod_rol;
    private String tipo_rol;

    public Rol(int cod_rol, String tipo_rol) {
        this.setCod_rol(cod_rol);
        this.setTipo_rol(tipo_rol);
    }

    public Rol() {
    }

    public int getCod_rol() {
        return cod_rol;
    }

    public void setCod_rol(int cod_rol) {
        this.cod_rol = cod_rol;
    }

    public String getTipo_rol() {
        return tipo_rol;
    }

    public void setTipo_rol(String tipo_rol) {
        this.tipo_rol = tipo_rol;
    }

}
