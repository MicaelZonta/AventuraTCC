package br.com.projeto.aventura.recurso.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.DDD;
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
	public String cadastrarPessoaFisica(@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "celularNumero", defaultValue = "") String celularNumero,
			@RequestParam(value = "idDDD", defaultValue = "") String idDDD,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "sobrenome", defaultValue = "") String sobrenome,
			@RequestParam(value = "dataNascimento", defaultValue = "") String dataNascimento,
			@RequestParam(value = "cpf", defaultValue = "") String cpf) {
		Usuario usuario = getUsuario(URL_CADASTRAR);

		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setEmail(email);

		Celular cel = new Celular();
		DDD celDDD = new DDD();
		cel.setNumero(celularNumero);
		celDDD.setIdDDD(Long.parseLong(idDDD));
		cel.setDdd(celDDD);

		pessoaFisica.setNome(nome);
		pessoaFisica.setSobrenome(sobrenome);
		pessoaFisica.setCPF(cpf);

		Date data = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		try {
			data = formatter.parse(dataNascimento);
		} catch (ParseException e1) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		pessoaFisica.setDataNascimento(data);
		pessoaFisica.setIdUsuario(usuario.getIdUsuario());

		try {
			pessoaFisica = pessoaFisicaServico.cadastrarPessoaFisica(pessoaFisica);
			pessoaFisica.setCelular(cel);
			cel.setIdPessoa(pessoaFisica.getIdPessoa());
			pessoaFisica = pessoaFisicaServico.editarPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return pessoaFisica.toString();
	}

	@RequestMapping(method = RequestMethod.PUT, value = URL_EDITAR)
	public String editarPessoaFisica(@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "celularNumero", defaultValue = "") String celularNumero,
			@RequestParam(value = "idDDD", defaultValue = "") String idDDD) {

		Usuario usuario = getUsuario(URL_EDITAR);

		PessoaFisica pessoaFisica = null;
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			boolean update = false;
			if (email.length() != 0) {
				pessoaFisica.setEmail(email);
				update = true;
			}
			if (celularNumero.length() != 0) {
				pessoaFisica.getCelular().setNumero(celularNumero);
				update = true;
			}
			if (idDDD.length() != 0) {
				DDD ddd = new DDD();
				ddd.setIdDDD(Long.parseLong(idDDD));
				pessoaFisica.getCelular().setDdd(ddd);
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

		return pessoaFisica.toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_ENCONTRAR)
	public String encontrarPessoaFisica() {
		Usuario usuario = getUsuario(URL_ENCONTRAR);
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
