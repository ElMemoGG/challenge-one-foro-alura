package com.foro.alura.api.domain.respuesta;

import com.foro.alura.api.domain.topico.DatosDetalleTopico;
import com.foro.alura.api.domain.usuario.DatosDetalleUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        DatosDetalleTopico idTopico,
        DatosDetalleUsuario idUsuario

) {

    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha(), new DatosDetalleTopico(respuesta.getTopico()), new DatosDetalleUsuario(respuesta.getUsuario()) );
    }
}
