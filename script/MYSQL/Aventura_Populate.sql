USE aventura_db;

## 	USUARIOS
INSERT INTO 
Usuario (username,password,favor,ativo) 
VALUES ('user','$2a$10$ZmetWuFktyIZzg24lbJrgu1kRVNYElvDD.IN3U92p/2UuHG75u6Gy',10,true);

INSERT INTO 
Usuario (username,password,favor,ativo) 
VALUES ('micael','$2a$10$ZmetWuFktyIZzg24lbJrgu1kRVNYElvDD.IN3U92p/2UuHG75u6Gy',10,true);

INSERT INTO 
Usuario (username,password,favor,ativo) 
VALUES ('rafael','$2a$10$ZmetWuFktyIZzg24lbJrgu1kRVNYElvDD.IN3U92p/2UuHG75u6Gy',10,true);

INSERT INTO 
Usuario (username,password,favor,ativo) 
VALUES ('djalma','$2a$10$ZmetWuFktyIZzg24lbJrgu1kRVNYElvDD.IN3U92p/2UuHG75u6Gy',10,true);

## PESSOA

INSERT INTO 
Pessoa (idUsuario,email) 
VALUES (1,'1@email');

INSERT INTO 
Pessoa (idUsuario,email) 
VALUES (2,'2@email');

INSERT INTO 
Pessoa (idUsuario,email) 
VALUES (3,'3@email');

INSERT INTO 
Pessoa (idUsuario,email) 
VALUES (4,'4@email');


## 	ROLES
INSERT INTO
Role (nome)
VALUES ("ROLE_USER");

##	USUARIO_ROLES
INSERT INTO
Usuario_Role (idUsuario,idRole)
VALUES (1,1);

INSERT INTO
Usuario_Role (idUsuario,idRole)
VALUES (2,1);

INSERT INTO
Usuario_Role (idUsuario,idRole)
VALUES (3,1);

INSERT INTO
Usuario_Role (idUsuario,idRole)
VALUES (4,1);


##	DDD
INSERT INTO
DDD (numero)
VALUES ('011');

## MISSÃO

INSERT INTO
Missao ( nome, descricao , dataCriacao , dataTermino, maxAventureiros, idPessoa,longitude, latitude)
VALUES ('Comprar Uva', 'vai compra uva1 ', '2017-10-10', '2017-10-10', 1, 1,50,50);

INSERT INTO
Missao ( nome, descricao , dataCriacao , maxAventureiros, idPessoa,longitude, latitude)
VALUES ('Comprar Pizza', 'vai comprar pizza', '2017-10-10', 1, 2,50,50);

INSERT INTO
Missao ( nome, descricao , dataCriacao , maxAventureiros, idPessoa,longitude, latitude)
VALUES ('Matar Hitler', 'mate o hitler' , '2017-10-10', 1, 3,50,50);

## Pessoa Fisica
INSERT INTO 
pessoa_fisica ( idPessoa, nome, sobrenome, dataNascimento,CPF)
VALUES ( 1, 'Micael','Zonta','1994-09-26','44178920839');

INSERT INTO 
pessoa_fisica ( idPessoa, nome, sobrenome, dataNascimento,CPF)
VALUES ( 2, 'Djalma','Junior','1992-09-26','11122233344');

INSERT INTO 
pessoa_fisica ( idPessoa, nome, sobrenome, dataNascimento,CPF)
VALUES ( 3, 'Rafael','Murakami','1993-10-02','11122233344');

INSERT INTO 
pessoa_fisica ( idPessoa, nome, sobrenome, dataNascimento,CPF)
VALUES ( 4, 'Natália','Borges','1996-12-31','11122233344');

## Situacao
INSERT INTO 
situacao ( nome)
VALUES ( 'INICIADO' );

INSERT INTO 
situacao ( nome)
VALUES ( 'PAUSA' );

INSERT INTO 
situacao ( nome)
VALUES ( 'COMPLETA' );

INSERT INTO 
situacao ( nome)
VALUES ( 'ESPERA' );

INSERT INTO 
situacao ( nome)
VALUES ( 'CANCELADO' );

INSERT INTO 
situacao ( nome)
VALUES ( 'DESISTENCIA' );

## Missao Progresso
INSERT INTO 
missao_progresso ( idMissao , idPessoa, idSituacao )
VALUES ( 3, 3, 1 );

## MissaoTarefa
INSERT INTO
missao_tarefa (idMissao, nome, descricao)
VALUES (3,'teste','teste');

## Tarefa Progresso
INSERT INTO 
Tarefa_Progresso (idSituacao, idMissaoProgresso, idMissaoTarefa) 
VALUES (1, 1, 1)