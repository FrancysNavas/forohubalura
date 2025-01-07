package com.aluracursos.forohubalura.domain.topico;

import com.aluracursos.forohubalura.domain.curso.Curso;
import com.aluracursos.forohubalura.domain.usuario.DatosRespuestaUsuario;
import com.aluracursos.forohubalura.domain.usuario.Usuario;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaCreacion,
                                   DatosRespuestaUsuario usuario) {


   }

