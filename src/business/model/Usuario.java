package business.model;

import java.io.Serializable;

public class Usuario implements Serializable  {
	
	private String login;
	private String senha;
	
	public Usuario(String login, String senha){
		this.login = login;
		this.senha = senha;
		
	}
	
	public Usuario() {
		this.login = "";
		this.senha = "";
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	
}
