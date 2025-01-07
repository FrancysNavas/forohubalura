package com.aluracursos.forohubalura.domain.topico;

import com.aluracursos.forohubalura.domain.curso.Curso;
import com.aluracursos.forohubalura.domain.curso.DatosCurso;
import com.aluracursos.forohubalura.domain.usuario.DatosRespuestaUsuario;
import com.aluracursos.forohubalura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopico(Long id,
                          @NotBlank
                          String titulo,
                          @NotBlank
                          String mensaje,
                          @NotNull
                          String fechaCreacion,
                          Estado estatus,
                          DatosRespuestaUsuario usuario,
                          @NotNull
                          DatosCurso curso) {
}

