package com.foro.alura.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotNull
        @Email
        String email,
        @NotNull
        String password) {
}
