package com.foro.alura.api.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Estatus estatus,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso

) {
}
