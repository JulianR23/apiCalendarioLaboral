-- Crear tablas si aún no han sido generadas por Hibernate
CREATE TABLE IF NOT EXISTS tipo (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(50) UNIQUE NOT NULL
);

-- Migración por si la tabla ya existe con columna 'nombre'
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'tipo' AND column_name = 'nombre'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'tipo' AND column_name = 'tipo'
    ) THEN
        ALTER TABLE tipo RENAME COLUMN nombre TO tipo;
    END IF;
END $$;

CREATE TABLE IF NOT EXISTS calendario (
    id BIGSERIAL PRIMARY KEY,
    fecha DATE UNIQUE NOT NULL,
    idtipo BIGINT REFERENCES tipo(id),
    descripcion VARCHAR(100)
);

-- Migración por si la tabla calendario ya existe con columna 'id_tipo'
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'calendario' AND column_name = 'id_tipo'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'calendario' AND column_name = 'idtipo'
    ) THEN
        ALTER TABLE calendario RENAME COLUMN id_tipo TO idtipo;
    END IF;
END $$;

-- Datos base de la tabla 'tipo' acorde al Taller 1
INSERT INTO tipo (id, tipo) VALUES
    (1, 'Día laboral'),
    (2, 'Fin de semana'),
    (3, 'Día festivo')
ON CONFLICT (id) DO UPDATE SET tipo = EXCLUDED.tipo;

-- Sincroniza la secuencia del id para que futuros INSERT desde /api/tipos/agregar no choquen
SELECT setval(pg_get_serial_sequence('tipo', 'id'), (SELECT COALESCE(MAX(id), 1) FROM tipo));
