package com.aluracursos.forohubalura.domain.curso;

import com.aluracursos.forohubalura.domain.topico.Topico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    @Entity(name = "Curso")
    @Table(name = "cursos")
    public class Curso {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;
        @Enumerated(EnumType.STRING)
        private Categoria categoria;

        @OneToMany(mappedBy = "curso")
        private List<Topico> topicos;

        public Curso() {
        }

        public Curso(Long id, String nombre, String string, List<Topico> topicos) {
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public List<Topico> getTopicos() {
            return topicos;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }

        public void setTopicos(List<Topico> topicos) {
            this.topicos = topicos;
        }
    }

