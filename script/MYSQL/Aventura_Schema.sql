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
    latitude bigint NOT NULL,
    longitude bigint NOT NULL,
    primary key(idUnidade)
);

## Avaliacao
CREATE TABLE Avaliacao(
    idMissaoProgresso bigint NOT NULL,
    idPessoa bigint NOT NULL,
    estrelas tinyint NOT NULL,
    descricao varchar(50) NOT NULL,
    primary key(idMissaoProgresso, idPessoa)
);

## Habilidade
CREATE TABLE Habilidade(
    idHabilidade bigint NOT NULL,
    nome varchar(25) NOT NULL,
    primary key(idHabilidade)
);

## Missao
CREATE TABLE Missao(
    idMissao bigint NOT NULL AUTO_INCREMENT,
    nome varchar(25) NOT NULL,
    descricao varchar(500) NOT NULL,
    dataCriacao datetime NOT NULL,
    dataTermino datetime NULL,
    maxAventureiros int NOT NULL,
    idPessoa bigint NOT NULL,
    latitude double NOT NULL,
    longitude double NOT NULL,
    primary key(idMissao)
);

## Missao_Dificuldade
CREATE TABLE Missao_Dificuldade(
    idMissaoDificuldade bigint NOT NULL,
    idMissao bigint NOT NULL,
    level int NOT NULL,
    experiencia int NOT NULL,
    idHabilidade bigint NOT NULL,
    primary key(idMissaoDificuldade)
);

## Missao_Dificuldade
CREATE TABLE Missao_Progresso(
    idMissaoProgresso bigint NOT NULL AUTO_INCREMENT,
    idMissao bigint NOT NULL,
    idPessoa bigint NOT NULL,
    idSituacao int NOT NULL,
    primary key(idMissaoProgresso)
);

## Missao_Tarefa
CREATE TABLE Missao_Tarefa(
    idMissaoTarefa bigint NOT NULL AUTO_INCREMENT,
    idMissao bigint NOT NULL,
    nome varchar(20) NOT NULL,
    descricao varchar(100) NOT NULL,
    primary key(idMissaoTarefa)
);

## Situacao
CREATE TABLE Situacao(
    idSituacao int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    primary key(idSituacao)
);

## Tarefa_Progresso
CREATE TABLE Tarefa_Progresso(
    idMissaoProgresso bigint NOT NULL,
    idMissaoTarefa bigint NOT NULL,
    idSituacao int NOT NULL,
    primary key(idMissaoProgresso,idMissaoTarefa)
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

## Avaliacao
ALTER TABLE Avaliacao
ADD FOREIGN KEY (idMissaoProgresso) REFERENCES Missao_Progresso(idMissaoProgresso);

ALTER TABLE Avaliacao
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

## Missao
ALTER TABLE Missao
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

## Missao_Dificuldade
ALTER TABLE Missao_Dificuldade
ADD FOREIGN KEY (idMissao) REFERENCES Missao(idMissao);

ALTER TABLE Missao_Dificuldade
ADD FOREIGN KEY (idHabilidade) REFERENCES Habilidade(idHabilidade);

## Missao_Progresso
ALTER TABLE Missao_Progresso
ADD FOREIGN KEY (idMissao) REFERENCES Missao(idMissao);

ALTER TABLE Missao_Progresso
ADD FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa);

ALTER TABLE Missao_Progresso
ADD FOREIGN KEY (idSituacao) REFERENCES Situacao(idSituacao);

## Missao_Tarefa
ALTER TABLE Missao_Tarefa
ADD FOREIGN KEY (idMissao) REFERENCES Missao(idMissao);

## Tarefa_Progresso
ALTER TABLE Tarefa_Progresso
ADD FOREIGN KEY (idMissaoProgresso) REFERENCES Missao_Progresso(idMissaoProgresso);

ALTER TABLE Tarefa_Progresso
ADD FOREIGN KEY (idMissaoTarefa) REFERENCES Missao_Tarefa(idMissaoTarefa);

ALTER TABLE Tarefa_Progresso
ADD FOREIGN KEY (idSituacao) REFERENCES Situacao(idSituacao);

/*
	CREATE UNIQUE CONSTRAINTS
*/

ALTER TABLE Missao_Progresso ADD CONSTRAINT UK_Missao_Progresso UNIQUE (idPessoa, idMissao);