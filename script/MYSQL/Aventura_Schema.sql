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
    ativo bool NOT NULL,
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

## 	Pessoa	
CREATE TABLE Pessoa (
	idPessoa bigint NOT NULL AUTO_INCREMENT,
    idUsuario bigint NOT NULL,
    email varchar(255) NOT NULL,
    primary key(idPessoa)
);

## 	Celular	
CREATE TABLE Celular (
    idPessoa bigint NOT NULL,
    idDDD bigint NOT NULL,
    numero char(9) NOT NULL,
    primary key(idPessoa)
);

## DDD
CREATE TABLE DDD (
    idDDD bigint NOT NULL AUTO_INCREMENT,
    numero char(3) NOT NULL,
    primary key(idDDD)
);

## PessoaFisica
CREATE TABLE Pessoa_Fisica (
    idPessoa bigint NOT NULL,
    nome varchar(20) NOT NULL,
    sobrenome varchar(60) NOT NULL,
    dataNascimento date NOT NULL,
    CPF char(11) NOT NULL,    
    primary key(idPessoa)
);

## Aventureiro
CREATE TABLE Aventureiro (
	idUnidade bigint NOT NULL,
    idPessoa bigint NOT NULL,
    primary key(idUnidade)
);

## Unidade
CREATE TABLE Unidade(
    idUnidade bigint NOT NULL AUTO_INCREMENT,
    primary key(idUnidade)
);

## Posicao
CREATE TABLE Posicao(
    idUnidade bigint NOT NULL,
    primary key(idUnidade)
);



/*
	CREATE FOREIGN KEYS
*/

##Usuario_Roles
ALTER TABLE Usuario_Role
ADD FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario);

ALTER TABLE Usuario_Role
ADD FOREIGN KEY (idRole) REFERENCES Role(idRole);

##Pessoa
ALTER TABLE Pessoa
ADD FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario);

##Celular
ALTER TABLE Celular
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

ALTER TABLE Celular
ADD FOREIGN KEY (idDDD) REFERENCES DDD(idDDD);

##Pessoa_Fisica
ALTER TABLE Pessoa_Fisica
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

##Aventureiro
ALTER TABLE Aventureiro
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

ALTER TABLE Aventureiro
ADD FOREIGN KEY (idUnidade) REFERENCES Unidade(idUnidade);

##Posicao
ALTER TABLE Posicao
ADD FOREIGN KEY (idUnidade) REFERENCES Unidade(idUnidade);