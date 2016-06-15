package model;

public class Cliente {

	
	private String ip;
	private String nome;
	private String login;
	
	
	public Cliente(String ip, String nome, String login) {	
		this.ip = ip;
		this.nome = nome;
		this.login = login;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}

}
