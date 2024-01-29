package com.foro.alura.api.service;

import com.foro.alura.api.domain.curso.CursoRepository;
import com.foro.alura.api.domain.topico.DatosCrearTopico;
import com.foro.alura.api.domain.topico.DatosRespuestaTopico;
import com.foro.alura.api.domain.topico.Topico;
import com.foro.alura.api.domain.topico.TopicoRepository;
import com.foro.alura.api.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaTopico agregarTopico(DatosCrearTopico datos){
        checkDuplicate(new DatosRespuestaTopico(datos));

        var curso = cursoRepository.findById(datos.idCurso()).get();

        if(curso == null){
            throw new ValidationException("No existe un curso con ese id");
        }
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), datos.fecha(), datos.estatus(), usuario, curso);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }


    public DatosRespuestaTopico actualizarTopico(DatosRespuestaTopico datos, Long id){
        //checkDuplicate(datos);

        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datos);

        return new DatosRespuestaTopico(topico);
    }



    public void checkDuplicate(DatosRespuestaTopico datos){
        if(topicoRepository.existsByTitulo(datos.titulo())){
            throw new ValidationException("El titulo de este topico ya existe");
        }
        if(topicoRepository.existsByMensaje(datos.mensaje())){
            throw new ValidationException("El mensaje de este topico ya existe");
        }
    }
}
