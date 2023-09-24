package com.foro.alura.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Estatus estatus,
        Long idUsuario,
        Long idCurso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getEstatus(), topico.getUsuario().getId(), topico.getCurso().getId());
    }

    public DatosRespuestaTopico(DatosCrearTopico topico) {
        this( null, topico.titulo(), topico.mensaje(), topico.fecha(), topico.estatus(), topico.idUsuario(), topico.idCurso());
    }
}
