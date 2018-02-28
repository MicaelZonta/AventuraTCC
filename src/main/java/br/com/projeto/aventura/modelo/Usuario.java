package br.com.projeto.aventura.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long 	id;
	private String 	nome;
	private String 	sobrenome;
	private Long 	celularNumero;
	private String 	email;
	
	public Usuario(String nome, String sobrenome, Long celularNumero, String email) {
		setNome(nome);
		setSobrenome(sobrenome);
		setCelularNumero(celularNumero);
		setEmail(email);
	}
	
	@SuppressWarnings("unused")
	private Usuario() {
		
	}
	
	public void validacao(String nome, String sobrenome, Long celularNumero, String email) {
		setNome(nome);
		setSobrenome(sobrenome);
		setCelularNumero(celularNumero);
		setEmail(email);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	private void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public Long getCelularNumero() {
		return celularNumero;
	}
	private void setCelularNumero(Long celularNumero) {
		this.celularNumero = celularNumero;
	}
	
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", celularNumero=" + celularNumero
				+ ", email=" + email + "]";
	}
	
}
