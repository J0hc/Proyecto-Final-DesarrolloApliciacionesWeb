/* Se crea la base de datos */
DROP SCHEMA IF EXISTS coffeLife;
DROP USER IF EXISTS 'usuario_prueba'@'%';
CREATE SCHEMA coffeLife;

/* Se crea un usuario para la base de datos llamado "usuario_prueba" y tiene la contraseña "usuario" */
CREATE USER 'usuario_prueba'@'%' IDENTIFIED BY 'usuario';

/* Se asignan los privilegios sobre la base de datos coffeLife al usuario creado */
GRANT ALL PRIVILEGES ON coffeLife.* TO 'usuario_prueba'@'%';
FLUSH PRIVILEGES;

/* Se crea la tabla usuarios */
CREATE TABLE usuarios (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    cedula VARCHAR(11) NOT NULL, 
    apellido VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla empleados */
CREATE TABLE empleados (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empleado VARCHAR(255) NOT NULL,
    cedula VARCHAR(11) NOT NULL, 
    apellidos VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    edad INT NOT NULL,
    cuenta_bancaria VARCHAR(255) NOT NULL,
    departamento VARCHAR(255) NOT NULL,
    lugar_trabajo VARCHAR(255) NOT NULL,
    horarios VARCHAR(255) NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla alertas */
CREATE TABLE alertas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    titulo_alerta VARCHAR(255) NOT NULL,
    descripcion_alerta TEXT NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla productos */
CREATE TABLE productos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    descripcion_producto TEXT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    disponibilidad BOOLEAN NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla ubicaciones */
CREATE TABLE ubicaciones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ubicacion VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    imagen_url VARCHAR(255) NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla promociones */
CREATE TABLE promociones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_cierre DATE NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla tareas */
CREATE TABLE tareas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla carritos */
CREATE TABLE carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(ID) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(ID) ON DELETE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla de contacto */
CREATE TABLE contacto (
    id_mensaje INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    numero VARCHAR(15) NOT NULL,
    mensaje TEXT NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

