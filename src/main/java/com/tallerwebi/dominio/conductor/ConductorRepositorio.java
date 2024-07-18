package com.tallerwebi.dominio.conductor;

public interface ConductorRepositorio {

    Conductor buscarConductorPorId(Integer id);

    void editarConductor(Conductor conductor);
}