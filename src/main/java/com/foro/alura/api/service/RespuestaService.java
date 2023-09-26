package com.foro.alura.api.service;

import com.foro.alura.api.domain.respuesta.DatosCrearRespuesta;
import com.foro.alura.api.domain.respuesta.DatosRespuestaRespuesta;
import com.foro.alura.api.domain.respuesta.Respuesta;
import com.foro.alura.api.domain.respuesta.RespuestaRepository;
import com.foro.alura.api.domain.topico.DatosRespuestaTopico;
import com.foro.alura.api.domain.topico.Estatus;
import com.foro.alura.api.domain.topico.TopicoRepository;
import com.foro.alura.api.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public DatosRespuestaRespuesta agregarRespuesta(DatosCrearRespuesta datosCrearRespuesta){
        var topico = topicoRepository.findById(datosCrearRespuesta.idTopico()).get();
        var primeraRespuesta = respuestaRepository.existsByTopicoId(datosCrearRespuesta.idTopico());

        var usuario = usuarioRepository.findById(datosCrearRespuesta.idUsuario()).get();

        if (topico == null){
            throw new ValidationException("No existe un topico con ese id");
        }
        if (usuario == null){
            throw new ValidationException("No existe un usuario con ese id");
        }

        if(!primeraRespuesta){
            /*var topico = topicoRepository.findById(datosCrearRespuesta.idTopico()).get();*/
            topico.actualizarDatos(new DatosRespuestaTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFecha(),
                    Estatus.DISCUSION,
                    topico.getUsuario().getId(),
                    topico.getCurso().getId()));
            topicoRepository.save(topico);
        }

        var respuesta = new Respuesta(null,
                datosCrearRespuesta.mensaje(),
                datosCrearRespuesta.fecha(),
                topico,
                usuario );
        respuestaRepository.save(respuesta);
        return new DatosRespuestaRespuesta(respuesta);
    }

}
