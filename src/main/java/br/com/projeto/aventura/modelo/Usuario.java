package br.com.projeto.aventura.modelo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.aventura.Util.UtilString;

@Entity(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "favor")
	private Long favor;

	@Column(name = "username", unique = true, nullable = false, length = 25)
	private String username;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "Usuario_Role", joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "idRole"))
	private Collection<Role> roles;

	@Column(name = "ativo")
	private boolean ativo;

	public Usuario(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public Usuario(String username, String password, Long favor) {
		this(username, password);
		setFavor(favor);
	}

	public Usuario() {

	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getFavor() {
		return favor;
	}

	public void setFavor(Long favor) {
		this.favor = favor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public void atualizarInstancia(Usuario usuario) {
		if (usuario.getPassword() != null) {
			setPassword(usuario.getPassword());
		}
	}
}
