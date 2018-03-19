/* 
		CREATE DATABASE
*/
DROP DATABASE IF EXISTS aventura_db;
CREATE DATABASE aventura_db;
USE aventura_db;

/*
		CREATE TABLES
*/

## 	Usuario
CREATE TABLE Usuario (
    idUsuario bigint AUTO_INCREMENT,
    favor bigint NOT NULL,
    username varchar(25) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    primary key(idUsuario)
);

## 	Roles
CREATE TABLE Role (
    idRole bigint AUTO_INCREMENT,
    nome varchar(25) NOT NULL UNIQUE,
    primary key(idRole)
);

## Usuario_Roles
CREATE TABLE Usuario_Role(
	idUsuario bigint NOT NULL,
    idRole bigint NOT NULL,
    primary key(idUsuario,idRole)
);

/*
	CREATE FOREIGN KEYS
*/

##Usuario_Roles
ALTER TABLE Usuario_Role
ADD FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario);

ALTER TABLE Usuario_Role
ADD FOREIGN KEY (idRole) REFERENCES Role(idRole);