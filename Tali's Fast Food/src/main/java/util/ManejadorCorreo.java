/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.*;

/**
 *
 * @author steve
 */
public class ManejadorCorreo {

    public static boolean correoEnviado(Usuario pUsuario) throws MessagingException {
        String correo = "talisfood22@gmail.com";
        String contra = "qhtxrbfdkocdajvt";
        String correoDestino = "steventobares65@gmail.com";
        String mensajeCorreo = String.format("Se registró una nueva solicitud de cuenta en el sistema TALIS del usuario %s, por favor actívela", pUsuario.getCedula());
        try {
            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.user", correo);
            p.put("mail.smtp.password", contra);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p, null);

            MimeMessage mensaje = new MimeMessage(s);

            mensaje.setFrom(new InternetAddress(correo));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("Activación de cuenta");
            mensaje.setText(mensajeCorreo);

            Transport t = s.getTransport("smtp");

            t.connect("smtp.gmail.com", correo, contra);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean correoConsulta(String emailUsuario, String mensaje) throws MessagingException {
        String correo = "talisfood22@gmail.com";
        String contra = "qhtxrbfdkocdajvt";
        String mensajeCorreo = String.format(mensaje);
        try {
            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.user", correo);
            p.put("mail.smtp.password", contra);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p, null);

            MimeMessage m = new MimeMessage(s);

            m.setFrom(new InternetAddress(emailUsuario));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            m.setSubject("Solicitud de Ayuda");
            m.setText(mensajeCorreo);

            Transport t = s.getTransport("smtp");

            t.connect("smtp.gmail.com", correo, contra);
            t.sendMessage(m, m.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean correoRestablecerContra(String emailUsuario, String contrasenna) throws MessagingException {
        String correo = "talisfood22@gmail.com";
        String contra = "qhtxrbfdkocdajvt";
        String mensajeCorreo = String.format("\nBuenas \nUsted ha solicidado un restablecimiento de contraseña, su nueva contraseña es: " + contrasenna);
        try {
            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.user", correo);
            p.put("mail.smtp.password", contra);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p, null);

            MimeMessage mensaje = new MimeMessage(s);

            mensaje.setFrom(new InternetAddress(correo));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emailUsuario));
            mensaje.setSubject("Restablecer contraseña");
            mensaje.setText(mensajeCorreo);

            Transport t = s.getTransport("smtp");

            t.connect("smtp.gmail.com", correo, contra);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean correoMotivoInactividad(Usuario pUsuario) throws MessagingException {
        String correo = "talisfood22@gmail.com";
        String contra = "qhtxrbfdkocdajvt";
        String mensajeCorreo = String.format("\nBuenas" + pUsuario.getNombre() + "\nSu cuenta ha sido inactiva debido a que su dirección está fuera del rango para envíos"
                + "\n¡Gracias por su preferencia!");
        String correoDestino = pUsuario.getEmail();
        try {
            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.user", correo);
            p.put("mail.smtp.password", contra);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p, null);

            MimeMessage mensaje = new MimeMessage(s);

            mensaje.setFrom(new InternetAddress(correo));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("Activación de cuenta");
            mensaje.setText(mensajeCorreo);

            Transport t = s.getTransport("smtp");

            t.connect("smtp.gmail.com", correo, contra);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean correoBienvenida(Usuario pUsuario) throws MessagingException {
        String correo = "talisfood22@gmail.com";
        String contra = "qhtxrbfdkocdajvt";
        String mensajeCorreo = String.format("\nBuenas" + pUsuario.getNombre() + "\nBienvenido a TALI'S food, su cuenta ha sido activada con éxito"
                + "\n¡Gracias por su preferencia!");
        String correoDestino = pUsuario.getEmail();
        try {
            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.user", correo);
            p.put("mail.smtp.password", contra);
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p, null);

            MimeMessage mensaje = new MimeMessage(s);

            mensaje.setFrom(new InternetAddress(correo));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("Activación de cuenta");
            mensaje.setText(mensajeCorreo);

            Transport t = s.getTransport("smtp");

            t.connect("smtp.gmail.com", correo, contra);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
