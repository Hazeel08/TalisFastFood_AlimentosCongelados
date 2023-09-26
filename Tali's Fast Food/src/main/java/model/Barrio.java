/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alberto
 */
public class Barrio {

    float cod_provincia;
    float cod_canton;
    float cod_distrito;
    float cod_barrio;
    String dsc_barrio;
    float log_activo;

    public Barrio(float cod_provincia, float cod_canton, float cod_distrito, float cod_barrio,
            String dsc_barrio, float log_activo) {
        this.setCod_provincia(cod_provincia);
        this.setCod_canton(cod_canton);
        this.setCod_distrito(cod_distrito);
        this.setDsc_barrio(dsc_barrio);
        this.setCod_barrio(cod_barrio);
        this.setLog_activo(log_activo);
    }

    public Barrio() {
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

    public float getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(float log_activo) {
        this.log_activo = log_activo;
    }
}
