/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author steve
 */
public class Producto {
    
    private int cod_producto;
    private String nombre;
    private String dsc_producto;
    private int cantidad_minima;
    private int cantidad_existencia;
    private int log_activo;
    private float precio;
    private String url_fotografia;
    
    public Producto() {
 
    }
    
    public int getCod_producto() {
        return cod_producto;
    }
    
    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDsc_producto() {
        return dsc_producto;
    }
    
    public void setDsc_producto(String dsc_producto) {
        this.dsc_producto = dsc_producto;
    }
    
    public int getCantidad_minima() {
        return cantidad_minima;
    }
    
    public void setCantidad_minima(int cantidad_minima) {
        this.cantidad_minima = cantidad_minima;
    }
    
    public int getCantidad_existencia() {
        return cantidad_existencia;
    }
    
    public void setCantidad_existencia(int cantidad_existencia) {
        this.cantidad_existencia = cantidad_existencia;
    }
    
    public int getLog_activo() {
        return log_activo;
    }
    
    public void setLog_activo(int log_activo) {
        this.log_activo = log_activo;
    }
    
    public float getPrecio() {
        return precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public String getUrl_fotografia() {
        return url_fotografia;
    }
    
    public void setUrl_fotografia(String url_fotografia) {
        this.url_fotografia = url_fotografia;
    }
    
}
