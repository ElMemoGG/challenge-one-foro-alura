package com.foro.alura.api.domain.topico;

import com.foro.alura.api.domain.curso.Curso;
import com.foro.alura.api.domain.curso.DatosListadoCurso;
import com.foro.alura.api.domain.usuario.DatosDetalleUsuario;
import com.foro.alura.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,

        String mensaje,

        LocalDateTime fecha,

        Estatus estatus,

        DatosDetalleUsuario usuario,

        DatosListadoCurso curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getEstatus(), new DatosDetalleUsuario(topico.getUsuario()), new DatosListadoCurso(topico.getCurso()) );
    }
}
