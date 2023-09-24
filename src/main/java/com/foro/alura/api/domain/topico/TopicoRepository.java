package com.foro.alura.api.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean findByTitulo(String titulo);

    boolean findByMensaje(String mensaje);

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);
}
