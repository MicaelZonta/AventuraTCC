
package br.com.projeto.aventura.modelo;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;

//Classe hipotética caso necessidade de expansão, por enquanto ignora, é apenas um gancho possível
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 7849561522523080235L;
	
	private String CNPJ;

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

}
