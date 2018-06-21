package br.com.projeto.aventura.recurso.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.view.Aventureiro_PessoaFisica;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
import br.com.projeto.aventura.servico.AvaliacaoServico;
import br.com.projeto.aventura.servico.AventureiroServico;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.UnidadeHabilidadeServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + PessoaFisicaRecurso.URL_HOME)
public class PessoaFisicaRecurso extends WebService {

	public static final String URL_HOME = "pessoa";
	public static final String URL_CADASTRAR = "cadastrar";
	public static final String URL_EDITAR = "editar";
	public static final String URL_ENCONTRAR = "encontrar";
	public static final String URL_AVALIAR = "avaliar";
	public static final String URL_LISTAR_HABILIDADES = "listarhabilidades";
	public static final String URL_LATITUDE_LONGITUDE = "latlng";

	PessoaFisicaServico pessoaFisicaServico;
	AvaliacaoServico avaliacaoServico;
	AventureiroServico aventureiroServico;
	UnidadeHabilidadeServico uniHabServico;

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

	public static String getUrlAvaliar() {
		return getUrlHome() + "/" + URL_AVALIAR;
	}

	public static String getUrlListarHabilidades() {
		return getUrlHome() + "/" + URL_LISTAR_HABILIDADES;
	}

	public static String getUrlLatitudeLongitude() {
		return getUrlHome() + "/" + URL_LATITUDE_LONGITUDE;
	}

	@Autowired
	public PessoaFisicaRecurso(UsuarioServico usuarioServ, PessoaFisicaServico pessoaFisicaServico,
			AvaliacaoServico avaliacaoServico, AventureiroServico aventureiroServico,
			UnidadeHabilidadeServico uniHabServico) {
		super(usuarioServ);
		this.pessoaFisicaServico = pessoaFisicaServico;
		this.avaliacaoServico = avaliacaoServico;
		this.aventureiroServico = aventureiroServico;
		this.uniHabServico = uniHabServico;
		WebServiceValidador validadorBasico = new WebServiceValidador();
		validadorBasico.addAutorizacao(RoleEnum.ADMIN, RoleEnum.USER);
		adicionarValidador(URL_CADASTRAR, validadorBasico);
		adicionarValidador(URL_EDITAR, validadorBasico);
		adicionarValidador(URL_ENCONTRAR, validadorBasico);
		adicionarValidador(URL_AVALIAR, validadorBasico);
		adicionarValidador(URL_LATITUDE_LONGITUDE, validadorBasico);
	}

	@RequestMapping(method = RequestMethod.PUT, value = URL_CADASTRAR)
	public Aventureiro_PessoaFisica cadastrarPessoaFisica(
			@RequestBody PessoaFisica pessoaFisica,
			@RequestParam(value = "longitude", defaultValue = "0") Double longitude,
			@RequestParam(value = "latitude", defaultValue = "0") Double latitude,
			@RequestParam(value = "idUsuario", defaultValue = "0") Long idUsuario) {

		pessoaFisica.setIdUsuario(idUsuario);
		Celular cel = pessoaFisica.getCelular();
		pessoaFisica.setCelular(null);

		Aventureiro_PessoaFisica av_pf = new Aventureiro_PessoaFisica();
		try {
			pessoaFisica = pessoaFisicaServico.cadastrarPessoaFisica(pessoaFisica);
			pessoaFisica.setCelular(cel);
			cel.setIdPessoa(pessoaFisica.getIdPessoa());
			pessoaFisica = pessoaFisicaServico.editarPessoaFisica(pessoaFisica);
			Aventureiro av = aventureiroServico.cadastrarAventureiro(pessoaFisica.getIdPessoa(), latitude, longitude);

			av_pf.setAventureiro(av);
			av_pf.setPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		return av_pf;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_EDITAR)
	public PessoaFisica editarPessoaFisica(
			@RequestBody PessoaFisica pessoaFisica2) {

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
	public Aventureiro_PessoaFisica encontrarPessoaFisica() {
		Usuario usuario = getUsuario(URL_ENCONTRAR);
		PessoaFisica pessoaFisica = null;
		Aventureiro_PessoaFisica av_pf = new Aventureiro_PessoaFisica();
		try {
			pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			Aventureiro av = aventureiroServico.encontrarAventureiroPorIdPessoa(pessoaFisica.getIdPessoa());

			av_pf.setAventureiro(av);
			av_pf.setPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return av_pf;
	}

	@RequestMapping(method = RequestMethod.PUT, value = URL_AVALIAR)
	public Avaliacao avaliarAventureiro(@RequestParam(value = "nota", defaultValue = "") Integer nota,
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestBody Missao missao,
			@RequestBody Usuario usuarioAvaliado) {
		Usuario usuario = getUsuario(URL_AVALIAR);
		Avaliacao avaliacao = null;
		try {
			avaliacao = avaliacaoServico.cadastrarAvaliacao(nota, descricao, missao, usuario, usuarioAvaliado);
			aventureiroServico.evoluirHabilidades(avaliacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return avaliacao;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_LISTAR_HABILIDADES)
	public List<UnidadeHabilidade> listarHabilidades() {
		Usuario usuario = getUsuario(URL_AVALIAR);
		List<UnidadeHabilidade> listHabilidades = new ArrayList<UnidadeHabilidade>();
		try {
			PessoaFisica pf = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			Aventureiro av = aventureiroServico.encontrarAventureiroPorIdPessoa(pf.getIdPessoa());
			listHabilidades = uniHabServico.listarUnidadeHabilidade(av.getIdUnidade());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return listHabilidades;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_LATITUDE_LONGITUDE)
	public void atualizarLatitudeLongitude(@RequestParam(value = "longitude", defaultValue = "0") Double longitude,
			@RequestParam(value = "latitude", defaultValue = "0") Double latitude) {
		Usuario usuario = getUsuario(URL_AVALIAR);
		try {
			PessoaFisica pf = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
			Aventureiro av = aventureiroServico.encontrarAventureiroPorIdPessoa(pf.getIdPessoa());
			av.getPosicao().setLatitude(latitude);
			av.getPosicao().setLongitude(longitude);
			aventureiroServico.atualizarAventureiro(av);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
	}
}
