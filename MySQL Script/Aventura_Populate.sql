USE aventura_db;

## 	USUARIOS
INSERT INTO 
Usuario (username,password,favor) 
VALUES ('user','$2a$10$ZmetWuFktyIZzg24lbJrgu1kRVNYElvDD.IN3U92p/2UuHG75u6Gy',10);

## 	ROLES
INSERT INTO
Role (nome)
VALUES ("ROLE_USER");

##	USUARIO_ROLES
INSERT INTO
Usuario_Role (idUsuario,idRole)
VALUES (1,1);