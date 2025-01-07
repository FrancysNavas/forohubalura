# :computer: Challenge ForoHub - API REST

Este proyecto es una implementación de un foro desarrollado con Java y Spring Boot, donde los usuarios pueden crear tópicos para plantear dudas, sugerencias, quejas y otros tipos de mensajes.

# Características :memo:
- Gestión de Tópicos: Realiza operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los tópicos de tu foro.
- Seguridad Robusta: Protege tus datos con autenticación basada en tokens JWT y validación rigurosa de datos en cada solicitud.
- Escalabilidad: Aprovecha la potencia de Spring Boot y MySQL para construir una plataforma escalable y adaptable.
- Personalizable: Adapta la plataforma a tus necesidades gracias a su arquitectura modular y extensible.
- Validación de datos en los endpoints REST.
- Migraciones automáticas de la base de datos usando Flyway.

# Tecnologías Utilizadas :hammer:
- Backend: Java, Spring Boot
- Base de Datos: MySQL
- Herramientas: Flyway, JWT, Maven
- Librerías y herramientas principales:
- spring-boot-starter-data-jpa para persistencia de datos.
- spring-boot-starter-security para la seguridad.
- spring-boot-starter-validation para validaciones.
- flyway-core para migraciones.
- spring-boot-starter-web para APIs REST.

# Endpoints Principales :dart:
- GET /topicos: Lista todos los tópicos.
- GET /topicos/{id}: Consulta un tópico por ID.
- POST /topicos: Crea un nuevo tópico.
- PUT /topicos/{id}: Actualiza un tópico existente.
- DELETE /topicos/{id}: Elimina un tópico por ID.

# Persistencia 💾
Almacenamiento de datos en una base de datos relacional:
Todos los datos (usuarios, tópicos, respuestas, cursos) se almacenan en una base de datos relacional (por ejemplo, MySQL).
Esto asegura que la información se maneje de forma eficiente y coherente.

# Documentación Automática 📖
 Swagger para exponer la documentación interactiva de la API:
Se utiliza Swagger para generar una documentación interactiva de la API, donde los desarrolladores pueden ver y probar las rutas disponibles de manera visual.

# Desarrollado por ✒️
* :woman: **Francys Navas**  - [francysnavas](https://github.com/francysnavas)


---
⌨️ con :purple_heart: por Francys Navas - 2025 para Alura Latam y Oracle ONE G7 :purple_heart:
