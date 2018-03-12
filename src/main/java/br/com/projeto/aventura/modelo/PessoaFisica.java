package br.com.projeto.aventura.modelo;

import java.util.Date;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;

public class PessoaFisica extends Pessoa {

	// Atributos Pessoais
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private Aventureiro aventureiro;
	private String CPF;

	public PessoaFisica(String nome, String sobrenome, Date dataNascimento, 
			Aventureiro aventureiro, String CPF, String email, Celular celular) {
		super(email, celular);
		setNome(nome);
		setSobrenome(sobrenome);
		setDataNascimento(dataNascimento);
		setAventureiro(aventureiro);
		setCPF(CPF);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
