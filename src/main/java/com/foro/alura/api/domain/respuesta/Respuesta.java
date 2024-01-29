package com.foro.alura.api.domain.respuesta;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foro.alura.api.domain.topico.Topico;
import com.foro.alura.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
    //private boolean solucionado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    /*@JsonManagedReference(value = "usuario_id")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void actualizarDatos(DatosRespuestaRespuesta datos) {
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
    }
}
