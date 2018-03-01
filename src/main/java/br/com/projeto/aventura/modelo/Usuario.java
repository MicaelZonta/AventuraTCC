package br.com.projeto.aventura.modelo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Usuario {

	long idConta;
	long favor;
	String username;
	String password;
	Collection<GrantedAuthority> roles;

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

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
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

	public Collection<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<GrantedAuthority> roles) {
		this.roles = roles;
	}

}
