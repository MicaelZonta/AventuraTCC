package br.com.projeto.aventura.recurso.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.PessoaServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + MissaoRecurso.URL_HOME)
public class MissaoRecurso extends WebService {

	public static final String URL_HOME = "missao";
	public static final String URL_CADASTRAR = "cadastrar";
	public static final String URL_EDITAR = "editar";
	public static final String URL_DELETAR = "deletar";
	public static final String URL_ENCONTRAR = "encontrar";

	@Autowired
	MissaoServico missaoServico;
	@Autowired
	PessoaServico pessoaServico;

	public static String getUrlHome() {
		return "/" + URL_HOME;
	}

	public static String getUrlCadastrar() {
		return getUrlHome() + "/" + URL_CADASTRAR;
	}

	public static String getUrlEditar() {
		return getUrlHome() + "/" + URL_EDITAR;
	}

	public static String getUrlEncontrar() {
		return getUrlHome() + "/" + URL_ENCONTRAR;
	}

	public MissaoRecurso(UsuarioServico usuarioServ, MissaoServico missaoServico, PessoaServico pessoaServico) {
		super(usuarioServ);
		this.missaoServico = missaoServico;
		this.pessoaServico = pessoaServico;
		WebServiceValidador validadorBasico = new WebServiceValidador();
		validadorBasico.addAutorizacao(RoleEnum.USER);
		adicionarValidador(URL_CADASTRAR, validadorBasico);
		adicionarValidador(URL_EDITAR, validadorBasico);
		adicionarValidador(URL_DELETAR, validadorBasico);
		adicionarValidador(URL_ENCONTRAR, validadorBasico);
	}

	@RequestMapping(method = RequestMethod.POST, value = URL_CADASTRAR)
	public Missao cadastrarMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao) {
		Usuario usuario = getUsuario(URL_CADASTRAR);

		try {
			Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			missao.setIdPessoa(pessoa.getIdPessoa());
			missao = missaoServico.cadastrarMissao(missao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return missao;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_EDITAR)
	public Missao editarMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao) {

		Usuario usuario = getUsuario(URL_EDITAR);
		Missao missaoOg = null;

		try {
			Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			missaoOg = missaoServico.encontrarMissao(missao.getIdMissao());
			if (missaoOg.getIdPessoa() != missao.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			}
			if (missaoOg.getIdPessoa() != pessoa.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			}

			boolean update = false;
			if (missao.getNome() != null && missao.getNome().length() > 0) {
				missaoOg.setNome(missao.getNome());
				update = true;
			}
			if (missao.getDescricao() != null && missao.getDescricao().length() > 0) {
				missaoOg.setDescricao(missao.getDescricao());
				update = true;
			}

			if (!update) {
				throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		try {
			missaoOg = missaoServico.editarMissao(missaoOg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return missaoOg;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URL_DELETAR)
	public Missao excluirMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao) {

		Usuario usuario = getUsuario(URL_DELETAR);
		Missao missaoOg = null;

		try {
			Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			missaoOg = missaoServico.encontrarMissao(missao.getIdMissao());
			if (missaoOg.getIdPessoa() != missao.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			}
			if (missaoOg.getIdPessoa() != pessoa.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			}

			missaoOg = missaoServico.deletarMissao(missaoOg);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		return missaoOg;
	}

	@SuppressWarnings("unused")
	@RequestMapping(method = RequestMethod.GET, value = URL_ENCONTRAR)
	public Missao encontrarPessoaFisica(@RequestParam(value = "idMissao", defaultValue = "") Long idMissao) {
		Usuario usuario = getUsuario(URL_ENCONTRAR);
		Missao missaoOg = null;
		try {
			missaoOg = missaoServico.encontrarMissao(idMissao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return missaoOg;
	}

}
