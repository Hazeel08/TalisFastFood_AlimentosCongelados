/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

import model.*;

/**
 *
 * @author steve
 */
public class SingletonUsuario {

    private static Usuario usuarioLogueado;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuarioLogueado) {
        SingletonUsuario.usuarioLogueado = usuarioLogueado;
    }
}
