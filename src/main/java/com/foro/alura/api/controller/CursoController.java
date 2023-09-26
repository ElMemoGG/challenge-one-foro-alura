package com.foro.alura.api.controller;


import com.foro.alura.api.domain.curso.*;
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
@ResponseBody
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Curso", description = "")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarMedico(@RequestBody @Valid DatosRespuestaCurso datosregistrocurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso =new Curso(null, datosregistrocurso.nombre(), datosregistrocurso.categoria());
        var newcurso=cursoRepository.save(curso);
        DatosRespuestaCurso respuestaCurso=new DatosRespuestaCurso(newcurso.getNombre(), newcurso.getCategoria());

        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(newcurso.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>>  listadoMedios(@PageableDefault(size = 10) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListadoCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoCurso(curso));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarcurso(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.ok(new DatosListadoCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizaCurso(@PathVariable Long id, @RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarDatos(datosActualizarCurso);

        return ResponseEntity.ok(new DatosRespuestaCurso(
               curso.getNombre(),
                curso.getCategoria()
                ));
    }

}
