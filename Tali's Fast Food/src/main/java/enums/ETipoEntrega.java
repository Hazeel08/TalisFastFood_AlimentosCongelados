/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum ETipoEntrega {
    EnvioDirecto(1),
    SinEnvio(2),
    Retiro(3),
    Encomienda(4);

    private int id_entrega;

    private ETipoEntrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }
}
