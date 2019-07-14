INSERT INTO TIPO_SALA (nombre) VALUES ('2D');
INSERT INTO TIPO_SALA (nombre) VALUES ('3D');
INSERT INTO TIPO_SALA (nombre) VALUES ('Dinamix');

INSERT INTO USUARIO (usuario, password, nombre, apellido, tipo_identificacion, numero_identificacion) VALUES ('andres', '12345', 'Andres', 'Algo', 'CC', '343243432');

INSERT INTO CIUDAD (nombre) VALUES ('Pereira');
INSERT INTO CIUDAD (nombre) VALUES ('Cali');
INSERT INTO CIUDAD (nombre) VALUES ('Armenia');
INSERT INTO CIUDAD (nombre) VALUES ('Medellin');

INSERT INTO SUCURSAL (nombre, direccion, ciudad_id, administrador_id) VALUES ('Sucursal 1', 'Direccion 1', 1, 1);
INSERT INTO SUCURSAL (nombre, direccion, ciudad_id, administrador_id) VALUES ('Sucursal 2', 'Direccion 2', 1, 1);
INSERT INTO SUCURSAL (nombre, direccion, ciudad_id, administrador_id) VALUES ('Sucursal 3', 'Direccion 3', 2, 1);
INSERT INTO SUCURSAL (nombre, direccion, ciudad_id, administrador_id) VALUES ('Sucursal 4', 'Direccion 4', 3, 1);

INSERT INTO CONFIGURACION_SISTEMA (id, valor, descripcion) VALUES ('max.sillas.fila', '10', 'Configuración que indica el valor máximo número de sillas que son aceptadas para una fila');