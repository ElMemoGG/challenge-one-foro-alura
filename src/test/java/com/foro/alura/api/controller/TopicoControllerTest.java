package com.foro.alura.api.controller;

import com.foro.alura.api.domain.topico.DatosCrearTopico;
import com.foro.alura.api.domain.topico.DatosDetalleTopico;
import com.foro.alura.api.domain.topico.DatosRespuestaTopico;
import com.foro.alura.api.domain.topico.Estatus;
import com.foro.alura.api.service.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    TopicoService topicoService;

    @Autowired
    private JacksonTester<DatosCrearTopico> crearTopicojacksonTester;

    @Autowired
    private JacksonTester<DatosRespuestaTopico> respuestaTopicojacksonTester;


    @Test
    @DisplayName("deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void registrarTopicoEsenario1() throws Exception {
        // give when
        var response = mvc.perform(post("/topico")).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void registrarTopicoEsenario2() throws Exception {
        // give
        var estatus = Estatus.NUEVO;
        var fecha = LocalDateTime.now();
        var datos = new DatosRespuestaTopico(null, "new", "content", fecha, estatus, 1L, 1L);
        // when
        when(topicoService.agregarTopico(any())).thenReturn(datos);

        var response = mvc.perform(post("/topico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(crearTopicojacksonTester.write(new DatosCrearTopico("new","content",fecha,estatus,1L,1L)).getJson())
        ).andReturn().getResponse();


        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var jsonEsperado = respuestaTopicojacksonTester.write(datos).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}