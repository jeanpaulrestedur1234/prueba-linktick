CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    category VARCHAR(100)
);

INSERT INTO product (name, description, price, category) VALUES
('Laptop Lenovo', 'Portátil con 16GB RAM y 512GB SSD', 2499.99, 'Tecnología'),
('Mouse Logitech', 'Mouse inalámbrico ergonómico', 149.90, 'Accesorios'),
('Monitor LG 24"', 'Monitor Full HD de 24 pulgadas', 799.00, 'Pantallas'),
('Teclado Mecánico', 'Teclado con retroiluminación RGB', 299.50, 'Accesorios'),
('Smartphone Samsung', 'Teléfono con cámara de 108MP', 3599.99, 'Móviles'),
('Tablet Huawei', 'Tableta con 10 pulgadas y 128GB de almacenamiento', 1599.00, 'Móviles'),
('Audífonos Sony', 'Audífonos con cancelación de ruido', 499.99, 'Accesorios'),
('Disco SSD 1TB', 'Unidad de estado sólido de alta velocidad', 699.99, 'Almacenamiento'),
('Impresora HP', 'Impresora multifuncional a color', 899.00, 'Oficina'),
('Webcam Logitech', 'Cámara web HD para videollamadas', 299.00, 'Accesorios');
