-- 1. Crear la base de datos
CREATE DATABASE unsafebox;

-- (Opcional: cambiar a esa base)
-- \c unsafebox;

-- 2. Crear tabla 'safebox'
CREATE TABLE safebox (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    created_on TIMESTAMP NOT NULL
);

-- 3. Crear tabla 'item'
CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    safebox_id UUID NOT NULL,
    description TEXT NOT NULL,
    created_on TIMESTAMP NOT NULL,
    CONSTRAINT fk_safebox
        FOREIGN KEY (safebox_id)
        REFERENCES safebox(id)
        ON DELETE CASCADE
);

-- 4. Crear tabla 'users'
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
