package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.enums.TipoUsuario;
import com.tallerwebi.dominio.usuario.Usuario;
import com.tallerwebi.presentacion.Datos.DatosLogin;
import com.tallerwebi.dominio.login.LoginServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
public class LoginControlador {

    private LoginServicio loginServicio;

    @Autowired
    public LoginControlador(LoginServicio loginServicio){
        this.loginServicio = loginServicio;
    }

    @RequestMapping("/")
    public ModelAndView error1(HttpSession session) {
        return this.mostrarHome(session);
    }


    @RequestMapping("/*")
    public ModelAndView error(String mensajeError) {
        ModelMap model = new ModelMap();
        String viewName= "error";
        if(mensajeError!=null){
            model.put("error", mensajeError);
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/home")
    public ModelAndView mostrarHome(HttpSession session){
        String viewName= "home";
        return new ModelAndView(viewName);
    }


    @RequestMapping(path = "/login")
    public ModelAndView mostrarLogin(String mensajeError){
        ModelMap model = new ModelMap();
        String viewName= "login";

        if(mensajeError!=null){
            model.put("error", mensajeError);
        }
        model.put("datosLogin", new DatosLogin());
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path="/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLoginnConductor, HttpSession session) {
        ModelMap model = new ModelMap();

        Usuario usuario = loginServicio.consultarUsuario(datosLoginnConductor.getUsuario(), datosLoginnConductor.getPassword());

            if (usuario != null) {
                session.setAttribute("IDUSUARIO", usuario.getId());
                session.setAttribute("tipoUsuario", usuario.getTipoUsuario());
                session.setAttribute("isEditForm", false);
                session.setAttribute("estaLogeado",true);
                model.put("correcto", "Usuario o clave correcta");
                if(usuario.getTipoUsuario().equals(TipoUsuario.conductor)){
                    return new ModelAndView("ubicacion", model);
                }else{
                    return new ModelAndView("redirect:/home-cliente", model);
                }
            }else{
                String mensajeError= "Usuario o clave incorrecta";
                return this.mostrarLogin(mensajeError);
            }
    }

    @RequestMapping(path = "/cerrar-sesion")
    public ModelAndView cerrarSesion(HttpSession session) {
        session.invalidate();
        return mostrarHome(session);
    }
}