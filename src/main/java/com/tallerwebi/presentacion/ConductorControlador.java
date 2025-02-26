package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.conductor.*;
import com.tallerwebi.dominio.exceptions.*;
import com.tallerwebi.dominio.mercadoPago.MercadoPagoServicio;
import com.tallerwebi.dominio.mercadoPago.MercadoPagoServicioImpl;
import com.tallerwebi.dominio.viaje.Viaje;
import com.tallerwebi.dominio.viaje.ViajeServicio;
import com.tallerwebi.presentacion.Datos.DatosViaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ConductorControlador {
    private ConductorServicio conductorServicio;
    private ViajeServicio viajeServicio;
    private final MercadoPagoServicio mercadoPagoServicio;

    @Autowired
    public ConductorControlador(ConductorServicio conductorServicio, ViajeServicio viajeServicio) {
        this.conductorServicio = conductorServicio;
        this.viajeServicio = viajeServicio;
        this.mercadoPagoServicio = new MercadoPagoServicioImpl();
    }

    @GetMapping("/home-conductor")
    public ModelAndView mostrarHomeConductor(HttpSession session) throws UsuarioNoEncontradoException, CoordenadasNoEncontradasException {
        ModelMap model = new ModelMap();
        String viewName = "home-conductor";
        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        model.put("conductor", conductor);

        if(conductor.getPenalizado()){
            model.put("isPenalizado", conductor.getPenalizado());
            model.put("noVehiculo",false);
            return new ModelAndView(viewName, model);
        }else if(conductor.getVehiculo()!=null){

        List<DatosViaje> viajesCercanosPendientes;

        Double distanciaAFiltrar = (Double) session.getAttribute("distancia");
        viajesCercanosPendientes = this.viajeServicio.filtrarViajesPorDistanciaDelConductor(
                (Double)session.getAttribute("latitud"),
                (Double)session.getAttribute("longitud"),
                distanciaAFiltrar, conductor);

        if (distanciaAFiltrar != null) {
            model.put("seleccionado", distanciaAFiltrar);
        }

        model.put("viajes", viajesCercanosPendientes);
        model.put("cantidadDeViajes", viajesCercanosPendientes.size());
        model.put("noVehiculo",false);

        return new ModelAndView(viewName, model);
        }
        model.put("noVehiculo",true);
        return new ModelAndView(viewName, model);
    }


    @RequestMapping("/historial")
    public ModelAndView mostrarHistorial(HttpSession session) throws UsuarioNoEncontradoException {
        ModelMap model = new ModelMap();

        String viewName = "historial-viajes";
        String claveGoogleMaps = "AIzaSyDcPeOyMBqG_1mZgjpei_R2ficRigdkINg";

        Conductor conductor;

        try {
            conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        } catch (UsuarioNoEncontradoException e) {
            model.put("mensajeError", e.getMessage() + " Por favor, vuelva a intentarlo.");
            return new ModelAndView("redirect:/*", model);
        }

        List<DatosViaje> historialViajes = this.viajeServicio.obtenerHistorialDeViajesConductor(conductor);

        model.put("clave", claveGoogleMaps);
        model.put("conductor", conductor);
        model.put("viajesObtenidos", historialViajes);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(value = "/viaje-aceptado", method = RequestMethod.GET)
    public ModelAndView AceptarViaje(HttpSession session, @RequestParam("idViaje") Integer idViaje) throws UsuarioNoEncontradoException, ViajeNoEncontradoException {
        ModelMap model = new ModelMap();
        String viewName = "viaje";
        String claveGoogleMaps = "AIzaSyDcPeOyMBqG_1mZgjpei_R2ficRigdkINg";

        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        DatosViaje viaje = viajeServicio.obtenerViajeAceptadoPorId(idViaje);

        try {
            this.viajeServicio.aceptarViaje(viaje, conductor);
        } catch (Exception e) {
            model.put("error", "Error al aceptar el viaje");
            return new ModelAndView("viaje", model);
        }

        model.put("clave", claveGoogleMaps);
        model.put("conductor", conductor);
        model.put("viaje", viaje);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(value = "/viajes-en-proceso")
    public ModelAndView verViajesEnProceso(HttpSession session) throws UsuarioNoEncontradoException {
        ModelMap model = new ModelMap();

        String viewName = "viajes-aceptados";
        Conductor conductor;

        try {
            conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        } catch (UsuarioNoEncontradoException e) {
            model.put("mensajeError", e.getMessage() + " Por favor, vuelva a intentarlo.");
            return new ModelAndView("redirect:/*", model);
        }

        List<Viaje> viajesObtenidos = viajeServicio.obtenerViajesEnProceso(conductor);

        model.put("conductor", conductor);
        model.put("viajesObtenidos", viajesObtenidos);
        return new ModelAndView(viewName, model);
    }


    @RequestMapping(value = "/viajeAceptado", method = RequestMethod.GET)
    public ModelAndView verViaje(HttpSession session, @RequestParam("idViaje") Integer idViaje) throws UsuarioNoEncontradoException, ViajeNoEncontradoException {
        ModelMap model = new ModelMap();

        String viewName = "viaje";
        String claveGoogleMaps = "AIzaSyDcPeOyMBqG_1mZgjpei_R2ficRigdkINg";
        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));

        DatosViaje viaje = viajeServicio.obtenerViajeAceptadoPorId(idViaje);

        model.put("clave", claveGoogleMaps);
        model.put("conductor", conductor);
        model.put("viaje", viaje);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping("/cancelar-viaje")
    public ModelAndView cancelarViaje(HttpSession session, @RequestParam("idViaje") Integer idViaje) throws UsuarioNoEncontradoException, ViajeNoEncontradoException {
        Viaje viaje = viajeServicio.obtenerViajePorId(idViaje);
        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        viajeServicio.cancelarViaje(viaje);
        this.conductorServicio.estaPenalizado(conductor);
        return new ModelAndView("redirect:/home-conductor");
    }

    @RequestMapping("/terminar-viaje")
    public ModelAndView terminarViaje(@RequestParam("idViaje") Integer idViaje) throws ViajeNoEncontradoException {
        DatosViaje viaje = viajeServicio.obtenerViajeAceptadoPorId(idViaje);
        viajeServicio.terminarViaje(viaje);
        return new ModelAndView("redirect:/home-conductor");
    }

    @RequestMapping("/volver")
    public ModelAndView volverAlHome(){
        return new ModelAndView("redirect:/ubicacion");
    }

    @RequestMapping("/descartar")
    public ModelAndView descartarViaje(HttpSession session, @RequestParam("idViaje") Integer idViaje) throws UsuarioNoEncontradoException, ViajeNoEncontradoException, ClienteNoEncontradoException {
        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));
        this.viajeServicio.duplicarViajeDescartado(this.viajeServicio.obtenerViajePorId(idViaje), conductor);
        this.conductorServicio.estaPenalizado(conductor);
        return new ModelAndView("redirect:/home-conductor");
    }

    @RequestMapping("/detalle")
    public ModelAndView VerDetalleDelViaje(HttpSession session, @RequestParam("idViaje") Integer idViaje) throws UsuarioNoEncontradoException, ViajeNoEncontradoException {
        ModelMap model = new ModelMap();

        String viewName = "detalle-viaje";
        String claveGoogleMaps = "AIzaSyDcPeOyMBqG_1mZgjpei_R2ficRigdkINg";
        Conductor conductor = conductorServicio.obtenerConductorPorId((Integer) session.getAttribute("IDUSUARIO"));

        DatosViaje viaje = viajeServicio.obtenerViajeAceptadoPorId(idViaje);

        model.put("clave", claveGoogleMaps);
        model.put("conductor", conductor);
        model.put("viaje", viaje);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping("/volver-historial")
    public ModelAndView volverAlHistorial(){
        return new ModelAndView("redirect:/historial");
    }


    @RequestMapping(value = "/filtrarPorDistancia", method = RequestMethod.POST)
    public ModelAndView filtrarPorDistancia(HttpSession session, @RequestParam Double distancia) {
        ModelMap model = new ModelMap();
        if (distancia < 0 || distancia > 10) {
            model.put("mensajeError", "Distancia a filtrar invalida");
            return new ModelAndView("redirect:/*", model);
        }

        session.setAttribute("distancia", distancia);
        return new ModelAndView("redirect:/home-conductor");
    }

    @RequestMapping(path = "/ubicacion")
    public ModelAndView ubicacion(){
        String viewName= "ubicacion";
        return new ModelAndView(viewName);
    }

    @RequestMapping(value = "/despenalizar")
    public ModelAndView despenalizarConductor(@RequestParam("montoPenalizacion") Double montoPenalizacion,
                                              RedirectAttributes redirectAttributes, HttpSession session) {

        if (montoPenalizacion == null || montoPenalizacion < 5000) {
            redirectAttributes.addFlashAttribute("error", "Monto de penalización inválido.");
            return new ModelAndView("redirect:/home-conductor");
        }

        try {
            String redirectUrl = mercadoPagoServicio.pagarPenalizacionMp(montoPenalizacion);

            Conductor conductor = this.conductorServicio.obtenerConductorPorId((Integer)session.getAttribute("IDUSUARIO"));

            this.conductorServicio.despenalizarConductor(conductor);

            return new ModelAndView("redirect:" + redirectUrl);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar el pago: " + e.getMessage());
            return new ModelAndView("redirect:/home-conductor");
        }
    }

    @RequestMapping(value = "/despenalizar-timer")
    public ModelAndView despenalizarConductorPorTimer(HttpSession session) throws UsuarioNoEncontradoException {
        Conductor conductor = this.conductorServicio.obtenerConductorPorId((Integer)session.getAttribute("IDUSUARIO"));
        this.conductorServicio.despenalizarConductor(conductor);
        return new ModelAndView("redirect:/home-conductor");
    }
}