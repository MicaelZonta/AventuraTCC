package br.com.projeto.aventura.recurso.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.PessoaServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + MissaoProgressoRecurso.URL_HOME)
public class MissaoProgressoRecurso extends WebService {

	public static final String URL_HOME = "missao_progresso";
	public static final String URL_ACEITAR_MISSAO = "aceitar";
	public static final String URL_CANCELAR_MISSAO = "cancelar";
	public static final String URL_COMPLETAR_TAREFA = "completar";
	public static final String URL_PAUSAR_MISSAO = "pausar";
	public static final String URL_BUSCAR_PROGRESSO = "buscar";
	public static final String URL_LISTAR_PROGRESSO_POR_PESSOA = "listarpessoa";
	public static final String URL_LISTAR_PROGRESSO_POR_MISSAO = "listarmissao";

	@Autowired
	MissaoProgressoServico missaoProgressoServico;
	@Autowired
	PessoaServico pessoaServico;
	@Autowired
	PessoaFisicaServico pessoaFisicaServico;

	public static String getUrlHome() {
		return "/" + URL_HOME;
	}

	public static String getUrlAceitarMissao() {
		return getUrlHome() + "/" + URL_ACEITAR_MISSAO;
	}

	public static String getUrlCancelarMissao() {
		return getUrlHome() + "/" + URL_CANCELAR_MISSAO;
	}

	public static String getUrlCompletarTarefa() {
		return getUrlHome() + "/" + URL_COMPLETAR_TAREFA;
	}

	public static String getUrlPausarMissao() {
		return getUrlHome() + "/" + URL_PAUSAR_MISSAO;
	}

	public static String getUrlBuscarProgresso() {
		return getUrlHome() + "/" + URL_BUSCAR_PROGRESSO;
	}

	public static String getUrlListarProgressoPorPessoa() {
		return getUrlHome() + "/" + URL_LISTAR_PROGRESSO_POR_PESSOA;
	}

	public static String getUrlListarProgressoPorMissao() {
		return getUrlHome() + "/" + URL_LISTAR_PROGRESSO_POR_MISSAO;
	}

	public MissaoProgressoRecurso(UsuarioServico usuarioServ, MissaoProgressoServico missaoProgressoServico,
			PessoaServico pessoaServico, PessoaFisicaServico pessoaFisicaServico) {
		super(usuarioServ);
		this.missaoProgressoServico = missaoProgressoServico;
		this.pessoaServico = pessoaServico;
		this.pessoaFisicaServico = pessoaFisicaServico;
		WebServiceValidador validadorBasico = new WebServiceValidador();
		validadorBasico.addAutorizacao(RoleEnum.USER);
		adicionarValidador(URL_ACEITAR_MISSAO, validadorBasico);
		adicionarValidador(URL_CANCELAR_MISSAO, validadorBasico);
		adicionarValidador(URL_COMPLETAR_TAREFA, validadorBasico);
		adicionarValidador(URL_PAUSAR_MISSAO, validadorBasico);
		adicionarValidador(URL_BUSCAR_PROGRESSO, validadorBasico);
		adicionarValidador(URL_LISTAR_PROGRESSO_POR_PESSOA, validadorBasico);
		adicionarValidador(URL_LISTAR_PROGRESSO_POR_MISSAO, validadorBasico);
	}

	@RequestMapping(method = RequestMethod.POST, value = URL_ACEITAR_MISSAO)
	public MissaoProgresso aceitarMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao) {
		Usuario usuario = getUsuario(URL_ACEITAR_MISSAO);
		MissaoProgresso mp = null;
		try {
			PessoaFisica pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			mp = missaoProgressoServico.aceitarMissao(missao, pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URL_CANCELAR_MISSAO)
	public MissaoProgresso cancelarMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao,
			@RequestParam(value = "usuarioPessoa", defaultValue = "") Usuario usuarioPessoa) {
		Usuario usuario = getUsuario(URL_CANCELAR_MISSAO);
		MissaoProgresso mp = null;
		try {
			Pessoa giver = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuarioPessoa.getIdUsuario());
			mp = missaoProgressoServico.cancelarMissao(missao, taker, giver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_COMPLETAR_TAREFA)
	public MissaoProgresso completarTarefa(@RequestParam(value = "missao", defaultValue = "") Missao missao,
			@RequestParam(value = "tarefaProgresso", defaultValue = "") TarefaProgresso tarefaProgresso) {
		Usuario usuario = getUsuario(URL_COMPLETAR_TAREFA);
		MissaoProgresso mp = null;
		try {
			PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			mp = missaoProgressoServico.completarTarefa(missao, taker, tarefaProgresso);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_PAUSAR_MISSAO)
	public MissaoProgresso pausarMissao(@RequestParam(value = "missao", defaultValue = "") Missao missao,
			@RequestParam(value = "usuarioPessoa", defaultValue = "") Usuario usuarioPessoa) {
		Usuario usuario = getUsuario(URL_PAUSAR_MISSAO);
		MissaoProgresso mp = null;
		try {
			Pessoa giver = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuarioPessoa.getIdUsuario());
			mp = missaoProgressoServico.pausarMissao(missao, taker, giver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_BUSCAR_PROGRESSO)
	public MissaoProgresso buscarMissaoProgresso(@RequestParam(value = "missao", defaultValue = "") Missao missao,
			@RequestParam(value = "usuarioPessoa", defaultValue = "") Usuario usuarioPessoa) {
		Usuario usuario = getUsuario(URL_BUSCAR_PROGRESSO);
		MissaoProgresso mp = null;
		try {
			PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuarioPessoa.getIdUsuario());
			mp = missaoProgressoServico.buscarMissaoProgresso(missao, taker);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_LISTAR_PROGRESSO_POR_PESSOA)
	public List<MissaoProgresso> listarMissaoProgresso() {
		Usuario usuario = getUsuario(URL_LISTAR_PROGRESSO_POR_PESSOA);
		List<MissaoProgresso> mp = null;
		try {
			PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			mp = missaoProgressoServico.listarMissaoProgresso(taker);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_LISTAR_PROGRESSO_POR_MISSAO)
	public List<MissaoProgresso> listarMissaoProgresso(
			@RequestParam(value = "missao", defaultValue = "") Missao missao) {
		Usuario usuario = getUsuario(URL_LISTAR_PROGRESSO_POR_MISSAO);
		List<MissaoProgresso> mp = null;
		try {
			mp = missaoProgressoServico.listarMissaoProgresso(missao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return mp;
	}

}
