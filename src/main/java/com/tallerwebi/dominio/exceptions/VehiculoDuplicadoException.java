package com.tallerwebi.dominio.exceptions;

public class VehiculoDuplicadoException extends Exception{

    private final String mensajeError;
    public VehiculoDuplicadoException(String message) {
        this.mensajeError=message;

    }

    @Override
    public String getMessage() {
        return mensajeError;
    }
}