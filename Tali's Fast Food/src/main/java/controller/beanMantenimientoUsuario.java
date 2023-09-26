/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import enums.EEstado;
import enums.ETipoRol;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;
import util.CommonStrings;
import util.ManejadorCorreo;

/**
 *
 * @author steve
 */
public class beanMantenimientoUsuario {

    private int cod_rol;
    private int cod_estado;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;

    private UsuarioDB usuarioDB;
    private RolDB rolDB;
    private EstadoDB estadoDB;
    private List<Usuario> filtradoListaUsuarios;
    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosInactivos;

    public beanMantenimientoUsuario() {
        usuario = new Usuario();
        usuarioSeleccionado = new Usuario();

        rolDB = new RolDB();
        estadoDB = new EstadoDB();

        filtradoListaUsuarios = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        listaUsuariosInactivos = new ArrayList<>();
        usuarioDB = new UsuarioDB();
    }

    public int getCod_rol() {
        return cod_rol;
    }

    public void setCod_rol(int cod_rol) {
        this.cod_rol = cod_rol;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getFiltradoListaUsuarios() {
        return filtradoListaUsuarios;
    }

    public void setFiltradoListaUsuarios(List<Usuario> filtradoListaProductos) {
        this.filtradoListaUsuarios = filtradoListaProductos;
    }

    public void validarIdentificacion(FacesContext context, UIComponent comp, Object value) {
        try {
            String idAInsertar = (String) value;
            if (usuarioDB.getUsuarioPorId(idAInsertar) != null) {
                ((UIInput) comp).setValid(false);
                PrimeFaces.current().executeScript("PF('dialogoAgregar').show()");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(CommonStrings.ERROR, "Identifiación duplicada"), CommonStrings.IDENTIFICACION_DUPLICADO);
                PrimeFaces.current().dialog().showMessageDynamic(message);
            }
        } catch (Exception e) {
        }
    }

    public void validarCorreo(FacesContext context, UIComponent comp, Object value) {
        try {
            String idAInsertar = (String) value;
            if (usuarioDB.getUsuarioPorEmail(idAInsertar) != null) {
                ((UIInput) comp).setValid(false);
                PrimeFaces.current().executeScript("PF('dialogoAgregar').show()");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(CommonStrings.ERROR, "Email duplicado"), CommonStrings.EMAIL_DUPLICADO);
                PrimeFaces.current().dialog().showMessageDynamic(message);
            }
        } catch (Exception e) {
        }
    }

    public boolean validaciones() {
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hay datos nulos, por favor digite la información requerida");
        FacesMessage mensajeEmail = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato del correo es inválido");
        FacesMessage mensajeClave = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formato de la contraseña es inválido");
        FacesMessage mensajeClavesIguales = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas deben ser iguales");
        FacesMessage mensajeCedula = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Formato de cédula inválido");

        String regexPass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^*&+=()/])"
                + "(?=\\S+$).{9,12}$";

        if (usuario.getCedula().isEmpty() || usuario.getNombre().isEmpty() || usuario.getPrimerApellido().isEmpty()
                || usuario.getSegundoApellido().isEmpty() || usuario.getTelefono().isEmpty() || usuario.getEmail().isEmpty()
                || usuario.getClave().isEmpty() || usuario.getConfirmarClave().isEmpty() || usuario.getTipoRol().getId_rol() == 0
                || usuario.getEstado().getCod_estado() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(error);
            return false;
        }

        // Valida que solo se digiten números en la cédula
        if (!usuario.getCedula().matches("[0-9]{9,12}")) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeCedula);
            return false;
        }

        // Valida formato del Email
        if (!usuario.getEmail().trim().matches("[A-Za-z0-9+_.-]+@(.+)$")) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeEmail);
            return false;
        }

        // Valida formato de contraseña
        if (!usuario.getClave().trim().matches(regexPass)) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeClave);
            return false;
        }

        // Valida que ambas contraseñas digitadas sean iguales
        if (!usuario.getClave().equals(usuario.getConfirmarClave())) {
            PrimeFaces.current().dialog().showMessageDynamic(mensajeClavesIguales);
            return false;
        }

        return true;
    }

    public List<Usuario> getListadoUsuarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            listaUsuarios = usuarioDB.getUsuarios();
        } catch (Exception e) {
        }
        return listaUsuarios;
    }

    public List<Usuario> getListadoUsuariosInactivos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            listaUsuariosInactivos = usuarioDB.getUsuariosInactivos();
        } catch (Exception e) {
        }
        return listaUsuariosInactivos;
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

    public LinkedList<Estado> getListadoEstadoUsuarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        int cod_estado = 0;
        String dsc_estado = "";
        LinkedList<Estado> listaEstadoUsuarios = new LinkedList<>();
        LinkedList resultLista = new LinkedList<>();

        resultLista.add(new SelectItem(0,
                "-- SELECCIONE EL ESTADO --"));
        try {
            listaEstadoUsuarios = estadoDB.listadoEstadoUsuarios();

            for (Iterator iter = listaEstadoUsuarios.iterator();
                    iter.hasNext();) {
                Estado estado = (Estado) iter.next();
                cod_estado = estado.getCod_estado();
                dsc_estado = estado.getEstado() == 1 ? "Pendiente" : estado.getEstado() == 2 ? "Activo" : "Inactivo";

                resultLista.add(new SelectItem(cod_estado,
                        dsc_estado));
            }

        } catch (Exception e) {
        }
        return resultLista;
    }

    public void guardarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", "Datos guardados exitosamente");
        try {
            for (ETipoRol eTipoRol : ETipoRol.values()) {
                if (eTipoRol.getId_rol() == cod_rol) {
                    usuario.setTipoRol(eTipoRol);
                }
            }

            for (EEstado eEstado : EEstado.values()) {
                if (eEstado.getCod_estado() == cod_estado) {
                    usuario.setEstado(eEstado);
                }
            }

            if (validaciones()) {
                usuarioDB.insertarUsuario(usuario);
                PrimeFaces.current().ajax().update(":formListadoUsuarios:tablaUsuarios");
                PrimeFaces.current().dialog().showMessageDynamic(mensaje);
                usuario = new Usuario();
            } else {
                PrimeFaces.current().executeScript("PF('dialogoAgregar').show()");
            }
        } catch (Exception e) {
        }
    }

    public void inactivarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Usuario inactivado exitosamente");
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un usuario de la lista");
        try {
            if (usuarioSeleccionado != null) {
                if (ManejadorCorreo.correoMotivoInactividad(usuarioSeleccionado)) {
                    usuarioDB.eliminarUsuario(usuarioSeleccionado);
                    PrimeFaces.current().dialog().showMessageDynamic(mensaje);
                }
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(error);
            }
        } catch (Exception e) {
        }
    }

    public void activarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Usuario activado exitosamente");
        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un usuario de la lista");
        try {
            if (usuarioSeleccionado != null) {
                if (ManejadorCorreo.correoBienvenida(usuarioSeleccionado)) {
                    usuarioDB.activarUsuario(usuarioSeleccionado);
                    PrimeFaces.current().dialog().showMessageDynamic(mensaje);
                }
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(error);
            }
        } catch (Exception e) {
        }
    }

}
