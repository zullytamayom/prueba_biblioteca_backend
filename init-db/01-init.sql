CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS libros (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    edicion VARCHAR(255) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    autor VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ejemplares (
    id BIGSERIAL PRIMARY KEY,
    codigo_inventario VARCHAR(255) UNIQUE NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'DISPONIBLE',
    libro_id BIGINT NOT NULL,
    FOREIGN KEY (libro_id) REFERENCES libros(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS prestamos (
    id BIGSERIAL PRIMARY KEY,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    fecha_entrega_real DATE,
    estado_prestamo VARCHAR(50) NOT NULL,
    usuario_id BIGINT NOT NULL,
    ejemplar_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (ejemplar_id) REFERENCES ejemplares(id) ON DELETE CASCADE
);


INSERT INTO usuarios (nombre, apellido, email, fecha_nacimiento) VALUES
('Juan David', 'Cardona Perez', 'juan.cardona@example.com', '1995-04-12'),
('Maria Camila', 'Restrepo Lopez', 'maria.restrepo@example.com', '1998-08-23');

INSERT INTO libros (titulo, isbn, edicion, fecha_publicacion, autor) VALUES
('Cien años de soledad', '978-0307474728', 'Primera', '1967-05-30', 'Gabriel Garcia Marquez'),
('El Alquimista', '978-0062511409', 'Especial', '1988-01-01', 'Paulo Coelho');

INSERT INTO ejemplares (codigo_inventario, estado, libro_id) VALUES
('INV-1001', 'DISPONIBLE', 1),
('INV-1002', 'DISPONIBLE', 1),
('INV-2001', 'PRESTADO', 2);

INSERT INTO prestamos (fecha_prestamo, fecha_devolucion, estado_prestamo, usuario_id, ejemplar_id) VALUES
(CURRENT_DATE - INTERVAL '2 days', CURRENT_DATE + INTERVAL '5 days', 'ACTIVO', 2, 3);