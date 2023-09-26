/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Provincia {

    float cod_provincia;
    String dsc_corta_provincia;
    String dsc_provincia;
    float log_activo;

    public Provincia(float cod_provincia, String dsc_corta_provincia,
            String dsc_provincia, float log_activo) {

        this.setCod_provincia(cod_provincia);
        this.setDsc_corta_provincia(dsc_corta_provincia);
        this.setDsc_provincia(dsc_provincia);
        this.setLog_activo(log_activo);
    }

    public Provincia() {
    }

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

}
