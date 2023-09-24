package com.foro.alura.api.domain.usuario;

public record DatosDetalleUsuario(
        Long id,
        String name,
        String email

) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getEmail());
    }
}
