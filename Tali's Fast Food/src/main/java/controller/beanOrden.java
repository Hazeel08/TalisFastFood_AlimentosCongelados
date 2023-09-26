/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SNMPExceptions;
import enums.EEstado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.*;
import org.primefaces.PrimeFaces;
import util.CommonStrings;
import util.SessionHelper;

/**
 *
 * @author steve
 */
public class beanOrden {

    private int cod_horario;
    private Usuario usuarioLogueado;
    private Orden orden;

    private Direccion direccionSeleccionada;

    // Atributos BD
    OrdenDB ordenDB;
    ProductoDB productoDB;
    DireccionDB direccionDB;
    ProvinciaDB proDB;
    CantonDB canDB;
    DistritoDB disDB;
    BarrioDB barDB;
    HorarioDB horarioDB;

    public beanOrden() {
        usuarioLogueado = new Usuario();
        orden = new Orden();
        direccionSeleccionada = new Direccion();

        // Instancias BD
        ordenDB = new OrdenDB();
        productoDB = new ProductoDB();
        direccionDB = new DireccionDB();
        proDB = new ProvinciaDB();
        canDB = new CantonDB();
        disDB = new DistritoDB();
        barDB = new BarrioDB();
        horarioDB = new HorarioDB();
    }

    public int getCod_horario() {
        return cod_horario;
    }

    public void setCod_horario(int cod_horario) {
        this.cod_horario = cod_horario;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Direccion getDireccionSeleccionada() {
        return direccionSeleccionada;
    }

    public void setDireccionSeleccionada(Direccion direccionSeleccionada) {
        this.direccionSeleccionada = direccionSeleccionada;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Usuario obtenerUsuarioLogueado() {
        this.usuarioLogueado = (Usuario) SessionHelper.getSessionByKey(CommonStrings.USUARIO_LOGUEADO);
        this.getOrden().setUsuario(usuarioLogueado);
        return usuarioLogueado;
    }

    public List<Direccion> getListaDirecciones() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        Direccion oDireccion = new Direccion();
        List<Direccion> lista = new ArrayList<>();
        List<Direccion> listaDirecciones = new ArrayList<>();
        oDireccion.setUsuario(obtenerUsuarioLogueado());

        try {
            listaDirecciones = direccionDB.getDirecciones(oDireccion);

            for (Iterator iter = listaDirecciones.iterator();
                    iter.hasNext();) {

                Direccion dir = (Direccion) iter.next();
                int cod_direccion = dir.getCodigo();
                float provincia = dir.getProvincia().getCod_provincia();
                float canton = dir.getCanton().getCod_canton();
                float distrito = dir.getDistrito().getCod_distrito();
                float barrio = dir.getBarrio().getCod_barrio();
                boolean esPrincipal = dir.isEsDireccionPrincipal();
                String otrasSenas = dir.getOtrasSennias();

                oDireccion.setCodigo(cod_direccion);
                oDireccion.setProvincia(proDB.getProvinciaPorId(provincia));
                oDireccion.setCanton(canDB.getCantonPorId(provincia, canton));
                oDireccion.setDistrito(disDB.getDistritoPorId(provincia, canton, distrito));
                oDireccion.setBarrio(barDB.getBarrioById(provincia, canton, distrito, barrio));
                oDireccion.setOtrasSennias(otrasSenas);
                oDireccion.setEsDireccionPrincipal(esPrincipal);

                lista.add(oDireccion);
            }

        } catch (Exception ex) {
        }
        return lista;
    }

    public List<SelectItem> getlistaHorarios() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String fecha_hora = null;
        int cod_horario = 0;

        LinkedList resultList = new LinkedList();
        List<Horario> lista = new ArrayList<>();
        Horario horario = new Horario();
        horario.setUsuario(obtenerUsuarioLogueado());

        try {
            lista = horarioDB.getHorarios(horario);

            resultList.add(new SelectItem(0,
                    "-- Seleccione --"));

            for (Iterator iter = lista.iterator();
                    iter.hasNext();) {

                Horario hor = (Horario) iter.next();
                fecha_hora = hor.getFormatoFechaYHora();
                cod_horario = hor.getCod_horario();
                resultList.add(new SelectItem(cod_horario,
                        fecha_hora));
            }
        } catch (Exception e) {
        }
        return resultList;
    }

    public boolean hayNulos() {
        FacesMessage errorDir = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una dirección");
        FacesMessage errorHor = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un horario");

        if (this.cod_horario == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(errorHor);
            return true;
        }

        if (direccionSeleccionada == null) {
            PrimeFaces.current().dialog().showMessageDynamic(errorDir);
            return true;
        }
        return false;
    }

    public String guardarOrden() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        try {
            orden.setHorario(horarioDB.obtenerHorarioPorId(cod_horario));
            orden.setDireccion(direccionSeleccionada);
            orden.setLog_activo(EEstado.Pendiente);
            if (!hayNulos()) {
                AutoRegistroInMemory.agregarOrden(orden);
                return "detalleOrden.xhtml";
            }
        } catch (Exception e) {
        }
        return "";
    }

}
