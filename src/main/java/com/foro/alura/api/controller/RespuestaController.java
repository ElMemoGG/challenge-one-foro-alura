package com.foro.alura.api.controller;


import com.foro.alura.api.domain.respuesta.RespuestaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    RespuestaRepository respuestaRepository;


}
