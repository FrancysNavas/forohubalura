package com.aluracursos.forohubalura.domain.curso;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Override
    Optional<Curso> findById(Long id);

    List<Curso> id(Long id);
}
