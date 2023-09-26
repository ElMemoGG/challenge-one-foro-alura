package com.foro.alura.api.domain.respuesta;

import com.foro.alura.api.domain.usuario.DatosDetalleUsuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        Long idTopico,
        DatosDetalleUsuario idUsuario
) {
    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha(),respuesta.getTopico().getId(), new DatosDetalleUsuario(respuesta.getUsuario()));

    }
}
