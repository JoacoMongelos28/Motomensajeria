package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.exceptions.UsuarioNoEncontradoException;
import com.tallerwebi.dominio.usuario.Usuario;
import com.tallerwebi.dominio.usuario.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CompaniaControlador {

    private UsuarioServicio usuarioServicio;

    @Autowired
    public CompaniaControlador(UsuarioServicio usuarioServicio){

        this.usuarioServicio = usuarioServicio;
    }

    @RequestMapping("/compania")
    public ModelAndView mostrarVistaCompania(HttpServletRequest request) throws UsuarioNoEncontradoException {
        ModelMap model = new ModelMap();
        Usuario usuario;

        if(request.getSession().getAttribute("IDUSUARIO") != null){
            usuario= usuarioServicio.obtenerUsuarioPorId((Integer)request.getSession().getAttribute("IDUSUARIO"));
                model.put("usuario", usuario);
        }else{
            model.put("usuario", null);
        }

        return new ModelAndView("compania", model);
    }
}