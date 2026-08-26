# API REST - Sistema de Gestión de Biblioteca

Servicio Backend desarrollado con Java 17 y Spring Boot 3 para la gestión de usuarios, libros, ejemplares y préstamos.

## Requisitos Previos

- Docker Engine instalado
- Docker Compose instalado

## Variables de Entorno

La aplicación utiliza las siguientes variables configuradas en el entorno o en `docker-compose.yml`:

- `PORT`: Puerto de exposición de la API (por defecto `8080`)
- `DB_URI`: URL de conexión JDBC a la base de datos
- `DB_USER`: Usuario de la base de datos
- `DB_PASSWORD`: Contraseña de la base de datos
- `DB_DRIVER`: Driver JDBC (`org.postgresql.Driver`)
- `CORS_ALLOWED_ORIGINS`: Orígenes permitidos para el frontend React

## Instrucciones de Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/zullytamayom/prueba_biblioteca_backend.git