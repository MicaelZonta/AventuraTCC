package br.com.projeto.aventura.modelo.abstrato;

import br.com.projeto.aventura.modelo.Celular;

public abstract class Pessoa {

	private String email;
	private Celular celular;
	
	public Pessoa(String email, Celular celular) {
		setEmail(email);
		setCelular(celular);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Celular getCelular() {
		return celular;
	}

	public void setCelular(Celular celular) {
		this.celular = celular;
	}

	
	
}
