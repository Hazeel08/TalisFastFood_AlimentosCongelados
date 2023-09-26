/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import util.ManejadorCorreo;

public class beanAyuda {

    private String email;
    private String mensaje;
    private String mensajeResultado;

    public beanAyuda() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public void setMensajeResultado(String mensajeResultado) {
        this.mensajeResultado = mensajeResultado;
    }

    public void limpiar() {
        this.setEmail("");
        this.setMensaje("");
        this.setMensajeResultado("");
    }

    public void enviarCorreoConsulta() throws SNMPExceptions {
        try {
            if (ManejadorCorreo.correoConsulta(this.getEmail(), this.getMensaje())) {
                this.setMensajeResultado("Su consulta ha sido realizada con éxito.");
            }
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
