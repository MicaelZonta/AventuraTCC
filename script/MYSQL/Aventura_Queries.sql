use aventura_db;

SELECT m.* FROM missao m
INNER JOIN pessoa p ON m.idPessoa = p.idPessoa
INNER JOIN usuario u on u.idUsuario = p.idUsuario  
WHERE m.maxAventureiros > (SELECT count(mp.idMissaoProgresso) FROM missao_progresso mp WHERE mp.idMissao = m.idMissao)
AND m.dataTermino IS NULL
AND u.ativo = true
AND m.nome LIKE '%Pizza%'
AND m.descricao LIKE '%Pizza%'
AND m.dataCriacao >= '2017-10-10'
AND 1000 >= aventura_db.getDistancia(m.latitude,m.longitude,50,50)