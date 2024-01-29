package com.foro.alura.api.controller;


import com.foro.alura.api.domain.curso.DatosListadoCurso;
import com.foro.alura.api.domain.topico.DatosCrearTopico;
import com.foro.alura.api.domain.topico.DatosDetalleTopico;
import com.foro.alura.api.domain.topico.DatosRespuestaTopico;
import com.foro.alura.api.domain.topico.TopicoRepository;
import com.foro.alura.api.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topico", description = "")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico, UriComponentsBuilder uriComponentsBuilder){
        var response = topicoService.agregarTopico(datosCrearTopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizaTopico(@PathVariable Long id, @RequestBody @Valid DatosRespuestaTopico datosRespuestaTopico){
        var response = topicoService.actualizarTopico(datosRespuestaTopico, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>>  listadoTopicos(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC )  Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
}
