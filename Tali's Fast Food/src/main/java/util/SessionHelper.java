/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author steve
 */
public class SessionHelper {

    public static void addSession(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public static Object getSessionByKey(String key) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> session = context.getSessionMap();
        return session.get(key);
    }

    public static void invalidateSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
    }
}
