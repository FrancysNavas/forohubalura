package com.aluracursos.forohubalura.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosCurso(Long id,
                         @NotBlank
                         String nombre,
                         Categoria categoria
) {
}
