package com.foro.alura.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaCurso(
        @NotNull
        String nombre,
        @NotNull
        String categoria) {
}
