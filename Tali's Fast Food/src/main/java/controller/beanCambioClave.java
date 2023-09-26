/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.SNMPExceptions;
import java.security.SecureRandom;
import model.UsuarioDB;
import util.ManejadorCorreo;

/**
 *
 * @author hcald
 */
public class beanCambioClave {

    private String cedula;
    private String email;
    private UsuarioDB oUsuarioDB;
    private String nuevaContra;
    private String mensaje;

    public beanCambioClave() {
        oUsuarioDB = new UsuarioDB();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarioDB getoUsuarioDB() {
        return oUsuarioDB;
    }

    public void setoUsuarioDB(UsuarioDB oUsuarioDB) {
        this.oUsuarioDB = oUsuarioDB;
    }

    public String getNuevaContra() {
        return nuevaContra;
    }

    public void setNuevaContra(String nuevaContra) {
        this.nuevaContra = nuevaContra;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String limpiar() {
        this.setCedula("");
        this.setEmail("");
        this.setMensaje("");
        return "inicioSesion.xhtml";
    }

    public void generadorContrasenna() throws SNMPExceptions {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*#";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // Cada iteración del bucle elige aleatoriamente un carácter del dado
        // Rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i <= 9; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        this.setNuevaContra(sb.toString());
        restablecerContra();
    }

    public void restablecerContra() throws SNMPExceptions {
        try {
            if (ManejadorCorreo.correoRestablecerContra(this.getEmail(), this.getNuevaContra())) {
                this.setMensaje("Su contraseña ha sido restablecida con éxito y enviada a su correo electrónico.");
                oUsuarioDB.restablecerContrasenna(this.getCedula(), this.getNuevaContra());
            }
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
