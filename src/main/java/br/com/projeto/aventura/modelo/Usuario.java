package br.com.projeto.aventura.modelo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity(name = "Usuario")
public class Usuario {

	@Id
	long idUsuario;

	@Column(name = "favor")
	long favor;

	@Column(name = "username", length = 25)
	String username;

	@Column(name = "password", length = 255)
	String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Usuario_Role", joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "idRole"))
	Collection<Role> roles;

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

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getFavor() {
		return favor;
	}

	public void setFavor(long favor) {
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

}
