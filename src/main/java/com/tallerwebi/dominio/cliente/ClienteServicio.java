package com.tallerwebi.dominio.cliente;

import com.tallerwebi.dominio.exceptions.UsuarioNoEncontradoException;

public interface ClienteServicio {
    Cliente obtenerClientePorId(Integer idusuario) throws UsuarioNoEncontradoException;
}
