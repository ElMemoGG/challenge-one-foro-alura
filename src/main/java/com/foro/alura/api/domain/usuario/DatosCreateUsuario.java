package com.foro.alura.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosCreateUsuario(
        @NotNull
        String name,

        @NotNull
        @Email
        String email,
        @NotNull
        String password) {
    public DatosCreateUsuario(Usuario usuario) {
        this(usuario.getName(), usuario.getEmail(), usuario.getPassword());
    }
}
