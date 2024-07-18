package com.tallerwebi.dominio.usuario;

public interface UsuarioRepositorio {

    Usuario buscarDuplicados(String email, String nombreUsuario);

    Usuario guardarUsuario(Usuario usuario);

    void editarUsuario(Usuario usuario);

    Usuario getUsuarioById(Integer id);

}