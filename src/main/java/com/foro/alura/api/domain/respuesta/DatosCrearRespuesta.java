package com.foro.alura.api.domain.respuesta;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearRespuesta(

        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Long idTopico,
        @NotNull
        Long idUsuario
) {
}
