use aventura_db;

## Query para encontra missões próximas
SELECT m.* FROM missao m
INNER JOIN pessoa p ON m.idPessoa = p.idPessoa
INNER JOIN usuario u on u.idUsuario = p.idUsuario  
WHERE m.maxAventureiros > (SELECT count(mp.idMissaoProgresso) FROM missao_progresso mp WHERE mp.idMissao = m.idMissao AND mp.idSituacao NOT IN (5,6))
AND m.dataTermino IS NULL
AND u.ativo = true
AND m.nome LIKE '%Pizza%'
AND m.descricao LIKE '%Pizza%'
AND m.dataCriacao >= '2017-10-10'
AND 1000 >= aventura_db.getDistancia(m.latitude,m.longitude,50,50);

## Query para encontrar o progresso de um usuário numa missõao
SELECT mp.* FROM missao_progresso mp
INNER JOIN usuario_pessoa up
WHERE mp.idMissao = 3 AND up.idUsuario = 3;

## listar progresso da missão
SELECT * FROM missao_progresso mp
WHERE mp.idMissao = 3;