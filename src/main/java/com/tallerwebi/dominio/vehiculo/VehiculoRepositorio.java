package com.tallerwebi.dominio.vehiculo;

public interface VehiculoRepositorio {

    Vehiculo buscarVehiculoPorPatente(String patente);

    Vehiculo guardarVehiculo(Vehiculo vehiculo);

    void editar(Vehiculo vehiculo);
}