package com.aluracursos.forohubalura.domain.topico;

import com.aluracursos.forohubalura.domain.curso.Curso;
import com.aluracursos.forohubalura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record DatosRegistroTopico(String titulo,
                                  String mensaje,
                                  LocalDateTime fechaCreacion,
                                  Long usuarioId,
                                  Long cursoId,
                                  Estado estatus) {}