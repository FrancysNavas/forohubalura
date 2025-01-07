CREATE TABLE topicos
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo         VARCHAR(255) NOT NULL,
    mensaje        VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME     NOT NULL,
    estatus        VARCHAR(50)  NOT NULL,
    usuario_id     BIGINT       NOT NULL,
    curso_id       BIGINT       NOT NULL,
    CONSTRAINT fk_autor FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos (id)
);