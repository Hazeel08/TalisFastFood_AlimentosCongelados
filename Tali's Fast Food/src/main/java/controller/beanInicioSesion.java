/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.*;
import util.*;

/**
 *
 * @author steve
 */
@Named(value = "beanInicioSesion")
@SessionScoped
public class beanInicioSesion implements Serializable {

    private InicioSesion inicioSesion;
    private Rol rol;
    private Usuario usuario;

    private Usuario usuarioLogueado;

    private UsuarioDB oUsuarioDB;
    private RolDB rolDB;
    private InicioSesionDB inicioSesionDB;

    public beanInicioSesion() {
        inicioSesion = new InicioSesion();
        rol = new Rol();

        oUsuarioDB = new UsuarioDB();
        inicioSesionDB = new InicioSesionDB();
        rolDB = new RolDB();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        usuario = usuario;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuraioLogueado) {
        this.usuarioLogueado = usuraioLogueado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public InicioSesion getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(InicioSesion inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

    public LinkedList<Rol> getListadoRoles() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        int cod_rol = 0;
        String tipo_rol = "";
        LinkedList<Rol> listaRoles = new LinkedList<>();
        LinkedList resultLista = new LinkedList<>();

        resultLista.add(new SelectItem(0,
                "-- SELECCIONE EL ROL --"));
        try {
            listaRoles = rolDB.listadoRoles();

            for (Iterator iter = listaRoles.iterator();
                    iter.hasNext();) {
                Rol oRol = (Rol) iter.next();
                cod_rol = oRol.getCod_rol();
                tipo_rol = oRol.getTipo_rol();

                resultLista.add(new SelectItem(cod_rol,
                        tipo_rol));
            }

        } catch (Exception e) {
        }
        return resultLista;
    }

    public void paginaEnCarga() {
        String mensajeDelContexto = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MensajeIniciarSesion");
        if (mensajeDelContexto != null) {
            MessageHelper.addErrorMessage(mensajeDelContexto);
        }

        if (usuarioLogueado != null) {
            // Si el usuario está logueado hará la redirección al Menú Administrador
            if (usuarioLogueado.getTipoRol().Administrador.getId_rol() == 1) {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    ec.redirect(ec.getRequestContextPath()
                            + "/faces/mantenProductos.xhtml");
                } catch (IOException e) {
                }
            }

            // Si el usuario está logueado hará la redirección al Menú Cliente
            if (usuarioLogueado.getTipoRol().Cliente.getId_rol() == 2) {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    ec.redirect(ec.getRequestContextPath()
                            + "/faces/catalogoProductos.xhtml");
                } catch (IOException e) {
                }
            }

            // Si el usuario está logueado hará la redirección al Menú Administrador
            if (usuarioLogueado.getTipoRol().Despachador.getId_rol() == 3) {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    ec.redirect(ec.getRequestContextPath()
                            + "/faces/ordenesDespacho.xhtml");
                } catch (IOException e) {
                }
            }
        }
    }

    public String validarCredenciales() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String email = this.getInicioSesion().getEmail();
        String clave = this.getInicioSesion().getClave();
        int tipoRol = this.getRol().getCod_rol();
        try {
            usuario = inicioSesionDB.esInicioSesionCorrecto(email, clave, tipoRol);
            if (usuario != null) {
                // Inicio de sesión del administrador
                if (usuario.getTipoRol().Administrador.getId_rol() == tipoRol) {
                    MessageHelper.addSuccessMessage(CommonStrings.BIENVENIDO_ADMINISTRADOR);
                    SessionHelper.addSession(CommonStrings.USUARIO_LOGUEADO, usuario);
                    this.setUsuarioLogueado((Usuario) SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO));
                    return "mantenProductos.xhtml";
                }
                // Inicio de sesión del cliente
                if (usuario.getTipoRol().Cliente.getId_rol() == tipoRol) {
                    MessageHelper.addSuccessMessage(CommonStrings.BIENVENIDO_USUARIO);
                    SessionHelper.addSession(CommonStrings.USUARIO_LOGUEADO, usuario);
                    this.setUsuarioLogueado((Usuario) SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO));
                    return "catalogoProductos.xhtml";
                }
                // Inicio de sesión del despachador
                if (usuario.getTipoRol().Despachador.getId_rol() == tipoRol) {
                    MessageHelper.addSuccessMessage(CommonStrings.BIENVENIDO_DESPACHADOR);
                    SessionHelper.addSession(CommonStrings.USUARIO_LOGUEADO, usuario);
                    this.setUsuarioLogueado((Usuario) SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO));
                    return "ordenesDespacho.xhtml";
                }
            } else {
                MessageHelper.addErrorMessage(CommonStrings.LOGIN_INCORRECTO);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String cerrarSesion() {
        SessionHelper.invalidateSession();
        return "inicioSesion.xhtml";
    }
}
