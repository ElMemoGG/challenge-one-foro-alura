package com.foro.alura.api.domain.curso;


import com.foro.alura.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

/*    public Curso(String nombre, String categoria) {
        this.nombre=nombre;
        this.categoria=categoria;

    }*/

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        if(datosActualizarCurso.nombre() != null){
            this.nombre = datosActualizarCurso.nombre();
        }
        if(datosActualizarCurso.categoria() != null){
            this.categoria = datosActualizarCurso.categoria();
        }
    }
}
