package com.foro.alura.api.controller;


import com.foro.alura.api.domain.curso.DatosListadoCurso;
import com.foro.alura.api.domain.curso.DatosRespuestaCurso;
import com.foro.alura.api.domain.respuesta.DatosCrearRespuesta;
import com.foro.alura.api.domain.respuesta.DatosDetalleRespuesta;
import com.foro.alura.api.domain.respuesta.DatosRespuestaRespuesta;
import com.foro.alura.api.domain.respuesta.RespuestaRepository;
import com.foro.alura.api.domain.topico.DatosCrearTopico;
import com.foro.alura.api.domain.topico.DatosDetalleTopico;
import com.foro.alura.api.domain.topico.DatosRespuestaTopico;
import com.foro.alura.api.service.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuesta", description = "")
public class RespuestaController {

    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosCrearRespuesta datosCrearRespuesta, UriComponentsBuilder uriComponentsBuilder){
        var response = respuestaService.agregarRespuesta(datosCrearRespuesta);
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar respuestas",
            description = "",
            tags = { "Respuesta"/*, "post"*/ }
            )
    @Parameter(name="topico",  description = "Id topico")
    public ResponseEntity<Page<DatosRespuestaRespuesta>>  listadoRespuestas(@PageableDefault(size = 10) Pageable paginacion,
                                                                            @RequestParam(required = false) Long topico){
        if (topico != null){
            return ResponseEntity.ok(respuestaRepository.getReferenceByTopicoId(paginacion, topico).map(DatosRespuestaRespuesta::new));
        }
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosRespuestaRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizaRespuesta(@PathVariable Long id, @RequestBody @Valid DatosRespuestaRespuesta datosRespuestaRespuesta){
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarDatos(datosRespuestaRespuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
    }


}
