package br.com.projeto.aventura.recurso.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
import br.com.projeto.aventura.servico.CelularServico;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + PessoaFisicaRecurso.URL_HOME)
public class PessoaFisicaRecurso extends WebService {

	public static final String URL_HOME = "pessoa";
	public static final String URL_CADASTRAR = "cadastrar";
	public static final String URL_EDITAR = "editar";
	public static final String URL_ENCONTRAR = "encontrar";

	@Autowired
	PessoaFisicaServico pessoaFisicaServico;

	public static String getUrlHome() {
		return "/" + URL_HOME;
	}

	public static String getUrlCadastrar() {
		return "/" + URL_HOME + "/" + URL_CADASTRAR;
	}

	public static String getUrlEditar() {
		return "/" + URL_HOME + "/" + URL_EDITAR;
	}

	public static String getUrlEncontrar() {
		return "/" + URL_HOME + "/" + URL_ENCONTRAR;
	}

	public PessoaFisicaRecurso(UsuarioServico usuarioServ, PessoaFisicaServico pessoaFisicaServico) {
		super(usuarioServ);
		this.pessoaFisicaServico = pessoaFisicaServico;
		WebServiceValidador validadorBasico = new WebServiceValidador();
		validadorBasico.addAutorizacao(RoleEnum.ADMIN, RoleEnum.USER);
		adicionarValidador(URL_CADASTRAR, validadorBasico);
		adicionarValidador(URL_EDITAR, validadorBasico);
		adicionarValidador(URL_ENCONTRAR, validadorBasico);
	}

	@RequestMapping(method = RequestMethod.POST, value = URL_CADASTRAR)
	public String cadastrarPessoaFisica(HttpServletRequest request) {
		
		Usuario usuario = getUsuario(request, URL_CADASTRAR);
		
		PessoaFisica pessoaFisica = lerJson(request, PessoaFisica.class);
		Celular celular = pessoaFisica.getCelular();
		pessoaFisica.setCelular(null);
		pessoaFisica.setIdUsuario(usuario.getIdUsuario());
		
		try {
			pessoaFisica = pessoaFisicaServico.cadastrarPessoaFisica(pessoaFisica);
			pessoaFisica.setCelular(celular);
			celular.setIdPessoa(pessoaFisica.getIdPessoa());
			pessoaFisica = pessoaFisicaServico.editarPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		return pessoaFisica.toString();
	}

	@RequestMapping(method = RequestMethod.PUT, value = URL_EDITAR)
	public String editarPessoaFisica(HttpServletRequest request) {
		
		Usuario usuario = getUsuario(request, URL_EDITAR);

		PessoaFisica pessoaFisica = null;
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			PessoaFisica pessoaFisicaUpdate = lerJson(request, PessoaFisica.class);
			pessoaFisica.atualizarInstancia(pessoaFisicaUpdate);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		try {
			pessoaFisica = pessoaFisicaServico.editarPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		return pessoaFisica.toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_ENCONTRAR)
	public String encontrarPessoaFisica(HttpServletRequest request) {
		Usuario usuario = getUsuario(request, URL_ENCONTRAR);
		PessoaFisica pessoaFisica = null;
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return pessoaFisica.toString();
	}

}
