package com.aluracursos.forohubalura.domain.topico;

import com.aluracursos.forohubalura.domain.usuario.DatosRespuestaUsuario;

public record DatosListadoTopicos(Long id,
                                  String titulo,
                                  String mensaje,
                                  String fechaCreacion
                                  ) {

    public DatosListadoTopicos(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion().toString());
    }
}
