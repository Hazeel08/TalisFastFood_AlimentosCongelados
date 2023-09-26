/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class AutoRegistroInMemory {

    public static List<Direccion> direcciones = new ArrayList<Direccion>();
    public static List<Horario> horarios = new ArrayList<Horario>();
    public static List<DetalleOrden> detallesOrdenes = new ArrayList<DetalleOrden>();
    public static List<Orden> listaOrden = new ArrayList<Orden>();
    private static Usuario oUsuario;
    private static Orden orden;

    public static Usuario agregarUsuario(Usuario pUsuario) {
        oUsuario = pUsuario;
        return oUsuario;
    }

    public static Usuario getoUsuario() {
        return oUsuario;
    }

    public static void agregarOrden(Orden pOrden) {
        orden = pOrden;
        listaOrden.add(orden);
    }

    public static Orden getOrden() {
        return orden;
    }

    // Agrega a la lista direcciones un objeto de tipo Direccion
    public static void agregarDireccion(Direccion pDireccion) {
        direcciones.add(pDireccion);
    }

    // Elimina un objeto direccion
    public static void eliminarDireccion(Direccion pDireccion) {
        direcciones.remove(pDireccion);
    }

    // Agrega a la lista horarios un objeto de tipo Horario
    public static void agregarHorario(Horario pHorario) {
        horarios.add(pHorario);
    }

    // Elimina un objeto horario
    public static void eliminarHorario(Horario pHorario) {
        horarios.remove(pHorario);
    }

    // Agrega a la lista direcciones un objeto de tipo Direccion
    public static void agregarDetalleOrden(DetalleOrden pDetalleOrden) {
        detallesOrdenes.add(pDetalleOrden);
    }

    // Elimina un objeto direccion
    public static void eliminarDetalleOrden(DetalleOrden pDetalleOrden) {
        detallesOrdenes.remove(pDetalleOrden);
    }
}
