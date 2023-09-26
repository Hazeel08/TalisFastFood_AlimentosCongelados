/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Estudiante
 */
public class Canton {

    float cod_provincia;
    float cod_canton;
    String dsc_canton;
    float log_activo;

    public Canton(float cod_provincia, float cod_canton, String dsc_canton, float log_activo) {
        this.setCod_provincia(cod_provincia);
        this.setCod_canton(cod_canton);
        this.setDsc_canton(dsc_canton);
        this.setLog_activo(log_activo);
    }

    public Canton() {
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

    public String getDsc_canton() {
        return dsc_canton;
    }

    public void setDsc_canton(String dsc_canton) {
        this.dsc_canton = dsc_canton;
    }

    public float getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(float log_activo) {
        this.log_activo = log_activo;
    }

}
