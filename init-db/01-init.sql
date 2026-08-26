CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS libros (
    id_libro BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    edicion VARCHAR(255),
    fecha_publicacion DATE,
    autor VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS ejemplares (
    id_ejemplar BIGSERIAL PRIMARY KEY,
    codigo_ejemplar VARCHAR(255) UNIQUE NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'DISPONIBLE',
    libro_id BIGINT NOT NULL,
    FOREIGN KEY (libro_id) REFERENCES libros(id_libro) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS prestamos (
    id_prestamo BIGSERIAL PRIMARY KEY,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    estado_prestamo VARCHAR(50),
    usuario_id BIGINT NOT NULL,
    ejemplar_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (ejemplar_id) REFERENCES ejemplares(id_ejemplar) ON DELETE CASCADE
);


INSERT INTO usuarios (nombre, apellido, email, fecha_nacimiento, activo) VALUES
('Juan David', 'Cardona Perez', 'juan.cardona@example.com', '1995-04-12', true),
('Maria Camila', 'Restrepo Lopez', 'maria.restrepo@example.com', '1998-08-23', true);

INSERT INTO libros (titulo, isbn, edicion, fecha_publicacion, autor, activo) VALUES
('Cien años de soledad', '978-0307474728', 'Primera', '1967-05-30', 'Gabriel Garcia Marquez', true),
('El Alquimista', '978-0062511409', 'Especial', '1988-01-01', 'Paulo Coelho', true);

INSERT INTO ejemplares (codigo_ejemplar, estado, libro_id) VALUES
('INV-1001', 'DISPONIBLE', 1),
('INV-1002', 'DISPONIBLE', 1),
('INV-2001', 'PRESTADO', 2);

INSERT INTO prestamos (fecha_prestamo, fecha_devolucion, estado_prestamo, usuario_id, ejemplar_id) VALUES
(CURRENT_DATE - INTERVAL '2 days', CURRENT_DATE + INTERVAL '5 days', 'ACTIVO', 2, 3);
