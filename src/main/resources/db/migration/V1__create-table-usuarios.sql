CREATE TABLE IF NOT EXISTS usuarios
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    perfil     VARCHAR(100) NOT NULL
);