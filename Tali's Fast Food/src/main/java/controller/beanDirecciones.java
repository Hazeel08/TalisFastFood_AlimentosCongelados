/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.LinkedList;
import java.util.List;
import model.AutoRegistroInMemory;
import model.DireccionDB;
import model.Direccion;

/**
 *
 * @author steve
 */
public class beanDirecciones {

    public beanDirecciones() {
    }

    public List<Direccion> getListadoDescripciones() {
        return AutoRegistroInMemory.direcciones;
    }

}
