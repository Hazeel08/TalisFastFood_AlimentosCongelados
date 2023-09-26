/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum ETipoPago {
    Tarjeta(1),
    Efectivo(2),
    CuentaPorCobrar(3);

    private int cod_tipo_pago;

    private ETipoPago(int cod_tipo_pago) {
        this.cod_tipo_pago = cod_tipo_pago;
    }

    public int getCod_tipo_pago() {
        return cod_tipo_pago;
    }

    public void setCod_tipo_pago(int cod_tipo_pago) {
        this.cod_tipo_pago = cod_tipo_pago;
    }

}
