/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filtros;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.CommonStrings;

/**
 *
 * @author steve

@WebFilter("/faces/*")
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

        // Excepciones para que el filtro no bloquee archivos css, javascript, imagenes etc.
        // Si hay una extensión nueva puede agregarse aqui
        String[] staticResourcesExtensions = {".css", ".js", ".jpg", ".png", ".gif", ".ttf", ".jfif"};
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession sesionActual = request.getSession();
        String loginURL = request.getContextPath() + "/faces/inicioSesion.xhtml";
        String autoRegistroURL = request.getContextPath() + "/faces/autoRegistro.xhtml";
        String datosDireccionURL = request.getContextPath() + "/faces/datosDireccion.xhtml";
        String datosHorarioURL = request.getContextPath() + "/faces/datosHorario.xhtml";
        String pagPrincipalURL = request.getContextPath() + "/faces/pagPrincipal.xhtml";
        String requestURL = request.getRequestURI();
        boolean estaLogeado = sesionActual.getAttribute(CommonStrings.USUARIO_LOGUEADO) != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean autoRegistroRequest = request.getRequestURI().equals(autoRegistroURL);
        boolean datosDireccionquest = request.getRequestURI().equals(datosDireccionURL);
        boolean datosHorarioRequest = request.getRequestURI().equals(datosHorarioURL);
        boolean pagPrincipalRequest = request.getRequestURI().equals(pagPrincipalURL);

        boolean resourceRequest
                = requestURL.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
        for (String staticResource : staticResourcesExtensions) {
            if (requestURL.contains(staticResource)) {
                resourceRequest = true;
            }
        }
        if (estaLogeado || loginRequest || autoRegistroRequest || pagPrincipalRequest || resourceRequest
                || datosDireccionquest || datosHorarioRequest) {
            fc.doFilter(request, response);
        } else {
            sesionActual.setAttribute("MensajeIniciarSesion", CommonStrings.FAVOR_INICIAR_SESION);
            response.sendRedirect(loginURL);
        }
    }
}
 */
