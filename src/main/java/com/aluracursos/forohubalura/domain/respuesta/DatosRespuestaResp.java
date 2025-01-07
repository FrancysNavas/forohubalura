package com.aluracursos.forohubalura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaResp(Long id,
                                 String autor,
                                 String titulo,
                                 LocalDateTime fechaCreacion,
                                 String mensaje) {
}
