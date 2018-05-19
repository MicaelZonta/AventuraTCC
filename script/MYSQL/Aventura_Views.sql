## Um usuário que também é pessoa fisica ##
CREATE VIEW usuario_pessoa AS
SELECT p.idPessoa,u.idUsuario,u.ativo FROM pessoa p
INNER JOIN usuario u ON u.idUsuario = p.idUsuario
INNER JOIN pessoa_fisica pf ON pf.idPessoa = p.idPessoa;
