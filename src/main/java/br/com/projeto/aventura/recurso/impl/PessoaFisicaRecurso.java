package br.com.projeto.aventura.recurso.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
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
		return getUrlHome() + "/" + URL_CADASTRAR;
	}

	public static String getUrlEditar() {
		return getUrlHome() + "/" + URL_EDITAR;
	}

	public static String getUrlEncontrar() {
		return getUrlHome() + "/" + URL_ENCONTRAR;
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

	@RequestMapping(method = RequestMethod.PUT, value = URL_CADASTRAR)
	public PessoaFisica cadastrarPessoaFisica(@RequestParam(value = "pessoaFisica", defaultValue = "") PessoaFisica pessoaFisica) {
		Usuario usuario = getUsuario(URL_CADASTRAR);

		pessoaFisica.setIdUsuario(usuario.getIdUsuario());
		Celular cel = pessoaFisica.getCelular();
		pessoaFisica.setCelular(null);

		try {
			pessoaFisica = pessoaFisicaServico.cadastrarPessoaFisica(pessoaFisica);
			pessoaFisica.setCelular(cel);
			cel.setIdPessoa(pessoaFisica.getIdPessoa());
			pessoaFisica = pessoaFisicaServico.editarPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return pessoaFisica;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_EDITAR)
	public PessoaFisica editarPessoaFisica(@RequestParam(value = "pessoaFisica", defaultValue = "")PessoaFisica pessoaFisica2 ) {

		Usuario usuario = getUsuario(URL_EDITAR);

		PessoaFisica pessoaFisica = null;
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			boolean update = false;
			if (pessoaFisica2.getEmail().length() != 0) {
				pessoaFisica.setEmail(pessoaFisica2.getEmail());
				update = true;
			}
			if (pessoaFisica2.getCelular().getNumero().length() != 0) {
				pessoaFisica.getCelular().setNumero(pessoaFisica2.getCelular().getNumero());
				update = true;
			}
			if (pessoaFisica2.getCelular().getDdd() != null) {
				pessoaFisica.getCelular().setDdd(pessoaFisica2.getCelular().getDdd());
				update = true;
			}
			if (!update) {
				throw new RuntimeException();
			}
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

		return pessoaFisica;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_ENCONTRAR)
	public PessoaFisica encontrarPessoaFisica() {
		Usuario usuario = getUsuario(URL_ENCONTRAR);
		PessoaFisica pessoaFisica = null;
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return pessoaFisica;
	}

}
