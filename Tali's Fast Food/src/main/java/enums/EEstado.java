/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum EEstado {
    Pendiente(1),
    Activo(2),
    Inactivo(3);

    private int cod_estado;

    private EEstado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

}
