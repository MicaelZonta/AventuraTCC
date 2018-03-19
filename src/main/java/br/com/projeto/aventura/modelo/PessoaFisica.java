package br.com.projeto.aventura.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;

@Entity(name = "Pessoa_Fisica")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class PessoaFisica extends Pessoa {

	@Column(name = "nome", nullable = false, length = 20)
	private String nome;

	@Column(name = "sobrenome", nullable = false, length = 60)
	private String sobrenome;

	@Column(name = "dataNascimento", nullable = false)
	private Date dataNascimento;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPessoa")
	private Aventureiro aventureiro;

	@Column(name = "CPF", unique = true, nullable = false, length = 11)
	private String CPF;

	public PessoaFisica(String nome, String sobrenome, Date dataNascimento, Aventureiro aventureiro, String CPF,
			String email, Celular celular) {
		super(email, celular);
		setNome(nome);
		setSobrenome(sobrenome);
		setDataNascimento(dataNascimento);
		setAventureiro(aventureiro);
		setCPF(CPF);
	}
	public PessoaFisica() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Aventureiro getAventureiro() {
		return aventureiro;
	}

	public void setAventureiro(Aventureiro aventureiro) {
		this.aventureiro = aventureiro;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void atualizarInstancia(PessoaFisica p2) {

		if (p2.getCelular() != null) {
			if (p2.getCelular().getNumero() != null) {
				this.getCelular().setNumero(p2.getCelular().getNumero());
			}
			if (p2.getCelular().getDdd() != null) {
				this.getCelular().setDdd(p2.getCelular().getDdd());
			}
		}

		if (p2.getEmail() != null) {
			this.setEmail(p2.getEmail());
		}

	}

	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = "";
			jsonInString = mapper.writeValueAsString(this);
			return jsonInString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
