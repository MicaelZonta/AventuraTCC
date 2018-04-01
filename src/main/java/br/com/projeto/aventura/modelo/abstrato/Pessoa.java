package br.com.projeto.aventura.modelo.abstrato;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.projeto.aventura.modelo.Celular;

@Entity(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = -1362553512477461677L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long idPessoa;

	@Column(name = "idUsuario", unique = true, nullable = false)
	long idUsuario;

	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPessoa")
	private Celular celular;

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
