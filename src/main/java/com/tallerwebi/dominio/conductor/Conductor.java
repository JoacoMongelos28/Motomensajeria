package com.tallerwebi.dominio.conductor;

import com.tallerwebi.dominio.enums.TipoUsuario;
import com.tallerwebi.dominio.usuario.Usuario;
import com.tallerwebi.dominio.vehiculo.Vehiculo;
import com.tallerwebi.dominio.viaje.Viaje;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Conductor extends Usuario {

    @Column(name = "isPenalizado")
    private Boolean isPenalizado;

    @Column(name = "horaPenalizacion")
    private LocalDateTime horaPenalizacion;

    @Column(name = "cantPenalizacion")
    private Integer cantPenalizacion;

    @Column(name = "montoPenalizacion")
    private Double montoPenalizacion;

    @OneToMany(mappedBy = "conductor")
    private List<Viaje> viajes;

    @OneToOne
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;

    public Conductor() {

    }

    public Conductor(String nombre, String apellido, Integer numeroDeDni, String email, String numeroDeTelefono, String nombreUsuario, String password, String domicilio, TipoUsuario tipoUsuario, Boolean isPenalizado, LocalDateTime horaPenalizacion, Integer cantPenalizacion, List<Viaje> viajes, Vehiculo vehiculo) {
        super(nombre, apellido, numeroDeDni, email, numeroDeTelefono, nombreUsuario, password, domicilio, tipoUsuario);
        this.isPenalizado = isPenalizado;
        this.horaPenalizacion = horaPenalizacion;
        this.cantPenalizacion = cantPenalizacion;
        this.viajes = viajes;
        this.vehiculo = vehiculo;
    }

    public Boolean getPenalizado() {
        return isPenalizado;
    }

    public void setPenalizado(Boolean penalizado) {
        isPenalizado = penalizado;
    }

    public LocalDateTime getHoraPenalizacion() {
        return horaPenalizacion;
    }

    public void setHoraPenalizacion(LocalDateTime horaPenalizacion) {
        this.horaPenalizacion = horaPenalizacion;
    }

    public Integer getCantPenalizacion() {
        return cantPenalizacion;
    }

    public void setCantPenalizacion(Integer cantPenalizacion) {
        this.cantPenalizacion = cantPenalizacion;
    }

    public Double getMontoPenalizacion() {
        return montoPenalizacion;
    }

    public void setMontoPenalizacion(Double montoaPagarPorPenalizacion) {
        this.montoPenalizacion = montoaPagarPorPenalizacion;
    }

    @Override
    public List<Viaje> getViajes() {
        return viajes;
    }

    @Override
    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}