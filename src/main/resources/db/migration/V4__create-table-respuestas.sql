CREATE TABLE respuestas
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje        VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME     NOT NULL,
    solucion       BOOLEAN      NOT NULL,
    usuario_id     BIGINT       NOT NULL,
    topico_id      BIGINT       NOT NULL,
    CONSTRAINT fk_respuesta_autor FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topicos (id)
);