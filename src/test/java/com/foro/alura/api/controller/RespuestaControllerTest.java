package com.foro.alura.api.controller;

import com.foro.alura.api.domain.respuesta.DatosCrearRespuesta;
import com.foro.alura.api.domain.respuesta.DatosRespuestaRespuesta;
import com.foro.alura.api.domain.topico.DatosCrearTopico;
import com.foro.alura.api.domain.topico.Estatus;
import com.foro.alura.api.domain.topico.TopicoRepository;
import com.foro.alura.api.domain.usuario.Usuario;
import com.foro.alura.api.service.RespuestaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RespuestaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RespuestaService respuestaService;

    @Autowired
    private JacksonTester<DatosCrearRespuesta> crearRespuestaJacksonTester;

    @Autowired
    private JacksonTester<DatosRespuestaRespuesta> respuestaRespuestaJacksonTester;

    @Autowired
    private TopicoRepository topicoRepository;


    @Autowired
    private JacksonTester<DatosCrearTopico> crearTopicojacksonTester;


    @Test
    @DisplayName("deberia retornar estado http 201 cuando los datos ingresados son validos")
    @WithMockUser
    void registrarRespuesta() throws Exception {
        //give
        var fecha = LocalDateTime.now();

        var datos = new DatosRespuestaRespuesta(null, "messaje", fecha, 1L, null);

        //when
        when(respuestaService.agregarRespuesta(any())).thenReturn(datos);

   /*     var response2 = mvc.perform(post("/topico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(crearTopicojacksonTester.write(new DatosCrearTopico("new","content",fecha, Estatus.NUEVO,1L,1L)).getJson())
        ).andReturn().getResponse();

        System.out.println(response2.getContentAsByteArray());*/

        var response = mvc.perform(post("/respuesta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(crearRespuestaJacksonTester.write(new DatosCrearRespuesta("messaje",fecha,1L,1L)).getJson())
        ).andReturn().getResponse();



        //then

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var jsonEsperado = respuestaRespuestaJacksonTester.write(datos).getJson();
        //var topicCheck = topicoRepository.getReferenceById(1L);

        //assertThat(topicCheck.).isEqualTo(Estatus.DISCUSION);


        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);


    }


}