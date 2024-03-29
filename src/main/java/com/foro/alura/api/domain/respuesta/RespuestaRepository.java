package com.foro.alura.api.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    boolean existsByTopicoId(Long idTopico);

    Page<Respuesta> getReferenceByTopicoId(Pageable paginacion, Long idTopico);
}
