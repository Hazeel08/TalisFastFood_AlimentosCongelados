/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Distrito {

    float cod_provincia;
    float cod_canton;
    float cod_distrito;
    String dsc_Distrito;
    float log_activo;

    public Distrito(float cod_provincia, float cod_canton, float cod_distrito, String dsc_Distrito, float log_activo) {
        this.setCod_provincia(cod_provincia);
        this.setCod_canton(cod_canton);
        this.setCod_distrito(cod_distrito);
        this.setDsc_Distrito(dsc_Distrito);
        this.setLog_activo(log_activo);
    }

    public Distrito() {
    }

    public float getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(float cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public float getCod_canton() {
        return cod_canton;
    }

    public void setCod_canton(float cod_canton) {
        this.cod_canton = cod_canton;
    }

    public float getCod_distrito() {
        return cod_distrito;
    }

    public void setCod_distrito(float cod_distrito) {
        this.cod_distrito = cod_distrito;
    }

    public String getDsc_Distrito() {
        return dsc_Distrito;
    }

    public void setDsc_Distrito(String dsc_Distrito) {
        this.dsc_Distrito = dsc_Distrito;
    }

    public float getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(float log_activo) {
        this.log_activo = log_activo;
    }

}
