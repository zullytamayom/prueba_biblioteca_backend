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
   cd fullstack
   ```

2. Iniciar la aplicación con Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. Verificar que los servicios estén corriendo:
   ```bash
   docker-compose ps
   ```

4. Ver logs de la API:
   ```bash
   docker-compose logs -f api
   ```

5. La API estará disponible en: http://localhost:8080

6. Para detener la aplicación:
   ```bash
   docker-compose down
   ```

7. Para reiniciar la aplicación:
   ```bash
   docker-compose restart
   ```

8. Para ver los logs de la base de datos:
   ```bash
   docker-compose logs -f db
   ```

## Generación de Archivo .dump

Una vez la base de datos esté corriendo y tenga datos de prueba, generar el dump:

```bash
docker exec biblioteca-db pg_dump -U postgres biblioteca_db > init-db/01-init.dump
```